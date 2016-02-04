package android.support.v4.view;

import android.animation.ValueAnimator;
import android.graphics.Paint;
import android.view.View;

class ViewCompatHC {
    static long m1203a() {
        return ValueAnimator.getFrameDelay();
    }

    public static float m1201a(View view) {
        return view.getAlpha();
    }

    public static void m1205a(View view, int i, Paint paint) {
        view.setLayerType(i, paint);
    }

    public static int m1207b(View view) {
        return view.getLayerType();
    }

    public static int m1202a(int i, int i2, int i3) {
        return View.resolveSizeAndState(i, i2, i3);
    }

    public static int m1210c(View view) {
        return view.getMeasuredState();
    }

    public static float m1212d(View view) {
        return view.getTranslationX();
    }

    public static float m1213e(View view) {
        return view.getTranslationY();
    }

    public static void m1204a(View view, float f) {
        view.setTranslationX(f);
    }

    public static void m1208b(View view, float f) {
        view.setTranslationY(f);
    }

    public static void m1211c(View view, float f) {
        view.setAlpha(f);
    }

    public static void m1214f(View view) {
        view.jumpDrawablesToCurrentState();
    }

    public static void m1206a(View view, boolean z) {
        view.setSaveFromParentEnabled(z);
    }

    public static void m1209b(View view, boolean z) {
        view.setActivated(z);
    }
}
