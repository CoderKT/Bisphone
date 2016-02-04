package cz.msebera.android.httpclient.client.protocol;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderIterator;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpResponseInterceptor;
import cz.msebera.android.httpclient.client.CookieStore;
import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.cookie.CookieOrigin;
import cz.msebera.android.httpclient.cookie.CookieSpec;
import cz.msebera.android.httpclient.cookie.MalformedCookieException;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;

public class ResponseProcessCookies implements HttpResponseInterceptor {
    public HttpClientAndroidLog f7303a;

    public ResponseProcessCookies() {
        this.f7303a = new HttpClientAndroidLog(getClass());
    }

    public void m11564a(HttpResponse httpResponse, HttpContext httpContext) {
        Args.m12722a((Object) httpResponse, "HTTP request");
        Args.m12722a((Object) httpContext, "HTTP context");
        HttpClientContext a = HttpClientContext.m11538a(httpContext);
        CookieSpec c = a.m11543c();
        if (c == null) {
            this.f7303a.m11834a("Cookie spec not specified in HTTP context");
            return;
        }
        CookieStore b = a.m11542b();
        if (b == null) {
            this.f7303a.m11834a("Cookie store not specified in HTTP context");
            return;
        }
        CookieOrigin d = a.m11544d();
        if (d == null) {
            this.f7303a.m11834a("Cookie origin not specified in HTTP context");
            return;
        }
        m11563a(httpResponse.m10619e("Set-Cookie"), c, d, b);
        if (c.m11789a() > 0) {
            m11563a(httpResponse.m10619e("Set-Cookie2"), c, d, b);
        }
    }

    private void m11563a(HeaderIterator headerIterator, CookieSpec cookieSpec, CookieOrigin cookieOrigin, CookieStore cookieStore) {
        while (headerIterator.hasNext()) {
            Header a = headerIterator.m11373a();
            try {
                for (Cookie cookie : cookieSpec.m11790a(a, cookieOrigin)) {
                    try {
                        cookieSpec.m11792a(cookie, cookieOrigin);
                        cookieStore.m11468a(cookie);
                        if (this.f7303a.m11836a()) {
                            this.f7303a.m11834a("Cookie accepted [" + m11562a(cookie) + "]");
                        }
                    } catch (MalformedCookieException e) {
                        if (this.f7303a.m11842c()) {
                            this.f7303a.m11840c("Cookie rejected [" + m11562a(cookie) + "] " + e.getMessage());
                        }
                    }
                }
            } catch (MalformedCookieException e2) {
                if (this.f7303a.m11842c()) {
                    this.f7303a.m11840c("Invalid cookie header: \"" + a + "\". " + e2.getMessage());
                }
            }
        }
    }

    private static String m11562a(Cookie cookie) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(cookie.m11768a());
        stringBuilder.append("=\"");
        String b = cookie.m11770b();
        if (b != null) {
            if (b.length() > 100) {
                b = b.substring(0, 100) + "...";
            }
            stringBuilder.append(b);
        }
        stringBuilder.append("\"");
        stringBuilder.append(", version:");
        stringBuilder.append(Integer.toString(cookie.m11776h()));
        stringBuilder.append(", domain:");
        stringBuilder.append(cookie.m11772d());
        stringBuilder.append(", path:");
        stringBuilder.append(cookie.m11773e());
        stringBuilder.append(", expiry:");
        stringBuilder.append(cookie.m11771c());
        return stringBuilder.toString();
    }
}
