package com.google.android.gms.location;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.location.internal.zzd;
import com.google.android.gms.location.internal.zzl;
import com.google.android.gms.location.internal.zzq;

public class LocationServices {
    public static final Api<NoOptions> f6205a;
    public static final FusedLocationProviderApi f6206b;
    public static final GeofencingApi f6207c;
    public static final SettingsApi f6208d;
    private static final zzc<zzl> f6209e;
    private static final com.google.android.gms.common.api.Api.zza<zzl, NoOptions> f6210f;

    /* renamed from: com.google.android.gms.location.LocationServices.1 */
    final class C08731 extends com.google.android.gms.common.api.Api.zza<zzl, NoOptions> {
        C08731() {
        }

        public /* synthetic */ zzb m9368a(Context context, Looper looper, zzf com_google_android_gms_common_internal_zzf, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return m9369a(context, looper, com_google_android_gms_common_internal_zzf, (NoOptions) obj, connectionCallbacks, onConnectionFailedListener);
        }

        public zzl m9369a(Context context, Looper looper, zzf com_google_android_gms_common_internal_zzf, NoOptions noOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzl(context, looper, connectionCallbacks, onConnectionFailedListener, "locationServices", com_google_android_gms_common_internal_zzf);
        }
    }

    public abstract class zza<R extends Result> extends com.google.android.gms.internal.zzlb.zza<R, zzl> {
        public zza(GoogleApiClient googleApiClient) {
            super(LocationServices.f6209e, googleApiClient);
        }
    }

    static {
        f6209e = new zzc();
        f6210f = new C08731();
        f6205a = new Api("LocationServices.API", f6210f, f6209e);
        f6206b = new zzd();
        f6207c = new com.google.android.gms.location.internal.zzf();
        f6208d = new zzq();
    }

    public static zzl m9371a(GoogleApiClient googleApiClient) {
        boolean z = true;
        zzx.m8727b(googleApiClient != null, "GoogleApiClient parameter is required.");
        zzl com_google_android_gms_location_internal_zzl = (zzl) googleApiClient.m8380a(f6209e);
        if (com_google_android_gms_location_internal_zzl == null) {
            z = false;
        }
        zzx.m8723a(z, (Object) "GoogleApiClient is not configured to use the LocationServices.API Api. Pass thisinto GoogleApiClient.Builder#addApi() to use this feature.");
        return com_google_android_gms_location_internal_zzl;
    }
}
