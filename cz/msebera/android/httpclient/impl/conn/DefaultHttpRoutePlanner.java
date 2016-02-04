package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.conn.params.ConnRouteParams;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.conn.routing.HttpRoutePlanner;
import cz.msebera.android.httpclient.conn.scheme.SchemeRegistry;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import java.net.InetAddress;

@Deprecated
public class DefaultHttpRoutePlanner implements HttpRoutePlanner {
    protected final SchemeRegistry f7670a;

    public DefaultHttpRoutePlanner(SchemeRegistry schemeRegistry) {
        Args.m12722a((Object) schemeRegistry, "Scheme registry");
        this.f7670a = schemeRegistry;
    }

    public HttpRoute m12249a(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) {
        Args.m12722a((Object) httpRequest, "HTTP request");
        HttpRoute b = ConnRouteParams.m11678b(httpRequest.m10622g());
        if (b != null) {
            return b;
        }
        Asserts.m12728a((Object) httpHost, "Target host");
        InetAddress c = ConnRouteParams.m11679c(httpRequest.m10622g());
        HttpHost a = ConnRouteParams.m11677a(httpRequest.m10622g());
        try {
            boolean d = this.f7670a.m11740a(httpHost.m11386c()).m11729d();
            if (a == null) {
                return new HttpRoute(httpHost, c, d);
            }
            return new HttpRoute(httpHost, c, a, d);
        } catch (IllegalStateException e) {
            throw new HttpException(e.getMessage());
        }
    }
}
