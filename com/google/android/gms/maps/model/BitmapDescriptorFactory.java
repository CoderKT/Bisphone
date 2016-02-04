package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.maps.model.internal.zza;

public final class BitmapDescriptorFactory {
    private static zza f6370a;

    public static BitmapDescriptor m10078a(int i) {
        try {
            return new BitmapDescriptor(m10079a().m10201a(i));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    private static zza m10079a() {
        return (zza) zzx.m8719a(f6370a, (Object) "IBitmapDescriptorFactory is not initialized");
    }

    public static void m10080a(zza com_google_android_gms_maps_model_internal_zza) {
        if (f6370a == null) {
            f6370a = (zza) zzx.m8718a((Object) com_google_android_gms_maps_model_internal_zza);
        }
    }
}
