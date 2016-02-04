package parallaxedScrollView;

import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public abstract class ParallaxedView {
    public static boolean f8636b;
    protected WeakReference<View> f8637c;
    protected int f8638d;
    protected List<Animation> f8639e;

    protected abstract void m13455a(View view, float f);

    static {
        f8636b = VERSION.SDK_INT >= 11;
    }

    public ParallaxedView(View view) {
        this.f8638d = 0;
        this.f8639e = new ArrayList();
        this.f8637c = new WeakReference(view);
    }

    @SuppressLint({"NewApi"})
    public void m13454a(float f) {
        View view = (View) this.f8637c.get();
        if (view == null) {
            return;
        }
        if (f8636b) {
            view.setTranslationY(f);
        } else {
            m13455a(view, f);
        }
    }

    public void m13457b(float f) {
        View view = (View) this.f8637c.get();
        if (view == null) {
            return;
        }
        if (f8636b) {
            view.setAlpha(f);
        } else {
            m13458b(view, f);
        }
    }

    protected synchronized void m13456a(Animation animation) {
        this.f8639e.add(animation);
    }

    protected void m13458b(View view, float f) {
        m13456a(new AlphaAnimation(f, f));
    }

    protected synchronized void m13453a() {
        View view = (View) this.f8637c.get();
        if (view != null) {
            Animation animationSet = new AnimationSet(true);
            for (Animation animation : this.f8639e) {
                if (animation != null) {
                    animationSet.addAnimation(animation);
                }
            }
            animationSet.setDuration(0);
            animationSet.setFillAfter(true);
            view.setAnimation(animationSet);
            animationSet.start();
            this.f8639e.clear();
        }
    }
}
