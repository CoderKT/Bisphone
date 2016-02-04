package android.support.v4.view;

import android.view.KeyEvent;

class KeyEventCompatHoneycomb {
    public static int m912a(int i) {
        return KeyEvent.normalizeMetaState(i);
    }

    public static boolean m913a(int i, int i2) {
        return KeyEvent.metaStateHasModifiers(i, i2);
    }

    public static boolean m914b(int i) {
        return KeyEvent.metaStateHasNoModifiers(i);
    }
}
