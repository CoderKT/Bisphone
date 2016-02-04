package cz.msebera.android.httpclient.impl.auth;

import cz.msebera.android.httpclient.auth.AuthScheme;
import cz.msebera.android.httpclient.auth.AuthSchemeFactory;
import cz.msebera.android.httpclient.auth.AuthSchemeProvider;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.HttpContext;

public class NTLMSchemeFactory implements AuthSchemeFactory, AuthSchemeProvider {
    public AuthScheme m12012a(HttpParams httpParams) {
        return new NTLMScheme();
    }

    public AuthScheme m12013a(HttpContext httpContext) {
        return new NTLMScheme();
    }
}
