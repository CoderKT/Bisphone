package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.appcompat.C0057R;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;

abstract class AbsActionBarView extends ViewGroup {
    protected final VisibilityAnimListener f1132a;
    protected final Context f1133b;
    protected ActionMenuView f1134c;
    protected ActionMenuPresenter f1135d;
    protected int f1136e;
    protected ViewPropertyAnimatorCompat f1137f;
    private boolean f1138g;
    private boolean f1139h;

    public class VisibilityAnimListener implements ViewPropertyAnimatorListener {
        int f1129a;
        final /* synthetic */ AbsActionBarView f1130b;
        private boolean f1131c;

        protected VisibilityAnimListener(AbsActionBarView absActionBarView) {
            this.f1130b = absActionBarView;
            this.f1131c = false;
        }

        public VisibilityAnimListener m2539a(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, int i) {
            this.f1130b.f1137f = viewPropertyAnimatorCompat;
            this.f1129a = i;
            return this;
        }

        public void m2540a(View view) {
            super.setVisibility(0);
            this.f1131c = false;
        }

        public void m2541b(View view) {
            if (!this.f1131c) {
                this.f1130b.f1137f = null;
                super.setVisibility(this.f1129a);
            }
        }

        public void m2542c(View view) {
            this.f1131c = true;
        }
    }

    AbsActionBarView(Context context) {
        this(context, null);
    }

    AbsActionBarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    AbsActionBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1132a = new VisibilityAnimListener(this);
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(C0057R.attr.actionBarPopupTheme, typedValue, true) || typedValue.resourceId == 0) {
            this.f1133b = context;
        } else {
            this.f1133b = new ContextThemeWrapper(context, typedValue.resourceId);
        }
    }

    protected void onConfigurationChanged(Configuration configuration) {
        if (VERSION.SDK_INT >= 8) {
            super.onConfigurationChanged(configuration);
        }
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(null, C0057R.styleable.ActionBar, C0057R.attr.actionBarStyle, 0);
        setContentHeight(obtainStyledAttributes.getLayoutDimension(C0057R.styleable.ActionBar_height, 0));
        obtainStyledAttributes.recycle();
        if (this.f1135d != null) {
            this.f1135d.m2621a(configuration);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int a = MotionEventCompat.m987a(motionEvent);
        if (a == 0) {
            this.f1138g = false;
        }
        if (!this.f1138g) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (a == 0 && !onTouchEvent) {
                this.f1138g = true;
            }
        }
        if (a == 1 || a == 3) {
            this.f1138g = false;
        }
        return true;
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        int a = MotionEventCompat.m987a(motionEvent);
        if (a == 9) {
            this.f1139h = false;
        }
        if (!this.f1139h) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (a == 9 && !onHoverEvent) {
                this.f1139h = true;
            }
        }
        if (a == 10 || a == 3) {
            this.f1139h = false;
        }
        return true;
    }

    public void setContentHeight(int i) {
        this.f1136e = i;
        requestLayout();
    }

    public int getContentHeight() {
        return this.f1136e;
    }

    public int getAnimatedVisibility() {
        if (this.f1137f != null) {
            return this.f1132a.f1129a;
        }
        return getVisibility();
    }

    public ViewPropertyAnimatorCompat m2548a(int i, long j) {
        if (this.f1137f != null) {
            this.f1137f.m1405b();
        }
        if (i == 0) {
            if (getVisibility() != 0) {
                ViewCompat.m1171c((View) this, 0.0f);
            }
            ViewPropertyAnimatorCompat a = ViewCompat.m1185n(this).m1398a(1.0f);
            a.m1399a(j);
            a.m1400a(this.f1132a.m2539a(a, i));
            return a;
        }
        a = ViewCompat.m1185n(this).m1398a(0.0f);
        a.m1399a(j);
        a.m1400a(this.f1132a.m2539a(a, i));
        return a;
    }

    public void setVisibility(int i) {
        if (i != getVisibility()) {
            if (this.f1137f != null) {
                this.f1137f.m1405b();
            }
            super.setVisibility(i);
        }
    }

    public boolean m2549a() {
        if (this.f1135d != null) {
            return this.f1135d.m2635d();
        }
        return false;
    }

    protected int m2546a(View view, int i, int i2, int i3) {
        view.measure(MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE), i2);
        return Math.max(0, (i - view.getMeasuredWidth()) - i3);
    }

    protected static int m2543a(int i, int i2, boolean z) {
        return z ? i - i2 : i + i2;
    }

    protected int m2547a(View view, int i, int i2, int i3, boolean z) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i4 = ((i3 - measuredHeight) / 2) + i2;
        if (z) {
            view.layout(i - measuredWidth, i4, i, measuredHeight + i4);
        } else {
            view.layout(i, i4, i + measuredWidth, measuredHeight + i4);
        }
        return z ? -measuredWidth : measuredWidth;
    }
}
