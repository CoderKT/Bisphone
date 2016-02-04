package android.support.v4.view;

import android.view.VelocityTracker;

class VelocityTrackerCompatHoneycomb {
    public static float m1022a(VelocityTracker velocityTracker, int i) {
        return velocityTracker.getXVelocity(i);
    }

    public static float m1023b(VelocityTracker velocityTracker, int i) {
        return velocityTracker.getYVelocity(i);
    }
}
