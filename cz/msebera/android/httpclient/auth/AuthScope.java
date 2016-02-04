package cz.msebera.android.httpclient.auth;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.util.LangUtils;
import java.util.Locale;

public class AuthScope {
    public static final String f7222a;
    public static final String f7223b;
    public static final String f7224c;
    public static final AuthScope f7225d;
    private final String f7226e;
    private final String f7227f;
    private final String f7228g;
    private final int f7229h;

    static {
        f7222a = null;
        f7223b = null;
        f7224c = null;
        f7225d = new AuthScope(f7222a, -1, f7223b, f7224c);
    }

    public AuthScope(String str, int i, String str2, String str3) {
        this.f7228g = str == null ? f7222a : str.toLowerCase(Locale.ENGLISH);
        if (i < 0) {
            i = -1;
        }
        this.f7229h = i;
        if (str2 == null) {
            str2 = f7223b;
        }
        this.f7227f = str2;
        this.f7226e = str3 == null ? f7224c : str3.toUpperCase(Locale.ENGLISH);
    }

    public AuthScope(HttpHost httpHost, String str, String str2) {
        this(httpHost.m11384a(), httpHost.m11385b(), str, str2);
    }

    public AuthScope(String str, int i) {
        this(str, i, f7223b, f7224c);
    }

    public int m11429a(AuthScope authScope) {
        int i = 0;
        if (LangUtils.m12769a(this.f7226e, authScope.f7226e)) {
            i = 1;
        } else if (!(this.f7226e == f7224c || authScope.f7226e == f7224c)) {
            return -1;
        }
        if (LangUtils.m12769a(this.f7227f, authScope.f7227f)) {
            i += 2;
        } else if (!(this.f7227f == f7223b || authScope.f7227f == f7223b)) {
            return -1;
        }
        if (this.f7229h == authScope.f7229h) {
            i += 4;
        } else if (!(this.f7229h == -1 || authScope.f7229h == -1)) {
            return -1;
        }
        if (LangUtils.m12769a(this.f7228g, authScope.f7228g)) {
            return i + 8;
        }
        if (this.f7228g == f7222a || authScope.f7228g == f7222a) {
            return i;
        }
        return -1;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AuthScope)) {
            return super.equals(obj);
        }
        AuthScope authScope = (AuthScope) obj;
        if (LangUtils.m12769a(this.f7228g, authScope.f7228g) && this.f7229h == authScope.f7229h && LangUtils.m12769a(this.f7227f, authScope.f7227f) && LangUtils.m12769a(this.f7226e, authScope.f7226e)) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.f7226e != null) {
            stringBuilder.append(this.f7226e.toUpperCase(Locale.ENGLISH));
            stringBuilder.append(' ');
        }
        if (this.f7227f != null) {
            stringBuilder.append('\'');
            stringBuilder.append(this.f7227f);
            stringBuilder.append('\'');
        } else {
            stringBuilder.append("<any realm>");
        }
        if (this.f7228g != null) {
            stringBuilder.append('@');
            stringBuilder.append(this.f7228g);
            if (this.f7229h >= 0) {
                stringBuilder.append(':');
                stringBuilder.append(this.f7229h);
            }
        }
        return stringBuilder.toString();
    }

    public int hashCode() {
        return LangUtils.m12767a(LangUtils.m12767a(LangUtils.m12766a(LangUtils.m12767a(17, this.f7228g), this.f7229h), this.f7227f), this.f7226e);
    }
}
