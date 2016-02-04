package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.LangUtils;

public class BasicHeaderElement implements HeaderElement, Cloneable {
    private final String f7854a;
    private final String f7855b;
    private final NameValuePair[] f7856c;

    public BasicHeaderElement(String str, String str2, NameValuePair[] nameValuePairArr) {
        this.f7854a = (String) Args.m12722a((Object) str, "Name");
        this.f7855b = str2;
        if (nameValuePairArr != null) {
            this.f7856c = nameValuePairArr;
        } else {
            this.f7856c = new NameValuePair[0];
        }
    }

    public BasicHeaderElement(String str, String str2) {
        this(str, str2, null);
    }

    public String m12569a() {
        return this.f7854a;
    }

    public String m12570b() {
        return this.f7855b;
    }

    public NameValuePair[] m12571c() {
        return (NameValuePair[]) this.f7856c.clone();
    }

    public int m12572d() {
        return this.f7856c.length;
    }

    public NameValuePair m12567a(int i) {
        return this.f7856c[i];
    }

    public NameValuePair m12568a(String str) {
        Args.m12722a((Object) str, "Name");
        for (NameValuePair nameValuePair : this.f7856c) {
            if (nameValuePair.m11403a().equalsIgnoreCase(str)) {
                return nameValuePair;
            }
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HeaderElement)) {
            return false;
        }
        BasicHeaderElement basicHeaderElement = (BasicHeaderElement) obj;
        if (this.f7854a.equals(basicHeaderElement.f7854a) && LangUtils.m12769a(this.f7855b, basicHeaderElement.f7855b) && LangUtils.m12770a(this.f7856c, basicHeaderElement.f7856c)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int a = LangUtils.m12767a(LangUtils.m12767a(17, this.f7854a), this.f7855b);
        for (Object a2 : this.f7856c) {
            a = LangUtils.m12767a(a, a2);
        }
        return a;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.f7854a);
        if (this.f7855b != null) {
            stringBuilder.append("=");
            stringBuilder.append(this.f7855b);
        }
        for (Object obj : this.f7856c) {
            stringBuilder.append("; ");
            stringBuilder.append(obj);
        }
        return stringBuilder.toString();
    }

    public Object clone() {
        return super.clone();
    }
}
