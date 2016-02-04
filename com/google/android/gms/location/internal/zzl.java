package com.google.android.gms.location.internal;

import android.content.Context;
import android.location.Location;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

public class zzl extends zzb {
    private final zzk f6279e;

    public zzl(Context context, Looper looper, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, String str, zzf com_google_android_gms_common_internal_zzf) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, str, com_google_android_gms_common_internal_zzf);
        this.f6279e = new zzk(context, this.d);
    }

    public void m9523a() {
        synchronized (this.f6279e) {
            if (m8510b()) {
                try {
                    this.f6279e.m9521b();
                    this.f6279e.m9522c();
                } catch (Throwable e) {
                    Log.e("LocationClientImpl", "Client disconnected before listeners could be cleaned up", e);
                }
            }
            super.m8499a();
        }
    }

    public void m9524a(LocationRequest locationRequest, LocationListener locationListener, Looper looper, zzg com_google_android_gms_location_internal_zzg) {
        synchronized (this.f6279e) {
            this.f6279e.m9519a(locationRequest, locationListener, looper, com_google_android_gms_location_internal_zzg);
        }
    }

    public boolean m9525q() {
        return true;
    }

    public Location m9526r() {
        return this.f6279e.m9518a();
    }
}
