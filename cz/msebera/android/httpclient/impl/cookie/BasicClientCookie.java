package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.cookie.ClientCookie;
import cz.msebera.android.httpclient.cookie.SetCookie;
import cz.msebera.android.httpclient.util.Args;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class BasicClientCookie implements ClientCookie, SetCookie, Serializable, Cloneable {
    private final String f7748a;
    private Map<String, String> f7749b;
    private String f7750c;
    private String f7751d;
    private String f7752e;
    private Date f7753f;
    private String f7754g;
    private boolean f7755h;
    private int f7756i;

    public BasicClientCookie(String str, String str2) {
        Args.m12722a((Object) str, "Name");
        this.f7748a = str;
        this.f7749b = new HashMap();
        this.f7750c = str2;
    }

    public String m12392a() {
        return this.f7748a;
    }

    public String m12398b() {
        return this.f7750c;
    }

    public void m12402c(String str) {
        this.f7751d = str;
    }

    public Date m12401c() {
        return this.f7753f;
    }

    public void m12399b(Date date) {
        this.f7753f = date;
    }

    public String m12403d() {
        return this.f7752e;
    }

    public void m12404d(String str) {
        if (str != null) {
            this.f7752e = str.toLowerCase(Locale.ENGLISH);
        } else {
            this.f7752e = null;
        }
    }

    public String m12405e() {
        return this.f7754g;
    }

    public void m12406e(String str) {
        this.f7754g = str;
    }

    public boolean m12408g() {
        return this.f7755h;
    }

    public void m12396a(boolean z) {
        this.f7755h = z;
    }

    public int[] m12407f() {
        return null;
    }

    public int m12409h() {
        return this.f7756i;
    }

    public void m12394a(int i) {
        this.f7756i = i;
    }

    public boolean m12397a(Date date) {
        Args.m12722a((Object) date, "Date");
        return this.f7753f != null && this.f7753f.getTime() <= date.getTime();
    }

    public void m12395a(String str, String str2) {
        this.f7749b.put(str, str2);
    }

    public String m12393a(String str) {
        return (String) this.f7749b.get(str);
    }

    public boolean m12400b(String str) {
        return this.f7749b.get(str) != null;
    }

    public Object clone() {
        BasicClientCookie basicClientCookie = (BasicClientCookie) super.clone();
        basicClientCookie.f7749b = new HashMap(this.f7749b);
        return basicClientCookie;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[version: ");
        stringBuilder.append(Integer.toString(this.f7756i));
        stringBuilder.append("]");
        stringBuilder.append("[name: ");
        stringBuilder.append(this.f7748a);
        stringBuilder.append("]");
        stringBuilder.append("[value: ");
        stringBuilder.append(this.f7750c);
        stringBuilder.append("]");
        stringBuilder.append("[domain: ");
        stringBuilder.append(this.f7752e);
        stringBuilder.append("]");
        stringBuilder.append("[path: ");
        stringBuilder.append(this.f7754g);
        stringBuilder.append("]");
        stringBuilder.append("[expiry: ");
        stringBuilder.append(this.f7753f);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
