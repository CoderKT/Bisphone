package com.google.android.gms.location.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.location.LocationSettingsResult;
import se.emilsjolander.stickylistheaders.C1128R;

public interface zzj extends IInterface {

    public abstract class zza extends Binder implements zzj {

        class zza implements zzj {
            private IBinder f6269a;

            zza(IBinder iBinder) {
                this.f6269a = iBinder;
            }

            public void m9506a(LocationSettingsResult locationSettingsResult) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.ISettingsCallbacks");
                    if (locationSettingsResult != null) {
                        obtain.writeInt(1);
                        locationSettingsResult.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f6269a.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.f6269a;
            }
        }

        public static zzj m9507a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.internal.ISettingsCallbacks");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzj)) ? new zza(iBinder) : (zzj) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    parcel.enforceInterface("com.google.android.gms.location.internal.ISettingsCallbacks");
                    m9505a(parcel.readInt() != 0 ? (LocationSettingsResult) LocationSettingsResult.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.location.internal.ISettingsCallbacks");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m9505a(LocationSettingsResult locationSettingsResult);
}
