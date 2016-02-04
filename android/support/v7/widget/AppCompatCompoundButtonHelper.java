package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.appcompat.C0057R;
import android.support.v7.graphics.drawable.DrawableUtils;
import android.util.AttributeSet;
import android.widget.CompoundButton;

class AppCompatCompoundButtonHelper {
    private final CompoundButton f1341a;
    private final TintManager f1342b;
    private ColorStateList f1343c;
    private Mode f1344d;
    private boolean f1345e;
    private boolean f1346f;
    private boolean f1347g;

    AppCompatCompoundButtonHelper(CompoundButton compoundButton, TintManager tintManager) {
        this.f1343c = null;
        this.f1344d = null;
        this.f1345e = false;
        this.f1346f = false;
        this.f1341a = compoundButton;
        this.f1342b = tintManager;
    }

    void m2782a(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = this.f1341a.getContext().obtainStyledAttributes(attributeSet, C0057R.styleable.CompoundButton, i, 0);
        try {
            if (obtainStyledAttributes.hasValue(C0057R.styleable.CompoundButton_android_button)) {
                int resourceId = obtainStyledAttributes.getResourceId(C0057R.styleable.CompoundButton_android_button, 0);
                if (resourceId != 0) {
                    this.f1341a.setButtonDrawable(this.f1342b.m3753a(resourceId));
                }
            }
            if (obtainStyledAttributes.hasValue(C0057R.styleable.CompoundButton_buttonTint)) {
                CompoundButtonCompat.m1711a(this.f1341a, obtainStyledAttributes.getColorStateList(C0057R.styleable.CompoundButton_buttonTint));
            }
            if (obtainStyledAttributes.hasValue(C0057R.styleable.CompoundButton_buttonTintMode)) {
                CompoundButtonCompat.m1712a(this.f1341a, DrawableUtils.m2244a(obtainStyledAttributes.getInt(C0057R.styleable.CompoundButton_buttonTintMode, -1), null));
            }
            obtainStyledAttributes.recycle();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
        }
    }

    void m2780a(ColorStateList colorStateList) {
        this.f1343c = colorStateList;
        this.f1345e = true;
        m2785d();
    }

    ColorStateList m2779a() {
        return this.f1343c;
    }

    void m2781a(Mode mode) {
        this.f1344d = mode;
        this.f1346f = true;
        m2785d();
    }

    Mode m2783b() {
        return this.f1344d;
    }

    void m2784c() {
        if (this.f1347g) {
            this.f1347g = false;
            return;
        }
        this.f1347g = true;
        m2785d();
    }

    void m2785d() {
        Drawable a = CompoundButtonCompat.m1710a(this.f1341a);
        if (a == null) {
            return;
        }
        if (this.f1345e || this.f1346f) {
            a = DrawableCompat.m664c(a).mutate();
            if (this.f1345e) {
                DrawableCompat.m660a(a, this.f1343c);
            }
            if (this.f1346f) {
                DrawableCompat.m661a(a, this.f1344d);
            }
            if (a.isStateful()) {
                a.setState(this.f1341a.getDrawableState());
            }
            this.f1341a.setButtonDrawable(a);
        }
    }

    int m2778a(int i) {
        if (VERSION.SDK_INT >= 17) {
            return i;
        }
        Drawable a = CompoundButtonCompat.m1710a(this.f1341a);
        if (a != null) {
            return i + a.getIntrinsicWidth();
        }
        return i;
    }
}
