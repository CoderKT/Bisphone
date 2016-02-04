package cz.msebera.android.httpclient.conn.scheme;

import cz.msebera.android.httpclient.params.HttpParams;
import java.net.Socket;

@Deprecated
public interface SchemeLayeredSocketFactory extends SchemeSocketFactory {
    Socket m10706a(Socket socket, String str, int i, HttpParams httpParams);
}
