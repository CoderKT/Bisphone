package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.cookie.ClientCookie;
import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.cookie.CookieOrigin;
import cz.msebera.android.httpclient.cookie.CookiePathComparator;
import cz.msebera.android.httpclient.cookie.CookieRestrictionViolationException;
import cz.msebera.android.httpclient.cookie.MalformedCookieException;
import cz.msebera.android.httpclient.message.BufferedHeader;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RFC2109Spec extends CookieSpecBase {
    private static final CookiePathComparator f7780a;
    private static final String[] f7781b;
    private final String[] f7782c;
    private final boolean f7783d;

    static {
        f7780a = new CookiePathComparator();
        f7781b = new String[]{"EEE, dd MMM yyyy HH:mm:ss zzz", "EEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy"};
    }

    public RFC2109Spec(String[] strArr, boolean z) {
        if (strArr != null) {
            this.f7782c = (String[]) strArr.clone();
        } else {
            this.f7782c = f7781b;
        }
        this.f7783d = z;
        m12390a("version", new RFC2109VersionHandler());
        m12390a("path", new BasicPathHandler());
        m12390a("domain", new RFC2109DomainHandler());
        m12390a("max-age", new BasicMaxAgeHandler());
        m12390a("secure", new BasicSecureHandler());
        m12390a("comment", new BasicCommentHandler());
        m12390a("expires", new BasicExpiresHandler(this.f7782c));
    }

    public RFC2109Spec() {
        this(null, false);
    }

    public List<Cookie> m12473a(Header header, CookieOrigin cookieOrigin) {
        Args.m12722a((Object) header, "Header");
        Args.m12722a((Object) cookieOrigin, "Cookie origin");
        if (header.m11361c().equalsIgnoreCase("Set-Cookie")) {
            return m12439a(header.m11363e(), cookieOrigin);
        }
        throw new MalformedCookieException("Unrecognized cookie header '" + header.toString() + "'");
    }

    public void m12475a(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.m12722a((Object) cookie, "Cookie");
        String a = cookie.m11768a();
        if (a.indexOf(32) != -1) {
            throw new CookieRestrictionViolationException("Cookie name may not contain blanks");
        } else if (a.startsWith("$")) {
            throw new CookieRestrictionViolationException("Cookie name may not start with $");
        } else {
            super.m12440a(cookie, cookieOrigin);
        }
    }

    public List<Header> m12474a(List<Cookie> list) {
        Args.m12723a((Collection) list, "List of cookies");
        if (list.size() > 1) {
            List<Cookie> arrayList = new ArrayList(list);
            Collections.sort(arrayList, f7780a);
            list = arrayList;
        }
        if (this.f7783d) {
            return m12470b(list);
        }
        return m12471c(list);
    }

    private List<Header> m12470b(List<Cookie> list) {
        int i = Integer.MAX_VALUE;
        for (Cookie cookie : list) {
            int h;
            if (cookie.m11776h() < i) {
                h = cookie.m11776h();
            } else {
                h = i;
            }
            i = h;
        }
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(list.size() * 40);
        charArrayBuffer.m12751a("Cookie");
        charArrayBuffer.m12751a(": ");
        charArrayBuffer.m12751a("$Version=");
        charArrayBuffer.m12751a(Integer.toString(i));
        for (Cookie cookie2 : list) {
            charArrayBuffer.m12751a("; ");
            m12476a(charArrayBuffer, cookie2, i);
        }
        List<Header> arrayList = new ArrayList(1);
        arrayList.add(new BufferedHeader(charArrayBuffer));
        return arrayList;
    }

    private List<Header> m12471c(List<Cookie> list) {
        List<Header> arrayList = new ArrayList(list.size());
        for (Cookie cookie : list) {
            int h = cookie.m11776h();
            CharArrayBuffer charArrayBuffer = new CharArrayBuffer(40);
            charArrayBuffer.m12751a("Cookie: ");
            charArrayBuffer.m12751a("$Version=");
            charArrayBuffer.m12751a(Integer.toString(h));
            charArrayBuffer.m12751a("; ");
            m12476a(charArrayBuffer, cookie, h);
            arrayList.add(new BufferedHeader(charArrayBuffer));
        }
        return arrayList;
    }

    protected void m12477a(CharArrayBuffer charArrayBuffer, String str, String str2, int i) {
        charArrayBuffer.m12751a(str);
        charArrayBuffer.m12751a("=");
        if (str2 == null) {
            return;
        }
        if (i > 0) {
            charArrayBuffer.m12748a('\"');
            charArrayBuffer.m12751a(str2);
            charArrayBuffer.m12748a('\"');
            return;
        }
        charArrayBuffer.m12751a(str2);
    }

    protected void m12476a(CharArrayBuffer charArrayBuffer, Cookie cookie, int i) {
        m12477a(charArrayBuffer, cookie.m11768a(), cookie.m11770b(), i);
        if (cookie.m11773e() != null && (cookie instanceof ClientCookie) && ((ClientCookie) cookie).m11778b("path")) {
            charArrayBuffer.m12751a("; ");
            m12477a(charArrayBuffer, "$Path", cookie.m11773e(), i);
        }
        if (cookie.m11772d() != null && (cookie instanceof ClientCookie) && ((ClientCookie) cookie).m11778b("domain")) {
            charArrayBuffer.m12751a("; ");
            m12477a(charArrayBuffer, "$Domain", cookie.m11772d(), i);
        }
    }

    public int m12472a() {
        return 1;
    }

    public Header m12478b() {
        return null;
    }

    public String toString() {
        return "rfc2109";
    }
}
