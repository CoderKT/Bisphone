package org.jivesoftware.smack.proxy;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import javax.net.SocketFactory;

class DirectSocketFactory extends SocketFactory {
    public Socket createSocket(String str, int i) {
        Socket socket = new Socket(Proxy.NO_PROXY);
        socket.connect(new InetSocketAddress(str, i));
        return socket;
    }

    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) {
        return new Socket(str, i, inetAddress, i2);
    }

    public Socket createSocket(InetAddress inetAddress, int i) {
        Socket socket = new Socket(Proxy.NO_PROXY);
        socket.connect(new InetSocketAddress(inetAddress, i));
        return socket;
    }

    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) {
        return new Socket(inetAddress, i, inetAddress2, i2);
    }
}
