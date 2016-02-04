package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.cookie.CookieSpec;
import cz.msebera.android.httpclient.cookie.CookieSpecFactory;
import cz.msebera.android.httpclient.cookie.CookieSpecProvider;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.HttpContext;
import java.util.Collection;

public class NetscapeDraftSpecFactory implements CookieSpecFactory, CookieSpecProvider {
    private final String[] f7779a;

    public NetscapeDraftSpecFactory(String[] strArr) {
        this.f7779a = strArr;
    }

    public NetscapeDraftSpecFactory() {
        this(null);
    }

    public CookieSpec m12465a(HttpParams httpParams) {
        if (httpParams == null) {
            return new NetscapeDraftSpec();
        }
        String[] strArr;
        Collection collection = (Collection) httpParams.m12084a("http.protocol.cookie-datepatterns");
        if (collection != null) {
            strArr = (String[]) collection.toArray(new String[collection.size()]);
        } else {
            strArr = null;
        }
        return new NetscapeDraftSpec(strArr);
    }

    public CookieSpec m12466a(HttpContext httpContext) {
        return new NetscapeDraftSpec(this.f7779a);
    }
}
