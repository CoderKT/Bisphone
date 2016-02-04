package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.conn.OperatedClientConnection;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.conn.routing.RouteTracker;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.pool.PoolEntry;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Deprecated
class HttpPoolEntry extends PoolEntry<HttpRoute, OperatedClientConnection> {
    public HttpClientAndroidLog f7680a;
    private final RouteTracker f7681b;

    public HttpPoolEntry(HttpClientAndroidLog httpClientAndroidLog, String str, HttpRoute httpRoute, OperatedClientConnection operatedClientConnection, long j, TimeUnit timeUnit) {
        super(str, httpRoute, operatedClientConnection, j, timeUnit);
        this.f7680a = httpClientAndroidLog;
        this.f7681b = new RouteTracker(httpRoute);
    }

    public boolean m12258a(long j) {
        boolean a = super.m12253a(j);
        if (a && this.f7680a.m11836a()) {
            this.f7680a.m11834a("Connection " + this + " expired @ " + new Date(m12256h()));
        }
        return a;
    }

    RouteTracker m12257a() {
        return this.f7681b;
    }

    HttpRoute m12259b() {
        return (HttpRoute) m12254f();
    }

    HttpRoute m12260c() {
        return this.f7681b.m11717j();
    }

    public boolean m12261d() {
        return !((OperatedClientConnection) m12255g()).m11375c();
    }

    public void m12262e() {
        try {
            ((OperatedClientConnection) m12255g()).close();
        } catch (Throwable e) {
            this.f7680a.m11835a("I/O error closing connection", e);
        }
    }
}
