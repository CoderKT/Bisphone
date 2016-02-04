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
import java.util.StringTokenizer;

public class RFC2965PortAttributeHandler implements CookieAttributeHandler {
    private static int[] m12494a(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
        int[] iArr = new int[stringTokenizer.countTokens()];
        int i = 0;
        while (stringTokenizer.hasMoreTokens()) {
            try {
                iArr[i] = Integer.parseInt(stringTokenizer.nextToken().trim());
                if (iArr[i] < 0) {
                    throw new MalformedCookieException("Invalid Port attribute.");
                }
                i++;
            } catch (NumberFormatException e) {
                throw new MalformedCookieException("Invalid Port attribute: " + e.getMessage());
            }
        }
        return iArr;
    }

    private static boolean m12493a(int i, int[] iArr) {
        for (int i2 : iArr) {
            if (i == i2) {
                return true;
            }
        }
        return false;
    }

    public void m12496a(SetCookie setCookie, String str) {
        Args.m12722a((Object) setCookie, "Cookie");
        if (setCookie instanceof SetCookie2) {
            SetCookie2 setCookie2 = (SetCookie2) setCookie;
            if (str != null && str.trim().length() > 0) {
                setCookie2.m11808a(m12494a(str));
            }
        }
    }

    public void m12495a(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.m12722a((Object) cookie, "Cookie");
        Args.m12722a((Object) cookieOrigin, "Cookie origin");
        int c = cookieOrigin.m11785c();
        if ((cookie instanceof ClientCookie) && ((ClientCookie) cookie).m11778b("port") && !m12493a(c, cookie.m11774f())) {
            throw new CookieRestrictionViolationException("Port attribute violates RFC 2965: Request port not found in cookie's port list.");
        }
    }

    public boolean m12497b(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.m12722a((Object) cookie, "Cookie");
        Args.m12722a((Object) cookieOrigin, "Cookie origin");
        int c = cookieOrigin.m11785c();
        if ((cookie instanceof ClientCookie) && ((ClientCookie) cookie).m11778b("port")) {
            if (cookie.m11774f() == null) {
                return false;
            }
            if (!m12493a(c, cookie.m11774f())) {
                return false;
            }
        }
        return true;
    }
}
