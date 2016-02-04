package cz.msebera.android.httpclient.client.protocol;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpRequestInterceptor;
import cz.msebera.android.httpclient.client.CookieStore;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.config.Lookup;
import cz.msebera.android.httpclient.conn.routing.RouteInfo;
import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.cookie.CookieOrigin;
import cz.msebera.android.httpclient.cookie.CookieSpec;
import cz.msebera.android.httpclient.cookie.CookieSpecProvider;
import cz.msebera.android.httpclient.cookie.SetCookie2;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.TextUtils;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RequestAddCookies implements HttpRequestInterceptor {
    public HttpClientAndroidLog f7297a;

    public RequestAddCookies() {
        this.f7297a = new HttpClientAndroidLog(getClass());
    }

    public void m11552a(HttpRequest httpRequest, HttpContext httpContext) {
        Object obj = null;
        Args.m12722a((Object) httpRequest, "HTTP request");
        Args.m12722a((Object) httpContext, "HTTP context");
        if (!httpRequest.m10637h().m11406a().equalsIgnoreCase("CONNECT")) {
            HttpContext a = HttpClientContext.m11538a(httpContext);
            CookieStore b = a.m11542b();
            if (b == null) {
                this.f7297a.m11834a("Cookie store not specified in HTTP context");
                return;
            }
            Lookup e = a.m11545e();
            if (e == null) {
                this.f7297a.m11834a("CookieSpec registry not specified in HTTP context");
                return;
            }
            HttpHost o = a.m11537o();
            if (o == null) {
                this.f7297a.m11834a("Target host not set in the context");
                return;
            }
            RouteInfo a2 = a.m11540a();
            if (a2 == null) {
                this.f7297a.m11834a("Connection route not set in the context");
                return;
            }
            String str;
            URI k;
            String a3 = a.m11551k().m11493a();
            if (a3 == null) {
                str = "best-match";
            } else {
                str = a3;
            }
            if (this.f7297a.m11836a()) {
                this.f7297a.m11834a("CookieSpec selected: " + str);
            }
            if (httpRequest instanceof HttpUriRequest) {
                k = ((HttpUriRequest) httpRequest).m10648k();
            } else {
                try {
                    k = new URI(httpRequest.m10637h().m11408c());
                } catch (URISyntaxException e2) {
                    k = null;
                }
            }
            if (k != null) {
                a3 = k.getPath();
            } else {
                a3 = null;
            }
            String a4 = o.m11384a();
            int b2 = o.m11385b();
            if (b2 < 0) {
                b2 = a2.m11685a().m11385b();
            }
            if (b2 < 0) {
                b2 = 0;
            }
            if (TextUtils.m12771a(a3)) {
                a3 = "/";
            }
            CookieOrigin cookieOrigin = new CookieOrigin(a4, b2, a3, a2.m11692g());
            CookieSpecProvider cookieSpecProvider = (CookieSpecProvider) e.m11424b(str);
            if (cookieSpecProvider == null) {
                throw new HttpException("Unsupported cookie policy: " + str);
            }
            Header b3;
            CookieSpec a5 = cookieSpecProvider.m11796a(a);
            List<Cookie> arrayList = new ArrayList(b.m11467a());
            List<Cookie> arrayList2 = new ArrayList();
            Date date = new Date();
            for (Cookie cookie : arrayList) {
                if (cookie.m11769a(date)) {
                    if (this.f7297a.m11836a()) {
                        this.f7297a.m11834a("Cookie " + cookie + " expired");
                    }
                } else if (a5.m11794b(cookie, cookieOrigin)) {
                    if (this.f7297a.m11836a()) {
                        this.f7297a.m11834a("Cookie " + cookie + " match " + cookieOrigin);
                    }
                    arrayList2.add(cookie);
                }
            }
            if (!arrayList2.isEmpty()) {
                for (Header b32 : a5.m11791a(arrayList2)) {
                    httpRequest.m10608a(b32);
                }
            }
            int a6 = a5.m11789a();
            if (a6 > 0) {
                for (Cookie cookie2 : arrayList2) {
                    if (a6 != cookie2.m11776h() || !(cookie2 instanceof SetCookie2)) {
                        obj = 1;
                    }
                }
                if (obj != null) {
                    b32 = a5.m11793b();
                    if (b32 != null) {
                        httpRequest.m10608a(b32);
                    }
                }
            }
            httpContext.m11529a("http.cookie-spec", a5);
            httpContext.m11529a("http.cookie-origin", cookieOrigin);
        }
    }
}
