package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.C0057R;
import android.support.v7.graphics.drawable.DrawableUtils;
import android.util.AttributeSet;
import android.view.View;

class AppCompatBackgroundHelper {
    private final View f1329a;
    private final TintManager f1330b;
    private TintInfo f1331c;
    private TintInfo f1332d;

    AppCompatBackgroundHelper(View view, TintManager tintManager) {
        this.f1329a = view;
        this.f1330b = tintManager;
    }

    void m2774a(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = this.f1329a.getContext().obtainStyledAttributes(attributeSet, C0057R.styleable.ViewBackgroundHelper, i, 0);
        try {
            if (obtainStyledAttributes.hasValue(C0057R.styleable.ViewBackgroundHelper_android_background)) {
                ColorStateList c = this.f1330b.m3757c(obtainStyledAttributes.getResourceId(C0057R.styleable.ViewBackgroundHelper_android_background, -1));
                if (c != null) {
                    m2776b(c);
                }
            }
            if (obtainStyledAttributes.hasValue(C0057R.styleable.ViewBackgroundHelper_backgroundTint)) {
                ViewCompat.m1159a(this.f1329a, obtainStyledAttributes.getColorStateList(C0057R.styleable.ViewBackgroundHelper_backgroundTint));
            }
            if (obtainStyledAttributes.hasValue(C0057R.styleable.ViewBackgroundHelper_backgroundTintMode)) {
                ViewCompat.m1160a(this.f1329a, DrawableUtils.m2244a(obtainStyledAttributes.getInt(C0057R.styleable.ViewBackgroundHelper_backgroundTintMode, -1), null));
            }
            obtainStyledAttributes.recycle();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
        }
    }

    void m2770a(int i) {
        m2776b(this.f1330b != null ? this.f1330b.m3757c(i) : null);
    }

    void m2773a(Drawable drawable) {
        m2776b(null);
    }

    void m2771a(ColorStateList colorStateList) {
        if (this.f1332d == null) {
            this.f1332d = new TintInfo();
        }
        this.f1332d.f1816a = colorStateList;
        this.f1332d.f1819d = true;
        m2777c();
    }

    ColorStateList m2769a() {
        return this.f1332d != null ? this.f1332d.f1816a : null;
    }

    void m2772a(Mode mode) {
        if (this.f1332d == null) {
            this.f1332d = new TintInfo();
        }
        this.f1332d.f1817b = mode;
        this.f1332d.f1818c = true;
        m2777c();
    }

    Mode m2775b() {
        return this.f1332d != null ? this.f1332d.f1817b : null;
    }

    void m2777c() {
        Drawable background = this.f1329a.getBackground();
        if (background == null) {
            return;
        }
        if (this.f1332d != null) {
            TintManager.m3739a(background, this.f1332d, this.f1329a.getDrawableState());
        } else if (this.f1331c != null) {
            TintManager.m3739a(background, this.f1331c, this.f1329a.getDrawableState());
        }
    }

    void m2776b(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.f1331c == null) {
                this.f1331c = new TintInfo();
            }
            this.f1331c.f1816a = colorStateList;
            this.f1331c.f1819d = true;
        } else {
            this.f1331c = null;
        }
        m2777c();
    }
}
