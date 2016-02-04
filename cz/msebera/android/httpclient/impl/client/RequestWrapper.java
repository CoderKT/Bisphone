package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.ProtocolException;
import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.RequestLine;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.message.AbstractHttpMessage;
import cz.msebera.android.httpclient.message.BasicRequestLine;
import cz.msebera.android.httpclient.params.HttpProtocolParams;
import cz.msebera.android.httpclient.util.Args;
import java.net.URI;

@Deprecated
public class RequestWrapper extends AbstractHttpMessage implements HttpUriRequest {
    private final HttpRequest f7612c;
    private URI f7613d;
    private String f7614e;
    private ProtocolVersion f7615f;
    private int f7616g;

    public RequestWrapper(HttpRequest httpRequest) {
        Args.m12722a((Object) httpRequest, "HTTP request");
        this.f7612c = httpRequest;
        m10624a(httpRequest.m10622g());
        m10626a(httpRequest.m10620e());
        if (httpRequest instanceof HttpUriRequest) {
            this.f7613d = ((HttpUriRequest) httpRequest).m10648k();
            this.f7614e = ((HttpUriRequest) httpRequest).m10645a();
            this.f7615f = null;
        } else {
            RequestLine h = httpRequest.m10637h();
            try {
                this.f7613d = new URI(h.m11408c());
                this.f7614e = h.m11406a();
                this.f7615f = httpRequest.m10617d();
            } catch (Throwable e) {
                throw new ProtocolException("Invalid request URI: " + h.m11408c(), e);
            }
        }
        this.f7616g = 0;
    }

    public void m12140m() {
        this.a.m12650a();
        m10626a(this.f7612c.m10620e());
    }

    public String m12132a() {
        return this.f7614e;
    }

    public ProtocolVersion m12134d() {
        if (this.f7615f == null) {
            this.f7615f = HttpProtocolParams.m12682b(m10636g());
        }
        return this.f7615f;
    }

    public URI m12138k() {
        return this.f7613d;
    }

    public void m12133a(URI uri) {
        this.f7613d = uri;
    }

    public RequestLine m12135h() {
        String a = m12132a();
        ProtocolVersion d = m12134d();
        String str = null;
        if (this.f7613d != null) {
            str = this.f7613d.toASCIIString();
        }
        if (str == null || str.length() == 0) {
            str = "/";
        }
        return new BasicRequestLine(a, str, d);
    }

    public void m12136i() {
        throw new UnsupportedOperationException();
    }

    public boolean m12137j() {
        return false;
    }

    public HttpRequest m12141n() {
        return this.f7612c;
    }

    public boolean m12139l() {
        return true;
    }

    public int m12142o() {
        return this.f7616g;
    }

    public void m12143p() {
        this.f7616g++;
    }
}
