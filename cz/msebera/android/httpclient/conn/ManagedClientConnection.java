package cz.msebera.android.httpclient.conn;

import cz.msebera.android.httpclient.HttpClientConnection;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.HttpContext;
import java.util.concurrent.TimeUnit;

@Deprecated
public interface ManagedClientConnection extends HttpClientConnection, ConnectionReleaseTrigger, HttpRoutedConnection, ManagedHttpClientConnection {
    void m11653a(long j, TimeUnit timeUnit);

    void m11654a(HttpHost httpHost, boolean z, HttpParams httpParams);

    void m11655a(HttpRoute httpRoute, HttpContext httpContext, HttpParams httpParams);

    void m11656a(HttpContext httpContext, HttpParams httpParams);

    void m11657a(Object obj);

    void m11658a(boolean z, HttpParams httpParams);

    HttpRoute m11659h();

    void m11660k();

    void m11661l();
}
