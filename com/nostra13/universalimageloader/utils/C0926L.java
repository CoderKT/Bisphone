package com.nostra13.universalimageloader.utils;

import android.util.Log;
import com.nostra13.universalimageloader.core.ImageLoader;

/* renamed from: com.nostra13.universalimageloader.utils.L */
public final class C0926L {
    private static volatile boolean f7092a;
    private static volatile boolean f7093b;

    static {
        f7092a = false;
        f7093b = true;
    }

    public static void m11274a(boolean z) {
        f7092a = z;
    }

    public static void m11272a(String str, Object... objArr) {
        if (f7092a) {
            C0926L.m11271a(3, null, str, objArr);
        }
    }

    public static void m11275b(String str, Object... objArr) {
        C0926L.m11271a(4, null, str, objArr);
    }

    public static void m11276c(String str, Object... objArr) {
        C0926L.m11271a(5, null, str, objArr);
    }

    public static void m11273a(Throwable th) {
        C0926L.m11271a(6, th, null, new Object[0]);
    }

    public static void m11277d(String str, Object... objArr) {
        C0926L.m11271a(6, null, str, objArr);
    }

    private static void m11271a(int i, Throwable th, String str, Object... objArr) {
        if (f7093b) {
            String format;
            if (objArr.length > 0) {
                format = String.format(str, objArr);
            } else {
                format = str;
            }
            if (th != null) {
                if (format == null) {
                    format = th.getMessage();
                }
                String stackTraceString = Log.getStackTraceString(th);
                format = String.format("%1$s\n%2$s", new Object[]{format, stackTraceString});
            }
            Log.println(i, ImageLoader.f6906a, format);
        }
    }
}
