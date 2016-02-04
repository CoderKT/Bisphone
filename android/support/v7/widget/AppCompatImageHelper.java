package android.support.v7.widget;

import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;

class AppCompatImageHelper {
    private static final int[] f1353a;
    private final ImageView f1354b;
    private final TintManager f1355c;

    static {
        f1353a = new int[]{16843033};
    }

    AppCompatImageHelper(ImageView imageView, TintManager tintManager) {
        this.f1354b = imageView;
        this.f1355c = tintManager;
    }

    void m2787a(AttributeSet attributeSet, int i) {
        TintTypedArray a = TintTypedArray.m3759a(this.f1354b.getContext(), attributeSet, f1353a, i, 0);
        try {
            if (a.m3773e(0)) {
                this.f1354b.setImageDrawable(a.m3762a(0));
            }
            a.m3763a();
        } catch (Throwable th) {
            a.m3763a();
        }
    }

    void m2786a(int i) {
        if (i != 0) {
            this.f1354b.setImageDrawable(this.f1355c != null ? this.f1355c.m3753a(i) : ContextCompat.m98a(this.f1354b.getContext(), i));
        } else {
            this.f1354b.setImageDrawable(null);
        }
    }
}
