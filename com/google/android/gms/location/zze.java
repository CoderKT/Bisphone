package com.google.android.gms.location;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;
import se.emilsjolander.stickylistheaders.C1128R;

public class zze implements Creator<LocationResult> {
    static void m9545a(LocationResult locationResult, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8475b(parcel, 1, locationResult.m9366a(), false);
        zzb.m8460a(parcel, 1000, locationResult.m9367b());
        zzb.m8456a(parcel, a);
    }

    public LocationResult m9546a(Parcel parcel) {
        int b = zza.m8438b(parcel);
        int i = 0;
        List list = LocationResult.f6202a;
        while (parcel.dataPosition() < b) {
            int a = zza.m8432a(parcel);
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    list = zza.m8441c(parcel, a, Location.CREATOR);
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
            return new LocationResult(i, list);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public LocationResult[] m9547a(int i) {
        return new LocationResult[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m9546a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m9547a(i);
    }
}
