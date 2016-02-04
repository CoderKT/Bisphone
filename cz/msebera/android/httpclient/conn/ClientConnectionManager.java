package cz.msebera.android.httpclient.conn;

import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.conn.scheme.SchemeRegistry;
import java.util.concurrent.TimeUnit;

@Deprecated
public interface ClientConnectionManager {
    ClientConnectionRequest m11632a(HttpRoute httpRoute, Object obj);

    SchemeRegistry m11633a();

    void m11634a(ManagedClientConnection managedClientConnection, long j, TimeUnit timeUnit);

    void m11635b();
}
