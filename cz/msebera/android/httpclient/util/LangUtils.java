package cz.msebera.android.httpclient.util;

public final class LangUtils {
    public static int m12766a(int i, int i2) {
        return (i * 37) + i2;
    }

    public static int m12768a(int i, boolean z) {
        return m12766a(i, z ? 1 : 0);
    }

    public static int m12767a(int i, Object obj) {
        return m12766a(i, obj != null ? obj.hashCode() : 0);
    }

    public static boolean m12769a(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        } else {
            return obj.equals(obj2);
        }
    }

    public static boolean m12770a(Object[] objArr, Object[] objArr2) {
        if (objArr == null) {
            if (objArr2 == null) {
                return true;
            }
            return false;
        } else if (objArr2 == null || objArr.length != objArr2.length) {
            return false;
        } else {
            for (int i = 0; i < objArr.length; i++) {
                if (!m12769a(objArr[i], objArr2[i])) {
                    return false;
                }
            }
            return true;
        }
    }
}
