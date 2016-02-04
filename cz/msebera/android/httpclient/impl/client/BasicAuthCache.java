package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.auth.AuthScheme;
import cz.msebera.android.httpclient.client.AuthCache;
import cz.msebera.android.httpclient.conn.SchemePortResolver;
import cz.msebera.android.httpclient.conn.UnsupportedSchemeException;
import cz.msebera.android.httpclient.impl.conn.DefaultSchemePortResolver;
import cz.msebera.android.httpclient.util.Args;
import java.util.HashMap;

public class BasicAuthCache implements AuthCache {
    private final HashMap<HttpHost, AuthScheme> f7567a;
    private final SchemePortResolver f7568b;

    public BasicAuthCache(SchemePortResolver schemePortResolver) {
        this.f7567a = new HashMap();
        if (schemePortResolver == null) {
            schemePortResolver = DefaultSchemePortResolver.f7671a;
        }
        this.f7568b = schemePortResolver;
    }

    public BasicAuthCache() {
        this(null);
    }

    protected HttpHost m12076c(HttpHost httpHost) {
        if (httpHost.m11385b() > 0) {
            return httpHost;
        }
        try {
            return new HttpHost(httpHost.m11384a(), this.f7568b.m11667a(httpHost), httpHost.m11386c());
        } catch (UnsupportedSchemeException e) {
            return httpHost;
        }
    }

    public void m12074a(HttpHost httpHost, AuthScheme authScheme) {
        Args.m12722a((Object) httpHost, "HTTP host");
        this.f7567a.put(m12076c(httpHost), authScheme);
    }

    public AuthScheme m12073a(HttpHost httpHost) {
        Args.m12722a((Object) httpHost, "HTTP host");
        return (AuthScheme) this.f7567a.get(m12076c(httpHost));
    }

    public void m12075b(HttpHost httpHost) {
        Args.m12722a((Object) httpHost, "HTTP host");
        this.f7567a.remove(m12076c(httpHost));
    }

    public String toString() {
        return this.f7567a.toString();
    }
}
