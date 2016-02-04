package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import se.emilsjolander.stickylistheaders.C1128R;

public class zza implements Creator<ConnectionEvent> {
    static void m8750a(ConnectionEvent connectionEvent, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8460a(parcel, 1, connectionEvent.f5813a);
        zzb.m8461a(parcel, 2, connectionEvent.m8738a());
        zzb.m8466a(parcel, 4, connectionEvent.m8740c(), false);
        zzb.m8466a(parcel, 5, connectionEvent.m8741d(), false);
        zzb.m8466a(parcel, 6, connectionEvent.m8742e(), false);
        zzb.m8466a(parcel, 7, connectionEvent.m8743f(), false);
        zzb.m8466a(parcel, 8, connectionEvent.m8744g(), false);
        zzb.m8461a(parcel, 10, connectionEvent.m8748k());
        zzb.m8461a(parcel, 11, connectionEvent.m8747j());
        zzb.m8460a(parcel, 12, connectionEvent.m8739b());
        zzb.m8466a(parcel, 13, connectionEvent.m8745h(), false);
        zzb.m8456a(parcel, a);
    }

    public ConnectionEvent m8751a(Parcel parcel) {
        int b = com.google.android.gms.common.internal.safeparcel.zza.m8438b(parcel);
        int i = 0;
        long j = 0;
        int i2 = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        long j2 = 0;
        long j3 = 0;
        while (parcel.dataPosition() < b) {
            int a = com.google.android.gms.common.internal.safeparcel.zza.m8432a(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    i = com.google.android.gms.common.internal.safeparcel.zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    j = com.google.android.gms.common.internal.safeparcel.zza.m8447h(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    str = com.google.android.gms.common.internal.safeparcel.zza.m8450k(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    str2 = com.google.android.gms.common.internal.safeparcel.zza.m8450k(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    str3 = com.google.android.gms.common.internal.safeparcel.zza.m8450k(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    str4 = com.google.android.gms.common.internal.safeparcel.zza.m8450k(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                    str5 = com.google.android.gms.common.internal.safeparcel.zza.m8450k(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                    j2 = com.google.android.gms.common.internal.safeparcel.zza.m8447h(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_stackFromBottom /*11*/:
                    j3 = com.google.android.gms.common.internal.safeparcel.zza.m8447h(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_scrollingCache /*12*/:
                    i2 = com.google.android.gms.common.internal.safeparcel.zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_transcriptMode /*13*/:
                    str6 = com.google.android.gms.common.internal.safeparcel.zza.m8450k(parcel, a);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zza.m8439b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new ConnectionEvent(i, j, i2, str, str2, str3, str4, str5, str6, j2, j3);
        }
        throw new com.google.android.gms.common.internal.safeparcel.zza.zza("Overread allowed size end=" + b, parcel);
    }

    public ConnectionEvent[] m8752a(int i) {
        return new ConnectionEvent[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m8751a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m8752a(i);
    }
}
