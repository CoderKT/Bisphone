package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzj implements Creator<StreetViewPanoramaCamera> {
    static void m10454a(StreetViewPanoramaCamera streetViewPanoramaCamera, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8460a(parcel, 1, streetViewPanoramaCamera.m10151a());
        zzb.m8459a(parcel, 2, streetViewPanoramaCamera.f6441a);
        zzb.m8459a(parcel, 3, streetViewPanoramaCamera.f6442b);
        zzb.m8459a(parcel, 4, streetViewPanoramaCamera.f6443c);
        zzb.m8456a(parcel, a);
    }

    public StreetViewPanoramaCamera m10455a(Parcel parcel) {
        float f = 0.0f;
        int b = zza.m8438b(parcel);
        float f2 = 0.0f;
        int i = 0;
        float f3 = 0.0f;
        while (parcel.dataPosition() < b) {
            int a = zza.m8432a(parcel);
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    i = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    f2 = zza.m8448i(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    f3 = zza.m8448i(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    f = zza.m8448i(parcel, a);
                    break;
                default:
                    zza.m8439b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new StreetViewPanoramaCamera(i, f2, f3, f);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public StreetViewPanoramaCamera[] m10456a(int i) {
        return new StreetViewPanoramaCamera[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m10455a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m10456a(i);
    }
}
