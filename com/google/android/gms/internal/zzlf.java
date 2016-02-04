package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzlb.zza;

public class zzlf implements zzlj {
    private final zzli f6000a;

    /* renamed from: com.google.android.gms.internal.zzlf.1 */
    class C08541 extends zzb {
        final /* synthetic */ zzlf f5999a;

        C08541(zzlf com_google_android_gms_internal_zzlf, zzlj com_google_android_gms_internal_zzlj) {
            this.f5999a = com_google_android_gms_internal_zzlf;
            super(com_google_android_gms_internal_zzlj);
        }

        public void m9061a() {
            this.f5999a.m9073a(1);
        }
    }

    public zzlf(zzli com_google_android_gms_internal_zzli) {
        this.f6000a = com_google_android_gms_internal_zzli;
    }

    private <A extends zzb> void m9070a(zzf<A> com_google_android_gms_internal_zzli_zzf_A) {
        this.f6000a.m9188a((zzf) com_google_android_gms_internal_zzli_zzf_A);
        zzb a = this.f6000a.m9183a(com_google_android_gms_internal_zzli_zzf_A.m9041b());
        if (a.m8349b() || !this.f6000a.f6064e.containsKey(com_google_android_gms_internal_zzli_zzf_A.m9041b())) {
            com_google_android_gms_internal_zzli_zzf_A.m9038a(a);
        } else {
            com_google_android_gms_internal_zzli_zzf_A.m9039a(new Status(17));
        }
    }

    public <A extends zzb, T extends zza<? extends Result, A>> T m9071a(T t) {
        try {
            m9070a((zzf) t);
        } catch (DeadObjectException e) {
            this.f6000a.m9187a(new C08541(this, this));
        }
        return t;
    }

    public void m9072a() {
        while (!this.f6000a.f6061b.isEmpty()) {
            try {
                m9070a((zzf) this.f6000a.f6061b.remove());
            } catch (Throwable e) {
                Log.w("GACConnected", "Service died while flushing queue", e);
            }
        }
    }

    public void m9073a(int i) {
        if (i == 1) {
            this.f6000a.m9201k();
        }
        for (zzf b : this.f6000a.f6069j) {
            b.m9042b(new Status(8, "The connection to Google Play services was lost"));
        }
        this.f6000a.m9185a(null);
        this.f6000a.f6060a.m8579a(i);
        this.f6000a.f6060a.m8578a();
        if (i == 2) {
            this.f6000a.m9191b();
        }
    }

    public void m9074a(Bundle bundle) {
    }

    public void m9075a(ConnectionResult connectionResult, Api<?> api, int i) {
    }

    public void m9076b() {
        this.f6000a.f6064e.clear();
        this.f6000a.m9195e();
        this.f6000a.m9185a(null);
        this.f6000a.f6060a.m8578a();
    }

    public void m9077c() {
    }

    public String m9078d() {
        return "CONNECTED";
    }
}
