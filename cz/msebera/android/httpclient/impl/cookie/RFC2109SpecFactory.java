package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.cookie.CookieSpec;
import cz.msebera.android.httpclient.cookie.CookieSpecFactory;
import cz.msebera.android.httpclient.cookie.CookieSpecProvider;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.HttpContext;
import java.util.Collection;

public class RFC2109SpecFactory implements CookieSpecFactory, CookieSpecProvider {
    private final String[] f7784a;
    private final boolean f7785b;

    public RFC2109SpecFactory(String[] strArr, boolean z) {
        this.f7784a = strArr;
        this.f7785b = z;
    }

    public RFC2109SpecFactory() {
        this(null, false);
    }

    public CookieSpec m12479a(HttpParams httpParams) {
        if (httpParams == null) {
            return new RFC2109Spec();
        }
        String[] strArr;
        Collection collection = (Collection) httpParams.m12084a("http.protocol.cookie-datepatterns");
        if (collection != null) {
            strArr = (String[]) collection.toArray(new String[collection.size()]);
        } else {
            strArr = null;
        }
        return new RFC2109Spec(strArr, httpParams.m12085a("http.protocol.single-cookie-header", false));
    }

    public CookieSpec m12480a(HttpContext httpContext) {
        return new RFC2109Spec(this.f7784a, this.f7785b);
    }
}
