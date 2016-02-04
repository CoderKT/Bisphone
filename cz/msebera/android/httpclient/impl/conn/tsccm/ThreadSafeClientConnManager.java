package cz.msebera.android.httpclient.impl.conn.tsccm;

import cz.msebera.android.httpclient.conn.ClientConnectionManager;
import cz.msebera.android.httpclient.conn.ClientConnectionOperator;
import cz.msebera.android.httpclient.conn.ClientConnectionRequest;
import cz.msebera.android.httpclient.conn.ManagedClientConnection;
import cz.msebera.android.httpclient.conn.params.ConnPerRouteBean;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.conn.scheme.SchemeRegistry;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.impl.conn.DefaultClientConnectionOperator;
import cz.msebera.android.httpclient.impl.conn.SchemeRegistryFactory;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import java.util.concurrent.TimeUnit;

@Deprecated
public class ThreadSafeClientConnManager implements ClientConnectionManager {
    public HttpClientAndroidLog f7735a;
    protected final SchemeRegistry f7736b;
    protected final AbstractConnPool f7737c;
    protected final ConnPoolByRoute f7738d;
    protected final ClientConnectionOperator f7739e;
    protected final ConnPerRouteBean f7740f;

    /* renamed from: cz.msebera.android.httpclient.impl.conn.tsccm.ThreadSafeClientConnManager.1 */
    class C09401 implements ClientConnectionRequest {
        final /* synthetic */ PoolEntryRequest f7732a;
        final /* synthetic */ HttpRoute f7733b;
        final /* synthetic */ ThreadSafeClientConnManager f7734c;

        C09401(ThreadSafeClientConnManager threadSafeClientConnManager, PoolEntryRequest poolEntryRequest, HttpRoute httpRoute) {
            this.f7734c = threadSafeClientConnManager;
            this.f7732a = poolEntryRequest;
            this.f7733b = httpRoute;
        }

        public void m12374a() {
            this.f7732a.m12340a();
        }

        public ManagedClientConnection m12373a(long j, TimeUnit timeUnit) {
            Args.m12722a(this.f7733b, "Route");
            if (this.f7734c.f7735a.m11836a()) {
                this.f7734c.f7735a.m11834a("Get connection: " + this.f7733b + ", timeout = " + j);
            }
            return new BasicPooledConnAdapter(this.f7734c, this.f7732a.m12339a(j, timeUnit));
        }
    }

    public ThreadSafeClientConnManager(SchemeRegistry schemeRegistry) {
        this(schemeRegistry, -1, TimeUnit.MILLISECONDS);
    }

    public ThreadSafeClientConnManager() {
        this(SchemeRegistryFactory.m12319a());
    }

    public ThreadSafeClientConnManager(SchemeRegistry schemeRegistry, long j, TimeUnit timeUnit) {
        this(schemeRegistry, j, timeUnit, new ConnPerRouteBean());
    }

    public ThreadSafeClientConnManager(SchemeRegistry schemeRegistry, long j, TimeUnit timeUnit, ConnPerRouteBean connPerRouteBean) {
        Args.m12722a((Object) schemeRegistry, "Scheme registry");
        this.f7735a = new HttpClientAndroidLog(getClass());
        this.f7736b = schemeRegistry;
        this.f7740f = connPerRouteBean;
        this.f7739e = m12375a(schemeRegistry);
        this.f7738d = m12379a(j, timeUnit);
        this.f7737c = this.f7738d;
    }

    @Deprecated
    public ThreadSafeClientConnManager(HttpParams httpParams, SchemeRegistry schemeRegistry) {
        Args.m12722a((Object) schemeRegistry, "Scheme registry");
        this.f7735a = new HttpClientAndroidLog(getClass());
        this.f7736b = schemeRegistry;
        this.f7740f = new ConnPerRouteBean();
        this.f7739e = m12375a(schemeRegistry);
        this.f7738d = (ConnPoolByRoute) m12378a(httpParams);
        this.f7737c = this.f7738d;
    }

    protected void finalize() {
        try {
            m12381b();
        } finally {
            super.finalize();
        }
    }

    @Deprecated
    protected AbstractConnPool m12378a(HttpParams httpParams) {
        return new ConnPoolByRoute(this.f7739e, httpParams);
    }

    protected ConnPoolByRoute m12379a(long j, TimeUnit timeUnit) {
        return new ConnPoolByRoute(this.f7739e, this.f7740f, 20, j, timeUnit);
    }

    protected ClientConnectionOperator m12375a(SchemeRegistry schemeRegistry) {
        return new DefaultClientConnectionOperator(schemeRegistry);
    }

    public SchemeRegistry m12377a() {
        return this.f7736b;
    }

    public ClientConnectionRequest m12376a(HttpRoute httpRoute, Object obj) {
        return new C09401(this, this.f7738d.m12348a(httpRoute, obj), httpRoute);
    }

    public void m12380a(ManagedClientConnection managedClientConnection, long j, TimeUnit timeUnit) {
        boolean r;
        Args.m12724a(managedClientConnection instanceof BasicPooledConnAdapter, "Connection class mismatch, connection not obtained from this manager");
        BasicPooledConnAdapter basicPooledConnAdapter = (BasicPooledConnAdapter) managedClientConnection;
        if (basicPooledConnAdapter.m12338s() != null) {
            Asserts.m12729a(basicPooledConnAdapter.m12337p() == this, "Connection not obtained from this manager");
        }
        synchronized (basicPooledConnAdapter) {
            BasicPoolEntry basicPoolEntry = (BasicPoolEntry) basicPooledConnAdapter.m12338s();
            if (basicPoolEntry == null) {
                return;
            }
            try {
                if (basicPooledConnAdapter.m12180c() && !basicPooledConnAdapter.m12193r()) {
                    basicPooledConnAdapter.m12207e();
                }
                r = basicPooledConnAdapter.m12193r();
                if (this.f7735a.m11836a()) {
                    if (r) {
                        this.f7735a.m11834a("Released connection is reusable.");
                    } else {
                        this.f7735a.m11834a("Released connection is not reusable.");
                    }
                }
                basicPooledConnAdapter.m12336n();
                this.f7738d.m12354a(basicPoolEntry, r, j, timeUnit);
            } catch (Throwable e) {
                if (this.f7735a.m11836a()) {
                    this.f7735a.m11835a("Exception shutting down released connection.", e);
                }
                r = basicPooledConnAdapter.m12193r();
                if (this.f7735a.m11836a()) {
                    if (r) {
                        this.f7735a.m11834a("Released connection is reusable.");
                    } else {
                        this.f7735a.m11834a("Released connection is not reusable.");
                    }
                }
                basicPooledConnAdapter.m12336n();
                this.f7738d.m12354a(basicPoolEntry, r, j, timeUnit);
            } catch (Throwable th) {
                r = basicPooledConnAdapter.m12193r();
                if (this.f7735a.m11836a()) {
                    if (r) {
                        this.f7735a.m11834a("Released connection is reusable.");
                    } else {
                        this.f7735a.m11834a("Released connection is not reusable.");
                    }
                }
                basicPooledConnAdapter.m12336n();
                this.f7738d.m12354a(basicPoolEntry, r, j, timeUnit);
            }
        }
    }

    public void m12381b() {
        this.f7735a.m11834a("Shutting down");
        this.f7738d.m12352a();
    }
}
