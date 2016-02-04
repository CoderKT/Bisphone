package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

class AppCompatTextHelperV17 extends AppCompatTextHelper {
    private static final int[] f1441b;
    private TintInfo f1442c;
    private TintInfo f1443d;

    static {
        f1441b = new int[]{16843666, 16843667};
    }

    AppCompatTextHelperV17(TextView textView) {
        super(textView);
    }

    void m2854a(AttributeSet attributeSet, int i) {
        super.m2851a(attributeSet, i);
        Context context = this.a.getContext();
        TintManager a = TintManager.m3737a(context);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f1441b, i, 0);
        if (obtainStyledAttributes.hasValue(0)) {
            this.f1442c = AppCompatTextHelper.m2847a(context, a, obtainStyledAttributes.getResourceId(0, 0));
        }
        if (obtainStyledAttributes.hasValue(1)) {
            this.f1443d = AppCompatTextHelper.m2847a(context, a, obtainStyledAttributes.getResourceId(1, 0));
        }
        obtainStyledAttributes.recycle();
    }

    void m2853a() {
        super.m2848a();
        if (this.f1442c != null || this.f1443d != null) {
            Drawable[] compoundDrawablesRelative = this.a.getCompoundDrawablesRelative();
            m2850a(compoundDrawablesRelative[0], this.f1442c);
            m2850a(compoundDrawablesRelative[2], this.f1443d);
        }
    }
}
