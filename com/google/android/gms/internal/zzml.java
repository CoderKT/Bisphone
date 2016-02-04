package com.google.android.gms.internal;

import android.content.Context;
import java.util.regex.Pattern;

public final class zzml {
    private static Pattern f6146a;

    static {
        f6146a = null;
    }

    public static int m9296a(int i) {
        return i / 1000;
    }

    public static boolean m9297a(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.type.watch");
    }

    @Deprecated
    public static boolean m9298b(int i) {
        return false;
    }
}
