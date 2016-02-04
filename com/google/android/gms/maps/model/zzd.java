package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzd implements Creator<LatLngBounds> {
    static void m10436a(LatLngBounds latLngBounds, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8460a(parcel, 1, latLngBounds.m10110a());
        zzb.m8464a(parcel, 2, latLngBounds.f6403a, i, false);
        zzb.m8464a(parcel, 3, latLngBounds.f6404b, i, false);
        zzb.m8456a(parcel, a);
    }

    public LatLngBounds m10437a(Parcel parcel) {
        LatLng latLng = null;
        int b = zza.m8438b(parcel);
        int i = 0;
        LatLng latLng2 = null;
        while (parcel.dataPosition() < b) {
            int f;
            LatLng latLng3;
            int a = zza.m8432a(parcel);
            LatLng latLng4;
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    latLng4 = latLng;
                    latLng = latLng2;
                    f = zza.m8445f(parcel, a);
                    latLng3 = latLng4;
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    f = i;
                    latLng4 = (LatLng) zza.m8434a(parcel, a, LatLng.CREATOR);
                    latLng3 = latLng;
                    latLng = latLng4;
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    latLng3 = (LatLng) zza.m8434a(parcel, a, LatLng.CREATOR);
                    latLng = latLng2;
                    f = i;
                    break;
                default:
                    zza.m8439b(parcel, a);
                    latLng3 = latLng;
                    latLng = latLng2;
                    f = i;
                    break;
            }
            i = f;
            latLng2 = latLng;
            latLng = latLng3;
        }
        if (parcel.dataPosition() == b) {
            return new LatLngBounds(i, latLng2, latLng);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public LatLngBounds[] m10438a(int i) {
        return new LatLngBounds[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m10437a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m10438a(i);
    }
}
