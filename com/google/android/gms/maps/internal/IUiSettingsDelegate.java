package com.google.android.gms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import se.emilsjolander.stickylistheaders.C1128R;

public interface IUiSettingsDelegate extends IInterface {

    public abstract class zza extends Binder implements IUiSettingsDelegate {

        class zza implements IUiSettingsDelegate {
            private IBinder f6344a;

            zza(IBinder iBinder) {
                this.f6344a = iBinder;
            }

            public void m9975a(boolean z) {
                int i = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    if (!z) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    this.f6344a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean m9976a() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    this.f6344a.transact(9, obtain, obtain2, 0);
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
                return this.f6344a;
            }

            public void m9977b(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f6344a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean m9978b() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    this.f6344a.transact(10, obtain, obtain2, 0);
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

            public void m9979c(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f6344a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean m9980c() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    this.f6344a.transact(11, obtain, obtain2, 0);
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

            public void m9981d(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f6344a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean m9982d() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    this.f6344a.transact(12, obtain, obtain2, 0);
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

            public void m9983e(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f6344a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean m9984e() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    this.f6344a.transact(13, obtain, obtain2, 0);
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

            public void m9985f(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f6344a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean m9986f() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    this.f6344a.transact(14, obtain, obtain2, 0);
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

            public void m9987g(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f6344a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean m9988g() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    this.f6344a.transact(15, obtain, obtain2, 0);
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

            public void m9989h(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f6344a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean m9990h() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    this.f6344a.transact(17, obtain, obtain2, 0);
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

            public void m9991i(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f6344a.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean m9992i() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    this.f6344a.transact(19, obtain, obtain2, 0);
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

            public void m9993j(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f6344a.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static IUiSettingsDelegate m9994a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IUiSettingsDelegate)) ? new zza(iBinder) : (IUiSettingsDelegate) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            int i3 = 0;
            boolean z;
            boolean a;
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m9956a(z);
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m9958b(z);
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m9960c(z);
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m9962d(z);
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m9964e(z);
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m9966f(z);
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m9968g(z);
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m9970h(z);
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    a = m9957a();
                    parcel2.writeNoException();
                    if (a) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    a = m9959b();
                    parcel2.writeNoException();
                    if (a) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_stackFromBottom /*11*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    a = m9961c();
                    parcel2.writeNoException();
                    if (a) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_scrollingCache /*12*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    a = m9963d();
                    parcel2.writeNoException();
                    if (a) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_transcriptMode /*13*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    a = m9965e();
                    parcel2.writeNoException();
                    if (a) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_cacheColorHint /*14*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    a = m9967f();
                    parcel2.writeNoException();
                    if (a) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_divider /*15*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    a = m9969g();
                    parcel2.writeNoException();
                    if (a) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_dividerHeight /*16*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m9972i(z);
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_choiceMode /*17*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    a = m9971h();
                    parcel2.writeNoException();
                    if (a) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_fastScrollEnabled /*18*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m9974j(z);
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_overScrollMode /*19*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    a = m9973i();
                    parcel2.writeNoException();
                    if (a) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m9956a(boolean z);

    boolean m9957a();

    void m9958b(boolean z);

    boolean m9959b();

    void m9960c(boolean z);

    boolean m9961c();

    void m9962d(boolean z);

    boolean m9963d();

    void m9964e(boolean z);

    boolean m9965e();

    void m9966f(boolean z);

    boolean m9967f();

    void m9968g(boolean z);

    boolean m9969g();

    void m9970h(boolean z);

    boolean m9971h();

    void m9972i(boolean z);

    boolean m9973i();

    void m9974j(boolean z);
}
