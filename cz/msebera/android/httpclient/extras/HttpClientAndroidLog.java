package cz.msebera.android.httpclient.extras;

import android.util.Log;

public class HttpClientAndroidLog {
    private String f7434a;
    private boolean f7435b;
    private boolean f7436c;
    private boolean f7437d;
    private boolean f7438e;
    private boolean f7439f;

    public HttpClientAndroidLog(Object obj) {
        this.f7434a = obj.toString();
        this.f7435b = false;
        this.f7436c = false;
        this.f7437d = false;
        this.f7438e = false;
        this.f7439f = false;
    }

    public boolean m11836a() {
        return this.f7435b;
    }

    public void m11834a(Object obj) {
        if (m11836a()) {
            Log.d(this.f7434a, obj.toString());
        }
    }

    public void m11835a(Object obj, Throwable th) {
        if (m11836a()) {
            Log.d(this.f7434a, obj.toString(), th);
        }
    }

    public boolean m11839b() {
        return this.f7436c;
    }

    public void m11837b(Object obj) {
        if (m11839b()) {
            Log.e(this.f7434a, obj.toString());
        }
    }

    public void m11838b(Object obj, Throwable th) {
        if (m11839b()) {
            Log.e(this.f7434a, obj.toString(), th);
        }
    }

    public boolean m11842c() {
        return this.f7438e;
    }

    public void m11840c(Object obj) {
        if (m11842c()) {
            Log.w(this.f7434a, obj.toString());
        }
    }

    public void m11841c(Object obj, Throwable th) {
        if (m11842c()) {
            Log.w(this.f7434a, obj.toString(), th);
        }
    }

    public boolean m11844d() {
        return this.f7439f;
    }

    public void m11843d(Object obj) {
        if (m11844d()) {
            Log.i(this.f7434a, obj.toString());
        }
    }
}
