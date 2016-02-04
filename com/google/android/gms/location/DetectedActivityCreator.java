package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import se.emilsjolander.stickylistheaders.C1128R;

public class DetectedActivityCreator implements Creator<DetectedActivity> {
    static void m9341a(DetectedActivity detectedActivity, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8460a(parcel, 1, detectedActivity.f6176b);
        zzb.m8460a(parcel, 1000, detectedActivity.m9340c());
        zzb.m8460a(parcel, 2, detectedActivity.f6177c);
        zzb.m8456a(parcel, a);
    }

    public DetectedActivity m9342a(Parcel parcel) {
        int i = 0;
        int b = zza.m8438b(parcel);
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < b) {
            int a = zza.m8432a(parcel);
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    i2 = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    i = zza.m8445f(parcel, a);
                    break;
                case 1000:
                    i3 = zza.m8445f(parcel, a);
                    break;
                default:
                    zza.m8439b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new DetectedActivity(i3, i2, i);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public DetectedActivity[] m9343a(int i) {
        return new DetectedActivity[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m9342a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m9343a(i);
    }
}
