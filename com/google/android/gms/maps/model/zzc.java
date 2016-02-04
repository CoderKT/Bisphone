package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzc implements Creator<GroundOverlayOptions> {
    static void m10433a(GroundOverlayOptions groundOverlayOptions, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8460a(parcel, 1, groundOverlayOptions.m10098b());
        zzb.m8463a(parcel, 2, groundOverlayOptions.m10097a(), false);
        zzb.m8464a(parcel, 3, groundOverlayOptions.m10099c(), i, false);
        zzb.m8459a(parcel, 4, groundOverlayOptions.m10100d());
        zzb.m8459a(parcel, 5, groundOverlayOptions.m10101e());
        zzb.m8464a(parcel, 6, groundOverlayOptions.m10102f(), i, false);
        zzb.m8459a(parcel, 7, groundOverlayOptions.m10103g());
        zzb.m8459a(parcel, 8, groundOverlayOptions.m10104h());
        zzb.m8469a(parcel, 9, groundOverlayOptions.m10108l());
        zzb.m8459a(parcel, 10, groundOverlayOptions.m10105i());
        zzb.m8459a(parcel, 11, groundOverlayOptions.m10106j());
        zzb.m8459a(parcel, 12, groundOverlayOptions.m10107k());
        zzb.m8456a(parcel, a);
    }

    public GroundOverlayOptions m10434a(Parcel parcel) {
        int b = zza.m8438b(parcel);
        int i = 0;
        IBinder iBinder = null;
        LatLng latLng = null;
        float f = 0.0f;
        float f2 = 0.0f;
        LatLngBounds latLngBounds = null;
        float f3 = 0.0f;
        float f4 = 0.0f;
        boolean z = false;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        while (parcel.dataPosition() < b) {
            int a = zza.m8432a(parcel);
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    i = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    iBinder = zza.m8451l(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    latLng = (LatLng) zza.m8434a(parcel, a, LatLng.CREATOR);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    f = zza.m8448i(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    f2 = zza.m8448i(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    latLngBounds = (LatLngBounds) zza.m8434a(parcel, a, LatLngBounds.CREATOR);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    f3 = zza.m8448i(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                    f4 = zza.m8448i(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                    z = zza.m8442c(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                    f5 = zza.m8448i(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_stackFromBottom /*11*/:
                    f6 = zza.m8448i(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_scrollingCache /*12*/:
                    f7 = zza.m8448i(parcel, a);
                    break;
                default:
                    zza.m8439b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new GroundOverlayOptions(i, iBinder, latLng, f, f2, latLngBounds, f3, f4, z, f5, f6, f7);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public GroundOverlayOptions[] m10435a(int i) {
        return new GroundOverlayOptions[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m10434a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m10435a(i);
    }
}
