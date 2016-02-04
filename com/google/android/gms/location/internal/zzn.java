package com.google.android.gms.location.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzn implements Creator<LocationRequestUpdateData> {
    static void m9530a(LocationRequestUpdateData locationRequestUpdateData, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8460a(parcel, 1, locationRequestUpdateData.f6242a);
        zzb.m8460a(parcel, 1000, locationRequestUpdateData.m9397a());
        zzb.m8464a(parcel, 2, locationRequestUpdateData.f6243b, i, false);
        zzb.m8463a(parcel, 3, locationRequestUpdateData.m9398b(), false);
        zzb.m8464a(parcel, 4, locationRequestUpdateData.f6245d, i, false);
        zzb.m8463a(parcel, 5, locationRequestUpdateData.m9399c(), false);
        zzb.m8463a(parcel, 6, locationRequestUpdateData.m9400d(), false);
        zzb.m8456a(parcel, a);
    }

    public LocationRequestUpdateData m9531a(Parcel parcel) {
        IBinder iBinder = null;
        int b = zza.m8438b(parcel);
        int i = 0;
        int i2 = 1;
        IBinder iBinder2 = null;
        PendingIntent pendingIntent = null;
        IBinder iBinder3 = null;
        LocationRequestInternal locationRequestInternal = null;
        while (parcel.dataPosition() < b) {
            int a = zza.m8432a(parcel);
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    i2 = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    locationRequestInternal = (LocationRequestInternal) zza.m8434a(parcel, a, LocationRequestInternal.CREATOR);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    iBinder3 = zza.m8451l(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    pendingIntent = (PendingIntent) zza.m8434a(parcel, a, PendingIntent.CREATOR);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    iBinder2 = zza.m8451l(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    iBinder = zza.m8451l(parcel, a);
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
            return new LocationRequestUpdateData(i, i2, locationRequestInternal, iBinder3, pendingIntent, iBinder2, iBinder);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public LocationRequestUpdateData[] m9532a(int i) {
        return new LocationRequestUpdateData[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m9531a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m9532a(i);
    }
}
