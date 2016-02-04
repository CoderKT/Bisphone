package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.LangUtils;
import java.io.Serializable;

public class BasicNameValuePair implements NameValuePair, Serializable, Cloneable {
    private final String f7886a;
    private final String f7887b;

    public BasicNameValuePair(String str, String str2) {
        this.f7886a = (String) Args.m12722a((Object) str, "Name");
        this.f7887b = str2;
    }

    public String m12627a() {
        return this.f7886a;
    }

    public String m12628b() {
        return this.f7887b;
    }

    public String toString() {
        if (this.f7887b == null) {
            return this.f7886a;
        }
        StringBuilder stringBuilder = new StringBuilder((this.f7886a.length() + 1) + this.f7887b.length());
        stringBuilder.append(this.f7886a);
        stringBuilder.append("=");
        stringBuilder.append(this.f7887b);
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NameValuePair)) {
            return false;
        }
        BasicNameValuePair basicNameValuePair = (BasicNameValuePair) obj;
        if (this.f7886a.equals(basicNameValuePair.f7886a) && LangUtils.m12769a(this.f7887b, basicNameValuePair.f7887b)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return LangUtils.m12767a(LangUtils.m12767a(17, this.f7886a), this.f7887b);
    }

    public Object clone() {
        return super.clone();
    }
}
