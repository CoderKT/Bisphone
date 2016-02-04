package com.fasterxml.jackson.core.io;

import java.math.BigDecimal;

public final class NumberInput {
    static final String MAX_LONG_STR;
    static final String MIN_LONG_STR_NO_SIGN;

    static {
        MIN_LONG_STR_NO_SIGN = String.valueOf(Long.MIN_VALUE).substring(1);
        MAX_LONG_STR = String.valueOf(Long.MAX_VALUE);
    }

    public static int parseInt(char[] cArr, int i, int i2) {
        int i3;
        int i4 = cArr[i] - 48;
        if (i2 > 4) {
            i3 = i + 1;
            i3++;
            i3++;
            i = i3 + 1;
            i4 = (((((((i4 * 10) + (cArr[i3] - 48)) * 10) + (cArr[i3] - 48)) * 10) + (cArr[i3] - 48)) * 10) + (cArr[i] - 48);
            i2 -= 4;
            if (i2 > 4) {
                i3 = i + 1;
                i3++;
                i3++;
                return (((((((i4 * 10) + (cArr[i3] - 48)) * 10) + (cArr[i3] - 48)) * 10) + (cArr[i3] - 48)) * 10) + (cArr[i3 + 1] - 48);
            }
        }
        if (i2 <= 1) {
            return i4;
        }
        i3 = i + 1;
        i4 = (i4 * 10) + (cArr[i3] - 48);
        if (i2 <= 2) {
            return i4;
        }
        i3++;
        i4 = (i4 * 10) + (cArr[i3] - 48);
        if (i2 > 3) {
            return (i4 * 10) + (cArr[i3 + 1] - 48);
        }
        return i4;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int parseInt(java.lang.String r8) {
        /*
        r2 = 0;
        r1 = 1;
        r6 = 57;
        r5 = 48;
        r0 = r8.charAt(r2);
        r4 = r8.length();
        r3 = 45;
        if (r0 != r3) goto L_0x0020;
    L_0x0012:
        r3 = r1;
    L_0x0013:
        if (r3 == 0) goto L_0x0033;
    L_0x0015:
        if (r4 == r1) goto L_0x001b;
    L_0x0017:
        r0 = 10;
        if (r4 <= r0) goto L_0x0022;
    L_0x001b:
        r0 = java.lang.Integer.parseInt(r8);
    L_0x001f:
        return r0;
    L_0x0020:
        r3 = r2;
        goto L_0x0013;
    L_0x0022:
        r0 = 2;
        r1 = r8.charAt(r1);
        r7 = r0;
        r0 = r1;
        r1 = r7;
    L_0x002a:
        if (r0 > r6) goto L_0x002e;
    L_0x002c:
        if (r0 >= r5) goto L_0x003c;
    L_0x002e:
        r0 = java.lang.Integer.parseInt(r8);
        goto L_0x001f;
    L_0x0033:
        r2 = 9;
        if (r4 <= r2) goto L_0x002a;
    L_0x0037:
        r0 = java.lang.Integer.parseInt(r8);
        goto L_0x001f;
    L_0x003c:
        r0 = r0 + -48;
        if (r1 >= r4) goto L_0x0082;
    L_0x0040:
        r2 = r1 + 1;
        r1 = r8.charAt(r1);
        if (r1 > r6) goto L_0x004a;
    L_0x0048:
        if (r1 >= r5) goto L_0x004f;
    L_0x004a:
        r0 = java.lang.Integer.parseInt(r8);
        goto L_0x001f;
    L_0x004f:
        r0 = r0 * 10;
        r1 = r1 + -48;
        r0 = r0 + r1;
        if (r2 >= r4) goto L_0x0082;
    L_0x0056:
        r1 = r2 + 1;
        r2 = r8.charAt(r2);
        if (r2 > r6) goto L_0x0060;
    L_0x005e:
        if (r2 >= r5) goto L_0x0065;
    L_0x0060:
        r0 = java.lang.Integer.parseInt(r8);
        goto L_0x001f;
    L_0x0065:
        r0 = r0 * 10;
        r2 = r2 + -48;
        r0 = r0 + r2;
        if (r1 >= r4) goto L_0x0082;
    L_0x006c:
        r2 = r1 + 1;
        r1 = r8.charAt(r1);
        if (r1 > r6) goto L_0x0076;
    L_0x0074:
        if (r1 >= r5) goto L_0x007b;
    L_0x0076:
        r0 = java.lang.Integer.parseInt(r8);
        goto L_0x001f;
    L_0x007b:
        r0 = r0 * 10;
        r1 = r1 + -48;
        r0 = r0 + r1;
        if (r2 < r4) goto L_0x0086;
    L_0x0082:
        if (r3 == 0) goto L_0x001f;
    L_0x0084:
        r0 = -r0;
        goto L_0x001f;
    L_0x0086:
        r1 = r2;
        goto L_0x006c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.NumberInput.parseInt(java.lang.String):int");
    }

    public static long parseLong(char[] cArr, int i, int i2) {
        int i3 = i2 - 9;
        return ((long) parseInt(cArr, i3 + i, 9)) + (((long) parseInt(cArr, i, i3)) * 1000000000);
    }

    public static long parseLong(String str) {
        if (str.length() <= 9) {
            return (long) parseInt(str);
        }
        return Long.parseLong(str);
    }

    public static boolean inLongRange(char[] cArr, int i, int i2, boolean z) {
        String str = z ? MIN_LONG_STR_NO_SIGN : MAX_LONG_STR;
        int length = str.length();
        if (i2 < length) {
            return true;
        }
        if (i2 > length) {
            return false;
        }
        for (int i3 = 0; i3 < length; i3++) {
            int charAt = cArr[i + i3] - str.charAt(i3);
            if (charAt != 0) {
                return charAt < 0;
            }
        }
        return true;
    }

    public static boolean inLongRange(String str, boolean z) {
        String str2 = z ? MIN_LONG_STR_NO_SIGN : MAX_LONG_STR;
        int length = str2.length();
        int length2 = str.length();
        if (length2 < length) {
            return true;
        }
        if (length2 > length) {
            return false;
        }
        for (length2 = 0; length2 < length; length2++) {
            int charAt = str.charAt(length2) - str2.charAt(length2);
            if (charAt != 0) {
                return charAt < 0;
            }
        }
        return true;
    }

    public static int parseAsInt(String str, int i) {
        int i2 = 0;
        if (str == null) {
            return i;
        }
        String trim = str.trim();
        int length = trim.length();
        if (length == 0) {
            return i;
        }
        String substring;
        int length2;
        char charAt;
        if (0 < length) {
            char charAt2 = trim.charAt(0);
            if (charAt2 == '+') {
                substring = trim.substring(1);
                length2 = substring.length();
            } else if (charAt2 == '-') {
                i2 = 1;
                length2 = length;
                substring = trim;
            }
            while (i2 < length2) {
                charAt = substring.charAt(i2);
                if (charAt <= '9' || charAt < '0') {
                    try {
                        return (int) parseDouble(substring);
                    } catch (NumberFormatException e) {
                        return i;
                    }
                }
                i2++;
            }
            return Integer.parseInt(substring);
        }
        length2 = length;
        substring = trim;
        while (i2 < length2) {
            charAt = substring.charAt(i2);
            if (charAt <= '9') {
            }
            return (int) parseDouble(substring);
        }
        try {
            return Integer.parseInt(substring);
        } catch (NumberFormatException e2) {
            return i;
        }
    }

    public static long parseAsLong(String str, long j) {
        int i = 0;
        if (str == null) {
            return j;
        }
        String trim = str.trim();
        int length = trim.length();
        if (length == 0) {
            return j;
        }
        String substring;
        int length2;
        char charAt;
        if (0 < length) {
            char charAt2 = trim.charAt(0);
            if (charAt2 == '+') {
                substring = trim.substring(1);
                length2 = substring.length();
            } else if (charAt2 == '-') {
                i = 1;
                length2 = length;
                substring = trim;
            }
            while (i < length2) {
                charAt = substring.charAt(i);
                if (charAt <= '9' || charAt < '0') {
                    try {
                        return (long) parseDouble(substring);
                    } catch (NumberFormatException e) {
                        return j;
                    }
                }
                i++;
            }
            return Long.parseLong(substring);
        }
        length2 = length;
        substring = trim;
        while (i < length2) {
            charAt = substring.charAt(i);
            if (charAt <= '9') {
            }
            return (long) parseDouble(substring);
        }
        try {
            return Long.parseLong(substring);
        } catch (NumberFormatException e2) {
            return j;
        }
    }

    public static double parseDouble(String str) {
        if ("2.2250738585072012e-308".equals(str)) {
            return Double.MIN_VALUE;
        }
        return Double.parseDouble(str);
    }

    public static BigDecimal parseBigDecimal(String str) {
        try {
            return new BigDecimal(str);
        } catch (NumberFormatException e) {
            throw _badBD(str);
        }
    }

    public static BigDecimal parseBigDecimal(char[] cArr) {
        return parseBigDecimal(cArr, 0, cArr.length);
    }

    public static BigDecimal parseBigDecimal(char[] cArr, int i, int i2) {
        try {
            return new BigDecimal(cArr, i, i2);
        } catch (NumberFormatException e) {
            throw _badBD(new String(cArr, i, i2));
        }
    }

    private static NumberFormatException _badBD(String str) {
        return new NumberFormatException("Value \"" + str + "\" can not be represented as BigDecimal");
    }
}
