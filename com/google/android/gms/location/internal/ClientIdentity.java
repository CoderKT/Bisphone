package com.google.android.gms.location.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;

public class ClientIdentity implements SafeParcelable {
    public static final zzc CREATOR;
    public final int f6227a;
    public final String f6228b;
    private final int f6229c;

    static {
        CREATOR = new zzc();
    }

    ClientIdentity(int i, int i2, String str) {
        this.f6229c = i;
        this.f6227a = i2;
        this.f6228b = str;
    }

    int m9388a() {
        return this.f6229c;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof ClientIdentity)) {
            return false;
        }
        ClientIdentity clientIdentity = (ClientIdentity) obj;
        return clientIdentity.f6227a == this.f6227a && zzw.m8717a(clientIdentity.f6228b, this.f6228b);
    }

    public int hashCode() {
        return this.f6227a;
    }

    public String toString() {
        return String.format("%d:%s", new Object[]{Integer.valueOf(this.f6227a), this.f6228b});
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzc.m9427a(this, parcel, i);
    }
}
