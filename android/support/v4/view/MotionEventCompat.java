package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.MotionEvent;

public class MotionEventCompat {
    static final MotionEventVersionImpl f438a;

    interface MotionEventVersionImpl {
        int m969a(MotionEvent motionEvent);

        int m970a(MotionEvent motionEvent, int i);

        int m971b(MotionEvent motionEvent, int i);

        float m972c(MotionEvent motionEvent, int i);

        float m973d(MotionEvent motionEvent, int i);

        float m974e(MotionEvent motionEvent, int i);
    }

    class BaseMotionEventVersionImpl implements MotionEventVersionImpl {
        BaseMotionEventVersionImpl() {
        }

        public int m976a(MotionEvent motionEvent, int i) {
            if (i == 0) {
                return 0;
            }
            return -1;
        }

        public int m977b(MotionEvent motionEvent, int i) {
            if (i == 0) {
                return 0;
            }
            throw new IndexOutOfBoundsException("Pre-Eclair does not support multiple pointers");
        }

        public float m978c(MotionEvent motionEvent, int i) {
            if (i == 0) {
                return motionEvent.getX();
            }
            throw new IndexOutOfBoundsException("Pre-Eclair does not support multiple pointers");
        }

        public float m979d(MotionEvent motionEvent, int i) {
            if (i == 0) {
                return motionEvent.getY();
            }
            throw new IndexOutOfBoundsException("Pre-Eclair does not support multiple pointers");
        }

        public int m975a(MotionEvent motionEvent) {
            return 0;
        }

        public float m980e(MotionEvent motionEvent, int i) {
            return 0.0f;
        }
    }

    class EclairMotionEventVersionImpl extends BaseMotionEventVersionImpl {
        EclairMotionEventVersionImpl() {
        }

        public int m981a(MotionEvent motionEvent, int i) {
            return MotionEventCompatEclair.m995a(motionEvent, i);
        }

        public int m982b(MotionEvent motionEvent, int i) {
            return MotionEventCompatEclair.m996b(motionEvent, i);
        }

        public float m983c(MotionEvent motionEvent, int i) {
            return MotionEventCompatEclair.m997c(motionEvent, i);
        }

        public float m984d(MotionEvent motionEvent, int i) {
            return MotionEventCompatEclair.m998d(motionEvent, i);
        }
    }

    class GingerbreadMotionEventVersionImpl extends EclairMotionEventVersionImpl {
        GingerbreadMotionEventVersionImpl() {
        }

        public int m985a(MotionEvent motionEvent) {
            return MotionEventCompatGingerbread.m999a(motionEvent);
        }
    }

    class HoneycombMr1MotionEventVersionImpl extends GingerbreadMotionEventVersionImpl {
        HoneycombMr1MotionEventVersionImpl() {
        }

        public float m986e(MotionEvent motionEvent, int i) {
            return MotionEventCompatHoneycombMr1.m1000a(motionEvent, i);
        }
    }

    static {
        if (VERSION.SDK_INT >= 12) {
            f438a = new HoneycombMr1MotionEventVersionImpl();
        } else if (VERSION.SDK_INT >= 9) {
            f438a = new GingerbreadMotionEventVersionImpl();
        } else if (VERSION.SDK_INT >= 5) {
            f438a = new EclairMotionEventVersionImpl();
        } else {
            f438a = new BaseMotionEventVersionImpl();
        }
    }

    public static int m987a(MotionEvent motionEvent) {
        return motionEvent.getAction() & 255;
    }

    public static int m989b(MotionEvent motionEvent) {
        return (motionEvent.getAction() & 65280) >> 8;
    }

    public static int m988a(MotionEvent motionEvent, int i) {
        return f438a.m970a(motionEvent, i);
    }

    public static int m990b(MotionEvent motionEvent, int i) {
        return f438a.m971b(motionEvent, i);
    }

    public static float m991c(MotionEvent motionEvent, int i) {
        return f438a.m972c(motionEvent, i);
    }

    public static float m993d(MotionEvent motionEvent, int i) {
        return f438a.m973d(motionEvent, i);
    }

    public static int m992c(MotionEvent motionEvent) {
        return f438a.m969a(motionEvent);
    }

    public static float m994e(MotionEvent motionEvent, int i) {
        return f438a.m974e(motionEvent, i);
    }
}
