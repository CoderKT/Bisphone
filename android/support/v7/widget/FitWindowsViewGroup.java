package android.support.v7.widget;

import android.graphics.Rect;

public interface FitWindowsViewGroup {

    public interface OnFitSystemWindowsListener {
        void m2104a(Rect rect);
    }

    void setOnFitSystemWindowsListener(OnFitSystemWindowsListener onFitSystemWindowsListener);
}
