package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class LocationSettingsStates implements SafeParcelable {
    public static final Creator<LocationSettingsStates> CREATOR;
    private final int f6219a;
    private final boolean f6220b;
    private final boolean f6221c;
    private final boolean f6222d;
    private final boolean f6223e;
    private final boolean f6224f;
    private final boolean f6225g;
    private final boolean f6226h;

    static {
        CREATOR = new zzh();
    }

    LocationSettingsStates(int i, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        this.f6219a = i;
        this.f6220b = z;
        this.f6221c = z2;
        this.f6222d = z3;
        this.f6223e = z4;
        this.f6224f = z5;
        this.f6225g = z6;
        this.f6226h = z7;
    }

    public int m9380a() {
        return this.f6219a;
    }

    public boolean m9381b() {
        return this.f6220b;
    }

    public boolean m9382c() {
        return this.f6223e;
    }

    public boolean m9383d() {
        return this.f6221c;
    }

    public int describeContents() {
        return 0;
    }

    public boolean m9384e() {
        return this.f6224f;
    }

    public boolean m9385f() {
        return this.f6222d;
    }

    public boolean m9386g() {
        return this.f6225g;
    }

    public boolean m9387h() {
        return this.f6226h;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzh.m9554a(this, parcel, i);
    }
}
