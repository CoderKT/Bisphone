package cz.msebera.android.httpclient.cookie;

import java.io.Serializable;
import java.util.Comparator;

public class CookiePathComparator implements Serializable, Comparator<Cookie> {
    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m11788a((Cookie) obj, (Cookie) obj2);
    }

    private String m11787a(Cookie cookie) {
        String e = cookie.m11773e();
        if (e == null) {
            e = "/";
        }
        if (e.endsWith("/")) {
            return e;
        }
        return e + '/';
    }

    public int m11788a(Cookie cookie, Cookie cookie2) {
        String a = m11787a(cookie);
        String a2 = m11787a(cookie2);
        if (a.equals(a2)) {
            return 0;
        }
        if (a.startsWith(a2)) {
            return -1;
        }
        if (a2.startsWith(a)) {
            return 1;
        }
        return 0;
    }
}
