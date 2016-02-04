package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class LatLng implements SafeParcelable {
    public static final zze CREATOR;
    public final double f6400a;
    public final double f6401b;
    private final int f6402c;

    static {
        CREATOR = new zze();
    }

    public LatLng(double d, double d2) {
        this(1, d, d2);
    }

    LatLng(int i, double d, double d2) {
        this.f6402c = i;
        if (-180.0d > d2 || d2 >= 180.0d) {
            this.f6401b = ((((d2 - 180.0d) % 360.0d) + 360.0d) % 360.0d) - 180.0d;
        } else {
            this.f6401b = d2;
        }
        this.f6400a = Math.max(-90.0d, Math.min(90.0d, d));
    }

    int m10109a() {
        return this.f6402c;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLng)) {
            return false;
        }
        LatLng latLng = (LatLng) obj;
        return Double.doubleToLongBits(this.f6400a) == Double.doubleToLongBits(latLng.f6400a) && Double.doubleToLongBits(this.f6401b) == Double.doubleToLongBits(latLng.f6401b);
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.f6400a);
        int i = ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 31;
        long doubleToLongBits2 = Double.doubleToLongBits(this.f6401b);
        return (i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
    }

    public String toString() {
        return "lat/lng: (" + this.f6400a + "," + this.f6401b + ")";
    }

    public void writeToParcel(Parcel parcel, int i) {
        zze.m10439a(this, parcel, i);
    }
}
