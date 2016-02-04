package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.List;

public final class LocationSettingsRequest implements SafeParcelable {
    public static final Creator<LocationSettingsRequest> CREATOR;
    private final int f6211a;
    private final List<LocationRequest> f6212b;
    private final boolean f6213c;
    private final boolean f6214d;
    private final boolean f6215e;

    static {
        CREATOR = new zzf();
    }

    LocationSettingsRequest(int i, List<LocationRequest> list, boolean z, boolean z2, boolean z3) {
        this.f6211a = i;
        this.f6212b = list;
        this.f6213c = z;
        this.f6214d = z2;
        this.f6215e = z3;
    }

    public int m9372a() {
        return this.f6211a;
    }

    public List<LocationRequest> m9373b() {
        return Collections.unmodifiableList(this.f6212b);
    }

    public boolean m9374c() {
        return this.f6213c;
    }

    public boolean m9375d() {
        return this.f6214d;
    }

    public int describeContents() {
        return 0;
    }

    public boolean m9376e() {
        return this.f6215e;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzf.m9548a(this, parcel, i);
    }
}
