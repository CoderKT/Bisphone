package cz.msebera.android.httpclient.conn.routing;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.conn.routing.RouteInfo.LayerType;
import cz.msebera.android.httpclient.conn.routing.RouteInfo.TunnelType;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.LangUtils;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class HttpRoute implements RouteInfo, Cloneable {
    private final HttpHost f7348a;
    private final InetAddress f7349b;
    private final List<HttpHost> f7350c;
    private final TunnelType f7351d;
    private final LayerType f7352e;
    private final boolean f7353f;

    private HttpRoute(HttpHost httpHost, InetAddress inetAddress, List<HttpHost> list, boolean z, TunnelType tunnelType, LayerType layerType) {
        Args.m12722a((Object) httpHost, "Target host");
        this.f7348a = httpHost;
        this.f7349b = inetAddress;
        if (list == null || list.isEmpty()) {
            this.f7350c = null;
        } else {
            this.f7350c = new ArrayList(list);
        }
        if (tunnelType == TunnelType.TUNNELLED) {
            Args.m12724a(this.f7350c != null, "Proxy required if tunnelled");
        }
        this.f7353f = z;
        if (tunnelType == null) {
            tunnelType = TunnelType.PLAIN;
        }
        this.f7351d = tunnelType;
        if (layerType == null) {
            layerType = LayerType.PLAIN;
        }
        this.f7352e = layerType;
    }

    public HttpRoute(HttpHost httpHost, InetAddress inetAddress, HttpHost[] httpHostArr, boolean z, TunnelType tunnelType, LayerType layerType) {
        this(httpHost, inetAddress, httpHostArr != null ? Arrays.asList(httpHostArr) : null, z, tunnelType, layerType);
    }

    public HttpRoute(HttpHost httpHost, InetAddress inetAddress, boolean z) {
        this(httpHost, inetAddress, Collections.emptyList(), z, TunnelType.PLAIN, LayerType.PLAIN);
    }

    public HttpRoute(HttpHost httpHost) {
        this(httpHost, null, Collections.emptyList(), false, TunnelType.PLAIN, LayerType.PLAIN);
    }

    public HttpRoute(HttpHost httpHost, InetAddress inetAddress, HttpHost httpHost2, boolean z) {
        this(httpHost, inetAddress, Collections.singletonList(Args.m12722a((Object) httpHost2, "Proxy host")), z, z ? TunnelType.TUNNELLED : TunnelType.PLAIN, z ? LayerType.LAYERED : LayerType.PLAIN);
    }

    public final HttpHost m11693a() {
        return this.f7348a;
    }

    public final InetAddress m11695b() {
        return this.f7349b;
    }

    public final int m11696c() {
        return this.f7350c != null ? this.f7350c.size() + 1 : 1;
    }

    public final HttpHost m11694a(int i) {
        Args.m12726b(i, "Hop index");
        int c = m11696c();
        Args.m12724a(i < c, "Hop index exceeds tracked route length");
        if (i < c - 1) {
            return (HttpHost) this.f7350c.get(i);
        }
        return this.f7348a;
    }

    public final HttpHost m11697d() {
        return (this.f7350c == null || this.f7350c.isEmpty()) ? null : (HttpHost) this.f7350c.get(0);
    }

    public final boolean m11698e() {
        return this.f7351d == TunnelType.TUNNELLED;
    }

    public final boolean m11699f() {
        return this.f7352e == LayerType.LAYERED;
    }

    public final boolean m11700g() {
        return this.f7353f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HttpRoute)) {
            return false;
        }
        HttpRoute httpRoute = (HttpRoute) obj;
        if (this.f7353f == httpRoute.f7353f && this.f7351d == httpRoute.f7351d && this.f7352e == httpRoute.f7352e && LangUtils.m12769a(this.f7348a, httpRoute.f7348a) && LangUtils.m12769a(this.f7349b, httpRoute.f7349b) && LangUtils.m12769a(this.f7350c, httpRoute.f7350c)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i;
        int a = LangUtils.m12767a(LangUtils.m12767a(17, this.f7348a), this.f7349b);
        if (this.f7350c != null) {
            i = a;
            for (Object a2 : this.f7350c) {
                i = LangUtils.m12767a(i, a2);
            }
        } else {
            i = a;
        }
        return LangUtils.m12767a(LangUtils.m12767a(LangUtils.m12768a(i, this.f7353f), this.f7351d), this.f7352e);
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder((m11696c() * 30) + 50);
        if (this.f7349b != null) {
            stringBuilder.append(this.f7349b);
            stringBuilder.append("->");
        }
        stringBuilder.append('{');
        if (this.f7351d == TunnelType.TUNNELLED) {
            stringBuilder.append('t');
        }
        if (this.f7352e == LayerType.LAYERED) {
            stringBuilder.append('l');
        }
        if (this.f7353f) {
            stringBuilder.append('s');
        }
        stringBuilder.append("}->");
        if (this.f7350c != null) {
            for (HttpHost append : this.f7350c) {
                stringBuilder.append(append);
                stringBuilder.append("->");
            }
        }
        stringBuilder.append(this.f7348a);
        return stringBuilder.toString();
    }

    public Object clone() {
        return super.clone();
    }
}
