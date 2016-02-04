package android.support.v4.view;

import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

class AccessibilityDelegateCompatIcs {

    public interface AccessibilityDelegateBridge {
        void m795a(View view, int i);

        void m796a(View view, Object obj);

        boolean m797a(View view, AccessibilityEvent accessibilityEvent);

        boolean m798a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent);

        void m799b(View view, AccessibilityEvent accessibilityEvent);

        void m800c(View view, AccessibilityEvent accessibilityEvent);

        void m801d(View view, AccessibilityEvent accessibilityEvent);
    }

    /* renamed from: android.support.v4.view.AccessibilityDelegateCompatIcs.1 */
    final class C00181 extends AccessibilityDelegate {
        final /* synthetic */ AccessibilityDelegateBridge f425a;

        C00181(AccessibilityDelegateBridge accessibilityDelegateBridge) {
            this.f425a = accessibilityDelegateBridge;
        }

        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            return this.f425a.m797a(view, accessibilityEvent);
        }

        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f425a.m799b(view, accessibilityEvent);
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            this.f425a.m796a(view, (Object) accessibilityNodeInfo);
        }

        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f425a.m800c(view, accessibilityEvent);
        }

        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return this.f425a.m798a(viewGroup, view, accessibilityEvent);
        }

        public void sendAccessibilityEvent(View view, int i) {
            this.f425a.m795a(view, i);
        }

        public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
            this.f425a.m801d(view, accessibilityEvent);
        }
    }

    public static Object m871a() {
        return new AccessibilityDelegate();
    }

    public static Object m872a(AccessibilityDelegateBridge accessibilityDelegateBridge) {
        return new C00181(accessibilityDelegateBridge);
    }

    public static boolean m875a(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        return ((AccessibilityDelegate) obj).dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public static void m877b(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        ((AccessibilityDelegate) obj).onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    public static void m874a(Object obj, View view, Object obj2) {
        ((AccessibilityDelegate) obj).onInitializeAccessibilityNodeInfo(view, (AccessibilityNodeInfo) obj2);
    }

    public static void m878c(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        ((AccessibilityDelegate) obj).onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public static boolean m876a(Object obj, ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return ((AccessibilityDelegate) obj).onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    public static void m873a(Object obj, View view, int i) {
        ((AccessibilityDelegate) obj).sendAccessibilityEvent(view, i);
    }

    public static void m879d(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        ((AccessibilityDelegate) obj).sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }
}
