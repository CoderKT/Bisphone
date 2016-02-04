package com.google.android.gms.maps.model.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.maps.model.LatLng;
import java.util.List;
import se.emilsjolander.stickylistheaders.C1128R;

public interface IPolylineDelegate extends IInterface {

    public abstract class zza extends Binder implements IPolylineDelegate {

        class zza implements IPolylineDelegate {
            private IBinder f6477a;

            zza(IBinder iBinder) {
                this.f6477a = iBinder;
            }

            public void m10182a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    this.f6477a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m10183a(float f) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    obtain.writeFloat(f);
                    this.f6477a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m10184a(int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    obtain.writeInt(i);
                    this.f6477a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m10185a(List<LatLng> list) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    obtain.writeTypedList(list);
                    this.f6477a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m10186a(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f6477a.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean m10187a(IPolylineDelegate iPolylineDelegate) {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    obtain.writeStrongBinder(iPolylineDelegate != null ? iPolylineDelegate.asBinder() : null);
                    this.f6477a.transact(15, obtain, obtain2, 0);
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
                return this.f6477a;
            }

            public String m10188b() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    this.f6477a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m10189b(float f) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    obtain.writeFloat(f);
                    this.f6477a.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m10190b(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f6477a.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<LatLng> m10191c() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    this.f6477a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    List<LatLng> createTypedArrayList = obtain2.createTypedArrayList(LatLng.CREATOR);
                    return createTypedArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public float m10192d() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    this.f6477a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    float readFloat = obtain2.readFloat();
                    return readFloat;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int m10193e() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    this.f6477a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public float m10194f() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    this.f6477a.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    float readFloat = obtain2.readFloat();
                    return readFloat;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean m10195g() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    this.f6477a.transact(12, obtain, obtain2, 0);
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

            public boolean m10196h() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    this.f6477a.transact(14, obtain, obtain2, 0);
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

            public int m10197i() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    this.f6477a.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static IPolylineDelegate m10198a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IPolylineDelegate)) ? new zza(iBinder) : (IPolylineDelegate) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            int i3 = 0;
            float d;
            boolean z;
            boolean g;
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    m10166a();
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    String b = m10172b();
                    parcel2.writeNoException();
                    parcel2.writeString(b);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    m10169a(parcel.createTypedArrayList(LatLng.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    List c = m10175c();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(c);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    m10167a(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    d = m10176d();
                    parcel2.writeNoException();
                    parcel2.writeFloat(d);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    m10168a(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    i3 = m10177e();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    m10173b(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    d = m10178f();
                    parcel2.writeNoException();
                    parcel2.writeFloat(d);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_stackFromBottom /*11*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m10170a(z);
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_scrollingCache /*12*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    g = m10179g();
                    parcel2.writeNoException();
                    if (g) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_transcriptMode /*13*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m10174b(z);
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_cacheColorHint /*14*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    g = m10180h();
                    parcel2.writeNoException();
                    if (g) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_divider /*15*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    g = m10171a(m10198a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (g) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_dividerHeight /*16*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    i3 = m10181i();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m10166a();

    void m10167a(float f);

    void m10168a(int i);

    void m10169a(List<LatLng> list);

    void m10170a(boolean z);

    boolean m10171a(IPolylineDelegate iPolylineDelegate);

    String m10172b();

    void m10173b(float f);

    void m10174b(boolean z);

    List<LatLng> m10175c();

    float m10176d();

    int m10177e();

    float m10178f();

    boolean m10179g();

    boolean m10180h();

    int m10181i();
}
