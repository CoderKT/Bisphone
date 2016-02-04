package com.google.android.gms.maps.model.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import se.emilsjolander.stickylistheaders.C1128R;

public interface zzc extends IInterface {

    public abstract class zza extends Binder implements zzc {

        class zza implements zzc {
            private IBinder f6480a;

            zza(IBinder iBinder) {
                this.f6480a = iBinder;
            }

            public void m10272a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    this.f6480a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m10273a(float f) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    obtain.writeFloat(f);
                    this.f6480a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m10274a(float f, float f2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    obtain.writeFloat(f);
                    obtain.writeFloat(f2);
                    this.f6480a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m10275a(zzd com_google_android_gms_dynamic_zzd) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    this.f6480a.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m10276a(LatLng latLng) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    if (latLng != null) {
                        obtain.writeInt(1);
                        latLng.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f6480a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m10277a(LatLngBounds latLngBounds) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    if (latLngBounds != null) {
                        obtain.writeInt(1);
                        latLngBounds.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f6480a.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m10278a(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f6480a.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean m10279a(zzc com_google_android_gms_maps_model_internal_zzc) {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    obtain.writeStrongBinder(com_google_android_gms_maps_model_internal_zzc != null ? com_google_android_gms_maps_model_internal_zzc.asBinder() : null);
                    this.f6480a.transact(19, obtain, obtain2, 0);
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
                return this.f6480a;
            }

            public String m10280b() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    this.f6480a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m10281b(float f) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    obtain.writeFloat(f);
                    this.f6480a.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public LatLng m10282c() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    this.f6480a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    LatLng a = obtain2.readInt() != 0 ? LatLng.CREATOR.m10440a(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return a;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m10283c(float f) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    obtain.writeFloat(f);
                    this.f6480a.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public float m10284d() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    this.f6480a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    float readFloat = obtain2.readFloat();
                    return readFloat;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m10285d(float f) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    obtain.writeFloat(f);
                    this.f6480a.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public float m10286e() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    this.f6480a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    float readFloat = obtain2.readFloat();
                    return readFloat;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public LatLngBounds m10287f() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    this.f6480a.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    LatLngBounds a = obtain2.readInt() != 0 ? LatLngBounds.CREATOR.m10437a(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return a;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public float m10288g() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    this.f6480a.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    float readFloat = obtain2.readFloat();
                    return readFloat;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public float m10289h() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    this.f6480a.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    float readFloat = obtain2.readFloat();
                    return readFloat;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean m10290i() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    this.f6480a.transact(16, obtain, obtain2, 0);
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

            public float m10291j() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    this.f6480a.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    float readFloat = obtain2.readFloat();
                    return readFloat;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int m10292k() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    this.f6480a.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static zzc m10293a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzc)) ? new zza(iBinder) : (zzc) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            LatLngBounds latLngBounds = null;
            int i3 = 0;
            LatLng a;
            float d;
            boolean i4;
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    m10251a();
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    String b = m10259b();
                    parcel2.writeNoException();
                    parcel2.writeString(b);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    if (parcel.readInt() != 0) {
                        a = LatLng.CREATOR.m10440a(parcel);
                    }
                    m10255a(a);
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    a = m10261c();
                    parcel2.writeNoException();
                    if (a != null) {
                        parcel2.writeInt(1);
                        a.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    m10252a(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    m10253a(parcel.readFloat(), parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    d = m10263d();
                    parcel2.writeNoException();
                    parcel2.writeFloat(d);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    d = m10265e();
                    parcel2.writeNoException();
                    parcel2.writeFloat(d);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    if (parcel.readInt() != 0) {
                        latLngBounds = LatLngBounds.CREATOR.m10437a(parcel);
                    }
                    m10256a(latLngBounds);
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    latLngBounds = m10266f();
                    parcel2.writeNoException();
                    if (latLngBounds != null) {
                        parcel2.writeInt(1);
                        latLngBounds.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_stackFromBottom /*11*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    m10260b(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_scrollingCache /*12*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    d = m10267g();
                    parcel2.writeNoException();
                    parcel2.writeFloat(d);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_transcriptMode /*13*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    m10262c(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_cacheColorHint /*14*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    d = m10268h();
                    parcel2.writeNoException();
                    parcel2.writeFloat(d);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_divider /*15*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    m10257a(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_dividerHeight /*16*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    i4 = m10269i();
                    parcel2.writeNoException();
                    if (i4) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_choiceMode /*17*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    m10264d(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_fastScrollEnabled /*18*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    d = m10270j();
                    parcel2.writeNoException();
                    parcel2.writeFloat(d);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_overScrollMode /*19*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    i4 = m10258a(m10293a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (i4) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_fastScrollAlwaysVisible /*20*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    int k = m10271k();
                    parcel2.writeNoException();
                    parcel2.writeInt(k);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_requiresFadingEdge /*21*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    m10254a(com.google.android.gms.dynamic.zzd.zza.m9022a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m10251a();

    void m10252a(float f);

    void m10253a(float f, float f2);

    void m10254a(zzd com_google_android_gms_dynamic_zzd);

    void m10255a(LatLng latLng);

    void m10256a(LatLngBounds latLngBounds);

    void m10257a(boolean z);

    boolean m10258a(zzc com_google_android_gms_maps_model_internal_zzc);

    String m10259b();

    void m10260b(float f);

    LatLng m10261c();

    void m10262c(float f);

    float m10263d();

    void m10264d(float f);

    float m10265e();

    LatLngBounds m10266f();

    float m10267g();

    float m10268h();

    boolean m10269i();

    float m10270j();

    int m10271k();
}
