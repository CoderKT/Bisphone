package cz.msebera.android.httpclient.client.methods;

import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.RequestLine;
import cz.msebera.android.httpclient.client.config.RequestConfig;
import cz.msebera.android.httpclient.message.BasicRequestLine;
import cz.msebera.android.httpclient.params.HttpProtocolParams;
import java.net.URI;

public abstract class HttpRequestBase extends AbstractExecutionAwareRequest implements Configurable, HttpUriRequest {
    private ProtocolVersion f6563c;
    private URI f6564d;
    private RequestConfig f6565e;

    public abstract String m10649a();

    public void m10650a(ProtocolVersion protocolVersion) {
        this.f6563c = protocolVersion;
    }

    public ProtocolVersion m10653d() {
        return this.f6563c != null ? this.f6563c : HttpProtocolParams.m12682b(m10636g());
    }

    public URI m10655k() {
        return this.f6564d;
    }

    public RequestLine m10654h() {
        String a = m10649a();
        ProtocolVersion d = m10653d();
        URI k = m10655k();
        String str = null;
        if (k != null) {
            str = k.toASCIIString();
        }
        if (str == null || str.length() == 0) {
            str = "/";
        }
        return new BasicRequestLine(a, str, d);
    }

    public RequestConfig d_() {
        return this.f6565e;
    }

    public void m10651a(RequestConfig requestConfig) {
        this.f6565e = requestConfig;
    }

    public void m10652a(URI uri) {
        this.f6564d = uri;
    }

    public String toString() {
        return m10649a() + " " + m10655k() + " " + m10653d();
    }
}
