package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzl implements Creator<StreetViewPanoramaLocation> {
    static void m10460a(StreetViewPanoramaLocation streetViewPanoramaLocation, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8460a(parcel, 1, streetViewPanoramaLocation.m10153a());
        zzb.m8471a(parcel, 2, streetViewPanoramaLocation.f6449a, i, false);
        zzb.m8464a(parcel, 3, streetViewPanoramaLocation.f6450b, i, false);
        zzb.m8466a(parcel, 4, streetViewPanoramaLocation.f6451c, false);
        zzb.m8456a(parcel, a);
    }

    public StreetViewPanoramaLocation m10461a(Parcel parcel) {
        String str = null;
        int b = zza.m8438b(parcel);
        int i = 0;
        LatLng latLng = null;
        StreetViewPanoramaLink[] streetViewPanoramaLinkArr = null;
        while (parcel.dataPosition() < b) {
            LatLng latLng2;
            StreetViewPanoramaLink[] streetViewPanoramaLinkArr2;
            int f;
            String str2;
            int a = zza.m8432a(parcel);
            String str3;
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    str3 = str;
                    latLng2 = latLng;
                    streetViewPanoramaLinkArr2 = streetViewPanoramaLinkArr;
                    f = zza.m8445f(parcel, a);
                    str2 = str3;
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    f = i;
                    LatLng latLng3 = latLng;
                    streetViewPanoramaLinkArr2 = (StreetViewPanoramaLink[]) zza.m8440b(parcel, a, StreetViewPanoramaLink.CREATOR);
                    str2 = str;
                    latLng2 = latLng3;
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    streetViewPanoramaLinkArr2 = streetViewPanoramaLinkArr;
                    f = i;
                    str3 = str;
                    latLng2 = (LatLng) zza.m8434a(parcel, a, LatLng.CREATOR);
                    str2 = str3;
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    str2 = zza.m8450k(parcel, a);
                    latLng2 = latLng;
                    streetViewPanoramaLinkArr2 = streetViewPanoramaLinkArr;
                    f = i;
                    break;
                default:
                    zza.m8439b(parcel, a);
                    str2 = str;
                    latLng2 = latLng;
                    streetViewPanoramaLinkArr2 = streetViewPanoramaLinkArr;
                    f = i;
                    break;
            }
            i = f;
            streetViewPanoramaLinkArr = streetViewPanoramaLinkArr2;
            latLng = latLng2;
            str = str2;
        }
        if (parcel.dataPosition() == b) {
            return new StreetViewPanoramaLocation(i, streetViewPanoramaLinkArr, latLng, str);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public StreetViewPanoramaLocation[] m10462a(int i) {
        return new StreetViewPanoramaLocation[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m10461a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m10462a(i);
    }
}
