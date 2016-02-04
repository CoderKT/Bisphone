package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzk implements Creator<StreetViewPanoramaLink> {
    static void m10457a(StreetViewPanoramaLink streetViewPanoramaLink, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8460a(parcel, 1, streetViewPanoramaLink.m10152a());
        zzb.m8466a(parcel, 2, streetViewPanoramaLink.f6446a, false);
        zzb.m8459a(parcel, 3, streetViewPanoramaLink.f6447b);
        zzb.m8456a(parcel, a);
    }

    public StreetViewPanoramaLink m10458a(Parcel parcel) {
        int b = zza.m8438b(parcel);
        int i = 0;
        String str = null;
        float f = 0.0f;
        while (parcel.dataPosition() < b) {
            int a = zza.m8432a(parcel);
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    i = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    str = zza.m8450k(parcel, a);
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
            return new StreetViewPanoramaLink(i, str, f);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public StreetViewPanoramaLink[] m10459a(int i) {
        return new StreetViewPanoramaLink[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m10458a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m10459a(i);
    }
}
