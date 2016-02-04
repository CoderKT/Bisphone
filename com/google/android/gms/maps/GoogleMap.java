package com.google.android.gms.maps;

import android.graphics.Bitmap;
import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.internal.zzf;

public final class GoogleMap {
    private final IGoogleMapDelegate f6299a;
    private UiSettings f6300b;

    public interface CancelableCallback {
        void m5621a();

        void m5622b();
    }

    public interface SnapshotReadyCallback {
        void m5625a(Bitmap bitmap);
    }

    public interface InfoWindowAdapter {
        View m5627a(Marker marker);

        View m5628b(Marker marker);
    }

    public interface OnMapLoadedCallback {
        void m5635o();
    }

    public interface OnMapLongClickListener {
        void m5636a(LatLng latLng);
    }

    public interface OnMarkerClickListener {
        boolean m5637a(Marker marker);
    }

    public interface OnMarkerDragListener {
        void m5638b(Marker marker);

        void m5639c(Marker marker);

        void m5640d(Marker marker);
    }

    public interface OnMyLocationButtonClickListener {
        boolean m5641n();
    }

    /* renamed from: com.google.android.gms.maps.GoogleMap.10 */
    class AnonymousClass10 extends com.google.android.gms.maps.internal.zzm.zza {
        final /* synthetic */ OnMarkerClickListener f6284a;
        final /* synthetic */ GoogleMap f6285b;

        AnonymousClass10(GoogleMap googleMap, OnMarkerClickListener onMarkerClickListener) {
            this.f6285b = googleMap;
            this.f6284a = onMarkerClickListener;
        }

        public boolean m9564a(zzf com_google_android_gms_maps_model_internal_zzf) {
            return this.f6284a.m5637a(new Marker(com_google_android_gms_maps_model_internal_zzf));
        }
    }

    /* renamed from: com.google.android.gms.maps.GoogleMap.11 */
    class AnonymousClass11 extends com.google.android.gms.maps.internal.zzn.zza {
        final /* synthetic */ OnMarkerDragListener f6286a;
        final /* synthetic */ GoogleMap f6287b;

        AnonymousClass11(GoogleMap googleMap, OnMarkerDragListener onMarkerDragListener) {
            this.f6287b = googleMap;
            this.f6286a = onMarkerDragListener;
        }

        public void m9569a(zzf com_google_android_gms_maps_model_internal_zzf) {
            this.f6286a.m5638b(new Marker(com_google_android_gms_maps_model_internal_zzf));
        }

        public void m9570b(zzf com_google_android_gms_maps_model_internal_zzf) {
            this.f6286a.m5640d(new Marker(com_google_android_gms_maps_model_internal_zzf));
        }

        public void m9571c(zzf com_google_android_gms_maps_model_internal_zzf) {
            this.f6286a.m5639c(new Marker(com_google_android_gms_maps_model_internal_zzf));
        }
    }

    /* renamed from: com.google.android.gms.maps.GoogleMap.13 */
    class AnonymousClass13 extends com.google.android.gms.maps.internal.zzd.zza {
        final /* synthetic */ InfoWindowAdapter f6288a;
        final /* synthetic */ GoogleMap f6289b;

        AnonymousClass13(GoogleMap googleMap, InfoWindowAdapter infoWindowAdapter) {
            this.f6289b = googleMap;
            this.f6288a = infoWindowAdapter;
        }

        public zzd m9575a(zzf com_google_android_gms_maps_model_internal_zzf) {
            return zze.m9023a(this.f6288a.m5627a(new Marker(com_google_android_gms_maps_model_internal_zzf)));
        }

        public zzd m9576b(zzf com_google_android_gms_maps_model_internal_zzf) {
            return zze.m9023a(this.f6288a.m5628b(new Marker(com_google_android_gms_maps_model_internal_zzf)));
        }
    }

    /* renamed from: com.google.android.gms.maps.GoogleMap.3 */
    class C08773 extends com.google.android.gms.maps.internal.zzo.zza {
        final /* synthetic */ OnMyLocationButtonClickListener f6290a;
        final /* synthetic */ GoogleMap f6291b;

        C08773(GoogleMap googleMap, OnMyLocationButtonClickListener onMyLocationButtonClickListener) {
            this.f6291b = googleMap;
            this.f6290a = onMyLocationButtonClickListener;
        }

        public boolean m9579a() {
            return this.f6290a.m5641n();
        }
    }

    /* renamed from: com.google.android.gms.maps.GoogleMap.4 */
    class C08784 extends com.google.android.gms.maps.internal.zzj.zza {
        final /* synthetic */ OnMapLoadedCallback f6292a;
        final /* synthetic */ GoogleMap f6293b;

        C08784(GoogleMap googleMap, OnMapLoadedCallback onMapLoadedCallback) {
            this.f6293b = googleMap;
            this.f6292a = onMapLoadedCallback;
        }

        public void m9582a() {
            this.f6292a.m5635o();
        }
    }

    /* renamed from: com.google.android.gms.maps.GoogleMap.5 */
    class C08795 extends com.google.android.gms.maps.internal.zzw.zza {
        final /* synthetic */ SnapshotReadyCallback f6294a;
        final /* synthetic */ GoogleMap f6295b;

        C08795(GoogleMap googleMap, SnapshotReadyCallback snapshotReadyCallback) {
            this.f6295b = googleMap;
            this.f6294a = snapshotReadyCallback;
        }

        public void m9586a(Bitmap bitmap) {
            this.f6294a.m5625a(bitmap);
        }

        public void m9587a(zzd com_google_android_gms_dynamic_zzd) {
            this.f6294a.m5625a((Bitmap) zze.m9024a(com_google_android_gms_dynamic_zzd));
        }
    }

    /* renamed from: com.google.android.gms.maps.GoogleMap.9 */
    class C08809 extends com.google.android.gms.maps.internal.zzk.zza {
        final /* synthetic */ OnMapLongClickListener f6296a;
        final /* synthetic */ GoogleMap f6297b;

        C08809(GoogleMap googleMap, OnMapLongClickListener onMapLongClickListener) {
            this.f6297b = googleMap;
            this.f6296a = onMapLongClickListener;
        }

        public void m9590a(LatLng latLng) {
            this.f6296a.m5636a(latLng);
        }
    }

    final class zza extends com.google.android.gms.maps.internal.zzb.zza {
        private final CancelableCallback f6298a;

        zza(CancelableCallback cancelableCallback) {
            this.f6298a = cancelableCallback;
        }

        public void m9594a() {
            this.f6298a.m5621a();
        }

        public void m9595b() {
            this.f6298a.m5622b();
        }
    }

    protected GoogleMap(IGoogleMapDelegate iGoogleMapDelegate) {
        this.f6299a = (IGoogleMapDelegate) zzx.m8718a((Object) iGoogleMapDelegate);
    }

    IGoogleMapDelegate m9596a() {
        return this.f6299a;
    }

    public final Marker m9597a(MarkerOptions markerOptions) {
        try {
            zzf a = this.f6299a.m9700a(markerOptions);
            return a != null ? new Marker(a) : null;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void m9598a(CameraUpdate cameraUpdate) {
        try {
            this.f6299a.m9728b(cameraUpdate.m9557a());
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void m9599a(CameraUpdate cameraUpdate, CancelableCallback cancelableCallback) {
        try {
            this.f6299a.m9708a(cameraUpdate.m9557a(), cancelableCallback == null ? null : new zza(cancelableCallback));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void m9600a(InfoWindowAdapter infoWindowAdapter) {
        if (infoWindowAdapter == null) {
            try {
                this.f6299a.m9710a(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.f6299a.m9710a(new AnonymousClass13(this, infoWindowAdapter));
    }

    public void m9601a(OnMapLoadedCallback onMapLoadedCallback) {
        if (onMapLoadedCallback == null) {
            try {
                this.f6299a.m9715a(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.f6299a.m9715a(new C08784(this, onMapLoadedCallback));
    }

    public final void m9602a(OnMapLongClickListener onMapLongClickListener) {
        if (onMapLongClickListener == null) {
            try {
                this.f6299a.m9716a(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.f6299a.m9716a(new C08809(this, onMapLongClickListener));
    }

    public final void m9603a(OnMarkerClickListener onMarkerClickListener) {
        if (onMarkerClickListener == null) {
            try {
                this.f6299a.m9718a(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.f6299a.m9718a(new AnonymousClass10(this, onMarkerClickListener));
    }

    public final void m9604a(OnMarkerDragListener onMarkerDragListener) {
        if (onMarkerDragListener == null) {
            try {
                this.f6299a.m9719a(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.f6299a.m9719a(new AnonymousClass11(this, onMarkerDragListener));
    }

    public final void m9605a(OnMyLocationButtonClickListener onMyLocationButtonClickListener) {
        if (onMyLocationButtonClickListener == null) {
            try {
                this.f6299a.m9720a(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.f6299a.m9720a(new C08773(this, onMyLocationButtonClickListener));
    }

    public final void m9606a(SnapshotReadyCallback snapshotReadyCallback) {
        m9607a(snapshotReadyCallback, null);
    }

    public final void m9607a(SnapshotReadyCallback snapshotReadyCallback, Bitmap bitmap) {
        try {
            this.f6299a.m9723a(new C08795(this, snapshotReadyCallback), (zzd) (zze) (bitmap != null ? zze.m9023a((Object) bitmap) : null));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void m9608a(boolean z) {
        try {
            this.f6299a.m9732c(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final CameraPosition m9609b() {
        try {
            return this.f6299a.m9696a();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void m9610c() {
        try {
            this.f6299a.m9735e();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final UiSettings m9611d() {
        try {
            if (this.f6300b == null) {
                this.f6300b = new UiSettings(this.f6299a.m9741k());
            }
            return this.f6300b;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
