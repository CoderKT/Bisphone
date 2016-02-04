package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzx;

public final class LatLngBounds implements SafeParcelable {
    public static final zzd CREATOR;
    public final LatLng f6403a;
    public final LatLng f6404b;
    private final int f6405c;

    static {
        CREATOR = new zzd();
    }

    LatLngBounds(int i, LatLng latLng, LatLng latLng2) {
        zzx.m8719a((Object) latLng, (Object) "null southwest");
        zzx.m8719a((Object) latLng2, (Object) "null northeast");
        zzx.m8724a(latLng2.f6400a >= latLng.f6400a, "southern latitude exceeds northern latitude (%s > %s)", Double.valueOf(latLng.f6400a), Double.valueOf(latLng2.f6400a));
        this.f6405c = i;
        this.f6403a = latLng;
        this.f6404b = latLng2;
    }

    int m10110a() {
        return this.f6405c;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLngBounds)) {
            return false;
        }
        LatLngBounds latLngBounds = (LatLngBounds) obj;
        return this.f6403a.equals(latLngBounds.f6403a) && this.f6404b.equals(latLngBounds.f6404b);
    }

    public int hashCode() {
        return zzw.m8715a(this.f6403a, this.f6404b);
    }

    public String toString() {
        return zzw.m8716a((Object) this).m8714a("southwest", this.f6403a).m8714a("northeast", this.f6404b).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzd.m10436a(this, parcel, i);
    }
}
