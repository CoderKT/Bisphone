package com.google.android.gms.location.internal;

import android.location.Location;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class zzd implements FusedLocationProviderApi {

    abstract class zza extends com.google.android.gms.location.LocationServices.zza<Status> {
        public zza(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        public /* synthetic */ Result m9433c(Status status) {
            return m9434d(status);
        }

        public Status m9434d(Status status) {
            return status;
        }
    }

    /* renamed from: com.google.android.gms.location.internal.zzd.1 */
    class C08761 extends zza {
        final /* synthetic */ LocationRequest f6263b;
        final /* synthetic */ LocationListener f6264c;
        final /* synthetic */ zzd f6265d;

        /* renamed from: com.google.android.gms.location.internal.zzd.1.1 */
        class C08751 extends com.google.android.gms.location.internal.zzg.zza {
            final /* synthetic */ C08761 f6262a;

            C08751(C08761 c08761) {
                this.f6262a = c08761;
            }

            public void m9432a(FusedLocationProviderResult fusedLocationProviderResult) {
                this.f6262a.m9029a(fusedLocationProviderResult.m9389a());
            }
        }

        C08761(zzd com_google_android_gms_location_internal_zzd, GoogleApiClient googleApiClient, LocationRequest locationRequest, LocationListener locationListener) {
            this.f6265d = com_google_android_gms_location_internal_zzd;
            this.f6263b = locationRequest;
            this.f6264c = locationListener;
            super(googleApiClient);
        }

        protected void m9435a(zzl com_google_android_gms_location_internal_zzl) {
            com_google_android_gms_location_internal_zzl.m9524a(this.f6263b, this.f6264c, null, new C08751(this));
        }

        protected /* synthetic */ void m9436b(zzb com_google_android_gms_common_api_Api_zzb) {
            m9435a((zzl) com_google_android_gms_common_api_Api_zzb);
        }
    }

    public Location m9437a(GoogleApiClient googleApiClient) {
        try {
            return LocationServices.m9371a(googleApiClient).m9526r();
        } catch (Exception e) {
            return null;
        }
    }

    public PendingResult<Status> m9438a(GoogleApiClient googleApiClient, LocationRequest locationRequest, LocationListener locationListener) {
        return googleApiClient.m8381a(new C08761(this, googleApiClient, locationRequest, locationListener));
    }
}
