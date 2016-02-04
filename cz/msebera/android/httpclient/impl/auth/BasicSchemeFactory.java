package cz.msebera.android.httpclient.impl.auth;

import cz.msebera.android.httpclient.auth.AuthScheme;
import cz.msebera.android.httpclient.auth.AuthSchemeFactory;
import cz.msebera.android.httpclient.auth.AuthSchemeProvider;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.HttpContext;
import java.nio.charset.Charset;

public class BasicSchemeFactory implements AuthSchemeFactory, AuthSchemeProvider {
    private final Charset f7463a;

    public BasicSchemeFactory(Charset charset) {
        this.f7463a = charset;
    }

    public BasicSchemeFactory() {
        this(null);
    }

    public AuthScheme m11899a(HttpParams httpParams) {
        return new BasicScheme();
    }

    public AuthScheme m11900a(HttpContext httpContext) {
        return new BasicScheme(this.f7463a);
    }
}
