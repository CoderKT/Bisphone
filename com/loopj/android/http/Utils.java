package com.loopj.android.http;

class Utils {
    public static void m10778a(boolean z, String str) {
        if (!z) {
            throw new AssertionError(str);
        }
    }

    public static <T> T m10777a(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException(str + " should not be null!");
    }
}
