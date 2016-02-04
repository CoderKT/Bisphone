package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.signin.internal.zzh;
import com.google.android.gms.signin.internal.zzi;
import java.util.concurrent.Executors;

public final class zzqu {
    public static final zzc<zzi> f6148a;
    public static final zzc<zzi> f6149b;
    public static final zza<zzi, zzqx> f6150c;
    static final zza<zzi, NoOptions> f6151d;
    public static final Scope f6152e;
    public static final Scope f6153f;
    public static final Api<zzqx> f6154g;
    public static final Api<NoOptions> f6155h;
    public static final zzqv f6156i;

    /* renamed from: com.google.android.gms.internal.zzqu.1 */
    final class C08691 extends zza<zzi, zzqx> {
        C08691() {
        }

        public zzi m9316a(Context context, Looper looper, zzf com_google_android_gms_common_internal_zzf, zzqx com_google_android_gms_internal_zzqx, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzi(context, looper, true, com_google_android_gms_common_internal_zzf, com_google_android_gms_internal_zzqx == null ? zzqx.f6163a : com_google_android_gms_internal_zzqx, connectionCallbacks, onConnectionFailedListener, Executors.newSingleThreadExecutor());
        }
    }

    /* renamed from: com.google.android.gms.internal.zzqu.2 */
    final class C08702 extends zza<zzi, NoOptions> {
        C08702() {
        }

        public /* synthetic */ zzb m9317a(Context context, Looper looper, zzf com_google_android_gms_common_internal_zzf, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return m9318a(context, looper, com_google_android_gms_common_internal_zzf, (NoOptions) obj, connectionCallbacks, onConnectionFailedListener);
        }

        public zzi m9318a(Context context, Looper looper, zzf com_google_android_gms_common_internal_zzf, NoOptions noOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzi(context, looper, false, com_google_android_gms_common_internal_zzf, zzqx.f6163a, connectionCallbacks, onConnectionFailedListener, Executors.newSingleThreadExecutor());
        }
    }

    static {
        f6148a = new zzc();
        f6149b = new zzc();
        f6150c = new C08691();
        f6151d = new C08702();
        f6152e = new Scope("profile");
        f6153f = new Scope("email");
        f6154g = new Api("SignIn.API", f6150c, f6148a);
        f6155h = new Api("SignIn.INTERNAL_API", f6151d, f6149b);
        f6156i = new zzh();
    }
}
