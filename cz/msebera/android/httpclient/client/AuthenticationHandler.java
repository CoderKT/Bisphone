package cz.msebera.android.httpclient.client;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.auth.AuthScheme;
import cz.msebera.android.httpclient.protocol.HttpContext;
import java.util.Map;

@Deprecated
public interface AuthenticationHandler {
    AuthScheme m11455a(Map<String, Header> map, HttpResponse httpResponse, HttpContext httpContext);

    boolean m11456a(HttpResponse httpResponse, HttpContext httpContext);

    Map<String, Header> m11457b(HttpResponse httpResponse, HttpContext httpContext);
}
