package android.support.v4.animation;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;

class HoneycombMr1AnimatorCompatProvider implements AnimatorProvider {
    private TimeInterpolator f13a;

    HoneycombMr1AnimatorCompatProvider() {
    }

    public void m79a(View view) {
        if (this.f13a == null) {
            this.f13a = new ValueAnimator().getInterpolator();
        }
        view.animate().setInterpolator(this.f13a);
    }
}
