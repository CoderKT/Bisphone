package cz.msebera.android.httpclient.impl.io;

import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;
import java.net.Socket;

@Deprecated
public class SocketOutputBuffer extends AbstractSessionOutputBuffer {
    public SocketOutputBuffer(Socket socket, int i, HttpParams httpParams) {
        int sendBufferSize;
        int i2 = 1024;
        Args.m12722a((Object) socket, "Socket");
        if (i < 0) {
            sendBufferSize = socket.getSendBufferSize();
        } else {
            sendBufferSize = i;
        }
        if (sendBufferSize >= 1024) {
            i2 = sendBufferSize;
        }
        m12541a(socket.getOutputStream(), i2, httpParams);
    }
}
