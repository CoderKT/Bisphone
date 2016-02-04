package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzc implements Creator<CheckServerAuthResult> {
    static void m10488a(CheckServerAuthResult checkServerAuthResult, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8460a(parcel, 1, checkServerAuthResult.f6487a);
        zzb.m8469a(parcel, 2, checkServerAuthResult.f6488b);
        zzb.m8475b(parcel, 3, checkServerAuthResult.f6489c, false);
        zzb.m8456a(parcel, a);
    }

    public CheckServerAuthResult m10489a(Parcel parcel) {
        boolean z = false;
        int b = zza.m8438b(parcel);
        List list = null;
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = zza.m8432a(parcel);
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    i = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    z = zza.m8442c(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    list = zza.m8441c(parcel, a, Scope.CREATOR);
                    break;
                default:
                    zza.m8439b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new CheckServerAuthResult(i, z, list);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public CheckServerAuthResult[] m10490a(int i) {
        return new CheckServerAuthResult[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m10489a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m10490a(i);
    }
}
