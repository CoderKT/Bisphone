package android.support.v4.view;

import android.view.View;
import android.view.ViewParent;

public class NestedScrollingChildHelper {
    private final View f439a;
    private ViewParent f440b;
    private boolean f441c;
    private int[] f442d;

    public NestedScrollingChildHelper(View view) {
        this.f439a = view;
    }

    public void m1001a(boolean z) {
        if (this.f441c) {
            ViewCompat.m1190s(this.f439a);
        }
        this.f441c = z;
    }

    public boolean m1002a() {
        return this.f441c;
    }

    public boolean m1008b() {
        return this.f440b != null;
    }

    public boolean m1005a(int i) {
        if (m1008b()) {
            return true;
        }
        if (m1002a()) {
            View view = this.f439a;
            for (ViewParent parent = this.f439a.getParent(); parent != null; parent = parent.getParent()) {
                if (ViewParentCompat.m1340a(parent, view, this.f439a, i)) {
                    this.f440b = parent;
                    ViewParentCompat.m1341b(parent, view, this.f439a, i);
                    return true;
                }
                if (parent instanceof View) {
                    view = (View) parent;
                }
            }
        }
        return false;
    }

    public void m1009c() {
        if (this.f440b != null) {
            ViewParentCompat.m1335a(this.f440b, this.f439a);
            this.f440b = null;
        }
    }

    public boolean m1006a(int i, int i2, int i3, int i4, int[] iArr) {
        if (!m1002a() || this.f440b == null) {
            return false;
        }
        if (i != 0 || i2 != 0 || i3 != 0 || i4 != 0) {
            int i5;
            int i6;
            if (iArr != null) {
                this.f439a.getLocationInWindow(iArr);
                int i7 = iArr[0];
                i5 = iArr[1];
                i6 = i7;
            } else {
                i5 = 0;
                i6 = 0;
            }
            ViewParentCompat.m1336a(this.f440b, this.f439a, i, i2, i3, i4);
            if (iArr != null) {
                this.f439a.getLocationInWindow(iArr);
                iArr[0] = iArr[0] - i6;
                iArr[1] = iArr[1] - i5;
            }
            return true;
        } else if (iArr == null) {
            return false;
        } else {
            iArr[0] = 0;
            iArr[1] = 0;
            return false;
        }
    }

    public boolean m1007a(int i, int i2, int[] iArr, int[] iArr2) {
        if (!m1002a() || this.f440b == null) {
            return false;
        }
        if (i != 0 || i2 != 0) {
            int i3;
            int i4;
            if (iArr2 != null) {
                this.f439a.getLocationInWindow(iArr2);
                i3 = iArr2[0];
                i4 = iArr2[1];
            } else {
                i4 = 0;
                i3 = 0;
            }
            if (iArr == null) {
                if (this.f442d == null) {
                    this.f442d = new int[2];
                }
                iArr = this.f442d;
            }
            iArr[0] = 0;
            iArr[1] = 0;
            ViewParentCompat.m1337a(this.f440b, this.f439a, i, i2, iArr);
            if (iArr2 != null) {
                this.f439a.getLocationInWindow(iArr2);
                iArr2[0] = iArr2[0] - i3;
                iArr2[1] = iArr2[1] - i4;
            }
            if (iArr[0] == 0 && iArr[1] == 0) {
                return false;
            }
            return true;
        } else if (iArr2 == null) {
            return false;
        } else {
            iArr2[0] = 0;
            iArr2[1] = 0;
            return false;
        }
    }

    public boolean m1004a(float f, float f2, boolean z) {
        if (!m1002a() || this.f440b == null) {
            return false;
        }
        return ViewParentCompat.m1339a(this.f440b, this.f439a, f, f2, z);
    }

    public boolean m1003a(float f, float f2) {
        if (!m1002a() || this.f440b == null) {
            return false;
        }
        return ViewParentCompat.m1338a(this.f440b, this.f439a, f, f2);
    }
}
