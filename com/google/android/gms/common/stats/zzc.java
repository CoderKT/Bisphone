package com.google.android.gms.common.stats;

import com.google.android.gms.internal.zzlr;

public final class zzc {
    public static zzlr<Integer> f5840a;

    public final class zza {
        public static zzlr<Integer> f5834a;
        public static zzlr<String> f5835b;
        public static zzlr<String> f5836c;
        public static zzlr<String> f5837d;
        public static zzlr<String> f5838e;
        public static zzlr<Long> f5839f;

        static {
            f5834a = zzlr.m9238a("gms:common:stats:connections:level", Integer.valueOf(zzd.f5842b));
            f5835b = zzlr.m9240a("gms:common:stats:connections:ignored_calling_processes", "");
            f5836c = zzlr.m9240a("gms:common:stats:connections:ignored_calling_services", "");
            f5837d = zzlr.m9240a("gms:common:stats:connections:ignored_target_processes", "");
            f5838e = zzlr.m9240a("gms:common:stats:connections:ignored_target_services", "com.google.android.gms.auth.GetToken");
            f5839f = zzlr.m9239a("gms:common:stats:connections:time_out_duration", Long.valueOf(600000));
        }
    }

    static {
        f5840a = zzlr.m9238a("gms:common:stats:max_num_of_events", Integer.valueOf(100));
    }
}
