package cz.msebera.android.httpclient.impl;

import cz.msebera.android.httpclient.HttpInetConnection;
import cz.msebera.android.httpclient.impl.io.SocketInputBuffer;
import cz.msebera.android.httpclient.impl.io.SocketOutputBuffer;
import cz.msebera.android.httpclient.io.SessionInputBuffer;
import cz.msebera.android.httpclient.io.SessionOutputBuffer;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;

@Deprecated
public class SocketHttpClientConnection extends AbstractHttpClientConnection implements HttpInetConnection {
    private volatile boolean f7457a;
    private volatile Socket f7458b;

    public SocketHttpClientConnection() {
        this.f7458b = null;
    }

    protected void m11882q() {
        Asserts.m12729a(!this.f7457a, "Connection is already open");
    }

    protected void m11881j() {
        Asserts.m12729a(this.f7457a, "Connection is not open");
    }

    protected SessionInputBuffer m11872a(Socket socket, int i, HttpParams httpParams) {
        return new SocketInputBuffer(socket, i, httpParams);
    }

    protected SessionOutputBuffer m11874b(Socket socket, int i, HttpParams httpParams) {
        return new SocketOutputBuffer(socket, i, httpParams);
    }

    protected void m11873a(Socket socket, HttpParams httpParams) {
        Args.m12722a((Object) socket, "Socket");
        Args.m12722a((Object) httpParams, "HTTP parameters");
        this.f7458b = socket;
        int a = httpParams.m12081a("http.socket.buffer-size", -1);
        m11852a(m11872a(socket, a, httpParams), m11874b(socket, a, httpParams), httpParams);
        this.f7457a = true;
    }

    public boolean m11876c() {
        return this.f7457a;
    }

    protected Socket m11880i() {
        return this.f7458b;
    }

    public InetAddress m11878f() {
        if (this.f7458b != null) {
            return this.f7458b.getInetAddress();
        }
        return null;
    }

    public int m11879g() {
        if (this.f7458b != null) {
            return this.f7458b.getPort();
        }
        return -1;
    }

    public void m11875b(int i) {
        m11881j();
        if (this.f7458b != null) {
            try {
                this.f7458b.setSoTimeout(i);
            } catch (SocketException e) {
            }
        }
    }

    public void m11877e() {
        this.f7457a = false;
        Socket socket = this.f7458b;
        if (socket != null) {
            socket.close();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void close() {
        /*
        r2 = this;
        r0 = r2.f7457a;
        if (r0 != 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r0 = 0;
        r2.f7457a = r0;
        r1 = r2.f7458b;
        r2.m11860o();	 Catch:{ all -> 0x0017 }
        r1.shutdownOutput();	 Catch:{ IOException -> 0x001c, UnsupportedOperationException -> 0x0020 }
    L_0x0010:
        r1.shutdownInput();	 Catch:{ IOException -> 0x001e, UnsupportedOperationException -> 0x0020 }
    L_0x0013:
        r1.close();
        goto L_0x0004;
    L_0x0017:
        r0 = move-exception;
        r1.close();
        throw r0;
    L_0x001c:
        r0 = move-exception;
        goto L_0x0010;
    L_0x001e:
        r0 = move-exception;
        goto L_0x0013;
    L_0x0020:
        r0 = move-exception;
        goto L_0x0013;
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.httpclient.impl.SocketHttpClientConnection.close():void");
    }

    private static void m11871a(StringBuilder stringBuilder, SocketAddress socketAddress) {
        if (socketAddress instanceof InetSocketAddress) {
            Object hostAddress;
            InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
            if (inetSocketAddress.getAddress() != null) {
                hostAddress = inetSocketAddress.getAddress().getHostAddress();
            } else {
                hostAddress = inetSocketAddress.getAddress();
            }
            stringBuilder.append(hostAddress).append(':').append(inetSocketAddress.getPort());
            return;
        }
        stringBuilder.append(socketAddress);
    }

    public String toString() {
        if (this.f7458b == null) {
            return super.toString();
        }
        StringBuilder stringBuilder = new StringBuilder();
        SocketAddress remoteSocketAddress = this.f7458b.getRemoteSocketAddress();
        SocketAddress localSocketAddress = this.f7458b.getLocalSocketAddress();
        if (!(remoteSocketAddress == null || localSocketAddress == null)) {
            m11871a(stringBuilder, localSocketAddress);
            stringBuilder.append("<->");
            m11871a(stringBuilder, remoteSocketAddress);
        }
        return stringBuilder.toString();
    }
}
