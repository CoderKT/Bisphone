package com.google.android.gms.maps.model.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.util.List;
import se.emilsjolander.stickylistheaders.C1128R;

public interface zzd extends IInterface {

    public abstract class zza extends Binder implements zzd {

        class zza implements zzd {
            private IBinder f6481a;

            zza(IBinder iBinder) {
                this.f6481a = iBinder;
            }

            public int m10300a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                    this.f6481a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean m10301a(zzd com_google_android_gms_maps_model_internal_zzd) {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                    obtain.writeStrongBinder(com_google_android_gms_maps_model_internal_zzd != null ? com_google_android_gms_maps_model_internal_zzd.asBinder() : null);
                    this.f6481a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
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
                return this.f6481a;
            }

            public int m10302b() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                    this.f6481a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<IBinder> m10303c() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                    this.f6481a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    List<IBinder> createBinderArrayList = obtain2.createBinderArrayList();
                    return createBinderArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean m10304d() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                    this.f6481a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int m10305e() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                    this.f6481a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static zzd m10306a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzd)) ? new zza(iBinder) : (zzd) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            int i3 = 0;
            boolean d;
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                    i3 = m10294a();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                    i3 = m10296b();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                    List c = m10297c();
                    parcel2.writeNoException();
                    parcel2.writeBinderList(c);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                    d = m10298d();
                    parcel2.writeNoException();
                    if (d) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                    d = m10295a(m10306a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (d) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                    i3 = m10299e();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    int m10294a();

    boolean m10295a(zzd com_google_android_gms_maps_model_internal_zzd);

    int m10296b();

    List<IBinder> m10297c();

    boolean m10298d();

    int m10299e();
}
