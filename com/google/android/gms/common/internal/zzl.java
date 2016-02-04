package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.ServiceConnection;

public abstract class zzl {
    private static final Object f5785a;
    private static zzl f5786b;

    static {
        f5785a = new Object();
    }

    public static zzl m8586a(Context context) {
        synchronized (f5785a) {
            if (f5786b == null) {
                f5786b = new zzm(context.getApplicationContext());
            }
        }
        return f5786b;
    }

    public abstract boolean m8587a(String str, ServiceConnection serviceConnection, String str2);

    public abstract void m8588b(String str, ServiceConnection serviceConnection, String str2);
}
