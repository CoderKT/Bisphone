package cz.msebera.android.httpclient.protocol;

import cz.msebera.android.httpclient.HttpConnection;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpInetConnection;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpRequestInterceptor;
import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.ProtocolException;
import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.util.Args;
import java.net.InetAddress;

public class RequestTargetHost implements HttpRequestInterceptor {
    public void m12715a(HttpRequest httpRequest, HttpContext httpContext) {
        Args.m12722a((Object) httpRequest, "HTTP request");
        HttpCoreContext b = HttpCoreContext.m11530b(httpContext);
        ProtocolVersion b2 = httpRequest.m10637h().m11407b();
        if ((!httpRequest.m10637h().m11406a().equalsIgnoreCase("CONNECT") || !b2.m11401c(HttpVersion.f7209b)) && !httpRequest.m10612a("Host")) {
            HttpHost httpHost;
            HttpHost o = b.m11537o();
            if (o == null) {
                HttpConnection l = b.m11534l();
                if (l instanceof HttpInetConnection) {
                    InetAddress f = ((HttpInetConnection) l).m11389f();
                    int g = ((HttpInetConnection) l).m11390g();
                    if (f != null) {
                        httpHost = new HttpHost(f.getHostName(), g);
                        if (httpHost == null) {
                            if (!b2.m11401c(HttpVersion.f7209b)) {
                                throw new ProtocolException("Target host missing");
                            }
                            return;
                        }
                    }
                }
                httpHost = o;
                if (httpHost == null) {
                    if (!b2.m11401c(HttpVersion.f7209b)) {
                        throw new ProtocolException("Target host missing");
                    }
                    return;
                }
            }
            httpHost = o;
            httpRequest.m10610a("Host", httpHost.m11388e());
        }
    }
}
