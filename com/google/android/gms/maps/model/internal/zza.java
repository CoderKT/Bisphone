package com.google.android.gms.maps.model.internal;

import android.graphics.Bitmap;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.zzd;
import se.emilsjolander.stickylistheaders.C1128R;

public interface zza extends IInterface {

    public abstract class zza extends Binder implements zza {

        class zza implements zza {
            private IBinder f6478a;

            zza(IBinder iBinder) {
                this.f6478a = iBinder;
            }

            public zzd m10206a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    this.f6478a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    zzd a = com.google.android.gms.dynamic.zzd.zza.m9022a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzd m10207a(float f) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    obtain.writeFloat(f);
                    this.f6478a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    zzd a = com.google.android.gms.dynamic.zzd.zza.m9022a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzd m10208a(int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    obtain.writeInt(i);
                    this.f6478a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    zzd a = com.google.android.gms.dynamic.zzd.zza.m9022a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzd m10209a(Bitmap bitmap) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    if (bitmap != null) {
                        obtain.writeInt(1);
                        bitmap.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f6478a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    zzd a = com.google.android.gms.dynamic.zzd.zza.m9022a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzd m10210a(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    obtain.writeString(str);
                    this.f6478a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    zzd a = com.google.android.gms.dynamic.zzd.zza.m9022a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.f6478a;
            }

            public zzd m10211b(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    obtain.writeString(str);
                    this.f6478a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    zzd a = com.google.android.gms.dynamic.zzd.zza.m9022a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzd m10212c(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    obtain.writeString(str);
                    this.f6478a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    zzd a = com.google.android.gms.dynamic.zzd.zza.m9022a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static zza m10213a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zza)) ? new zza(iBinder) : (zza) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            IBinder iBinder = null;
            zzd a;
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    a = m10201a(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(a != null ? a.asBinder() : null);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    a = m10203a(parcel.readString());
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    a = m10204b(parcel.readString());
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    a = m10199a();
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    a = m10200a(parcel.readFloat());
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    a = m10202a(parcel.readInt() != 0 ? (Bitmap) Bitmap.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    a = m10205c(parcel.readString());
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    zzd m10199a();

    zzd m10200a(float f);

    zzd m10201a(int i);

    zzd m10202a(Bitmap bitmap);

    zzd m10203a(String str);

    zzd m10204b(String str);

    zzd m10205c(String str);
}
