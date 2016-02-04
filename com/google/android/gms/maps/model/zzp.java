package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzp implements Creator<VisibleRegion> {
    static void m10472a(VisibleRegion visibleRegion, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8460a(parcel, 1, visibleRegion.m10165a());
        zzb.m8464a(parcel, 2, visibleRegion.f6471a, i, false);
        zzb.m8464a(parcel, 3, visibleRegion.f6472b, i, false);
        zzb.m8464a(parcel, 4, visibleRegion.f6473c, i, false);
        zzb.m8464a(parcel, 5, visibleRegion.f6474d, i, false);
        zzb.m8464a(parcel, 6, visibleRegion.f6475e, i, false);
        zzb.m8456a(parcel, a);
    }

    public VisibleRegion m10473a(Parcel parcel) {
        LatLngBounds latLngBounds = null;
        int b = zza.m8438b(parcel);
        int i = 0;
        LatLng latLng = null;
        LatLng latLng2 = null;
        LatLng latLng3 = null;
        LatLng latLng4 = null;
        while (parcel.dataPosition() < b) {
            int a = zza.m8432a(parcel);
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    i = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    latLng4 = (LatLng) zza.m8434a(parcel, a, LatLng.CREATOR);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    latLng3 = (LatLng) zza.m8434a(parcel, a, LatLng.CREATOR);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    latLng2 = (LatLng) zza.m8434a(parcel, a, LatLng.CREATOR);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    latLng = (LatLng) zza.m8434a(parcel, a, LatLng.CREATOR);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    latLngBounds = (LatLngBounds) zza.m8434a(parcel, a, LatLngBounds.CREATOR);
                    break;
                default:
                    zza.m8439b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new VisibleRegion(i, latLng4, latLng3, latLng2, latLng, latLngBounds);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public VisibleRegion[] m10474a(int i) {
        return new VisibleRegion[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m10473a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m10474a(i);
    }
}
