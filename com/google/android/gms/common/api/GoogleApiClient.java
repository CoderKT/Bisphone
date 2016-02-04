package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.ApiOptions.NotRequiredOptions;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzli;
import com.google.android.gms.internal.zzlp;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzqu;
import com.google.android.gms.internal.zzqw;
import com.google.android.gms.internal.zzqx;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class GoogleApiClient {

    public interface ConnectionCallbacks {
        void m5631a(Bundle bundle);

        void m5632b(int i);
    }

    public interface OnConnectionFailedListener {
        void m5633a(ConnectionResult connectionResult);
    }

    public final class Builder {
        private Account f5660a;
        private final Set<Scope> f5661b;
        private int f5662c;
        private View f5663d;
        private String f5664e;
        private String f5665f;
        private final Map<Api<?>, com.google.android.gms.common.internal.zzf.zza> f5666g;
        private final Context f5667h;
        private final Map<Api<?>, ApiOptions> f5668i;
        private FragmentActivity f5669j;
        private int f5670k;
        private OnConnectionFailedListener f5671l;
        private Looper f5672m;
        private GoogleApiAvailability f5673n;
        private com.google.android.gms.common.api.Api.zza<? extends zzqw, zzqx> f5674o;
        private final ArrayList<ConnectionCallbacks> f5675p;
        private final ArrayList<OnConnectionFailedListener> f5676q;
        private zzqx f5677r;

        /* renamed from: com.google.android.gms.common.api.GoogleApiClient.Builder.1 */
        class C06491 implements Runnable {
            final /* synthetic */ GoogleApiClient f5658a;
            final /* synthetic */ Builder f5659b;

            C06491(Builder builder, GoogleApiClient googleApiClient) {
                this.f5659b = builder;
                this.f5658a = googleApiClient;
            }

            public void run() {
                if (!this.f5659b.f5669j.isFinishing() && !this.f5659b.f5669j.getSupportFragmentManager().m360e()) {
                    this.f5659b.m8366a(zzlp.m9220b(this.f5659b.f5669j), this.f5658a);
                }
            }
        }

        public Builder(Context context) {
            this.f5661b = new HashSet();
            this.f5666g = new zzme();
            this.f5668i = new zzme();
            this.f5670k = -1;
            this.f5673n = GoogleApiAvailability.m8308a();
            this.f5674o = zzqu.f6150c;
            this.f5675p = new ArrayList();
            this.f5676q = new ArrayList();
            this.f5667h = context;
            this.f5672m = context.getMainLooper();
            this.f5664e = context.getPackageName();
            this.f5665f = context.getClass().getName();
        }

        private void m8366a(zzlp com_google_android_gms_internal_zzlp, GoogleApiClient googleApiClient) {
            com_google_android_gms_internal_zzlp.m9225a(this.f5670k, googleApiClient, this.f5671l);
        }

        private GoogleApiClient m8367c() {
            GoogleApiClient com_google_android_gms_internal_zzli = new zzli(this.f5667h.getApplicationContext(), this.f5672m, m8371a(), this.f5673n, this.f5674o, this.f5668i, this.f5675p, this.f5676q, this.f5670k);
            zzlp a = zzlp.m9215a(this.f5669j);
            if (a == null) {
                new Handler(this.f5667h.getMainLooper()).post(new C06491(this, com_google_android_gms_internal_zzli));
            } else {
                m8366a(a, com_google_android_gms_internal_zzli);
            }
            return com_google_android_gms_internal_zzli;
        }

        public Builder m8368a(Api<? extends NotRequiredOptions> api) {
            zzx.m8719a((Object) api, (Object) "Api must not be null");
            this.f5668i.put(api, null);
            this.f5661b.addAll(api.m8358a().m8343a(null));
            return this;
        }

        public Builder m8369a(ConnectionCallbacks connectionCallbacks) {
            zzx.m8719a((Object) connectionCallbacks, (Object) "Listener must not be null");
            this.f5675p.add(connectionCallbacks);
            return this;
        }

        public Builder m8370a(OnConnectionFailedListener onConnectionFailedListener) {
            zzx.m8719a((Object) onConnectionFailedListener, (Object) "Listener must not be null");
            this.f5676q.add(onConnectionFailedListener);
            return this;
        }

        public zzf m8371a() {
            if (this.f5668i.containsKey(zzqu.f6154g)) {
                zzx.m8723a(this.f5677r == null, (Object) "SignIn.API can't be used in conjunction with requestServerAuthCode.");
                this.f5677r = (zzqx) this.f5668i.get(zzqu.f6154g);
            }
            return new zzf(this.f5660a, this.f5661b, this.f5666g, this.f5662c, this.f5663d, this.f5664e, this.f5665f, this.f5677r != null ? this.f5677r : zzqx.f6163a);
        }

        public GoogleApiClient m8372b() {
            zzx.m8727b(!this.f5668i.isEmpty(), "must call addApi() to add at least one API");
            return this.f5670k >= 0 ? m8367c() : new zzli(this.f5667h, this.f5672m, m8371a(), this.f5673n, this.f5674o, this.f5668i, this.f5675p, this.f5676q, -1);
        }
    }

    public interface ServerAuthCodeCallbacks {

        public class CheckResult {
            private boolean f5678a;
            private Set<Scope> f5679b;

            public boolean m8373a() {
                return this.f5678a;
            }

            public Set<Scope> m8374b() {
                return this.f5679b;
            }
        }

        CheckResult m8375a(String str, Set<Scope> set);

        boolean m8376a(String str, String str2);
    }

    public interface zza {
        void m8377a(ConnectionResult connectionResult);

        void m8378b(ConnectionResult connectionResult);
    }

    public Looper m8379a() {
        throw new UnsupportedOperationException();
    }

    public <C extends zzb> C m8380a(zzc<C> com_google_android_gms_common_api_Api_zzc_C) {
        throw new UnsupportedOperationException();
    }

    public <A extends zzb, T extends com.google.android.gms.internal.zzlb.zza<? extends Result, A>> T m8381a(T t) {
        throw new UnsupportedOperationException();
    }

    public abstract void m8382a(OnConnectionFailedListener onConnectionFailedListener);

    public abstract void m8383a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public abstract void m8384b();

    public abstract void m8385b(OnConnectionFailedListener onConnectionFailedListener);

    public abstract void m8386c();

    public int m8387d() {
        throw new UnsupportedOperationException();
    }
}
