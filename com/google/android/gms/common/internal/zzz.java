package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzz implements Creator<ResolveAccountResponse> {
    static void m8731a(ResolveAccountResponse resolveAccountResponse, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8460a(parcel, 1, resolveAccountResponse.f5708a);
        zzb.m8463a(parcel, 2, resolveAccountResponse.f5709b, false);
        zzb.m8464a(parcel, 3, resolveAccountResponse.m8424b(), i, false);
        zzb.m8469a(parcel, 4, resolveAccountResponse.m8425c());
        zzb.m8469a(parcel, 5, resolveAccountResponse.m8426d());
        zzb.m8456a(parcel, a);
    }

    public ResolveAccountResponse m8732a(Parcel parcel) {
        ConnectionResult connectionResult = null;
        boolean z = false;
        int b = zza.m8438b(parcel);
        boolean z2 = false;
        IBinder iBinder = null;
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = zza.m8432a(parcel);
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    i = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    iBinder = zza.m8451l(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    connectionResult = (ConnectionResult) zza.m8434a(parcel, a, ConnectionResult.CREATOR);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    z2 = zza.m8442c(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    z = zza.m8442c(parcel, a);
                    break;
                default:
                    zza.m8439b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new ResolveAccountResponse(i, iBinder, connectionResult, z2, z);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public ResolveAccountResponse[] m8733a(int i) {
        return new ResolveAccountResponse[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m8732a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m8733a(i);
    }
}
