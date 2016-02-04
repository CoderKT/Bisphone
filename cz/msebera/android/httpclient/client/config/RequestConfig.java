package cz.msebera.android.httpclient.client.config;

import cz.msebera.android.httpclient.HttpHost;
import java.net.InetAddress;
import java.util.Collection;

public class RequestConfig implements Cloneable {
    public static final RequestConfig f7263a;
    private final boolean f7264b;
    private final HttpHost f7265c;
    private final InetAddress f7266d;
    private final boolean f7267e;
    private final String f7268f;
    private final boolean f7269g;
    private final boolean f7270h;
    private final boolean f7271i;
    private final int f7272j;
    private final boolean f7273k;
    private final Collection<String> f7274l;
    private final Collection<String> f7275m;
    private final int f7276n;
    private final int f7277o;
    private final int f7278p;

    public class Builder {
        private boolean f7248a;
        private HttpHost f7249b;
        private InetAddress f7250c;
        private boolean f7251d;
        private String f7252e;
        private boolean f7253f;
        private boolean f7254g;
        private boolean f7255h;
        private int f7256i;
        private boolean f7257j;
        private Collection<String> f7258k;
        private Collection<String> f7259l;
        private int f7260m;
        private int f7261n;
        private int f7262o;

        Builder() {
            this.f7251d = true;
            this.f7253f = true;
            this.f7256i = 50;
            this.f7254g = true;
            this.f7257j = true;
            this.f7260m = -1;
            this.f7261n = -1;
            this.f7262o = -1;
        }

        public Builder m11481a(boolean z) {
            this.f7248a = z;
            return this;
        }

        public Builder m11477a(HttpHost httpHost) {
            this.f7249b = httpHost;
            return this;
        }

        public Builder m11479a(InetAddress inetAddress) {
            this.f7250c = inetAddress;
            return this;
        }

        public Builder m11485b(boolean z) {
            this.f7251d = z;
            return this;
        }

        public Builder m11478a(String str) {
            this.f7252e = str;
            return this;
        }

        public Builder m11487c(boolean z) {
            this.f7253f = z;
            return this;
        }

        public Builder m11489d(boolean z) {
            this.f7254g = z;
            return this;
        }

        public Builder m11490e(boolean z) {
            this.f7255h = z;
            return this;
        }

        public Builder m11476a(int i) {
            this.f7256i = i;
            return this;
        }

        public Builder m11491f(boolean z) {
            this.f7257j = z;
            return this;
        }

        public Builder m11480a(Collection<String> collection) {
            this.f7258k = collection;
            return this;
        }

        public Builder m11484b(Collection<String> collection) {
            this.f7259l = collection;
            return this;
        }

        public Builder m11483b(int i) {
            this.f7260m = i;
            return this;
        }

        public Builder m11486c(int i) {
            this.f7261n = i;
            return this;
        }

        public Builder m11488d(int i) {
            this.f7262o = i;
            return this;
        }

        public RequestConfig m11482a() {
            return new RequestConfig(this.f7248a, this.f7249b, this.f7250c, this.f7251d, this.f7252e, this.f7253f, this.f7254g, this.f7255h, this.f7256i, this.f7257j, this.f7258k, this.f7259l, this.f7260m, this.f7261n, this.f7262o);
        }
    }

    protected /* synthetic */ Object clone() {
        return m11498f();
    }

    static {
        f7263a = new Builder().m11482a();
    }

    RequestConfig(boolean z, HttpHost httpHost, InetAddress inetAddress, boolean z2, String str, boolean z3, boolean z4, boolean z5, int i, boolean z6, Collection<String> collection, Collection<String> collection2, int i2, int i3, int i4) {
        this.f7264b = z;
        this.f7265c = httpHost;
        this.f7266d = inetAddress;
        this.f7267e = z2;
        this.f7268f = str;
        this.f7269g = z3;
        this.f7270h = z4;
        this.f7271i = z5;
        this.f7272j = i;
        this.f7273k = z6;
        this.f7274l = collection;
        this.f7275m = collection2;
        this.f7276n = i2;
        this.f7277o = i3;
        this.f7278p = i4;
    }

    public String m11493a() {
        return this.f7268f;
    }

    public boolean m11494b() {
        return this.f7270h;
    }

    public boolean m11495c() {
        return this.f7271i;
    }

    public Collection<String> m11496d() {
        return this.f7274l;
    }

    public Collection<String> m11497e() {
        return this.f7275m;
    }

    protected RequestConfig m11498f() {
        return (RequestConfig) super.clone();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(", expectContinueEnabled=").append(this.f7264b);
        stringBuilder.append(", proxy=").append(this.f7265c);
        stringBuilder.append(", localAddress=").append(this.f7266d);
        stringBuilder.append(", staleConnectionCheckEnabled=").append(this.f7267e);
        stringBuilder.append(", cookieSpec=").append(this.f7268f);
        stringBuilder.append(", redirectsEnabled=").append(this.f7269g);
        stringBuilder.append(", relativeRedirectsAllowed=").append(this.f7270h);
        stringBuilder.append(", maxRedirects=").append(this.f7272j);
        stringBuilder.append(", circularRedirectsAllowed=").append(this.f7271i);
        stringBuilder.append(", authenticationEnabled=").append(this.f7273k);
        stringBuilder.append(", targetPreferredAuthSchemes=").append(this.f7274l);
        stringBuilder.append(", proxyPreferredAuthSchemes=").append(this.f7275m);
        stringBuilder.append(", connectionRequestTimeout=").append(this.f7276n);
        stringBuilder.append(", connectTimeout=").append(this.f7277o);
        stringBuilder.append(", socketTimeout=").append(this.f7278p);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static Builder m11492g() {
        return new Builder();
    }
}
