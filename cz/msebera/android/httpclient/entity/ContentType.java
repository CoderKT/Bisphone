package cz.msebera.android.httpclient.entity;

import cz.msebera.android.httpclient.Consts;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicHeaderValueFormatter;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import cz.msebera.android.httpclient.util.TextUtils;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Locale;

public final class ContentType implements Serializable {
    public static final ContentType f7399a;
    public static final ContentType f7400b;
    public static final ContentType f7401c;
    public static final ContentType f7402d;
    public static final ContentType f7403e;
    public static final ContentType f7404f;
    public static final ContentType f7405g;
    public static final ContentType f7406h;
    public static final ContentType f7407i;
    public static final ContentType f7408j;
    public static final ContentType f7409k;
    public static final ContentType f7410l;
    public static final ContentType f7411m;
    public static final ContentType f7412n;
    private final String f7413o;
    private final Charset f7414p;
    private final NameValuePair[] f7415q;

    static {
        f7399a = m11825a("application/atom+xml", Consts.f7199c);
        f7400b = m11825a("application/x-www-form-urlencoded", Consts.f7199c);
        f7401c = m11825a("application/json", Consts.f7197a);
        f7402d = m11825a("application/octet-stream", (Charset) null);
        f7403e = m11825a("application/svg+xml", Consts.f7199c);
        f7404f = m11825a("application/xhtml+xml", Consts.f7199c);
        f7405g = m11825a("application/xml", Consts.f7199c);
        f7406h = m11825a("multipart/form-data", Consts.f7199c);
        f7407i = m11825a("text/html", Consts.f7199c);
        f7408j = m11825a("text/plain", Consts.f7199c);
        f7409k = m11825a("text/xml", Consts.f7199c);
        f7410l = m11825a("*/*", (Charset) null);
        f7411m = f7408j;
        f7412n = f7402d;
    }

    ContentType(String str, Charset charset) {
        this.f7413o = str;
        this.f7414p = charset;
        this.f7415q = null;
    }

    public Charset m11827a() {
        return this.f7414p;
    }

    public String toString() {
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(64);
        charArrayBuffer.m12751a(this.f7413o);
        if (this.f7415q != null) {
            charArrayBuffer.m12751a("; ");
            BasicHeaderValueFormatter.f7863b.m12581a(charArrayBuffer, this.f7415q, false);
        } else if (this.f7414p != null) {
            charArrayBuffer.m12751a("; charset=");
            charArrayBuffer.m12751a(this.f7414p.name());
        }
        return charArrayBuffer.toString();
    }

    private static boolean m11826a(String str) {
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '\"' || charAt == ',' || charAt == ';') {
                return false;
            }
        }
        return true;
    }

    public static ContentType m11825a(String str, Charset charset) {
        String toLowerCase = ((String) Args.m12727b((CharSequence) str, "MIME type")).toLowerCase(Locale.ENGLISH);
        Args.m12724a(m11826a(toLowerCase), "MIME type may not contain reserved characters");
        return new ContentType(toLowerCase, charset);
    }

    public static ContentType m11824a(String str, String str2) {
        return m11825a(str, !TextUtils.m12772b(str2) ? Charset.forName(str2) : null);
    }
}
