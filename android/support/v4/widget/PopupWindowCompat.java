package android.support.v4.widget;

import android.os.Build.VERSION;
import android.view.View;
import android.widget.PopupWindow;

public class PopupWindowCompat {
    static final PopupWindowImpl f632a;

    interface PopupWindowImpl {
        void m1816a(PopupWindow popupWindow, int i);

        void m1817a(PopupWindow popupWindow, View view, int i, int i2, int i3);

        void m1818a(PopupWindow popupWindow, boolean z);
    }

    class BasePopupWindowImpl implements PopupWindowImpl {
        BasePopupWindowImpl() {
        }

        public void m1820a(PopupWindow popupWindow, View view, int i, int i2, int i3) {
            popupWindow.showAsDropDown(view, i, i2);
        }

        public void m1821a(PopupWindow popupWindow, boolean z) {
        }

        public void m1819a(PopupWindow popupWindow, int i) {
        }
    }

    class GingerbreadPopupWindowImpl extends BasePopupWindowImpl {
        GingerbreadPopupWindowImpl() {
        }

        public void m1822a(PopupWindow popupWindow, int i) {
            PopupWindowCompatGingerbread.m1833a(popupWindow, i);
        }
    }

    class KitKatPopupWindowImpl extends GingerbreadPopupWindowImpl {
        KitKatPopupWindowImpl() {
        }

        public void m1823a(PopupWindow popupWindow, View view, int i, int i2, int i3) {
            PopupWindowCompatKitKat.m1834a(popupWindow, view, i, i2, i3);
        }
    }

    class Api21PopupWindowImpl extends KitKatPopupWindowImpl {
        Api21PopupWindowImpl() {
        }

        public void m1824a(PopupWindow popupWindow, boolean z) {
            PopupWindowCompatApi21.m1830a(popupWindow, z);
        }
    }

    class Api23PopupWindowImpl extends Api21PopupWindowImpl {
        Api23PopupWindowImpl() {
        }

        public void m1826a(PopupWindow popupWindow, boolean z) {
            PopupWindowCompatApi23.m1832a(popupWindow, z);
        }

        public void m1825a(PopupWindow popupWindow, int i) {
            PopupWindowCompatApi23.m1831a(popupWindow, i);
        }
    }

    static {
        int i = VERSION.SDK_INT;
        if (i >= 23) {
            f632a = new Api23PopupWindowImpl();
        } else if (i >= 21) {
            f632a = new Api21PopupWindowImpl();
        } else if (i >= 19) {
            f632a = new KitKatPopupWindowImpl();
        } else if (i >= 9) {
            f632a = new GingerbreadPopupWindowImpl();
        } else {
            f632a = new BasePopupWindowImpl();
        }
    }

    public static void m1828a(PopupWindow popupWindow, View view, int i, int i2, int i3) {
        f632a.m1817a(popupWindow, view, i, i2, i3);
    }

    public static void m1829a(PopupWindow popupWindow, boolean z) {
        f632a.m1818a(popupWindow, z);
    }

    public static void m1827a(PopupWindow popupWindow, int i) {
        f632a.m1816a(popupWindow, i);
    }
}
