package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;
import java.util.List;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzh implements Creator<PolygonOptions> {
    static void m10448a(PolygonOptions polygonOptions, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8460a(parcel, 1, polygonOptions.m10135a());
        zzb.m8475b(parcel, 2, polygonOptions.m10137c(), false);
        zzb.m8477c(parcel, 3, polygonOptions.m10136b(), false);
        zzb.m8459a(parcel, 4, polygonOptions.m10138d());
        zzb.m8460a(parcel, 5, polygonOptions.m10139e());
        zzb.m8460a(parcel, 6, polygonOptions.m10140f());
        zzb.m8459a(parcel, 7, polygonOptions.m10141g());
        zzb.m8469a(parcel, 8, polygonOptions.m10142h());
        zzb.m8469a(parcel, 9, polygonOptions.m10143i());
        zzb.m8456a(parcel, a);
    }

    public PolygonOptions m10449a(Parcel parcel) {
        float f = 0.0f;
        boolean z = false;
        int b = zza.m8438b(parcel);
        List list = null;
        List arrayList = new ArrayList();
        boolean z2 = false;
        int i = 0;
        int i2 = 0;
        float f2 = 0.0f;
        int i3 = 0;
        while (parcel.dataPosition() < b) {
            int a = zza.m8432a(parcel);
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    i3 = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    list = zza.m8441c(parcel, a, LatLng.CREATOR);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    zza.m8437a(parcel, a, arrayList, getClass().getClassLoader());
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    f2 = zza.m8448i(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    i2 = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    i = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    f = zza.m8448i(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                    z2 = zza.m8442c(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                    z = zza.m8442c(parcel, a);
                    break;
                default:
                    zza.m8439b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new PolygonOptions(i3, list, arrayList, f2, i2, i, f, z2, z);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public PolygonOptions[] m10450a(int i) {
        return new PolygonOptions[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m10449a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m10450a(i);
    }
}
