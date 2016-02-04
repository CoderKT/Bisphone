package org.jivesoftware.smackx.caps;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.AbstractConnectionListener;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.NotFilter;
import org.jivesoftware.smack.filter.PresenceTypeFilter;
import org.jivesoftware.smack.filter.StanzaExtensionFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.filter.StanzaTypeFilter;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.stringencoder.Base64;
import org.jivesoftware.smackx.caps.cache.EntityCapsPersistentCache;
import org.jivesoftware.smackx.caps.packet.CapsExtension;
import org.jivesoftware.smackx.disco.AbstractNodeInformationProvider;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.disco.packet.DiscoverInfo;
import org.jivesoftware.smackx.disco.packet.DiscoverInfo.Feature;
import org.jivesoftware.smackx.disco.packet.DiscoverInfo.Identity;
import org.jivesoftware.smackx.xdata.FormField;
import org.jivesoftware.smackx.xdata.packet.DataForm;
import org.jxmpp.util.cache.LruCache;

public class EntityCapsManager extends Manager {
    private static final LruCache<String, DiscoverInfo> CAPS_CACHE;
    private static String DEFAULT_ENTITY_NODE = null;
    private static final String DEFAULT_HASH = "SHA-1";
    public static final String ELEMENT = "c";
    private static final LruCache<String, NodeVerHash> JID_TO_NODEVER_CACHE;
    private static final Logger LOGGER;
    public static final String NAMESPACE = "http://jabber.org/protocol/caps";
    private static final StanzaFilter PRESENCES_WITHOUT_CAPS;
    private static final StanzaFilter PRESENCES_WITH_CAPS;
    private static final Map<String, MessageDigest> SUPPORTED_HASHES;
    private static boolean autoEnableEntityCaps;
    private static Map<XMPPConnection, EntityCapsManager> instances;
    protected static EntityCapsPersistentCache persistentCache;
    private CapsVersionAndHash currentCapsVersion;
    private boolean entityCapsEnabled;
    private String entityNode;
    private final Queue<CapsVersionAndHash> lastLocalCapsVersions;
    private volatile Presence presenceSend;
    private final ServiceDiscoveryManager sdm;

    /* renamed from: org.jivesoftware.smackx.caps.EntityCapsManager.1 */
    final class C10461 implements ConnectionCreationListener {
        C10461() {
        }

        public void connectionCreated(XMPPConnection xMPPConnection) {
            EntityCapsManager.getInstanceFor(xMPPConnection);
        }
    }

    /* renamed from: org.jivesoftware.smackx.caps.EntityCapsManager.2 */
    class C10472 extends AbstractConnectionListener {
        C10472() {
        }

        public void connected(XMPPConnection xMPPConnection) {
            processCapsStreamFeatureIfAvailable(xMPPConnection);
        }

        public void authenticated(XMPPConnection xMPPConnection, boolean z) {
            processCapsStreamFeatureIfAvailable(xMPPConnection);
            if (!z) {
                EntityCapsManager.this.presenceSend = null;
            }
        }

        private void processCapsStreamFeatureIfAvailable(XMPPConnection xMPPConnection) {
            CapsExtension capsExtension = (CapsExtension) xMPPConnection.getFeature(EntityCapsManager.ELEMENT, EntityCapsManager.NAMESPACE);
            if (capsExtension != null) {
                EntityCapsManager.addCapsExtensionInfo(xMPPConnection.getServiceName(), capsExtension);
            }
        }
    }

    /* renamed from: org.jivesoftware.smackx.caps.EntityCapsManager.3 */
    class C10483 implements StanzaListener {
        C10483() {
        }

        public void processPacket(Stanza stanza) {
            if (EntityCapsManager.this.entityCapsEnabled()) {
                EntityCapsManager.addCapsExtensionInfo(stanza.getFrom(), CapsExtension.from(stanza));
            }
        }
    }

    /* renamed from: org.jivesoftware.smackx.caps.EntityCapsManager.4 */
    class C10494 implements StanzaListener {
        C10494() {
        }

        public void processPacket(Stanza stanza) {
            EntityCapsManager.JID_TO_NODEVER_CACHE.remove(stanza.getFrom());
        }
    }

    /* renamed from: org.jivesoftware.smackx.caps.EntityCapsManager.5 */
    class C10505 implements StanzaListener {
        C10505() {
        }

        public void processPacket(Stanza stanza) {
            EntityCapsManager.this.presenceSend = (Presence) stanza;
        }
    }

    /* renamed from: org.jivesoftware.smackx.caps.EntityCapsManager.6 */
    class C10516 implements StanzaListener {
        C10516() {
        }

        public void processPacket(Stanza stanza) {
            if (EntityCapsManager.this.entityCapsEnabled) {
                CapsVersionAndHash capsVersionAndHash = EntityCapsManager.this.getCapsVersionAndHash();
                stanza.overrideExtension(new CapsExtension(EntityCapsManager.this.entityNode, capsVersionAndHash.version, capsVersionAndHash.hash));
                return;
            }
            stanza.removeExtension(EntityCapsManager.ELEMENT, EntityCapsManager.NAMESPACE);
        }
    }

    /* renamed from: org.jivesoftware.smackx.caps.EntityCapsManager.7 */
    class C10527 extends AbstractNodeInformationProvider {
        List<String> features;
        List<ExtensionElement> packetExtensions;
        final /* synthetic */ List val$identities;

        C10527(List list) {
            this.val$identities = list;
            this.features = EntityCapsManager.this.sdm.getFeatures();
            this.packetExtensions = EntityCapsManager.this.sdm.getExtendedInfoAsList();
        }

        public List<String> getNodeFeatures() {
            return this.features;
        }

        public List<Identity> getNodeIdentities() {
            return this.val$identities;
        }

        public List<ExtensionElement> getNodePacketExtensions() {
            return this.packetExtensions;
        }
    }

    /* renamed from: org.jivesoftware.smackx.caps.EntityCapsManager.8 */
    final class C10538 implements Comparator<FormField> {
        C10538() {
        }

        public int compare(FormField formField, FormField formField2) {
            return formField.getVariable().compareTo(formField2.getVariable());
        }
    }

    public class NodeVerHash {
        private String hash;
        private String node;
        private String nodeVer;
        private String ver;

        NodeVerHash(String str, CapsVersionAndHash capsVersionAndHash) {
            this(str, capsVersionAndHash.version, capsVersionAndHash.hash);
        }

        NodeVerHash(String str, String str2, String str3) {
            this.node = str;
            this.ver = str2;
            this.hash = str3;
            this.nodeVer = str + "#" + str2;
        }

        public String getNodeVer() {
            return this.nodeVer;
        }

        public String getNode() {
            return this.node;
        }

        public String getHash() {
            return this.hash;
        }

        public String getVer() {
            return this.ver;
        }
    }

    static {
        LOGGER = Logger.getLogger(EntityCapsManager.class.getName());
        SUPPORTED_HASHES = new HashMap();
        DEFAULT_ENTITY_NODE = "http://www.igniterealtime.org/projects/smack";
        autoEnableEntityCaps = true;
        instances = new WeakHashMap();
        PRESENCES_WITH_CAPS = new AndFilter(new StanzaTypeFilter(Presence.class), new StanzaExtensionFilter(ELEMENT, NAMESPACE));
        PRESENCES_WITHOUT_CAPS = new AndFilter(new StanzaTypeFilter(Presence.class), new NotFilter(new StanzaExtensionFilter(ELEMENT, NAMESPACE)));
        CAPS_CACHE = new LruCache(1000);
        JID_TO_NODEVER_CACHE = new LruCache(10000);
        XMPPConnectionRegistry.addConnectionCreationListener(new C10461());
        try {
            SUPPORTED_HASHES.put(DEFAULT_HASH, MessageDigest.getInstance(DEFAULT_HASH));
        } catch (NoSuchAlgorithmException e) {
        }
    }

    public static void setDefaultEntityNode(String str) {
        DEFAULT_ENTITY_NODE = str;
    }

    public static void addDiscoverInfoByNode(String str, DiscoverInfo discoverInfo) {
        CAPS_CACHE.put(str, discoverInfo);
        if (persistentCache != null) {
            persistentCache.addDiscoverInfoByNodePersistent(str, discoverInfo);
        }
    }

    public static String getNodeVersionByJid(String str) {
        NodeVerHash nodeVerHash = (NodeVerHash) JID_TO_NODEVER_CACHE.get(str);
        if (nodeVerHash != null) {
            return nodeVerHash.nodeVer;
        }
        return null;
    }

    public static NodeVerHash getNodeVerHashByJid(String str) {
        return (NodeVerHash) JID_TO_NODEVER_CACHE.get(str);
    }

    public static DiscoverInfo getDiscoverInfoByUser(String str) {
        NodeVerHash nodeVerHash = (NodeVerHash) JID_TO_NODEVER_CACHE.get(str);
        if (nodeVerHash == null) {
            return null;
        }
        return getDiscoveryInfoByNodeVer(nodeVerHash.nodeVer);
    }

    public static DiscoverInfo getDiscoveryInfoByNodeVer(String str) {
        DiscoverInfo discoverInfo = (DiscoverInfo) CAPS_CACHE.get(str);
        if (discoverInfo == null && persistentCache != null) {
            discoverInfo = persistentCache.lookup(str);
            if (discoverInfo != null) {
                CAPS_CACHE.put(str, discoverInfo);
            }
        }
        DiscoverInfo discoverInfo2 = discoverInfo;
        if (discoverInfo2 != null) {
            return new DiscoverInfo(discoverInfo2);
        }
        return discoverInfo2;
    }

    public static void setPersistentCache(EntityCapsPersistentCache entityCapsPersistentCache) {
        persistentCache = entityCapsPersistentCache;
    }

    public static void setMaxsCacheSizes(int i, int i2) {
        JID_TO_NODEVER_CACHE.m13452a(i);
        CAPS_CACHE.m13452a(i2);
    }

    public static void clearMemoryCache() {
        JID_TO_NODEVER_CACHE.clear();
        CAPS_CACHE.clear();
    }

    private static void addCapsExtensionInfo(String str, CapsExtension capsExtension) {
        String hash = capsExtension.getHash();
        if (SUPPORTED_HASHES.containsKey(hash.toUpperCase(Locale.US))) {
            hash = hash.toLowerCase(Locale.US);
            JID_TO_NODEVER_CACHE.put(str, new NodeVerHash(capsExtension.getNode(), capsExtension.getVer(), hash));
        }
    }

    private EntityCapsManager(XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        this.lastLocalCapsVersions = new ConcurrentLinkedQueue();
        this.entityNode = DEFAULT_ENTITY_NODE;
        this.sdm = ServiceDiscoveryManager.getInstanceFor(xMPPConnection);
        instances.put(xMPPConnection, this);
        xMPPConnection.addConnectionListener(new C10472());
        updateLocalEntityCaps();
        if (autoEnableEntityCaps) {
            enableEntityCaps();
        }
        xMPPConnection.addAsyncStanzaListener(new C10483(), PRESENCES_WITH_CAPS);
        xMPPConnection.addAsyncStanzaListener(new C10494(), PRESENCES_WITHOUT_CAPS);
        xMPPConnection.addPacketSendingListener(new C10505(), PresenceTypeFilter.AVAILABLE);
        xMPPConnection.addPacketInterceptor(new C10516(), PresenceTypeFilter.AVAILABLE);
        this.sdm.setEntityCapsManager(this);
    }

    public static synchronized EntityCapsManager getInstanceFor(XMPPConnection xMPPConnection) {
        EntityCapsManager entityCapsManager;
        synchronized (EntityCapsManager.class) {
            if (SUPPORTED_HASHES.size() <= 0) {
                throw new IllegalStateException("No supported hashes for EntityCapsManager");
            }
            entityCapsManager = (EntityCapsManager) instances.get(xMPPConnection);
            if (entityCapsManager == null) {
                entityCapsManager = new EntityCapsManager(xMPPConnection);
            }
        }
        return entityCapsManager;
    }

    public synchronized void enableEntityCaps() {
        this.sdm.addFeature(NAMESPACE);
        updateLocalEntityCaps();
        this.entityCapsEnabled = true;
    }

    public synchronized void disableEntityCaps() {
        this.entityCapsEnabled = false;
        this.sdm.removeFeature(NAMESPACE);
    }

    public boolean entityCapsEnabled() {
        return this.entityCapsEnabled;
    }

    public void setEntityNode(String str) {
        this.entityNode = str;
        updateLocalEntityCaps();
    }

    public void removeUserCapsNode(String str) {
        JID_TO_NODEVER_CACHE.remove(str);
    }

    public CapsVersionAndHash getCapsVersionAndHash() {
        return this.currentCapsVersion;
    }

    public String getLocalNodeVer() {
        CapsVersionAndHash capsVersionAndHash = getCapsVersionAndHash();
        if (capsVersionAndHash == null) {
            return null;
        }
        return this.entityNode + '#' + capsVersionAndHash.version;
    }

    public boolean areEntityCapsSupported(String str) {
        return this.sdm.supportsFeature(str, NAMESPACE);
    }

    public boolean areEntityCapsSupportedByServer() {
        return areEntityCapsSupported(connection().getServiceName());
    }

    public void updateLocalEntityCaps() {
        XMPPConnection connection = connection();
        DiscoverInfo discoverInfo = new DiscoverInfo();
        discoverInfo.setType(Type.result);
        this.sdm.addDiscoverInfoTo(discoverInfo);
        this.currentCapsVersion = generateVerificationString(discoverInfo);
        String localNodeVer = getLocalNodeVer();
        discoverInfo.setNode(localNodeVer);
        addDiscoverInfoByNode(localNodeVer, discoverInfo);
        if (this.lastLocalCapsVersions.size() > 10) {
            this.sdm.removeNodeInformationProvider(this.entityNode + '#' + ((CapsVersionAndHash) this.lastLocalCapsVersions.poll()).version);
        }
        this.lastLocalCapsVersions.add(this.currentCapsVersion);
        if (connection != null) {
            JID_TO_NODEVER_CACHE.put(connection.getUser(), new NodeVerHash(this.entityNode, this.currentCapsVersion));
        }
        this.sdm.setNodeInformationProvider(localNodeVer, new C10527(new LinkedList(ServiceDiscoveryManager.getInstanceFor(connection).getIdentities())));
        if (connection != null && connection.isAuthenticated() && this.presenceSend != null) {
            try {
                connection.sendStanza(this.presenceSend.cloneWithNewId());
            } catch (Throwable e) {
                LOGGER.log(Level.WARNING, "Could could not update presence with caps info", e);
            }
        }
    }

    public static boolean verifyDiscoverInfoVersion(String str, String str2, DiscoverInfo discoverInfo) {
        if (discoverInfo.containsDuplicateIdentities() || discoverInfo.containsDuplicateFeatures() || verifyPacketExtensions(discoverInfo) || !str.equals(generateVerificationString(discoverInfo, str2).version)) {
            return false;
        }
        return true;
    }

    protected static boolean verifyPacketExtensions(DiscoverInfo discoverInfo) {
        List<FormField> linkedList = new LinkedList();
        for (ExtensionElement extensionElement : discoverInfo.getExtensions()) {
            if (extensionElement.getNamespace().equals(DataForm.NAMESPACE)) {
                for (FormField formField : ((DataForm) extensionElement).getFields()) {
                    if (formField.getVariable().equals(FormField.FORM_TYPE)) {
                        for (FormField equals : linkedList) {
                            if (formField.equals(equals)) {
                                return true;
                            }
                        }
                        linkedList.add(formField);
                    }
                }
                continue;
            }
        }
        return false;
    }

    protected static CapsVersionAndHash generateVerificationString(DiscoverInfo discoverInfo) {
        return generateVerificationString(discoverInfo, null);
    }

    protected static CapsVersionAndHash generateVerificationString(DiscoverInfo discoverInfo, String str) {
        if (str == null) {
            str = DEFAULT_HASH;
        }
        MessageDigest messageDigest = (MessageDigest) SUPPORTED_HASHES.get(str.toUpperCase(Locale.US));
        if (messageDigest == null) {
            return null;
        }
        byte[] digest;
        String toLowerCase = str.toLowerCase(Locale.US);
        DataForm from = DataForm.from(discoverInfo);
        StringBuilder stringBuilder = new StringBuilder();
        SortedSet<Identity> treeSet = new TreeSet();
        for (Identity add : discoverInfo.getIdentities()) {
            treeSet.add(add);
        }
        for (Identity add2 : treeSet) {
            stringBuilder.append(add2.getCategory());
            stringBuilder.append("/");
            stringBuilder.append(add2.getType());
            stringBuilder.append("/");
            stringBuilder.append(add2.getLanguage() == null ? "" : add2.getLanguage());
            stringBuilder.append("/");
            stringBuilder.append(add2.getName() == null ? "" : add2.getName());
            stringBuilder.append("<");
        }
        SortedSet<String> treeSet2 = new TreeSet();
        for (Feature var : discoverInfo.getFeatures()) {
            treeSet2.add(var.getVar());
        }
        for (String append : treeSet2) {
            stringBuilder.append(append);
            stringBuilder.append("<");
        }
        if (from != null && from.hasHiddenFormTypeField()) {
            synchronized (from) {
                FormField formField;
                SortedSet<FormField> treeSet3 = new TreeSet(new C10538());
                FormField formField2 = null;
                for (FormField formField3 : from.getFields()) {
                    if (!formField3.getVariable().equals(FormField.FORM_TYPE)) {
                        treeSet3.add(formField3);
                        formField3 = formField2;
                    }
                    formField2 = formField3;
                }
                if (formField2 != null) {
                    formFieldValuesToCaps(formField2.getValues(), stringBuilder);
                }
                for (FormField formField32 : treeSet3) {
                    stringBuilder.append(formField32.getVariable());
                    stringBuilder.append("<");
                    formFieldValuesToCaps(formField32.getValues(), stringBuilder);
                }
            }
        }
        synchronized (messageDigest) {
            digest = messageDigest.digest(stringBuilder.toString().getBytes());
        }
        return new CapsVersionAndHash(Base64.encodeToString(digest), toLowerCase);
    }

    private static void formFieldValuesToCaps(List<String> list, StringBuilder stringBuilder) {
        SortedSet<String> treeSet = new TreeSet();
        for (String add : list) {
            treeSet.add(add);
        }
        for (String add2 : treeSet) {
            stringBuilder.append(add2);
            stringBuilder.append("<");
        }
    }
}
