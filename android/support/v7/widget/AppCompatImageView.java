package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.v4.view.TintableBackgroundView;
import android.util.AttributeSet;
import android.widget.ImageView;

public class AppCompatImageView extends ImageView implements TintableBackgroundView {
    private AppCompatBackgroundHelper f1202a;
    private AppCompatImageHelper f1203b;

    public AppCompatImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AppCompatImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TintManager a = TintManager.m3737a(context);
        this.f1202a = new AppCompatBackgroundHelper(this, a);
        this.f1202a.m2774a(attributeSet, i);
        this.f1203b = new AppCompatImageHelper(this, a);
        this.f1203b.m2787a(attributeSet, i);
    }

    public void setImageResource(int i) {
        this.f1203b.m2786a(i);
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        if (this.f1202a != null) {
            this.f1202a.m2770a(i);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        if (this.f1202a != null) {
            this.f1202a.m2773a(drawable);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (this.f1202a != null) {
            this.f1202a.m2771a(colorStateList);
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        return this.f1202a != null ? this.f1202a.m2769a() : null;
    }

    public void setSupportBackgroundTintMode(Mode mode) {
        if (this.f1202a != null) {
            this.f1202a.m2772a(mode);
        }
    }

    public Mode getSupportBackgroundTintMode() {
        return this.f1202a != null ? this.f1202a.m2775b() : null;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.f1202a != null) {
            this.f1202a.m2777c();
        }
    }
}
