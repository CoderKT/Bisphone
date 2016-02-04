package com.google.android.gms.location.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import se.emilsjolander.stickylistheaders.C1128R;

public class zze implements Creator<FusedLocationProviderResult> {
    static void m9439a(FusedLocationProviderResult fusedLocationProviderResult, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8464a(parcel, 1, fusedLocationProviderResult.m9389a(), i, false);
        zzb.m8460a(parcel, 1000, fusedLocationProviderResult.m9390b());
        zzb.m8456a(parcel, a);
    }

    public FusedLocationProviderResult m9440a(Parcel parcel) {
        int b = zza.m8438b(parcel);
        int i = 0;
        Status status = null;
        while (parcel.dataPosition() < b) {
            int a = zza.m8432a(parcel);
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    status = (Status) zza.m8434a(parcel, a, Status.CREATOR);
                    break;
                case 1000:
                    i = zza.m8445f(parcel, a);
                    break;
                default:
                    zza.m8439b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new FusedLocationProviderResult(i, status);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public FusedLocationProviderResult[] m9441a(int i) {
        return new FusedLocationProviderResult[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m9440a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m9441a(i);
    }
}
