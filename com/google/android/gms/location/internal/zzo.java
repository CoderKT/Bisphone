package com.google.android.gms.location.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzo implements Creator<ParcelableGeofence> {
    static void m9533a(ParcelableGeofence parcelableGeofence, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8466a(parcel, 1, parcelableGeofence.m9411f(), false);
        zzb.m8460a(parcel, 1000, parcelableGeofence.m9406a());
        zzb.m8461a(parcel, 2, parcelableGeofence.m9412g());
        zzb.m8468a(parcel, 3, parcelableGeofence.m9407b());
        zzb.m8458a(parcel, 4, parcelableGeofence.m9408c());
        zzb.m8458a(parcel, 5, parcelableGeofence.m9409d());
        zzb.m8459a(parcel, 6, parcelableGeofence.m9410e());
        zzb.m8460a(parcel, 7, parcelableGeofence.m9413h());
        zzb.m8460a(parcel, 8, parcelableGeofence.m9414i());
        zzb.m8460a(parcel, 9, parcelableGeofence.m9415j());
        zzb.m8456a(parcel, a);
    }

    public ParcelableGeofence m9534a(Parcel parcel) {
        int b = zza.m8438b(parcel);
        int i = 0;
        String str = null;
        int i2 = 0;
        short s = (short) 0;
        double d = 0.0d;
        double d2 = 0.0d;
        float f = 0.0f;
        long j = 0;
        int i3 = 0;
        int i4 = -1;
        while (parcel.dataPosition() < b) {
            int a = zza.m8432a(parcel);
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    str = zza.m8450k(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    j = zza.m8447h(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    s = zza.m8444e(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    d = zza.m8449j(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    d2 = zza.m8449j(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    f = zza.m8448i(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    i2 = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                    i3 = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                    i4 = zza.m8445f(parcel, a);
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
            return new ParcelableGeofence(i, str, i2, s, d, d2, f, j, i3, i4);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public ParcelableGeofence[] m9535a(int i) {
        return new ParcelableGeofence[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m9534a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m9535a(i);
    }
}
