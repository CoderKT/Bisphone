package com.google.android.gms.internal;

public final class zzmp implements zzmn {
    private static zzmp f6147a;

    public static synchronized zzmn m9301a() {
        zzmn com_google_android_gms_internal_zzmn;
        synchronized (zzmp.class) {
            if (f6147a == null) {
                f6147a = new zzmp();
            }
            com_google_android_gms_internal_zzmn = f6147a;
        }
        return com_google_android_gms_internal_zzmn;
    }
}
