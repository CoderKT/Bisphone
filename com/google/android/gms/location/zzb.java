package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.util.List;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzb implements Creator<GestureRequest> {
    static void m9539a(GestureRequest gestureRequest, Parcel parcel, int i) {
        int a = com.google.android.gms.common.internal.safeparcel.zzb.m8455a(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.m8467a(parcel, 1, gestureRequest.m9350b(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.m8460a(parcel, 1000, gestureRequest.m9349a());
        com.google.android.gms.common.internal.safeparcel.zzb.m8456a(parcel, a);
    }

    public GestureRequest m9540a(Parcel parcel) {
        int b = zza.m8438b(parcel);
        int i = 0;
        List list = null;
        while (parcel.dataPosition() < b) {
            int a = zza.m8432a(parcel);
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    list = zza.m8454o(parcel, a);
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
            return new GestureRequest(i, list);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public GestureRequest[] m9541a(int i) {
        return new GestureRequest[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m9540a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m9541a(i);
    }
}
