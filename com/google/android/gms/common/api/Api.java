package com.google.android.gms.common.api;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.internal.zzx;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class Api<O extends ApiOptions> {
    private final zza<?, O> f5653a;
    private final zze<?, O> f5654b;
    private final zzc<?> f5655c;
    private final zzf<?> f5656d;
    private final String f5657e;

    public interface ApiOptions {

        public interface HasOptions extends ApiOptions {
        }

        public interface NotRequiredOptions extends ApiOptions {
        }

        public final class NoOptions implements NotRequiredOptions {
            private NoOptions() {
            }
        }

        public interface Optional extends HasOptions, NotRequiredOptions {
        }
    }

    public abstract class zza<T extends zzb, O> {
        public int m8341a() {
            return Integer.MAX_VALUE;
        }

        public abstract T m8342a(Context context, Looper looper, com.google.android.gms.common.internal.zzf com_google_android_gms_common_internal_zzf, O o, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener);

        public List<Scope> m8343a(O o) {
            return Collections.emptyList();
        }
    }

    public interface zzb {
        void m8344a();

        void m8345a(com.google.android.gms.common.api.GoogleApiClient.zza com_google_android_gms_common_api_GoogleApiClient_zza);

        void m8346a(zzp com_google_android_gms_common_internal_zzp);

        void m8347a(zzp com_google_android_gms_common_internal_zzp, Set<Scope> set);

        void m8348a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

        boolean m8349b();

        boolean m8350c();

        IBinder m8351d();
    }

    public final class zzc<C extends zzb> {
    }

    public interface zzd<T extends IInterface> {
        T m8352a(IBinder iBinder);

        String m8353a();

        void m8354a(int i, T t);

        String m8355b();
    }

    public interface zze<T extends zzd, O> {
        int m8356a();

        T m8357a(O o);
    }

    public final class zzf<C extends zzd> {
    }

    public <C extends zzb> Api(String str, zza<C, O> com_google_android_gms_common_api_Api_zza_C__O, zzc<C> com_google_android_gms_common_api_Api_zzc_C) {
        zzx.m8719a((Object) com_google_android_gms_common_api_Api_zza_C__O, (Object) "Cannot construct an Api with a null ClientBuilder");
        zzx.m8719a((Object) com_google_android_gms_common_api_Api_zzc_C, (Object) "Cannot construct an Api with a null ClientKey");
        this.f5657e = str;
        this.f5653a = com_google_android_gms_common_api_Api_zza_C__O;
        this.f5654b = null;
        this.f5655c = com_google_android_gms_common_api_Api_zzc_C;
        this.f5656d = null;
    }

    public zza<?, O> m8358a() {
        zzx.m8723a(this.f5653a != null, (Object) "This API was constructed with a SimpleClientBuilder. Use getSimpleClientBuilder");
        return this.f5653a;
    }

    public zze<?, O> m8359b() {
        zzx.m8723a(this.f5654b != null, (Object) "This API was constructed with a ClientBuilder. Use getClientBuilder");
        return this.f5654b;
    }

    public zzc<?> m8360c() {
        zzx.m8723a(this.f5655c != null, (Object) "This API was constructed with a SimpleClientKey. Use getSimpleClientKey");
        return this.f5655c;
    }

    public boolean m8361d() {
        return this.f5656d != null;
    }

    public String m8362e() {
        return this.f5657e;
    }
}
