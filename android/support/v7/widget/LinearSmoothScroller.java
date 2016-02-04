package android.support.v7.widget;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.SmoothScroller;
import android.support.v7.widget.RecyclerView.SmoothScroller.Action;
import android.support.v7.widget.RecyclerView.State;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import se.emilsjolander.stickylistheaders.C1128R;

public abstract class LinearSmoothScroller extends SmoothScroller {
    private final float f1513a;
    protected final LinearInterpolator f1514b;
    protected final DecelerateInterpolator f1515c;
    protected PointF f1516d;
    protected int f1517e;
    protected int f1518f;

    public abstract PointF m3036a(int i);

    public LinearSmoothScroller(Context context) {
        this.f1514b = new LinearInterpolator();
        this.f1515c = new DecelerateInterpolator();
        this.f1517e = 0;
        this.f1518f = 0;
        this.f1513a = m3033a(context.getResources().getDisplayMetrics());
    }

    protected void m3037a() {
    }

    protected void m3040a(View view, State state, Action action) {
        int b = m3042b(view, m3044c());
        int a = m3035a(view, m3046d());
        int b2 = m3041b((int) Math.sqrt((double) ((b * b) + (a * a))));
        if (b2 > 0) {
            action.m3441a(-b, -a, b2, this.f1515c);
        }
    }

    protected void m3038a(int i, int i2, State state, Action action) {
        if (m3031j() == 0) {
            m3027f();
            return;
        }
        this.f1517e = m3032a(this.f1517e, i);
        this.f1518f = m3032a(this.f1518f, i2);
        if (this.f1517e == 0 && this.f1518f == 0) {
            m3039a(action);
        }
    }

    protected void m3043b() {
        this.f1518f = 0;
        this.f1517e = 0;
        this.f1516d = null;
    }

    protected float m3033a(DisplayMetrics displayMetrics) {
        return 25.0f / ((float) displayMetrics.densityDpi);
    }

    protected int m3041b(int i) {
        return (int) Math.ceil(((double) m3045c(i)) / 0.3356d);
    }

    protected int m3045c(int i) {
        return (int) Math.ceil((double) (((float) Math.abs(i)) * this.f1513a));
    }

    protected int m3044c() {
        if (this.f1516d == null || this.f1516d.x == 0.0f) {
            return 0;
        }
        return this.f1516d.x > 0.0f ? 1 : -1;
    }

    protected int m3046d() {
        if (this.f1516d == null || this.f1516d.y == 0.0f) {
            return 0;
        }
        return this.f1516d.y > 0.0f ? 1 : -1;
    }

    protected void m3039a(Action action) {
        PointF a = m3036a(m3030i());
        if (a == null || (a.x == 0.0f && a.y == 0.0f)) {
            Log.e("LinearSmoothScroller", "To support smooth scrolling, you should override \nLayoutManager#computeScrollVectorForPosition.\nFalling back to instant scroll");
            action.m3440a(m3030i());
            m3027f();
            return;
        }
        m3019a(a);
        this.f1516d = a;
        this.f1517e = (int) (a.x * 10000.0f);
        this.f1518f = (int) (a.y * 10000.0f);
        action.m3441a((int) (((float) this.f1517e) * 1.2f), (int) (((float) this.f1518f) * 1.2f), (int) (((float) m3045c(10000)) * 1.2f), this.f1514b);
    }

    private int m3032a(int i, int i2) {
        int i3 = i - i2;
        if (i * i3 <= 0) {
            return 0;
        }
        return i3;
    }

    public int m3034a(int i, int i2, int i3, int i4, int i5) {
        switch (i5) {
            case -1:
                return i3 - i;
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                int i6 = i3 - i;
                if (i6 > 0) {
                    return i6;
                }
                i6 = i4 - i2;
                return i6 >= 0 ? 0 : i6;
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return i4 - i2;
            default:
                throw new IllegalArgumentException("snap preference should be one of the constants defined in SmoothScroller, starting with SNAP_");
        }
    }

    public int m3035a(View view, int i) {
        LayoutManager e = m3025e();
        if (!e.m3139d()) {
            return 0;
        }
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return m3034a(e.m3151h(view) - layoutParams.topMargin, e.m3155j(view) + layoutParams.bottomMargin, e.m3170u(), e.m3168s() - e.m3172w(), i);
    }

    public int m3042b(View view, int i) {
        LayoutManager e = m3025e();
        if (!e.m3132c()) {
            return 0;
        }
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return m3034a(e.m3149g(view) - layoutParams.leftMargin, e.m3153i(view) + layoutParams.rightMargin, e.m3169t(), e.m3167r() - e.m3171v(), i);
    }
}
