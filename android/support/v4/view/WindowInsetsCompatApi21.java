package android.support.v4.view;

import android.view.WindowInsets;

class WindowInsetsCompatApi21 extends WindowInsetsCompat {
    private final WindowInsets f540a;

    WindowInsetsCompatApi21(WindowInsets windowInsets) {
        this.f540a = windowInsets;
    }

    public int m1429a() {
        return this.f540a.getSystemWindowInsetLeft();
    }

    public int m1431b() {
        return this.f540a.getSystemWindowInsetTop();
    }

    public int m1432c() {
        return this.f540a.getSystemWindowInsetRight();
    }

    public int m1433d() {
        return this.f540a.getSystemWindowInsetBottom();
    }

    public WindowInsetsCompat m1430a(int i, int i2, int i3, int i4) {
        return new WindowInsetsCompatApi21(this.f540a.replaceSystemWindowInsets(i, i2, i3, i4));
    }

    WindowInsets m1434e() {
        return this.f540a;
    }
}
