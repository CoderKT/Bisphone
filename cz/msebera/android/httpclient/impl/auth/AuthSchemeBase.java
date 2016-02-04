package cz.msebera.android.httpclient.impl.auth;

import cz.msebera.android.httpclient.FormattedHeader;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.auth.ChallengeState;
import cz.msebera.android.httpclient.auth.ContextAwareAuthScheme;
import cz.msebera.android.httpclient.auth.Credentials;
import cz.msebera.android.httpclient.auth.MalformedChallengeException;
import cz.msebera.android.httpclient.protocol.HTTP;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.util.Locale;

public abstract class AuthSchemeBase implements ContextAwareAuthScheme {
    private ChallengeState f7459a;

    protected abstract void m11885a(CharArrayBuffer charArrayBuffer, int i, int i2);

    public void m11884a(Header header) {
        CharArrayBuffer a;
        int b;
        Args.m12722a((Object) header, "Header");
        String c = header.m11361c();
        if (c.equalsIgnoreCase("WWW-Authenticate")) {
            this.f7459a = ChallengeState.TARGET;
        } else if (c.equalsIgnoreCase("Proxy-Authenticate")) {
            this.f7459a = ChallengeState.PROXY;
        } else {
            throw new MalformedChallengeException("Unexpected header name: " + c);
        }
        if (header instanceof FormattedHeader) {
            a = ((FormattedHeader) header).m11364a();
            b = ((FormattedHeader) header).m11365b();
        } else {
            c = header.m11362d();
            if (c == null) {
                throw new MalformedChallengeException("Header value is null");
            }
            a = new CharArrayBuffer(c.length());
            a.m12751a(c);
            b = 0;
        }
        while (b < a.m12757c() && HTTP.m12703a(a.m12744a(b))) {
            b++;
        }
        int i = b;
        while (i < a.m12757c() && !HTTP.m12703a(a.m12744a(i))) {
            i++;
        }
        c = a.m12746a(b, i);
        if (c.equalsIgnoreCase(m11416a())) {
            m11885a(a, i, a.m12757c());
            return;
        }
        throw new MalformedChallengeException("Invalid scheme identifier: " + c);
    }

    public Header m11883a(Credentials credentials, HttpRequest httpRequest, HttpContext httpContext) {
        return m11415a(credentials, httpRequest);
    }

    public boolean m11886e() {
        return this.f7459a != null && this.f7459a == ChallengeState.PROXY;
    }

    public String toString() {
        String a = m11416a();
        if (a != null) {
            return a.toUpperCase(Locale.ENGLISH);
        }
        return super.toString();
    }
}
