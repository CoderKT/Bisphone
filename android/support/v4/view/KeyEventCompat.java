package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.KeyEvent;

public class KeyEventCompat {
    static final KeyEventVersionImpl f431a;

    interface KeyEventVersionImpl {
        boolean m901a(int i, int i2);

        boolean m902b(int i);
    }

    class BaseKeyEventVersionImpl implements KeyEventVersionImpl {
        BaseKeyEventVersionImpl() {
        }

        private static int m903a(int i, int i2, int i3, int i4, int i5) {
            Object obj = 1;
            Object obj2 = (i2 & i3) != 0 ? 1 : null;
            int i6 = i4 | i5;
            if ((i2 & i6) == 0) {
                obj = null;
            }
            if (obj2 != null) {
                if (obj == null) {
                    return i & (i6 ^ -1);
                }
                throw new IllegalArgumentException("bad arguments");
            } else if (obj != null) {
                return i & (i3 ^ -1);
            } else {
                return i;
            }
        }

        public int m904a(int i) {
            int i2;
            if ((i & 192) != 0) {
                i2 = i | 1;
            } else {
                i2 = i;
            }
            if ((i2 & 48) != 0) {
                i2 |= 2;
            }
            return i2 & 247;
        }

        public boolean m905a(int i, int i2) {
            if (m903a(m903a(m904a(i) & 247, i2, 1, 64, 128), i2, 2, 16, 32) == i2) {
                return true;
            }
            return false;
        }

        public boolean m906b(int i) {
            return (m904a(i) & 247) == 0;
        }
    }

    class EclairKeyEventVersionImpl extends BaseKeyEventVersionImpl {
        EclairKeyEventVersionImpl() {
        }
    }

    class HoneycombKeyEventVersionImpl extends EclairKeyEventVersionImpl {
        HoneycombKeyEventVersionImpl() {
        }

        public int m907a(int i) {
            return KeyEventCompatHoneycomb.m912a(i);
        }

        public boolean m908a(int i, int i2) {
            return KeyEventCompatHoneycomb.m913a(i, i2);
        }

        public boolean m909b(int i) {
            return KeyEventCompatHoneycomb.m914b(i);
        }
    }

    static {
        if (VERSION.SDK_INT >= 11) {
            f431a = new HoneycombKeyEventVersionImpl();
        } else {
            f431a = new BaseKeyEventVersionImpl();
        }
    }

    public static boolean m911a(KeyEvent keyEvent, int i) {
        return f431a.m901a(keyEvent.getMetaState(), i);
    }

    public static boolean m910a(KeyEvent keyEvent) {
        return f431a.m902b(keyEvent.getMetaState());
    }
}
