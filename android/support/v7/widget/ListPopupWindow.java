package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.text.TextUtilsCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.appcompat.C0057R;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import java.lang.reflect.Method;
import se.emilsjolander.stickylistheaders.C1128R;

public class ListPopupWindow {
    private static Method f1386a;
    private static Method f1387c;
    private final ListSelectorHider f1388A;
    private Runnable f1389B;
    private final Handler f1390C;
    private Rect f1391D;
    private boolean f1392E;
    private int f1393F;
    int f1394b;
    private Context f1395d;
    private PopupWindow f1396e;
    private ListAdapter f1397f;
    private DropDownListView f1398g;
    private int f1399h;
    private int f1400i;
    private int f1401j;
    private int f1402k;
    private int f1403l;
    private boolean f1404m;
    private int f1405n;
    private boolean f1406o;
    private boolean f1407p;
    private View f1408q;
    private int f1409r;
    private DataSetObserver f1410s;
    private View f1411t;
    private Drawable f1412u;
    private OnItemClickListener f1413v;
    private OnItemSelectedListener f1414w;
    private final ResizePopupRunnable f1415x;
    private final PopupTouchInterceptor f1416y;
    private final PopupScrollListener f1417z;

    public abstract class ForwardingListener implements OnTouchListener {
        private final float f975a;
        private final int f976b;
        private final int f977c;
        private final View f978d;
        private Runnable f979e;
        private Runnable f980f;
        private boolean f981g;
        private boolean f982h;
        private int f983i;
        private final int[] f984j;

        class DisallowIntercept implements Runnable {
            final /* synthetic */ ForwardingListener f1577a;

            private DisallowIntercept(ForwardingListener forwardingListener) {
                this.f1577a = forwardingListener;
            }

            public void run() {
                this.f1577a.f978d.getParent().requestDisallowInterceptTouchEvent(true);
            }
        }

        class TriggerLongPress implements Runnable {
            final /* synthetic */ ForwardingListener f1578a;

            private TriggerLongPress(ForwardingListener forwardingListener) {
                this.f1578a = forwardingListener;
            }

            public void run() {
                this.f1578a.m2329e();
            }
        }

        public abstract ListPopupWindow m2330a();

        public ForwardingListener(View view) {
            this.f984j = new int[2];
            this.f978d = view;
            this.f975a = (float) ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
            this.f976b = ViewConfiguration.getTapTimeout();
            this.f977c = (this.f976b + ViewConfiguration.getLongPressTimeout()) / 2;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            boolean b;
            boolean z = this.f981g;
            if (z) {
                b = this.f982h ? m2326b(motionEvent) : m2326b(motionEvent) || !m2332c();
            } else {
                boolean z2 = m2322a(motionEvent) && m2331b();
                if (z2) {
                    long uptimeMillis = SystemClock.uptimeMillis();
                    MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                    this.f978d.onTouchEvent(obtain);
                    obtain.recycle();
                }
                b = z2;
            }
            this.f981g = b;
            if (b || z) {
                return true;
            }
            return false;
        }

        protected boolean m2331b() {
            ListPopupWindow a = m2330a();
            if (!(a == null || a.m2828k())) {
                a.m2814c();
            }
            return true;
        }

        protected boolean m2332c() {
            ListPopupWindow a = m2330a();
            if (a != null && a.m2828k()) {
                a.m2826i();
            }
            return true;
        }

        private boolean m2322a(MotionEvent motionEvent) {
            View view = this.f978d;
            if (!view.isEnabled()) {
                return false;
            }
            switch (MotionEventCompat.m987a(motionEvent)) {
                case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                    this.f983i = motionEvent.getPointerId(0);
                    this.f982h = false;
                    if (this.f979e == null) {
                        this.f979e = new DisallowIntercept();
                    }
                    view.postDelayed(this.f979e, (long) this.f976b);
                    if (this.f980f == null) {
                        this.f980f = new TriggerLongPress();
                    }
                    view.postDelayed(this.f980f, (long) this.f977c);
                    return false;
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    m2328d();
                    return false;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    int findPointerIndex = motionEvent.findPointerIndex(this.f983i);
                    if (findPointerIndex < 0 || m2323a(view, motionEvent.getX(findPointerIndex), motionEvent.getY(findPointerIndex), this.f975a)) {
                        return false;
                    }
                    m2328d();
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    return true;
                default:
                    return false;
            }
        }

        private void m2328d() {
            if (this.f980f != null) {
                this.f978d.removeCallbacks(this.f980f);
            }
            if (this.f979e != null) {
                this.f978d.removeCallbacks(this.f979e);
            }
        }

        private void m2329e() {
            m2328d();
            View view = this.f978d;
            if (view.isEnabled() && !view.isLongClickable() && m2331b()) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                view.onTouchEvent(obtain);
                obtain.recycle();
                this.f981g = true;
                this.f982h = true;
            }
        }

        private boolean m2326b(MotionEvent motionEvent) {
            boolean z = true;
            View view = this.f978d;
            ListPopupWindow a = m2330a();
            if (a == null || !a.m2828k()) {
                return false;
            }
            View a2 = a.f1398g;
            if (a2 == null || !a2.isShown()) {
                return false;
            }
            MotionEvent obtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
            m2327b(view, obtainNoHistory);
            m2324a(a2, obtainNoHistory);
            boolean a3 = a2.m3257a(obtainNoHistory, this.f983i);
            obtainNoHistory.recycle();
            int a4 = MotionEventCompat.m987a(motionEvent);
            boolean z2;
            if (a4 == 1 || a4 == 3) {
                z2 = false;
            } else {
                z2 = true;
            }
            if (!(a3 && r2)) {
                z = false;
            }
            return z;
        }

        private static boolean m2323a(View view, float f, float f2, float f3) {
            return f >= (-f3) && f2 >= (-f3) && f < ((float) (view.getRight() - view.getLeft())) + f3 && f2 < ((float) (view.getBottom() - view.getTop())) + f3;
        }

        private boolean m2324a(View view, MotionEvent motionEvent) {
            int[] iArr = this.f984j;
            view.getLocationOnScreen(iArr);
            motionEvent.offsetLocation((float) (-iArr[0]), (float) (-iArr[1]));
            return true;
        }

        private boolean m2327b(View view, MotionEvent motionEvent) {
            int[] iArr = this.f984j;
            view.getLocationOnScreen(iArr);
            motionEvent.offsetLocation((float) iArr[0], (float) iArr[1]);
            return true;
        }
    }

    /* renamed from: android.support.v7.widget.ListPopupWindow.1 */
    class C00841 extends ForwardingListener {
        final /* synthetic */ ListPopupWindow f1560a;

        public ListPopupWindow m3243a() {
            return this.f1560a;
        }
    }

    /* renamed from: android.support.v7.widget.ListPopupWindow.2 */
    class C00852 implements Runnable {
        final /* synthetic */ ListPopupWindow f1561a;

        C00852(ListPopupWindow listPopupWindow) {
            this.f1561a = listPopupWindow;
        }

        public void run() {
            View e = this.f1561a.m2818e();
            if (e != null && e.getWindowToken() != null) {
                this.f1561a.m2814c();
            }
        }
    }

    /* renamed from: android.support.v7.widget.ListPopupWindow.3 */
    class C00863 implements OnItemSelectedListener {
        final /* synthetic */ ListPopupWindow f1562a;

        C00863(ListPopupWindow listPopupWindow) {
            this.f1562a = listPopupWindow;
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            if (i != -1) {
                DropDownListView a = this.f1562a.f1398g;
                if (a != null) {
                    a.f1572g = false;
                }
            }
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    class DropDownListView extends ListViewCompat {
        private boolean f1572g;
        private boolean f1573h;
        private boolean f1574i;
        private ViewPropertyAnimatorCompat f1575j;
        private ListViewAutoScrollHelper f1576k;

        public DropDownListView(Context context, boolean z) {
            super(context, null, C0057R.attr.dropDownListViewStyle);
            this.f1573h = z;
            setCacheColorHint(0);
        }

        public boolean m3257a(MotionEvent motionEvent, int i) {
            boolean z;
            boolean z2;
            int a = MotionEventCompat.m987a(motionEvent);
            switch (a) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    z = false;
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    z = true;
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    z = false;
                    z2 = false;
                    break;
                default:
                    z = false;
                    z2 = true;
                    break;
            }
            int findPointerIndex = motionEvent.findPointerIndex(i);
            if (findPointerIndex < 0) {
                z = false;
                z2 = false;
            } else {
                int x = (int) motionEvent.getX(findPointerIndex);
                findPointerIndex = (int) motionEvent.getY(findPointerIndex);
                int pointToPosition = pointToPosition(x, findPointerIndex);
                if (pointToPosition == -1) {
                    z2 = z;
                    z = true;
                } else {
                    View childAt = getChildAt(pointToPosition - getFirstVisiblePosition());
                    m3253a(childAt, pointToPosition, (float) x, (float) findPointerIndex);
                    if (a == 1) {
                        m3252a(childAt, pointToPosition);
                    }
                    z = false;
                    z2 = true;
                }
            }
            if (!z2 || r0) {
                m3255d();
            }
            if (z2) {
                if (this.f1576k == null) {
                    this.f1576k = new ListViewAutoScrollHelper(this);
                }
                this.f1576k.m1690a(true);
                this.f1576k.onTouch(this, motionEvent);
            } else if (this.f1576k != null) {
                this.f1576k.m1690a(false);
            }
            return z2;
        }

        private void m3252a(View view, int i) {
            performItemClick(view, i, getItemIdAtPosition(i));
        }

        private void m3255d() {
            this.f1574i = false;
            setPressed(false);
            drawableStateChanged();
            View childAt = getChildAt(this.f - getFirstVisiblePosition());
            if (childAt != null) {
                childAt.setPressed(false);
            }
            if (this.f1575j != null) {
                this.f1575j.m1405b();
                this.f1575j = null;
            }
        }

        private void m3253a(View view, int i, float f, float f2) {
            this.f1574i = true;
            if (VERSION.SDK_INT >= 21) {
                drawableHotspotChanged(f, f2);
            }
            if (!isPressed()) {
                setPressed(true);
            }
            layoutChildren();
            if (this.f != -1) {
                View childAt = getChildAt(this.f - getFirstVisiblePosition());
                if (!(childAt == null || childAt == view || !childAt.isPressed())) {
                    childAt.setPressed(false);
                }
            }
            this.f = i;
            float left = f - ((float) view.getLeft());
            float top = f2 - ((float) view.getTop());
            if (VERSION.SDK_INT >= 21) {
                view.drawableHotspotChanged(left, top);
            }
            if (!view.isPressed()) {
                view.setPressed(true);
            }
            setSelection(i);
            m3246a(i, view, f, f2);
            setSelectorEnabled(false);
            refreshDrawableState();
        }

        protected boolean m3256a() {
            return this.f1574i || super.m3248a();
        }

        public boolean isInTouchMode() {
            return (this.f1573h && this.f1572g) || super.isInTouchMode();
        }

        public boolean hasWindowFocus() {
            return this.f1573h || super.hasWindowFocus();
        }

        public boolean isFocused() {
            return this.f1573h || super.isFocused();
        }

        public boolean hasFocus() {
            return this.f1573h || super.hasFocus();
        }
    }

    class ListSelectorHider implements Runnable {
        final /* synthetic */ ListPopupWindow f1579a;

        private ListSelectorHider(ListPopupWindow listPopupWindow) {
            this.f1579a = listPopupWindow;
        }

        public void run() {
            this.f1579a.m2827j();
        }
    }

    class PopupDataSetObserver extends DataSetObserver {
        final /* synthetic */ ListPopupWindow f1580a;

        private PopupDataSetObserver(ListPopupWindow listPopupWindow) {
            this.f1580a = listPopupWindow;
        }

        public void onChanged() {
            if (this.f1580a.m2828k()) {
                this.f1580a.m2814c();
            }
        }

        public void onInvalidated() {
            this.f1580a.m2826i();
        }
    }

    class PopupScrollListener implements OnScrollListener {
        final /* synthetic */ ListPopupWindow f1581a;

        private PopupScrollListener(ListPopupWindow listPopupWindow) {
            this.f1581a = listPopupWindow;
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (i == 1 && !this.f1581a.m2829l() && this.f1581a.f1396e.getContentView() != null) {
                this.f1581a.f1390C.removeCallbacks(this.f1581a.f1415x);
                this.f1581a.f1415x.run();
            }
        }
    }

    class PopupTouchInterceptor implements OnTouchListener {
        final /* synthetic */ ListPopupWindow f1582a;

        private PopupTouchInterceptor(ListPopupWindow listPopupWindow) {
            this.f1582a = listPopupWindow;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (action == 0 && this.f1582a.f1396e != null && this.f1582a.f1396e.isShowing() && x >= 0 && x < this.f1582a.f1396e.getWidth() && y >= 0 && y < this.f1582a.f1396e.getHeight()) {
                this.f1582a.f1390C.postDelayed(this.f1582a.f1415x, 250);
            } else if (action == 1) {
                this.f1582a.f1390C.removeCallbacks(this.f1582a.f1415x);
            }
            return false;
        }
    }

    class ResizePopupRunnable implements Runnable {
        final /* synthetic */ ListPopupWindow f1583a;

        private ResizePopupRunnable(ListPopupWindow listPopupWindow) {
            this.f1583a = listPopupWindow;
        }

        public void run() {
            if (this.f1583a.f1398g != null && ViewCompat.m1192u(this.f1583a.f1398g) && this.f1583a.f1398g.getCount() > this.f1583a.f1398g.getChildCount() && this.f1583a.f1398g.getChildCount() <= this.f1583a.f1394b) {
                this.f1583a.f1396e.setInputMethodMode(2);
                this.f1583a.m2814c();
            }
        }
    }

    static {
        try {
            f1386a = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", new Class[]{Boolean.TYPE});
        } catch (NoSuchMethodException e) {
            Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
        }
        try {
            f1387c = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", new Class[]{View.class, Integer.TYPE, Boolean.TYPE});
        } catch (NoSuchMethodException e2) {
            Log.i("ListPopupWindow", "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
        }
    }

    public ListPopupWindow(Context context) {
        this(context, null, C0057R.attr.listPopupWindowStyle);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i, int i2) {
        this.f1399h = -2;
        this.f1400i = -2;
        this.f1403l = 1002;
        this.f1405n = 0;
        this.f1406o = false;
        this.f1407p = false;
        this.f1394b = Integer.MAX_VALUE;
        this.f1409r = 0;
        this.f1415x = new ResizePopupRunnable();
        this.f1416y = new PopupTouchInterceptor();
        this.f1417z = new PopupScrollListener();
        this.f1388A = new ListSelectorHider();
        this.f1391D = new Rect();
        this.f1395d = context;
        this.f1390C = new Handler(context.getMainLooper());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0057R.styleable.ListPopupWindow, i, i2);
        this.f1401j = obtainStyledAttributes.getDimensionPixelOffset(C0057R.styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        this.f1402k = obtainStyledAttributes.getDimensionPixelOffset(C0057R.styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
        if (this.f1402k != 0) {
            this.f1404m = true;
        }
        obtainStyledAttributes.recycle();
        this.f1396e = new AppCompatPopupWindow(context, attributeSet, i);
        this.f1396e.setInputMethodMode(1);
        this.f1393F = TextUtilsCompat.m713a(this.f1395d.getResources().getConfiguration().locale);
    }

    public void m2810a(ListAdapter listAdapter) {
        if (this.f1410s == null) {
            this.f1410s = new PopupDataSetObserver();
        } else if (this.f1397f != null) {
            this.f1397f.unregisterDataSetObserver(this.f1410s);
        }
        this.f1397f = listAdapter;
        if (this.f1397f != null) {
            listAdapter.registerDataSetObserver(this.f1410s);
        }
        if (this.f1398g != null) {
            this.f1398g.setAdapter(this.f1397f);
        }
    }

    public void m2806a(int i) {
        this.f1409r = i;
    }

    public void m2812a(boolean z) {
        this.f1392E = z;
        this.f1396e.setFocusable(z);
    }

    public Drawable m2816d() {
        return this.f1396e.getBackground();
    }

    public void m2807a(Drawable drawable) {
        this.f1396e.setBackgroundDrawable(drawable);
    }

    public View m2818e() {
        return this.f1411t;
    }

    public void m2808a(View view) {
        this.f1411t = view;
    }

    public int m2820f() {
        return this.f1401j;
    }

    public void m2813b(int i) {
        this.f1401j = i;
    }

    public int m2822g() {
        if (this.f1404m) {
            return this.f1402k;
        }
        return 0;
    }

    public void m2815c(int i) {
        this.f1402k = i;
        this.f1404m = true;
    }

    public void m2817d(int i) {
        this.f1405n = i;
    }

    public int m2824h() {
        return this.f1400i;
    }

    public void m2819e(int i) {
        this.f1400i = i;
    }

    public void m2821f(int i) {
        Drawable background = this.f1396e.getBackground();
        if (background != null) {
            background.getPadding(this.f1391D);
            this.f1400i = (this.f1391D.left + this.f1391D.right) + i;
            return;
        }
        m2819e(i);
    }

    public void m2809a(OnItemClickListener onItemClickListener) {
        this.f1413v = onItemClickListener;
    }

    public void m2814c() {
        boolean z = true;
        boolean z2 = false;
        int i = -1;
        int b = m2801b();
        boolean l = m2829l();
        PopupWindowCompat.m1827a(this.f1396e, this.f1403l);
        int i2;
        if (this.f1396e.isShowing()) {
            int i3;
            int i4;
            if (this.f1400i == -1) {
                i3 = -1;
            } else if (this.f1400i == -2) {
                i3 = m2818e().getWidth();
            } else {
                i3 = this.f1400i;
            }
            if (this.f1399h == -1) {
                if (!l) {
                    b = -1;
                }
                PopupWindow popupWindow;
                if (l) {
                    popupWindow = this.f1396e;
                    if (this.f1400i == -1) {
                        i2 = -1;
                    } else {
                        i2 = 0;
                    }
                    popupWindow.setWidth(i2);
                    this.f1396e.setHeight(0);
                    i4 = b;
                } else {
                    popupWindow = this.f1396e;
                    if (this.f1400i == -1) {
                        i2 = -1;
                    } else {
                        i2 = 0;
                    }
                    popupWindow.setWidth(i2);
                    this.f1396e.setHeight(-1);
                    i4 = b;
                }
            } else if (this.f1399h == -2) {
                i4 = b;
            } else {
                i4 = this.f1399h;
            }
            PopupWindow popupWindow2 = this.f1396e;
            if (!(this.f1407p || this.f1406o)) {
                z2 = true;
            }
            popupWindow2.setOutsideTouchable(z2);
            popupWindow2 = this.f1396e;
            View e = m2818e();
            b = this.f1401j;
            int i5 = this.f1402k;
            if (i3 < 0) {
                i3 = -1;
            }
            if (i4 >= 0) {
                i = i4;
            }
            popupWindow2.update(e, b, i5, i3, i);
            return;
        }
        if (this.f1400i == -1) {
            i2 = -1;
        } else if (this.f1400i == -2) {
            i2 = m2818e().getWidth();
        } else {
            i2 = this.f1400i;
        }
        if (this.f1399h == -1) {
            b = -1;
        } else if (this.f1399h != -2) {
            b = this.f1399h;
        }
        this.f1396e.setWidth(i2);
        this.f1396e.setHeight(b);
        m2803b(true);
        popupWindow2 = this.f1396e;
        if (this.f1407p || this.f1406o) {
            z = false;
        }
        popupWindow2.setOutsideTouchable(z);
        this.f1396e.setTouchInterceptor(this.f1416y);
        PopupWindowCompat.m1828a(this.f1396e, m2818e(), this.f1401j, this.f1402k, this.f1405n);
        this.f1398g.setSelection(-1);
        if (!this.f1392E || this.f1398g.isInTouchMode()) {
            m2827j();
        }
        if (!this.f1392E) {
            this.f1390C.post(this.f1388A);
        }
    }

    public void m2826i() {
        this.f1396e.dismiss();
        m2800a();
        this.f1396e.setContentView(null);
        this.f1398g = null;
        this.f1390C.removeCallbacks(this.f1415x);
    }

    public void m2811a(OnDismissListener onDismissListener) {
        this.f1396e.setOnDismissListener(onDismissListener);
    }

    private void m2800a() {
        if (this.f1408q != null) {
            ViewParent parent = this.f1408q.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.f1408q);
            }
        }
    }

    public void m2823g(int i) {
        this.f1396e.setInputMethodMode(i);
    }

    public void m2825h(int i) {
        DropDownListView dropDownListView = this.f1398g;
        if (m2828k() && dropDownListView != null) {
            dropDownListView.f1572g = false;
            dropDownListView.setSelection(i);
            if (VERSION.SDK_INT >= 11 && dropDownListView.getChoiceMode() != 0) {
                dropDownListView.setItemChecked(i, true);
            }
        }
    }

    public void m2827j() {
        DropDownListView dropDownListView = this.f1398g;
        if (dropDownListView != null) {
            dropDownListView.f1572g = true;
            dropDownListView.requestLayout();
        }
    }

    public boolean m2828k() {
        return this.f1396e.isShowing();
    }

    public boolean m2829l() {
        return this.f1396e.getInputMethodMode() == 2;
    }

    public ListView m2830m() {
        return this.f1398g;
    }

    private int m2801b() {
        int i;
        int i2;
        int i3;
        int i4;
        boolean z = true;
        LayoutParams layoutParams;
        View view;
        if (this.f1398g == null) {
            Context context = this.f1395d;
            this.f1389B = new C00852(this);
            this.f1398g = new DropDownListView(context, !this.f1392E);
            if (this.f1412u != null) {
                this.f1398g.setSelector(this.f1412u);
            }
            this.f1398g.setAdapter(this.f1397f);
            this.f1398g.setOnItemClickListener(this.f1413v);
            this.f1398g.setFocusable(true);
            this.f1398g.setFocusableInTouchMode(true);
            this.f1398g.setOnItemSelectedListener(new C00863(this));
            this.f1398g.setOnScrollListener(this.f1417z);
            if (this.f1414w != null) {
                this.f1398g.setOnItemSelectedListener(this.f1414w);
            }
            View view2 = this.f1398g;
            View view3 = this.f1408q;
            if (view3 != null) {
                View linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(1);
                ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-1, 0, 1.0f);
                switch (this.f1409r) {
                    case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                        linearLayout.addView(view3);
                        linearLayout.addView(view2, layoutParams2);
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        linearLayout.addView(view2, layoutParams2);
                        linearLayout.addView(view3);
                        break;
                    default:
                        Log.e("ListPopupWindow", "Invalid hint position " + this.f1409r);
                        break;
                }
                if (this.f1400i >= 0) {
                    i = this.f1400i;
                    i2 = Integer.MIN_VALUE;
                } else {
                    i2 = 0;
                    i = 0;
                }
                view3.measure(MeasureSpec.makeMeasureSpec(i, i2), 0);
                layoutParams = (LayoutParams) view3.getLayoutParams();
                i2 = layoutParams.bottomMargin + (view3.getMeasuredHeight() + layoutParams.topMargin);
                view = linearLayout;
            } else {
                view = view2;
                i2 = 0;
            }
            this.f1396e.setContentView(view);
            i3 = i2;
        } else {
            ViewGroup viewGroup = (ViewGroup) this.f1396e.getContentView();
            view = this.f1408q;
            if (view != null) {
                layoutParams = (LayoutParams) view.getLayoutParams();
                i3 = layoutParams.bottomMargin + (view.getMeasuredHeight() + layoutParams.topMargin);
            } else {
                i3 = 0;
            }
        }
        Drawable background = this.f1396e.getBackground();
        if (background != null) {
            background.getPadding(this.f1391D);
            i2 = this.f1391D.top + this.f1391D.bottom;
            if (this.f1404m) {
                i4 = i2;
            } else {
                this.f1402k = -this.f1391D.top;
                i4 = i2;
            }
        } else {
            this.f1391D.setEmpty();
            i4 = 0;
        }
        if (this.f1396e.getInputMethodMode() != 2) {
            z = false;
        }
        i = m2798a(m2818e(), this.f1402k, z);
        if (this.f1406o || this.f1399h == -1) {
            return i + i4;
        }
        int makeMeasureSpec;
        switch (this.f1400i) {
            case -2:
                makeMeasureSpec = MeasureSpec.makeMeasureSpec(this.f1395d.getResources().getDisplayMetrics().widthPixels - (this.f1391D.left + this.f1391D.right), Integer.MIN_VALUE);
                break;
            case -1:
                makeMeasureSpec = MeasureSpec.makeMeasureSpec(this.f1395d.getResources().getDisplayMetrics().widthPixels - (this.f1391D.left + this.f1391D.right), 1073741824);
                break;
            default:
                makeMeasureSpec = MeasureSpec.makeMeasureSpec(this.f1400i, 1073741824);
                break;
        }
        i2 = this.f1398g.m3244a(makeMeasureSpec, 0, -1, i - i3, -1);
        if (i2 > 0) {
            i3 += i4;
        }
        return i2 + i3;
    }

    private void m2803b(boolean z) {
        if (f1386a != null) {
            try {
                f1386a.invoke(this.f1396e, new Object[]{Boolean.valueOf(z)});
            } catch (Exception e) {
                Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
        }
    }

    private int m2798a(View view, int i, boolean z) {
        if (f1387c != null) {
            try {
                return ((Integer) f1387c.invoke(this.f1396e, new Object[]{view, Integer.valueOf(i), Boolean.valueOf(z)})).intValue();
            } catch (Exception e) {
                Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
            }
        }
        return this.f1396e.getMaxAvailableHeight(view, i);
    }
}
