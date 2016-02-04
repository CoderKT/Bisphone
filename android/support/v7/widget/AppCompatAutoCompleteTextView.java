package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.v4.view.TintableBackgroundView;
import android.support.v7.appcompat.C0057R;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class AppCompatAutoCompleteTextView extends AutoCompleteTextView implements TintableBackgroundView {
    private static final int[] f1325a;
    private TintManager f1326b;
    private AppCompatBackgroundHelper f1327c;
    private AppCompatTextHelper f1328d;

    static {
        f1325a = new int[]{16843126};
    }

    public AppCompatAutoCompleteTextView(Context context) {
        this(context, null);
    }

    public AppCompatAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0057R.attr.autoCompleteTextViewStyle);
    }

    public AppCompatAutoCompleteTextView(Context context, AttributeSet attributeSet, int i) {
        super(TintContextWrapper.m3730a(context), attributeSet, i);
        TintTypedArray a = TintTypedArray.m3759a(getContext(), attributeSet, f1325a, i, 0);
        this.f1326b = a.m3767b();
        if (a.m3773e(0)) {
            setDropDownBackgroundDrawable(a.m3762a(0));
        }
        a.m3763a();
        this.f1327c = new AppCompatBackgroundHelper(this, this.f1326b);
        this.f1327c.m2774a(attributeSet, i);
        this.f1328d = AppCompatTextHelper.m2846a((TextView) this);
        this.f1328d.m2851a(attributeSet, i);
        this.f1328d.m2848a();
    }

    public void setDropDownBackgroundResource(int i) {
        if (this.f1326b != null) {
            setDropDownBackgroundDrawable(this.f1326b.m3753a(i));
        } else {
            super.setDropDownBackgroundResource(i);
        }
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        if (this.f1327c != null) {
            this.f1327c.m2770a(i);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        if (this.f1327c != null) {
            this.f1327c.m2773a(drawable);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (this.f1327c != null) {
            this.f1327c.m2771a(colorStateList);
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        return this.f1327c != null ? this.f1327c.m2769a() : null;
    }

    public void setSupportBackgroundTintMode(Mode mode) {
        if (this.f1327c != null) {
            this.f1327c.m2772a(mode);
        }
    }

    public Mode getSupportBackgroundTintMode() {
        return this.f1327c != null ? this.f1327c.m2775b() : null;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.f1327c != null) {
            this.f1327c.m2777c();
        }
        if (this.f1328d != null) {
            this.f1328d.m2848a();
        }
    }

    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        if (this.f1328d != null) {
            this.f1328d.m2849a(context, i);
        }
    }
}
