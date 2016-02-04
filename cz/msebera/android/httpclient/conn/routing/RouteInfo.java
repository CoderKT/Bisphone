package cz.msebera.android.httpclient.conn.routing;

import cz.msebera.android.httpclient.HttpHost;
import java.net.InetAddress;

public interface RouteInfo {

    public enum LayerType {
        PLAIN,
        LAYERED
    }

    public enum TunnelType {
        PLAIN,
        TUNNELLED
    }

    HttpHost m11685a();

    HttpHost m11686a(int i);

    InetAddress m11687b();

    int m11688c();

    HttpHost m11689d();

    boolean m11690e();

    boolean m11691f();

    boolean m11692g();
}
