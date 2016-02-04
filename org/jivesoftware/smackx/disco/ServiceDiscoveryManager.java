package org.jivesoftware.smackx.disco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.iqrequest.IQRequestHandler.Mode;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.packet.XMPPError.Condition;
import org.jivesoftware.smack.util.Objects;
import org.jivesoftware.smackx.caps.EntityCapsManager;
import org.jivesoftware.smackx.caps.EntityCapsManager.NodeVerHash;
import org.jivesoftware.smackx.disco.packet.DiscoverInfo;
import org.jivesoftware.smackx.disco.packet.DiscoverInfo.Identity;
import org.jivesoftware.smackx.disco.packet.DiscoverItems;
import org.jivesoftware.smackx.disco.packet.DiscoverItems.Item;
import org.jivesoftware.smackx.search.UserSearch;
import org.jivesoftware.smackx.xdata.packet.DataForm;
import org.jxmpp.util.cache.Cache;
import org.jxmpp.util.cache.ExpirationCache;

public class ServiceDiscoveryManager extends Manager {
    private static final String DEFAULT_IDENTITY_CATEGORY = "client";
    private static final String DEFAULT_IDENTITY_NAME = "Smack";
    private static final String DEFAULT_IDENTITY_TYPE = "pc";
    private static final Logger LOGGER;
    private static Identity defaultIdentity;
    private static Map<XMPPConnection, ServiceDiscoveryManager> instances;
    private EntityCapsManager capsManager;
    private DataForm extendedInfo;
    private final Set<String> features;
    private Set<Identity> identities;
    private Identity identity;
    private Map<String, NodeInformationProvider> nodeInformationProviders;
    private Cache<String, List<String>> services;

    /* renamed from: org.jivesoftware.smackx.disco.ServiceDiscoveryManager.1 */
    final class C10611 implements ConnectionCreationListener {
        C10611() {
        }

        public void connectionCreated(XMPPConnection xMPPConnection) {
            ServiceDiscoveryManager.getInstanceFor(xMPPConnection);
        }
    }

    /* renamed from: org.jivesoftware.smackx.disco.ServiceDiscoveryManager.2 */
    class C10622 extends AbstractIqRequestHandler {
        C10622(String str, String str2, Type type, Mode mode) {
            super(str, str2, type, mode);
        }

        public IQ handleIQRequest(IQ iq) {
            DiscoverItems discoverItems = (DiscoverItems) iq;
            IQ discoverItems2 = new DiscoverItems();
            discoverItems2.setType(Type.result);
            discoverItems2.setTo(discoverItems.getFrom());
            discoverItems2.setStanzaId(discoverItems.getStanzaId());
            discoverItems2.setNode(discoverItems.getNode());
            NodeInformationProvider access$000 = ServiceDiscoveryManager.this.getNodeInformationProvider(discoverItems.getNode());
            if (access$000 != null) {
                discoverItems2.addItems(access$000.getNodeItems());
                discoverItems2.addExtensions(access$000.getNodePacketExtensions());
            } else if (discoverItems.getNode() != null) {
                discoverItems2.setType(Type.error);
                discoverItems2.setError(new XMPPError(Condition.item_not_found));
            }
            return discoverItems2;
        }
    }

    /* renamed from: org.jivesoftware.smackx.disco.ServiceDiscoveryManager.3 */
    class C10633 extends AbstractIqRequestHandler {
        C10633(String str, String str2, Type type, Mode mode) {
            super(str, str2, type, mode);
        }

        public IQ handleIQRequest(IQ iq) {
            DiscoverInfo discoverInfo = (DiscoverInfo) iq;
            IQ discoverInfo2 = new DiscoverInfo();
            discoverInfo2.setType(Type.result);
            discoverInfo2.setTo(discoverInfo.getFrom());
            discoverInfo2.setStanzaId(discoverInfo.getStanzaId());
            discoverInfo2.setNode(discoverInfo.getNode());
            if (discoverInfo.getNode() == null) {
                ServiceDiscoveryManager.this.addDiscoverInfoTo(discoverInfo2);
            } else {
                NodeInformationProvider access$000 = ServiceDiscoveryManager.this.getNodeInformationProvider(discoverInfo.getNode());
                if (access$000 != null) {
                    discoverInfo2.addFeatures(access$000.getNodeFeatures());
                    discoverInfo2.addIdentities(access$000.getNodeIdentities());
                    discoverInfo2.addExtensions(access$000.getNodePacketExtensions());
                } else {
                    discoverInfo2.setType(Type.error);
                    discoverInfo2.setError(new XMPPError(Condition.item_not_found));
                }
            }
            return discoverInfo2;
        }
    }

    static {
        LOGGER = Logger.getLogger(ServiceDiscoveryManager.class.getName());
        defaultIdentity = new Identity(DEFAULT_IDENTITY_CATEGORY, DEFAULT_IDENTITY_NAME, DEFAULT_IDENTITY_TYPE);
        instances = new WeakHashMap();
        XMPPConnectionRegistry.addConnectionCreationListener(new C10611());
    }

    public static void setDefaultIdentity(Identity identity) {
        defaultIdentity = identity;
    }

    private ServiceDiscoveryManager(XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        this.identities = new HashSet();
        this.identity = defaultIdentity;
        this.features = new HashSet();
        this.extendedInfo = null;
        this.nodeInformationProviders = new ConcurrentHashMap();
        this.services = new ExpirationCache(25, 86400000);
        addFeature(DiscoverInfo.NAMESPACE);
        addFeature(DiscoverItems.NAMESPACE);
        xMPPConnection.registerIQRequestHandler(new C10622(UserSearch.ELEMENT, DiscoverItems.NAMESPACE, Type.get, Mode.async));
        xMPPConnection.registerIQRequestHandler(new C10633(UserSearch.ELEMENT, DiscoverInfo.NAMESPACE, Type.get, Mode.async));
    }

    public String getIdentityName() {
        return this.identity.getName();
    }

    public synchronized void setIdentity(Identity identity) {
        this.identity = (Identity) Objects.requireNonNull(identity, "Identity can not be null");
        renewEntityCapsVersion();
    }

    public Identity getIdentity() {
        return this.identity;
    }

    public String getIdentityType() {
        return this.identity.getType();
    }

    public synchronized void addIdentity(Identity identity) {
        this.identities.add(identity);
        renewEntityCapsVersion();
    }

    public synchronized boolean removeIdentity(Identity identity) {
        boolean z;
        if (identity.equals(this.identity)) {
            z = false;
        } else {
            this.identities.remove(identity);
            renewEntityCapsVersion();
            z = true;
        }
        return z;
    }

    public Set<Identity> getIdentities() {
        Set hashSet = new HashSet(this.identities);
        hashSet.add(defaultIdentity);
        return Collections.unmodifiableSet(hashSet);
    }

    public static synchronized ServiceDiscoveryManager getInstanceFor(XMPPConnection xMPPConnection) {
        ServiceDiscoveryManager serviceDiscoveryManager;
        synchronized (ServiceDiscoveryManager.class) {
            serviceDiscoveryManager = (ServiceDiscoveryManager) instances.get(xMPPConnection);
            if (serviceDiscoveryManager == null) {
                serviceDiscoveryManager = new ServiceDiscoveryManager(xMPPConnection);
                instances.put(xMPPConnection, serviceDiscoveryManager);
            }
        }
        return serviceDiscoveryManager;
    }

    public synchronized void addDiscoverInfoTo(DiscoverInfo discoverInfo) {
        discoverInfo.addIdentities(getIdentities());
        for (String addFeature : getFeatures()) {
            discoverInfo.addFeature(addFeature);
        }
        discoverInfo.addExtension(this.extendedInfo);
    }

    private NodeInformationProvider getNodeInformationProvider(String str) {
        if (str == null) {
            return null;
        }
        return (NodeInformationProvider) this.nodeInformationProviders.get(str);
    }

    public void setNodeInformationProvider(String str, NodeInformationProvider nodeInformationProvider) {
        this.nodeInformationProviders.put(str, nodeInformationProvider);
    }

    public void removeNodeInformationProvider(String str) {
        this.nodeInformationProviders.remove(str);
    }

    public synchronized List<String> getFeatures() {
        return new ArrayList(this.features);
    }

    public synchronized void addFeature(String str) {
        this.features.add(str);
        renewEntityCapsVersion();
    }

    public synchronized void removeFeature(String str) {
        this.features.remove(str);
        renewEntityCapsVersion();
    }

    public synchronized boolean includesFeature(String str) {
        return this.features.contains(str);
    }

    public synchronized void setExtendedInfo(DataForm dataForm) {
        this.extendedInfo = dataForm;
        renewEntityCapsVersion();
    }

    public DataForm getExtendedInfo() {
        return this.extendedInfo;
    }

    public List<ExtensionElement> getExtendedInfoAsList() {
        if (this.extendedInfo == null) {
            return null;
        }
        List<ExtensionElement> arrayList = new ArrayList(1);
        arrayList.add(this.extendedInfo);
        return arrayList;
    }

    public synchronized void removeExtendedInfo() {
        this.extendedInfo = null;
        renewEntityCapsVersion();
    }

    public DiscoverInfo discoverInfo(String str) {
        String str2 = null;
        if (str == null) {
            return discoverInfo(null, null);
        }
        DiscoverInfo discoverInfoByUser = EntityCapsManager.getDiscoverInfoByUser(str);
        if (discoverInfoByUser != null) {
            return discoverInfoByUser;
        }
        NodeVerHash nodeVerHashByJid = EntityCapsManager.getNodeVerHashByJid(str);
        if (nodeVerHashByJid != null) {
            str2 = nodeVerHashByJid.getNodeVer();
        }
        DiscoverInfo discoverInfo = discoverInfo(str, str2);
        if (nodeVerHashByJid == null || !EntityCapsManager.verifyDiscoverInfoVersion(nodeVerHashByJid.getVer(), nodeVerHashByJid.getHash(), discoverInfo)) {
            return discoverInfo;
        }
        EntityCapsManager.addDiscoverInfoByNode(nodeVerHashByJid.getNodeVer(), discoverInfo);
        return discoverInfo;
    }

    public DiscoverInfo discoverInfo(String str, String str2) {
        IQ discoverInfo = new DiscoverInfo();
        discoverInfo.setType(Type.get);
        discoverInfo.setTo(str);
        discoverInfo.setNode(str2);
        return (DiscoverInfo) connection().createPacketCollectorAndSend(discoverInfo).nextResultOrThrow();
    }

    public DiscoverItems discoverItems(String str) {
        return discoverItems(str, null);
    }

    public DiscoverItems discoverItems(String str, String str2) {
        IQ discoverItems = new DiscoverItems();
        discoverItems.setType(Type.get);
        discoverItems.setTo(str);
        discoverItems.setNode(str2);
        return (DiscoverItems) connection().createPacketCollectorAndSend(discoverItems).nextResultOrThrow();
    }

    public boolean canPublishItems(String str) {
        return canPublishItems(discoverInfo(str));
    }

    public static boolean canPublishItems(DiscoverInfo discoverInfo) {
        return discoverInfo.containsFeature("http://jabber.org/protocol/disco#publish");
    }

    public void publishItems(String str, DiscoverItems discoverItems) {
        publishItems(str, null, discoverItems);
    }

    public void publishItems(String str, String str2, DiscoverItems discoverItems) {
        discoverItems.setType(Type.set);
        discoverItems.setTo(str);
        discoverItems.setNode(str2);
        connection().createPacketCollectorAndSend(discoverItems).nextResultOrThrow();
    }

    public boolean serverSupportsFeature(String str) {
        return supportsFeature(connection().getServiceName(), str);
    }

    public boolean supportsFeature(String str, String str2) {
        return discoverInfo(str).containsFeature(str2);
    }

    public List<String> findServices(String str, boolean z, boolean z2) {
        Throwable e;
        String serviceName = connection().getServiceName();
        if (z2) {
            List<String> list = (List) this.services.get(str);
            if (list != null) {
                return list;
            }
        }
        List<String> linkedList = new LinkedList();
        try {
            if (discoverInfo(serviceName).containsFeature(str)) {
                linkedList.add(serviceName);
                if (z) {
                    if (z2) {
                        this.services.put(str, linkedList);
                    }
                    return linkedList;
                }
            }
            try {
                for (Item item : discoverItems(serviceName).getItems()) {
                    try {
                        if (discoverInfo(item.getEntityID()).containsFeature(str)) {
                            linkedList.add(item.getEntityID());
                            if (z) {
                                break;
                            }
                        } else {
                            continue;
                        }
                    } catch (XMPPErrorException e2) {
                        e = e2;
                        LOGGER.log(Level.WARNING, "Exception while discovering info for feature " + str + " of " + item.getEntityID() + " node: " + item.getNode(), e);
                    } catch (NoResponseException e3) {
                        e = e3;
                        LOGGER.log(Level.WARNING, "Exception while discovering info for feature " + str + " of " + item.getEntityID() + " node: " + item.getNode(), e);
                    }
                }
                if (z2) {
                    this.services.put(str, linkedList);
                }
                return linkedList;
            } catch (Throwable e4) {
                LOGGER.log(Level.WARNING, "Could not discover items about service", e4);
                return linkedList;
            }
        } catch (Throwable e42) {
            LOGGER.log(Level.WARNING, "Could not discover information about service", e42);
            return linkedList;
        }
    }

    public void setEntityCapsManager(EntityCapsManager entityCapsManager) {
        this.capsManager = entityCapsManager;
    }

    private void renewEntityCapsVersion() {
        if (this.capsManager != null && this.capsManager.entityCapsEnabled()) {
            this.capsManager.updateLocalEntityCaps();
        }
    }
}
