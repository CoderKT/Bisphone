package android.support.v13.app;

import android.app.Fragment;
import android.os.Build.VERSION;

public class FragmentCompat {
    static final FragmentCompatImpl f7a;

    interface FragmentCompatImpl {
        void m37a(Fragment fragment, boolean z);

        void m38b(Fragment fragment, boolean z);
    }

    class BaseFragmentCompatImpl implements FragmentCompatImpl {
        BaseFragmentCompatImpl() {
        }

        public void m39a(Fragment fragment, boolean z) {
        }

        public void m40b(Fragment fragment, boolean z) {
        }
    }

    class ICSFragmentCompatImpl extends BaseFragmentCompatImpl {
        ICSFragmentCompatImpl() {
        }

        public void m41a(Fragment fragment, boolean z) {
            FragmentCompatICS.m45a(fragment, z);
        }
    }

    class ICSMR1FragmentCompatImpl extends ICSFragmentCompatImpl {
        ICSMR1FragmentCompatImpl() {
        }

        public void m42b(Fragment fragment, boolean z) {
            FragmentCompatICSMR1.m46a(fragment, z);
        }
    }

    class MncFragmentCompatImpl extends ICSMR1FragmentCompatImpl {
        MncFragmentCompatImpl() {
        }
    }

    static {
        if (VERSION.SDK_INT >= 23) {
            f7a = new MncFragmentCompatImpl();
        } else if (VERSION.SDK_INT >= 15) {
            f7a = new ICSMR1FragmentCompatImpl();
        } else if (VERSION.SDK_INT >= 14) {
            f7a = new ICSFragmentCompatImpl();
        } else {
            f7a = new BaseFragmentCompatImpl();
        }
    }

    public static void m43a(Fragment fragment, boolean z) {
        f7a.m37a(fragment, z);
    }

    public static void m44b(Fragment fragment, boolean z) {
        f7a.m38b(fragment, z);
    }
}
