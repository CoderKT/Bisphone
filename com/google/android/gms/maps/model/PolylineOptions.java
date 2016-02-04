package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.List;

public final class PolylineOptions implements SafeParcelable {
    public static final zzi CREATOR;
    private final int f6434a;
    private final List<LatLng> f6435b;
    private float f6436c;
    private int f6437d;
    private float f6438e;
    private boolean f6439f;
    private boolean f6440g;

    static {
        CREATOR = new zzi();
    }

    public PolylineOptions() {
        this.f6436c = 10.0f;
        this.f6437d = -16777216;
        this.f6438e = 0.0f;
        this.f6439f = true;
        this.f6440g = false;
        this.f6434a = 1;
        this.f6435b = new ArrayList();
    }

    PolylineOptions(int i, List list, float f, int i2, float f2, boolean z, boolean z2) {
        this.f6436c = 10.0f;
        this.f6437d = -16777216;
        this.f6438e = 0.0f;
        this.f6439f = true;
        this.f6440g = false;
        this.f6434a = i;
        this.f6435b = list;
        this.f6436c = f;
        this.f6437d = i2;
        this.f6438e = f2;
        this.f6439f = z;
        this.f6440g = z2;
    }

    int m10144a() {
        return this.f6434a;
    }

    public List<LatLng> m10145b() {
        return this.f6435b;
    }

    public float m10146c() {
        return this.f6436c;
    }

    public int m10147d() {
        return this.f6437d;
    }

    public int describeContents() {
        return 0;
    }

    public float m10148e() {
        return this.f6438e;
    }

    public boolean m10149f() {
        return this.f6439f;
    }

    public boolean m10150g() {
        return this.f6440g;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzi.m10451a(this, parcel, i);
    }
}
