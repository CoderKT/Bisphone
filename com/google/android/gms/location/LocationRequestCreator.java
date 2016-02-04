package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import app.C0110R;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import se.emilsjolander.stickylistheaders.C1128R;

public class LocationRequestCreator implements Creator<LocationRequest> {
    static void m9363a(LocationRequest locationRequest, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8460a(parcel, 1, locationRequest.f6193a);
        zzb.m8460a(parcel, 1000, locationRequest.m9362b());
        zzb.m8461a(parcel, 2, locationRequest.f6194b);
        zzb.m8461a(parcel, 3, locationRequest.f6195c);
        zzb.m8469a(parcel, 4, locationRequest.f6196d);
        zzb.m8461a(parcel, 5, locationRequest.f6197e);
        zzb.m8460a(parcel, 6, locationRequest.f6198f);
        zzb.m8459a(parcel, 7, locationRequest.f6199g);
        zzb.m8461a(parcel, 8, locationRequest.f6200h);
        zzb.m8456a(parcel, a);
    }

    public LocationRequest m9364a(Parcel parcel) {
        int b = zza.m8438b(parcel);
        int i = 0;
        int i2 = C0110R.styleable.Theme_checkboxStyle;
        long j = 3600000;
        long j2 = 600000;
        boolean z = false;
        long j3 = Long.MAX_VALUE;
        int i3 = Integer.MAX_VALUE;
        float f = 0.0f;
        long j4 = 0;
        while (parcel.dataPosition() < b) {
            int a = zza.m8432a(parcel);
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    i2 = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    j = zza.m8447h(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    j2 = zza.m8447h(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    z = zza.m8442c(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    j3 = zza.m8447h(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    i3 = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    f = zza.m8448i(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                    j4 = zza.m8447h(parcel, a);
                    break;
                case 1000:
                    i = zza.m8445f(parcel, a);
                    break;
                default:
                    zza.m8439b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new LocationRequest(i, i2, j, j2, z, j3, i3, f, j4);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public LocationRequest[] m9365a(int i) {
        return new LocationRequest[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m9364a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m9365a(i);
    }
}
