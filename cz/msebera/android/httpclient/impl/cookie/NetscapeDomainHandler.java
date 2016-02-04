package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.cookie.CookieOrigin;
import cz.msebera.android.httpclient.cookie.CookieRestrictionViolationException;
import cz.msebera.android.httpclient.util.Args;
import java.util.Locale;
import java.util.StringTokenizer;

public class NetscapeDomainHandler extends BasicDomainHandler {
    public void m12457a(Cookie cookie, CookieOrigin cookieOrigin) {
        super.m12415a(cookie, cookieOrigin);
        String a = cookieOrigin.m11783a();
        String d = cookie.m11772d();
        if (a.contains(".")) {
            int countTokens = new StringTokenizer(d, ".").countTokens();
            if (m12456a(d)) {
                if (countTokens < 2) {
                    throw new CookieRestrictionViolationException("Domain attribute \"" + d + "\" violates the Netscape cookie specification for " + "special domains");
                }
            } else if (countTokens < 3) {
                throw new CookieRestrictionViolationException("Domain attribute \"" + d + "\" violates the Netscape cookie specification");
            }
        }
    }

    private static boolean m12456a(String str) {
        String toUpperCase = str.toUpperCase(Locale.ENGLISH);
        return toUpperCase.endsWith(".COM") || toUpperCase.endsWith(".EDU") || toUpperCase.endsWith(".NET") || toUpperCase.endsWith(".GOV") || toUpperCase.endsWith(".MIL") || toUpperCase.endsWith(".ORG") || toUpperCase.endsWith(".INT");
    }

    public boolean m12458b(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.m12722a((Object) cookie, "Cookie");
        Args.m12722a((Object) cookieOrigin, "Cookie origin");
        String a = cookieOrigin.m11783a();
        String d = cookie.m11772d();
        if (d == null) {
            return false;
        }
        return a.endsWith(d);
    }
}
