package cz.msebera.android.httpclient.impl.io;

import cz.msebera.android.httpclient.io.EofSensor;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;
import java.net.Socket;

@Deprecated
public class SocketInputBuffer extends AbstractSessionInputBuffer implements EofSensor {
    private final Socket f7850a;
    private boolean f7851b;

    public SocketInputBuffer(Socket socket, int i, HttpParams httpParams) {
        int receiveBufferSize;
        int i2 = 1024;
        Args.m12722a((Object) socket, "Socket");
        this.f7850a = socket;
        this.f7851b = false;
        if (i < 0) {
            receiveBufferSize = socket.getReceiveBufferSize();
        } else {
            receiveBufferSize = i;
        }
        if (receiveBufferSize >= 1024) {
            i2 = receiveBufferSize;
        }
        m12530a(socket.getInputStream(), i2, httpParams);
    }

    protected int m12563f() {
        int f = super.m12534f();
        this.f7851b = f == -1;
        return f;
    }

    public boolean m12561a(int i) {
        boolean g = m12535g();
        if (!g) {
            int soTimeout = this.f7850a.getSoTimeout();
            try {
                this.f7850a.setSoTimeout(i);
                m12563f();
                g = m12535g();
            } finally {
                this.f7850a.setSoTimeout(soTimeout);
            }
        }
        return g;
    }

    public boolean m12562c() {
        return this.f7851b;
    }
}
