package com.google.android.gms.location;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class LocationResult implements SafeParcelable {
    public static final Creator<LocationResult> CREATOR;
    static final List<Location> f6202a;
    private final int f6203b;
    private final List<Location> f6204c;

    static {
        f6202a = Collections.emptyList();
        CREATOR = new zze();
    }

    LocationResult(int i, List<Location> list) {
        this.f6203b = i;
        this.f6204c = list;
    }

    public List<Location> m9366a() {
        return this.f6204c;
    }

    int m9367b() {
        return this.f6203b;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof LocationResult)) {
            return false;
        }
        LocationResult locationResult = (LocationResult) obj;
        if (locationResult.f6204c.size() != this.f6204c.size()) {
            return false;
        }
        Iterator it = this.f6204c.iterator();
        for (Location time : locationResult.f6204c) {
            if (((Location) it.next()).getTime() != time.getTime()) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 17;
        for (Location time : this.f6204c) {
            long time2 = time.getTime();
            i = ((int) (time2 ^ (time2 >>> 32))) + (i * 31);
        }
        return i;
    }

    public String toString() {
        return "LocationResult[locations: " + this.f6204c + "]";
    }

    public void writeToParcel(Parcel parcel, int i) {
        zze.m9545a(this, parcel, i);
    }
}
