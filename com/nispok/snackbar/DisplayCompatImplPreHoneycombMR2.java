package com.nispok.snackbar;

import android.graphics.Point;
import android.view.Display;

class DisplayCompatImplPreHoneycombMR2 extends Impl {
    DisplayCompatImplPreHoneycombMR2() {
    }

    void m10810a(Display display, Point point) {
        point.x = display.getWidth();
        point.y = display.getHeight();
    }

    void m10811b(Display display, Point point) {
        point.x = display.getWidth();
        point.y = display.getHeight();
    }
}
