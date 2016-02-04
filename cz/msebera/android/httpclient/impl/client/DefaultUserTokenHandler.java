package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.HttpConnection;
import cz.msebera.android.httpclient.auth.AuthScheme;
import cz.msebera.android.httpclient.auth.AuthState;
import cz.msebera.android.httpclient.auth.Credentials;
import cz.msebera.android.httpclient.client.UserTokenHandler;
import cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import cz.msebera.android.httpclient.conn.ManagedHttpClientConnection;
import cz.msebera.android.httpclient.protocol.HttpContext;
import java.security.Principal;
import javax.net.ssl.SSLSession;

public class DefaultUserTokenHandler implements UserTokenHandler {
    public static final DefaultUserTokenHandler f7610a;

    static {
        f7610a = new DefaultUserTokenHandler();
    }

    public Object m12128a(HttpContext httpContext) {
        HttpClientContext a = HttpClientContext.m11538a(httpContext);
        Principal principal = null;
        AuthState i = a.m11549i();
        if (i != null) {
            principal = m12127a(i);
            if (principal == null) {
                principal = m12127a(a.m11550j());
            }
        }
        if (principal == null) {
            HttpConnection l = a.m11534l();
            if (l.m11375c() && (l instanceof ManagedHttpClientConnection)) {
                SSLSession m = ((ManagedHttpClientConnection) l).m11652m();
                if (m != null) {
                    return m.getLocalPrincipal();
                }
            }
        }
        return principal;
    }

    private static Principal m12127a(AuthState authState) {
        AuthScheme c = authState.m11437c();
        if (c != null && c.m11420d() && c.m11419c()) {
            Credentials d = authState.m11438d();
            if (d != null) {
                return d.m11441a();
            }
        }
        return null;
    }
}
