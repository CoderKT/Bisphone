package android.support.v4.view.accessibility;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.List;

class AccessibilityNodeProviderCompatKitKat {

    interface AccessibilityNodeInfoBridge {
        Object m1588a(int i);

        List<Object> m1589a(String str, int i);

        boolean m1590a(int i, int i2, Bundle bundle);

        Object m1591b(int i);
    }

    /* renamed from: android.support.v4.view.accessibility.AccessibilityNodeProviderCompatKitKat.1 */
    final class C00321 extends AccessibilityNodeProvider {
        final /* synthetic */ AccessibilityNodeInfoBridge f553a;

        C00321(AccessibilityNodeInfoBridge accessibilityNodeInfoBridge) {
            this.f553a = accessibilityNodeInfoBridge;
        }

        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
            return (AccessibilityNodeInfo) this.f553a.m1588a(i);
        }

        public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i) {
            return this.f553a.m1589a(str, i);
        }

        public boolean performAction(int i, int i2, Bundle bundle) {
            return this.f553a.m1590a(i, i2, bundle);
        }

        public AccessibilityNodeInfo findFocus(int i) {
            return (AccessibilityNodeInfo) this.f553a.m1591b(i);
        }
    }

    public static Object m1603a(AccessibilityNodeInfoBridge accessibilityNodeInfoBridge) {
        return new C00321(accessibilityNodeInfoBridge);
    }
}
