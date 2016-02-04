package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.cookie.CookieOrigin;
import cz.msebera.android.httpclient.cookie.CookieRestrictionViolationException;
import cz.msebera.android.httpclient.cookie.MalformedCookieException;
import cz.msebera.android.httpclient.cookie.SetCookie;
import cz.msebera.android.httpclient.util.Args;

public class RFC2109VersionHandler extends AbstractCookieAttributeHandler {
    public void m12482a(SetCookie setCookie, String str) {
        Args.m12722a((Object) setCookie, "Cookie");
        if (str == null) {
            throw new MalformedCookieException("Missing value for version attribute");
        } else if (str.trim().length() == 0) {
            throw new MalformedCookieException("Blank value for version attribute");
        } else {
            try {
                setCookie.m11802a(Integer.parseInt(str));
            } catch (NumberFormatException e) {
                throw new MalformedCookieException("Invalid version: " + e.getMessage());
            }
        }
    }

    public void m12481a(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.m12722a((Object) cookie, "Cookie");
        if (cookie.m11776h() < 0) {
            throw new CookieRestrictionViolationException("Cookie version may not be negative");
        }
    }
}
