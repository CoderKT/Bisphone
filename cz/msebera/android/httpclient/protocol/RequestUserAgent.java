package cz.msebera.android.httpclient.protocol;

import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpRequestInterceptor;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;

public class RequestUserAgent implements HttpRequestInterceptor {
    private final String f7919a;

    public RequestUserAgent(String str) {
        this.f7919a = str;
    }

    public RequestUserAgent() {
        this(null);
    }

    public void m12716a(HttpRequest httpRequest, HttpContext httpContext) {
        Args.m12722a((Object) httpRequest, "HTTP request");
        if (!httpRequest.m10612a("User-Agent")) {
            String str = null;
            HttpParams g = httpRequest.m10622g();
            if (g != null) {
                str = (String) g.m12084a("http.useragent");
            }
            if (str == null) {
                str = this.f7919a;
            }
            if (str != null) {
                httpRequest.m10610a("User-Agent", str);
            }
        }
    }
}
