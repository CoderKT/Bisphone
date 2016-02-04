package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.internal.zzlb.zza;

public interface zzlj {
    <A extends zzb, T extends zza<? extends Result, A>> T m9062a(T t);

    void m9063a();

    void m9064a(int i);

    void m9065a(Bundle bundle);

    void m9066a(ConnectionResult connectionResult, Api<?> api, int i);

    void m9067b();

    void m9068c();

    String m9069d();
}
