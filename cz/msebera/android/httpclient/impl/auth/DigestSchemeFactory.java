package cz.msebera.android.httpclient.impl.auth;

import cz.msebera.android.httpclient.auth.AuthScheme;
import cz.msebera.android.httpclient.auth.AuthSchemeFactory;
import cz.msebera.android.httpclient.auth.AuthSchemeProvider;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.HttpContext;
import java.nio.charset.Charset;

public class DigestSchemeFactory implements AuthSchemeFactory, AuthSchemeProvider {
    private final Charset f7471a;

    public DigestSchemeFactory(Charset charset) {
        this.f7471a = charset;
    }

    public DigestSchemeFactory() {
        this(null);
    }

    public AuthScheme m11911a(HttpParams httpParams) {
        return new DigestScheme();
    }

    public AuthScheme m11912a(HttpContext httpContext) {
        return new DigestScheme(this.f7471a);
    }
}
