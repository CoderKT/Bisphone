package android.support.v7.widget;

import android.content.Context;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.SmoothScroller;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import app.C0110R;
import java.util.List;
import se.emilsjolander.stickylistheaders.C1128R;

public class LinearLayoutManager extends LayoutManager {
    int f1547a;
    OrientationHelper f1548b;
    boolean f1549c;
    int f1550d;
    int f1551e;
    SavedState f1552f;
    final AnchorInfo f1553g;
    private LayoutState f1554k;
    private boolean f1555l;
    private boolean f1556m;
    private boolean f1557n;
    private boolean f1558o;
    private boolean f1559p;

    /* renamed from: android.support.v7.widget.LinearLayoutManager.1 */
    class C00821 extends LinearSmoothScroller {
        final /* synthetic */ LinearLayoutManager f1519a;

        C00821(LinearLayoutManager linearLayoutManager, Context context) {
            this.f1519a = linearLayoutManager;
            super(context);
        }

        public PointF m3047a(int i) {
            return this.f1519a.m3227c(i);
        }
    }

    class AnchorInfo {
        int f1520a;
        int f1521b;
        boolean f1522c;
        final /* synthetic */ LinearLayoutManager f1523d;

        AnchorInfo(LinearLayoutManager linearLayoutManager) {
            this.f1523d = linearLayoutManager;
        }

        void m3050a() {
            this.f1520a = -1;
            this.f1521b = Integer.MIN_VALUE;
            this.f1522c = false;
        }

        void m3052b() {
            this.f1521b = this.f1522c ? this.f1523d.f1548b.m3275d() : this.f1523d.f1548b.m3273c();
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.f1520a + ", mCoordinate=" + this.f1521b + ", mLayoutFromEnd=" + this.f1522c + '}';
        }

        private boolean m3049a(View view, State state) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            return !layoutParams.m3373a() && layoutParams.m3375c() >= 0 && layoutParams.m3375c() < state.m3460d();
        }

        public void m3051a(View view) {
            int b = this.f1523d.f1548b.m3271b();
            if (b >= 0) {
                m3053b(view);
                return;
            }
            this.f1520a = this.f1523d.m3135d(view);
            int c;
            if (this.f1522c) {
                b = (this.f1523d.f1548b.m3275d() - b) - this.f1523d.f1548b.m3272b(view);
                this.f1521b = this.f1523d.f1548b.m3275d() - b;
                if (b > 0) {
                    c = this.f1521b - this.f1523d.f1548b.m3274c(view);
                    int c2 = this.f1523d.f1548b.m3273c();
                    c -= c2 + Math.min(this.f1523d.f1548b.m3268a(view) - c2, 0);
                    if (c < 0) {
                        this.f1521b = Math.min(b, -c) + this.f1521b;
                        return;
                    }
                    return;
                }
                return;
            }
            c = this.f1523d.f1548b.m3268a(view);
            c2 = c - this.f1523d.f1548b.m3273c();
            this.f1521b = c;
            if (c2 > 0) {
                b = (this.f1523d.f1548b.m3275d() - Math.min(0, (this.f1523d.f1548b.m3275d() - b) - this.f1523d.f1548b.m3272b(view))) - (c + this.f1523d.f1548b.m3274c(view));
                if (b < 0) {
                    this.f1521b -= Math.min(c2, -b);
                }
            }
        }

        public void m3053b(View view) {
            if (this.f1522c) {
                this.f1521b = this.f1523d.f1548b.m3272b(view) + this.f1523d.f1548b.m3271b();
            } else {
                this.f1521b = this.f1523d.f1548b.m3268a(view);
            }
            this.f1520a = this.f1523d.m3135d(view);
        }
    }

    public class LayoutChunkResult {
        public int f1524a;
        public boolean f1525b;
        public boolean f1526c;
        public boolean f1527d;

        protected LayoutChunkResult() {
        }

        void m3054a() {
            this.f1524a = 0;
            this.f1525b = false;
            this.f1526c = false;
            this.f1527d = false;
        }
    }

    class LayoutState {
        boolean f1528a;
        int f1529b;
        int f1530c;
        int f1531d;
        int f1532e;
        int f1533f;
        int f1534g;
        int f1535h;
        boolean f1536i;
        int f1537j;
        List<ViewHolder> f1538k;

        LayoutState() {
            this.f1528a = true;
            this.f1535h = 0;
            this.f1536i = false;
            this.f1538k = null;
        }

        boolean m3059a(State state) {
            return this.f1531d >= 0 && this.f1531d < state.m3460d();
        }

        View m3056a(Recycler recycler) {
            if (this.f1538k != null) {
                return m3055b();
            }
            View b = recycler.m3405b(this.f1531d);
            this.f1531d += this.f1532e;
            return b;
        }

        private View m3055b() {
            int size = this.f1538k.size();
            for (int i = 0; i < size; i++) {
                View view = ((ViewHolder) this.f1538k.get(i)).f1642a;
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                if (!layoutParams.m3373a() && this.f1531d == layoutParams.m3375c()) {
                    m3058a(view);
                    return view;
                }
            }
            return null;
        }

        public void m3057a() {
            m3058a(null);
        }

        public void m3058a(View view) {
            View b = m3060b(view);
            if (b == null) {
                this.f1531d = -1;
            } else {
                this.f1531d = ((LayoutParams) b.getLayoutParams()).m3375c();
            }
        }

        public View m3060b(View view) {
            int size = this.f1538k.size();
            View view2 = null;
            int i = Integer.MAX_VALUE;
            int i2 = 0;
            while (i2 < size) {
                int i3;
                View view3;
                View view4 = ((ViewHolder) this.f1538k.get(i2)).f1642a;
                LayoutParams layoutParams = (LayoutParams) view4.getLayoutParams();
                if (view4 != view) {
                    if (layoutParams.m3373a()) {
                        i3 = i;
                        view3 = view2;
                    } else {
                        i3 = (layoutParams.m3375c() - this.f1531d) * this.f1532e;
                        if (i3 < 0) {
                            i3 = i;
                            view3 = view2;
                        } else if (i3 < i) {
                            if (i3 == 0) {
                                return view4;
                            }
                            view3 = view4;
                        }
                    }
                    i2++;
                    view2 = view3;
                    i = i3;
                }
                i3 = i;
                view3 = view2;
                i2++;
                view2 = view3;
                i = i3;
            }
            return view2;
        }
    }

    public class SavedState implements Parcelable {
        public static final Creator<SavedState> CREATOR;
        int f1539a;
        int f1540b;
        boolean f1541c;

        /* renamed from: android.support.v7.widget.LinearLayoutManager.SavedState.1 */
        final class C00831 implements Creator<SavedState> {
            C00831() {
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m3061a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m3062a(i);
            }

            public SavedState m3061a(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] m3062a(int i) {
                return new SavedState[i];
            }
        }

        SavedState(Parcel parcel) {
            boolean z = true;
            this.f1539a = parcel.readInt();
            this.f1540b = parcel.readInt();
            if (parcel.readInt() != 1) {
                z = false;
            }
            this.f1541c = z;
        }

        public SavedState(SavedState savedState) {
            this.f1539a = savedState.f1539a;
            this.f1540b = savedState.f1540b;
            this.f1541c = savedState.f1541c;
        }

        boolean m3063a() {
            return this.f1539a >= 0;
        }

        void m3064b() {
            this.f1539a = -1;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f1539a);
            parcel.writeInt(this.f1540b);
            parcel.writeInt(this.f1541c ? 1 : 0);
        }

        static {
            CREATOR = new C00831();
        }
    }

    public LinearLayoutManager(Context context) {
        this(context, 1, false);
    }

    public LinearLayoutManager(Context context, int i, boolean z) {
        this.f1556m = false;
        this.f1549c = false;
        this.f1557n = false;
        this.f1558o = true;
        this.f1550d = -1;
        this.f1551e = Integer.MIN_VALUE;
        this.f1552f = null;
        this.f1553g = new AnchorInfo(this);
        m3210a(i);
        m3220a(z);
    }

    public LayoutParams m3206a() {
        return new LayoutParams(-2, -2);
    }

    public void m3216a(RecyclerView recyclerView, Recycler recycler) {
        super.m3093a(recyclerView, recycler);
        if (this.f1559p) {
            m3127c(recycler);
            recycler.m3397a();
        }
    }

    public void m3218a(AccessibilityEvent accessibilityEvent) {
        super.m3102a(accessibilityEvent);
        if (m3166q() > 0) {
            AccessibilityRecordCompat a = AccessibilityEventCompat.m1441a(accessibilityEvent);
            a.m1638b(m3238h());
            a.m1639c(m3240j());
        }
    }

    public Parcelable m3223b() {
        if (this.f1552f != null) {
            return new SavedState(this.f1552f);
        }
        Parcelable savedState = new SavedState();
        if (m3166q() > 0) {
            m3235f();
            boolean z = this.f1555l ^ this.f1549c;
            savedState.f1541c = z;
            View B;
            if (z) {
                B = m3176B();
                savedState.f1540b = this.f1548b.m3275d() - this.f1548b.m3272b(B);
                savedState.f1539a = m3135d(B);
                return savedState;
            }
            B = m3175A();
            savedState.f1539a = m3135d(B);
            savedState.f1540b = this.f1548b.m3268a(B) - this.f1548b.m3273c();
            return savedState;
        }
        savedState.m3064b();
        return savedState;
    }

    public void m3212a(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.f1552f = (SavedState) parcelable;
            m3161m();
        }
    }

    public boolean m3228c() {
        return this.f1547a == 0;
    }

    public boolean m3231d() {
        return this.f1547a == 1;
    }

    public void m3210a(int i) {
        if (i == 0 || i == 1) {
            m3219a(null);
            if (i != this.f1547a) {
                this.f1547a = i;
                this.f1548b = null;
                m3161m();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("invalid orientation:" + i);
    }

    private void m3202z() {
        boolean z = true;
        if (this.f1547a == 1 || !m3233e()) {
            this.f1549c = this.f1556m;
            return;
        }
        if (this.f1556m) {
            z = false;
        }
        this.f1549c = z;
    }

    public void m3220a(boolean z) {
        m3219a(null);
        if (z != this.f1556m) {
            this.f1556m = z;
            m3161m();
        }
    }

    public View m3224b(int i) {
        int q = m3166q();
        if (q == 0) {
            return null;
        }
        int d = i - m3135d(m3150g(0));
        if (d >= 0 && d < q) {
            View g = m3150g(d);
            if (m3135d(g) == i) {
                return g;
            }
        }
        return super.m3117b(i);
    }

    protected int m3205a(State state) {
        if (state.m3459c()) {
            return this.f1548b.m3278f();
        }
        return 0;
    }

    public void m3217a(RecyclerView recyclerView, State state, int i) {
        SmoothScroller c00821 = new C00821(this, recyclerView.getContext());
        c00821.m3024d(i);
        m3088a(c00821);
    }

    public PointF m3227c(int i) {
        int i2 = 1;
        boolean z = false;
        if (m3166q() == 0) {
            return null;
        }
        if (i < m3135d(m3150g(0))) {
            z = true;
        }
        if (z != this.f1549c) {
            i2 = -1;
        }
        if (this.f1547a == 0) {
            return new PointF((float) i2, 0.0f);
        }
        return new PointF(0.0f, (float) i2);
    }

    public void m3213a(Recycler recycler, State state) {
        if (!(this.f1552f == null && this.f1550d == -1) && state.m3460d() == 0) {
            m3127c(recycler);
            return;
        }
        int i;
        int d;
        if (this.f1552f != null && this.f1552f.m3063a()) {
            this.f1550d = this.f1552f.f1539a;
        }
        m3235f();
        this.f1554k.f1528a = false;
        m3202z();
        this.f1553g.m3050a();
        this.f1553g.f1522c = this.f1549c ^ this.f1557n;
        m3190b(recycler, state, this.f1553g);
        int a = m3205a(state);
        if (this.f1554k.f1537j >= 0) {
            i = 0;
        } else {
            i = a;
            a = 0;
        }
        i += this.f1548b.m3273c();
        a += this.f1548b.m3279g();
        if (!(!state.m3457a() || this.f1550d == -1 || this.f1551e == Integer.MIN_VALUE)) {
            View b = m3224b(this.f1550d);
            if (b != null) {
                if (this.f1549c) {
                    d = (this.f1548b.m3275d() - this.f1548b.m3272b(b)) - this.f1551e;
                } else {
                    d = this.f1551e - (this.f1548b.m3268a(b) - this.f1548b.m3273c());
                }
                if (d > 0) {
                    i += d;
                } else {
                    a -= d;
                }
            }
        }
        m3214a(recycler, state, this.f1553g);
        m3082a(recycler);
        this.f1554k.f1536i = state.m3457a();
        int i2;
        if (this.f1553g.f1522c) {
            m3187b(this.f1553g);
            this.f1554k.f1535h = i;
            m3204a(recycler, this.f1554k, state, false);
            i = this.f1554k.f1529b;
            i2 = this.f1554k.f1531d;
            if (this.f1554k.f1530c > 0) {
                a += this.f1554k.f1530c;
            }
            m3180a(this.f1553g);
            this.f1554k.f1535h = a;
            LayoutState layoutState = this.f1554k;
            layoutState.f1531d += this.f1554k.f1532e;
            m3204a(recycler, this.f1554k, state, false);
            d = this.f1554k.f1529b;
            if (this.f1554k.f1530c > 0) {
                a = this.f1554k.f1530c;
                m3193d(i2, i);
                this.f1554k.f1535h = a;
                m3204a(recycler, this.f1554k, state, false);
                a = this.f1554k.f1529b;
            } else {
                a = i;
            }
            i = a;
            a = d;
        } else {
            m3180a(this.f1553g);
            this.f1554k.f1535h = a;
            m3204a(recycler, this.f1554k, state, false);
            a = this.f1554k.f1529b;
            d = this.f1554k.f1531d;
            if (this.f1554k.f1530c > 0) {
                i += this.f1554k.f1530c;
            }
            m3187b(this.f1553g);
            this.f1554k.f1535h = i;
            LayoutState layoutState2 = this.f1554k;
            layoutState2.f1531d += this.f1554k.f1532e;
            m3204a(recycler, this.f1554k, state, false);
            i = this.f1554k.f1529b;
            if (this.f1554k.f1530c > 0) {
                i2 = this.f1554k.f1530c;
                m3191c(d, a);
                this.f1554k.f1535h = i2;
                m3204a(recycler, this.f1554k, state, false);
                a = this.f1554k.f1529b;
            }
        }
        if (m3166q() > 0) {
            int b2;
            if ((this.f1549c ^ this.f1557n) != 0) {
                d = m3177a(a, recycler, state, true);
                i += d;
                a += d;
                b2 = m3185b(i, recycler, state, false);
                i += b2;
                a += b2;
            } else {
                d = m3185b(i, recycler, state, true);
                i += d;
                a += d;
                b2 = m3177a(a, recycler, state, false);
                i += b2;
                a += b2;
            }
        }
        m3189b(recycler, state, i, a);
        if (!state.m3457a()) {
            this.f1550d = -1;
            this.f1551e = Integer.MIN_VALUE;
            this.f1548b.m3269a();
        }
        this.f1555l = this.f1557n;
        this.f1552f = null;
    }

    void m3214a(Recycler recycler, State state, AnchorInfo anchorInfo) {
    }

    private void m3189b(Recycler recycler, State state, int i, int i2) {
        if (state.m3458b() && m3166q() != 0 && !state.m3457a() && m3242l()) {
            int i3 = 0;
            int i4 = 0;
            List b = recycler.m3406b();
            int size = b.size();
            int d = m3135d(m3150g(0));
            int i5 = 0;
            while (i5 < size) {
                int i6;
                int i7;
                ViewHolder viewHolder = (ViewHolder) b.get(i5);
                if (viewHolder.m3510q()) {
                    i6 = i4;
                    i7 = i3;
                } else {
                    if (((viewHolder.m3497d() < d) != this.f1549c ? -1 : 1) == -1) {
                        i7 = this.f1548b.m3274c(viewHolder.f1642a) + i3;
                        i6 = i4;
                    } else {
                        i6 = this.f1548b.m3274c(viewHolder.f1642a) + i4;
                        i7 = i3;
                    }
                }
                i5++;
                i3 = i7;
                i4 = i6;
            }
            this.f1554k.f1538k = b;
            if (i3 > 0) {
                m3193d(m3135d(m3175A()), i);
                this.f1554k.f1535h = i3;
                this.f1554k.f1530c = 0;
                this.f1554k.m3057a();
                m3204a(recycler, this.f1554k, state, false);
            }
            if (i4 > 0) {
                m3191c(m3135d(m3176B()), i2);
                this.f1554k.f1535h = i4;
                this.f1554k.f1530c = 0;
                this.f1554k.m3057a();
                m3204a(recycler, this.f1554k, state, false);
            }
            this.f1554k.f1538k = null;
        }
    }

    private void m3190b(Recycler recycler, State state, AnchorInfo anchorInfo) {
        if (!m3184a(state, anchorInfo) && !m3192c(recycler, state, anchorInfo)) {
            anchorInfo.m3052b();
            anchorInfo.f1520a = this.f1557n ? state.m3460d() - 1 : 0;
        }
    }

    private boolean m3192c(Recycler recycler, State state, AnchorInfo anchorInfo) {
        boolean z = false;
        if (m3166q() == 0) {
            return false;
        }
        View x = m3173x();
        if (x != null && anchorInfo.m3049a(x, state)) {
            anchorInfo.m3051a(x);
            return true;
        } else if (this.f1555l != this.f1557n) {
            return false;
        } else {
            x = anchorInfo.f1522c ? m3194f(recycler, state) : m3195g(recycler, state);
            if (x == null) {
                return false;
            }
            anchorInfo.m3053b(x);
            if (!state.m3457a() && m3242l()) {
                if (this.f1548b.m3268a(x) >= this.f1548b.m3275d() || this.f1548b.m3272b(x) < this.f1548b.m3273c()) {
                    z = true;
                }
                if (z) {
                    anchorInfo.f1521b = anchorInfo.f1522c ? this.f1548b.m3275d() : this.f1548b.m3273c();
                }
            }
            return true;
        }
    }

    private boolean m3184a(State state, AnchorInfo anchorInfo) {
        boolean z = false;
        if (state.m3457a() || this.f1550d == -1) {
            return false;
        }
        if (this.f1550d < 0 || this.f1550d >= state.m3460d()) {
            this.f1550d = -1;
            this.f1551e = Integer.MIN_VALUE;
            return false;
        }
        anchorInfo.f1520a = this.f1550d;
        if (this.f1552f != null && this.f1552f.m3063a()) {
            anchorInfo.f1522c = this.f1552f.f1541c;
            if (anchorInfo.f1522c) {
                anchorInfo.f1521b = this.f1548b.m3275d() - this.f1552f.f1540b;
                return true;
            }
            anchorInfo.f1521b = this.f1548b.m3273c() + this.f1552f.f1540b;
            return true;
        } else if (this.f1551e == Integer.MIN_VALUE) {
            View b = m3224b(this.f1550d);
            if (b == null) {
                if (m3166q() > 0) {
                    boolean z2;
                    if (this.f1550d < m3135d(m3150g(0))) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (z2 == this.f1549c) {
                        z = true;
                    }
                    anchorInfo.f1522c = z;
                }
                anchorInfo.m3052b();
                return true;
            } else if (this.f1548b.m3274c(b) > this.f1548b.m3278f()) {
                anchorInfo.m3052b();
                return true;
            } else if (this.f1548b.m3268a(b) - this.f1548b.m3273c() < 0) {
                anchorInfo.f1521b = this.f1548b.m3273c();
                anchorInfo.f1522c = false;
                return true;
            } else if (this.f1548b.m3275d() - this.f1548b.m3272b(b) < 0) {
                anchorInfo.f1521b = this.f1548b.m3275d();
                anchorInfo.f1522c = true;
                return true;
            } else {
                anchorInfo.f1521b = anchorInfo.f1522c ? this.f1548b.m3272b(b) + this.f1548b.m3271b() : this.f1548b.m3268a(b);
                return true;
            }
        } else {
            anchorInfo.f1522c = this.f1549c;
            if (this.f1549c) {
                anchorInfo.f1521b = this.f1548b.m3275d() - this.f1551e;
                return true;
            }
            anchorInfo.f1521b = this.f1548b.m3273c() + this.f1551e;
            return true;
        }
    }

    private int m3177a(int i, Recycler recycler, State state, boolean z) {
        int d = this.f1548b.m3275d() - i;
        if (d <= 0) {
            return 0;
        }
        d = -m3225c(-d, recycler, state);
        int i2 = i + d;
        if (!z) {
            return d;
        }
        i2 = this.f1548b.m3275d() - i2;
        if (i2 <= 0) {
            return d;
        }
        this.f1548b.m3270a(i2);
        return d + i2;
    }

    private int m3185b(int i, Recycler recycler, State state, boolean z) {
        int c = i - this.f1548b.m3273c();
        if (c <= 0) {
            return 0;
        }
        c = -m3225c(c, recycler, state);
        int i2 = i + c;
        if (!z) {
            return c;
        }
        i2 -= this.f1548b.m3273c();
        if (i2 <= 0) {
            return c;
        }
        this.f1548b.m3270a(-i2);
        return c - i2;
    }

    private void m3180a(AnchorInfo anchorInfo) {
        m3191c(anchorInfo.f1520a, anchorInfo.f1521b);
    }

    private void m3191c(int i, int i2) {
        this.f1554k.f1530c = this.f1548b.m3275d() - i2;
        this.f1554k.f1532e = this.f1549c ? -1 : 1;
        this.f1554k.f1531d = i;
        this.f1554k.f1533f = 1;
        this.f1554k.f1529b = i2;
        this.f1554k.f1534g = Integer.MIN_VALUE;
    }

    private void m3187b(AnchorInfo anchorInfo) {
        m3193d(anchorInfo.f1520a, anchorInfo.f1521b);
    }

    private void m3193d(int i, int i2) {
        this.f1554k.f1530c = i2 - this.f1548b.m3273c();
        this.f1554k.f1531d = i;
        this.f1554k.f1532e = this.f1549c ? 1 : -1;
        this.f1554k.f1533f = -1;
        this.f1554k.f1529b = i2;
        this.f1554k.f1534g = Integer.MIN_VALUE;
    }

    protected boolean m3233e() {
        return m3164o() == 1;
    }

    void m3235f() {
        if (this.f1554k == null) {
            this.f1554k = m3237g();
        }
        if (this.f1548b == null) {
            this.f1548b = OrientationHelper.m3266a(this, this.f1547a);
        }
    }

    LayoutState m3237g() {
        return new LayoutState();
    }

    public void m3230d(int i) {
        this.f1550d = i;
        this.f1551e = Integer.MIN_VALUE;
        if (this.f1552f != null) {
            this.f1552f.m3064b();
        }
        m3161m();
    }

    public void m3211a(int i, int i2) {
        this.f1550d = i;
        this.f1551e = i2;
        if (this.f1552f != null) {
            this.f1552f.m3064b();
        }
        m3161m();
    }

    public int m3203a(int i, Recycler recycler, State state) {
        if (this.f1547a == 1) {
            return 0;
        }
        return m3225c(i, recycler, state);
    }

    public int m3221b(int i, Recycler recycler, State state) {
        if (this.f1547a == 0) {
            return 0;
        }
        return m3225c(i, recycler, state);
    }

    public int m3222b(State state) {
        return m3196h(state);
    }

    public int m3226c(State state) {
        return m3196h(state);
    }

    public int m3229d(State state) {
        return m3198i(state);
    }

    public int m3232e(State state) {
        return m3198i(state);
    }

    public int m3234f(State state) {
        return m3200j(state);
    }

    public int m3236g(State state) {
        return m3200j(state);
    }

    private int m3196h(State state) {
        boolean z = false;
        if (m3166q() == 0) {
            return 0;
        }
        m3235f();
        OrientationHelper orientationHelper = this.f1548b;
        View a = m3178a(!this.f1558o, true);
        if (!this.f1558o) {
            z = true;
        }
        return ScrollbarHelper.m3656a(state, orientationHelper, a, m3186b(z, true), this, this.f1558o, this.f1549c);
    }

    private int m3198i(State state) {
        boolean z = false;
        if (m3166q() == 0) {
            return 0;
        }
        m3235f();
        OrientationHelper orientationHelper = this.f1548b;
        View a = m3178a(!this.f1558o, true);
        if (!this.f1558o) {
            z = true;
        }
        return ScrollbarHelper.m3655a(state, orientationHelper, a, m3186b(z, true), this, this.f1558o);
    }

    private int m3200j(State state) {
        boolean z = false;
        if (m3166q() == 0) {
            return 0;
        }
        m3235f();
        OrientationHelper orientationHelper = this.f1548b;
        View a = m3178a(!this.f1558o, true);
        if (!this.f1558o) {
            z = true;
        }
        return ScrollbarHelper.m3657b(state, orientationHelper, a, m3186b(z, true), this, this.f1558o);
    }

    private void m3179a(int i, int i2, boolean z, State state) {
        int i3 = -1;
        int i4 = 1;
        this.f1554k.f1535h = m3205a(state);
        this.f1554k.f1533f = i;
        View B;
        LayoutState layoutState;
        if (i == 1) {
            LayoutState layoutState2 = this.f1554k;
            layoutState2.f1535h += this.f1548b.m3279g();
            B = m3176B();
            layoutState = this.f1554k;
            if (!this.f1549c) {
                i3 = 1;
            }
            layoutState.f1532e = i3;
            this.f1554k.f1531d = m3135d(B) + this.f1554k.f1532e;
            this.f1554k.f1529b = this.f1548b.m3272b(B);
            i3 = this.f1548b.m3272b(B) - this.f1548b.m3275d();
        } else {
            B = m3175A();
            layoutState = this.f1554k;
            layoutState.f1535h += this.f1548b.m3273c();
            layoutState = this.f1554k;
            if (!this.f1549c) {
                i4 = -1;
            }
            layoutState.f1532e = i4;
            this.f1554k.f1531d = m3135d(B) + this.f1554k.f1532e;
            this.f1554k.f1529b = this.f1548b.m3268a(B);
            i3 = (-this.f1548b.m3268a(B)) + this.f1548b.m3273c();
        }
        this.f1554k.f1530c = i2;
        if (z) {
            LayoutState layoutState3 = this.f1554k;
            layoutState3.f1530c -= i3;
        }
        this.f1554k.f1534g = i3;
    }

    int m3225c(int i, Recycler recycler, State state) {
        if (m3166q() == 0 || i == 0) {
            return 0;
        }
        this.f1554k.f1528a = true;
        m3235f();
        int i2 = i > 0 ? 1 : -1;
        int abs = Math.abs(i);
        m3179a(i2, abs, true, state);
        int a = this.f1554k.f1534g + m3204a(recycler, this.f1554k, state, false);
        if (a < 0) {
            return 0;
        }
        if (abs > a) {
            i = i2 * a;
        }
        this.f1548b.m3270a(-i);
        this.f1554k.f1537j = i;
        return i;
    }

    public void m3219a(String str) {
        if (this.f1552f == null) {
            super.m3103a(str);
        }
    }

    private void m3182a(Recycler recycler, int i, int i2) {
        if (i != i2) {
            if (i2 > i) {
                for (int i3 = i2 - 1; i3 >= i; i3--) {
                    m3078a(i3, recycler);
                }
                return;
            }
            while (i > i2) {
                m3078a(i, recycler);
                i--;
            }
        }
    }

    private void m3181a(Recycler recycler, int i) {
        if (i >= 0) {
            int q = m3166q();
            int i2;
            if (this.f1549c) {
                for (i2 = q - 1; i2 >= 0; i2--) {
                    if (this.f1548b.m3272b(m3150g(i2)) > i) {
                        m3182a(recycler, q - 1, i2);
                        return;
                    }
                }
                return;
            }
            for (i2 = 0; i2 < q; i2++) {
                if (this.f1548b.m3272b(m3150g(i2)) > i) {
                    m3182a(recycler, 0, i2);
                    return;
                }
            }
        }
    }

    private void m3188b(Recycler recycler, int i) {
        int q = m3166q();
        if (i >= 0) {
            int e = this.f1548b.m3277e() - i;
            int i2;
            if (this.f1549c) {
                for (i2 = 0; i2 < q; i2++) {
                    if (this.f1548b.m3268a(m3150g(i2)) < e) {
                        m3182a(recycler, 0, i2);
                        return;
                    }
                }
                return;
            }
            for (i2 = q - 1; i2 >= 0; i2--) {
                if (this.f1548b.m3268a(m3150g(i2)) < e) {
                    m3182a(recycler, q - 1, i2);
                    return;
                }
            }
        }
    }

    private void m3183a(Recycler recycler, LayoutState layoutState) {
        if (!layoutState.f1528a) {
            return;
        }
        if (layoutState.f1533f == -1) {
            m3188b(recycler, layoutState.f1534g);
        } else {
            m3181a(recycler, layoutState.f1534g);
        }
    }

    int m3204a(Recycler recycler, LayoutState layoutState, State state, boolean z) {
        int i = layoutState.f1530c;
        if (layoutState.f1534g != Integer.MIN_VALUE) {
            if (layoutState.f1530c < 0) {
                layoutState.f1534g += layoutState.f1530c;
            }
            m3183a(recycler, layoutState);
        }
        int i2 = layoutState.f1530c + layoutState.f1535h;
        LayoutChunkResult layoutChunkResult = new LayoutChunkResult();
        while (i2 > 0 && layoutState.m3059a(state)) {
            layoutChunkResult.m3054a();
            m3215a(recycler, state, layoutState, layoutChunkResult);
            if (!layoutChunkResult.f1525b) {
                layoutState.f1529b += layoutChunkResult.f1524a * layoutState.f1533f;
                if (!(layoutChunkResult.f1526c && this.f1554k.f1538k == null && state.m3457a())) {
                    layoutState.f1530c -= layoutChunkResult.f1524a;
                    i2 -= layoutChunkResult.f1524a;
                }
                if (layoutState.f1534g != Integer.MIN_VALUE) {
                    layoutState.f1534g += layoutChunkResult.f1524a;
                    if (layoutState.f1530c < 0) {
                        layoutState.f1534g += layoutState.f1530c;
                    }
                    m3183a(recycler, layoutState);
                }
                if (z && layoutChunkResult.f1527d) {
                    break;
                }
            } else {
                break;
            }
        }
        return i - layoutState.f1530c;
    }

    void m3215a(Recycler recycler, State state, LayoutState layoutState, LayoutChunkResult layoutChunkResult) {
        View a = layoutState.m3056a(recycler);
        if (a == null) {
            layoutChunkResult.f1525b = true;
            return;
        }
        int r;
        int d;
        int i;
        int i2;
        LayoutParams layoutParams = (LayoutParams) a.getLayoutParams();
        if (layoutState.f1538k == null) {
            if (this.f1549c == (layoutState.f1533f == -1)) {
                m3123b(a);
            } else {
                m3124b(a, 0);
            }
        } else {
            boolean z;
            boolean z2 = this.f1549c;
            if (layoutState.f1533f == -1) {
                z = true;
            } else {
                z = false;
            }
            if (z2 == z) {
                m3095a(a);
            } else {
                m3096a(a, 0);
            }
        }
        m3097a(a, 0, 0);
        layoutChunkResult.f1524a = this.f1548b.m3274c(a);
        if (this.f1547a == 1) {
            if (m3233e()) {
                r = m3167r() - m3171v();
                d = r - this.f1548b.m3276d(a);
            } else {
                d = m3169t();
                r = this.f1548b.m3276d(a) + d;
            }
            if (layoutState.f1533f == -1) {
                i = layoutState.f1529b - layoutChunkResult.f1524a;
                i2 = r;
                r = layoutState.f1529b;
            } else {
                i = layoutState.f1529b;
                i2 = r;
                r = layoutState.f1529b + layoutChunkResult.f1524a;
            }
        } else {
            i = m3170u();
            r = this.f1548b.m3276d(a) + i;
            if (layoutState.f1533f == -1) {
                d = layoutState.f1529b - layoutChunkResult.f1524a;
                i2 = layoutState.f1529b;
            } else {
                d = layoutState.f1529b;
                i2 = layoutState.f1529b + layoutChunkResult.f1524a;
            }
        }
        m3098a(a, d + layoutParams.leftMargin, i + layoutParams.topMargin, i2 - layoutParams.rightMargin, r - layoutParams.bottomMargin);
        if (layoutParams.m3373a() || layoutParams.m3374b()) {
            layoutChunkResult.f1526c = true;
        }
        layoutChunkResult.f1527d = a.isFocusable();
    }

    private int m3201k(int i) {
        int i2 = 1;
        int i3 = Integer.MIN_VALUE;
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return -1;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                return 1;
            case C1128R.styleable.StickyListHeadersListView_android_choiceMode /*17*/:
                if (this.f1547a != 0) {
                    return Integer.MIN_VALUE;
                }
                return -1;
            case C0110R.styleable.Theme_actionModeCopyDrawable /*33*/:
                if (this.f1547a != 1) {
                    return Integer.MIN_VALUE;
                }
                return -1;
            case C0110R.styleable.Theme_textAppearanceSearchResultSubtitle /*66*/:
                if (this.f1547a != 0) {
                    i2 = Integer.MIN_VALUE;
                }
                return i2;
            case 130:
                if (this.f1547a == 1) {
                    i3 = 1;
                }
                return i3;
            default:
                return Integer.MIN_VALUE;
        }
    }

    private View m3175A() {
        return m3150g(this.f1549c ? m3166q() - 1 : 0);
    }

    private View m3176B() {
        return m3150g(this.f1549c ? 0 : m3166q() - 1);
    }

    private View m3178a(boolean z, boolean z2) {
        if (this.f1549c) {
            return m3207a(m3166q() - 1, -1, z, z2);
        }
        return m3207a(0, m3166q(), z, z2);
    }

    private View m3186b(boolean z, boolean z2) {
        if (this.f1549c) {
            return m3207a(0, m3166q(), z, z2);
        }
        return m3207a(m3166q() - 1, -1, z, z2);
    }

    private View m3194f(Recycler recycler, State state) {
        return this.f1549c ? m3197h(recycler, state) : m3199i(recycler, state);
    }

    private View m3195g(Recycler recycler, State state) {
        return this.f1549c ? m3199i(recycler, state) : m3197h(recycler, state);
    }

    private View m3197h(Recycler recycler, State state) {
        return m3208a(recycler, state, 0, m3166q(), state.m3460d());
    }

    private View m3199i(Recycler recycler, State state) {
        return m3208a(recycler, state, m3166q() - 1, -1, state.m3460d());
    }

    View m3208a(Recycler recycler, State state, int i, int i2, int i3) {
        View view = null;
        m3235f();
        int c = this.f1548b.m3273c();
        int d = this.f1548b.m3275d();
        int i4 = i2 > i ? 1 : -1;
        View view2 = null;
        while (i != i2) {
            View view3;
            View g = m3150g(i);
            int d2 = m3135d(g);
            if (d2 >= 0 && d2 < i3) {
                if (((LayoutParams) g.getLayoutParams()).m3373a()) {
                    if (view2 == null) {
                        view3 = view;
                        i += i4;
                        view = view3;
                        view2 = g;
                    }
                } else if (this.f1548b.m3268a(g) < d && this.f1548b.m3272b(g) >= c) {
                    return g;
                } else {
                    if (view == null) {
                        view3 = g;
                        g = view2;
                        i += i4;
                        view = view3;
                        view2 = g;
                    }
                }
            }
            view3 = view;
            g = view2;
            i += i4;
            view = view3;
            view2 = g;
        }
        if (view == null) {
            view = view2;
        }
        return view;
    }

    public int m3238h() {
        View a = m3207a(0, m3166q(), false, true);
        return a == null ? -1 : m3135d(a);
    }

    public int m3239i() {
        View a = m3207a(0, m3166q(), true, false);
        return a == null ? -1 : m3135d(a);
    }

    public int m3240j() {
        View a = m3207a(m3166q() - 1, -1, false, true);
        if (a == null) {
            return -1;
        }
        return m3135d(a);
    }

    public int m3241k() {
        View a = m3207a(m3166q() - 1, -1, true, false);
        if (a == null) {
            return -1;
        }
        return m3135d(a);
    }

    View m3207a(int i, int i2, boolean z, boolean z2) {
        m3235f();
        int c = this.f1548b.m3273c();
        int d = this.f1548b.m3275d();
        int i3 = i2 > i ? 1 : -1;
        View view = null;
        while (i != i2) {
            View g = m3150g(i);
            int a = this.f1548b.m3268a(g);
            int b = this.f1548b.m3272b(g);
            if (a < d && b > c) {
                if (!z) {
                    return g;
                }
                if (a >= c && b <= d) {
                    return g;
                }
                if (z2 && view == null) {
                    i += i3;
                    view = g;
                }
            }
            g = view;
            i += i3;
            view = g;
        }
        return view;
    }

    public View m3209a(View view, int i, Recycler recycler, State state) {
        m3202z();
        if (m3166q() == 0) {
            return null;
        }
        int k = m3201k(i);
        if (k == Integer.MIN_VALUE) {
            return null;
        }
        View g;
        m3235f();
        if (k == -1) {
            g = m3195g(recycler, state);
        } else {
            g = m3194f(recycler, state);
        }
        if (g == null) {
            return null;
        }
        View A;
        m3235f();
        m3179a(k, (int) (0.33f * ((float) this.f1548b.m3278f())), false, state);
        this.f1554k.f1534g = Integer.MIN_VALUE;
        this.f1554k.f1528a = false;
        m3204a(recycler, this.f1554k, state, true);
        if (k == -1) {
            A = m3175A();
        } else {
            A = m3176B();
        }
        if (A == g || !A.isFocusable()) {
            return null;
        }
        return A;
    }

    public boolean m3242l() {
        return this.f1552f == null && this.f1555l == this.f1557n;
    }
}
