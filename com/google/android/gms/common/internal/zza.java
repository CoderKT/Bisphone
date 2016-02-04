package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class zza extends com.google.android.gms.common.internal.zzp.zza {
    int f5719a;
    private Account f5720b;
    private Context f5721c;

    public static Account m8480a(zzp com_google_android_gms_common_internal_zzp) {
        Account account = null;
        if (com_google_android_gms_common_internal_zzp != null) {
            long clearCallingIdentity = Binder.clearCallingIdentity();
            try {
                account = com_google_android_gms_common_internal_zzp.m8478a();
            } catch (RemoteException e) {
                Log.w("AccountAccessor", "Remote account accessor probably died");
            } finally {
                Binder.restoreCallingIdentity(clearCallingIdentity);
            }
        }
        return account;
    }

    public Account m8481a() {
        int callingUid = Binder.getCallingUid();
        if (callingUid == this.f5719a) {
            return this.f5720b;
        }
        if (GooglePlayServicesUtil.m8323a(this.f5721c, callingUid)) {
            this.f5719a = callingUid;
            return this.f5720b;
        }
        throw new SecurityException("Caller is not GooglePlayServices");
    }

    public boolean equals(Object obj) {
        return this == obj ? true : !(obj instanceof zza) ? false : this.f5720b.equals(((zza) obj).f5720b);
    }
}
