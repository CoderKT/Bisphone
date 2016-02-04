package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class LocationSettingsResult implements Result, SafeParcelable {
    public static final Creator<LocationSettingsResult> CREATOR;
    private final int f6216a;
    private final Status f6217b;
    private final LocationSettingsStates f6218c;

    static {
        CREATOR = new zzg();
    }

    LocationSettingsResult(int i, Status status, LocationSettingsStates locationSettingsStates) {
        this.f6216a = i;
        this.f6217b = status;
        this.f6218c = locationSettingsStates;
    }

    public Status m9377a() {
        return this.f6217b;
    }

    public int m9378b() {
        return this.f6216a;
    }

    public LocationSettingsStates m9379c() {
        return this.f6218c;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzg.m9551a(this, parcel, i);
    }
}
