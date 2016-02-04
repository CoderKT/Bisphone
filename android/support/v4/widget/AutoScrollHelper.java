package android.support.v4.widget;

import android.content.res.Resources;
import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import org.jivesoftware.smack.tcp.XMPPTCPConnection.PacketWriter;
import se.emilsjolander.stickylistheaders.C1128R;

public abstract class AutoScrollHelper implements OnTouchListener {
    private static final int f568r;
    private final ClampedScroller f569a;
    private final Interpolator f570b;
    private final View f571c;
    private Runnable f572d;
    private float[] f573e;
    private float[] f574f;
    private int f575g;
    private int f576h;
    private float[] f577i;
    private float[] f578j;
    private float[] f579k;
    private boolean f580l;
    private boolean f581m;
    private boolean f582n;
    private boolean f583o;
    private boolean f584p;
    private boolean f585q;

    class ClampedScroller {
        private int f556a;
        private int f557b;
        private float f558c;
        private float f559d;
        private long f560e;
        private long f561f;
        private int f562g;
        private int f563h;
        private long f564i;
        private float f565j;
        private int f566k;

        public ClampedScroller() {
            this.f560e = Long.MIN_VALUE;
            this.f564i = -1;
            this.f561f = 0;
            this.f562g = 0;
            this.f563h = 0;
        }

        public void m1658a(int i) {
            this.f556a = i;
        }

        public void m1660b(int i) {
            this.f557b = i;
        }

        public void m1656a() {
            this.f560e = AnimationUtils.currentAnimationTimeMillis();
            this.f564i = -1;
            this.f561f = this.f560e;
            this.f565j = 0.5f;
            this.f562g = 0;
            this.f563h = 0;
        }

        public void m1659b() {
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.f566k = AutoScrollHelper.m1675b((int) (currentAnimationTimeMillis - this.f560e), 0, this.f557b);
            this.f565j = m1655a(currentAnimationTimeMillis);
            this.f564i = currentAnimationTimeMillis;
        }

        public boolean m1661c() {
            return this.f564i > 0 && AnimationUtils.currentAnimationTimeMillis() > this.f564i + ((long) this.f566k);
        }

        private float m1655a(long j) {
            if (j < this.f560e) {
                return 0.0f;
            }
            if (this.f564i < 0 || j < this.f564i) {
                return AutoScrollHelper.m1674b(((float) (j - this.f560e)) / ((float) this.f556a), 0.0f, 1.0f) * 0.5f;
            }
            long j2 = j - this.f564i;
            return (AutoScrollHelper.m1674b(((float) j2) / ((float) this.f566k), 0.0f, 1.0f) * this.f565j) + (1.0f - this.f565j);
        }

        private float m1654a(float f) {
            return ((-4.0f * f) * f) + (4.0f * f);
        }

        public void m1662d() {
            if (this.f561f == 0) {
                throw new RuntimeException("Cannot compute scroll delta before calling start()");
            }
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            float a = m1654a(m1655a(currentAnimationTimeMillis));
            long j = currentAnimationTimeMillis - this.f561f;
            this.f561f = currentAnimationTimeMillis;
            this.f562g = (int) ((((float) j) * a) * this.f558c);
            this.f563h = (int) ((((float) j) * a) * this.f559d);
        }

        public void m1657a(float f, float f2) {
            this.f558c = f;
            this.f559d = f2;
        }

        public int m1663e() {
            return (int) (this.f558c / Math.abs(this.f558c));
        }

        public int m1664f() {
            return (int) (this.f559d / Math.abs(this.f559d));
        }

        public int m1665g() {
            return this.f562g;
        }

        public int m1666h() {
            return this.f563h;
        }
    }

    class ScrollAnimationRunnable implements Runnable {
        final /* synthetic */ AutoScrollHelper f567a;

        private ScrollAnimationRunnable(AutoScrollHelper autoScrollHelper) {
            this.f567a = autoScrollHelper;
        }

        public void run() {
            if (this.f567a.f583o) {
                if (this.f567a.f581m) {
                    this.f567a.f581m = false;
                    this.f567a.f569a.m1656a();
                }
                ClampedScroller c = this.f567a.f569a;
                if (c.m1661c() || !this.f567a.m1671a()) {
                    this.f567a.f583o = false;
                    return;
                }
                if (this.f567a.f582n) {
                    this.f567a.f582n = false;
                    this.f567a.m1682d();
                }
                c.m1662d();
                this.f567a.m1691a(c.m1665g(), c.m1666h());
                ViewCompat.m1163a(this.f567a.f571c, (Runnable) this);
            }
        }
    }

    public abstract void m1691a(int i, int i2);

    public abstract boolean m1699e(int i);

    public abstract boolean m1700f(int i);

    static {
        f568r = ViewConfiguration.getTapTimeout();
    }

    public AutoScrollHelper(View view) {
        this.f569a = new ClampedScroller();
        this.f570b = new AccelerateInterpolator();
        this.f573e = new float[]{0.0f, 0.0f};
        this.f574f = new float[]{Float.MAX_VALUE, Float.MAX_VALUE};
        this.f577i = new float[]{0.0f, 0.0f};
        this.f578j = new float[]{0.0f, 0.0f};
        this.f579k = new float[]{Float.MAX_VALUE, Float.MAX_VALUE};
        this.f571c = view;
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        int i = (int) ((1575.0f * displayMetrics.density) + 0.5f);
        int i2 = (int) ((displayMetrics.density * 315.0f) + 0.5f);
        m1688a((float) i, (float) i);
        m1692b((float) i2, (float) i2);
        m1689a(1);
        m1698e(Float.MAX_VALUE, Float.MAX_VALUE);
        m1696d(0.2f, 0.2f);
        m1694c(1.0f, 1.0f);
        m1693b(f568r);
        m1695c((int) PacketWriter.QUEUE_SIZE);
        m1697d((int) PacketWriter.QUEUE_SIZE);
    }

    public AutoScrollHelper m1690a(boolean z) {
        if (this.f584p && !z) {
            m1680c();
        }
        this.f584p = z;
        return this;
    }

    public AutoScrollHelper m1688a(float f, float f2) {
        this.f579k[0] = f / 1000.0f;
        this.f579k[1] = f2 / 1000.0f;
        return this;
    }

    public AutoScrollHelper m1692b(float f, float f2) {
        this.f578j[0] = f / 1000.0f;
        this.f578j[1] = f2 / 1000.0f;
        return this;
    }

    public AutoScrollHelper m1694c(float f, float f2) {
        this.f577i[0] = f / 1000.0f;
        this.f577i[1] = f2 / 1000.0f;
        return this;
    }

    public AutoScrollHelper m1689a(int i) {
        this.f575g = i;
        return this;
    }

    public AutoScrollHelper m1696d(float f, float f2) {
        this.f573e[0] = f;
        this.f573e[1] = f2;
        return this;
    }

    public AutoScrollHelper m1698e(float f, float f2) {
        this.f574f[0] = f;
        this.f574f[1] = f2;
        return this;
    }

    public AutoScrollHelper m1693b(int i) {
        this.f576h = i;
        return this;
    }

    public AutoScrollHelper m1695c(int i) {
        this.f569a.m1658a(i);
        return this;
    }

    public AutoScrollHelper m1697d(int i) {
        this.f569a.m1660b(i);
        return this;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z = true;
        if (!this.f584p) {
            return false;
        }
        switch (MotionEventCompat.m987a(motionEvent)) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                this.f582n = true;
                this.f580l = false;
                break;
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                m1680c();
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                break;
        }
        this.f569a.m1657a(m1669a(0, motionEvent.getX(), (float) view.getWidth(), (float) this.f571c.getWidth()), m1669a(1, motionEvent.getY(), (float) view.getHeight(), (float) this.f571c.getHeight()));
        if (!this.f583o && m1671a()) {
            m1676b();
        }
        if (!(this.f585q && this.f583o)) {
            z = false;
        }
        return z;
    }

    private boolean m1671a() {
        ClampedScroller clampedScroller = this.f569a;
        int f = clampedScroller.m1664f();
        int e = clampedScroller.m1663e();
        return (f != 0 && m1700f(f)) || (e != 0 && m1699e(e));
    }

    private void m1676b() {
        if (this.f572d == null) {
            this.f572d = new ScrollAnimationRunnable();
        }
        this.f583o = true;
        this.f581m = true;
        if (this.f580l || this.f576h <= 0) {
            this.f572d.run();
        } else {
            ViewCompat.m1164a(this.f571c, this.f572d, (long) this.f576h);
        }
        this.f580l = true;
    }

    private void m1680c() {
        if (this.f581m) {
            this.f583o = false;
        } else {
            this.f569a.m1659b();
        }
    }

    private float m1669a(int i, float f, float f2, float f3) {
        float a = m1668a(this.f573e[i], f2, this.f574f[i], f);
        if (a == 0.0f) {
            return 0.0f;
        }
        float f4 = this.f577i[i];
        float f5 = this.f578j[i];
        float f6 = this.f579k[i];
        f4 *= f3;
        if (a > 0.0f) {
            return m1674b(a * f4, f5, f6);
        }
        return -m1674b((-a) * f4, f5, f6);
    }

    private float m1668a(float f, float f2, float f3, float f4) {
        float f5;
        float b = m1674b(f * f2, 0.0f, f3);
        b = m1685f(f2 - f4, b) - m1685f(f4, b);
        if (b < 0.0f) {
            f5 = -this.f570b.getInterpolation(-b);
        } else if (b <= 0.0f) {
            return 0.0f;
        } else {
            f5 = this.f570b.getInterpolation(b);
        }
        return m1674b(f5, -1.0f, 1.0f);
    }

    private float m1685f(float f, float f2) {
        if (f2 == 0.0f) {
            return 0.0f;
        }
        switch (this.f575g) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                if (f >= f2) {
                    return 0.0f;
                }
                if (f >= 0.0f) {
                    return 1.0f - (f / f2);
                }
                if (this.f583o && this.f575g == 1) {
                    return 1.0f;
                }
                return 0.0f;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                if (f < 0.0f) {
                    return f / (-f2);
                }
                return 0.0f;
            default:
                return 0.0f;
        }
    }

    private static int m1675b(int i, int i2, int i3) {
        if (i > i3) {
            return i3;
        }
        if (i < i2) {
            return i2;
        }
        return i;
    }

    private static float m1674b(float f, float f2, float f3) {
        if (f > f3) {
            return f3;
        }
        if (f < f2) {
            return f2;
        }
        return f;
    }

    private void m1682d() {
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
        this.f571c.onTouchEvent(obtain);
        obtain.recycle();
    }
}
