package cz.msebera.android.httpclient.client.protocol;

import cz.msebera.android.httpclient.auth.AuthSchemeProvider;
import cz.msebera.android.httpclient.auth.AuthState;
import cz.msebera.android.httpclient.client.AuthCache;
import cz.msebera.android.httpclient.client.CookieStore;
import cz.msebera.android.httpclient.client.CredentialsProvider;
import cz.msebera.android.httpclient.client.config.RequestConfig;
import cz.msebera.android.httpclient.config.Lookup;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.conn.routing.RouteInfo;
import cz.msebera.android.httpclient.cookie.CookieOrigin;
import cz.msebera.android.httpclient.cookie.CookieSpec;
import cz.msebera.android.httpclient.cookie.CookieSpecProvider;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.protocol.HttpCoreContext;

public class HttpClientContext extends HttpCoreContext {
    public static HttpClientContext m11538a(HttpContext httpContext) {
        if (httpContext instanceof HttpClientContext) {
            return (HttpClientContext) httpContext;
        }
        return new HttpClientContext(httpContext);
    }

    public HttpClientContext(HttpContext httpContext) {
        super(httpContext);
    }

    public RouteInfo m11540a() {
        return (RouteInfo) m11532a("http.route", HttpRoute.class);
    }

    public CookieStore m11542b() {
        return (CookieStore) m11532a("http.cookie-store", CookieStore.class);
    }

    public CookieSpec m11543c() {
        return (CookieSpec) m11532a("http.cookie-spec", CookieSpec.class);
    }

    public CookieOrigin m11544d() {
        return (CookieOrigin) m11532a("http.cookie-origin", CookieOrigin.class);
    }

    private <T> Lookup<T> m11539b(String str, Class<T> cls) {
        return (Lookup) m11532a(str, Lookup.class);
    }

    public Lookup<CookieSpecProvider> m11545e() {
        return m11539b("http.cookiespec-registry", CookieSpecProvider.class);
    }

    public Lookup<AuthSchemeProvider> m11546f() {
        return m11539b("http.authscheme-registry", AuthSchemeProvider.class);
    }

    public CredentialsProvider m11547g() {
        return (CredentialsProvider) m11532a("http.auth.credentials-provider", CredentialsProvider.class);
    }

    public AuthCache m11548h() {
        return (AuthCache) m11532a("http.auth.auth-cache", AuthCache.class);
    }

    public void m11541a(AuthCache authCache) {
        m11533a("http.auth.auth-cache", (Object) authCache);
    }

    public AuthState m11549i() {
        return (AuthState) m11532a("http.auth.target-scope", AuthState.class);
    }

    public AuthState m11550j() {
        return (AuthState) m11532a("http.auth.proxy-scope", AuthState.class);
    }

    public RequestConfig m11551k() {
        RequestConfig requestConfig = (RequestConfig) m11532a("http.request-config", RequestConfig.class);
        return requestConfig != null ? requestConfig : RequestConfig.f7263a;
    }
}
