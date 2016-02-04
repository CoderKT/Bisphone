package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.StatusLine;
import cz.msebera.android.httpclient.util.Args;
import java.io.Serializable;

public class BasicStatusLine implements StatusLine, Serializable, Cloneable {
    private final ProtocolVersion f7891a;
    private final int f7892b;
    private final String f7893c;

    public BasicStatusLine(ProtocolVersion protocolVersion, int i, String str) {
        this.f7891a = (ProtocolVersion) Args.m12722a((Object) protocolVersion, "Version");
        this.f7892b = Args.m12726b(i, "Status code");
        this.f7893c = str;
    }

    public int m12633b() {
        return this.f7892b;
    }

    public ProtocolVersion m12632a() {
        return this.f7891a;
    }

    public String m12634c() {
        return this.f7893c;
    }

    public String toString() {
        return BasicLineFormatter.f7878b.m12610a(null, (StatusLine) this).toString();
    }

    public Object clone() {
        return super.clone();
    }
}
