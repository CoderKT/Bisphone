package android.support.v4.graphics.drawable;

import android.graphics.drawable.Drawable;

class DrawableCompatHoneycomb {
    public static void m670a(Drawable drawable) {
        drawable.jumpToCurrentState();
    }

    public static Drawable m671b(Drawable drawable) {
        if (drawable instanceof DrawableWrapperHoneycomb) {
            return drawable;
        }
        return new DrawableWrapperHoneycomb(drawable);
    }
}
