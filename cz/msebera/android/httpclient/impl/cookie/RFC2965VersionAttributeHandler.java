package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.cookie.ClientCookie;
import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.cookie.CookieAttributeHandler;
import cz.msebera.android.httpclient.cookie.CookieOrigin;
import cz.msebera.android.httpclient.cookie.CookieRestrictionViolationException;
import cz.msebera.android.httpclient.cookie.MalformedCookieException;
import cz.msebera.android.httpclient.cookie.SetCookie;
import cz.msebera.android.httpclient.cookie.SetCookie2;
import cz.msebera.android.httpclient.util.Args;

public class RFC2965VersionAttributeHandler implements CookieAttributeHandler {
    public void m12510a(SetCookie setCookie, String str) {
        Args.m12722a((Object) setCookie, "Cookie");
        if (str == null) {
            throw new MalformedCookieException("Missing value for version attribute");
        }
        int parseInt;
        try {
            parseInt = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            parseInt = -1;
        }
        if (parseInt < 0) {
            throw new MalformedCookieException("Invalid cookie version.");
        }
        setCookie.m11802a(parseInt);
    }

    public void m12509a(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.m12722a((Object) cookie, "Cookie");
        if ((cookie instanceof SetCookie2) && (cookie instanceof ClientCookie) && !((ClientCookie) cookie).m11778b("version")) {
            throw new CookieRestrictionViolationException("Violates RFC 2965. Version attribute is required.");
        }
    }

    public boolean m12511b(Cookie cookie, CookieOrigin cookieOrigin) {
        return true;
    }
}
