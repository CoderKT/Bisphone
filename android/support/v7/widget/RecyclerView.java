package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.v4.os.TraceCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v4.widget.EdgeEffectCompat;
import android.support.v4.widget.ScrollerCompat;
import android.support.v7.recyclerview.C0058R;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import se.emilsjolander.stickylistheaders.C1128R;

public class RecyclerView extends ViewGroup implements NestedScrollingChild, ScrollingView {
    private static final Interpolator ao;
    private static final boolean f1658i;
    private static final Class<?>[] f1659j;
    private boolean f1660A;
    private boolean f1661B;
    private int f1662C;
    private boolean f1663D;
    private final boolean f1664E;
    private final AccessibilityManager f1665F;
    private List<OnChildAttachStateChangeListener> f1666G;
    private boolean f1667H;
    private int f1668I;
    private EdgeEffectCompat f1669J;
    private EdgeEffectCompat f1670K;
    private EdgeEffectCompat f1671L;
    private EdgeEffectCompat f1672M;
    private int f1673N;
    private int f1674O;
    private VelocityTracker f1675P;
    private int f1676Q;
    private int f1677R;
    private int f1678S;
    private int f1679T;
    private int f1680U;
    private final int f1681V;
    private final int f1682W;
    final Recycler f1683a;
    private float aa;
    private final ViewFlinger ab;
    private OnScrollListener ac;
    private List<OnScrollListener> ad;
    private ItemAnimatorListener ae;
    private boolean af;
    private RecyclerViewAccessibilityDelegate ag;
    private ChildDrawingOrderCallback ah;
    private final int[] ai;
    private final NestedScrollingChildHelper aj;
    private final int[] ak;
    private final int[] al;
    private final int[] am;
    private Runnable an;
    private final ProcessCallback ap;
    AdapterHelper f1684b;
    ChildHelper f1685c;
    final ViewInfoStore f1686d;
    ItemAnimator f1687e;
    final State f1688f;
    boolean f1689g;
    boolean f1690h;
    private final RecyclerViewDataObserver f1691k;
    private SavedState f1692l;
    private boolean f1693m;
    private final Runnable f1694n;
    private final Rect f1695o;
    private Adapter f1696p;
    private LayoutManager f1697q;
    private RecyclerListener f1698r;
    private final ArrayList<ItemDecoration> f1699s;
    private final ArrayList<OnItemTouchListener> f1700t;
    private OnItemTouchListener f1701u;
    private boolean f1702v;
    private boolean f1703w;
    private boolean f1704x;
    private boolean f1705y;
    private boolean f1706z;

    public abstract class ItemAnimator {
        private ItemAnimatorListener f1486a;
        private ArrayList<ItemAnimatorFinishedListener> f1487b;
        private long f1488c;
        private long f1489d;
        private long f1490e;
        private long f1491f;

        public interface ItemAnimatorFinishedListener {
            void m3362a();
        }

        interface ItemAnimatorListener {
            void m3363a(ViewHolder viewHolder);
        }

        public class ItemHolderInfo {
            public int f1595a;
            public int f1596b;
            public int f1597c;
            public int f1598d;

            public ItemHolderInfo m3364a(ViewHolder viewHolder) {
                return m3365a(viewHolder, 0);
            }

            public ItemHolderInfo m3365a(ViewHolder viewHolder, int i) {
                View view = viewHolder.f1642a;
                this.f1595a = view.getLeft();
                this.f1596b = view.getTop();
                this.f1597c = view.getRight();
                this.f1598d = view.getBottom();
                return this;
            }
        }

        public abstract void m2942a();

        public abstract boolean m2944a(ViewHolder viewHolder, ItemHolderInfo itemHolderInfo, ItemHolderInfo itemHolderInfo2);

        public abstract boolean m2945a(ViewHolder viewHolder, ViewHolder viewHolder2, ItemHolderInfo itemHolderInfo, ItemHolderInfo itemHolderInfo2);

        public abstract boolean m2946b();

        public abstract boolean m2947b(ViewHolder viewHolder, ItemHolderInfo itemHolderInfo, ItemHolderInfo itemHolderInfo2);

        public abstract void m2948c();

        public abstract void m2949c(ViewHolder viewHolder);

        public abstract boolean m2950c(ViewHolder viewHolder, ItemHolderInfo itemHolderInfo, ItemHolderInfo itemHolderInfo2);

        public ItemAnimator() {
            this.f1486a = null;
            this.f1487b = new ArrayList();
            this.f1488c = 120;
            this.f1489d = 120;
            this.f1490e = 250;
            this.f1491f = 250;
        }

        public long m2951d() {
            return this.f1490e;
        }

        public long m2952e() {
            return this.f1488c;
        }

        public long m2954f() {
            return this.f1489d;
        }

        public long m2956g() {
            return this.f1491f;
        }

        void m2943a(ItemAnimatorListener itemAnimatorListener) {
            this.f1486a = itemAnimatorListener;
        }

        public ItemHolderInfo m2941a(State state, ViewHolder viewHolder, int i, List<Object> list) {
            return m2959i().m3364a(viewHolder);
        }

        public ItemHolderInfo m2940a(State state, ViewHolder viewHolder) {
            return m2959i().m3364a(viewHolder);
        }

        static int m2939d(ViewHolder viewHolder) {
            int f = viewHolder.f1653l & 14;
            if (viewHolder.m3507n()) {
                return 4;
            }
            if ((f & 4) != 0) {
                return f;
            }
            int f2 = viewHolder.m3499f();
            int e = viewHolder.m3498e();
            if (f2 == -1 || e == -1 || f2 == e) {
                return f;
            }
            return f | 2048;
        }

        public final void m2953e(ViewHolder viewHolder) {
            m2955f(viewHolder);
            if (this.f1486a != null) {
                this.f1486a.m3363a(viewHolder);
            }
        }

        public void m2955f(ViewHolder viewHolder) {
        }

        public boolean m2957g(ViewHolder viewHolder) {
            return true;
        }

        public final void m2958h() {
            int size = this.f1487b.size();
            for (int i = 0; i < size; i++) {
                ((ItemAnimatorFinishedListener) this.f1487b.get(i)).m3362a();
            }
            this.f1487b.clear();
        }

        public ItemHolderInfo m2959i() {
            return new ItemHolderInfo();
        }
    }

    public abstract class SmoothScroller {
        private int f1506a;
        private RecyclerView f1507b;
        private LayoutManager f1508c;
        private boolean f1509d;
        private boolean f1510e;
        private View f1511f;
        private final Action f1512g;

        public class Action {
            private int f1617a;
            private int f1618b;
            private int f1619c;
            private int f1620d;
            private Interpolator f1621e;
            private boolean f1622f;
            private int f1623g;

            public Action(int i, int i2) {
                this(i, i2, Integer.MIN_VALUE, null);
            }

            public Action(int i, int i2, int i3, Interpolator interpolator) {
                this.f1620d = -1;
                this.f1622f = false;
                this.f1623g = 0;
                this.f1617a = i;
                this.f1618b = i2;
                this.f1619c = i3;
                this.f1621e = interpolator;
            }

            public void m3440a(int i) {
                this.f1620d = i;
            }

            boolean m3442a() {
                return this.f1620d >= 0;
            }

            private void m3438a(RecyclerView recyclerView) {
                if (this.f1620d >= 0) {
                    int i = this.f1620d;
                    this.f1620d = -1;
                    recyclerView.m3565g(i);
                    this.f1622f = false;
                } else if (this.f1622f) {
                    m3439b();
                    if (this.f1621e != null) {
                        recyclerView.ab.m3470a(this.f1617a, this.f1618b, this.f1619c, this.f1621e);
                    } else if (this.f1619c == Integer.MIN_VALUE) {
                        recyclerView.ab.m3472b(this.f1617a, this.f1618b);
                    } else {
                        recyclerView.ab.m3468a(this.f1617a, this.f1618b, this.f1619c);
                    }
                    this.f1623g++;
                    if (this.f1623g > 10) {
                        Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                    }
                    this.f1622f = false;
                } else {
                    this.f1623g = 0;
                }
            }

            private void m3439b() {
                if (this.f1621e != null && this.f1619c < 1) {
                    throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
                } else if (this.f1619c < 1) {
                    throw new IllegalStateException("Scroll duration must be a positive number");
                }
            }

            public void m3441a(int i, int i2, int i3, Interpolator interpolator) {
                this.f1617a = i;
                this.f1618b = i2;
                this.f1619c = i3;
                this.f1621e = interpolator;
                this.f1622f = true;
            }
        }

        protected abstract void m3017a();

        protected abstract void m3018a(int i, int i2, State state, Action action);

        protected abstract void m3021a(View view, State state, Action action);

        protected abstract void m3022b();

        public SmoothScroller() {
            this.f1506a = -1;
            this.f1512g = new Action(0, 0);
        }

        void m3020a(RecyclerView recyclerView, LayoutManager layoutManager) {
            this.f1507b = recyclerView;
            this.f1508c = layoutManager;
            if (this.f1506a == -1) {
                throw new IllegalArgumentException("Invalid target position");
            }
            this.f1507b.f1688f.f1625b = this.f1506a;
            this.f1510e = true;
            this.f1509d = true;
            this.f1511f = m3026e(m3030i());
            m3017a();
            this.f1507b.ab.m3466a();
        }

        public void m3024d(int i) {
            this.f1506a = i;
        }

        public LayoutManager m3025e() {
            return this.f1508c;
        }

        protected final void m3027f() {
            if (this.f1510e) {
                m3022b();
                this.f1507b.f1688f.f1625b = -1;
                this.f1511f = null;
                this.f1506a = -1;
                this.f1509d = false;
                this.f1510e = false;
                this.f1508c.m3072b(this);
                this.f1508c = null;
                this.f1507b = null;
            }
        }

        public boolean m3028g() {
            return this.f1509d;
        }

        public boolean m3029h() {
            return this.f1510e;
        }

        public int m3030i() {
            return this.f1506a;
        }

        private void m3014a(int i, int i2) {
            RecyclerView recyclerView = this.f1507b;
            if (!this.f1510e || this.f1506a == -1 || recyclerView == null) {
                m3027f();
            }
            this.f1509d = false;
            if (this.f1511f != null) {
                if (m3016a(this.f1511f) == this.f1506a) {
                    m3021a(this.f1511f, recyclerView.f1688f, this.f1512g);
                    this.f1512g.m3438a(recyclerView);
                    m3027f();
                } else {
                    Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
                    this.f1511f = null;
                }
            }
            if (this.f1510e) {
                m3018a(i, i2, recyclerView.f1688f, this.f1512g);
                boolean a = this.f1512g.m3442a();
                this.f1512g.m3438a(recyclerView);
                if (!a) {
                    return;
                }
                if (this.f1510e) {
                    this.f1509d = true;
                    recyclerView.ab.m3466a();
                    return;
                }
                m3027f();
            }
        }

        public int m3016a(View view) {
            return this.f1507b.m3613c(view);
        }

        public int m3031j() {
            return this.f1507b.f1697q.m3166q();
        }

        public View m3026e(int i) {
            return this.f1507b.f1697q.m3117b(i);
        }

        protected void m3023b(View view) {
            if (m3016a(view) == m3030i()) {
                this.f1511f = view;
            }
        }

        protected void m3019a(PointF pointF) {
            double sqrt = Math.sqrt((double) ((pointF.x * pointF.x) + (pointF.y * pointF.y)));
            pointF.x = (float) (((double) pointF.x) / sqrt);
            pointF.y = (float) (((double) pointF.y) / sqrt);
        }
    }

    public abstract class LayoutManager {
        private boolean f1542a;
        private boolean f1543b;
        ChildHelper f1544h;
        RecyclerView f1545i;
        SmoothScroller f1546j;

        public abstract LayoutParams m3074a();

        public LayoutManager() {
            this.f1542a = false;
            this.f1543b = false;
        }

        void m3089a(RecyclerView recyclerView) {
            if (recyclerView == null) {
                this.f1545i = null;
                this.f1544h = null;
                return;
            }
            this.f1545i = recyclerView;
            this.f1544h = recyclerView.f1685c;
        }

        public void m3161m() {
            if (this.f1545i != null) {
                this.f1545i.requestLayout();
            }
        }

        public void m3103a(String str) {
            if (this.f1545i != null) {
                this.f1545i.m3606a(str);
            }
        }

        public boolean m3159l() {
            return false;
        }

        void m3120b(RecyclerView recyclerView) {
            this.f1543b = true;
            m3128c(recyclerView);
        }

        void m3122b(RecyclerView recyclerView, Recycler recycler) {
            this.f1543b = false;
            m3093a(recyclerView, recycler);
        }

        public void m3128c(RecyclerView recyclerView) {
        }

        @Deprecated
        public void m3138d(RecyclerView recyclerView) {
        }

        public void m3093a(RecyclerView recyclerView, Recycler recycler) {
            m3138d(recyclerView);
        }

        public void m3083a(Recycler recycler, State state) {
            Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
        }

        public boolean m3105a(LayoutParams layoutParams) {
            return layoutParams != null;
        }

        public LayoutParams m3076a(android.view.ViewGroup.LayoutParams layoutParams) {
            if (layoutParams instanceof LayoutParams) {
                return new LayoutParams((LayoutParams) layoutParams);
            }
            if (layoutParams instanceof MarginLayoutParams) {
                return new LayoutParams((MarginLayoutParams) layoutParams);
            }
            return new LayoutParams(layoutParams);
        }

        public LayoutParams m3075a(Context context, AttributeSet attributeSet) {
            return new LayoutParams(context, attributeSet);
        }

        public int m3073a(int i, Recycler recycler, State state) {
            return 0;
        }

        public int m3113b(int i, Recycler recycler, State state) {
            return 0;
        }

        public boolean m3132c() {
            return false;
        }

        public boolean m3139d() {
            return false;
        }

        public void m3137d(int i) {
        }

        public void m3094a(RecyclerView recyclerView, State state, int i) {
            Log.e("RecyclerView", "You must override smoothScrollToPosition to support smooth scrolling");
        }

        public void m3088a(SmoothScroller smoothScroller) {
            if (!(this.f1546j == null || smoothScroller == this.f1546j || !this.f1546j.m3029h())) {
                this.f1546j.m3027f();
            }
            this.f1546j = smoothScroller;
            this.f1546j.m3020a(this.f1545i, this);
        }

        public boolean m3163n() {
            return this.f1546j != null && this.f1546j.m3029h();
        }

        public int m3164o() {
            return ViewCompat.m1179h(this.f1545i);
        }

        public void m3095a(View view) {
            m3096a(view, -1);
        }

        public void m3096a(View view, int i) {
            m3069a(view, i, true);
        }

        public void m3123b(View view) {
            m3124b(view, -1);
        }

        public void m3124b(View view, int i) {
            m3069a(view, i, false);
        }

        private void m3069a(View view, int i, boolean z) {
            ViewHolder b = RecyclerView.m3544b(view);
            if (z || b.m3510q()) {
                this.f1545i.f1686d.m3893c(b);
            } else {
                this.f1545i.f1686d.m3895d(b);
            }
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (b.m3504k() || b.m3502i()) {
                if (b.m3502i()) {
                    b.m3503j();
                } else {
                    b.m3505l();
                }
                this.f1544h.m2882a(view, i, view.getLayoutParams(), false);
            } else if (view.getParent() == this.f1545i) {
                int b2 = this.f1544h.m2886b(view);
                if (i == -1) {
                    i = this.f1544h.m2885b();
                }
                if (b2 == -1) {
                    throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.f1545i.indexOfChild(view));
                } else if (b2 != i) {
                    this.f1545i.f1697q.m3118b(b2, i);
                }
            } else {
                this.f1544h.m2883a(view, i, false);
                layoutParams.f1602c = true;
                if (this.f1546j != null && this.f1546j.m3029h()) {
                    this.f1546j.m3023b(view);
                }
            }
            if (layoutParams.f1603d) {
                b.f1642a.invalidate();
                layoutParams.f1603d = false;
            }
        }

        public void m3130c(View view) {
            this.f1544h.m2881a(view);
        }

        public void m3142e(int i) {
            if (m3150g(i) != null) {
                this.f1544h.m2880a(i);
            }
        }

        public int m3165p() {
            return -1;
        }

        public int m3135d(View view) {
            return ((LayoutParams) view.getLayoutParams()).m3375c();
        }

        public View m3117b(int i) {
            int q = m3166q();
            for (int i2 = 0; i2 < q; i2++) {
                View g = m3150g(i2);
                ViewHolder b = RecyclerView.m3544b(g);
                if (b != null && b.m3497d() == i && !b.m3496c() && (this.f1545i.f1688f.m3457a() || !b.m3510q())) {
                    return g;
                }
            }
            return null;
        }

        public void m3147f(int i) {
            m3066a(i, m3150g(i));
        }

        private void m3066a(int i, View view) {
            this.f1544h.m2891d(i);
        }

        public void m3099a(View view, int i, LayoutParams layoutParams) {
            ViewHolder b = RecyclerView.m3544b(view);
            if (b.m3510q()) {
                this.f1545i.f1686d.m3893c(b);
            } else {
                this.f1545i.f1686d.m3895d(b);
            }
            this.f1544h.m2882a(view, i, layoutParams, b.m3510q());
        }

        public void m3131c(View view, int i) {
            m3099a(view, i, (LayoutParams) view.getLayoutParams());
        }

        public void m3118b(int i, int i2) {
            View g = m3150g(i);
            if (g == null) {
                throw new IllegalArgumentException("Cannot move a child from non-existing index:" + i);
            }
            m3147f(i);
            m3131c(g, i2);
        }

        public void m3101a(View view, Recycler recycler) {
            m3130c(view);
            recycler.m3403a(view);
        }

        public void m3078a(int i, Recycler recycler) {
            View g = m3150g(i);
            m3142e(i);
            recycler.m3403a(g);
        }

        public int m3166q() {
            return this.f1544h != null ? this.f1544h.m2885b() : 0;
        }

        public View m3150g(int i) {
            return this.f1544h != null ? this.f1544h.m2887b(i) : null;
        }

        public int m3167r() {
            return this.f1545i != null ? this.f1545i.getWidth() : 0;
        }

        public int m3168s() {
            return this.f1545i != null ? this.f1545i.getHeight() : 0;
        }

        public int m3169t() {
            return this.f1545i != null ? this.f1545i.getPaddingLeft() : 0;
        }

        public int m3170u() {
            return this.f1545i != null ? this.f1545i.getPaddingTop() : 0;
        }

        public int m3171v() {
            return this.f1545i != null ? this.f1545i.getPaddingRight() : 0;
        }

        public int m3172w() {
            return this.f1545i != null ? this.f1545i.getPaddingBottom() : 0;
        }

        public View m3173x() {
            if (this.f1545i == null) {
                return null;
            }
            View focusedChild = this.f1545i.getFocusedChild();
            if (focusedChild == null || this.f1544h.m2890c(focusedChild)) {
                return null;
            }
            return focusedChild;
        }

        public void m3152h(int i) {
            if (this.f1545i != null) {
                this.f1545i.m3618d(i);
            }
        }

        public void m3154i(int i) {
            if (this.f1545i != null) {
                this.f1545i.m3615c(i);
            }
        }

        public void m3082a(Recycler recycler) {
            for (int q = m3166q() - 1; q >= 0; q--) {
                m3068a(recycler, q, m3150g(q));
            }
        }

        private void m3068a(Recycler recycler, int i, View view) {
            ViewHolder b = RecyclerView.m3544b(view);
            if (!b.m3496c()) {
                if (!b.m3507n() || b.m3510q() || this.f1545i.f1696p.m3343b()) {
                    m3147f(i);
                    recycler.m3415c(view);
                    return;
                }
                m3142e(i);
                recycler.m3409b(b);
            }
        }

        void m3119b(Recycler recycler) {
            int d = recycler.m3416d();
            for (int i = d - 1; i >= 0; i--) {
                View d2 = recycler.m3417d(i);
                ViewHolder b = RecyclerView.m3544b(d2);
                if (!b.m3496c()) {
                    b.m3492a(false);
                    if (b.m3511r()) {
                        this.f1545i.removeDetachedView(d2, false);
                    }
                    if (this.f1545i.f1687e != null) {
                        this.f1545i.f1687e.m2949c(b);
                    }
                    b.m3492a(true);
                    recycler.m3410b(d2);
                }
            }
            recycler.m3420e();
            if (d > 0) {
                this.f1545i.invalidate();
            }
        }

        public void m3097a(View view, int i, int i2) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Rect f = this.f1545i.m3625f(view);
            view.measure(m3065a(m3167r(), ((f.left + f.right) + i) + (((m3169t() + m3171v()) + layoutParams.leftMargin) + layoutParams.rightMargin), layoutParams.width, m3132c()), m3065a(m3168s(), ((f.bottom + f.top) + i2) + (((m3170u() + m3172w()) + layoutParams.topMargin) + layoutParams.bottomMargin), layoutParams.height, m3139d()));
        }

        public static int m3065a(int i, int i2, int i3, boolean z) {
            int i4 = 1073741824;
            int max = Math.max(0, i - i2);
            if (z) {
                if (i3 < 0) {
                    i4 = 0;
                    i3 = 0;
                }
            } else if (i3 < 0) {
                if (i3 == -1) {
                    i3 = max;
                } else if (i3 == -2) {
                    i4 = Integer.MIN_VALUE;
                    i3 = max;
                } else {
                    i4 = 0;
                    i3 = 0;
                }
            }
            return MeasureSpec.makeMeasureSpec(i3, i4);
        }

        public int m3141e(View view) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).f1601b;
            return rect.right + (view.getMeasuredWidth() + rect.left);
        }

        public int m3146f(View view) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).f1601b;
            return rect.bottom + (view.getMeasuredHeight() + rect.top);
        }

        public void m3098a(View view, int i, int i2, int i3, int i4) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).f1601b;
            view.layout(rect.left + i, rect.top + i2, i3 - rect.right, i4 - rect.bottom);
        }

        public int m3149g(View view) {
            return view.getLeft() - m3160m(view);
        }

        public int m3151h(View view) {
            return view.getTop() - m3157k(view);
        }

        public int m3153i(View view) {
            return view.getRight() + m3162n(view);
        }

        public int m3155j(View view) {
            return view.getBottom() + m3158l(view);
        }

        public int m3157k(View view) {
            return ((LayoutParams) view.getLayoutParams()).f1601b.top;
        }

        public int m3158l(View view) {
            return ((LayoutParams) view.getLayoutParams()).f1601b.bottom;
        }

        public int m3160m(View view) {
            return ((LayoutParams) view.getLayoutParams()).f1601b.left;
        }

        public int m3162n(View view) {
            return ((LayoutParams) view.getLayoutParams()).f1601b.right;
        }

        public View m3077a(View view, int i, Recycler recycler, State state) {
            return null;
        }

        public View m3136d(View view, int i) {
            return null;
        }

        public boolean m3109a(RecyclerView recyclerView, View view, Rect rect, boolean z) {
            int t = m3169t();
            int u = m3170u();
            int r = m3167r() - m3171v();
            int s = m3168s() - m3172w();
            int left = view.getLeft() + rect.left;
            int top = view.getTop() + rect.top;
            int width = left + rect.width();
            int height = top + rect.height();
            int min = Math.min(0, left - t);
            int min2 = Math.min(0, top - u);
            int max = Math.max(0, width - r);
            s = Math.max(0, height - s);
            if (m3164o() == 1) {
                if (max == 0) {
                    max = Math.max(min, width - r);
                }
                min = max;
            } else {
                min = min != 0 ? min : Math.min(left - t, max);
            }
            max = min2 != 0 ? min2 : Math.min(top - u, s);
            if (min == 0 && max == 0) {
                return false;
            }
            if (z) {
                recyclerView.scrollBy(min, max);
            } else {
                recyclerView.m3600a(min, max);
            }
            return true;
        }

        @Deprecated
        public boolean m3110a(RecyclerView recyclerView, View view, View view2) {
            return m3163n() || recyclerView.m3633j();
        }

        public boolean m3108a(RecyclerView recyclerView, State state, View view, View view2) {
            return m3110a(recyclerView, view, view2);
        }

        public void m3081a(Adapter adapter, Adapter adapter2) {
        }

        public boolean m3111a(RecyclerView recyclerView, ArrayList<View> arrayList, int i, int i2) {
            return false;
        }

        public void m3143e(RecyclerView recyclerView) {
        }

        public void m3090a(RecyclerView recyclerView, int i, int i2) {
        }

        public void m3121b(RecyclerView recyclerView, int i, int i2) {
        }

        public void m3129c(RecyclerView recyclerView, int i, int i2) {
        }

        public void m3092a(RecyclerView recyclerView, int i, int i2, Object obj) {
            m3129c(recyclerView, i, i2);
        }

        public void m3091a(RecyclerView recyclerView, int i, int i2, int i3) {
        }

        public int m3134d(State state) {
            return 0;
        }

        public int m3115b(State state) {
            return 0;
        }

        public int m3145f(State state) {
            return 0;
        }

        public int m3140e(State state) {
            return 0;
        }

        public int m3126c(State state) {
            return 0;
        }

        public int m3148g(State state) {
            return 0;
        }

        public void m3084a(Recycler recycler, State state, int i, int i2) {
            this.f1545i.m3570i(i, i2);
        }

        public Parcelable m3116b() {
            return null;
        }

        public void m3079a(Parcelable parcelable) {
        }

        void m3174y() {
            if (this.f1546j != null) {
                this.f1546j.m3027f();
            }
        }

        private void m3072b(SmoothScroller smoothScroller) {
            if (this.f1546j == smoothScroller) {
                this.f1546j = null;
            }
        }

        public void m3156j(int i) {
        }

        public void m3127c(Recycler recycler) {
            for (int q = m3166q() - 1; q >= 0; q--) {
                if (!RecyclerView.m3544b(m3150g(q)).m3496c()) {
                    m3078a(q, recycler);
                }
            }
        }

        void m3080a(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            m3085a(this.f1545i.f1683a, this.f1545i.f1688f, accessibilityNodeInfoCompat);
        }

        public void m3085a(Recycler recycler, State state, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (ViewCompat.m1170b(this.f1545i, -1) || ViewCompat.m1166a(this.f1545i, -1)) {
                accessibilityNodeInfoCompat.m1529a(8192);
                accessibilityNodeInfoCompat.m1533a(true);
            }
            if (ViewCompat.m1170b(this.f1545i, 1) || ViewCompat.m1166a(this.f1545i, 1)) {
                accessibilityNodeInfoCompat.m1529a(4096);
                accessibilityNodeInfoCompat.m1533a(true);
            }
            accessibilityNodeInfoCompat.m1532a(CollectionInfoCompat.m1523a(m3125c(recycler, state), m3133d(recycler, state), m3144e(recycler, state), m3114b(recycler, state)));
        }

        public void m3102a(AccessibilityEvent accessibilityEvent) {
            m3087a(this.f1545i.f1683a, this.f1545i.f1688f, accessibilityEvent);
        }

        public void m3087a(Recycler recycler, State state, AccessibilityEvent accessibilityEvent) {
            boolean z = true;
            AccessibilityRecordCompat a = AccessibilityEventCompat.m1441a(accessibilityEvent);
            if (this.f1545i != null && a != null) {
                if (!(ViewCompat.m1170b(this.f1545i, 1) || ViewCompat.m1170b(this.f1545i, -1) || ViewCompat.m1166a(this.f1545i, -1) || ViewCompat.m1166a(this.f1545i, 1))) {
                    z = false;
                }
                a.m1637a(z);
                if (this.f1545i.f1696p != null) {
                    a.m1635a(this.f1545i.f1696p.m3328a());
                }
            }
        }

        void m3100a(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            ViewHolder b = RecyclerView.m3544b(view);
            if (b != null && !b.m3510q() && !this.f1544h.m2890c(b.f1642a)) {
                m3086a(this.f1545i.f1683a, this.f1545i.f1688f, view, accessibilityNodeInfoCompat);
            }
        }

        public void m3086a(Recycler recycler, State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            int d;
            int d2 = m3139d() ? m3135d(view) : 0;
            if (m3132c()) {
                d = m3135d(view);
            } else {
                d = 0;
            }
            accessibilityNodeInfoCompat.m1536b(CollectionItemInfoCompat.m1524a(d2, 1, d, 1, false, false));
        }

        public int m3114b(Recycler recycler, State state) {
            return 0;
        }

        public int m3125c(Recycler recycler, State state) {
            if (this.f1545i == null || this.f1545i.f1696p == null || !m3139d()) {
                return 1;
            }
            return this.f1545i.f1696p.m3328a();
        }

        public int m3133d(Recycler recycler, State state) {
            if (this.f1545i == null || this.f1545i.f1696p == null || !m3132c()) {
                return 1;
            }
            return this.f1545i.f1696p.m3328a();
        }

        public boolean m3144e(Recycler recycler, State state) {
            return false;
        }

        boolean m3104a(int i, Bundle bundle) {
            return m3106a(this.f1545i.f1683a, this.f1545i.f1688f, i, bundle);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean m3106a(android.support.v7.widget.RecyclerView.Recycler r7, android.support.v7.widget.RecyclerView.State r8, int r9, android.os.Bundle r10) {
            /*
            r6 = this;
            r4 = -1;
            r2 = 1;
            r1 = 0;
            r0 = r6.f1545i;
            if (r0 != 0) goto L_0x0008;
        L_0x0007:
            return r1;
        L_0x0008:
            switch(r9) {
                case 4096: goto L_0x004a;
                case 8192: goto L_0x0018;
                default: goto L_0x000b;
            };
        L_0x000b:
            r0 = r1;
            r3 = r1;
        L_0x000d:
            if (r3 != 0) goto L_0x0011;
        L_0x000f:
            if (r0 == 0) goto L_0x0007;
        L_0x0011:
            r1 = r6.f1545i;
            r1.scrollBy(r0, r3);
            r1 = r2;
            goto L_0x0007;
        L_0x0018:
            r0 = r6.f1545i;
            r0 = android.support.v4.view.ViewCompat.m1170b(r0, r4);
            if (r0 == 0) goto L_0x007f;
        L_0x0020:
            r0 = r6.m3168s();
            r3 = r6.m3170u();
            r0 = r0 - r3;
            r3 = r6.m3172w();
            r0 = r0 - r3;
            r0 = -r0;
        L_0x002f:
            r3 = r6.f1545i;
            r3 = android.support.v4.view.ViewCompat.m1166a(r3, r4);
            if (r3 == 0) goto L_0x007a;
        L_0x0037:
            r3 = r6.m3167r();
            r4 = r6.m3169t();
            r3 = r3 - r4;
            r4 = r6.m3171v();
            r3 = r3 - r4;
            r3 = -r3;
            r5 = r3;
            r3 = r0;
            r0 = r5;
            goto L_0x000d;
        L_0x004a:
            r0 = r6.f1545i;
            r0 = android.support.v4.view.ViewCompat.m1170b(r0, r2);
            if (r0 == 0) goto L_0x007d;
        L_0x0052:
            r0 = r6.m3168s();
            r3 = r6.m3170u();
            r0 = r0 - r3;
            r3 = r6.m3172w();
            r0 = r0 - r3;
        L_0x0060:
            r3 = r6.f1545i;
            r3 = android.support.v4.view.ViewCompat.m1166a(r3, r2);
            if (r3 == 0) goto L_0x007a;
        L_0x0068:
            r3 = r6.m3167r();
            r4 = r6.m3169t();
            r3 = r3 - r4;
            r4 = r6.m3171v();
            r3 = r3 - r4;
            r5 = r3;
            r3 = r0;
            r0 = r5;
            goto L_0x000d;
        L_0x007a:
            r3 = r0;
            r0 = r1;
            goto L_0x000d;
        L_0x007d:
            r0 = r1;
            goto L_0x0060;
        L_0x007f:
            r0 = r1;
            goto L_0x002f;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.LayoutManager.a(android.support.v7.widget.RecyclerView$Recycler, android.support.v7.widget.RecyclerView$State, int, android.os.Bundle):boolean");
        }

        boolean m3112a(View view, int i, Bundle bundle) {
            return m3107a(this.f1545i.f1683a, this.f1545i.f1688f, view, i, bundle);
        }

        public boolean m3107a(Recycler recycler, State state, View view, int i, Bundle bundle) {
            return false;
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView.1 */
    class C00891 implements Runnable {
        final /* synthetic */ RecyclerView f1588a;

        C00891(RecyclerView recyclerView) {
            this.f1588a = recyclerView;
        }

        public void run() {
            if (this.f1588a.f1704x && !this.f1588a.isLayoutRequested()) {
                if (this.f1588a.f1660A) {
                    this.f1588a.f1706z = true;
                } else {
                    this.f1588a.m3588t();
                }
            }
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView.2 */
    class C00902 implements Runnable {
        final /* synthetic */ RecyclerView f1589a;

        C00902(RecyclerView recyclerView) {
            this.f1589a = recyclerView;
        }

        public void run() {
            if (this.f1589a.f1687e != null) {
                this.f1589a.f1687e.m2942a();
            }
            this.f1589a.af = false;
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView.3 */
    final class C00913 implements Interpolator {
        C00913() {
        }

        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * (((f2 * f2) * f2) * f2)) + 1.0f;
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView.4 */
    class C00924 implements ProcessCallback {
        final /* synthetic */ RecyclerView f1590a;

        C00924(RecyclerView recyclerView) {
            this.f1590a = recyclerView;
        }

        public void m3305a(ViewHolder viewHolder, ItemHolderInfo itemHolderInfo, ItemHolderInfo itemHolderInfo2) {
            this.f1590a.f1683a.m3418d(viewHolder);
            this.f1590a.m3546b(viewHolder, itemHolderInfo, itemHolderInfo2);
        }

        public void m3306b(ViewHolder viewHolder, ItemHolderInfo itemHolderInfo, ItemHolderInfo itemHolderInfo2) {
            this.f1590a.m3530a(viewHolder, itemHolderInfo, itemHolderInfo2);
        }

        public void m3307c(ViewHolder viewHolder, ItemHolderInfo itemHolderInfo, ItemHolderInfo itemHolderInfo2) {
            viewHolder.m3492a(false);
            if (this.f1590a.f1667H) {
                if (this.f1590a.f1687e.m2945a(viewHolder, viewHolder, itemHolderInfo, itemHolderInfo2)) {
                    this.f1590a.m3520C();
                }
            } else if (this.f1590a.f1687e.m2950c(viewHolder, itemHolderInfo, itemHolderInfo2)) {
                this.f1590a.m3520C();
            }
        }

        public void m3304a(ViewHolder viewHolder) {
            this.f1590a.f1697q.m3101a(viewHolder.f1642a, this.f1590a.f1683a);
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView.5 */
    class C00935 implements Callback {
        final /* synthetic */ RecyclerView f1591a;

        C00935(RecyclerView recyclerView) {
            this.f1591a = recyclerView;
        }

        public int m3308a() {
            return this.f1591a.getChildCount();
        }

        public void m3311a(View view, int i) {
            this.f1591a.addView(view, i);
            this.f1591a.m3572i(view);
        }

        public int m3309a(View view) {
            return this.f1591a.indexOfChild(view);
        }

        public void m3310a(int i) {
            View childAt = this.f1591a.getChildAt(i);
            if (childAt != null) {
                this.f1591a.m3569h(childAt);
            }
            this.f1591a.removeViewAt(i);
        }

        public View m3314b(int i) {
            return this.f1591a.getChildAt(i);
        }

        public void m3315b() {
            int a = m3308a();
            for (int i = 0; i < a; i++) {
                this.f1591a.m3569h(m3314b(i));
            }
            this.f1591a.removeAllViews();
        }

        public ViewHolder m3313b(View view) {
            return RecyclerView.m3544b(view);
        }

        public void m3312a(View view, int i, android.view.ViewGroup.LayoutParams layoutParams) {
            ViewHolder b = RecyclerView.m3544b(view);
            if (b != null) {
                if (b.m3511r() || b.m3496c()) {
                    b.m3506m();
                } else {
                    throw new IllegalArgumentException("Called attach on a child which is not detached: " + b);
                }
            }
            this.f1591a.attachViewToParent(view, i, layoutParams);
        }

        public void m3316c(int i) {
            View b = m3314b(i);
            if (b != null) {
                ViewHolder b2 = RecyclerView.m3544b(b);
                if (b2 != null) {
                    if (!b2.m3511r() || b2.m3496c()) {
                        b2.m3495b(256);
                    } else {
                        throw new IllegalArgumentException("called detach on an already detached child " + b2);
                    }
                }
            }
            this.f1591a.detachViewFromParent(i);
        }

        public void m3317c(View view) {
            ViewHolder b = RecyclerView.m3544b(view);
            if (b != null) {
                b.m3485z();
            }
        }

        public void m3318d(View view) {
            ViewHolder b = RecyclerView.m3544b(view);
            if (b != null) {
                b.m3473A();
            }
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView.6 */
    class C00946 implements Callback {
        final /* synthetic */ RecyclerView f1592a;

        C00946(RecyclerView recyclerView) {
            this.f1592a = recyclerView;
        }

        public ViewHolder m3319a(int i) {
            ViewHolder a = this.f1592a.m3596a(i, true);
            if (a == null || this.f1592a.f1685c.m2890c(a.f1642a)) {
                return null;
            }
            return a;
        }

        public void m3320a(int i, int i2) {
            this.f1592a.m3602a(i, i2, true);
            this.f1592a.f1689g = true;
            State.m3443a(this.f1592a.f1688f, i2);
        }

        public void m3323b(int i, int i2) {
            this.f1592a.m3602a(i, i2, false);
            this.f1592a.f1689g = true;
        }

        public void m3321a(int i, int i2, Object obj) {
            this.f1592a.m3601a(i, i2, obj);
            this.f1592a.f1690h = true;
        }

        public void m3322a(UpdateOp updateOp) {
            m3326c(updateOp);
        }

        void m3326c(UpdateOp updateOp) {
            switch (updateOp.f1313a) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    this.f1592a.f1697q.m3090a(this.f1592a, updateOp.f1314b, updateOp.f1316d);
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    this.f1592a.f1697q.m3121b(this.f1592a, updateOp.f1314b, updateOp.f1316d);
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    this.f1592a.f1697q.m3092a(this.f1592a, updateOp.f1314b, updateOp.f1316d, updateOp.f1315c);
                case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                    this.f1592a.f1697q.m3091a(this.f1592a, updateOp.f1314b, updateOp.f1316d, 1);
                default:
            }
        }

        public void m3324b(UpdateOp updateOp) {
            m3326c(updateOp);
        }

        public void m3325c(int i, int i2) {
            this.f1592a.m3623e(i, i2);
            this.f1592a.f1689g = true;
        }

        public void m3327d(int i, int i2) {
            this.f1592a.m3619d(i, i2);
            this.f1592a.f1689g = true;
        }
    }

    public abstract class Adapter<VH extends ViewHolder> {
        private final AdapterDataObservable f1593a;
        private boolean f1594b;

        public abstract int m3328a();

        public abstract VH m3330a(ViewGroup viewGroup, int i);

        public abstract void m3334a(VH vh, int i);

        public Adapter() {
            this.f1593a = new AdapterDataObservable();
            this.f1594b = false;
        }

        public void m3335a(VH vh, int i, List<Object> list) {
            m3334a((ViewHolder) vh, i);
        }

        public final VH m3338b(ViewGroup viewGroup, int i) {
            TraceCompat.m698a("RV CreateView");
            VH a = m3330a(viewGroup, i);
            a.f1646e = i;
            TraceCompat.m697a();
            return a;
        }

        public final void m3341b(VH vh, int i) {
            vh.f1643b = i;
            if (m3343b()) {
                vh.f1645d = m3337b(i);
            }
            vh.m3487a(1, 519);
            TraceCompat.m698a("RV OnBindView");
            m3335a(vh, i, vh.m3514u());
            vh.m3513t();
            TraceCompat.m697a();
        }

        public int m3329a(int i) {
            return 0;
        }

        public long m3337b(int i) {
            return -1;
        }

        public final boolean m3343b() {
            return this.f1594b;
        }

        public void m3333a(VH vh) {
        }

        public boolean m3344b(VH vh) {
            return false;
        }

        public void m3347c(VH vh) {
        }

        public void m3349d(VH vh) {
        }

        public void m3332a(AdapterDataObserver adapterDataObserver) {
            this.f1593a.registerObserver(adapterDataObserver);
        }

        public void m3340b(AdapterDataObserver adapterDataObserver) {
            this.f1593a.unregisterObserver(adapterDataObserver);
        }

        public void m3336a(RecyclerView recyclerView) {
        }

        public void m3342b(RecyclerView recyclerView) {
        }

        public final void m3345c() {
            this.f1593a.m3351a();
        }

        public final void m3346c(int i) {
            this.f1593a.m3352a(i, 1);
        }

        public final void m3331a(int i, int i2) {
            this.f1593a.m3352a(i, i2);
        }

        public final void m3348d(int i) {
            this.f1593a.m3354b(i, 1);
        }

        public final void m3339b(int i, int i2) {
            this.f1593a.m3354b(i, i2);
        }

        public final void m3350e(int i) {
            this.f1593a.m3355c(i, 1);
        }
    }

    class AdapterDataObservable extends Observable<AdapterDataObserver> {
        AdapterDataObservable() {
        }

        public void m3351a() {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) this.mObservers.get(size)).m3356a();
            }
        }

        public void m3352a(int i, int i2) {
            m3353a(i, i2, null);
        }

        public void m3353a(int i, int i2, Object obj) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) this.mObservers.get(size)).m3358a(i, i2, obj);
            }
        }

        public void m3354b(int i, int i2) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) this.mObservers.get(size)).m3359b(i, i2);
            }
        }

        public void m3355c(int i, int i2) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) this.mObservers.get(size)).m3360c(i, i2);
            }
        }
    }

    public abstract class AdapterDataObserver {
        public void m3356a() {
        }

        public void m3357a(int i, int i2) {
        }

        public void m3358a(int i, int i2, Object obj) {
            m3357a(i, i2);
        }

        public void m3359b(int i, int i2) {
        }

        public void m3360c(int i, int i2) {
        }
    }

    public interface ChildDrawingOrderCallback {
        int m3361a(int i, int i2);
    }

    class ItemAnimatorRestoreListener implements ItemAnimatorListener {
        final /* synthetic */ RecyclerView f1599a;

        private ItemAnimatorRestoreListener(RecyclerView recyclerView) {
            this.f1599a = recyclerView;
        }

        public void m3366a(ViewHolder viewHolder) {
            viewHolder.m3492a(true);
            if (viewHolder.f1648g != null && viewHolder.f1649h == null) {
                viewHolder.f1648g = null;
            }
            viewHolder.f1649h = null;
            if (!viewHolder.m3474B() && !this.f1599a.m3566g(viewHolder.f1642a) && viewHolder.m3511r()) {
                this.f1599a.removeDetachedView(viewHolder.f1642a, false);
            }
        }
    }

    public abstract class ItemDecoration {
        public void m3368a(Canvas canvas, RecyclerView recyclerView, State state) {
            m3367a(canvas, recyclerView);
        }

        @Deprecated
        public void m3367a(Canvas canvas, RecyclerView recyclerView) {
        }

        public void m3372b(Canvas canvas, RecyclerView recyclerView, State state) {
            m3371b(canvas, recyclerView);
        }

        @Deprecated
        public void m3371b(Canvas canvas, RecyclerView recyclerView) {
        }

        @Deprecated
        public void m3369a(Rect rect, int i, RecyclerView recyclerView) {
            rect.set(0, 0, 0, 0);
        }

        public void m3370a(Rect rect, View view, RecyclerView recyclerView, State state) {
            m3369a(rect, ((LayoutParams) view.getLayoutParams()).m3375c(), recyclerView);
        }
    }

    public class LayoutParams extends MarginLayoutParams {
        ViewHolder f1600a;
        final Rect f1601b;
        boolean f1602c;
        boolean f1603d;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1601b = new Rect();
            this.f1602c = true;
            this.f1603d = false;
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.f1601b = new Rect();
            this.f1602c = true;
            this.f1603d = false;
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1601b = new Rect();
            this.f1602c = true;
            this.f1603d = false;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1601b = new Rect();
            this.f1602c = true;
            this.f1603d = false;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
            this.f1601b = new Rect();
            this.f1602c = true;
            this.f1603d = false;
        }

        public boolean m3373a() {
            return this.f1600a.m3510q();
        }

        public boolean m3374b() {
            return this.f1600a.m3517x();
        }

        public int m3375c() {
            return this.f1600a.m3497d();
        }
    }

    public interface OnChildAttachStateChangeListener {
        void m3376a(View view);

        void m3377b(View view);
    }

    public interface OnItemTouchListener {
        void m3378a(boolean z);

        boolean m3379a(RecyclerView recyclerView, MotionEvent motionEvent);

        void m3380b(RecyclerView recyclerView, MotionEvent motionEvent);
    }

    public abstract class OnScrollListener {
        public void m3381a(RecyclerView recyclerView, int i) {
        }

        public void m3382a(RecyclerView recyclerView, int i, int i2) {
        }
    }

    public class RecycledViewPool {
        private SparseArray<ArrayList<ViewHolder>> f1604a;
        private SparseIntArray f1605b;
        private int f1606c;

        public RecycledViewPool() {
            this.f1604a = new SparseArray();
            this.f1605b = new SparseIntArray();
            this.f1606c = 0;
        }

        public void m3385a() {
            this.f1604a.clear();
        }

        public ViewHolder m3384a(int i) {
            ArrayList arrayList = (ArrayList) this.f1604a.get(i);
            if (arrayList == null || arrayList.isEmpty()) {
                return null;
            }
            int size = arrayList.size() - 1;
            ViewHolder viewHolder = (ViewHolder) arrayList.get(size);
            arrayList.remove(size);
            return viewHolder;
        }

        public void m3388a(ViewHolder viewHolder) {
            int h = viewHolder.m3501h();
            ArrayList b = m3383b(h);
            if (this.f1605b.get(h) > b.size()) {
                viewHolder.m3515v();
                b.add(viewHolder);
            }
        }

        void m3386a(Adapter adapter) {
            this.f1606c++;
        }

        void m3389b() {
            this.f1606c--;
        }

        void m3387a(Adapter adapter, Adapter adapter2, boolean z) {
            if (adapter != null) {
                m3389b();
            }
            if (!z && this.f1606c == 0) {
                m3385a();
            }
            if (adapter2 != null) {
                m3386a(adapter2);
            }
        }

        private ArrayList<ViewHolder> m3383b(int i) {
            ArrayList<ViewHolder> arrayList = (ArrayList) this.f1604a.get(i);
            if (arrayList == null) {
                arrayList = new ArrayList();
                this.f1604a.put(i, arrayList);
                if (this.f1605b.indexOfKey(i) < 0) {
                    this.f1605b.put(i, 5);
                }
            }
            return arrayList;
        }
    }

    public final class Recycler {
        final ArrayList<ViewHolder> f1607a;
        final ArrayList<ViewHolder> f1608b;
        final /* synthetic */ RecyclerView f1609c;
        private ArrayList<ViewHolder> f1610d;
        private final List<ViewHolder> f1611e;
        private int f1612f;
        private RecycledViewPool f1613g;
        private ViewCacheExtension f1614h;

        public Recycler(RecyclerView recyclerView) {
            this.f1609c = recyclerView;
            this.f1607a = new ArrayList();
            this.f1610d = null;
            this.f1608b = new ArrayList();
            this.f1611e = Collections.unmodifiableList(this.f1607a);
            this.f1612f = 2;
        }

        public void m3397a() {
            this.f1607a.clear();
            m3411c();
        }

        public void m3398a(int i) {
            this.f1612f = i;
            for (int size = this.f1608b.size() - 1; size >= 0 && this.f1608b.size() > i; size--) {
                m3412c(size);
            }
        }

        public List<ViewHolder> m3406b() {
            return this.f1611e;
        }

        boolean m3404a(ViewHolder viewHolder) {
            if (viewHolder.m3510q()) {
                return this.f1609c.f1688f.m3457a();
            }
            if (viewHolder.f1643b < 0 || viewHolder.f1643b >= this.f1609c.f1696p.m3328a()) {
                throw new IndexOutOfBoundsException("Inconsistency detected. Invalid view holder adapter position" + viewHolder);
            } else if (!this.f1609c.f1688f.m3457a() && this.f1609c.f1696p.m3329a(viewHolder.f1643b) != viewHolder.m3501h()) {
                return false;
            } else {
                if (!this.f1609c.f1696p.m3343b() || viewHolder.m3500g() == this.f1609c.f1696p.m3337b(viewHolder.f1643b)) {
                    return true;
                }
                return false;
            }
        }

        public View m3405b(int i) {
            return m3396a(i, false);
        }

        View m3396a(int i, boolean z) {
            boolean z2 = true;
            if (i < 0 || i >= this.f1609c.f1688f.m3460d()) {
                throw new IndexOutOfBoundsException("Invalid item position " + i + "(" + i + "). Item count:" + this.f1609c.f1688f.m3460d());
            }
            ViewHolder e;
            boolean z3;
            ViewHolder viewHolder;
            boolean z4;
            int b;
            boolean z5;
            int b2;
            android.view.ViewGroup.LayoutParams layoutParams;
            LayoutParams layoutParams2;
            if (this.f1609c.f1688f.m3457a()) {
                e = m3419e(i);
                ViewHolder viewHolder2 = e;
                z3 = e != null;
                viewHolder = viewHolder2;
            } else {
                viewHolder = null;
                z3 = false;
            }
            if (viewHolder == null) {
                viewHolder = m3394a(i, -1, z);
                if (viewHolder != null) {
                    if (m3404a(viewHolder)) {
                        z4 = true;
                    } else {
                        if (!z) {
                            viewHolder.m3495b(4);
                            if (viewHolder.m3502i()) {
                                this.f1609c.removeDetachedView(viewHolder.f1642a, false);
                                viewHolder.m3503j();
                            } else if (viewHolder.m3504k()) {
                                viewHolder.m3505l();
                            }
                            m3409b(viewHolder);
                        }
                        viewHolder = null;
                        z4 = z3;
                    }
                    if (viewHolder == null) {
                        b = this.f1609c.f1684b.m2761b(i);
                        if (b >= 0 || b >= this.f1609c.f1696p.m3328a()) {
                            throw new IndexOutOfBoundsException("Inconsistency detected. Invalid item position " + i + "(offset:" + b + ")." + "state:" + this.f1609c.f1688f.m3460d());
                        }
                        int a = this.f1609c.f1696p.m3329a(b);
                        if (this.f1609c.f1696p.m3343b()) {
                            viewHolder = m3395a(this.f1609c.f1696p.m3337b(b), a, z);
                            if (viewHolder != null) {
                                viewHolder.f1643b = b;
                                z4 = true;
                            }
                        }
                        if (viewHolder == null && this.f1614h != null) {
                            View a2 = this.f1614h.m3461a(this, i, a);
                            if (a2 != null) {
                                viewHolder = this.f1609c.m3597a(a2);
                                if (viewHolder == null) {
                                    throw new IllegalArgumentException("getViewForPositionAndType returned a view which does not have a ViewHolder");
                                } else if (viewHolder.m3496c()) {
                                    throw new IllegalArgumentException("getViewForPositionAndType returned a view that is ignored. You must call stopIgnoring before returning this view.");
                                }
                            }
                        }
                        if (viewHolder == null) {
                            viewHolder = m3422f().m3384a(a);
                            if (viewHolder != null) {
                                viewHolder.m3515v();
                                if (RecyclerView.f1658i) {
                                    m3393f(viewHolder);
                                }
                            }
                        }
                        if (viewHolder == null) {
                            e = this.f1609c.f1696p.m3338b(this.f1609c, a);
                            z5 = z4;
                            if (z5 && !this.f1609c.f1688f.m3457a() && e.m3493a(8192)) {
                                e.m3487a(0, 8192);
                                if (this.f1609c.f1688f.f1631h) {
                                    this.f1609c.m3529a(e, this.f1609c.f1687e.m2941a(this.f1609c.f1688f, e, ItemAnimator.m2939d(e) | 4096, e.m3514u()));
                                }
                            }
                            int i2;
                            if (!this.f1609c.f1688f.m3457a() && e.m3509p()) {
                                e.f1647f = i;
                                i2 = 0;
                            } else if (e.m3509p() || e.m3508o() || e.m3507n()) {
                                b2 = this.f1609c.f1684b.m2761b(i);
                                e.f1652k = this.f1609c;
                                this.f1609c.f1696p.m3341b(e, b2);
                                m3392d(e.f1642a);
                                if (this.f1609c.f1688f.m3457a()) {
                                    e.f1647f = i;
                                }
                                z4 = true;
                            } else {
                                i2 = 0;
                            }
                            layoutParams = e.f1642a.getLayoutParams();
                            if (layoutParams != null) {
                                layoutParams2 = (LayoutParams) this.f1609c.generateDefaultLayoutParams();
                                e.f1642a.setLayoutParams(layoutParams2);
                            } else if (this.f1609c.checkLayoutParams(layoutParams)) {
                                layoutParams2 = (LayoutParams) layoutParams;
                            } else {
                                layoutParams2 = (LayoutParams) this.f1609c.generateLayoutParams(layoutParams);
                                e.f1642a.setLayoutParams(layoutParams2);
                            }
                            layoutParams2.f1600a = e;
                            if (!z5 || r3 == 0) {
                                z2 = false;
                            }
                            layoutParams2.f1603d = z2;
                            return e.f1642a;
                        }
                    }
                    e = viewHolder;
                    z5 = z4;
                    e.m3487a(0, 8192);
                    if (this.f1609c.f1688f.f1631h) {
                        this.f1609c.m3529a(e, this.f1609c.f1687e.m2941a(this.f1609c.f1688f, e, ItemAnimator.m2939d(e) | 4096, e.m3514u()));
                    }
                    if (!this.f1609c.f1688f.m3457a()) {
                    }
                    if (e.m3509p()) {
                    }
                    b2 = this.f1609c.f1684b.m2761b(i);
                    e.f1652k = this.f1609c;
                    this.f1609c.f1696p.m3341b(e, b2);
                    m3392d(e.f1642a);
                    if (this.f1609c.f1688f.m3457a()) {
                        e.f1647f = i;
                    }
                    z4 = true;
                    layoutParams = e.f1642a.getLayoutParams();
                    if (layoutParams != null) {
                        layoutParams2 = (LayoutParams) this.f1609c.generateDefaultLayoutParams();
                        e.f1642a.setLayoutParams(layoutParams2);
                    } else if (this.f1609c.checkLayoutParams(layoutParams)) {
                        layoutParams2 = (LayoutParams) layoutParams;
                    } else {
                        layoutParams2 = (LayoutParams) this.f1609c.generateLayoutParams(layoutParams);
                        e.f1642a.setLayoutParams(layoutParams2);
                    }
                    layoutParams2.f1600a = e;
                    z2 = false;
                    layoutParams2.f1603d = z2;
                    return e.f1642a;
                }
            }
            z4 = z3;
            if (viewHolder == null) {
                b = this.f1609c.f1684b.m2761b(i);
                if (b >= 0) {
                }
                throw new IndexOutOfBoundsException("Inconsistency detected. Invalid item position " + i + "(offset:" + b + ")." + "state:" + this.f1609c.f1688f.m3460d());
            }
            e = viewHolder;
            z5 = z4;
            e.m3487a(0, 8192);
            if (this.f1609c.f1688f.f1631h) {
                this.f1609c.m3529a(e, this.f1609c.f1687e.m2941a(this.f1609c.f1688f, e, ItemAnimator.m2939d(e) | 4096, e.m3514u()));
            }
            if (!this.f1609c.f1688f.m3457a()) {
            }
            if (e.m3509p()) {
            }
            b2 = this.f1609c.f1684b.m2761b(i);
            e.f1652k = this.f1609c;
            this.f1609c.f1696p.m3341b(e, b2);
            m3392d(e.f1642a);
            if (this.f1609c.f1688f.m3457a()) {
                e.f1647f = i;
            }
            z4 = true;
            layoutParams = e.f1642a.getLayoutParams();
            if (layoutParams != null) {
                layoutParams2 = (LayoutParams) this.f1609c.generateDefaultLayoutParams();
                e.f1642a.setLayoutParams(layoutParams2);
            } else if (this.f1609c.checkLayoutParams(layoutParams)) {
                layoutParams2 = (LayoutParams) this.f1609c.generateLayoutParams(layoutParams);
                e.f1642a.setLayoutParams(layoutParams2);
            } else {
                layoutParams2 = (LayoutParams) layoutParams;
            }
            layoutParams2.f1600a = e;
            z2 = false;
            layoutParams2.f1603d = z2;
            return e.f1642a;
        }

        private void m3392d(View view) {
            if (this.f1609c.m3632i()) {
                if (ViewCompat.m1176e(view) == 0) {
                    ViewCompat.m1172c(view, 1);
                }
                if (!ViewCompat.m1169b(view)) {
                    ViewCompat.m1161a(view, this.f1609c.ag.m3646b());
                }
            }
        }

        private void m3393f(ViewHolder viewHolder) {
            if (viewHolder.f1642a instanceof ViewGroup) {
                m3391a((ViewGroup) viewHolder.f1642a, false);
            }
        }

        private void m3391a(ViewGroup viewGroup, boolean z) {
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (childAt instanceof ViewGroup) {
                    m3391a((ViewGroup) childAt, true);
                }
            }
            if (!z) {
                return;
            }
            if (viewGroup.getVisibility() == 4) {
                viewGroup.setVisibility(0);
                viewGroup.setVisibility(4);
                return;
            }
            int visibility = viewGroup.getVisibility();
            viewGroup.setVisibility(4);
            viewGroup.setVisibility(visibility);
        }

        public void m3403a(View view) {
            ViewHolder b = RecyclerView.m3544b(view);
            if (b.m3511r()) {
                this.f1609c.removeDetachedView(view, false);
            }
            if (b.m3502i()) {
                b.m3503j();
            } else if (b.m3504k()) {
                b.m3505l();
            }
            m3409b(b);
        }

        void m3411c() {
            for (int size = this.f1608b.size() - 1; size >= 0; size--) {
                m3412c(size);
            }
            this.f1608b.clear();
        }

        void m3412c(int i) {
            m3414c((ViewHolder) this.f1608b.get(i));
            this.f1608b.remove(i);
        }

        void m3409b(ViewHolder viewHolder) {
            boolean z = true;
            int i = 0;
            if (viewHolder.m3502i() || viewHolder.f1642a.getParent() != null) {
                StringBuilder append = new StringBuilder().append("Scrapped or attached views may not be recycled. isScrap:").append(viewHolder.m3502i()).append(" isAttached:");
                if (viewHolder.f1642a.getParent() == null) {
                    z = false;
                }
                throw new IllegalArgumentException(append.append(z).toString());
            } else if (viewHolder.m3511r()) {
                throw new IllegalArgumentException("Tmp detached view should be removed from RecyclerView before it can be recycled: " + viewHolder);
            } else if (viewHolder.m3496c()) {
                throw new IllegalArgumentException("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle.");
            } else {
                int i2;
                boolean c = viewHolder.m3475C();
                boolean z2 = this.f1609c.f1696p != null && c && this.f1609c.f1696p.m3344b(viewHolder);
                if (z2 || viewHolder.m3516w()) {
                    if (!viewHolder.m3493a(14)) {
                        int size = this.f1608b.size();
                        if (size == this.f1612f && size > 0) {
                            m3412c(0);
                        }
                        if (size < this.f1612f) {
                            this.f1608b.add(viewHolder);
                            z2 = true;
                            if (z2) {
                                m3414c(viewHolder);
                                i = 1;
                                i2 = z2;
                            } else {
                                z = z2;
                            }
                        }
                    }
                    z2 = false;
                    if (z2) {
                        z = z2;
                    } else {
                        m3414c(viewHolder);
                        i = 1;
                        i2 = z2;
                    }
                } else {
                    i2 = 0;
                }
                this.f1609c.f1686d.m3896e(viewHolder);
                if (i2 == 0 && r1 == 0 && c) {
                    viewHolder.f1652k = null;
                }
            }
        }

        void m3414c(ViewHolder viewHolder) {
            ViewCompat.m1161a(viewHolder.f1642a, null);
            m3421e(viewHolder);
            viewHolder.f1652k = null;
            m3422f().m3388a(viewHolder);
        }

        void m3410b(View view) {
            ViewHolder b = RecyclerView.m3544b(view);
            b.f1655o = null;
            b.f1656p = false;
            b.m3505l();
            m3409b(b);
        }

        void m3415c(View view) {
            ViewHolder b = RecyclerView.m3544b(view);
            if (!b.m3493a(12) && b.m3517x() && !this.f1609c.m3557c(b)) {
                if (this.f1610d == null) {
                    this.f1610d = new ArrayList();
                }
                b.m3490a(this, true);
                this.f1610d.add(b);
            } else if (!b.m3507n() || b.m3510q() || this.f1609c.f1696p.m3343b()) {
                b.m3490a(this, false);
                this.f1607a.add(b);
            } else {
                throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool.");
            }
        }

        void m3418d(ViewHolder viewHolder) {
            if (viewHolder.f1656p) {
                this.f1610d.remove(viewHolder);
            } else {
                this.f1607a.remove(viewHolder);
            }
            viewHolder.f1655o = null;
            viewHolder.f1656p = false;
            viewHolder.m3505l();
        }

        int m3416d() {
            return this.f1607a.size();
        }

        View m3417d(int i) {
            return ((ViewHolder) this.f1607a.get(i)).f1642a;
        }

        void m3420e() {
            this.f1607a.clear();
            if (this.f1610d != null) {
                this.f1610d.clear();
            }
        }

        ViewHolder m3419e(int i) {
            int i2 = 0;
            if (this.f1610d != null) {
                int size = this.f1610d.size();
                if (size != 0) {
                    ViewHolder viewHolder;
                    int i3 = 0;
                    while (i3 < size) {
                        viewHolder = (ViewHolder) this.f1610d.get(i3);
                        if (viewHolder.m3504k() || viewHolder.m3497d() != i) {
                            i3++;
                        } else {
                            viewHolder.m3495b(32);
                            return viewHolder;
                        }
                    }
                    if (this.f1609c.f1696p.m3343b()) {
                        int b = this.f1609c.f1684b.m2761b(i);
                        if (b > 0 && b < this.f1609c.f1696p.m3328a()) {
                            long b2 = this.f1609c.f1696p.m3337b(b);
                            while (i2 < size) {
                                viewHolder = (ViewHolder) this.f1610d.get(i2);
                                if (viewHolder.m3504k() || viewHolder.m3500g() != b2) {
                                    i2++;
                                } else {
                                    viewHolder.m3495b(32);
                                    return viewHolder;
                                }
                            }
                        }
                    }
                    return null;
                }
            }
            return null;
        }

        ViewHolder m3394a(int i, int i2, boolean z) {
            int i3 = 0;
            int size = this.f1607a.size();
            int i4 = 0;
            while (i4 < size) {
                View a;
                ViewHolder viewHolder = (ViewHolder) this.f1607a.get(i4);
                if (viewHolder.m3504k() || viewHolder.m3497d() != i || viewHolder.m3507n() || (!this.f1609c.f1688f.f1630g && viewHolder.m3510q())) {
                    i4++;
                } else if (i2 == -1 || viewHolder.m3501h() == i2) {
                    viewHolder.m3495b(32);
                    return viewHolder;
                } else {
                    Log.e("RecyclerView", "Scrap view for position " + i + " isn't dirty but has" + " wrong view type! (found " + viewHolder.m3501h() + " but expected " + i2 + ")");
                    if (!z) {
                        a = this.f1609c.f1685c.m2878a(i, i2);
                        if (a != null) {
                            viewHolder = RecyclerView.m3544b(a);
                            this.f1609c.f1685c.m2893e(a);
                            i3 = this.f1609c.f1685c.m2886b(a);
                            if (i3 != -1) {
                                throw new IllegalStateException("layout index should not be -1 after unhiding a view:" + viewHolder);
                            }
                            this.f1609c.f1685c.m2891d(i3);
                            m3415c(a);
                            viewHolder.m3495b(8224);
                            return viewHolder;
                        }
                    }
                    i4 = this.f1608b.size();
                    while (i3 < i4) {
                        viewHolder = (ViewHolder) this.f1608b.get(i3);
                        if (viewHolder.m3507n() || viewHolder.m3497d() != i) {
                            i3++;
                        } else if (z) {
                            return viewHolder;
                        } else {
                            this.f1608b.remove(i3);
                            return viewHolder;
                        }
                    }
                    return null;
                }
            }
            if (z) {
                a = this.f1609c.f1685c.m2878a(i, i2);
                if (a != null) {
                    viewHolder = RecyclerView.m3544b(a);
                    this.f1609c.f1685c.m2893e(a);
                    i3 = this.f1609c.f1685c.m2886b(a);
                    if (i3 != -1) {
                        this.f1609c.f1685c.m2891d(i3);
                        m3415c(a);
                        viewHolder.m3495b(8224);
                        return viewHolder;
                    }
                    throw new IllegalStateException("layout index should not be -1 after unhiding a view:" + viewHolder);
                }
            }
            i4 = this.f1608b.size();
            while (i3 < i4) {
                viewHolder = (ViewHolder) this.f1608b.get(i3);
                if (viewHolder.m3507n()) {
                }
                i3++;
            }
            return null;
        }

        ViewHolder m3395a(long j, int i, boolean z) {
            int size;
            for (size = this.f1607a.size() - 1; size >= 0; size--) {
                ViewHolder viewHolder = (ViewHolder) this.f1607a.get(size);
                if (viewHolder.m3500g() == j && !viewHolder.m3504k()) {
                    if (i == viewHolder.m3501h()) {
                        viewHolder.m3495b(32);
                        if (!viewHolder.m3510q() || this.f1609c.f1688f.m3457a()) {
                            return viewHolder;
                        }
                        viewHolder.m3487a(2, 14);
                        return viewHolder;
                    } else if (!z) {
                        this.f1607a.remove(size);
                        this.f1609c.removeDetachedView(viewHolder.f1642a, false);
                        m3410b(viewHolder.f1642a);
                    }
                }
            }
            for (size = this.f1608b.size() - 1; size >= 0; size--) {
                viewHolder = (ViewHolder) this.f1608b.get(size);
                if (viewHolder.m3500g() == j) {
                    if (i == viewHolder.m3501h()) {
                        if (z) {
                            return viewHolder;
                        }
                        this.f1608b.remove(size);
                        return viewHolder;
                    } else if (!z) {
                        m3412c(size);
                    }
                }
            }
            return null;
        }

        void m3421e(ViewHolder viewHolder) {
            if (this.f1609c.f1698r != null) {
                this.f1609c.f1698r.m3427a(viewHolder);
            }
            if (this.f1609c.f1696p != null) {
                this.f1609c.f1696p.m3333a(viewHolder);
            }
            if (this.f1609c.f1688f != null) {
                this.f1609c.f1686d.m3896e(viewHolder);
            }
        }

        void m3400a(Adapter adapter, Adapter adapter2, boolean z) {
            m3397a();
            m3422f().m3387a(adapter, adapter2, z);
        }

        void m3399a(int i, int i2) {
            int i3;
            int i4;
            int i5;
            if (i < i2) {
                i3 = -1;
                i4 = i2;
                i5 = i;
            } else {
                i3 = 1;
                i4 = i;
                i5 = i2;
            }
            int size = this.f1608b.size();
            for (int i6 = 0; i6 < size; i6++) {
                ViewHolder viewHolder = (ViewHolder) this.f1608b.get(i6);
                if (viewHolder != null && viewHolder.f1643b >= r3 && viewHolder.f1643b <= r2) {
                    if (viewHolder.f1643b == i) {
                        viewHolder.m3489a(i2 - i, false);
                    } else {
                        viewHolder.m3489a(i3, false);
                    }
                }
            }
        }

        void m3407b(int i, int i2) {
            int size = this.f1608b.size();
            for (int i3 = 0; i3 < size; i3++) {
                ViewHolder viewHolder = (ViewHolder) this.f1608b.get(i3);
                if (viewHolder != null && viewHolder.m3497d() >= i) {
                    viewHolder.m3489a(i2, true);
                }
            }
        }

        void m3408b(int i, int i2, boolean z) {
            int i3 = i + i2;
            for (int size = this.f1608b.size() - 1; size >= 0; size--) {
                ViewHolder viewHolder = (ViewHolder) this.f1608b.get(size);
                if (viewHolder != null) {
                    if (viewHolder.m3497d() >= i3) {
                        viewHolder.m3489a(-i2, z);
                    } else if (viewHolder.m3497d() >= i) {
                        viewHolder.m3495b(8);
                        m3412c(size);
                    }
                }
            }
        }

        void m3402a(ViewCacheExtension viewCacheExtension) {
            this.f1614h = viewCacheExtension;
        }

        void m3401a(RecycledViewPool recycledViewPool) {
            if (this.f1613g != null) {
                this.f1613g.m3389b();
            }
            this.f1613g = recycledViewPool;
            if (recycledViewPool != null) {
                this.f1613g.m3386a(this.f1609c.getAdapter());
            }
        }

        RecycledViewPool m3422f() {
            if (this.f1613g == null) {
                this.f1613g = new RecycledViewPool();
            }
            return this.f1613g;
        }

        void m3413c(int i, int i2) {
            int i3 = i + i2;
            for (int size = this.f1608b.size() - 1; size >= 0; size--) {
                ViewHolder viewHolder = (ViewHolder) this.f1608b.get(size);
                if (viewHolder != null) {
                    int d = viewHolder.m3497d();
                    if (d >= i && d < i3) {
                        viewHolder.m3495b(2);
                        m3412c(size);
                    }
                }
            }
        }

        void m3423g() {
            int size = this.f1608b.size();
            for (int i = 0; i < size; i++) {
                ViewHolder viewHolder = (ViewHolder) this.f1608b.get(i);
                if (viewHolder != null) {
                    viewHolder.m3495b(512);
                }
            }
        }

        void m3424h() {
            if (this.f1609c.f1696p == null || !this.f1609c.f1696p.m3343b()) {
                m3411c();
                return;
            }
            int size = this.f1608b.size();
            for (int i = 0; i < size; i++) {
                ViewHolder viewHolder = (ViewHolder) this.f1608b.get(i);
                if (viewHolder != null) {
                    viewHolder.m3495b(6);
                    viewHolder.m3491a(null);
                }
            }
        }

        void m3425i() {
            int i;
            int i2 = 0;
            int size = this.f1608b.size();
            for (i = 0; i < size; i++) {
                ((ViewHolder) this.f1608b.get(i)).m3486a();
            }
            size = this.f1607a.size();
            for (i = 0; i < size; i++) {
                ((ViewHolder) this.f1607a.get(i)).m3486a();
            }
            if (this.f1610d != null) {
                i = this.f1610d.size();
                while (i2 < i) {
                    ((ViewHolder) this.f1610d.get(i2)).m3486a();
                    i2++;
                }
            }
        }

        void m3426j() {
            int size = this.f1608b.size();
            for (int i = 0; i < size; i++) {
                LayoutParams layoutParams = (LayoutParams) ((ViewHolder) this.f1608b.get(i)).f1642a.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.f1602c = true;
                }
            }
        }
    }

    public interface RecyclerListener {
        void m3427a(ViewHolder viewHolder);
    }

    class RecyclerViewDataObserver extends AdapterDataObserver {
        final /* synthetic */ RecyclerView f1615a;

        private RecyclerViewDataObserver(RecyclerView recyclerView) {
            this.f1615a = recyclerView;
        }

        public void m3428a() {
            this.f1615a.m3606a(null);
            if (this.f1615a.f1696p.m3343b()) {
                this.f1615a.f1688f.f1629f = true;
                this.f1615a.m3523F();
            } else {
                this.f1615a.f1688f.f1629f = true;
                this.f1615a.m3523F();
            }
            if (!this.f1615a.f1684b.m2767d()) {
                this.f1615a.requestLayout();
            }
        }

        public void m3429a(int i, int i2, Object obj) {
            this.f1615a.m3606a(null);
            if (this.f1615a.f1684b.m2760a(i, i2, obj)) {
                m3430b();
            }
        }

        public void m3431b(int i, int i2) {
            this.f1615a.m3606a(null);
            if (this.f1615a.f1684b.m2763b(i, i2)) {
                m3430b();
            }
        }

        public void m3432c(int i, int i2) {
            this.f1615a.m3606a(null);
            if (this.f1615a.f1684b.m2766c(i, i2)) {
                m3430b();
            }
        }

        void m3430b() {
            if (this.f1615a.f1664E && this.f1615a.f1703w && this.f1615a.f1702v) {
                ViewCompat.m1163a(this.f1615a, this.f1615a.f1694n);
                return;
            }
            this.f1615a.f1663D = true;
            this.f1615a.requestLayout();
        }
    }

    public class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        Parcelable f1616a;

        /* renamed from: android.support.v7.widget.RecyclerView.SavedState.1 */
        final class C00951 implements Creator<SavedState> {
            C00951() {
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m3433a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m3434a(i);
            }

            public SavedState m3433a(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] m3434a(int i) {
                return new SavedState[i];
            }
        }

        SavedState(Parcel parcel) {
            super(parcel);
            this.f1616a = parcel.readParcelable(LayoutManager.class.getClassLoader());
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(this.f1616a, 0);
        }

        private void m3435a(SavedState savedState) {
            this.f1616a = savedState.f1616a;
        }

        static {
            CREATOR = new C00951();
        }
    }

    public class State {
        int f1624a;
        private int f1625b;
        private SparseArray<Object> f1626c;
        private int f1627d;
        private int f1628e;
        private boolean f1629f;
        private boolean f1630g;
        private boolean f1631h;
        private boolean f1632i;
        private boolean f1633j;

        public State() {
            this.f1625b = -1;
            this.f1624a = 0;
            this.f1627d = 0;
            this.f1628e = 0;
            this.f1629f = false;
            this.f1630g = false;
            this.f1631h = false;
            this.f1632i = false;
            this.f1633j = false;
        }

        static /* synthetic */ int m3443a(State state, int i) {
            int i2 = state.f1628e + i;
            state.f1628e = i2;
            return i2;
        }

        public boolean m3457a() {
            return this.f1630g;
        }

        public boolean m3458b() {
            return this.f1632i;
        }

        public boolean m3459c() {
            return this.f1625b != -1;
        }

        public int m3460d() {
            return this.f1630g ? this.f1627d - this.f1628e : this.f1624a;
        }

        public String toString() {
            return "State{mTargetPosition=" + this.f1625b + ", mData=" + this.f1626c + ", mItemCount=" + this.f1624a + ", mPreviousLayoutItemCount=" + this.f1627d + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.f1628e + ", mStructureChanged=" + this.f1629f + ", mInPreLayout=" + this.f1630g + ", mRunSimpleAnimations=" + this.f1631h + ", mRunPredictiveAnimations=" + this.f1632i + '}';
        }
    }

    public abstract class ViewCacheExtension {
        public abstract View m3461a(Recycler recycler, int i, int i2);
    }

    class ViewFlinger implements Runnable {
        final /* synthetic */ RecyclerView f1634a;
        private int f1635b;
        private int f1636c;
        private ScrollerCompat f1637d;
        private Interpolator f1638e;
        private boolean f1639f;
        private boolean f1640g;

        public ViewFlinger(RecyclerView recyclerView) {
            this.f1634a = recyclerView;
            this.f1638e = RecyclerView.ao;
            this.f1639f = false;
            this.f1640g = false;
            this.f1637d = ScrollerCompat.m1881a(recyclerView.getContext(), RecyclerView.ao);
        }

        public void run() {
            m3464c();
            this.f1634a.m3588t();
            ScrollerCompat scrollerCompat = this.f1637d;
            SmoothScroller smoothScroller = this.f1634a.f1697q.f1546j;
            if (scrollerCompat.m1893g()) {
                int d;
                int i;
                int f;
                int i2;
                Object obj;
                Object obj2;
                int b = scrollerCompat.m1888b();
                int c = scrollerCompat.m1889c();
                int i3 = b - this.f1635b;
                int i4 = c - this.f1636c;
                int i5 = 0;
                int i6 = 0;
                this.f1635b = b;
                this.f1636c = c;
                int i7 = 0;
                int i8 = 0;
                if (this.f1634a.f1696p != null) {
                    this.f1634a.m3610b();
                    this.f1634a.m3594z();
                    TraceCompat.m698a("RV Scroll");
                    if (i3 != 0) {
                        i5 = this.f1634a.f1697q.m3073a(i3, this.f1634a.f1683a, this.f1634a.f1688f);
                        i7 = i3 - i5;
                    }
                    if (i4 != 0) {
                        i6 = this.f1634a.f1697q.m3113b(i4, this.f1634a.f1683a, this.f1634a.f1688f);
                        i8 = i4 - i6;
                    }
                    TraceCompat.m697a();
                    this.f1634a.m3524G();
                    this.f1634a.m3518A();
                    this.f1634a.m3607a(false);
                    if (!(smoothScroller == null || smoothScroller.m3028g() || !smoothScroller.m3029h())) {
                        d = this.f1634a.f1688f.m3460d();
                        if (d == 0) {
                            smoothScroller.m3027f();
                            i = i7;
                            i7 = i6;
                            i6 = i;
                        } else if (smoothScroller.m3030i() >= d) {
                            smoothScroller.m3024d(d - 1);
                            smoothScroller.m3014a(i3 - i7, i4 - i8);
                            i = i7;
                            i7 = i6;
                            i6 = i;
                        } else {
                            smoothScroller.m3014a(i3 - i7, i4 - i8);
                        }
                        if (!this.f1634a.f1699s.isEmpty()) {
                            this.f1634a.invalidate();
                        }
                        if (ViewCompat.m1154a(this.f1634a) != 2) {
                            this.f1634a.m3567h(i3, i4);
                        }
                        if (!(i6 == 0 && i8 == 0)) {
                            f = (int) scrollerCompat.m1892f();
                            if (i6 == b) {
                                d = i6 >= 0 ? -f : i6 <= 0 ? f : 0;
                                i2 = d;
                            } else {
                                i2 = 0;
                            }
                            if (i8 != c) {
                                f = 0;
                            } else if (i8 < 0) {
                                f = -f;
                            } else if (i8 <= 0) {
                                f = 0;
                            }
                            if (ViewCompat.m1154a(this.f1634a) != 2) {
                                this.f1634a.m3616c(i2, f);
                            }
                            if ((i2 != 0 || i6 == b || scrollerCompat.m1890d() == 0) && (f != 0 || i8 == c || scrollerCompat.m1891e() == 0)) {
                                scrollerCompat.m1894h();
                            }
                        }
                        if (!(i5 == 0 && i7 == 0)) {
                            this.f1634a.m3630g(i5, i7);
                        }
                        if (!this.f1634a.awakenScrollBars()) {
                            this.f1634a.invalidate();
                        }
                        obj = (i4 == 0 && this.f1634a.f1697q.m3139d() && i7 == i4) ? 1 : null;
                        obj2 = (i3 == 0 && this.f1634a.f1697q.m3132c() && i5 == i3) ? 1 : null;
                        obj2 = ((i3 == 0 || i4 != 0) && obj2 == null && obj == null) ? null : 1;
                        if (!scrollerCompat.m1886a() || obj2 == null) {
                            this.f1634a.setScrollState(0);
                        } else {
                            m3466a();
                        }
                    }
                }
                i = i7;
                i7 = i6;
                i6 = i;
                if (this.f1634a.f1699s.isEmpty()) {
                    this.f1634a.invalidate();
                }
                if (ViewCompat.m1154a(this.f1634a) != 2) {
                    this.f1634a.m3567h(i3, i4);
                }
                f = (int) scrollerCompat.m1892f();
                if (i6 == b) {
                    i2 = 0;
                } else {
                    if (i6 >= 0) {
                        if (i6 <= 0) {
                        }
                    }
                    i2 = d;
                }
                if (i8 != c) {
                    f = 0;
                } else if (i8 < 0) {
                    f = -f;
                } else if (i8 <= 0) {
                    f = 0;
                }
                if (ViewCompat.m1154a(this.f1634a) != 2) {
                    this.f1634a.m3616c(i2, f);
                }
                scrollerCompat.m1894h();
                this.f1634a.m3630g(i5, i7);
                if (this.f1634a.awakenScrollBars()) {
                    this.f1634a.invalidate();
                }
                if (i4 == 0) {
                }
                if (i3 == 0) {
                }
                if (i3 == 0) {
                }
                if (scrollerCompat.m1886a()) {
                }
                this.f1634a.setScrollState(0);
            }
            if (smoothScroller != null) {
                if (smoothScroller.m3028g()) {
                    smoothScroller.m3014a(0, 0);
                }
                if (!this.f1640g) {
                    smoothScroller.m3027f();
                }
            }
            m3465d();
        }

        private void m3464c() {
            this.f1640g = false;
            this.f1639f = true;
        }

        private void m3465d() {
            this.f1639f = false;
            if (this.f1640g) {
                m3466a();
            }
        }

        void m3466a() {
            if (this.f1639f) {
                this.f1640g = true;
                return;
            }
            this.f1634a.removeCallbacks(this);
            ViewCompat.m1163a(this.f1634a, (Runnable) this);
        }

        public void m3467a(int i, int i2) {
            this.f1634a.setScrollState(2);
            this.f1636c = 0;
            this.f1635b = 0;
            this.f1637d.m1884a(0, 0, i, i2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            m3466a();
        }

        public void m3472b(int i, int i2) {
            m3469a(i, i2, 0, 0);
        }

        public void m3469a(int i, int i2, int i3, int i4) {
            m3468a(i, i2, m3463b(i, i2, i3, i4));
        }

        private float m3462a(float f) {
            return (float) Math.sin((double) ((float) (((double) (f - 0.5f)) * 0.4712389167638204d)));
        }

        private int m3463b(int i, int i2, int i3, int i4) {
            int round;
            int abs = Math.abs(i);
            int abs2 = Math.abs(i2);
            Object obj = abs > abs2 ? 1 : null;
            int sqrt = (int) Math.sqrt((double) ((i3 * i3) + (i4 * i4)));
            int sqrt2 = (int) Math.sqrt((double) ((i * i) + (i2 * i2)));
            int width = obj != null ? this.f1634a.getWidth() : this.f1634a.getHeight();
            int i5 = width / 2;
            float a = (m3462a(Math.min(1.0f, (((float) sqrt2) * 1.0f) / ((float) width))) * ((float) i5)) + ((float) i5);
            if (sqrt > 0) {
                round = Math.round(1000.0f * Math.abs(a / ((float) sqrt))) * 4;
            } else {
                if (obj != null) {
                    round = abs;
                } else {
                    round = abs2;
                }
                round = (int) (((((float) round) / ((float) width)) + 1.0f) * 300.0f);
            }
            return Math.min(round, 2000);
        }

        public void m3468a(int i, int i2, int i3) {
            m3470a(i, i2, i3, RecyclerView.ao);
        }

        public void m3470a(int i, int i2, int i3, Interpolator interpolator) {
            if (this.f1638e != interpolator) {
                this.f1638e = interpolator;
                this.f1637d = ScrollerCompat.m1881a(this.f1634a.getContext(), interpolator);
            }
            this.f1634a.setScrollState(2);
            this.f1636c = 0;
            this.f1635b = 0;
            this.f1637d.m1883a(0, 0, i, i2, i3);
            m3466a();
        }

        public void m3471b() {
            this.f1634a.removeCallbacks(this);
            this.f1637d.m1894h();
        }
    }

    public abstract class ViewHolder {
        private static final List<Object> f1641m;
        public final View f1642a;
        int f1643b;
        int f1644c;
        long f1645d;
        int f1646e;
        int f1647f;
        ViewHolder f1648g;
        ViewHolder f1649h;
        List<Object> f1650i;
        List<Object> f1651j;
        RecyclerView f1652k;
        private int f1653l;
        private int f1654n;
        private Recycler f1655o;
        private boolean f1656p;
        private int f1657q;

        static {
            f1641m = Collections.EMPTY_LIST;
        }

        public ViewHolder(View view) {
            this.f1643b = -1;
            this.f1644c = -1;
            this.f1645d = -1;
            this.f1646e = -1;
            this.f1647f = -1;
            this.f1648g = null;
            this.f1649h = null;
            this.f1650i = null;
            this.f1651j = null;
            this.f1654n = 0;
            this.f1655o = null;
            this.f1656p = false;
            this.f1657q = 0;
            if (view == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            this.f1642a = view;
        }

        void m3488a(int i, int i2, boolean z) {
            m3495b(8);
            m3489a(i2, z);
            this.f1643b = i;
        }

        void m3489a(int i, boolean z) {
            if (this.f1644c == -1) {
                this.f1644c = this.f1643b;
            }
            if (this.f1647f == -1) {
                this.f1647f = this.f1643b;
            }
            if (z) {
                this.f1647f += i;
            }
            this.f1643b += i;
            if (this.f1642a.getLayoutParams() != null) {
                ((LayoutParams) this.f1642a.getLayoutParams()).f1602c = true;
            }
        }

        void m3486a() {
            this.f1644c = -1;
            this.f1647f = -1;
        }

        void m3494b() {
            if (this.f1644c == -1) {
                this.f1644c = this.f1643b;
            }
        }

        boolean m3496c() {
            return (this.f1653l & 128) != 0;
        }

        public final int m3497d() {
            return this.f1647f == -1 ? this.f1643b : this.f1647f;
        }

        public final int m3498e() {
            if (this.f1652k == null) {
                return -1;
            }
            return this.f1652k.m3560d(this);
        }

        public final int m3499f() {
            return this.f1644c;
        }

        public final long m3500g() {
            return this.f1645d;
        }

        public final int m3501h() {
            return this.f1646e;
        }

        boolean m3502i() {
            return this.f1655o != null;
        }

        void m3503j() {
            this.f1655o.m3418d(this);
        }

        boolean m3504k() {
            return (this.f1653l & 32) != 0;
        }

        void m3505l() {
            this.f1653l &= -33;
        }

        void m3506m() {
            this.f1653l &= -257;
        }

        void m3490a(Recycler recycler, boolean z) {
            this.f1655o = recycler;
            this.f1656p = z;
        }

        boolean m3507n() {
            return (this.f1653l & 4) != 0;
        }

        boolean m3508o() {
            return (this.f1653l & 2) != 0;
        }

        boolean m3509p() {
            return (this.f1653l & 1) != 0;
        }

        boolean m3510q() {
            return (this.f1653l & 8) != 0;
        }

        boolean m3493a(int i) {
            return (this.f1653l & i) != 0;
        }

        boolean m3511r() {
            return (this.f1653l & 256) != 0;
        }

        boolean m3512s() {
            return (this.f1653l & 512) != 0 || m3507n();
        }

        void m3487a(int i, int i2) {
            this.f1653l = (this.f1653l & (i2 ^ -1)) | (i & i2);
        }

        void m3495b(int i) {
            this.f1653l |= i;
        }

        void m3491a(Object obj) {
            if (obj == null) {
                m3495b(1024);
            } else if ((this.f1653l & 1024) == 0) {
                m3484y();
                this.f1650i.add(obj);
            }
        }

        private void m3484y() {
            if (this.f1650i == null) {
                this.f1650i = new ArrayList();
                this.f1651j = Collections.unmodifiableList(this.f1650i);
            }
        }

        void m3513t() {
            if (this.f1650i != null) {
                this.f1650i.clear();
            }
            this.f1653l &= -1025;
        }

        List<Object> m3514u() {
            if ((this.f1653l & 1024) != 0) {
                return f1641m;
            }
            if (this.f1650i == null || this.f1650i.size() == 0) {
                return f1641m;
            }
            return this.f1651j;
        }

        void m3515v() {
            this.f1653l = 0;
            this.f1643b = -1;
            this.f1644c = -1;
            this.f1645d = -1;
            this.f1647f = -1;
            this.f1654n = 0;
            this.f1648g = null;
            this.f1649h = null;
            m3513t();
            this.f1657q = 0;
        }

        private void m3485z() {
            this.f1657q = ViewCompat.m1176e(this.f1642a);
            ViewCompat.m1172c(this.f1642a, 4);
        }

        private void m3473A() {
            ViewCompat.m1172c(this.f1642a, this.f1657q);
            this.f1657q = 0;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("ViewHolder{" + Integer.toHexString(hashCode()) + " position=" + this.f1643b + " id=" + this.f1645d + ", oldPos=" + this.f1644c + ", pLpos:" + this.f1647f);
            if (m3502i()) {
                stringBuilder.append(" scrap ").append(this.f1656p ? "[changeScrap]" : "[attachedScrap]");
            }
            if (m3507n()) {
                stringBuilder.append(" invalid");
            }
            if (!m3509p()) {
                stringBuilder.append(" unbound");
            }
            if (m3508o()) {
                stringBuilder.append(" update");
            }
            if (m3510q()) {
                stringBuilder.append(" removed");
            }
            if (m3496c()) {
                stringBuilder.append(" ignored");
            }
            if (m3511r()) {
                stringBuilder.append(" tmpDetached");
            }
            if (!m3516w()) {
                stringBuilder.append(" not recyclable(" + this.f1654n + ")");
            }
            if (m3512s()) {
                stringBuilder.append(" undefined adapter position");
            }
            if (this.f1642a.getParent() == null) {
                stringBuilder.append(" no parent");
            }
            stringBuilder.append("}");
            return stringBuilder.toString();
        }

        public final void m3492a(boolean z) {
            this.f1654n = z ? this.f1654n - 1 : this.f1654n + 1;
            if (this.f1654n < 0) {
                this.f1654n = 0;
                Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
            } else if (!z && this.f1654n == 1) {
                this.f1653l |= 16;
            } else if (z && this.f1654n == 0) {
                this.f1653l &= -17;
            }
        }

        public final boolean m3516w() {
            return (this.f1653l & 16) == 0 && !ViewCompat.m1173c(this.f1642a);
        }

        private boolean m3474B() {
            return (this.f1653l & 16) != 0;
        }

        private boolean m3475C() {
            return (this.f1653l & 16) == 0 && ViewCompat.m1173c(this.f1642a);
        }

        boolean m3517x() {
            return (this.f1653l & 2) != 0;
        }
    }

    static {
        boolean z;
        if (VERSION.SDK_INT == 18 || VERSION.SDK_INT == 19 || VERSION.SDK_INT == 20) {
            z = true;
        } else {
            z = false;
        }
        f1658i = z;
        f1659j = new Class[]{Context.class, AttributeSet.class, Integer.TYPE, Integer.TYPE};
        ao = new C00913();
    }

    public RecyclerView(Context context) {
        this(context, null);
    }

    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RecyclerView(Context context, AttributeSet attributeSet, int i) {
        boolean z;
        super(context, attributeSet, i);
        this.f1691k = new RecyclerViewDataObserver();
        this.f1683a = new Recycler(this);
        this.f1686d = new ViewInfoStore();
        this.f1694n = new C00891(this);
        this.f1695o = new Rect();
        this.f1699s = new ArrayList();
        this.f1700t = new ArrayList();
        this.f1667H = false;
        this.f1668I = 0;
        this.f1687e = new DefaultItemAnimator();
        this.f1673N = 0;
        this.f1674O = -1;
        this.aa = Float.MIN_VALUE;
        this.ab = new ViewFlinger(this);
        this.f1688f = new State();
        this.f1689g = false;
        this.f1690h = false;
        this.ae = new ItemAnimatorRestoreListener();
        this.af = false;
        this.ai = new int[2];
        this.ak = new int[2];
        this.al = new int[2];
        this.am = new int[2];
        this.an = new C00902(this);
        this.ap = new C00924(this);
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        this.f1664E = VERSION.SDK_INT >= 16;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.f1680U = viewConfiguration.getScaledTouchSlop();
        this.f1681V = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f1682W = viewConfiguration.getScaledMaximumFlingVelocity();
        if (ViewCompat.m1154a(this) == 2) {
            z = true;
        } else {
            z = false;
        }
        setWillNotDraw(z);
        this.f1687e.m2943a(this.ae);
        m3598a();
        m3586s();
        if (ViewCompat.m1176e(this) == 0) {
            ViewCompat.m1172c((View) this, 1);
        }
        this.f1665F = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new RecyclerViewAccessibilityDelegate(this));
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0058R.styleable.RecyclerView, i, 0);
            String string = obtainStyledAttributes.getString(C0058R.styleable.RecyclerView_layoutManager);
            obtainStyledAttributes.recycle();
            m3527a(context, string, attributeSet, i, 0);
        }
        this.aj = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
    }

    public RecyclerViewAccessibilityDelegate getCompatAccessibilityDelegate() {
        return this.ag;
    }

    public void setAccessibilityDelegateCompat(RecyclerViewAccessibilityDelegate recyclerViewAccessibilityDelegate) {
        this.ag = recyclerViewAccessibilityDelegate;
        ViewCompat.m1161a((View) this, this.ag);
    }

    private void m3527a(Context context, String str, AttributeSet attributeSet, int i, int i2) {
        if (str != null) {
            String trim = str.trim();
            if (trim.length() != 0) {
                String a = m3525a(context, trim);
                try {
                    ClassLoader classLoader;
                    Object[] objArr;
                    Constructor constructor;
                    if (isInEditMode()) {
                        classLoader = getClass().getClassLoader();
                    } else {
                        classLoader = context.getClassLoader();
                    }
                    Class asSubclass = classLoader.loadClass(a).asSubclass(LayoutManager.class);
                    try {
                        objArr = new Object[]{context, attributeSet, Integer.valueOf(i), Integer.valueOf(i2)};
                        constructor = asSubclass.getConstructor(f1659j);
                    } catch (Throwable e) {
                        constructor = asSubclass.getConstructor(new Class[0]);
                        objArr = null;
                    }
                    constructor.setAccessible(true);
                    setLayoutManager((LayoutManager) constructor.newInstance(objArr));
                } catch (Throwable e2) {
                    e2.initCause(e);
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + a, e2);
                } catch (Throwable e3) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find LayoutManager " + a, e3);
                } catch (Throwable e32) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + a, e32);
                } catch (Throwable e322) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + a, e322);
                } catch (Throwable e3222) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + a, e3222);
                } catch (Throwable e32222) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a LayoutManager " + a, e32222);
                }
            }
        }
    }

    private String m3525a(Context context, String str) {
        if (str.charAt(0) == '.') {
            return context.getPackageName() + str;
        }
        return !str.contains(".") ? RecyclerView.class.getPackage().getName() + '.' + str : str;
    }

    private void m3586s() {
        this.f1685c = new ChildHelper(new C00935(this));
    }

    void m3598a() {
        this.f1684b = new AdapterHelper(new C00946(this));
    }

    public void setHasFixedSize(boolean z) {
        this.f1703w = z;
    }

    public void setClipToPadding(boolean z) {
        if (z != this.f1693m) {
            m3631h();
        }
        this.f1693m = z;
        super.setClipToPadding(z);
        if (this.f1704x) {
            requestLayout();
        }
    }

    public void setScrollingTouchSlop(int i) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                break;
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                this.f1680U = ViewConfigurationCompat.m1248a(viewConfiguration);
                return;
            default:
                Log.w("RecyclerView", "setScrollingTouchSlop(): bad argument constant " + i + "; using default value");
                break;
        }
        this.f1680U = viewConfiguration.getScaledTouchSlop();
    }

    public void setAdapter(Adapter adapter) {
        setLayoutFrozen(false);
        m3528a(adapter, false, true);
        requestLayout();
    }

    private void m3528a(Adapter adapter, boolean z, boolean z2) {
        if (this.f1696p != null) {
            this.f1696p.m3340b(this.f1691k);
            this.f1696p.m3342b(this);
        }
        if (!z || z2) {
            if (this.f1687e != null) {
                this.f1687e.m2948c();
            }
            if (this.f1697q != null) {
                this.f1697q.m3127c(this.f1683a);
                this.f1697q.m3119b(this.f1683a);
            }
            this.f1683a.m3397a();
        }
        this.f1684b.m2755a();
        Adapter adapter2 = this.f1696p;
        this.f1696p = adapter;
        if (adapter != null) {
            adapter.m3332a(this.f1691k);
            adapter.m3336a(this);
        }
        if (this.f1697q != null) {
            this.f1697q.m3081a(adapter2, this.f1696p);
        }
        this.f1683a.m3400a(adapter2, this.f1696p, z);
        this.f1688f.f1629f = true;
        m3638o();
    }

    public Adapter getAdapter() {
        return this.f1696p;
    }

    public void setRecyclerListener(RecyclerListener recyclerListener) {
        this.f1698r = recyclerListener;
    }

    public int getBaseline() {
        if (this.f1697q != null) {
            return this.f1697q.m3165p();
        }
        return super.getBaseline();
    }

    public void setLayoutManager(LayoutManager layoutManager) {
        if (layoutManager != this.f1697q) {
            if (this.f1697q != null) {
                if (this.f1702v) {
                    this.f1697q.m3122b(this, this.f1683a);
                }
                this.f1697q.m3089a(null);
            }
            this.f1683a.m3397a();
            this.f1685c.m2879a();
            this.f1697q = layoutManager;
            if (layoutManager != null) {
                if (layoutManager.f1545i != null) {
                    throw new IllegalArgumentException("LayoutManager " + layoutManager + " is already attached to a RecyclerView: " + layoutManager.f1545i);
                }
                this.f1697q.m3089a(this);
                if (this.f1702v) {
                    this.f1697q.m3120b(this);
                }
            }
            requestLayout();
        }
    }

    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (this.f1692l != null) {
            savedState.m3435a(this.f1692l);
        } else if (this.f1697q != null) {
            savedState.f1616a = this.f1697q.m3116b();
        } else {
            savedState.f1616a = null;
        }
        return savedState;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        this.f1692l = (SavedState) parcelable;
        super.onRestoreInstanceState(this.f1692l.getSuperState());
        if (this.f1697q != null && this.f1692l.f1616a != null) {
            this.f1697q.m3079a(this.f1692l.f1616a);
        }
    }

    protected void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    private void m3545b(ViewHolder viewHolder) {
        View view = viewHolder.f1642a;
        boolean z = view.getParent() == this;
        this.f1683a.m3418d(m3597a(view));
        if (viewHolder.m3511r()) {
            this.f1685c.m2882a(view, -1, view.getLayoutParams(), true);
        } else if (z) {
            this.f1685c.m2892d(view);
        } else {
            this.f1685c.m2884a(view, true);
        }
    }

    private boolean m3566g(View view) {
        m3610b();
        boolean f = this.f1685c.m2894f(view);
        if (f) {
            ViewHolder b = m3544b(view);
            this.f1683a.m3418d(b);
            this.f1683a.m3409b(b);
        }
        m3607a(false);
        return f;
    }

    public LayoutManager getLayoutManager() {
        return this.f1697q;
    }

    public RecycledViewPool getRecycledViewPool() {
        return this.f1683a.m3422f();
    }

    public void setRecycledViewPool(RecycledViewPool recycledViewPool) {
        this.f1683a.m3401a(recycledViewPool);
    }

    public void setViewCacheExtension(ViewCacheExtension viewCacheExtension) {
        this.f1683a.m3402a(viewCacheExtension);
    }

    public void setItemViewCacheSize(int i) {
        this.f1683a.m3398a(i);
    }

    public int getScrollState() {
        return this.f1673N;
    }

    private void setScrollState(int i) {
        if (i != this.f1673N) {
            this.f1673N = i;
            if (i != 2) {
                m3590v();
            }
            m3627f(i);
        }
    }

    public void m3604a(ItemDecoration itemDecoration, int i) {
        if (this.f1697q != null) {
            this.f1697q.m3103a("Cannot add item decoration during a scroll  or layout");
        }
        if (this.f1699s.isEmpty()) {
            setWillNotDraw(false);
        }
        if (i < 0) {
            this.f1699s.add(itemDecoration);
        } else {
            this.f1699s.add(i, itemDecoration);
        }
        m3635l();
        requestLayout();
    }

    public void m3603a(ItemDecoration itemDecoration) {
        m3604a(itemDecoration, -1);
    }

    public void setChildDrawingOrderCallback(ChildDrawingOrderCallback childDrawingOrderCallback) {
        if (childDrawingOrderCallback != this.ah) {
            this.ah = childDrawingOrderCallback;
            setChildrenDrawingOrderEnabled(this.ah != null);
        }
    }

    @Deprecated
    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.ac = onScrollListener;
    }

    public void m3605a(OnScrollListener onScrollListener) {
        if (this.ad == null) {
            this.ad = new ArrayList();
        }
        this.ad.add(onScrollListener);
    }

    public void m3599a(int i) {
        if (!this.f1660A) {
            m3614c();
            if (this.f1697q == null) {
                Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
                return;
            }
            this.f1697q.m3137d(i);
            awakenScrollBars();
        }
    }

    private void m3565g(int i) {
        if (this.f1697q != null) {
            this.f1697q.m3137d(i);
            awakenScrollBars();
        }
    }

    public void m3611b(int i) {
        if (!this.f1660A) {
            if (this.f1697q == null) {
                Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            } else {
                this.f1697q.m3094a(this, this.f1688f, i);
            }
        }
    }

    public void scrollTo(int i, int i2) {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    public void scrollBy(int i, int i2) {
        if (this.f1697q == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (!this.f1660A) {
            boolean c = this.f1697q.m3132c();
            boolean d = this.f1697q.m3139d();
            if (c || d) {
                if (!c) {
                    i = 0;
                }
                if (!d) {
                    i2 = 0;
                }
                m3608a(i, i2, null);
            }
        }
    }

    private void m3588t() {
        if (!this.f1704x) {
            return;
        }
        if (this.f1667H) {
            TraceCompat.m698a("RV FullInvalidate");
            m3634k();
            TraceCompat.m697a();
        } else if (!this.f1684b.m2767d()) {
        } else {
            if (this.f1684b.m2759a(4) && !this.f1684b.m2759a(11)) {
                TraceCompat.m698a("RV PartialInvalidate");
                m3610b();
                this.f1684b.m2762b();
                if (!this.f1706z) {
                    if (m3589u()) {
                        m3634k();
                    } else {
                        this.f1684b.m2765c();
                    }
                }
                m3607a(true);
                TraceCompat.m697a();
            } else if (this.f1684b.m2767d()) {
                TraceCompat.m698a("RV FullInvalidate");
                m3634k();
                TraceCompat.m697a();
            }
        }
    }

    private boolean m3589u() {
        int b = this.f1685c.m2885b();
        for (int i = 0; i < b; i++) {
            ViewHolder b2 = m3544b(this.f1685c.m2887b(i));
            if (b2 != null && !b2.m3496c() && b2.m3517x()) {
                return true;
            }
        }
        return false;
    }

    boolean m3608a(int i, int i2, MotionEvent motionEvent) {
        int a;
        int i3;
        int i4;
        int i5;
        m3588t();
        if (this.f1696p != null) {
            int b;
            m3610b();
            m3594z();
            TraceCompat.m698a("RV Scroll");
            if (i != 0) {
                a = this.f1697q.m3073a(i, this.f1683a, this.f1688f);
                i3 = i - a;
            } else {
                a = 0;
                i3 = 0;
            }
            if (i2 != 0) {
                b = this.f1697q.m3113b(i2, this.f1683a, this.f1688f);
                i4 = i2 - b;
            } else {
                b = 0;
                i4 = 0;
            }
            TraceCompat.m697a();
            m3524G();
            m3518A();
            m3607a(false);
            i5 = i4;
            i4 = a;
            a = b;
        } else {
            a = 0;
            i4 = 0;
            i5 = 0;
            i3 = 0;
        }
        if (!this.f1699s.isEmpty()) {
            invalidate();
        }
        if (dispatchNestedScroll(i4, a, i3, i5, this.ak)) {
            this.f1678S -= this.ak[0];
            this.f1679T -= this.ak[1];
            if (motionEvent != null) {
                motionEvent.offsetLocation((float) this.ak[0], (float) this.ak[1]);
            }
            int[] iArr = this.am;
            iArr[0] = iArr[0] + this.ak[0];
            iArr = this.am;
            iArr[1] = iArr[1] + this.ak[1];
        } else if (ViewCompat.m1154a(this) != 2) {
            if (motionEvent != null) {
                m3526a(motionEvent.getX(), (float) i3, motionEvent.getY(), (float) i5);
            }
            m3567h(i, i2);
        }
        if (!(i4 == 0 && a == 0)) {
            m3630g(i4, a);
        }
        if (!awakenScrollBars()) {
            invalidate();
        }
        if (i4 == 0 && a == 0) {
            return false;
        }
        return true;
    }

    public int computeHorizontalScrollOffset() {
        return this.f1697q.m3132c() ? this.f1697q.m3115b(this.f1688f) : 0;
    }

    public int computeHorizontalScrollExtent() {
        return this.f1697q.m3132c() ? this.f1697q.m3134d(this.f1688f) : 0;
    }

    public int computeHorizontalScrollRange() {
        return this.f1697q.m3132c() ? this.f1697q.m3145f(this.f1688f) : 0;
    }

    public int computeVerticalScrollOffset() {
        return this.f1697q.m3139d() ? this.f1697q.m3126c(this.f1688f) : 0;
    }

    public int computeVerticalScrollExtent() {
        return this.f1697q.m3139d() ? this.f1697q.m3140e(this.f1688f) : 0;
    }

    public int computeVerticalScrollRange() {
        return this.f1697q.m3139d() ? this.f1697q.m3148g(this.f1688f) : 0;
    }

    void m3610b() {
        if (!this.f1705y) {
            this.f1705y = true;
            if (!this.f1660A) {
                this.f1706z = false;
            }
        }
    }

    void m3607a(boolean z) {
        if (this.f1705y) {
            if (!(!z || !this.f1706z || this.f1660A || this.f1697q == null || this.f1696p == null)) {
                m3634k();
            }
            this.f1705y = false;
            if (!this.f1660A) {
                this.f1706z = false;
            }
        }
    }

    public void setLayoutFrozen(boolean z) {
        if (z != this.f1660A) {
            m3606a("Do not setLayoutFrozen in layout or scroll");
            if (z) {
                long uptimeMillis = SystemClock.uptimeMillis();
                onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0));
                this.f1660A = z;
                this.f1661B = true;
                m3614c();
                return;
            }
            this.f1660A = z;
            if (!(!this.f1706z || this.f1697q == null || this.f1696p == null)) {
                requestLayout();
            }
            this.f1706z = false;
        }
    }

    public void m3600a(int i, int i2) {
        int i3 = 0;
        if (this.f1697q == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (!this.f1660A) {
            if (!this.f1697q.m3132c()) {
                i = 0;
            }
            if (this.f1697q.m3139d()) {
                i3 = i2;
            }
            if (i != 0 || i3 != 0) {
                this.ab.m3472b(i, i3);
            }
        }
    }

    public boolean m3612b(int i, int i2) {
        if (this.f1697q == null) {
            Log.e("RecyclerView", "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return false;
        } else if (this.f1660A) {
            return false;
        } else {
            boolean c = this.f1697q.m3132c();
            boolean d = this.f1697q.m3139d();
            if (!c || Math.abs(i) < this.f1681V) {
                i = 0;
            }
            if (!d || Math.abs(i2) < this.f1681V) {
                i2 = 0;
            }
            if ((i == 0 && i2 == 0) || dispatchNestedPreFling((float) i, (float) i2)) {
                return false;
            }
            c = c || d;
            dispatchNestedFling((float) i, (float) i2, c);
            if (!c) {
                return false;
            }
            this.ab.m3467a(Math.max(-this.f1682W, Math.min(i, this.f1682W)), Math.max(-this.f1682W, Math.min(i2, this.f1682W)));
            return true;
        }
    }

    public void m3614c() {
        setScrollState(0);
        m3590v();
    }

    private void m3590v() {
        this.ab.m3471b();
        if (this.f1697q != null) {
            this.f1697q.m3174y();
        }
    }

    public int getMinFlingVelocity() {
        return this.f1681V;
    }

    public int getMaxFlingVelocity() {
        return this.f1682W;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m3526a(float r8, float r9, float r10, float r11) {
        /*
        r7 = this;
        r6 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r0 = 1;
        r5 = 0;
        r1 = 0;
        r2 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1));
        if (r2 >= 0) goto L_0x0050;
    L_0x0009:
        r7.m3617d();
        r2 = r7.f1669J;
        r3 = -r9;
        r4 = r7.getWidth();
        r4 = (float) r4;
        r3 = r3 / r4;
        r4 = r7.getHeight();
        r4 = (float) r4;
        r4 = r10 / r4;
        r4 = r6 - r4;
        r2 = r2.m1764a(r3, r4);
        if (r2 == 0) goto L_0x0025;
    L_0x0024:
        r1 = r0;
    L_0x0025:
        r2 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1));
        if (r2 >= 0) goto L_0x006f;
    L_0x0029:
        r7.m3626f();
        r2 = r7.f1670K;
        r3 = -r11;
        r4 = r7.getHeight();
        r4 = (float) r4;
        r3 = r3 / r4;
        r4 = r7.getWidth();
        r4 = (float) r4;
        r4 = r8 / r4;
        r2 = r2.m1764a(r3, r4);
        if (r2 == 0) goto L_0x008e;
    L_0x0042:
        if (r0 != 0) goto L_0x004c;
    L_0x0044:
        r0 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1));
        if (r0 != 0) goto L_0x004c;
    L_0x0048:
        r0 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1));
        if (r0 == 0) goto L_0x004f;
    L_0x004c:
        android.support.v4.view.ViewCompat.m1174d(r7);
    L_0x004f:
        return;
    L_0x0050:
        r2 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1));
        if (r2 <= 0) goto L_0x0025;
    L_0x0054:
        r7.m3621e();
        r2 = r7.f1671L;
        r3 = r7.getWidth();
        r3 = (float) r3;
        r3 = r9 / r3;
        r4 = r7.getHeight();
        r4 = (float) r4;
        r4 = r10 / r4;
        r2 = r2.m1764a(r3, r4);
        if (r2 == 0) goto L_0x0025;
    L_0x006d:
        r1 = r0;
        goto L_0x0025;
    L_0x006f:
        r2 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1));
        if (r2 <= 0) goto L_0x008e;
    L_0x0073:
        r7.m3629g();
        r2 = r7.f1672M;
        r3 = r7.getHeight();
        r3 = (float) r3;
        r3 = r11 / r3;
        r4 = r7.getWidth();
        r4 = (float) r4;
        r4 = r8 / r4;
        r4 = r6 - r4;
        r2 = r2.m1764a(r3, r4);
        if (r2 != 0) goto L_0x0042;
    L_0x008e:
        r0 = r1;
        goto L_0x0042;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.a(float, float, float, float):void");
    }

    private void m3591w() {
        int i = 0;
        if (this.f1669J != null) {
            i = this.f1669J.m1768c();
        }
        if (this.f1670K != null) {
            i |= this.f1670K.m1768c();
        }
        if (this.f1671L != null) {
            i |= this.f1671L.m1768c();
        }
        if (this.f1672M != null) {
            i |= this.f1672M.m1768c();
        }
        if (i != 0) {
            ViewCompat.m1174d(this);
        }
    }

    private void m3567h(int i, int i2) {
        int i3 = 0;
        if (!(this.f1669J == null || this.f1669J.m1762a() || i <= 0)) {
            i3 = this.f1669J.m1768c();
        }
        if (!(this.f1671L == null || this.f1671L.m1762a() || i >= 0)) {
            i3 |= this.f1671L.m1768c();
        }
        if (!(this.f1670K == null || this.f1670K.m1762a() || i2 <= 0)) {
            i3 |= this.f1670K.m1768c();
        }
        if (!(this.f1672M == null || this.f1672M.m1762a() || i2 >= 0)) {
            i3 |= this.f1672M.m1768c();
        }
        if (i3 != 0) {
            ViewCompat.m1174d(this);
        }
    }

    void m3616c(int i, int i2) {
        if (i < 0) {
            m3617d();
            this.f1669J.m1765a(-i);
        } else if (i > 0) {
            m3621e();
            this.f1671L.m1765a(i);
        }
        if (i2 < 0) {
            m3626f();
            this.f1670K.m1765a(-i2);
        } else if (i2 > 0) {
            m3629g();
            this.f1672M.m1765a(i2);
        }
        if (i != 0 || i2 != 0) {
            ViewCompat.m1174d(this);
        }
    }

    void m3617d() {
        if (this.f1669J == null) {
            this.f1669J = new EdgeEffectCompat(getContext());
            if (this.f1693m) {
                this.f1669J.m1761a((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
            } else {
                this.f1669J.m1761a(getMeasuredHeight(), getMeasuredWidth());
            }
        }
    }

    void m3621e() {
        if (this.f1671L == null) {
            this.f1671L = new EdgeEffectCompat(getContext());
            if (this.f1693m) {
                this.f1671L.m1761a((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
            } else {
                this.f1671L.m1761a(getMeasuredHeight(), getMeasuredWidth());
            }
        }
    }

    void m3626f() {
        if (this.f1670K == null) {
            this.f1670K = new EdgeEffectCompat(getContext());
            if (this.f1693m) {
                this.f1670K.m1761a((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
            } else {
                this.f1670K.m1761a(getMeasuredWidth(), getMeasuredHeight());
            }
        }
    }

    void m3629g() {
        if (this.f1672M == null) {
            this.f1672M = new EdgeEffectCompat(getContext());
            if (this.f1693m) {
                this.f1672M.m1761a((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
            } else {
                this.f1672M.m1761a(getMeasuredWidth(), getMeasuredHeight());
            }
        }
    }

    void m3631h() {
        this.f1672M = null;
        this.f1670K = null;
        this.f1671L = null;
        this.f1669J = null;
    }

    public View focusSearch(View view, int i) {
        View d = this.f1697q.m3136d(view, i);
        if (d != null) {
            return d;
        }
        d = FocusFinder.getInstance().findNextFocus(this, view, i);
        if (!(d != null || this.f1696p == null || this.f1697q == null || m3633j() || this.f1660A)) {
            m3610b();
            d = this.f1697q.m3077a(view, i, this.f1683a, this.f1688f);
            m3607a(false);
        }
        return d == null ? super.focusSearch(view, i) : d;
    }

    public void requestChildFocus(View view, View view2) {
        if (!(this.f1697q.m3108a(this, this.f1688f, view, view2) || view2 == null)) {
            Rect rect;
            boolean z;
            this.f1695o.set(0, 0, view2.getWidth(), view2.getHeight());
            android.view.ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
            if (layoutParams instanceof LayoutParams) {
                LayoutParams layoutParams2 = (LayoutParams) layoutParams;
                if (!layoutParams2.f1602c) {
                    Rect rect2 = layoutParams2.f1601b;
                    rect = this.f1695o;
                    rect.left -= rect2.left;
                    rect = this.f1695o;
                    rect.right += rect2.right;
                    rect = this.f1695o;
                    rect.top -= rect2.top;
                    rect = this.f1695o;
                    rect.bottom = rect2.bottom + rect.bottom;
                }
            }
            offsetDescendantRectToMyCoords(view2, this.f1695o);
            offsetRectIntoDescendantCoords(view, this.f1695o);
            rect = this.f1695o;
            if (this.f1704x) {
                z = false;
            } else {
                z = true;
            }
            requestChildRectangleOnScreen(view, rect, z);
        }
        super.requestChildFocus(view, view2);
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        return this.f1697q.m3109a(this, view, rect, z);
    }

    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        if (this.f1697q == null || !this.f1697q.m3111a(this, (ArrayList) arrayList, i, i2)) {
            super.addFocusables(arrayList, i, i2);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f1668I = 0;
        this.f1702v = true;
        this.f1704x = false;
        if (this.f1697q != null) {
            this.f1697q.m3120b(this);
        }
        this.af = false;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f1687e != null) {
            this.f1687e.m2948c();
        }
        this.f1704x = false;
        m3614c();
        this.f1702v = false;
        if (this.f1697q != null) {
            this.f1697q.m3122b(this, this.f1683a);
        }
        removeCallbacks(this.an);
        this.f1686d.m3890b();
    }

    public boolean isAttachedToWindow() {
        return this.f1702v;
    }

    void m3606a(String str) {
        if (!m3633j()) {
            return;
        }
        if (str == null) {
            throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling");
        }
        throw new IllegalStateException(str);
    }

    private boolean m3542a(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 3 || action == 0) {
            this.f1701u = null;
        }
        int size = this.f1700t.size();
        int i = 0;
        while (i < size) {
            OnItemTouchListener onItemTouchListener = (OnItemTouchListener) this.f1700t.get(i);
            if (!onItemTouchListener.m3379a(this, motionEvent) || action == 3) {
                i++;
            } else {
                this.f1701u = onItemTouchListener;
                return true;
            }
        }
        return false;
    }

    private boolean m3553b(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (this.f1701u != null) {
            if (action == 0) {
                this.f1701u = null;
            } else {
                this.f1701u.m3380b(this, motionEvent);
                if (action == 3 || action == 1) {
                    this.f1701u = null;
                }
                return true;
            }
        }
        if (action != 0) {
            int size = this.f1700t.size();
            for (int i = 0; i < size; i++) {
                OnItemTouchListener onItemTouchListener = (OnItemTouchListener) this.f1700t.get(i);
                if (onItemTouchListener.m3379a(this, motionEvent)) {
                    this.f1701u = onItemTouchListener;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int i = -1;
        boolean z = true;
        if (this.f1660A) {
            return false;
        }
        if (m3542a(motionEvent)) {
            m3593y();
            return true;
        } else if (this.f1697q == null) {
            return false;
        } else {
            boolean c = this.f1697q.m3132c();
            boolean d = this.f1697q.m3139d();
            if (this.f1675P == null) {
                this.f1675P = VelocityTracker.obtain();
            }
            this.f1675P.addMovement(motionEvent);
            int a = MotionEventCompat.m987a(motionEvent);
            int b = MotionEventCompat.m989b(motionEvent);
            int i2;
            switch (a) {
                case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                    if (this.f1661B) {
                        this.f1661B = false;
                    }
                    this.f1674O = MotionEventCompat.m990b(motionEvent, 0);
                    i = (int) (motionEvent.getX() + 0.5f);
                    this.f1678S = i;
                    this.f1676Q = i;
                    i = (int) (motionEvent.getY() + 0.5f);
                    this.f1679T = i;
                    this.f1677R = i;
                    if (this.f1673N == 2) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                        setScrollState(1);
                    }
                    int[] iArr = this.am;
                    this.am[1] = 0;
                    iArr[0] = 0;
                    if (c) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    if (d) {
                        i2 |= 2;
                    }
                    startNestedScroll(i2);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    this.f1675P.clear();
                    stopNestedScroll();
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    a = MotionEventCompat.m988a(motionEvent, this.f1674O);
                    if (a >= 0) {
                        b = (int) (MotionEventCompat.m991c(motionEvent, a) + 0.5f);
                        a = (int) (MotionEventCompat.m993d(motionEvent, a) + 0.5f);
                        if (this.f1673N != 1) {
                            b -= this.f1676Q;
                            a -= this.f1677R;
                            if (!c || Math.abs(b) <= this.f1680U) {
                                c = false;
                            } else {
                                this.f1678S = ((b < 0 ? -1 : 1) * this.f1680U) + this.f1676Q;
                                c = true;
                            }
                            if (d && Math.abs(a) > this.f1680U) {
                                i2 = this.f1677R;
                                int i3 = this.f1680U;
                                if (a >= 0) {
                                    i = 1;
                                }
                                this.f1679T = i2 + (i * i3);
                                c = true;
                            }
                            if (c) {
                                setScrollState(1);
                                break;
                            }
                        }
                    }
                    Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.f1674O + " not found. Did any MotionEvents get skipped?");
                    return false;
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    m3593y();
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    this.f1674O = MotionEventCompat.m990b(motionEvent, b);
                    i2 = (int) (MotionEventCompat.m991c(motionEvent, b) + 0.5f);
                    this.f1678S = i2;
                    this.f1676Q = i2;
                    i2 = (int) (MotionEventCompat.m993d(motionEvent, b) + 0.5f);
                    this.f1679T = i2;
                    this.f1677R = i2;
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    m3556c(motionEvent);
                    break;
            }
            if (this.f1673N != 1) {
                z = false;
            }
            return z;
        }
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        int size = this.f1700t.size();
        for (int i = 0; i < size; i++) {
            ((OnItemTouchListener) this.f1700t.get(i)).m3378a(z);
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (this.f1660A || this.f1661B) {
            return false;
        }
        if (m3553b(motionEvent)) {
            m3593y();
            return true;
        } else if (this.f1697q == null) {
            return false;
        } else {
            boolean c = this.f1697q.m3132c();
            boolean d = this.f1697q.m3139d();
            if (this.f1675P == null) {
                this.f1675P = VelocityTracker.obtain();
            }
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            int a = MotionEventCompat.m987a(motionEvent);
            int b = MotionEventCompat.m989b(motionEvent);
            if (a == 0) {
                int[] iArr = this.am;
                this.am[1] = 0;
                iArr[0] = 0;
            }
            obtain.offsetLocation((float) this.am[0], (float) this.am[1]);
            switch (a) {
                case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                    this.f1674O = MotionEventCompat.m990b(motionEvent, 0);
                    a = (int) (motionEvent.getX() + 0.5f);
                    this.f1678S = a;
                    this.f1676Q = a;
                    a = (int) (motionEvent.getY() + 0.5f);
                    this.f1679T = a;
                    this.f1677R = a;
                    if (c) {
                        a = 1;
                    } else {
                        a = 0;
                    }
                    if (d) {
                        a |= 2;
                    }
                    startNestedScroll(a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    this.f1675P.addMovement(obtain);
                    this.f1675P.computeCurrentVelocity(1000, (float) this.f1682W);
                    float f = c ? -VelocityTrackerCompat.m1020a(this.f1675P, this.f1674O) : 0.0f;
                    float f2;
                    if (d) {
                        f2 = -VelocityTrackerCompat.m1021b(this.f1675P, this.f1674O);
                    } else {
                        f2 = 0.0f;
                    }
                    if ((f == 0.0f && r0 == 0.0f) || !m3612b((int) f, (int) r0)) {
                        setScrollState(0);
                    }
                    m3592x();
                    z = true;
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    a = MotionEventCompat.m988a(motionEvent, this.f1674O);
                    if (a >= 0) {
                        int c2 = (int) (MotionEventCompat.m991c(motionEvent, a) + 0.5f);
                        int d2 = (int) (MotionEventCompat.m993d(motionEvent, a) + 0.5f);
                        int i = this.f1678S - c2;
                        a = this.f1679T - d2;
                        if (dispatchNestedPreScroll(i, a, this.al, this.ak)) {
                            i -= this.al[0];
                            a -= this.al[1];
                            obtain.offsetLocation((float) this.ak[0], (float) this.ak[1]);
                            int[] iArr2 = this.am;
                            iArr2[0] = iArr2[0] + this.ak[0];
                            iArr2 = this.am;
                            iArr2[1] = iArr2[1] + this.ak[1];
                        }
                        if (this.f1673N != 1) {
                            boolean z2;
                            if (!c || Math.abs(i) <= this.f1680U) {
                                z2 = false;
                            } else {
                                if (i > 0) {
                                    i -= this.f1680U;
                                } else {
                                    i += this.f1680U;
                                }
                                z2 = true;
                            }
                            if (d && Math.abs(a) > this.f1680U) {
                                if (a > 0) {
                                    a -= this.f1680U;
                                } else {
                                    a += this.f1680U;
                                }
                                z2 = true;
                            }
                            if (z2) {
                                setScrollState(1);
                            }
                        }
                        if (this.f1673N == 1) {
                            this.f1678S = c2 - this.ak[0];
                            this.f1679T = d2 - this.ak[1];
                            if (!c) {
                                i = 0;
                            }
                            if (!d) {
                                a = 0;
                            }
                            if (m3608a(i, a, obtain)) {
                                getParent().requestDisallowInterceptTouchEvent(true);
                                break;
                            }
                        }
                    }
                    Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.f1674O + " not found. Did any MotionEvents get skipped?");
                    return false;
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    m3593y();
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    this.f1674O = MotionEventCompat.m990b(motionEvent, b);
                    a = (int) (MotionEventCompat.m991c(motionEvent, b) + 0.5f);
                    this.f1678S = a;
                    this.f1676Q = a;
                    a = (int) (MotionEventCompat.m993d(motionEvent, b) + 0.5f);
                    this.f1679T = a;
                    this.f1677R = a;
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    m3556c(motionEvent);
                    break;
            }
            if (!z) {
                this.f1675P.addMovement(obtain);
            }
            obtain.recycle();
            return true;
        }
    }

    private void m3592x() {
        if (this.f1675P != null) {
            this.f1675P.clear();
        }
        stopNestedScroll();
        m3591w();
    }

    private void m3593y() {
        m3592x();
        setScrollState(0);
    }

    private void m3556c(MotionEvent motionEvent) {
        int b = MotionEventCompat.m989b(motionEvent);
        if (MotionEventCompat.m990b(motionEvent, b) == this.f1674O) {
            b = b == 0 ? 1 : 0;
            this.f1674O = MotionEventCompat.m990b(motionEvent, b);
            int c = (int) (MotionEventCompat.m991c(motionEvent, b) + 0.5f);
            this.f1678S = c;
            this.f1676Q = c;
            b = (int) (MotionEventCompat.m993d(motionEvent, b) + 0.5f);
            this.f1679T = b;
            this.f1677R = b;
        }
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (!(this.f1697q == null || this.f1660A || (MotionEventCompat.m992c(motionEvent) & 2) == 0 || motionEvent.getAction() != 8)) {
            float f;
            float e;
            if (this.f1697q.m3139d()) {
                f = -MotionEventCompat.m994e(motionEvent, 9);
            } else {
                f = 0.0f;
            }
            if (this.f1697q.m3132c()) {
                e = MotionEventCompat.m994e(motionEvent, 10);
            } else {
                e = 0.0f;
            }
            if (!(f == 0.0f && e == 0.0f)) {
                float scrollFactor = getScrollFactor();
                m3608a((int) (e * scrollFactor), (int) (f * scrollFactor), motionEvent);
            }
        }
        return false;
    }

    private float getScrollFactor() {
        if (this.aa == Float.MIN_VALUE) {
            TypedValue typedValue = new TypedValue();
            if (!getContext().getTheme().resolveAttribute(16842829, typedValue, true)) {
                return 0.0f;
            }
            this.aa = typedValue.getDimension(getContext().getResources().getDisplayMetrics());
        }
        return this.aa;
    }

    protected void onMeasure(int i, int i2) {
        if (this.f1663D) {
            m3610b();
            m3522E();
            if (this.f1688f.f1632i) {
                this.f1688f.f1630g = true;
            } else {
                this.f1684b.m2768e();
                this.f1688f.f1630g = false;
            }
            this.f1663D = false;
            m3607a(false);
        }
        if (this.f1696p != null) {
            this.f1688f.f1624a = this.f1696p.m3328a();
        } else {
            this.f1688f.f1624a = 0;
        }
        if (this.f1697q == null) {
            m3570i(i, i2);
        } else {
            this.f1697q.m3084a(this.f1683a, this.f1688f, i, i2);
        }
        this.f1688f.f1630g = false;
    }

    private void m3570i(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        switch (mode) {
            case Integer.MIN_VALUE:
            case 1073741824:
                break;
            default:
                size = ViewCompat.m1183l(this);
                break;
        }
        switch (mode2) {
            case Integer.MIN_VALUE:
            case 1073741824:
                break;
            default:
                size2 = ViewCompat.m1184m(this);
                break;
        }
        setMeasuredDimension(size, size2);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3 || i2 != i4) {
            m3631h();
        }
    }

    public void setItemAnimator(ItemAnimator itemAnimator) {
        if (this.f1687e != null) {
            this.f1687e.m2948c();
            this.f1687e.m2943a(null);
        }
        this.f1687e = itemAnimator;
        if (this.f1687e != null) {
            this.f1687e.m2943a(this.ae);
        }
    }

    private void m3594z() {
        this.f1668I++;
    }

    private void m3518A() {
        this.f1668I--;
        if (this.f1668I < 1) {
            this.f1668I = 0;
            m3519B();
        }
    }

    boolean m3632i() {
        return this.f1665F != null && this.f1665F.isEnabled();
    }

    private void m3519B() {
        int i = this.f1662C;
        this.f1662C = 0;
        if (i != 0 && m3632i()) {
            AccessibilityEvent obtain = AccessibilityEvent.obtain();
            obtain.setEventType(2048);
            AccessibilityEventCompat.m1442a(obtain, i);
            sendAccessibilityEventUnchecked(obtain);
        }
    }

    public boolean m3633j() {
        return this.f1668I > 0;
    }

    boolean m3609a(AccessibilityEvent accessibilityEvent) {
        int i = 0;
        if (!m3633j()) {
            return false;
        }
        int b;
        if (accessibilityEvent != null) {
            b = AccessibilityEventCompat.m1443b(accessibilityEvent);
        } else {
            b = 0;
        }
        if (b != 0) {
            i = b;
        }
        this.f1662C = i | this.f1662C;
        return true;
    }

    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (!m3609a(accessibilityEvent)) {
            super.sendAccessibilityEventUnchecked(accessibilityEvent);
        }
    }

    public ItemAnimator getItemAnimator() {
        return this.f1687e;
    }

    private void m3520C() {
        if (!this.af && this.f1702v) {
            ViewCompat.m1163a((View) this, this.an);
            this.af = true;
        }
    }

    private boolean m3521D() {
        return this.f1687e != null && this.f1697q.m3159l();
    }

    private void m3522E() {
        boolean z;
        boolean z2 = true;
        if (this.f1667H) {
            this.f1684b.m2755a();
            m3638o();
            this.f1697q.m3143e(this);
        }
        if (this.f1687e == null || !this.f1697q.m3159l()) {
            this.f1684b.m2768e();
        } else {
            this.f1684b.m2762b();
        }
        boolean z3;
        if (this.f1689g || this.f1690h) {
            z3 = true;
        } else {
            z3 = false;
        }
        State state = this.f1688f;
        if (!this.f1704x || this.f1687e == null || (!(this.f1667H || r0 || this.f1697q.f1542a) || (this.f1667H && !this.f1696p.m3343b()))) {
            z = false;
        } else {
            z = true;
        }
        state.f1631h = z;
        State state2 = this.f1688f;
        if (!(this.f1688f.f1631h && r0 && !this.f1667H && m3521D())) {
            z2 = false;
        }
        state2.f1632i = z2;
    }

    void m3634k() {
        if (this.f1696p == null) {
            Log.e("RecyclerView", "No adapter attached; skipping layout");
        } else if (this.f1697q == null) {
            Log.e("RecyclerView", "No layout manager attached; skipping layout");
        } else {
            int b;
            int i;
            ViewHolder b2;
            this.f1686d.m3886a();
            m3610b();
            m3594z();
            m3522E();
            State state = this.f1688f;
            boolean z = this.f1688f.f1631h && this.f1690h;
            state.f1633j = z;
            this.f1690h = false;
            this.f1689g = false;
            this.f1688f.f1630g = this.f1688f.f1632i;
            this.f1688f.f1624a = this.f1696p.m3328a();
            m3538a(this.ai);
            if (this.f1688f.f1631h) {
                b = this.f1685c.m2885b();
                for (i = 0; i < b; i++) {
                    b2 = m3544b(this.f1685c.m2887b(i));
                    if (!b2.m3496c() && (!b2.m3507n() || this.f1696p.m3343b())) {
                        this.f1686d.m3888a(b2, this.f1687e.m2941a(this.f1688f, b2, ItemAnimator.m2939d(b2), b2.m3514u()));
                        if (!(!this.f1688f.f1633j || !b2.m3517x() || b2.m3510q() || b2.m3496c() || b2.m3507n())) {
                            this.f1686d.m3887a(m3595a(b2), b2);
                        }
                    }
                }
            }
            if (this.f1688f.f1632i) {
                m3636m();
                z = this.f1688f.f1629f;
                this.f1688f.f1629f = false;
                this.f1697q.m3083a(this.f1683a, this.f1688f);
                this.f1688f.f1629f = z;
                for (i = 0; i < this.f1685c.m2885b(); i++) {
                    b2 = m3544b(this.f1685c.m2887b(i));
                    if (!(b2.m3496c() || this.f1686d.m3892b(b2))) {
                        b = ItemAnimator.m2939d(b2);
                        boolean a = b2.m3493a(8192);
                        if (!a) {
                            b |= 4096;
                        }
                        ItemHolderInfo a2 = this.f1687e.m2941a(this.f1688f, b2, b, b2.m3514u());
                        if (a) {
                            m3529a(b2, a2);
                        } else {
                            this.f1686d.m3891b(b2, a2);
                        }
                    }
                }
                m3637n();
                this.f1684b.m2765c();
            } else {
                m3637n();
            }
            this.f1688f.f1624a = this.f1696p.m3328a();
            this.f1688f.f1628e = 0;
            this.f1688f.f1630g = false;
            this.f1697q.m3083a(this.f1683a, this.f1688f);
            this.f1688f.f1629f = false;
            this.f1692l = null;
            state = this.f1688f;
            z = this.f1688f.f1631h && this.f1687e != null;
            state.f1631h = z;
            if (this.f1688f.f1631h) {
                b = this.f1685c.m2885b();
                for (i = 0; i < b; i++) {
                    b2 = m3544b(this.f1685c.m2887b(i));
                    if (!b2.m3496c()) {
                        long a3 = m3595a(b2);
                        ItemHolderInfo a4 = this.f1687e.m2940a(this.f1688f, b2);
                        ViewHolder a5 = this.f1686d.m3885a(a3);
                        if (a5 == null || a5.m3496c()) {
                            this.f1686d.m3894c(b2, a4);
                        } else {
                            m3531a(a5, b2, this.f1686d.m3884a(a5), a4);
                        }
                    }
                }
                this.f1686d.m3889a(this.ap);
            }
            m3607a(false);
            this.f1697q.m3119b(this.f1683a);
            this.f1688f.f1627d = this.f1688f.f1624a;
            this.f1667H = false;
            this.f1688f.f1631h = false;
            this.f1688f.f1632i = false;
            m3518A();
            this.f1697q.f1542a = false;
            if (this.f1683a.f1610d != null) {
                this.f1683a.f1610d.clear();
            }
            this.f1686d.m3886a();
            if (m3574j(this.ai[0], this.ai[1])) {
                m3630g(0, 0);
            }
        }
    }

    private void m3529a(ViewHolder viewHolder, ItemHolderInfo itemHolderInfo) {
        viewHolder.m3487a(0, 8192);
        if (this.f1688f.f1633j && viewHolder.m3517x() && !viewHolder.m3510q() && !viewHolder.m3496c()) {
            this.f1686d.m3887a(m3595a(viewHolder), viewHolder);
        }
        this.f1686d.m3888a(viewHolder, itemHolderInfo);
    }

    private void m3538a(int[] iArr) {
        int b = this.f1685c.m2885b();
        if (b == 0) {
            iArr[0] = 0;
            iArr[1] = 0;
            return;
        }
        int i = Integer.MAX_VALUE;
        int i2 = Integer.MIN_VALUE;
        int i3 = 0;
        while (i3 < b) {
            int i4;
            ViewHolder b2 = m3544b(this.f1685c.m2887b(i3));
            if (b2.m3496c()) {
                i4 = i;
            } else {
                i4 = b2.m3497d();
                if (i4 < i) {
                    i = i4;
                }
                if (i4 > i2) {
                    i2 = i4;
                    i4 = i;
                } else {
                    i4 = i;
                }
            }
            i3++;
            i = i4;
        }
        iArr[0] = i;
        iArr[1] = i2;
    }

    private boolean m3574j(int i, int i2) {
        int b = this.f1685c.m2885b();
        if (b != 0) {
            for (int i3 = 0; i3 < b; i3++) {
                ViewHolder b2 = m3544b(this.f1685c.m2887b(i3));
                if (!b2.m3496c()) {
                    int d = b2.m3497d();
                    if (d < i || d > i2) {
                        return true;
                    }
                }
            }
            return false;
        } else if (i == 0 && i2 == 0) {
            return false;
        } else {
            return true;
        }
    }

    protected void removeDetachedView(View view, boolean z) {
        ViewHolder b = m3544b(view);
        if (b != null) {
            if (b.m3511r()) {
                b.m3506m();
            } else if (!b.m3496c()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + b);
            }
        }
        m3569h(view);
        super.removeDetachedView(view, z);
    }

    long m3595a(ViewHolder viewHolder) {
        return this.f1696p.m3343b() ? viewHolder.m3500g() : (long) viewHolder.f1643b;
    }

    private void m3530a(ViewHolder viewHolder, ItemHolderInfo itemHolderInfo, ItemHolderInfo itemHolderInfo2) {
        viewHolder.m3492a(false);
        if (this.f1687e.m2947b(viewHolder, itemHolderInfo, itemHolderInfo2)) {
            m3520C();
        }
    }

    private void m3546b(ViewHolder viewHolder, ItemHolderInfo itemHolderInfo, ItemHolderInfo itemHolderInfo2) {
        m3545b(viewHolder);
        viewHolder.m3492a(false);
        if (this.f1687e.m2944a(viewHolder, itemHolderInfo, itemHolderInfo2)) {
            m3520C();
        }
    }

    private void m3531a(ViewHolder viewHolder, ViewHolder viewHolder2, ItemHolderInfo itemHolderInfo, ItemHolderInfo itemHolderInfo2) {
        viewHolder.m3492a(false);
        if (viewHolder != viewHolder2) {
            viewHolder.f1648g = viewHolder2;
            m3545b(viewHolder);
            this.f1683a.m3418d(viewHolder);
            viewHolder2.m3492a(false);
            viewHolder2.f1649h = viewHolder;
        }
        if (this.f1687e.m2945a(viewHolder, viewHolder2, itemHolderInfo, itemHolderInfo2)) {
            m3520C();
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        m3610b();
        TraceCompat.m698a("RV OnLayout");
        m3634k();
        TraceCompat.m697a();
        m3607a(false);
        this.f1704x = true;
    }

    public void requestLayout() {
        if (this.f1705y || this.f1660A) {
            this.f1706z = true;
        } else {
            super.requestLayout();
        }
    }

    void m3635l() {
        int c = this.f1685c.m2888c();
        for (int i = 0; i < c; i++) {
            ((LayoutParams) this.f1685c.m2889c(i).getLayoutParams()).f1602c = true;
        }
        this.f1683a.m3426j();
    }

    public void draw(Canvas canvas) {
        int i;
        int i2;
        int i3 = 1;
        int i4 = 0;
        super.draw(canvas);
        int size = this.f1699s.size();
        for (i = 0; i < size; i++) {
            ((ItemDecoration) this.f1699s.get(i)).m3372b(canvas, this, this.f1688f);
        }
        if (this.f1669J == null || this.f1669J.m1762a()) {
            i2 = 0;
        } else {
            i = canvas.save();
            i2 = this.f1693m ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((float) (i2 + (-getHeight())), 0.0f);
            if (this.f1669J == null || !this.f1669J.m1766a(canvas)) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            canvas.restoreToCount(i);
        }
        if (!(this.f1670K == null || this.f1670K.m1762a())) {
            size = canvas.save();
            if (this.f1693m) {
                canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
            }
            if (this.f1670K == null || !this.f1670K.m1766a(canvas)) {
                i = 0;
            } else {
                i = 1;
            }
            i2 |= i;
            canvas.restoreToCount(size);
        }
        if (!(this.f1671L == null || this.f1671L.m1762a())) {
            size = canvas.save();
            int width = getWidth();
            if (this.f1693m) {
                i = getPaddingTop();
            } else {
                i = 0;
            }
            canvas.rotate(90.0f);
            canvas.translate((float) (-i), (float) (-width));
            if (this.f1671L == null || !this.f1671L.m1766a(canvas)) {
                i = 0;
            } else {
                i = 1;
            }
            i2 |= i;
            canvas.restoreToCount(size);
        }
        if (!(this.f1672M == null || this.f1672M.m1762a())) {
            i = canvas.save();
            canvas.rotate(180.0f);
            if (this.f1693m) {
                canvas.translate((float) ((-getWidth()) + getPaddingRight()), (float) ((-getHeight()) + getPaddingBottom()));
            } else {
                canvas.translate((float) (-getWidth()), (float) (-getHeight()));
            }
            if (this.f1672M != null && this.f1672M.m1766a(canvas)) {
                i4 = 1;
            }
            i2 |= i4;
            canvas.restoreToCount(i);
        }
        if (i2 != 0 || this.f1687e == null || this.f1699s.size() <= 0 || !this.f1687e.m2946b()) {
            i3 = i2;
        }
        if (i3 != 0) {
            ViewCompat.m1174d(this);
        }
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = this.f1699s.size();
        for (int i = 0; i < size; i++) {
            ((ItemDecoration) this.f1699s.get(i)).m3368a(canvas, this, this.f1688f);
        }
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && this.f1697q.m3105a((LayoutParams) layoutParams);
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        if (this.f1697q != null) {
            return this.f1697q.m3074a();
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager");
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        if (this.f1697q != null) {
            return this.f1697q.m3075a(getContext(), attributeSet);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager");
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        if (this.f1697q != null) {
            return this.f1697q.m3076a(layoutParams);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager");
    }

    void m3636m() {
        int c = this.f1685c.m2888c();
        for (int i = 0; i < c; i++) {
            ViewHolder b = m3544b(this.f1685c.m2889c(i));
            if (!b.m3496c()) {
                b.m3494b();
            }
        }
    }

    void m3637n() {
        int c = this.f1685c.m2888c();
        for (int i = 0; i < c; i++) {
            ViewHolder b = m3544b(this.f1685c.m2889c(i));
            if (!b.m3496c()) {
                b.m3486a();
            }
        }
        this.f1683a.m3425i();
    }

    void m3619d(int i, int i2) {
        int i3;
        int c = this.f1685c.m2888c();
        int i4;
        int i5;
        if (i < i2) {
            i3 = -1;
            i4 = i2;
            i5 = i;
        } else {
            i3 = 1;
            i4 = i;
            i5 = i2;
        }
        for (int i6 = 0; i6 < c; i6++) {
            ViewHolder b = m3544b(this.f1685c.m2889c(i6));
            if (b != null && b.f1643b >= r3 && b.f1643b <= r2) {
                if (b.f1643b == i) {
                    b.m3489a(i2 - i, false);
                } else {
                    b.m3489a(i3, false);
                }
                this.f1688f.f1629f = true;
            }
        }
        this.f1683a.m3399a(i, i2);
        requestLayout();
    }

    void m3623e(int i, int i2) {
        int c = this.f1685c.m2888c();
        for (int i3 = 0; i3 < c; i3++) {
            ViewHolder b = m3544b(this.f1685c.m2889c(i3));
            if (!(b == null || b.m3496c() || b.f1643b < i)) {
                b.m3489a(i2, false);
                this.f1688f.f1629f = true;
            }
        }
        this.f1683a.m3407b(i, i2);
        requestLayout();
    }

    void m3602a(int i, int i2, boolean z) {
        int i3 = i + i2;
        int c = this.f1685c.m2888c();
        for (int i4 = 0; i4 < c; i4++) {
            ViewHolder b = m3544b(this.f1685c.m2889c(i4));
            if (!(b == null || b.m3496c())) {
                if (b.f1643b >= i3) {
                    b.m3489a(-i2, z);
                    this.f1688f.f1629f = true;
                } else if (b.f1643b >= i) {
                    b.m3488a(i - 1, -i2, z);
                    this.f1688f.f1629f = true;
                }
            }
        }
        this.f1683a.m3408b(i, i2, z);
        requestLayout();
    }

    void m3601a(int i, int i2, Object obj) {
        int c = this.f1685c.m2888c();
        int i3 = i + i2;
        for (int i4 = 0; i4 < c; i4++) {
            View c2 = this.f1685c.m2889c(i4);
            ViewHolder b = m3544b(c2);
            if (b != null && !b.m3496c() && b.f1643b >= i && b.f1643b < i3) {
                b.m3495b(2);
                b.m3491a(obj);
                ((LayoutParams) c2.getLayoutParams()).f1602c = true;
            }
        }
        this.f1683a.m3413c(i, i2);
    }

    private boolean m3557c(ViewHolder viewHolder) {
        return this.f1687e == null || this.f1687e.m2957g(viewHolder);
    }

    private void m3523F() {
        if (!this.f1667H) {
            this.f1667H = true;
            int c = this.f1685c.m2888c();
            for (int i = 0; i < c; i++) {
                ViewHolder b = m3544b(this.f1685c.m2889c(i));
                if (!(b == null || b.m3496c())) {
                    b.m3495b(512);
                }
            }
            this.f1683a.m3423g();
        }
    }

    void m3638o() {
        int c = this.f1685c.m2888c();
        for (int i = 0; i < c; i++) {
            ViewHolder b = m3544b(this.f1685c.m2889c(i));
            if (!(b == null || b.m3496c())) {
                b.m3495b(6);
            }
        }
        m3635l();
        this.f1683a.m3424h();
    }

    public ViewHolder m3597a(View view) {
        Object parent = view.getParent();
        if (parent == null || parent == this) {
            return m3544b(view);
        }
        throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
    }

    static ViewHolder m3544b(View view) {
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).f1600a;
    }

    public int m3613c(View view) {
        ViewHolder b = m3544b(view);
        return b != null ? b.m3497d() : -1;
    }

    ViewHolder m3596a(int i, boolean z) {
        int c = this.f1685c.m2888c();
        for (int i2 = 0; i2 < c; i2++) {
            ViewHolder b = m3544b(this.f1685c.m2889c(i2));
            if (!(b == null || b.m3510q())) {
                if (z) {
                    if (b.f1643b == i) {
                        return b;
                    }
                } else if (b.m3497d() == i) {
                    return b;
                }
            }
        }
        return null;
    }

    public boolean drawChild(Canvas canvas, View view, long j) {
        return super.drawChild(canvas, view, j);
    }

    public void m3615c(int i) {
        int b = this.f1685c.m2885b();
        for (int i2 = 0; i2 < b; i2++) {
            this.f1685c.m2887b(i2).offsetTopAndBottom(i);
        }
    }

    public void m3620d(View view) {
    }

    public void m3624e(View view) {
    }

    public void m3618d(int i) {
        int b = this.f1685c.m2885b();
        for (int i2 = 0; i2 < b; i2++) {
            this.f1685c.m2887b(i2).offsetLeftAndRight(i);
        }
    }

    Rect m3625f(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (!layoutParams.f1602c) {
            return layoutParams.f1601b;
        }
        Rect rect = layoutParams.f1601b;
        rect.set(0, 0, 0, 0);
        int size = this.f1699s.size();
        for (int i = 0; i < size; i++) {
            this.f1695o.set(0, 0, 0, 0);
            ((ItemDecoration) this.f1699s.get(i)).m3370a(this.f1695o, view, this, this.f1688f);
            rect.left += this.f1695o.left;
            rect.top += this.f1695o.top;
            rect.right += this.f1695o.right;
            rect.bottom += this.f1695o.bottom;
        }
        layoutParams.f1602c = false;
        return rect;
    }

    public void m3628f(int i, int i2) {
    }

    void m3630g(int i, int i2) {
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX, scrollY);
        m3628f(i, i2);
        if (this.ac != null) {
            this.ac.m3382a(this, i, i2);
        }
        if (this.ad != null) {
            for (scrollY = this.ad.size() - 1; scrollY >= 0; scrollY--) {
                ((OnScrollListener) this.ad.get(scrollY)).m3382a(this, i, i2);
            }
        }
    }

    public void m3622e(int i) {
    }

    void m3627f(int i) {
        if (this.f1697q != null) {
            this.f1697q.m3156j(i);
        }
        m3622e(i);
        if (this.ac != null) {
            this.ac.m3381a(this, i);
        }
        if (this.ad != null) {
            for (int size = this.ad.size() - 1; size >= 0; size--) {
                ((OnScrollListener) this.ad.get(size)).m3381a(this, i);
            }
        }
    }

    public boolean m3639p() {
        return !this.f1704x || this.f1667H || this.f1684b.m2767d();
    }

    private void m3524G() {
        int b = this.f1685c.m2885b();
        for (int i = 0; i < b; i++) {
            View b2 = this.f1685c.m2887b(i);
            ViewHolder a = m3597a(b2);
            if (!(a == null || a.f1649h == null)) {
                View view = a.f1649h.f1642a;
                int left = b2.getLeft();
                int top = b2.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
    }

    private void m3569h(View view) {
        ViewHolder b = m3544b(view);
        m3624e(view);
        if (!(this.f1696p == null || b == null)) {
            this.f1696p.m3349d(b);
        }
        if (this.f1666G != null) {
            for (int size = this.f1666G.size() - 1; size >= 0; size--) {
                ((OnChildAttachStateChangeListener) this.f1666G.get(size)).m3377b(view);
            }
        }
    }

    private void m3572i(View view) {
        ViewHolder b = m3544b(view);
        m3620d(view);
        if (!(this.f1696p == null || b == null)) {
            this.f1696p.m3347c(b);
        }
        if (this.f1666G != null) {
            for (int size = this.f1666G.size() - 1; size >= 0; size--) {
                ((OnChildAttachStateChangeListener) this.f1666G.get(size)).m3376a(view);
            }
        }
    }

    private int m3560d(ViewHolder viewHolder) {
        if (viewHolder.m3493a(524) || !viewHolder.m3509p()) {
            return -1;
        }
        return this.f1684b.m2764c(viewHolder.f1643b);
    }

    public void setNestedScrollingEnabled(boolean z) {
        this.aj.m1001a(z);
    }

    public boolean isNestedScrollingEnabled() {
        return this.aj.m1002a();
    }

    public boolean startNestedScroll(int i) {
        return this.aj.m1005a(i);
    }

    public void stopNestedScroll() {
        this.aj.m1009c();
    }

    public boolean hasNestedScrollingParent() {
        return this.aj.m1008b();
    }

    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.aj.m1006a(i, i2, i3, i4, iArr);
    }

    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return this.aj.m1007a(i, i2, iArr, iArr2);
    }

    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.aj.m1004a(f, f2, z);
    }

    public boolean dispatchNestedPreFling(float f, float f2) {
        return this.aj.m1003a(f, f2);
    }

    protected int getChildDrawingOrder(int i, int i2) {
        if (this.ah == null) {
            return super.getChildDrawingOrder(i, i2);
        }
        return this.ah.m3361a(i, i2);
    }
}
