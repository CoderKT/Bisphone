package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.maps.model.internal.zzf;

public final class Marker {
    private final zzf f6406a;

    public Marker(zzf com_google_android_gms_maps_model_internal_zzf) {
        this.f6406a = (zzf) zzx.m8718a((Object) com_google_android_gms_maps_model_internal_zzf);
    }

    public LatLng m10111a() {
        try {
            return this.f6406a.m10320c();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void m10112a(String str) {
        try {
            this.f6406a.m10312a(str);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String m10113b() {
        try {
            return this.f6406a.m10323e();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void m10114b(String str) {
        try {
            this.f6406a.m10318b(str);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void m10115c() {
        try {
            this.f6406a.m10325g();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void m10116d() {
        try {
            this.f6406a.m10326h();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Marker)) {
            return false;
        }
        try {
            return this.f6406a.m10314a(((Marker) obj).f6406a);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int hashCode() {
        try {
            return this.f6406a.m10329k();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
