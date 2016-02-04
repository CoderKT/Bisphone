package org.jivesoftware.smackx.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.iqrequest.IQRequestHandler.Mode;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.packet.XMPPError.Condition;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.commands.AdHocCommand.Action;
import org.jivesoftware.smackx.commands.AdHocCommand.SpecificErrorCondition;
import org.jivesoftware.smackx.commands.AdHocCommand.Status;
import org.jivesoftware.smackx.commands.packet.AdHocCommandData;
import org.jivesoftware.smackx.commands.packet.AdHocCommandData.SpecificError;
import org.jivesoftware.smackx.disco.AbstractNodeInformationProvider;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.disco.packet.DiscoverInfo.Identity;
import org.jivesoftware.smackx.disco.packet.DiscoverItems;
import org.jivesoftware.smackx.disco.packet.DiscoverItems.Item;
import org.jivesoftware.smackx.xdata.Form;
import org.jivesoftware.smackx.xdata.packet.DataForm;

public class AdHocCommandManager extends Manager {
    private static final Logger LOGGER;
    public static final String NAMESPACE = "http://jabber.org/protocol/commands";
    private static final int SESSION_TIMEOUT = 120;
    private static Map<XMPPConnection, AdHocCommandManager> instances;
    private final Map<String, AdHocCommandInfo> commands;
    private final Map<String, LocalCommand> executingCommands;
    private final ServiceDiscoveryManager serviceDiscoveryManager;
    private Thread sessionsSweeper;

    /* renamed from: org.jivesoftware.smackx.commands.AdHocCommandManager.1 */
    final class C10551 implements ConnectionCreationListener {
        C10551() {
        }

        public void connectionCreated(XMPPConnection xMPPConnection) {
            AdHocCommandManager.getAddHocCommandsManager(xMPPConnection);
        }
    }

    /* renamed from: org.jivesoftware.smackx.commands.AdHocCommandManager.2 */
    class C10562 extends AbstractNodeInformationProvider {
        C10562() {
        }

        public List<Item> getNodeItems() {
            List<Item> arrayList = new ArrayList();
            for (AdHocCommandInfo adHocCommandInfo : AdHocCommandManager.this.getRegisteredCommands()) {
                Item item = new Item(adHocCommandInfo.getOwnerJID());
                item.setName(adHocCommandInfo.getName());
                item.setNode(adHocCommandInfo.getNode());
                arrayList.add(item);
            }
            return arrayList;
        }
    }

    /* renamed from: org.jivesoftware.smackx.commands.AdHocCommandManager.3 */
    class C10573 extends AbstractIqRequestHandler {
        C10573(String str, String str2, Type type, Mode mode) {
            super(str, str2, type, mode);
        }

        public IQ handleIQRequest(IQ iq) {
            Throwable e;
            try {
                return AdHocCommandManager.this.processAdHocCommand((AdHocCommandData) iq);
            } catch (NoResponseException e2) {
                e = e2;
                AdHocCommandManager.LOGGER.log(Level.INFO, "processAdHocCommand threw exceptino", e);
                return null;
            } catch (NotConnectedException e3) {
                e = e3;
                AdHocCommandManager.LOGGER.log(Level.INFO, "processAdHocCommand threw exceptino", e);
                return null;
            }
        }
    }

    /* renamed from: org.jivesoftware.smackx.commands.AdHocCommandManager.4 */
    class C10584 implements LocalCommandFactory {
        final /* synthetic */ Class val$clazz;

        C10584(Class cls) {
            this.val$clazz = cls;
        }

        public LocalCommand getInstance() {
            return (LocalCommand) this.val$clazz.newInstance();
        }
    }

    /* renamed from: org.jivesoftware.smackx.commands.AdHocCommandManager.5 */
    class C10595 extends AbstractNodeInformationProvider {
        final /* synthetic */ String val$name;

        C10595(String str) {
            this.val$name = str;
        }

        public List<String> getNodeFeatures() {
            List<String> arrayList = new ArrayList();
            arrayList.add(AdHocCommandManager.NAMESPACE);
            arrayList.add(DataForm.NAMESPACE);
            return arrayList;
        }

        public List<Identity> getNodeIdentities() {
            List<Identity> arrayList = new ArrayList();
            arrayList.add(new Identity("automation", this.val$name, "command-node"));
            return arrayList;
        }
    }

    /* renamed from: org.jivesoftware.smackx.commands.AdHocCommandManager.6 */
    class C10606 implements Runnable {
        C10606() {
        }

        public void run() {
            while (true) {
                for (String str : AdHocCommandManager.this.executingCommands.keySet()) {
                    LocalCommand localCommand = (LocalCommand) AdHocCommandManager.this.executingCommands.get(str);
                    if (localCommand != null) {
                        if (System.currentTimeMillis() - localCommand.getCreationDate() > 240000) {
                            AdHocCommandManager.this.executingCommands.remove(str);
                        }
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    class AdHocCommandInfo {
        private LocalCommandFactory factory;
        private String name;
        private String node;
        private String ownerJID;

        public AdHocCommandInfo(String str, String str2, String str3, LocalCommandFactory localCommandFactory) {
            this.node = str;
            this.name = str2;
            this.ownerJID = str3;
            this.factory = localCommandFactory;
        }

        public LocalCommand getCommandInstance() {
            return this.factory.getInstance();
        }

        public String getName() {
            return this.name;
        }

        public String getNode() {
            return this.node;
        }

        public String getOwnerJID() {
            return this.ownerJID;
        }
    }

    static {
        LOGGER = Logger.getLogger(AdHocCommandManager.class.getName());
        instances = new WeakHashMap();
        XMPPConnectionRegistry.addConnectionCreationListener(new C10551());
    }

    public static synchronized AdHocCommandManager getAddHocCommandsManager(XMPPConnection xMPPConnection) {
        AdHocCommandManager adHocCommandManager;
        synchronized (AdHocCommandManager.class) {
            adHocCommandManager = (AdHocCommandManager) instances.get(xMPPConnection);
            if (adHocCommandManager == null) {
                adHocCommandManager = new AdHocCommandManager(xMPPConnection);
                instances.put(xMPPConnection, adHocCommandManager);
            }
        }
        return adHocCommandManager;
    }

    private AdHocCommandManager(XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        this.commands = new ConcurrentHashMap();
        this.executingCommands = new ConcurrentHashMap();
        this.serviceDiscoveryManager = ServiceDiscoveryManager.getInstanceFor(xMPPConnection);
        ServiceDiscoveryManager.getInstanceFor(xMPPConnection).addFeature(NAMESPACE);
        ServiceDiscoveryManager.getInstanceFor(xMPPConnection).setNodeInformationProvider(NAMESPACE, new C10562());
        xMPPConnection.registerIQRequestHandler(new C10573(AdHocCommandData.ELEMENT, NAMESPACE, Type.set, Mode.async));
        this.sessionsSweeper = null;
    }

    public void registerCommand(String str, String str2, Class<? extends LocalCommand> cls) {
        registerCommand(str, str2, new C10584(cls));
    }

    public void registerCommand(String str, String str2, LocalCommandFactory localCommandFactory) {
        this.commands.put(str, new AdHocCommandInfo(str, str2, connection().getUser(), localCommandFactory));
        this.serviceDiscoveryManager.setNodeInformationProvider(str, new C10595(str2));
    }

    public DiscoverItems discoverCommands(String str) {
        return this.serviceDiscoveryManager.discoverItems(str, NAMESPACE);
    }

    public void publishCommands(String str) {
        DiscoverItems discoverItems = new DiscoverItems();
        for (AdHocCommandInfo adHocCommandInfo : getRegisteredCommands()) {
            Item item = new Item(adHocCommandInfo.getOwnerJID());
            item.setName(adHocCommandInfo.getName());
            item.setNode(adHocCommandInfo.getNode());
            discoverItems.addItem(item);
        }
        this.serviceDiscoveryManager.publishItems(str, NAMESPACE, discoverItems);
    }

    public RemoteCommand getRemoteCommand(String str, String str2) {
        return new RemoteCommand(connection(), str2, str);
    }

    private IQ processAdHocCommand(AdHocCommandData adHocCommandData) {
        AdHocCommandData adHocCommandData2 = new AdHocCommandData();
        adHocCommandData2.setTo(adHocCommandData.getFrom());
        adHocCommandData2.setStanzaId(adHocCommandData.getStanzaId());
        adHocCommandData2.setNode(adHocCommandData.getNode());
        adHocCommandData2.setId(adHocCommandData.getTo());
        String sessionID = adHocCommandData.getSessionID();
        String node = adHocCommandData.getNode();
        LocalCommand localCommand;
        if (sessionID != null) {
            localCommand = (LocalCommand) this.executingCommands.get(sessionID);
            if (localCommand == null) {
                return respondError(adHocCommandData2, Condition.bad_request, SpecificErrorCondition.badSessionid);
            }
            if (System.currentTimeMillis() - localCommand.getCreationDate() > 120000) {
                this.executingCommands.remove(sessionID);
                return respondError(adHocCommandData2, Condition.not_allowed, SpecificErrorCondition.sessionExpired);
            }
            synchronized (localCommand) {
                Action action = adHocCommandData.getAction();
                IQ respondError;
                if (action == null || !action.equals(Action.unknown)) {
                    if (action == null || Action.execute.equals(action)) {
                        action = localCommand.getExecuteAction();
                    }
                    if (localCommand.isValidAction(action)) {
                        try {
                            adHocCommandData2.setType(Type.result);
                            localCommand.setData(adHocCommandData2);
                            if (Action.next.equals(action)) {
                                localCommand.incrementStage();
                                localCommand.next(new Form(adHocCommandData.getForm()));
                                if (localCommand.isLastStage()) {
                                    adHocCommandData2.setStatus(Status.completed);
                                } else {
                                    adHocCommandData2.setStatus(Status.executing);
                                }
                            } else if (Action.complete.equals(action)) {
                                localCommand.incrementStage();
                                localCommand.complete(new Form(adHocCommandData.getForm()));
                                adHocCommandData2.setStatus(Status.completed);
                                this.executingCommands.remove(sessionID);
                            } else if (Action.prev.equals(action)) {
                                localCommand.decrementStage();
                                localCommand.prev();
                            } else if (Action.cancel.equals(action)) {
                                localCommand.cancel();
                                adHocCommandData2.setStatus(Status.canceled);
                                this.executingCommands.remove(sessionID);
                            }
                            return adHocCommandData2;
                        } catch (XMPPErrorException e) {
                            XMPPError xMPPError = e.getXMPPError();
                            if (XMPPError.Type.CANCEL.equals(xMPPError.getType())) {
                                adHocCommandData2.setStatus(Status.canceled);
                                this.executingCommands.remove(sessionID);
                            }
                            return respondError(adHocCommandData2, xMPPError);
                        }
                    }
                    respondError = respondError(adHocCommandData2, Condition.bad_request, SpecificErrorCondition.badAction);
                    return respondError;
                }
                respondError = respondError(adHocCommandData2, Condition.bad_request, SpecificErrorCondition.malformedAction);
                return respondError;
            }
        } else if (!this.commands.containsKey(node)) {
            return respondError(adHocCommandData2, Condition.item_not_found);
        } else {
            String randomString = StringUtils.randomString(15);
            try {
                localCommand = newInstanceOfCmd(node, randomString);
                adHocCommandData2.setType(Type.result);
                localCommand.setData(adHocCommandData2);
                if (!localCommand.hasPermission(adHocCommandData.getFrom())) {
                    return respondError(adHocCommandData2, Condition.forbidden);
                }
                Action action2 = adHocCommandData.getAction();
                if (action2 != null && action2.equals(Action.unknown)) {
                    return respondError(adHocCommandData2, Condition.bad_request, SpecificErrorCondition.malformedAction);
                }
                if (action2 != null && !action2.equals(Action.execute)) {
                    return respondError(adHocCommandData2, Condition.bad_request, SpecificErrorCondition.badAction);
                }
                localCommand.incrementStage();
                localCommand.execute();
                if (localCommand.isLastStage()) {
                    adHocCommandData2.setStatus(Status.completed);
                } else {
                    adHocCommandData2.setStatus(Status.executing);
                    this.executingCommands.put(randomString, localCommand);
                    if (this.sessionsSweeper == null) {
                        this.sessionsSweeper = new Thread(new C10606());
                        this.sessionsSweeper.setDaemon(true);
                        this.sessionsSweeper.start();
                    }
                }
                return adHocCommandData2;
            } catch (XMPPErrorException e2) {
                XMPPError xMPPError2 = e2.getXMPPError();
                if (XMPPError.Type.CANCEL.equals(xMPPError2.getType())) {
                    adHocCommandData2.setStatus(Status.canceled);
                    this.executingCommands.remove(randomString);
                }
                return respondError(adHocCommandData2, xMPPError2);
            }
        }
    }

    private IQ respondError(AdHocCommandData adHocCommandData, Condition condition) {
        return respondError(adHocCommandData, new XMPPError(condition));
    }

    private static IQ respondError(AdHocCommandData adHocCommandData, Condition condition, SpecificErrorCondition specificErrorCondition) {
        return respondError(adHocCommandData, new XMPPError(condition, new SpecificError(specificErrorCondition)));
    }

    private static IQ respondError(AdHocCommandData adHocCommandData, XMPPError xMPPError) {
        adHocCommandData.setType(Type.error);
        adHocCommandData.setError(xMPPError);
        return adHocCommandData;
    }

    private LocalCommand newInstanceOfCmd(String str, String str2) {
        AdHocCommandInfo adHocCommandInfo = (AdHocCommandInfo) this.commands.get(str);
        try {
            LocalCommand commandInstance = adHocCommandInfo.getCommandInstance();
            commandInstance.setSessionID(str2);
            commandInstance.setName(adHocCommandInfo.getName());
            commandInstance.setNode(adHocCommandInfo.getNode());
            return commandInstance;
        } catch (InstantiationException e) {
            throw new XMPPErrorException(new XMPPError(Condition.internal_server_error));
        } catch (IllegalAccessException e2) {
            throw new XMPPErrorException(new XMPPError(Condition.internal_server_error));
        }
    }

    private Collection<AdHocCommandInfo> getRegisteredCommands() {
        return this.commands.values();
    }
}
