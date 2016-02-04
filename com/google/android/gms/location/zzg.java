package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzg implements Creator<LocationSettingsResult> {
    static void m9551a(LocationSettingsResult locationSettingsResult, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8464a(parcel, 1, locationSettingsResult.m9377a(), i, false);
        zzb.m8460a(parcel, 1000, locationSettingsResult.m9378b());
        zzb.m8464a(parcel, 2, locationSettingsResult.m9379c(), i, false);
        zzb.m8456a(parcel, a);
    }

    public LocationSettingsResult m9552a(Parcel parcel) {
        LocationSettingsStates locationSettingsStates = null;
        int b = zza.m8438b(parcel);
        int i = 0;
        Status status = null;
        while (parcel.dataPosition() < b) {
            int i2;
            LocationSettingsStates locationSettingsStates2;
            Status status2;
            int a = zza.m8432a(parcel);
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    i2 = i;
                    Status status3 = (Status) zza.m8434a(parcel, a, Status.CREATOR);
                    locationSettingsStates2 = locationSettingsStates;
                    status2 = status3;
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    locationSettingsStates2 = (LocationSettingsStates) zza.m8434a(parcel, a, LocationSettingsStates.CREATOR);
                    status2 = status;
                    i2 = i;
                    break;
                case 1000:
                    LocationSettingsStates locationSettingsStates3 = locationSettingsStates;
                    status2 = status;
                    i2 = zza.m8445f(parcel, a);
                    locationSettingsStates2 = locationSettingsStates3;
                    break;
                default:
                    zza.m8439b(parcel, a);
                    locationSettingsStates2 = locationSettingsStates;
                    status2 = status;
                    i2 = i;
                    break;
            }
            i = i2;
            status = status2;
            locationSettingsStates = locationSettingsStates2;
        }
        if (parcel.dataPosition() == b) {
            return new LocationSettingsResult(i, status, locationSettingsStates);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public LocationSettingsResult[] m9553a(int i) {
        return new LocationSettingsResult[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m9552a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m9553a(i);
    }
}
