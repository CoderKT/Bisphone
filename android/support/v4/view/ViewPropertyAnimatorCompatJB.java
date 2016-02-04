package android.support.v4.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

class ViewPropertyAnimatorCompatJB {

    /* renamed from: android.support.v4.view.ViewPropertyAnimatorCompatJB.1 */
    final class C00271 extends AnimatorListenerAdapter {
        final /* synthetic */ ViewPropertyAnimatorListener f536a;
        final /* synthetic */ View f537b;

        C00271(ViewPropertyAnimatorListener viewPropertyAnimatorListener, View view) {
            this.f536a = viewPropertyAnimatorListener;
            this.f537b = view;
        }

        public void onAnimationCancel(Animator animator) {
            this.f536a.m1377c(this.f537b);
        }

        public void onAnimationEnd(Animator animator) {
            this.f536a.m1376b(this.f537b);
        }

        public void onAnimationStart(Animator animator) {
            this.f536a.m1375a(this.f537b);
        }
    }

    public static void m1418a(View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        if (viewPropertyAnimatorListener != null) {
            view.animate().setListener(new C00271(viewPropertyAnimatorListener, view));
        } else {
            view.animate().setListener(null);
        }
    }
}
