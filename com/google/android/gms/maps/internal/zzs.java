package com.google.android.gms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import se.emilsjolander.stickylistheaders.C1128R;

public interface zzs extends IInterface {

    public abstract class zza extends Binder implements zzs {

        class zza implements zzs {
            private IBinder f6362a;

            zza(IBinder iBinder) {
                this.f6362a = iBinder;
            }

            public void m10055a(StreetViewPanoramaLocation streetViewPanoramaLocation) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IOnStreetViewPanoramaChangeListener");
                    if (streetViewPanoramaLocation != null) {
                        obtain.writeInt(1);
                        streetViewPanoramaLocation.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f6362a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.f6362a;
            }
        }

        public static zzs m10056a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IOnStreetViewPanoramaChangeListener");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzs)) ? new zza(iBinder) : (zzs) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IOnStreetViewPanoramaChangeListener");
                    m10054a(parcel.readInt() != 0 ? StreetViewPanoramaLocation.CREATOR.m10461a(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.maps.internal.IOnStreetViewPanoramaChangeListener");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m10054a(StreetViewPanoramaLocation streetViewPanoramaLocation);
}
