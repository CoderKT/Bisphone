package com.nispok.snackbar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build.VERSION;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nispok.snackbar.enums.SnackbarType;
import com.nispok.snackbar.layouts.SnackbarLayout;
import com.nispok.snackbar.listeners.ActionClickListener;
import com.nispok.snackbar.listeners.ActionSwipeListener;
import com.nispok.snackbar.listeners.EventListener;
import com.nispok.snackbar.listeners.SwipeDismissTouchListener;
import com.nispok.snackbar.listeners.SwipeDismissTouchListener.DismissCallbacks;

public class Snackbar extends SnackbarLayout {
    private boolean f6715A;
    private long f6716B;
    private ActionClickListener f6717C;
    private ActionSwipeListener f6718D;
    private boolean f6719E;
    private boolean f6720F;
    private boolean f6721G;
    private EventListener f6722H;
    private Typeface f6723I;
    private Typeface f6724J;
    private boolean f6725K;
    private boolean f6726L;
    private boolean f6727M;
    private Rect f6728N;
    private Rect f6729O;
    private Point f6730P;
    private Point f6731Q;
    private Activity f6732R;
    private Float f6733S;
    private boolean f6734T;
    private Runnable f6735U;
    private Runnable f6736V;
    private int f6737a;
    private int f6738b;
    private SnackbarType f6739c;
    private SnackbarDuration f6740d;
    private CharSequence f6741e;
    private TextView f6742f;
    private TextView f6743g;
    private int f6744h;
    private int f6745i;
    private int f6746j;
    private Integer f6747k;
    private SnackbarPosition f6748l;
    private SnackbarPosition f6749m;
    private int f6750n;
    private int f6751o;
    private int f6752p;
    private int f6753q;
    private int f6754r;
    private long f6755s;
    private long f6756t;
    private long f6757u;
    private CharSequence f6758v;
    private int f6759w;
    private boolean f6760x;
    private boolean f6761y;
    private boolean f6762z;

    /* renamed from: com.nispok.snackbar.Snackbar.1 */
    class C09011 implements Runnable {
        final /* synthetic */ Snackbar f6694a;

        C09011(Snackbar snackbar) {
            this.f6694a = snackbar;
        }

        public void run() {
            this.f6694a.m10875b();
        }
    }

    /* renamed from: com.nispok.snackbar.Snackbar.2 */
    class C09022 implements Runnable {
        final /* synthetic */ Snackbar f6695a;

        C09022(Snackbar snackbar) {
            this.f6695a = snackbar;
        }

        public void run() {
            this.f6695a.m10879c();
        }
    }

    /* renamed from: com.nispok.snackbar.Snackbar.4 */
    class C09034 implements OnClickListener {
        final /* synthetic */ Snackbar f6696a;

        C09034(Snackbar snackbar) {
            this.f6696a = snackbar;
        }

        public void onClick(View view) {
            if (!(this.f6696a.f6717C == null || this.f6696a.f6727M || (this.f6696a.f6720F && !this.f6696a.f6719E))) {
                this.f6696a.f6717C.m4053a(this.f6696a);
                this.f6696a.f6720F = true;
            }
            if (this.f6696a.f6721G) {
                this.f6696a.m10875b();
            }
        }
    }

    /* renamed from: com.nispok.snackbar.Snackbar.5 */
    class C09045 implements DismissCallbacks {
        final /* synthetic */ Snackbar f6697a;

        C09045(Snackbar snackbar) {
            this.f6697a = snackbar;
        }

        public boolean m10817a(Object obj) {
            return true;
        }

        public void m10815a(View view, Object obj) {
            if (view != null) {
                if (this.f6697a.f6718D != null) {
                    this.f6697a.f6718D.m10891a();
                }
                this.f6697a.m10846e(false);
            }
        }

        public void m10816a(boolean z) {
            if (!this.f6697a.m10850g()) {
                if (z) {
                    this.f6697a.removeCallbacks(this.f6697a.f6735U);
                    this.f6697a.f6756t = System.currentTimeMillis();
                    return;
                }
                this.f6697a.f6757u = this.f6697a.f6757u - (this.f6697a.f6756t - this.f6697a.f6755s);
                this.f6697a.m10827a(this.f6697a.f6757u);
            }
        }
    }

    /* renamed from: com.nispok.snackbar.Snackbar.6 */
    class C09056 implements OnPreDrawListener {
        final /* synthetic */ Snackbar f6698a;

        C09056(Snackbar snackbar) {
            this.f6698a = snackbar;
        }

        public boolean onPreDraw() {
            this.f6698a.getViewTreeObserver().removeOnPreDrawListener(this);
            if (this.f6698a.f6722H != null) {
                if (this.f6698a.f6715A) {
                    this.f6698a.f6722H.m10893b(this.f6698a);
                } else {
                    this.f6698a.f6722H.m10892a(this.f6698a);
                }
                if (!this.f6698a.f6760x) {
                    this.f6698a.f6722H.m10894c(this.f6698a);
                    this.f6698a.f6715A = false;
                }
            }
            return true;
        }
    }

    /* renamed from: com.nispok.snackbar.Snackbar.7 */
    class C09077 implements AnimationListener {
        final /* synthetic */ Snackbar f6700a;

        /* renamed from: com.nispok.snackbar.Snackbar.7.1 */
        class C09061 implements Runnable {
            final /* synthetic */ C09077 f6699a;

            C09061(C09077 c09077) {
                this.f6699a = c09077;
            }

            public void run() {
                this.f6699a.f6700a.f6755s = System.currentTimeMillis();
                if (this.f6699a.f6700a.f6757u == -1) {
                    this.f6699a.f6700a.f6757u = this.f6699a.f6700a.getDuration();
                }
                if (this.f6699a.f6700a.m10849f()) {
                    this.f6699a.f6700a.m10853h();
                }
            }
        }

        C09077(Snackbar snackbar) {
            this.f6700a = snackbar;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            if (this.f6700a.f6722H != null) {
                this.f6700a.f6722H.m10894c(this.f6700a);
                this.f6700a.f6715A = false;
            }
            this.f6700a.m10830a(this.f6700a.f6742f);
            this.f6700a.post(new C09061(this));
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    /* renamed from: com.nispok.snackbar.Snackbar.8 */
    class C09098 implements AnimationListener {
        final /* synthetic */ Snackbar f6702a;

        /* renamed from: com.nispok.snackbar.Snackbar.8.1 */
        class C09081 implements Runnable {
            final /* synthetic */ C09098 f6701a;

            C09081(C09098 c09098) {
                this.f6701a = c09098;
            }

            public void run() {
                this.f6701a.f6702a.m10855i();
            }
        }

        C09098(Snackbar snackbar) {
            this.f6702a = snackbar;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            this.f6702a.post(new C09081(this));
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    public enum SnackbarDuration {
        LENGTH_SHORT(2000),
        LENGTH_LONG(3500),
        LENGTH_INDEFINITE(-1);
        
        private long f6707d;

        private SnackbarDuration(long j) {
            this.f6707d = j;
        }

        public long m10818a() {
            return this.f6707d;
        }
    }

    public enum SnackbarPosition {
        TOP(48),
        BOTTOM(80),
        BOTTOM_CENTER(81);
        
        private int f6712d;

        private SnackbarPosition(int i) {
            this.f6712d = i;
        }

        public int m10819a() {
            return this.f6712d;
        }
    }

    private Snackbar(Context context) {
        super(context);
        this.f6737a = -10000;
        this.f6738b = -10000;
        this.f6739c = SnackbarType.SINGLE_LINE;
        this.f6740d = SnackbarDuration.LENGTH_LONG;
        this.f6744h = this.f6737a;
        this.f6745i = this.f6737a;
        this.f6748l = SnackbarPosition.BOTTOM;
        this.f6749m = SnackbarPosition.BOTTOM_CENTER;
        this.f6750n = this.f6738b;
        this.f6751o = 0;
        this.f6752p = 0;
        this.f6753q = 0;
        this.f6754r = 0;
        this.f6757u = -1;
        this.f6759w = this.f6737a;
        this.f6760x = true;
        this.f6761y = true;
        this.f6762z = false;
        this.f6715A = false;
        this.f6716B = -1;
        this.f6721G = true;
        this.f6725K = false;
        this.f6726L = true;
        this.f6727M = false;
        this.f6728N = new Rect();
        this.f6729O = new Rect();
        this.f6730P = new Point();
        this.f6731Q = new Point();
        this.f6733S = null;
        this.f6735U = new C09011(this);
        this.f6736V = new C09022(this);
        if (VERSION.SDK_INT >= 16) {
            addView(new SnackbarHelperChildViewJB(getContext()));
        }
    }

    public static Snackbar m10825a(Context context) {
        return new Snackbar(context);
    }

    public Snackbar m10868a(CharSequence charSequence) {
        this.f6741e = charSequence;
        if (this.f6742f != null) {
            this.f6742f.setText(this.f6741e);
        }
        return this;
    }

    public Snackbar m10865a(int i) {
        this.f6744h = i;
        return this;
    }

    public Snackbar m10873b(CharSequence charSequence) {
        this.f6758v = charSequence;
        if (this.f6743g != null) {
            this.f6743g.setText(this.f6758v);
        }
        return this;
    }

    public Snackbar m10867a(ActionClickListener actionClickListener) {
        this.f6717C = actionClickListener;
        return this;
    }

    public Snackbar m10869a(boolean z) {
        this.f6760x = z;
        this.f6761y = z;
        return this;
    }

    public Snackbar m10874b(boolean z) {
        this.f6760x = z;
        return this;
    }

    public Snackbar m10878c(boolean z) {
        this.f6761y = z;
        return this;
    }

    public Snackbar m10881d(boolean z) {
        this.f6726L = z;
        return this;
    }

    public Snackbar m10866a(SnackbarDuration snackbarDuration) {
        this.f6740d = snackbarDuration;
        return this;
    }

    private static MarginLayoutParams m10824a(ViewGroup viewGroup, int i, int i2, SnackbarPosition snackbarPosition) {
        MarginLayoutParams layoutParams;
        if (viewGroup instanceof FrameLayout) {
            layoutParams = new LayoutParams(i, i2);
            layoutParams.gravity = snackbarPosition.m10819a();
            return layoutParams;
        } else if (viewGroup instanceof RelativeLayout) {
            layoutParams = new RelativeLayout.LayoutParams(i, i2);
            if (snackbarPosition == SnackbarPosition.TOP) {
                layoutParams.addRule(10, -1);
                return layoutParams;
            }
            layoutParams.addRule(12, -1);
            return layoutParams;
        } else if (viewGroup instanceof LinearLayout) {
            layoutParams = new LinearLayout.LayoutParams(i, i2);
            layoutParams.gravity = snackbarPosition.m10819a();
            return layoutParams;
        } else {
            throw new IllegalStateException("Requires FrameLayout or RelativeLayout for the parent of Snackbar");
        }
    }

    static boolean m10838b(Context context) {
        if (context == null) {
            return true;
        }
        return context.getResources().getBoolean(C0900R.bool.sb__is_phone);
    }

    private MarginLayoutParams m10823a(Context context, Activity activity, ViewGroup viewGroup, boolean z) {
        int i;
        MarginLayoutParams marginLayoutParams;
        View view = (SnackbarLayout) LayoutInflater.from(context).inflate(C0900R.layout.sb__template, this, true);
        view.setOrientation(1);
        Resources resources = getResources();
        if (this.f6744h != this.f6737a) {
            i = this.f6744h;
        } else {
            i = resources.getColor(C0900R.color.sb__background);
        }
        this.f6744h = i;
        this.f6746j = resources.getDimensionPixelOffset(C0900R.dimen.sb__offset);
        this.f6734T = z;
        float f = resources.getDisplayMetrics().density;
        View findViewById = view.findViewById(C0900R.id.sb__divider);
        if (this.f6734T) {
            view.setMinimumHeight(m10820a(this.f6739c.m10888a(), f));
            view.setMaxHeight(m10820a(this.f6739c.m10889b(), f));
            view.setBackgroundColor(this.f6744h);
            MarginLayoutParams a = m10824a(viewGroup, -1, -2, this.f6748l);
            if (this.f6747k != null) {
                findViewById.setBackgroundColor(this.f6747k.intValue());
                marginLayoutParams = a;
            } else {
                findViewById.setVisibility(8);
                marginLayoutParams = a;
            }
        } else {
            this.f6739c = SnackbarType.SINGLE_LINE;
            view.setMinimumWidth(resources.getDimensionPixelSize(C0900R.dimen.sb__min_width));
            if (this.f6733S == null) {
                i = resources.getDimensionPixelSize(C0900R.dimen.sb__max_width);
            } else {
                i = DisplayCompat.m10803a(activity, this.f6733S);
            }
            view.setMaxWidth(i);
            view.setBackgroundResource(C0900R.drawable.sb__bg);
            ((GradientDrawable) view.getBackground()).setColor(this.f6744h);
            marginLayoutParams = m10824a(viewGroup, -2, m10820a(this.f6739c.m10889b(), f), this.f6749m);
            if (this.f6747k != null) {
                findViewById.setBackgroundResource(C0900R.drawable.sb__divider_bg);
                ((GradientDrawable) findViewById.getBackground()).setColor(this.f6747k.intValue());
            } else {
                findViewById.setVisibility(8);
            }
        }
        if (this.f6750n != this.f6738b) {
            m10831a(view, resources.getDrawable(this.f6750n));
        }
        this.f6742f = (TextView) view.findViewById(C0900R.id.sb__text);
        this.f6742f.setText(this.f6741e);
        this.f6742f.setTypeface(this.f6723I);
        if (this.f6745i != this.f6737a) {
            this.f6742f.setTextColor(this.f6745i);
        }
        this.f6742f.setMaxLines(this.f6739c.m10890c());
        this.f6743g = (TextView) view.findViewById(C0900R.id.sb__action);
        if (TextUtils.isEmpty(this.f6758v)) {
            this.f6743g.setVisibility(8);
        } else {
            requestLayout();
            this.f6743g.setText(this.f6758v);
            this.f6743g.setTypeface(this.f6724J);
            if (this.f6759w != this.f6737a) {
                this.f6743g.setTextColor(this.f6759w);
            }
            this.f6743g.setOnClickListener(new C09034(this));
            this.f6743g.setMaxLines(this.f6739c.m10890c());
        }
        view = view.findViewById(C0900R.id.sb__inner);
        view.setClickable(true);
        if (this.f6726L && resources.getBoolean(C0900R.bool.sb__is_swipeable)) {
            view.setOnTouchListener(new SwipeDismissTouchListener(this, null, new C09045(this)));
        }
        return marginLayoutParams;
    }

    private void m10828a(Activity activity, Rect rect) {
        rect.bottom = 0;
        rect.right = 0;
        rect.top = 0;
        rect.left = 0;
        if (activity != null) {
            ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
            Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
            boolean c = m10841c(activity);
            boolean a = m10833a(viewGroup);
            Rect rect2 = this.f6729O;
            Point point = this.f6731Q;
            Point point2 = this.f6730P;
            viewGroup.getWindowVisibleDisplayFrame(rect2);
            DisplayCompat.m10805b(defaultDisplay, point);
            DisplayCompat.m10804a(defaultDisplay, point2);
            if (point2.x < point.x) {
                if (c || a) {
                    rect.right = Math.max(Math.min(point.x - point2.x, point.x - rect2.right), 0);
                }
            } else if (point2.y >= point.y) {
            } else {
                if (c || a) {
                    rect.bottom = Math.max(Math.min(point.y - point2.y, point.y - rect2.bottom), 0);
                }
            }
        }
    }

    private static int m10820a(int i, float f) {
        return (int) ((((float) i) * f) + 0.5f);
    }

    public void m10871a(Activity activity) {
        this.f6715A = true;
        m10877b(activity);
    }

    public void m10877b(Activity activity) {
        ViewGroup viewGroup = (ViewGroup) activity.findViewById(16908290);
        MarginLayoutParams a = m10823a((Context) activity, activity, viewGroup, m10838b((Context) activity));
        m10872a(activity, a);
        m10829a(activity, a, viewGroup);
    }

    private void m10829a(Activity activity, MarginLayoutParams marginLayoutParams, ViewGroup viewGroup) {
        viewGroup.removeView(this);
        if (VERSION.SDK_INT >= 21) {
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                float elevation = viewGroup.getChildAt(i).getElevation();
                if (elevation > getElevation()) {
                    setElevation(elevation);
                }
            }
        }
        viewGroup.addView(this, marginLayoutParams);
        bringToFront();
        if (VERSION.SDK_INT < 19) {
            viewGroup.requestLayout();
            viewGroup.invalidate();
        }
        this.f6725K = true;
        this.f6732R = activity;
        getViewTreeObserver().addOnPreDrawListener(new C09056(this));
        if (this.f6760x) {
            Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), m10821a(this.f6748l));
            loadAnimation.setAnimationListener(new C09077(this));
            startAnimation(loadAnimation);
        } else if (m10849f()) {
            m10853h();
        }
    }

    private void m10830a(View view) {
        AccessibilityEvent obtain = AccessibilityEvent.obtain(8);
        AccessibilityEventCompat.m1441a(obtain).m1636a(view);
        try {
            view.sendAccessibilityEventUnchecked(obtain);
        } catch (IllegalStateException e) {
        }
    }

    private boolean m10849f() {
        return !m10850g();
    }

    private boolean m10850g() {
        return getDuration() == SnackbarDuration.LENGTH_INDEFINITE.m10818a();
    }

    @TargetApi(16)
    private boolean m10833a(ViewGroup viewGroup) {
        if (VERSION.SDK_INT >= 16 && (viewGroup.getWindowSystemUiVisibility() & 512) == 512) {
            return true;
        }
        return false;
    }

    private boolean m10841c(Activity activity) {
        if (VERSION.SDK_INT >= 19 && (activity.getWindow().getAttributes().flags & 134217728) != 0) {
            return true;
        }
        return false;
    }

    private void m10853h() {
        postDelayed(this.f6735U, getDuration());
    }

    private void m10827a(long j) {
        postDelayed(this.f6735U, j);
    }

    public void m10870a() {
        this.f6762z = true;
        m10875b();
    }

    public void m10875b() {
        m10846e(this.f6761y);
    }

    private void m10846e(boolean z) {
        if (!this.f6727M) {
            this.f6727M = true;
            if (this.f6722H != null && this.f6725K) {
                if (this.f6762z) {
                    this.f6722H.m10896e(this);
                } else {
                    this.f6722H.m10895d(this);
                }
            }
            if (z) {
                Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), m10835b(this.f6748l));
                loadAnimation.setAnimationListener(new C09098(this));
                startAnimation(loadAnimation);
                return;
            }
            m10855i();
        }
    }

    private void m10855i() {
        clearAnimation();
        ViewGroup viewGroup = (ViewGroup) getParent();
        if (viewGroup != null) {
            viewGroup.removeView(this);
        }
        if (this.f6722H != null && this.f6725K) {
            this.f6722H.m10897f(this);
        }
        this.f6725K = false;
        this.f6727M = false;
        this.f6762z = false;
        this.f6732R = null;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f6725K = false;
        if (this.f6735U != null) {
            removeCallbacks(this.f6735U);
        }
        if (this.f6736V != null) {
            removeCallbacks(this.f6736V);
        }
    }

    void m10876b(int i) {
        m10880c(i);
    }

    protected void m10880c(int i) {
        if (this.f6736V != null) {
            post(this.f6736V);
        }
    }

    protected void m10879c() {
        if (!this.f6727M && ((ViewGroup) getParent()) != null) {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) getLayoutParams();
            m10872a(this.f6732R, marginLayoutParams);
            setLayoutParams(marginLayoutParams);
        }
    }

    protected void m10872a(Activity activity, MarginLayoutParams marginLayoutParams) {
        if (this.f6734T) {
            marginLayoutParams.topMargin = this.f6751o;
            marginLayoutParams.rightMargin = this.f6754r;
            marginLayoutParams.leftMargin = this.f6753q;
            marginLayoutParams.bottomMargin = this.f6752p;
        } else {
            marginLayoutParams.topMargin = this.f6751o;
            marginLayoutParams.rightMargin = this.f6754r;
            marginLayoutParams.leftMargin = this.f6753q + this.f6746j;
            marginLayoutParams.bottomMargin = this.f6752p + this.f6746j;
        }
        m10828a(activity, this.f6728N);
        marginLayoutParams.rightMargin += this.f6728N.right;
        marginLayoutParams.bottomMargin += this.f6728N.bottom;
    }

    public int getActionColor() {
        return this.f6759w;
    }

    public CharSequence getActionLabel() {
        return this.f6758v;
    }

    public int getTextColor() {
        return this.f6745i;
    }

    public int getColor() {
        return this.f6744h;
    }

    public int getLineColor() {
        return this.f6747k.intValue();
    }

    public CharSequence getText() {
        return this.f6741e;
    }

    public long getDuration() {
        return this.f6716B == -1 ? this.f6740d.m10818a() : this.f6716B;
    }

    public SnackbarType getType() {
        return this.f6739c;
    }

    public int getOffset() {
        return this.f6746j;
    }

    public boolean m10882d() {
        return this.f6725K;
    }

    public boolean m10883e() {
        return this.f6727M;
    }

    public static int m10821a(SnackbarPosition snackbarPosition) {
        return snackbarPosition == SnackbarPosition.TOP ? C0900R.anim.sb__top_in : C0900R.anim.sb__bottom_in;
    }

    public static int m10835b(SnackbarPosition snackbarPosition) {
        return snackbarPosition == SnackbarPosition.TOP ? C0900R.anim.sb__top_out : C0900R.anim.sb__bottom_out;
    }

    public static void m10831a(View view, Drawable drawable) {
        if (VERSION.SDK_INT < 16) {
            view.setBackgroundDrawable(drawable);
        } else {
            view.setBackground(drawable);
        }
    }
}
