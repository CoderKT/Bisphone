package com.google.android.gms.location.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzc implements Creator<ClientIdentity> {
    static void m9427a(ClientIdentity clientIdentity, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8460a(parcel, 1, clientIdentity.f6227a);
        zzb.m8460a(parcel, 1000, clientIdentity.m9388a());
        zzb.m8466a(parcel, 2, clientIdentity.f6228b, false);
        zzb.m8456a(parcel, a);
    }

    public ClientIdentity m9428a(Parcel parcel) {
        int i = 0;
        int b = zza.m8438b(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = zza.m8432a(parcel);
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    i = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    str = zza.m8450k(parcel, a);
                    break;
                case 1000:
                    i2 = zza.m8445f(parcel, a);
                    break;
                default:
                    zza.m8439b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new ClientIdentity(i2, i, str);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public ClientIdentity[] m9429a(int i) {
        return new ClientIdentity[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m9428a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m9429a(i);
    }
}
