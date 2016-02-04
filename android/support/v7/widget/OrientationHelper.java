package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.view.View;
import se.emilsjolander.stickylistheaders.C1128R;

public abstract class OrientationHelper {
    protected final LayoutManager f1586a;
    private int f1587b;

    /* renamed from: android.support.v7.widget.OrientationHelper.1 */
    final class C00871 extends OrientationHelper {
        C00871(LayoutManager layoutManager) {
            super(null);
        }

        public int m3285d() {
            return this.a.m3167r() - this.a.m3171v();
        }

        public int m3287e() {
            return this.a.m3167r();
        }

        public void m3281a(int i) {
            this.a.m3152h(i);
        }

        public int m3283c() {
            return this.a.m3169t();
        }

        public int m3284c(View view) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            return layoutParams.rightMargin + (this.a.m3141e(view) + layoutParams.leftMargin);
        }

        public int m3286d(View view) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            return layoutParams.bottomMargin + (this.a.m3146f(view) + layoutParams.topMargin);
        }

        public int m3282b(View view) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            return layoutParams.rightMargin + this.a.m3153i(view);
        }

        public int m3280a(View view) {
            return this.a.m3149g(view) - ((LayoutParams) view.getLayoutParams()).leftMargin;
        }

        public int m3288f() {
            return (this.a.m3167r() - this.a.m3169t()) - this.a.m3171v();
        }

        public int m3289g() {
            return this.a.m3171v();
        }
    }

    /* renamed from: android.support.v7.widget.OrientationHelper.2 */
    final class C00882 extends OrientationHelper {
        C00882(LayoutManager layoutManager) {
            super(null);
        }

        public int m3295d() {
            return this.a.m3168s() - this.a.m3172w();
        }

        public int m3297e() {
            return this.a.m3168s();
        }

        public void m3291a(int i) {
            this.a.m3154i(i);
        }

        public int m3293c() {
            return this.a.m3170u();
        }

        public int m3294c(View view) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            return layoutParams.bottomMargin + (this.a.m3146f(view) + layoutParams.topMargin);
        }

        public int m3296d(View view) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            return layoutParams.rightMargin + (this.a.m3141e(view) + layoutParams.leftMargin);
        }

        public int m3292b(View view) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            return layoutParams.bottomMargin + this.a.m3155j(view);
        }

        public int m3290a(View view) {
            return this.a.m3151h(view) - ((LayoutParams) view.getLayoutParams()).topMargin;
        }

        public int m3298f() {
            return (this.a.m3168s() - this.a.m3170u()) - this.a.m3172w();
        }

        public int m3299g() {
            return this.a.m3172w();
        }
    }

    public abstract int m3268a(View view);

    public abstract void m3270a(int i);

    public abstract int m3272b(View view);

    public abstract int m3273c();

    public abstract int m3274c(View view);

    public abstract int m3275d();

    public abstract int m3276d(View view);

    public abstract int m3277e();

    public abstract int m3278f();

    public abstract int m3279g();

    private OrientationHelper(LayoutManager layoutManager) {
        this.f1587b = Integer.MIN_VALUE;
        this.f1586a = layoutManager;
    }

    public void m3269a() {
        this.f1587b = m3278f();
    }

    public int m3271b() {
        return Integer.MIN_VALUE == this.f1587b ? 0 : m3278f() - this.f1587b;
    }

    public static OrientationHelper m3266a(LayoutManager layoutManager, int i) {
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                return m3265a(layoutManager);
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return m3267b(layoutManager);
            default:
                throw new IllegalArgumentException("invalid orientation");
        }
    }

    public static OrientationHelper m3265a(LayoutManager layoutManager) {
        return new C00871(layoutManager);
    }

    public static OrientationHelper m3267b(LayoutManager layoutManager) {
        return new C00882(layoutManager);
    }
}
