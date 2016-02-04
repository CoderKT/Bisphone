package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.cookie.SetCookie2;
import java.util.Date;

public class BasicClientCookie2 extends BasicClientCookie implements SetCookie2 {
    private String f7757a;
    private int[] f7758b;
    private boolean f7759c;

    public BasicClientCookie2(String str, String str2) {
        super(str, str2);
    }

    public int[] m12413f() {
        return this.f7758b;
    }

    public void m12410a(int[] iArr) {
        this.f7758b = iArr;
    }

    public void a_(String str) {
        this.f7757a = str;
    }

    public void m12412b(boolean z) {
        this.f7759c = z;
    }

    public boolean m12411a(Date date) {
        return this.f7759c || super.m12397a(date);
    }

    public Object clone() {
        BasicClientCookie2 basicClientCookie2 = (BasicClientCookie2) super.clone();
        if (this.f7758b != null) {
            basicClientCookie2.f7758b = (int[]) this.f7758b.clone();
        }
        return basicClientCookie2;
    }
}
