package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v7.appcompat.C0057R;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

public class ActionBarContainer extends FrameLayout {
    Drawable f1141a;
    Drawable f1142b;
    Drawable f1143c;
    boolean f1144d;
    boolean f1145e;
    private boolean f1146f;
    private View f1147g;
    private View f1148h;
    private View f1149i;
    private int f1150j;

    public ActionBarContainer(Context context) {
        this(context, null);
    }

    public ActionBarContainer(Context context, AttributeSet attributeSet) {
        Drawable actionBarBackgroundDrawableV21;
        super(context, attributeSet);
        if (VERSION.SDK_INT >= 21) {
            actionBarBackgroundDrawableV21 = new ActionBarBackgroundDrawableV21(this);
        } else {
            actionBarBackgroundDrawableV21 = new ActionBarBackgroundDrawable(this);
        }
        setBackgroundDrawable(actionBarBackgroundDrawableV21);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0057R.styleable.ActionBar);
        this.f1141a = obtainStyledAttributes.getDrawable(C0057R.styleable.ActionBar_background);
        this.f1142b = obtainStyledAttributes.getDrawable(C0057R.styleable.ActionBar_backgroundStacked);
        this.f1150j = obtainStyledAttributes.getDimensionPixelSize(C0057R.styleable.ActionBar_height, -1);
        if (getId() == C0057R.id.split_action_bar) {
            this.f1144d = true;
            this.f1143c = obtainStyledAttributes.getDrawable(C0057R.styleable.ActionBar_backgroundSplit);
        }
        obtainStyledAttributes.recycle();
        boolean z = this.f1144d ? this.f1143c == null : this.f1141a == null && this.f1142b == null;
        setWillNotDraw(z);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.f1148h = findViewById(C0057R.id.action_bar);
        this.f1149i = findViewById(C0057R.id.action_context_bar);
    }

    public void setPrimaryBackground(Drawable drawable) {
        boolean z = true;
        if (this.f1141a != null) {
            this.f1141a.setCallback(null);
            unscheduleDrawable(this.f1141a);
        }
        this.f1141a = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.f1148h != null) {
                this.f1141a.setBounds(this.f1148h.getLeft(), this.f1148h.getTop(), this.f1148h.getRight(), this.f1148h.getBottom());
            }
        }
        if (this.f1144d) {
            if (this.f1143c != null) {
                z = false;
            }
        } else if (!(this.f1141a == null && this.f1142b == null)) {
            z = false;
        }
        setWillNotDraw(z);
        invalidate();
    }

    public void setStackedBackground(Drawable drawable) {
        boolean z = true;
        if (this.f1142b != null) {
            this.f1142b.setCallback(null);
            unscheduleDrawable(this.f1142b);
        }
        this.f1142b = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.f1145e && this.f1142b != null) {
                this.f1142b.setBounds(this.f1147g.getLeft(), this.f1147g.getTop(), this.f1147g.getRight(), this.f1147g.getBottom());
            }
        }
        if (this.f1144d) {
            if (this.f1143c != null) {
                z = false;
            }
        } else if (!(this.f1141a == null && this.f1142b == null)) {
            z = false;
        }
        setWillNotDraw(z);
        invalidate();
    }

    public void setSplitBackground(Drawable drawable) {
        boolean z = true;
        if (this.f1143c != null) {
            this.f1143c.setCallback(null);
            unscheduleDrawable(this.f1143c);
        }
        this.f1143c = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.f1144d && this.f1143c != null) {
                this.f1143c.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            }
        }
        if (this.f1144d) {
            if (this.f1143c != null) {
                z = false;
            }
        } else if (!(this.f1141a == null && this.f1142b == null)) {
            z = false;
        }
        setWillNotDraw(z);
        invalidate();
    }

    public void setVisibility(int i) {
        boolean z;
        super.setVisibility(i);
        if (i == 0) {
            z = true;
        } else {
            z = false;
        }
        if (this.f1141a != null) {
            this.f1141a.setVisible(z, false);
        }
        if (this.f1142b != null) {
            this.f1142b.setVisible(z, false);
        }
        if (this.f1143c != null) {
            this.f1143c.setVisible(z, false);
        }
    }

    protected boolean verifyDrawable(Drawable drawable) {
        return (drawable == this.f1141a && !this.f1144d) || ((drawable == this.f1142b && this.f1145e) || ((drawable == this.f1143c && this.f1144d) || super.verifyDrawable(drawable)));
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.f1141a != null && this.f1141a.isStateful()) {
            this.f1141a.setState(getDrawableState());
        }
        if (this.f1142b != null && this.f1142b.isStateful()) {
            this.f1142b.setState(getDrawableState());
        }
        if (this.f1143c != null && this.f1143c.isStateful()) {
            this.f1143c.setState(getDrawableState());
        }
    }

    public void jumpDrawablesToCurrentState() {
        if (VERSION.SDK_INT >= 11) {
            super.jumpDrawablesToCurrentState();
            if (this.f1141a != null) {
                this.f1141a.jumpToCurrentState();
            }
            if (this.f1142b != null) {
                this.f1142b.jumpToCurrentState();
            }
            if (this.f1143c != null) {
                this.f1143c.jumpToCurrentState();
            }
        }
    }

    public void setTransitioning(boolean z) {
        this.f1146f = z;
        setDescendantFocusability(z ? 393216 : 262144);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f1146f || super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    public void setTabContainer(ScrollingTabContainerView scrollingTabContainerView) {
        if (this.f1147g != null) {
            removeView(this.f1147g);
        }
        this.f1147g = scrollingTabContainerView;
        if (scrollingTabContainerView != null) {
            addView(scrollingTabContainerView);
            LayoutParams layoutParams = scrollingTabContainerView.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            scrollingTabContainerView.setAllowCollapse(false);
        }
    }

    public View getTabContainer() {
        return this.f1147g;
    }

    public ActionMode startActionModeForChild(View view, Callback callback) {
        return null;
    }

    private boolean m2550a(View view) {
        return view == null || view.getVisibility() == 8 || view.getMeasuredHeight() == 0;
    }

    private int m2551b(View view) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        return layoutParams.bottomMargin + (view.getMeasuredHeight() + layoutParams.topMargin);
    }

    public void onMeasure(int i, int i2) {
        if (this.f1148h == null && MeasureSpec.getMode(i2) == Integer.MIN_VALUE && this.f1150j >= 0) {
            i2 = MeasureSpec.makeMeasureSpec(Math.min(this.f1150j, MeasureSpec.getSize(i2)), Integer.MIN_VALUE);
        }
        super.onMeasure(i, i2);
        if (this.f1148h != null) {
            int mode = MeasureSpec.getMode(i2);
            if (this.f1147g != null && this.f1147g.getVisibility() != 8 && mode != 1073741824) {
                int b;
                if (!m2550a(this.f1148h)) {
                    b = m2551b(this.f1148h);
                } else if (m2550a(this.f1149i)) {
                    b = 0;
                } else {
                    b = m2551b(this.f1149i);
                }
                setMeasuredDimension(getMeasuredWidth(), Math.min(b + m2551b(this.f1147g), mode == Integer.MIN_VALUE ? MeasureSpec.getSize(i2) : Integer.MAX_VALUE));
            }
        }
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = 1;
        super.onLayout(z, i, i2, i3, i4);
        View view = this.f1147g;
        boolean z2 = (view == null || view.getVisibility() == 8) ? false : true;
        if (!(view == null || view.getVisibility() == 8)) {
            int measuredHeight = getMeasuredHeight();
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
            view.layout(i, (measuredHeight - view.getMeasuredHeight()) - layoutParams.bottomMargin, i3, measuredHeight - layoutParams.bottomMargin);
        }
        if (!this.f1144d) {
            int i6;
            if (this.f1141a != null) {
                if (this.f1148h.getVisibility() == 0) {
                    this.f1141a.setBounds(this.f1148h.getLeft(), this.f1148h.getTop(), this.f1148h.getRight(), this.f1148h.getBottom());
                } else if (this.f1149i == null || this.f1149i.getVisibility() != 0) {
                    this.f1141a.setBounds(0, 0, 0, 0);
                } else {
                    this.f1141a.setBounds(this.f1149i.getLeft(), this.f1149i.getTop(), this.f1149i.getRight(), this.f1149i.getBottom());
                }
                i6 = 1;
            } else {
                i6 = 0;
            }
            this.f1145e = z2;
            if (!z2 || this.f1142b == null) {
                i5 = i6;
            } else {
                this.f1142b.setBounds(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            }
        } else if (this.f1143c != null) {
            this.f1143c.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        } else {
            i5 = 0;
        }
        if (i5 != 0) {
            invalidate();
        }
    }
}
