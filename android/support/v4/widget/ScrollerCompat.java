package android.support.v4.widget;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class ScrollerCompat {
    Object f639a;
    ScrollerCompatImpl f640b;

    interface ScrollerCompatImpl {
        Object m1837a(Context context, Interpolator interpolator);

        void m1838a(Object obj, int i, int i2, int i3, int i4);

        void m1839a(Object obj, int i, int i2, int i3, int i4, int i5);

        void m1840a(Object obj, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8);

        void m1841a(Object obj, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10);

        boolean m1842a(Object obj);

        boolean m1843a(Object obj, int i, int i2, int i3, int i4, int i5, int i6);

        int m1844b(Object obj);

        int m1845c(Object obj);

        float m1846d(Object obj);

        boolean m1847e(Object obj);

        void m1848f(Object obj);

        int m1849g(Object obj);

        int m1850h(Object obj);
    }

    class ScrollerCompatImplBase implements ScrollerCompatImpl {
        ScrollerCompatImplBase() {
        }

        public Object m1851a(Context context, Interpolator interpolator) {
            return interpolator != null ? new Scroller(context, interpolator) : new Scroller(context);
        }

        public boolean m1856a(Object obj) {
            return ((Scroller) obj).isFinished();
        }

        public int m1858b(Object obj) {
            return ((Scroller) obj).getCurrX();
        }

        public int m1859c(Object obj) {
            return ((Scroller) obj).getCurrY();
        }

        public float m1860d(Object obj) {
            return 0.0f;
        }

        public boolean m1861e(Object obj) {
            return ((Scroller) obj).computeScrollOffset();
        }

        public void m1852a(Object obj, int i, int i2, int i3, int i4) {
            ((Scroller) obj).startScroll(i, i2, i3, i4);
        }

        public void m1853a(Object obj, int i, int i2, int i3, int i4, int i5) {
            ((Scroller) obj).startScroll(i, i2, i3, i4, i5);
        }

        public void m1854a(Object obj, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            ((Scroller) obj).fling(i, i2, i3, i4, i5, i6, i7, i8);
        }

        public void m1855a(Object obj, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
            ((Scroller) obj).fling(i, i2, i3, i4, i5, i6, i7, i8);
        }

        public void m1862f(Object obj) {
            ((Scroller) obj).abortAnimation();
        }

        public int m1863g(Object obj) {
            return ((Scroller) obj).getFinalX();
        }

        public int m1864h(Object obj) {
            return ((Scroller) obj).getFinalY();
        }

        public boolean m1857a(Object obj, int i, int i2, int i3, int i4, int i5, int i6) {
            return false;
        }
    }

    class ScrollerCompatImplGingerbread implements ScrollerCompatImpl {
        ScrollerCompatImplGingerbread() {
        }

        public Object m1865a(Context context, Interpolator interpolator) {
            return ScrollerCompatGingerbread.m1895a(context, interpolator);
        }

        public boolean m1870a(Object obj) {
            return ScrollerCompatGingerbread.m1900a(obj);
        }

        public int m1872b(Object obj) {
            return ScrollerCompatGingerbread.m1902b(obj);
        }

        public int m1873c(Object obj) {
            return ScrollerCompatGingerbread.m1903c(obj);
        }

        public float m1874d(Object obj) {
            return 0.0f;
        }

        public boolean m1875e(Object obj) {
            return ScrollerCompatGingerbread.m1904d(obj);
        }

        public void m1866a(Object obj, int i, int i2, int i3, int i4) {
            ScrollerCompatGingerbread.m1896a(obj, i, i2, i3, i4);
        }

        public void m1867a(Object obj, int i, int i2, int i3, int i4, int i5) {
            ScrollerCompatGingerbread.m1897a(obj, i, i2, i3, i4, i5);
        }

        public void m1868a(Object obj, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            ScrollerCompatGingerbread.m1898a(obj, i, i2, i3, i4, i5, i6, i7, i8);
        }

        public void m1869a(Object obj, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
            ScrollerCompatGingerbread.m1899a(obj, i, i2, i3, i4, i5, i6, i7, i8, i9, i10);
        }

        public void m1876f(Object obj) {
            ScrollerCompatGingerbread.m1905e(obj);
        }

        public int m1877g(Object obj) {
            return ScrollerCompatGingerbread.m1906f(obj);
        }

        public int m1878h(Object obj) {
            return ScrollerCompatGingerbread.m1907g(obj);
        }

        public boolean m1871a(Object obj, int i, int i2, int i3, int i4, int i5, int i6) {
            return ScrollerCompatGingerbread.m1901a(obj, i, i2, i3, i4, i5, i6);
        }
    }

    class ScrollerCompatImplIcs extends ScrollerCompatImplGingerbread {
        ScrollerCompatImplIcs() {
        }

        public float m1879d(Object obj) {
            return ScrollerCompatIcs.m1908a(obj);
        }
    }

    public static ScrollerCompat m1880a(Context context) {
        return m1881a(context, null);
    }

    public static ScrollerCompat m1881a(Context context, Interpolator interpolator) {
        return new ScrollerCompat(context, interpolator);
    }

    ScrollerCompat(Context context, Interpolator interpolator) {
        this(VERSION.SDK_INT, context, interpolator);
    }

    private ScrollerCompat(int i, Context context, Interpolator interpolator) {
        if (i >= 14) {
            this.f640b = new ScrollerCompatImplIcs();
        } else if (i >= 9) {
            this.f640b = new ScrollerCompatImplGingerbread();
        } else {
            this.f640b = new ScrollerCompatImplBase();
        }
        this.f639a = this.f640b.m1837a(context, interpolator);
    }

    public boolean m1886a() {
        return this.f640b.m1842a(this.f639a);
    }

    public int m1888b() {
        return this.f640b.m1844b(this.f639a);
    }

    public int m1889c() {
        return this.f640b.m1845c(this.f639a);
    }

    public int m1890d() {
        return this.f640b.m1849g(this.f639a);
    }

    public int m1891e() {
        return this.f640b.m1850h(this.f639a);
    }

    public float m1892f() {
        return this.f640b.m1846d(this.f639a);
    }

    public boolean m1893g() {
        return this.f640b.m1847e(this.f639a);
    }

    public void m1882a(int i, int i2, int i3, int i4) {
        this.f640b.m1838a(this.f639a, i, i2, i3, i4);
    }

    public void m1883a(int i, int i2, int i3, int i4, int i5) {
        this.f640b.m1839a(this.f639a, i, i2, i3, i4, i5);
    }

    public void m1884a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.f640b.m1840a(this.f639a, i, i2, i3, i4, i5, i6, i7, i8);
    }

    public void m1885a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        this.f640b.m1841a(this.f639a, i, i2, i3, i4, i5, i6, i7, i8, i9, i10);
    }

    public boolean m1887a(int i, int i2, int i3, int i4, int i5, int i6) {
        return this.f640b.m1843a(this.f639a, i, i2, i3, i4, i5, i6);
    }

    public void m1894h() {
        this.f640b.m1848f(this.f639a);
    }
}
