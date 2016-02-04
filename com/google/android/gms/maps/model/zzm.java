package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzm implements Creator<StreetViewPanoramaOrientation> {
    static void m10463a(StreetViewPanoramaOrientation streetViewPanoramaOrientation, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8460a(parcel, 1, streetViewPanoramaOrientation.m10157a());
        zzb.m8459a(parcel, 2, streetViewPanoramaOrientation.f6455a);
        zzb.m8459a(parcel, 3, streetViewPanoramaOrientation.f6456b);
        zzb.m8456a(parcel, a);
    }

    public StreetViewPanoramaOrientation m10464a(Parcel parcel) {
        float f = 0.0f;
        int b = zza.m8438b(parcel);
        int i = 0;
        float f2 = 0.0f;
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
                    f = zza.m8448i(parcel, a);
                    break;
                default:
                    zza.m8439b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new StreetViewPanoramaOrientation(i, f2, f);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public StreetViewPanoramaOrientation[] m10465a(int i) {
        return new StreetViewPanoramaOrientation[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m10464a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m10465a(i);
    }
}
