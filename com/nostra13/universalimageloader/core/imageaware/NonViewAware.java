package com.nostra13.universalimageloader.core.imageaware;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;

public class NonViewAware implements ImageAware {
    protected final String f7083a;
    protected final ImageSize f7084b;
    protected final ViewScaleType f7085c;

    public NonViewAware(String str, ImageSize imageSize, ViewScaleType viewScaleType) {
        if (imageSize == null) {
            throw new IllegalArgumentException("imageSize must not be null");
        } else if (viewScaleType == null) {
            throw new IllegalArgumentException("scaleType must not be null");
        } else {
            this.f7083a = str;
            this.f7084b = imageSize;
            this.f7085c = viewScaleType;
        }
    }

    public int m11252a() {
        return this.f7084b.m11167a();
    }

    public int m11255b() {
        return this.f7084b.m11170b();
    }

    public ViewScaleType m11256c() {
        return this.f7085c;
    }

    public View m11257d() {
        return null;
    }

    public boolean m11258e() {
        return false;
    }

    public int m11259f() {
        return TextUtils.isEmpty(this.f7083a) ? super.hashCode() : this.f7083a.hashCode();
    }

    public boolean m11254a(Drawable drawable) {
        return true;
    }

    public boolean m11253a(Bitmap bitmap) {
        return true;
    }
}
