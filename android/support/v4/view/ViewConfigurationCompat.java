package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.ViewConfiguration;

public class ViewConfigurationCompat {
    static final ViewConfigurationVersionImpl f455a;

    interface ViewConfigurationVersionImpl {
        int m1241a(ViewConfiguration viewConfiguration);

        boolean m1242b(ViewConfiguration viewConfiguration);
    }

    class BaseViewConfigurationVersionImpl implements ViewConfigurationVersionImpl {
        BaseViewConfigurationVersionImpl() {
        }

        public int m1243a(ViewConfiguration viewConfiguration) {
            return viewConfiguration.getScaledTouchSlop();
        }

        public boolean m1244b(ViewConfiguration viewConfiguration) {
            return true;
        }
    }

    class FroyoViewConfigurationVersionImpl extends BaseViewConfigurationVersionImpl {
        FroyoViewConfigurationVersionImpl() {
        }

        public int m1245a(ViewConfiguration viewConfiguration) {
            return ViewConfigurationCompatFroyo.m1250a(viewConfiguration);
        }
    }

    class HoneycombViewConfigurationVersionImpl extends FroyoViewConfigurationVersionImpl {
        HoneycombViewConfigurationVersionImpl() {
        }

        public boolean m1246b(ViewConfiguration viewConfiguration) {
            return false;
        }
    }

    class IcsViewConfigurationVersionImpl extends HoneycombViewConfigurationVersionImpl {
        IcsViewConfigurationVersionImpl() {
        }

        public boolean m1247b(ViewConfiguration viewConfiguration) {
            return ViewConfigurationCompatICS.m1251a(viewConfiguration);
        }
    }

    static {
        if (VERSION.SDK_INT >= 14) {
            f455a = new IcsViewConfigurationVersionImpl();
        } else if (VERSION.SDK_INT >= 11) {
            f455a = new HoneycombViewConfigurationVersionImpl();
        } else if (VERSION.SDK_INT >= 8) {
            f455a = new FroyoViewConfigurationVersionImpl();
        } else {
            f455a = new BaseViewConfigurationVersionImpl();
        }
    }

    public static int m1248a(ViewConfiguration viewConfiguration) {
        return f455a.m1241a(viewConfiguration);
    }

    public static boolean m1249b(ViewConfiguration viewConfiguration) {
        return f455a.m1242b(viewConfiguration);
    }
}
