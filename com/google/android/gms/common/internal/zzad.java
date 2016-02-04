package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzad implements Creator<ValidateAccountRequest> {
    static void m8529a(ValidateAccountRequest validateAccountRequest, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8460a(parcel, 1, validateAccountRequest.f5713a);
        zzb.m8460a(parcel, 2, validateAccountRequest.m8427a());
        zzb.m8463a(parcel, 3, validateAccountRequest.f5714b, false);
        zzb.m8471a(parcel, 4, validateAccountRequest.m8428b(), i, false);
        zzb.m8462a(parcel, 5, validateAccountRequest.m8430d(), false);
        zzb.m8466a(parcel, 6, validateAccountRequest.m8429c(), false);
        zzb.m8456a(parcel, a);
    }

    public ValidateAccountRequest m8530a(Parcel parcel) {
        int i = 0;
        String str = null;
        int b = zza.m8438b(parcel);
        Bundle bundle = null;
        Scope[] scopeArr = null;
        IBinder iBinder = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = zza.m8432a(parcel);
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    i2 = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    i = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    iBinder = zza.m8451l(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    scopeArr = (Scope[]) zza.m8440b(parcel, a, Scope.CREATOR);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    bundle = zza.m8452m(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    str = zza.m8450k(parcel, a);
                    break;
                default:
                    zza.m8439b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new ValidateAccountRequest(i2, i, iBinder, scopeArr, bundle, str);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public ValidateAccountRequest[] m8531a(int i) {
        return new ValidateAccountRequest[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m8530a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m8531a(i);
    }
}
