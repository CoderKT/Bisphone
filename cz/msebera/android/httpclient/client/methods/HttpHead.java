package cz.msebera.android.httpclient.client.methods;

import java.net.URI;

public class HttpHead extends HttpRequestBase {
    public HttpHead(URI uri) {
        m10652a(uri);
    }

    public HttpHead(String str) {
        m10652a(URI.create(str));
    }

    public String m11516a() {
        return "HEAD";
    }
}
