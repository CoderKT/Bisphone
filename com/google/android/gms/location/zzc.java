package com.google.android.gms.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import se.emilsjolander.stickylistheaders.C1128R;

public interface zzc extends IInterface {

    public abstract class zza extends Binder implements zzc {

        class zza implements zzc {
            private IBinder f6280a;

            zza(IBinder iBinder) {
                this.f6280a = iBinder;
            }

            public void m9542a(LocationAvailability locationAvailability) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.ILocationCallback");
                    if (locationAvailability != null) {
                        obtain.writeInt(1);
                        locationAvailability.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f6280a.transact(2, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void m9543a(LocationResult locationResult) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.ILocationCallback");
                    if (locationResult != null) {
                        obtain.writeInt(1);
                        locationResult.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f6280a.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.f6280a;
            }
        }

        public static zzc m9510a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.ILocationCallback");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzc)) ? new zza(iBinder) : (zzc) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            LocationAvailability locationAvailability = null;
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    LocationResult locationResult;
                    parcel.enforceInterface("com.google.android.gms.location.ILocationCallback");
                    if (parcel.readInt() != 0) {
                        locationResult = (LocationResult) LocationResult.CREATOR.createFromParcel(parcel);
                    }
                    m9509a(locationResult);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    parcel.enforceInterface("com.google.android.gms.location.ILocationCallback");
                    if (parcel.readInt() != 0) {
                        locationAvailability = LocationAvailability.CREATOR.m9354a(parcel);
                    }
                    m9508a(locationAvailability);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.location.ILocationCallback");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m9508a(LocationAvailability locationAvailability);

    void m9509a(LocationResult locationResult);
}
