package android.support.v4.widget;

import android.widget.PopupWindow;
import java.lang.reflect.Method;

class PopupWindowCompatGingerbread {
    private static Method f634a;
    private static boolean f635b;

    static void m1833a(PopupWindow popupWindow, int i) {
        if (!f635b) {
            try {
                f634a = PopupWindow.class.getDeclaredMethod("setWindowLayoutType", new Class[]{Integer.TYPE});
                f634a.setAccessible(true);
            } catch (Exception e) {
            }
            f635b = true;
        }
        if (f634a != null) {
            try {
                f634a.invoke(popupWindow, new Object[]{Integer.valueOf(i)});
            } catch (Exception e2) {
            }
        }
    }
}
