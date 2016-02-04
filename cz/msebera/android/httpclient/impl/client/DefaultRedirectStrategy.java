package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.ProtocolException;
import cz.msebera.android.httpclient.client.CircularRedirectException;
import cz.msebera.android.httpclient.client.RedirectStrategy;
import cz.msebera.android.httpclient.client.config.RequestConfig;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.methods.HttpHead;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.client.methods.RequestBuilder;
import cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import cz.msebera.android.httpclient.client.utils.URIBuilder;
import cz.msebera.android.httpclient.client.utils.URIUtils;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import cz.msebera.android.httpclient.util.TextUtils;
import java.net.URI;
import java.util.Locale;

public class DefaultRedirectStrategy implements RedirectStrategy {
    public static final DefaultRedirectStrategy f7582b;
    private static final String[] f7583c;
    public HttpClientAndroidLog f7584a;

    static {
        f7582b = new DefaultRedirectStrategy();
        f7583c = new String[]{"GET", "HEAD"};
    }

    public DefaultRedirectStrategy() {
        this.f7584a = new HttpClientAndroidLog(getClass());
    }

    public boolean m12107a(HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext) {
        Args.m12722a((Object) httpRequest, "HTTP request");
        Args.m12722a((Object) httpResponse, "HTTP response");
        int b = httpResponse.m11391a().m11410b();
        String a = httpRequest.m10637h().m11406a();
        Header c = httpResponse.m10616c("location");
        switch (b) {
            case 301:
            case 307:
                return m12109b(a);
            case 302:
                if (!m12109b(a) || c == null) {
                    return false;
                }
                return true;
            case 303:
                return true;
            default:
                return false;
        }
    }

    public URI m12110c(HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext) {
        Args.m12722a((Object) httpRequest, "HTTP request");
        Args.m12722a((Object) httpResponse, "HTTP response");
        Args.m12722a((Object) httpContext, "HTTP context");
        HttpClientContext a = HttpClientContext.m11538a(httpContext);
        Header c = httpResponse.m10616c("location");
        if (c == null) {
            throw new ProtocolException("Received redirect response " + httpResponse.m11391a() + " but no location header");
        }
        String d = c.m11362d();
        if (this.f7584a.m11836a()) {
            this.f7584a.m11834a("Redirect requested to location '" + d + "'");
        }
        RequestConfig k = a.m11551k();
        URI a2 = m12106a(d);
        try {
            URI uri;
            if (a2.isAbsolute()) {
                uri = a2;
            } else if (k.m11494b()) {
                Object o = a.m11537o();
                Asserts.m12728a(o, "Target host");
                uri = URIUtils.m11591a(URIUtils.m11590a(new URI(httpRequest.m10637h().m11408c()), o, false), a2);
            } else {
                throw new ProtocolException("Relative redirect location '" + a2 + "' not allowed");
            }
            RedirectLocations redirectLocations = (RedirectLocations) a.m11531a("http.protocol.redirect-locations");
            if (redirectLocations == null) {
                redirectLocations = new RedirectLocations();
                httpContext.m11529a("http.protocol.redirect-locations", redirectLocations);
            }
            if (k.m11495c() || !redirectLocations.m12157a(uri)) {
                redirectLocations.m12159b(uri);
                return uri;
            }
            throw new CircularRedirectException("Circular redirect to '" + uri + "'");
        } catch (Throwable e) {
            throw new ProtocolException(e.getMessage(), e);
        }
    }

    protected URI m12106a(String str) {
        try {
            URIBuilder uRIBuilder = new URIBuilder(new URI(str).normalize());
            String c = uRIBuilder.m11585c();
            if (c != null) {
                uRIBuilder.m11584c(c.toLowerCase(Locale.ENGLISH));
            }
            if (TextUtils.m12771a(uRIBuilder.m11587d())) {
                uRIBuilder.m11586d("/");
            }
            return uRIBuilder.m11581a();
        } catch (Throwable e) {
            throw new ProtocolException("Invalid redirect URI: " + str, e);
        }
    }

    protected boolean m12109b(String str) {
        for (String equalsIgnoreCase : f7583c) {
            if (equalsIgnoreCase.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    public HttpUriRequest m12108b(HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext) {
        URI c = m12110c(httpRequest, httpResponse, httpContext);
        String a = httpRequest.m10637h().m11406a();
        if (a.equalsIgnoreCase("HEAD")) {
            return new HttpHead(c);
        }
        if (a.equalsIgnoreCase("GET")) {
            return new HttpGet(c);
        }
        if (httpResponse.m11391a().m11410b() == 307) {
            return RequestBuilder.m11520a(httpRequest).m11523a(c).m11522a();
        }
        return new HttpGet(c);
    }
}
