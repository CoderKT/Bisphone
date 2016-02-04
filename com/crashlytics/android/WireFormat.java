package com.crashlytics.android;

final class WireFormat {
    static final int f5453a;
    static final int f5454b;
    static final int f5455c;
    static final int f5456d;

    static int m8094a(int i, int i2) {
        return (i << 3) | i2;
    }

    static {
        f5453a = m8094a(1, 3);
        f5454b = m8094a(1, 4);
        f5455c = m8094a(2, 0);
        f5456d = m8094a(3, 2);
    }
}
