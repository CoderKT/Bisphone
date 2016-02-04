package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzc implements Creator<Scope> {
    static void m8407a(Scope scope, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8460a(parcel, 1, scope.f5680a);
        zzb.m8466a(parcel, 2, scope.m8396a(), false);
        zzb.m8456a(parcel, a);
    }

    public Scope m8408a(Parcel parcel) {
        int b = zza.m8438b(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < b) {
            int a = zza.m8432a(parcel);
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    i = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    str = zza.m8450k(parcel, a);
                    break;
                default:
                    zza.m8439b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new Scope(i, str);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public Scope[] m8409a(int i) {
        return new Scope[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m8408a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m8409a(i);
    }
}
