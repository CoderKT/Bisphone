package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.v4.view.TintableBackgroundView;
import android.support.v7.appcompat.C0057R;
import android.util.AttributeSet;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

public class AppCompatMultiAutoCompleteTextView extends MultiAutoCompleteTextView implements TintableBackgroundView {
    private static final int[] f1356a;
    private TintManager f1357b;
    private AppCompatBackgroundHelper f1358c;
    private AppCompatTextHelper f1359d;

    static {
        f1356a = new int[]{16843126};
    }

    public AppCompatMultiAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0057R.attr.autoCompleteTextViewStyle);
    }

    public AppCompatMultiAutoCompleteTextView(Context context, AttributeSet attributeSet, int i) {
        super(TintContextWrapper.m3730a(context), attributeSet, i);
        TintTypedArray a = TintTypedArray.m3759a(getContext(), attributeSet, f1356a, i, 0);
        this.f1357b = a.m3767b();
        if (a.m3773e(0)) {
            setDropDownBackgroundDrawable(a.m3762a(0));
        }
        a.m3763a();
        this.f1358c = new AppCompatBackgroundHelper(this, this.f1357b);
        this.f1358c.m2774a(attributeSet, i);
        this.f1359d = AppCompatTextHelper.m2846a((TextView) this);
        this.f1359d.m2851a(attributeSet, i);
        this.f1359d.m2848a();
    }

    public void setDropDownBackgroundResource(int i) {
        if (this.f1357b != null) {
            setDropDownBackgroundDrawable(this.f1357b.m3753a(i));
        } else {
            super.setDropDownBackgroundResource(i);
        }
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        if (this.f1358c != null) {
            this.f1358c.m2770a(i);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        if (this.f1358c != null) {
            this.f1358c.m2773a(drawable);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (this.f1358c != null) {
            this.f1358c.m2771a(colorStateList);
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        return this.f1358c != null ? this.f1358c.m2769a() : null;
    }

    public void setSupportBackgroundTintMode(Mode mode) {
        if (this.f1358c != null) {
            this.f1358c.m2772a(mode);
        }
    }

    public Mode getSupportBackgroundTintMode() {
        return this.f1358c != null ? this.f1358c.m2775b() : null;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.f1358c != null) {
            this.f1358c.m2777c();
        }
        if (this.f1359d != null) {
            this.f1359d.m2848a();
        }
    }

    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        if (this.f1359d != null) {
            this.f1359d.m2849a(context, i);
        }
    }
}
