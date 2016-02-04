package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.cookie.ClientCookie;
import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.cookie.CookieAttributeHandler;
import cz.msebera.android.httpclient.cookie.CookieOrigin;
import cz.msebera.android.httpclient.cookie.CookieRestrictionViolationException;
import cz.msebera.android.httpclient.cookie.MalformedCookieException;
import cz.msebera.android.httpclient.cookie.SetCookie;
import cz.msebera.android.httpclient.util.Args;
import java.util.Locale;

public class RFC2965DomainAttributeHandler implements CookieAttributeHandler {
    public void m12490a(SetCookie setCookie, String str) {
        Args.m12722a((Object) setCookie, "Cookie");
        if (str == null) {
            throw new MalformedCookieException("Missing value for domain attribute");
        } else if (str.trim().length() == 0) {
            throw new MalformedCookieException("Blank value for domain attribute");
        } else {
            String toLowerCase = str.toLowerCase(Locale.ENGLISH);
            if (!str.startsWith(".")) {
                toLowerCase = '.' + toLowerCase;
            }
            setCookie.m11806d(toLowerCase);
        }
    }

    public boolean m12491a(String str, String str2) {
        return str.equals(str2) || (str2.startsWith(".") && str.endsWith(str2));
    }

    public void m12489a(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.m12722a((Object) cookie, "Cookie");
        Args.m12722a((Object) cookieOrigin, "Cookie origin");
        String toLowerCase = cookieOrigin.m11783a().toLowerCase(Locale.ENGLISH);
        if (cookie.m11772d() == null) {
            throw new CookieRestrictionViolationException("Invalid cookie state: domain not specified");
        }
        String toLowerCase2 = cookie.m11772d().toLowerCase(Locale.ENGLISH);
        if ((cookie instanceof ClientCookie) && ((ClientCookie) cookie).m11778b("domain")) {
            if (toLowerCase2.startsWith(".")) {
                int indexOf = toLowerCase2.indexOf(46, 1);
                if ((indexOf < 0 || indexOf == toLowerCase2.length() - 1) && !toLowerCase2.equals(".local")) {
                    throw new CookieRestrictionViolationException("Domain attribute \"" + cookie.m11772d() + "\" violates RFC 2965: the value contains no embedded dots " + "and the value is not .local");
                } else if (!m12491a(toLowerCase, toLowerCase2)) {
                    throw new CookieRestrictionViolationException("Domain attribute \"" + cookie.m11772d() + "\" violates RFC 2965: effective host name does not " + "domain-match domain attribute.");
                } else if (toLowerCase.substring(0, toLowerCase.length() - toLowerCase2.length()).indexOf(46) != -1) {
                    throw new CookieRestrictionViolationException("Domain attribute \"" + cookie.m11772d() + "\" violates RFC 2965: " + "effective host minus domain may not contain any dots");
                } else {
                    return;
                }
            }
            throw new CookieRestrictionViolationException("Domain attribute \"" + cookie.m11772d() + "\" violates RFC 2109: domain must start with a dot");
        } else if (!cookie.m11772d().equals(toLowerCase)) {
            throw new CookieRestrictionViolationException("Illegal domain attribute: \"" + cookie.m11772d() + "\"." + "Domain of origin: \"" + toLowerCase + "\"");
        }
    }

    public boolean m12492b(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.m12722a((Object) cookie, "Cookie");
        Args.m12722a((Object) cookieOrigin, "Cookie origin");
        String toLowerCase = cookieOrigin.m11783a().toLowerCase(Locale.ENGLISH);
        String d = cookie.m11772d();
        if (m12491a(toLowerCase, d) && toLowerCase.substring(0, toLowerCase.length() - d.length()).indexOf(46) == -1) {
            return true;
        }
        return false;
    }
}
