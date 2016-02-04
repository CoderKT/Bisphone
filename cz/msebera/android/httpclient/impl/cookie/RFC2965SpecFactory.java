package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.cookie.CookieSpec;
import cz.msebera.android.httpclient.cookie.CookieSpecFactory;
import cz.msebera.android.httpclient.cookie.CookieSpecProvider;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.HttpContext;
import java.util.Collection;

public class RFC2965SpecFactory implements CookieSpecFactory, CookieSpecProvider {
    private final String[] f7786a;
    private final boolean f7787b;

    public RFC2965SpecFactory(String[] strArr, boolean z) {
        this.f7786a = strArr;
        this.f7787b = z;
    }

    public RFC2965SpecFactory() {
        this(null, false);
    }

    public CookieSpec m12507a(HttpParams httpParams) {
        if (httpParams == null) {
            return new RFC2965Spec();
        }
        String[] strArr;
        Collection collection = (Collection) httpParams.m12084a("http.protocol.cookie-datepatterns");
        if (collection != null) {
            strArr = (String[]) collection.toArray(new String[collection.size()]);
        } else {
            strArr = null;
        }
        return new RFC2965Spec(strArr, httpParams.m12085a("http.protocol.single-cookie-header", false));
    }

    public CookieSpec m12508a(HttpContext httpContext) {
        return new RFC2965Spec(this.f7786a, this.f7787b);
    }
}
