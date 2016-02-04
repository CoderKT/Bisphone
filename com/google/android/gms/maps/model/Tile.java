package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class Tile implements SafeParcelable {
    public static final zzn CREATOR;
    public final int f6458a;
    public final int f6459b;
    public final byte[] f6460c;
    private final int f6461d;

    static {
        CREATOR = new zzn();
    }

    Tile(int i, int i2, int i3, byte[] bArr) {
        this.f6461d = i;
        this.f6458a = i2;
        this.f6459b = i3;
        this.f6460c = bArr;
    }

    public Tile(int i, int i2, byte[] bArr) {
        this(1, i, i2, bArr);
    }

    int m10158a() {
        return this.f6461d;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzn.m10466a(this, parcel, i);
    }
}
