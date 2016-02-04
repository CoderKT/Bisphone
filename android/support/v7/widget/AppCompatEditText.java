package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.v4.view.TintableBackgroundView;
import android.support.v7.appcompat.C0057R;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

public class AppCompatEditText extends EditText implements TintableBackgroundView {
    private TintManager f1348a;
    private AppCompatBackgroundHelper f1349b;
    private AppCompatTextHelper f1350c;

    public AppCompatEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0057R.attr.editTextStyle);
    }

    public AppCompatEditText(Context context, AttributeSet attributeSet, int i) {
        super(TintContextWrapper.m3730a(context), attributeSet, i);
        this.f1348a = TintManager.m3737a(getContext());
        this.f1349b = new AppCompatBackgroundHelper(this, this.f1348a);
        this.f1349b.m2774a(attributeSet, i);
        this.f1350c = AppCompatTextHelper.m2846a((TextView) this);
        this.f1350c.m2851a(attributeSet, i);
        this.f1350c.m2848a();
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        if (this.f1349b != null) {
            this.f1349b.m2770a(i);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        if (this.f1349b != null) {
            this.f1349b.m2773a(drawable);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (this.f1349b != null) {
            this.f1349b.m2771a(colorStateList);
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        return this.f1349b != null ? this.f1349b.m2769a() : null;
    }

    public void setSupportBackgroundTintMode(Mode mode) {
        if (this.f1349b != null) {
            this.f1349b.m2772a(mode);
        }
    }

    public Mode getSupportBackgroundTintMode() {
        return this.f1349b != null ? this.f1349b.m2775b() : null;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.f1349b != null) {
            this.f1349b.m2777c();
        }
        if (this.f1350c != null) {
            this.f1350c.m2848a();
        }
    }

    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        if (this.f1350c != null) {
            this.f1350c.m2849a(context, i);
        }
    }
}
