package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.CompoundButton;
import java.lang.reflect.Field;

class CompoundButtonCompatDonut {
    private static Field f587a;
    private static boolean f588b;

    static void m1715a(CompoundButton compoundButton, ColorStateList colorStateList) {
        if (compoundButton instanceof TintableCompoundButton) {
            ((TintableCompoundButton) compoundButton).setSupportButtonTintList(colorStateList);
        }
    }

    static void m1716a(CompoundButton compoundButton, Mode mode) {
        if (compoundButton instanceof TintableCompoundButton) {
            ((TintableCompoundButton) compoundButton).setSupportButtonTintMode(mode);
        }
    }

    static Drawable m1714a(CompoundButton compoundButton) {
        if (!f588b) {
            try {
                f587a = CompoundButton.class.getDeclaredField("mButtonDrawable");
                f587a.setAccessible(true);
            } catch (Throwable e) {
                Log.i("CompoundButtonCompatDonut", "Failed to retrieve mButtonDrawable field", e);
            }
            f588b = true;
        }
        if (f587a != null) {
            try {
                return (Drawable) f587a.get(compoundButton);
            } catch (Throwable e2) {
                Log.i("CompoundButtonCompatDonut", "Failed to get button drawable via reflection", e2);
                f587a = null;
            }
        }
        return null;
    }
}
