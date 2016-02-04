package com.google.android.gms.maps.model.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import app.C0110R;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.maps.model.LatLng;
import se.emilsjolander.stickylistheaders.C1128R;

public interface zzf extends IInterface {

    public abstract class zza extends Binder implements zzf {

        class zza implements zzf {
            private IBinder f6482a;

            zza(IBinder iBinder) {
                this.f6482a = iBinder;
            }

            public void m10333a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    this.f6482a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m10334a(float f) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    obtain.writeFloat(f);
                    this.f6482a.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m10335a(float f, float f2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    obtain.writeFloat(f);
                    obtain.writeFloat(f2);
                    this.f6482a.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m10336a(zzd com_google_android_gms_dynamic_zzd) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    this.f6482a.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m10337a(LatLng latLng) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    if (latLng != null) {
                        obtain.writeInt(1);
                        latLng.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f6482a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m10338a(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    obtain.writeString(str);
                    this.f6482a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m10339a(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f6482a.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean m10340a(zzf com_google_android_gms_maps_model_internal_zzf) {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    obtain.writeStrongBinder(com_google_android_gms_maps_model_internal_zzf != null ? com_google_android_gms_maps_model_internal_zzf.asBinder() : null);
                    this.f6482a.transact(16, obtain, obtain2, 0);
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
                return this.f6482a;
            }

            public String m10341b() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    this.f6482a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m10342b(float f) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    obtain.writeFloat(f);
                    this.f6482a.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m10343b(float f, float f2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    obtain.writeFloat(f);
                    obtain.writeFloat(f2);
                    this.f6482a.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m10344b(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    obtain.writeString(str);
                    this.f6482a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m10345b(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f6482a.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public LatLng m10346c() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    this.f6482a.transact(4, obtain, obtain2, 0);
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

            public void m10347c(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f6482a.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String m10348d() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    this.f6482a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String m10349e() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    this.f6482a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean m10350f() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    this.f6482a.transact(10, obtain, obtain2, 0);
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

            public void m10351g() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    this.f6482a.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m10352h() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    this.f6482a.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean m10353i() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    this.f6482a.transact(13, obtain, obtain2, 0);
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

            public boolean m10354j() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    this.f6482a.transact(15, obtain, obtain2, 0);
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

            public int m10355k() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    this.f6482a.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean m10356l() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    this.f6482a.transact(21, obtain, obtain2, 0);
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

            public float m10357m() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    this.f6482a.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    float readFloat = obtain2.readFloat();
                    return readFloat;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public float m10358n() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    this.f6482a.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                    float readFloat = obtain2.readFloat();
                    return readFloat;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static zzf m10359a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzf)) ? new zza(iBinder) : (zzf) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            int i3 = 0;
            String b;
            boolean z;
            boolean f;
            float m;
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    m10307a();
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    b = m10315b();
                    parcel2.writeNoException();
                    parcel2.writeString(b);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    m10311a(parcel.readInt() != 0 ? LatLng.CREATOR.m10440a(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    LatLng c = m10320c();
                    parcel2.writeNoException();
                    if (c != null) {
                        parcel2.writeInt(1);
                        c.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    m10312a(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    b = m10322d();
                    parcel2.writeNoException();
                    parcel2.writeString(b);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    m10318b(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    b = m10323e();
                    parcel2.writeNoException();
                    parcel2.writeString(b);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m10313a(z);
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    f = m10324f();
                    parcel2.writeNoException();
                    if (f) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_stackFromBottom /*11*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    m10325g();
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_scrollingCache /*12*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    m10326h();
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_transcriptMode /*13*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    f = m10327i();
                    parcel2.writeNoException();
                    if (f) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_cacheColorHint /*14*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m10319b(z);
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_divider /*15*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    f = m10328j();
                    parcel2.writeNoException();
                    if (f) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_dividerHeight /*16*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    f = m10314a(m10359a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (f) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_choiceMode /*17*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    i3 = m10329k();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_fastScrollEnabled /*18*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    m10310a(com.google.android.gms.dynamic.zzd.zza.m9022a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_overScrollMode /*19*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    m10309a(parcel.readFloat(), parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_fastScrollAlwaysVisible /*20*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m10321c(z);
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_requiresFadingEdge /*21*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    f = m10330l();
                    parcel2.writeNoException();
                    if (f) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_stickyListHeadersListViewStyle /*22*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    m10308a(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_hasStickyHeaders /*23*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    m = m10331m();
                    parcel2.writeNoException();
                    parcel2.writeFloat(m);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_isDrawingListUnderStickyHeader /*24*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    m10317b(parcel.readFloat(), parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case C0110R.styleable.Theme_actionMenuTextAppearance /*25*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    m10316b(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case C0110R.styleable.Theme_actionMenuTextColor /*26*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    m = m10332n();
                    parcel2.writeNoException();
                    parcel2.writeFloat(m);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m10307a();

    void m10308a(float f);

    void m10309a(float f, float f2);

    void m10310a(zzd com_google_android_gms_dynamic_zzd);

    void m10311a(LatLng latLng);

    void m10312a(String str);

    void m10313a(boolean z);

    boolean m10314a(zzf com_google_android_gms_maps_model_internal_zzf);

    String m10315b();

    void m10316b(float f);

    void m10317b(float f, float f2);

    void m10318b(String str);

    void m10319b(boolean z);

    LatLng m10320c();

    void m10321c(boolean z);

    String m10322d();

    String m10323e();

    boolean m10324f();

    void m10325g();

    void m10326h();

    boolean m10327i();

    boolean m10328j();

    int m10329k();

    boolean m10330l();

    float m10331m();

    float m10332n();
}
