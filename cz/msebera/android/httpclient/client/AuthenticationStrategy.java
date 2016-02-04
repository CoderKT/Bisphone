package cz.msebera.android.httpclient.client;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.auth.AuthOption;
import cz.msebera.android.httpclient.auth.AuthScheme;
import cz.msebera.android.httpclient.protocol.HttpContext;
import java.util.Map;
import java.util.Queue;

public interface AuthenticationStrategy {
    Queue<AuthOption> m11458a(Map<String, Header> map, HttpHost httpHost, HttpResponse httpResponse, HttpContext httpContext);

    void m11459a(HttpHost httpHost, AuthScheme authScheme, HttpContext httpContext);

    boolean m11460a(HttpHost httpHost, HttpResponse httpResponse, HttpContext httpContext);

    Map<String, Header> m11461b(HttpHost httpHost, HttpResponse httpResponse, HttpContext httpContext);

    void m11462b(HttpHost httpHost, AuthScheme authScheme, HttpContext httpContext);
}
