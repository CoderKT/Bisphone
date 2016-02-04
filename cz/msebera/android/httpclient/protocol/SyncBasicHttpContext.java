package cz.msebera.android.httpclient.protocol;

@Deprecated
public class SyncBasicHttpContext extends BasicHttpContext {
    public SyncBasicHttpContext(HttpContext httpContext) {
        super(httpContext);
    }

    public synchronized Object m12717a(String str) {
        return super.m12686a(str);
    }

    public synchronized void m12718a(String str, Object obj) {
        super.m12687a(str, obj);
    }
}
