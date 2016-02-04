package cz.msebera.android.httpclient.client.protocol;

import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpRequestInterceptor;
import cz.msebera.android.httpclient.conn.routing.RouteInfo;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;

public class RequestClientConnControl implements HttpRequestInterceptor {
    public HttpClientAndroidLog f7301a;

    public RequestClientConnControl() {
        this.f7301a = new HttpClientAndroidLog(getClass());
    }

    public void m11558a(HttpRequest httpRequest, HttpContext httpContext) {
        Args.m12722a((Object) httpRequest, "HTTP request");
        if (httpRequest.m10637h().m11406a().equalsIgnoreCase("CONNECT")) {
            httpRequest.m10614b("Proxy-Connection", "Keep-Alive");
            return;
        }
        RouteInfo a = HttpClientContext.m11538a(httpContext).m11540a();
        if (a == null) {
            this.f7301a.m11834a("Connection route not set in the context");
            return;
        }
        if ((a.m11688c() == 1 || a.m11690e()) && !httpRequest.m10612a("Connection")) {
            httpRequest.m10610a("Connection", "Keep-Alive");
        }
        if (a.m11688c() == 2 && !a.m11690e() && !httpRequest.m10612a("Proxy-Connection")) {
            httpRequest.m10610a("Proxy-Connection", "Keep-Alive");
        }
    }
}
