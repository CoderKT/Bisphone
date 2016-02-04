package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.FormattedHeader;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.cookie.CookieOrigin;
import cz.msebera.android.httpclient.cookie.MalformedCookieException;
import cz.msebera.android.httpclient.message.BufferedHeader;
import cz.msebera.android.httpclient.message.ParserCursor;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NetscapeDraftSpec extends CookieSpecBase {
    private final String[] f7778a;

    public NetscapeDraftSpec(String[] strArr) {
        if (strArr != null) {
            this.f7778a = (String[]) strArr.clone();
        } else {
            this.f7778a = new String[]{"EEE, dd-MMM-yy HH:mm:ss z"};
        }
        m12390a("path", new BasicPathHandler());
        m12390a("domain", new NetscapeDomainHandler());
        m12390a("secure", new BasicSecureHandler());
        m12390a("comment", new BasicCommentHandler());
        m12390a("expires", new BasicExpiresHandler(this.f7778a));
    }

    public NetscapeDraftSpec() {
        this(null);
    }

    public List<Cookie> m12462a(Header header, CookieOrigin cookieOrigin) {
        Args.m12722a((Object) header, "Header");
        Args.m12722a((Object) cookieOrigin, "Cookie origin");
        if (header.m11361c().equalsIgnoreCase("Set-Cookie")) {
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
            return m12439a(new HeaderElement[]{netscapeDraftHeaderParser.m12460a(a, parserCursor)}, cookieOrigin);
        }
        throw new MalformedCookieException("Unrecognized cookie header '" + header.toString() + "'");
    }

    public List<Header> m12463a(List<Cookie> list) {
        Args.m12723a((Collection) list, "List of cookies");
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(list.size() * 20);
        charArrayBuffer.m12751a("Cookie");
        charArrayBuffer.m12751a(": ");
        for (int i = 0; i < list.size(); i++) {
            Cookie cookie = (Cookie) list.get(i);
            if (i > 0) {
                charArrayBuffer.m12751a("; ");
            }
            charArrayBuffer.m12751a(cookie.m11768a());
            String b = cookie.m11770b();
            if (b != null) {
                charArrayBuffer.m12751a("=");
                charArrayBuffer.m12751a(b);
            }
        }
        List<Header> arrayList = new ArrayList(1);
        arrayList.add(new BufferedHeader(charArrayBuffer));
        return arrayList;
    }

    public int m12461a() {
        return 0;
    }

    public Header m12464b() {
        return null;
    }

    public String toString() {
        return "netscape";
    }
}
