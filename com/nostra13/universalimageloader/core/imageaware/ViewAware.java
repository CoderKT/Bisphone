package com.nostra13.universalimageloader.core.imageaware;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.utils.C0926L;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class ViewAware implements ImageAware {
    protected Reference<View> f7081a;
    protected boolean f7082b;

    protected abstract void m11235a(Bitmap bitmap, View view);

    protected abstract void m11236a(Drawable drawable, View view);

    public ViewAware(View view) {
        this(view, true);
    }

    public ViewAware(View view, boolean z) {
        if (view == null) {
            throw new IllegalArgumentException("view must not be null");
        }
        this.f7081a = new WeakReference(view);
        this.f7082b = z;
    }

    public int m11234a() {
        View view = (View) this.f7081a.get();
        if (view == null) {
            return 0;
        }
        int i;
        LayoutParams layoutParams = view.getLayoutParams();
        if (!this.f7082b || layoutParams == null || layoutParams.width == -2) {
            i = 0;
        } else {
            i = view.getWidth();
        }
        if (i > 0 || layoutParams == null) {
            return i;
        }
        return layoutParams.width;
    }

    public int m11239b() {
        View view = (View) this.f7081a.get();
        if (view == null) {
            return 0;
        }
        int i;
        LayoutParams layoutParams = view.getLayoutParams();
        if (!this.f7082b || layoutParams == null || layoutParams.height == -2) {
            i = 0;
        } else {
            i = view.getHeight();
        }
        if (i > 0 || layoutParams == null) {
            return i;
        }
        return layoutParams.height;
    }

    public ViewScaleType m11240c() {
        return ViewScaleType.CROP;
    }

    public View m11241d() {
        return (View) this.f7081a.get();
    }

    public boolean m11242e() {
        return this.f7081a.get() == null;
    }

    public int m11243f() {
        View view = (View) this.f7081a.get();
        return view == null ? super.hashCode() : view.hashCode();
    }

    public boolean m11238a(Drawable drawable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            View view = (View) this.f7081a.get();
            if (view != null) {
                m11236a(drawable, view);
                return true;
            }
        }
        C0926L.m11276c("Can't set a drawable into view. You should call ImageLoader on UI thread for it.", new Object[0]);
        return false;
    }

    public boolean m11237a(Bitmap bitmap) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            View view = (View) this.f7081a.get();
            if (view != null) {
                m11235a(bitmap, view);
                return true;
            }
        }
        C0926L.m11276c("Can't set a bitmap into view. You should call ImageLoader on UI thread for it.", new Object[0]);
        return false;
    }
}
