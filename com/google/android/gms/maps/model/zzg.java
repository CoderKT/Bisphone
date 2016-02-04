package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzg implements Creator<PointOfInterest> {
    static void m10445a(PointOfInterest pointOfInterest, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8460a(parcel, 1, pointOfInterest.m10134a());
        zzb.m8464a(parcel, 2, pointOfInterest.f6421a, i, false);
        zzb.m8466a(parcel, 3, pointOfInterest.f6422b, false);
        zzb.m8466a(parcel, 4, pointOfInterest.f6423c, false);
        zzb.m8456a(parcel, a);
    }

    public PointOfInterest m10446a(Parcel parcel) {
        String str = null;
        int b = zza.m8438b(parcel);
        int i = 0;
        String str2 = null;
        LatLng latLng = null;
        while (parcel.dataPosition() < b) {
            LatLng latLng2;
            int f;
            String str3;
            int a = zza.m8432a(parcel);
            String str4;
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    str4 = str;
                    str = str2;
                    latLng2 = latLng;
                    f = zza.m8445f(parcel, a);
                    str3 = str4;
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    f = i;
                    str4 = str2;
                    latLng2 = (LatLng) zza.m8434a(parcel, a, LatLng.CREATOR);
                    str3 = str;
                    str = str4;
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    latLng2 = latLng;
                    f = i;
                    str4 = str;
                    str = zza.m8450k(parcel, a);
                    str3 = str4;
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    str3 = zza.m8450k(parcel, a);
                    str = str2;
                    latLng2 = latLng;
                    f = i;
                    break;
                default:
                    zza.m8439b(parcel, a);
                    str3 = str;
                    str = str2;
                    latLng2 = latLng;
                    f = i;
                    break;
            }
            i = f;
            latLng = latLng2;
            str2 = str;
            str = str3;
        }
        if (parcel.dataPosition() == b) {
            return new PointOfInterest(i, latLng, str2, str);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public PointOfInterest[] m10447a(int i) {
        return new PointOfInterest[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m10446a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m10447a(i);
    }
}
