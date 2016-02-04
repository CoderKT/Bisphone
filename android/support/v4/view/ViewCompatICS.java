package android.support.v4.view;

import android.view.View;
import android.view.View.AccessibilityDelegate;

class ViewCompatICS {
    public static boolean m1216a(View view, int i) {
        return view.canScrollHorizontally(i);
    }

    public static boolean m1217b(View view, int i) {
        return view.canScrollVertically(i);
    }

    public static void m1215a(View view, Object obj) {
        view.setAccessibilityDelegate((AccessibilityDelegate) obj);
    }
}
