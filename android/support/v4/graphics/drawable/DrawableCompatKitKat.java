package android.support.v4.graphics.drawable;

import android.graphics.drawable.Drawable;

class DrawableCompatKitKat {
    public static void m672a(Drawable drawable, boolean z) {
        drawable.setAutoMirrored(z);
    }

    public static boolean m673a(Drawable drawable) {
        return drawable.isAutoMirrored();
    }

    public static Drawable m674b(Drawable drawable) {
        if (drawable instanceof DrawableWrapperKitKat) {
            return drawable;
        }
        return new DrawableWrapperKitKat(drawable);
    }
}
