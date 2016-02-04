package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;

public class StreetViewPanoramaLocation implements SafeParcelable {
    public static final zzl CREATOR;
    public final StreetViewPanoramaLink[] f6449a;
    public final LatLng f6450b;
    public final String f6451c;
    private final int f6452d;

    static {
        CREATOR = new zzl();
    }

    StreetViewPanoramaLocation(int i, StreetViewPanoramaLink[] streetViewPanoramaLinkArr, LatLng latLng, String str) {
        this.f6452d = i;
        this.f6449a = streetViewPanoramaLinkArr;
        this.f6450b = latLng;
        this.f6451c = str;
    }

    int m10153a() {
        return this.f6452d;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreetViewPanoramaLocation)) {
            return false;
        }
        StreetViewPanoramaLocation streetViewPanoramaLocation = (StreetViewPanoramaLocation) obj;
        return this.f6451c.equals(streetViewPanoramaLocation.f6451c) && this.f6450b.equals(streetViewPanoramaLocation.f6450b);
    }

    public int hashCode() {
        return zzw.m8715a(this.f6450b, this.f6451c);
    }

    public String toString() {
        return zzw.m8716a((Object) this).m8714a("panoId", this.f6451c).m8714a("position", this.f6450b.toString()).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzl.m10460a(this, parcel, i);
    }
}
