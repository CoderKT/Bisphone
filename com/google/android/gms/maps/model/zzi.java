package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzi implements Creator<PolylineOptions> {
    static void m10451a(PolylineOptions polylineOptions, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8460a(parcel, 1, polylineOptions.m10144a());
        zzb.m8475b(parcel, 2, polylineOptions.m10145b(), false);
        zzb.m8459a(parcel, 3, polylineOptions.m10146c());
        zzb.m8460a(parcel, 4, polylineOptions.m10147d());
        zzb.m8459a(parcel, 5, polylineOptions.m10148e());
        zzb.m8469a(parcel, 6, polylineOptions.m10149f());
        zzb.m8469a(parcel, 7, polylineOptions.m10150g());
        zzb.m8456a(parcel, a);
    }

    public PolylineOptions m10452a(Parcel parcel) {
        float f = 0.0f;
        boolean z = false;
        int b = zza.m8438b(parcel);
        List list = null;
        boolean z2 = false;
        int i = 0;
        float f2 = 0.0f;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = zza.m8432a(parcel);
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    i2 = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    list = zza.m8441c(parcel, a, LatLng.CREATOR);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    f2 = zza.m8448i(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    i = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    f = zza.m8448i(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    z2 = zza.m8442c(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    z = zza.m8442c(parcel, a);
                    break;
                default:
                    zza.m8439b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new PolylineOptions(i2, list, f2, i, f, z2, z);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public PolylineOptions[] m10453a(int i) {
        return new PolylineOptions[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m10452a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m10453a(i);
    }
}
