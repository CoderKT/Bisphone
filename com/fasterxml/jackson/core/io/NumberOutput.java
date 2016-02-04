package com.fasterxml.jackson.core.io;

public final class NumberOutput {
    private static int BILLION;
    static final char[] FULL_3;
    static final byte[] FULL_TRIPLETS_B;
    static final char[] LEAD_3;
    private static long MAX_INT_AS_LONG;
    private static int MILLION;
    private static long MIN_INT_AS_LONG;
    static final String SMALLEST_LONG;
    private static long TEN_BILLION_L;
    private static long THOUSAND_L;
    static final String[] sSmallIntStrs;
    static final String[] sSmallIntStrs2;

    static {
        MILLION = 1000000;
        BILLION = 1000000000;
        TEN_BILLION_L = 10000000000L;
        THOUSAND_L = 1000;
        MIN_INT_AS_LONG = -2147483648L;
        MAX_INT_AS_LONG = 2147483647L;
        SMALLEST_LONG = String.valueOf(Long.MIN_VALUE);
        LEAD_3 = new char[4000];
        FULL_3 = new char[4000];
        int i = 0;
        for (int i2 = 0; i2 < 10; i2++) {
            char c;
            char c2 = (char) (i2 + 48);
            if (i2 == 0) {
                c = '\u0000';
            } else {
                c = c2;
            }
            int i3 = 0;
            while (i3 < 10) {
                char c3;
                char c4 = (char) (i3 + 48);
                if (i2 == 0 && i3 == 0) {
                    c3 = '\u0000';
                } else {
                    c3 = c4;
                }
                int i4 = i;
                for (i = 0; i < 10; i++) {
                    char c5 = (char) (i + 48);
                    LEAD_3[i4] = c;
                    LEAD_3[i4 + 1] = c3;
                    LEAD_3[i4 + 2] = c5;
                    FULL_3[i4] = c2;
                    FULL_3[i4 + 1] = c4;
                    FULL_3[i4 + 2] = c5;
                    i4 += 4;
                }
                i3++;
                i = i4;
            }
        }
        FULL_TRIPLETS_B = new byte[4000];
        for (int i5 = 0; i5 < 4000; i5++) {
            FULL_TRIPLETS_B[i5] = (byte) FULL_3[i5];
        }
        sSmallIntStrs = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        sSmallIntStrs2 = new String[]{"-1", "-2", "-3", "-4", "-5", "-6", "-7", "-8", "-9", "-10"};
    }

    public static String toString(int i) {
        if (i < sSmallIntStrs.length) {
            if (i >= 0) {
                return sSmallIntStrs[i];
            }
            int i2 = (-i) - 1;
            if (i2 < sSmallIntStrs2.length) {
                return sSmallIntStrs2[i2];
            }
        }
        return Integer.toString(i);
    }

    public static String toString(long j) {
        if (j > 2147483647L || j < -2147483648L) {
            return Long.toString(j);
        }
        return toString((int) j);
    }

    public static String toString(double d) {
        return Double.toString(d);
    }
}
