package cz.msebera.android.httpclient.conn;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.util.Args;
import java.net.InetAddress;
import java.net.InetSocketAddress;

@Deprecated
public class HttpInetSocketAddress extends InetSocketAddress {
    private final HttpHost f7342a;

    public HttpInetSocketAddress(HttpHost httpHost, InetAddress inetAddress, int i) {
        super(inetAddress, i);
        Args.m12722a((Object) httpHost, "HTTP host");
        this.f7342a = httpHost;
    }

    public HttpHost m11650a() {
        return this.f7342a;
    }

    public String toString() {
        return this.f7342a.m11384a() + ":" + getPort();
    }
}
