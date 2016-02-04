package cz.msebera.android.httpclient.conn.params;

import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.util.Args;
import java.util.concurrent.ConcurrentHashMap;

@Deprecated
public final class ConnPerRouteBean implements ConnPerRoute {
    private final ConcurrentHashMap<HttpRoute, Integer> f7344a;
    private volatile int f7345b;

    public ConnPerRouteBean(int i) {
        this.f7344a = new ConcurrentHashMap();
        m11676a(i);
    }

    public ConnPerRouteBean() {
        this(2);
    }

    public void m11676a(int i) {
        Args.m12719a(i, "Defautl max per route");
        this.f7345b = i;
    }

    public int m11675a(HttpRoute httpRoute) {
        Args.m12722a((Object) httpRoute, "HTTP route");
        Integer num = (Integer) this.f7344a.get(httpRoute);
        if (num != null) {
            return num.intValue();
        }
        return this.f7345b;
    }

    public String toString() {
        return this.f7344a.toString();
    }
}
