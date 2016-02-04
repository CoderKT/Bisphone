package cz.msebera.android.httpclient.conn.scheme;

import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.LangUtils;
import java.util.Locale;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager;

@Deprecated
public final class Scheme {
    private final String f7368a;
    private final SchemeSocketFactory f7369b;
    private final int f7370c;
    private final boolean f7371d;
    private String f7372e;

    public Scheme(String str, int i, SchemeSocketFactory schemeSocketFactory) {
        Args.m12722a((Object) str, "Scheme name");
        boolean z = i > 0 && i <= InBandBytestreamManager.MAXIMUM_BLOCK_SIZE;
        Args.m12724a(z, "Port is invalid");
        Args.m12722a((Object) schemeSocketFactory, "Socket factory");
        this.f7368a = str.toLowerCase(Locale.ENGLISH);
        this.f7370c = i;
        if (schemeSocketFactory instanceof SchemeLayeredSocketFactory) {
            this.f7371d = true;
            this.f7369b = schemeSocketFactory;
        } else if (schemeSocketFactory instanceof LayeredSchemeSocketFactory) {
            this.f7371d = true;
            this.f7369b = new SchemeLayeredSocketFactoryAdaptor2((LayeredSchemeSocketFactory) schemeSocketFactory);
        } else {
            this.f7371d = false;
            this.f7369b = schemeSocketFactory;
        }
    }

    @Deprecated
    public Scheme(String str, SocketFactory socketFactory, int i) {
        Args.m12722a((Object) str, "Scheme name");
        Args.m12722a((Object) socketFactory, "Socket factory");
        boolean z = i > 0 && i <= InBandBytestreamManager.MAXIMUM_BLOCK_SIZE;
        Args.m12724a(z, "Port is invalid");
        this.f7368a = str.toLowerCase(Locale.ENGLISH);
        if (socketFactory instanceof LayeredSocketFactory) {
            this.f7369b = new SchemeLayeredSocketFactoryAdaptor((LayeredSocketFactory) socketFactory);
            this.f7371d = true;
        } else {
            this.f7369b = new SchemeSocketFactoryAdaptor(socketFactory);
            this.f7371d = false;
        }
        this.f7370c = i;
    }

    public final int m11725a() {
        return this.f7370c;
    }

    public final SchemeSocketFactory m11727b() {
        return this.f7369b;
    }

    public final String m11728c() {
        return this.f7368a;
    }

    public final boolean m11729d() {
        return this.f7371d;
    }

    public final int m11726a(int i) {
        return i <= 0 ? this.f7370c : i;
    }

    public final String toString() {
        if (this.f7372e == null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.f7368a);
            stringBuilder.append(':');
            stringBuilder.append(Integer.toString(this.f7370c));
            this.f7372e = stringBuilder.toString();
        }
        return this.f7372e;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Scheme)) {
            return false;
        }
        Scheme scheme = (Scheme) obj;
        if (this.f7368a.equals(scheme.f7368a) && this.f7370c == scheme.f7370c && this.f7371d == scheme.f7371d) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return LangUtils.m12768a(LangUtils.m12767a(LangUtils.m12766a(17, this.f7370c), this.f7368a), this.f7371d);
    }
}
