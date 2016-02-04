package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.location.internal.ParcelableGeofence;
import java.util.List;
import se.emilsjolander.stickylistheaders.C1128R;

public class zza implements Creator<GeofencingRequest> {
    static void m9536a(GeofencingRequest geofencingRequest, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8475b(parcel, 1, geofencingRequest.m9347b(), false);
        zzb.m8460a(parcel, 1000, geofencingRequest.m9346a());
        zzb.m8460a(parcel, 2, geofencingRequest.m9348c());
        zzb.m8456a(parcel, a);
    }

    public GeofencingRequest m9537a(Parcel parcel) {
        int i = 0;
        int b = com.google.android.gms.common.internal.safeparcel.zza.m8438b(parcel);
        List list = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = com.google.android.gms.common.internal.safeparcel.zza.m8432a(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    list = com.google.android.gms.common.internal.safeparcel.zza.m8441c(parcel, a, ParcelableGeofence.CREATOR);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    i = com.google.android.gms.common.internal.safeparcel.zza.m8445f(parcel, a);
                    break;
                case 1000:
                    i2 = com.google.android.gms.common.internal.safeparcel.zza.m8445f(parcel, a);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zza.m8439b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new GeofencingRequest(i2, list, i);
        }
        throw new com.google.android.gms.common.internal.safeparcel.zza.zza("Overread allowed size end=" + b, parcel);
    }

    public GeofencingRequest[] m9538a(int i) {
        return new GeofencingRequest[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m9537a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m9538a(i);
    }
}
