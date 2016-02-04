package app.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CustomViewPager extends ViewPager {
    private boolean f4652a;

    public CustomViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4652a = true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f4652a) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.f4652a) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    public void setPagingEnabled(boolean z) {
        this.f4652a = z;
    }
}
