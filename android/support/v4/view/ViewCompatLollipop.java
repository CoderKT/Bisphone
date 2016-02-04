package android.support.v4.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.view.View;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.WindowInsets;

class ViewCompatLollipop {

    /* renamed from: android.support.v4.view.ViewCompatLollipop.1 */
    final class C00201 implements OnApplyWindowInsetsListener {
        final /* synthetic */ OnApplyWindowInsetsListener f454a;

        C00201(OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
            this.f454a = onApplyWindowInsetsListener;
        }

        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            return ((WindowInsetsCompatApi21) this.f454a.m1013a(view, new WindowInsetsCompatApi21(windowInsets))).m1434e();
        }
    }

    public static void m1234a(View view) {
        view.requestApplyInsets();
    }

    public static void m1235a(View view, float f) {
        view.setElevation(f);
    }

    public static void m1238a(View view, OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        view.setOnApplyWindowInsetsListener(new C00201(onApplyWindowInsetsListener));
    }

    static void m1236a(View view, ColorStateList colorStateList) {
        view.setBackgroundTintList(colorStateList);
    }

    static void m1237a(View view, Mode mode) {
        view.setBackgroundTintMode(mode);
    }

    public static WindowInsetsCompat m1233a(View view, WindowInsetsCompat windowInsetsCompat) {
        if (!(windowInsetsCompat instanceof WindowInsetsCompatApi21)) {
            return windowInsetsCompat;
        }
        WindowInsets e = ((WindowInsetsCompatApi21) windowInsetsCompat).m1434e();
        WindowInsets onApplyWindowInsets = view.onApplyWindowInsets(e);
        if (onApplyWindowInsets != e) {
            return new WindowInsetsCompatApi21(onApplyWindowInsets);
        }
        return windowInsetsCompat;
    }

    public static void m1239b(View view) {
        view.stopNestedScroll();
    }
}
