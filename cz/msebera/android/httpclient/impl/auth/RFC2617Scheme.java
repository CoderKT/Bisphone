package cz.msebera.android.httpclient.impl.auth;

import cz.msebera.android.httpclient.Consts;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.auth.MalformedChallengeException;
import cz.msebera.android.httpclient.message.BasicHeaderValueParser;
import cz.msebera.android.httpclient.message.ParserCursor;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public abstract class RFC2617Scheme extends AuthSchemeBase {
    private final Map<String, String> f7460a;
    private final Charset f7461b;

    public RFC2617Scheme(Charset charset) {
        this.f7460a = new HashMap();
        if (charset == null) {
            charset = Consts.f7198b;
        }
        this.f7461b = charset;
    }

    public Charset m11891g() {
        return this.f7461b;
    }

    String m11887a(HttpRequest httpRequest) {
        String str = (String) httpRequest.m10622g().m12084a("http.auth.credential-charset");
        if (str == null) {
            return m11891g().name();
        }
        return str;
    }

    protected void m11889a(CharArrayBuffer charArrayBuffer, int i, int i2) {
        HeaderElement[] a = BasicHeaderValueParser.f7865b.m12585a(charArrayBuffer, new ParserCursor(i, charArrayBuffer.m12757c()));
        if (a.length == 0) {
            throw new MalformedChallengeException("Authentication challenge is empty");
        }
        this.f7460a.clear();
        for (HeaderElement headerElement : a) {
            this.f7460a.put(headerElement.m11368a().toLowerCase(Locale.ENGLISH), headerElement.m11369b());
        }
    }

    protected Map<String, String> m11892h() {
        return this.f7460a;
    }

    public String m11888a(String str) {
        if (str == null) {
            return null;
        }
        return (String) this.f7460a.get(str.toLowerCase(Locale.ENGLISH));
    }

    public String m11890b() {
        return m11888a("realm");
    }
}
