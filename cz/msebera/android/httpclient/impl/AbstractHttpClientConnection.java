package cz.msebera.android.httpclient.impl;

import cz.msebera.android.httpclient.HttpClientConnection;
import cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpResponseFactory;
import cz.msebera.android.httpclient.impl.entity.EntityDeserializer;
import cz.msebera.android.httpclient.impl.entity.EntitySerializer;
import cz.msebera.android.httpclient.impl.entity.LaxContentLengthStrategy;
import cz.msebera.android.httpclient.impl.entity.StrictContentLengthStrategy;
import cz.msebera.android.httpclient.impl.io.DefaultHttpResponseParser;
import cz.msebera.android.httpclient.impl.io.HttpRequestWriter;
import cz.msebera.android.httpclient.io.EofSensor;
import cz.msebera.android.httpclient.io.HttpMessageParser;
import cz.msebera.android.httpclient.io.HttpMessageWriter;
import cz.msebera.android.httpclient.io.HttpTransportMetrics;
import cz.msebera.android.httpclient.io.SessionInputBuffer;
import cz.msebera.android.httpclient.io.SessionOutputBuffer;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;
import java.net.SocketTimeoutException;

@Deprecated
public abstract class AbstractHttpClientConnection implements HttpClientConnection {
    private final EntitySerializer f7440a;
    private final EntityDeserializer f7441b;
    private SessionInputBuffer f7442c;
    private SessionOutputBuffer f7443d;
    private EofSensor f7444e;
    private HttpMessageParser<HttpResponse> f7445f;
    private HttpMessageWriter<HttpRequest> f7446g;
    private HttpConnectionMetricsImpl f7447h;

    protected abstract void m11856j();

    public AbstractHttpClientConnection() {
        this.f7442c = null;
        this.f7443d = null;
        this.f7444e = null;
        this.f7445f = null;
        this.f7446g = null;
        this.f7447h = null;
        this.f7440a = m11858l();
        this.f7441b = m11857k();
    }

    protected EntityDeserializer m11857k() {
        return new EntityDeserializer(new LaxContentLengthStrategy());
    }

    protected EntitySerializer m11858l() {
        return new EntitySerializer(new StrictContentLengthStrategy());
    }

    protected HttpResponseFactory m11859n() {
        return DefaultHttpResponseFactory.f7449a;
    }

    protected HttpMessageParser<HttpResponse> m11847a(SessionInputBuffer sessionInputBuffer, HttpResponseFactory httpResponseFactory, HttpParams httpParams) {
        return new DefaultHttpResponseParser(sessionInputBuffer, null, httpResponseFactory, httpParams);
    }

    protected HttpMessageWriter<HttpRequest> m11848a(SessionOutputBuffer sessionOutputBuffer, HttpParams httpParams) {
        return new HttpRequestWriter(sessionOutputBuffer, null, httpParams);
    }

    protected HttpConnectionMetricsImpl m11846a(HttpTransportMetrics httpTransportMetrics, HttpTransportMetrics httpTransportMetrics2) {
        return new HttpConnectionMetricsImpl(httpTransportMetrics, httpTransportMetrics2);
    }

    protected void m11852a(SessionInputBuffer sessionInputBuffer, SessionOutputBuffer sessionOutputBuffer, HttpParams httpParams) {
        this.f7442c = (SessionInputBuffer) Args.m12722a((Object) sessionInputBuffer, "Input session buffer");
        this.f7443d = (SessionOutputBuffer) Args.m12722a((Object) sessionOutputBuffer, "Output session buffer");
        if (sessionInputBuffer instanceof EofSensor) {
            this.f7444e = (EofSensor) sessionInputBuffer;
        }
        this.f7445f = m11847a(sessionInputBuffer, m11859n(), httpParams);
        this.f7446g = m11848a(sessionOutputBuffer, httpParams);
        this.f7447h = m11846a(sessionInputBuffer.m12269b(), sessionOutputBuffer.m12281b());
    }

    public boolean m11853a(int i) {
        m11856j();
        try {
            return this.f7442c.m12268a(i);
        } catch (SocketTimeoutException e) {
            return false;
        }
    }

    public void m11850a(HttpRequest httpRequest) {
        Args.m12722a((Object) httpRequest, "HTTP request");
        m11856j();
        this.f7446g.m12518b(httpRequest);
        this.f7447h.m11869a();
    }

    public void m11849a(HttpEntityEnclosingRequest httpEntityEnclosingRequest) {
        Args.m12722a((Object) httpEntityEnclosingRequest, "HTTP request");
        m11856j();
        if (httpEntityEnclosingRequest.m10657c() != null) {
            this.f7440a.m12515a(this.f7443d, httpEntityEnclosingRequest, httpEntityEnclosingRequest.m10657c());
        }
    }

    protected void m11860o() {
        this.f7443d.m12276a();
    }

    public void m11854b() {
        m11856j();
        m11860o();
    }

    public HttpResponse m11845a() {
        m11856j();
        HttpResponse httpResponse = (HttpResponse) this.f7445f.m12241a();
        if (httpResponse.m11391a().m11410b() >= 200) {
            this.f7447h.m11870b();
        }
        return httpResponse;
    }

    public void m11851a(HttpResponse httpResponse) {
        Args.m12722a((Object) httpResponse, "HTTP response");
        m11856j();
        httpResponse.m11392a(this.f7441b.m12513b(this.f7442c, httpResponse));
    }

    protected boolean m11861p() {
        return this.f7444e != null && this.f7444e.m12264c();
    }

    public boolean m11855d() {
        boolean z = true;
        if (!m11375c() || m11861p()) {
            return z;
        }
        try {
            this.f7442c.m12268a(1);
            return m11861p();
        } catch (SocketTimeoutException e) {
            return false;
        } catch (IOException e2) {
            return z;
        }
    }
}
