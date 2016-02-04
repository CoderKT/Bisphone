package com.google.android.gms.common.internal;

import android.os.Looper;
import android.text.TextUtils;

public final class zzx {
    public static <T> T m8718a(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException("null reference");
    }

    public static <T> T m8719a(T t, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    public static String m8720a(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        throw new IllegalArgumentException("Given String is empty or null");
    }

    public static String m8721a(String str, Object obj) {
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        throw new IllegalArgumentException(String.valueOf(obj));
    }

    public static void m8722a(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    public static void m8723a(boolean z, Object obj) {
        if (!z) {
            throw new IllegalStateException(String.valueOf(obj));
        }
    }

    public static void m8724a(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(String.format(str, objArr));
        }
    }

    public static void m8725b(String str) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException(str);
        }
    }

    public static void m8726b(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void m8727b(boolean z, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }
}
