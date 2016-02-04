package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityNodeInfo.CollectionInfo;
import android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo;

class AccessibilityNodeInfoCompatApi21 {
    public static Object m1553a(int i, int i2, boolean z, int i3) {
        return CollectionInfo.obtain(i, i2, z, i3);
    }

    public static Object m1552a(int i, int i2, int i3, int i4, boolean z, boolean z2) {
        return CollectionItemInfo.obtain(i, i2, i3, i4, z, z2);
    }
}
