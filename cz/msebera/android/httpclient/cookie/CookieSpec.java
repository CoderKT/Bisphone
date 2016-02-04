package cz.msebera.android.httpclient.cookie;

import cz.msebera.android.httpclient.Header;
import java.util.List;

public interface CookieSpec {
    int m11789a();

    List<Cookie> m11790a(Header header, CookieOrigin cookieOrigin);

    List<Header> m11791a(List<Cookie> list);

    void m11792a(Cookie cookie, CookieOrigin cookieOrigin);

    Header m11793b();

    boolean m11794b(Cookie cookie, CookieOrigin cookieOrigin);
}
