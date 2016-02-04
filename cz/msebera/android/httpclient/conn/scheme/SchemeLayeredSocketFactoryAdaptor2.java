package cz.msebera.android.httpclient.conn.scheme;

import cz.msebera.android.httpclient.params.HttpParams;
import java.net.InetSocketAddress;
import java.net.Socket;

@Deprecated
class SchemeLayeredSocketFactoryAdaptor2 implements SchemeLayeredSocketFactory {
    private final LayeredSchemeSocketFactory f7373a;

    SchemeLayeredSocketFactoryAdaptor2(LayeredSchemeSocketFactory layeredSchemeSocketFactory) {
        this.f7373a = layeredSchemeSocketFactory;
    }

    public Socket m11730a(HttpParams httpParams) {
        return this.f7373a.m10698a(httpParams);
    }

    public Socket m11732a(Socket socket, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, HttpParams httpParams) {
        return this.f7373a.m10699a(socket, inetSocketAddress, inetSocketAddress2, httpParams);
    }

    public boolean m11733a(Socket socket) {
        return this.f7373a.m10700a(socket);
    }

    public Socket m11731a(Socket socket, String str, int i, HttpParams httpParams) {
        return this.f7373a.m10701b(socket, str, i, true);
    }
}
