package cz.msebera.android.httpclient.client.methods;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.client.config.RequestConfig;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.utils.URIBuilder;
import cz.msebera.android.httpclient.message.HeaderGroup;
import cz.msebera.android.httpclient.protocol.HTTP;
import cz.msebera.android.httpclient.util.Args;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;

public class RequestBuilder {
    private String f7289a;
    private ProtocolVersion f7290b;
    private URI f7291c;
    private HeaderGroup f7292d;
    private HttpEntity f7293e;
    private LinkedList<NameValuePair> f7294f;
    private RequestConfig f7295g;

    class InternalEntityEclosingRequest extends HttpEntityEnclosingRequestBase {
        private final String f7287c;

        InternalEntityEclosingRequest(String str) {
            this.f7287c = str;
        }

        public String m11518a() {
            return this.f7287c;
        }
    }

    class InternalRequest extends HttpRequestBase {
        private final String f7288c;

        InternalRequest(String str) {
            this.f7288c = str;
        }

        public String m11519a() {
            return this.f7288c;
        }
    }

    RequestBuilder(String str) {
        this.f7289a = str;
    }

    RequestBuilder() {
        this(null);
    }

    public static RequestBuilder m11520a(HttpRequest httpRequest) {
        Args.m12722a((Object) httpRequest, "HTTP request");
        return new RequestBuilder().m11521b(httpRequest);
    }

    private RequestBuilder m11521b(HttpRequest httpRequest) {
        if (httpRequest != null) {
            this.f7289a = httpRequest.m10637h().m11406a();
            this.f7290b = httpRequest.m10637h().m11407b();
            if (httpRequest instanceof HttpUriRequest) {
                this.f7291c = ((HttpUriRequest) httpRequest).m10648k();
            } else {
                this.f7291c = URI.create(httpRequest.m10637h().m11408c());
            }
            if (this.f7292d == null) {
                this.f7292d = new HeaderGroup();
            }
            this.f7292d.m12650a();
            this.f7292d.m12652a(httpRequest.m10620e());
            if (httpRequest instanceof HttpEntityEnclosingRequest) {
                this.f7293e = ((HttpEntityEnclosingRequest) httpRequest).m10657c();
            } else {
                this.f7293e = null;
            }
            if (httpRequest instanceof Configurable) {
                this.f7295g = ((Configurable) httpRequest).d_();
            } else {
                this.f7295g = null;
            }
            this.f7294f = null;
        }
        return this;
    }

    public RequestBuilder m11523a(URI uri) {
        this.f7291c = uri;
        return this;
    }

    public HttpUriRequest m11522a() {
        URI uri;
        HttpUriRequest internalRequest;
        URI create = this.f7291c != null ? this.f7291c : URI.create("/");
        HttpEntity httpEntity = this.f7293e;
        if (this.f7294f == null || this.f7294f.isEmpty()) {
            uri = create;
        } else if (httpEntity == null && ("POST".equalsIgnoreCase(this.f7289a) || "PUT".equalsIgnoreCase(this.f7289a))) {
            httpEntity = new UrlEncodedFormEntity(this.f7294f, HTTP.f7912a);
            uri = create;
        } else {
            try {
                uri = new URIBuilder(create).m11580a(this.f7294f).m11581a();
            } catch (URISyntaxException e) {
                uri = create;
            }
        }
        if (httpEntity == null) {
            internalRequest = new InternalRequest(this.f7289a);
        } else {
            internalRequest = new InternalEntityEclosingRequest(this.f7289a);
            internalRequest.m10658a(httpEntity);
        }
        internalRequest.m10650a(this.f7290b);
        internalRequest.m10652a(uri);
        if (this.f7292d != null) {
            internalRequest.m10626a(this.f7292d.m12656b());
        }
        internalRequest.m10651a(this.f7295g);
        return internalRequest;
    }
}
