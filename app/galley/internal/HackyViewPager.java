package app.galley.internal;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import com.ortiz.touch.TouchImageView;

public class HackyViewPager extends ViewPager {
    public HackyViewPager(Context context) {
        super(context);
    }

    public HackyViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected boolean m5137a(View view, boolean z, int i, int i2, int i3) {
        if (view instanceof TouchImageView) {
            return ((TouchImageView) view).m11340a(-i);
        }
        return super.m1306a(view, z, i, i2, i3);
    }
}
