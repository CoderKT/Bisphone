package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.cookie.CookieOrigin;
import cz.msebera.android.httpclient.cookie.SetCookie;
import cz.msebera.android.httpclient.util.Args;

public class BasicSecureHandler extends AbstractCookieAttributeHandler {
    public void m12423a(SetCookie setCookie, String str) {
        Args.m12722a((Object) setCookie, "Cookie");
        setCookie.m11803a(true);
    }

    public boolean m12424b(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.m12722a((Object) cookie, "Cookie");
        Args.m12722a((Object) cookieOrigin, "Cookie origin");
        return !cookie.m11775g() || cookieOrigin.m11786d();
    }
}
