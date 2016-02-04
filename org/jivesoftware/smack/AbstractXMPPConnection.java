package org.jivesoftware.smack;

import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.PacketCollector.Configuration;
import org.jivesoftware.smack.SmackException.AlreadyConnectedException;
import org.jivesoftware.smack.SmackException.AlreadyLoggedInException;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.SmackException.ResourceBindingNotOfferedException;
import org.jivesoftware.smack.XMPPConnection.FromMode;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.compress.packet.Compress;
import org.jivesoftware.smack.compress.packet.Compressed;
import org.jivesoftware.smack.compression.XMPPInputOutputStream;
import org.jivesoftware.smack.debugger.SmackDebugger;
import org.jivesoftware.smack.filter.IQReplyFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.filter.StanzaIdFilter;
import org.jivesoftware.smack.iqrequest.IQRequestHandler;
import org.jivesoftware.smack.iqrequest.IQRequestHandler.Mode;
import org.jivesoftware.smack.packet.Bind;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smack.packet.Mechanisms;
import org.jivesoftware.smack.packet.PlainStreamElement;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Session;
import org.jivesoftware.smack.packet.Session.Feature;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.packet.StartTls;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.packet.XMPPError.Condition;
import org.jivesoftware.smack.parsing.ParsingExceptionCallback;
import org.jivesoftware.smack.parsing.UnparsablePacket;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smack.sasl.packet.SaslStreamElements;
import org.jivesoftware.smack.util.BoundedThreadPoolExecutor;
import org.jivesoftware.smack.util.DNSUtil;
import org.jivesoftware.smack.util.Objects;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smack.util.ParserUtils;
import org.jivesoftware.smack.util.SmackExecutorThreadFactory;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smack.util.dns.HostAddress;
import org.jxmpp.util.XmppStringUtils;
import org.xmlpull.v1.XmlPullParser;
import se.emilsjolander.stickylistheaders.C1128R;

public abstract class AbstractXMPPConnection implements XMPPConnection {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final Logger LOGGER;
    private static final AtomicInteger connectionCounter;
    private static boolean replyToUnknownIqDefault;
    private final Map<StanzaListener, ListenerWrapper> asyncRecvListeners;
    protected boolean authenticated;
    private final ExecutorService cachedExecutorService;
    private final Collection<PacketCollector> collectors;
    protected XMPPInputOutputStream compressionHandler;
    protected final ConnectionConfiguration config;
    protected boolean connected;
    protected final int connectionCounterValue;
    protected final Set<ConnectionListener> connectionListeners;
    protected final Lock connectionLock;
    protected SmackDebugger debugger;
    private final BoundedThreadPoolExecutor executorService;
    private FromMode fromMode;
    private final Map<String, IQRequestHandler> getIqRequestHandler;
    protected String host;
    protected List<HostAddress> hostAddresses;
    private final Map<StanzaListener, InterceptorWrapper> interceptors;
    protected final SynchronizationPoint<Exception> lastFeaturesReceived;
    private long lastStanzaReceived;
    private long packetReplyTimeout;
    private ParsingExceptionCallback parsingExceptionCallback;
    protected int port;
    protected Reader reader;
    private final ScheduledExecutorService removeCallbacksService;
    private boolean replyToUnkownIq;
    protected SASLAuthentication saslAuthentication;
    protected final SynchronizationPoint<SmackException> saslFeatureReceived;
    private final Map<StanzaListener, ListenerWrapper> sendListeners;
    private String serviceName;
    private final Map<String, IQRequestHandler> setIqRequestHandler;
    private final ExecutorService singleThreadedExecutorService;
    protected final Map<String, ExtensionElement> streamFeatures;
    protected String streamId;
    private final Map<StanzaListener, ListenerWrapper> syncRecvListeners;
    private String usedPassword;
    private String usedResource;
    private String usedUsername;
    protected String user;
    protected boolean wasAuthenticated;
    protected Writer writer;

    /* renamed from: org.jivesoftware.smack.AbstractXMPPConnection.1 */
    class C09811 implements Runnable {
        final /* synthetic */ List val$listenersToNotify;
        final /* synthetic */ Stanza val$packet;

        C09811(List list, Stanza stanza) {
            this.val$listenersToNotify = list;
            this.val$packet = stanza;
        }

        public void run() {
            for (StanzaListener processPacket : this.val$listenersToNotify) {
                try {
                    processPacket.processPacket(this.val$packet);
                } catch (Throwable e) {
                    AbstractXMPPConnection.LOGGER.log(Level.WARNING, "Sending listener threw exception", e);
                }
            }
        }
    }

    /* renamed from: org.jivesoftware.smack.AbstractXMPPConnection.2 */
    class C09822 implements Runnable {
        final /* synthetic */ IQRequestHandler val$finalIqRequestHandler;
        final /* synthetic */ IQ val$iq;

        C09822(IQRequestHandler iQRequestHandler, IQ iq) {
            this.val$finalIqRequestHandler = iQRequestHandler;
            this.val$iq = iq;
        }

        public void run() {
            Stanza handleIQRequest = this.val$finalIqRequestHandler.handleIQRequest(this.val$iq);
            if (handleIQRequest != null) {
                try {
                    AbstractXMPPConnection.this.sendStanza(handleIQRequest);
                } catch (Throwable e) {
                    AbstractXMPPConnection.LOGGER.log(Level.WARNING, "NotConnectedException while sending response to IQ request", e);
                }
            }
        }
    }

    /* renamed from: org.jivesoftware.smack.AbstractXMPPConnection.3 */
    class C09833 implements Runnable {
        final /* synthetic */ StanzaListener val$listener;
        final /* synthetic */ Stanza val$packet;

        C09833(StanzaListener stanzaListener, Stanza stanza) {
            this.val$listener = stanzaListener;
            this.val$packet = stanza;
        }

        public void run() {
            try {
                this.val$listener.processPacket(this.val$packet);
            } catch (Throwable e) {
                AbstractXMPPConnection.LOGGER.log(Level.SEVERE, "Exception in async packet listener", e);
            }
        }
    }

    /* renamed from: org.jivesoftware.smack.AbstractXMPPConnection.4 */
    class C09844 implements Runnable {
        final /* synthetic */ Collection val$listenersToNotify;
        final /* synthetic */ Stanza val$packet;

        C09844(Collection collection, Stanza stanza) {
            this.val$listenersToNotify = collection;
            this.val$packet = stanza;
        }

        public void run() {
            for (StanzaListener processPacket : this.val$listenersToNotify) {
                try {
                    processPacket.processPacket(this.val$packet);
                } catch (Throwable e) {
                    AbstractXMPPConnection.LOGGER.log(Level.WARNING, "Got not connected exception, aborting", e);
                    return;
                } catch (Throwable e2) {
                    AbstractXMPPConnection.LOGGER.log(Level.SEVERE, "Exception in packet listener", e2);
                }
            }
        }
    }

    /* renamed from: org.jivesoftware.smack.AbstractXMPPConnection.5 */
    class C09855 implements StanzaListener {
        final /* synthetic */ StanzaListener val$callback;
        final /* synthetic */ ExceptionCallback val$exceptionCallback;

        C09855(StanzaListener stanzaListener, ExceptionCallback exceptionCallback) {
            this.val$callback = stanzaListener;
            this.val$exceptionCallback = exceptionCallback;
        }

        public void processPacket(Stanza stanza) {
            try {
                XMPPErrorException.ifHasErrorThenThrow(stanza);
                this.val$callback.processPacket(stanza);
                AbstractXMPPConnection.this.removeAsyncStanzaListener(this);
            } catch (Exception e) {
                if (this.val$exceptionCallback != null) {
                    this.val$exceptionCallback.processException(e);
                }
                AbstractXMPPConnection.this.removeAsyncStanzaListener(this);
            } catch (Throwable th) {
                AbstractXMPPConnection.this.removeAsyncStanzaListener(this);
            }
        }
    }

    /* renamed from: org.jivesoftware.smack.AbstractXMPPConnection.6 */
    class C09866 implements Runnable {
        final /* synthetic */ ExceptionCallback val$exceptionCallback;
        final /* synthetic */ StanzaListener val$packetListener;
        final /* synthetic */ StanzaFilter val$replyFilter;

        C09866(StanzaListener stanzaListener, ExceptionCallback exceptionCallback, StanzaFilter stanzaFilter) {
            this.val$packetListener = stanzaListener;
            this.val$exceptionCallback = exceptionCallback;
            this.val$replyFilter = stanzaFilter;
        }

        public void run() {
            if (AbstractXMPPConnection.this.removeAsyncStanzaListener(this.val$packetListener) && this.val$exceptionCallback != null) {
                this.val$exceptionCallback.processException(NoResponseException.newWith(AbstractXMPPConnection.this, this.val$replyFilter));
            }
        }
    }

    /* renamed from: org.jivesoftware.smack.AbstractXMPPConnection.7 */
    class C09877 implements StanzaListener {
        final /* synthetic */ StanzaListener val$callback;

        C09877(StanzaListener stanzaListener) {
            this.val$callback = stanzaListener;
        }

        public void processPacket(Stanza stanza) {
            try {
                this.val$callback.processPacket(stanza);
            } finally {
                AbstractXMPPConnection.this.removeSyncStanzaListener(this);
            }
        }
    }

    /* renamed from: org.jivesoftware.smack.AbstractXMPPConnection.8 */
    class C09888 implements Runnable {
        final /* synthetic */ StanzaListener val$packetListener;

        C09888(StanzaListener stanzaListener) {
            this.val$packetListener = stanzaListener;
        }

        public void run() {
            AbstractXMPPConnection.this.removeSyncStanzaListener(this.val$packetListener);
        }
    }

    /* renamed from: org.jivesoftware.smack.AbstractXMPPConnection.9 */
    /* synthetic */ class C09899 {
        static final /* synthetic */ int[] $SwitchMap$org$jivesoftware$smack$XMPPConnection$FromMode;
        static final /* synthetic */ int[] f8574xdbd7d2a3;
        static final /* synthetic */ int[] $SwitchMap$org$jivesoftware$smack$packet$IQ$Type;

        static {
            f8574xdbd7d2a3 = new int[Mode.values().length];
            try {
                f8574xdbd7d2a3[Mode.sync.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f8574xdbd7d2a3[Mode.async.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $SwitchMap$org$jivesoftware$smack$packet$IQ$Type = new int[Type.values().length];
            try {
                $SwitchMap$org$jivesoftware$smack$packet$IQ$Type[Type.set.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$org$jivesoftware$smack$packet$IQ$Type[Type.get.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            $SwitchMap$org$jivesoftware$smack$XMPPConnection$FromMode = new int[FromMode.values().length];
            try {
                $SwitchMap$org$jivesoftware$smack$XMPPConnection$FromMode[FromMode.OMITTED.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$org$jivesoftware$smack$XMPPConnection$FromMode[FromMode.USER.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$org$jivesoftware$smack$XMPPConnection$FromMode[FromMode.UNCHANGED.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public class InterceptorWrapper {
        private final StanzaFilter packetFilter;
        private final StanzaListener packetInterceptor;

        public InterceptorWrapper(StanzaListener stanzaListener, StanzaFilter stanzaFilter) {
            this.packetInterceptor = stanzaListener;
            this.packetFilter = stanzaFilter;
        }

        public boolean filterMatches(Stanza stanza) {
            return this.packetFilter == null || this.packetFilter.accept(stanza);
        }

        public StanzaListener getInterceptor() {
            return this.packetInterceptor;
        }
    }

    class ListenerNotification implements Runnable {
        private final Stanza packet;

        public ListenerNotification(Stanza stanza) {
            this.packet = stanza;
        }

        public void run() {
            AbstractXMPPConnection.this.invokePacketCollectorsAndNotifyRecvListeners(this.packet);
        }
    }

    public class ListenerWrapper {
        private final StanzaFilter packetFilter;
        private final StanzaListener packetListener;

        public ListenerWrapper(StanzaListener stanzaListener, StanzaFilter stanzaFilter) {
            this.packetListener = stanzaListener;
            this.packetFilter = stanzaFilter;
        }

        public boolean filterMatches(Stanza stanza) {
            return this.packetFilter == null || this.packetFilter.accept(stanza);
        }

        public StanzaListener getListener() {
            return this.packetListener;
        }
    }

    protected abstract void connectInternal();

    public abstract boolean isSecureConnection();

    public abstract boolean isUsingCompression();

    protected abstract void loginAnonymously();

    protected abstract void loginNonAnonymously(String str, String str2, String str3);

    public abstract void send(PlainStreamElement plainStreamElement);

    protected abstract void sendStanzaInternal(Stanza stanza);

    protected abstract void shutdown();

    static {
        $assertionsDisabled = !AbstractXMPPConnection.class.desiredAssertionStatus();
        LOGGER = Logger.getLogger(AbstractXMPPConnection.class.getName());
        connectionCounter = new AtomicInteger(0);
        SmackConfiguration.getVersion();
        replyToUnknownIqDefault = true;
    }

    protected static Collection<ConnectionCreationListener> getConnectionCreationListeners() {
        return XMPPConnectionRegistry.getConnectionCreationListeners();
    }

    protected AbstractXMPPConnection(ConnectionConfiguration connectionConfiguration) {
        this.connectionListeners = new CopyOnWriteArraySet();
        this.collectors = new ConcurrentLinkedQueue();
        this.syncRecvListeners = new LinkedHashMap();
        this.asyncRecvListeners = new LinkedHashMap();
        this.sendListeners = new HashMap();
        this.interceptors = new HashMap();
        this.connectionLock = new ReentrantLock();
        this.streamFeatures = new HashMap();
        this.connected = false;
        this.packetReplyTimeout = (long) SmackConfiguration.getDefaultPacketReplyTimeout();
        this.debugger = null;
        this.lastFeaturesReceived = new SynchronizationPoint(this);
        this.saslFeatureReceived = new SynchronizationPoint(this);
        this.saslAuthentication = new SASLAuthentication(this);
        this.connectionCounterValue = connectionCounter.getAndIncrement();
        this.fromMode = FromMode.OMITTED;
        this.parsingExceptionCallback = SmackConfiguration.getDefaultParsingExceptionCallback();
        this.executorService = new BoundedThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, 100, new SmackExecutorThreadFactory(this.connectionCounterValue, "Incoming Processor"));
        this.removeCallbacksService = Executors.newSingleThreadScheduledExecutor(new SmackExecutorThreadFactory(this.connectionCounterValue, "Remove Callbacks"));
        this.cachedExecutorService = Executors.newCachedThreadPool(new SmackExecutorThreadFactory(this.connectionCounterValue, "Cached Executor"));
        this.singleThreadedExecutorService = Executors.newSingleThreadExecutor(new SmackExecutorThreadFactory(getConnectionCounter(), "Single Threaded Executor"));
        this.authenticated = false;
        this.wasAuthenticated = false;
        this.setIqRequestHandler = new HashMap();
        this.getIqRequestHandler = new HashMap();
        this.replyToUnkownIq = replyToUnknownIqDefault;
        this.config = connectionConfiguration;
    }

    public ConnectionConfiguration getConfiguration() {
        return this.config;
    }

    public String getServiceName() {
        if (this.serviceName != null) {
            return this.serviceName;
        }
        return this.config.getServiceName();
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }

    public synchronized AbstractXMPPConnection connect() {
        throwAlreadyConnectedExceptionIfAppropriate();
        this.saslAuthentication.init();
        this.saslFeatureReceived.init();
        this.lastFeaturesReceived.init();
        this.streamId = null;
        connectInternal();
        return this;
    }

    public synchronized void login() {
        if (isAnonymous()) {
            throwNotConnectedExceptionIfAppropriate("Did you call connect() before login()?");
            throwAlreadyLoggedInExceptionIfAppropriate();
            loginAnonymously();
        } else {
            login(this.usedUsername != null ? this.usedUsername : this.config.getUsername(), this.usedPassword != null ? this.usedPassword : this.config.getPassword(), this.usedResource != null ? this.usedResource : this.config.getResource());
        }
    }

    public synchronized void login(CharSequence charSequence, String str) {
        login(charSequence, str, this.config.getResource());
    }

    public synchronized void login(CharSequence charSequence, String str, String str2) {
        if (!this.config.allowNullOrEmptyUsername) {
            StringUtils.requireNotNullOrEmpty(charSequence, "Username must not be null or empty");
        }
        throwNotConnectedExceptionIfAppropriate();
        throwAlreadyLoggedInExceptionIfAppropriate();
        this.usedUsername = charSequence != null ? charSequence.toString() : null;
        this.usedPassword = str;
        this.usedResource = str2;
        loginNonAnonymously(this.usedUsername, this.usedPassword, this.usedResource);
    }

    public final boolean isConnected() {
        return this.connected;
    }

    public final boolean isAuthenticated() {
        return this.authenticated;
    }

    public final String getUser() {
        return this.user;
    }

    public String getStreamId() {
        if (isConnected()) {
            return this.streamId;
        }
        return null;
    }

    protected void bindResourceAndEstablishSession(String str) {
        LOGGER.finer("Waiting for last features to be received before continuing with resource binding");
        this.lastFeaturesReceived.checkIfSuccessOrWait();
        if (hasFeature(Bind.ELEMENT, Bind.NAMESPACE)) {
            Stanza newSet = Bind.newSet(str);
            this.user = ((Bind) createPacketCollectorAndSend(new StanzaIdFilter(newSet), newSet).nextResultOrThrow()).getJid();
            this.serviceName = XmppStringUtils.m13444b(this.user);
            Feature feature = (Feature) getFeature(Session.ELEMENT, Session.NAMESPACE);
            if (feature != null && !feature.isOptional() && !getConfiguration().isLegacySessionDisabled()) {
                newSet = new Session();
                createPacketCollectorAndSend(new StanzaIdFilter(newSet), newSet).nextResultOrThrow();
                return;
            }
            return;
        }
        throw new ResourceBindingNotOfferedException();
    }

    protected void afterSuccessfulLogin(boolean z) {
        this.authenticated = true;
        if (this.config.isDebuggerEnabled() && this.debugger != null) {
            this.debugger.userHasLogged(this.user);
        }
        callConnectionAuthenticatedListener(z);
        if (this.config.isSendPresence() && !z) {
            sendStanza(new Presence(Presence.Type.available));
        }
    }

    public final boolean isAnonymous() {
        return this.config.getUsername() == null && this.usedUsername == null && !this.config.allowNullOrEmptyUsername;
    }

    protected List<HostAddress> populateHostAddresses() {
        List linkedList = new LinkedList();
        if (this.config.host != null) {
            this.hostAddresses = new ArrayList(1);
            this.hostAddresses.add(new HostAddress(this.config.host, this.config.port));
        } else {
            this.hostAddresses = DNSUtil.resolveXMPPDomain(this.config.serviceName, linkedList);
        }
        if ($assertionsDisabled || !this.hostAddresses.isEmpty()) {
            return linkedList;
        }
        throw new AssertionError();
    }

    protected Lock getConnectionLock() {
        return this.connectionLock;
    }

    protected void throwNotConnectedExceptionIfAppropriate() {
        throwNotConnectedExceptionIfAppropriate(null);
    }

    protected void throwNotConnectedExceptionIfAppropriate(String str) {
        if (!isConnected()) {
            throw new NotConnectedException(str);
        }
    }

    protected void throwAlreadyConnectedExceptionIfAppropriate() {
        if (isConnected()) {
            throw new AlreadyConnectedException();
        }
    }

    protected void throwAlreadyLoggedInExceptionIfAppropriate() {
        if (isAuthenticated()) {
            throw new AlreadyLoggedInException();
        }
    }

    @Deprecated
    public void sendPacket(Stanza stanza) {
        sendStanza(stanza);
    }

    public void sendStanza(Stanza stanza) {
        Objects.requireNonNull(stanza, "Packet must not be null");
        throwNotConnectedExceptionIfAppropriate();
        switch (C09899.$SwitchMap$org$jivesoftware$smack$XMPPConnection$FromMode[this.fromMode.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                stanza.setFrom(null);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                stanza.setFrom(getUser());
                break;
        }
        firePacketInterceptors(stanza);
        sendStanzaInternal(stanza);
    }

    protected SASLAuthentication getSASLAuthentication() {
        return this.saslAuthentication;
    }

    public void disconnect() {
        try {
            disconnect(new Presence(Presence.Type.unavailable));
        } catch (Throwable e) {
            LOGGER.log(Level.FINEST, "Connection is already disconnected", e);
        }
    }

    public synchronized void disconnect(Presence presence) {
        sendStanza(presence);
        shutdown();
        callConnectionClosedListener();
    }

    public void addConnectionListener(ConnectionListener connectionListener) {
        if (connectionListener != null) {
            this.connectionListeners.add(connectionListener);
        }
    }

    public void removeConnectionListener(ConnectionListener connectionListener) {
        this.connectionListeners.remove(connectionListener);
    }

    public PacketCollector createPacketCollectorAndSend(IQ iq) {
        return createPacketCollectorAndSend(new IQReplyFilter(iq, this), iq);
    }

    public PacketCollector createPacketCollectorAndSend(StanzaFilter stanzaFilter, Stanza stanza) {
        NotConnectedException e;
        PacketCollector createPacketCollector = createPacketCollector(stanzaFilter);
        try {
            sendStanza(stanza);
            return createPacketCollector;
        } catch (NotConnectedException e2) {
            e = e2;
            createPacketCollector.cancel();
            throw e;
        } catch (RuntimeException e3) {
            e = e3;
            createPacketCollector.cancel();
            throw e;
        }
    }

    public PacketCollector createPacketCollector(StanzaFilter stanzaFilter) {
        return createPacketCollector(PacketCollector.newConfiguration().setStanzaFilter(stanzaFilter));
    }

    public PacketCollector createPacketCollector(Configuration configuration) {
        PacketCollector packetCollector = new PacketCollector(this, configuration);
        this.collectors.add(packetCollector);
        return packetCollector;
    }

    public void removePacketCollector(PacketCollector packetCollector) {
        this.collectors.remove(packetCollector);
    }

    @Deprecated
    public void addPacketListener(StanzaListener stanzaListener, StanzaFilter stanzaFilter) {
        addAsyncStanzaListener(stanzaListener, stanzaFilter);
    }

    @Deprecated
    public boolean removePacketListener(StanzaListener stanzaListener) {
        return removeAsyncStanzaListener(stanzaListener);
    }

    public void addSyncStanzaListener(StanzaListener stanzaListener, StanzaFilter stanzaFilter) {
        if (stanzaListener == null) {
            throw new NullPointerException("Packet listener is null.");
        }
        ListenerWrapper listenerWrapper = new ListenerWrapper(stanzaListener, stanzaFilter);
        synchronized (this.syncRecvListeners) {
            this.syncRecvListeners.put(stanzaListener, listenerWrapper);
        }
    }

    public boolean removeSyncStanzaListener(StanzaListener stanzaListener) {
        boolean z;
        synchronized (this.syncRecvListeners) {
            z = this.syncRecvListeners.remove(stanzaListener) != null;
        }
        return z;
    }

    public void addAsyncStanzaListener(StanzaListener stanzaListener, StanzaFilter stanzaFilter) {
        if (stanzaListener == null) {
            throw new NullPointerException("Packet listener is null.");
        }
        ListenerWrapper listenerWrapper = new ListenerWrapper(stanzaListener, stanzaFilter);
        synchronized (this.asyncRecvListeners) {
            this.asyncRecvListeners.put(stanzaListener, listenerWrapper);
        }
    }

    public boolean removeAsyncStanzaListener(StanzaListener stanzaListener) {
        boolean z;
        synchronized (this.asyncRecvListeners) {
            z = this.asyncRecvListeners.remove(stanzaListener) != null;
        }
        return z;
    }

    public void addPacketSendingListener(StanzaListener stanzaListener, StanzaFilter stanzaFilter) {
        if (stanzaListener == null) {
            throw new NullPointerException("Packet listener is null.");
        }
        ListenerWrapper listenerWrapper = new ListenerWrapper(stanzaListener, stanzaFilter);
        synchronized (this.sendListeners) {
            this.sendListeners.put(stanzaListener, listenerWrapper);
        }
    }

    public void removePacketSendingListener(StanzaListener stanzaListener) {
        synchronized (this.sendListeners) {
            this.sendListeners.remove(stanzaListener);
        }
    }

    protected void firePacketSendingListeners(Stanza stanza) {
        List linkedList = new LinkedList();
        synchronized (this.sendListeners) {
            for (ListenerWrapper listenerWrapper : this.sendListeners.values()) {
                if (listenerWrapper.filterMatches(stanza)) {
                    linkedList.add(listenerWrapper.getListener());
                }
            }
        }
        if (!linkedList.isEmpty()) {
            asyncGo(new C09811(linkedList, stanza));
        }
    }

    public void addPacketInterceptor(StanzaListener stanzaListener, StanzaFilter stanzaFilter) {
        if (stanzaListener == null) {
            throw new NullPointerException("Packet interceptor is null.");
        }
        InterceptorWrapper interceptorWrapper = new InterceptorWrapper(stanzaListener, stanzaFilter);
        synchronized (this.interceptors) {
            this.interceptors.put(stanzaListener, interceptorWrapper);
        }
    }

    public void removePacketInterceptor(StanzaListener stanzaListener) {
        synchronized (this.interceptors) {
            this.interceptors.remove(stanzaListener);
        }
    }

    private void firePacketInterceptors(Stanza stanza) {
        List<StanzaListener> linkedList = new LinkedList();
        synchronized (this.interceptors) {
            for (InterceptorWrapper interceptorWrapper : this.interceptors.values()) {
                if (interceptorWrapper.filterMatches(stanza)) {
                    linkedList.add(interceptorWrapper.getInterceptor());
                }
            }
        }
        for (StanzaListener processPacket : linkedList) {
            try {
                processPacket.processPacket(stanza);
            } catch (Throwable e) {
                LOGGER.log(Level.SEVERE, "Packet interceptor threw exception", e);
            }
        }
    }

    protected void initDebugger() {
        if (this.reader == null || this.writer == null) {
            throw new NullPointerException("Reader or writer isn't initialized.");
        } else if (this.config.isDebuggerEnabled()) {
            if (this.debugger == null) {
                this.debugger = SmackConfiguration.createDebugger(this, this.writer, this.reader);
            }
            if (this.debugger == null) {
                LOGGER.severe("Debugging enabled but could not find debugger class");
                return;
            }
            this.reader = this.debugger.newConnectionReader(this.reader);
            this.writer = this.debugger.newConnectionWriter(this.writer);
        }
    }

    public long getPacketReplyTimeout() {
        return this.packetReplyTimeout;
    }

    public void setPacketReplyTimeout(long j) {
        this.packetReplyTimeout = j;
    }

    public static void setReplyToUnknownIqDefault(boolean z) {
        replyToUnknownIqDefault = z;
    }

    public void setReplyToUnknownIq(boolean z) {
        this.replyToUnkownIq = z;
    }

    protected void parseAndProcessStanza(XmlPullParser xmlPullParser) {
        ParserUtils.assertAtStartTag(xmlPullParser);
        int depth = xmlPullParser.getDepth();
        Stanza stanza = null;
        try {
            stanza = PacketParserUtils.parseStanza(xmlPullParser);
        } catch (Exception e) {
            UnparsablePacket unparsablePacket = new UnparsablePacket(PacketParserUtils.parseContentDepth(xmlPullParser, depth), e);
            ParsingExceptionCallback parsingExceptionCallback = getParsingExceptionCallback();
            if (parsingExceptionCallback != null) {
                parsingExceptionCallback.handleUnparsablePacket(unparsablePacket);
            }
        }
        ParserUtils.assertAtEndTag(xmlPullParser);
        if (stanza != null) {
            processPacket(stanza);
        }
    }

    protected void processPacket(Stanza stanza) {
        if ($assertionsDisabled || stanza != null) {
            this.lastStanzaReceived = System.currentTimeMillis();
            this.executorService.executeBlocking(new ListenerNotification(stanza));
            return;
        }
        throw new AssertionError();
    }

    protected void invokePacketCollectorsAndNotifyRecvListeners(Stanza stanza) {
        if (stanza instanceof IQ) {
            IQ iq = (IQ) stanza;
            Type type = iq.getType();
            switch (C09899.$SwitchMap$org$jivesoftware$smack$packet$IQ$Type[type.ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    IQRequestHandler iQRequestHandler;
                    String b = XmppStringUtils.m13445b(iq.getChildElementName(), iq.getChildElementNamespace());
                    IQRequestHandler iQRequestHandler2;
                    switch (C09899.$SwitchMap$org$jivesoftware$smack$packet$IQ$Type[type.ordinal()]) {
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                            synchronized (this.setIqRequestHandler) {
                                iQRequestHandler2 = (IQRequestHandler) this.setIqRequestHandler.get(b);
                                break;
                            }
                            iQRequestHandler = iQRequestHandler2;
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                            synchronized (this.getIqRequestHandler) {
                                iQRequestHandler2 = (IQRequestHandler) this.getIqRequestHandler.get(b);
                                break;
                            }
                            iQRequestHandler = iQRequestHandler2;
                            break;
                        default:
                            throw new IllegalStateException("Should only encounter IQ type 'get' or 'set'");
                    }
                    if (iQRequestHandler == null) {
                        if (this.replyToUnkownIq) {
                            try {
                                sendStanza(IQ.createErrorResponse(iq, new XMPPError(Condition.feature_not_implemented)));
                                break;
                            } catch (Throwable e) {
                                LOGGER.log(Level.WARNING, "NotConnectedException while sending error IQ to unkown IQ request", e);
                                break;
                            }
                        }
                        return;
                    }
                    ExecutorService executorService = null;
                    switch (C09899.f8574xdbd7d2a3[iQRequestHandler.getMode().ordinal()]) {
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                            executorService = this.singleThreadedExecutorService;
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                            executorService = this.cachedExecutorService;
                            break;
                    }
                    executorService.execute(new C09822(iQRequestHandler, iq));
                    return;
            }
        }
        Collection<StanzaListener> linkedList = new LinkedList();
        synchronized (this.asyncRecvListeners) {
            for (ListenerWrapper listenerWrapper : this.asyncRecvListeners.values()) {
                if (listenerWrapper.filterMatches(stanza)) {
                    linkedList.add(listenerWrapper.getListener());
                }
            }
        }
        for (StanzaListener c09833 : linkedList) {
            asyncGo(new C09833(c09833, stanza));
        }
        for (PacketCollector processPacket : this.collectors) {
            processPacket.processPacket(stanza);
        }
        linkedList.clear();
        synchronized (this.syncRecvListeners) {
            for (ListenerWrapper listenerWrapper2 : this.syncRecvListeners.values()) {
                if (listenerWrapper2.filterMatches(stanza)) {
                    linkedList.add(listenerWrapper2.getListener());
                }
            }
        }
        this.singleThreadedExecutorService.execute(new C09844(linkedList, stanza));
    }

    protected void setWasAuthenticated() {
        if (!this.wasAuthenticated) {
            this.wasAuthenticated = this.authenticated;
        }
    }

    protected void callConnectionConnectedListener() {
        for (ConnectionListener connected : this.connectionListeners) {
            connected.connected(this);
        }
    }

    protected void callConnectionAuthenticatedListener(boolean z) {
        for (ConnectionListener authenticated : this.connectionListeners) {
            try {
                authenticated.authenticated(this, z);
            } catch (Throwable e) {
                LOGGER.log(Level.SEVERE, "Exception in authenticated listener", e);
            }
        }
    }

    void callConnectionClosedListener() {
        for (ConnectionListener connectionClosed : this.connectionListeners) {
            try {
                connectionClosed.connectionClosed();
            } catch (Throwable e) {
                LOGGER.log(Level.SEVERE, "Error in listener while closing connection", e);
            }
        }
    }

    protected void callConnectionClosedOnErrorListener(Exception exception) {
        LOGGER.log(Level.WARNING, "Connection closed with error", exception);
        for (ConnectionListener connectionClosedOnError : this.connectionListeners) {
            try {
                connectionClosedOnError.connectionClosedOnError(exception);
            } catch (Throwable e) {
                LOGGER.log(Level.SEVERE, "Error in listener while closing connection", e);
            }
        }
    }

    protected void notifyReconnection() {
        for (ConnectionListener reconnectionSuccessful : this.connectionListeners) {
            try {
                reconnectionSuccessful.reconnectionSuccessful();
            } catch (Throwable e) {
                LOGGER.log(Level.WARNING, "notifyReconnection()", e);
            }
        }
    }

    public int getConnectionCounter() {
        return this.connectionCounterValue;
    }

    public void setFromMode(FromMode fromMode) {
        this.fromMode = fromMode;
    }

    public FromMode getFromMode() {
        return this.fromMode;
    }

    protected void finalize() {
        LOGGER.fine("finalizing XMPPConnection ( " + getConnectionCounter() + "): Shutting down executor services");
        try {
            this.executorService.shutdownNow();
            this.cachedExecutorService.shutdown();
            this.removeCallbacksService.shutdownNow();
            this.singleThreadedExecutorService.shutdownNow();
        } catch (Throwable th) {
            LOGGER.log(Level.WARNING, "finalize() threw trhowable", th);
        } finally {
            super.finalize();
        }
    }

    protected final void parseFeatures(XmlPullParser xmlPullParser) {
        this.streamFeatures.clear();
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if (next == 2 && xmlPullParser.getDepth() == depth + 1) {
                ExtensionElement extensionElement = null;
                String name = xmlPullParser.getName();
                String namespace = xmlPullParser.getNamespace();
                Object obj = -1;
                int i;
                switch (name.hashCode()) {
                    case -676919238:
                        if (name.equals(Mechanisms.ELEMENT)) {
                            obj = 1;
                            break;
                        }
                        break;
                    case 3023933:
                        if (name.equals(Bind.ELEMENT)) {
                            i = 2;
                            break;
                        }
                        break;
                    case 1316817241:
                        if (name.equals(StartTls.ELEMENT)) {
                            obj = null;
                            break;
                        }
                        break;
                    case 1431984486:
                        if (name.equals(Compress.Feature.ELEMENT)) {
                            obj = 4;
                            break;
                        }
                        break;
                    case 1984987798:
                        if (name.equals(Session.ELEMENT)) {
                            i = 3;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                        extensionElement = PacketParserUtils.parseStartTlsFeature(xmlPullParser);
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        extensionElement = new Mechanisms(PacketParserUtils.parseMechanisms(xmlPullParser));
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                        extensionElement = Bind.Feature.INSTANCE;
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                        extensionElement = PacketParserUtils.parseSessionFeature(xmlPullParser);
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                        extensionElement = PacketParserUtils.parseCompressionFeature(xmlPullParser);
                        break;
                    default:
                        ExtensionElementProvider streamFeatureProvider = ProviderManager.getStreamFeatureProvider(name, namespace);
                        if (streamFeatureProvider != null) {
                            extensionElement = (ExtensionElement) streamFeatureProvider.parse(xmlPullParser);
                            break;
                        }
                        break;
                }
                if (extensionElement != null) {
                    addStreamFeature(extensionElement);
                }
            } else if (next == 3 && xmlPullParser.getDepth() == depth) {
                if (hasFeature(Mechanisms.ELEMENT, SaslStreamElements.NAMESPACE) && (!hasFeature(StartTls.ELEMENT, StartTls.NAMESPACE) || this.config.getSecurityMode() == SecurityMode.disabled)) {
                    this.saslFeatureReceived.reportSuccess();
                }
                if (hasFeature(Bind.ELEMENT, Bind.NAMESPACE) && !(hasFeature(Compress.Feature.ELEMENT, Compressed.NAMESPACE) && this.config.isCompressionEnabled())) {
                    this.lastFeaturesReceived.reportSuccess();
                }
                afterFeaturesReceived();
                return;
            }
        }
    }

    protected void afterFeaturesReceived() {
    }

    public <F extends ExtensionElement> F getFeature(String str, String str2) {
        return (ExtensionElement) this.streamFeatures.get(XmppStringUtils.m13445b(str, str2));
    }

    public boolean hasFeature(String str, String str2) {
        return getFeature(str, str2) != null;
    }

    private void addStreamFeature(ExtensionElement extensionElement) {
        this.streamFeatures.put(XmppStringUtils.m13445b(extensionElement.getElementName(), extensionElement.getNamespace()), extensionElement);
    }

    public void sendStanzaWithResponseCallback(Stanza stanza, StanzaFilter stanzaFilter, StanzaListener stanzaListener) {
        sendStanzaWithResponseCallback(stanza, stanzaFilter, stanzaListener, null);
    }

    public void sendStanzaWithResponseCallback(Stanza stanza, StanzaFilter stanzaFilter, StanzaListener stanzaListener, ExceptionCallback exceptionCallback) {
        sendStanzaWithResponseCallback(stanza, stanzaFilter, stanzaListener, exceptionCallback, getPacketReplyTimeout());
    }

    public void sendStanzaWithResponseCallback(Stanza stanza, StanzaFilter stanzaFilter, StanzaListener stanzaListener, ExceptionCallback exceptionCallback, long j) {
        Objects.requireNonNull(stanza, "stanza must not be null");
        Objects.requireNonNull(stanzaFilter, "replyFilter must not be null");
        Objects.requireNonNull(stanzaListener, "callback must not be null");
        StanzaListener c09855 = new C09855(stanzaListener, exceptionCallback);
        this.removeCallbacksService.schedule(new C09866(c09855, exceptionCallback, stanzaFilter), j, TimeUnit.MILLISECONDS);
        addAsyncStanzaListener(c09855, stanzaFilter);
        sendStanza(stanza);
    }

    public void sendIqWithResponseCallback(IQ iq, StanzaListener stanzaListener) {
        sendIqWithResponseCallback(iq, stanzaListener, null);
    }

    public void sendIqWithResponseCallback(IQ iq, StanzaListener stanzaListener, ExceptionCallback exceptionCallback) {
        sendIqWithResponseCallback(iq, stanzaListener, exceptionCallback, getPacketReplyTimeout());
    }

    public void sendIqWithResponseCallback(IQ iq, StanzaListener stanzaListener, ExceptionCallback exceptionCallback, long j) {
        sendStanzaWithResponseCallback(iq, new IQReplyFilter(iq, this), stanzaListener, exceptionCallback, j);
    }

    public void addOneTimeSyncCallback(StanzaListener stanzaListener, StanzaFilter stanzaFilter) {
        StanzaListener c09877 = new C09877(stanzaListener);
        addSyncStanzaListener(c09877, stanzaFilter);
        this.removeCallbacksService.schedule(new C09888(c09877), getPacketReplyTimeout(), TimeUnit.MILLISECONDS);
    }

    public IQRequestHandler registerIQRequestHandler(IQRequestHandler iQRequestHandler) {
        IQRequestHandler iQRequestHandler2;
        String b = XmppStringUtils.m13445b(iQRequestHandler.getElement(), iQRequestHandler.getNamespace());
        switch (C09899.$SwitchMap$org$jivesoftware$smack$packet$IQ$Type[iQRequestHandler.getType().ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                synchronized (this.setIqRequestHandler) {
                    iQRequestHandler2 = (IQRequestHandler) this.setIqRequestHandler.put(b, iQRequestHandler);
                    break;
                }
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                synchronized (this.getIqRequestHandler) {
                    iQRequestHandler2 = (IQRequestHandler) this.getIqRequestHandler.put(b, iQRequestHandler);
                    break;
                }
                break;
            default:
                throw new IllegalArgumentException("Only IQ type of 'get' and 'set' allowed");
        }
        return iQRequestHandler2;
    }

    public final IQRequestHandler unregisterIQRequestHandler(IQRequestHandler iQRequestHandler) {
        return unregisterIQRequestHandler(iQRequestHandler.getElement(), iQRequestHandler.getNamespace(), iQRequestHandler.getType());
    }

    public IQRequestHandler unregisterIQRequestHandler(String str, String str2, Type type) {
        IQRequestHandler iQRequestHandler;
        String b = XmppStringUtils.m13445b(str, str2);
        switch (C09899.$SwitchMap$org$jivesoftware$smack$packet$IQ$Type[type.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                synchronized (this.setIqRequestHandler) {
                    iQRequestHandler = (IQRequestHandler) this.setIqRequestHandler.remove(b);
                    break;
                }
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                synchronized (this.getIqRequestHandler) {
                    iQRequestHandler = (IQRequestHandler) this.getIqRequestHandler.remove(b);
                    break;
                }
                break;
            default:
                throw new IllegalArgumentException("Only IQ type of 'get' and 'set' allowed");
        }
        return iQRequestHandler;
    }

    public long getLastStanzaReceived() {
        return this.lastStanzaReceived;
    }

    public void setParsingExceptionCallback(ParsingExceptionCallback parsingExceptionCallback) {
        this.parsingExceptionCallback = parsingExceptionCallback;
    }

    public ParsingExceptionCallback getParsingExceptionCallback() {
        return this.parsingExceptionCallback;
    }

    protected final void asyncGo(Runnable runnable) {
        this.cachedExecutorService.execute(runnable);
    }

    protected final ScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        return this.removeCallbacksService.schedule(runnable, j, timeUnit);
    }
}
