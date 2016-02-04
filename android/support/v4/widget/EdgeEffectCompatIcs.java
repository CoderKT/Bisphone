package android.support.v4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.EdgeEffect;

class EdgeEffectCompatIcs {
    public static Object m1769a(Context context) {
        return new EdgeEffect(context);
    }

    public static void m1770a(Object obj, int i, int i2) {
        ((EdgeEffect) obj).setSize(i, i2);
    }

    public static boolean m1771a(Object obj) {
        return ((EdgeEffect) obj).isFinished();
    }

    public static void m1775b(Object obj) {
        ((EdgeEffect) obj).finish();
    }

    public static boolean m1772a(Object obj, float f) {
        ((EdgeEffect) obj).onPull(f);
        return true;
    }

    public static boolean m1776c(Object obj) {
        EdgeEffect edgeEffect = (EdgeEffect) obj;
        edgeEffect.onRelease();
        return edgeEffect.isFinished();
    }

    public static boolean m1773a(Object obj, int i) {
        ((EdgeEffect) obj).onAbsorb(i);
        return true;
    }

    public static boolean m1774a(Object obj, Canvas canvas) {
        return ((EdgeEffect) obj).draw(canvas);
    }
}
