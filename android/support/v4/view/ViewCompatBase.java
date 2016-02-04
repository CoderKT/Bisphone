package android.support.v4.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.view.View;
import java.lang.reflect.Field;

class ViewCompatBase {
    private static Field f450a;
    private static boolean f451b;
    private static Field f452c;
    private static boolean f453d;

    static void m1194a(View view, ColorStateList colorStateList) {
        if (view instanceof TintableBackgroundView) {
            ((TintableBackgroundView) view).setSupportBackgroundTintList(colorStateList);
        }
    }

    static void m1195a(View view, Mode mode) {
        if (view instanceof TintableBackgroundView) {
            ((TintableBackgroundView) view).setSupportBackgroundTintMode(mode);
        }
    }

    static boolean m1196a(View view) {
        return view.getWidth() > 0 && view.getHeight() > 0;
    }

    static int m1197b(View view) {
        if (!f451b) {
            try {
                f450a = View.class.getDeclaredField("mMinWidth");
                f450a.setAccessible(true);
            } catch (NoSuchFieldException e) {
            }
            f451b = true;
        }
        if (f450a != null) {
            try {
                return ((Integer) f450a.get(view)).intValue();
            } catch (Exception e2) {
            }
        }
        return 0;
    }

    static int m1198c(View view) {
        if (!f453d) {
            try {
                f452c = View.class.getDeclaredField("mMinHeight");
                f452c.setAccessible(true);
            } catch (NoSuchFieldException e) {
            }
            f453d = true;
        }
        if (f452c != null) {
            try {
                return ((Integer) f452c.get(view)).intValue();
            } catch (Exception e2) {
            }
        }
        return 0;
    }

    static boolean m1199d(View view) {
        return view.getWindowToken() != null;
    }
}
