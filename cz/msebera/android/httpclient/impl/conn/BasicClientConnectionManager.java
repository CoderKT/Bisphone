package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.HttpClientConnection;
import cz.msebera.android.httpclient.conn.ClientConnectionManager;
import cz.msebera.android.httpclient.conn.ClientConnectionOperator;
import cz.msebera.android.httpclient.conn.ClientConnectionRequest;
import cz.msebera.android.httpclient.conn.ManagedClientConnection;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.conn.scheme.SchemeRegistry;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Deprecated
public class BasicClientConnectionManager implements ClientConnectionManager {
    private static final AtomicLong f7643b;
    public HttpClientAndroidLog f7644a;
    private final SchemeRegistry f7645c;
    private final ClientConnectionOperator f7646d;
    private HttpPoolEntry f7647e;
    private ManagedClientConnectionImpl f7648f;
    private volatile boolean f7649g;

    /* renamed from: cz.msebera.android.httpclient.impl.conn.BasicClientConnectionManager.1 */
    class C09381 implements ClientConnectionRequest {
        final /* synthetic */ HttpRoute f7640a;
        final /* synthetic */ Object f7641b;
        final /* synthetic */ BasicClientConnectionManager f7642c;

        C09381(BasicClientConnectionManager basicClientConnectionManager, HttpRoute httpRoute, Object obj) {
            this.f7642c = basicClientConnectionManager;
            this.f7640a = httpRoute;
            this.f7641b = obj;
        }

        public void m12212a() {
        }

        public ManagedClientConnection m12211a(long j, TimeUnit timeUnit) {
            return this.f7642c.m12219b(this.f7640a, this.f7641b);
        }
    }

    static {
        f7643b = new AtomicLong();
    }

    public BasicClientConnectionManager(SchemeRegistry schemeRegistry) {
        this.f7644a = new HttpClientAndroidLog(getClass());
        Args.m12722a((Object) schemeRegistry, "Scheme registry");
        this.f7645c = schemeRegistry;
        this.f7646d = m12215a(schemeRegistry);
    }

    public BasicClientConnectionManager() {
        this(SchemeRegistryFactory.m12319a());
    }

    protected void finalize() {
        try {
            m12220b();
        } finally {
            super.finalize();
        }
    }

    public SchemeRegistry m12217a() {
        return this.f7645c;
    }

    protected ClientConnectionOperator m12215a(SchemeRegistry schemeRegistry) {
        return new DefaultClientConnectionOperator(schemeRegistry);
    }

    public final ClientConnectionRequest m12216a(HttpRoute httpRoute, Object obj) {
        return new C09381(this, httpRoute, obj);
    }

    private void m12214c() {
        Asserts.m12729a(!this.f7649g, "Connection manager has been shut down");
    }

    ManagedClientConnection m12219b(HttpRoute httpRoute, Object obj) {
        ManagedClientConnection managedClientConnection;
        Args.m12722a((Object) httpRoute, "Route");
        synchronized (this) {
            m12214c();
            if (this.f7644a.m11836a()) {
                this.f7644a.m11834a("Get connection for route " + httpRoute);
            }
            Asserts.m12729a(this.f7648f == null, "Invalid use of BasicClientConnManager: connection still allocated.\nMake sure to release the connection before allocating another one.");
            if (!(this.f7647e == null || this.f7647e.m12259b().equals(httpRoute))) {
                this.f7647e.m12262e();
                this.f7647e = null;
            }
            if (this.f7647e == null) {
                this.f7647e = new HttpPoolEntry(this.f7644a, Long.toString(f7643b.getAndIncrement()), httpRoute, this.f7646d.m11637a(), 0, TimeUnit.MILLISECONDS);
            }
            if (this.f7647e.m12258a(System.currentTimeMillis())) {
                this.f7647e.m12262e();
                this.f7647e.m12257a().m11715h();
            }
            this.f7648f = new ManagedClientConnectionImpl(this, this.f7646d, this.f7647e);
            managedClientConnection = this.f7648f;
        }
        return managedClientConnection;
    }

    private void m12213a(HttpClientConnection httpClientConnection) {
        try {
            httpClientConnection.m11377e();
        } catch (Throwable e) {
            if (this.f7644a.m11836a()) {
                this.f7644a.m11835a("I/O exception shutting down connection", e);
            }
        }
    }

    public void m12218a(ManagedClientConnection managedClientConnection, long j, TimeUnit timeUnit) {
        Args.m12724a(managedClientConnection instanceof ManagedClientConnectionImpl, "Connection class mismatch, connection not obtained from this manager");
        HttpClientConnection httpClientConnection = (ManagedClientConnectionImpl) managedClientConnection;
        synchronized (httpClientConnection) {
            if (this.f7644a.m11836a()) {
                this.f7644a.m11834a("Releasing connection " + managedClientConnection);
            }
            if (httpClientConnection.m12315n() == null) {
                return;
            }
            Asserts.m12729a(httpClientConnection.m12317p() == this, "Connection not obtained from this manager");
            synchronized (this) {
                if (this.f7649g) {
                    m12213a(httpClientConnection);
                    return;
                }
                try {
                    if (httpClientConnection.m12304c() && !httpClientConnection.m12318q()) {
                        m12213a(httpClientConnection);
                    }
                    if (httpClientConnection.m12318q()) {
                        this.f7647e.m12251a(j, timeUnit != null ? timeUnit : TimeUnit.MILLISECONDS);
                        if (this.f7644a.m11836a()) {
                            String str;
                            if (j > 0) {
                                str = "for " + j + " " + timeUnit;
                            } else {
                                str = "indefinitely";
                            }
                            this.f7644a.m11834a("Connection can be kept alive " + str);
                        }
                    }
                    httpClientConnection.m12316o();
                    this.f7648f = null;
                    if (this.f7647e.m12261d()) {
                        this.f7647e = null;
                    }
                    return;
                } catch (Throwable th) {
                    httpClientConnection.m12316o();
                    this.f7648f = null;
                    if (this.f7647e.m12261d()) {
                        this.f7647e = null;
                    }
                }
            }
        }
    }

    public void m12220b() {
        synchronized (this) {
            this.f7649g = true;
            try {
                if (this.f7647e != null) {
                    this.f7647e.m12262e();
                }
                this.f7647e = null;
                this.f7648f = null;
            } catch (Throwable th) {
                this.f7647e = null;
                this.f7648f = null;
            }
        }
    }
}
