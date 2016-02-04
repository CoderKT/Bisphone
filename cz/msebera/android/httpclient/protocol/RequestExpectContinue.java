package cz.msebera.android.httpclient.protocol;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpRequestInterceptor;
import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.util.Args;

public class RequestExpectContinue implements HttpRequestInterceptor {
    private final boolean f7918a;

    @Deprecated
    public RequestExpectContinue() {
        this(false);
    }

    public RequestExpectContinue(boolean z) {
        this.f7918a = z;
    }

    public void m12714a(HttpRequest httpRequest, HttpContext httpContext) {
        Args.m12722a((Object) httpRequest, "HTTP request");
        if (!httpRequest.m10612a("Expect") && (httpRequest instanceof HttpEntityEnclosingRequest)) {
            ProtocolVersion b = httpRequest.m10637h().m11407b();
            HttpEntity c = ((HttpEntityEnclosingRequest) httpRequest).m10657c();
            if (c != null && c.m10542b() != 0 && !b.m11401c(HttpVersion.f7209b) && httpRequest.m10622g().m12085a("http.protocol.expect-continue", this.f7918a)) {
                httpRequest.m10610a("Expect", "100-continue");
            }
        }
    }
}
