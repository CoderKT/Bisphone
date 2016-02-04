package com.google.android.gms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import se.emilsjolander.stickylistheaders.C1128R;

public interface ICameraUpdateFactoryDelegate extends IInterface {

    public abstract class zza extends Binder implements ICameraUpdateFactoryDelegate {

        class zza implements ICameraUpdateFactoryDelegate {
            private IBinder f6335a;

            zza(IBinder iBinder) {
                this.f6335a = iBinder;
            }

            public zzd m9684a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    this.f6335a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    zzd a = com.google.android.gms.dynamic.zzd.zza.m9022a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzd m9685a(float f) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    obtain.writeFloat(f);
                    this.f6335a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    zzd a = com.google.android.gms.dynamic.zzd.zza.m9022a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzd m9686a(float f, float f2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    obtain.writeFloat(f);
                    obtain.writeFloat(f2);
                    this.f6335a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    zzd a = com.google.android.gms.dynamic.zzd.zza.m9022a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzd m9687a(float f, int i, int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    obtain.writeFloat(f);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.f6335a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    zzd a = com.google.android.gms.dynamic.zzd.zza.m9022a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzd m9688a(CameraPosition cameraPosition) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    if (cameraPosition != null) {
                        obtain.writeInt(1);
                        cameraPosition.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f6335a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    zzd a = com.google.android.gms.dynamic.zzd.zza.m9022a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzd m9689a(LatLng latLng) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    if (latLng != null) {
                        obtain.writeInt(1);
                        latLng.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f6335a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    zzd a = com.google.android.gms.dynamic.zzd.zza.m9022a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzd m9690a(LatLng latLng, float f) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    if (latLng != null) {
                        obtain.writeInt(1);
                        latLng.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeFloat(f);
                    this.f6335a.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    zzd a = com.google.android.gms.dynamic.zzd.zza.m9022a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzd m9691a(LatLngBounds latLngBounds, int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    if (latLngBounds != null) {
                        obtain.writeInt(1);
                        latLngBounds.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    this.f6335a.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    zzd a = com.google.android.gms.dynamic.zzd.zza.m9022a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzd m9692a(LatLngBounds latLngBounds, int i, int i2, int i3) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    if (latLngBounds != null) {
                        obtain.writeInt(1);
                        latLngBounds.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.f6335a.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    zzd a = com.google.android.gms.dynamic.zzd.zza.m9022a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.f6335a;
            }

            public zzd m9693b() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    this.f6335a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    zzd a = com.google.android.gms.dynamic.zzd.zza.m9022a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzd m9694b(float f) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    obtain.writeFloat(f);
                    this.f6335a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    zzd a = com.google.android.gms.dynamic.zzd.zza.m9022a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static ICameraUpdateFactoryDelegate m9695a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ICameraUpdateFactoryDelegate)) ? new zza(iBinder) : (ICameraUpdateFactoryDelegate) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            IBinder iBinder = null;
            zzd a;
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    a = m9673a();
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    a = m9682b();
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    a = m9675a(parcel.readFloat(), parcel.readFloat());
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    a = m9674a(parcel.readFloat());
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    a = m9683b(parcel.readFloat());
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    a = m9676a(parcel.readFloat(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    a = m9677a(parcel.readInt() != 0 ? CameraPosition.CREATOR.m10428a(parcel) : null);
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    a = m9678a(parcel.readInt() != 0 ? LatLng.CREATOR.m10440a(parcel) : null);
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    a = m9679a(parcel.readInt() != 0 ? LatLng.CREATOR.m10440a(parcel) : null, parcel.readFloat());
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    a = m9680a(parcel.readInt() != 0 ? LatLngBounds.CREATOR.m10437a(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_stackFromBottom /*11*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    a = m9681a(parcel.readInt() != 0 ? LatLngBounds.CREATOR.m10437a(parcel) : null, parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    zzd m9673a();

    zzd m9674a(float f);

    zzd m9675a(float f, float f2);

    zzd m9676a(float f, int i, int i2);

    zzd m9677a(CameraPosition cameraPosition);

    zzd m9678a(LatLng latLng);

    zzd m9679a(LatLng latLng, float f);

    zzd m9680a(LatLngBounds latLngBounds, int i);

    zzd m9681a(LatLngBounds latLngBounds, int i, int i2, int i3);

    zzd m9682b();

    zzd m9683b(float f);
}
