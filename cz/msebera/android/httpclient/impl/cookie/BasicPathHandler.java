package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.cookie.CookieAttributeHandler;
import cz.msebera.android.httpclient.cookie.CookieOrigin;
import cz.msebera.android.httpclient.cookie.CookieRestrictionViolationException;
import cz.msebera.android.httpclient.cookie.SetCookie;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.TextUtils;

public class BasicPathHandler implements CookieAttributeHandler {
    public void m12421a(SetCookie setCookie, String str) {
        Args.m12722a((Object) setCookie, "Cookie");
        if (TextUtils.m12772b(str)) {
            str = "/";
        }
        setCookie.m11807e(str);
    }

    public void m12420a(Cookie cookie, CookieOrigin cookieOrigin) {
        if (!m12422b(cookie, cookieOrigin)) {
            throw new CookieRestrictionViolationException("Illegal path attribute \"" + cookie.m11773e() + "\". Path of origin: \"" + cookieOrigin.m11784b() + "\"");
        }
    }

    public boolean m12422b(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.m12722a((Object) cookie, "Cookie");
        Args.m12722a((Object) cookieOrigin, "Cookie origin");
        String b = cookieOrigin.m11784b();
        String e = cookie.m11773e();
        if (e == null) {
            e = "/";
        }
        if (e.length() > 1 && e.endsWith("/")) {
            e = e.substring(0, e.length() - 1);
        }
        boolean startsWith = b.startsWith(e);
        if (!startsWith || b.length() == e.length() || e.endsWith("/")) {
            return startsWith;
        }
        if (b.charAt(e.length()) == '/') {
            return true;
        }
        return false;
    }
}
