package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.v4.view.TintableBackgroundView;
import android.util.AttributeSet;
import android.widget.TextView;

public class AppCompatTextView extends TextView implements TintableBackgroundView {
    private TintManager f986a;
    private AppCompatBackgroundHelper f987b;
    private AppCompatTextHelper f988c;

    public AppCompatTextView(Context context) {
        this(context, null);
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842884);
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f986a = TintManager.m3737a(getContext());
        this.f987b = new AppCompatBackgroundHelper(this, this.f986a);
        this.f987b.m2774a(attributeSet, i);
        this.f988c = AppCompatTextHelper.m2846a((TextView) this);
        this.f988c.m2851a(attributeSet, i);
        this.f988c.m2848a();
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        if (this.f987b != null) {
            this.f987b.m2770a(i);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        if (this.f987b != null) {
            this.f987b.m2773a(drawable);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (this.f987b != null) {
            this.f987b.m2771a(colorStateList);
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        return this.f987b != null ? this.f987b.m2769a() : null;
    }

    public void setSupportBackgroundTintMode(Mode mode) {
        if (this.f987b != null) {
            this.f987b.m2772a(mode);
        }
    }

    public Mode getSupportBackgroundTintMode() {
        return this.f987b != null ? this.f987b.m2775b() : null;
    }

    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        if (this.f988c != null) {
            this.f988c.m2849a(context, i);
        }
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.f987b != null) {
            this.f987b.m2777c();
        }
        if (this.f988c != null) {
            this.f988c.m2848a();
        }
    }
}
