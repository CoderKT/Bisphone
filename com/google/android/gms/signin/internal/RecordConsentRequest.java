package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class RecordConsentRequest implements SafeParcelable {
    public static final Creator<RecordConsentRequest> CREATOR;
    final int f6490a;
    private final Account f6491b;
    private final Scope[] f6492c;
    private final String f6493d;

    static {
        CREATOR = new zzg();
    }

    RecordConsentRequest(int i, Account account, Scope[] scopeArr, String str) {
        this.f6490a = i;
        this.f6491b = account;
        this.f6492c = scopeArr;
        this.f6493d = str;
    }

    public Account m10482a() {
        return this.f6491b;
    }

    public Scope[] m10483b() {
        return this.f6492c;
    }

    public String m10484c() {
        return this.f6493d;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzg.m10516a(this, parcel, i);
    }
}
