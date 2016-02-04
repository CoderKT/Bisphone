package cz.msebera.android.httpclient.protocol;

import cz.msebera.android.httpclient.HttpConnection;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.util.Args;

public class HttpCoreContext implements HttpContext {
    private final HttpContext f7296a;

    public static HttpCoreContext m11530b(HttpContext httpContext) {
        Args.m12722a((Object) httpContext, "HTTP context");
        if (httpContext instanceof HttpCoreContext) {
            return (HttpCoreContext) httpContext;
        }
        return new HttpCoreContext(httpContext);
    }

    public HttpCoreContext(HttpContext httpContext) {
        this.f7296a = httpContext;
    }

    public HttpCoreContext() {
        this.f7296a = new BasicHttpContext();
    }

    public Object m11531a(String str) {
        return this.f7296a.m11528a(str);
    }

    public void m11533a(String str, Object obj) {
        this.f7296a.m11529a(str, obj);
    }

    public <T> T m11532a(String str, Class<T> cls) {
        Args.m12722a((Object) cls, "Attribute class");
        Object a = m11531a(str);
        if (a == null) {
            return null;
        }
        return cls.cast(a);
    }

    public HttpConnection m11534l() {
        return (HttpConnection) m11532a("http.connection", HttpConnection.class);
    }

    public HttpRequest m11535m() {
        return (HttpRequest) m11532a("http.request", HttpRequest.class);
    }

    public boolean m11536n() {
        Boolean bool = (Boolean) m11532a("http.request_sent", Boolean.class);
        return bool != null && bool.booleanValue();
    }

    public HttpHost m11537o() {
        return (HttpHost) m11532a("http.target_host", HttpHost.class);
    }
}
