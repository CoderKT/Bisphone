package com.google.android.gms.signin.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.common.api.Scope;
import java.util.List;
import se.emilsjolander.stickylistheaders.C1128R;

public interface zzd extends IInterface {

    public abstract class zza extends Binder implements zzd {
        public zza() {
            attachInterface(this, "com.google.android.gms.signin.internal.IOfflineAccessCallbacks");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    parcel.enforceInterface("com.google.android.gms.signin.internal.IOfflineAccessCallbacks");
                    m10492a(parcel.readString(), parcel.createTypedArrayList(Scope.CREATOR), com.google.android.gms.signin.internal.zzf.zza.m10515a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    parcel.enforceInterface("com.google.android.gms.signin.internal.IOfflineAccessCallbacks");
                    m10491a(parcel.readString(), parcel.readString(), com.google.android.gms.signin.internal.zzf.zza.m10515a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.signin.internal.IOfflineAccessCallbacks");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m10491a(String str, String str2, zzf com_google_android_gms_signin_internal_zzf);

    void m10492a(String str, List<Scope> list, zzf com_google_android_gms_signin_internal_zzf);
}
