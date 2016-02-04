package app.messaging;

import android.database.Cursor;

public class LoadEarlier {
    private Cursor f3220a;
    private int f3221b;
    private boolean f3222c;
    private boolean f3223d;
    private int f3224e;
    private boolean f3225f;

    public int m5884a() {
        return this.f3224e;
    }

    public void m5885a(int i) {
        this.f3224e = i;
    }

    public Cursor m5888b() {
        return this.f3220a;
    }

    public void m5886a(Cursor cursor) {
        this.f3220a = cursor;
    }

    public boolean m5892c() {
        return this.f3225f;
    }

    public void m5889b(int i) {
        this.f3221b = i;
    }

    public boolean m5893d() {
        return this.f3222c;
    }

    public boolean m5894e() {
        return this.f3223d;
    }

    public void m5887a(boolean z) {
        this.f3222c = z;
    }

    public void m5890b(boolean z) {
        this.f3223d = z;
    }

    public void m5891c(boolean z) {
        this.f3225f = z;
    }

    public LoadEarlier m5895f() {
        this.f3220a = null;
        this.f3221b = 0;
        this.f3224e = -1;
        return this;
    }
}
