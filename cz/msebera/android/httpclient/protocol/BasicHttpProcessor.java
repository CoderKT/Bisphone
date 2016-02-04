package cz.msebera.android.httpclient.protocol;

import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpRequestInterceptor;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpResponseInterceptor;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public final class BasicHttpProcessor implements HttpProcessor, Cloneable {
    protected final List<HttpRequestInterceptor> f7908a;
    protected final List<HttpResponseInterceptor> f7909b;

    public BasicHttpProcessor() {
        this.f7908a = new ArrayList();
        this.f7909b = new ArrayList();
    }

    public void m12691a(HttpRequestInterceptor httpRequestInterceptor) {
        if (httpRequestInterceptor != null) {
            this.f7908a.add(httpRequestInterceptor);
        }
    }

    public void m12692a(HttpRequestInterceptor httpRequestInterceptor, int i) {
        if (httpRequestInterceptor != null) {
            this.f7908a.add(i, httpRequestInterceptor);
        }
    }

    public final void m12698b(HttpRequestInterceptor httpRequestInterceptor) {
        m12691a(httpRequestInterceptor);
    }

    public final void m12699b(HttpRequestInterceptor httpRequestInterceptor, int i) {
        m12692a(httpRequestInterceptor, i);
    }

    public int m12688a() {
        return this.f7908a.size();
    }

    public HttpRequestInterceptor m12689a(int i) {
        if (i < 0 || i >= this.f7908a.size()) {
            return null;
        }
        return (HttpRequestInterceptor) this.f7908a.get(i);
    }

    public void m12694a(HttpResponseInterceptor httpResponseInterceptor) {
        if (httpResponseInterceptor != null) {
            this.f7909b.add(httpResponseInterceptor);
        }
    }

    public final void m12700b(HttpResponseInterceptor httpResponseInterceptor) {
        m12694a(httpResponseInterceptor);
    }

    public int m12696b() {
        return this.f7909b.size();
    }

    public HttpResponseInterceptor m12697b(int i) {
        if (i < 0 || i >= this.f7909b.size()) {
            return null;
        }
        return (HttpResponseInterceptor) this.f7909b.get(i);
    }

    public void m12690a(HttpRequest httpRequest, HttpContext httpContext) {
        for (HttpRequestInterceptor a : this.f7908a) {
            a.m10535a(httpRequest, httpContext);
        }
    }

    public void m12693a(HttpResponse httpResponse, HttpContext httpContext) {
        for (HttpResponseInterceptor a : this.f7909b) {
            a.m10537a(httpResponse, httpContext);
        }
    }

    protected void m12695a(BasicHttpProcessor basicHttpProcessor) {
        basicHttpProcessor.f7908a.clear();
        basicHttpProcessor.f7908a.addAll(this.f7908a);
        basicHttpProcessor.f7909b.clear();
        basicHttpProcessor.f7909b.addAll(this.f7909b);
    }

    public Object clone() {
        BasicHttpProcessor basicHttpProcessor = (BasicHttpProcessor) super.clone();
        m12695a(basicHttpProcessor);
        return basicHttpProcessor;
    }
}
