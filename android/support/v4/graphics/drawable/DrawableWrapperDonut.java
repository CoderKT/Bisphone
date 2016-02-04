package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;

class DrawableWrapperDonut extends Drawable implements Callback, DrawableWrapper {
    static final Mode f351a;
    Drawable f352b;
    private ColorStateList f353c;
    private Mode f354d;
    private int f355e;
    private Mode f356f;
    private boolean f357g;

    static {
        f351a = Mode.SRC_IN;
    }

    DrawableWrapperDonut(Drawable drawable) {
        this.f354d = f351a;
        m685a(drawable);
    }

    public void draw(Canvas canvas) {
        this.f352b.draw(canvas);
    }

    protected void onBoundsChange(Rect rect) {
        this.f352b.setBounds(rect);
    }

    public void setChangingConfigurations(int i) {
        this.f352b.setChangingConfigurations(i);
    }

    public int getChangingConfigurations() {
        return this.f352b.getChangingConfigurations();
    }

    public void setDither(boolean z) {
        this.f352b.setDither(z);
    }

    public void setFilterBitmap(boolean z) {
        this.f352b.setFilterBitmap(z);
    }

    public void setAlpha(int i) {
        this.f352b.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.f352b.setColorFilter(colorFilter);
    }

    public boolean isStateful() {
        return (this.f353c != null && this.f353c.isStateful()) || this.f352b.isStateful();
    }

    public boolean setState(int[] iArr) {
        return m683a(iArr) || this.f352b.setState(iArr);
    }

    public int[] getState() {
        return this.f352b.getState();
    }

    public Drawable getCurrent() {
        return this.f352b.getCurrent();
    }

    public boolean setVisible(boolean z, boolean z2) {
        return super.setVisible(z, z2) || this.f352b.setVisible(z, z2);
    }

    public int getOpacity() {
        return this.f352b.getOpacity();
    }

    public Region getTransparentRegion() {
        return this.f352b.getTransparentRegion();
    }

    public int getIntrinsicWidth() {
        return this.f352b.getIntrinsicWidth();
    }

    public int getIntrinsicHeight() {
        return this.f352b.getIntrinsicHeight();
    }

    public int getMinimumWidth() {
        return this.f352b.getMinimumWidth();
    }

    public int getMinimumHeight() {
        return this.f352b.getMinimumHeight();
    }

    public boolean getPadding(Rect rect) {
        return this.f352b.getPadding(rect);
    }

    public Drawable mutate() {
        Drawable drawable = this.f352b;
        Drawable mutate = drawable.mutate();
        if (mutate != drawable) {
            m685a(mutate);
        }
        return this;
    }

    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }

    protected boolean onLevelChange(int i) {
        return this.f352b.setLevel(i);
    }

    public void setTint(int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    public void setTintList(ColorStateList colorStateList) {
        this.f353c = colorStateList;
        m683a(getState());
    }

    public void setTintMode(Mode mode) {
        this.f354d = mode;
        m683a(getState());
    }

    private boolean m683a(int[] iArr) {
        if (this.f353c == null || this.f354d == null) {
            this.f357g = false;
            clearColorFilter();
        } else {
            int colorForState = this.f353c.getColorForState(iArr, this.f353c.getDefaultColor());
            Mode mode = this.f354d;
            if (!(this.f357g && colorForState == this.f355e && mode == this.f356f)) {
                setColorFilter(colorForState, mode);
                this.f355e = colorForState;
                this.f356f = mode;
                this.f357g = true;
                return true;
            }
        }
        return false;
    }

    public Drawable m684a() {
        return this.f352b;
    }

    public void m685a(Drawable drawable) {
        if (this.f352b != null) {
            this.f352b.setCallback(null);
        }
        this.f352b = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        invalidateSelf();
    }
}
