package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.TintableCompoundButton;
import android.support.v7.appcompat.C0057R;
import android.util.AttributeSet;
import android.widget.CheckBox;

public class AppCompatCheckBox extends CheckBox implements TintableCompoundButton {
    private TintManager f1336a;
    private AppCompatCompoundButtonHelper f1337b;

    public AppCompatCheckBox(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0057R.attr.checkboxStyle);
    }

    public AppCompatCheckBox(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1336a = TintManager.m3737a(context);
        this.f1337b = new AppCompatCompoundButtonHelper(this, this.f1336a);
        this.f1337b.m2782a(attributeSet, i);
    }

    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        if (this.f1337b != null) {
            this.f1337b.m2784c();
        }
    }

    public void setButtonDrawable(int i) {
        setButtonDrawable(this.f1336a != null ? this.f1336a.m3753a(i) : ContextCompat.m98a(getContext(), i));
    }

    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        return this.f1337b != null ? this.f1337b.m2778a(compoundPaddingLeft) : compoundPaddingLeft;
    }

    public void setSupportButtonTintList(ColorStateList colorStateList) {
        if (this.f1337b != null) {
            this.f1337b.m2780a(colorStateList);
        }
    }

    public ColorStateList getSupportButtonTintList() {
        return this.f1337b != null ? this.f1337b.m2779a() : null;
    }

    public void setSupportButtonTintMode(Mode mode) {
        if (this.f1337b != null) {
            this.f1337b.m2781a(mode);
        }
    }

    public Mode getSupportButtonTintMode() {
        return this.f1337b != null ? this.f1337b.m2783b() : null;
    }
}
