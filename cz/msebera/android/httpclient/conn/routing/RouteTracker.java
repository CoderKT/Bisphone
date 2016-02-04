package cz.msebera.android.httpclient.conn.routing;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.conn.routing.RouteInfo.LayerType;
import cz.msebera.android.httpclient.conn.routing.RouteInfo.TunnelType;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import cz.msebera.android.httpclient.util.LangUtils;
import java.net.InetAddress;

public final class RouteTracker implements RouteInfo, Cloneable {
    private final HttpHost f7360a;
    private final InetAddress f7361b;
    private boolean f7362c;
    private HttpHost[] f7363d;
    private TunnelType f7364e;
    private LayerType f7365f;
    private boolean f7366g;

    public RouteTracker(HttpHost httpHost, InetAddress inetAddress) {
        Args.m12722a((Object) httpHost, "Target host");
        this.f7360a = httpHost;
        this.f7361b = inetAddress;
        this.f7364e = TunnelType.PLAIN;
        this.f7365f = LayerType.PLAIN;
    }

    public void m11715h() {
        this.f7362c = false;
        this.f7363d = null;
        this.f7364e = TunnelType.PLAIN;
        this.f7365f = LayerType.PLAIN;
        this.f7366g = false;
    }

    public RouteTracker(HttpRoute httpRoute) {
        this(httpRoute.m11693a(), httpRoute.m11695b());
    }

    public final void m11705a(boolean z) {
        Asserts.m12729a(!this.f7362c, "Already connected");
        this.f7362c = true;
        this.f7366g = z;
    }

    public final void m11704a(HttpHost httpHost, boolean z) {
        boolean z2;
        Args.m12722a((Object) httpHost, "Proxy host");
        if (this.f7362c) {
            z2 = false;
        } else {
            z2 = true;
        }
        Asserts.m12729a(z2, "Already connected");
        this.f7362c = true;
        this.f7363d = new HttpHost[]{httpHost};
        this.f7366g = z;
    }

    public final void m11708b(boolean z) {
        Asserts.m12729a(this.f7362c, "No tunnel unless connected");
        Asserts.m12728a(this.f7363d, "No tunnel without proxy");
        this.f7364e = TunnelType.TUNNELLED;
        this.f7366g = z;
    }

    public final void m11707b(HttpHost httpHost, boolean z) {
        Args.m12722a((Object) httpHost, "Proxy host");
        Asserts.m12729a(this.f7362c, "No tunnel unless connected");
        Asserts.m12728a(this.f7363d, "No tunnel without proxy");
        Object obj = new HttpHost[(this.f7363d.length + 1)];
        System.arraycopy(this.f7363d, 0, obj, 0, this.f7363d.length);
        obj[obj.length - 1] = httpHost;
        this.f7363d = obj;
        this.f7366g = z;
    }

    public final void m11710c(boolean z) {
        Asserts.m12729a(this.f7362c, "No layered protocol unless connected");
        this.f7365f = LayerType.LAYERED;
        this.f7366g = z;
    }

    public final HttpHost m11702a() {
        return this.f7360a;
    }

    public final InetAddress m11706b() {
        return this.f7361b;
    }

    public final int m11709c() {
        if (!this.f7362c) {
            return 0;
        }
        if (this.f7363d == null) {
            return 1;
        }
        return this.f7363d.length + 1;
    }

    public final HttpHost m11703a(int i) {
        Args.m12726b(i, "Hop index");
        int c = m11709c();
        Args.m12724a(i < c, "Hop index exceeds tracked route length");
        if (i < c - 1) {
            return this.f7363d[i];
        }
        return this.f7360a;
    }

    public final HttpHost m11711d() {
        return this.f7363d == null ? null : this.f7363d[0];
    }

    public final boolean m11716i() {
        return this.f7362c;
    }

    public final boolean m11712e() {
        return this.f7364e == TunnelType.TUNNELLED;
    }

    public final boolean m11713f() {
        return this.f7365f == LayerType.LAYERED;
    }

    public final boolean m11714g() {
        return this.f7366g;
    }

    public final HttpRoute m11717j() {
        return !this.f7362c ? null : new HttpRoute(this.f7360a, this.f7361b, this.f7363d, this.f7366g, this.f7364e, this.f7365f);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RouteTracker)) {
            return false;
        }
        RouteTracker routeTracker = (RouteTracker) obj;
        if (this.f7362c == routeTracker.f7362c && this.f7366g == routeTracker.f7366g && this.f7364e == routeTracker.f7364e && this.f7365f == routeTracker.f7365f && LangUtils.m12769a(this.f7360a, routeTracker.f7360a) && LangUtils.m12769a(this.f7361b, routeTracker.f7361b) && LangUtils.m12770a(this.f7363d, routeTracker.f7363d)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int a = LangUtils.m12767a(LangUtils.m12767a(17, this.f7360a), this.f7361b);
        if (this.f7363d != null) {
            HttpHost[] httpHostArr = this.f7363d;
            int length = httpHostArr.length;
            int i = 0;
            while (i < length) {
                int a2 = LangUtils.m12767a(a, httpHostArr[i]);
                i++;
                a = a2;
            }
        }
        return LangUtils.m12767a(LangUtils.m12767a(LangUtils.m12768a(LangUtils.m12768a(a, this.f7362c), this.f7366g), this.f7364e), this.f7365f);
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder((m11709c() * 30) + 50);
        stringBuilder.append("RouteTracker[");
        if (this.f7361b != null) {
            stringBuilder.append(this.f7361b);
            stringBuilder.append("->");
        }
        stringBuilder.append('{');
        if (this.f7362c) {
            stringBuilder.append('c');
        }
        if (this.f7364e == TunnelType.TUNNELLED) {
            stringBuilder.append('t');
        }
        if (this.f7365f == LayerType.LAYERED) {
            stringBuilder.append('l');
        }
        if (this.f7366g) {
            stringBuilder.append('s');
        }
        stringBuilder.append("}->");
        if (this.f7363d != null) {
            for (Object append : this.f7363d) {
                stringBuilder.append(append);
                stringBuilder.append("->");
            }
        }
        stringBuilder.append(this.f7360a);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public Object clone() {
        return super.clone();
    }
}
