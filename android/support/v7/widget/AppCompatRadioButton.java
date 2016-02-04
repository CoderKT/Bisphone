package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.TintableCompoundButton;
import android.support.v7.appcompat.C0057R;
import android.util.AttributeSet;
import android.widget.RadioButton;

public class AppCompatRadioButton extends RadioButton implements TintableCompoundButton {
    private TintManager f1369a;
    private AppCompatCompoundButtonHelper f1370b;

    public AppCompatRadioButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0057R.attr.radioButtonStyle);
    }

    public AppCompatRadioButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1369a = TintManager.m3737a(context);
        this.f1370b = new AppCompatCompoundButtonHelper(this, this.f1369a);
        this.f1370b.m2782a(attributeSet, i);
    }

    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        if (this.f1370b != null) {
            this.f1370b.m2784c();
        }
    }

    public void setButtonDrawable(int i) {
        setButtonDrawable(this.f1369a != null ? this.f1369a.m3753a(i) : ContextCompat.m98a(getContext(), i));
    }

    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        return this.f1370b != null ? this.f1370b.m2778a(compoundPaddingLeft) : compoundPaddingLeft;
    }

    public void setSupportButtonTintList(ColorStateList colorStateList) {
        if (this.f1370b != null) {
            this.f1370b.m2780a(colorStateList);
        }
    }

    public ColorStateList getSupportButtonTintList() {
        return this.f1370b != null ? this.f1370b.m2779a() : null;
    }

    public void setSupportButtonTintMode(Mode mode) {
        if (this.f1370b != null) {
            this.f1370b.m2781a(mode);
        }
    }

    public Mode getSupportButtonTintMode() {
        return this.f1370b != null ? this.f1370b.m2783b() : null;
    }
}
