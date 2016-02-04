package com.google.android.gms.location.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.location.LocationRequest;
import java.util.List;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzm implements Creator<LocationRequestInternal> {
    static void m9527a(LocationRequestInternal locationRequestInternal, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8464a(parcel, 1, locationRequestInternal.f6234b, i, false);
        zzb.m8460a(parcel, 1000, locationRequestInternal.m9393a());
        zzb.m8469a(parcel, 2, locationRequestInternal.f6235c);
        zzb.m8469a(parcel, 3, locationRequestInternal.f6236d);
        zzb.m8469a(parcel, 4, locationRequestInternal.f6237e);
        zzb.m8475b(parcel, 5, locationRequestInternal.f6238f, false);
        zzb.m8466a(parcel, 6, locationRequestInternal.f6239g, false);
        zzb.m8469a(parcel, 7, locationRequestInternal.f6240h);
        zzb.m8456a(parcel, a);
    }

    public LocationRequestInternal m9528a(Parcel parcel) {
        String str = null;
        boolean z = true;
        boolean z2 = false;
        int b = zza.m8438b(parcel);
        List list = LocationRequestInternal.f6233a;
        boolean z3 = true;
        boolean z4 = false;
        LocationRequest locationRequest = null;
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = zza.m8432a(parcel);
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    locationRequest = (LocationRequest) zza.m8434a(parcel, a, LocationRequest.CREATOR);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    z4 = zza.m8442c(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    z3 = zza.m8442c(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    z = zza.m8442c(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    list = zza.m8441c(parcel, a, ClientIdentity.CREATOR);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    str = zza.m8450k(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    z2 = zza.m8442c(parcel, a);
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
            return new LocationRequestInternal(i, locationRequest, z4, z3, z, list, str, z2);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public LocationRequestInternal[] m9529a(int i) {
        return new LocationRequestInternal[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m9528a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m9529a(i);
    }
}
