package cz.msebera.android.httpclient.util;

public class Asserts {
    public static void m12729a(boolean z, String str) {
        if (!z) {
            throw new IllegalStateException(str);
        }
    }

    public static void m12728a(Object obj, String str) {
        if (obj == null) {
            throw new IllegalStateException(str + " is null");
        }
    }
}
