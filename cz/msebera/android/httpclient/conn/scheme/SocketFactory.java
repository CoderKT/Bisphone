package cz.msebera.android.httpclient.conn.scheme;

import cz.msebera.android.httpclient.params.HttpParams;
import java.net.InetAddress;
import java.net.Socket;

@Deprecated
public interface SocketFactory {
    Socket m10702a(Socket socket, String str, int i, InetAddress inetAddress, int i2, HttpParams httpParams);

    boolean m10703a(Socket socket);

    Socket m10704c();
}
