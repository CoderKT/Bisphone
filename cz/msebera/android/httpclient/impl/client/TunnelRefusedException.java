package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpResponse;

@Deprecated
public class TunnelRefusedException extends HttpException {
    private final HttpResponse f7628a;

    public TunnelRefusedException(String str, HttpResponse httpResponse) {
        super(str);
        this.f7628a = httpResponse;
    }

    public HttpResponse m12168a() {
        return this.f7628a;
    }
}
