package app.xmpp;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Random;
import java.util.WeakHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.AbstractConnectionListener;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.XMPPException.StreamErrorException;
import org.jivesoftware.smack.packet.StreamError.Condition;
import org.jivesoftware.smack.util.Async;
import se.emilsjolander.stickylistheaders.C1128R;

public class ReconnectionManager {
    private static final Logger f4985b;
    private static final Map<CustomXMPPConnection, ReconnectionManager> f4986c;
    private static boolean f4987d;
    private static int f4988h;
    private static ReconnectionPolicy f4989i;
    boolean f4990a;
    private final WeakReference<CustomXMPPConnection> f4991e;
    private final int f4992f;
    private final CustomThread f4993g;
    private volatile int f4994j;
    private volatile ReconnectionPolicy f4995k;
    private boolean f4996l;
    private Thread f4997m;
    private final ConnectionListener f4998n;

    /* renamed from: app.xmpp.ReconnectionManager.1 */
    final class C05611 implements ConnectionCreationListener {
        C05611() {
        }

        public void connectionCreated(XMPPConnection xMPPConnection) {
            if (xMPPConnection instanceof CustomXMPPConnection) {
                ReconnectionManager.m7480a((CustomXMPPConnection) xMPPConnection);
            }
        }
    }

    /* renamed from: app.xmpp.ReconnectionManager.2 */
    class C05622 extends AbstractConnectionListener {
        final /* synthetic */ ReconnectionManager f4978a;

        C05622(ReconnectionManager reconnectionManager) {
            this.f4978a = reconnectionManager;
        }

        public void connectionClosed() {
            this.f4978a.f4990a = true;
        }

        public void authenticated(XMPPConnection xMPPConnection, boolean z) {
            this.f4978a.f4990a = false;
            this.f4978a.f4993g.f4980a = 0;
        }

        public void connectionClosedOnError(Exception exception) {
            this.f4978a.f4990a = false;
            if (this.f4978a.m7492c()) {
                if (exception instanceof StreamErrorException) {
                    if (Condition.conflict == ((StreamErrorException) exception).getStreamError().getCondition()) {
                        return;
                    }
                }
                this.f4978a.m7489e();
            }
        }
    }

    /* renamed from: app.xmpp.ReconnectionManager.3 */
    /* synthetic */ class C05633 {
        static final /* synthetic */ int[] f4979a;

        static {
            f4979a = new int[ReconnectionPolicy.values().length];
            try {
                f4979a[ReconnectionPolicy.FIXED_DELAY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4979a[ReconnectionPolicy.RANDOM_INCREASING_DELAY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    class CustomThread extends Thread {
        public int f4980a;
        final /* synthetic */ ReconnectionManager f4981b;

        private CustomThread(ReconnectionManager reconnectionManager) {
            this.f4981b = reconnectionManager;
            this.f4980a = 0;
        }

        private int m7478a() {
            this.f4980a++;
            switch (C05633.f4979a[this.f4981b.f4995k.ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    return this.f4981b.f4994j;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    if (this.f4980a > 13) {
                        return (this.f4981b.f4992f * 6) * 5;
                    }
                    if (this.f4980a > 7) {
                        return this.f4981b.f4992f * 6;
                    }
                    return this.f4981b.f4992f;
                default:
                    throw new AssertionError("Unknown reconnection policy " + this.f4981b.f4995k);
            }
        }

        public void run() {
            CustomXMPPConnection customXMPPConnection = (CustomXMPPConnection) this.f4981b.f4991e.get();
            if (customXMPPConnection != null) {
                while (this.f4981b.m7483a((XMPPConnection) customXMPPConnection)) {
                    int a = m7478a();
                    while (this.f4981b.m7483a((XMPPConnection) customXMPPConnection) && a > 0) {
                        try {
                            Thread.sleep(1000);
                            int i = a - 1;
                            for (ConnectionListener reconnectingIn : customXMPPConnection.m7310a()) {
                                reconnectingIn.reconnectingIn(i);
                            }
                            a = i;
                        } catch (Throwable e) {
                            ReconnectionManager.f4985b.log(Level.FINE, "waiting for reconnection interrupted", e);
                        }
                    }
                    for (ConnectionListener reconnectingIn2 : customXMPPConnection.m7310a()) {
                        reconnectingIn2.reconnectingIn(0);
                    }
                    try {
                        if (this.f4981b.m7483a((XMPPConnection) customXMPPConnection)) {
                            customXMPPConnection.connect();
                        }
                    } catch (Exception e2) {
                        Exception exception = e2;
                        for (ConnectionListener reconnectingIn22 : customXMPPConnection.m7310a()) {
                            reconnectingIn22.reconnectionFailed(exception);
                        }
                    }
                }
            }
        }
    }

    public enum ReconnectionPolicy {
        RANDOM_INCREASING_DELAY,
        FIXED_DELAY
    }

    static {
        f4985b = Logger.getLogger(ReconnectionManager.class.getName());
        f4986c = new WeakHashMap();
        XMPPConnectionRegistry.addConnectionCreationListener(new C05611());
        f4987d = false;
        f4988h = 15;
        f4989i = ReconnectionPolicy.RANDOM_INCREASING_DELAY;
    }

    public static synchronized ReconnectionManager m7480a(CustomXMPPConnection customXMPPConnection) {
        ReconnectionManager reconnectionManager;
        synchronized (ReconnectionManager.class) {
            reconnectionManager = (ReconnectionManager) f4986c.get(customXMPPConnection);
            if (reconnectionManager == null) {
                reconnectionManager = new ReconnectionManager(customXMPPConnection);
                f4986c.put(customXMPPConnection, reconnectionManager);
            }
        }
        return reconnectionManager;
    }

    public static boolean m7481a() {
        return f4987d;
    }

    private ReconnectionManager(CustomXMPPConnection customXMPPConnection) {
        this.f4992f = new Random().nextInt(13) + 2;
        this.f4994j = f4988h;
        this.f4995k = f4989i;
        this.f4996l = false;
        this.f4990a = false;
        this.f4998n = new C05622(this);
        this.f4991e = new WeakReference(customXMPPConnection);
        this.f4993g = new CustomThread();
        if (m7481a()) {
            m7491b();
        }
    }

    public synchronized void m7491b() {
        if (!this.f4996l) {
            XMPPConnection xMPPConnection = (XMPPConnection) this.f4991e.get();
            if (xMPPConnection == null) {
                throw new IllegalStateException("Connection instance no longer available");
            }
            xMPPConnection.addConnectionListener(this.f4998n);
            this.f4996l = true;
        }
    }

    public boolean m7492c() {
        return this.f4996l;
    }

    private boolean m7483a(XMPPConnection xMPPConnection) {
        return (this.f4990a || xMPPConnection.isConnected() || !m7492c()) ? false : true;
    }

    private synchronized void m7489e() {
        XMPPConnection xMPPConnection = (XMPPConnection) this.f4991e.get();
        if (xMPPConnection == null) {
            f4985b.fine("Connection is null, will not reconnect");
        } else if (this.f4997m == null || !this.f4997m.isAlive()) {
            this.f4997m = Async.go(this.f4993g, "Smack Reconnection Manager (" + xMPPConnection.getConnectionCounter() + ')');
        }
    }
}
