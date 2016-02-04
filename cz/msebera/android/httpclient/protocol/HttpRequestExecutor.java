package cz.msebera.android.httpclient.protocol;

import cz.msebera.android.httpclient.HttpClientConnection;
import cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.ProtocolException;
import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;

public class HttpRequestExecutor {
    private final int f7914a;

    public HttpRequestExecutor(int i) {
        this.f7914a = Args.m12719a(i, "Wait for continue time");
    }

    public HttpRequestExecutor() {
        this(3000);
    }

    protected boolean m12708a(HttpRequest httpRequest, HttpResponse httpResponse) {
        if ("HEAD".equalsIgnoreCase(httpRequest.m10637h().m11406a())) {
            return false;
        }
        int b = httpResponse.m11391a().m11410b();
        if (b < 200 || b == 204 || b == 304 || b == 205) {
            return false;
        }
        return true;
    }

    public HttpResponse m12705a(HttpRequest httpRequest, HttpClientConnection httpClientConnection, HttpContext httpContext) {
        Args.m12722a((Object) httpRequest, "HTTP request");
        Args.m12722a((Object) httpClientConnection, "Client connection");
        Args.m12722a((Object) httpContext, "HTTP context");
        try {
            HttpResponse b = m12709b(httpRequest, httpClientConnection, httpContext);
            if (b == null) {
                b = m12710c(httpRequest, httpClientConnection, httpContext);
            }
            return b;
        } catch (IOException e) {
            m12704a(httpClientConnection);
            throw e;
        } catch (HttpException e2) {
            m12704a(httpClientConnection);
            throw e2;
        } catch (RuntimeException e3) {
            m12704a(httpClientConnection);
            throw e3;
        }
    }

    private static void m12704a(HttpClientConnection httpClientConnection) {
        try {
            httpClientConnection.close();
        } catch (IOException e) {
        }
    }

    public void m12706a(HttpRequest httpRequest, HttpProcessor httpProcessor, HttpContext httpContext) {
        Args.m12722a((Object) httpRequest, "HTTP request");
        Args.m12722a((Object) httpProcessor, "HTTP processor");
        Args.m12722a((Object) httpContext, "HTTP context");
        httpContext.m11529a("http.request", httpRequest);
        httpProcessor.m10535a(httpRequest, httpContext);
    }

    protected HttpResponse m12709b(HttpRequest httpRequest, HttpClientConnection httpClientConnection, HttpContext httpContext) {
        HttpResponse httpResponse;
        Args.m12722a((Object) httpRequest, "HTTP request");
        Args.m12722a((Object) httpClientConnection, "Client connection");
        Args.m12722a((Object) httpContext, "HTTP context");
        httpContext.m11529a("http.connection", httpClientConnection);
        httpContext.m11529a("http.request_sent", Boolean.FALSE);
        httpClientConnection.m11380a(httpRequest);
        if (httpRequest instanceof HttpEntityEnclosingRequest) {
            Object obj;
            ProtocolVersion b = httpRequest.m10637h().m11407b();
            if (((HttpEntityEnclosingRequest) httpRequest).m10656b() && !b.m11401c(HttpVersion.f7209b)) {
                httpClientConnection.m11383b();
                if (httpClientConnection.m11382a(this.f7914a)) {
                    HttpResponse a = httpClientConnection.m11378a();
                    if (m12708a(httpRequest, a)) {
                        httpClientConnection.m11381a(a);
                    }
                    int b2 = a.m11391a().m11410b();
                    if (b2 >= 200) {
                        obj = null;
                        httpResponse = a;
                    } else if (b2 != 100) {
                        throw new ProtocolException("Unexpected response: " + a.m11391a());
                    } else {
                        httpResponse = null;
                        obj = 1;
                    }
                    if (obj != null) {
                        httpClientConnection.m11379a((HttpEntityEnclosingRequest) httpRequest);
                    }
                }
            }
            httpResponse = null;
            int i = 1;
            if (obj != null) {
                httpClientConnection.m11379a((HttpEntityEnclosingRequest) httpRequest);
            }
        } else {
            httpResponse = null;
        }
        httpClientConnection.m11383b();
        httpContext.m11529a("http.request_sent", Boolean.TRUE);
        return httpResponse;
    }

    protected HttpResponse m12710c(HttpRequest httpRequest, HttpClientConnection httpClientConnection, HttpContext httpContext) {
        Args.m12722a((Object) httpRequest, "HTTP request");
        Args.m12722a((Object) httpClientConnection, "Client connection");
        Args.m12722a((Object) httpContext, "HTTP context");
        HttpResponse httpResponse = null;
        int i = 0;
        while (true) {
            if (httpResponse != null && r0 >= 200) {
                return httpResponse;
            }
            httpResponse = httpClientConnection.m11378a();
            if (m12708a(httpRequest, httpResponse)) {
                httpClientConnection.m11381a(httpResponse);
            }
            i = httpResponse.m11391a().m11410b();
        }
    }

    public void m12707a(HttpResponse httpResponse, HttpProcessor httpProcessor, HttpContext httpContext) {
        Args.m12722a((Object) httpResponse, "HTTP response");
        Args.m12722a((Object) httpProcessor, "HTTP processor");
        Args.m12722a((Object) httpContext, "HTTP context");
        httpContext.m11529a("http.response", httpResponse);
        httpProcessor.m10537a(httpResponse, httpContext);
    }
}
