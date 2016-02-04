package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import se.emilsjolander.stickylistheaders.C1128R;

public class LocationAvailabilityCreator implements Creator<LocationAvailability> {
    static void m9353a(LocationAvailability locationAvailability, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8460a(parcel, 1, locationAvailability.f6188a);
        zzb.m8460a(parcel, 1000, locationAvailability.m9352b());
        zzb.m8460a(parcel, 2, locationAvailability.f6189b);
        zzb.m8461a(parcel, 3, locationAvailability.f6190c);
        zzb.m8460a(parcel, 4, locationAvailability.f6191d);
        zzb.m8456a(parcel, a);
    }

    public LocationAvailability m9354a(Parcel parcel) {
        int i = 1;
        int b = zza.m8438b(parcel);
        int i2 = 0;
        int i3 = 1000;
        long j = 0;
        int i4 = 1;
        while (parcel.dataPosition() < b) {
            int a = zza.m8432a(parcel);
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    i4 = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    i = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    j = zza.m8447h(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    i3 = zza.m8445f(parcel, a);
                    break;
                case 1000:
                    i2 = zza.m8445f(parcel, a);
                    break;
                default:
                    zza.m8439b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new LocationAvailability(i2, i3, i4, i, j);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public LocationAvailability[] m9355a(int i) {
        return new LocationAvailability[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m9354a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m9355a(i);
    }
}
