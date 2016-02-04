package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzc implements Creator<AuthAccountRequest> {
    static void m8532a(AuthAccountRequest authAccountRequest, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8460a(parcel, 1, authAccountRequest.f5693a);
        zzb.m8463a(parcel, 2, authAccountRequest.f5694b, false);
        zzb.m8471a(parcel, 3, authAccountRequest.f5695c, i, false);
        zzb.m8456a(parcel, a);
    }

    public AuthAccountRequest m8533a(Parcel parcel) {
        Scope[] scopeArr = null;
        int b = zza.m8438b(parcel);
        int i = 0;
        IBinder iBinder = null;
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
                    scopeArr = (Scope[]) zza.m8440b(parcel, a, Scope.CREATOR);
                    break;
                default:
                    zza.m8439b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new AuthAccountRequest(i, iBinder, scopeArr);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public AuthAccountRequest[] m8534a(int i) {
        return new AuthAccountRequest[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m8533a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m8534a(i);
    }
}
