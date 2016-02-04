package org.jivesoftware.smack.roster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.AbstractConnectionClosedListener;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.ExceptionCallback;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.SmackException.NotLoggedInException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.filter.StanzaTypeFilter;
import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.iqrequest.IQRequestHandler.Mode;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Presence.Type;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.packet.XMPPError.Condition;
import org.jivesoftware.smack.roster.packet.RosterPacket;
import org.jivesoftware.smack.roster.packet.RosterPacket.Item;
import org.jivesoftware.smack.roster.packet.RosterPacket.ItemType;
import org.jivesoftware.smack.roster.packet.RosterVer;
import org.jivesoftware.smack.roster.rosterstore.RosterStore;
import org.jivesoftware.smack.util.Objects;
import org.jivesoftware.smackx.search.UserSearch;
import org.jxmpp.util.XmppStringUtils;
import se.emilsjolander.stickylistheaders.C1128R;

public class Roster extends Manager {
    private static final Map<XMPPConnection, Roster> INSTANCES;
    private static final Logger LOGGER;
    private static final StanzaFilter PRESENCE_PACKET_FILTER;
    private static SubscriptionMode defaultSubscriptionMode;
    private static boolean rosterLoadedAtLoginDefault;
    private final Map<String, RosterEntry> entries;
    private final Map<String, RosterGroup> groups;
    private final Map<String, Map<String, Presence>> presenceMap;
    private final PresencePacketListener presencePacketListener;
    private final Set<RosterListener> rosterListeners;
    private final Object rosterListenersAndEntriesLock;
    private boolean rosterLoadedAtLogin;
    private final Set<RosterLoadedListener> rosterLoadedListeners;
    private RosterState rosterState;
    private RosterStore rosterStore;
    private SubscriptionMode subscriptionMode;
    private final Set<RosterEntry> unfiledEntries;

    /* renamed from: org.jivesoftware.smack.roster.Roster.1 */
    final class C10101 implements ConnectionCreationListener {
        C10101() {
        }

        public void connectionCreated(XMPPConnection xMPPConnection) {
            Roster.getInstanceFor(xMPPConnection);
        }
    }

    /* renamed from: org.jivesoftware.smack.roster.Roster.2 */
    class C10112 extends AbstractConnectionClosedListener {
        C10112() {
        }

        public void authenticated(XMPPConnection xMPPConnection, boolean z) {
            if (!xMPPConnection.isAnonymous() && Roster.this.isRosterLoadedAtLogin() && !z) {
                try {
                    Roster.this.reload();
                } catch (Throwable e) {
                    Roster.LOGGER.log(Level.SEVERE, "Could not reload Roster", e);
                }
            }
        }

        public void connectionTerminated() {
            Roster.this.setOfflinePresencesAndResetLoaded();
        }
    }

    /* renamed from: org.jivesoftware.smack.roster.Roster.3 */
    class C10123 implements ExceptionCallback {
        C10123() {
        }

        public void processException(Exception exception) {
            Roster.this.rosterState = RosterState.uninitialized;
            Roster.LOGGER.log(Level.SEVERE, "Exception reloading roster", exception);
        }
    }

    /* renamed from: org.jivesoftware.smack.roster.Roster.4 */
    /* synthetic */ class C10134 {
        static final /* synthetic */ int[] $SwitchMap$org$jivesoftware$smack$packet$Presence$Type;
        static final /* synthetic */ int[] $SwitchMap$org$jivesoftware$smack$roster$Roster$SubscriptionMode;
        static final /* synthetic */ int[] f8578x69f76037;

        static {
            $SwitchMap$org$jivesoftware$smack$packet$Presence$Type = new int[Type.values().length];
            try {
                $SwitchMap$org$jivesoftware$smack$packet$Presence$Type[Type.available.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$jivesoftware$smack$packet$Presence$Type[Type.unavailable.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$org$jivesoftware$smack$packet$Presence$Type[Type.subscribe.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$org$jivesoftware$smack$packet$Presence$Type[Type.unsubscribe.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$org$jivesoftware$smack$packet$Presence$Type[Type.error.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            $SwitchMap$org$jivesoftware$smack$roster$Roster$SubscriptionMode = new int[SubscriptionMode.values().length];
            try {
                $SwitchMap$org$jivesoftware$smack$roster$Roster$SubscriptionMode[SubscriptionMode.accept_all.ordinal()] = 1;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$org$jivesoftware$smack$roster$Roster$SubscriptionMode[SubscriptionMode.reject_all.ordinal()] = 2;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$org$jivesoftware$smack$roster$Roster$SubscriptionMode[SubscriptionMode.manual.ordinal()] = 3;
            } catch (NoSuchFieldError e8) {
            }
            f8578x69f76037 = new int[ItemType.values().length];
            try {
                f8578x69f76037[ItemType.from.ordinal()] = 1;
            } catch (NoSuchFieldError e9) {
            }
            try {
                f8578x69f76037[ItemType.both.ordinal()] = 2;
            } catch (NoSuchFieldError e10) {
            }
            try {
                f8578x69f76037[ItemType.none.ordinal()] = 3;
            } catch (NoSuchFieldError e11) {
            }
            try {
                f8578x69f76037[ItemType.to.ordinal()] = 4;
            } catch (NoSuchFieldError e12) {
            }
        }
    }

    class PresencePacketListener implements StanzaListener {
        private PresencePacketListener() {
        }

        private Map<String, Presence> getUserPresences(String str) {
            Map<String, Presence> map = (Map) Roster.this.presenceMap.get(str);
            if (map != null) {
                return map;
            }
            Map concurrentHashMap = new ConcurrentHashMap();
            Roster.this.presenceMap.put(str, concurrentHashMap);
            return concurrentHashMap;
        }

        public void processPacket(Stanza stanza) {
            if (Roster.this.rosterState == RosterState.loading) {
                try {
                    Roster.this.waitUntilLoaded();
                } catch (Throwable e) {
                    Roster.LOGGER.log(Level.INFO, "Presence listener was interrupted", e);
                }
            }
            if (!Roster.this.isLoaded() && Roster.this.rosterLoadedAtLogin) {
                Roster.LOGGER.warning("Roster not loaded while processing presence stanza");
            }
            XMPPConnection access$800 = Roster.this.connection();
            Presence presence = (Presence) stanza;
            String from = presence.getFrom();
            String access$900 = Roster.this.getMapKey(from);
            Stanza stanza2 = null;
            Map userPresences;
            switch (C10134.$SwitchMap$org$jivesoftware$smack$packet$Presence$Type[presence.getType().ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    userPresences = getUserPresences(access$900);
                    userPresences.remove("");
                    userPresences.put(XmppStringUtils.m13446c(from), presence);
                    if (Roster.this.entries.containsKey(access$900)) {
                        Roster.this.fireRosterPresenceEvent(presence);
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    if ("".equals(XmppStringUtils.m13446c(from))) {
                        getUserPresences(access$900).put("", presence);
                    } else if (Roster.this.presenceMap.get(access$900) != null) {
                        ((Map) Roster.this.presenceMap.get(access$900)).put(XmppStringUtils.m13446c(from), presence);
                    }
                    if (Roster.this.entries.containsKey(access$900)) {
                        Roster.this.fireRosterPresenceEvent(presence);
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    switch (C10134.$SwitchMap$org$jivesoftware$smack$roster$Roster$SubscriptionMode[Roster.this.subscriptionMode.ordinal()]) {
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                            stanza2 = new Presence(Type.subscribed);
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                            stanza2 = new Presence(Type.unsubscribed);
                            break;
                    }
                    if (stanza2 != null) {
                        stanza2.setTo(presence.getFrom());
                        access$800.sendStanza(stanza2);
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    if (Roster.this.subscriptionMode != SubscriptionMode.manual) {
                        stanza2 = new Presence(Type.unsubscribed);
                        stanza2.setTo(presence.getFrom());
                        access$800.sendStanza(stanza2);
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    if ("".equals(XmppStringUtils.m13446c(from))) {
                        userPresences = getUserPresences(access$900);
                        userPresences.clear();
                        userPresences.put("", presence);
                        if (Roster.this.entries.containsKey(access$900)) {
                            Roster.this.fireRosterPresenceEvent(presence);
                        }
                    }
                default:
            }
        }
    }

    class RosterPushListener extends AbstractIqRequestHandler {
        private RosterPushListener() {
            super(UserSearch.ELEMENT, RosterPacket.NAMESPACE, IQ.Type.set, Mode.sync);
        }

        public IQ handleIQRequest(IQ iq) {
            XMPPConnection access$2100 = Roster.this.connection();
            RosterPacket rosterPacket = (RosterPacket) iq;
            String d = XmppStringUtils.m13447d(access$2100.getUser());
            String from = rosterPacket.getFrom();
            if (from == null || from.equals(d)) {
                Collection rosterItems = rosterPacket.getRosterItems();
                if (rosterItems.size() != 1) {
                    Roster.LOGGER.warning("Ignoring roster push with not exaclty one entry. size=" + rosterItems.size());
                    return IQ.createErrorResponse(iq, new XMPPError(Condition.bad_request));
                }
                Collection arrayList = new ArrayList();
                Collection arrayList2 = new ArrayList();
                Collection arrayList3 = new ArrayList();
                ArrayList arrayList4 = new ArrayList();
                Item item = (Item) rosterItems.iterator().next();
                RosterEntry rosterEntry = new RosterEntry(item.getUser(), item.getName(), item.getItemType(), item.getItemStatus(), Roster.this, access$2100);
                String version = rosterPacket.getVersion();
                if (item.getItemType().equals(ItemType.remove)) {
                    Roster.this.deleteEntry(arrayList3, rosterEntry);
                    if (Roster.this.rosterStore != null) {
                        Roster.this.rosterStore.removeEntry(rosterEntry.getUser(), version);
                    }
                } else if (Roster.hasValidSubscriptionType(item)) {
                    Roster.this.addUpdateEntry(arrayList, arrayList2, arrayList4, item, rosterEntry);
                    if (Roster.this.rosterStore != null) {
                        Roster.this.rosterStore.addEntry(item, version);
                    }
                }
                Roster.this.removeEmptyGroups();
                Roster.this.fireRosterChangedEvent(arrayList, arrayList2, arrayList3);
                return IQ.createResultIQ(rosterPacket);
            }
            Roster.LOGGER.warning("Ignoring roster push with a non matching 'from' ourJid='" + d + "' from='" + from + "'");
            return IQ.createErrorResponse(iq, new XMPPError(Condition.service_unavailable));
        }
    }

    class RosterResultListener implements StanzaListener {
        private RosterResultListener() {
        }

        public void processPacket(Stanza stanza) {
            XMPPConnection access$1300 = Roster.this.connection();
            Roster.LOGGER.fine("RosterResultListener received stanza");
            Collection arrayList = new ArrayList();
            Collection arrayList2 = new ArrayList();
            Collection arrayList3 = new ArrayList();
            Collection arrayList4 = new ArrayList();
            Item item;
            if (stanza instanceof RosterPacket) {
                RosterPacket rosterPacket = (RosterPacket) stanza;
                Collection arrayList5 = new ArrayList();
                for (Item item2 : rosterPacket.getRosterItems()) {
                    if (Roster.hasValidSubscriptionType(item2)) {
                        arrayList5.add(item2);
                    }
                }
                Iterator it = arrayList5.iterator();
                while (it.hasNext()) {
                    item = (Item) it.next();
                    Roster.this.addUpdateEntry(arrayList, arrayList2, arrayList4, item, new RosterEntry(item.getUser(), item.getName(), item.getItemType(), item.getItemStatus(), Roster.this, access$1300));
                }
                Set<String> hashSet = new HashSet();
                for (RosterEntry user : Roster.this.entries.values()) {
                    hashSet.add(user.getUser());
                }
                hashSet.removeAll(arrayList);
                hashSet.removeAll(arrayList2);
                hashSet.removeAll(arrayList4);
                for (String str : hashSet) {
                    Roster.this.deleteEntry(arrayList3, (RosterEntry) Roster.this.entries.get(str));
                }
                if (Roster.this.rosterStore != null) {
                    Roster.this.rosterStore.resetEntries(arrayList5, rosterPacket.getVersion());
                }
                Roster.this.removeEmptyGroups();
            } else {
                for (Item item3 : Roster.this.rosterStore.getEntries()) {
                    Roster.this.addUpdateEntry(arrayList, arrayList2, arrayList4, item3, new RosterEntry(item3.getUser(), item3.getName(), item3.getItemType(), item3.getItemStatus(), Roster.this, access$1300));
                }
            }
            Roster.this.rosterState = RosterState.loaded;
            synchronized (Roster.this) {
                Roster.this.notifyAll();
            }
            Roster.this.fireRosterChangedEvent(arrayList, arrayList2, arrayList3);
            try {
                synchronized (Roster.this.rosterLoadedListeners) {
                    for (RosterLoadedListener onRosterLoaded : Roster.this.rosterLoadedListeners) {
                        onRosterLoaded.onRosterLoaded(Roster.this);
                    }
                }
            } catch (Throwable e) {
                Roster.LOGGER.log(Level.WARNING, "RosterLoadedListener threw exception", e);
            }
        }
    }

    enum RosterState {
        uninitialized,
        loading,
        loaded
    }

    public enum SubscriptionMode {
        accept_all,
        reject_all,
        manual
    }

    static {
        LOGGER = Logger.getLogger(Roster.class.getName());
        XMPPConnectionRegistry.addConnectionCreationListener(new C10101());
        INSTANCES = new WeakHashMap();
        PRESENCE_PACKET_FILTER = StanzaTypeFilter.PRESENCE;
        rosterLoadedAtLoginDefault = true;
        defaultSubscriptionMode = SubscriptionMode.accept_all;
    }

    public static synchronized Roster getInstanceFor(XMPPConnection xMPPConnection) {
        Roster roster;
        synchronized (Roster.class) {
            roster = (Roster) INSTANCES.get(xMPPConnection);
            if (roster == null) {
                roster = new Roster(xMPPConnection);
                INSTANCES.put(xMPPConnection, roster);
            }
        }
        return roster;
    }

    public static SubscriptionMode getDefaultSubscriptionMode() {
        return defaultSubscriptionMode;
    }

    public static void setDefaultSubscriptionMode(SubscriptionMode subscriptionMode) {
        defaultSubscriptionMode = subscriptionMode;
    }

    private Roster(XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        this.groups = new ConcurrentHashMap();
        this.entries = new ConcurrentHashMap();
        this.unfiledEntries = new CopyOnWriteArraySet();
        this.rosterListeners = new LinkedHashSet();
        this.presenceMap = new ConcurrentHashMap();
        this.rosterLoadedListeners = new LinkedHashSet();
        this.rosterListenersAndEntriesLock = new Object();
        this.rosterState = RosterState.uninitialized;
        this.presencePacketListener = new PresencePacketListener();
        this.rosterLoadedAtLogin = rosterLoadedAtLoginDefault;
        this.subscriptionMode = getDefaultSubscriptionMode();
        xMPPConnection.registerIQRequestHandler(new RosterPushListener());
        xMPPConnection.addSyncStanzaListener(this.presencePacketListener, PRESENCE_PACKET_FILTER);
        xMPPConnection.addConnectionListener(new C10112());
        if (xMPPConnection.isAuthenticated()) {
            try {
                reload();
            } catch (Throwable e) {
                LOGGER.log(Level.SEVERE, "Could not reload Roster", e);
            }
        }
    }

    public SubscriptionMode getSubscriptionMode() {
        return this.subscriptionMode;
    }

    public void setSubscriptionMode(SubscriptionMode subscriptionMode) {
        this.subscriptionMode = subscriptionMode;
    }

    public void reload() {
        XMPPConnection connection = connection();
        if (!connection.isAuthenticated()) {
            throw new NotLoggedInException();
        } else if (connection.isAnonymous()) {
            throw new IllegalStateException("Anonymous users can't have a roster.");
        } else {
            IQ rosterPacket = new RosterPacket();
            if (this.rosterStore != null && isRosterVersioningSupported()) {
                rosterPacket.setVersion(this.rosterStore.getRosterVersion());
            }
            this.rosterState = RosterState.loading;
            connection.sendIqWithResponseCallback(rosterPacket, new RosterResultListener(), new C10123());
        }
    }

    public void reloadAndWait() {
        reload();
        waitUntilLoaded();
    }

    public boolean setRosterStore(RosterStore rosterStore) {
        Throwable e;
        this.rosterStore = rosterStore;
        try {
            reload();
            return true;
        } catch (NotLoggedInException e2) {
            e = e2;
            LOGGER.log(Level.FINER, "Could not reload roster", e);
            return false;
        } catch (NotConnectedException e3) {
            e = e3;
            LOGGER.log(Level.FINER, "Could not reload roster", e);
            return false;
        }
    }

    protected boolean waitUntilLoaded() {
        long packetReplyTimeout = connection().getPacketReplyTimeout();
        long currentTimeMillis = System.currentTimeMillis();
        long j = packetReplyTimeout;
        while (!isLoaded() && j > 0) {
            synchronized (this) {
                if (!isLoaded()) {
                    wait(j);
                }
            }
            packetReplyTimeout = System.currentTimeMillis();
            j -= packetReplyTimeout - currentTimeMillis;
            currentTimeMillis = packetReplyTimeout;
        }
        return isLoaded();
    }

    public boolean isLoaded() {
        return this.rosterState == RosterState.loaded;
    }

    public boolean addRosterListener(RosterListener rosterListener) {
        boolean add;
        synchronized (this.rosterListenersAndEntriesLock) {
            add = this.rosterListeners.add(rosterListener);
        }
        return add;
    }

    public boolean removeRosterListener(RosterListener rosterListener) {
        boolean remove;
        synchronized (this.rosterListenersAndEntriesLock) {
            remove = this.rosterListeners.remove(rosterListener);
        }
        return remove;
    }

    public boolean addRosterLoadedListener(RosterLoadedListener rosterLoadedListener) {
        boolean add;
        synchronized (rosterLoadedListener) {
            add = this.rosterLoadedListeners.add(rosterLoadedListener);
        }
        return add;
    }

    public boolean removeRosterLoadedListener(RosterLoadedListener rosterLoadedListener) {
        boolean remove;
        synchronized (rosterLoadedListener) {
            remove = this.rosterLoadedListeners.remove(rosterLoadedListener);
        }
        return remove;
    }

    public RosterGroup createGroup(String str) {
        XMPPConnection connection = connection();
        if (connection.isAnonymous()) {
            throw new IllegalStateException("Anonymous users can't have a roster.");
        } else if (this.groups.containsKey(str)) {
            return (RosterGroup) this.groups.get(str);
        } else {
            RosterGroup rosterGroup = new RosterGroup(str, connection);
            this.groups.put(str, rosterGroup);
            return rosterGroup;
        }
    }

    public void createEntry(String str, String str2, String[] strArr) {
        XMPPConnection connection = connection();
        if (!connection.isAuthenticated()) {
            throw new NotLoggedInException();
        } else if (connection.isAnonymous()) {
            throw new IllegalStateException("Anonymous users can't have a roster.");
        } else {
            IQ rosterPacket = new RosterPacket();
            rosterPacket.setType(IQ.Type.set);
            Item item = new Item(str, str2);
            if (strArr != null) {
                for (String str3 : strArr) {
                    if (str3 != null && str3.trim().length() > 0) {
                        item.addGroupName(str3);
                    }
                }
            }
            rosterPacket.addRosterItem(item);
            connection.createPacketCollectorAndSend(rosterPacket).nextResultOrThrow();
            Stanza presence = new Presence(Type.subscribe);
            presence.setTo(str);
            connection.sendStanza(presence);
        }
    }

    public void removeEntry(RosterEntry rosterEntry) {
        XMPPConnection connection = connection();
        if (!connection.isAuthenticated()) {
            throw new NotLoggedInException();
        } else if (connection.isAnonymous()) {
            throw new IllegalStateException("Anonymous users can't have a roster.");
        } else if (this.entries.containsKey(rosterEntry.getUser())) {
            IQ rosterPacket = new RosterPacket();
            rosterPacket.setType(IQ.Type.set);
            Item toRosterItem = RosterEntry.toRosterItem(rosterEntry);
            toRosterItem.setItemType(ItemType.remove);
            rosterPacket.addRosterItem(toRosterItem);
            connection.createPacketCollectorAndSend(rosterPacket).nextResultOrThrow();
        }
    }

    public int getEntryCount() {
        return getEntries().size();
    }

    public void getEntriesAndAddListener(RosterListener rosterListener, RosterEntries rosterEntries) {
        Objects.requireNonNull(rosterListener, "listener must not be null");
        Objects.requireNonNull(rosterEntries, "rosterEntries must not be null");
        synchronized (this.rosterListenersAndEntriesLock) {
            rosterEntries.rosterEntires(this.entries.values());
            addRosterListener(rosterListener);
        }
    }

    public Set<RosterEntry> getEntries() {
        Set<RosterEntry> hashSet;
        synchronized (this.rosterListenersAndEntriesLock) {
            hashSet = new HashSet(this.entries.size());
            for (RosterEntry add : this.entries.values()) {
                hashSet.add(add);
            }
        }
        return hashSet;
    }

    public int getUnfiledEntryCount() {
        return this.unfiledEntries.size();
    }

    public Set<RosterEntry> getUnfiledEntries() {
        return Collections.unmodifiableSet(this.unfiledEntries);
    }

    public RosterEntry getEntry(String str) {
        if (str == null) {
            return null;
        }
        return (RosterEntry) this.entries.get(getMapKey(str));
    }

    public boolean contains(String str) {
        return getEntry(str) != null;
    }

    public RosterGroup getGroup(String str) {
        return (RosterGroup) this.groups.get(str);
    }

    public int getGroupCount() {
        return this.groups.size();
    }

    public Collection<RosterGroup> getGroups() {
        return Collections.unmodifiableCollection(this.groups.values());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.jivesoftware.smack.packet.Presence getPresence(java.lang.String r8) {
        /*
        r7 = this;
        r1 = 0;
        r0 = org.jxmpp.util.XmppStringUtils.m13447d(r8);
        r0 = r7.getMapKey(r0);
        r2 = r7.presenceMap;
        r0 = r2.get(r0);
        r0 = (java.util.Map) r0;
        if (r0 != 0) goto L_0x001e;
    L_0x0013:
        r0 = new org.jivesoftware.smack.packet.Presence;
        r1 = org.jivesoftware.smack.packet.Presence.Type.unavailable;
        r0.<init>(r1);
        r0.setFrom(r8);
    L_0x001d:
        return r0;
    L_0x001e:
        r2 = r0.keySet();
        r6 = r2.iterator();
        r3 = r1;
        r2 = r1;
    L_0x0028:
        r1 = r6.hasNext();
        if (r1 == 0) goto L_0x0072;
    L_0x002e:
        r1 = r6.next();
        r1 = (java.lang.String) r1;
        r1 = r0.get(r1);
        r1 = (org.jivesoftware.smack.packet.Presence) r1;
        r4 = r1.isAvailable();
        if (r4 != 0) goto L_0x0042;
    L_0x0040:
        r3 = r1;
        goto L_0x0028;
    L_0x0042:
        if (r2 == 0) goto L_0x004e;
    L_0x0044:
        r4 = r1.getPriority();
        r5 = r2.getPriority();
        if (r4 <= r5) goto L_0x0050;
    L_0x004e:
        r2 = r1;
        goto L_0x0028;
    L_0x0050:
        r4 = r1.getPriority();
        r5 = r2.getPriority();
        if (r4 != r5) goto L_0x0070;
    L_0x005a:
        r4 = r1.getMode();
        if (r4 != 0) goto L_0x0062;
    L_0x0060:
        r4 = org.jivesoftware.smack.packet.Presence.Mode.available;
    L_0x0062:
        r5 = r2.getMode();
        if (r5 != 0) goto L_0x006a;
    L_0x0068:
        r5 = org.jivesoftware.smack.packet.Presence.Mode.available;
    L_0x006a:
        r4 = r4.compareTo(r5);
        if (r4 < 0) goto L_0x004e;
    L_0x0070:
        r1 = r2;
        goto L_0x004e;
    L_0x0072:
        if (r2 != 0) goto L_0x0086;
    L_0x0074:
        if (r3 == 0) goto L_0x007b;
    L_0x0076:
        r0 = r3.clone();
        goto L_0x001d;
    L_0x007b:
        r0 = new org.jivesoftware.smack.packet.Presence;
        r1 = org.jivesoftware.smack.packet.Presence.Type.unavailable;
        r0.<init>(r1);
        r0.setFrom(r8);
        goto L_0x001d;
    L_0x0086:
        r0 = r2.clone();
        goto L_0x001d;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.roster.Roster.getPresence(java.lang.String):org.jivesoftware.smack.packet.Presence");
    }

    public Presence getPresenceResource(String str) {
        String mapKey = getMapKey(str);
        String c = XmppStringUtils.m13446c(str);
        Map map = (Map) this.presenceMap.get(mapKey);
        if (map == null) {
            Presence presence = new Presence(Type.unavailable);
            presence.setFrom(str);
            return presence;
        }
        presence = (Presence) map.get(c);
        if (presence != null) {
            return presence.clone();
        }
        presence = new Presence(Type.unavailable);
        presence.setFrom(str);
        return presence;
    }

    public List<Presence> getAllPresences(String str) {
        Map map = (Map) this.presenceMap.get(getMapKey(str));
        if (map == null) {
            new Presence(Type.unavailable).setFrom(str);
            return new ArrayList(Arrays.asList(new Presence[]{r1}));
        }
        List<Presence> arrayList = new ArrayList(map.values().size());
        for (Presence clone : map.values()) {
            arrayList.add(clone.clone());
        }
        return arrayList;
    }

    public List<Presence> getAvailablePresences(String str) {
        List<Presence> allPresences = getAllPresences(str);
        List<Presence> arrayList = new ArrayList(allPresences.size());
        for (Presence presence : allPresences) {
            if (presence.isAvailable()) {
                arrayList.add(presence);
            }
        }
        return arrayList;
    }

    public List<Presence> getPresences(String str) {
        Map map = (Map) this.presenceMap.get(getMapKey(str));
        if (map == null) {
            Presence presence;
            new Presence(Type.unavailable).setFrom(str);
            return Arrays.asList(new Presence[]{presence});
        }
        List<Presence> arrayList = new ArrayList();
        Presence presence2 = null;
        for (Presence presence3 : map.values()) {
            if (presence3.isAvailable()) {
                arrayList.add(presence3.clone());
                presence3 = presence2;
            }
            presence2 = presence3;
        }
        if (!arrayList.isEmpty()) {
            return arrayList;
        }
        if (presence2 != null) {
            return Arrays.asList(new Presence[]{presence2.clone()});
        }
        new Presence(Type.unavailable).setFrom(str);
        return Arrays.asList(new Presence[]{presence3});
    }

    public boolean isSubscribedToMyPresence(String str) {
        if (connection().getServiceName().equals(str)) {
            return true;
        }
        RosterEntry entry = getEntry(str);
        if (entry == null) {
            return false;
        }
        switch (C10134.f8578x69f76037[entry.getType().ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                return true;
            default:
                return false;
        }
    }

    public void setRosterLoadedAtLogin(boolean z) {
        this.rosterLoadedAtLogin = z;
    }

    public boolean isRosterLoadedAtLogin() {
        return this.rosterLoadedAtLogin;
    }

    RosterStore getRosterStore() {
        return this.rosterStore;
    }

    private String getMapKey(String str) {
        if (str == null) {
            return null;
        }
        return !this.entries.containsKey(str) ? XmppStringUtils.m13447d(str).toLowerCase(Locale.US) : str;
    }

    private void setOfflinePresencesAndResetLoaded() {
        for (String str : this.presenceMap.keySet()) {
            Map map = (Map) this.presenceMap.get(str);
            if (map != null) {
                for (String str2 : map.keySet()) {
                    Stanza presence = new Presence(Type.unavailable);
                    presence.setFrom(str + "/" + str2);
                    try {
                        this.presencePacketListener.processPacket(presence);
                    } catch (Throwable e) {
                        throw new IllegalStateException("presencePakcetListener should never throw a NotConnectedException when processPacket is called with a presence of type unavailable", e);
                    }
                }
                continue;
            }
        }
        this.rosterState = RosterState.uninitialized;
    }

    private void fireRosterChangedEvent(Collection<String> collection, Collection<String> collection2, Collection<String> collection3) {
        synchronized (this.rosterListenersAndEntriesLock) {
            for (RosterListener rosterListener : this.rosterListeners) {
                if (!collection.isEmpty()) {
                    rosterListener.entriesAdded(collection);
                }
                if (!collection2.isEmpty()) {
                    rosterListener.entriesUpdated(collection2);
                }
                if (!collection3.isEmpty()) {
                    rosterListener.entriesDeleted(collection3);
                }
            }
        }
    }

    private void fireRosterPresenceEvent(Presence presence) {
        synchronized (this.rosterListenersAndEntriesLock) {
            for (RosterListener presenceChanged : this.rosterListeners) {
                presenceChanged.presenceChanged(presence);
            }
        }
    }

    private void addUpdateEntry(Collection<String> collection, Collection<String> collection2, Collection<String> collection3, Item item, RosterEntry rosterEntry) {
        synchronized (this.rosterListenersAndEntriesLock) {
            RosterEntry rosterEntry2 = (RosterEntry) this.entries.put(item.getUser(), rosterEntry);
        }
        if (rosterEntry2 == null) {
            collection.add(item.getUser());
        } else {
            Item toRosterItem = RosterEntry.toRosterItem(rosterEntry2);
            if (rosterEntry2.equalsDeep(rosterEntry) && item.getGroupNames().equals(toRosterItem.getGroupNames())) {
                collection3.add(item.getUser());
            } else {
                collection2.add(item.getUser());
            }
        }
        if (item.getGroupNames().isEmpty()) {
            this.unfiledEntries.add(rosterEntry);
        } else {
            this.unfiledEntries.remove(rosterEntry);
        }
        Collection arrayList = new ArrayList();
        for (String str : item.getGroupNames()) {
            arrayList.add(str);
            RosterGroup group = getGroup(str);
            if (group == null) {
                group = createGroup(str);
                this.groups.put(str, group);
            }
            group.addEntryLocal(rosterEntry);
        }
        List<String> arrayList2 = new ArrayList();
        for (RosterGroup name : getGroups()) {
            arrayList2.add(name.getName());
        }
        arrayList2.removeAll(arrayList);
        for (String str2 : arrayList2) {
            RosterGroup group2 = getGroup(str2);
            group2.removeEntryLocal(rosterEntry);
            if (group2.getEntryCount() == 0) {
                this.groups.remove(str2);
            }
        }
    }

    private void deleteEntry(Collection<String> collection, RosterEntry rosterEntry) {
        String user = rosterEntry.getUser();
        this.entries.remove(user);
        this.unfiledEntries.remove(rosterEntry);
        this.presenceMap.remove(XmppStringUtils.m13447d(user));
        collection.add(user);
        for (Entry entry : this.groups.entrySet()) {
            RosterGroup rosterGroup = (RosterGroup) entry.getValue();
            rosterGroup.removeEntryLocal(rosterEntry);
            if (rosterGroup.getEntryCount() == 0) {
                this.groups.remove(entry.getKey());
            }
        }
    }

    private void removeEmptyGroups() {
        for (RosterGroup rosterGroup : getGroups()) {
            if (rosterGroup.getEntryCount() == 0) {
                this.groups.remove(rosterGroup.getName());
            }
        }
    }

    private static boolean hasValidSubscriptionType(Item item) {
        switch (C10134.f8578x69f76037[item.getItemType().ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                return true;
            default:
                return false;
        }
    }

    public boolean isRosterVersioningSupported() {
        return connection().hasFeature(RosterVer.ELEMENT, RosterVer.NAMESPACE);
    }
}
