package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.RequestLine;
import cz.msebera.android.httpclient.util.Args;
import java.io.Serializable;

public class BasicRequestLine implements RequestLine, Serializable, Cloneable {
    private final ProtocolVersion f7888a;
    private final String f7889b;
    private final String f7890c;

    public BasicRequestLine(String str, String str2, ProtocolVersion protocolVersion) {
        this.f7889b = (String) Args.m12722a((Object) str, "Method");
        this.f7890c = (String) Args.m12722a((Object) str2, "URI");
        this.f7888a = (ProtocolVersion) Args.m12722a((Object) protocolVersion, "Version");
    }

    public String m12629a() {
        return this.f7889b;
    }

    public ProtocolVersion m12630b() {
        return this.f7888a;
    }

    public String m12631c() {
        return this.f7890c;
    }

    public String toString() {
        return BasicLineFormatter.f7878b.m12609a(null, (RequestLine) this).toString();
    }

    public Object clone() {
        return super.clone();
    }
}
