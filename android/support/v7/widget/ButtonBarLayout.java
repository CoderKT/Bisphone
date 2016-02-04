package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.appcompat.C0057R;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;

public class ButtonBarLayout extends LinearLayout {
    private boolean f1444a;
    private int f1445b;

    public ButtonBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1445b = -1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0057R.styleable.ButtonBarLayout);
        this.f1444a = obtainStyledAttributes.getBoolean(C0057R.styleable.ButtonBarLayout_allowStacking, false);
        obtainStyledAttributes.recycle();
    }

    public void setAllowStacking(boolean z) {
        if (this.f1444a != z) {
            this.f1444a = z;
            if (!this.f1444a && getOrientation() == 1) {
                setStacked(false);
            }
            requestLayout();
        }
    }

    protected void onMeasure(int i, int i2) {
        boolean z;
        int i3;
        int size = MeasureSpec.getSize(i);
        if (this.f1444a) {
            if (size > this.f1445b && m2855a()) {
                setStacked(false);
            }
            this.f1445b = size;
        }
        if (m2855a() || MeasureSpec.getMode(i) != 1073741824) {
            z = false;
            i3 = i;
        } else {
            i3 = MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE);
            z = true;
        }
        super.onMeasure(i3, i2);
        if (this.f1444a && !m2855a() && (getMeasuredWidthAndState() & -16777216) == 16777216) {
            setStacked(true);
            z = true;
        }
        if (z) {
            super.onMeasure(i, i2);
        }
    }

    private void setStacked(boolean z) {
        setOrientation(z ? 1 : 0);
        setGravity(z ? 5 : 80);
        View findViewById = findViewById(C0057R.id.spacer);
        if (findViewById != null) {
            findViewById.setVisibility(z ? 8 : 4);
        }
        for (int childCount = getChildCount() - 2; childCount >= 0; childCount--) {
            bringChildToFront(getChildAt(childCount));
        }
    }

    private boolean m2855a() {
        return getOrientation() == 1;
    }
}
