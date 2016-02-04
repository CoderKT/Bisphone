package cz.msebera.android.httpclient.protocol;

import cz.msebera.android.httpclient.util.Args;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BasicHttpContext implements HttpContext {
    private final HttpContext f7906a;
    private final Map<String, Object> f7907b;

    public BasicHttpContext() {
        this(null);
    }

    public BasicHttpContext(HttpContext httpContext) {
        this.f7907b = new ConcurrentHashMap();
        this.f7906a = httpContext;
    }

    public Object m12686a(String str) {
        Args.m12722a((Object) str, "Id");
        Object obj = this.f7907b.get(str);
        if (obj != null || this.f7906a == null) {
            return obj;
        }
        return this.f7906a.m11528a(str);
    }

    public void m12687a(String str, Object obj) {
        Args.m12722a((Object) str, "Id");
        if (obj != null) {
            this.f7907b.put(str, obj);
        } else {
            this.f7907b.remove(str);
        }
    }

    public String toString() {
        return this.f7907b.toString();
    }
}
