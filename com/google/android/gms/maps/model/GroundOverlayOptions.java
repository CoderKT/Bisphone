package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.zzd.zza;

public final class GroundOverlayOptions implements SafeParcelable {
    public static final zzc CREATOR;
    private final int f6388a;
    private BitmapDescriptor f6389b;
    private LatLng f6390c;
    private float f6391d;
    private float f6392e;
    private LatLngBounds f6393f;
    private float f6394g;
    private float f6395h;
    private boolean f6396i;
    private float f6397j;
    private float f6398k;
    private float f6399l;

    static {
        CREATOR = new zzc();
    }

    public GroundOverlayOptions() {
        this.f6396i = true;
        this.f6397j = 0.0f;
        this.f6398k = 0.5f;
        this.f6399l = 0.5f;
        this.f6388a = 1;
    }

    GroundOverlayOptions(int i, IBinder iBinder, LatLng latLng, float f, float f2, LatLngBounds latLngBounds, float f3, float f4, boolean z, float f5, float f6, float f7) {
        this.f6396i = true;
        this.f6397j = 0.0f;
        this.f6398k = 0.5f;
        this.f6399l = 0.5f;
        this.f6388a = i;
        this.f6389b = new BitmapDescriptor(zza.m9022a(iBinder));
        this.f6390c = latLng;
        this.f6391d = f;
        this.f6392e = f2;
        this.f6393f = latLngBounds;
        this.f6394g = f3;
        this.f6395h = f4;
        this.f6396i = z;
        this.f6397j = f5;
        this.f6398k = f6;
        this.f6399l = f7;
    }

    IBinder m10097a() {
        return this.f6389b.m10077a().asBinder();
    }

    int m10098b() {
        return this.f6388a;
    }

    public LatLng m10099c() {
        return this.f6390c;
    }

    public float m10100d() {
        return this.f6391d;
    }

    public int describeContents() {
        return 0;
    }

    public float m10101e() {
        return this.f6392e;
    }

    public LatLngBounds m10102f() {
        return this.f6393f;
    }

    public float m10103g() {
        return this.f6394g;
    }

    public float m10104h() {
        return this.f6395h;
    }

    public float m10105i() {
        return this.f6397j;
    }

    public float m10106j() {
        return this.f6398k;
    }

    public float m10107k() {
        return this.f6399l;
    }

    public boolean m10108l() {
        return this.f6396i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzc.m10433a(this, parcel, i);
    }
}
