package com.nispok.snackbar;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.view.Display;

@TargetApi(17)
class DisplayCompatImplJBMR1 extends Impl {
    DisplayCompatImplJBMR1() {
    }

    void m10808a(Display display, Point point) {
        display.getSize(point);
    }

    void m10809b(Display display, Point point) {
        display.getRealSize(point);
    }
}
