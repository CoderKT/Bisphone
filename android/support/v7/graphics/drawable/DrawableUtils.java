package android.support.v7.graphics.drawable;

import android.graphics.PorterDuff.Mode;
import android.os.Build.VERSION;
import se.emilsjolander.stickylistheaders.C1128R;

public class DrawableUtils {
    public static Mode m2244a(int i, Mode mode) {
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                return Mode.SRC_OVER;
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                return Mode.SRC_IN;
            case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                return Mode.SRC_ATOP;
            case C1128R.styleable.StickyListHeadersListView_android_cacheColorHint /*14*/:
                return Mode.MULTIPLY;
            case C1128R.styleable.StickyListHeadersListView_android_divider /*15*/:
                return Mode.SCREEN;
            case C1128R.styleable.StickyListHeadersListView_android_dividerHeight /*16*/:
                if (VERSION.SDK_INT >= 11) {
                    return Mode.valueOf("ADD");
                }
                return mode;
            default:
                return mode;
        }
    }
}
