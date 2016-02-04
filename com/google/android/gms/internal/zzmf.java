package com.google.android.gms.internal;

public class zzmf {
    public static final int[] f6131a;
    public static final long[] f6132b;
    public static final Object[] f6133c;

    static {
        f6131a = new int[0];
        f6132b = new long[0];
        f6133c = new Object[0];
    }

    public static int m9292a(int[] iArr, int i, int i2) {
        int i3 = 0;
        int i4 = i - 1;
        while (i3 <= i4) {
            int i5 = (i3 + i4) >>> 1;
            int i6 = iArr[i5];
            if (i6 < i2) {
                i3 = i5 + 1;
            } else if (i6 <= i2) {
                return i5;
            } else {
                i4 = i5 - 1;
            }
        }
        return i3 ^ -1;
    }

    public static boolean m9293a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }
}
