package cz.msebera.android.httpclient.client.protocol;

import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.auth.AuthState;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;

@Deprecated
public class RequestTargetAuthentication extends RequestAuthenticationBase {
    public void m11561a(HttpRequest httpRequest, HttpContext httpContext) {
        Args.m12722a((Object) httpRequest, "HTTP request");
        Args.m12722a((Object) httpContext, "HTTP context");
        if (!httpRequest.m10637h().m11406a().equalsIgnoreCase("CONNECT") && !httpRequest.m10612a("Authorization")) {
            AuthState authState = (AuthState) httpContext.m11528a("http.auth.target-scope");
            if (authState == null) {
                this.a.m11834a("Target auth state not set in the context");
                return;
            }
            if (this.a.m11836a()) {
                this.a.m11834a("Target auth state: " + authState.m11436b());
            }
            m11557a(authState, httpRequest, httpContext);
        }
    }
}
