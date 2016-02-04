package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.cookie.CookieAttributeHandler;
import cz.msebera.android.httpclient.cookie.CookieSpec;
import cz.msebera.android.httpclient.util.Args;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCookieSpec implements CookieSpec {
    private final Map<String, CookieAttributeHandler> f7747a;

    public AbstractCookieSpec() {
        this.f7747a = new HashMap(10);
    }

    public void m12390a(String str, CookieAttributeHandler cookieAttributeHandler) {
        Args.m12722a((Object) str, "Attribute name");
        Args.m12722a((Object) cookieAttributeHandler, "Attribute handler");
        this.f7747a.put(str, cookieAttributeHandler);
    }

    protected CookieAttributeHandler m12389a(String str) {
        return (CookieAttributeHandler) this.f7747a.get(str);
    }

    protected Collection<CookieAttributeHandler> m12391c() {
        return this.f7747a.values();
    }
}
