package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.cookie.CookieAttributeHandler;
import cz.msebera.android.httpclient.cookie.CookieOrigin;
import cz.msebera.android.httpclient.cookie.MalformedCookieException;
import cz.msebera.android.httpclient.cookie.SetCookie;
import cz.msebera.android.httpclient.util.Args;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class CookieSpecBase extends AbstractCookieSpec {
    protected static String m12437a(CookieOrigin cookieOrigin) {
        String b = cookieOrigin.m11784b();
        int lastIndexOf = b.lastIndexOf(47);
        if (lastIndexOf < 0) {
            return b;
        }
        if (lastIndexOf == 0) {
            lastIndexOf = 1;
        }
        return b.substring(0, lastIndexOf);
    }

    protected static String m12438b(CookieOrigin cookieOrigin) {
        return cookieOrigin.m11783a();
    }

    protected List<Cookie> m12439a(HeaderElement[] headerElementArr, CookieOrigin cookieOrigin) {
        List<Cookie> arrayList = new ArrayList(headerElementArr.length);
        for (HeaderElement headerElement : headerElementArr) {
            String a = headerElement.m11368a();
            String b = headerElement.m11369b();
            if (a == null || a.length() == 0) {
                throw new MalformedCookieException("Cookie name may not be empty");
            }
            SetCookie basicClientCookie = new BasicClientCookie(a, b);
            basicClientCookie.m12406e(m12437a(cookieOrigin));
            basicClientCookie.m12404d(m12438b(cookieOrigin));
            NameValuePair[] c = headerElement.m11370c();
            for (int length = c.length - 1; length >= 0; length--) {
                NameValuePair nameValuePair = c[length];
                String toLowerCase = nameValuePair.m11403a().toLowerCase(Locale.ENGLISH);
                basicClientCookie.m12395a(toLowerCase, nameValuePair.m11404b());
                CookieAttributeHandler a2 = m12389a(toLowerCase);
                if (a2 != null) {
                    a2.m11780a(basicClientCookie, nameValuePair.m11404b());
                }
            }
            arrayList.add(basicClientCookie);
        }
        return arrayList;
    }

    public void m12440a(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.m12722a((Object) cookie, "Cookie");
        Args.m12722a((Object) cookieOrigin, "Cookie origin");
        for (CookieAttributeHandler a : m12391c()) {
            a.m11779a(cookie, cookieOrigin);
        }
    }

    public boolean m12441b(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.m12722a((Object) cookie, "Cookie");
        Args.m12722a((Object) cookieOrigin, "Cookie origin");
        for (CookieAttributeHandler b : m12391c()) {
            if (!b.m11781b(cookie, cookieOrigin)) {
                return false;
            }
        }
        return true;
    }
}
