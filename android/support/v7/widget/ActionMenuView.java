package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuBuilder.ItemInvoker;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPresenter.Callback;
import android.support.v7.view.menu.MenuView;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewDebug.ExportedProperty;
import android.view.accessibility.AccessibilityEvent;

public class ActionMenuView extends LinearLayoutCompat implements ItemInvoker, MenuView {
    private MenuBuilder f1254a;
    private Context f1255b;
    private int f1256c;
    private boolean f1257d;
    private ActionMenuPresenter f1258e;
    private Callback f1259f;
    private MenuBuilder.Callback f1260g;
    private boolean f1261h;
    private int f1262i;
    private int f1263j;
    private int f1264k;
    private OnMenuItemClickListener f1265l;

    public interface ActionMenuChildView {
        boolean m2338c();

        boolean m2339d();
    }

    class ActionMenuPresenterCallback implements Callback {
        final /* synthetic */ ActionMenuView f1229a;

        private ActionMenuPresenterCallback(ActionMenuView actionMenuView) {
            this.f1229a = actionMenuView;
        }

        public void m2641a(MenuBuilder menuBuilder, boolean z) {
        }

        public boolean m2642a(MenuBuilder menuBuilder) {
            return false;
        }
    }

    public class LayoutParams extends android.support.v7.widget.LinearLayoutCompat.LayoutParams {
        @ExportedProperty
        public boolean f1232a;
        @ExportedProperty
        public int f1233b;
        @ExportedProperty
        public int f1234c;
        @ExportedProperty
        public boolean f1235d;
        @ExportedProperty
        public boolean f1236e;
        boolean f1237f;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
            this.f1232a = layoutParams.f1232a;
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.f1232a = false;
        }
    }

    class MenuBuilderCallback implements MenuBuilder.Callback {
        final /* synthetic */ ActionMenuView f1238a;

        private MenuBuilderCallback(ActionMenuView actionMenuView) {
            this.f1238a = actionMenuView;
        }

        public boolean m2644a(MenuBuilder menuBuilder, MenuItem menuItem) {
            return this.f1238a.f1265l != null && this.f1238a.f1265l.m2645a(menuItem);
        }

        public void m2643a(MenuBuilder menuBuilder) {
            if (this.f1238a.f1260g != null) {
                this.f1238a.f1260g.m2032a(menuBuilder);
            }
        }
    }

    public interface OnMenuItemClickListener {
        boolean m2645a(MenuItem menuItem);
    }

    public /* synthetic */ android.support.v7.widget.LinearLayoutCompat.LayoutParams m2679b(AttributeSet attributeSet) {
        return m2671a(attributeSet);
    }

    protected /* synthetic */ android.support.v7.widget.LinearLayoutCompat.LayoutParams m2680b(android.view.ViewGroup.LayoutParams layoutParams) {
        return m2672a(layoutParams);
    }

    protected /* synthetic */ android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return m2678b();
    }

    public /* synthetic */ android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return m2671a(attributeSet);
    }

    protected /* synthetic */ android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return m2672a(layoutParams);
    }

    protected /* synthetic */ android.support.v7.widget.LinearLayoutCompat.LayoutParams m2688j() {
        return m2678b();
    }

    public ActionMenuView(Context context) {
        this(context, null);
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBaselineAligned(false);
        float f = context.getResources().getDisplayMetrics().density;
        this.f1263j = (int) (56.0f * f);
        this.f1264k = (int) (f * 4.0f);
        this.f1255b = context;
        this.f1256c = 0;
    }

    public void setPopupTheme(int i) {
        if (this.f1256c != i) {
            this.f1256c = i;
            if (i == 0) {
                this.f1255b = getContext();
            } else {
                this.f1255b = new ContextThemeWrapper(getContext(), i);
            }
        }
    }

    public int getPopupTheme() {
        return this.f1256c;
    }

    public void setPresenter(ActionMenuPresenter actionMenuPresenter) {
        this.f1258e = actionMenuPresenter;
        this.f1258e.m2625a(this);
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (VERSION.SDK_INT >= 8) {
            super.onConfigurationChanged(configuration);
        }
        if (this.f1258e != null) {
            this.f1258e.m2630b(false);
            if (this.f1258e.m2639h()) {
                this.f1258e.m2636e();
                this.f1258e.m2635d();
            }
        }
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.f1265l = onMenuItemClickListener;
    }

    protected void onMeasure(int i, int i2) {
        boolean z = this.f1261h;
        this.f1261h = MeasureSpec.getMode(i) == 1073741824;
        if (z != this.f1261h) {
            this.f1262i = 0;
        }
        int size = MeasureSpec.getSize(i);
        if (!(!this.f1261h || this.f1254a == null || size == this.f1262i)) {
            this.f1262i = size;
            this.f1254a.m2432b(true);
        }
        int childCount = getChildCount();
        if (!this.f1261h || childCount <= 0) {
            for (int i3 = 0; i3 < childCount; i3++) {
                LayoutParams layoutParams = (LayoutParams) getChildAt(i3).getLayoutParams();
                layoutParams.rightMargin = 0;
                layoutParams.leftMargin = 0;
            }
            super.onMeasure(i, i2);
            return;
        }
        m2670c(i, i2);
    }

    private void m2670c(int i, int i2) {
        int mode = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childMeasureSpec = getChildMeasureSpec(i2, paddingTop, -2);
        int i3 = size - paddingLeft;
        int i4 = i3 / this.f1263j;
        size = i3 % this.f1263j;
        if (i4 == 0) {
            setMeasuredDimension(i3, 0);
            return;
        }
        int i5;
        LayoutParams layoutParams;
        Object obj;
        Object obj2;
        int i6 = this.f1263j + (size / i4);
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        paddingLeft = 0;
        Object obj3 = null;
        long j = 0;
        int childCount = getChildCount();
        int i10 = 0;
        while (i10 < childCount) {
            int i11;
            long j2;
            int i12;
            int i13;
            View childAt = getChildAt(i10);
            if (childAt.getVisibility() == 8) {
                i11 = paddingLeft;
                j2 = j;
                i12 = i7;
                i13 = i4;
                i4 = i8;
            } else {
                boolean z = childAt instanceof ActionMenuItemView;
                i5 = paddingLeft + 1;
                if (z) {
                    childAt.setPadding(this.f1264k, 0, this.f1264k, 0);
                }
                layoutParams = (LayoutParams) childAt.getLayoutParams();
                layoutParams.f1237f = false;
                layoutParams.f1234c = 0;
                layoutParams.f1233b = 0;
                layoutParams.f1235d = false;
                layoutParams.leftMargin = 0;
                layoutParams.rightMargin = 0;
                boolean z2 = z && ((ActionMenuItemView) childAt).m2346b();
                layoutParams.f1236e = z2;
                if (layoutParams.f1232a) {
                    paddingLeft = 1;
                } else {
                    paddingLeft = i4;
                }
                int a = m2667a(childAt, i6, paddingLeft, childMeasureSpec, paddingTop);
                i8 = Math.max(i8, a);
                if (layoutParams.f1235d) {
                    paddingLeft = i9 + 1;
                } else {
                    paddingLeft = i9;
                }
                if (layoutParams.f1232a) {
                    obj = 1;
                } else {
                    obj = obj3;
                }
                int i14 = i4 - a;
                i9 = Math.max(i7, childAt.getMeasuredHeight());
                if (a == 1) {
                    long j3 = ((long) (1 << i10)) | j;
                    i12 = i9;
                    i13 = i14;
                    i9 = paddingLeft;
                    obj3 = obj;
                    j2 = j3;
                    i4 = i8;
                    i11 = i5;
                } else {
                    i11 = i5;
                    i4 = i8;
                    long j4 = j;
                    i12 = i9;
                    i13 = i14;
                    obj3 = obj;
                    i9 = paddingLeft;
                    j2 = j4;
                }
            }
            i10++;
            i8 = i4;
            i7 = i12;
            i4 = i13;
            j = j2;
            paddingLeft = i11;
        }
        if (obj3 == null || paddingLeft != 2) {
            obj2 = null;
        } else {
            obj2 = 1;
        }
        Object obj4 = null;
        long j5 = j;
        paddingTop = i4;
        while (i9 > 0 && paddingTop > 0) {
            i5 = Integer.MAX_VALUE;
            j = 0;
            i4 = 0;
            int i15 = 0;
            while (i15 < childCount) {
                layoutParams = (LayoutParams) getChildAt(i15).getLayoutParams();
                if (layoutParams.f1235d) {
                    int i16 = layoutParams.f1233b;
                    if (r0 < i5) {
                        i4 = layoutParams.f1233b;
                        j = (long) (1 << i15);
                        size = 1;
                    } else if (layoutParams.f1233b == i5) {
                        j |= (long) (1 << i15);
                        size = i4 + 1;
                        i4 = i5;
                    } else {
                        size = i4;
                        i4 = i5;
                    }
                } else {
                    size = i4;
                    i4 = i5;
                }
                i15++;
                i5 = i4;
                i4 = size;
            }
            j5 |= j;
            if (i4 > paddingTop) {
                j = j5;
                break;
            }
            i15 = i5 + 1;
            i5 = 0;
            i4 = paddingTop;
            long j6 = j5;
            while (i5 < childCount) {
                View childAt2 = getChildAt(i5);
                layoutParams = (LayoutParams) childAt2.getLayoutParams();
                if ((((long) (1 << i5)) & j) != 0) {
                    if (obj2 != null && layoutParams.f1236e && i4 == 1) {
                        childAt2.setPadding(this.f1264k + i6, 0, this.f1264k, 0);
                    }
                    layoutParams.f1233b++;
                    layoutParams.f1237f = true;
                    size = i4 - 1;
                } else if (layoutParams.f1233b == i15) {
                    j6 |= (long) (1 << i5);
                    size = i4;
                } else {
                    size = i4;
                }
                i5++;
                i4 = size;
            }
            j5 = j6;
            i10 = 1;
            paddingTop = i4;
        }
        j = j5;
        obj = (obj3 == null && paddingLeft == 1) ? 1 : null;
        if (paddingTop <= 0 || j == 0 || (paddingTop >= paddingLeft - 1 && obj == null && i8 <= 1)) {
            obj2 = obj4;
        } else {
            float f;
            View childAt3;
            float bitCount = (float) Long.bitCount(j);
            if (obj == null) {
                if (!((1 & j) == 0 || ((LayoutParams) getChildAt(0).getLayoutParams()).f1236e)) {
                    bitCount -= 0.5f;
                }
                if (!((((long) (1 << (childCount - 1))) & j) == 0 || ((LayoutParams) getChildAt(childCount - 1).getLayoutParams()).f1236e)) {
                    f = bitCount - 0.5f;
                    paddingLeft = f <= 0.0f ? (int) (((float) (paddingTop * i6)) / f) : 0;
                    i4 = 0;
                    obj2 = obj4;
                    while (i4 < childCount) {
                        if ((((long) (1 << i4)) & j) != 0) {
                            obj = obj2;
                        } else {
                            childAt3 = getChildAt(i4);
                            layoutParams = (LayoutParams) childAt3.getLayoutParams();
                            if (childAt3 instanceof ActionMenuItemView) {
                                layoutParams.f1234c = paddingLeft;
                                layoutParams.f1237f = true;
                                if (i4 == 0 && !layoutParams.f1236e) {
                                    layoutParams.leftMargin = (-paddingLeft) / 2;
                                }
                                obj = 1;
                            } else if (layoutParams.f1232a) {
                                if (i4 != 0) {
                                    layoutParams.leftMargin = paddingLeft / 2;
                                }
                                if (i4 != childCount - 1) {
                                    layoutParams.rightMargin = paddingLeft / 2;
                                }
                                obj = obj2;
                            } else {
                                layoutParams.f1234c = paddingLeft;
                                layoutParams.f1237f = true;
                                layoutParams.rightMargin = (-paddingLeft) / 2;
                                obj = 1;
                            }
                        }
                        i4++;
                        obj2 = obj;
                    }
                }
            }
            f = bitCount;
            if (f <= 0.0f) {
            }
            i4 = 0;
            obj2 = obj4;
            while (i4 < childCount) {
                if ((((long) (1 << i4)) & j) != 0) {
                    childAt3 = getChildAt(i4);
                    layoutParams = (LayoutParams) childAt3.getLayoutParams();
                    if (childAt3 instanceof ActionMenuItemView) {
                        layoutParams.f1234c = paddingLeft;
                        layoutParams.f1237f = true;
                        layoutParams.leftMargin = (-paddingLeft) / 2;
                        obj = 1;
                    } else if (layoutParams.f1232a) {
                        if (i4 != 0) {
                            layoutParams.leftMargin = paddingLeft / 2;
                        }
                        if (i4 != childCount - 1) {
                            layoutParams.rightMargin = paddingLeft / 2;
                        }
                        obj = obj2;
                    } else {
                        layoutParams.f1234c = paddingLeft;
                        layoutParams.f1237f = true;
                        layoutParams.rightMargin = (-paddingLeft) / 2;
                        obj = 1;
                    }
                } else {
                    obj = obj2;
                }
                i4++;
                obj2 = obj;
            }
        }
        if (obj2 != null) {
            for (paddingLeft = 0; paddingLeft < childCount; paddingLeft++) {
                childAt = getChildAt(paddingLeft);
                layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.f1237f) {
                    childAt.measure(MeasureSpec.makeMeasureSpec(layoutParams.f1234c + (layoutParams.f1233b * i6), 1073741824), childMeasureSpec);
                }
            }
        }
        if (mode == 1073741824) {
            i7 = size2;
        }
        setMeasuredDimension(i3, i7);
    }

    static int m2667a(View view, int i, int i2, int i3, int i4) {
        boolean z;
        int i5;
        boolean z2 = false;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(i3) - i4, MeasureSpec.getMode(i3));
        ActionMenuItemView actionMenuItemView = view instanceof ActionMenuItemView ? (ActionMenuItemView) view : null;
        if (actionMenuItemView == null || !actionMenuItemView.m2346b()) {
            z = false;
        } else {
            z = true;
        }
        if (i2 <= 0 || (z && i2 < 2)) {
            i5 = 0;
        } else {
            view.measure(MeasureSpec.makeMeasureSpec(i * i2, Integer.MIN_VALUE), makeMeasureSpec);
            int measuredWidth = view.getMeasuredWidth();
            i5 = measuredWidth / i;
            if (measuredWidth % i != 0) {
                i5++;
            }
            if (z && r1 < 2) {
                i5 = 2;
            }
        }
        if (!layoutParams.f1232a && z) {
            z2 = true;
        }
        layoutParams.f1235d = z2;
        layoutParams.f1233b = i5;
        view.measure(MeasureSpec.makeMeasureSpec(i5 * i, 1073741824), makeMeasureSpec);
        return i5;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.f1261h) {
            int i5;
            int i6;
            LayoutParams layoutParams;
            int paddingLeft;
            int childCount = getChildCount();
            int i7 = (i4 - i2) / 2;
            int dividerWidth = getDividerWidth();
            int i8 = 0;
            int i9 = 0;
            int paddingRight = ((i3 - i) - getPaddingRight()) - getPaddingLeft();
            Object obj = null;
            boolean a = ViewUtils.m3901a(this);
            int i10 = 0;
            while (i10 < childCount) {
                Object obj2;
                View childAt = getChildAt(i10);
                if (childAt.getVisibility() == 8) {
                    obj2 = obj;
                    i5 = i9;
                    i6 = paddingRight;
                    paddingRight = i8;
                } else {
                    layoutParams = (LayoutParams) childAt.getLayoutParams();
                    if (layoutParams.f1232a) {
                        i6 = childAt.getMeasuredWidth();
                        if (m2676a(i10)) {
                            i6 += dividerWidth;
                        }
                        int measuredHeight = childAt.getMeasuredHeight();
                        if (a) {
                            paddingLeft = layoutParams.leftMargin + getPaddingLeft();
                            i5 = paddingLeft + i6;
                        } else {
                            i5 = (getWidth() - getPaddingRight()) - layoutParams.rightMargin;
                            paddingLeft = i5 - i6;
                        }
                        int i11 = i7 - (measuredHeight / 2);
                        childAt.layout(paddingLeft, i11, i5, measuredHeight + i11);
                        i6 = paddingRight - i6;
                        obj2 = 1;
                        i5 = i9;
                        paddingRight = i8;
                    } else {
                        i5 = (childAt.getMeasuredWidth() + layoutParams.leftMargin) + layoutParams.rightMargin;
                        paddingLeft = i8 + i5;
                        i5 = paddingRight - i5;
                        if (m2676a(i10)) {
                            paddingLeft += dividerWidth;
                        }
                        Object obj3 = obj;
                        i6 = i5;
                        i5 = i9 + 1;
                        paddingRight = paddingLeft;
                        obj2 = obj3;
                    }
                }
                i10++;
                i8 = paddingRight;
                paddingRight = i6;
                i9 = i5;
                obj = obj2;
            }
            if (childCount == 1 && obj == null) {
                View childAt2 = getChildAt(0);
                i6 = childAt2.getMeasuredWidth();
                i5 = childAt2.getMeasuredHeight();
                paddingRight = ((i3 - i) / 2) - (i6 / 2);
                i9 = i7 - (i5 / 2);
                childAt2.layout(paddingRight, i9, i6 + paddingRight, i5 + i9);
                return;
            }
            paddingLeft = i9 - (obj != null ? 0 : 1);
            paddingRight = Math.max(0, paddingLeft > 0 ? paddingRight / paddingLeft : 0);
            View childAt3;
            if (a) {
                i6 = getWidth() - getPaddingRight();
                i5 = 0;
                while (i5 < childCount) {
                    childAt3 = getChildAt(i5);
                    layoutParams = (LayoutParams) childAt3.getLayoutParams();
                    if (childAt3.getVisibility() == 8) {
                        paddingLeft = i6;
                    } else if (layoutParams.f1232a) {
                        paddingLeft = i6;
                    } else {
                        i6 -= layoutParams.rightMargin;
                        i8 = childAt3.getMeasuredWidth();
                        i10 = childAt3.getMeasuredHeight();
                        dividerWidth = i7 - (i10 / 2);
                        childAt3.layout(i6 - i8, dividerWidth, i6, i10 + dividerWidth);
                        paddingLeft = i6 - ((layoutParams.leftMargin + i8) + paddingRight);
                    }
                    i5++;
                    i6 = paddingLeft;
                }
                return;
            }
            i6 = getPaddingLeft();
            i5 = 0;
            while (i5 < childCount) {
                childAt3 = getChildAt(i5);
                layoutParams = (LayoutParams) childAt3.getLayoutParams();
                if (childAt3.getVisibility() == 8) {
                    paddingLeft = i6;
                } else if (layoutParams.f1232a) {
                    paddingLeft = i6;
                } else {
                    i6 += layoutParams.leftMargin;
                    i8 = childAt3.getMeasuredWidth();
                    i10 = childAt3.getMeasuredHeight();
                    dividerWidth = i7 - (i10 / 2);
                    childAt3.layout(i6, dividerWidth, i6 + i8, i10 + dividerWidth);
                    paddingLeft = ((layoutParams.rightMargin + i8) + paddingRight) + i6;
                }
                i5++;
                i6 = paddingLeft;
            }
            return;
        }
        super.onLayout(z, i, i2, i3, i4);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m2687i();
    }

    public void setOverflowIcon(Drawable drawable) {
        getMenu();
        this.f1258e.m2622a(drawable);
    }

    public Drawable getOverflowIcon() {
        getMenu();
        return this.f1258e.m2632c();
    }

    public boolean m2675a() {
        return this.f1257d;
    }

    public void setOverflowReserved(boolean z) {
        this.f1257d = z;
    }

    protected LayoutParams m2678b() {
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.h = 16;
        return layoutParams;
    }

    public LayoutParams m2671a(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    protected LayoutParams m2672a(android.view.ViewGroup.LayoutParams layoutParams) {
        if (layoutParams == null) {
            return m2678b();
        }
        LayoutParams layoutParams2 = layoutParams instanceof LayoutParams ? new LayoutParams((LayoutParams) layoutParams) : new LayoutParams(layoutParams);
        if (layoutParams2.h > 0) {
            return layoutParams2;
        }
        layoutParams2.h = 16;
        return layoutParams2;
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams != null && (layoutParams instanceof LayoutParams);
    }

    public LayoutParams m2681c() {
        LayoutParams b = m2678b();
        b.f1232a = true;
        return b;
    }

    public boolean m2677a(MenuItemImpl menuItemImpl) {
        return this.f1254a.m2426a((MenuItem) menuItemImpl, 0);
    }

    public int getWindowAnimations() {
        return 0;
    }

    public void m2673a(MenuBuilder menuBuilder) {
        this.f1254a = menuBuilder;
    }

    public Menu getMenu() {
        if (this.f1254a == null) {
            Context context = getContext();
            this.f1254a = new MenuBuilder(context);
            this.f1254a.m2418a(new MenuBuilderCallback());
            this.f1258e = new ActionMenuPresenter(context);
            this.f1258e.m2633c(true);
            this.f1258e.m2363a(this.f1259f != null ? this.f1259f : new ActionMenuPresenterCallback());
            this.f1254a.m2421a(this.f1258e, this.f1255b);
            this.f1258e.m2625a(this);
        }
        return this.f1254a;
    }

    public void m2674a(Callback callback, MenuBuilder.Callback callback2) {
        this.f1259f = callback;
        this.f1260g = callback2;
    }

    public MenuBuilder m2682d() {
        return this.f1254a;
    }

    public boolean m2683e() {
        return this.f1258e != null && this.f1258e.m2635d();
    }

    public boolean m2684f() {
        return this.f1258e != null && this.f1258e.m2636e();
    }

    public boolean m2685g() {
        return this.f1258e != null && this.f1258e.m2639h();
    }

    public boolean m2686h() {
        return this.f1258e != null && this.f1258e.m2640i();
    }

    public void m2687i() {
        if (this.f1258e != null) {
            this.f1258e.m2637f();
        }
    }

    protected boolean m2676a(int i) {
        boolean z = false;
        if (i == 0) {
            return false;
        }
        View childAt = getChildAt(i - 1);
        View childAt2 = getChildAt(i);
        if (i < getChildCount() && (childAt instanceof ActionMenuChildView)) {
            z = 0 | ((ActionMenuChildView) childAt).m2339d();
        }
        return (i <= 0 || !(childAt2 instanceof ActionMenuChildView)) ? z : ((ActionMenuChildView) childAt2).m2338c() | z;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    public void setExpandedActionViewsExclusive(boolean z) {
        this.f1258e.m2634d(z);
    }
}
