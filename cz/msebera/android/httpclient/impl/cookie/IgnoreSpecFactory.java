package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.cookie.CookieSpec;
import cz.msebera.android.httpclient.cookie.CookieSpecFactory;
import cz.msebera.android.httpclient.cookie.CookieSpecProvider;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.HttpContext;

public class IgnoreSpecFactory implements CookieSpecFactory, CookieSpecProvider {
    public CookieSpec m12454a(HttpParams httpParams) {
        return new IgnoreSpec();
    }

    public CookieSpec m12455a(HttpContext httpContext) {
        return new IgnoreSpec();
    }
}
