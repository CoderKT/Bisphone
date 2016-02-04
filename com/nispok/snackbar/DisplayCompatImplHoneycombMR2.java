package com.nispok.snackbar;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.view.Display;

@TargetApi(13)
class DisplayCompatImplHoneycombMR2 extends Impl {
    DisplayCompatImplHoneycombMR2() {
    }

    void m10806a(Display display, Point point) {
        display.getSize(point);
    }

    void m10807b(Display display, Point point) {
        display.getSize(point);
    }
}
