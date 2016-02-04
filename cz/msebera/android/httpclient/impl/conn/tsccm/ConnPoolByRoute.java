package cz.msebera.android.httpclient.impl.conn.tsccm;

import cz.msebera.android.httpclient.conn.ClientConnectionOperator;
import cz.msebera.android.httpclient.conn.ConnectionPoolTimeoutException;
import cz.msebera.android.httpclient.conn.OperatedClientConnection;
import cz.msebera.android.httpclient.conn.params.ConnManagerParams;
import cz.msebera.android.httpclient.conn.params.ConnPerRoute;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@Deprecated
public class ConnPoolByRoute extends AbstractConnPool {
    public HttpClientAndroidLog f7712f;
    protected final ClientConnectionOperator f7713g;
    protected final ConnPerRoute f7714h;
    protected final Set<BasicPoolEntry> f7715i;
    protected final Queue<BasicPoolEntry> f7716j;
    protected final Queue<WaitingThread> f7717k;
    protected final Map<HttpRoute, RouteSpecificPool> f7718l;
    protected volatile boolean f7719m;
    protected volatile int f7720n;
    protected volatile int f7721o;
    private final Lock f7722p;
    private final long f7723q;
    private final TimeUnit f7724r;

    /* renamed from: cz.msebera.android.httpclient.impl.conn.tsccm.ConnPoolByRoute.1 */
    class C09391 implements PoolEntryRequest {
        final /* synthetic */ WaitingThreadAborter f7708a;
        final /* synthetic */ HttpRoute f7709b;
        final /* synthetic */ Object f7710c;
        final /* synthetic */ ConnPoolByRoute f7711d;

        C09391(ConnPoolByRoute connPoolByRoute, WaitingThreadAborter waitingThreadAborter, HttpRoute httpRoute, Object obj) {
            this.f7711d = connPoolByRoute;
            this.f7708a = waitingThreadAborter;
            this.f7709b = httpRoute;
            this.f7710c = obj;
        }

        public void m12342a() {
            this.f7711d.f7722p.lock();
            try {
                this.f7708a.m12385a();
            } finally {
                this.f7711d.f7722p.unlock();
            }
        }

        public BasicPoolEntry m12341a(long j, TimeUnit timeUnit) {
            return this.f7711d.m12345a(this.f7709b, this.f7710c, j, timeUnit, this.f7708a);
        }
    }

    public ConnPoolByRoute(ClientConnectionOperator clientConnectionOperator, ConnPerRoute connPerRoute, int i) {
        this(clientConnectionOperator, connPerRoute, i, -1, TimeUnit.MILLISECONDS);
    }

    public ConnPoolByRoute(ClientConnectionOperator clientConnectionOperator, ConnPerRoute connPerRoute, int i, long j, TimeUnit timeUnit) {
        this.f7712f = new HttpClientAndroidLog(getClass());
        Args.m12722a((Object) clientConnectionOperator, "Connection operator");
        Args.m12722a((Object) connPerRoute, "Connections per route");
        this.f7722p = this.f7700b;
        this.f7715i = this.f7701c;
        this.f7713g = clientConnectionOperator;
        this.f7714h = connPerRoute;
        this.f7720n = i;
        this.f7716j = m12356b();
        this.f7717k = m12357c();
        this.f7718l = m12358d();
        this.f7723q = j;
        this.f7724r = timeUnit;
    }

    @Deprecated
    public ConnPoolByRoute(ClientConnectionOperator clientConnectionOperator, HttpParams httpParams) {
        this(clientConnectionOperator, ConnManagerParams.m11670a(httpParams), ConnManagerParams.m11674b(httpParams));
    }

    protected Queue<BasicPoolEntry> m12356b() {
        return new LinkedList();
    }

    protected Queue<WaitingThread> m12357c() {
        return new LinkedList();
    }

    protected Map<HttpRoute, RouteSpecificPool> m12358d() {
        return new HashMap();
    }

    protected RouteSpecificPool m12349a(HttpRoute httpRoute) {
        return new RouteSpecificPool(httpRoute, this.f7714h);
    }

    protected WaitingThread m12351a(Condition condition, RouteSpecificPool routeSpecificPool) {
        return new WaitingThread(condition, routeSpecificPool);
    }

    private void m12344b(BasicPoolEntry basicPoolEntry) {
        OperatedClientConnection c = basicPoolEntry.m12334c();
        if (c != null) {
            try {
                c.close();
            } catch (Throwable e) {
                this.f7712f.m11835a("I/O error closing connection", e);
            }
        }
    }

    protected RouteSpecificPool m12350a(HttpRoute httpRoute, boolean z) {
        this.f7722p.lock();
        try {
            RouteSpecificPool routeSpecificPool = (RouteSpecificPool) this.f7718l.get(httpRoute);
            if (routeSpecificPool == null && z) {
                routeSpecificPool = m12349a(httpRoute);
                this.f7718l.put(httpRoute, routeSpecificPool);
            }
            this.f7722p.unlock();
            return routeSpecificPool;
        } catch (Throwable th) {
            this.f7722p.unlock();
        }
    }

    public PoolEntryRequest m12348a(HttpRoute httpRoute, Object obj) {
        return new C09391(this, new WaitingThreadAborter(), httpRoute, obj);
    }

    protected BasicPoolEntry m12345a(HttpRoute httpRoute, Object obj, long j, TimeUnit timeUnit, WaitingThreadAborter waitingThreadAborter) {
        Date date = null;
        if (j > 0) {
            date = new Date(System.currentTimeMillis() + timeUnit.toMillis(j));
        }
        BasicPoolEntry basicPoolEntry = null;
        this.f7722p.lock();
        RouteSpecificPool a = m12350a(httpRoute, true);
        WaitingThread waitingThread = null;
        while (basicPoolEntry == null) {
            Asserts.m12729a(!this.f7719m, "Connection pool shut down");
            if (this.f7712f.m11836a()) {
                this.f7712f.m11834a("[" + httpRoute + "] total kept alive: " + this.f7716j.size() + ", total issued: " + this.f7715i.size() + ", total allocated: " + this.f7721o + " out of " + this.f7720n);
            }
            basicPoolEntry = m12347a(a, obj);
            if (basicPoolEntry != null) {
                break;
            }
            try {
                Object obj2 = a.m12369d() > 0 ? 1 : null;
                if (this.f7712f.m11836a()) {
                    this.f7712f.m11834a("Available capacity: " + a.m12369d() + " out of " + a.m12364b() + " [" + httpRoute + "][" + obj + "]");
                }
                if (obj2 != null && this.f7721o < this.f7720n) {
                    basicPoolEntry = m12346a(a, this.f7713g);
                } else if (obj2 == null || this.f7716j.isEmpty()) {
                    if (this.f7712f.m11836a()) {
                        this.f7712f.m11834a("Need to wait for connection [" + httpRoute + "][" + obj + "]");
                    }
                    if (waitingThread == null) {
                        waitingThread = m12351a(this.f7722p.newCondition(), a);
                        waitingThreadAborter.m12386a(waitingThread);
                    }
                    a.m12363a(waitingThread);
                    this.f7717k.add(waitingThread);
                    boolean a2 = waitingThread.m12383a(date);
                    a.m12366b(waitingThread);
                    this.f7717k.remove(waitingThread);
                    if (!(a2 || date == null || date.getTime() > System.currentTimeMillis())) {
                        throw new ConnectionPoolTimeoutException("Timeout waiting for connection from pool");
                    }
                } else {
                    m12359e();
                    a = m12350a(httpRoute, true);
                    basicPoolEntry = m12346a(a, this.f7713g);
                }
            } catch (Throwable th) {
                this.f7722p.unlock();
            }
        }
        this.f7722p.unlock();
        return basicPoolEntry;
    }

    public void m12354a(BasicPoolEntry basicPoolEntry, boolean z, long j, TimeUnit timeUnit) {
        HttpRoute d = basicPoolEntry.m12335d();
        if (this.f7712f.m11836a()) {
            this.f7712f.m11834a("Releasing connection [" + d + "][" + basicPoolEntry.m12194a() + "]");
        }
        this.f7722p.lock();
        try {
            if (this.f7719m) {
                m12344b(basicPoolEntry);
                return;
            }
            this.f7715i.remove(basicPoolEntry);
            RouteSpecificPool a = m12350a(d, true);
            if (!z || a.m12369d() < 0) {
                m12344b(basicPoolEntry);
                a.m12370e();
                this.f7721o--;
            } else {
                if (this.f7712f.m11836a()) {
                    String str;
                    if (j > 0) {
                        str = "for " + j + " " + timeUnit;
                    } else {
                        str = "indefinitely";
                    }
                    this.f7712f.m11834a("Pooling connection [" + d + "][" + basicPoolEntry.m12194a() + "]; keep alive " + str);
                }
                a.m12362a(basicPoolEntry);
                basicPoolEntry.m12331a(j, timeUnit);
                this.f7716j.add(basicPoolEntry);
            }
            m12355a(a);
            this.f7722p.unlock();
        } finally {
            this.f7722p.unlock();
        }
    }

    protected BasicPoolEntry m12347a(RouteSpecificPool routeSpecificPool, Object obj) {
        BasicPoolEntry basicPoolEntry = null;
        this.f7722p.lock();
        Object obj2 = null;
        while (obj2 == null) {
            try {
                basicPoolEntry = routeSpecificPool.m12361a(obj);
                if (basicPoolEntry != null) {
                    if (this.f7712f.m11836a()) {
                        this.f7712f.m11834a("Getting free connection [" + routeSpecificPool.m12360a() + "][" + obj + "]");
                    }
                    this.f7716j.remove(basicPoolEntry);
                    if (basicPoolEntry.m12332a(System.currentTimeMillis())) {
                        if (this.f7712f.m11836a()) {
                            this.f7712f.m11834a("Closing expired free connection [" + routeSpecificPool.m12360a() + "][" + obj + "]");
                        }
                        m12344b(basicPoolEntry);
                        routeSpecificPool.m12370e();
                        this.f7721o--;
                    } else {
                        this.f7715i.add(basicPoolEntry);
                        obj2 = 1;
                    }
                } else if (this.f7712f.m11836a()) {
                    this.f7712f.m11834a("No free connections [" + routeSpecificPool.m12360a() + "][" + obj + "]");
                    obj2 = 1;
                } else {
                    obj2 = 1;
                }
            } catch (Throwable th) {
                this.f7722p.unlock();
            }
        }
        this.f7722p.unlock();
        return basicPoolEntry;
    }

    protected BasicPoolEntry m12346a(RouteSpecificPool routeSpecificPool, ClientConnectionOperator clientConnectionOperator) {
        if (this.f7712f.m11836a()) {
            this.f7712f.m11834a("Creating new connection [" + routeSpecificPool.m12360a() + "]");
        }
        BasicPoolEntry basicPoolEntry = new BasicPoolEntry(clientConnectionOperator, routeSpecificPool.m12360a(), this.f7723q, this.f7724r);
        this.f7722p.lock();
        try {
            routeSpecificPool.m12365b(basicPoolEntry);
            this.f7721o++;
            this.f7715i.add(basicPoolEntry);
            return basicPoolEntry;
        } finally {
            basicPoolEntry = this.f7722p;
            basicPoolEntry.unlock();
        }
    }

    protected void m12353a(BasicPoolEntry basicPoolEntry) {
        HttpRoute d = basicPoolEntry.m12335d();
        if (this.f7712f.m11836a()) {
            this.f7712f.m11834a("Deleting connection [" + d + "][" + basicPoolEntry.m12194a() + "]");
        }
        this.f7722p.lock();
        try {
            m12344b(basicPoolEntry);
            RouteSpecificPool a = m12350a(d, true);
            a.m12368c(basicPoolEntry);
            this.f7721o--;
            if (a.m12367c()) {
                this.f7718l.remove(d);
            }
            this.f7722p.unlock();
        } catch (Throwable th) {
            this.f7722p.unlock();
        }
    }

    protected void m12359e() {
        this.f7722p.lock();
        try {
            BasicPoolEntry basicPoolEntry = (BasicPoolEntry) this.f7716j.remove();
            if (basicPoolEntry != null) {
                m12353a(basicPoolEntry);
            } else if (this.f7712f.m11836a()) {
                this.f7712f.m11834a("No free connection to delete");
            }
            this.f7722p.unlock();
        } catch (Throwable th) {
            this.f7722p.unlock();
        }
    }

    protected void m12355a(RouteSpecificPool routeSpecificPool) {
        WaitingThread waitingThread = null;
        this.f7722p.lock();
        if (routeSpecificPool != null) {
            try {
                if (routeSpecificPool.m12371f()) {
                    if (this.f7712f.m11836a()) {
                        this.f7712f.m11834a("Notifying thread waiting on pool [" + routeSpecificPool.m12360a() + "]");
                    }
                    waitingThread = routeSpecificPool.m12372g();
                    if (waitingThread != null) {
                        waitingThread.m12382a();
                    }
                    this.f7722p.unlock();
                }
            } catch (Throwable th) {
                this.f7722p.unlock();
            }
        }
        if (!this.f7717k.isEmpty()) {
            if (this.f7712f.m11836a()) {
                this.f7712f.m11834a("Notifying thread waiting on any pool");
            }
            waitingThread = (WaitingThread) this.f7717k.remove();
        } else if (this.f7712f.m11836a()) {
            this.f7712f.m11834a("Notifying no-one, there are no waiting threads");
        }
        if (waitingThread != null) {
            waitingThread.m12382a();
        }
        this.f7722p.unlock();
    }

    public void m12352a() {
        this.f7722p.lock();
        try {
            if (!this.f7719m) {
                BasicPoolEntry basicPoolEntry;
                this.f7719m = true;
                Iterator it = this.f7715i.iterator();
                while (it.hasNext()) {
                    basicPoolEntry = (BasicPoolEntry) it.next();
                    it.remove();
                    m12344b(basicPoolEntry);
                }
                it = this.f7716j.iterator();
                while (it.hasNext()) {
                    basicPoolEntry = (BasicPoolEntry) it.next();
                    it.remove();
                    if (this.f7712f.m11836a()) {
                        this.f7712f.m11834a("Closing connection [" + basicPoolEntry.m12335d() + "][" + basicPoolEntry.m12194a() + "]");
                    }
                    m12344b(basicPoolEntry);
                }
                it = this.f7717k.iterator();
                while (it.hasNext()) {
                    WaitingThread waitingThread = (WaitingThread) it.next();
                    it.remove();
                    waitingThread.m12382a();
                }
                this.f7718l.clear();
                this.f7722p.unlock();
            }
        } finally {
            this.f7722p.unlock();
        }
    }
}
