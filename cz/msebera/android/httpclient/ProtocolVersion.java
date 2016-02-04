package cz.msebera.android.httpclient;

import cz.msebera.android.httpclient.util.Args;
import java.io.Serializable;

public class ProtocolVersion implements Serializable, Cloneable {
    protected final String f7205d;
    protected final int f7206e;
    protected final int f7207f;

    public ProtocolVersion(String str, int i, int i2) {
        this.f7205d = (String) Args.m12722a((Object) str, "Protocol name");
        this.f7206e = Args.m12726b(i, "Protocol minor version");
        this.f7207f = Args.m12726b(i2, "Protocol minor version");
    }

    public final String m11396a() {
        return this.f7205d;
    }

    public final int m11398b() {
        return this.f7206e;
    }

    public final int m11400c() {
        return this.f7207f;
    }

    public ProtocolVersion m11395a(int i, int i2) {
        return (i == this.f7206e && i2 == this.f7207f) ? this : new ProtocolVersion(this.f7205d, i, i2);
    }

    public final int hashCode() {
        return (this.f7205d.hashCode() ^ (this.f7206e * 100000)) ^ this.f7207f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProtocolVersion)) {
            return false;
        }
        ProtocolVersion protocolVersion = (ProtocolVersion) obj;
        if (this.f7205d.equals(protocolVersion.f7205d) && this.f7206e == protocolVersion.f7206e && this.f7207f == protocolVersion.f7207f) {
            return true;
        }
        return false;
    }

    public boolean m11397a(ProtocolVersion protocolVersion) {
        return protocolVersion != null && this.f7205d.equals(protocolVersion.f7205d);
    }

    public int m11399b(ProtocolVersion protocolVersion) {
        Args.m12722a((Object) protocolVersion, "Protocol version");
        Args.m12725a(this.f7205d.equals(protocolVersion.f7205d), "Versions for different protocols cannot be compared: %s %s", this, protocolVersion);
        int b = m11398b() - protocolVersion.m11398b();
        if (b == 0) {
            return m11400c() - protocolVersion.m11400c();
        }
        return b;
    }

    public final boolean m11401c(ProtocolVersion protocolVersion) {
        return m11397a(protocolVersion) && m11399b(protocolVersion) <= 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.f7205d);
        stringBuilder.append('/');
        stringBuilder.append(Integer.toString(this.f7206e));
        stringBuilder.append('.');
        stringBuilder.append(Integer.toString(this.f7207f));
        return stringBuilder.toString();
    }

    public Object clone() {
        return super.clone();
    }
}
