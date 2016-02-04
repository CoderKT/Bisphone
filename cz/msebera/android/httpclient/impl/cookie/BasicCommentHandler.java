package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.cookie.SetCookie;
import cz.msebera.android.httpclient.util.Args;

public class BasicCommentHandler extends AbstractCookieAttributeHandler {
    public void m12414a(SetCookie setCookie, String str) {
        Args.m12722a((Object) setCookie, "Cookie");
        setCookie.m11805c(str);
    }
}
