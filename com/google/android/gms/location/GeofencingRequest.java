package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.location.internal.ParcelableGeofence;
import java.util.List;

public class GeofencingRequest implements SafeParcelable {
    public static final Creator<GeofencingRequest> CREATOR;
    private final int f6179a;
    private final List<ParcelableGeofence> f6180b;
    private final int f6181c;

    static {
        CREATOR = new zza();
    }

    GeofencingRequest(int i, List<ParcelableGeofence> list, int i2) {
        this.f6179a = i;
        this.f6180b = list;
        this.f6181c = i2;
    }

    public int m9346a() {
        return this.f6179a;
    }

    public List<ParcelableGeofence> m9347b() {
        return this.f6180b;
    }

    public int m9348c() {
        return this.f6181c;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.m9536a(this, parcel, i);
    }
}
