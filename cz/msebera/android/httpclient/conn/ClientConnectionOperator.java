package cz.msebera.android.httpclient.conn;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.HttpContext;
import java.net.InetAddress;

@Deprecated
public interface ClientConnectionOperator {
    OperatedClientConnection m11637a();

    void m11638a(OperatedClientConnection operatedClientConnection, HttpHost httpHost, HttpContext httpContext, HttpParams httpParams);

    void m11639a(OperatedClientConnection operatedClientConnection, HttpHost httpHost, InetAddress inetAddress, HttpContext httpContext, HttpParams httpParams);
}
