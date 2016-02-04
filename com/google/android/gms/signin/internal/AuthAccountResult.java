package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class AuthAccountResult implements SafeParcelable {
    public static final Creator<AuthAccountResult> CREATOR;
    final int f6486a;

    static {
        CREATOR = new zza();
    }

    public AuthAccountResult() {
        this(1);
    }

    AuthAccountResult(int i) {
        this.f6486a = i;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.m10485a(this, parcel, i);
    }
}
