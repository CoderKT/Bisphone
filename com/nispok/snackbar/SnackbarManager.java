package com.nispok.snackbar;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.lang.ref.WeakReference;

public class SnackbarManager {
    private static final String f6765a;
    private static final Handler f6766b;
    private static WeakReference<Snackbar> f6767c;

    /* renamed from: com.nispok.snackbar.SnackbarManager.1 */
    final class C09101 implements Runnable {
        final /* synthetic */ Snackbar f6763a;
        final /* synthetic */ Activity f6764b;

        C09101(Snackbar snackbar, Activity activity) {
            this.f6763a = snackbar;
            this.f6764b = activity;
        }

        public void run() {
            Snackbar a = SnackbarManager.m10884a();
            if (a != null) {
                if (!a.m10882d() || a.m10883e()) {
                    a.m10875b();
                } else {
                    a.m10878c(false);
                    a.m10870a();
                    SnackbarManager.f6767c = new WeakReference(this.f6763a);
                    this.f6763a.m10874b(false);
                    this.f6763a.m10871a(this.f6764b);
                    return;
                }
            }
            SnackbarManager.f6767c = new WeakReference(this.f6763a);
            this.f6763a.m10877b(this.f6764b);
        }
    }

    static {
        f6765a = SnackbarManager.class.getSimpleName();
        f6766b = new Handler(Looper.getMainLooper());
    }

    private SnackbarManager() {
    }

    public static void m10886a(Snackbar snackbar) {
        try {
            m10887a(snackbar, (Activity) snackbar.getContext());
        } catch (Throwable e) {
            Log.e(f6765a, "Couldn't get Activity from the Snackbar's Context. Try calling #show(Snackbar, Activity) instead", e);
        }
    }

    public static void m10887a(Snackbar snackbar, Activity activity) {
        f6766b.post(new C09101(snackbar, activity));
    }

    public static Snackbar m10884a() {
        if (f6767c != null) {
            return (Snackbar) f6767c.get();
        }
        return null;
    }
}
