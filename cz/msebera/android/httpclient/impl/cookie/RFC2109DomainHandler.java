package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.cookie.CookieAttributeHandler;
import cz.msebera.android.httpclient.cookie.CookieOrigin;
import cz.msebera.android.httpclient.cookie.CookieRestrictionViolationException;
import cz.msebera.android.httpclient.cookie.MalformedCookieException;
import cz.msebera.android.httpclient.cookie.SetCookie;
import cz.msebera.android.httpclient.util.Args;
import java.util.Locale;

public class RFC2109DomainHandler implements CookieAttributeHandler {
    public void m12468a(SetCookie setCookie, String str) {
        Args.m12722a((Object) setCookie, "Cookie");
        if (str == null) {
            throw new MalformedCookieException("Missing value for domain attribute");
        } else if (str.trim().length() == 0) {
            throw new MalformedCookieException("Blank value for domain attribute");
        } else {
            setCookie.m11806d(str);
        }
    }

    public void m12467a(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.m12722a((Object) cookie, "Cookie");
        Args.m12722a((Object) cookieOrigin, "Cookie origin");
        String a = cookieOrigin.m11783a();
        String d = cookie.m11772d();
        if (d == null) {
            throw new CookieRestrictionViolationException("Cookie domain may not be null");
        } else if (!d.equals(a)) {
            if (d.indexOf(46) == -1) {
                throw new CookieRestrictionViolationException("Domain attribute \"" + d + "\" does not match the host \"" + a + "\"");
            } else if (d.startsWith(".")) {
                int indexOf = d.indexOf(46, 1);
                if (indexOf < 0 || indexOf == d.length() - 1) {
                    throw new CookieRestrictionViolationException("Domain attribute \"" + d + "\" violates RFC 2109: domain must contain an embedded dot");
                }
                a = a.toLowerCase(Locale.ENGLISH);
                if (!a.endsWith(d)) {
                    throw new CookieRestrictionViolationException("Illegal domain attribute \"" + d + "\". Domain of origin: \"" + a + "\"");
                } else if (a.substring(0, a.length() - d.length()).indexOf(46) != -1) {
                    throw new CookieRestrictionViolationException("Domain attribute \"" + d + "\" violates RFC 2109: host minus domain may not contain any dots");
                }
            } else {
                throw new CookieRestrictionViolationException("Domain attribute \"" + d + "\" violates RFC 2109: domain must start with a dot");
            }
        }
    }

    public boolean m12469b(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.m12722a((Object) cookie, "Cookie");
        Args.m12722a((Object) cookieOrigin, "Cookie origin");
        String a = cookieOrigin.m11783a();
        String d = cookie.m11772d();
        if (d == null) {
            return false;
        }
        if (a.equals(d) || (d.startsWith(".") && a.endsWith(d))) {
            return true;
        }
        return false;
    }
}
