package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.conn.ClientConnectionOperator;
import cz.msebera.android.httpclient.conn.OperatedClientConnection;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.conn.routing.RouteTracker;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import java.io.InterruptedIOException;

@Deprecated
public abstract class AbstractPoolEntry {
    protected final ClientConnectionOperator f7634a;
    protected final OperatedClientConnection f7635b;
    protected volatile HttpRoute f7636c;
    protected volatile Object f7637d;
    protected volatile RouteTracker f7638e;

    protected AbstractPoolEntry(ClientConnectionOperator clientConnectionOperator, HttpRoute httpRoute) {
        Args.m12722a((Object) clientConnectionOperator, "Connection operator");
        this.f7634a = clientConnectionOperator;
        this.f7635b = clientConnectionOperator.m11637a();
        this.f7636c = httpRoute;
        this.f7638e = null;
    }

    public Object m12194a() {
        return this.f7637d;
    }

    public void m12198a(Object obj) {
        this.f7637d = obj;
    }

    public void m12196a(HttpRoute httpRoute, HttpContext httpContext, HttpParams httpParams) {
        HttpHost httpHost;
        Args.m12722a((Object) httpRoute, "Route");
        Args.m12722a((Object) httpParams, "HTTP parameters");
        if (this.f7638e != null) {
            Asserts.m12729a(!this.f7638e.m11716i(), "Connection already open");
        }
        this.f7638e = new RouteTracker(httpRoute);
        HttpHost d = httpRoute.m11697d();
        ClientConnectionOperator clientConnectionOperator = this.f7634a;
        OperatedClientConnection operatedClientConnection = this.f7635b;
        if (d != null) {
            httpHost = d;
        } else {
            httpHost = httpRoute.m11693a();
        }
        clientConnectionOperator.m11639a(operatedClientConnection, httpHost, httpRoute.m11695b(), httpContext, httpParams);
        RouteTracker routeTracker = this.f7638e;
        if (routeTracker == null) {
            throw new InterruptedIOException("Request aborted");
        } else if (d == null) {
            routeTracker.m11705a(this.f7635b.m11665h());
        } else {
            routeTracker.m11704a(d, this.f7635b.m11665h());
        }
    }

    public void m12199a(boolean z, HttpParams httpParams) {
        Args.m12722a((Object) httpParams, "HTTP parameters");
        Asserts.m12728a(this.f7638e, "Route tracker");
        Asserts.m12729a(this.f7638e.m11716i(), "Connection not open");
        Asserts.m12729a(!this.f7638e.m11712e(), "Connection is already tunnelled");
        this.f7635b.m11663a(null, this.f7638e.m11702a(), z, httpParams);
        this.f7638e.m11708b(z);
    }

    public void m12195a(HttpHost httpHost, boolean z, HttpParams httpParams) {
        Args.m12722a((Object) httpHost, "Next proxy");
        Args.m12722a((Object) httpParams, "Parameters");
        Asserts.m12728a(this.f7638e, "Route tracker");
        Asserts.m12729a(this.f7638e.m11716i(), "Connection not open");
        this.f7635b.m11663a(null, httpHost, z, httpParams);
        this.f7638e.m11707b(httpHost, z);
    }

    public void m12197a(HttpContext httpContext, HttpParams httpParams) {
        Args.m12722a((Object) httpParams, "HTTP parameters");
        Asserts.m12728a(this.f7638e, "Route tracker");
        Asserts.m12729a(this.f7638e.m11716i(), "Connection not open");
        Asserts.m12729a(this.f7638e.m11712e(), "Protocol layering without a tunnel not supported");
        Asserts.m12729a(!this.f7638e.m11713f(), "Multiple protocol layering not supported");
        this.f7634a.m11638a(this.f7635b, this.f7638e.m11702a(), httpContext, httpParams);
        this.f7638e.m11710c(this.f7635b.m11665h());
    }

    protected void m12200b() {
        this.f7638e = null;
        this.f7637d = null;
    }
}
