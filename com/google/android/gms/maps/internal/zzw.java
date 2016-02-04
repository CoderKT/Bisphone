package com.google.android.gms.maps.internal;

import android.graphics.Bitmap;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.zzd;
import se.emilsjolander.stickylistheaders.C1128R;

public interface zzw extends IInterface {

    public abstract class zza extends Binder implements zzw {

        class zza implements zzw {
            private IBinder f6366a;

            zza(IBinder iBinder) {
                this.f6366a = iBinder;
            }

            public void m10066a(Bitmap bitmap) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ISnapshotReadyCallback");
                    if (bitmap != null) {
                        obtain.writeInt(1);
                        bitmap.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f6366a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m10067a(zzd com_google_android_gms_dynamic_zzd) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ISnapshotReadyCallback");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    this.f6366a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.f6366a;
            }
        }

        public zza() {
            attachInterface(this, "com.google.android.gms.maps.internal.ISnapshotReadyCallback");
        }

        public static zzw m9585a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.ISnapshotReadyCallback");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzw)) ? new zza(iBinder) : (zzw) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ISnapshotReadyCallback");
                    m9583a(parcel.readInt() != 0 ? (Bitmap) Bitmap.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ISnapshotReadyCallback");
                    m9584a(com.google.android.gms.dynamic.zzd.zza.m9022a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.maps.internal.ISnapshotReadyCallback");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m9583a(Bitmap bitmap);

    void m9584a(zzd com_google_android_gms_dynamic_zzd);
}
