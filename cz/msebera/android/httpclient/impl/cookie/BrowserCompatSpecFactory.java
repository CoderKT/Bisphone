package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.cookie.CookieSpec;
import cz.msebera.android.httpclient.cookie.CookieSpecFactory;
import cz.msebera.android.httpclient.cookie.CookieSpecProvider;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.HttpContext;
import java.util.Collection;

public class BrowserCompatSpecFactory implements CookieSpecFactory, CookieSpecProvider {
    private final String[] f7775a;
    private final SecurityLevel f7776b;

    public enum SecurityLevel {
        SECURITYLEVEL_DEFAULT,
        SECURITYLEVEL_IE_MEDIUM
    }

    public BrowserCompatSpecFactory(String[] strArr, SecurityLevel securityLevel) {
        this.f7775a = strArr;
        this.f7776b = securityLevel;
    }

    public BrowserCompatSpecFactory() {
        this(null, SecurityLevel.SECURITYLEVEL_DEFAULT);
    }

    public CookieSpec m12447a(HttpParams httpParams) {
        if (httpParams == null) {
            return new BrowserCompatSpec(null, this.f7776b);
        }
        String[] strArr;
        Collection collection = (Collection) httpParams.m12084a("http.protocol.cookie-datepatterns");
        if (collection != null) {
            strArr = (String[]) collection.toArray(new String[collection.size()]);
        } else {
            strArr = null;
        }
        return new BrowserCompatSpec(strArr, this.f7776b);
    }

    public CookieSpec m12448a(HttpContext httpContext) {
        return new BrowserCompatSpec(this.f7775a);
    }
}
