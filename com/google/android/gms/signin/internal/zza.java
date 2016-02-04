package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import se.emilsjolander.stickylistheaders.C1128R;

public class zza implements Creator<AuthAccountResult> {
    static void m10485a(AuthAccountResult authAccountResult, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8460a(parcel, 1, authAccountResult.f6486a);
        zzb.m8456a(parcel, a);
    }

    public AuthAccountResult m10486a(Parcel parcel) {
        int b = com.google.android.gms.common.internal.safeparcel.zza.m8438b(parcel);
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = com.google.android.gms.common.internal.safeparcel.zza.m8432a(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    i = com.google.android.gms.common.internal.safeparcel.zza.m8445f(parcel, a);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zza.m8439b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new AuthAccountResult(i);
        }
        throw new com.google.android.gms.common.internal.safeparcel.zza.zza("Overread allowed size end=" + b, parcel);
    }

    public AuthAccountResult[] m10487a(int i) {
        return new AuthAccountResult[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m10486a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m10487a(i);
    }
}
