package cz.msebera.android.httpclient.conn;

import cz.msebera.android.httpclient.HttpClientConnection;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpInetConnection;
import cz.msebera.android.httpclient.params.HttpParams;
import java.net.Socket;

@Deprecated
public interface OperatedClientConnection extends HttpClientConnection, HttpInetConnection {
    void m11662a(Socket socket, HttpHost httpHost);

    void m11663a(Socket socket, HttpHost httpHost, boolean z, HttpParams httpParams);

    void m11664a(boolean z, HttpParams httpParams);

    boolean m11665h();

    Socket m11666i();
}
