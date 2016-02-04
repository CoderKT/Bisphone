package android.support.v7.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.support.v4.graphics.drawable.DrawableCompat;

public class DrawableWrapper extends Drawable implements Callback {
    private Drawable f898a;

    public DrawableWrapper(Drawable drawable) {
        m2245a(drawable);
    }

    public void draw(Canvas canvas) {
        this.f898a.draw(canvas);
    }

    protected void onBoundsChange(Rect rect) {
        this.f898a.setBounds(rect);
    }

    public void setChangingConfigurations(int i) {
        this.f898a.setChangingConfigurations(i);
    }

    public int getChangingConfigurations() {
        return this.f898a.getChangingConfigurations();
    }

    public void setDither(boolean z) {
        this.f898a.setDither(z);
    }

    public void setFilterBitmap(boolean z) {
        this.f898a.setFilterBitmap(z);
    }

    public void setAlpha(int i) {
        this.f898a.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.f898a.setColorFilter(colorFilter);
    }

    public boolean isStateful() {
        return this.f898a.isStateful();
    }

    public boolean setState(int[] iArr) {
        return this.f898a.setState(iArr);
    }

    public int[] getState() {
        return this.f898a.getState();
    }

    public void jumpToCurrentState() {
        DrawableCompat.m656a(this.f898a);
    }

    public Drawable getCurrent() {
        return this.f898a.getCurrent();
    }

    public boolean setVisible(boolean z, boolean z2) {
        return super.setVisible(z, z2) || this.f898a.setVisible(z, z2);
    }

    public int getOpacity() {
        return this.f898a.getOpacity();
    }

    public Region getTransparentRegion() {
        return this.f898a.getTransparentRegion();
    }

    public int getIntrinsicWidth() {
        return this.f898a.getIntrinsicWidth();
    }

    public int getIntrinsicHeight() {
        return this.f898a.getIntrinsicHeight();
    }

    public int getMinimumWidth() {
        return this.f898a.getMinimumWidth();
    }

    public int getMinimumHeight() {
        return this.f898a.getMinimumHeight();
    }

    public boolean getPadding(Rect rect) {
        return this.f898a.getPadding(rect);
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
        return this.f898a.setLevel(i);
    }

    public void setAutoMirrored(boolean z) {
        DrawableCompat.m662a(this.f898a, z);
    }

    public boolean isAutoMirrored() {
        return DrawableCompat.m663b(this.f898a);
    }

    public void setTint(int i) {
        DrawableCompat.m658a(this.f898a, i);
    }

    public void setTintList(ColorStateList colorStateList) {
        DrawableCompat.m660a(this.f898a, colorStateList);
    }

    public void setTintMode(Mode mode) {
        DrawableCompat.m661a(this.f898a, mode);
    }

    public void setHotspot(float f, float f2) {
        DrawableCompat.m657a(this.f898a, f, f2);
    }

    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        DrawableCompat.m659a(this.f898a, i, i2, i3, i4);
    }

    public void m2245a(Drawable drawable) {
        if (this.f898a != null) {
            this.f898a.setCallback(null);
        }
        this.f898a = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
    }
}
