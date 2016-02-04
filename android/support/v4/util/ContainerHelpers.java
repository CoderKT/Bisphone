package android.support.v4.util;

class ContainerHelpers {
    static final int[] f381a;
    static final long[] f382b;
    static final Object[] f383c;

    static {
        f381a = new int[0];
        f382b = new long[0];
        f383c = new Object[0];
    }

    public static int m757a(int i) {
        return m762c(i * 4) / 4;
    }

    public static int m761b(int i) {
        return m762c(i * 8) / 8;
    }

    public static int m762c(int i) {
        for (int i2 = 4; i2 < 32; i2++) {
            if (i <= (1 << i2) - 12) {
                return (1 << i2) - 12;
            }
        }
        return i;
    }

    public static boolean m760a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    static int m758a(int[] iArr, int i, int i2) {
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

    static int m759a(long[] jArr, int i, long j) {
        int i2 = 0;
        int i3 = i - 1;
        while (i2 <= i3) {
            int i4 = (i2 + i3) >>> 1;
            long j2 = jArr[i4];
            if (j2 < j) {
                i2 = i4 + 1;
            } else if (j2 <= j) {
                return i4;
            } else {
                i3 = i4 - 1;
            }
        }
        return i2 ^ -1;
    }
}
