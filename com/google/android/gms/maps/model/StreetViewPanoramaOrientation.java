package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzx;

public class StreetViewPanoramaOrientation implements SafeParcelable {
    public static final zzm CREATOR;
    public final float f6455a;
    public final float f6456b;
    private final int f6457c;

    public final class Builder {
        public float f6453a;
        public float f6454b;

        public Builder m10154a(float f) {
            this.f6454b = f;
            return this;
        }

        public StreetViewPanoramaOrientation m10155a() {
            return new StreetViewPanoramaOrientation(this.f6454b, this.f6453a);
        }

        public Builder m10156b(float f) {
            this.f6453a = f;
            return this;
        }
    }

    static {
        CREATOR = new zzm();
    }

    public StreetViewPanoramaOrientation(float f, float f2) {
        this(1, f, f2);
    }

    StreetViewPanoramaOrientation(int i, float f, float f2) {
        boolean z = -90.0f <= f && f <= 90.0f;
        zzx.m8727b(z, "Tilt needs to be between -90 and 90 inclusive");
        this.f6457c = i;
        this.f6455a = 0.0f + f;
        if (((double) f2) <= 0.0d) {
            f2 = (f2 % 360.0f) + 360.0f;
        }
        this.f6456b = f2 % 360.0f;
    }

    int m10157a() {
        return this.f6457c;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreetViewPanoramaOrientation)) {
            return false;
        }
        StreetViewPanoramaOrientation streetViewPanoramaOrientation = (StreetViewPanoramaOrientation) obj;
        return Float.floatToIntBits(this.f6455a) == Float.floatToIntBits(streetViewPanoramaOrientation.f6455a) && Float.floatToIntBits(this.f6456b) == Float.floatToIntBits(streetViewPanoramaOrientation.f6456b);
    }

    public int hashCode() {
        return zzw.m8715a(Float.valueOf(this.f6455a), Float.valueOf(this.f6456b));
    }

    public String toString() {
        return zzw.m8716a((Object) this).m8714a("tilt", Float.valueOf(this.f6455a)).m8714a("bearing", Float.valueOf(this.f6456b)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzm.m10463a(this, parcel, i);
    }
}
