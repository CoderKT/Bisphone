package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzb implements Creator<CircleOptions> {
    static void m10430a(CircleOptions circleOptions, Parcel parcel, int i) {
        int a = com.google.android.gms.common.internal.safeparcel.zzb.m8455a(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.m8460a(parcel, 1, circleOptions.m10089a());
        com.google.android.gms.common.internal.safeparcel.zzb.m8464a(parcel, 2, circleOptions.m10090b(), i, false);
        com.google.android.gms.common.internal.safeparcel.zzb.m8458a(parcel, 3, circleOptions.m10091c());
        com.google.android.gms.common.internal.safeparcel.zzb.m8459a(parcel, 4, circleOptions.m10092d());
        com.google.android.gms.common.internal.safeparcel.zzb.m8460a(parcel, 5, circleOptions.m10093e());
        com.google.android.gms.common.internal.safeparcel.zzb.m8460a(parcel, 6, circleOptions.m10094f());
        com.google.android.gms.common.internal.safeparcel.zzb.m8459a(parcel, 7, circleOptions.m10095g());
        com.google.android.gms.common.internal.safeparcel.zzb.m8469a(parcel, 8, circleOptions.m10096h());
        com.google.android.gms.common.internal.safeparcel.zzb.m8456a(parcel, a);
    }

    public CircleOptions m10431a(Parcel parcel) {
        float f = 0.0f;
        boolean z = false;
        int b = zza.m8438b(parcel);
        LatLng latLng = null;
        double d = 0.0d;
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
                    latLng = (LatLng) zza.m8434a(parcel, a, LatLng.CREATOR);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    d = zza.m8449j(parcel, a);
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
                    z = zza.m8442c(parcel, a);
                    break;
                default:
                    zza.m8439b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new CircleOptions(i3, latLng, d, f2, i2, i, f, z);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public CircleOptions[] m10432a(int i) {
        return new CircleOptions[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m10431a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m10432a(i);
    }
}
