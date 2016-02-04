package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;

public final class Scope implements SafeParcelable {
    public static final Creator<Scope> CREATOR;
    final int f5680a;
    private final String f5681b;

    static {
        CREATOR = new zzc();
    }

    Scope(int i, String str) {
        zzx.m8721a(str, (Object) "scopeUri must not be null or empty");
        this.f5680a = i;
        this.f5681b = str;
    }

    public Scope(String str) {
        this(1, str);
    }

    public String m8396a() {
        return this.f5681b;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return this == obj ? true : !(obj instanceof Scope) ? false : this.f5681b.equals(((Scope) obj).f5681b);
    }

    public int hashCode() {
        return this.f5681b.hashCode();
    }

    public String toString() {
        return this.f5681b;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzc.m8407a(this, parcel, i);
    }
}
