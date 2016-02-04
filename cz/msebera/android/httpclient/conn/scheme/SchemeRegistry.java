package cz.msebera.android.httpclient.conn.scheme;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.util.Args;
import java.util.concurrent.ConcurrentHashMap;

@Deprecated
public final class SchemeRegistry {
    private final ConcurrentHashMap<String, Scheme> f7376a;

    public SchemeRegistry() {
        this.f7376a = new ConcurrentHashMap();
    }

    public final Scheme m11740a(String str) {
        Scheme b = m11741b(str);
        if (b != null) {
            return b;
        }
        throw new IllegalStateException("Scheme '" + str + "' not registered.");
    }

    public final Scheme m11738a(HttpHost httpHost) {
        Args.m12722a((Object) httpHost, "Host");
        return m11740a(httpHost.m11386c());
    }

    public final Scheme m11741b(String str) {
        Args.m12722a((Object) str, "Scheme name");
        return (Scheme) this.f7376a.get(str);
    }

    public final Scheme m11739a(Scheme scheme) {
        Args.m12722a((Object) scheme, "Scheme");
        return (Scheme) this.f7376a.put(scheme.m11728c(), scheme);
    }
}
