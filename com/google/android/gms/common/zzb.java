package com.google.android.gms.common;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzb implements Creator<ConnectionResult> {
    static void m8769a(ConnectionResult connectionResult, Parcel parcel, int i) {
        int a = com.google.android.gms.common.internal.safeparcel.zzb.m8455a(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.m8460a(parcel, 1, connectionResult.f5635b);
        com.google.android.gms.common.internal.safeparcel.zzb.m8460a(parcel, 2, connectionResult.m8304c());
        com.google.android.gms.common.internal.safeparcel.zzb.m8464a(parcel, 3, connectionResult.m8305d(), i, false);
        com.google.android.gms.common.internal.safeparcel.zzb.m8466a(parcel, 4, connectionResult.m8306e(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.m8456a(parcel, a);
    }

    public ConnectionResult m8770a(Parcel parcel) {
        String str = null;
        int i = 0;
        int b = zza.m8438b(parcel);
        PendingIntent pendingIntent = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            PendingIntent pendingIntent2;
            int i3;
            String str2;
            int a = zza.m8432a(parcel);
            String str3;
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    str3 = str;
                    pendingIntent2 = pendingIntent;
                    i3 = i;
                    i = zza.m8445f(parcel, a);
                    str2 = str3;
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    i = i2;
                    PendingIntent pendingIntent3 = pendingIntent;
                    i3 = zza.m8445f(parcel, a);
                    str2 = str;
                    pendingIntent2 = pendingIntent3;
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    i3 = i;
                    i = i2;
                    str3 = str;
                    pendingIntent2 = (PendingIntent) zza.m8434a(parcel, a, PendingIntent.CREATOR);
                    str2 = str3;
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    str2 = zza.m8450k(parcel, a);
                    pendingIntent2 = pendingIntent;
                    i3 = i;
                    i = i2;
                    break;
                default:
                    zza.m8439b(parcel, a);
                    str2 = str;
                    pendingIntent2 = pendingIntent;
                    i3 = i;
                    i = i2;
                    break;
            }
            i2 = i;
            i = i3;
            pendingIntent = pendingIntent2;
            str = str2;
        }
        if (parcel.dataPosition() == b) {
            return new ConnectionResult(i2, i, pendingIntent, str);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public ConnectionResult[] m8771a(int i) {
        return new ConnectionResult[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m8770a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m8771a(i);
    }
}
