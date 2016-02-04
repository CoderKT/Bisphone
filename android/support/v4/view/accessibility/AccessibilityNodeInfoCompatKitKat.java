package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeInfo.CollectionInfo;
import android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo;

class AccessibilityNodeInfoCompatKitKat {
    public static void m1577a(Object obj, Object obj2) {
        ((AccessibilityNodeInfo) obj).setCollectionInfo((CollectionInfo) obj2);
    }

    public static void m1578b(Object obj, Object obj2) {
        ((AccessibilityNodeInfo) obj).setCollectionItemInfo((CollectionItemInfo) obj2);
    }

    public static Object m1576a(int i, int i2, boolean z, int i3) {
        return CollectionInfo.obtain(i, i2, z);
    }

    public static Object m1575a(int i, int i2, int i3, int i4, boolean z) {
        return CollectionItemInfo.obtain(i, i2, i3, i4, z);
    }
}
