package cz.msebera.android.httpclient.client.protocol;

import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.auth.AuthState;
import cz.msebera.android.httpclient.conn.HttpRoutedConnection;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;

@Deprecated
public class RequestProxyAuthentication extends RequestAuthenticationBase {
    public void m11560a(HttpRequest httpRequest, HttpContext httpContext) {
        Args.m12722a((Object) httpRequest, "HTTP request");
        Args.m12722a((Object) httpContext, "HTTP context");
        if (!httpRequest.m10612a("Proxy-Authorization")) {
            HttpRoutedConnection httpRoutedConnection = (HttpRoutedConnection) httpContext.m11528a("http.connection");
            if (httpRoutedConnection == null) {
                this.a.m11834a("HTTP connection not set in the context");
            } else if (!httpRoutedConnection.m11651h().m11698e()) {
                AuthState authState = (AuthState) httpContext.m11528a("http.auth.proxy-scope");
                if (authState == null) {
                    this.a.m11834a("Proxy auth state not set in the context");
                    return;
                }
                if (this.a.m11836a()) {
                    this.a.m11834a("Proxy auth state: " + authState.m11436b());
                }
                m11557a(authState, httpRequest, httpContext);
            }
        }
    }
}
