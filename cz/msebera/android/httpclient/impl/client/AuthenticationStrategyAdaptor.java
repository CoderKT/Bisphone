package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.auth.AuthOption;
import cz.msebera.android.httpclient.auth.AuthScheme;
import cz.msebera.android.httpclient.auth.AuthScope;
import cz.msebera.android.httpclient.auth.Credentials;
import cz.msebera.android.httpclient.client.AuthCache;
import cz.msebera.android.httpclient.client.AuthenticationHandler;
import cz.msebera.android.httpclient.client.AuthenticationStrategy;
import cz.msebera.android.httpclient.client.CredentialsProvider;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;

@Deprecated
class AuthenticationStrategyAdaptor implements AuthenticationStrategy {
    public HttpClientAndroidLog f7561a;
    private final AuthenticationHandler f7562b;

    public boolean m12063a(HttpHost httpHost, HttpResponse httpResponse, HttpContext httpContext) {
        return this.f7562b.m11456a(httpResponse, httpContext);
    }

    public Map<String, Header> m12064b(HttpHost httpHost, HttpResponse httpResponse, HttpContext httpContext) {
        return this.f7562b.m11457b(httpResponse, httpContext);
    }

    public Queue<AuthOption> m12061a(Map<String, Header> map, HttpHost httpHost, HttpResponse httpResponse, HttpContext httpContext) {
        Args.m12722a((Object) map, "Map of auth challenges");
        Args.m12722a((Object) httpHost, "Host");
        Args.m12722a((Object) httpResponse, "HTTP response");
        Args.m12722a((Object) httpContext, "HTTP context");
        Queue<AuthOption> linkedList = new LinkedList();
        CredentialsProvider credentialsProvider = (CredentialsProvider) httpContext.m11528a("http.auth.credentials-provider");
        if (credentialsProvider == null) {
            this.f7561a.m11834a("Credentials provider not set in the context");
            return linkedList;
        }
        try {
            AuthScheme a = this.f7562b.m11455a(map, httpResponse, httpContext);
            a.m11417a((Header) map.get(a.m11416a().toLowerCase(Locale.ENGLISH)));
            Credentials a2 = credentialsProvider.m11469a(new AuthScope(httpHost.m11384a(), httpHost.m11385b(), a.m11418b(), a.m11416a()));
            if (a2 != null) {
                linkedList.add(new AuthOption(a, a2));
            }
            return linkedList;
        } catch (Throwable e) {
            if (this.f7561a.m11842c()) {
                this.f7561a.m11841c(e.getMessage(), e);
            }
            return linkedList;
        }
    }

    public void m12062a(HttpHost httpHost, AuthScheme authScheme, HttpContext httpContext) {
        AuthCache authCache = (AuthCache) httpContext.m11528a("http.auth.auth-cache");
        if (m12059a(authScheme)) {
            if (authCache == null) {
                authCache = new BasicAuthCache();
                httpContext.m11529a("http.auth.auth-cache", authCache);
            }
            if (this.f7561a.m11836a()) {
                this.f7561a.m11834a("Caching '" + authScheme.m11416a() + "' auth scheme for " + httpHost);
            }
            authCache.m11453a(httpHost, authScheme);
        }
    }

    public void m12065b(HttpHost httpHost, AuthScheme authScheme, HttpContext httpContext) {
        AuthCache authCache = (AuthCache) httpContext.m11528a("http.auth.auth-cache");
        if (authCache != null) {
            if (this.f7561a.m11836a()) {
                this.f7561a.m11834a("Removing from cache '" + authScheme.m11416a() + "' auth scheme for " + httpHost);
            }
            authCache.m11454b(httpHost);
        }
    }

    private boolean m12059a(AuthScheme authScheme) {
        if (authScheme == null || !authScheme.m11420d()) {
            return false;
        }
        String a = authScheme.m11416a();
        if (a.equalsIgnoreCase("Basic") || a.equalsIgnoreCase("Digest")) {
            return true;
        }
        return false;
    }

    public AuthenticationHandler m12060a() {
        return this.f7562b;
    }
}
