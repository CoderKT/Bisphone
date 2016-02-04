package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ResolveAccountRequest implements SafeParcelable {
    public static final Creator<ResolveAccountRequest> CREATOR;
    final int f5705a;
    private final Account f5706b;
    private final int f5707c;

    static {
        CREATOR = new zzy();
    }

    ResolveAccountRequest(int i, Account account, int i2) {
        this.f5705a = i;
        this.f5706b = account;
        this.f5707c = i2;
    }

    public ResolveAccountRequest(Account account, int i) {
        this(1, account, i);
    }

    public Account m8421a() {
        return this.f5706b;
    }

    public int m8422b() {
        return this.f5707c;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzy.m8728a(this, parcel, i);
    }
}
