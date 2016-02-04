package android.support.v4.view.accessibility;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.List;

class AccessibilityNodeProviderCompatJellyBean {

    interface AccessibilityNodeInfoBridge {
        Object m1580a(int i);

        List<Object> m1581a(String str, int i);

        boolean m1582a(int i, int i2, Bundle bundle);
    }

    /* renamed from: android.support.v4.view.accessibility.AccessibilityNodeProviderCompatJellyBean.1 */
    final class C00311 extends AccessibilityNodeProvider {
        final /* synthetic */ AccessibilityNodeInfoBridge f552a;

        C00311(AccessibilityNodeInfoBridge accessibilityNodeInfoBridge) {
            this.f552a = accessibilityNodeInfoBridge;
        }

        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
            return (AccessibilityNodeInfo) this.f552a.m1580a(i);
        }

        public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i) {
            return this.f552a.m1581a(str, i);
        }

        public boolean performAction(int i, int i2, Bundle bundle) {
            return this.f552a.m1582a(i, i2, bundle);
        }
    }

    public static Object m1602a(AccessibilityNodeInfoBridge accessibilityNodeInfoBridge) {
        return new C00311(accessibilityNodeInfoBridge);
    }
}
