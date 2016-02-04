package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.conn.scheme.PlainSocketFactory;
import cz.msebera.android.httpclient.conn.scheme.Scheme;
import cz.msebera.android.httpclient.conn.scheme.SchemeRegistry;
import cz.msebera.android.httpclient.conn.ssl.SSLSocketFactory;

@Deprecated
public final class SchemeRegistryFactory {
    public static SchemeRegistry m12319a() {
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.m11739a(new Scheme("http", 80, PlainSocketFactory.m11719a()));
        schemeRegistry.m11739a(new Scheme("https", 443, SSLSocketFactory.m10709d()));
        return schemeRegistry;
    }
}
