package com.google.android.gms.location.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.location.internal.zzi.zza;

public class zzb extends zzj<zzi> {
    protected final zzp<zzi> f6260d;
    private final String f6261e;

    /* renamed from: com.google.android.gms.location.internal.zzb.1 */
    class C08741 implements zzp<zzi> {
        final /* synthetic */ zzb f6259a;

        C08741(zzb com_google_android_gms_location_internal_zzb) {
            this.f6259a = com_google_android_gms_location_internal_zzb;
        }

        public void m9418a() {
            this.f6259a.m8520m();
        }

        public zzi m9419b() {
            return (zzi) this.f6259a.m8522o();
        }

        public /* synthetic */ IInterface m9420c() {
            return m9419b();
        }
    }

    public zzb(Context context, Looper looper, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, String str, zzf com_google_android_gms_common_internal_zzf) {
        super(context, looper, 23, com_google_android_gms_common_internal_zzf, connectionCallbacks, onConnectionFailedListener);
        this.f6260d = new C08741(this);
        this.f6261e = str;
    }

    protected /* synthetic */ IInterface m9422a(IBinder iBinder) {
        return m9423b(iBinder);
    }

    protected zzi m9423b(IBinder iBinder) {
        return zza.m9504a(iBinder);
    }

    protected String m9424e() {
        return "com.google.android.location.internal.GoogleLocationManagerService.START";
    }

    protected String m9425f() {
        return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
    }

    protected Bundle m9426l() {
        Bundle bundle = new Bundle();
        bundle.putString("client_name", this.f6261e);
        return bundle;
    }
}
