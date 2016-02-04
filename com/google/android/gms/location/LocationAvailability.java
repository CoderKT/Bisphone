package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;

public final class LocationAvailability implements SafeParcelable {
    public static final LocationAvailabilityCreator CREATOR;
    int f6188a;
    int f6189b;
    long f6190c;
    int f6191d;
    private final int f6192e;

    static {
        CREATOR = new LocationAvailabilityCreator();
    }

    LocationAvailability(int i, int i2, int i3, int i4, long j) {
        this.f6192e = i;
        this.f6191d = i2;
        this.f6188a = i3;
        this.f6189b = i4;
        this.f6190c = j;
    }

    public boolean m9351a() {
        return this.f6191d < 1000;
    }

    int m9352b() {
        return this.f6192e;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof LocationAvailability)) {
            return false;
        }
        LocationAvailability locationAvailability = (LocationAvailability) obj;
        return this.f6191d == locationAvailability.f6191d && this.f6188a == locationAvailability.f6188a && this.f6189b == locationAvailability.f6189b && this.f6190c == locationAvailability.f6190c;
    }

    public int hashCode() {
        return zzw.m8715a(Integer.valueOf(this.f6191d), Integer.valueOf(this.f6188a), Integer.valueOf(this.f6189b), Long.valueOf(this.f6190c));
    }

    public String toString() {
        return "LocationAvailability[isLocationAvailable: " + m9351a() + "]";
    }

    public void writeToParcel(Parcel parcel, int i) {
        LocationAvailabilityCreator.m9353a(this, parcel, i);
    }
}
