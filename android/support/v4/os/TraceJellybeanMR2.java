package android.support.v4.os;

import android.os.Trace;

class TraceJellybeanMR2 {
    public static void m700a(String str) {
        Trace.beginSection(str);
    }

    public static void m699a() {
        Trace.endSection();
    }
}
