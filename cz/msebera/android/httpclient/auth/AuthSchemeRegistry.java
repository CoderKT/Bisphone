package cz.msebera.android.httpclient.auth;

import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.config.Lookup;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

@Deprecated
public final class AuthSchemeRegistry implements Lookup<AuthSchemeProvider> {
    private final ConcurrentHashMap<String, AuthSchemeFactory> f7221a;

    /* renamed from: cz.msebera.android.httpclient.auth.AuthSchemeRegistry.1 */
    class C09301 implements AuthSchemeProvider {
        final /* synthetic */ String f7219a;
        final /* synthetic */ AuthSchemeRegistry f7220b;

        C09301(AuthSchemeRegistry authSchemeRegistry, String str) {
            this.f7220b = authSchemeRegistry;
            this.f7219a = str;
        }

        public AuthScheme m11423a(HttpContext httpContext) {
            return this.f7220b.m11425a(this.f7219a, ((HttpRequest) httpContext.m11528a("http.request")).m10622g());
        }
    }

    public /* synthetic */ Object m11428b(String str) {
        return m11426a(str);
    }

    public AuthSchemeRegistry() {
        this.f7221a = new ConcurrentHashMap();
    }

    public void m11427a(String str, AuthSchemeFactory authSchemeFactory) {
        Args.m12722a((Object) str, "Name");
        Args.m12722a((Object) authSchemeFactory, "Authentication scheme factory");
        this.f7221a.put(str.toLowerCase(Locale.ENGLISH), authSchemeFactory);
    }

    public AuthScheme m11425a(String str, HttpParams httpParams) {
        Args.m12722a((Object) str, "Name");
        AuthSchemeFactory authSchemeFactory = (AuthSchemeFactory) this.f7221a.get(str.toLowerCase(Locale.ENGLISH));
        if (authSchemeFactory != null) {
            return authSchemeFactory.m11421a(httpParams);
        }
        throw new IllegalStateException("Unsupported authentication scheme: " + str);
    }

    public AuthSchemeProvider m11426a(String str) {
        return new C09301(this, str);
    }
}
