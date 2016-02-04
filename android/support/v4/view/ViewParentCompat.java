package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewParent;

public class ViewParentCompat {
    static final ViewParentCompatImpl f522a;

    interface ViewParentCompatImpl {
        void m1314a(ViewParent viewParent, View view);

        void m1315a(ViewParent viewParent, View view, int i, int i2, int i3, int i4);

        void m1316a(ViewParent viewParent, View view, int i, int i2, int[] iArr);

        boolean m1317a(ViewParent viewParent, View view, float f, float f2);

        boolean m1318a(ViewParent viewParent, View view, float f, float f2, boolean z);

        boolean m1319a(ViewParent viewParent, View view, View view2, int i);

        void m1320b(ViewParent viewParent, View view, View view2, int i);
    }

    class ViewParentCompatStubImpl implements ViewParentCompatImpl {
        ViewParentCompatStubImpl() {
        }

        public boolean m1326a(ViewParent viewParent, View view, View view2, int i) {
            if (viewParent instanceof NestedScrollingParent) {
                return ((NestedScrollingParent) viewParent).onStartNestedScroll(view, view2, i);
            }
            return false;
        }

        public void m1327b(ViewParent viewParent, View view, View view2, int i) {
            if (viewParent instanceof NestedScrollingParent) {
                ((NestedScrollingParent) viewParent).onNestedScrollAccepted(view, view2, i);
            }
        }

        public void m1321a(ViewParent viewParent, View view) {
            if (viewParent instanceof NestedScrollingParent) {
                ((NestedScrollingParent) viewParent).onStopNestedScroll(view);
            }
        }

        public void m1322a(ViewParent viewParent, View view, int i, int i2, int i3, int i4) {
            if (viewParent instanceof NestedScrollingParent) {
                ((NestedScrollingParent) viewParent).onNestedScroll(view, i, i2, i3, i4);
            }
        }

        public void m1323a(ViewParent viewParent, View view, int i, int i2, int[] iArr) {
            if (viewParent instanceof NestedScrollingParent) {
                ((NestedScrollingParent) viewParent).onNestedPreScroll(view, i, i2, iArr);
            }
        }

        public boolean m1325a(ViewParent viewParent, View view, float f, float f2, boolean z) {
            if (viewParent instanceof NestedScrollingParent) {
                return ((NestedScrollingParent) viewParent).onNestedFling(view, f, f2, z);
            }
            return false;
        }

        public boolean m1324a(ViewParent viewParent, View view, float f, float f2) {
            if (viewParent instanceof NestedScrollingParent) {
                return ((NestedScrollingParent) viewParent).onNestedPreFling(view, f, f2);
            }
            return false;
        }
    }

    class ViewParentCompatICSImpl extends ViewParentCompatStubImpl {
        ViewParentCompatICSImpl() {
        }
    }

    class ViewParentCompatKitKatImpl extends ViewParentCompatICSImpl {
        ViewParentCompatKitKatImpl() {
        }
    }

    class ViewParentCompatLollipopImpl extends ViewParentCompatKitKatImpl {
        ViewParentCompatLollipopImpl() {
        }

        public boolean m1333a(ViewParent viewParent, View view, View view2, int i) {
            return ViewParentCompatLollipop.m1347a(viewParent, view, view2, i);
        }

        public void m1334b(ViewParent viewParent, View view, View view2, int i) {
            ViewParentCompatLollipop.m1348b(viewParent, view, view2, i);
        }

        public void m1328a(ViewParent viewParent, View view) {
            ViewParentCompatLollipop.m1342a(viewParent, view);
        }

        public void m1329a(ViewParent viewParent, View view, int i, int i2, int i3, int i4) {
            ViewParentCompatLollipop.m1343a(viewParent, view, i, i2, i3, i4);
        }

        public void m1330a(ViewParent viewParent, View view, int i, int i2, int[] iArr) {
            ViewParentCompatLollipop.m1344a(viewParent, view, i, i2, iArr);
        }

        public boolean m1332a(ViewParent viewParent, View view, float f, float f2, boolean z) {
            return ViewParentCompatLollipop.m1346a(viewParent, view, f, f2, z);
        }

        public boolean m1331a(ViewParent viewParent, View view, float f, float f2) {
            return ViewParentCompatLollipop.m1345a(viewParent, view, f, f2);
        }
    }

    static {
        int i = VERSION.SDK_INT;
        if (i >= 21) {
            f522a = new ViewParentCompatLollipopImpl();
        } else if (i >= 19) {
            f522a = new ViewParentCompatKitKatImpl();
        } else if (i >= 14) {
            f522a = new ViewParentCompatICSImpl();
        } else {
            f522a = new ViewParentCompatStubImpl();
        }
    }

    public static boolean m1340a(ViewParent viewParent, View view, View view2, int i) {
        return f522a.m1319a(viewParent, view, view2, i);
    }

    public static void m1341b(ViewParent viewParent, View view, View view2, int i) {
        f522a.m1320b(viewParent, view, view2, i);
    }

    public static void m1335a(ViewParent viewParent, View view) {
        f522a.m1314a(viewParent, view);
    }

    public static void m1336a(ViewParent viewParent, View view, int i, int i2, int i3, int i4) {
        f522a.m1315a(viewParent, view, i, i2, i3, i4);
    }

    public static void m1337a(ViewParent viewParent, View view, int i, int i2, int[] iArr) {
        f522a.m1316a(viewParent, view, i, i2, iArr);
    }

    public static boolean m1339a(ViewParent viewParent, View view, float f, float f2, boolean z) {
        return f522a.m1318a(viewParent, view, f, f2, z);
    }

    public static boolean m1338a(ViewParent viewParent, View view, float f, float f2) {
        return f522a.m1317a(viewParent, view, f, f2);
    }
}
