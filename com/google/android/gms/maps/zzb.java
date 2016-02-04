package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzb implements Creator<StreetViewPanoramaOptions> {
    static void m10478a(StreetViewPanoramaOptions streetViewPanoramaOptions, Parcel parcel, int i) {
        int a = com.google.android.gms.common.internal.safeparcel.zzb.m8455a(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.m8460a(parcel, 1, streetViewPanoramaOptions.m9661a());
        com.google.android.gms.common.internal.safeparcel.zzb.m8464a(parcel, 2, streetViewPanoramaOptions.m9667g(), i, false);
        com.google.android.gms.common.internal.safeparcel.zzb.m8466a(parcel, 3, streetViewPanoramaOptions.m9670j(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.m8464a(parcel, 4, streetViewPanoramaOptions.m9668h(), i, false);
        com.google.android.gms.common.internal.safeparcel.zzb.m8465a(parcel, 5, streetViewPanoramaOptions.m9669i(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.m8457a(parcel, 6, streetViewPanoramaOptions.m9662b());
        com.google.android.gms.common.internal.safeparcel.zzb.m8457a(parcel, 7, streetViewPanoramaOptions.m9663c());
        com.google.android.gms.common.internal.safeparcel.zzb.m8457a(parcel, 8, streetViewPanoramaOptions.m9664d());
        com.google.android.gms.common.internal.safeparcel.zzb.m8457a(parcel, 9, streetViewPanoramaOptions.m9665e());
        com.google.android.gms.common.internal.safeparcel.zzb.m8457a(parcel, 10, streetViewPanoramaOptions.m9666f());
        com.google.android.gms.common.internal.safeparcel.zzb.m8456a(parcel, a);
    }

    public StreetViewPanoramaOptions m10479a(Parcel parcel) {
        Integer num = null;
        byte b = (byte) 0;
        int b2 = zza.m8438b(parcel);
        byte b3 = (byte) 0;
        byte b4 = (byte) 0;
        byte b5 = (byte) 0;
        byte b6 = (byte) 0;
        LatLng latLng = null;
        String str = null;
        StreetViewPanoramaCamera streetViewPanoramaCamera = null;
        int i = 0;
        while (parcel.dataPosition() < b2) {
            int a = zza.m8432a(parcel);
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    i = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    streetViewPanoramaCamera = (StreetViewPanoramaCamera) zza.m8434a(parcel, a, StreetViewPanoramaCamera.CREATOR);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    str = zza.m8450k(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    latLng = (LatLng) zza.m8434a(parcel, a, LatLng.CREATOR);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    num = zza.m8446g(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    b6 = zza.m8443d(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    b5 = zza.m8443d(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                    b4 = zza.m8443d(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                    b3 = zza.m8443d(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                    b = zza.m8443d(parcel, a);
                    break;
                default:
                    zza.m8439b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b2) {
            return new StreetViewPanoramaOptions(i, streetViewPanoramaCamera, str, latLng, num, b6, b5, b4, b3, b);
        }
        throw new zza.zza("Overread allowed size end=" + b2, parcel);
    }

    public StreetViewPanoramaOptions[] m10480a(int i) {
        return new StreetViewPanoramaOptions[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m10479a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m10480a(i);
    }
}
