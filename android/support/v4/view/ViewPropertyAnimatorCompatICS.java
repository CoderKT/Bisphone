package android.support.v4.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.animation.Interpolator;

class ViewPropertyAnimatorCompatICS {

    /* renamed from: android.support.v4.view.ViewPropertyAnimatorCompatICS.1 */
    final class C00261 extends AnimatorListenerAdapter {
        final /* synthetic */ ViewPropertyAnimatorListener f534a;
        final /* synthetic */ View f535b;

        C00261(ViewPropertyAnimatorListener viewPropertyAnimatorListener, View view) {
            this.f534a = viewPropertyAnimatorListener;
            this.f535b = view;
        }

        public void onAnimationCancel(Animator animator) {
            this.f534a.m1377c(this.f535b);
        }

        public void onAnimationEnd(Animator animator) {
            this.f534a.m1376b(this.f535b);
        }

        public void onAnimationStart(Animator animator) {
            this.f534a.m1375a(this.f535b);
        }
    }

    public static void m1410a(View view, long j) {
        view.animate().setDuration(j);
    }

    public static void m1409a(View view, float f) {
        view.animate().alpha(f);
    }

    public static void m1414b(View view, float f) {
        view.animate().translationX(f);
    }

    public static void m1417c(View view, float f) {
        view.animate().translationY(f);
    }

    public static long m1408a(View view) {
        return view.animate().getDuration();
    }

    public static void m1412a(View view, Interpolator interpolator) {
        view.animate().setInterpolator(interpolator);
    }

    public static void m1415b(View view, long j) {
        view.animate().setStartDelay(j);
    }

    public static void m1413b(View view) {
        view.animate().cancel();
    }

    public static void m1416c(View view) {
        view.animate().start();
    }

    public static void m1411a(View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        if (viewPropertyAnimatorListener != null) {
            view.animate().setListener(new C00261(viewPropertyAnimatorListener, view));
        } else {
            view.animate().setListener(null);
        }
    }
}
