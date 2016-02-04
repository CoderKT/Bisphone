package com.nispok.snackbar.listeners;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewPropertyAnimator;
import se.emilsjolander.stickylistheaders.C1128R;

public class SwipeDismissTouchListener implements OnTouchListener {
    private int f6776a;
    private int f6777b;
    private int f6778c;
    private long f6779d;
    private View f6780e;
    private DismissCallbacks f6781f;
    private int f6782g;
    private float f6783h;
    private float f6784i;
    private boolean f6785j;
    private int f6786k;
    private Object f6787l;
    private VelocityTracker f6788m;
    private float f6789n;

    public interface DismissCallbacks {
        void m10812a(View view, Object obj);

        void m10813a(boolean z);

        boolean m10814a(Object obj);
    }

    /* renamed from: com.nispok.snackbar.listeners.SwipeDismissTouchListener.1 */
    class C09111 extends AnimatorListenerAdapter {
        final /* synthetic */ View f6774a;
        final /* synthetic */ SwipeDismissTouchListener f6775b;

        C09111(SwipeDismissTouchListener swipeDismissTouchListener, View view) {
            this.f6775b = swipeDismissTouchListener;
            this.f6774a = view;
        }

        public void onAnimationEnd(Animator animator) {
            this.f6775b.m10898a(this.f6774a);
        }
    }

    public SwipeDismissTouchListener(View view, Object obj, DismissCallbacks dismissCallbacks) {
        this.f6782g = 1;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(view.getContext());
        this.f6776a = viewConfiguration.getScaledTouchSlop();
        this.f6777b = viewConfiguration.getScaledMinimumFlingVelocity() * 16;
        this.f6778c = viewConfiguration.getScaledMaximumFlingVelocity();
        this.f6779d = (long) view.getContext().getResources().getInteger(17694720);
        this.f6780e = view;
        this.f6787l = obj;
        this.f6781f = dismissCallbacks;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z = true;
        motionEvent.offsetLocation(this.f6789n, 0.0f);
        if (this.f6782g < 2) {
            this.f6782g = view.getWidth();
        }
        float rawX;
        float xVelocity;
        switch (motionEvent.getActionMasked()) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                this.f6783h = motionEvent.getRawX();
                this.f6784i = motionEvent.getRawY();
                if (!this.f6781f.m10814a(this.f6787l)) {
                    return false;
                }
                this.f6781f.m10813a(true);
                this.f6788m = VelocityTracker.obtain();
                this.f6788m.addMovement(motionEvent);
                return false;
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                if (this.f6788m == null) {
                    return false;
                }
                boolean z2;
                this.f6781f.m10813a(false);
                rawX = motionEvent.getRawX() - this.f6783h;
                this.f6788m.addMovement(motionEvent);
                this.f6788m.computeCurrentVelocity(1000);
                xVelocity = this.f6788m.getXVelocity();
                float abs = Math.abs(xVelocity);
                float abs2 = Math.abs(this.f6788m.getYVelocity());
                if (Math.abs(rawX) > ((float) (this.f6782g / 2)) && this.f6785j) {
                    z2 = rawX > 0.0f;
                } else if (((float) this.f6777b) > abs || abs > ((float) this.f6778c) || abs2 >= abs || abs2 >= abs || !this.f6785j) {
                    z2 = false;
                    z = false;
                } else {
                    boolean z3;
                    if (xVelocity < 0.0f) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    z2 = z3 == ((rawX > 0.0f ? 1 : (rawX == 0.0f ? 0 : -1)) < 0);
                    if (this.f6788m.getXVelocity() <= 0.0f) {
                        z = false;
                    }
                    boolean z4 = z;
                    z = z2;
                    z2 = z4;
                }
                if (z) {
                    ViewPropertyAnimator animate = view.animate();
                    if (z2) {
                        rawX = (float) this.f6782g;
                    } else {
                        rawX = (float) (-this.f6782g);
                    }
                    animate.translationX(rawX).alpha(0.0f).setDuration(this.f6779d).setListener(null);
                    this.f6780e.animate().alpha(0.0f).setDuration(this.f6779d).setListener(new C09111(this, view));
                } else if (this.f6785j) {
                    view.animate().translationX(0.0f).alpha(1.0f).setDuration(this.f6779d).setListener(null);
                    this.f6780e.animate().alpha(1.0f).setDuration(this.f6779d);
                }
                if (this.f6788m != null) {
                    this.f6788m.recycle();
                    this.f6788m = null;
                }
                this.f6789n = 0.0f;
                this.f6783h = 0.0f;
                this.f6784i = 0.0f;
                this.f6785j = false;
                return false;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                if (this.f6788m == null) {
                    return false;
                }
                this.f6788m.addMovement(motionEvent);
                xVelocity = motionEvent.getRawX() - this.f6783h;
                rawX = motionEvent.getRawY() - this.f6784i;
                if (Math.abs(xVelocity) > ((float) this.f6776a) && Math.abs(rawX) < Math.abs(xVelocity) / 2.0f) {
                    int i;
                    this.f6785j = true;
                    if (xVelocity > 0.0f) {
                        i = this.f6776a;
                    } else {
                        i = -this.f6776a;
                    }
                    this.f6786k = i;
                    if (view.getParent() != null) {
                        view.getParent().requestDisallowInterceptTouchEvent(true);
                    }
                    MotionEvent obtain = MotionEvent.obtain(motionEvent);
                    obtain.setAction((motionEvent.getActionIndex() << 8) | 3);
                    view.onTouchEvent(obtain);
                    obtain.recycle();
                }
                if (!this.f6785j) {
                    return false;
                }
                this.f6789n = xVelocity;
                view.setTranslationX(xVelocity - ((float) this.f6786k));
                view.setAlpha(Math.max(0.0f, Math.min(1.0f, 1.0f - ((2.0f * Math.abs(xVelocity)) / ((float) this.f6782g)))));
                this.f6780e.setAlpha(Math.max(0.2f, Math.min(1.0f, 1.0f - (Math.abs(xVelocity) / ((float) this.f6782g)))));
                return true;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                if (this.f6788m == null) {
                    return false;
                }
                view.animate().translationX(0.0f).alpha(1.0f).setDuration(this.f6779d).setListener(null);
                this.f6780e.animate().alpha(1.0f).setDuration(this.f6779d);
                this.f6788m.recycle();
                this.f6788m = null;
                this.f6789n = 0.0f;
                this.f6783h = 0.0f;
                this.f6784i = 0.0f;
                this.f6785j = false;
                return false;
            default:
                return false;
        }
    }

    private void m10898a(View view) {
        this.f6781f.m10812a(view, this.f6787l);
    }
}
