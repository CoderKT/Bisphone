package android.support.v7.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckedTextView;
import android.widget.TextView;

public class AppCompatCheckedTextView extends CheckedTextView {
    private static final int[] f1338a;
    private TintManager f1339b;
    private AppCompatTextHelper f1340c;

    static {
        f1338a = new int[]{16843016};
    }

    public AppCompatCheckedTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16843720);
    }

    public AppCompatCheckedTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1340c = AppCompatTextHelper.m2846a((TextView) this);
        this.f1340c.m2851a(attributeSet, i);
        this.f1340c.m2848a();
        if (TintManager.f1820a) {
            TintTypedArray a = TintTypedArray.m3759a(getContext(), attributeSet, f1338a, i, 0);
            setCheckMarkDrawable(a.m3762a(0));
            a.m3763a();
            this.f1339b = a.m3767b();
        }
    }

    public void setCheckMarkDrawable(int i) {
        if (this.f1339b != null) {
            setCheckMarkDrawable(this.f1339b.m3753a(i));
        } else {
            super.setCheckMarkDrawable(i);
        }
    }

    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        if (this.f1340c != null) {
            this.f1340c.m2849a(context, i);
        }
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.f1340c != null) {
            this.f1340c.m2848a();
        }
    }
}
