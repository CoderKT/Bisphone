package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Set;

public class AuthAccountRequest implements SafeParcelable {
    public static final Creator<AuthAccountRequest> CREATOR;
    final int f5693a;
    final IBinder f5694b;
    final Scope[] f5695c;

    static {
        CREATOR = new zzc();
    }

    AuthAccountRequest(int i, IBinder iBinder, Scope[] scopeArr) {
        this.f5693a = i;
        this.f5694b = iBinder;
        this.f5695c = scopeArr;
    }

    public AuthAccountRequest(zzp com_google_android_gms_common_internal_zzp, Set<Scope> set) {
        this(1, com_google_android_gms_common_internal_zzp.asBinder(), (Scope[]) set.toArray(new Scope[set.size()]));
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzc.m8532a(this, parcel, i);
    }
}
