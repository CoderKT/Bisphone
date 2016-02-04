package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import se.emilsjolander.stickylistheaders.C1128R;

public class zza implements Creator<CameraPosition> {
    static void m10427a(CameraPosition cameraPosition, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8460a(parcel, 1, cameraPosition.m10088a());
        zzb.m8464a(parcel, 2, cameraPosition.f6375a, i, false);
        zzb.m8459a(parcel, 3, cameraPosition.f6376b);
        zzb.m8459a(parcel, 4, cameraPosition.f6377c);
        zzb.m8459a(parcel, 5, cameraPosition.f6378d);
        zzb.m8456a(parcel, a);
    }

    public CameraPosition m10428a(Parcel parcel) {
        float f = 0.0f;
        int b = com.google.android.gms.common.internal.safeparcel.zza.m8438b(parcel);
        int i = 0;
        LatLng latLng = null;
        float f2 = 0.0f;
        float f3 = 0.0f;
        while (parcel.dataPosition() < b) {
            int a = com.google.android.gms.common.internal.safeparcel.zza.m8432a(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    i = com.google.android.gms.common.internal.safeparcel.zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    latLng = (LatLng) com.google.android.gms.common.internal.safeparcel.zza.m8434a(parcel, a, LatLng.CREATOR);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    f3 = com.google.android.gms.common.internal.safeparcel.zza.m8448i(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    f2 = com.google.android.gms.common.internal.safeparcel.zza.m8448i(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    f = com.google.android.gms.common.internal.safeparcel.zza.m8448i(parcel, a);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zza.m8439b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new CameraPosition(i, latLng, f3, f2, f);
        }
        throw new com.google.android.gms.common.internal.safeparcel.zza.zza("Overread allowed size end=" + b, parcel);
    }

    public CameraPosition[] m10429a(int i) {
        return new CameraPosition[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m10428a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m10429a(i);
    }
}
