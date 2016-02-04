package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzp.zza;

public class ResolveAccountResponse implements SafeParcelable {
    public static final Creator<ResolveAccountResponse> CREATOR;
    final int f5708a;
    IBinder f5709b;
    private ConnectionResult f5710c;
    private boolean f5711d;
    private boolean f5712e;

    static {
        CREATOR = new zzz();
    }

    public ResolveAccountResponse(int i) {
        this(new ConnectionResult(i, null));
    }

    ResolveAccountResponse(int i, IBinder iBinder, ConnectionResult connectionResult, boolean z, boolean z2) {
        this.f5708a = i;
        this.f5709b = iBinder;
        this.f5710c = connectionResult;
        this.f5711d = z;
        this.f5712e = z2;
    }

    public ResolveAccountResponse(ConnectionResult connectionResult) {
        this(1, null, connectionResult, false, false);
    }

    public zzp m8423a() {
        return zza.m8479a(this.f5709b);
    }

    public ConnectionResult m8424b() {
        return this.f5710c;
    }

    public boolean m8425c() {
        return this.f5711d;
    }

    public boolean m8426d() {
        return this.f5712e;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResolveAccountResponse)) {
            return false;
        }
        ResolveAccountResponse resolveAccountResponse = (ResolveAccountResponse) obj;
        return this.f5710c.equals(resolveAccountResponse.f5710c) && m8423a().equals(resolveAccountResponse.m8423a());
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzz.m8731a(this, parcel, i);
    }
}
