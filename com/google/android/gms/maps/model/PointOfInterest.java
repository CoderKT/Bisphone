package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class PointOfInterest implements SafeParcelable {
    public static final zzg CREATOR;
    public final LatLng f6421a;
    public final String f6422b;
    public final String f6423c;
    private final int f6424d;

    static {
        CREATOR = new zzg();
    }

    PointOfInterest(int i, LatLng latLng, String str, String str2) {
        this.f6424d = i;
        this.f6421a = latLng;
        this.f6422b = str;
        this.f6423c = str2;
    }

    int m10134a() {
        return this.f6424d;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzg.m10445a(this, parcel, i);
    }
}
