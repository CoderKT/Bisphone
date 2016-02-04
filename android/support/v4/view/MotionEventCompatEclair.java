package android.support.v4.view;

import android.view.MotionEvent;

class MotionEventCompatEclair {
    public static int m995a(MotionEvent motionEvent, int i) {
        return motionEvent.findPointerIndex(i);
    }

    public static int m996b(MotionEvent motionEvent, int i) {
        return motionEvent.getPointerId(i);
    }

    public static float m997c(MotionEvent motionEvent, int i) {
        return motionEvent.getX(i);
    }

    public static float m998d(MotionEvent motionEvent, int i) {
        return motionEvent.getY(i);
    }
}
