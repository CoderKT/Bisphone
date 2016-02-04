package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Process;
import com.google.android.gms.common.internal.zzd;

public class zzmm {
    public static boolean m9299a() {
        return zzd.f5743a && zzlr.m9241b() && zzlr.m9237a() == Process.myUid();
    }

    public static boolean m9300a(Context context, String str) {
        try {
            return (context.getPackageManager().getApplicationInfo(str, 0).flags & 2097152) != 0;
        } catch (NameNotFoundException e) {
            return false;
        }
    }
}
