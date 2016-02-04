package android.support.v4.animation;

import android.os.Build.VERSION;
import android.view.View;

public abstract class AnimatorCompatHelper {
    static AnimatorProvider f12a;

    static {
        if (VERSION.SDK_INT >= 12) {
            f12a = new HoneycombMr1AnimatorCompatProvider();
        } else {
            f12a = new DonutAnimatorCompatProvider();
        }
    }

    public static void m76a(View view) {
        f12a.m77a(view);
    }
}
