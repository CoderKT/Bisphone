package android.support.v4.view.accessibility;

import android.os.Build.VERSION;
import android.view.accessibility.AccessibilityEvent;

public class AccessibilityEventCompat {
    private static final AccessibilityEventVersionImpl f541a;

    interface AccessibilityEventVersionImpl {
        int m1435a(AccessibilityEvent accessibilityEvent);

        void m1436a(AccessibilityEvent accessibilityEvent, int i);
    }

    class AccessibilityEventStubImpl implements AccessibilityEventVersionImpl {
        AccessibilityEventStubImpl() {
        }

        public void m1438a(AccessibilityEvent accessibilityEvent, int i) {
        }

        public int m1437a(AccessibilityEvent accessibilityEvent) {
            return 0;
        }
    }

    class AccessibilityEventIcsImpl extends AccessibilityEventStubImpl {
        AccessibilityEventIcsImpl() {
        }
    }

    class AccessibilityEventKitKatImpl extends AccessibilityEventIcsImpl {
        AccessibilityEventKitKatImpl() {
        }

        public void m1440a(AccessibilityEvent accessibilityEvent, int i) {
            AccessibilityEventCompatKitKat.m1445a(accessibilityEvent, i);
        }

        public int m1439a(AccessibilityEvent accessibilityEvent) {
            return AccessibilityEventCompatKitKat.m1444a(accessibilityEvent);
        }
    }

    static {
        if (VERSION.SDK_INT >= 19) {
            f541a = new AccessibilityEventKitKatImpl();
        } else if (VERSION.SDK_INT >= 14) {
            f541a = new AccessibilityEventIcsImpl();
        } else {
            f541a = new AccessibilityEventStubImpl();
        }
    }

    public static AccessibilityRecordCompat m1441a(AccessibilityEvent accessibilityEvent) {
        return new AccessibilityRecordCompat(accessibilityEvent);
    }

    public static void m1442a(AccessibilityEvent accessibilityEvent, int i) {
        f541a.m1436a(accessibilityEvent, i);
    }

    public static int m1443b(AccessibilityEvent accessibilityEvent) {
        return f541a.m1435a(accessibilityEvent);
    }
}
