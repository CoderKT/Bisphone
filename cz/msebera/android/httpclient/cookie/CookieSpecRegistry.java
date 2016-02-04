package cz.msebera.android.httpclient.cookie;

import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.config.Lookup;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

@Deprecated
public final class CookieSpecRegistry implements Lookup<CookieSpecProvider> {
    private final ConcurrentHashMap<String, CookieSpecFactory> f7395a;

    /* renamed from: cz.msebera.android.httpclient.cookie.CookieSpecRegistry.1 */
    class C09361 implements CookieSpecProvider {
        final /* synthetic */ String f7393a;
        final /* synthetic */ CookieSpecRegistry f7394b;

        C09361(CookieSpecRegistry cookieSpecRegistry, String str) {
            this.f7394b = cookieSpecRegistry;
            this.f7393a = str;
        }

        public CookieSpec m11797a(HttpContext httpContext) {
            return this.f7394b.m11798a(this.f7393a, ((HttpRequest) httpContext.m11528a("http.request")).m10622g());
        }
    }

    public /* synthetic */ Object m11801b(String str) {
        return m11799a(str);
    }

    public CookieSpecRegistry() {
        this.f7395a = new ConcurrentHashMap();
    }

    public void m11800a(String str, CookieSpecFactory cookieSpecFactory) {
        Args.m12722a((Object) str, "Name");
        Args.m12722a((Object) cookieSpecFactory, "Cookie spec factory");
        this.f7395a.put(str.toLowerCase(Locale.ENGLISH), cookieSpecFactory);
    }

    public CookieSpec m11798a(String str, HttpParams httpParams) {
        Args.m12722a((Object) str, "Name");
        CookieSpecFactory cookieSpecFactory = (CookieSpecFactory) this.f7395a.get(str.toLowerCase(Locale.ENGLISH));
        if (cookieSpecFactory != null) {
            return cookieSpecFactory.m11795a(httpParams);
        }
        throw new IllegalStateException("Unsupported cookie spec: " + str);
    }

    public CookieSpecProvider m11799a(String str) {
        return new C09361(this, str);
    }
}
