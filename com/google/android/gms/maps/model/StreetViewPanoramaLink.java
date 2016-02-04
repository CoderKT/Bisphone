package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;

public class StreetViewPanoramaLink implements SafeParcelable {
    public static final zzk CREATOR;
    public final String f6446a;
    public final float f6447b;
    private final int f6448c;

    static {
        CREATOR = new zzk();
    }

    StreetViewPanoramaLink(int i, String str, float f) {
        this.f6448c = i;
        this.f6446a = str;
        if (((double) f) <= 0.0d) {
            f = (f % 360.0f) + 360.0f;
        }
        this.f6447b = f % 360.0f;
    }

    int m10152a() {
        return this.f6448c;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreetViewPanoramaLink)) {
            return false;
        }
        StreetViewPanoramaLink streetViewPanoramaLink = (StreetViewPanoramaLink) obj;
        return this.f6446a.equals(streetViewPanoramaLink.f6446a) && Float.floatToIntBits(this.f6447b) == Float.floatToIntBits(streetViewPanoramaLink.f6447b);
    }

    public int hashCode() {
        return zzw.m8715a(this.f6446a, Float.valueOf(this.f6447b));
    }

    public String toString() {
        return zzw.m8716a((Object) this).m8714a("panoId", this.f6446a).m8714a("bearing", Float.valueOf(this.f6447b)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzk.m10457a(this, parcel, i);
    }
}
