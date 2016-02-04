package se.emilsjolander.stickylistheaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

public class WrapperView extends ViewGroup {
    View f8656a;
    Drawable f8657b;
    int f8658c;
    View f8659d;
    int f8660e;

    WrapperView(Context context) {
        super(context);
    }

    public boolean m13477a() {
        return this.f8659d != null;
    }

    public View getItem() {
        return this.f8656a;
    }

    public View getHeader() {
        return this.f8659d;
    }

    void m13476a(View view, View view2, Drawable drawable, int i) {
        if (view == null) {
            throw new NullPointerException("List view item must not be null.");
        }
        if (this.f8656a != view) {
            removeView(this.f8656a);
            this.f8656a = view;
            Object parent = view.getParent();
            if (!(parent == null || parent == this || !(parent instanceof ViewGroup))) {
                ((ViewGroup) parent).removeView(view);
            }
            addView(view);
        }
        if (this.f8659d != view2) {
            if (this.f8659d != null) {
                removeView(this.f8659d);
            }
            this.f8659d = view2;
            if (view2 != null) {
                addView(view2);
            }
        }
        if (this.f8657b != drawable) {
            this.f8657b = drawable;
            this.f8658c = i;
            invalidate();
        }
    }

    protected void onMeasure(int i, int i2) {
        int measuredHeight;
        int size = MeasureSpec.getSize(i);
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(size, 1073741824);
        if (this.f8659d != null) {
            LayoutParams layoutParams = this.f8659d.getLayoutParams();
            if (layoutParams == null || layoutParams.height <= 0) {
                this.f8659d.measure(makeMeasureSpec, MeasureSpec.makeMeasureSpec(0, 0));
            } else {
                this.f8659d.measure(makeMeasureSpec, MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824));
            }
            measuredHeight = this.f8659d.getMeasuredHeight() + 0;
        } else if (this.f8657b == null || this.f8656a.getVisibility() == 8) {
            measuredHeight = 0;
        } else {
            measuredHeight = this.f8658c + 0;
        }
        LayoutParams layoutParams2 = this.f8656a.getLayoutParams();
        if (this.f8656a.getVisibility() == 8) {
            this.f8656a.measure(makeMeasureSpec, MeasureSpec.makeMeasureSpec(0, 1073741824));
        } else if (layoutParams2 == null || layoutParams2.height < 0) {
            this.f8656a.measure(makeMeasureSpec, MeasureSpec.makeMeasureSpec(0, 0));
            measuredHeight += this.f8656a.getMeasuredHeight();
        } else {
            this.f8656a.measure(makeMeasureSpec, MeasureSpec.makeMeasureSpec(layoutParams2.height, 1073741824));
            measuredHeight += this.f8656a.getMeasuredHeight();
        }
        setMeasuredDimension(size, measuredHeight);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int width = getWidth();
        int height = getHeight();
        if (this.f8659d != null) {
            int measuredHeight = this.f8659d.getMeasuredHeight();
            this.f8659d.layout(0, 0, width, measuredHeight);
            this.f8660e = measuredHeight;
            this.f8656a.layout(0, measuredHeight, width, height);
        } else if (this.f8657b != null) {
            this.f8657b.setBounds(0, 0, width, this.f8658c);
            this.f8660e = this.f8658c;
            this.f8656a.layout(0, this.f8658c, width, height);
        } else {
            this.f8660e = 0;
            this.f8656a.layout(0, 0, width, height);
        }
    }

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.f8659d == null && this.f8657b != null && this.f8656a.getVisibility() != 8) {
            if (VERSION.SDK_INT < 11) {
                canvas.clipRect(0, 0, getWidth(), this.f8658c);
            }
            this.f8657b.draw(canvas);
        }
    }
}
