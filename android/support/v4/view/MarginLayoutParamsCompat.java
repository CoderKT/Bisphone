package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.ViewGroup.MarginLayoutParams;

public class MarginLayoutParamsCompat {
    static final MarginLayoutParamsCompatImpl f436a;

    interface MarginLayoutParamsCompatImpl {
        int m924a(MarginLayoutParams marginLayoutParams);

        int m925b(MarginLayoutParams marginLayoutParams);
    }

    class MarginLayoutParamsCompatImplBase implements MarginLayoutParamsCompatImpl {
        MarginLayoutParamsCompatImplBase() {
        }

        public int m926a(MarginLayoutParams marginLayoutParams) {
            return marginLayoutParams.leftMargin;
        }

        public int m927b(MarginLayoutParams marginLayoutParams) {
            return marginLayoutParams.rightMargin;
        }
    }

    class MarginLayoutParamsCompatImplJbMr1 implements MarginLayoutParamsCompatImpl {
        MarginLayoutParamsCompatImplJbMr1() {
        }

        public int m928a(MarginLayoutParams marginLayoutParams) {
            return MarginLayoutParamsCompatJellybeanMr1.m932a(marginLayoutParams);
        }

        public int m929b(MarginLayoutParams marginLayoutParams) {
            return MarginLayoutParamsCompatJellybeanMr1.m933b(marginLayoutParams);
        }
    }

    static {
        if (VERSION.SDK_INT >= 17) {
            f436a = new MarginLayoutParamsCompatImplJbMr1();
        } else {
            f436a = new MarginLayoutParamsCompatImplBase();
        }
    }

    public static int m930a(MarginLayoutParams marginLayoutParams) {
        return f436a.m924a(marginLayoutParams);
    }

    public static int m931b(MarginLayoutParams marginLayoutParams) {
        return f436a.m925b(marginLayoutParams);
    }
}
