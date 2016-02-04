package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import se.emilsjolander.stickylistheaders.C1128R;

public class zze implements Creator<LatLng> {
    static void m10439a(LatLng latLng, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8460a(parcel, 1, latLng.m10109a());
        zzb.m8458a(parcel, 2, latLng.f6400a);
        zzb.m8458a(parcel, 3, latLng.f6401b);
        zzb.m8456a(parcel, a);
    }

    public LatLng m10440a(Parcel parcel) {
        double d = 0.0d;
        int b = zza.m8438b(parcel);
        int i = 0;
        double d2 = 0.0d;
        while (parcel.dataPosition() < b) {
            int a = zza.m8432a(parcel);
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    i = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    d2 = zza.m8449j(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    d = zza.m8449j(parcel, a);
                    break;
                default:
                    zza.m8439b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new LatLng(i, d2, d);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public LatLng[] m10441a(int i) {
        return new LatLng[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m10440a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m10441a(i);
    }
}
