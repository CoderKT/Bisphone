package cz.msebera.android.httpclient.cookie;

import cz.msebera.android.httpclient.util.Args;
import java.util.Locale;

public final class CookieOrigin {
    private final String f7389a;
    private final int f7390b;
    private final String f7391c;
    private final boolean f7392d;

    public CookieOrigin(String str, int i, String str2, boolean z) {
        Args.m12727b((CharSequence) str, "Host");
        Args.m12726b(i, "Port");
        Args.m12722a((Object) str2, "Path");
        this.f7389a = str.toLowerCase(Locale.ENGLISH);
        this.f7390b = i;
        if (str2.trim().length() != 0) {
            this.f7391c = str2;
        } else {
            this.f7391c = "/";
        }
        this.f7392d = z;
    }

    public String m11783a() {
        return this.f7389a;
    }

    public String m11784b() {
        return this.f7391c;
    }

    public int m11785c() {
        return this.f7390b;
    }

    public boolean m11786d() {
        return this.f7392d;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        if (this.f7392d) {
            stringBuilder.append("(secure)");
        }
        stringBuilder.append(this.f7389a);
        stringBuilder.append(':');
        stringBuilder.append(Integer.toString(this.f7390b));
        stringBuilder.append(this.f7391c);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}
