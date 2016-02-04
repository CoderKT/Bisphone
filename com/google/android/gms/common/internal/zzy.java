package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzy implements Creator<ResolveAccountRequest> {
    static void m8728a(ResolveAccountRequest resolveAccountRequest, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8460a(parcel, 1, resolveAccountRequest.f5705a);
        zzb.m8464a(parcel, 2, resolveAccountRequest.m8421a(), i, false);
        zzb.m8460a(parcel, 3, resolveAccountRequest.m8422b());
        zzb.m8456a(parcel, a);
    }

    public ResolveAccountRequest m8729a(Parcel parcel) {
        int i = 0;
        int b = zza.m8438b(parcel);
        Account account = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            Account account2;
            int f;
            int a = zza.m8432a(parcel);
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    int i3 = i;
                    account2 = account;
                    f = zza.m8445f(parcel, a);
                    a = i3;
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    f = i2;
                    Account account3 = (Account) zza.m8434a(parcel, a, Account.CREATOR);
                    a = i;
                    account2 = account3;
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    a = zza.m8445f(parcel, a);
                    account2 = account;
                    f = i2;
                    break;
                default:
                    zza.m8439b(parcel, a);
                    a = i;
                    account2 = account;
                    f = i2;
                    break;
            }
            i2 = f;
            account = account2;
            i = a;
        }
        if (parcel.dataPosition() == b) {
            return new ResolveAccountRequest(i2, account, i);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public ResolveAccountRequest[] m8730a(int i) {
        return new ResolveAccountRequest[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m8729a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m8730a(i);
    }
}
