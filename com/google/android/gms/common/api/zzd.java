package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzd implements Creator<Status> {
    static void m8410a(Status status, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8460a(parcel, 1, status.m8403f());
        zzb.m8460a(parcel, 1000, status.m8401d());
        zzb.m8466a(parcel, 2, status.m8400c(), false);
        zzb.m8464a(parcel, 3, status.m8399b(), i, false);
        zzb.m8456a(parcel, a);
    }

    public Status m8411a(Parcel parcel) {
        PendingIntent pendingIntent = null;
        int i = 0;
        int b = zza.m8438b(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = zza.m8432a(parcel);
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    i = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    str = zza.m8450k(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    pendingIntent = (PendingIntent) zza.m8434a(parcel, a, PendingIntent.CREATOR);
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
            return new Status(i2, i, str, pendingIntent);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public Status[] m8412a(int i) {
        return new Status[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m8411a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m8412a(i);
    }
}
