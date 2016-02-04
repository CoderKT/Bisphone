package butterknife.internal;

import android.view.View;
import android.view.View.OnClickListener;

public abstract class DebouncingOnClickListener implements OnClickListener {
    private static boolean f2790a;
    private static final Runnable f2791b;

    /* renamed from: butterknife.internal.DebouncingOnClickListener.1 */
    final class C05861 implements Runnable {
        C05861() {
        }

        public void run() {
            DebouncingOnClickListener.f2790a = true;
        }
    }

    public abstract void m5246a(View view);

    static {
        f2790a = true;
        f2791b = new C05861();
    }

    public final void onClick(View view) {
        if (f2790a) {
            f2790a = false;
            view.post(f2791b);
            m5246a(view);
        }
    }
}
