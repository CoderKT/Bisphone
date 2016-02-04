package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.util.Args;
import java.io.Serializable;

public class BasicHeader implements Header, Serializable, Cloneable {
    private final String f7852a;
    private final String f7853b;

    public BasicHeader(String str, String str2) {
        this.f7852a = (String) Args.m12722a((Object) str, "Name");
        this.f7853b = str2;
    }

    public String m12564c() {
        return this.f7852a;
    }

    public String m12565d() {
        return this.f7853b;
    }

    public String toString() {
        return BasicLineFormatter.f7878b.m12607a(null, (Header) this).toString();
    }

    public HeaderElement[] m12566e() {
        if (this.f7853b != null) {
            return BasicHeaderValueParser.m12588a(this.f7853b, null);
        }
        return new HeaderElement[0];
    }

    public Object clone() {
        return super.clone();
    }
}
