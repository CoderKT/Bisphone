package com.google.android.gms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import se.emilsjolander.stickylistheaders.C1128R;

public interface zzl extends IInterface {

    public abstract class zza extends Binder implements zzl {

        class zza implements zzl {
            private IBinder f6355a;

            zza(IBinder iBinder) {
                this.f6355a = iBinder;
            }

            public void m10039a(IGoogleMapDelegate iGoogleMapDelegate) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IOnMapReadyCallback");
                    obtain.writeStrongBinder(iGoogleMapDelegate != null ? iGoogleMapDelegate.asBinder() : null);
                    this.f6355a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.f6355a;
            }
        }

        public zza() {
            attachInterface(this, "com.google.android.gms.maps.internal.IOnMapReadyCallback");
        }

        public static zzl m9641a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IOnMapReadyCallback");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzl)) ? new zza(iBinder) : (zzl) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IOnMapReadyCallback");
                    m9640a(com.google.android.gms.maps.internal.IGoogleMapDelegate.zza.m9806a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.maps.internal.IOnMapReadyCallback");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m9640a(IGoogleMapDelegate iGoogleMapDelegate);
}
