package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.conn.DnsResolver;
import java.net.InetAddress;

public class SystemDefaultDnsResolver implements DnsResolver {
    public static final SystemDefaultDnsResolver f7696a;

    static {
        f7696a = new SystemDefaultDnsResolver();
    }

    public InetAddress[] m12320a(String str) {
        return InetAddress.getAllByName(str);
    }
}
