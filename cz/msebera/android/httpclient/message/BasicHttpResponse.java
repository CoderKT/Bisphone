package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.ReasonPhraseCatalog;
import cz.msebera.android.httpclient.StatusLine;
import cz.msebera.android.httpclient.util.Args;
import java.util.Locale;

public class BasicHttpResponse extends AbstractHttpMessage implements HttpResponse {
    private StatusLine f7870c;
    private ProtocolVersion f7871d;
    private int f7872e;
    private String f7873f;
    private HttpEntity f7874g;
    private final ReasonPhraseCatalog f7875h;
    private Locale f7876i;

    public BasicHttpResponse(StatusLine statusLine, ReasonPhraseCatalog reasonPhraseCatalog, Locale locale) {
        this.f7870c = (StatusLine) Args.m12722a((Object) statusLine, "Status line");
        this.f7871d = statusLine.m11409a();
        this.f7872e = statusLine.m11410b();
        this.f7873f = statusLine.m11411c();
        this.f7875h = reasonPhraseCatalog;
        this.f7876i = locale;
    }

    public ProtocolVersion m12602d() {
        return this.f7871d;
    }

    public StatusLine m12598a() {
        if (this.f7870c == null) {
            String str;
            ProtocolVersion protocolVersion = this.f7871d != null ? this.f7871d : HttpVersion.f7210c;
            int i = this.f7872e;
            if (this.f7873f != null) {
                str = this.f7873f;
            } else {
                str = m12599a(this.f7872e);
            }
            this.f7870c = new BasicStatusLine(protocolVersion, i, str);
        }
        return this.f7870c;
    }

    public HttpEntity m12601b() {
        return this.f7874g;
    }

    public void m12600a(HttpEntity httpEntity) {
        this.f7874g = httpEntity;
    }

    protected String m12599a(int i) {
        if (this.f7875h == null) {
            return null;
        }
        Locale locale;
        ReasonPhraseCatalog reasonPhraseCatalog = this.f7875h;
        if (this.f7876i != null) {
            locale = this.f7876i;
        } else {
            locale = Locale.getDefault();
        }
        return reasonPhraseCatalog.m11405a(i, locale);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(m12598a());
        stringBuilder.append(' ');
        stringBuilder.append(this.a);
        if (this.f7874g != null) {
            stringBuilder.append(' ');
            stringBuilder.append(this.f7874g);
        }
        return stringBuilder.toString();
    }
}
