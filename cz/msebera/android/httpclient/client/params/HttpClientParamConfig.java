package cz.msebera.android.httpclient.client.params;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.client.config.RequestConfig;
import cz.msebera.android.httpclient.client.config.RequestConfig.Builder;
import cz.msebera.android.httpclient.params.HttpParams;
import java.net.InetAddress;
import java.util.Collection;

@Deprecated
public final class HttpClientParamConfig {
    public static RequestConfig m11524a(HttpParams httpParams) {
        boolean z;
        Builder c = RequestConfig.m11492g().m11488d(httpParams.m12081a("http.socket.timeout", 0)).m11485b(httpParams.m12085a("http.connection.stalecheck", true)).m11486c(httpParams.m12081a("http.connection.timeout", 0)).m11481a(httpParams.m12085a("http.protocol.expect-continue", false)).m11477a((HttpHost) httpParams.m12084a("http.route.default-proxy")).m11479a((InetAddress) httpParams.m12084a("http.route.local-address")).m11484b((Collection) httpParams.m12084a("http.auth.proxy-scheme-pref")).m11480a((Collection) httpParams.m12084a("http.auth.target-scheme-pref")).m11491f(httpParams.m12085a("http.protocol.handle-authentication", true)).m11490e(httpParams.m12085a("http.protocol.allow-circular-redirects", false)).m11483b((int) httpParams.m12082a("http.conn-manager.timeout", 0)).m11478a((String) httpParams.m12084a("http.protocol.cookie-policy")).m11476a(httpParams.m12081a("http.protocol.max-redirects", 50)).m11487c(httpParams.m12085a("http.protocol.handle-redirects", true));
        if (httpParams.m12085a("http.protocol.reject-relative-redirect", false)) {
            z = false;
        } else {
            z = true;
        }
        return c.m11489d(z).m11482a();
    }
}
