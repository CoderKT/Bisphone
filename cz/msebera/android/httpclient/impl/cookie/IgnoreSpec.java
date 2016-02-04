package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.cookie.CookieOrigin;
import java.util.Collections;
import java.util.List;

public class IgnoreSpec extends CookieSpecBase {
    public int m12450a() {
        return 0;
    }

    public List<Cookie> m12451a(Header header, CookieOrigin cookieOrigin) {
        return Collections.emptyList();
    }

    public List<Header> m12452a(List<Cookie> list) {
        return Collections.emptyList();
    }

    public Header m12453b() {
        return null;
    }
}
