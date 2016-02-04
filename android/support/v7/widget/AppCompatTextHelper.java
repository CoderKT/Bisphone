package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v7.appcompat.C0057R;
import android.support.v7.text.AllCapsTransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;

class AppCompatTextHelper {
    private static final int[] f1434b;
    private static final int[] f1435c;
    final TextView f1436a;
    private TintInfo f1437d;
    private TintInfo f1438e;
    private TintInfo f1439f;
    private TintInfo f1440g;

    static AppCompatTextHelper m2846a(TextView textView) {
        if (VERSION.SDK_INT >= 17) {
            return new AppCompatTextHelperV17(textView);
        }
        return new AppCompatTextHelper(textView);
    }

    static {
        f1434b = new int[]{16842804, 16843119, 16843117, 16843120, 16843118};
        f1435c = new int[]{C0057R.attr.textAllCaps};
    }

    AppCompatTextHelper(TextView textView) {
        this.f1436a = textView;
    }

    void m2851a(AttributeSet attributeSet, int i) {
        Context context = this.f1436a.getContext();
        TintManager a = TintManager.m3737a(context);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f1434b, i, 0);
        int resourceId = obtainStyledAttributes.getResourceId(0, -1);
        if (obtainStyledAttributes.hasValue(1)) {
            this.f1437d = m2847a(context, a, obtainStyledAttributes.getResourceId(1, 0));
        }
        if (obtainStyledAttributes.hasValue(2)) {
            this.f1438e = m2847a(context, a, obtainStyledAttributes.getResourceId(2, 0));
        }
        if (obtainStyledAttributes.hasValue(3)) {
            this.f1439f = m2847a(context, a, obtainStyledAttributes.getResourceId(3, 0));
        }
        if (obtainStyledAttributes.hasValue(4)) {
            this.f1440g = m2847a(context, a, obtainStyledAttributes.getResourceId(4, 0));
        }
        obtainStyledAttributes.recycle();
        if (resourceId != -1) {
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, C0057R.styleable.TextAppearance);
            if (obtainStyledAttributes2.hasValue(C0057R.styleable.TextAppearance_textAllCaps)) {
                m2852a(obtainStyledAttributes2.getBoolean(C0057R.styleable.TextAppearance_textAllCaps, false));
            }
            obtainStyledAttributes2.recycle();
        }
        TypedArray obtainStyledAttributes3 = context.obtainStyledAttributes(attributeSet, f1435c, i, 0);
        if (obtainStyledAttributes3.getBoolean(0, false)) {
            m2852a(true);
        }
        obtainStyledAttributes3.recycle();
    }

    void m2849a(Context context, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, f1435c);
        if (obtainStyledAttributes.hasValue(0)) {
            m2852a(obtainStyledAttributes.getBoolean(0, false));
        }
        obtainStyledAttributes.recycle();
    }

    void m2852a(boolean z) {
        this.f1436a.setTransformationMethod(z ? new AllCapsTransformationMethod(this.f1436a.getContext()) : null);
    }

    void m2848a() {
        if (this.f1437d != null || this.f1438e != null || this.f1439f != null || this.f1440g != null) {
            Drawable[] compoundDrawables = this.f1436a.getCompoundDrawables();
            m2850a(compoundDrawables[0], this.f1437d);
            m2850a(compoundDrawables[1], this.f1438e);
            m2850a(compoundDrawables[2], this.f1439f);
            m2850a(compoundDrawables[3], this.f1440g);
        }
    }

    final void m2850a(Drawable drawable, TintInfo tintInfo) {
        if (drawable != null && tintInfo != null) {
            TintManager.m3739a(drawable, tintInfo, this.f1436a.getDrawableState());
        }
    }

    protected static TintInfo m2847a(Context context, TintManager tintManager, int i) {
        ColorStateList c = tintManager.m3757c(i);
        if (c != null) {
            TintInfo tintInfo = new TintInfo();
            tintInfo.f1819d = true;
            tintInfo.f1816a = c;
        }
        return null;
    }
}
