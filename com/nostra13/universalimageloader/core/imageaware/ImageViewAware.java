package com.nostra13.universalimageloader.core.imageaware;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.utils.C0926L;
import java.lang.reflect.Field;

public class ImageViewAware extends ViewAware {
    public /* synthetic */ View m11250d() {
        return m11251g();
    }

    public ImageViewAware(ImageView imageView) {
        super(imageView);
    }

    public int m11245a() {
        int a = super.m11234a();
        if (a <= 0) {
            Object obj = (ImageView) this.a.get();
            if (obj != null) {
                return m11244a(obj, "mMaxWidth");
            }
        }
        return a;
    }

    public int m11248b() {
        int b = super.m11239b();
        if (b <= 0) {
            Object obj = (ImageView) this.a.get();
            if (obj != null) {
                return m11244a(obj, "mMaxHeight");
            }
        }
        return b;
    }

    public ViewScaleType m11249c() {
        ImageView imageView = (ImageView) this.a.get();
        if (imageView != null) {
            return ViewScaleType.m11171a(imageView);
        }
        return super.m11240c();
    }

    public ImageView m11251g() {
        return (ImageView) super.m11241d();
    }

    protected void m11247a(Drawable drawable, View view) {
        ((ImageView) view).setImageDrawable(drawable);
        if (drawable instanceof AnimationDrawable) {
            ((AnimationDrawable) drawable).start();
        }
    }

    protected void m11246a(Bitmap bitmap, View view) {
        ((ImageView) view).setImageBitmap(bitmap);
    }

    private static int m11244a(Object obj, String str) {
        try {
            Field declaredField = ImageView.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            int intValue = ((Integer) declaredField.get(obj)).intValue();
            if (intValue > 0 && intValue < Integer.MAX_VALUE) {
                return intValue;
            }
        } catch (Throwable e) {
            C0926L.m11273a(e);
        }
        return 0;
    }
}
