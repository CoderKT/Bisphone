package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

public class TintTypedArray {
    private final Context f1833a;
    private final TypedArray f1834b;
    private TintManager f1835c;

    public static TintTypedArray m3758a(Context context, AttributeSet attributeSet, int[] iArr) {
        return new TintTypedArray(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public static TintTypedArray m3759a(Context context, AttributeSet attributeSet, int[] iArr, int i, int i2) {
        return new TintTypedArray(context, context.obtainStyledAttributes(attributeSet, iArr, i, i2));
    }

    private TintTypedArray(Context context, TypedArray typedArray) {
        this.f1833a = context;
        this.f1834b = typedArray;
    }

    public Drawable m3762a(int i) {
        if (this.f1834b.hasValue(i)) {
            int resourceId = this.f1834b.getResourceId(i, 0);
            if (resourceId != 0) {
                return m3767b().m3753a(resourceId);
            }
        }
        return this.f1834b.getDrawable(i);
    }

    public Drawable m3766b(int i) {
        if (this.f1834b.hasValue(i)) {
            int resourceId = this.f1834b.getResourceId(i, 0);
            if (resourceId != 0) {
                return m3767b().m3754a(resourceId, true);
            }
        }
        return null;
    }

    public CharSequence m3769c(int i) {
        return this.f1834b.getText(i);
    }

    public String m3771d(int i) {
        return this.f1834b.getString(i);
    }

    public boolean m3764a(int i, boolean z) {
        return this.f1834b.getBoolean(i, z);
    }

    public int m3761a(int i, int i2) {
        return this.f1834b.getInt(i, i2);
    }

    public float m3760a(int i, float f) {
        return this.f1834b.getFloat(i, f);
    }

    public int m3765b(int i, int i2) {
        return this.f1834b.getColor(i, i2);
    }

    public int m3768c(int i, int i2) {
        return this.f1834b.getInteger(i, i2);
    }

    public int m3770d(int i, int i2) {
        return this.f1834b.getDimensionPixelOffset(i, i2);
    }

    public int m3772e(int i, int i2) {
        return this.f1834b.getDimensionPixelSize(i, i2);
    }

    public int m3774f(int i, int i2) {
        return this.f1834b.getLayoutDimension(i, i2);
    }

    public int m3775g(int i, int i2) {
        return this.f1834b.getResourceId(i, i2);
    }

    public boolean m3773e(int i) {
        return this.f1834b.hasValue(i);
    }

    public void m3763a() {
        this.f1834b.recycle();
    }

    public TintManager m3767b() {
        if (this.f1835c == null) {
            this.f1835c = TintManager.m3737a(this.f1833a);
        }
        return this.f1835c;
    }
}
