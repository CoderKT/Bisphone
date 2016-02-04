package com.nispok.snackbar;

import android.app.Activity;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.view.Display;

class DisplayCompat {
    private static final Impl f6693a;

    abstract class Impl {
        abstract void m10801a(Display display, Point point);

        abstract void m10802b(Display display, Point point);

        Impl() {
        }
    }

    static {
        if (VERSION.SDK_INT >= 17) {
            f6693a = new DisplayCompatImplJBMR1();
        } else if (VERSION.SDK_INT >= 13) {
            f6693a = new DisplayCompatImplHoneycombMR2();
        } else {
            f6693a = new DisplayCompatImplPreHoneycombMR2();
        }
    }

    public static void m10804a(Display display, Point point) {
        f6693a.m10801a(display, point);
    }

    public static void m10805b(Display display, Point point) {
        f6693a.m10802b(display, point);
    }

    public static int m10803a(Activity activity, Float f) {
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        m10805b(defaultDisplay, point);
        return (int) (((float) point.x) * f.floatValue());
    }
}
