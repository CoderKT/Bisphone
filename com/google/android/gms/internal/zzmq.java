package com.google.android.gms.internal;

import android.content.res.Configuration;
import android.content.res.Resources;

public final class zzmq {
    public static boolean m9302a(Resources resources) {
        if (resources == null) {
            return false;
        }
        return (zzmx.m9304a() && ((resources.getConfiguration().screenLayout & 15) > 3)) || m9303b(resources);
    }

    private static boolean m9303b(Resources resources) {
        Configuration configuration = resources.getConfiguration();
        return zzmx.m9306b() && (configuration.screenLayout & 15) <= 3 && configuration.smallestScreenWidthDp >= 600;
    }
}
