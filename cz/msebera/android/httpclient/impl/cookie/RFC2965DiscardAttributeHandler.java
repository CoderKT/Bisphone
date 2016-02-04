package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.cookie.CookieAttributeHandler;
import cz.msebera.android.httpclient.cookie.CookieOrigin;
import cz.msebera.android.httpclient.cookie.SetCookie;
import cz.msebera.android.httpclient.cookie.SetCookie2;

public class RFC2965DiscardAttributeHandler implements CookieAttributeHandler {
    public void m12487a(SetCookie setCookie, String str) {
        if (setCookie instanceof SetCookie2) {
            ((SetCookie2) setCookie).m11809b(true);
        }
    }

    public void m12486a(Cookie cookie, CookieOrigin cookieOrigin) {
    }

    public boolean m12488b(Cookie cookie, CookieOrigin cookieOrigin) {
        return true;
    }
}
