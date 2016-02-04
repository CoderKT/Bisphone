package org.jivesoftware.smack;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Random;
import java.util.WeakHashMap;
import java.util.logging.Logger;
import org.jivesoftware.smack.XMPPException.StreamErrorException;
import org.jivesoftware.smack.packet.StreamError.Condition;
import org.jivesoftware.smack.util.Async;
import se.emilsjolander.stickylistheaders.C1128R;

public class ReconnectionManager {
    private static final Map<AbstractXMPPConnection, ReconnectionManager> INSTANCES;
    private static final Logger LOGGER;
    private static int defaultFixedDelay;
    private static ReconnectionPolicy defaultReconnectionPolicy;
    private static boolean enabledPerDefault;
    private boolean automaticReconnectEnabled;
    private final ConnectionListener connectionListener;
    boolean done;
    private volatile int fixedDelay;
    private final int randomBase;
    private volatile ReconnectionPolicy reconnectionPolicy;
    private final Runnable reconnectionRunnable;
    private Thread reconnectionThread;
    private final WeakReference<AbstractXMPPConnection> weakRefConnection;

    /* renamed from: org.jivesoftware.smack.ReconnectionManager.1 */
    final class C09911 implements ConnectionCreationListener {
        C09911() {
        }

        public void connectionCreated(XMPPConnection xMPPConnection) {
            if (xMPPConnection instanceof AbstractXMPPConnection) {
                ReconnectionManager.getInstanceFor((AbstractXMPPConnection) xMPPConnection);
            }
        }
    }

    /* renamed from: org.jivesoftware.smack.ReconnectionManager.2 */
    class C09922 extends Thread {
        private int attempts;

        C09922() {
            this.attempts = 0;
        }

        private int timeDelay() {
            this.attempts++;
            switch (C09944.f8575x55bc48cf[ReconnectionManager.this.reconnectionPolicy.ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    return ReconnectionManager.this.fixedDelay;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    if (this.attempts > 13) {
                        return (ReconnectionManager.this.randomBase * 6) * 5;
                    }
                    if (this.attempts > 7) {
                        return ReconnectionManager.this.randomBase * 6;
                    }
                    return ReconnectionManager.this.randomBase;
                default:
                    throw new AssertionError("Unknown reconnection policy " + ReconnectionManager.this.reconnectionPolicy);
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r6 = this;
            r5 = 0;
            r0 = org.jivesoftware.smack.ReconnectionManager.this;
            r0 = r0.weakRefConnection;
            r0 = r0.get();
            r0 = (org.jivesoftware.smack.AbstractXMPPConnection) r0;
            if (r0 != 0) goto L_0x0027;
        L_0x000f:
            return;
        L_0x0010:
            r1 = org.jivesoftware.smack.ReconnectionManager.this;	 Catch:{ SmackException -> 0x007e, IOException -> 0x0096, XMPPException -> 0x0099 }
            r1 = r1.isReconnectionPossible(r0);	 Catch:{ SmackException -> 0x007e, IOException -> 0x0096, XMPPException -> 0x0099 }
            if (r1 == 0) goto L_0x001b;
        L_0x0018:
            r0.connect();	 Catch:{ SmackException -> 0x007e, IOException -> 0x0096, XMPPException -> 0x0099 }
        L_0x001b:
            r1 = r0.isAuthenticated();	 Catch:{ SmackException -> 0x007e, IOException -> 0x0096, XMPPException -> 0x0099 }
            if (r1 != 0) goto L_0x0024;
        L_0x0021:
            r0.login();	 Catch:{ SmackException -> 0x007e, IOException -> 0x0096, XMPPException -> 0x0099 }
        L_0x0024:
            r1 = 0;
            r6.attempts = r1;	 Catch:{ SmackException -> 0x007e, IOException -> 0x0096, XMPPException -> 0x0099 }
        L_0x0027:
            r1 = org.jivesoftware.smack.ReconnectionManager.this;
            r1 = r1.isReconnectionPossible(r0);
            if (r1 == 0) goto L_0x000f;
        L_0x002f:
            r1 = r6.timeDelay();
        L_0x0033:
            r2 = org.jivesoftware.smack.ReconnectionManager.this;
            r2 = r2.isReconnectionPossible(r0);
            if (r2 == 0) goto L_0x0066;
        L_0x003b:
            if (r1 <= 0) goto L_0x0066;
        L_0x003d:
            r2 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
            java.lang.Thread.sleep(r2);	 Catch:{ InterruptedException -> 0x005a }
            r2 = r1 + -1;
            r1 = r0.connectionListeners;	 Catch:{ InterruptedException -> 0x005a }
            r3 = r1.iterator();	 Catch:{ InterruptedException -> 0x005a }
        L_0x004a:
            r1 = r3.hasNext();	 Catch:{ InterruptedException -> 0x005a }
            if (r1 == 0) goto L_0x007c;
        L_0x0050:
            r1 = r3.next();	 Catch:{ InterruptedException -> 0x005a }
            r1 = (org.jivesoftware.smack.ConnectionListener) r1;	 Catch:{ InterruptedException -> 0x005a }
            r1.reconnectingIn(r2);	 Catch:{ InterruptedException -> 0x005a }
            goto L_0x004a;
        L_0x005a:
            r1 = move-exception;
            r2 = org.jivesoftware.smack.ReconnectionManager.LOGGER;
            r3 = java.util.logging.Level.FINE;
            r4 = "waiting for reconnection interrupted";
            r2.log(r3, r4, r1);
        L_0x0066:
            r1 = r0.connectionListeners;
            r2 = r1.iterator();
        L_0x006c:
            r1 = r2.hasNext();
            if (r1 == 0) goto L_0x0010;
        L_0x0072:
            r1 = r2.next();
            r1 = (org.jivesoftware.smack.ConnectionListener) r1;
            r1.reconnectingIn(r5);
            goto L_0x006c;
        L_0x007c:
            r1 = r2;
            goto L_0x0033;
        L_0x007e:
            r1 = move-exception;
            r2 = r1;
        L_0x0080:
            r1 = r0.connectionListeners;
            r3 = r1.iterator();
        L_0x0086:
            r1 = r3.hasNext();
            if (r1 == 0) goto L_0x0027;
        L_0x008c:
            r1 = r3.next();
            r1 = (org.jivesoftware.smack.ConnectionListener) r1;
            r1.reconnectionFailed(r2);
            goto L_0x0086;
        L_0x0096:
            r1 = move-exception;
            r2 = r1;
            goto L_0x0080;
        L_0x0099:
            r1 = move-exception;
            r2 = r1;
            goto L_0x0080;
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.ReconnectionManager.2.run():void");
        }
    }

    /* renamed from: org.jivesoftware.smack.ReconnectionManager.3 */
    class C09933 extends AbstractConnectionListener {
        C09933() {
        }

        public void connectionClosed() {
            ReconnectionManager.this.done = true;
        }

        public void authenticated(XMPPConnection xMPPConnection, boolean z) {
            ReconnectionManager.this.done = false;
        }

        public void connectionClosedOnError(Exception exception) {
            ReconnectionManager.this.done = false;
            if (ReconnectionManager.this.isAutomaticReconnectEnabled()) {
                if (exception instanceof StreamErrorException) {
                    if (Condition.conflict == ((StreamErrorException) exception).getStreamError().getCondition()) {
                        return;
                    }
                }
                ReconnectionManager.this.reconnect();
            }
        }
    }

    /* renamed from: org.jivesoftware.smack.ReconnectionManager.4 */
    /* synthetic */ class C09944 {
        static final /* synthetic */ int[] f8575x55bc48cf;

        static {
            f8575x55bc48cf = new int[ReconnectionPolicy.values().length];
            try {
                f8575x55bc48cf[ReconnectionPolicy.FIXED_DELAY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f8575x55bc48cf[ReconnectionPolicy.RANDOM_INCREASING_DELAY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public enum ReconnectionPolicy {
        RANDOM_INCREASING_DELAY,
        FIXED_DELAY
    }

    static {
        LOGGER = Logger.getLogger(ReconnectionManager.class.getName());
        INSTANCES = new WeakHashMap();
        XMPPConnectionRegistry.addConnectionCreationListener(new C09911());
        enabledPerDefault = false;
        defaultFixedDelay = 15;
        defaultReconnectionPolicy = ReconnectionPolicy.RANDOM_INCREASING_DELAY;
    }

    public static synchronized ReconnectionManager getInstanceFor(AbstractXMPPConnection abstractXMPPConnection) {
        ReconnectionManager reconnectionManager;
        synchronized (ReconnectionManager.class) {
            reconnectionManager = (ReconnectionManager) INSTANCES.get(abstractXMPPConnection);
            if (reconnectionManager == null) {
                reconnectionManager = new ReconnectionManager(abstractXMPPConnection);
                INSTANCES.put(abstractXMPPConnection, reconnectionManager);
            }
        }
        return reconnectionManager;
    }

    public static void setEnabledPerDefault(boolean z) {
        enabledPerDefault = z;
    }

    public static boolean getEnabledPerDefault() {
        return enabledPerDefault;
    }

    public static void setDefaultFixedDelay(int i) {
        defaultFixedDelay = i;
        setDefaultReconnectionPolicy(ReconnectionPolicy.FIXED_DELAY);
    }

    public static void setDefaultReconnectionPolicy(ReconnectionPolicy reconnectionPolicy) {
        defaultReconnectionPolicy = reconnectionPolicy;
    }

    public void setFixedDelay(int i) {
        this.fixedDelay = i;
        setReconnectionPolicy(ReconnectionPolicy.FIXED_DELAY);
    }

    public void setReconnectionPolicy(ReconnectionPolicy reconnectionPolicy) {
        this.reconnectionPolicy = reconnectionPolicy;
    }

    private ReconnectionManager(AbstractXMPPConnection abstractXMPPConnection) {
        this.randomBase = new Random().nextInt(13) + 2;
        this.fixedDelay = defaultFixedDelay;
        this.reconnectionPolicy = defaultReconnectionPolicy;
        this.automaticReconnectEnabled = false;
        this.done = false;
        this.connectionListener = new C09933();
        this.weakRefConnection = new WeakReference(abstractXMPPConnection);
        this.reconnectionRunnable = new C09922();
        if (getEnabledPerDefault()) {
            enableAutomaticReconnection();
        }
    }

    public synchronized void enableAutomaticReconnection() {
        if (!this.automaticReconnectEnabled) {
            XMPPConnection xMPPConnection = (XMPPConnection) this.weakRefConnection.get();
            if (xMPPConnection == null) {
                throw new IllegalStateException("Connection instance no longer available");
            }
            xMPPConnection.addConnectionListener(this.connectionListener);
            this.automaticReconnectEnabled = true;
        }
    }

    public synchronized void disableAutomaticReconnection() {
        if (this.automaticReconnectEnabled) {
            XMPPConnection xMPPConnection = (XMPPConnection) this.weakRefConnection.get();
            if (xMPPConnection == null) {
                throw new IllegalStateException("Connection instance no longer available");
            }
            xMPPConnection.removeConnectionListener(this.connectionListener);
            this.automaticReconnectEnabled = false;
        }
    }

    public boolean isAutomaticReconnectEnabled() {
        return this.automaticReconnectEnabled;
    }

    private boolean isReconnectionPossible(XMPPConnection xMPPConnection) {
        return (this.done || xMPPConnection.isConnected() || !isAutomaticReconnectEnabled()) ? false : true;
    }

    private synchronized void reconnect() {
        XMPPConnection xMPPConnection = (XMPPConnection) this.weakRefConnection.get();
        if (xMPPConnection == null) {
            LOGGER.fine("Connection is null, will not reconnect");
        } else if (this.reconnectionThread == null || !this.reconnectionThread.isAlive()) {
            this.reconnectionThread = Async.go(this.reconnectionRunnable, "Smack Reconnection Manager (" + xMPPConnection.getConnectionCounter() + ')');
        }
    }
}
