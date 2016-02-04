package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.LayoutInflater;

public class LayoutInflaterCompat {
    static final LayoutInflaterCompatImpl f432a;

    interface LayoutInflaterCompatImpl {
        void m915a(LayoutInflater layoutInflater, LayoutInflaterFactory layoutInflaterFactory);
    }

    class LayoutInflaterCompatImplBase implements LayoutInflaterCompatImpl {
        LayoutInflaterCompatImplBase() {
        }

        public void m916a(LayoutInflater layoutInflater, LayoutInflaterFactory layoutInflaterFactory) {
            LayoutInflaterCompatBase.m920a(layoutInflater, layoutInflaterFactory);
        }
    }

    class LayoutInflaterCompatImplV11 extends LayoutInflaterCompatImplBase {
        LayoutInflaterCompatImplV11() {
        }

        public void m917a(LayoutInflater layoutInflater, LayoutInflaterFactory layoutInflaterFactory) {
            LayoutInflaterCompatHC.m921a(layoutInflater, layoutInflaterFactory);
        }
    }

    class LayoutInflaterCompatImplV21 extends LayoutInflaterCompatImplV11 {
        LayoutInflaterCompatImplV21() {
        }

        public void m918a(LayoutInflater layoutInflater, LayoutInflaterFactory layoutInflaterFactory) {
            LayoutInflaterCompatLollipop.m923a(layoutInflater, layoutInflaterFactory);
        }
    }

    static {
        int i = VERSION.SDK_INT;
        if (i >= 21) {
            f432a = new LayoutInflaterCompatImplV21();
        } else if (i >= 11) {
            f432a = new LayoutInflaterCompatImplV11();
        } else {
            f432a = new LayoutInflaterCompatImplBase();
        }
    }

    public static void m919a(LayoutInflater layoutInflater, LayoutInflaterFactory layoutInflaterFactory) {
        f432a.m915a(layoutInflater, layoutInflaterFactory);
    }
}
