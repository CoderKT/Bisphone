package android.support.v4.view.accessibility;

import android.graphics.Rect;
import android.os.Build.VERSION;
import app.C0110R;
import se.emilsjolander.stickylistheaders.C1128R;

public class AccessibilityNodeInfoCompat {
    private static final AccessibilityNodeInfoImpl f544a;
    private final Object f545b;

    interface AccessibilityNodeInfoImpl {
        int m1446a(Object obj);

        Object m1447a(int i, int i2, int i3, int i4, boolean z, boolean z2);

        Object m1448a(int i, int i2, boolean z, int i3);

        void m1449a(Object obj, int i);

        void m1450a(Object obj, Rect rect);

        void m1451a(Object obj, CharSequence charSequence);

        void m1452a(Object obj, Object obj2);

        void m1453a(Object obj, boolean z);

        CharSequence m1454b(Object obj);

        void m1455b(Object obj, Rect rect);

        void m1456b(Object obj, Object obj2);

        CharSequence m1457c(Object obj);

        CharSequence m1458d(Object obj);

        CharSequence m1459e(Object obj);

        boolean m1460f(Object obj);

        boolean m1461g(Object obj);

        boolean m1462h(Object obj);

        boolean m1463i(Object obj);

        boolean m1464j(Object obj);

        boolean m1465k(Object obj);

        boolean m1466l(Object obj);

        boolean m1467m(Object obj);

        boolean m1468n(Object obj);

        boolean m1469o(Object obj);

        String m1470p(Object obj);
    }

    class AccessibilityNodeInfoStubImpl implements AccessibilityNodeInfoImpl {
        AccessibilityNodeInfoStubImpl() {
        }

        public void m1474a(Object obj, int i) {
        }

        public int m1471a(Object obj) {
            return 0;
        }

        public void m1475a(Object obj, Rect rect) {
        }

        public void m1480b(Object obj, Rect rect) {
        }

        public CharSequence m1479b(Object obj) {
            return null;
        }

        public CharSequence m1482c(Object obj) {
            return null;
        }

        public CharSequence m1483d(Object obj) {
            return null;
        }

        public CharSequence m1484e(Object obj) {
            return null;
        }

        public boolean m1485f(Object obj) {
            return false;
        }

        public boolean m1486g(Object obj) {
            return false;
        }

        public boolean m1487h(Object obj) {
            return false;
        }

        public boolean m1488i(Object obj) {
            return false;
        }

        public boolean m1489j(Object obj) {
            return false;
        }

        public boolean m1490k(Object obj) {
            return false;
        }

        public boolean m1491l(Object obj) {
            return false;
        }

        public boolean m1492m(Object obj) {
            return false;
        }

        public boolean m1493n(Object obj) {
            return false;
        }

        public boolean m1494o(Object obj) {
            return false;
        }

        public void m1476a(Object obj, CharSequence charSequence) {
        }

        public void m1478a(Object obj, boolean z) {
        }

        public String m1495p(Object obj) {
            return null;
        }

        public void m1477a(Object obj, Object obj2) {
        }

        public void m1481b(Object obj, Object obj2) {
        }

        public Object m1473a(int i, int i2, boolean z, int i3) {
            return null;
        }

        public Object m1472a(int i, int i2, int i3, int i4, boolean z, boolean z2) {
            return null;
        }
    }

    class AccessibilityNodeInfoIcsImpl extends AccessibilityNodeInfoStubImpl {
        AccessibilityNodeInfoIcsImpl() {
        }

        public void m1497a(Object obj, int i) {
            AccessibilityNodeInfoCompatIcs.m1555a(obj, i);
        }

        public int m1496a(Object obj) {
            return AccessibilityNodeInfoCompatIcs.m1554a(obj);
        }

        public void m1498a(Object obj, Rect rect) {
            AccessibilityNodeInfoCompatIcs.m1556a(obj, rect);
        }

        public void m1502b(Object obj, Rect rect) {
            AccessibilityNodeInfoCompatIcs.m1560b(obj, rect);
        }

        public CharSequence m1501b(Object obj) {
            return AccessibilityNodeInfoCompatIcs.m1559b(obj);
        }

        public CharSequence m1503c(Object obj) {
            return AccessibilityNodeInfoCompatIcs.m1561c(obj);
        }

        public CharSequence m1504d(Object obj) {
            return AccessibilityNodeInfoCompatIcs.m1562d(obj);
        }

        public CharSequence m1505e(Object obj) {
            return AccessibilityNodeInfoCompatIcs.m1563e(obj);
        }

        public boolean m1506f(Object obj) {
            return AccessibilityNodeInfoCompatIcs.m1564f(obj);
        }

        public boolean m1507g(Object obj) {
            return AccessibilityNodeInfoCompatIcs.m1565g(obj);
        }

        public boolean m1508h(Object obj) {
            return AccessibilityNodeInfoCompatIcs.m1566h(obj);
        }

        public boolean m1509i(Object obj) {
            return AccessibilityNodeInfoCompatIcs.m1567i(obj);
        }

        public boolean m1510j(Object obj) {
            return AccessibilityNodeInfoCompatIcs.m1568j(obj);
        }

        public boolean m1511k(Object obj) {
            return AccessibilityNodeInfoCompatIcs.m1569k(obj);
        }

        public boolean m1512l(Object obj) {
            return AccessibilityNodeInfoCompatIcs.m1570l(obj);
        }

        public boolean m1513m(Object obj) {
            return AccessibilityNodeInfoCompatIcs.m1571m(obj);
        }

        public boolean m1514n(Object obj) {
            return AccessibilityNodeInfoCompatIcs.m1572n(obj);
        }

        public boolean m1515o(Object obj) {
            return AccessibilityNodeInfoCompatIcs.m1573o(obj);
        }

        public void m1499a(Object obj, CharSequence charSequence) {
            AccessibilityNodeInfoCompatIcs.m1557a(obj, charSequence);
        }

        public void m1500a(Object obj, boolean z) {
            AccessibilityNodeInfoCompatIcs.m1558a(obj, z);
        }
    }

    class AccessibilityNodeInfoJellybeanImpl extends AccessibilityNodeInfoIcsImpl {
        AccessibilityNodeInfoJellybeanImpl() {
        }
    }

    class AccessibilityNodeInfoJellybeanMr1Impl extends AccessibilityNodeInfoJellybeanImpl {
        AccessibilityNodeInfoJellybeanMr1Impl() {
        }
    }

    class AccessibilityNodeInfoJellybeanMr2Impl extends AccessibilityNodeInfoJellybeanMr1Impl {
        AccessibilityNodeInfoJellybeanMr2Impl() {
        }

        public String m1516p(Object obj) {
            return AccessibilityNodeInfoCompatJellybeanMr2.m1574a(obj);
        }
    }

    class AccessibilityNodeInfoKitKatImpl extends AccessibilityNodeInfoJellybeanMr2Impl {
        AccessibilityNodeInfoKitKatImpl() {
        }

        public void m1519a(Object obj, Object obj2) {
            AccessibilityNodeInfoCompatKitKat.m1577a(obj, obj2);
        }

        public Object m1518a(int i, int i2, boolean z, int i3) {
            return AccessibilityNodeInfoCompatKitKat.m1576a(i, i2, z, i3);
        }

        public Object m1517a(int i, int i2, int i3, int i4, boolean z, boolean z2) {
            return AccessibilityNodeInfoCompatKitKat.m1575a(i, i2, i3, i4, z);
        }

        public void m1520b(Object obj, Object obj2) {
            AccessibilityNodeInfoCompatKitKat.m1578b(obj, obj2);
        }
    }

    class AccessibilityNodeInfoApi21Impl extends AccessibilityNodeInfoKitKatImpl {
        AccessibilityNodeInfoApi21Impl() {
        }

        public Object m1522a(int i, int i2, boolean z, int i3) {
            return AccessibilityNodeInfoCompatApi21.m1553a(i, i2, z, i3);
        }

        public Object m1521a(int i, int i2, int i3, int i4, boolean z, boolean z2) {
            return AccessibilityNodeInfoCompatApi21.m1552a(i, i2, i3, i4, z, z2);
        }
    }

    class AccessibilityNodeInfoApi22Impl extends AccessibilityNodeInfoApi21Impl {
        AccessibilityNodeInfoApi22Impl() {
        }
    }

    public class CollectionInfoCompat {
        final Object f542a;

        public static CollectionInfoCompat m1523a(int i, int i2, boolean z, int i3) {
            return new CollectionInfoCompat(AccessibilityNodeInfoCompat.f544a.m1448a(i, i2, z, i3));
        }

        private CollectionInfoCompat(Object obj) {
            this.f542a = obj;
        }
    }

    public class CollectionItemInfoCompat {
        private final Object f543a;

        public static CollectionItemInfoCompat m1524a(int i, int i2, int i3, int i4, boolean z, boolean z2) {
            return new CollectionItemInfoCompat(AccessibilityNodeInfoCompat.f544a.m1447a(i, i2, i3, i4, z, z2));
        }

        private CollectionItemInfoCompat(Object obj) {
            this.f543a = obj;
        }
    }

    static {
        if (VERSION.SDK_INT >= 22) {
            f544a = new AccessibilityNodeInfoApi22Impl();
        } else if (VERSION.SDK_INT >= 21) {
            f544a = new AccessibilityNodeInfoApi21Impl();
        } else if (VERSION.SDK_INT >= 19) {
            f544a = new AccessibilityNodeInfoKitKatImpl();
        } else if (VERSION.SDK_INT >= 18) {
            f544a = new AccessibilityNodeInfoJellybeanMr2Impl();
        } else if (VERSION.SDK_INT >= 17) {
            f544a = new AccessibilityNodeInfoJellybeanMr1Impl();
        } else if (VERSION.SDK_INT >= 16) {
            f544a = new AccessibilityNodeInfoJellybeanImpl();
        } else if (VERSION.SDK_INT >= 14) {
            f544a = new AccessibilityNodeInfoIcsImpl();
        } else {
            f544a = new AccessibilityNodeInfoStubImpl();
        }
    }

    public AccessibilityNodeInfoCompat(Object obj) {
        this.f545b = obj;
    }

    public Object m1528a() {
        return this.f545b;
    }

    public int m1534b() {
        return f544a.m1446a(this.f545b);
    }

    public void m1529a(int i) {
        f544a.m1449a(this.f545b, i);
    }

    public void m1530a(Rect rect) {
        f544a.m1450a(this.f545b, rect);
    }

    public void m1535b(Rect rect) {
        f544a.m1455b(this.f545b, rect);
    }

    public boolean m1537c() {
        return f544a.m1460f(this.f545b);
    }

    public boolean m1538d() {
        return f544a.m1461g(this.f545b);
    }

    public boolean m1539e() {
        return f544a.m1464j(this.f545b);
    }

    public boolean m1540f() {
        return f544a.m1465k(this.f545b);
    }

    public boolean m1541g() {
        return f544a.m1469o(this.f545b);
    }

    public boolean m1542h() {
        return f544a.m1462h(this.f545b);
    }

    public boolean m1543i() {
        return f544a.m1466l(this.f545b);
    }

    public boolean m1544j() {
        return f544a.m1463i(this.f545b);
    }

    public boolean m1545k() {
        return f544a.m1467m(this.f545b);
    }

    public boolean m1546l() {
        return f544a.m1468n(this.f545b);
    }

    public void m1533a(boolean z) {
        f544a.m1453a(this.f545b, z);
    }

    public CharSequence m1547m() {
        return f544a.m1458d(this.f545b);
    }

    public CharSequence m1548n() {
        return f544a.m1454b(this.f545b);
    }

    public void m1531a(CharSequence charSequence) {
        f544a.m1451a(this.f545b, charSequence);
    }

    public CharSequence m1549o() {
        return f544a.m1459e(this.f545b);
    }

    public CharSequence m1550p() {
        return f544a.m1457c(this.f545b);
    }

    public String m1551q() {
        return f544a.m1470p(this.f545b);
    }

    public void m1532a(Object obj) {
        f544a.m1452a(this.f545b, ((CollectionInfoCompat) obj).f542a);
    }

    public void m1536b(Object obj) {
        f544a.m1456b(this.f545b, ((CollectionItemInfoCompat) obj).f543a);
    }

    public int hashCode() {
        return this.f545b == null ? 0 : this.f545b.hashCode();
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
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
        if (this.f545b == null) {
            if (accessibilityNodeInfoCompat.f545b != null) {
                return false;
            }
            return true;
        } else if (this.f545b.equals(accessibilityNodeInfoCompat.f545b)) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString());
        Rect rect = new Rect();
        m1530a(rect);
        stringBuilder.append("; boundsInParent: " + rect);
        m1535b(rect);
        stringBuilder.append("; boundsInScreen: " + rect);
        stringBuilder.append("; packageName: ").append(m1547m());
        stringBuilder.append("; className: ").append(m1548n());
        stringBuilder.append("; text: ").append(m1549o());
        stringBuilder.append("; contentDescription: ").append(m1550p());
        stringBuilder.append("; viewId: ").append(m1551q());
        stringBuilder.append("; checkable: ").append(m1537c());
        stringBuilder.append("; checked: ").append(m1538d());
        stringBuilder.append("; focusable: ").append(m1539e());
        stringBuilder.append("; focused: ").append(m1540f());
        stringBuilder.append("; selected: ").append(m1541g());
        stringBuilder.append("; clickable: ").append(m1542h());
        stringBuilder.append("; longClickable: ").append(m1543i());
        stringBuilder.append("; enabled: ").append(m1544j());
        stringBuilder.append("; password: ").append(m1545k());
        stringBuilder.append("; scrollable: " + m1546l());
        stringBuilder.append("; [");
        int b = m1534b();
        while (b != 0) {
            int numberOfTrailingZeros = 1 << Integer.numberOfTrailingZeros(b);
            b &= numberOfTrailingZeros ^ -1;
            stringBuilder.append(m1526b(numberOfTrailingZeros));
            if (b != 0) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private static String m1526b(int i) {
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return "ACTION_FOCUS";
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                return "ACTION_CLEAR_FOCUS";
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                return "ACTION_SELECT";
            case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                return "ACTION_CLEAR_SELECTION";
            case C1128R.styleable.StickyListHeadersListView_android_dividerHeight /*16*/:
                return "ACTION_CLICK";
            case C0110R.styleable.Theme_actionModeCutDrawable /*32*/:
                return "ACTION_LONG_CLICK";
            case C0110R.styleable.Theme_imageButtonStyle /*64*/:
                return "ACTION_ACCESSIBILITY_FOCUS";
            case 128:
                return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
            case 256:
                return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
            case 512:
                return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
            case 1024:
                return "ACTION_NEXT_HTML_ELEMENT";
            case 2048:
                return "ACTION_PREVIOUS_HTML_ELEMENT";
            case 4096:
                return "ACTION_SCROLL_FORWARD";
            case 8192:
                return "ACTION_SCROLL_BACKWARD";
            case 16384:
                return "ACTION_COPY";
            case 32768:
                return "ACTION_PASTE";
            case 65536:
                return "ACTION_CUT";
            case 131072:
                return "ACTION_SET_SELECTION";
            default:
                return "ACTION_UNKNOWN";
        }
    }
}
