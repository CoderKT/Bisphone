package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.FormattedHeader;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.cookie.CookieOrigin;
import cz.msebera.android.httpclient.cookie.CookieSpec;
import cz.msebera.android.httpclient.cookie.MalformedCookieException;
import cz.msebera.android.httpclient.cookie.SetCookie2;
import cz.msebera.android.httpclient.message.ParserCursor;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.util.List;

public class BestMatchSpec implements CookieSpec {
    private final String[] f7761a;
    private final boolean f7762b;
    private RFC2965Spec f7763c;
    private RFC2109Spec f7764d;
    private BrowserCompatSpec f7765e;

    public BestMatchSpec(String[] strArr, boolean z) {
        this.f7761a = strArr == null ? null : (String[]) strArr.clone();
        this.f7762b = z;
    }

    public BestMatchSpec() {
        this(null, false);
    }

    private RFC2965Spec m12425c() {
        if (this.f7763c == null) {
            this.f7763c = new RFC2965Spec(this.f7761a, this.f7762b);
        }
        return this.f7763c;
    }

    private RFC2109Spec m12426d() {
        if (this.f7764d == null) {
            this.f7764d = new RFC2109Spec(this.f7761a, this.f7762b);
        }
        return this.f7764d;
    }

    private BrowserCompatSpec m12427e() {
        if (this.f7765e == null) {
            this.f7765e = new BrowserCompatSpec(this.f7761a);
        }
        return this.f7765e;
    }

    public List<Cookie> m12429a(Header header, CookieOrigin cookieOrigin) {
        Args.m12722a((Object) header, "Header");
        Args.m12722a((Object) cookieOrigin, "Cookie origin");
        HeaderElement[] e = header.m11363e();
        int i = 0;
        int i2 = 0;
        for (HeaderElement headerElement : e) {
            if (headerElement.m11367a("version") != null) {
                i2 = 1;
            }
            if (headerElement.m11367a("expires") != null) {
                i = 1;
            }
        }
        if (i != 0 || r0 == 0) {
            CharArrayBuffer a;
            ParserCursor parserCursor;
            NetscapeDraftHeaderParser netscapeDraftHeaderParser = NetscapeDraftHeaderParser.f7777a;
            if (header instanceof FormattedHeader) {
                a = ((FormattedHeader) header).m11364a();
                parserCursor = new ParserCursor(((FormattedHeader) header).m11365b(), a.m12757c());
            } else {
                String d = header.m11362d();
                if (d == null) {
                    throw new MalformedCookieException("Header value is null");
                }
                a = new CharArrayBuffer(d.length());
                a.m12751a(d);
                parserCursor = new ParserCursor(0, a.m12757c());
            }
            return m12427e().m12439a(new HeaderElement[]{netscapeDraftHeaderParser.m12460a(a, parserCursor)}, cookieOrigin);
        } else if ("Set-Cookie2".equals(header.m11361c())) {
            return m12425c().m12502a(e, cookieOrigin);
        } else {
            return m12426d().m12439a(e, cookieOrigin);
        }
    }

    public void m12431a(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.m12722a((Object) cookie, "Cookie");
        Args.m12722a((Object) cookieOrigin, "Cookie origin");
        if (cookie.m11776h() <= 0) {
            m12427e().m12440a(cookie, cookieOrigin);
        } else if (cookie instanceof SetCookie2) {
            m12425c().m12503a(cookie, cookieOrigin);
        } else {
            m12426d().m12475a(cookie, cookieOrigin);
        }
    }

    public boolean m12433b(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.m12722a((Object) cookie, "Cookie");
        Args.m12722a((Object) cookieOrigin, "Cookie origin");
        if (cookie.m11776h() <= 0) {
            return m12427e().m12441b(cookie, cookieOrigin);
        }
        if (cookie instanceof SetCookie2) {
            return m12425c().m12506b(cookie, cookieOrigin);
        }
        return m12426d().m12441b(cookie, cookieOrigin);
    }

    public List<Header> m12430a(List<Cookie> list) {
        Args.m12722a((Object) list, "List of cookies");
        int i = Integer.MAX_VALUE;
        Object obj = 1;
        for (Cookie cookie : list) {
            int h;
            if (!(cookie instanceof SetCookie2)) {
                obj = null;
            }
            if (cookie.m11776h() < i) {
                h = cookie.m11776h();
            } else {
                h = i;
            }
            i = h;
        }
        if (i <= 0) {
            return m12427e().m12445a(list);
        }
        if (obj != null) {
            return m12425c().m12474a(list);
        }
        return m12426d().m12474a(list);
    }

    public int m12428a() {
        return m12425c().m12500a();
    }

    public Header m12432b() {
        return m12425c().m12505b();
    }

    public String toString() {
        return "best-match";
    }
}
