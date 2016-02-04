package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;

public final class VisibleRegion implements SafeParcelable {
    public static final zzp CREATOR;
    public final LatLng f6471a;
    public final LatLng f6472b;
    public final LatLng f6473c;
    public final LatLng f6474d;
    public final LatLngBounds f6475e;
    private final int f6476f;

    static {
        CREATOR = new zzp();
    }

    VisibleRegion(int i, LatLng latLng, LatLng latLng2, LatLng latLng3, LatLng latLng4, LatLngBounds latLngBounds) {
        this.f6476f = i;
        this.f6471a = latLng;
        this.f6472b = latLng2;
        this.f6473c = latLng3;
        this.f6474d = latLng4;
        this.f6475e = latLngBounds;
    }

    int m10165a() {
        return this.f6476f;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VisibleRegion)) {
            return false;
        }
        VisibleRegion visibleRegion = (VisibleRegion) obj;
        return this.f6471a.equals(visibleRegion.f6471a) && this.f6472b.equals(visibleRegion.f6472b) && this.f6473c.equals(visibleRegion.f6473c) && this.f6474d.equals(visibleRegion.f6474d) && this.f6475e.equals(visibleRegion.f6475e);
    }

    public int hashCode() {
        return zzw.m8715a(this.f6471a, this.f6472b, this.f6473c, this.f6474d, this.f6475e);
    }

    public String toString() {
        return zzw.m8716a((Object) this).m8714a("nearLeft", this.f6471a).m8714a("nearRight", this.f6472b).m8714a("farLeft", this.f6473c).m8714a("farRight", this.f6474d).m8714a("latLngBounds", this.f6475e).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzp.m10472a(this, parcel, i);
    }
}
