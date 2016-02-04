package cz.msebera.android.httpclient.conn.params;

import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;

@Deprecated
public final class ConnManagerParams {
    private static final ConnPerRoute f7343a;

    /* renamed from: cz.msebera.android.httpclient.conn.params.ConnManagerParams.1 */
    final class C09351 implements ConnPerRoute {
        C09351() {
        }

        public int m11669a(HttpRoute httpRoute) {
            return 2;
        }
    }

    @Deprecated
    public static void m11672a(HttpParams httpParams, long j) {
        Args.m12722a((Object) httpParams, "HTTP parameters");
        httpParams.m12087b("http.conn-manager.timeout", j);
    }

    static {
        f7343a = new C09351();
    }

    public static void m11673a(HttpParams httpParams, ConnPerRoute connPerRoute) {
        Args.m12722a((Object) httpParams, "HTTP parameters");
        httpParams.m12083a("http.conn-manager.max-per-route", (Object) connPerRoute);
    }

    public static ConnPerRoute m11670a(HttpParams httpParams) {
        Args.m12722a((Object) httpParams, "HTTP parameters");
        ConnPerRoute connPerRoute = (ConnPerRoute) httpParams.m12084a("http.conn-manager.max-per-route");
        if (connPerRoute == null) {
            return f7343a;
        }
        return connPerRoute;
    }

    public static void m11671a(HttpParams httpParams, int i) {
        Args.m12722a((Object) httpParams, "HTTP parameters");
        httpParams.m12086b("http.conn-manager.max-total", i);
    }

    public static int m11674b(HttpParams httpParams) {
        Args.m12722a((Object) httpParams, "HTTP parameters");
        return httpParams.m12081a("http.conn-manager.max-total", 20);
    }
}
