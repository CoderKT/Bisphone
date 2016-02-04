package io.fabric.sdk.android;

import android.util.Log;

public class DefaultLogger implements Logger {
    private int f8139a;

    public DefaultLogger(int i) {
        this.f8139a = i;
    }

    public DefaultLogger() {
        this.f8139a = 4;
    }

    public boolean m12879a(String str, int i) {
        return this.f8139a <= i;
    }

    public void m12878a(String str, String str2, Throwable th) {
        if (m12879a(str, 3)) {
            Log.d(str, str2, th);
        }
    }

    public void m12881b(String str, String str2, Throwable th) {
        if (m12879a(str, 4)) {
            Log.i(str, str2, th);
        }
    }

    public void m12883c(String str, String str2, Throwable th) {
        if (m12879a(str, 5)) {
            Log.w(str, str2, th);
        }
    }

    public void m12885d(String str, String str2, Throwable th) {
        if (m12879a(str, 6)) {
            Log.e(str, str2, th);
        }
    }

    public void m12877a(String str, String str2) {
        m12878a(str, str2, null);
    }

    public void m12880b(String str, String str2) {
        m12881b(str, str2, null);
    }

    public void m12882c(String str, String str2) {
        m12883c(str, str2, null);
    }

    public void m12884d(String str, String str2) {
        m12885d(str, str2, null);
    }

    public void m12875a(int i, String str, String str2) {
        m12876a(i, str, str2, false);
    }

    public void m12876a(int i, String str, String str2, boolean z) {
        if (z || m12879a(str, i)) {
            Log.println(i, str, str2);
        }
    }
}
