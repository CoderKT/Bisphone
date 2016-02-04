package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.cookie.ClientCookie;
import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.cookie.CookieAttributeHandler;
import cz.msebera.android.httpclient.cookie.CookieOrigin;
import cz.msebera.android.httpclient.cookie.MalformedCookieException;
import cz.msebera.android.httpclient.cookie.SetCookie;
import cz.msebera.android.httpclient.message.BufferedHeader;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

public class RFC2965Spec extends RFC2109Spec {
    public RFC2965Spec() {
        this(null, false);
    }

    public RFC2965Spec(String[] strArr, boolean z) {
        super(strArr, z);
        m12390a("domain", new RFC2965DomainAttributeHandler());
        m12390a("port", new RFC2965PortAttributeHandler());
        m12390a("commenturl", new RFC2965CommentUrlAttributeHandler());
        m12390a("discard", new RFC2965DiscardAttributeHandler());
        m12390a("version", new RFC2965VersionAttributeHandler());
    }

    public List<Cookie> m12501a(Header header, CookieOrigin cookieOrigin) {
        Args.m12722a((Object) header, "Header");
        Args.m12722a((Object) cookieOrigin, "Cookie origin");
        if (header.m11361c().equalsIgnoreCase("Set-Cookie2")) {
            return m12498b(header.m11363e(), m12499c(cookieOrigin));
        }
        throw new MalformedCookieException("Unrecognized cookie header '" + header.toString() + "'");
    }

    protected List<Cookie> m12502a(HeaderElement[] headerElementArr, CookieOrigin cookieOrigin) {
        return m12498b(headerElementArr, m12499c(cookieOrigin));
    }

    private List<Cookie> m12498b(HeaderElement[] headerElementArr, CookieOrigin cookieOrigin) {
        List<Cookie> arrayList = new ArrayList(headerElementArr.length);
        for (HeaderElement headerElement : headerElementArr) {
            String a = headerElement.m11368a();
            String b = headerElement.m11369b();
            if (a == null || a.length() == 0) {
                throw new MalformedCookieException("Cookie name may not be empty");
            }
            SetCookie basicClientCookie2 = new BasicClientCookie2(a, b);
            basicClientCookie2.m12406e(CookieSpecBase.m12437a(cookieOrigin));
            basicClientCookie2.m12404d(CookieSpecBase.m12438b(cookieOrigin));
            basicClientCookie2.m12410a(new int[]{cookieOrigin.m11785c()});
            NameValuePair[] c = headerElement.m11370c();
            Map hashMap = new HashMap(c.length);
            for (int length = c.length - 1; length >= 0; length--) {
                NameValuePair nameValuePair = c[length];
                hashMap.put(nameValuePair.m11403a().toLowerCase(Locale.ENGLISH), nameValuePair);
            }
            for (Entry value : hashMap.entrySet()) {
                NameValuePair nameValuePair2 = (NameValuePair) value.getValue();
                b = nameValuePair2.m11403a().toLowerCase(Locale.ENGLISH);
                basicClientCookie2.m12395a(b, nameValuePair2.m11404b());
                CookieAttributeHandler a2 = m12389a(b);
                if (a2 != null) {
                    a2.m11780a(basicClientCookie2, nameValuePair2.m11404b());
                }
            }
            arrayList.add(basicClientCookie2);
        }
        return arrayList;
    }

    public void m12503a(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.m12722a((Object) cookie, "Cookie");
        Args.m12722a((Object) cookieOrigin, "Cookie origin");
        super.m12475a(cookie, m12499c(cookieOrigin));
    }

    public boolean m12506b(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.m12722a((Object) cookie, "Cookie");
        Args.m12722a((Object) cookieOrigin, "Cookie origin");
        return super.m12441b(cookie, m12499c(cookieOrigin));
    }

    protected void m12504a(CharArrayBuffer charArrayBuffer, Cookie cookie, int i) {
        super.m12476a(charArrayBuffer, cookie, i);
        if (cookie instanceof ClientCookie) {
            String a = ((ClientCookie) cookie).m11777a("port");
            if (a != null) {
                charArrayBuffer.m12751a("; $Port");
                charArrayBuffer.m12751a("=\"");
                if (a.trim().length() > 0) {
                    int[] f = cookie.m11774f();
                    if (f != null) {
                        int length = f.length;
                        for (int i2 = 0; i2 < length; i2++) {
                            if (i2 > 0) {
                                charArrayBuffer.m12751a(",");
                            }
                            charArrayBuffer.m12751a(Integer.toString(f[i2]));
                        }
                    }
                }
                charArrayBuffer.m12751a("\"");
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static cz.msebera.android.httpclient.cookie.CookieOrigin m12499c(cz.msebera.android.httpclient.cookie.CookieOrigin r6) {
        /*
        r1 = 0;
        r3 = r6.m11783a();
        r2 = 1;
        r0 = r1;
    L_0x0007:
        r4 = r3.length();
        if (r0 >= r4) goto L_0x0044;
    L_0x000d:
        r4 = r3.charAt(r0);
        r5 = 46;
        if (r4 == r5) goto L_0x0019;
    L_0x0015:
        r5 = 58;
        if (r4 != r5) goto L_0x0041;
    L_0x0019:
        if (r1 == 0) goto L_0x0040;
    L_0x001b:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r0 = r0.append(r3);
        r1 = ".local";
        r0 = r0.append(r1);
        r1 = r0.toString();
        r0 = new cz.msebera.android.httpclient.cookie.CookieOrigin;
        r2 = r6.m11785c();
        r3 = r6.m11784b();
        r4 = r6.m11786d();
        r0.<init>(r1, r2, r3, r4);
        r6 = r0;
    L_0x0040:
        return r6;
    L_0x0041:
        r0 = r0 + 1;
        goto L_0x0007;
    L_0x0044:
        r1 = r2;
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.httpclient.impl.cookie.RFC2965Spec.c(cz.msebera.android.httpclient.cookie.CookieOrigin):cz.msebera.android.httpclient.cookie.CookieOrigin");
    }

    public int m12500a() {
        return 1;
    }

    public Header m12505b() {
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(40);
        charArrayBuffer.m12751a("Cookie2");
        charArrayBuffer.m12751a(": ");
        charArrayBuffer.m12751a("$Version=");
        charArrayBuffer.m12751a(Integer.toString(m12500a()));
        return new BufferedHeader(charArrayBuffer);
    }

    public String toString() {
        return "rfc2965";
    }
}
