package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;

class DrawableCompatBase {
    public static void m667a(Drawable drawable, int i) {
        if (drawable instanceof DrawableWrapper) {
            ((DrawableWrapper) drawable).setTint(i);
        }
    }

    public static void m668a(Drawable drawable, ColorStateList colorStateList) {
        if (drawable instanceof DrawableWrapper) {
            ((DrawableWrapper) drawable).setTintList(colorStateList);
        }
    }

    public static void m669a(Drawable drawable, Mode mode) {
        if (drawable instanceof DrawableWrapper) {
            ((DrawableWrapper) drawable).setTintMode(mode);
        }
    }

    public static Drawable m666a(Drawable drawable) {
        if (drawable instanceof DrawableWrapperDonut) {
            return drawable;
        }
        return new DrawableWrapperDonut(drawable);
    }
}
