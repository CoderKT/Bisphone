package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.widget.CompoundButton;

public final class CompoundButtonCompat {
    private static final CompoundButtonCompatImpl f586a;

    interface CompoundButtonCompatImpl {
        Drawable m1701a(CompoundButton compoundButton);

        void m1702a(CompoundButton compoundButton, ColorStateList colorStateList);

        void m1703a(CompoundButton compoundButton, Mode mode);
    }

    class BaseCompoundButtonCompat implements CompoundButtonCompatImpl {
        BaseCompoundButtonCompat() {
        }

        public void m1705a(CompoundButton compoundButton, ColorStateList colorStateList) {
            CompoundButtonCompatDonut.m1715a(compoundButton, colorStateList);
        }

        public void m1706a(CompoundButton compoundButton, Mode mode) {
            CompoundButtonCompatDonut.m1716a(compoundButton, mode);
        }

        public Drawable m1704a(CompoundButton compoundButton) {
            return CompoundButtonCompatDonut.m1714a(compoundButton);
        }
    }

    class LollipopCompoundButtonImpl extends BaseCompoundButtonCompat {
        LollipopCompoundButtonImpl() {
        }

        public void m1707a(CompoundButton compoundButton, ColorStateList colorStateList) {
            CompoundButtonCompatLollipop.m1717a(compoundButton, colorStateList);
        }

        public void m1708a(CompoundButton compoundButton, Mode mode) {
            CompoundButtonCompatLollipop.m1718a(compoundButton, mode);
        }
    }

    class Api23CompoundButtonImpl extends LollipopCompoundButtonImpl {
        Api23CompoundButtonImpl() {
        }

        public Drawable m1709a(CompoundButton compoundButton) {
            return CompoundButtonCompatApi23.m1713a(compoundButton);
        }
    }

    static {
        int i = VERSION.SDK_INT;
        if (i >= 23) {
            f586a = new Api23CompoundButtonImpl();
        } else if (i >= 21) {
            f586a = new LollipopCompoundButtonImpl();
        } else {
            f586a = new BaseCompoundButtonCompat();
        }
    }

    public static void m1711a(CompoundButton compoundButton, ColorStateList colorStateList) {
        f586a.m1702a(compoundButton, colorStateList);
    }

    public static void m1712a(CompoundButton compoundButton, Mode mode) {
        f586a.m1703a(compoundButton, mode);
    }

    public static Drawable m1710a(CompoundButton compoundButton) {
        return f586a.m1701a(compoundButton);
    }
}
