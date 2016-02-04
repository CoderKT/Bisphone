package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;

public class DrawableCompat {
    static final DrawableImpl f350a;

    interface DrawableImpl {
        void m626a(Drawable drawable);

        void m627a(Drawable drawable, float f, float f2);

        void m628a(Drawable drawable, int i);

        void m629a(Drawable drawable, int i, int i2, int i3, int i4);

        void m630a(Drawable drawable, ColorStateList colorStateList);

        void m631a(Drawable drawable, Mode mode);

        void m632a(Drawable drawable, boolean z);

        boolean m633b(Drawable drawable);

        Drawable m634c(Drawable drawable);
    }

    class BaseDrawableImpl implements DrawableImpl {
        BaseDrawableImpl() {
        }

        public void m635a(Drawable drawable) {
        }

        public void m641a(Drawable drawable, boolean z) {
        }

        public boolean m642b(Drawable drawable) {
            return false;
        }

        public void m636a(Drawable drawable, float f, float f2) {
        }

        public void m638a(Drawable drawable, int i, int i2, int i3, int i4) {
        }

        public void m637a(Drawable drawable, int i) {
            DrawableCompatBase.m667a(drawable, i);
        }

        public void m639a(Drawable drawable, ColorStateList colorStateList) {
            DrawableCompatBase.m668a(drawable, colorStateList);
        }

        public void m640a(Drawable drawable, Mode mode) {
            DrawableCompatBase.m669a(drawable, mode);
        }

        public Drawable m643c(Drawable drawable) {
            return DrawableCompatBase.m666a(drawable);
        }
    }

    class HoneycombDrawableImpl extends BaseDrawableImpl {
        HoneycombDrawableImpl() {
        }

        public void m644a(Drawable drawable) {
            DrawableCompatHoneycomb.m670a(drawable);
        }

        public Drawable m645c(Drawable drawable) {
            return DrawableCompatHoneycomb.m671b(drawable);
        }
    }

    class JellybeanMr1DrawableImpl extends HoneycombDrawableImpl {
        JellybeanMr1DrawableImpl() {
        }
    }

    class KitKatDrawableImpl extends JellybeanMr1DrawableImpl {
        KitKatDrawableImpl() {
        }

        public void m646a(Drawable drawable, boolean z) {
            DrawableCompatKitKat.m672a(drawable, z);
        }

        public boolean m647b(Drawable drawable) {
            return DrawableCompatKitKat.m673a(drawable);
        }

        public Drawable m648c(Drawable drawable) {
            return DrawableCompatKitKat.m674b(drawable);
        }
    }

    class LollipopDrawableImpl extends KitKatDrawableImpl {
        LollipopDrawableImpl() {
        }

        public void m649a(Drawable drawable, float f, float f2) {
            DrawableCompatLollipop.m676a(drawable, f, f2);
        }

        public void m651a(Drawable drawable, int i, int i2, int i3, int i4) {
            DrawableCompatLollipop.m678a(drawable, i, i2, i3, i4);
        }

        public void m650a(Drawable drawable, int i) {
            DrawableCompatLollipop.m677a(drawable, i);
        }

        public void m652a(Drawable drawable, ColorStateList colorStateList) {
            DrawableCompatLollipop.m679a(drawable, colorStateList);
        }

        public void m653a(Drawable drawable, Mode mode) {
            DrawableCompatLollipop.m680a(drawable, mode);
        }

        public Drawable m654c(Drawable drawable) {
            return DrawableCompatLollipop.m675a(drawable);
        }
    }

    class LollipopMr1DrawableImpl extends LollipopDrawableImpl {
        LollipopMr1DrawableImpl() {
        }

        public Drawable m655c(Drawable drawable) {
            return DrawableCompatApi22.m665a(drawable);
        }
    }

    class MDrawableImpl extends LollipopMr1DrawableImpl {
        MDrawableImpl() {
        }
    }

    static {
        int i = VERSION.SDK_INT;
        if (i >= 23) {
            f350a = new MDrawableImpl();
        } else if (i >= 22) {
            f350a = new LollipopMr1DrawableImpl();
        } else if (i >= 21) {
            f350a = new LollipopDrawableImpl();
        } else if (i >= 19) {
            f350a = new KitKatDrawableImpl();
        } else if (i >= 17) {
            f350a = new JellybeanMr1DrawableImpl();
        } else if (i >= 11) {
            f350a = new HoneycombDrawableImpl();
        } else {
            f350a = new BaseDrawableImpl();
        }
    }

    public static void m656a(Drawable drawable) {
        f350a.m626a(drawable);
    }

    public static void m662a(Drawable drawable, boolean z) {
        f350a.m632a(drawable, z);
    }

    public static boolean m663b(Drawable drawable) {
        return f350a.m633b(drawable);
    }

    public static void m657a(Drawable drawable, float f, float f2) {
        f350a.m627a(drawable, f, f2);
    }

    public static void m659a(Drawable drawable, int i, int i2, int i3, int i4) {
        f350a.m629a(drawable, i, i2, i3, i4);
    }

    public static void m658a(Drawable drawable, int i) {
        f350a.m628a(drawable, i);
    }

    public static void m660a(Drawable drawable, ColorStateList colorStateList) {
        f350a.m630a(drawable, colorStateList);
    }

    public static void m661a(Drawable drawable, Mode mode) {
        f350a.m631a(drawable, mode);
    }

    public static Drawable m664c(Drawable drawable) {
        return f350a.m634c(drawable);
    }
}
