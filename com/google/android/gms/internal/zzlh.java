package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.internal.zzlb.zza;
import java.util.Collections;

public class zzlh implements zzlj {
    private final zzli f6046a;

    public zzlh(zzli com_google_android_gms_internal_zzli) {
        this.f6046a = com_google_android_gms_internal_zzli;
    }

    public <A extends zzb, T extends zza<? extends Result, A>> T m9149a(T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }

    public void m9150a() {
        this.f6046a.m9196f();
        this.f6046a.f6065f = Collections.emptySet();
    }

    public void m9151a(int i) {
    }

    public void m9152a(Bundle bundle) {
    }

    public void m9153a(ConnectionResult connectionResult, Api<?> api, int i) {
    }

    public void m9154b() {
        for (zzf com_google_android_gms_internal_zzli_zzf : this.f6046a.f6061b) {
            com_google_android_gms_internal_zzli_zzf.m9040a(null);
            com_google_android_gms_internal_zzli_zzf.m9046g();
        }
        this.f6046a.f6061b.clear();
        this.f6046a.f6064e.clear();
        this.f6046a.m9195e();
    }

    public void m9155c() {
        this.f6046a.m9197g();
    }

    public String m9156d() {
        return "DISCONNECTED";
    }
}
