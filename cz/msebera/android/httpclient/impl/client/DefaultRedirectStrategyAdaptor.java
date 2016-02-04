package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.RedirectHandler;
import cz.msebera.android.httpclient.client.RedirectStrategy;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.methods.HttpHead;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.protocol.HttpContext;
import java.net.URI;

@Deprecated
class DefaultRedirectStrategyAdaptor implements RedirectStrategy {
    private final RedirectHandler f7585a;

    public boolean m12112a(HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext) {
        return this.f7585a.m11470a(httpResponse, httpContext);
    }

    public HttpUriRequest m12113b(HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext) {
        URI b = this.f7585a.m11471b(httpResponse, httpContext);
        if (httpRequest.m10637h().m11406a().equalsIgnoreCase("HEAD")) {
            return new HttpHead(b);
        }
        return new HttpGet(b);
    }

    public RedirectHandler m12111a() {
        return this.f7585a;
    }
}
