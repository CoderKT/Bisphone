package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzh implements Creator<LocationSettingsStates> {
    static void m9554a(LocationSettingsStates locationSettingsStates, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8469a(parcel, 1, locationSettingsStates.m9381b());
        zzb.m8460a(parcel, 1000, locationSettingsStates.m9380a());
        zzb.m8469a(parcel, 2, locationSettingsStates.m9383d());
        zzb.m8469a(parcel, 3, locationSettingsStates.m9385f());
        zzb.m8469a(parcel, 4, locationSettingsStates.m9382c());
        zzb.m8469a(parcel, 5, locationSettingsStates.m9384e());
        zzb.m8469a(parcel, 6, locationSettingsStates.m9386g());
        zzb.m8469a(parcel, 7, locationSettingsStates.m9387h());
        zzb.m8456a(parcel, a);
    }

    public LocationSettingsStates m9555a(Parcel parcel) {
        boolean z = false;
        int b = zza.m8438b(parcel);
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = zza.m8432a(parcel);
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    z7 = zza.m8442c(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    z6 = zza.m8442c(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    z5 = zza.m8442c(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    z4 = zza.m8442c(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    z3 = zza.m8442c(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    z2 = zza.m8442c(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
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
            return new LocationSettingsStates(i, z7, z6, z5, z4, z3, z2, z);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public LocationSettingsStates[] m9556a(int i) {
        return new LocationSettingsStates[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m9555a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m9556a(i);
    }
}
