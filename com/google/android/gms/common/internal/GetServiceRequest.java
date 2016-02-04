package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzp.zza;
import java.util.Collection;

public class GetServiceRequest implements SafeParcelable {
    public static final Creator<GetServiceRequest> CREATOR;
    final int f5697a;
    final int f5698b;
    int f5699c;
    String f5700d;
    IBinder f5701e;
    Scope[] f5702f;
    Bundle f5703g;
    Account f5704h;

    static {
        CREATOR = new zzi();
    }

    public GetServiceRequest(int i) {
        this.f5697a = 2;
        this.f5699c = GoogleApiAvailability.f5641a;
        this.f5698b = i;
    }

    GetServiceRequest(int i, int i2, int i3, String str, IBinder iBinder, Scope[] scopeArr, Bundle bundle, Account account) {
        this.f5697a = i;
        this.f5698b = i2;
        this.f5699c = i3;
        this.f5700d = str;
        if (i < 2) {
            this.f5704h = m8415a(iBinder);
        } else {
            this.f5701e = iBinder;
            this.f5704h = account;
        }
        this.f5702f = scopeArr;
        this.f5703g = bundle;
    }

    private Account m8415a(IBinder iBinder) {
        return iBinder != null ? zza.m8480a(zza.m8479a(iBinder)) : null;
    }

    public GetServiceRequest m8416a(Account account) {
        this.f5704h = account;
        return this;
    }

    public GetServiceRequest m8417a(Bundle bundle) {
        this.f5703g = bundle;
        return this;
    }

    public GetServiceRequest m8418a(zzp com_google_android_gms_common_internal_zzp) {
        if (com_google_android_gms_common_internal_zzp != null) {
            this.f5701e = com_google_android_gms_common_internal_zzp.asBinder();
        }
        return this;
    }

    public GetServiceRequest m8419a(String str) {
        this.f5700d = str;
        return this;
    }

    public GetServiceRequest m8420a(Collection<Scope> collection) {
        this.f5702f = (Scope[]) collection.toArray(new Scope[collection.size()]);
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzi.m8549a(this, parcel, i);
    }
}
