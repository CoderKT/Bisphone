package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v4.widget.ScrollerCompat;
import android.support.v7.appcompat.C0057R;
import android.support.v7.view.menu.MenuPresenter;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window.Callback;
import app.C0110R;
import se.emilsjolander.stickylistheaders.C1128R;

public class ActionBarOverlayLayout extends ViewGroup implements NestedScrollingParent, DecorContentParent {
    static final int[] f1167a;
    private final Runnable f1168A;
    private final NestedScrollingParentHelper f1169B;
    private int f1170b;
    private int f1171c;
    private ContentFrameLayout f1172d;
    private ActionBarContainer f1173e;
    private DecorToolbar f1174f;
    private Drawable f1175g;
    private boolean f1176h;
    private boolean f1177i;
    private boolean f1178j;
    private boolean f1179k;
    private boolean f1180l;
    private int f1181m;
    private int f1182n;
    private final Rect f1183o;
    private final Rect f1184p;
    private final Rect f1185q;
    private final Rect f1186r;
    private final Rect f1187s;
    private final Rect f1188t;
    private ActionBarVisibilityCallback f1189u;
    private final int f1190v;
    private ScrollerCompat f1191w;
    private ViewPropertyAnimatorCompat f1192x;
    private final ViewPropertyAnimatorListener f1193y;
    private final Runnable f1194z;

    public interface ActionBarVisibilityCallback {
        void m2197a(int i);

        void m2198f();

        void m2199g();

        void m2200g(boolean z);

        void m2201h();

        void m2202i();
    }

    /* renamed from: android.support.v7.widget.ActionBarOverlayLayout.1 */
    class C00621 extends ViewPropertyAnimatorListenerAdapter {
        final /* synthetic */ ActionBarOverlayLayout f1164a;

        C00621(ActionBarOverlayLayout actionBarOverlayLayout) {
            this.f1164a = actionBarOverlayLayout;
        }

        public void m2559b(View view) {
            this.f1164a.f1192x = null;
            this.f1164a.f1180l = false;
        }

        public void m2560c(View view) {
            this.f1164a.f1192x = null;
            this.f1164a.f1180l = false;
        }
    }

    /* renamed from: android.support.v7.widget.ActionBarOverlayLayout.2 */
    class C00632 implements Runnable {
        final /* synthetic */ ActionBarOverlayLayout f1165a;

        C00632(ActionBarOverlayLayout actionBarOverlayLayout) {
            this.f1165a = actionBarOverlayLayout;
        }

        public void run() {
            this.f1165a.m2579k();
            this.f1165a.f1192x = ViewCompat.m1185n(this.f1165a.f1173e).m1406c(0.0f).m1400a(this.f1165a.f1193y);
        }
    }

    /* renamed from: android.support.v7.widget.ActionBarOverlayLayout.3 */
    class C00643 implements Runnable {
        final /* synthetic */ ActionBarOverlayLayout f1166a;

        C00643(ActionBarOverlayLayout actionBarOverlayLayout) {
            this.f1166a = actionBarOverlayLayout;
        }

        public void run() {
            this.f1166a.m2579k();
            this.f1166a.f1192x = ViewCompat.m1185n(this.f1166a.f1173e).m1406c((float) (-this.f1166a.f1173e.getHeight())).m1400a(this.f1166a.f1193y);
        }
    }

    public class LayoutParams extends MarginLayoutParams {
        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    protected /* synthetic */ android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return m2588b();
    }

    public /* synthetic */ android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return m2584a(attributeSet);
    }

    static {
        f1167a = new int[]{C0057R.attr.actionBarSize, 16842841};
    }

    public ActionBarOverlayLayout(Context context) {
        this(context, null);
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1171c = 0;
        this.f1183o = new Rect();
        this.f1184p = new Rect();
        this.f1185q = new Rect();
        this.f1186r = new Rect();
        this.f1187s = new Rect();
        this.f1188t = new Rect();
        this.f1190v = 600;
        this.f1193y = new C00621(this);
        this.f1194z = new C00632(this);
        this.f1168A = new C00643(this);
        m2572a(context);
        this.f1169B = new NestedScrollingParentHelper(this);
    }

    private void m2572a(Context context) {
        boolean z = true;
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(f1167a);
        this.f1170b = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.f1175g = obtainStyledAttributes.getDrawable(1);
        setWillNotDraw(this.f1175g == null);
        obtainStyledAttributes.recycle();
        if (context.getApplicationInfo().targetSdkVersion >= 19) {
            z = false;
        }
        this.f1176h = z;
        this.f1191w = ScrollerCompat.m1880a(context);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m2579k();
    }

    public void setActionBarVisibilityCallback(ActionBarVisibilityCallback actionBarVisibilityCallback) {
        this.f1189u = actionBarVisibilityCallback;
        if (getWindowToken() != null) {
            this.f1189u.m2197a(this.f1171c);
            if (this.f1182n != 0) {
                onWindowSystemUiVisibilityChanged(this.f1182n);
                ViewCompat.m1187p(this);
            }
        }
    }

    public void setOverlayMode(boolean z) {
        this.f1177i = z;
        boolean z2 = z && getContext().getApplicationInfo().targetSdkVersion < 19;
        this.f1176h = z2;
    }

    public boolean m2587a() {
        return this.f1177i;
    }

    public void setHasNonEmbeddedTabs(boolean z) {
        this.f1178j = z;
    }

    public void setShowingForActionMode(boolean z) {
    }

    protected void onConfigurationChanged(Configuration configuration) {
        if (VERSION.SDK_INT >= 8) {
            super.onConfigurationChanged(configuration);
        }
        m2572a(getContext());
        ViewCompat.m1187p(this);
    }

    public void onWindowSystemUiVisibilityChanged(int i) {
        boolean z;
        boolean z2 = true;
        if (VERSION.SDK_INT >= 16) {
            super.onWindowSystemUiVisibilityChanged(i);
        }
        m2589c();
        int i2 = this.f1182n ^ i;
        this.f1182n = i;
        boolean z3 = (i & 4) == 0;
        if ((i & 256) != 0) {
            z = true;
        } else {
            z = false;
        }
        if (this.f1189u != null) {
            ActionBarVisibilityCallback actionBarVisibilityCallback = this.f1189u;
            if (z) {
                z2 = false;
            }
            actionBarVisibilityCallback.m2200g(z2);
            if (z3 || !z) {
                this.f1189u.m2198f();
            } else {
                this.f1189u.m2199g();
            }
        }
        if ((i2 & 256) != 0 && this.f1189u != null) {
            ViewCompat.m1187p(this);
        }
    }

    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.f1171c = i;
        if (this.f1189u != null) {
            this.f1189u.m2197a(i);
        }
    }

    private boolean m2576a(View view, Rect rect, boolean z, boolean z2, boolean z3, boolean z4) {
        boolean z5 = false;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (z && layoutParams.leftMargin != rect.left) {
            layoutParams.leftMargin = rect.left;
            z5 = true;
        }
        if (z2 && layoutParams.topMargin != rect.top) {
            layoutParams.topMargin = rect.top;
            z5 = true;
        }
        if (z4 && layoutParams.rightMargin != rect.right) {
            layoutParams.rightMargin = rect.right;
            z5 = true;
        }
        if (!z3 || layoutParams.bottomMargin == rect.bottom) {
            return z5;
        }
        layoutParams.bottomMargin = rect.bottom;
        return true;
    }

    protected boolean fitSystemWindows(Rect rect) {
        boolean a;
        m2589c();
        if ((ViewCompat.m1186o(this) & 256) != 0) {
            a = m2576a(this.f1173e, rect, true, true, false, true);
            this.f1186r.set(rect);
            ViewUtils.m3900a(this, this.f1186r, this.f1183o);
        } else {
            a = m2576a(this.f1173e, rect, true, true, false, true);
            this.f1186r.set(rect);
            ViewUtils.m3900a(this, this.f1186r, this.f1183o);
        }
        if (!this.f1184p.equals(this.f1183o)) {
            this.f1184p.set(this.f1183o);
            a = true;
        }
        if (a) {
            requestLayout();
        }
        return true;
    }

    protected LayoutParams m2588b() {
        return new LayoutParams(-1, -1);
    }

    public LayoutParams m2584a(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    protected void onMeasure(int i, int i2) {
        Object obj;
        int i3;
        m2589c();
        measureChildWithMargins(this.f1173e, i, 0, i2, 0);
        LayoutParams layoutParams = (LayoutParams) this.f1173e.getLayoutParams();
        int max = Math.max(0, (this.f1173e.getMeasuredWidth() + layoutParams.leftMargin) + layoutParams.rightMargin);
        int max2 = Math.max(0, layoutParams.bottomMargin + (this.f1173e.getMeasuredHeight() + layoutParams.topMargin));
        int a = ViewUtils.m3899a(0, ViewCompat.m1180i(this.f1173e));
        if ((ViewCompat.m1186o(this) & 256) != 0) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            i3 = this.f1170b;
            if (this.f1178j && this.f1173e.getTabContainer() != null) {
                i3 += this.f1170b;
            }
        } else {
            i3 = this.f1173e.getVisibility() != 8 ? this.f1173e.getMeasuredHeight() : 0;
        }
        this.f1185q.set(this.f1183o);
        this.f1187s.set(this.f1186r);
        Rect rect;
        Rect rect2;
        if (this.f1177i || obj != null) {
            rect = this.f1187s;
            rect.top = i3 + rect.top;
            rect2 = this.f1187s;
            rect2.bottom += 0;
        } else {
            rect = this.f1185q;
            rect.top = i3 + rect.top;
            rect2 = this.f1185q;
            rect2.bottom += 0;
        }
        m2576a(this.f1172d, this.f1185q, true, true, true, true);
        if (!this.f1188t.equals(this.f1187s)) {
            this.f1188t.set(this.f1187s);
            this.f1172d.m2128a(this.f1187s);
        }
        measureChildWithMargins(this.f1172d, i, 0, i2, 0);
        layoutParams = (LayoutParams) this.f1172d.getLayoutParams();
        int max3 = Math.max(max, (this.f1172d.getMeasuredWidth() + layoutParams.leftMargin) + layoutParams.rightMargin);
        i3 = Math.max(max2, layoutParams.bottomMargin + (this.f1172d.getMeasuredHeight() + layoutParams.topMargin));
        int a2 = ViewUtils.m3899a(a, ViewCompat.m1180i(this.f1172d));
        setMeasuredDimension(ViewCompat.m1153a(Math.max(max3 + (getPaddingLeft() + getPaddingRight()), getSuggestedMinimumWidth()), i, a2), ViewCompat.m1153a(Math.max(i3 + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight()), i2, a2 << 16));
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingRight = (i3 - i) - getPaddingRight();
        int paddingTop = getPaddingTop();
        paddingRight = (i4 - i2) - getPaddingBottom();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int i6 = layoutParams.leftMargin + paddingLeft;
                paddingRight = layoutParams.topMargin + paddingTop;
                childAt.layout(i6, paddingRight, childAt.getMeasuredWidth() + i6, childAt.getMeasuredHeight() + paddingRight);
            }
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.f1175g != null && !this.f1176h) {
            int bottom = this.f1173e.getVisibility() == 0 ? (int) ((((float) this.f1173e.getBottom()) + ViewCompat.m1182k(this.f1173e)) + 0.5f) : 0;
            this.f1175g.setBounds(0, bottom, getWidth(), this.f1175g.getIntrinsicHeight() + bottom);
            this.f1175g.draw(canvas);
        }
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public boolean onStartNestedScroll(View view, View view2, int i) {
        if ((i & 2) == 0 || this.f1173e.getVisibility() != 0) {
            return false;
        }
        return this.f1179k;
    }

    public void onNestedScrollAccepted(View view, View view2, int i) {
        this.f1169B.m1012a(view, view2, i);
        this.f1181m = getActionBarHideOffset();
        m2579k();
        if (this.f1189u != null) {
            this.f1189u.m2201h();
        }
    }

    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        this.f1181m += i2;
        setActionBarHideOffset(this.f1181m);
    }

    public void onStopNestedScroll(View view) {
        if (this.f1179k && !this.f1180l) {
            if (this.f1181m <= this.f1173e.getHeight()) {
                m2580l();
            } else {
                m2581m();
            }
        }
        if (this.f1189u != null) {
            this.f1189u.m2202i();
        }
    }

    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        if (!this.f1179k || !z) {
            return false;
        }
        if (m2574a(f, f2)) {
            m2583o();
        } else {
            m2582n();
        }
        this.f1180l = true;
        return true;
    }

    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
    }

    public boolean onNestedPreFling(View view, float f, float f2) {
        return false;
    }

    public int getNestedScrollAxes() {
        return this.f1169B.m1010a();
    }

    void m2589c() {
        if (this.f1172d == null) {
            this.f1172d = (ContentFrameLayout) findViewById(C0057R.id.action_bar_activity_content);
            this.f1173e = (ActionBarContainer) findViewById(C0057R.id.action_bar_container);
            this.f1174f = m2571a(findViewById(C0057R.id.action_bar));
        }
    }

    private DecorToolbar m2571a(View view) {
        if (view instanceof DecorToolbar) {
            return (DecorToolbar) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        throw new IllegalStateException("Can't make a decor toolbar out of " + view.getClass().getSimpleName());
    }

    public void setHideOnContentScrollEnabled(boolean z) {
        if (z != this.f1179k) {
            this.f1179k = z;
            if (!z) {
                m2579k();
                setActionBarHideOffset(0);
            }
        }
    }

    public int getActionBarHideOffset() {
        return this.f1173e != null ? -((int) ViewCompat.m1182k(this.f1173e)) : 0;
    }

    public void setActionBarHideOffset(int i) {
        m2579k();
        ViewCompat.m1167b(this.f1173e, (float) (-Math.max(0, Math.min(i, this.f1173e.getHeight()))));
    }

    private void m2579k() {
        removeCallbacks(this.f1194z);
        removeCallbacks(this.f1168A);
        if (this.f1192x != null) {
            this.f1192x.m1405b();
        }
    }

    private void m2580l() {
        m2579k();
        postDelayed(this.f1194z, 600);
    }

    private void m2581m() {
        m2579k();
        postDelayed(this.f1168A, 600);
    }

    private void m2582n() {
        m2579k();
        this.f1194z.run();
    }

    private void m2583o() {
        m2579k();
        this.f1168A.run();
    }

    private boolean m2574a(float f, float f2) {
        this.f1191w.m1884a(0, 0, 0, (int) f2, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (this.f1191w.m1891e() > this.f1173e.getHeight()) {
            return true;
        }
        return false;
    }

    public void setWindowCallback(Callback callback) {
        m2589c();
        this.f1174f.m2902a(callback);
    }

    public void setWindowTitle(CharSequence charSequence) {
        m2589c();
        this.f1174f.m2903a(charSequence);
    }

    public CharSequence getTitle() {
        m2589c();
        return this.f1174f.m2911e();
    }

    public void m2585a(int i) {
        m2589c();
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                this.f1174f.m2912f();
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                this.f1174f.m2913g();
            case C0110R.styleable.Theme_switchStyle /*109*/:
                setOverlayMode(true);
            default:
        }
    }

    public void setUiOptions(int i) {
    }

    public void setIcon(int i) {
        m2589c();
        this.f1174f.m2897a(i);
    }

    public void setIcon(Drawable drawable) {
        m2589c();
        this.f1174f.m2898a(drawable);
    }

    public void setLogo(int i) {
        m2589c();
        this.f1174f.m2906b(i);
    }

    public boolean m2590d() {
        m2589c();
        return this.f1174f.m2914h();
    }

    public boolean m2591e() {
        m2589c();
        return this.f1174f.m2915i();
    }

    public boolean m2592f() {
        m2589c();
        return this.f1174f.m2916j();
    }

    public boolean m2593g() {
        m2589c();
        return this.f1174f.m2917k();
    }

    public boolean m2594h() {
        m2589c();
        return this.f1174f.m2918l();
    }

    public void m2595i() {
        m2589c();
        this.f1174f.m2919m();
    }

    public void m2586a(Menu menu, MenuPresenter.Callback callback) {
        m2589c();
        this.f1174f.m2901a(menu, callback);
    }

    public void m2596j() {
        m2589c();
        this.f1174f.m2920n();
    }
}
