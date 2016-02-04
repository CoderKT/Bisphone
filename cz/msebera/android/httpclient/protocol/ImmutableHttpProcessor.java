package cz.msebera.android.httpclient.protocol;

import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpRequestInterceptor;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpResponseInterceptor;

public final class ImmutableHttpProcessor implements HttpProcessor {
    private final HttpRequestInterceptor[] f7915a;
    private final HttpResponseInterceptor[] f7916b;

    public ImmutableHttpProcessor(HttpRequestInterceptor[] httpRequestInterceptorArr, HttpResponseInterceptor[] httpResponseInterceptorArr) {
        if (httpRequestInterceptorArr != null) {
            int length = httpRequestInterceptorArr.length;
            this.f7915a = new HttpRequestInterceptor[length];
            System.arraycopy(httpRequestInterceptorArr, 0, this.f7915a, 0, length);
        } else {
            this.f7915a = new HttpRequestInterceptor[0];
        }
        if (httpResponseInterceptorArr != null) {
            length = httpResponseInterceptorArr.length;
            this.f7916b = new HttpResponseInterceptor[length];
            System.arraycopy(httpResponseInterceptorArr, 0, this.f7916b, 0, length);
            return;
        }
        this.f7916b = new HttpResponseInterceptor[0];
    }

    public void m12711a(HttpRequest httpRequest, HttpContext httpContext) {
        for (HttpRequestInterceptor a : this.f7915a) {
            a.m10535a(httpRequest, httpContext);
        }
    }

    public void m12712a(HttpResponse httpResponse, HttpContext httpContext) {
        for (HttpResponseInterceptor a : this.f7916b) {
            a.m10537a(httpResponse, httpContext);
        }
    }
}
