package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.cookie.CookieSpec;
import cz.msebera.android.httpclient.cookie.CookieSpecFactory;
import cz.msebera.android.httpclient.cookie.CookieSpecProvider;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.HttpContext;
import java.util.Collection;

public class BestMatchSpecFactory implements CookieSpecFactory, CookieSpecProvider {
    private final String[] f7766a;
    private final boolean f7767b;

    public BestMatchSpecFactory(String[] strArr, boolean z) {
        this.f7766a = strArr;
        this.f7767b = z;
    }

    public BestMatchSpecFactory() {
        this(null, false);
    }

    public CookieSpec m12434a(HttpParams httpParams) {
        if (httpParams == null) {
            return new BestMatchSpec();
        }
        String[] strArr;
        Collection collection = (Collection) httpParams.m12084a("http.protocol.cookie-datepatterns");
        if (collection != null) {
            strArr = (String[]) collection.toArray(new String[collection.size()]);
        } else {
            strArr = null;
        }
        return new BestMatchSpec(strArr, httpParams.m12085a("http.protocol.single-cookie-header", false));
    }

    public CookieSpec m12435a(HttpContext httpContext) {
        return new BestMatchSpec(this.f7766a, this.f7767b);
    }
}
