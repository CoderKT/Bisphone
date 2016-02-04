package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderIterator;
import cz.msebera.android.httpclient.HttpMessage;
import cz.msebera.android.httpclient.params.BasicHttpParams;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;

public abstract class AbstractHttpMessage implements HttpMessage {
    protected HeaderGroup f6559a;
    @Deprecated
    protected HttpParams f6560b;

    @Deprecated
    protected AbstractHttpMessage(HttpParams httpParams) {
        this.f6559a = new HeaderGroup();
        this.f6560b = httpParams;
    }

    protected AbstractHttpMessage() {
        this(null);
    }

    public boolean m10627a(String str) {
        return this.f6559a.m12659c(str);
    }

    public Header[] m10630b(String str) {
        return this.f6559a.m12653a(str);
    }

    public Header m10631c(String str) {
        return this.f6559a.m12654b(str);
    }

    public Header[] m10634e() {
        return this.f6559a.m12656b();
    }

    public void m10623a(Header header) {
        this.f6559a.m12651a(header);
    }

    public void m10625a(String str, String str2) {
        Args.m12722a((Object) str, "Header name");
        this.f6559a.m12651a(new BasicHeader(str, str2));
    }

    public void m10629b(String str, String str2) {
        Args.m12722a((Object) str, "Header name");
        this.f6559a.m12658c(new BasicHeader(str, str2));
    }

    public void m10626a(Header[] headerArr) {
        this.f6559a.m12652a(headerArr);
    }

    public void m10628b(Header header) {
        this.f6559a.m12655b(header);
    }

    public void m10632d(String str) {
        if (str != null) {
            HeaderIterator c = this.f6559a.m12657c();
            while (c.hasNext()) {
                if (str.equalsIgnoreCase(c.m11373a().m11361c())) {
                    c.remove();
                }
            }
        }
    }

    public HeaderIterator m10635f() {
        return this.f6559a.m12657c();
    }

    public HeaderIterator m10633e(String str) {
        return this.f6559a.m12660d(str);
    }

    @Deprecated
    public HttpParams m10636g() {
        if (this.f6560b == null) {
            this.f6560b = new BasicHttpParams();
        }
        return this.f6560b;
    }

    @Deprecated
    public void m10624a(HttpParams httpParams) {
        this.f6560b = (HttpParams) Args.m12722a((Object) httpParams, "HTTP parameters");
    }
}
