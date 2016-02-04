package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.client.utils.DateUtils;
import cz.msebera.android.httpclient.cookie.MalformedCookieException;
import cz.msebera.android.httpclient.cookie.SetCookie;
import cz.msebera.android.httpclient.util.Args;
import java.util.Date;

public class BasicExpiresHandler extends AbstractCookieAttributeHandler {
    private final String[] f7760a;

    public BasicExpiresHandler(String[] strArr) {
        Args.m12722a((Object) strArr, "Array of date patterns");
        this.f7760a = strArr;
    }

    public void m12418a(SetCookie setCookie, String str) {
        Args.m12722a((Object) setCookie, "Cookie");
        if (str == null) {
            throw new MalformedCookieException("Missing value for expires attribute");
        }
        Date a = DateUtils.m11568a(str, this.f7760a);
        if (a == null) {
            throw new MalformedCookieException("Unable to parse expires attribute: " + str);
        }
        setCookie.m11804b(a);
    }
}
