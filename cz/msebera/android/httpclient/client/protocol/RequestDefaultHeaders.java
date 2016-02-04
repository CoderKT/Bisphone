package cz.msebera.android.httpclient.client.protocol;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpRequestInterceptor;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import java.util.Collection;

public class RequestDefaultHeaders implements HttpRequestInterceptor {
    private final Collection<? extends Header> f7302a;

    public RequestDefaultHeaders(Collection<? extends Header> collection) {
        this.f7302a = collection;
    }

    public RequestDefaultHeaders() {
        this(null);
    }

    public void m11559a(HttpRequest httpRequest, HttpContext httpContext) {
        Args.m12722a((Object) httpRequest, "HTTP request");
        if (!httpRequest.m10637h().m11406a().equalsIgnoreCase("CONNECT")) {
            Collection collection = (Collection) httpRequest.m10622g().m12084a("http.default-headers");
            if (collection == null) {
                collection = this.f7302a;
            }
            if (r0 != null) {
                for (Header a : r0) {
                    httpRequest.m10608a(a);
                }
            }
        }
    }
}
