package org.jivesoftware.smackx.muc;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.filter.NotFilter;
import org.jivesoftware.smack.filter.StanzaExtensionFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.filter.StanzaTypeFilter;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.disco.AbstractNodeInformationProvider;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.disco.packet.DiscoverItems.Item;
import org.jivesoftware.smackx.muc.packet.MUCInitialPresence;
import org.jivesoftware.smackx.muc.packet.MUCUser;
import org.jivesoftware.smackx.muc.packet.MUCUser.Decline;

public class MultiUserChatManager extends Manager {
    private static final String DISCO_NODE = "http://jabber.org/protocol/muc#rooms";
    private static final Map<XMPPConnection, MultiUserChatManager> INSTANCES;
    private static final StanzaFilter INVITATION_FILTER;
    private final Set<InvitationListener> invitationsListeners;
    private final Set<String> joinedRooms;
    private final Map<String, WeakReference<MultiUserChat>> multiUserChats;

    /* renamed from: org.jivesoftware.smackx.muc.MultiUserChatManager.1 */
    final class C10891 implements ConnectionCreationListener {

        /* renamed from: org.jivesoftware.smackx.muc.MultiUserChatManager.1.1 */
        class C10881 extends AbstractNodeInformationProvider {
            final /* synthetic */ WeakReference val$weakRefConnection;

            C10881(WeakReference weakReference) {
                this.val$weakRefConnection = weakReference;
            }

            public List<Item> getNodeItems() {
                XMPPConnection xMPPConnection = (XMPPConnection) this.val$weakRefConnection.get();
                if (xMPPConnection == null) {
                    return Collections.emptyList();
                }
                Set<String> joinedRooms = MultiUserChatManager.getInstanceFor(xMPPConnection).getJoinedRooms();
                List<Item> arrayList = new ArrayList();
                for (String item : joinedRooms) {
                    arrayList.add(new Item(item));
                }
                return arrayList;
            }
        }

        C10891() {
        }

        public void connectionCreated(XMPPConnection xMPPConnection) {
            ServiceDiscoveryManager.getInstanceFor(xMPPConnection).addFeature(MUCInitialPresence.NAMESPACE);
            ServiceDiscoveryManager.getInstanceFor(xMPPConnection).setNodeInformationProvider(MultiUserChatManager.DISCO_NODE, new C10881(new WeakReference(xMPPConnection)));
        }
    }

    /* renamed from: org.jivesoftware.smackx.muc.MultiUserChatManager.2 */
    class C10902 implements StanzaListener {
        C10902() {
        }

        public void processPacket(Stanza stanza) {
            Message message = (Message) stanza;
            MUCUser from = MUCUser.from(message);
            if (from.getInvite() != null) {
                MultiUserChat multiUserChat = MultiUserChatManager.this.getMultiUserChat(stanza.getFrom());
                for (InvitationListener invitationReceived : MultiUserChatManager.this.invitationsListeners) {
                    invitationReceived.invitationReceived(MultiUserChatManager.this.connection(), multiUserChat, from.getInvite().getFrom(), from.getInvite().getReason(), from.getPassword(), message);
                }
            }
        }
    }

    static {
        XMPPConnectionRegistry.addConnectionCreationListener(new C10891());
        INSTANCES = new WeakHashMap();
        INVITATION_FILTER = new AndFilter(StanzaTypeFilter.MESSAGE, new StanzaExtensionFilter(new MUCUser()), new NotFilter(MessageTypeFilter.ERROR));
    }

    public static synchronized MultiUserChatManager getInstanceFor(XMPPConnection xMPPConnection) {
        MultiUserChatManager multiUserChatManager;
        synchronized (MultiUserChatManager.class) {
            multiUserChatManager = (MultiUserChatManager) INSTANCES.get(xMPPConnection);
            if (multiUserChatManager == null) {
                multiUserChatManager = new MultiUserChatManager(xMPPConnection);
                INSTANCES.put(xMPPConnection, multiUserChatManager);
            }
        }
        return multiUserChatManager;
    }

    private MultiUserChatManager(XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        this.invitationsListeners = new CopyOnWriteArraySet();
        this.joinedRooms = new HashSet();
        this.multiUserChats = new HashMap();
        xMPPConnection.addAsyncStanzaListener(new C10902(), INVITATION_FILTER);
    }

    public synchronized MultiUserChat getMultiUserChat(String str) {
        MultiUserChat createNewMucAndAddToMap;
        WeakReference weakReference = (WeakReference) this.multiUserChats.get(str);
        if (weakReference == null) {
            createNewMucAndAddToMap = createNewMucAndAddToMap(str);
        } else {
            createNewMucAndAddToMap = (MultiUserChat) weakReference.get();
            if (createNewMucAndAddToMap == null) {
                createNewMucAndAddToMap = createNewMucAndAddToMap(str);
            }
        }
        return createNewMucAndAddToMap;
    }

    private MultiUserChat createNewMucAndAddToMap(String str) {
        MultiUserChat multiUserChat = new MultiUserChat(connection(), str, this);
        this.multiUserChats.put(str, new WeakReference(multiUserChat));
        return multiUserChat;
    }

    public boolean isServiceEnabled(String str) {
        return ServiceDiscoveryManager.getInstanceFor(connection()).supportsFeature(str, MUCInitialPresence.NAMESPACE);
    }

    public Set<String> getJoinedRooms() {
        return Collections.unmodifiableSet(this.joinedRooms);
    }

    public List<String> getJoinedRooms(String str) {
        List<Item> items = ServiceDiscoveryManager.getInstanceFor(connection()).discoverItems(str, DISCO_NODE).getItems();
        List<String> arrayList = new ArrayList(items.size());
        for (Item entityID : items) {
            arrayList.add(entityID.getEntityID());
        }
        return arrayList;
    }

    public RoomInfo getRoomInfo(String str) {
        return new RoomInfo(ServiceDiscoveryManager.getInstanceFor(connection()).discoverInfo(str));
    }

    public List<String> getServiceNames() {
        return ServiceDiscoveryManager.getInstanceFor(connection()).findServices(MUCInitialPresence.NAMESPACE, false, false);
    }

    public List<HostedRoom> getHostedRooms(String str) {
        List<Item> items = ServiceDiscoveryManager.getInstanceFor(connection()).discoverItems(str).getItems();
        List<HostedRoom> arrayList = new ArrayList(items.size());
        for (Item hostedRoom : items) {
            arrayList.add(new HostedRoom(hostedRoom));
        }
        return arrayList;
    }

    public void decline(String str, String str2, String str3) {
        Stanza message = new Message(str);
        ExtensionElement mUCUser = new MUCUser();
        Decline decline = new Decline();
        decline.setTo(str2);
        decline.setReason(str3);
        mUCUser.setDecline(decline);
        message.addExtension(mUCUser);
        connection().sendStanza(message);
    }

    public void addInvitationListener(InvitationListener invitationListener) {
        this.invitationsListeners.add(invitationListener);
    }

    public void removeInvitationListener(InvitationListener invitationListener) {
        this.invitationsListeners.remove(invitationListener);
    }

    void addJoinedRoom(String str) {
        this.joinedRooms.add(str);
    }

    void removeJoinedRoom(String str) {
        this.joinedRooms.remove(str);
    }
}
