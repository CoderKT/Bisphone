package android.support.v4.view;

import android.os.Build.VERSION;

public class GravityCompat {
    static final GravityCompatImpl f430a;

    interface GravityCompatImpl {
        int m896a(int i, int i2);
    }

    class GravityCompatImplBase implements GravityCompatImpl {
        GravityCompatImplBase() {
        }

        public int m897a(int i, int i2) {
            return -8388609 & i;
        }
    }

    class GravityCompatImplJellybeanMr1 implements GravityCompatImpl {
        GravityCompatImplJellybeanMr1() {
        }

        public int m898a(int i, int i2) {
            return GravityCompatJellybeanMr1.m900a(i, i2);
        }
    }

    static {
        if (VERSION.SDK_INT >= 17) {
            f430a = new GravityCompatImplJellybeanMr1();
        } else {
            f430a = new GravityCompatImplBase();
        }
    }

    public static int m899a(int i, int i2) {
        return f430a.m896a(i, i2);
    }
}
