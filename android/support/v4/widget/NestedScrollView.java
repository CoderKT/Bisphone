package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import app.C0110R;
import java.util.List;
import se.emilsjolander.stickylistheaders.C1128R;

public class NestedScrollView extends FrameLayout implements NestedScrollingChild, NestedScrollingParent, ScrollingView {
    private static final AccessibilityDelegate f605v;
    private static final int[] f606w;
    private OnScrollChangeListener f607A;
    private long f608a;
    private final Rect f609b;
    private ScrollerCompat f610c;
    private EdgeEffectCompat f611d;
    private EdgeEffectCompat f612e;
    private int f613f;
    private boolean f614g;
    private boolean f615h;
    private View f616i;
    private boolean f617j;
    private VelocityTracker f618k;
    private boolean f619l;
    private boolean f620m;
    private int f621n;
    private int f622o;
    private int f623p;
    private int f624q;
    private final int[] f625r;
    private final int[] f626s;
    private int f627t;
    private SavedState f628u;
    private final NestedScrollingParentHelper f629x;
    private final NestedScrollingChildHelper f630y;
    private float f631z;

    class AccessibilityDelegate extends AccessibilityDelegateCompat {
        AccessibilityDelegate() {
        }

        public boolean m1782a(View view, int i, Bundle bundle) {
            if (super.m866a(view, i, bundle)) {
                return true;
            }
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            if (!nestedScrollView.isEnabled()) {
                return false;
            }
            int min;
            switch (i) {
                case 4096:
                    min = Math.min(((nestedScrollView.getHeight() - nestedScrollView.getPaddingBottom()) - nestedScrollView.getPaddingTop()) + nestedScrollView.getScrollY(), nestedScrollView.getScrollRange());
                    if (min == nestedScrollView.getScrollY()) {
                        return false;
                    }
                    nestedScrollView.m1812b(0, min);
                    return true;
                case 8192:
                    min = Math.max(nestedScrollView.getScrollY() - ((nestedScrollView.getHeight() - nestedScrollView.getPaddingBottom()) - nestedScrollView.getPaddingTop()), 0);
                    if (min == nestedScrollView.getScrollY()) {
                        return false;
                    }
                    nestedScrollView.m1812b(0, min);
                    return true;
                default:
                    return false;
            }
        }

        public void m1781a(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.m864a(view, accessibilityNodeInfoCompat);
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            accessibilityNodeInfoCompat.m1531a(ScrollView.class.getName());
            if (nestedScrollView.isEnabled()) {
                int a = nestedScrollView.getScrollRange();
                if (a > 0) {
                    accessibilityNodeInfoCompat.m1533a(true);
                    if (nestedScrollView.getScrollY() > 0) {
                        accessibilityNodeInfoCompat.m1529a(8192);
                    }
                    if (nestedScrollView.getScrollY() < a) {
                        accessibilityNodeInfoCompat.m1529a(4096);
                    }
                }
            }
        }

        public void m1783d(View view, AccessibilityEvent accessibilityEvent) {
            super.m870d(view, accessibilityEvent);
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            accessibilityEvent.setClassName(ScrollView.class.getName());
            AccessibilityRecordCompat a = AccessibilityEventCompat.m1441a(accessibilityEvent);
            a.m1637a(nestedScrollView.getScrollRange() > 0);
            a.m1640d(nestedScrollView.getScrollX());
            a.m1641e(nestedScrollView.getScrollY());
            a.m1642f(nestedScrollView.getScrollX());
            a.m1643g(nestedScrollView.getScrollRange());
        }
    }

    public interface OnScrollChangeListener {
        void m1784a(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4);
    }

    class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        public int f604a;

        /* renamed from: android.support.v4.widget.NestedScrollView.SavedState.1 */
        final class C00351 implements Creator<SavedState> {
            C00351() {
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m1785a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m1786a(i);
            }

            public SavedState m1785a(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] m1786a(int i) {
                return new SavedState[i];
            }
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.f604a = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f604a);
        }

        public String toString() {
            return "HorizontalScrollView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " scrollPosition=" + this.f604a + "}";
        }

        static {
            CREATOR = new C00351();
        }
    }

    static {
        f605v = new AccessibilityDelegate();
        f606w = new int[]{16843130};
    }

    public NestedScrollView(Context context) {
        this(context, null);
    }

    public NestedScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NestedScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f609b = new Rect();
        this.f614g = true;
        this.f615h = false;
        this.f616i = null;
        this.f617j = false;
        this.f620m = true;
        this.f624q = -1;
        this.f625r = new int[2];
        this.f626s = new int[2];
        m1789a();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f606w, i, 0);
        setFillViewport(obtainStyledAttributes.getBoolean(0, false));
        obtainStyledAttributes.recycle();
        this.f629x = new NestedScrollingParentHelper(this);
        this.f630y = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
        ViewCompat.m1161a((View) this, f605v);
    }

    public void setNestedScrollingEnabled(boolean z) {
        this.f630y.m1001a(z);
    }

    public boolean isNestedScrollingEnabled() {
        return this.f630y.m1002a();
    }

    public boolean startNestedScroll(int i) {
        return this.f630y.m1005a(i);
    }

    public void stopNestedScroll() {
        this.f630y.m1009c();
    }

    public boolean hasNestedScrollingParent() {
        return this.f630y.m1008b();
    }

    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.f630y.m1006a(i, i2, i3, i4, iArr);
    }

    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return this.f630y.m1007a(i, i2, iArr, iArr2);
    }

    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.f630y.m1004a(f, f2, z);
    }

    public boolean dispatchNestedPreFling(float f, float f2) {
        return this.f630y.m1003a(f, f2);
    }

    public boolean onStartNestedScroll(View view, View view2, int i) {
        return (i & 2) != 0;
    }

    public void onNestedScrollAccepted(View view, View view2, int i) {
        this.f629x.m1012a(view, view2, i);
        startNestedScroll(2);
    }

    public void onStopNestedScroll(View view) {
        this.f629x.m1011a(view);
        stopNestedScroll();
    }

    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        int scrollY = getScrollY();
        scrollBy(0, i4);
        int scrollY2 = getScrollY() - scrollY;
        dispatchNestedScroll(0, scrollY2, 0, i4 - scrollY2, null);
    }

    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
    }

    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        if (z) {
            return false;
        }
        m1805f((int) f2);
        return true;
    }

    public boolean onNestedPreFling(View view, float f, float f2) {
        return false;
    }

    public int getNestedScrollAxes() {
        return this.f629x.m1010a();
    }

    public boolean shouldDelayChildPressedState() {
        return true;
    }

    protected float getTopFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int scrollY = getScrollY();
        if (scrollY < verticalFadingEdgeLength) {
            return ((float) scrollY) / ((float) verticalFadingEdgeLength);
        }
        return 1.0f;
    }

    protected float getBottomFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int bottom = (getChildAt(0).getBottom() - getScrollY()) - (getHeight() - getPaddingBottom());
        if (bottom < verticalFadingEdgeLength) {
            return ((float) bottom) / ((float) verticalFadingEdgeLength);
        }
        return 1.0f;
    }

    public int getMaxScrollAmount() {
        return (int) (0.5f * ((float) getHeight()));
    }

    private void m1789a() {
        this.f610c = new ScrollerCompat(getContext(), null);
        setFocusable(true);
        setDescendantFocusability(262144);
        setWillNotDraw(false);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.f621n = viewConfiguration.getScaledTouchSlop();
        this.f622o = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f623p = viewConfiguration.getScaledMaximumFlingVelocity();
    }

    public void addView(View view) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view);
    }

    public void addView(View view, int i) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view, i);
    }

    public void addView(View view, LayoutParams layoutParams) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view, layoutParams);
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view, i, layoutParams);
    }

    public void setOnScrollChangeListener(OnScrollChangeListener onScrollChangeListener) {
        this.f607A = onScrollChangeListener;
    }

    private boolean m1798b() {
        View childAt = getChildAt(0);
        if (childAt == null) {
            return false;
        }
        if (getHeight() < (childAt.getHeight() + getPaddingTop()) + getPaddingBottom()) {
            return true;
        }
        return false;
    }

    public void setFillViewport(boolean z) {
        if (z != this.f619l) {
            this.f619l = z;
            requestLayout();
        }
    }

    public void setSmoothScrollingEnabled(boolean z) {
        this.f620m = z;
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        if (this.f607A != null) {
            this.f607A.m1784a(this, i, i2, i3, i4);
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.f619l && MeasureSpec.getMode(i2) != 0 && getChildCount() > 0) {
            View childAt = getChildAt(0);
            int measuredHeight = getMeasuredHeight();
            if (childAt.getMeasuredHeight() < measuredHeight) {
                childAt.measure(getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight(), ((FrameLayout.LayoutParams) childAt.getLayoutParams()).width), MeasureSpec.makeMeasureSpec((measuredHeight - getPaddingTop()) - getPaddingBottom(), 1073741824));
            }
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || m1811a(keyEvent);
    }

    public boolean m1811a(KeyEvent keyEvent) {
        int i = 33;
        this.f609b.setEmpty();
        if (m1798b()) {
            if (keyEvent.getAction() != 0) {
                return false;
            }
            switch (keyEvent.getKeyCode()) {
                case C1128R.styleable.StickyListHeadersListView_android_overScrollMode /*19*/:
                    if (keyEvent.isAltPressed()) {
                        return m1813b(33);
                    }
                    return m1814c(33);
                case C1128R.styleable.StickyListHeadersListView_android_fastScrollAlwaysVisible /*20*/:
                    if (keyEvent.isAltPressed()) {
                        return m1813b(130);
                    }
                    return m1814c(130);
                case C0110R.styleable.Theme_editTextColor /*62*/:
                    if (!keyEvent.isShiftPressed()) {
                        i = 130;
                    }
                    m1809a(i);
                    return false;
                default:
                    return false;
            }
        } else if (!isFocused() || keyEvent.getKeyCode() == 4) {
            return false;
        } else {
            boolean z;
            View findFocus = findFocus();
            if (findFocus == this) {
                findFocus = null;
            }
            findFocus = FocusFinder.getInstance().findNextFocus(this, findFocus, 130);
            if (findFocus == null || findFocus == this || !findFocus.requestFocus(130)) {
                z = false;
            } else {
                z = true;
            }
            return z;
        }
    }

    private boolean m1800c(int i, int i2) {
        if (getChildCount() <= 0) {
            return false;
        }
        int scrollY = getScrollY();
        View childAt = getChildAt(0);
        if (i2 < childAt.getTop() - scrollY || i2 >= childAt.getBottom() - scrollY || i < childAt.getLeft() || i >= childAt.getRight()) {
            return false;
        }
        return true;
    }

    private void m1799c() {
        if (this.f618k == null) {
            this.f618k = VelocityTracker.obtain();
        } else {
            this.f618k.clear();
        }
    }

    private void m1801d() {
        if (this.f618k == null) {
            this.f618k = VelocityTracker.obtain();
        }
    }

    private void m1802e() {
        if (this.f618k != null) {
            this.f618k.recycle();
            this.f618k = null;
        }
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (z) {
            m1802e();
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        int action = motionEvent.getAction();
        if (action == 2 && this.f617j) {
            return true;
        }
        switch (action & 255) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                action = (int) motionEvent.getY();
                if (!m1800c((int) motionEvent.getX(), action)) {
                    this.f617j = false;
                    m1802e();
                    break;
                }
                this.f613f = action;
                this.f624q = MotionEventCompat.m990b(motionEvent, 0);
                m1799c();
                this.f618k.addMovement(motionEvent);
                if (!this.f610c.m1886a()) {
                    z = true;
                }
                this.f617j = z;
                startNestedScroll(2);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                this.f617j = false;
                this.f624q = -1;
                m1802e();
                if (this.f610c.m1887a(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                    ViewCompat.m1174d(this);
                }
                stopNestedScroll();
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                action = this.f624q;
                if (action != -1) {
                    int a = MotionEventCompat.m988a(motionEvent, action);
                    if (a != -1) {
                        action = (int) MotionEventCompat.m993d(motionEvent, a);
                        if (Math.abs(action - this.f613f) > this.f621n && (getNestedScrollAxes() & 2) == 0) {
                            this.f617j = true;
                            this.f613f = action;
                            m1801d();
                            this.f618k.addMovement(motionEvent);
                            this.f627t = 0;
                            ViewParent parent = getParent();
                            if (parent != null) {
                                parent.requestDisallowInterceptTouchEvent(true);
                                break;
                            }
                        }
                    }
                    Log.e("NestedScrollView", "Invalid pointerId=" + action + " in onInterceptTouchEvent");
                    break;
                }
                break;
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                m1790a(motionEvent);
                break;
        }
        return this.f617j;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        m1801d();
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        int a = MotionEventCompat.m987a(motionEvent);
        if (a == 0) {
            this.f627t = 0;
        }
        obtain.offsetLocation(0.0f, (float) this.f627t);
        switch (a) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                if (getChildCount() != 0) {
                    boolean z = !this.f610c.m1886a();
                    this.f617j = z;
                    if (z) {
                        ViewParent parent = getParent();
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
                    }
                    if (!this.f610c.m1886a()) {
                        this.f610c.m1894h();
                    }
                    this.f613f = (int) motionEvent.getY();
                    this.f624q = MotionEventCompat.m990b(motionEvent, 0);
                    startNestedScroll(2);
                    break;
                }
                return false;
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                if (this.f617j) {
                    VelocityTracker velocityTracker = this.f618k;
                    velocityTracker.computeCurrentVelocity(1000, (float) this.f623p);
                    a = (int) VelocityTrackerCompat.m1021b(velocityTracker, this.f624q);
                    if (Math.abs(a) > this.f622o) {
                        m1805f(-a);
                    } else if (this.f610c.m1887a(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                        ViewCompat.m1174d(this);
                    }
                }
                this.f624q = -1;
                m1804f();
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                int a2 = MotionEventCompat.m988a(motionEvent, this.f624q);
                if (a2 != -1) {
                    int i;
                    int d = (int) MotionEventCompat.m993d(motionEvent, a2);
                    a = this.f613f - d;
                    if (dispatchNestedPreScroll(0, a, this.f626s, this.f625r)) {
                        a -= this.f626s[1];
                        obtain.offsetLocation(0.0f, (float) this.f625r[1]);
                        this.f627t += this.f625r[1];
                    }
                    if (this.f617j || Math.abs(a) <= this.f621n) {
                        i = a;
                    } else {
                        ViewParent parent2 = getParent();
                        if (parent2 != null) {
                            parent2.requestDisallowInterceptTouchEvent(true);
                        }
                        this.f617j = true;
                        if (a > 0) {
                            i = a - this.f621n;
                        } else {
                            i = a + this.f621n;
                        }
                    }
                    if (this.f617j) {
                        Object obj;
                        this.f613f = d - this.f625r[1];
                        int scrollY = getScrollY();
                        int scrollRange = getScrollRange();
                        a = ViewCompat.m1154a(this);
                        if (a == 0 || (a == 1 && scrollRange > 0)) {
                            obj = 1;
                        } else {
                            obj = null;
                        }
                        if (m1810a(0, i, 0, getScrollY(), 0, scrollRange, 0, 0, true) && !hasNestedScrollingParent()) {
                            this.f618k.clear();
                        }
                        int scrollY2 = getScrollY() - scrollY;
                        if (!dispatchNestedScroll(0, scrollY2, 0, i - scrollY2, this.f625r)) {
                            if (obj != null) {
                                m1806g();
                                a = scrollY + i;
                                if (a < 0) {
                                    this.f611d.m1764a(((float) i) / ((float) getHeight()), MotionEventCompat.m991c(motionEvent, a2) / ((float) getWidth()));
                                    if (!this.f612e.m1762a()) {
                                        this.f612e.m1768c();
                                    }
                                } else if (a > scrollRange) {
                                    this.f612e.m1764a(((float) i) / ((float) getHeight()), 1.0f - (MotionEventCompat.m991c(motionEvent, a2) / ((float) getWidth())));
                                    if (!this.f611d.m1762a()) {
                                        this.f611d.m1768c();
                                    }
                                }
                                if (!(this.f611d == null || (this.f611d.m1762a() && this.f612e.m1762a()))) {
                                    ViewCompat.m1174d(this);
                                    break;
                                }
                            }
                        }
                        this.f613f -= this.f625r[1];
                        obtain.offsetLocation(0.0f, (float) this.f625r[1]);
                        this.f627t += this.f625r[1];
                        break;
                    }
                }
                Log.e("NestedScrollView", "Invalid pointerId=" + this.f624q + " in onTouchEvent");
                break;
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                if (this.f617j && getChildCount() > 0 && this.f610c.m1887a(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                    ViewCompat.m1174d(this);
                }
                this.f624q = -1;
                m1804f();
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                a = MotionEventCompat.m989b(motionEvent);
                this.f613f = (int) MotionEventCompat.m993d(motionEvent, a);
                this.f624q = MotionEventCompat.m990b(motionEvent, a);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                m1790a(motionEvent);
                this.f613f = (int) MotionEventCompat.m993d(motionEvent, MotionEventCompat.m988a(motionEvent, this.f624q));
                break;
        }
        if (this.f618k != null) {
            this.f618k.addMovement(obtain);
        }
        obtain.recycle();
        return true;
    }

    private void m1790a(MotionEvent motionEvent) {
        int action = (motionEvent.getAction() & 65280) >> 8;
        if (MotionEventCompat.m990b(motionEvent, action) == this.f624q) {
            action = action == 0 ? 1 : 0;
            this.f613f = (int) MotionEventCompat.m993d(motionEvent, action);
            this.f624q = MotionEventCompat.m990b(motionEvent, action);
            if (this.f618k != null) {
                this.f618k.clear();
            }
        }
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if ((MotionEventCompat.m992c(motionEvent) & 2) == 0) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                if (this.f617j) {
                    return false;
                }
                float e = MotionEventCompat.m994e(motionEvent, 9);
                if (e == 0.0f) {
                    return false;
                }
                int verticalScrollFactorCompat = (int) (e * getVerticalScrollFactorCompat());
                int scrollRange = getScrollRange();
                int scrollY = getScrollY();
                verticalScrollFactorCompat = scrollY - verticalScrollFactorCompat;
                if (verticalScrollFactorCompat < 0) {
                    scrollRange = 0;
                } else if (verticalScrollFactorCompat <= scrollRange) {
                    scrollRange = verticalScrollFactorCompat;
                }
                if (scrollRange == scrollY) {
                    return false;
                }
                super.scrollTo(getScrollX(), scrollRange);
                return true;
            default:
                return false;
        }
    }

    private float getVerticalScrollFactorCompat() {
        if (this.f631z == 0.0f) {
            TypedValue typedValue = new TypedValue();
            Context context = getContext();
            if (context.getTheme().resolveAttribute(16842829, typedValue, true)) {
                this.f631z = typedValue.getDimension(context.getResources().getDisplayMetrics());
            } else {
                throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
            }
        }
        return this.f631z;
    }

    protected void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        super.scrollTo(i, i2);
    }

    boolean m1810a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
        boolean z2;
        boolean z3;
        int a = ViewCompat.m1154a(this);
        Object obj = computeHorizontalScrollRange() > computeHorizontalScrollExtent() ? 1 : null;
        Object obj2 = computeVerticalScrollRange() > computeVerticalScrollExtent() ? 1 : null;
        Object obj3 = (a == 0 || (a == 1 && obj != null)) ? 1 : null;
        obj = (a == 0 || (a == 1 && obj2 != null)) ? 1 : null;
        int i9 = i3 + i;
        if (obj3 == null) {
            i7 = 0;
        }
        int i10 = i4 + i2;
        if (obj == null) {
            i8 = 0;
        }
        int i11 = -i7;
        int i12 = i7 + i5;
        a = -i8;
        int i13 = i8 + i6;
        if (i9 > i12) {
            z2 = true;
        } else if (i9 < i11) {
            z2 = true;
            i12 = i11;
        } else {
            z2 = false;
            i12 = i9;
        }
        if (i10 > i13) {
            z3 = true;
        } else if (i10 < a) {
            z3 = true;
            i13 = a;
        } else {
            z3 = false;
            i13 = i10;
        }
        if (z3) {
            this.f610c.m1887a(i12, i13, 0, 0, 0, getScrollRange());
        }
        onOverScrolled(i12, i13, z2, z3);
        if (z2 || z3) {
            return true;
        }
        return false;
    }

    private int getScrollRange() {
        if (getChildCount() > 0) {
            return Math.max(0, getChildAt(0).getHeight() - ((getHeight() - getPaddingBottom()) - getPaddingTop()));
        }
        return 0;
    }

    private View m1788a(boolean z, int i, int i2) {
        List focusables = getFocusables(2);
        View view = null;
        Object obj = null;
        int size = focusables.size();
        int i3 = 0;
        while (i3 < size) {
            View view2;
            Object obj2;
            View view3 = (View) focusables.get(i3);
            int top = view3.getTop();
            int bottom = view3.getBottom();
            if (i < bottom && top < i2) {
                Object obj3 = (i >= top || bottom >= i2) ? null : 1;
                if (view == null) {
                    Object obj4 = obj3;
                    view2 = view3;
                    obj2 = obj4;
                } else {
                    Object obj5 = ((!z || top >= view.getTop()) && (z || bottom <= view.getBottom())) ? null : 1;
                    if (obj != null) {
                        if (!(obj3 == null || obj5 == null)) {
                            view2 = view3;
                            obj2 = obj;
                        }
                    } else if (obj3 != null) {
                        view2 = view3;
                        int i4 = 1;
                    } else if (obj5 != null) {
                        view2 = view3;
                        obj2 = obj;
                    }
                }
                i3++;
                view = view2;
                obj = obj2;
            }
            obj2 = obj;
            view2 = view;
            i3++;
            view = view2;
            obj = obj2;
        }
        return view;
    }

    public boolean m1809a(int i) {
        int i2 = i == 130 ? 1 : 0;
        int height = getHeight();
        if (i2 != 0) {
            this.f609b.top = getScrollY() + height;
            i2 = getChildCount();
            if (i2 > 0) {
                View childAt = getChildAt(i2 - 1);
                if (this.f609b.top + height > childAt.getBottom()) {
                    this.f609b.top = childAt.getBottom() - height;
                }
            }
        } else {
            this.f609b.top = getScrollY() - height;
            if (this.f609b.top < 0) {
                this.f609b.top = 0;
            }
        }
        this.f609b.bottom = this.f609b.top + height;
        return m1791a(i, this.f609b.top, this.f609b.bottom);
    }

    public boolean m1813b(int i) {
        int i2 = i == 130 ? 1 : 0;
        int height = getHeight();
        this.f609b.top = 0;
        this.f609b.bottom = height;
        if (i2 != 0) {
            i2 = getChildCount();
            if (i2 > 0) {
                this.f609b.bottom = getChildAt(i2 - 1).getBottom() + getPaddingBottom();
                this.f609b.top = this.f609b.bottom - height;
            }
        }
        return m1791a(i, this.f609b.top, this.f609b.bottom);
    }

    private boolean m1791a(int i, int i2, int i3) {
        boolean z = false;
        int height = getHeight();
        int scrollY = getScrollY();
        int i4 = scrollY + height;
        boolean z2 = i == 33;
        View a = m1788a(z2, i2, i3);
        if (a == null) {
            a = this;
        }
        if (i2 < scrollY || i3 > i4) {
            m1803e(z2 ? i2 - scrollY : i3 - i4);
            z = true;
        }
        if (a != findFocus()) {
            a.requestFocus(i);
        }
        return z;
    }

    public boolean m1814c(int i) {
        View findFocus = findFocus();
        if (findFocus == this) {
            findFocus = null;
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, findFocus, i);
        int maxScrollAmount = getMaxScrollAmount();
        if (findNextFocus == null || !m1794a(findNextFocus, maxScrollAmount, getHeight())) {
            if (i == 33 && getScrollY() < maxScrollAmount) {
                maxScrollAmount = getScrollY();
            } else if (i == 130 && getChildCount() > 0) {
                int bottom = getChildAt(0).getBottom();
                int scrollY = (getScrollY() + getHeight()) - getPaddingBottom();
                if (bottom - scrollY < maxScrollAmount) {
                    maxScrollAmount = bottom - scrollY;
                }
            }
            if (maxScrollAmount == 0) {
                return false;
            }
            if (i != 130) {
                maxScrollAmount = -maxScrollAmount;
            }
            m1803e(maxScrollAmount);
        } else {
            findNextFocus.getDrawingRect(this.f609b);
            offsetDescendantRectToMyCoords(findNextFocus, this.f609b);
            m1803e(m1807a(this.f609b));
            findNextFocus.requestFocus(i);
        }
        if (findFocus != null && findFocus.isFocused() && m1793a(findFocus)) {
            int descendantFocusability = getDescendantFocusability();
            setDescendantFocusability(131072);
            requestFocus();
            setDescendantFocusability(descendantFocusability);
        }
        return true;
    }

    private boolean m1793a(View view) {
        return !m1794a(view, 0, getHeight());
    }

    private boolean m1794a(View view, int i, int i2) {
        view.getDrawingRect(this.f609b);
        offsetDescendantRectToMyCoords(view, this.f609b);
        return this.f609b.bottom + i >= getScrollY() && this.f609b.top - i <= getScrollY() + i2;
    }

    private void m1803e(int i) {
        if (i == 0) {
            return;
        }
        if (this.f620m) {
            m1808a(0, i);
        } else {
            scrollBy(0, i);
        }
    }

    public final void m1808a(int i, int i2) {
        if (getChildCount() != 0) {
            if (AnimationUtils.currentAnimationTimeMillis() - this.f608a > 250) {
                int max = Math.max(0, getChildAt(0).getHeight() - ((getHeight() - getPaddingBottom()) - getPaddingTop()));
                int scrollY = getScrollY();
                this.f610c.m1882a(getScrollX(), scrollY, 0, Math.max(0, Math.min(scrollY + i2, max)) - scrollY);
                ViewCompat.m1174d(this);
            } else {
                if (!this.f610c.m1886a()) {
                    this.f610c.m1894h();
                }
                scrollBy(i, i2);
            }
            this.f608a = AnimationUtils.currentAnimationTimeMillis();
        }
    }

    public final void m1812b(int i, int i2) {
        m1808a(i - getScrollX(), i2 - getScrollY());
    }

    public int computeVerticalScrollRange() {
        int height = (getHeight() - getPaddingBottom()) - getPaddingTop();
        if (getChildCount() == 0) {
            return height;
        }
        int bottom = getChildAt(0).getBottom();
        int scrollY = getScrollY();
        height = Math.max(0, bottom - height);
        if (scrollY < 0) {
            return bottom - scrollY;
        }
        if (scrollY > height) {
            return bottom + (scrollY - height);
        }
        return bottom;
    }

    public int computeVerticalScrollOffset() {
        return Math.max(0, super.computeVerticalScrollOffset());
    }

    public int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    public int computeHorizontalScrollRange() {
        return super.computeHorizontalScrollRange();
    }

    public int computeHorizontalScrollOffset() {
        return super.computeHorizontalScrollOffset();
    }

    public int computeHorizontalScrollExtent() {
        return super.computeHorizontalScrollExtent();
    }

    protected void measureChild(View view, int i, int i2) {
        view.measure(getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight(), view.getLayoutParams().width), MeasureSpec.makeMeasureSpec(0, 0));
    }

    protected void measureChildWithMargins(View view, int i, int i2, int i3, int i4) {
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
        view.measure(getChildMeasureSpec(i, (((getPaddingLeft() + getPaddingRight()) + marginLayoutParams.leftMargin) + marginLayoutParams.rightMargin) + i2, marginLayoutParams.width), MeasureSpec.makeMeasureSpec(marginLayoutParams.bottomMargin + marginLayoutParams.topMargin, 0));
    }

    public void computeScroll() {
        if (this.f610c.m1893g()) {
            int scrollX = getScrollX();
            int scrollY = getScrollY();
            int b = this.f610c.m1888b();
            int c = this.f610c.m1889c();
            if (scrollX != b || scrollY != c) {
                int scrollRange = getScrollRange();
                int a = ViewCompat.m1154a(this);
                int i = (a == 0 || (a == 1 && scrollRange > 0)) ? 1 : 0;
                m1810a(b - scrollX, c - scrollY, scrollX, scrollY, 0, scrollRange, 0, 0, false);
                if (i != 0) {
                    m1806g();
                    if (c <= 0 && scrollY > 0) {
                        this.f611d.m1765a((int) this.f610c.m1892f());
                    } else if (c >= scrollRange && scrollY < scrollRange) {
                        this.f612e.m1765a((int) this.f610c.m1892f());
                    }
                }
            }
        }
    }

    private void m1797b(View view) {
        view.getDrawingRect(this.f609b);
        offsetDescendantRectToMyCoords(view, this.f609b);
        int a = m1807a(this.f609b);
        if (a != 0) {
            scrollBy(0, a);
        }
    }

    private boolean m1792a(Rect rect, boolean z) {
        int a = m1807a(rect);
        boolean z2 = a != 0;
        if (z2) {
            if (z) {
                scrollBy(0, a);
            } else {
                m1808a(0, a);
            }
        }
        return z2;
    }

    protected int m1807a(Rect rect) {
        if (getChildCount() == 0) {
            return 0;
        }
        int height = getHeight();
        int scrollY = getScrollY();
        int i = scrollY + height;
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        if (rect.top > 0) {
            scrollY += verticalFadingEdgeLength;
        }
        if (rect.bottom < getChildAt(0).getHeight()) {
            i -= verticalFadingEdgeLength;
        }
        if (rect.bottom > i && rect.top > scrollY) {
            if (rect.height() > height) {
                scrollY = (rect.top - scrollY) + 0;
            } else {
                scrollY = (rect.bottom - i) + 0;
            }
            scrollY = Math.min(scrollY, getChildAt(0).getBottom() - i);
        } else if (rect.top >= scrollY || rect.bottom >= i) {
            scrollY = 0;
        } else {
            if (rect.height() > height) {
                scrollY = 0 - (i - rect.bottom);
            } else {
                scrollY = 0 - (scrollY - rect.top);
            }
            scrollY = Math.max(scrollY, -getScrollY());
        }
        return scrollY;
    }

    public void requestChildFocus(View view, View view2) {
        if (this.f614g) {
            this.f616i = view2;
        } else {
            m1797b(view2);
        }
        super.requestChildFocus(view, view2);
    }

    protected boolean onRequestFocusInDescendants(int i, Rect rect) {
        if (i == 2) {
            i = 130;
        } else if (i == 1) {
            i = 33;
        }
        View findNextFocus = rect == null ? FocusFinder.getInstance().findNextFocus(this, null, i) : FocusFinder.getInstance().findNextFocusFromRect(this, rect, i);
        if (findNextFocus == null || m1793a(findNextFocus)) {
            return false;
        }
        return findNextFocus.requestFocus(i, rect);
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        rect.offset(view.getLeft() - view.getScrollX(), view.getTop() - view.getScrollY());
        return m1792a(rect, z);
    }

    public void requestLayout() {
        this.f614g = true;
        super.requestLayout();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.f614g = false;
        if (this.f616i != null && m1795a(this.f616i, (View) this)) {
            m1797b(this.f616i);
        }
        this.f616i = null;
        if (!this.f615h) {
            if (this.f628u != null) {
                scrollTo(getScrollX(), this.f628u.f604a);
                this.f628u = null;
            }
            int max = Math.max(0, (getChildCount() > 0 ? getChildAt(0).getMeasuredHeight() : 0) - (((i4 - i2) - getPaddingBottom()) - getPaddingTop()));
            if (getScrollY() > max) {
                scrollTo(getScrollX(), max);
            } else if (getScrollY() < 0) {
                scrollTo(getScrollX(), 0);
            }
        }
        scrollTo(getScrollX(), getScrollY());
        this.f615h = true;
    }

    public void onAttachedToWindow() {
        this.f615h = false;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        View findFocus = findFocus();
        if (findFocus != null && this != findFocus && m1794a(findFocus, 0, i4)) {
            findFocus.getDrawingRect(this.f609b);
            offsetDescendantRectToMyCoords(findFocus, this.f609b);
            m1803e(m1807a(this.f609b));
        }
    }

    private static boolean m1795a(View view, View view2) {
        if (view == view2) {
            return true;
        }
        ViewParent parent = view.getParent();
        boolean z = (parent instanceof ViewGroup) && m1795a((View) parent, view2);
        return z;
    }

    public void m1815d(int i) {
        if (getChildCount() > 0) {
            int height = (getHeight() - getPaddingBottom()) - getPaddingTop();
            int height2 = getChildAt(0).getHeight();
            this.f610c.m1885a(getScrollX(), getScrollY(), 0, i, 0, 0, 0, Math.max(0, height2 - height), 0, height / 2);
            ViewCompat.m1174d(this);
        }
    }

    private void m1805f(int i) {
        int scrollY = getScrollY();
        boolean z = (scrollY > 0 || i > 0) && (scrollY < getScrollRange() || i < 0);
        if (!dispatchNestedPreFling(0.0f, (float) i)) {
            dispatchNestedFling(0.0f, (float) i, z);
            if (z) {
                m1815d(i);
            }
        }
    }

    private void m1804f() {
        this.f617j = false;
        m1802e();
        stopNestedScroll();
        if (this.f611d != null) {
            this.f611d.m1768c();
            this.f612e.m1768c();
        }
    }

    public void scrollTo(int i, int i2) {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            int b = m1796b(i, (getWidth() - getPaddingRight()) - getPaddingLeft(), childAt.getWidth());
            int b2 = m1796b(i2, (getHeight() - getPaddingBottom()) - getPaddingTop(), childAt.getHeight());
            if (b != getScrollX() || b2 != getScrollY()) {
                super.scrollTo(b, b2);
            }
        }
    }

    private void m1806g() {
        if (ViewCompat.m1154a(this) == 2) {
            this.f611d = null;
            this.f612e = null;
        } else if (this.f611d == null) {
            Context context = getContext();
            this.f611d = new EdgeEffectCompat(context);
            this.f612e = new EdgeEffectCompat(context);
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.f611d != null) {
            int save;
            int width;
            int scrollY = getScrollY();
            if (!this.f611d.m1762a()) {
                save = canvas.save();
                width = (getWidth() - getPaddingLeft()) - getPaddingRight();
                canvas.translate((float) getPaddingLeft(), (float) Math.min(0, scrollY));
                this.f611d.m1761a(width, getHeight());
                if (this.f611d.m1766a(canvas)) {
                    ViewCompat.m1174d(this);
                }
                canvas.restoreToCount(save);
            }
            if (!this.f612e.m1762a()) {
                save = canvas.save();
                width = (getWidth() - getPaddingLeft()) - getPaddingRight();
                int height = getHeight();
                canvas.translate((float) ((-width) + getPaddingLeft()), (float) (Math.max(getScrollRange(), scrollY) + height));
                canvas.rotate(180.0f, (float) width, 0.0f);
                this.f612e.m1761a(width, height);
                if (this.f612e.m1766a(canvas)) {
                    ViewCompat.m1174d(this);
                }
                canvas.restoreToCount(save);
            }
        }
    }

    private static int m1796b(int i, int i2, int i3) {
        if (i2 >= i3 || i < 0) {
            return 0;
        }
        if (i2 + i > i3) {
            return i3 - i2;
        }
        return i;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.f628u = savedState;
        requestLayout();
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.f604a = getScrollY();
        return savedState;
    }
}
