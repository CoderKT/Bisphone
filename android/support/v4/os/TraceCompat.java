package android.support.v4.os;

import android.os.Build.VERSION;

public class TraceCompat {
    public static void m698a(String str) {
        if (VERSION.SDK_INT >= 18) {
            TraceJellybeanMR2.m700a(str);
        }
    }

    public static void m697a() {
        if (VERSION.SDK_INT >= 18) {
            TraceJellybeanMR2.m699a();
        }
    }
}
