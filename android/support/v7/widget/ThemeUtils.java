package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.graphics.ColorUtils;
import android.util.TypedValue;

class ThemeUtils {
    static final int[] f1804a;
    static final int[] f1805b;
    static final int[] f1806c;
    static final int[] f1807d;
    static final int[] f1808e;
    static final int[] f1809f;
    static final int[] f1810g;
    static final int[] f1811h;
    private static final ThreadLocal<TypedValue> f1812i;
    private static final int[] f1813j;

    static {
        f1812i = new ThreadLocal();
        f1804a = new int[]{-16842910};
        f1805b = new int[]{16842908};
        f1806c = new int[]{16843518};
        f1807d = new int[]{16842919};
        f1808e = new int[]{16842912};
        f1809f = new int[]{16842913};
        f1810g = new int[]{-16842919, -16842908};
        f1811h = new int[0];
        f1813j = new int[1];
    }

    public static int m3723a(Context context, int i) {
        f1813j[0] = i;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, f1813j);
        try {
            int color = obtainStyledAttributes.getColor(0, 0);
            return color;
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static ColorStateList m3726b(Context context, int i) {
        f1813j[0] = i;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, f1813j);
        try {
            ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(0);
            return colorStateList;
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static int m3727c(Context context, int i) {
        ColorStateList b = m3726b(context, i);
        if (b != null && b.isStateful()) {
            return b.getColorForState(f1804a, b.getDefaultColor());
        }
        TypedValue a = m3725a();
        context.getTheme().resolveAttribute(16842803, a, true);
        return m3724a(context, i, a.getFloat());
    }

    private static TypedValue m3725a() {
        TypedValue typedValue = (TypedValue) f1812i.get();
        if (typedValue != null) {
            return typedValue;
        }
        typedValue = new TypedValue();
        f1812i.set(typedValue);
        return typedValue;
    }

    static int m3724a(Context context, int i, float f) {
        int a = m3723a(context, i);
        return ColorUtils.m624b(a, Math.round(((float) Color.alpha(a)) * f));
    }
}
