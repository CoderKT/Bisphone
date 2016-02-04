package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

class ScrollbarHelper {
    static int m3656a(State state, OrientationHelper orientationHelper, View view, View view2, LayoutManager layoutManager, boolean z, boolean z2) {
        if (layoutManager.m3166q() == 0 || state.m3460d() == 0 || view == null || view2 == null) {
            return 0;
        }
        int max = z2 ? Math.max(0, (state.m3460d() - Math.max(layoutManager.m3135d(view), layoutManager.m3135d(view2))) - 1) : Math.max(0, Math.min(layoutManager.m3135d(view), layoutManager.m3135d(view2)));
        if (!z) {
            return max;
        }
        return Math.round((((float) max) * (((float) Math.abs(orientationHelper.m3272b(view2) - orientationHelper.m3268a(view))) / ((float) (Math.abs(layoutManager.m3135d(view) - layoutManager.m3135d(view2)) + 1)))) + ((float) (orientationHelper.m3273c() - orientationHelper.m3268a(view))));
    }

    static int m3655a(State state, OrientationHelper orientationHelper, View view, View view2, LayoutManager layoutManager, boolean z) {
        if (layoutManager.m3166q() == 0 || state.m3460d() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return Math.abs(layoutManager.m3135d(view) - layoutManager.m3135d(view2)) + 1;
        }
        return Math.min(orientationHelper.m3278f(), orientationHelper.m3272b(view2) - orientationHelper.m3268a(view));
    }

    static int m3657b(State state, OrientationHelper orientationHelper, View view, View view2, LayoutManager layoutManager, boolean z) {
        if (layoutManager.m3166q() == 0 || state.m3460d() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return state.m3460d();
        }
        return (int) ((((float) (orientationHelper.m3272b(view2) - orientationHelper.m3268a(view))) / ((float) (Math.abs(layoutManager.m3135d(view) - layoutManager.m3135d(view2)) + 1))) * ((float) state.m3460d()));
    }
}
