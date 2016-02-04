package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.v7.appcompat.C0057R;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import java.lang.ref.WeakReference;

public final class ViewStubCompat extends View {
    private int f1914a;
    private int f1915b;
    private WeakReference<View> f1916c;
    private LayoutInflater f1917d;
    private OnInflateListener f1918e;

    public interface OnInflateListener {
        void m3897a(ViewStubCompat viewStubCompat, View view);
    }

    public ViewStubCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ViewStubCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1914a = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0057R.styleable.ViewStubCompat, i, 0);
        this.f1915b = obtainStyledAttributes.getResourceId(C0057R.styleable.ViewStubCompat_android_inflatedId, -1);
        this.f1914a = obtainStyledAttributes.getResourceId(C0057R.styleable.ViewStubCompat_android_layout, 0);
        setId(obtainStyledAttributes.getResourceId(C0057R.styleable.ViewStubCompat_android_id, -1));
        obtainStyledAttributes.recycle();
        setVisibility(8);
        setWillNotDraw(true);
    }

    public int getInflatedId() {
        return this.f1915b;
    }

    public void setInflatedId(int i) {
        this.f1915b = i;
    }

    public int getLayoutResource() {
        return this.f1914a;
    }

    public void setLayoutResource(int i) {
        this.f1914a = i;
    }

    public void setLayoutInflater(LayoutInflater layoutInflater) {
        this.f1917d = layoutInflater;
    }

    public LayoutInflater getLayoutInflater() {
        return this.f1917d;
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(0, 0);
    }

    public void draw(Canvas canvas) {
    }

    protected void dispatchDraw(Canvas canvas) {
    }

    public void setVisibility(int i) {
        if (this.f1916c != null) {
            View view = (View) this.f1916c.get();
            if (view != null) {
                view.setVisibility(i);
                return;
            }
            throw new IllegalStateException("setVisibility called on un-referenced view");
        }
        super.setVisibility(i);
        if (i == 0 || i == 4) {
            m3898a();
        }
    }

    public View m3898a() {
        ViewParent parent = getParent();
        if (parent == null || !(parent instanceof ViewGroup)) {
            throw new IllegalStateException("ViewStub must have a non-null ViewGroup viewParent");
        } else if (this.f1914a != 0) {
            LayoutInflater layoutInflater;
            ViewGroup viewGroup = (ViewGroup) parent;
            if (this.f1917d != null) {
                layoutInflater = this.f1917d;
            } else {
                layoutInflater = LayoutInflater.from(getContext());
            }
            View inflate = layoutInflater.inflate(this.f1914a, viewGroup, false);
            if (this.f1915b != -1) {
                inflate.setId(this.f1915b);
            }
            int indexOfChild = viewGroup.indexOfChild(this);
            viewGroup.removeViewInLayout(this);
            LayoutParams layoutParams = getLayoutParams();
            if (layoutParams != null) {
                viewGroup.addView(inflate, indexOfChild, layoutParams);
            } else {
                viewGroup.addView(inflate, indexOfChild);
            }
            this.f1916c = new WeakReference(inflate);
            if (this.f1918e != null) {
                this.f1918e.m3897a(this, inflate);
            }
            return inflate;
        } else {
            throw new IllegalArgumentException("ViewStub must have a valid layoutResource");
        }
    }

    public void setOnInflateListener(OnInflateListener onInflateListener) {
        this.f1918e = onInflateListener;
    }
}
