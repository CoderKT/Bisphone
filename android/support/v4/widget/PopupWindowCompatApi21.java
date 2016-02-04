package android.support.v4.widget;

import android.util.Log;
import android.widget.PopupWindow;
import java.lang.reflect.Field;

class PopupWindowCompatApi21 {
    private static Field f633a;

    static {
        try {
            f633a = PopupWindow.class.getDeclaredField("mOverlapAnchor");
            f633a.setAccessible(true);
        } catch (Throwable e) {
            Log.i("PopupWindowCompatApi21", "Could not fetch mOverlapAnchor field from PopupWindow", e);
        }
    }

    static void m1830a(PopupWindow popupWindow, boolean z) {
        if (f633a != null) {
            try {
                f633a.set(popupWindow, Boolean.valueOf(z));
            } catch (Throwable e) {
                Log.i("PopupWindowCompatApi21", "Could not set overlap anchor field in PopupWindow", e);
            }
        }
    }
}
