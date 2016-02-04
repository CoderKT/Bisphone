package cz.msebera.android.httpclient.client.methods;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import cz.msebera.android.httpclient.client.utils.CloneUtils;

public abstract class HttpEntityEnclosingRequestBase extends HttpRequestBase implements HttpEntityEnclosingRequest {
    private HttpEntity f6566c;

    public HttpEntity m10660c() {
        return this.f6566c;
    }

    public void m10658a(HttpEntity httpEntity) {
        this.f6566c = httpEntity;
    }

    public boolean m10659b() {
        Header c = m10631c("Expect");
        return c != null && "100-continue".equalsIgnoreCase(c.m11362d());
    }

    public Object clone() {
        HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase = (HttpEntityEnclosingRequestBase) super.clone();
        if (this.f6566c != null) {
            httpEntityEnclosingRequestBase.f6566c = (HttpEntity) CloneUtils.m11565a(this.f6566c);
        }
        return httpEntityEnclosingRequestBase;
    }
}
