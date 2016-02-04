package cz.msebera.android.httpclient.conn.scheme;

import cz.msebera.android.httpclient.conn.ConnectTimeoutException;
import cz.msebera.android.httpclient.params.HttpConnectionParams;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

@Deprecated
public class PlainSocketFactory implements SchemeSocketFactory, SocketFactory {
    private final HostNameResolver f7367a;

    public static PlainSocketFactory m11719a() {
        return new PlainSocketFactory();
    }

    public PlainSocketFactory() {
        this.f7367a = null;
    }

    public Socket m11720a(HttpParams httpParams) {
        return new Socket();
    }

    public Socket m11724c() {
        return new Socket();
    }

    public Socket m11722a(Socket socket, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, HttpParams httpParams) {
        Args.m12722a((Object) inetSocketAddress, "Remote address");
        Args.m12722a((Object) httpParams, "HTTP parameters");
        if (socket == null) {
            socket = m11724c();
        }
        if (inetSocketAddress2 != null) {
            socket.setReuseAddress(HttpConnectionParams.m12672b(httpParams));
            socket.bind(inetSocketAddress2);
        }
        int e = HttpConnectionParams.m12676e(httpParams);
        try {
            socket.setSoTimeout(HttpConnectionParams.m12668a(httpParams));
            socket.connect(inetSocketAddress, e);
            return socket;
        } catch (SocketTimeoutException e2) {
            throw new ConnectTimeoutException("Connect to " + inetSocketAddress + " timed out");
        }
    }

    public final boolean m11723a(Socket socket) {
        return false;
    }

    @Deprecated
    public Socket m11721a(Socket socket, String str, int i, InetAddress inetAddress, int i2, HttpParams httpParams) {
        InetAddress a;
        InetSocketAddress inetSocketAddress = null;
        if (inetAddress != null || i2 > 0) {
            if (i2 <= 0) {
                i2 = 0;
            }
            inetSocketAddress = new InetSocketAddress(inetAddress, i2);
        }
        if (this.f7367a != null) {
            a = this.f7367a.m11718a(str);
        } else {
            a = InetAddress.getByName(str);
        }
        return m11722a(socket, new InetSocketAddress(a, i), inetSocketAddress, httpParams);
    }
}
