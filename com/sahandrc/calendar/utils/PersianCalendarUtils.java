package com.sahandrc.calendar.utils;

public class PersianCalendarUtils {
    public static long m11351a(long j, int i, int i2) {
        return (((long) (i < 7 ? i * 31 : (i * 30) + 6)) + ((1029983 * ((long) Math.floor(((double) (j - 474)) / 2820.0d))) + (((365 * ((m11349a((double) (j - 474), 2820.0d) + 474) - 1)) + ((long) Math.floor(((double) ((682 * (m11349a((double) (j - 474), 2820.0d) + 474)) - 110)) / 2816.0d))) + 1948320))) + ((long) i2);
    }

    public static long m11350a(long j) {
        long a = j - m11351a(475, 0, 1);
        long a2 = m11349a((double) a, 1029983.0d);
        a = ((((long) Math.floor(((double) a) / 1029983.0d)) * 2820) + 474) + (a2 != 1029982 ? (long) Math.floor(((((double) a2) * 2816.0d) + 1031337.0d) / 1028522.0d) : 2820);
        a2 = (1 + j) - m11351a(a, 0, 1);
        int ceil = (int) (a2 > 186 ? Math.ceil(((double) (a2 - 6)) / 30.0d) - 1.0d : Math.ceil(((double) a2) / 31.0d) - 1.0d);
        return ((long) ((int) (j - (m11351a(a, ceil, 1) - 1)))) | ((a << 16) | ((long) (ceil << 8)));
    }

    public static long m11349a(double d, double d2) {
        return (long) (d - (Math.floor(d / d2) * d2));
    }
}
