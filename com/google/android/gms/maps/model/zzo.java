package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzo implements Creator<TileOverlayOptions> {
    static void m10469a(TileOverlayOptions tileOverlayOptions, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8460a(parcel, 1, tileOverlayOptions.m10160a());
        zzb.m8463a(parcel, 2, tileOverlayOptions.m10161b(), false);
        zzb.m8469a(parcel, 3, tileOverlayOptions.m10163d());
        zzb.m8459a(parcel, 4, tileOverlayOptions.m10162c());
        zzb.m8469a(parcel, 5, tileOverlayOptions.m10164e());
        zzb.m8456a(parcel, a);
    }

    public TileOverlayOptions m10470a(Parcel parcel) {
        boolean z = false;
        int b = zza.m8438b(parcel);
        IBinder iBinder = null;
        float f = 0.0f;
        boolean z2 = true;
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = zza.m8432a(parcel);
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    i = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    iBinder = zza.m8451l(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    z = zza.m8442c(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    f = zza.m8448i(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    z2 = zza.m8442c(parcel, a);
                    break;
                default:
                    zza.m8439b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new TileOverlayOptions(i, iBinder, z, f, z2);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public TileOverlayOptions[] m10471a(int i) {
        return new TileOverlayOptions[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m10470a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m10471a(i);
    }
}
