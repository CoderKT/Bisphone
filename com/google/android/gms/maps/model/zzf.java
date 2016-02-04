package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzf implements Creator<MarkerOptions> {
    static void m10442a(MarkerOptions markerOptions, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8460a(parcel, 1, markerOptions.m10117a());
        zzb.m8464a(parcel, 2, markerOptions.m10122c(), i, false);
        zzb.m8466a(parcel, 3, markerOptions.m10123d(), false);
        zzb.m8466a(parcel, 4, markerOptions.m10124e(), false);
        zzb.m8463a(parcel, 5, markerOptions.m10121b(), false);
        zzb.m8459a(parcel, 6, markerOptions.m10125f());
        zzb.m8459a(parcel, 7, markerOptions.m10126g());
        zzb.m8469a(parcel, 8, markerOptions.m10127h());
        zzb.m8469a(parcel, 9, markerOptions.m10128i());
        zzb.m8469a(parcel, 10, markerOptions.m10129j());
        zzb.m8459a(parcel, 11, markerOptions.m10130k());
        zzb.m8459a(parcel, 12, markerOptions.m10131l());
        zzb.m8459a(parcel, 13, markerOptions.m10132m());
        zzb.m8459a(parcel, 14, markerOptions.m10133n());
        zzb.m8456a(parcel, a);
    }

    public MarkerOptions m10443a(Parcel parcel) {
        int b = zza.m8438b(parcel);
        int i = 0;
        LatLng latLng = null;
        String str = null;
        String str2 = null;
        IBinder iBinder = null;
        float f = 0.0f;
        float f2 = 0.0f;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        float f3 = 0.0f;
        float f4 = 0.5f;
        float f5 = 0.0f;
        float f6 = 1.0f;
        while (parcel.dataPosition() < b) {
            int a = zza.m8432a(parcel);
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    i = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    latLng = (LatLng) zza.m8434a(parcel, a, LatLng.CREATOR);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    str = zza.m8450k(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    str2 = zza.m8450k(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    iBinder = zza.m8451l(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    f = zza.m8448i(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    f2 = zza.m8448i(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                    z = zza.m8442c(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                    z2 = zza.m8442c(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                    z3 = zza.m8442c(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_stackFromBottom /*11*/:
                    f3 = zza.m8448i(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_scrollingCache /*12*/:
                    f4 = zza.m8448i(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_transcriptMode /*13*/:
                    f5 = zza.m8448i(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_cacheColorHint /*14*/:
                    f6 = zza.m8448i(parcel, a);
                    break;
                default:
                    zza.m8439b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new MarkerOptions(i, latLng, str, str2, iBinder, f, f2, z, z2, z3, f3, f4, f5, f6);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public MarkerOptions[] m10444a(int i) {
        return new MarkerOptions[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m10443a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m10444a(i);
    }
}
