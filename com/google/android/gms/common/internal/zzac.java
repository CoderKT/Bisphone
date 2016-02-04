package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.Api.zzd;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

public class zzac<T extends IInterface> extends zzj<T> {
    private final zzd<T> f5742d;

    public zzac(Context context, Looper looper, int i, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, zzf com_google_android_gms_common_internal_zzf, zzd com_google_android_gms_common_api_Api_zzd) {
        super(context, looper, i, com_google_android_gms_common_internal_zzf, connectionCallbacks, onConnectionFailedListener);
        this.f5742d = com_google_android_gms_common_api_Api_zzd;
    }

    protected T m8525a(IBinder iBinder) {
        return this.f5742d.m8352a(iBinder);
    }

    protected void m8526a(int i, T t) {
        this.f5742d.m8354a(i, t);
    }

    protected String m8527e() {
        return this.f5742d.m8353a();
    }

    protected String m8528f() {
        return this.f5742d.m8355b();
    }
}
