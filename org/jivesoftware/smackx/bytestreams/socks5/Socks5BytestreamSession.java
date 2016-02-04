package org.jivesoftware.smackx.bytestreams.socks5;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import org.jivesoftware.smackx.bytestreams.BytestreamSession;

public class Socks5BytestreamSession implements BytestreamSession {
    private final boolean isDirect;
    private final Socket socket;

    protected Socks5BytestreamSession(Socket socket, boolean z) {
        this.socket = socket;
        this.isDirect = z;
    }

    public boolean isDirect() {
        return this.isDirect;
    }

    public boolean isMediated() {
        return !this.isDirect;
    }

    public InputStream getInputStream() {
        return this.socket.getInputStream();
    }

    public OutputStream getOutputStream() {
        return this.socket.getOutputStream();
    }

    public int getReadTimeout() {
        try {
            return this.socket.getSoTimeout();
        } catch (SocketException e) {
            throw new IOException("Error on underlying Socket");
        }
    }

    public void setReadTimeout(int i) {
        try {
            this.socket.setSoTimeout(i);
        } catch (SocketException e) {
            throw new IOException("Error on underlying Socket");
        }
    }

    public void close() {
        this.socket.close();
    }
}
