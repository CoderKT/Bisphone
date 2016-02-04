package android.support.v4.view;

import android.view.View;

class ViewCompatJB {
    public static boolean m1222a(View view) {
        return view.hasTransientState();
    }

    public static void m1223b(View view) {
        view.postInvalidateOnAnimation();
    }

    public static void m1220a(View view, Runnable runnable) {
        view.postOnAnimation(runnable);
    }

    public static void m1221a(View view, Runnable runnable, long j) {
        view.postOnAnimationDelayed(runnable, j);
    }

    public static int m1224c(View view) {
        return view.getImportantForAccessibility();
    }

    public static void m1219a(View view, int i) {
        view.setImportantForAccessibility(i);
    }

    public static int m1225d(View view) {
        return view.getMinimumWidth();
    }

    public static int m1226e(View view) {
        return view.getMinimumHeight();
    }

    public static void m1227f(View view) {
        view.requestFitSystemWindows();
    }

    public static boolean m1228g(View view) {
        return view.hasOverlappingRendering();
    }
}
