package com.google.android.gms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.zzd;
import se.emilsjolander.stickylistheaders.C1128R;

public interface zzp extends IInterface {

    public abstract class zza extends Binder implements zzp {

        class zza implements zzp {
            private IBinder f6359a;

            zza(IBinder iBinder) {
                this.f6359a = iBinder;
            }

            public void m10046a(zzd com_google_android_gms_dynamic_zzd) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IOnMyLocationChangeListener");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    this.f6359a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.f6359a;
            }
        }

        public static zzp m10047a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IOnMyLocationChangeListener");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzp)) ? new zza(iBinder) : (zzp) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IOnMyLocationChangeListener");
                    m10045a(com.google.android.gms.dynamic.zzd.zza.m9022a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.maps.internal.IOnMyLocationChangeListener");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m10045a(zzd com_google_android_gms_dynamic_zzd);
}
