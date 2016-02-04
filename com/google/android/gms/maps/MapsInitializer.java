package com.google.android.gms.maps;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.maps.internal.zzc;
import com.google.android.gms.maps.internal.zzy;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public final class MapsInitializer {
    private static boolean f6323a;

    static {
        f6323a = false;
    }

    public static synchronized int m9658a(Context context) {
        int i = 0;
        synchronized (MapsInitializer.class) {
            zzx.m8719a((Object) context, (Object) "Context is null");
            if (!f6323a) {
                try {
                    m9659a(zzy.m10069a(context));
                    f6323a = true;
                } catch (GooglePlayServicesNotAvailableException e) {
                    i = e.f5643a;
                }
            }
        }
        return i;
    }

    public static void m9659a(zzc com_google_android_gms_maps_internal_zzc) {
        try {
            CameraUpdateFactory.m9561a(com_google_android_gms_maps_internal_zzc.m9999a());
            BitmapDescriptorFactory.m10080a(com_google_android_gms_maps_internal_zzc.m10005b());
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
