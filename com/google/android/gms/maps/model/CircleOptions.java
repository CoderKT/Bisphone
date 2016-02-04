package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class CircleOptions implements SafeParcelable {
    public static final zzb CREATOR;
    private final int f6380a;
    private LatLng f6381b;
    private double f6382c;
    private float f6383d;
    private int f6384e;
    private int f6385f;
    private float f6386g;
    private boolean f6387h;

    static {
        CREATOR = new zzb();
    }

    public CircleOptions() {
        this.f6381b = null;
        this.f6382c = 0.0d;
        this.f6383d = 10.0f;
        this.f6384e = -16777216;
        this.f6385f = 0;
        this.f6386g = 0.0f;
        this.f6387h = true;
        this.f6380a = 1;
    }

    CircleOptions(int i, LatLng latLng, double d, float f, int i2, int i3, float f2, boolean z) {
        this.f6381b = null;
        this.f6382c = 0.0d;
        this.f6383d = 10.0f;
        this.f6384e = -16777216;
        this.f6385f = 0;
        this.f6386g = 0.0f;
        this.f6387h = true;
        this.f6380a = i;
        this.f6381b = latLng;
        this.f6382c = d;
        this.f6383d = f;
        this.f6384e = i2;
        this.f6385f = i3;
        this.f6386g = f2;
        this.f6387h = z;
    }

    int m10089a() {
        return this.f6380a;
    }

    public LatLng m10090b() {
        return this.f6381b;
    }

    public double m10091c() {
        return this.f6382c;
    }

    public float m10092d() {
        return this.f6383d;
    }

    public int describeContents() {
        return 0;
    }

    public int m10093e() {
        return this.f6384e;
    }

    public int m10094f() {
        return this.f6385f;
    }

    public float m10095g() {
        return this.f6386g;
    }

    public boolean m10096h() {
        return this.f6387h;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.m10430a(this, parcel, i);
    }
}
