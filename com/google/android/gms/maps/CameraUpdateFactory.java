package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public final class CameraUpdateFactory {
    private static ICameraUpdateFactoryDelegate f6283a;

    public static CameraUpdate m9558a(CameraPosition cameraPosition) {
        try {
            return new CameraUpdate(m9560a().m9677a(cameraPosition));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public static CameraUpdate m9559a(LatLng latLng, float f) {
        try {
            return new CameraUpdate(m9560a().m9679a(latLng, f));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    private static ICameraUpdateFactoryDelegate m9560a() {
        return (ICameraUpdateFactoryDelegate) zzx.m8719a(f6283a, (Object) "CameraUpdateFactory is not initialized");
    }

    public static void m9561a(ICameraUpdateFactoryDelegate iCameraUpdateFactoryDelegate) {
        f6283a = (ICameraUpdateFactoryDelegate) zzx.m8718a((Object) iCameraUpdateFactoryDelegate);
    }
}
