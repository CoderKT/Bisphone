package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzg implements Creator<RecordConsentRequest> {
    static void m10516a(RecordConsentRequest recordConsentRequest, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8460a(parcel, 1, recordConsentRequest.f6490a);
        zzb.m8464a(parcel, 2, recordConsentRequest.m10482a(), i, false);
        zzb.m8471a(parcel, 3, recordConsentRequest.m10483b(), i, false);
        zzb.m8466a(parcel, 4, recordConsentRequest.m10484c(), false);
        zzb.m8456a(parcel, a);
    }

    public RecordConsentRequest m10517a(Parcel parcel) {
        String str = null;
        int b = zza.m8438b(parcel);
        int i = 0;
        Scope[] scopeArr = null;
        Account account = null;
        while (parcel.dataPosition() < b) {
            Scope[] scopeArr2;
            Account account2;
            int f;
            String str2;
            int a = zza.m8432a(parcel);
            String str3;
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    str3 = str;
                    scopeArr2 = scopeArr;
                    account2 = account;
                    f = zza.m8445f(parcel, a);
                    str2 = str3;
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    f = i;
                    Scope[] scopeArr3 = scopeArr;
                    account2 = (Account) zza.m8434a(parcel, a, Account.CREATOR);
                    str2 = str;
                    scopeArr2 = scopeArr3;
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    account2 = account;
                    f = i;
                    str3 = str;
                    scopeArr2 = (Scope[]) zza.m8440b(parcel, a, Scope.CREATOR);
                    str2 = str3;
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    str2 = zza.m8450k(parcel, a);
                    scopeArr2 = scopeArr;
                    account2 = account;
                    f = i;
                    break;
                default:
                    zza.m8439b(parcel, a);
                    str2 = str;
                    scopeArr2 = scopeArr;
                    account2 = account;
                    f = i;
                    break;
            }
            i = f;
            account = account2;
            scopeArr = scopeArr2;
            str = str2;
        }
        if (parcel.dataPosition() == b) {
            return new RecordConsentRequest(i, account, scopeArr, str);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public RecordConsentRequest[] m10518a(int i) {
        return new RecordConsentRequest[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m10517a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m10518a(i);
    }
}
