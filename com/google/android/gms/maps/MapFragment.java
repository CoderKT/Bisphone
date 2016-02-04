package com.google.android.gms.maps;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzf;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.IMapFragmentDelegate;
import com.google.android.gms.maps.internal.MapLifecycleDelegate;
import com.google.android.gms.maps.internal.zzy;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

public class MapFragment extends Fragment {
    private final zzb f3120a;
    private GoogleMap f3121b;

    class zza implements MapLifecycleDelegate {
        private final Fragment f6317a;
        private final IMapFragmentDelegate f6318b;

        /* renamed from: com.google.android.gms.maps.MapFragment.zza.1 */
        class C08811 extends com.google.android.gms.maps.internal.zzl.zza {
            final /* synthetic */ OnMapReadyCallback f6315a;
            final /* synthetic */ zza f6316b;

            C08811(zza com_google_android_gms_maps_MapFragment_zza, OnMapReadyCallback onMapReadyCallback) {
                this.f6316b = com_google_android_gms_maps_MapFragment_zza;
                this.f6315a = onMapReadyCallback;
            }

            public void m9642a(IGoogleMapDelegate iGoogleMapDelegate) {
                this.f6315a.m9660a(new GoogleMap(iGoogleMapDelegate));
            }
        }

        public zza(Fragment fragment, IMapFragmentDelegate iMapFragmentDelegate) {
            this.f6318b = (IMapFragmentDelegate) zzx.m8718a((Object) iMapFragmentDelegate);
            this.f6317a = (Fragment) zzx.m8718a((Object) fragment);
        }

        public View m9643a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            try {
                return (View) zze.m9024a(this.f6318b.m9812a(zze.m9023a((Object) layoutInflater), zze.m9023a((Object) viewGroup), bundle));
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void m9644a() {
            try {
                this.f6318b.m9817b();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void m9645a(Activity activity, Bundle bundle, Bundle bundle2) {
            try {
                this.f6318b.m9815a(zze.m9023a((Object) activity), (GoogleMapOptions) bundle.getParcelable("MapOptions"), bundle2);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void m9646a(Bundle bundle) {
            if (bundle == null) {
                try {
                    bundle = new Bundle();
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                }
            }
            Bundle arguments = this.f6317a.getArguments();
            if (arguments != null && arguments.containsKey("MapOptions")) {
                com.google.android.gms.maps.internal.zzx.m10068a(bundle, "MapOptions", arguments.getParcelable("MapOptions"));
            }
            this.f6318b.m9814a(bundle);
        }

        public void m9647a(OnMapReadyCallback onMapReadyCallback) {
            try {
                this.f6318b.m9816a(new C08811(this, onMapReadyCallback));
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void m9648b() {
            try {
                this.f6318b.m9819c();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void m9649b(Bundle bundle) {
            try {
                this.f6318b.m9818b(bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void m9650c() {
            try {
                this.f6318b.m9821d();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void m9651d() {
            try {
                this.f6318b.m9822e();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void m9652e() {
            try {
                this.f6318b.m9823f();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public IMapFragmentDelegate m9653f() {
            return this.f6318b;
        }
    }

    class zzb extends com.google.android.gms.dynamic.zza<zza> {
        protected zzf<zza> f6319a;
        private final Fragment f6320b;
        private Activity f6321c;
        private final List<OnMapReadyCallback> f6322d;

        zzb(Fragment fragment) {
            this.f6322d = new ArrayList();
            this.f6320b = fragment;
        }

        private void m9654a(Activity activity) {
            this.f6321c = activity;
            m9657g();
        }

        protected void m9656a(zzf<zza> com_google_android_gms_dynamic_zzf_com_google_android_gms_maps_MapFragment_zza) {
            this.f6319a = com_google_android_gms_dynamic_zzf_com_google_android_gms_maps_MapFragment_zza;
            m9657g();
        }

        public void m9657g() {
            if (this.f6321c != null && this.f6319a != null && m9011a() == null) {
                try {
                    MapsInitializer.m9658a(this.f6321c);
                    IMapFragmentDelegate b = zzy.m10069a(this.f6321c).m10004b(zze.m9023a(this.f6321c));
                    if (b != null) {
                        this.f6319a.m8991a(new zza(this.f6320b, b));
                        for (OnMapReadyCallback a : this.f6322d) {
                            ((zza) m9011a()).m9647a(a);
                        }
                        this.f6322d.clear();
                    }
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                } catch (GooglePlayServicesNotAvailableException e2) {
                }
            }
        }
    }

    public MapFragment() {
        this.f3120a = new zzb(this);
    }

    protected IMapFragmentDelegate m5605a() {
        this.f3120a.m9657g();
        return this.f3120a.m9011a() == null ? null : ((zza) this.f3120a.m9011a()).m9653f();
    }

    @Deprecated
    public final GoogleMap m5606b() {
        IMapFragmentDelegate a = m5605a();
        if (a == null) {
            return null;
        }
        try {
            IGoogleMapDelegate a2 = a.m9813a();
            if (a2 == null) {
                return null;
            }
            if (this.f3121b == null || this.f3121b.m9596a().asBinder() != a2.asBinder()) {
                this.f3121b = new GoogleMap(a2);
            }
            return this.f3121b;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void onActivityCreated(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(MapFragment.class.getClassLoader());
        }
        super.onActivityCreated(bundle);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f3120a.m9654a(activity);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f3120a.m9013a(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View a = this.f3120a.m9010a(layoutInflater, viewGroup, bundle);
        a.setClickable(true);
        return a;
    }

    public void onDestroy() {
        this.f3120a.m9020e();
        super.onDestroy();
    }

    public void onDestroyView() {
        this.f3120a.m9019d();
        super.onDestroyView();
    }

    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(activity, attributeSet, bundle);
        this.f3120a.m9654a(activity);
        Parcelable a = GoogleMapOptions.m9612a(activity, attributeSet);
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("MapOptions", a);
        this.f3120a.m9012a(activity, bundle2, bundle);
    }

    public void onLowMemory() {
        this.f3120a.m9021f();
        super.onLowMemory();
    }

    public void onPause() {
        this.f3120a.m9018c();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.f3120a.m9016b();
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(MapFragment.class.getClassLoader());
        }
        super.onSaveInstanceState(bundle);
        this.f3120a.m9017b(bundle);
    }

    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
    }
}
