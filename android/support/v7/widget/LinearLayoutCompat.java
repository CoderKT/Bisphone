package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.C0057R;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import app.C0110R;
import se.emilsjolander.stickylistheaders.C1128R;

public class LinearLayoutCompat extends ViewGroup {
    private boolean f1239a;
    private int f1240b;
    private int f1241c;
    private int f1242d;
    private int f1243e;
    private int f1244f;
    private float f1245g;
    private boolean f1246h;
    private int[] f1247i;
    private int[] f1248j;
    private Drawable f1249k;
    private int f1250l;
    private int f1251m;
    private int f1252n;
    private int f1253o;

    public class LayoutParams extends MarginLayoutParams {
        public float f1230g;
        public int f1231h;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1231h = -1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0057R.styleable.LinearLayoutCompat_Layout);
            this.f1230g = obtainStyledAttributes.getFloat(C0057R.styleable.LinearLayoutCompat_Layout_android_layout_weight, 0.0f);
            this.f1231h = obtainStyledAttributes.getInt(C0057R.styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.f1231h = -1;
            this.f1230g = 0.0f;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1231h = -1;
        }
    }

    protected /* synthetic */ android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return m2666j();
    }

    public /* synthetic */ android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return m2657b(attributeSet);
    }

    protected /* synthetic */ android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return m2658b(layoutParams);
    }

    public LinearLayoutCompat(Context context) {
        this(context, null);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1239a = true;
        this.f1240b = -1;
        this.f1241c = 0;
        this.f1243e = 8388659;
        TintTypedArray a = TintTypedArray.m3759a(context, attributeSet, C0057R.styleable.LinearLayoutCompat, i, 0);
        int a2 = a.m3761a(C0057R.styleable.LinearLayoutCompat_android_orientation, -1);
        if (a2 >= 0) {
            setOrientation(a2);
        }
        a2 = a.m3761a(C0057R.styleable.LinearLayoutCompat_android_gravity, -1);
        if (a2 >= 0) {
            setGravity(a2);
        }
        boolean a3 = a.m3764a(C0057R.styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (!a3) {
            setBaselineAligned(a3);
        }
        this.f1245g = a.m3760a(C0057R.styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.f1240b = a.m3761a(C0057R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.f1246h = a.m3764a(C0057R.styleable.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(a.m3762a(C0057R.styleable.LinearLayoutCompat_divider));
        this.f1252n = a.m3761a(C0057R.styleable.LinearLayoutCompat_showDividers, 0);
        this.f1253o = a.m3772e(C0057R.styleable.LinearLayoutCompat_dividerPadding, 0);
        a.m3763a();
    }

    public void setShowDividers(int i) {
        if (i != this.f1252n) {
            requestLayout();
        }
        this.f1252n = i;
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public int getShowDividers() {
        return this.f1252n;
    }

    public Drawable getDividerDrawable() {
        return this.f1249k;
    }

    public void setDividerDrawable(Drawable drawable) {
        boolean z = false;
        if (drawable != this.f1249k) {
            this.f1249k = drawable;
            if (drawable != null) {
                this.f1250l = drawable.getIntrinsicWidth();
                this.f1251m = drawable.getIntrinsicHeight();
            } else {
                this.f1250l = 0;
                this.f1251m = 0;
            }
            if (drawable == null) {
                z = true;
            }
            setWillNotDraw(z);
            requestLayout();
        }
    }

    public void setDividerPadding(int i) {
        this.f1253o = i;
    }

    public int getDividerPadding() {
        return this.f1253o;
    }

    public int getDividerWidth() {
        return this.f1250l;
    }

    protected void onDraw(Canvas canvas) {
        if (this.f1249k != null) {
            if (this.f1242d == 1) {
                m2653a(canvas);
            } else {
                m2662b(canvas);
            }
        }
    }

    void m2653a(Canvas canvas) {
        int virtualChildCount = getVirtualChildCount();
        int i = 0;
        while (i < virtualChildCount) {
            View b = m2659b(i);
            if (!(b == null || b.getVisibility() == 8 || !m2664c(i))) {
                m2654a(canvas, (b.getTop() - ((LayoutParams) b.getLayoutParams()).topMargin) - this.f1251m);
            }
            i++;
        }
        if (m2664c(virtualChildCount)) {
            int height;
            View b2 = m2659b(virtualChildCount - 1);
            if (b2 == null) {
                height = (getHeight() - getPaddingBottom()) - this.f1251m;
            } else {
                LayoutParams layoutParams = (LayoutParams) b2.getLayoutParams();
                height = layoutParams.bottomMargin + b2.getBottom();
            }
            m2654a(canvas, height);
        }
    }

    void m2662b(Canvas canvas) {
        int virtualChildCount = getVirtualChildCount();
        boolean a = ViewUtils.m3901a(this);
        int i = 0;
        while (i < virtualChildCount) {
            LayoutParams layoutParams;
            int right;
            View b = m2659b(i);
            if (!(b == null || b.getVisibility() == 8 || !m2664c(i))) {
                layoutParams = (LayoutParams) b.getLayoutParams();
                if (a) {
                    right = layoutParams.rightMargin + b.getRight();
                } else {
                    right = (b.getLeft() - layoutParams.leftMargin) - this.f1250l;
                }
                m2663b(canvas, right);
            }
            i++;
        }
        if (m2664c(virtualChildCount)) {
            View b2 = m2659b(virtualChildCount - 1);
            if (b2 != null) {
                layoutParams = (LayoutParams) b2.getLayoutParams();
                if (a) {
                    right = (b2.getLeft() - layoutParams.leftMargin) - this.f1250l;
                } else {
                    right = layoutParams.rightMargin + b2.getRight();
                }
            } else if (a) {
                right = getPaddingLeft();
            } else {
                right = (getWidth() - getPaddingRight()) - this.f1250l;
            }
            m2663b(canvas, right);
        }
    }

    void m2654a(Canvas canvas, int i) {
        this.f1249k.setBounds(getPaddingLeft() + this.f1253o, i, (getWidth() - getPaddingRight()) - this.f1253o, this.f1251m + i);
        this.f1249k.draw(canvas);
    }

    void m2663b(Canvas canvas, int i) {
        this.f1249k.setBounds(i, getPaddingTop() + this.f1253o, this.f1250l + i, (getHeight() - getPaddingBottom()) - this.f1253o);
        this.f1249k.draw(canvas);
    }

    public void setBaselineAligned(boolean z) {
        this.f1239a = z;
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.f1246h = z;
    }

    public int getBaseline() {
        if (this.f1240b < 0) {
            return super.getBaseline();
        }
        if (getChildCount() <= this.f1240b) {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
        View childAt = getChildAt(this.f1240b);
        int baseline = childAt.getBaseline();
        if (baseline != -1) {
            int i;
            int i2 = this.f1241c;
            if (this.f1242d == 1) {
                i = this.f1243e & 112;
                if (i != 48) {
                    switch (i) {
                        case C1128R.styleable.StickyListHeadersListView_android_dividerHeight /*16*/:
                            i = i2 + (((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.f1244f) / 2);
                            break;
                        case C0110R.styleable.Theme_panelMenuListTheme /*80*/:
                            i = ((getBottom() - getTop()) - getPaddingBottom()) - this.f1244f;
                            break;
                    }
                }
            }
            i = i2;
            return (((LayoutParams) childAt.getLayoutParams()).topMargin + i) + baseline;
        } else if (this.f1240b == 0) {
            return -1;
        } else {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
        }
    }

    public int getBaselineAlignedChildIndex() {
        return this.f1240b;
    }

    public void setBaselineAlignedChildIndex(int i) {
        if (i < 0 || i >= getChildCount()) {
            throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
        }
        this.f1240b = i;
    }

    View m2659b(int i) {
        return getChildAt(i);
    }

    int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.f1245g;
    }

    public void setWeightSum(float f) {
        this.f1245g = Math.max(0.0f, f);
    }

    protected void onMeasure(int i, int i2) {
        if (this.f1242d == 1) {
            m2651a(i, i2);
        } else {
            m2660b(i, i2);
        }
    }

    protected boolean m2664c(int i) {
        if (i == 0) {
            if ((this.f1252n & 1) != 0) {
                return true;
            }
            return false;
        } else if (i == getChildCount()) {
            if ((this.f1252n & 4) == 0) {
                return false;
            }
            return true;
        } else if ((this.f1252n & 2) == 0) {
            return false;
        } else {
            for (int i2 = i - 1; i2 >= 0; i2--) {
                if (getChildAt(i2).getVisibility() != 8) {
                    return true;
                }
            }
            return false;
        }
    }

    void m2651a(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        View b;
        this.f1244f = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        Object obj = 1;
        float f = 0.0f;
        int virtualChildCount = getVirtualChildCount();
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        Object obj2 = null;
        Object obj3 = null;
        int i11 = this.f1240b;
        boolean z = this.f1246h;
        int i12 = Integer.MIN_VALUE;
        int i13 = 0;
        while (i13 < virtualChildCount) {
            Object obj4;
            Object obj5;
            View b2 = m2659b(i13);
            if (b2 == null) {
                this.f1244f += m2665d(i13);
                i3 = i12;
                obj4 = obj3;
                obj5 = obj;
                i4 = i8;
                i5 = i7;
            } else if (b2.getVisibility() == 8) {
                i13 += m2650a(b2, i13);
                i3 = i12;
                obj4 = obj3;
                obj5 = obj;
                i4 = i8;
                i5 = i7;
            } else {
                if (m2664c(i13)) {
                    this.f1244f += this.f1251m;
                }
                LayoutParams layoutParams = (LayoutParams) b2.getLayoutParams();
                float f2 = f + layoutParams.f1230g;
                if (mode2 == 1073741824 && layoutParams.height == 0 && layoutParams.f1230g > 0.0f) {
                    i3 = this.f1244f;
                    this.f1244f = Math.max(i3, (layoutParams.topMargin + i3) + layoutParams.bottomMargin);
                    obj3 = 1;
                } else {
                    i3 = Integer.MIN_VALUE;
                    if (layoutParams.height == 0 && layoutParams.f1230g > 0.0f) {
                        i3 = 0;
                        layoutParams.height = -2;
                    }
                    int i14 = i3;
                    m2655a(b2, i13, i, 0, i2, f2 == 0.0f ? this.f1244f : 0);
                    if (i14 != Integer.MIN_VALUE) {
                        layoutParams.height = i14;
                    }
                    i3 = b2.getMeasuredHeight();
                    int i15 = this.f1244f;
                    this.f1244f = Math.max(i15, (((i15 + i3) + layoutParams.topMargin) + layoutParams.bottomMargin) + m2656b(b2));
                    if (z) {
                        i12 = Math.max(i3, i12);
                    }
                }
                if (i11 >= 0 && i11 == i13 + 1) {
                    this.f1241c = this.f1244f;
                }
                if (i13 >= i11 || layoutParams.f1230g <= 0.0f) {
                    Object obj6;
                    Object obj7 = null;
                    if (mode == 1073741824 || layoutParams.width != -1) {
                        obj6 = obj2;
                    } else {
                        obj6 = 1;
                        obj7 = 1;
                    }
                    i4 = layoutParams.rightMargin + layoutParams.leftMargin;
                    i5 = b2.getMeasuredWidth() + i4;
                    i7 = Math.max(i7, i5);
                    int a = ViewUtils.m3899a(i8, ViewCompat.m1180i(b2));
                    obj5 = (obj == null || layoutParams.width != -1) ? null : 1;
                    if (layoutParams.f1230g > 0.0f) {
                        if (obj7 != null) {
                            i3 = i4;
                        } else {
                            i3 = i5;
                        }
                        i3 = Math.max(i10, i3);
                        i4 = i9;
                    } else {
                        if (obj7 == null) {
                            i4 = i5;
                        }
                        i4 = Math.max(i9, i4);
                        i3 = i10;
                    }
                    i13 += m2650a(b2, i13);
                    obj4 = obj3;
                    i10 = i3;
                    i9 = i4;
                    i5 = i7;
                    i3 = i12;
                    i4 = a;
                    obj2 = obj6;
                    f = f2;
                } else {
                    throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
                }
            }
            i13++;
            i12 = i3;
            obj3 = obj4;
            obj = obj5;
            i8 = i4;
            i7 = i5;
        }
        if (this.f1244f > 0 && m2664c(virtualChildCount)) {
            this.f1244f += this.f1251m;
        }
        if (z && (mode2 == Integer.MIN_VALUE || mode2 == 0)) {
            this.f1244f = 0;
            i6 = 0;
            while (i6 < virtualChildCount) {
                b = m2659b(i6);
                if (b == null) {
                    this.f1244f += m2665d(i6);
                    i3 = i6;
                } else if (b.getVisibility() == 8) {
                    i3 = m2650a(b, i6) + i6;
                } else {
                    LayoutParams layoutParams2 = (LayoutParams) b.getLayoutParams();
                    int i16 = this.f1244f;
                    this.f1244f = Math.max(i16, (layoutParams2.bottomMargin + ((i16 + i12) + layoutParams2.topMargin)) + m2656b(b));
                    i3 = i6;
                }
                i6 = i3 + 1;
            }
        }
        this.f1244f += getPaddingTop() + getPaddingBottom();
        int a2 = ViewCompat.m1153a(Math.max(this.f1244f, getSuggestedMinimumHeight()), i2, 0);
        i6 = (16777215 & a2) - this.f1244f;
        int i17;
        if (obj3 != null || (i6 != 0 && f > 0.0f)) {
            if (this.f1245g > 0.0f) {
                f = this.f1245g;
            }
            this.f1244f = 0;
            i12 = 0;
            float f3 = f;
            Object obj8 = obj;
            i17 = i9;
            i16 = i8;
            i10 = i7;
            i15 = i6;
            while (i12 < virtualChildCount) {
                View b3 = m2659b(i12);
                if (b3.getVisibility() == 8) {
                    i3 = i17;
                    i6 = i16;
                    i4 = i10;
                    obj5 = obj8;
                } else {
                    float f4;
                    float f5;
                    layoutParams2 = (LayoutParams) b3.getLayoutParams();
                    float f6 = layoutParams2.f1230g;
                    if (f6 > 0.0f) {
                        i6 = (int) ((((float) i15) * f6) / f3);
                        f3 -= f6;
                        i15 -= i6;
                        i4 = getChildMeasureSpec(i, ((getPaddingLeft() + getPaddingRight()) + layoutParams2.leftMargin) + layoutParams2.rightMargin, layoutParams2.width);
                        if (layoutParams2.height == 0 && mode2 == 1073741824) {
                            if (i6 <= 0) {
                                i6 = 0;
                            }
                            b3.measure(i4, MeasureSpec.makeMeasureSpec(i6, 1073741824));
                        } else {
                            i6 += b3.getMeasuredHeight();
                            if (i6 < 0) {
                                i6 = 0;
                            }
                            b3.measure(i4, MeasureSpec.makeMeasureSpec(i6, 1073741824));
                        }
                        f4 = f3;
                        i13 = i15;
                        i15 = ViewUtils.m3899a(i16, ViewCompat.m1180i(b3) & -256);
                        f5 = f4;
                    } else {
                        f5 = f3;
                        i13 = i15;
                        i15 = i16;
                    }
                    i16 = layoutParams2.leftMargin + layoutParams2.rightMargin;
                    i4 = b3.getMeasuredWidth() + i16;
                    i10 = Math.max(i10, i4);
                    Object obj9 = (mode == 1073741824 || layoutParams2.width != -1) ? null : 1;
                    if (obj9 == null) {
                        i16 = i4;
                    }
                    i4 = Math.max(i17, i16);
                    obj5 = (obj8 == null || layoutParams2.width != -1) ? null : 1;
                    i5 = this.f1244f;
                    this.f1244f = Math.max(i5, (layoutParams2.bottomMargin + ((b3.getMeasuredHeight() + i5) + layoutParams2.topMargin)) + m2656b(b3));
                    i3 = i4;
                    i4 = i10;
                    f4 = f5;
                    i6 = i15;
                    i15 = i13;
                    f3 = f4;
                }
                i12++;
                i17 = i3;
                i10 = i4;
                obj8 = obj5;
                i16 = i6;
            }
            this.f1244f += getPaddingTop() + getPaddingBottom();
            obj = obj8;
            i3 = i17;
            i8 = i16;
            i6 = i10;
        } else {
            i17 = Math.max(i9, i10);
            if (z && mode2 != 1073741824) {
                for (i6 = 0; i6 < virtualChildCount; i6++) {
                    b = m2659b(i6);
                    if (!(b == null || b.getVisibility() == 8 || ((LayoutParams) b.getLayoutParams()).f1230g <= 0.0f)) {
                        b.measure(MeasureSpec.makeMeasureSpec(b.getMeasuredWidth(), 1073741824), MeasureSpec.makeMeasureSpec(i12, 1073741824));
                    }
                }
            }
            i3 = i17;
            i6 = i7;
        }
        if (obj != null || mode == 1073741824) {
            i3 = i6;
        }
        setMeasuredDimension(ViewCompat.m1153a(Math.max(i3 + (getPaddingLeft() + getPaddingRight()), getSuggestedMinimumWidth()), i, i8), a2);
        if (obj2 != null) {
            m2647c(virtualChildCount, i2);
        }
    }

    private void m2647c(int i, int i2) {
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View b = m2659b(i3);
            if (b.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) b.getLayoutParams();
                if (layoutParams.width == -1) {
                    int i4 = layoutParams.height;
                    layoutParams.height = b.getMeasuredHeight();
                    measureChildWithMargins(b, makeMeasureSpec, 0, i2, 0);
                    layoutParams.height = i4;
                }
            }
        }
    }

    void m2660b(int i, int i2) {
        Object obj;
        int i3;
        int i4;
        LayoutParams layoutParams;
        this.f1244f = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        Object obj2 = 1;
        float f = 0.0f;
        int virtualChildCount = getVirtualChildCount();
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        Object obj3 = null;
        Object obj4 = null;
        if (this.f1247i == null || this.f1248j == null) {
            this.f1247i = new int[4];
            this.f1248j = new int[4];
        }
        int[] iArr = this.f1247i;
        int[] iArr2 = this.f1248j;
        iArr[3] = -1;
        iArr[2] = -1;
        iArr[1] = -1;
        iArr[0] = -1;
        iArr2[3] = -1;
        iArr2[2] = -1;
        iArr2[1] = -1;
        iArr2[0] = -1;
        boolean z = this.f1239a;
        boolean z2 = this.f1246h;
        if (mode == 1073741824) {
            obj = 1;
        } else {
            obj = null;
        }
        int i9 = Integer.MIN_VALUE;
        int i10 = 0;
        while (i10 < virtualChildCount) {
            Object obj5;
            Object obj6;
            int i11;
            int i12;
            View b = m2659b(i10);
            if (b == null) {
                this.f1244f += m2665d(i10);
                i3 = i9;
                obj5 = obj4;
                obj6 = obj2;
                i11 = i6;
                i12 = i5;
            } else if (b.getVisibility() == 8) {
                i10 += m2650a(b, i10);
                i3 = i9;
                obj5 = obj4;
                obj6 = obj2;
                i11 = i6;
                i12 = i5;
            } else {
                Object obj7;
                if (m2664c(i10)) {
                    this.f1244f += this.f1250l;
                }
                LayoutParams layoutParams2 = (LayoutParams) b.getLayoutParams();
                float f2 = f + layoutParams2.f1230g;
                if (mode == 1073741824 && layoutParams2.width == 0 && layoutParams2.f1230g > 0.0f) {
                    if (obj != null) {
                        this.f1244f += layoutParams2.leftMargin + layoutParams2.rightMargin;
                    } else {
                        i3 = this.f1244f;
                        this.f1244f = Math.max(i3, (layoutParams2.leftMargin + i3) + layoutParams2.rightMargin);
                    }
                    if (z) {
                        i3 = MeasureSpec.makeMeasureSpec(0, 0);
                        b.measure(i3, i3);
                    } else {
                        obj4 = 1;
                    }
                } else {
                    i3 = Integer.MIN_VALUE;
                    if (layoutParams2.width == 0 && layoutParams2.f1230g > 0.0f) {
                        i3 = 0;
                        layoutParams2.width = -2;
                    }
                    int i13 = i3;
                    m2655a(b, i10, i, f2 == 0.0f ? this.f1244f : 0, i2, 0);
                    if (i13 != Integer.MIN_VALUE) {
                        layoutParams2.width = i13;
                    }
                    i3 = b.getMeasuredWidth();
                    if (obj != null) {
                        this.f1244f += ((layoutParams2.leftMargin + i3) + layoutParams2.rightMargin) + m2656b(b);
                    } else {
                        int i14 = this.f1244f;
                        this.f1244f = Math.max(i14, (((i14 + i3) + layoutParams2.leftMargin) + layoutParams2.rightMargin) + m2656b(b));
                    }
                    if (z2) {
                        i9 = Math.max(i3, i9);
                    }
                }
                Object obj8 = null;
                if (mode2 == 1073741824 || layoutParams2.height != -1) {
                    obj7 = obj3;
                } else {
                    obj7 = 1;
                    obj8 = 1;
                }
                i11 = layoutParams2.bottomMargin + layoutParams2.topMargin;
                i12 = b.getMeasuredHeight() + i11;
                int a = ViewUtils.m3899a(i6, ViewCompat.m1180i(b));
                if (z) {
                    i6 = b.getBaseline();
                    if (i6 != -1) {
                        int i15 = ((((layoutParams2.f1231h < 0 ? this.f1243e : layoutParams2.f1231h) & 112) >> 4) & -2) >> 1;
                        iArr[i15] = Math.max(iArr[i15], i6);
                        iArr2[i15] = Math.max(iArr2[i15], i12 - i6);
                    }
                }
                i6 = Math.max(i5, i12);
                obj6 = (obj2 == null || layoutParams2.height != -1) ? null : 1;
                if (layoutParams2.f1230g > 0.0f) {
                    if (obj8 != null) {
                        i3 = i11;
                    } else {
                        i3 = i12;
                    }
                    i3 = Math.max(i8, i3);
                    i11 = i7;
                } else {
                    if (obj8 == null) {
                        i11 = i12;
                    }
                    i11 = Math.max(i7, i11);
                    i3 = i8;
                }
                i10 += m2650a(b, i10);
                obj5 = obj4;
                i8 = i3;
                i7 = i11;
                i12 = i6;
                i3 = i9;
                i11 = a;
                obj3 = obj7;
                f = f2;
            }
            i10++;
            i9 = i3;
            obj4 = obj5;
            obj2 = obj6;
            i6 = i11;
            i5 = i12;
        }
        if (this.f1244f > 0 && m2664c(virtualChildCount)) {
            this.f1244f += this.f1250l;
        }
        if (iArr[1] == -1 && iArr[0] == -1 && iArr[2] == -1 && iArr[3] == -1) {
            i10 = i5;
        } else {
            i10 = Math.max(i5, Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))) + Math.max(iArr2[3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))));
        }
        if (z2 && (mode == Integer.MIN_VALUE || mode == 0)) {
            this.f1244f = 0;
            i4 = 0;
            while (i4 < virtualChildCount) {
                View b2 = m2659b(i4);
                if (b2 == null) {
                    this.f1244f += m2665d(i4);
                    i3 = i4;
                } else if (b2.getVisibility() == 8) {
                    i3 = m2650a(b2, i4) + i4;
                } else {
                    layoutParams = (LayoutParams) b2.getLayoutParams();
                    if (obj != null) {
                        this.f1244f = ((layoutParams.rightMargin + (layoutParams.leftMargin + i9)) + m2656b(b2)) + this.f1244f;
                        i3 = i4;
                    } else {
                        i11 = this.f1244f;
                        this.f1244f = Math.max(i11, (layoutParams.rightMargin + ((i11 + i9) + layoutParams.leftMargin)) + m2656b(b2));
                        i3 = i4;
                    }
                }
                i4 = i3 + 1;
            }
        }
        this.f1244f += getPaddingLeft() + getPaddingRight();
        int a2 = ViewCompat.m1153a(Math.max(this.f1244f, getSuggestedMinimumWidth()), i, 0);
        i4 = (16777215 & a2) - this.f1244f;
        int i16;
        if (obj4 != null || (i4 != 0 && f > 0.0f)) {
            if (this.f1245g > 0.0f) {
                f = this.f1245g;
            }
            iArr[3] = -1;
            iArr[2] = -1;
            iArr[1] = -1;
            iArr[0] = -1;
            iArr2[3] = -1;
            iArr2[2] = -1;
            iArr2[1] = -1;
            iArr2[0] = -1;
            this.f1244f = 0;
            i9 = 0;
            float f3 = f;
            Object obj9 = obj2;
            i16 = i7;
            i15 = i6;
            i14 = i4;
            i7 = -1;
            while (i9 < virtualChildCount) {
                float f4;
                Object obj10;
                View b3 = m2659b(i9);
                if (b3 == null) {
                    f4 = f3;
                    i4 = i14;
                    i11 = i7;
                    i14 = i16;
                    obj10 = obj9;
                } else if (b3.getVisibility() == 8) {
                    f4 = f3;
                    i4 = i14;
                    i11 = i7;
                    i14 = i16;
                    obj10 = obj9;
                } else {
                    float f5;
                    layoutParams = (LayoutParams) b3.getLayoutParams();
                    float f6 = layoutParams.f1230g;
                    if (f6 > 0.0f) {
                        i4 = (int) ((((float) i14) * f6) / f3);
                        f3 -= f6;
                        i11 = i14 - i4;
                        i14 = getChildMeasureSpec(i2, ((getPaddingTop() + getPaddingBottom()) + layoutParams.topMargin) + layoutParams.bottomMargin, layoutParams.height);
                        if (layoutParams.width == 0 && mode == 1073741824) {
                            if (i4 <= 0) {
                                i4 = 0;
                            }
                            b3.measure(MeasureSpec.makeMeasureSpec(i4, 1073741824), i14);
                        } else {
                            i4 += b3.getMeasuredWidth();
                            if (i4 < 0) {
                                i4 = 0;
                            }
                            b3.measure(MeasureSpec.makeMeasureSpec(i4, 1073741824), i14);
                        }
                        i8 = ViewUtils.m3899a(i15, ViewCompat.m1180i(b3) & -16777216);
                        f5 = f3;
                    } else {
                        i11 = i14;
                        i8 = i15;
                        f5 = f3;
                    }
                    if (obj != null) {
                        this.f1244f += ((b3.getMeasuredWidth() + layoutParams.leftMargin) + layoutParams.rightMargin) + m2656b(b3);
                    } else {
                        i4 = this.f1244f;
                        this.f1244f = Math.max(i4, (((b3.getMeasuredWidth() + i4) + layoutParams.leftMargin) + layoutParams.rightMargin) + m2656b(b3));
                    }
                    obj5 = (mode2 == 1073741824 || layoutParams.height != -1) ? null : 1;
                    i10 = layoutParams.topMargin + layoutParams.bottomMargin;
                    i14 = b3.getMeasuredHeight() + i10;
                    i7 = Math.max(i7, i14);
                    if (obj5 != null) {
                        i4 = i10;
                    } else {
                        i4 = i14;
                    }
                    i10 = Math.max(i16, i4);
                    obj5 = (obj9 == null || layoutParams.height != -1) ? null : 1;
                    if (z) {
                        i12 = b3.getBaseline();
                        if (i12 != -1) {
                            i3 = ((((layoutParams.f1231h < 0 ? this.f1243e : layoutParams.f1231h) & 112) >> 4) & -2) >> 1;
                            iArr[i3] = Math.max(iArr[i3], i12);
                            iArr2[i3] = Math.max(iArr2[i3], i14 - i12);
                        }
                    }
                    f4 = f5;
                    i14 = i10;
                    obj10 = obj5;
                    i15 = i8;
                    i4 = i11;
                    i11 = i7;
                }
                i9++;
                i16 = i14;
                i7 = i11;
                obj9 = obj10;
                i14 = i4;
                f3 = f4;
            }
            this.f1244f += getPaddingLeft() + getPaddingRight();
            if (!(iArr[1] == -1 && iArr[0] == -1 && iArr[2] == -1 && iArr[3] == -1)) {
                i7 = Math.max(i7, Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))) + Math.max(iArr2[3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))));
            }
            obj2 = obj9;
            i3 = i16;
            i6 = i15;
            i4 = i7;
        } else {
            i16 = Math.max(i7, i8);
            if (z2 && mode != 1073741824) {
                for (i4 = 0; i4 < virtualChildCount; i4++) {
                    View b4 = m2659b(i4);
                    if (!(b4 == null || b4.getVisibility() == 8 || ((LayoutParams) b4.getLayoutParams()).f1230g <= 0.0f)) {
                        b4.measure(MeasureSpec.makeMeasureSpec(i9, 1073741824), MeasureSpec.makeMeasureSpec(b4.getMeasuredHeight(), 1073741824));
                    }
                }
            }
            i3 = i16;
            i4 = i10;
        }
        if (obj2 != null || mode2 == 1073741824) {
            i3 = i4;
        }
        setMeasuredDimension((-16777216 & i6) | a2, ViewCompat.m1153a(Math.max(i3 + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight()), i2, i6 << 16));
        if (obj3 != null) {
            m2648d(virtualChildCount, i);
        }
    }

    private void m2648d(int i, int i2) {
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View b = m2659b(i3);
            if (b.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) b.getLayoutParams();
                if (layoutParams.height == -1) {
                    int i4 = layoutParams.width;
                    layoutParams.width = b.getMeasuredWidth();
                    measureChildWithMargins(b, i2, 0, makeMeasureSpec, 0);
                    layoutParams.width = i4;
                }
            }
        }
    }

    int m2650a(View view, int i) {
        return 0;
    }

    int m2665d(int i) {
        return 0;
    }

    void m2655a(View view, int i, int i2, int i3, int i4, int i5) {
        measureChildWithMargins(view, i2, i3, i4, i5);
    }

    int m2649a(View view) {
        return 0;
    }

    int m2656b(View view) {
        return 0;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.f1242d == 1) {
            m2652a(i, i2, i3, i4);
        } else {
            m2661b(i, i2, i3, i4);
        }
    }

    void m2652a(int i, int i2, int i3, int i4) {
        int paddingLeft = getPaddingLeft();
        int i5 = i3 - i;
        int paddingRight = i5 - getPaddingRight();
        int paddingRight2 = (i5 - paddingLeft) - getPaddingRight();
        int virtualChildCount = getVirtualChildCount();
        int i6 = this.f1243e & 8388615;
        switch (this.f1243e & 112) {
            case C1128R.styleable.StickyListHeadersListView_android_dividerHeight /*16*/:
                i5 = getPaddingTop() + (((i4 - i2) - this.f1244f) / 2);
                break;
            case C0110R.styleable.Theme_panelMenuListTheme /*80*/:
                i5 = ((getPaddingTop() + i4) - i2) - this.f1244f;
                break;
            default:
                i5 = getPaddingTop();
                break;
        }
        int i7 = 0;
        int i8 = i5;
        while (i7 < virtualChildCount) {
            View b = m2659b(i7);
            if (b == null) {
                i8 += m2665d(i7);
                i5 = i7;
            } else if (b.getVisibility() != 8) {
                int i9;
                int measuredWidth = b.getMeasuredWidth();
                int measuredHeight = b.getMeasuredHeight();
                LayoutParams layoutParams = (LayoutParams) b.getLayoutParams();
                i5 = layoutParams.f1231h;
                if (i5 < 0) {
                    i5 = i6;
                }
                switch (GravityCompat.m899a(i5, ViewCompat.m1179h(this)) & 7) {
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        i9 = ((((paddingRight2 - measuredWidth) / 2) + paddingLeft) + layoutParams.leftMargin) - layoutParams.rightMargin;
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                        i9 = (paddingRight - measuredWidth) - layoutParams.rightMargin;
                        break;
                    default:
                        i9 = paddingLeft + layoutParams.leftMargin;
                        break;
                }
                if (m2664c(i7)) {
                    i5 = this.f1251m + i8;
                } else {
                    i5 = i8;
                }
                int i10 = i5 + layoutParams.topMargin;
                m2646a(b, i9, i10 + m2649a(b), measuredWidth, measuredHeight);
                i8 = i10 + ((layoutParams.bottomMargin + measuredHeight) + m2656b(b));
                i5 = m2650a(b, i7) + i7;
            } else {
                i5 = i7;
            }
            i7 = i5 + 1;
        }
    }

    void m2661b(int i, int i2, int i3, int i4) {
        int paddingLeft;
        int i5;
        int i6;
        boolean a = ViewUtils.m3901a(this);
        int paddingTop = getPaddingTop();
        int i7 = i4 - i2;
        int paddingBottom = i7 - getPaddingBottom();
        int paddingBottom2 = (i7 - paddingTop) - getPaddingBottom();
        int virtualChildCount = getVirtualChildCount();
        i7 = this.f1243e & 8388615;
        int i8 = this.f1243e & 112;
        boolean z = this.f1239a;
        int[] iArr = this.f1247i;
        int[] iArr2 = this.f1248j;
        switch (GravityCompat.m899a(i7, ViewCompat.m1179h(this))) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                paddingLeft = getPaddingLeft() + (((i3 - i) - this.f1244f) / 2);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                paddingLeft = ((getPaddingLeft() + i3) - i) - this.f1244f;
                break;
            default:
                paddingLeft = getPaddingLeft();
                break;
        }
        if (a) {
            i5 = -1;
            i6 = virtualChildCount - 1;
        } else {
            i5 = 1;
            i6 = 0;
        }
        int i9 = 0;
        while (i9 < virtualChildCount) {
            int i10 = i6 + (i5 * i9);
            View b = m2659b(i10);
            if (b == null) {
                paddingLeft += m2665d(i10);
                i7 = i9;
            } else if (b.getVisibility() != 8) {
                int i11;
                int measuredWidth = b.getMeasuredWidth();
                int measuredHeight = b.getMeasuredHeight();
                LayoutParams layoutParams = (LayoutParams) b.getLayoutParams();
                if (!z || layoutParams.height == -1) {
                    i7 = -1;
                } else {
                    i7 = b.getBaseline();
                }
                int i12 = layoutParams.f1231h;
                if (i12 < 0) {
                    i12 = i8;
                }
                switch (i12 & 112) {
                    case C1128R.styleable.StickyListHeadersListView_android_dividerHeight /*16*/:
                        i11 = ((((paddingBottom2 - measuredHeight) / 2) + paddingTop) + layoutParams.topMargin) - layoutParams.bottomMargin;
                        break;
                    case C0110R.styleable.Theme_homeAsUpIndicator /*48*/:
                        i11 = paddingTop + layoutParams.topMargin;
                        if (i7 != -1) {
                            i11 += iArr[1] - i7;
                            break;
                        }
                        break;
                    case C0110R.styleable.Theme_panelMenuListTheme /*80*/:
                        i11 = (paddingBottom - measuredHeight) - layoutParams.bottomMargin;
                        if (i7 != -1) {
                            i11 -= iArr2[2] - (b.getMeasuredHeight() - i7);
                            break;
                        }
                        break;
                    default:
                        i11 = paddingTop;
                        break;
                }
                if (m2664c(i10)) {
                    i7 = this.f1250l + paddingLeft;
                } else {
                    i7 = paddingLeft;
                }
                paddingLeft = i7 + layoutParams.leftMargin;
                m2646a(b, paddingLeft + m2649a(b), i11, measuredWidth, measuredHeight);
                paddingLeft += (layoutParams.rightMargin + measuredWidth) + m2656b(b);
                i7 = m2650a(b, i10) + i9;
            } else {
                i7 = i9;
            }
            i9 = i7 + 1;
        }
    }

    private void m2646a(View view, int i, int i2, int i3, int i4) {
        view.layout(i, i2, i + i3, i2 + i4);
    }

    public void setOrientation(int i) {
        if (this.f1242d != i) {
            this.f1242d = i;
            requestLayout();
        }
    }

    public int getOrientation() {
        return this.f1242d;
    }

    public void setGravity(int i) {
        if (this.f1243e != i) {
            int i2;
            if ((8388615 & i) == 0) {
                i2 = 8388611 | i;
            } else {
                i2 = i;
            }
            if ((i2 & 112) == 0) {
                i2 |= 48;
            }
            this.f1243e = i2;
            requestLayout();
        }
    }

    public void setHorizontalGravity(int i) {
        int i2 = i & 8388615;
        if ((this.f1243e & 8388615) != i2) {
            this.f1243e = i2 | (this.f1243e & -8388616);
            requestLayout();
        }
    }

    public void setVerticalGravity(int i) {
        int i2 = i & 112;
        if ((this.f1243e & 112) != i2) {
            this.f1243e = i2 | (this.f1243e & -113);
            requestLayout();
        }
    }

    public LayoutParams m2657b(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    protected LayoutParams m2666j() {
        if (this.f1242d == 0) {
            return new LayoutParams(-2, -2);
        }
        if (this.f1242d == 1) {
            return new LayoutParams(-1, -2);
        }
        return null;
    }

    protected LayoutParams m2658b(android.view.ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (VERSION.SDK_INT >= 14) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName(LinearLayoutCompat.class.getName());
        }
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (VERSION.SDK_INT >= 14) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName(LinearLayoutCompat.class.getName());
        }
    }
}
