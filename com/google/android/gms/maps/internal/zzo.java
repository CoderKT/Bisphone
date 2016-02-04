package com.google.android.gms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import se.emilsjolander.stickylistheaders.C1128R;

public interface zzo extends IInterface {

    public abstract class zza extends Binder implements zzo {

        class zza implements zzo {
            private IBinder f6358a;

            zza(IBinder iBinder) {
                this.f6358a = iBinder;
            }

            public boolean m10044a() {
                boolean z = true;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IOnMyLocationButtonClickListener");
                    this.f6358a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.f6358a;
            }
        }

        public zza() {
            attachInterface(this, "com.google.android.gms.maps.internal.IOnMyLocationButtonClickListener");
        }

        public static zzo m9578a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IOnMyLocationButtonClickListener");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzo)) ? new zza(iBinder) : (zzo) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IOnMyLocationButtonClickListener");
                    boolean a = m9577a();
                    parcel2.writeNoException();
                    parcel2.writeInt(a ? 1 : 0);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.maps.internal.IOnMyLocationButtonClickListener");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    boolean m9577a();
}
