package cz.msebera.android.httpclient;

import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.LangUtils;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.Locale;

public final class HttpHost implements Serializable, Cloneable {
    protected final String f7200a;
    protected final String f7201b;
    protected final int f7202c;
    protected final String f7203d;
    protected final InetAddress f7204e;

    public HttpHost(String str, int i, String str2) {
        this.f7200a = (String) Args.m12727b((CharSequence) str, "Host name");
        this.f7201b = str.toLowerCase(Locale.ENGLISH);
        if (str2 != null) {
            this.f7203d = str2.toLowerCase(Locale.ENGLISH);
        } else {
            this.f7203d = "http";
        }
        this.f7202c = i;
        this.f7204e = null;
    }

    public HttpHost(String str, int i) {
        this(str, i, null);
    }

    public String m11384a() {
        return this.f7200a;
    }

    public int m11385b() {
        return this.f7202c;
    }

    public String m11386c() {
        return this.f7203d;
    }

    public String m11387d() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.f7203d);
        stringBuilder.append("://");
        stringBuilder.append(this.f7200a);
        if (this.f7202c != -1) {
            stringBuilder.append(':');
            stringBuilder.append(Integer.toString(this.f7202c));
        }
        return stringBuilder.toString();
    }

    public String m11388e() {
        if (this.f7202c == -1) {
            return this.f7200a;
        }
        StringBuilder stringBuilder = new StringBuilder(this.f7200a.length() + 6);
        stringBuilder.append(this.f7200a);
        stringBuilder.append(":");
        stringBuilder.append(Integer.toString(this.f7202c));
        return stringBuilder.toString();
    }

    public String toString() {
        return m11387d();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HttpHost)) {
            return false;
        }
        HttpHost httpHost = (HttpHost) obj;
        if (this.f7201b.equals(httpHost.f7201b) && this.f7202c == httpHost.f7202c && this.f7203d.equals(httpHost.f7203d)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return LangUtils.m12767a(LangUtils.m12766a(LangUtils.m12767a(17, this.f7201b), this.f7202c), this.f7203d);
    }

    public Object clone() {
        return super.clone();
    }
}
