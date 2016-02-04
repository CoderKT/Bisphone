package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import app.C0110R;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.internal.IPolylineDelegate;
import com.google.android.gms.maps.model.internal.zzb;
import com.google.android.gms.maps.model.internal.zzc;
import com.google.android.gms.maps.model.internal.zzf;
import com.google.android.gms.maps.model.internal.zzg;
import com.google.android.gms.maps.model.internal.zzh;
import se.emilsjolander.stickylistheaders.C1128R;

public interface IGoogleMapDelegate extends IInterface {

    public abstract class zza extends Binder implements IGoogleMapDelegate {

        class zza implements IGoogleMapDelegate {
            private IBinder f6336a;

            zza(IBinder iBinder) {
                this.f6336a = iBinder;
            }

            public CameraPosition m9751a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.f6336a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    CameraPosition a = obtain2.readInt() != 0 ? CameraPosition.CREATOR.m10428a(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return a;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IPolylineDelegate m9752a(PolylineOptions polylineOptions) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (polylineOptions != null) {
                        obtain.writeInt(1);
                        polylineOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f6336a.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    IPolylineDelegate a = com.google.android.gms.maps.model.internal.IPolylineDelegate.zza.m10198a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzb m9753a(CircleOptions circleOptions) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (circleOptions != null) {
                        obtain.writeInt(1);
                        circleOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f6336a.transact(35, obtain, obtain2, 0);
                    obtain2.readException();
                    zzb a = com.google.android.gms.maps.model.internal.zzb.zza.m10250a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzc m9754a(GroundOverlayOptions groundOverlayOptions) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (groundOverlayOptions != null) {
                        obtain.writeInt(1);
                        groundOverlayOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f6336a.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    zzc a = com.google.android.gms.maps.model.internal.zzc.zza.m10293a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzf m9755a(MarkerOptions markerOptions) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (markerOptions != null) {
                        obtain.writeInt(1);
                        markerOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f6336a.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    zzf a = com.google.android.gms.maps.model.internal.zzf.zza.m10359a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzg m9756a(PolygonOptions polygonOptions) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (polygonOptions != null) {
                        obtain.writeInt(1);
                        polygonOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f6336a.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    zzg a = com.google.android.gms.maps.model.internal.zzg.zza.m10400a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzh m9757a(TileOverlayOptions tileOverlayOptions) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (tileOverlayOptions != null) {
                        obtain.writeInt(1);
                        tileOverlayOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f6336a.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    zzh a = com.google.android.gms.maps.model.internal.zzh.zza.m10423a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9758a(int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeInt(i);
                    this.f6336a.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9759a(int i, int i2, int i3, int i4) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeInt(i4);
                    this.f6336a.transact(39, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9760a(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f6336a.transact(54, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9761a(zzd com_google_android_gms_dynamic_zzd) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    this.f6336a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9762a(zzd com_google_android_gms_dynamic_zzd, int i, zzb com_google_android_gms_maps_internal_zzb) {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    obtain.writeInt(i);
                    if (com_google_android_gms_maps_internal_zzb != null) {
                        iBinder = com_google_android_gms_maps_internal_zzb.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.f6336a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9763a(zzd com_google_android_gms_dynamic_zzd, zzb com_google_android_gms_maps_internal_zzb) {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    if (com_google_android_gms_maps_internal_zzb != null) {
                        iBinder = com_google_android_gms_maps_internal_zzb.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.f6336a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9764a(ILocationSourceDelegate iLocationSourceDelegate) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(iLocationSourceDelegate != null ? iLocationSourceDelegate.asBinder() : null);
                    this.f6336a.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9765a(zzd com_google_android_gms_maps_internal_zzd) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(com_google_android_gms_maps_internal_zzd != null ? com_google_android_gms_maps_internal_zzd.asBinder() : null);
                    this.f6336a.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9766a(zze com_google_android_gms_maps_internal_zze) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(com_google_android_gms_maps_internal_zze != null ? com_google_android_gms_maps_internal_zze.asBinder() : null);
                    this.f6336a.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9767a(zzf com_google_android_gms_maps_internal_zzf) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(com_google_android_gms_maps_internal_zzf != null ? com_google_android_gms_maps_internal_zzf.asBinder() : null);
                    this.f6336a.transact(45, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9768a(zzg com_google_android_gms_maps_internal_zzg) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(com_google_android_gms_maps_internal_zzg != null ? com_google_android_gms_maps_internal_zzg.asBinder() : null);
                    this.f6336a.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9769a(zzi com_google_android_gms_maps_internal_zzi) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(com_google_android_gms_maps_internal_zzi != null ? com_google_android_gms_maps_internal_zzi.asBinder() : null);
                    this.f6336a.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9770a(zzj com_google_android_gms_maps_internal_zzj) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(com_google_android_gms_maps_internal_zzj != null ? com_google_android_gms_maps_internal_zzj.asBinder() : null);
                    this.f6336a.transact(42, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9771a(zzk com_google_android_gms_maps_internal_zzk) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(com_google_android_gms_maps_internal_zzk != null ? com_google_android_gms_maps_internal_zzk.asBinder() : null);
                    this.f6336a.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9772a(zzl com_google_android_gms_maps_internal_zzl) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(com_google_android_gms_maps_internal_zzl != null ? com_google_android_gms_maps_internal_zzl.asBinder() : null);
                    this.f6336a.transact(53, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9773a(zzm com_google_android_gms_maps_internal_zzm) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(com_google_android_gms_maps_internal_zzm != null ? com_google_android_gms_maps_internal_zzm.asBinder() : null);
                    this.f6336a.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9774a(zzn com_google_android_gms_maps_internal_zzn) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(com_google_android_gms_maps_internal_zzn != null ? com_google_android_gms_maps_internal_zzn.asBinder() : null);
                    this.f6336a.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9775a(zzo com_google_android_gms_maps_internal_zzo) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(com_google_android_gms_maps_internal_zzo != null ? com_google_android_gms_maps_internal_zzo.asBinder() : null);
                    this.f6336a.transact(37, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9776a(zzp com_google_android_gms_maps_internal_zzp) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(com_google_android_gms_maps_internal_zzp != null ? com_google_android_gms_maps_internal_zzp.asBinder() : null);
                    this.f6336a.transact(36, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9777a(zzq com_google_android_gms_maps_internal_zzq) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(com_google_android_gms_maps_internal_zzq != null ? com_google_android_gms_maps_internal_zzq.asBinder() : null);
                    this.f6336a.transact(80, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9778a(zzw com_google_android_gms_maps_internal_zzw, zzd com_google_android_gms_dynamic_zzd) {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(com_google_android_gms_maps_internal_zzw != null ? com_google_android_gms_maps_internal_zzw.asBinder() : null);
                    if (com_google_android_gms_dynamic_zzd != null) {
                        iBinder = com_google_android_gms_dynamic_zzd.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.f6336a.transact(38, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9779a(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeString(str);
                    this.f6336a.transact(61, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9780a(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f6336a.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.f6336a;
            }

            public float m9781b() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.f6336a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    float readFloat = obtain2.readFloat();
                    return readFloat;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9782b(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f6336a.transact(60, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        bundle.readFromParcel(obtain2);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9783b(zzd com_google_android_gms_dynamic_zzd) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    this.f6336a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean m9784b(boolean z) {
                boolean z2 = true;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeInt(z ? 1 : 0);
                    this.f6336a.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z2 = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z2;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public float m9785c() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.f6336a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    float readFloat = obtain2.readFloat();
                    return readFloat;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9786c(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f6336a.transact(81, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9787c(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f6336a.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9788d() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.f6336a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9789d(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f6336a.transact(41, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9790e() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.f6336a.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int m9791f() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.f6336a.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean m9792g() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.f6336a.transact(17, obtain, obtain2, 0);
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

            public boolean m9793h() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.f6336a.transact(19, obtain, obtain2, 0);
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

            public boolean m9794i() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.f6336a.transact(21, obtain, obtain2, 0);
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

            public Location m9795j() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.f6336a.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    Location location = obtain2.readInt() != 0 ? (Location) Location.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return location;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IUiSettingsDelegate m9796k() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.f6336a.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                    IUiSettingsDelegate a = com.google.android.gms.maps.internal.IUiSettingsDelegate.zza.m9994a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IProjectionDelegate m9797l() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.f6336a.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                    IProjectionDelegate a = com.google.android.gms.maps.internal.IProjectionDelegate.zza.m9870a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean m9798m() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.f6336a.transact(40, obtain, obtain2, 0);
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

            public com.google.android.gms.maps.model.internal.zzd m9799n() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.f6336a.transact(44, obtain, obtain2, 0);
                    obtain2.readException();
                    com.google.android.gms.maps.model.internal.zzd a = com.google.android.gms.maps.model.internal.zzd.zza.m10306a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9800o() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.f6336a.transact(55, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9801p() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.f6336a.transact(56, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9802q() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.f6336a.transact(57, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m9803r() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.f6336a.transact(58, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean m9804s() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.f6336a.transact(59, obtain, obtain2, 0);
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

            public void m9805t() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.f6336a.transact(82, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static IGoogleMapDelegate m9806a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IGoogleMapDelegate)) ? new zza(iBinder) : (IGoogleMapDelegate) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            int i3 = 0;
            IBinder iBinder = null;
            float b;
            boolean g;
            boolean z;
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    CameraPosition a = m9696a();
                    parcel2.writeNoException();
                    if (a != null) {
                        parcel2.writeInt(1);
                        a.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    b = m9726b();
                    parcel2.writeNoException();
                    parcel2.writeFloat(b);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    b = m9730c();
                    parcel2.writeNoException();
                    parcel2.writeFloat(b);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    m9706a(com.google.android.gms.dynamic.zzd.zza.m9022a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    m9728b(com.google.android.gms.dynamic.zzd.zza.m9022a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    m9708a(com.google.android.gms.dynamic.zzd.zza.m9022a(parcel.readStrongBinder()), com.google.android.gms.maps.internal.zzb.zza.m9593a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    m9707a(com.google.android.gms.dynamic.zzd.zza.m9022a(parcel.readStrongBinder()), parcel.readInt(), com.google.android.gms.maps.internal.zzb.zza.m9593a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    m9733d();
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    IPolylineDelegate a2 = m9697a(parcel.readInt() != 0 ? PolylineOptions.CREATOR.m10452a(parcel) : null);
                    parcel2.writeNoException();
                    if (a2 != null) {
                        iBinder = a2.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    zzg a3 = m9701a(parcel.readInt() != 0 ? PolygonOptions.CREATOR.m10449a(parcel) : null);
                    parcel2.writeNoException();
                    if (a3 != null) {
                        iBinder = a3.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_stackFromBottom /*11*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    zzf a4 = m9700a(parcel.readInt() != 0 ? MarkerOptions.CREATOR.m10443a(parcel) : null);
                    parcel2.writeNoException();
                    if (a4 != null) {
                        iBinder = a4.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_scrollingCache /*12*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    zzc a5 = m9699a(parcel.readInt() != 0 ? GroundOverlayOptions.CREATOR.m10434a(parcel) : null);
                    parcel2.writeNoException();
                    if (a5 != null) {
                        iBinder = a5.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_transcriptMode /*13*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    zzh a6 = m9702a(parcel.readInt() != 0 ? TileOverlayOptions.CREATOR.m10470a(parcel) : null);
                    parcel2.writeNoException();
                    if (a6 != null) {
                        iBinder = a6.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_cacheColorHint /*14*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    m9735e();
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_divider /*15*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    int f = m9736f();
                    parcel2.writeNoException();
                    parcel2.writeInt(f);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_dividerHeight /*16*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    m9703a(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_choiceMode /*17*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    g = m9737g();
                    parcel2.writeNoException();
                    parcel2.writeInt(g ? 1 : 0);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_fastScrollEnabled /*18*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m9725a(z);
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_overScrollMode /*19*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    g = m9738h();
                    parcel2.writeNoException();
                    if (g) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_fastScrollAlwaysVisible /*20*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    g = m9729b(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    if (g) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_android_requiresFadingEdge /*21*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    g = m9739i();
                    parcel2.writeNoException();
                    if (g) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_stickyListHeadersListViewStyle /*22*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m9732c(z);
                    parcel2.writeNoException();
                    return true;
                case C1128R.styleable.StickyListHeadersListView_hasStickyHeaders /*23*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    Location j = m9740j();
                    parcel2.writeNoException();
                    if (j != null) {
                        parcel2.writeInt(1);
                        j.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case C1128R.styleable.StickyListHeadersListView_isDrawingListUnderStickyHeader /*24*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    m9709a(com.google.android.gms.maps.internal.ILocationSourceDelegate.zza.m9811a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case C0110R.styleable.Theme_actionMenuTextAppearance /*25*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    IUiSettingsDelegate k = m9741k();
                    parcel2.writeNoException();
                    if (k != null) {
                        iBinder = k.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case C0110R.styleable.Theme_actionMenuTextColor /*26*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    IProjectionDelegate l = m9742l();
                    parcel2.writeNoException();
                    if (l != null) {
                        iBinder = l.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case C0110R.styleable.Theme_actionModeStyle /*27*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    m9711a(com.google.android.gms.maps.internal.zze.zza.m10020a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case C0110R.styleable.Theme_actionModeCloseButtonStyle /*28*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    m9714a(com.google.android.gms.maps.internal.zzi.zza.m10036a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case C0110R.styleable.Theme_actionModeBackground /*29*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    m9716a(com.google.android.gms.maps.internal.zzk.zza.m9589a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case C0110R.styleable.Theme_actionModeSplitBackground /*30*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    m9718a(com.google.android.gms.maps.internal.zzm.zza.m9563a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case C0110R.styleable.Theme_actionModeCloseDrawable /*31*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    m9719a(com.google.android.gms.maps.internal.zzn.zza.m9568a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case C0110R.styleable.Theme_actionModeCutDrawable /*32*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    m9713a(com.google.android.gms.maps.internal.zzg.zza.m10028a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case C0110R.styleable.Theme_actionModeCopyDrawable /*33*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    m9710a(com.google.android.gms.maps.internal.zzd.zza.m9574a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case C0110R.styleable.Theme_actionModeSelectAllDrawable /*35*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    zzb a7 = m9698a(parcel.readInt() != 0 ? CircleOptions.CREATOR.m10431a(parcel) : null);
                    parcel2.writeNoException();
                    if (a7 != null) {
                        iBinder = a7.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case C0110R.styleable.Theme_actionModeShareDrawable /*36*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    m9721a(com.google.android.gms.maps.internal.zzp.zza.m10047a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case C0110R.styleable.Theme_actionModeFindDrawable /*37*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    m9720a(com.google.android.gms.maps.internal.zzo.zza.m9578a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case C0110R.styleable.Theme_actionModeWebSearchDrawable /*38*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    m9723a(com.google.android.gms.maps.internal.zzw.zza.m9585a(parcel.readStrongBinder()), com.google.android.gms.dynamic.zzd.zza.m9022a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case C0110R.styleable.Theme_actionModePopupWindowStyle /*39*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    m9704a(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case C0110R.styleable.Theme_textAppearanceLargePopupMenu /*40*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    g = m9743m();
                    parcel2.writeNoException();
                    if (g) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case C0110R.styleable.Theme_textAppearanceSmallPopupMenu /*41*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m9734d(z);
                    parcel2.writeNoException();
                    return true;
                case C0110R.styleable.Theme_dialogTheme /*42*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    m9715a(com.google.android.gms.maps.internal.zzj.zza.m9581a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case C0110R.styleable.Theme_listDividerAlertDialog /*44*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    com.google.android.gms.maps.model.internal.zzd n = m9744n();
                    parcel2.writeNoException();
                    if (n != null) {
                        iBinder = n.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case C0110R.styleable.Theme_actionDropDownStyle /*45*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    m9712a(com.google.android.gms.maps.internal.zzf.zza.m10025a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case C0110R.styleable.Theme_selectableItemBackgroundBorderless /*53*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    m9717a(com.google.android.gms.maps.internal.zzl.zza.m9641a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case C0110R.styleable.Theme_borderlessButtonStyle /*54*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    m9705a(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case C0110R.styleable.Theme_dividerVertical /*55*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    m9745o();
                    parcel2.writeNoException();
                    return true;
                case C0110R.styleable.Theme_dividerHorizontal /*56*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    m9746p();
                    parcel2.writeNoException();
                    return true;
                case C0110R.styleable.Theme_activityChooserViewStyle /*57*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    m9747q();
                    parcel2.writeNoException();
                    return true;
                case C0110R.styleable.Theme_toolbarStyle /*58*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    m9748r();
                    parcel2.writeNoException();
                    return true;
                case C0110R.styleable.Theme_toolbarNavigationButtonStyle /*59*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    g = m9749s();
                    parcel2.writeNoException();
                    if (g) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case C0110R.styleable.Theme_popupMenuStyle /*60*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    Bundle bundle = parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null;
                    m9727b(bundle);
                    parcel2.writeNoException();
                    if (bundle != null) {
                        parcel2.writeInt(1);
                        bundle.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case C0110R.styleable.Theme_popupWindowStyle /*61*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    m9724a(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case C0110R.styleable.Theme_panelMenuListTheme /*80*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    m9722a(com.google.android.gms.maps.internal.zzq.zza.m10050a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case C0110R.styleable.Theme_listChoiceBackgroundIndicator /*81*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    m9731c(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case C0110R.styleable.Theme_colorPrimary /*82*/:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    m9750t();
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    CameraPosition m9696a();

    IPolylineDelegate m9697a(PolylineOptions polylineOptions);

    zzb m9698a(CircleOptions circleOptions);

    zzc m9699a(GroundOverlayOptions groundOverlayOptions);

    zzf m9700a(MarkerOptions markerOptions);

    zzg m9701a(PolygonOptions polygonOptions);

    zzh m9702a(TileOverlayOptions tileOverlayOptions);

    void m9703a(int i);

    void m9704a(int i, int i2, int i3, int i4);

    void m9705a(Bundle bundle);

    void m9706a(zzd com_google_android_gms_dynamic_zzd);

    void m9707a(zzd com_google_android_gms_dynamic_zzd, int i, zzb com_google_android_gms_maps_internal_zzb);

    void m9708a(zzd com_google_android_gms_dynamic_zzd, zzb com_google_android_gms_maps_internal_zzb);

    void m9709a(ILocationSourceDelegate iLocationSourceDelegate);

    void m9710a(zzd com_google_android_gms_maps_internal_zzd);

    void m9711a(zze com_google_android_gms_maps_internal_zze);

    void m9712a(zzf com_google_android_gms_maps_internal_zzf);

    void m9713a(zzg com_google_android_gms_maps_internal_zzg);

    void m9714a(zzi com_google_android_gms_maps_internal_zzi);

    void m9715a(zzj com_google_android_gms_maps_internal_zzj);

    void m9716a(zzk com_google_android_gms_maps_internal_zzk);

    void m9717a(zzl com_google_android_gms_maps_internal_zzl);

    void m9718a(zzm com_google_android_gms_maps_internal_zzm);

    void m9719a(zzn com_google_android_gms_maps_internal_zzn);

    void m9720a(zzo com_google_android_gms_maps_internal_zzo);

    void m9721a(zzp com_google_android_gms_maps_internal_zzp);

    void m9722a(zzq com_google_android_gms_maps_internal_zzq);

    void m9723a(zzw com_google_android_gms_maps_internal_zzw, zzd com_google_android_gms_dynamic_zzd);

    void m9724a(String str);

    void m9725a(boolean z);

    float m9726b();

    void m9727b(Bundle bundle);

    void m9728b(zzd com_google_android_gms_dynamic_zzd);

    boolean m9729b(boolean z);

    float m9730c();

    void m9731c(Bundle bundle);

    void m9732c(boolean z);

    void m9733d();

    void m9734d(boolean z);

    void m9735e();

    int m9736f();

    boolean m9737g();

    boolean m9738h();

    boolean m9739i();

    Location m9740j();

    IUiSettingsDelegate m9741k();

    IProjectionDelegate m9742l();

    boolean m9743m();

    com.google.android.gms.maps.model.internal.zzd m9744n();

    void m9745o();

    void m9746p();

    void m9747q();

    void m9748r();

    boolean m9749s();

    void m9750t();
}
