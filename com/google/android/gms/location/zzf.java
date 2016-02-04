package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzf implements Creator<LocationSettingsRequest> {
    static void m9548a(LocationSettingsRequest locationSettingsRequest, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8475b(parcel, 1, locationSettingsRequest.m9373b(), false);
        zzb.m8460a(parcel, 1000, locationSettingsRequest.m9372a());
        zzb.m8469a(parcel, 2, locationSettingsRequest.m9374c());
        zzb.m8469a(parcel, 3, locationSettingsRequest.m9375d());
        zzb.m8469a(parcel, 4, locationSettingsRequest.m9376e());
        zzb.m8456a(parcel, a);
    }

    public LocationSettingsRequest m9549a(Parcel parcel) {
        boolean z = false;
        int b = zza.m8438b(parcel);
        List list = null;
        boolean z2 = false;
        boolean z3 = false;
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = zza.m8432a(parcel);
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    list = zza.m8441c(parcel, a, LocationRequest.CREATOR);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    z3 = zza.m8442c(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    z2 = zza.m8442c(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    z = zza.m8442c(parcel, a);
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
            return new LocationSettingsRequest(i, list, z3, z2, z);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public LocationSettingsRequest[] m9550a(int i) {
        return new LocationSettingsRequest[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m9549a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m9550a(i);
    }
}
