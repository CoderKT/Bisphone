package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ValidateAccountRequest implements SafeParcelable {
    public static final Creator<ValidateAccountRequest> CREATOR;
    final int f5713a;
    final IBinder f5714b;
    private final int f5715c;
    private final Scope[] f5716d;
    private final Bundle f5717e;
    private final String f5718f;

    static {
        CREATOR = new zzad();
    }

    ValidateAccountRequest(int i, int i2, IBinder iBinder, Scope[] scopeArr, Bundle bundle, String str) {
        this.f5713a = i;
        this.f5715c = i2;
        this.f5714b = iBinder;
        this.f5716d = scopeArr;
        this.f5717e = bundle;
        this.f5718f = str;
    }

    public ValidateAccountRequest(zzp com_google_android_gms_common_internal_zzp, Scope[] scopeArr, String str, Bundle bundle) {
        this(1, GoogleApiAvailability.f5641a, com_google_android_gms_common_internal_zzp == null ? null : com_google_android_gms_common_internal_zzp.asBinder(), scopeArr, bundle, str);
    }

    public int m8427a() {
        return this.f5715c;
    }

    public Scope[] m8428b() {
        return this.f5716d;
    }

    public String m8429c() {
        return this.f5718f;
    }

    public Bundle m8430d() {
        return this.f5717e;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzad.m8529a(this, parcel, i);
    }
}
