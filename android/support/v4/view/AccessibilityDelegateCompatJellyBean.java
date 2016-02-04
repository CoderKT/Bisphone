package android.support.v4.view;

import android.os.Bundle;
import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;

class AccessibilityDelegateCompatJellyBean {

    public interface AccessibilityDelegateBridgeJellyBean {
        Object m840a(View view);

        void m841a(View view, int i);

        void m842a(View view, Object obj);

        boolean m843a(View view, int i, Bundle bundle);

        boolean m844a(View view, AccessibilityEvent accessibilityEvent);

        boolean m845a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent);

        void m846b(View view, AccessibilityEvent accessibilityEvent);

        void m847c(View view, AccessibilityEvent accessibilityEvent);

        void m848d(View view, AccessibilityEvent accessibilityEvent);
    }

    /* renamed from: android.support.v4.view.AccessibilityDelegateCompatJellyBean.1 */
    final class C00191 extends AccessibilityDelegate {
        final /* synthetic */ AccessibilityDelegateBridgeJellyBean f426a;

        C00191(AccessibilityDelegateBridgeJellyBean accessibilityDelegateBridgeJellyBean) {
            this.f426a = accessibilityDelegateBridgeJellyBean;
        }

        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            return this.f426a.m844a(view, accessibilityEvent);
        }

        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f426a.m846b(view, accessibilityEvent);
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            this.f426a.m842a(view, (Object) accessibilityNodeInfo);
        }

        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f426a.m847c(view, accessibilityEvent);
        }

        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return this.f426a.m845a(viewGroup, view, accessibilityEvent);
        }

        public void sendAccessibilityEvent(View view, int i) {
            this.f426a.m841a(view, i);
        }

        public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
            this.f426a.m848d(view, accessibilityEvent);
        }

        public AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
            return (AccessibilityNodeProvider) this.f426a.m840a(view);
        }

        public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            return this.f426a.m843a(view, i, bundle);
        }
    }

    public static Object m880a(AccessibilityDelegateBridgeJellyBean accessibilityDelegateBridgeJellyBean) {
        return new C00191(accessibilityDelegateBridgeJellyBean);
    }

    public static Object m881a(Object obj, View view) {
        return ((AccessibilityDelegate) obj).getAccessibilityNodeProvider(view);
    }

    public static boolean m882a(Object obj, View view, int i, Bundle bundle) {
        return ((AccessibilityDelegate) obj).performAccessibilityAction(view, i, bundle);
    }
}
