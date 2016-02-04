package cz.msebera.android.httpclient.protocol;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpRequestInterceptor;
import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.ProtocolException;
import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.util.Args;

public class RequestContent implements HttpRequestInterceptor {
    private final boolean f7917a;

    public RequestContent() {
        this(false);
    }

    public RequestContent(boolean z) {
        this.f7917a = z;
    }

    public void m12713a(HttpRequest httpRequest, HttpContext httpContext) {
        Args.m12722a((Object) httpRequest, "HTTP request");
        if (httpRequest instanceof HttpEntityEnclosingRequest) {
            if (this.f7917a) {
                httpRequest.m10618d("Transfer-Encoding");
                httpRequest.m10618d("Content-Length");
            } else if (httpRequest.m10612a("Transfer-Encoding")) {
                throw new ProtocolException("Transfer-encoding header already present");
            } else if (httpRequest.m10612a("Content-Length")) {
                throw new ProtocolException("Content-Length header already present");
            }
            ProtocolVersion b = httpRequest.m10637h().m11407b();
            HttpEntity c = ((HttpEntityEnclosingRequest) httpRequest).m10657c();
            if (c == null) {
                httpRequest.m10610a("Content-Length", "0");
                return;
            }
            if (!c.m10545e() && c.m10542b() >= 0) {
                httpRequest.m10610a("Content-Length", Long.toString(c.m10542b()));
            } else if (b.m11401c(HttpVersion.f7209b)) {
                throw new ProtocolException("Chunked transfer encoding not allowed for " + b);
            } else {
                httpRequest.m10610a("Transfer-Encoding", "chunked");
            }
            if (!(c.m10548h() == null || httpRequest.m10612a("Content-Type"))) {
                httpRequest.m10608a(c.m10548h());
            }
            if (c.m10547g() != null && !httpRequest.m10612a("Content-Encoding")) {
                httpRequest.m10608a(c.m10547g());
            }
        }
    }
}
