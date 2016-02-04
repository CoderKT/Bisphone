package cz.msebera.android.httpclient.impl;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpResponseFactory;
import cz.msebera.android.httpclient.ReasonPhraseCatalog;
import cz.msebera.android.httpclient.StatusLine;
import cz.msebera.android.httpclient.message.BasicHttpResponse;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import java.util.Locale;

public class DefaultHttpResponseFactory implements HttpResponseFactory {
    public static final DefaultHttpResponseFactory f7449a;
    protected final ReasonPhraseCatalog f7450b;

    static {
        f7449a = new DefaultHttpResponseFactory();
    }

    public DefaultHttpResponseFactory(ReasonPhraseCatalog reasonPhraseCatalog) {
        this.f7450b = (ReasonPhraseCatalog) Args.m12722a((Object) reasonPhraseCatalog, "Reason phrase catalog");
    }

    public DefaultHttpResponseFactory() {
        this(EnglishReasonPhraseCatalog.f7451a);
    }

    public HttpResponse m11865a(StatusLine statusLine, HttpContext httpContext) {
        Args.m12722a((Object) statusLine, "Status line");
        return new BasicHttpResponse(statusLine, this.f7450b, m11866a(httpContext));
    }

    protected Locale m11866a(HttpContext httpContext) {
        return Locale.getDefault();
    }
}
