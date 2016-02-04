package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.VelocityTracker;

public class VelocityTrackerCompat {
    static final VelocityTrackerVersionImpl f445a;

    interface VelocityTrackerVersionImpl {
        float m1014a(VelocityTracker velocityTracker, int i);

        float m1015b(VelocityTracker velocityTracker, int i);
    }

    class BaseVelocityTrackerVersionImpl implements VelocityTrackerVersionImpl {
        BaseVelocityTrackerVersionImpl() {
        }

        public float m1016a(VelocityTracker velocityTracker, int i) {
            return velocityTracker.getXVelocity();
        }

        public float m1017b(VelocityTracker velocityTracker, int i) {
            return velocityTracker.getYVelocity();
        }
    }

    class HoneycombVelocityTrackerVersionImpl implements VelocityTrackerVersionImpl {
        HoneycombVelocityTrackerVersionImpl() {
        }

        public float m1018a(VelocityTracker velocityTracker, int i) {
            return VelocityTrackerCompatHoneycomb.m1022a(velocityTracker, i);
        }

        public float m1019b(VelocityTracker velocityTracker, int i) {
            return VelocityTrackerCompatHoneycomb.m1023b(velocityTracker, i);
        }
    }

    static {
        if (VERSION.SDK_INT >= 11) {
            f445a = new HoneycombVelocityTrackerVersionImpl();
        } else {
            f445a = new BaseVelocityTrackerVersionImpl();
        }
    }

    public static float m1020a(VelocityTracker velocityTracker, int i) {
        return f445a.m1014a(velocityTracker, i);
    }

    public static float m1021b(VelocityTracker velocityTracker, int i) {
        return f445a.m1015b(velocityTracker, i);
    }
}
