package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.cookie.MalformedCookieException;
import cz.msebera.android.httpclient.cookie.SetCookie;
import cz.msebera.android.httpclient.util.Args;
import java.util.Date;

public class BasicMaxAgeHandler extends AbstractCookieAttributeHandler {
    public void m12419a(SetCookie setCookie, String str) {
        Args.m12722a((Object) setCookie, "Cookie");
        if (str == null) {
            throw new MalformedCookieException("Missing value for max-age attribute");
        }
        try {
            int parseInt = Integer.parseInt(str);
            if (parseInt < 0) {
                throw new MalformedCookieException("Negative max-age attribute: " + str);
            }
            setCookie.m11804b(new Date(System.currentTimeMillis() + (((long) parseInt) * 1000)));
        } catch (NumberFormatException e) {
            throw new MalformedCookieException("Invalid max-age attribute: " + str);
        }
    }
}
