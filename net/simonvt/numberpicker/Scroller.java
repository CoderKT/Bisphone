package net.simonvt.numberpicker;

import android.content.Context;
import android.view.ViewConfiguration;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import app.C0110R;
import se.emilsjolander.stickylistheaders.C1128R;

public class Scroller {
    private static float f8545B;
    private static float f8546C;
    private static float f8547u;
    private static float f8548v;
    private static float f8549w;
    private static float f8550x;
    private static final float[] f8551y;
    private final float f8552A;
    private int f8553a;
    private int f8554b;
    private int f8555c;
    private int f8556d;
    private int f8557e;
    private int f8558f;
    private int f8559g;
    private int f8560h;
    private int f8561i;
    private int f8562j;
    private int f8563k;
    private long f8564l;
    private int f8565m;
    private float f8566n;
    private float f8567o;
    private float f8568p;
    private boolean f8569q;
    private Interpolator f8570r;
    private boolean f8571s;
    private float f8572t;
    private float f8573z;

    static {
        f8547u = (float) (Math.log(0.75d) / Math.log(0.9d));
        f8548v = 800.0f;
        f8549w = 0.4f;
        f8550x = 1.0f - f8549w;
        f8551y = new float[C0110R.styleable.Theme_buttonStyleSmall];
        float f = 0.0f;
        int i = 0;
        while (i <= 100) {
            float f2;
            float f3 = ((float) i) / 100.0f;
            float f4 = 1.0f;
            float f5 = f;
            while (true) {
                f = ((f4 - f5) / 2.0f) + f5;
                f2 = (3.0f * f) * (1.0f - f);
                float f6 = ((((1.0f - f) * f8549w) + (f8550x * f)) * f2) + ((f * f) * f);
                if (((double) Math.abs(f6 - f3)) < 1.0E-5d) {
                    break;
                } else if (f6 > f3) {
                    f4 = f;
                } else {
                    f5 = f;
                }
            }
            f8551y[i] = ((f * f) * f) + f2;
            i++;
            f = f5;
        }
        f8551y[100] = 1.0f;
        f8545B = 8.0f;
        f8546C = 1.0f;
        f8546C = 1.0f / m13413a(1.0f);
    }

    public Scroller(Context context, Interpolator interpolator) {
        this(context, interpolator, true);
    }

    public Scroller(Context context, Interpolator interpolator, boolean z) {
        this.f8569q = true;
        this.f8570r = interpolator;
        this.f8552A = context.getResources().getDisplayMetrics().density * 160.0f;
        this.f8573z = m13414b(ViewConfiguration.getScrollFriction());
        this.f8571s = z;
    }

    private float m13414b(float f) {
        return (386.0878f * this.f8552A) * f;
    }

    public final boolean m13418a() {
        return this.f8569q;
    }

    public final void m13417a(boolean z) {
        this.f8569q = z;
    }

    public final int m13419b() {
        return this.f8563k;
    }

    public float m13420c() {
        return this.f8572t - ((this.f8573z * ((float) m13424g())) / 2000.0f);
    }

    public final int m13421d() {
        return this.f8555c;
    }

    public final int m13422e() {
        return this.f8557e;
    }

    public boolean m13423f() {
        if (this.f8569q) {
            return false;
        }
        int currentAnimationTimeMillis = (int) (AnimationUtils.currentAnimationTimeMillis() - this.f8564l);
        if (currentAnimationTimeMillis < this.f8565m) {
            float f;
            switch (this.f8553a) {
                case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                    f = ((float) currentAnimationTimeMillis) * this.f8566n;
                    if (this.f8570r == null) {
                        f = m13413a(f);
                    } else {
                        f = this.f8570r.getInterpolation(f);
                    }
                    this.f8562j = this.f8554b + Math.round(this.f8567o * f);
                    this.f8563k = Math.round(f * this.f8568p) + this.f8555c;
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    f = ((float) currentAnimationTimeMillis) / ((float) this.f8565m);
                    int i = (int) (100.0f * f);
                    float f2 = ((float) i) / 100.0f;
                    float f3 = ((float) (i + 1)) / 100.0f;
                    float f4 = f8551y[i];
                    f = (((f - f2) / (f3 - f2)) * (f8551y[i + 1] - f4)) + f4;
                    this.f8562j = this.f8554b + Math.round(((float) (this.f8556d - this.f8554b)) * f);
                    this.f8562j = Math.min(this.f8562j, this.f8559g);
                    this.f8562j = Math.max(this.f8562j, this.f8558f);
                    this.f8563k = Math.round(f * ((float) (this.f8557e - this.f8555c))) + this.f8555c;
                    this.f8563k = Math.min(this.f8563k, this.f8561i);
                    this.f8563k = Math.max(this.f8563k, this.f8560h);
                    if (this.f8562j == this.f8556d && this.f8563k == this.f8557e) {
                        this.f8569q = true;
                        break;
                    }
            }
        }
        this.f8562j = this.f8556d;
        this.f8563k = this.f8557e;
        this.f8569q = true;
        return true;
    }

    public void m13415a(int i, int i2, int i3, int i4, int i5) {
        this.f8553a = 0;
        this.f8569q = false;
        this.f8565m = i5;
        this.f8564l = AnimationUtils.currentAnimationTimeMillis();
        this.f8554b = i;
        this.f8555c = i2;
        this.f8556d = i + i3;
        this.f8557e = i2 + i4;
        this.f8567o = (float) i3;
        this.f8568p = (float) i4;
        this.f8566n = 1.0f / ((float) this.f8565m);
    }

    public void m13416a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        float c;
        float f;
        float f2;
        if (this.f8571s && !this.f8569q) {
            c = m13420c();
            f = (float) (this.f8556d - this.f8554b);
            f2 = (float) (this.f8557e - this.f8555c);
            float floatValue = Double.valueOf(Math.sqrt((double) ((f * f) + (f2 * f2)))).floatValue();
            f = (f / floatValue) * c;
            c *= f2 / floatValue;
            if (Math.signum((float) i3) == Math.signum(f) && Math.signum((float) i4) == Math.signum(c)) {
                i3 = (int) (f + ((float) i3));
                i4 = (int) (c + ((float) i4));
            }
        }
        this.f8553a = 1;
        this.f8569q = false;
        f2 = Double.valueOf(Math.sqrt((double) ((i3 * i3) + (i4 * i4)))).floatValue();
        this.f8572t = f2;
        double log = Math.log((double) ((f8549w * f2) / f8548v));
        this.f8565m = (int) (1000.0d * Math.exp(log / (((double) f8547u) - 1.0d)));
        this.f8564l = AnimationUtils.currentAnimationTimeMillis();
        this.f8554b = i;
        this.f8555c = i2;
        f = f2 == 0.0f ? 1.0f : ((float) i3) / f2;
        c = f2 == 0.0f ? 1.0f : ((float) i4) / f2;
        int exp = (int) (((double) f8548v) * Math.exp(log * (((double) f8547u) / (((double) f8547u) - 1.0d))));
        this.f8558f = i5;
        this.f8559g = i6;
        this.f8560h = i7;
        this.f8561i = i8;
        this.f8556d = Math.round(f * ((float) exp)) + i;
        this.f8556d = Math.min(this.f8556d, this.f8559g);
        this.f8556d = Math.max(this.f8556d, this.f8558f);
        this.f8557e = Math.round(c * ((float) exp)) + i2;
        this.f8557e = Math.min(this.f8557e, this.f8561i);
        this.f8557e = Math.max(this.f8557e, this.f8560h);
    }

    static float m13413a(float f) {
        float f2 = f8545B * f;
        if (f2 < 1.0f) {
            f2 -= 1.0f - ((float) Math.exp((double) (-f2)));
        } else {
            f2 = ((1.0f - ((float) Math.exp((double) (1.0f - f2)))) * (1.0f - 0.36787945f)) + 0.36787945f;
        }
        return f2 * f8546C;
    }

    public int m13424g() {
        return (int) (AnimationUtils.currentAnimationTimeMillis() - this.f8564l);
    }
}
