package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation.Builder;

public class StreetViewPanoramaCamera implements SafeParcelable {
    public static final zzj CREATOR;
    public final float f6441a;
    public final float f6442b;
    public final float f6443c;
    private final int f6444d;
    private StreetViewPanoramaOrientation f6445e;

    static {
        CREATOR = new zzj();
    }

    StreetViewPanoramaCamera(int i, float f, float f2, float f3) {
        boolean z = -90.0f <= f2 && f2 <= 90.0f;
        zzx.m8727b(z, "Tilt needs to be between -90 and 90 inclusive");
        this.f6444d = i;
        if (((double) f) <= 0.0d) {
            f = 0.0f;
        }
        this.f6441a = f;
        this.f6442b = f2 + 0.0f;
        this.f6443c = (((double) f3) <= 0.0d ? (f3 % 360.0f) + 360.0f : f3) % 360.0f;
        this.f6445e = new Builder().m10154a(f2).m10156b(f3).m10155a();
    }

    int m10151a() {
        return this.f6444d;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreetViewPanoramaCamera)) {
            return false;
        }
        StreetViewPanoramaCamera streetViewPanoramaCamera = (StreetViewPanoramaCamera) obj;
        return Float.floatToIntBits(this.f6441a) == Float.floatToIntBits(streetViewPanoramaCamera.f6441a) && Float.floatToIntBits(this.f6442b) == Float.floatToIntBits(streetViewPanoramaCamera.f6442b) && Float.floatToIntBits(this.f6443c) == Float.floatToIntBits(streetViewPanoramaCamera.f6443c);
    }

    public int hashCode() {
        return zzw.m8715a(Float.valueOf(this.f6441a), Float.valueOf(this.f6442b), Float.valueOf(this.f6443c));
    }

    public String toString() {
        return zzw.m8716a((Object) this).m8714a("zoom", Float.valueOf(this.f6441a)).m8714a("tilt", Float.valueOf(this.f6442b)).m8714a("bearing", Float.valueOf(this.f6443c)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzj.m10454a(this, parcel, i);
    }
}
