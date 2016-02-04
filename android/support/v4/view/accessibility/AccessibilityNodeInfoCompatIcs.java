package android.support.v4.view.accessibility;

import android.graphics.Rect;
import android.view.accessibility.AccessibilityNodeInfo;

class AccessibilityNodeInfoCompatIcs {
    public static void m1555a(Object obj, int i) {
        ((AccessibilityNodeInfo) obj).addAction(i);
    }

    public static int m1554a(Object obj) {
        return ((AccessibilityNodeInfo) obj).getActions();
    }

    public static void m1556a(Object obj, Rect rect) {
        ((AccessibilityNodeInfo) obj).getBoundsInParent(rect);
    }

    public static void m1560b(Object obj, Rect rect) {
        ((AccessibilityNodeInfo) obj).getBoundsInScreen(rect);
    }

    public static CharSequence m1559b(Object obj) {
        return ((AccessibilityNodeInfo) obj).getClassName();
    }

    public static CharSequence m1561c(Object obj) {
        return ((AccessibilityNodeInfo) obj).getContentDescription();
    }

    public static CharSequence m1562d(Object obj) {
        return ((AccessibilityNodeInfo) obj).getPackageName();
    }

    public static CharSequence m1563e(Object obj) {
        return ((AccessibilityNodeInfo) obj).getText();
    }

    public static boolean m1564f(Object obj) {
        return ((AccessibilityNodeInfo) obj).isCheckable();
    }

    public static boolean m1565g(Object obj) {
        return ((AccessibilityNodeInfo) obj).isChecked();
    }

    public static boolean m1566h(Object obj) {
        return ((AccessibilityNodeInfo) obj).isClickable();
    }

    public static boolean m1567i(Object obj) {
        return ((AccessibilityNodeInfo) obj).isEnabled();
    }

    public static boolean m1568j(Object obj) {
        return ((AccessibilityNodeInfo) obj).isFocusable();
    }

    public static boolean m1569k(Object obj) {
        return ((AccessibilityNodeInfo) obj).isFocused();
    }

    public static boolean m1570l(Object obj) {
        return ((AccessibilityNodeInfo) obj).isLongClickable();
    }

    public static boolean m1571m(Object obj) {
        return ((AccessibilityNodeInfo) obj).isPassword();
    }

    public static boolean m1572n(Object obj) {
        return ((AccessibilityNodeInfo) obj).isScrollable();
    }

    public static boolean m1573o(Object obj) {
        return ((AccessibilityNodeInfo) obj).isSelected();
    }

    public static void m1557a(Object obj, CharSequence charSequence) {
        ((AccessibilityNodeInfo) obj).setClassName(charSequence);
    }

    public static void m1558a(Object obj, boolean z) {
        ((AccessibilityNodeInfo) obj).setScrollable(z);
    }
}
