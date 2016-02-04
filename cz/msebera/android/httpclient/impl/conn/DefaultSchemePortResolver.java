package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.conn.SchemePortResolver;
import cz.msebera.android.httpclient.conn.UnsupportedSchemeException;
import cz.msebera.android.httpclient.util.Args;

public class DefaultSchemePortResolver implements SchemePortResolver {
    public static final DefaultSchemePortResolver f7671a;

    static {
        f7671a = new DefaultSchemePortResolver();
    }

    public int m12250a(HttpHost httpHost) {
        Args.m12722a((Object) httpHost, "HTTP host");
        int b = httpHost.m11385b();
        if (b > 0) {
            return b;
        }
        String c = httpHost.m11386c();
        if (c.equalsIgnoreCase("http")) {
            return 80;
        }
        if (c.equalsIgnoreCase("https")) {
            return 443;
        }
        throw new UnsupportedSchemeException(c + " protocol is not supported");
    }
}
