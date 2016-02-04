package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.RequestLine;
import cz.msebera.android.httpclient.util.Args;

public class BasicHttpRequest extends AbstractHttpMessage implements HttpRequest {
    private final String f7867c;
    private final String f7868d;
    private RequestLine f7869e;

    public BasicHttpRequest(String str, String str2, ProtocolVersion protocolVersion) {
        this(new BasicRequestLine(str, str2, protocolVersion));
    }

    public BasicHttpRequest(RequestLine requestLine) {
        this.f7869e = (RequestLine) Args.m12722a((Object) requestLine, "Request line");
        this.f7867c = requestLine.m11406a();
        this.f7868d = requestLine.m11408c();
    }

    public ProtocolVersion m12596d() {
        return m12597h().m11407b();
    }

    public RequestLine m12597h() {
        if (this.f7869e == null) {
            this.f7869e = new BasicRequestLine(this.f7867c, this.f7868d, HttpVersion.f7210c);
        }
        return this.f7869e;
    }

    public String toString() {
        return this.f7867c + ' ' + this.f7868d + ' ' + this.a;
    }
}
