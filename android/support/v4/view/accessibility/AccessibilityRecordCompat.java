package android.support.v4.view.accessibility;

import android.os.Build.VERSION;
import android.view.View;

public class AccessibilityRecordCompat {
    private static final AccessibilityRecordImpl f554a;
    private final Object f555b;

    interface AccessibilityRecordImpl {
        Object m1604a();

        void m1605a(Object obj, int i);

        void m1606a(Object obj, View view);

        void m1607a(Object obj, boolean z);

        void m1608b(Object obj, int i);

        void m1609c(Object obj, int i);

        void m1610d(Object obj, int i);

        void m1611e(Object obj, int i);

        void m1612f(Object obj, int i);

        void m1613g(Object obj, int i);
    }

    class AccessibilityRecordStubImpl implements AccessibilityRecordImpl {
        AccessibilityRecordStubImpl() {
        }

        public Object m1614a() {
            return null;
        }

        public void m1615a(Object obj, int i) {
        }

        public void m1618b(Object obj, int i) {
        }

        public void m1622f(Object obj, int i) {
        }

        public void m1623g(Object obj, int i) {
        }

        public void m1619c(Object obj, int i) {
        }

        public void m1620d(Object obj, int i) {
        }

        public void m1617a(Object obj, boolean z) {
        }

        public void m1616a(Object obj, View view) {
        }

        public void m1621e(Object obj, int i) {
        }
    }

    class AccessibilityRecordIcsImpl extends AccessibilityRecordStubImpl {
        AccessibilityRecordIcsImpl() {
        }

        public Object m1624a() {
            return AccessibilityRecordCompatIcs.m1644a();
        }

        public void m1625a(Object obj, int i) {
            AccessibilityRecordCompatIcs.m1645a(obj, i);
        }

        public void m1628b(Object obj, int i) {
            AccessibilityRecordCompatIcs.m1648b(obj, i);
        }

        public void m1629c(Object obj, int i) {
            AccessibilityRecordCompatIcs.m1649c(obj, i);
        }

        public void m1630d(Object obj, int i) {
            AccessibilityRecordCompatIcs.m1650d(obj, i);
        }

        public void m1627a(Object obj, boolean z) {
            AccessibilityRecordCompatIcs.m1647a(obj, z);
        }

        public void m1626a(Object obj, View view) {
            AccessibilityRecordCompatIcs.m1646a(obj, view);
        }

        public void m1631e(Object obj, int i) {
            AccessibilityRecordCompatIcs.m1651e(obj, i);
        }
    }

    class AccessibilityRecordIcsMr1Impl extends AccessibilityRecordIcsImpl {
        AccessibilityRecordIcsMr1Impl() {
        }

        public void m1632f(Object obj, int i) {
            AccessibilityRecordCompatIcsMr1.m1652a(obj, i);
        }

        public void m1633g(Object obj, int i) {
            AccessibilityRecordCompatIcsMr1.m1653b(obj, i);
        }
    }

    class AccessibilityRecordJellyBeanImpl extends AccessibilityRecordIcsMr1Impl {
        AccessibilityRecordJellyBeanImpl() {
        }
    }

    static {
        if (VERSION.SDK_INT >= 16) {
            f554a = new AccessibilityRecordJellyBeanImpl();
        } else if (VERSION.SDK_INT >= 15) {
            f554a = new AccessibilityRecordIcsMr1Impl();
        } else if (VERSION.SDK_INT >= 14) {
            f554a = new AccessibilityRecordIcsImpl();
        } else {
            f554a = new AccessibilityRecordStubImpl();
        }
    }

    public AccessibilityRecordCompat(Object obj) {
        this.f555b = obj;
    }

    public static AccessibilityRecordCompat m1634a() {
        return new AccessibilityRecordCompat(f554a.m1604a());
    }

    public void m1636a(View view) {
        f554a.m1606a(this.f555b, view);
    }

    public void m1637a(boolean z) {
        f554a.m1607a(this.f555b, z);
    }

    public void m1635a(int i) {
        f554a.m1608b(this.f555b, i);
    }

    public void m1638b(int i) {
        f554a.m1605a(this.f555b, i);
    }

    public void m1639c(int i) {
        f554a.m1611e(this.f555b, i);
    }

    public void m1640d(int i) {
        f554a.m1609c(this.f555b, i);
    }

    public void m1641e(int i) {
        f554a.m1610d(this.f555b, i);
    }

    public void m1642f(int i) {
        f554a.m1612f(this.f555b, i);
    }

    public void m1643g(int i) {
        f554a.m1613g(this.f555b, i);
    }

    public int hashCode() {
        return this.f555b == null ? 0 : this.f555b.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AccessibilityRecordCompat accessibilityRecordCompat = (AccessibilityRecordCompat) obj;
        if (this.f555b == null) {
            if (accessibilityRecordCompat.f555b != null) {
                return false;
            }
            return true;
        } else if (this.f555b.equals(accessibilityRecordCompat.f555b)) {
            return true;
        } else {
            return false;
        }
    }
}
