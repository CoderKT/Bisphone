package com.google.android.gms.location.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class FusedLocationProviderResult implements Result, SafeParcelable {
    public static final Creator<FusedLocationProviderResult> CREATOR;
    public static final FusedLocationProviderResult f6230a;
    private final int f6231b;
    private final Status f6232c;

    static {
        f6230a = new FusedLocationProviderResult(Status.f5682a);
        CREATOR = new zze();
    }

    FusedLocationProviderResult(int i, Status status) {
        this.f6231b = i;
        this.f6232c = status;
    }

    public FusedLocationProviderResult(Status status) {
        this(1, status);
    }

    public Status m9389a() {
        return this.f6232c;
    }

    public int m9390b() {
        return this.f6231b;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zze.m9439a(this, parcel, i);
    }
}
