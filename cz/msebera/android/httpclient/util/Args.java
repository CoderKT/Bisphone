package cz.msebera.android.httpclient.util;

import java.util.Collection;

public class Args {
    public static void m12724a(boolean z, String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    public static void m12725a(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(String.format(str, objArr));
        }
    }

    public static <T> T m12722a(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException(str + " may not be null");
    }

    public static <T extends CharSequence> T m12721a(T t, String str) {
        if (t == null) {
            throw new IllegalArgumentException(str + " may not be null");
        } else if (!TextUtils.m12771a(t)) {
            return t;
        } else {
            throw new IllegalArgumentException(str + " may not be empty");
        }
    }

    public static <T extends CharSequence> T m12727b(T t, String str) {
        if (t == null) {
            throw new IllegalArgumentException(str + " may not be null");
        } else if (!TextUtils.m12772b(t)) {
            return t;
        } else {
            throw new IllegalArgumentException(str + " may not be blank");
        }
    }

    public static <E, T extends Collection<E>> T m12723a(T t, String str) {
        if (t == null) {
            throw new IllegalArgumentException(str + " may not be null");
        } else if (!t.isEmpty()) {
            return t;
        } else {
            throw new IllegalArgumentException(str + " may not be empty");
        }
    }

    public static int m12719a(int i, String str) {
        if (i > 0) {
            return i;
        }
        throw new IllegalArgumentException(str + " may not be negative or zero");
    }

    public static int m12726b(int i, String str) {
        if (i >= 0) {
            return i;
        }
        throw new IllegalArgumentException(str + " may not be negative");
    }

    public static long m12720a(long j, String str) {
        if (j >= 0) {
            return j;
        }
        throw new IllegalArgumentException(str + " may not be negative");
    }
}
