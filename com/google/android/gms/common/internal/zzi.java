package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzi implements Creator<GetServiceRequest> {
    static void m8549a(GetServiceRequest getServiceRequest, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8460a(parcel, 1, getServiceRequest.f5697a);
        zzb.m8460a(parcel, 2, getServiceRequest.f5698b);
        zzb.m8460a(parcel, 3, getServiceRequest.f5699c);
        zzb.m8466a(parcel, 4, getServiceRequest.f5700d, false);
        zzb.m8463a(parcel, 5, getServiceRequest.f5701e, false);
        zzb.m8471a(parcel, 6, getServiceRequest.f5702f, i, false);
        zzb.m8462a(parcel, 7, getServiceRequest.f5703g, false);
        zzb.m8464a(parcel, 8, getServiceRequest.f5704h, i, false);
        zzb.m8456a(parcel, a);
    }

    public GetServiceRequest m8550a(Parcel parcel) {
        int i = 0;
        Account account = null;
        int b = zza.m8438b(parcel);
        Bundle bundle = null;
        Scope[] scopeArr = null;
        IBinder iBinder = null;
        String str = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < b) {
            int a = zza.m8432a(parcel);
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    i3 = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    i2 = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    i = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    str = zza.m8450k(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    iBinder = zza.m8451l(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    scopeArr = (Scope[]) zza.m8440b(parcel, a, Scope.CREATOR);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    bundle = zza.m8452m(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                    account = (Account) zza.m8434a(parcel, a, Account.CREATOR);
                    break;
                default:
                    zza.m8439b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new GetServiceRequest(i3, i2, i, str, iBinder, scopeArr, bundle, account);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public GetServiceRequest[] m8551a(int i) {
        return new GetServiceRequest[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m8550a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m8551a(i);
    }
}
