package cz.msebera.android.httpclient.conn.scheme;

import cz.msebera.android.httpclient.params.HttpParams;
import java.net.InetSocketAddress;
import java.net.Socket;

@Deprecated
public interface SchemeSocketFactory {
    Socket m10698a(HttpParams httpParams);

    Socket m10699a(Socket socket, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, HttpParams httpParams);

    boolean m10700a(Socket socket);
}
