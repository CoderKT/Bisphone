package android.support.v4.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v4.widget.EdgeEffectCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import app.C0110R;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import se.emilsjolander.stickylistheaders.C1128R;

public class ViewPager extends ViewGroup {
    private static final int[] f473a;
    private static final ViewPositionComparator ah;
    private static final Comparator<ItemInfo> f474c;
    private static final Interpolator f475d;
    private boolean f476A;
    private boolean f477B;
    private int f478C;
    private int f479D;
    private int f480E;
    private float f481F;
    private float f482G;
    private float f483H;
    private float f484I;
    private int f485J;
    private VelocityTracker f486K;
    private int f487L;
    private int f488M;
    private int f489N;
    private int f490O;
    private boolean f491P;
    private EdgeEffectCompat f492Q;
    private EdgeEffectCompat f493R;
    private boolean f494S;
    private boolean f495T;
    private boolean f496U;
    private int f497V;
    private List<OnPageChangeListener> f498W;
    private OnPageChangeListener aa;
    private OnPageChangeListener ab;
    private OnAdapterChangeListener ac;
    private PageTransformer ad;
    private Method ae;
    private int af;
    private ArrayList<View> ag;
    private final Runnable ai;
    private int aj;
    private int f499b;
    private final ArrayList<ItemInfo> f500e;
    private final ItemInfo f501f;
    private final Rect f502g;
    private PagerAdapter f503h;
    private int f504i;
    private int f505j;
    private Parcelable f506k;
    private ClassLoader f507l;
    private Scroller f508m;
    private PagerObserver f509n;
    private int f510o;
    private Drawable f511p;
    private int f512q;
    private int f513r;
    private float f514s;
    private float f515t;
    private int f516u;
    private int f517v;
    private boolean f518w;
    private boolean f519x;
    private boolean f520y;
    private int f521z;

    /* renamed from: android.support.v4.view.ViewPager.1 */
    final class C00211 implements Comparator<ItemInfo> {
        C00211() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m1252a((ItemInfo) obj, (ItemInfo) obj2);
        }

        public int m1252a(ItemInfo itemInfo, ItemInfo itemInfo2) {
            return itemInfo.f458b - itemInfo2.f458b;
        }
    }

    /* renamed from: android.support.v4.view.ViewPager.2 */
    final class C00222 implements Interpolator {
        C00222() {
        }

        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * (((f2 * f2) * f2) * f2)) + 1.0f;
        }
    }

    /* renamed from: android.support.v4.view.ViewPager.3 */
    class C00233 implements Runnable {
        final /* synthetic */ ViewPager f456a;

        C00233(ViewPager viewPager) {
            this.f456a = viewPager;
        }

        public void run() {
            this.f456a.setScrollState(0);
            this.f456a.m1310c();
        }
    }

    interface Decor {
    }

    class ItemInfo {
        Object f457a;
        int f458b;
        boolean f459c;
        float f460d;
        float f461e;

        ItemInfo() {
        }
    }

    public class LayoutParams extends android.view.ViewGroup.LayoutParams {
        public boolean f462a;
        public int f463b;
        float f464c;
        boolean f465d;
        int f466e;
        int f467f;

        public LayoutParams() {
            super(-1, -1);
            this.f464c = 0.0f;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f464c = 0.0f;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ViewPager.f473a);
            this.f463b = obtainStyledAttributes.getInteger(0, 48);
            obtainStyledAttributes.recycle();
        }
    }

    class MyAccessibilityDelegate extends AccessibilityDelegateCompat {
        final /* synthetic */ ViewPager f468b;

        MyAccessibilityDelegate(ViewPager viewPager) {
            this.f468b = viewPager;
        }

        public void m1256d(View view, AccessibilityEvent accessibilityEvent) {
            super.m870d(view, accessibilityEvent);
            accessibilityEvent.setClassName(ViewPager.class.getName());
            AccessibilityRecordCompat a = AccessibilityRecordCompat.m1634a();
            a.m1637a(m1253b());
            if (accessibilityEvent.getEventType() == 4096 && this.f468b.f503h != null) {
                a.m1635a(this.f468b.f503h.getCount());
                a.m1638b(this.f468b.f504i);
                a.m1639c(this.f468b.f504i);
            }
        }

        public void m1254a(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.m864a(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.m1531a(ViewPager.class.getName());
            accessibilityNodeInfoCompat.m1533a(m1253b());
            if (this.f468b.canScrollHorizontally(1)) {
                accessibilityNodeInfoCompat.m1529a(4096);
            }
            if (this.f468b.canScrollHorizontally(-1)) {
                accessibilityNodeInfoCompat.m1529a(8192);
            }
        }

        public boolean m1255a(View view, int i, Bundle bundle) {
            if (super.m866a(view, i, bundle)) {
                return true;
            }
            switch (i) {
                case 4096:
                    if (!this.f468b.canScrollHorizontally(1)) {
                        return false;
                    }
                    this.f468b.setCurrentItem(this.f468b.f504i + 1);
                    return true;
                case 8192:
                    if (!this.f468b.canScrollHorizontally(-1)) {
                        return false;
                    }
                    this.f468b.setCurrentItem(this.f468b.f504i - 1);
                    return true;
                default:
                    return false;
            }
        }

        private boolean m1253b() {
            return this.f468b.f503h != null && this.f468b.f503h.getCount() > 1;
        }
    }

    interface OnAdapterChangeListener {
        void m1257a(PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2);
    }

    public interface OnPageChangeListener {
        void m1258a(int i);

        void m1259a(int i, float f, int i2);

        void m1260b(int i);
    }

    public interface PageTransformer {
        void m1261a(View view, float f);
    }

    class PagerObserver extends DataSetObserver {
        final /* synthetic */ ViewPager f469a;

        private PagerObserver(ViewPager viewPager) {
            this.f469a = viewPager;
        }

        public void onChanged() {
            this.f469a.m1309b();
        }

        public void onInvalidated() {
            this.f469a.m1309b();
        }
    }

    public class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        int f470a;
        Parcelable f471b;
        ClassLoader f472c;

        /* renamed from: android.support.v4.view.ViewPager.SavedState.1 */
        final class C00241 implements ParcelableCompatCreatorCallbacks<SavedState> {
            C00241() {
            }

            public /* synthetic */ Object m1262a(Parcel parcel, ClassLoader classLoader) {
                return m1264b(parcel, classLoader);
            }

            public /* synthetic */ Object[] m1263a(int i) {
                return m1265b(i);
            }

            public SavedState m1264b(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            public SavedState[] m1265b(int i) {
                return new SavedState[i];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f470a);
            parcel.writeParcelable(this.f471b, i);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.f470a + "}";
        }

        static {
            CREATOR = ParcelableCompat.m693a(new C00241());
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel);
            if (classLoader == null) {
                classLoader = getClass().getClassLoader();
            }
            this.f470a = parcel.readInt();
            this.f471b = parcel.readParcelable(classLoader);
            this.f472c = classLoader;
        }
    }

    public class SimpleOnPageChangeListener implements OnPageChangeListener {
        public void m1267a(int i, float f, int i2) {
        }

        public void m1266a(int i) {
        }

        public void m1268b(int i) {
        }
    }

    class ViewPositionComparator implements Comparator<View> {
        ViewPositionComparator() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m1269a((View) obj, (View) obj2);
        }

        public int m1269a(View view, View view2) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            LayoutParams layoutParams2 = (LayoutParams) view2.getLayoutParams();
            if (layoutParams.f462a != layoutParams2.f462a) {
                return layoutParams.f462a ? 1 : -1;
            } else {
                return layoutParams.f466e - layoutParams2.f466e;
            }
        }
    }

    static {
        f473a = new int[]{16842931};
        f474c = new C00211();
        f475d = new C00222();
        ah = new ViewPositionComparator();
    }

    public ViewPager(Context context) {
        super(context);
        this.f500e = new ArrayList();
        this.f501f = new ItemInfo();
        this.f502g = new Rect();
        this.f505j = -1;
        this.f506k = null;
        this.f507l = null;
        this.f514s = -3.4028235E38f;
        this.f515t = Float.MAX_VALUE;
        this.f521z = 1;
        this.f485J = -1;
        this.f494S = true;
        this.f495T = false;
        this.ai = new C00233(this);
        this.aj = 0;
        m1297a();
    }

    public ViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f500e = new ArrayList();
        this.f501f = new ItemInfo();
        this.f502g = new Rect();
        this.f505j = -1;
        this.f506k = null;
        this.f507l = null;
        this.f514s = -3.4028235E38f;
        this.f515t = Float.MAX_VALUE;
        this.f521z = 1;
        this.f485J = -1;
        this.f494S = true;
        this.f495T = false;
        this.ai = new C00233(this);
        this.aj = 0;
        m1297a();
    }

    void m1297a() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.f508m = new Scroller(context, f475d);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f = context.getResources().getDisplayMetrics().density;
        this.f480E = ViewConfigurationCompat.m1248a(viewConfiguration);
        this.f487L = (int) (400.0f * f);
        this.f488M = viewConfiguration.getScaledMaximumFlingVelocity();
        this.f492Q = new EdgeEffectCompat(context);
        this.f493R = new EdgeEffectCompat(context);
        this.f489N = (int) (25.0f * f);
        this.f490O = (int) (2.0f * f);
        this.f478C = (int) (16.0f * f);
        ViewCompat.m1161a((View) this, new MyAccessibilityDelegate(this));
        if (ViewCompat.m1176e(this) == 0) {
            ViewCompat.m1172c((View) this, 1);
        }
    }

    protected void onDetachedFromWindow() {
        removeCallbacks(this.ai);
        super.onDetachedFromWindow();
    }

    private void setScrollState(int i) {
        if (this.aj != i) {
            this.aj = i;
            if (this.ad != null) {
                m1282b(i != 0);
            }
            m1287f(i);
        }
    }

    public void setAdapter(PagerAdapter pagerAdapter) {
        if (this.f503h != null) {
            this.f503h.m59b(this.f509n);
            this.f503h.m55a((ViewGroup) this);
            for (int i = 0; i < this.f500e.size(); i++) {
                ItemInfo itemInfo = (ItemInfo) this.f500e.get(i);
                this.f503h.m56a((ViewGroup) this, itemInfo.f458b, itemInfo.f457a);
            }
            this.f503h.m62b((ViewGroup) this);
            this.f500e.clear();
            m1289g();
            this.f504i = 0;
            scrollTo(0, 0);
        }
        PagerAdapter pagerAdapter2 = this.f503h;
        this.f503h = pagerAdapter;
        this.f499b = 0;
        if (this.f503h != null) {
            if (this.f509n == null) {
                this.f509n = new PagerObserver();
            }
            this.f503h.m51a(this.f509n);
            this.f520y = false;
            boolean z = this.f494S;
            this.f494S = true;
            this.f499b = this.f503h.getCount();
            if (this.f505j >= 0) {
                this.f503h.m52a(this.f506k, this.f507l);
                m1302a(this.f505j, false, true);
                this.f505j = -1;
                this.f506k = null;
                this.f507l = null;
            } else if (z) {
                requestLayout();
            } else {
                m1310c();
            }
        }
        if (this.ac != null && pagerAdapter2 != pagerAdapter) {
            this.ac.m1257a(pagerAdapter2, pagerAdapter);
        }
    }

    private void m1289g() {
        int i = 0;
        while (i < getChildCount()) {
            if (!((LayoutParams) getChildAt(i).getLayoutParams()).f462a) {
                removeViewAt(i);
                i--;
            }
            i++;
        }
    }

    public PagerAdapter getAdapter() {
        return this.f503h;
    }

    void setOnAdapterChangeListener(OnAdapterChangeListener onAdapterChangeListener) {
        this.ac = onAdapterChangeListener;
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    public void setCurrentItem(int i) {
        boolean z;
        this.f520y = false;
        if (this.f494S) {
            z = false;
        } else {
            z = true;
        }
        m1302a(i, z, false);
    }

    public void m1301a(int i, boolean z) {
        this.f520y = false;
        m1302a(i, z, false);
    }

    public int getCurrentItem() {
        return this.f504i;
    }

    void m1302a(int i, boolean z, boolean z2) {
        m1303a(i, z, z2, 0);
    }

    void m1303a(int i, boolean z, boolean z2, int i2) {
        boolean z3 = false;
        if (this.f503h == null || this.f503h.getCount() <= 0) {
            setScrollingCacheEnabled(false);
        } else if (z2 || this.f504i != i || this.f500e.size() == 0) {
            if (i < 0) {
                i = 0;
            } else if (i >= this.f503h.getCount()) {
                i = this.f503h.getCount() - 1;
            }
            int i3 = this.f521z;
            if (i > this.f504i + i3 || i < this.f504i - i3) {
                for (int i4 = 0; i4 < this.f500e.size(); i4++) {
                    ((ItemInfo) this.f500e.get(i4)).f459c = true;
                }
            }
            if (this.f504i != i) {
                z3 = true;
            }
            if (this.f494S) {
                this.f504i = i;
                if (z3) {
                    m1286e(i);
                }
                requestLayout();
                return;
            }
            m1298a(i);
            m1274a(i, z, i2, z3);
        } else {
            setScrollingCacheEnabled(false);
        }
    }

    private void m1274a(int i, boolean z, int i2, boolean z2) {
        int max;
        ItemInfo b = m1307b(i);
        if (b != null) {
            max = (int) (Math.max(this.f514s, Math.min(b.f461e, this.f515t)) * ((float) getClientWidth()));
        } else {
            max = 0;
        }
        if (z) {
            m1300a(max, 0, i2);
            if (z2) {
                m1286e(i);
                return;
            }
            return;
        }
        if (z2) {
            m1286e(i);
        }
        m1278a(false);
        scrollTo(max, 0);
        m1285d(max);
    }

    @Deprecated
    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.aa = onPageChangeListener;
    }

    public void m1304a(OnPageChangeListener onPageChangeListener) {
        if (this.f498W == null) {
            this.f498W = new ArrayList();
        }
        this.f498W.add(onPageChangeListener);
    }

    void setChildrenDrawingOrderEnabledCompat(boolean z) {
        if (VERSION.SDK_INT >= 7) {
            if (this.ae == null) {
                try {
                    this.ae = ViewGroup.class.getDeclaredMethod("setChildrenDrawingOrderEnabled", new Class[]{Boolean.TYPE});
                } catch (Throwable e) {
                    Log.e("ViewPager", "Can't find setChildrenDrawingOrderEnabled", e);
                }
            }
            try {
                this.ae.invoke(this, new Object[]{Boolean.valueOf(z)});
            } catch (Throwable e2) {
                Log.e("ViewPager", "Error changing children drawing order", e2);
            }
        }
    }

    protected int getChildDrawingOrder(int i, int i2) {
        if (this.af == 2) {
            i2 = (i - 1) - i2;
        }
        return ((LayoutParams) ((View) this.ag.get(i2)).getLayoutParams()).f467f;
    }

    public int getOffscreenPageLimit() {
        return this.f521z;
    }

    public void setOffscreenPageLimit(int i) {
        if (i < 1) {
            Log.w("ViewPager", "Requested offscreen page limit " + i + " too small; defaulting to " + 1);
            i = 1;
        }
        if (i != this.f521z) {
            this.f521z = i;
            m1310c();
        }
    }

    public void setPageMargin(int i) {
        int i2 = this.f510o;
        this.f510o = i;
        int width = getWidth();
        m1273a(width, width, i, i2);
        requestLayout();
    }

    public int getPageMargin() {
        return this.f510o;
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.f511p = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    public void setPageMarginDrawable(int i) {
        setPageMarginDrawable(getContext().getResources().getDrawable(i));
    }

    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f511p;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f511p;
        if (drawable != null && drawable.isStateful()) {
            drawable.setState(getDrawableState());
        }
    }

    float m1294a(float f) {
        return (float) Math.sin((double) ((float) (((double) (f - 0.5f)) * 0.4712389167638204d)));
    }

    void m1300a(int i, int i2, int i3) {
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int i4 = i - scrollX;
        int i5 = i2 - scrollY;
        if (i4 == 0 && i5 == 0) {
            m1278a(false);
            m1310c();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int clientWidth = getClientWidth();
        int i6 = clientWidth / 2;
        float a = (((float) i6) * m1294a(Math.min(1.0f, (((float) Math.abs(i4)) * 1.0f) / ((float) clientWidth)))) + ((float) i6);
        int abs = Math.abs(i3);
        if (abs > 0) {
            clientWidth = Math.round(1000.0f * Math.abs(a / ((float) abs))) * 4;
        } else {
            clientWidth = (int) (((((float) Math.abs(i4)) / ((((float) clientWidth) * this.f503h.m64c(this.f504i)) + ((float) this.f510o))) + 1.0f) * 100.0f);
        }
        this.f508m.startScroll(scrollX, scrollY, i4, i5, Math.min(clientWidth, 600));
        ViewCompat.m1174d(this);
    }

    ItemInfo m1295a(int i, int i2) {
        ItemInfo itemInfo = new ItemInfo();
        itemInfo.f458b = i;
        itemInfo.f457a = this.f503h.m50a((ViewGroup) this, i);
        itemInfo.f460d = this.f503h.m64c(i);
        if (i2 < 0 || i2 >= this.f500e.size()) {
            this.f500e.add(itemInfo);
        } else {
            this.f500e.add(i2, itemInfo);
        }
        return itemInfo;
    }

    void m1309b() {
        int count = this.f503h.getCount();
        this.f499b = count;
        boolean z = this.f500e.size() < (this.f521z * 2) + 1 && this.f500e.size() < count;
        boolean z2 = false;
        int i = this.f504i;
        boolean z3 = z;
        int i2 = 0;
        while (i2 < this.f500e.size()) {
            int i3;
            boolean z4;
            int i4;
            boolean z5;
            ItemInfo itemInfo = (ItemInfo) this.f500e.get(i2);
            int a = this.f503h.m47a(itemInfo.f457a);
            if (a == -1) {
                i3 = i2;
                z4 = z2;
                i4 = i;
                z5 = z3;
            } else if (a == -2) {
                this.f500e.remove(i2);
                i2--;
                if (!z2) {
                    this.f503h.m55a((ViewGroup) this);
                    z2 = true;
                }
                this.f503h.m56a((ViewGroup) this, itemInfo.f458b, itemInfo.f457a);
                if (this.f504i == itemInfo.f458b) {
                    i3 = i2;
                    z4 = z2;
                    i4 = Math.max(0, Math.min(this.f504i, count - 1));
                    z5 = true;
                } else {
                    i3 = i2;
                    z4 = z2;
                    i4 = i;
                    z5 = true;
                }
            } else if (itemInfo.f458b != a) {
                if (itemInfo.f458b == this.f504i) {
                    i = a;
                }
                itemInfo.f458b = a;
                i3 = i2;
                z4 = z2;
                i4 = i;
                z5 = true;
            } else {
                i3 = i2;
                z4 = z2;
                i4 = i;
                z5 = z3;
            }
            z3 = z5;
            i = i4;
            z2 = z4;
            i2 = i3 + 1;
        }
        if (z2) {
            this.f503h.m62b((ViewGroup) this);
        }
        Collections.sort(this.f500e, f474c);
        if (z3) {
            i4 = getChildCount();
            for (i2 = 0; i2 < i4; i2++) {
                LayoutParams layoutParams = (LayoutParams) getChildAt(i2).getLayoutParams();
                if (!layoutParams.f462a) {
                    layoutParams.f464c = 0.0f;
                }
            }
            m1302a(i, false, true);
            requestLayout();
        }
    }

    void m1310c() {
        m1298a(this.f504i);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void m1298a(int r19) {
        /*
        r18 = this;
        r3 = 0;
        r2 = 2;
        r0 = r18;
        r4 = r0.f504i;
        r0 = r19;
        if (r4 == r0) goto L_0x033f;
    L_0x000a:
        r0 = r18;
        r2 = r0.f504i;
        r0 = r19;
        if (r2 >= r0) goto L_0x0030;
    L_0x0012:
        r2 = 66;
    L_0x0014:
        r0 = r18;
        r3 = r0.f504i;
        r0 = r18;
        r3 = r0.m1307b(r3);
        r0 = r19;
        r1 = r18;
        r1.f504i = r0;
        r4 = r3;
        r3 = r2;
    L_0x0026:
        r0 = r18;
        r2 = r0.f503h;
        if (r2 != 0) goto L_0x0033;
    L_0x002c:
        r18.m1290h();
    L_0x002f:
        return;
    L_0x0030:
        r2 = 17;
        goto L_0x0014;
    L_0x0033:
        r0 = r18;
        r2 = r0.f520y;
        if (r2 == 0) goto L_0x003d;
    L_0x0039:
        r18.m1290h();
        goto L_0x002f;
    L_0x003d:
        r2 = r18.getWindowToken();
        if (r2 == 0) goto L_0x002f;
    L_0x0043:
        r0 = r18;
        r2 = r0.f503h;
        r0 = r18;
        r2.m55a(r0);
        r0 = r18;
        r2 = r0.f521z;
        r5 = 0;
        r0 = r18;
        r6 = r0.f504i;
        r6 = r6 - r2;
        r11 = java.lang.Math.max(r5, r6);
        r0 = r18;
        r5 = r0.f503h;
        r12 = r5.getCount();
        r5 = r12 + -1;
        r0 = r18;
        r6 = r0.f504i;
        r2 = r2 + r6;
        r13 = java.lang.Math.min(r5, r2);
        r0 = r18;
        r2 = r0.f499b;
        if (r12 == r2) goto L_0x00da;
    L_0x0073:
        r2 = r18.getResources();	 Catch:{ NotFoundException -> 0x00d0 }
        r3 = r18.getId();	 Catch:{ NotFoundException -> 0x00d0 }
        r2 = r2.getResourceName(r3);	 Catch:{ NotFoundException -> 0x00d0 }
    L_0x007f:
        r3 = new java.lang.IllegalStateException;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: ";
        r4 = r4.append(r5);
        r0 = r18;
        r5 = r0.f499b;
        r4 = r4.append(r5);
        r5 = ", found: ";
        r4 = r4.append(r5);
        r4 = r4.append(r12);
        r5 = " Pager id: ";
        r4 = r4.append(r5);
        r2 = r4.append(r2);
        r4 = " Pager class: ";
        r2 = r2.append(r4);
        r4 = r18.getClass();
        r2 = r2.append(r4);
        r4 = " Problematic adapter: ";
        r2 = r2.append(r4);
        r0 = r18;
        r4 = r0.f503h;
        r4 = r4.getClass();
        r2 = r2.append(r4);
        r2 = r2.toString();
        r3.<init>(r2);
        throw r3;
    L_0x00d0:
        r2 = move-exception;
        r2 = r18.getId();
        r2 = java.lang.Integer.toHexString(r2);
        goto L_0x007f;
    L_0x00da:
        r6 = 0;
        r2 = 0;
        r5 = r2;
    L_0x00dd:
        r0 = r18;
        r2 = r0.f500e;
        r2 = r2.size();
        if (r5 >= r2) goto L_0x033c;
    L_0x00e7:
        r0 = r18;
        r2 = r0.f500e;
        r2 = r2.get(r5);
        r2 = (android.support.v4.view.ViewPager.ItemInfo) r2;
        r7 = r2.f458b;
        r0 = r18;
        r8 = r0.f504i;
        if (r7 < r8) goto L_0x01cf;
    L_0x00f9:
        r7 = r2.f458b;
        r0 = r18;
        r8 = r0.f504i;
        if (r7 != r8) goto L_0x033c;
    L_0x0101:
        if (r2 != 0) goto L_0x0339;
    L_0x0103:
        if (r12 <= 0) goto L_0x0339;
    L_0x0105:
        r0 = r18;
        r2 = r0.f504i;
        r0 = r18;
        r2 = r0.m1295a(r2, r5);
        r10 = r2;
    L_0x0110:
        if (r10 == 0) goto L_0x0180;
    L_0x0112:
        r9 = 0;
        r8 = r5 + -1;
        if (r8 < 0) goto L_0x01d4;
    L_0x0117:
        r0 = r18;
        r2 = r0.f500e;
        r2 = r2.get(r8);
        r2 = (android.support.v4.view.ViewPager.ItemInfo) r2;
    L_0x0121:
        r14 = r18.getClientWidth();
        if (r14 > 0) goto L_0x01d7;
    L_0x0127:
        r6 = 0;
    L_0x0128:
        r0 = r18;
        r7 = r0.f504i;
        r7 = r7 + -1;
        r16 = r7;
        r7 = r9;
        r9 = r16;
        r17 = r8;
        r8 = r5;
        r5 = r17;
    L_0x0138:
        if (r9 < 0) goto L_0x0142;
    L_0x013a:
        r15 = (r7 > r6 ? 1 : (r7 == r6 ? 0 : -1));
        if (r15 < 0) goto L_0x0216;
    L_0x013e:
        if (r9 >= r11) goto L_0x0216;
    L_0x0140:
        if (r2 != 0) goto L_0x01e6;
    L_0x0142:
        r6 = r10.f460d;
        r9 = r8 + 1;
        r2 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
        if (r2 >= 0) goto L_0x017b;
    L_0x014c:
        r0 = r18;
        r2 = r0.f500e;
        r2 = r2.size();
        if (r9 >= r2) goto L_0x024c;
    L_0x0156:
        r0 = r18;
        r2 = r0.f500e;
        r2 = r2.get(r9);
        r2 = (android.support.v4.view.ViewPager.ItemInfo) r2;
        r7 = r2;
    L_0x0161:
        if (r14 > 0) goto L_0x024f;
    L_0x0163:
        r2 = 0;
        r5 = r2;
    L_0x0165:
        r0 = r18;
        r2 = r0.f504i;
        r2 = r2 + 1;
        r16 = r2;
        r2 = r7;
        r7 = r9;
        r9 = r16;
    L_0x0171:
        if (r9 >= r12) goto L_0x017b;
    L_0x0173:
        r11 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1));
        if (r11 < 0) goto L_0x029a;
    L_0x0177:
        if (r9 <= r13) goto L_0x029a;
    L_0x0179:
        if (r2 != 0) goto L_0x025c;
    L_0x017b:
        r0 = r18;
        r0.m1275a(r10, r8, r4);
    L_0x0180:
        r0 = r18;
        r4 = r0.f503h;
        r0 = r18;
        r5 = r0.f504i;
        if (r10 == 0) goto L_0x02e8;
    L_0x018a:
        r2 = r10.f457a;
    L_0x018c:
        r0 = r18;
        r4.m63b(r0, r5, r2);
        r0 = r18;
        r2 = r0.f503h;
        r0 = r18;
        r2.m62b(r0);
        r5 = r18.getChildCount();
        r2 = 0;
        r4 = r2;
    L_0x01a0:
        if (r4 >= r5) goto L_0x02eb;
    L_0x01a2:
        r0 = r18;
        r6 = r0.getChildAt(r4);
        r2 = r6.getLayoutParams();
        r2 = (android.support.v4.view.ViewPager.LayoutParams) r2;
        r2.f467f = r4;
        r7 = r2.f462a;
        if (r7 != 0) goto L_0x01cb;
    L_0x01b4:
        r7 = r2.f464c;
        r8 = 0;
        r7 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1));
        if (r7 != 0) goto L_0x01cb;
    L_0x01bb:
        r0 = r18;
        r6 = r0.m1296a(r6);
        if (r6 == 0) goto L_0x01cb;
    L_0x01c3:
        r7 = r6.f460d;
        r2.f464c = r7;
        r6 = r6.f458b;
        r2.f466e = r6;
    L_0x01cb:
        r2 = r4 + 1;
        r4 = r2;
        goto L_0x01a0;
    L_0x01cf:
        r2 = r5 + 1;
        r5 = r2;
        goto L_0x00dd;
    L_0x01d4:
        r2 = 0;
        goto L_0x0121;
    L_0x01d7:
        r6 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r7 = r10.f460d;
        r6 = r6 - r7;
        r7 = r18.getPaddingLeft();
        r7 = (float) r7;
        r15 = (float) r14;
        r7 = r7 / r15;
        r6 = r6 + r7;
        goto L_0x0128;
    L_0x01e6:
        r15 = r2.f458b;
        if (r9 != r15) goto L_0x0210;
    L_0x01ea:
        r15 = r2.f459c;
        if (r15 != 0) goto L_0x0210;
    L_0x01ee:
        r0 = r18;
        r15 = r0.f500e;
        r15.remove(r5);
        r0 = r18;
        r15 = r0.f503h;
        r2 = r2.f457a;
        r0 = r18;
        r15.m56a(r0, r9, r2);
        r5 = r5 + -1;
        r8 = r8 + -1;
        if (r5 < 0) goto L_0x0214;
    L_0x0206:
        r0 = r18;
        r2 = r0.f500e;
        r2 = r2.get(r5);
        r2 = (android.support.v4.view.ViewPager.ItemInfo) r2;
    L_0x0210:
        r9 = r9 + -1;
        goto L_0x0138;
    L_0x0214:
        r2 = 0;
        goto L_0x0210;
    L_0x0216:
        if (r2 == 0) goto L_0x0230;
    L_0x0218:
        r15 = r2.f458b;
        if (r9 != r15) goto L_0x0230;
    L_0x021c:
        r2 = r2.f460d;
        r7 = r7 + r2;
        r5 = r5 + -1;
        if (r5 < 0) goto L_0x022e;
    L_0x0223:
        r0 = r18;
        r2 = r0.f500e;
        r2 = r2.get(r5);
        r2 = (android.support.v4.view.ViewPager.ItemInfo) r2;
        goto L_0x0210;
    L_0x022e:
        r2 = 0;
        goto L_0x0210;
    L_0x0230:
        r2 = r5 + 1;
        r0 = r18;
        r2 = r0.m1295a(r9, r2);
        r2 = r2.f460d;
        r7 = r7 + r2;
        r8 = r8 + 1;
        if (r5 < 0) goto L_0x024a;
    L_0x023f:
        r0 = r18;
        r2 = r0.f500e;
        r2 = r2.get(r5);
        r2 = (android.support.v4.view.ViewPager.ItemInfo) r2;
        goto L_0x0210;
    L_0x024a:
        r2 = 0;
        goto L_0x0210;
    L_0x024c:
        r7 = 0;
        goto L_0x0161;
    L_0x024f:
        r2 = r18.getPaddingRight();
        r2 = (float) r2;
        r5 = (float) r14;
        r2 = r2 / r5;
        r5 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = r2 + r5;
        r5 = r2;
        goto L_0x0165;
    L_0x025c:
        r11 = r2.f458b;
        if (r9 != r11) goto L_0x0332;
    L_0x0260:
        r11 = r2.f459c;
        if (r11 != 0) goto L_0x0332;
    L_0x0264:
        r0 = r18;
        r11 = r0.f500e;
        r11.remove(r7);
        r0 = r18;
        r11 = r0.f503h;
        r2 = r2.f457a;
        r0 = r18;
        r11.m56a(r0, r9, r2);
        r0 = r18;
        r2 = r0.f500e;
        r2 = r2.size();
        if (r7 >= r2) goto L_0x0298;
    L_0x0280:
        r0 = r18;
        r2 = r0.f500e;
        r2 = r2.get(r7);
        r2 = (android.support.v4.view.ViewPager.ItemInfo) r2;
    L_0x028a:
        r16 = r6;
        r6 = r2;
        r2 = r16;
    L_0x028f:
        r9 = r9 + 1;
        r16 = r2;
        r2 = r6;
        r6 = r16;
        goto L_0x0171;
    L_0x0298:
        r2 = 0;
        goto L_0x028a;
    L_0x029a:
        if (r2 == 0) goto L_0x02c1;
    L_0x029c:
        r11 = r2.f458b;
        if (r9 != r11) goto L_0x02c1;
    L_0x02a0:
        r2 = r2.f460d;
        r6 = r6 + r2;
        r7 = r7 + 1;
        r0 = r18;
        r2 = r0.f500e;
        r2 = r2.size();
        if (r7 >= r2) goto L_0x02bf;
    L_0x02af:
        r0 = r18;
        r2 = r0.f500e;
        r2 = r2.get(r7);
        r2 = (android.support.v4.view.ViewPager.ItemInfo) r2;
    L_0x02b9:
        r16 = r6;
        r6 = r2;
        r2 = r16;
        goto L_0x028f;
    L_0x02bf:
        r2 = 0;
        goto L_0x02b9;
    L_0x02c1:
        r0 = r18;
        r2 = r0.m1295a(r9, r7);
        r7 = r7 + 1;
        r2 = r2.f460d;
        r6 = r6 + r2;
        r0 = r18;
        r2 = r0.f500e;
        r2 = r2.size();
        if (r7 >= r2) goto L_0x02e6;
    L_0x02d6:
        r0 = r18;
        r2 = r0.f500e;
        r2 = r2.get(r7);
        r2 = (android.support.v4.view.ViewPager.ItemInfo) r2;
    L_0x02e0:
        r16 = r6;
        r6 = r2;
        r2 = r16;
        goto L_0x028f;
    L_0x02e6:
        r2 = 0;
        goto L_0x02e0;
    L_0x02e8:
        r2 = 0;
        goto L_0x018c;
    L_0x02eb:
        r18.m1290h();
        r2 = r18.hasFocus();
        if (r2 == 0) goto L_0x002f;
    L_0x02f4:
        r2 = r18.findFocus();
        if (r2 == 0) goto L_0x0330;
    L_0x02fa:
        r0 = r18;
        r2 = r0.m1308b(r2);
    L_0x0300:
        if (r2 == 0) goto L_0x030a;
    L_0x0302:
        r2 = r2.f458b;
        r0 = r18;
        r4 = r0.f504i;
        if (r2 == r4) goto L_0x002f;
    L_0x030a:
        r2 = 0;
    L_0x030b:
        r4 = r18.getChildCount();
        if (r2 >= r4) goto L_0x002f;
    L_0x0311:
        r0 = r18;
        r4 = r0.getChildAt(r2);
        r0 = r18;
        r5 = r0.m1296a(r4);
        if (r5 == 0) goto L_0x032d;
    L_0x031f:
        r5 = r5.f458b;
        r0 = r18;
        r6 = r0.f504i;
        if (r5 != r6) goto L_0x032d;
    L_0x0327:
        r4 = r4.requestFocus(r3);
        if (r4 != 0) goto L_0x002f;
    L_0x032d:
        r2 = r2 + 1;
        goto L_0x030b;
    L_0x0330:
        r2 = 0;
        goto L_0x0300;
    L_0x0332:
        r16 = r6;
        r6 = r2;
        r2 = r16;
        goto L_0x028f;
    L_0x0339:
        r10 = r2;
        goto L_0x0110;
    L_0x033c:
        r2 = r6;
        goto L_0x0101;
    L_0x033f:
        r4 = r3;
        r3 = r2;
        goto L_0x0026;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.view.ViewPager.a(int):void");
    }

    private void m1290h() {
        if (this.af != 0) {
            if (this.ag == null) {
                this.ag = new ArrayList();
            } else {
                this.ag.clear();
            }
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                this.ag.add(getChildAt(i));
            }
            Collections.sort(this.ag, ah);
        }
    }

    private void m1275a(ItemInfo itemInfo, int i, ItemInfo itemInfo2) {
        float f;
        float f2;
        int i2;
        ItemInfo itemInfo3;
        int i3;
        int count = this.f503h.getCount();
        int clientWidth = getClientWidth();
        if (clientWidth > 0) {
            f = ((float) this.f510o) / ((float) clientWidth);
        } else {
            f = 0.0f;
        }
        if (itemInfo2 != null) {
            clientWidth = itemInfo2.f458b;
            int i4;
            if (clientWidth < itemInfo.f458b) {
                f2 = (itemInfo2.f461e + itemInfo2.f460d) + f;
                i4 = clientWidth + 1;
                i2 = 0;
                while (i4 <= itemInfo.f458b && i2 < this.f500e.size()) {
                    itemInfo3 = (ItemInfo) this.f500e.get(i2);
                    while (i4 > itemInfo3.f458b && i2 < this.f500e.size() - 1) {
                        i2++;
                        itemInfo3 = (ItemInfo) this.f500e.get(i2);
                    }
                    while (i4 < itemInfo3.f458b) {
                        f2 += this.f503h.m64c(i4) + f;
                        i4++;
                    }
                    itemInfo3.f461e = f2;
                    f2 += itemInfo3.f460d + f;
                    i4++;
                }
            } else if (clientWidth > itemInfo.f458b) {
                i2 = this.f500e.size() - 1;
                f2 = itemInfo2.f461e;
                i4 = clientWidth - 1;
                while (i4 >= itemInfo.f458b && i2 >= 0) {
                    itemInfo3 = (ItemInfo) this.f500e.get(i2);
                    while (i4 < itemInfo3.f458b && i2 > 0) {
                        i2--;
                        itemInfo3 = (ItemInfo) this.f500e.get(i2);
                    }
                    while (i4 > itemInfo3.f458b) {
                        f2 -= this.f503h.m64c(i4) + f;
                        i4--;
                    }
                    f2 -= itemInfo3.f460d + f;
                    itemInfo3.f461e = f2;
                    i4--;
                }
            }
        }
        int size = this.f500e.size();
        float f3 = itemInfo.f461e;
        i2 = itemInfo.f458b - 1;
        this.f514s = itemInfo.f458b == 0 ? itemInfo.f461e : -3.4028235E38f;
        this.f515t = itemInfo.f458b == count + -1 ? (itemInfo.f461e + itemInfo.f460d) - 1.0f : Float.MAX_VALUE;
        for (i3 = i - 1; i3 >= 0; i3--) {
            itemInfo3 = (ItemInfo) this.f500e.get(i3);
            f2 = f3;
            while (i2 > itemInfo3.f458b) {
                f2 -= this.f503h.m64c(i2) + f;
                i2--;
            }
            f3 = f2 - (itemInfo3.f460d + f);
            itemInfo3.f461e = f3;
            if (itemInfo3.f458b == 0) {
                this.f514s = f3;
            }
            i2--;
        }
        f3 = (itemInfo.f461e + itemInfo.f460d) + f;
        i2 = itemInfo.f458b + 1;
        for (i3 = i + 1; i3 < size; i3++) {
            itemInfo3 = (ItemInfo) this.f500e.get(i3);
            f2 = f3;
            while (i2 < itemInfo3.f458b) {
                f2 = (this.f503h.m64c(i2) + f) + f2;
                i2++;
            }
            if (itemInfo3.f458b == count - 1) {
                this.f515t = (itemInfo3.f460d + f2) - 1.0f;
            }
            itemInfo3.f461e = f2;
            f3 = f2 + (itemInfo3.f460d + f);
            i2++;
        }
        this.f495T = false;
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.f470a = this.f504i;
        if (this.f503h != null) {
            savedState.f471b = this.f503h.m48a();
        }
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            if (this.f503h != null) {
                this.f503h.m52a(savedState.f471b, savedState.f472c);
                m1302a(savedState.f470a, false, true);
                return;
            }
            this.f505j = savedState.f470a;
            this.f506k = savedState.f471b;
            this.f507l = savedState.f472c;
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutParams) {
        android.view.ViewGroup.LayoutParams layoutParams2;
        if (checkLayoutParams(layoutParams)) {
            layoutParams2 = layoutParams;
        } else {
            layoutParams2 = generateLayoutParams(layoutParams);
        }
        LayoutParams layoutParams3 = (LayoutParams) layoutParams2;
        layoutParams3.f462a |= view instanceof Decor;
        if (!this.f518w) {
            super.addView(view, i, layoutParams2);
        } else if (layoutParams3 == null || !layoutParams3.f462a) {
            layoutParams3.f465d = true;
            addViewInLayout(view, i, layoutParams2);
        } else {
            throw new IllegalStateException("Cannot add pager decor view during layout");
        }
    }

    public void removeView(View view) {
        if (this.f518w) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    ItemInfo m1296a(View view) {
        for (int i = 0; i < this.f500e.size(); i++) {
            ItemInfo itemInfo = (ItemInfo) this.f500e.get(i);
            if (this.f503h.m57a(view, itemInfo.f457a)) {
                return itemInfo;
            }
        }
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    android.support.v4.view.ViewPager.ItemInfo m1308b(android.view.View r3) {
        /*
        r2 = this;
    L_0x0000:
        r0 = r3.getParent();
        if (r0 == r2) goto L_0x0012;
    L_0x0006:
        if (r0 == 0) goto L_0x000c;
    L_0x0008:
        r1 = r0 instanceof android.view.View;
        if (r1 != 0) goto L_0x000e;
    L_0x000c:
        r0 = 0;
    L_0x000d:
        return r0;
    L_0x000e:
        r0 = (android.view.View) r0;
        r3 = r0;
        goto L_0x0000;
    L_0x0012:
        r0 = r2.m1296a(r3);
        goto L_0x000d;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.view.ViewPager.b(android.view.View):android.support.v4.view.ViewPager$ItemInfo");
    }

    ItemInfo m1307b(int i) {
        for (int i2 = 0; i2 < this.f500e.size(); i2++) {
            ItemInfo itemInfo = (ItemInfo) this.f500e.get(i2);
            if (itemInfo.f458b == i) {
                return itemInfo;
            }
        }
        return null;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f494S = true;
    }

    protected void onMeasure(int i, int i2) {
        LayoutParams layoutParams;
        int i3;
        setMeasuredDimension(getDefaultSize(0, i), getDefaultSize(0, i2));
        int measuredWidth = getMeasuredWidth();
        this.f479D = Math.min(measuredWidth / 10, this.f478C);
        int paddingLeft = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            int i5;
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() != 8) {
                layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams != null && layoutParams.f462a) {
                    int i6 = layoutParams.f463b & 7;
                    int i7 = layoutParams.f463b & 112;
                    i3 = Integer.MIN_VALUE;
                    i5 = Integer.MIN_VALUE;
                    Object obj = (i7 == 48 || i7 == 80) ? 1 : null;
                    Object obj2 = (i6 == 3 || i6 == 5) ? 1 : null;
                    if (obj != null) {
                        i3 = 1073741824;
                    } else if (obj2 != null) {
                        i5 = 1073741824;
                    }
                    if (layoutParams.width != -2) {
                        i7 = 1073741824;
                        i3 = layoutParams.width != -1 ? layoutParams.width : paddingLeft;
                    } else {
                        i7 = i3;
                        i3 = paddingLeft;
                    }
                    if (layoutParams.height != -2) {
                        i5 = 1073741824;
                        if (layoutParams.height != -1) {
                            measuredWidth = layoutParams.height;
                            childAt.measure(MeasureSpec.makeMeasureSpec(i3, i7), MeasureSpec.makeMeasureSpec(measuredWidth, i5));
                            if (obj != null) {
                                measuredHeight -= childAt.getMeasuredHeight();
                            } else if (obj2 != null) {
                                paddingLeft -= childAt.getMeasuredWidth();
                            }
                        }
                    }
                    measuredWidth = measuredHeight;
                    childAt.measure(MeasureSpec.makeMeasureSpec(i3, i7), MeasureSpec.makeMeasureSpec(measuredWidth, i5));
                    if (obj != null) {
                        measuredHeight -= childAt.getMeasuredHeight();
                    } else if (obj2 != null) {
                        paddingLeft -= childAt.getMeasuredWidth();
                    }
                }
            }
        }
        this.f516u = MeasureSpec.makeMeasureSpec(paddingLeft, 1073741824);
        this.f517v = MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
        this.f518w = true;
        m1310c();
        this.f518w = false;
        i3 = getChildCount();
        for (i5 = 0; i5 < i3; i5++) {
            View childAt2 = getChildAt(i5);
            if (childAt2.getVisibility() != 8) {
                layoutParams = (LayoutParams) childAt2.getLayoutParams();
                if (layoutParams == null || !layoutParams.f462a) {
                    childAt2.measure(MeasureSpec.makeMeasureSpec((int) (layoutParams.f464c * ((float) paddingLeft)), 1073741824), this.f517v);
                }
            }
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3) {
            m1273a(i, i3, this.f510o, this.f510o);
        }
    }

    private void m1273a(int i, int i2, int i3, int i4) {
        if (i2 <= 0 || this.f500e.isEmpty()) {
            ItemInfo b = m1307b(this.f504i);
            int min = (int) ((b != null ? Math.min(b.f461e, this.f515t) : 0.0f) * ((float) ((i - getPaddingLeft()) - getPaddingRight())));
            if (min != getScrollX()) {
                m1278a(false);
                scrollTo(min, getScrollY());
                return;
            }
            return;
        }
        int paddingLeft = (int) (((float) (((i - getPaddingLeft()) - getPaddingRight()) + i3)) * (((float) getScrollX()) / ((float) (((i2 - getPaddingLeft()) - getPaddingRight()) + i4))));
        scrollTo(paddingLeft, getScrollY());
        if (!this.f508m.isFinished()) {
            this.f508m.startScroll(paddingLeft, 0, (int) (m1307b(this.f504i).f461e * ((float) i)), 0, this.f508m.getDuration() - this.f508m.timePassed());
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        LayoutParams layoutParams;
        int max;
        int childCount = getChildCount();
        int i5 = i3 - i;
        int i6 = i4 - i2;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int scrollX = getScrollX();
        int i7 = 0;
        int i8 = 0;
        while (i8 < childCount) {
            int measuredWidth;
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.f462a) {
                    int i9 = layoutParams.f463b & 112;
                    switch (layoutParams.f463b & 7) {
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                            max = Math.max((i5 - childAt.getMeasuredWidth()) / 2, paddingLeft);
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                            max = paddingLeft;
                            paddingLeft = childAt.getMeasuredWidth() + paddingLeft;
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                            measuredWidth = (i5 - paddingRight) - childAt.getMeasuredWidth();
                            paddingRight += childAt.getMeasuredWidth();
                            max = measuredWidth;
                            break;
                        default:
                            max = paddingLeft;
                            break;
                    }
                    int i10;
                    switch (i9) {
                        case C1128R.styleable.StickyListHeadersListView_android_dividerHeight /*16*/:
                            measuredWidth = Math.max((i6 - childAt.getMeasuredHeight()) / 2, paddingTop);
                            i10 = paddingBottom;
                            paddingBottom = paddingTop;
                            paddingTop = i10;
                            break;
                        case C0110R.styleable.Theme_homeAsUpIndicator /*48*/:
                            measuredWidth = childAt.getMeasuredHeight() + paddingTop;
                            i10 = paddingTop;
                            paddingTop = paddingBottom;
                            paddingBottom = measuredWidth;
                            measuredWidth = i10;
                            break;
                        case C0110R.styleable.Theme_panelMenuListTheme /*80*/:
                            measuredWidth = (i6 - paddingBottom) - childAt.getMeasuredHeight();
                            i10 = paddingBottom + childAt.getMeasuredHeight();
                            paddingBottom = paddingTop;
                            paddingTop = i10;
                            break;
                        default:
                            measuredWidth = paddingTop;
                            i10 = paddingBottom;
                            paddingBottom = paddingTop;
                            paddingTop = i10;
                            break;
                    }
                    max += scrollX;
                    childAt.layout(max, measuredWidth, childAt.getMeasuredWidth() + max, childAt.getMeasuredHeight() + measuredWidth);
                    measuredWidth = i7 + 1;
                    i7 = paddingBottom;
                    paddingBottom = paddingTop;
                    paddingTop = paddingRight;
                    paddingRight = paddingLeft;
                    i8++;
                    paddingLeft = paddingRight;
                    paddingRight = paddingTop;
                    paddingTop = i7;
                    i7 = measuredWidth;
                }
            }
            measuredWidth = i7;
            i7 = paddingTop;
            paddingTop = paddingRight;
            paddingRight = paddingLeft;
            i8++;
            paddingLeft = paddingRight;
            paddingRight = paddingTop;
            paddingTop = i7;
            i7 = measuredWidth;
        }
        max = (i5 - paddingLeft) - paddingRight;
        for (paddingRight = 0; paddingRight < childCount; paddingRight++) {
            View childAt2 = getChildAt(paddingRight);
            if (childAt2.getVisibility() != 8) {
                layoutParams = (LayoutParams) childAt2.getLayoutParams();
                if (!layoutParams.f462a) {
                    ItemInfo a = m1296a(childAt2);
                    if (a != null) {
                        i5 = ((int) (a.f461e * ((float) max))) + paddingLeft;
                        if (layoutParams.f465d) {
                            layoutParams.f465d = false;
                            childAt2.measure(MeasureSpec.makeMeasureSpec((int) (layoutParams.f464c * ((float) max)), 1073741824), MeasureSpec.makeMeasureSpec((i6 - paddingTop) - paddingBottom, 1073741824));
                        }
                        childAt2.layout(i5, paddingTop, childAt2.getMeasuredWidth() + i5, childAt2.getMeasuredHeight() + paddingTop);
                    }
                }
            }
        }
        this.f512q = paddingTop;
        this.f513r = i6 - paddingBottom;
        this.f497V = i7;
        if (this.f494S) {
            m1274a(this.f504i, false, 0, false);
        }
        this.f494S = false;
    }

    public void computeScroll() {
        if (this.f508m.isFinished() || !this.f508m.computeScrollOffset()) {
            m1278a(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.f508m.getCurrX();
        int currY = this.f508m.getCurrY();
        if (!(scrollX == currX && scrollY == currY)) {
            scrollTo(currX, currY);
            if (!m1285d(currX)) {
                this.f508m.abortAnimation();
                scrollTo(0, currY);
            }
        }
        ViewCompat.m1174d(this);
    }

    private boolean m1285d(int i) {
        if (this.f500e.size() == 0) {
            this.f496U = false;
            m1299a(0, 0.0f, 0);
            if (this.f496U) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        ItemInfo j = m1292j();
        int clientWidth = getClientWidth();
        int i2 = this.f510o + clientWidth;
        float f = ((float) this.f510o) / ((float) clientWidth);
        int i3 = j.f458b;
        float f2 = ((((float) i) / ((float) clientWidth)) - j.f461e) / (j.f460d + f);
        clientWidth = (int) (((float) i2) * f2);
        this.f496U = false;
        m1299a(i3, f2, clientWidth);
        if (this.f496U) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    protected void m1299a(int i, float f, int i2) {
        int paddingLeft;
        int paddingRight;
        int i3;
        if (this.f497V > 0) {
            int scrollX = getScrollX();
            paddingLeft = getPaddingLeft();
            paddingRight = getPaddingRight();
            int width = getWidth();
            int childCount = getChildCount();
            i3 = 0;
            while (i3 < childCount) {
                int i4;
                View childAt = getChildAt(i3);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.f462a) {
                    int max;
                    switch (layoutParams.f463b & 7) {
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                            max = Math.max((width - childAt.getMeasuredWidth()) / 2, paddingLeft);
                            i4 = paddingRight;
                            paddingRight = paddingLeft;
                            paddingLeft = i4;
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                            max = childAt.getWidth() + paddingLeft;
                            i4 = paddingLeft;
                            paddingLeft = paddingRight;
                            paddingRight = max;
                            max = i4;
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                            max = (width - paddingRight) - childAt.getMeasuredWidth();
                            i4 = paddingRight + childAt.getMeasuredWidth();
                            paddingRight = paddingLeft;
                            paddingLeft = i4;
                            break;
                        default:
                            max = paddingLeft;
                            i4 = paddingRight;
                            paddingRight = paddingLeft;
                            paddingLeft = i4;
                            break;
                    }
                    max = (max + scrollX) - childAt.getLeft();
                    if (max != 0) {
                        childAt.offsetLeftAndRight(max);
                    }
                } else {
                    i4 = paddingRight;
                    paddingRight = paddingLeft;
                    paddingLeft = i4;
                }
                i3++;
                i4 = paddingLeft;
                paddingLeft = paddingRight;
                paddingRight = i4;
            }
        }
        m1281b(i, f, i2);
        if (this.ad != null) {
            paddingRight = getScrollX();
            i3 = getChildCount();
            for (paddingLeft = 0; paddingLeft < i3; paddingLeft++) {
                View childAt2 = getChildAt(paddingLeft);
                if (!((LayoutParams) childAt2.getLayoutParams()).f462a) {
                    this.ad.m1261a(childAt2, ((float) (childAt2.getLeft() - paddingRight)) / ((float) getClientWidth()));
                }
            }
        }
        this.f496U = true;
    }

    private void m1281b(int i, float f, int i2) {
        if (this.aa != null) {
            this.aa.m1259a(i, f, i2);
        }
        if (this.f498W != null) {
            int size = this.f498W.size();
            for (int i3 = 0; i3 < size; i3++) {
                OnPageChangeListener onPageChangeListener = (OnPageChangeListener) this.f498W.get(i3);
                if (onPageChangeListener != null) {
                    onPageChangeListener.m1259a(i, f, i2);
                }
            }
        }
        if (this.ab != null) {
            this.ab.m1259a(i, f, i2);
        }
    }

    private void m1286e(int i) {
        if (this.aa != null) {
            this.aa.m1258a(i);
        }
        if (this.f498W != null) {
            int size = this.f498W.size();
            for (int i2 = 0; i2 < size; i2++) {
                OnPageChangeListener onPageChangeListener = (OnPageChangeListener) this.f498W.get(i2);
                if (onPageChangeListener != null) {
                    onPageChangeListener.m1258a(i);
                }
            }
        }
        if (this.ab != null) {
            this.ab.m1258a(i);
        }
    }

    private void m1287f(int i) {
        if (this.aa != null) {
            this.aa.m1260b(i);
        }
        if (this.f498W != null) {
            int size = this.f498W.size();
            for (int i2 = 0; i2 < size; i2++) {
                OnPageChangeListener onPageChangeListener = (OnPageChangeListener) this.f498W.get(i2);
                if (onPageChangeListener != null) {
                    onPageChangeListener.m1260b(i);
                }
            }
        }
        if (this.ab != null) {
            this.ab.m1260b(i);
        }
    }

    private void m1278a(boolean z) {
        int scrollX;
        boolean z2 = this.aj == 2;
        if (z2) {
            setScrollingCacheEnabled(false);
            this.f508m.abortAnimation();
            scrollX = getScrollX();
            int scrollY = getScrollY();
            int currX = this.f508m.getCurrX();
            int currY = this.f508m.getCurrY();
            if (!(scrollX == currX && scrollY == currY)) {
                scrollTo(currX, currY);
                if (currX != scrollX) {
                    m1285d(currX);
                }
            }
        }
        this.f520y = false;
        boolean z3 = z2;
        for (scrollX = 0; scrollX < this.f500e.size(); scrollX++) {
            ItemInfo itemInfo = (ItemInfo) this.f500e.get(scrollX);
            if (itemInfo.f459c) {
                itemInfo.f459c = false;
                z3 = true;
            }
        }
        if (!z3) {
            return;
        }
        if (z) {
            ViewCompat.m1163a((View) this, this.ai);
        } else {
            this.ai.run();
        }
    }

    private boolean m1279a(float f, float f2) {
        return (f < ((float) this.f479D) && f2 > 0.0f) || (f > ((float) (getWidth() - this.f479D)) && f2 < 0.0f);
    }

    private void m1282b(boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            int i2;
            if (z) {
                i2 = 2;
            } else {
                i2 = 0;
            }
            ViewCompat.m1158a(getChildAt(i), i2, null);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            m1291i();
            return false;
        }
        if (action != 0) {
            if (this.f476A) {
                return true;
            }
            if (this.f477B) {
                return false;
            }
        }
        switch (action) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                float x = motionEvent.getX();
                this.f483H = x;
                this.f481F = x;
                x = motionEvent.getY();
                this.f484I = x;
                this.f482G = x;
                this.f485J = MotionEventCompat.m990b(motionEvent, 0);
                this.f477B = false;
                this.f508m.computeScrollOffset();
                if (this.aj == 2 && Math.abs(this.f508m.getFinalX() - this.f508m.getCurrX()) > this.f490O) {
                    this.f508m.abortAnimation();
                    this.f520y = false;
                    m1310c();
                    this.f476A = true;
                    m1284c(true);
                    setScrollState(1);
                    break;
                }
                m1278a(false);
                this.f476A = false;
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                action = this.f485J;
                if (action != -1) {
                    action = MotionEventCompat.m988a(motionEvent, action);
                    float c = MotionEventCompat.m991c(motionEvent, action);
                    float f = c - this.f481F;
                    float abs = Math.abs(f);
                    float d = MotionEventCompat.m993d(motionEvent, action);
                    float abs2 = Math.abs(d - this.f484I);
                    if (f == 0.0f || m1279a(this.f481F, f) || !m1306a(this, false, (int) f, (int) c, (int) d)) {
                        if (abs > ((float) this.f480E) && 0.5f * abs > abs2) {
                            this.f476A = true;
                            m1284c(true);
                            setScrollState(1);
                            this.f481F = f > 0.0f ? this.f483H + ((float) this.f480E) : this.f483H - ((float) this.f480E);
                            this.f482G = d;
                            setScrollingCacheEnabled(true);
                        } else if (abs2 > ((float) this.f480E)) {
                            this.f477B = true;
                        }
                        if (this.f476A && m1283b(c)) {
                            ViewCompat.m1174d(this);
                            break;
                        }
                    }
                    this.f481F = c;
                    this.f482G = d;
                    this.f477B = true;
                    return false;
                }
                break;
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                m1277a(motionEvent);
                break;
        }
        if (this.f486K == null) {
            this.f486K = VelocityTracker.obtain();
        }
        this.f486K.addMovement(motionEvent);
        return this.f476A;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (this.f491P) {
            return true;
        }
        if (motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) {
            return false;
        }
        if (this.f503h == null || this.f503h.getCount() == 0) {
            return false;
        }
        if (this.f486K == null) {
            this.f486K = VelocityTracker.obtain();
        }
        this.f486K.addMovement(motionEvent);
        float x;
        int a;
        switch (motionEvent.getAction() & 255) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                this.f508m.abortAnimation();
                this.f520y = false;
                m1310c();
                x = motionEvent.getX();
                this.f483H = x;
                this.f481F = x;
                x = motionEvent.getY();
                this.f484I = x;
                this.f482G = x;
                this.f485J = MotionEventCompat.m990b(motionEvent, 0);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                if (this.f476A) {
                    VelocityTracker velocityTracker = this.f486K;
                    velocityTracker.computeCurrentVelocity(1000, (float) this.f488M);
                    a = (int) VelocityTrackerCompat.m1020a(velocityTracker, this.f485J);
                    this.f520y = true;
                    int clientWidth = getClientWidth();
                    int scrollX = getScrollX();
                    ItemInfo j = m1292j();
                    m1303a(m1270a(j.f458b, ((((float) scrollX) / ((float) clientWidth)) - j.f461e) / j.f460d, a, (int) (MotionEventCompat.m991c(motionEvent, MotionEventCompat.m988a(motionEvent, this.f485J)) - this.f483H)), true, true, a);
                    z = m1291i();
                    break;
                }
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                if (!this.f476A) {
                    a = MotionEventCompat.m988a(motionEvent, this.f485J);
                    if (a == -1) {
                        z = m1291i();
                        break;
                    }
                    float c = MotionEventCompat.m991c(motionEvent, a);
                    float abs = Math.abs(c - this.f481F);
                    float d = MotionEventCompat.m993d(motionEvent, a);
                    x = Math.abs(d - this.f482G);
                    if (abs > ((float) this.f480E) && abs > x) {
                        this.f476A = true;
                        m1284c(true);
                        if (c - this.f483H > 0.0f) {
                            x = this.f483H + ((float) this.f480E);
                        } else {
                            x = this.f483H - ((float) this.f480E);
                        }
                        this.f481F = x;
                        this.f482G = d;
                        setScrollState(1);
                        setScrollingCacheEnabled(true);
                        ViewParent parent = getParent();
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
                    }
                }
                if (this.f476A) {
                    z = false | m1283b(MotionEventCompat.m991c(motionEvent, MotionEventCompat.m988a(motionEvent, this.f485J)));
                    break;
                }
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                if (this.f476A) {
                    m1274a(this.f504i, true, 0, false);
                    z = m1291i();
                    break;
                }
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                a = MotionEventCompat.m989b(motionEvent);
                this.f481F = MotionEventCompat.m991c(motionEvent, a);
                this.f485J = MotionEventCompat.m990b(motionEvent, a);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                m1277a(motionEvent);
                this.f481F = MotionEventCompat.m991c(motionEvent, MotionEventCompat.m988a(motionEvent, this.f485J));
                break;
        }
        if (z) {
            ViewCompat.m1174d(this);
        }
        return true;
    }

    private boolean m1291i() {
        this.f485J = -1;
        m1293k();
        return this.f492Q.m1768c() | this.f493R.m1768c();
    }

    private void m1284c(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    private boolean m1283b(float f) {
        boolean z;
        float f2;
        boolean z2 = true;
        boolean z3 = false;
        float f3 = this.f481F - f;
        this.f481F = f;
        float scrollX = ((float) getScrollX()) + f3;
        int clientWidth = getClientWidth();
        float f4 = ((float) clientWidth) * this.f514s;
        float f5 = ((float) clientWidth) * this.f515t;
        ItemInfo itemInfo = (ItemInfo) this.f500e.get(0);
        ItemInfo itemInfo2 = (ItemInfo) this.f500e.get(this.f500e.size() - 1);
        if (itemInfo.f458b != 0) {
            f4 = itemInfo.f461e * ((float) clientWidth);
            z = false;
        } else {
            z = true;
        }
        if (itemInfo2.f458b != this.f503h.getCount() - 1) {
            f2 = itemInfo2.f461e * ((float) clientWidth);
            z2 = false;
        } else {
            f2 = f5;
        }
        if (scrollX < f4) {
            if (z) {
                z3 = this.f492Q.m1763a(Math.abs(f4 - scrollX) / ((float) clientWidth));
            }
        } else if (scrollX > f2) {
            if (z2) {
                z3 = this.f493R.m1763a(Math.abs(scrollX - f2) / ((float) clientWidth));
            }
            f4 = f2;
        } else {
            f4 = scrollX;
        }
        this.f481F += f4 - ((float) ((int) f4));
        scrollTo((int) f4, getScrollY());
        m1285d((int) f4);
        return z3;
    }

    private ItemInfo m1292j() {
        float f;
        int clientWidth = getClientWidth();
        float scrollX = clientWidth > 0 ? ((float) getScrollX()) / ((float) clientWidth) : 0.0f;
        if (clientWidth > 0) {
            f = ((float) this.f510o) / ((float) clientWidth);
        } else {
            f = 0.0f;
        }
        float f2 = 0.0f;
        float f3 = 0.0f;
        int i = -1;
        int i2 = 0;
        Object obj = 1;
        ItemInfo itemInfo = null;
        while (i2 < this.f500e.size()) {
            int i3;
            ItemInfo itemInfo2;
            ItemInfo itemInfo3 = (ItemInfo) this.f500e.get(i2);
            ItemInfo itemInfo4;
            if (obj != null || itemInfo3.f458b == i + 1) {
                itemInfo4 = itemInfo3;
                i3 = i2;
                itemInfo2 = itemInfo4;
            } else {
                itemInfo3 = this.f501f;
                itemInfo3.f461e = (f2 + f3) + f;
                itemInfo3.f458b = i + 1;
                itemInfo3.f460d = this.f503h.m64c(itemInfo3.f458b);
                itemInfo4 = itemInfo3;
                i3 = i2 - 1;
                itemInfo2 = itemInfo4;
            }
            f2 = itemInfo2.f461e;
            f3 = (itemInfo2.f460d + f2) + f;
            if (obj == null && scrollX < f2) {
                return itemInfo;
            }
            if (scrollX < f3 || i3 == this.f500e.size() - 1) {
                return itemInfo2;
            }
            f3 = f2;
            i = itemInfo2.f458b;
            obj = null;
            f2 = itemInfo2.f460d;
            itemInfo = itemInfo2;
            i2 = i3 + 1;
        }
        return itemInfo;
    }

    private int m1270a(int i, float f, int i2, int i3) {
        if (Math.abs(i3) <= this.f489N || Math.abs(i2) <= this.f487L) {
            i = (int) ((i >= this.f504i ? 0.4f : 0.6f) + (((float) i) + f));
        } else if (i2 <= 0) {
            i++;
        }
        if (this.f500e.size() <= 0) {
            return i;
        }
        return Math.max(((ItemInfo) this.f500e.get(0)).f458b, Math.min(i, ((ItemInfo) this.f500e.get(this.f500e.size() - 1)).f458b));
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        int i = 0;
        int a = ViewCompat.m1154a(this);
        if (a == 0 || (a == 1 && this.f503h != null && this.f503h.getCount() > 1)) {
            int height;
            int width;
            if (!this.f492Q.m1762a()) {
                a = canvas.save();
                height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate((float) ((-height) + getPaddingTop()), this.f514s * ((float) width));
                this.f492Q.m1761a(height, width);
                i = 0 | this.f492Q.m1766a(canvas);
                canvas.restoreToCount(a);
            }
            if (!this.f493R.m1762a()) {
                a = canvas.save();
                height = getWidth();
                width = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate((float) (-getPaddingTop()), (-(this.f515t + 1.0f)) * ((float) height));
                this.f493R.m1761a(width, height);
                i |= this.f493R.m1766a(canvas);
                canvas.restoreToCount(a);
            }
        } else {
            this.f492Q.m1767b();
            this.f493R.m1767b();
        }
        if (i != 0) {
            ViewCompat.m1174d(this);
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f510o > 0 && this.f511p != null && this.f500e.size() > 0 && this.f503h != null) {
            int scrollX = getScrollX();
            int width = getWidth();
            float f = ((float) this.f510o) / ((float) width);
            ItemInfo itemInfo = (ItemInfo) this.f500e.get(0);
            float f2 = itemInfo.f461e;
            int size = this.f500e.size();
            int i = itemInfo.f458b;
            int i2 = ((ItemInfo) this.f500e.get(size - 1)).f458b;
            int i3 = 0;
            int i4 = i;
            while (i4 < i2) {
                float f3;
                while (i4 > itemInfo.f458b && i3 < size) {
                    i3++;
                    itemInfo = (ItemInfo) this.f500e.get(i3);
                }
                if (i4 == itemInfo.f458b) {
                    f3 = (itemInfo.f461e + itemInfo.f460d) * ((float) width);
                    f2 = (itemInfo.f461e + itemInfo.f460d) + f;
                } else {
                    float c = this.f503h.m64c(i4);
                    f3 = (f2 + c) * ((float) width);
                    f2 += c + f;
                }
                if (((float) this.f510o) + f3 > ((float) scrollX)) {
                    this.f511p.setBounds((int) f3, this.f512q, (int) ((((float) this.f510o) + f3) + 0.5f), this.f513r);
                    this.f511p.draw(canvas);
                }
                if (f3 <= ((float) (scrollX + width))) {
                    i4++;
                } else {
                    return;
                }
            }
        }
    }

    private void m1277a(MotionEvent motionEvent) {
        int b = MotionEventCompat.m989b(motionEvent);
        if (MotionEventCompat.m990b(motionEvent, b) == this.f485J) {
            b = b == 0 ? 1 : 0;
            this.f481F = MotionEventCompat.m991c(motionEvent, b);
            this.f485J = MotionEventCompat.m990b(motionEvent, b);
            if (this.f486K != null) {
                this.f486K.clear();
            }
        }
    }

    private void m1293k() {
        this.f476A = false;
        this.f477B = false;
        if (this.f486K != null) {
            this.f486K.recycle();
            this.f486K = null;
        }
    }

    private void setScrollingCacheEnabled(boolean z) {
        if (this.f519x != z) {
            this.f519x = z;
        }
    }

    public boolean canScrollHorizontally(int i) {
        boolean z = true;
        if (this.f503h == null) {
            return false;
        }
        int clientWidth = getClientWidth();
        int scrollX = getScrollX();
        if (i < 0) {
            if (scrollX <= ((int) (((float) clientWidth) * this.f514s))) {
                z = false;
            }
            return z;
        } else if (i <= 0) {
            return false;
        } else {
            if (scrollX >= ((int) (((float) clientWidth) * this.f515t))) {
                z = false;
            }
            return z;
        }
    }

    protected boolean m1306a(View view, boolean z, int i, int i2, int i3) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (i2 + scrollX >= childAt.getLeft() && i2 + scrollX < childAt.getRight() && i3 + scrollY >= childAt.getTop() && i3 + scrollY < childAt.getBottom()) {
                    if (m1306a(childAt, true, i, (i2 + scrollX) - childAt.getLeft(), (i3 + scrollY) - childAt.getTop())) {
                        return true;
                    }
                }
            }
        }
        if (z && ViewCompat.m1166a(view, -i)) {
            return true;
        }
        return false;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || m1305a(keyEvent);
    }

    public boolean m1305a(KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0) {
            return false;
        }
        switch (keyEvent.getKeyCode()) {
            case C1128R.styleable.StickyListHeadersListView_android_requiresFadingEdge /*21*/:
                return m1311c(17);
            case C1128R.styleable.StickyListHeadersListView_stickyListHeadersListViewStyle /*22*/:
                return m1311c(66);
            case C0110R.styleable.Theme_popupWindowStyle /*61*/:
                if (VERSION.SDK_INT < 11) {
                    return false;
                }
                if (KeyEventCompat.m910a(keyEvent)) {
                    return m1311c(2);
                }
                if (KeyEventCompat.m911a(keyEvent, 1)) {
                    return m1311c(1);
                }
                return false;
            default:
                return false;
        }
    }

    public boolean m1311c(int i) {
        View view;
        boolean d;
        View findFocus = findFocus();
        if (findFocus == this) {
            view = null;
        } else {
            if (findFocus != null) {
                Object obj;
                for (ViewPager parent = findFocus.getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
                    if (parent == this) {
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                if (obj == null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(findFocus.getClass().getSimpleName());
                    for (ViewParent parent2 = findFocus.getParent(); parent2 instanceof ViewGroup; parent2 = parent2.getParent()) {
                        stringBuilder.append(" => ").append(parent2.getClass().getSimpleName());
                    }
                    Log.e("ViewPager", "arrowScroll tried to find focus based on non-child current focused view " + stringBuilder.toString());
                    view = null;
                }
            }
            view = findFocus;
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, view, i);
        if (findNextFocus == null || findNextFocus == view) {
            if (i == 17 || i == 1) {
                d = m1312d();
            } else {
                if (i == 66 || i == 2) {
                    d = m1313e();
                }
                d = false;
            }
        } else if (i == 17) {
            d = (view == null || m1271a(this.f502g, findNextFocus).left < m1271a(this.f502g, view).left) ? findNextFocus.requestFocus() : m1312d();
        } else {
            if (i == 66) {
                d = (view == null || m1271a(this.f502g, findNextFocus).left > m1271a(this.f502g, view).left) ? findNextFocus.requestFocus() : m1313e();
            }
            d = false;
        }
        if (d) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i));
        }
        return d;
    }

    private Rect m1271a(Rect rect, View view) {
        Rect rect2;
        if (rect == null) {
            rect2 = new Rect();
        } else {
            rect2 = rect;
        }
        if (view == null) {
            rect2.set(0, 0, 0, 0);
            return rect2;
        }
        rect2.left = view.getLeft();
        rect2.right = view.getRight();
        rect2.top = view.getTop();
        rect2.bottom = view.getBottom();
        ViewPager parent = view.getParent();
        while ((parent instanceof ViewGroup) && parent != this) {
            ViewGroup viewGroup = parent;
            rect2.left += viewGroup.getLeft();
            rect2.right += viewGroup.getRight();
            rect2.top += viewGroup.getTop();
            rect2.bottom += viewGroup.getBottom();
            parent = viewGroup.getParent();
        }
        return rect2;
    }

    boolean m1312d() {
        if (this.f504i <= 0) {
            return false;
        }
        m1301a(this.f504i - 1, true);
        return true;
    }

    boolean m1313e() {
        if (this.f503h == null || this.f504i >= this.f503h.getCount() - 1) {
            return false;
        }
        m1301a(this.f504i + 1, true);
        return true;
    }

    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                View childAt = getChildAt(i3);
                if (childAt.getVisibility() == 0) {
                    ItemInfo a = m1296a(childAt);
                    if (a != null && a.f458b == this.f504i) {
                        childAt.addFocusables(arrayList, i, i2);
                    }
                }
            }
        }
        if ((descendantFocusability == 262144 && size != arrayList.size()) || !isFocusable()) {
            return;
        }
        if (((i2 & 1) != 1 || !isInTouchMode() || isFocusableInTouchMode()) && arrayList != null) {
            arrayList.add(this);
        }
    }

    public void addTouchables(ArrayList<View> arrayList) {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0) {
                ItemInfo a = m1296a(childAt);
                if (a != null && a.f458b == this.f504i) {
                    childAt.addTouchables(arrayList);
                }
            }
        }
    }

    protected boolean onRequestFocusInDescendants(int i, Rect rect) {
        int i2;
        int i3 = -1;
        int childCount = getChildCount();
        if ((i & 2) != 0) {
            i3 = 1;
            i2 = 0;
        } else {
            i2 = childCount - 1;
            childCount = -1;
        }
        while (i2 != childCount) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0) {
                ItemInfo a = m1296a(childAt);
                if (a != null && a.f458b == this.f504i && childAt.requestFocus(i, rect)) {
                    return true;
                }
            }
            i2 += i3;
        }
        return false;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0) {
                ItemInfo a = m1296a(childAt);
                if (a != null && a.f458b == this.f504i && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }
}
