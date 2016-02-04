package cz.msebera.android.httpclient.protocol;

import cz.msebera.android.httpclient.util.Args;

@Deprecated
public final class DefaultedHttpContext implements HttpContext {
    private final HttpContext f7910a;
    private final HttpContext f7911b;

    public DefaultedHttpContext(HttpContext httpContext, HttpContext httpContext2) {
        this.f7910a = (HttpContext) Args.m12722a((Object) httpContext, "HTTP context");
        this.f7911b = httpContext2;
    }

    public Object m12701a(String str) {
        Object a = this.f7910a.m11528a(str);
        if (a == null) {
            return this.f7911b.m11528a(str);
        }
        return a;
    }

    public void m12702a(String str, Object obj) {
        this.f7910a.m11529a(str, obj);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[local: ").append(this.f7910a);
        stringBuilder.append("defaults: ").append(this.f7911b);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
