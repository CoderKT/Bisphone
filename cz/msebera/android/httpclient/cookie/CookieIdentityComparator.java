package cz.msebera.android.httpclient.cookie;

import java.io.Serializable;
import java.util.Comparator;

public class CookieIdentityComparator implements Serializable, Comparator<Cookie> {
    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m11782a((Cookie) obj, (Cookie) obj2);
    }

    public int m11782a(Cookie cookie, Cookie cookie2) {
        String d;
        String d2;
        int compareTo = cookie.m11768a().compareTo(cookie2.m11768a());
        if (compareTo == 0) {
            d = cookie.m11772d();
            if (d == null) {
                d = "";
            } else if (d.indexOf(46) == -1) {
                d = d + ".local";
            }
            d2 = cookie2.m11772d();
            if (d2 == null) {
                d2 = "";
            } else if (d2.indexOf(46) == -1) {
                d2 = d2 + ".local";
            }
            compareTo = d.compareToIgnoreCase(d2);
        }
        if (compareTo != 0) {
            return compareTo;
        }
        d = cookie.m11773e();
        if (d == null) {
            d = "/";
        }
        d2 = cookie2.m11773e();
        if (d2 == null) {
            d2 = "/";
        }
        return d.compareTo(d2);
    }
}
