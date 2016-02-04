package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityEvent;

class AccessibilityEventCompatKitKat {
    public static void m1445a(AccessibilityEvent accessibilityEvent, int i) {
        accessibilityEvent.setContentChangeTypes(i);
    }

    public static int m1444a(AccessibilityEvent accessibilityEvent) {
        return accessibilityEvent.getContentChangeTypes();
    }
}
