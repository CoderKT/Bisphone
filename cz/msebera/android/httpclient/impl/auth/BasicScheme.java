package cz.msebera.android.httpclient.impl.auth;

import cz.msebera.android.httpclient.Consts;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.auth.Credentials;
import cz.msebera.android.httpclient.extras.Base64;
import cz.msebera.android.httpclient.message.BufferedHeader;
import cz.msebera.android.httpclient.protocol.BasicHttpContext;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import cz.msebera.android.httpclient.util.EncodingUtils;
import java.nio.charset.Charset;
import org.jivesoftware.smackx.xdatavalidation.packet.ValidateElement.BasicValidateElement;

public class BasicScheme extends RFC2617Scheme {
    private boolean f7462a;

    public BasicScheme(Charset charset) {
        super(charset);
        this.f7462a = false;
    }

    public BasicScheme() {
        this(Consts.f7198b);
    }

    public String m11895a() {
        return BasicValidateElement.METHOD;
    }

    public void m11896a(Header header) {
        super.m11884a(header);
        this.f7462a = true;
    }

    public boolean m11898d() {
        return this.f7462a;
    }

    public boolean m11897c() {
        return false;
    }

    @Deprecated
    public Header m11893a(Credentials credentials, HttpRequest httpRequest) {
        return m11894a(credentials, httpRequest, new BasicHttpContext());
    }

    public Header m11894a(Credentials credentials, HttpRequest httpRequest, HttpContext httpContext) {
        Args.m12722a((Object) credentials, "Credentials");
        Args.m12722a((Object) httpRequest, "HTTP request");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(credentials.m11441a().getName());
        stringBuilder.append(":");
        stringBuilder.append(credentials.m11442b() == null ? "null" : credentials.m11442b());
        byte[] b = Base64.m11832b(EncodingUtils.m12763a(stringBuilder.toString(), m11887a(httpRequest)), 2);
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(32);
        if (m11886e()) {
            charArrayBuffer.m12751a("Proxy-Authorization");
        } else {
            charArrayBuffer.m12751a("Authorization");
        }
        charArrayBuffer.m12751a(": Basic ");
        charArrayBuffer.m12752a(b, 0, b.length);
        return new BufferedHeader(charArrayBuffer);
    }
}
