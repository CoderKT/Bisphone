package android.support.v4.view;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.view.View;

class ViewPropertyAnimatorCompatKK {

    /* renamed from: android.support.v4.view.ViewPropertyAnimatorCompatKK.1 */
    final class C00281 implements AnimatorUpdateListener {
        final /* synthetic */ ViewPropertyAnimatorUpdateListener f538a;
        final /* synthetic */ View f539b;

        C00281(ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener, View view) {
            this.f538a = viewPropertyAnimatorUpdateListener;
            this.f539b = view;
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f538a.m1423a(this.f539b);
        }
    }

    public static void m1419a(View view, ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener) {
        AnimatorUpdateListener animatorUpdateListener = null;
        if (viewPropertyAnimatorUpdateListener != null) {
            animatorUpdateListener = new C00281(viewPropertyAnimatorUpdateListener, view);
        }
        view.animate().setUpdateListener(animatorUpdateListener);
    }
}
