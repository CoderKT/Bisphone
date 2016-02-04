package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzn implements Creator<Tile> {
    static void m10466a(Tile tile, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8460a(parcel, 1, tile.m10158a());
        zzb.m8460a(parcel, 2, tile.f6458a);
        zzb.m8460a(parcel, 3, tile.f6459b);
        zzb.m8470a(parcel, 4, tile.f6460c, false);
        zzb.m8456a(parcel, a);
    }

    public Tile m10467a(Parcel parcel) {
        int i = 0;
        int b = zza.m8438b(parcel);
        byte[] bArr = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < b) {
            int a = zza.m8432a(parcel);
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    i3 = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    i2 = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    i = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    bArr = zza.m8453n(parcel, a);
                    break;
                default:
                    zza.m8439b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new Tile(i3, i2, i, bArr);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public Tile[] m10468a(int i) {
        return new Tile[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m10467a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m10468a(i);
    }
}
