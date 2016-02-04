package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;
import se.emilsjolander.stickylistheaders.C1128R;

public class ActivityRecognitionResultCreator implements Creator<ActivityRecognitionResult> {
    static void m9332a(ActivityRecognitionResult activityRecognitionResult, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8475b(parcel, 1, activityRecognitionResult.f6170a, false);
        zzb.m8460a(parcel, 1000, activityRecognitionResult.m9331a());
        zzb.m8461a(parcel, 2, activityRecognitionResult.f6171b);
        zzb.m8461a(parcel, 3, activityRecognitionResult.f6172c);
        zzb.m8460a(parcel, 4, activityRecognitionResult.f6173d);
        zzb.m8456a(parcel, a);
    }

    public ActivityRecognitionResult m9333a(Parcel parcel) {
        long j = 0;
        int i = 0;
        int b = zza.m8438b(parcel);
        List list = null;
        long j2 = 0;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = zza.m8432a(parcel);
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    list = zza.m8441c(parcel, a, DetectedActivity.CREATOR);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    j2 = zza.m8447h(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    j = zza.m8447h(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    i = zza.m8445f(parcel, a);
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
            return new ActivityRecognitionResult(i2, list, j2, j, i);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public ActivityRecognitionResult[] m9334a(int i) {
        return new ActivityRecognitionResult[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m9333a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m9334a(i);
    }
}
