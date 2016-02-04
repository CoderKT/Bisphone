package android.support.v4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build.VERSION;

public class EdgeEffectCompat {
    private static final EdgeEffectImpl f601b;
    private Object f602a;

    interface EdgeEffectImpl {
        Object m1733a(Context context);

        void m1734a(Object obj, int i, int i2);

        boolean m1735a(Object obj);

        boolean m1736a(Object obj, float f);

        boolean m1737a(Object obj, float f, float f2);

        boolean m1738a(Object obj, int i);

        boolean m1739a(Object obj, Canvas canvas);

        void m1740b(Object obj);

        boolean m1741c(Object obj);
    }

    class BaseEdgeEffectImpl implements EdgeEffectImpl {
        BaseEdgeEffectImpl() {
        }

        public Object m1742a(Context context) {
            return null;
        }

        public void m1743a(Object obj, int i, int i2) {
        }

        public boolean m1744a(Object obj) {
            return true;
        }

        public void m1749b(Object obj) {
        }

        public boolean m1745a(Object obj, float f) {
            return false;
        }

        public boolean m1750c(Object obj) {
            return false;
        }

        public boolean m1747a(Object obj, int i) {
            return false;
        }

        public boolean m1748a(Object obj, Canvas canvas) {
            return false;
        }

        public boolean m1746a(Object obj, float f, float f2) {
            return false;
        }
    }

    class EdgeEffectIcsImpl implements EdgeEffectImpl {
        EdgeEffectIcsImpl() {
        }

        public Object m1751a(Context context) {
            return EdgeEffectCompatIcs.m1769a(context);
        }

        public void m1752a(Object obj, int i, int i2) {
            EdgeEffectCompatIcs.m1770a(obj, i, i2);
        }

        public boolean m1753a(Object obj) {
            return EdgeEffectCompatIcs.m1771a(obj);
        }

        public void m1758b(Object obj) {
            EdgeEffectCompatIcs.m1775b(obj);
        }

        public boolean m1754a(Object obj, float f) {
            return EdgeEffectCompatIcs.m1772a(obj, f);
        }

        public boolean m1759c(Object obj) {
            return EdgeEffectCompatIcs.m1776c(obj);
        }

        public boolean m1756a(Object obj, int i) {
            return EdgeEffectCompatIcs.m1773a(obj, i);
        }

        public boolean m1757a(Object obj, Canvas canvas) {
            return EdgeEffectCompatIcs.m1774a(obj, canvas);
        }

        public boolean m1755a(Object obj, float f, float f2) {
            return EdgeEffectCompatIcs.m1772a(obj, f);
        }
    }

    class EdgeEffectLollipopImpl extends EdgeEffectIcsImpl {
        EdgeEffectLollipopImpl() {
        }

        public boolean m1760a(Object obj, float f, float f2) {
            return EdgeEffectCompatLollipop.m1777a(obj, f, f2);
        }
    }

    static {
        if (VERSION.SDK_INT >= 21) {
            f601b = new EdgeEffectLollipopImpl();
        } else if (VERSION.SDK_INT >= 14) {
            f601b = new EdgeEffectIcsImpl();
        } else {
            f601b = new BaseEdgeEffectImpl();
        }
    }

    public EdgeEffectCompat(Context context) {
        this.f602a = f601b.m1733a(context);
    }

    public void m1761a(int i, int i2) {
        f601b.m1734a(this.f602a, i, i2);
    }

    public boolean m1762a() {
        return f601b.m1735a(this.f602a);
    }

    public void m1767b() {
        f601b.m1740b(this.f602a);
    }

    public boolean m1763a(float f) {
        return f601b.m1736a(this.f602a, f);
    }

    public boolean m1764a(float f, float f2) {
        return f601b.m1737a(this.f602a, f, f2);
    }

    public boolean m1768c() {
        return f601b.m1741c(this.f602a);
    }

    public boolean m1765a(int i) {
        return f601b.m1738a(this.f602a, i);
    }

    public boolean m1766a(Canvas canvas) {
        return f601b.m1739a(this.f602a, canvas);
    }
}
