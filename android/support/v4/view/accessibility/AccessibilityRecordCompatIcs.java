package android.support.v4.view.accessibility;

import android.view.View;
import android.view.accessibility.AccessibilityRecord;

class AccessibilityRecordCompatIcs {
    public static Object m1644a() {
        return AccessibilityRecord.obtain();
    }

    public static void m1645a(Object obj, int i) {
        ((AccessibilityRecord) obj).setFromIndex(i);
    }

    public static void m1648b(Object obj, int i) {
        ((AccessibilityRecord) obj).setItemCount(i);
    }

    public static void m1649c(Object obj, int i) {
        ((AccessibilityRecord) obj).setScrollX(i);
    }

    public static void m1650d(Object obj, int i) {
        ((AccessibilityRecord) obj).setScrollY(i);
    }

    public static void m1647a(Object obj, boolean z) {
        ((AccessibilityRecord) obj).setScrollable(z);
    }

    public static void m1646a(Object obj, View view) {
        ((AccessibilityRecord) obj).setSource(view);
    }

    public static void m1651e(Object obj, int i) {
        ((AccessibilityRecord) obj).setToIndex(i);
    }
}
