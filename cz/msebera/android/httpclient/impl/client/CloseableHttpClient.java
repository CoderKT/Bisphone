package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.client.utils.URIUtils;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import java.io.Closeable;
import java.net.URI;

public abstract class CloseableHttpClient implements Closeable {
    public HttpClientAndroidLog f7540b;

    protected abstract CloseableHttpResponse m12015a(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext);

    public CloseableHttpClient() {
        this.f7540b = new HttpClientAndroidLog(getClass());
    }

    public CloseableHttpResponse m12016a(HttpUriRequest httpUriRequest, HttpContext httpContext) {
        Args.m12722a((Object) httpUriRequest, "HTTP request");
        return m12015a(m12014a(httpUriRequest), httpUriRequest, httpContext);
    }

    private static HttpHost m12014a(HttpUriRequest httpUriRequest) {
        HttpHost httpHost = null;
        URI k = httpUriRequest.m10648k();
        if (k.isAbsolute()) {
            httpHost = URIUtils.m11592b(k);
            if (httpHost == null) {
                throw new ClientProtocolException("URI does not specify a valid host name: " + k);
            }
        }
        return httpHost;
    }
}
