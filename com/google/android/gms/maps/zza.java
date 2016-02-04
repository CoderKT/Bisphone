package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.maps.model.CameraPosition;
import se.emilsjolander.stickylistheaders.C1128R;

public class zza implements Creator<GoogleMapOptions> {
    static void m10475a(GoogleMapOptions googleMapOptions, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8460a(parcel, 1, googleMapOptions.m9613a());
        zzb.m8457a(parcel, 2, googleMapOptions.m9617b());
        zzb.m8457a(parcel, 3, googleMapOptions.m9619c());
        zzb.m8460a(parcel, 4, googleMapOptions.m9638m());
        zzb.m8464a(parcel, 5, googleMapOptions.m9639n(), i, false);
        zzb.m8457a(parcel, 6, googleMapOptions.m9621d());
        zzb.m8457a(parcel, 7, googleMapOptions.m9623e());
        zzb.m8457a(parcel, 8, googleMapOptions.m9625f());
        zzb.m8457a(parcel, 9, googleMapOptions.m9627g());
        zzb.m8457a(parcel, 10, googleMapOptions.m9629h());
        zzb.m8457a(parcel, 11, googleMapOptions.m9631i());
        zzb.m8457a(parcel, 12, googleMapOptions.m9633j());
        zzb.m8457a(parcel, 14, googleMapOptions.m9635k());
        zzb.m8457a(parcel, 15, googleMapOptions.m9637l());
        zzb.m8456a(parcel, a);
    }

    public GoogleMapOptions m10476a(Parcel parcel) {
        int b = com.google.android.gms.common.internal.safeparcel.zza.m8438b(parcel);
        int i = 0;
        byte b2 = (byte) -1;
        byte b3 = (byte) -1;
        int i2 = 0;
        CameraPosition cameraPosition = null;
        byte b4 = (byte) -1;
        byte b5 = (byte) -1;
        byte b6 = (byte) -1;
        byte b7 = (byte) -1;
        byte b8 = (byte) -1;
        byte b9 = (byte) -1;
        byte b10 = (byte) -1;
        byte b11 = (byte) -1;
        byte b12 = (byte) -1;
        while (parcel.dataPosition() < b) {
            int a = com.google.android.gms.common.internal.safeparcel.zza.m8432a(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    i = com.google.android.gms.common.internal.safeparcel.zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    b2 = com.google.android.gms.common.internal.safeparcel.zza.m8443d(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    b3 = com.google.android.gms.common.internal.safeparcel.zza.m8443d(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    i2 = com.google.android.gms.common.internal.safeparcel.zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    cameraPosition = (CameraPosition) com.google.android.gms.common.internal.safeparcel.zza.m8434a(parcel, a, CameraPosition.CREATOR);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    b4 = com.google.android.gms.common.internal.safeparcel.zza.m8443d(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    b5 = com.google.android.gms.common.internal.safeparcel.zza.m8443d(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                    b6 = com.google.android.gms.common.internal.safeparcel.zza.m8443d(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                    b7 = com.google.android.gms.common.internal.safeparcel.zza.m8443d(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                    b8 = com.google.android.gms.common.internal.safeparcel.zza.m8443d(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_stackFromBottom /*11*/:
                    b9 = com.google.android.gms.common.internal.safeparcel.zza.m8443d(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_scrollingCache /*12*/:
                    b10 = com.google.android.gms.common.internal.safeparcel.zza.m8443d(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_cacheColorHint /*14*/:
                    b11 = com.google.android.gms.common.internal.safeparcel.zza.m8443d(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_divider /*15*/:
                    b12 = com.google.android.gms.common.internal.safeparcel.zza.m8443d(parcel, a);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zza.m8439b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new GoogleMapOptions(i, b2, b3, i2, cameraPosition, b4, b5, b6, b7, b8, b9, b10, b11, b12);
        }
        throw new com.google.android.gms.common.internal.safeparcel.zza.zza("Overread allowed size end=" + b, parcel);
    }

    public GoogleMapOptions[] m10477a(int i) {
        return new GoogleMapOptions[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m10476a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m10477a(i);
    }
}
