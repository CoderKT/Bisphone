package cz.msebera.android.httpclient.conn.scheme;

import cz.msebera.android.httpclient.params.HttpParams;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

@Deprecated
class SchemeSocketFactoryAdaptor implements SchemeSocketFactory {
    private final SocketFactory f7374a;

    SchemeSocketFactoryAdaptor(SocketFactory socketFactory) {
        this.f7374a = socketFactory;
    }

    public Socket m11735a(Socket socket, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, HttpParams httpParams) {
        String hostName = inetSocketAddress.getHostName();
        int port = inetSocketAddress.getPort();
        InetAddress inetAddress = null;
        int i = 0;
        if (inetSocketAddress2 != null) {
            inetAddress = inetSocketAddress2.getAddress();
            i = inetSocketAddress2.getPort();
        }
        return this.f7374a.m10702a(socket, hostName, port, inetAddress, i, httpParams);
    }

    public Socket m11734a(HttpParams httpParams) {
        return this.f7374a.m10704c();
    }

    public boolean m11736a(Socket socket) {
        return this.f7374a.m10703a(socket);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof SchemeSocketFactoryAdaptor) {
            return this.f7374a.equals(((SchemeSocketFactoryAdaptor) obj).f7374a);
        }
        return this.f7374a.equals(obj);
    }

    public int hashCode() {
        return this.f7374a.hashCode();
    }
}
