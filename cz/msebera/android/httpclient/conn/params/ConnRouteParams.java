package cz.msebera.android.httpclient.conn.params;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;
import java.net.InetAddress;

@Deprecated
public class ConnRouteParams {
    public static final HttpHost f7346a;
    public static final HttpRoute f7347b;

    static {
        f7346a = new HttpHost("127.0.0.255", 0, "no-host");
        f7347b = new HttpRoute(f7346a);
    }

    public static HttpHost m11677a(HttpParams httpParams) {
        Args.m12722a((Object) httpParams, "Parameters");
        HttpHost httpHost = (HttpHost) httpParams.m12084a("http.route.default-proxy");
        if (httpHost == null || !f7346a.equals(httpHost)) {
            return httpHost;
        }
        return null;
    }

    public static HttpRoute m11678b(HttpParams httpParams) {
        Args.m12722a((Object) httpParams, "Parameters");
        HttpRoute httpRoute = (HttpRoute) httpParams.m12084a("http.route.forced-route");
        if (httpRoute == null || !f7347b.equals(httpRoute)) {
            return httpRoute;
        }
        return null;
    }

    public static InetAddress m11679c(HttpParams httpParams) {
        Args.m12722a((Object) httpParams, "Parameters");
        return (InetAddress) httpParams.m12084a("http.route.local-address");
    }
}
