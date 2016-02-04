package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.cookie.CookieAttributeHandler;
import cz.msebera.android.httpclient.cookie.CookieOrigin;
import cz.msebera.android.httpclient.cookie.CookieRestrictionViolationException;
import cz.msebera.android.httpclient.cookie.MalformedCookieException;
import cz.msebera.android.httpclient.cookie.SetCookie;
import cz.msebera.android.httpclient.util.Args;

public class BasicDomainHandler implements CookieAttributeHandler {
    public void m12416a(SetCookie setCookie, String str) {
        Args.m12722a((Object) setCookie, "Cookie");
        if (str == null) {
            throw new MalformedCookieException("Missing value for domain attribute");
        } else if (str.trim().length() == 0) {
            throw new MalformedCookieException("Blank value for domain attribute");
        } else {
            setCookie.m11806d(str);
        }
    }

    public void m12415a(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.m12722a((Object) cookie, "Cookie");
        Args.m12722a((Object) cookieOrigin, "Cookie origin");
        String a = cookieOrigin.m11783a();
        String d = cookie.m11772d();
        if (d == null) {
            throw new CookieRestrictionViolationException("Cookie domain may not be null");
        } else if (a.contains(".")) {
            if (!a.endsWith(d)) {
                if (d.startsWith(".")) {
                    d = d.substring(1, d.length());
                }
                if (!a.equals(d)) {
                    throw new CookieRestrictionViolationException("Illegal domain attribute \"" + d + "\". Domain of origin: \"" + a + "\"");
                }
            }
        } else if (!a.equals(d)) {
            throw new CookieRestrictionViolationException("Illegal domain attribute \"" + d + "\". Domain of origin: \"" + a + "\"");
        }
    }

    public boolean m12417b(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.m12722a((Object) cookie, "Cookie");
        Args.m12722a((Object) cookieOrigin, "Cookie origin");
        String a = cookieOrigin.m11783a();
        String d = cookie.m11772d();
        if (d == null) {
            return false;
        }
        if (a.equals(d)) {
            return true;
        }
        if (!d.startsWith(".")) {
            d = '.' + d;
        }
        boolean z = a.endsWith(d) || a.equals(d.substring(1));
        return z;
    }
}
