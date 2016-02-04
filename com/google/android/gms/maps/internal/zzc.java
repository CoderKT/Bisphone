package com.google.android.gms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import se.emilsjolander.stickylistheaders.C1128R;

public interface zzc extends IInterface {

    public abstract class zza extends Binder implements zzc {

        class zza implements zzc {
            private IBinder f6346a;

            zza(IBinder iBinder) {
                this.f6346a = iBinder;
            }

            public ICameraUpdateFactoryDelegate m10007a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
                    this.f6346a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    ICameraUpdateFactoryDelegate a = com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate.zza.m9695a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IMapViewDelegate m10008a(zzd com_google_android_gms_dynamic_zzd, GoogleMapOptions googleMapOptions) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    if (googleMapOptions != null) {
                        obtain.writeInt(1);
                        googleMapOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f6346a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    IMapViewDelegate a = com.google.android.gms.maps.internal.IMapViewDelegate.zza.m9863a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IStreetViewPanoramaViewDelegate m10009a(zzd com_google_android_gms_dynamic_zzd, StreetViewPanoramaOptions streetViewPanoramaOptions) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    if (streetViewPanoramaOptions != null) {
                        obtain.writeInt(1);
                        streetViewPanoramaOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f6346a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    IStreetViewPanoramaViewDelegate a = com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate.zza.m9955a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m10010a(zzd com_google_android_gms_dynamic_zzd) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    this.f6346a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m10011a(zzd com_google_android_gms_dynamic_zzd, int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    obtain.writeInt(i);
                    this.f6346a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.f6346a;
            }

            public IMapFragmentDelegate m10012b(zzd com_google_android_gms_dynamic_zzd) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    this.f6346a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    IMapFragmentDelegate a = com.google.android.gms.maps.internal.IMapFragmentDelegate.zza.m9840a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public com.google.android.gms.maps.model.internal.zza m10013b() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
                    this.f6346a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    com.google.android.gms.maps.model.internal.zza a = com.google.android.gms.maps.model.internal.zza.zza.m10213a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IStreetViewPanoramaFragmentDelegate m10014c(zzd com_google_android_gms_dynamic_zzd) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    this.f6346a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    IStreetViewPanoramaFragmentDelegate a = com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate.zza.m9936a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static zzc m10015a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.ICreator");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzc)) ? new zza(iBinder) : (zzc) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            IBinder iBinder = null;
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                    m10002a(com.google.android.gms.dynamic.zzd.zza.m9022a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                    IMapFragmentDelegate b = m10004b(com.google.android.gms.dynamic.zzd.zza.m9022a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (b != null) {
                        iBinder = b.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                    IMapViewDelegate a = m10000a(com.google.android.gms.dynamic.zzd.zza.m9022a(parcel.readStrongBinder()), parcel.readInt() != 0 ? GoogleMapOptions.CREATOR.m10476a(parcel) : null);
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                    ICameraUpdateFactoryDelegate a2 = m9999a();
                    parcel2.writeNoException();
                    if (a2 != null) {
                        iBinder = a2.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                    com.google.android.gms.maps.model.internal.zza b2 = m10005b();
                    parcel2.writeNoException();
                    if (b2 != null) {
                        iBinder = b2.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                    m10003a(com.google.android.gms.dynamic.zzd.zza.m9022a(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                    IStreetViewPanoramaViewDelegate a3 = m10001a(com.google.android.gms.dynamic.zzd.zza.m9022a(parcel.readStrongBinder()), parcel.readInt() != 0 ? StreetViewPanoramaOptions.CREATOR.m10479a(parcel) : null);
                    parcel2.writeNoException();
                    if (a3 != null) {
                        iBinder = a3.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                    IStreetViewPanoramaFragmentDelegate c = m10006c(com.google.android.gms.dynamic.zzd.zza.m9022a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (c != null) {
                        iBinder = c.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.maps.internal.ICreator");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    ICameraUpdateFactoryDelegate m9999a();

    IMapViewDelegate m10000a(zzd com_google_android_gms_dynamic_zzd, GoogleMapOptions googleMapOptions);

    IStreetViewPanoramaViewDelegate m10001a(zzd com_google_android_gms_dynamic_zzd, StreetViewPanoramaOptions streetViewPanoramaOptions);

    void m10002a(zzd com_google_android_gms_dynamic_zzd);

    void m10003a(zzd com_google_android_gms_dynamic_zzd, int i);

    IMapFragmentDelegate m10004b(zzd com_google_android_gms_dynamic_zzd);

    com.google.android.gms.maps.model.internal.zza m10005b();

    IStreetViewPanoramaFragmentDelegate m10006c(zzd com_google_android_gms_dynamic_zzd);
}
