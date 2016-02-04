package cz.msebera.android.httpclient.client.protocol;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpRequestInterceptor;
import cz.msebera.android.httpclient.auth.AuthProtocolState;
import cz.msebera.android.httpclient.auth.AuthScheme;
import cz.msebera.android.httpclient.auth.AuthScope;
import cz.msebera.android.httpclient.auth.AuthState;
import cz.msebera.android.httpclient.auth.Credentials;
import cz.msebera.android.httpclient.client.AuthCache;
import cz.msebera.android.httpclient.client.CredentialsProvider;
import cz.msebera.android.httpclient.conn.routing.RouteInfo;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;

public class RequestAuthCache implements HttpRequestInterceptor {
    public HttpClientAndroidLog f7298a;

    public RequestAuthCache() {
        this.f7298a = new HttpClientAndroidLog(getClass());
    }

    public void m11554a(HttpRequest httpRequest, HttpContext httpContext) {
        Args.m12722a((Object) httpRequest, "HTTP request");
        Args.m12722a((Object) httpContext, "HTTP context");
        HttpClientContext a = HttpClientContext.m11538a(httpContext);
        AuthCache h = a.m11548h();
        if (h == null) {
            this.f7298a.m11834a("Auth cache not set in the context");
            return;
        }
        CredentialsProvider g = a.m11547g();
        if (g == null) {
            this.f7298a.m11834a("Credentials provider not set in the context");
            return;
        }
        RouteInfo a2 = a.m11540a();
        if (a2 == null) {
            this.f7298a.m11834a("Route info not set in the context");
            return;
        }
        HttpHost o = a.m11537o();
        if (o == null) {
            this.f7298a.m11834a("Target host not set in the context");
            return;
        }
        HttpHost httpHost;
        if (o.m11385b() < 0) {
            httpHost = new HttpHost(o.m11384a(), a2.m11685a().m11385b(), o.m11386c());
        } else {
            httpHost = o;
        }
        AuthState i = a.m11549i();
        if (i != null && i.m11436b() == AuthProtocolState.UNCHALLENGED) {
            AuthScheme a3 = h.m11452a(httpHost);
            if (a3 != null) {
                m11553a(httpHost, a3, i, g);
            }
        }
        httpHost = a2.m11689d();
        i = a.m11550j();
        if (httpHost != null && i != null && i.m11436b() == AuthProtocolState.UNCHALLENGED) {
            AuthScheme a4 = h.m11452a(httpHost);
            if (a4 != null) {
                m11553a(httpHost, a4, i, g);
            }
        }
    }

    private void m11553a(HttpHost httpHost, AuthScheme authScheme, AuthState authState, CredentialsProvider credentialsProvider) {
        String a = authScheme.m11416a();
        if (this.f7298a.m11836a()) {
            this.f7298a.m11834a("Re-using cached '" + a + "' auth scheme for " + httpHost);
        }
        Credentials a2 = credentialsProvider.m11469a(new AuthScope(httpHost, AuthScope.f7223b, a));
        if (a2 != null) {
            if ("BASIC".equalsIgnoreCase(authScheme.m11416a())) {
                authState.m11431a(AuthProtocolState.CHALLENGED);
            } else {
                authState.m11431a(AuthProtocolState.SUCCESS);
            }
            authState.m11433a(authScheme, a2);
            return;
        }
        this.f7298a.m11834a("No credentials for preemptive authentication");
    }
}
