package com.google.android.gms.common.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class zzw {

    public final class zza {
        private final List<String> f5809a;
        private final Object f5810b;

        private zza(Object obj) {
            this.f5810b = zzx.m8718a(obj);
            this.f5809a = new ArrayList();
        }

        public zza m8714a(String str, Object obj) {
            this.f5809a.add(((String) zzx.m8718a((Object) str)) + "=" + String.valueOf(obj));
            return this;
        }

        public String toString() {
            StringBuilder append = new StringBuilder(100).append(this.f5810b.getClass().getSimpleName()).append('{');
            int size = this.f5809a.size();
            for (int i = 0; i < size; i++) {
                append.append((String) this.f5809a.get(i));
                if (i < size - 1) {
                    append.append(", ");
                }
            }
            return append.append('}').toString();
        }
    }

    public static int m8715a(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    public static zza m8716a(Object obj) {
        return new zza(null);
    }

    public static boolean m8717a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }
}
