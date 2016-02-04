package cz.msebera.android.httpclient.conn;

import cz.msebera.android.httpclient.HttpClientConnection;
import cz.msebera.android.httpclient.HttpInetConnection;
import javax.net.ssl.SSLSession;

public interface ManagedHttpClientConnection extends HttpClientConnection, HttpInetConnection {
    SSLSession m11652m();
}
