package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.signin.internal.AuthAccountResult;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzlg implements zzlj {
    private final zzli f6024a;
    private final Lock f6025b;
    private final Context f6026c;
    private final GoogleApiAvailability f6027d;
    private ConnectionResult f6028e;
    private int f6029f;
    private int f6030g;
    private boolean f6031h;
    private int f6032i;
    private final Bundle f6033j;
    private final Set<com.google.android.gms.common.api.Api.zzc> f6034k;
    private zzqw f6035l;
    private int f6036m;
    private boolean f6037n;
    private boolean f6038o;
    private zzp f6039p;
    private boolean f6040q;
    private boolean f6041r;
    private final com.google.android.gms.common.internal.zzf f6042s;
    private final Map<Api<?>, Integer> f6043t;
    private final com.google.android.gms.common.api.Api.zza<? extends zzqw, zzqx> f6044u;
    private ArrayList<Future<?>> f6045v;

    /* renamed from: com.google.android.gms.internal.zzlg.1 */
    class C08551 implements Runnable {
        final /* synthetic */ zzlg f6001a;

        C08551(zzlg com_google_android_gms_internal_zzlg) {
            this.f6001a = com_google_android_gms_internal_zzlg;
        }

        public void run() {
            this.f6001a.f6027d.m8317b(this.f6001a.f6026c);
        }
    }

    class zza extends com.google.android.gms.signin.internal.zzb {
        private final WeakReference<zzlg> f6005a;

        /* renamed from: com.google.android.gms.internal.zzlg.zza.1 */
        class C08561 extends zzb {
            final /* synthetic */ zzlg f6002a;
            final /* synthetic */ ConnectionResult f6003b;
            final /* synthetic */ zza f6004c;

            C08561(zza com_google_android_gms_internal_zzlg_zza, zzlj com_google_android_gms_internal_zzlj, zzlg com_google_android_gms_internal_zzlg, ConnectionResult connectionResult) {
                this.f6004c = com_google_android_gms_internal_zzlg_zza;
                this.f6002a = com_google_android_gms_internal_zzlg;
                this.f6003b = connectionResult;
                super(com_google_android_gms_internal_zzlj);
            }

            public void m9079a() {
                this.f6002a.m9104a(this.f6003b);
            }
        }

        zza(zzlg com_google_android_gms_internal_zzlg) {
            this.f6005a = new WeakReference(com_google_android_gms_internal_zzlg);
        }

        public void m9089a(ConnectionResult connectionResult, AuthAccountResult authAccountResult) {
            zzlg com_google_android_gms_internal_zzlg = (zzlg) this.f6005a.get();
            if (com_google_android_gms_internal_zzlg != null) {
                com_google_android_gms_internal_zzlg.f6024a.m9187a(new C08561(this, com_google_android_gms_internal_zzlg, com_google_android_gms_internal_zzlg, connectionResult));
            }
        }
    }

    class zzb extends com.google.android.gms.common.internal.zzt.zza {
        private final WeakReference<zzlg> f6009a;

        /* renamed from: com.google.android.gms.internal.zzlg.zzb.1 */
        class C08571 extends zzb {
            final /* synthetic */ zzlg f6006a;
            final /* synthetic */ ResolveAccountResponse f6007b;
            final /* synthetic */ zzb f6008c;

            C08571(zzb com_google_android_gms_internal_zzlg_zzb, zzlj com_google_android_gms_internal_zzlj, zzlg com_google_android_gms_internal_zzlg, ResolveAccountResponse resolveAccountResponse) {
                this.f6008c = com_google_android_gms_internal_zzlg_zzb;
                this.f6006a = com_google_android_gms_internal_zzlg;
                this.f6007b = resolveAccountResponse;
                super(com_google_android_gms_internal_zzlj);
            }

            public void m9090a() {
                this.f6006a.m9105a(this.f6007b);
            }
        }

        zzb(zzlg com_google_android_gms_internal_zzlg) {
            this.f6009a = new WeakReference(com_google_android_gms_internal_zzlg);
        }

        public void m9091a(ResolveAccountResponse resolveAccountResponse) {
            zzlg com_google_android_gms_internal_zzlg = (zzlg) this.f6009a.get();
            if (com_google_android_gms_internal_zzlg != null) {
                com_google_android_gms_internal_zzlg.f6024a.m9187a(new C08571(this, com_google_android_gms_internal_zzlg, com_google_android_gms_internal_zzlg, resolveAccountResponse));
            }
        }
    }

    abstract class zzi implements Runnable {
        final /* synthetic */ zzlg f6010b;

        private zzi(zzlg com_google_android_gms_internal_zzlg) {
            this.f6010b = com_google_android_gms_internal_zzlg;
        }

        protected abstract void m9092a();

        public void run() {
            this.f6010b.f6025b.lock();
            try {
                if (!Thread.interrupted()) {
                    m9092a();
                    this.f6010b.f6025b.unlock();
                }
            } catch (RuntimeException e) {
                this.f6010b.f6024a.m9189a(e);
            } finally {
                this.f6010b.f6025b.unlock();
            }
        }
    }

    class zzc extends zzi {
        final /* synthetic */ zzlg f6011a;

        private zzc(zzlg com_google_android_gms_internal_zzlg) {
            this.f6011a = com_google_android_gms_internal_zzlg;
            super(null);
        }

        public void m9093a() {
            this.f6011a.f6035l.m9319a(this.f6011a.f6039p, this.f6011a.f6024a.f6065f, new zza(this.f6011a));
        }
    }

    class zzd implements com.google.android.gms.common.api.GoogleApiClient.zza {
        private final WeakReference<zzlg> f6012a;
        private final Api<?> f6013b;
        private final int f6014c;

        public zzd(zzlg com_google_android_gms_internal_zzlg, Api<?> api, int i) {
            this.f6012a = new WeakReference(com_google_android_gms_internal_zzlg);
            this.f6013b = api;
            this.f6014c = i;
        }

        public void m9094a(ConnectionResult connectionResult) {
            boolean z = false;
            zzlg com_google_android_gms_internal_zzlg = (zzlg) this.f6012a.get();
            if (com_google_android_gms_internal_zzlg != null) {
                if (Looper.myLooper() == com_google_android_gms_internal_zzlg.f6024a.m9182a()) {
                    z = true;
                }
                zzx.m8723a(z, (Object) "onReportServiceBinding must be called on the GoogleApiClient handler thread");
                com_google_android_gms_internal_zzlg.f6025b.lock();
                try {
                    if (com_google_android_gms_internal_zzlg.m9114b(0)) {
                        if (!connectionResult.m8303b()) {
                            com_google_android_gms_internal_zzlg.m9113b(connectionResult, this.f6013b, this.f6014c);
                        }
                        if (com_google_android_gms_internal_zzlg.m9123e()) {
                            com_google_android_gms_internal_zzlg.m9126f();
                        }
                        com_google_android_gms_internal_zzlg.f6025b.unlock();
                    }
                } finally {
                    com_google_android_gms_internal_zzlg.f6025b.unlock();
                }
            }
        }

        public void m9095b(ConnectionResult connectionResult) {
            boolean z = true;
            zzlg com_google_android_gms_internal_zzlg = (zzlg) this.f6012a.get();
            if (com_google_android_gms_internal_zzlg != null) {
                if (Looper.myLooper() != com_google_android_gms_internal_zzlg.f6024a.m9182a()) {
                    z = false;
                }
                zzx.m8723a(z, (Object) "onReportAccountValidation must be called on the GoogleApiClient handler thread");
                com_google_android_gms_internal_zzlg.f6025b.lock();
                try {
                    if (com_google_android_gms_internal_zzlg.m9114b(1)) {
                        if (!connectionResult.m8303b()) {
                            com_google_android_gms_internal_zzlg.m9113b(connectionResult, this.f6013b, this.f6014c);
                        }
                        if (com_google_android_gms_internal_zzlg.m9123e()) {
                            com_google_android_gms_internal_zzlg.m9130h();
                        }
                        com_google_android_gms_internal_zzlg.f6025b.unlock();
                    }
                } finally {
                    com_google_android_gms_internal_zzlg.f6025b.unlock();
                }
            }
        }
    }

    class zze extends zzi {
        final /* synthetic */ zzlg f6017a;
        private final Map<com.google.android.gms.common.api.Api.zzb, com.google.android.gms.common.api.GoogleApiClient.zza> f6018c;

        /* renamed from: com.google.android.gms.internal.zzlg.zze.1 */
        class C08581 extends zzb {
            final /* synthetic */ ConnectionResult f6015a;
            final /* synthetic */ zze f6016b;

            C08581(zze com_google_android_gms_internal_zzlg_zze, zzlj com_google_android_gms_internal_zzlj, ConnectionResult connectionResult) {
                this.f6016b = com_google_android_gms_internal_zzlg_zze;
                this.f6015a = connectionResult;
                super(com_google_android_gms_internal_zzlj);
            }

            public void m9096a() {
                this.f6016b.f6017a.m9122d(this.f6015a);
            }
        }

        public zze(zzlg com_google_android_gms_internal_zzlg, Map<com.google.android.gms.common.api.Api.zzb, com.google.android.gms.common.api.GoogleApiClient.zza> map) {
            this.f6017a = com_google_android_gms_internal_zzlg;
            super(null);
            this.f6018c = map;
        }

        public void m9097a() {
            int a = this.f6017a.f6027d.m8310a(this.f6017a.f6026c);
            if (a != 0) {
                this.f6017a.f6024a.m9187a(new C08581(this, this.f6017a, new ConnectionResult(a, null)));
                return;
            }
            if (this.f6017a.f6037n) {
                this.f6017a.f6035l.m9323h();
            }
            for (com.google.android.gms.common.api.Api.zzb com_google_android_gms_common_api_Api_zzb : this.f6018c.keySet()) {
                com_google_android_gms_common_api_Api_zzb.m8345a((com.google.android.gms.common.api.GoogleApiClient.zza) this.f6018c.get(com_google_android_gms_common_api_Api_zzb));
            }
        }
    }

    class zzf extends zzi {
        final /* synthetic */ zzlg f6019a;
        private final ArrayList<com.google.android.gms.common.api.Api.zzb> f6020c;

        public zzf(zzlg com_google_android_gms_internal_zzlg, ArrayList<com.google.android.gms.common.api.Api.zzb> arrayList) {
            this.f6019a = com_google_android_gms_internal_zzlg;
            super(null);
            this.f6020c = arrayList;
        }

        public void m9098a() {
            Set set = this.f6019a.f6024a.f6065f;
            Set h = set.isEmpty() ? this.f6019a.m9139m() : set;
            Iterator it = this.f6020c.iterator();
            while (it.hasNext()) {
                ((com.google.android.gms.common.api.Api.zzb) it.next()).m8347a(this.f6019a.f6039p, h);
            }
        }
    }

    class zzg implements ConnectionCallbacks, OnConnectionFailedListener {
        final /* synthetic */ zzlg f6021a;

        private zzg(zzlg com_google_android_gms_internal_zzlg) {
            this.f6021a = com_google_android_gms_internal_zzlg;
        }

        public void m9099a(Bundle bundle) {
            this.f6021a.f6035l.m9321a(new zzb(this.f6021a));
        }

        public void m9100a(ConnectionResult connectionResult) {
            this.f6021a.f6025b.lock();
            try {
                if (this.f6021a.m9120c(connectionResult)) {
                    this.f6021a.m9135k();
                    this.f6021a.m9131i();
                } else {
                    this.f6021a.m9122d(connectionResult);
                }
                this.f6021a.f6025b.unlock();
            } catch (Throwable th) {
                this.f6021a.f6025b.unlock();
            }
        }

        public void m9101b(int i) {
        }
    }

    class zzh extends zzi {
        final /* synthetic */ zzlg f6022a;
        private final ArrayList<com.google.android.gms.common.api.Api.zzb> f6023c;

        public zzh(zzlg com_google_android_gms_internal_zzlg, ArrayList<com.google.android.gms.common.api.Api.zzb> arrayList) {
            this.f6022a = com_google_android_gms_internal_zzlg;
            super(null);
            this.f6023c = arrayList;
        }

        public void m9102a() {
            Iterator it = this.f6023c.iterator();
            while (it.hasNext()) {
                ((com.google.android.gms.common.api.Api.zzb) it.next()).m8346a(this.f6022a.f6039p);
            }
        }
    }

    public zzlg(zzli com_google_android_gms_internal_zzli, com.google.android.gms.common.internal.zzf com_google_android_gms_common_internal_zzf, Map<Api<?>, Integer> map, GoogleApiAvailability googleApiAvailability, com.google.android.gms.common.api.Api.zza<? extends zzqw, zzqx> com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzqw__com_google_android_gms_internal_zzqx, Lock lock, Context context) {
        this.f6030g = 0;
        this.f6031h = false;
        this.f6033j = new Bundle();
        this.f6034k = new HashSet();
        this.f6045v = new ArrayList();
        this.f6024a = com_google_android_gms_internal_zzli;
        this.f6042s = com_google_android_gms_common_internal_zzf;
        this.f6043t = map;
        this.f6027d = googleApiAvailability;
        this.f6044u = com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzqw__com_google_android_gms_internal_zzqx;
        this.f6025b = lock;
        this.f6026c = context;
    }

    private void m9104a(ConnectionResult connectionResult) {
        if (!m9114b(2)) {
            return;
        }
        if (connectionResult.m8303b()) {
            m9131i();
        } else if (m9120c(connectionResult)) {
            m9135k();
            m9131i();
        } else {
            m9122d(connectionResult);
        }
    }

    private void m9105a(ResolveAccountResponse resolveAccountResponse) {
        if (m9114b(0)) {
            ConnectionResult b = resolveAccountResponse.m8424b();
            if (b.m8303b()) {
                this.f6039p = resolveAccountResponse.m8423a();
                this.f6038o = true;
                this.f6040q = resolveAccountResponse.m8425c();
                this.f6041r = resolveAccountResponse.m8426d();
                m9126f();
            } else if (m9120c(b)) {
                m9135k();
                m9126f();
            } else {
                m9122d(b);
            }
        }
    }

    private void m9109a(boolean z) {
        if (this.f6035l != null) {
            if (this.f6035l.m8349b() && z) {
                this.f6035l.m9322g();
            }
            this.f6035l.m8344a();
            this.f6039p = null;
        }
    }

    private boolean m9110a(int i, int i2, ConnectionResult connectionResult) {
        return (i2 != 1 || m9115b(connectionResult)) ? this.f6028e == null || i < this.f6029f : false;
    }

    private void m9113b(ConnectionResult connectionResult, Api<?> api, int i) {
        if (i != 2) {
            int a = api.m8358a().m8341a();
            if (m9110a(a, i, connectionResult)) {
                this.f6028e = connectionResult;
                this.f6029f = a;
            }
        }
        this.f6024a.f6064e.put(api.m8360c(), connectionResult);
    }

    private boolean m9114b(int i) {
        if (this.f6030g == i) {
            return true;
        }
        Log.i("GoogleApiClientConnecting", this.f6024a.m9203m());
        Log.wtf("GoogleApiClientConnecting", "GoogleApiClient connecting is in step " + m9117c(this.f6030g) + " but received callback for step " + m9117c(i), new Exception());
        m9122d(new ConnectionResult(8, null));
        return false;
    }

    private boolean m9115b(ConnectionResult connectionResult) {
        return connectionResult.m8302a() || this.f6027d.m8316b(connectionResult.m8304c()) != null;
    }

    private String m9117c(int i) {
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                return "STEP_GETTING_SERVICE_BINDINGS";
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return "STEP_VALIDATING_ACCOUNT";
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                return "STEP_AUTHENTICATING";
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                return "STEP_GETTING_REMOTE_SERVICE";
            default:
                return "UNKNOWN";
        }
    }

    private boolean m9120c(ConnectionResult connectionResult) {
        return this.f6036m != 2 ? this.f6036m == 1 && !connectionResult.m8302a() : true;
    }

    private void m9122d(ConnectionResult connectionResult) {
        m9137l();
        m9109a(!connectionResult.m8302a());
        this.f6024a.f6064e.clear();
        this.f6024a.m9185a(connectionResult);
        if (!this.f6027d.m8314a(this.f6026c, connectionResult.m8304c())) {
            this.f6024a.m9202l();
        }
        if (!(this.f6031h || this.f6024a.m9200j())) {
            this.f6024a.f6060a.m8581a(connectionResult);
        }
        this.f6031h = false;
        this.f6024a.f6060a.m8578a();
    }

    private boolean m9123e() {
        this.f6032i--;
        if (this.f6032i > 0) {
            return false;
        }
        if (this.f6032i < 0) {
            Log.i("GoogleApiClientConnecting", this.f6024a.m9203m());
            Log.wtf("GoogleApiClientConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
            m9122d(new ConnectionResult(8, null));
            return false;
        } else if (this.f6028e == null) {
            return true;
        } else {
            m9122d(this.f6028e);
            return false;
        }
    }

    private void m9126f() {
        if (this.f6032i == 0) {
            if (!this.f6037n) {
                m9131i();
            } else if (this.f6038o) {
                m9128g();
            }
        }
    }

    private void m9128g() {
        ArrayList arrayList = new ArrayList();
        this.f6030g = 1;
        this.f6032i = this.f6024a.f6063d.size();
        for (com.google.android.gms.common.api.Api.zzc com_google_android_gms_common_api_Api_zzc : this.f6024a.f6063d.keySet()) {
            if (!this.f6024a.f6064e.containsKey(com_google_android_gms_common_api_Api_zzc)) {
                arrayList.add(this.f6024a.f6063d.get(com_google_android_gms_common_api_Api_zzc));
            } else if (m9123e()) {
                m9130h();
            }
        }
        if (!arrayList.isEmpty()) {
            this.f6045v.add(zzlk.m9204a().submit(new zzh(this, arrayList)));
        }
    }

    private void m9130h() {
        this.f6030g = 2;
        this.f6024a.f6065f = m9139m();
        this.f6045v.add(zzlk.m9204a().submit(new zzc()));
    }

    private void m9131i() {
        ArrayList arrayList = new ArrayList();
        this.f6030g = 3;
        this.f6032i = this.f6024a.f6063d.size();
        for (com.google.android.gms.common.api.Api.zzc com_google_android_gms_common_api_Api_zzc : this.f6024a.f6063d.keySet()) {
            if (!this.f6024a.f6064e.containsKey(com_google_android_gms_common_api_Api_zzc)) {
                arrayList.add(this.f6024a.f6063d.get(com_google_android_gms_common_api_Api_zzc));
            } else if (m9123e()) {
                m9133j();
            }
        }
        if (!arrayList.isEmpty()) {
            this.f6045v.add(zzlk.m9204a().submit(new zzf(this, arrayList)));
        }
    }

    private void m9133j() {
        this.f6024a.m9198h();
        zzlk.m9204a().execute(new C08551(this));
        if (this.f6035l != null) {
            if (this.f6040q) {
                this.f6035l.m9320a(this.f6039p, this.f6041r);
            }
            m9109a(false);
        }
        for (com.google.android.gms.common.api.Api.zzc com_google_android_gms_common_api_Api_zzc : this.f6024a.f6064e.keySet()) {
            ((com.google.android.gms.common.api.Api.zzb) this.f6024a.f6063d.get(com_google_android_gms_common_api_Api_zzc)).m8344a();
        }
        if (this.f6031h) {
            this.f6031h = false;
            m9146b();
            return;
        }
        this.f6024a.f6060a.m8580a(this.f6033j.isEmpty() ? null : this.f6033j);
    }

    private void m9135k() {
        this.f6037n = false;
        this.f6024a.f6065f = Collections.emptySet();
        for (com.google.android.gms.common.api.Api.zzc com_google_android_gms_common_api_Api_zzc : this.f6034k) {
            if (!this.f6024a.f6064e.containsKey(com_google_android_gms_common_api_Api_zzc)) {
                this.f6024a.f6064e.put(com_google_android_gms_common_api_Api_zzc, new ConnectionResult(17, null));
            }
        }
    }

    private void m9137l() {
        Iterator it = this.f6045v.iterator();
        while (it.hasNext()) {
            ((Future) it.next()).cancel(true);
        }
        this.f6045v.clear();
    }

    private Set<Scope> m9139m() {
        Set<Scope> hashSet = new HashSet(this.f6042s.m8539c());
        Map e = this.f6042s.m8541e();
        for (Api api : e.keySet()) {
            if (!this.f6024a.f6064e.containsKey(api.m8360c())) {
                hashSet.addAll(((com.google.android.gms.common.internal.zzf.zza) e.get(api)).f5744a);
            }
        }
        return hashSet;
    }

    public <A extends com.google.android.gms.common.api.Api.zzb, T extends com.google.android.gms.internal.zzlb.zza<? extends Result, A>> T m9141a(T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }

    public void m9142a() {
        this.f6024a.f6060a.m8584b();
        this.f6024a.f6064e.clear();
        this.f6031h = false;
        this.f6037n = false;
        this.f6028e = null;
        this.f6030g = 0;
        this.f6036m = 2;
        this.f6038o = false;
        this.f6040q = false;
        Map hashMap = new HashMap();
        int i = 0;
        for (Api api : this.f6043t.keySet()) {
            com.google.android.gms.common.api.Api.zzb com_google_android_gms_common_api_Api_zzb = (com.google.android.gms.common.api.Api.zzb) this.f6024a.f6063d.get(api.m8360c());
            int intValue = ((Integer) this.f6043t.get(api)).intValue();
            int i2 = (api.m8358a().m8341a() == 1 ? 1 : 0) | i;
            if (com_google_android_gms_common_api_Api_zzb.m8350c()) {
                this.f6037n = true;
                if (intValue < this.f6036m) {
                    this.f6036m = intValue;
                }
                if (intValue != 0) {
                    this.f6034k.add(api.m8360c());
                }
            }
            hashMap.put(com_google_android_gms_common_api_Api_zzb, new zzd(this, api, intValue));
            i = i2;
        }
        if (i != 0) {
            this.f6037n = false;
        }
        if (this.f6037n) {
            this.f6042s.m8537a(Integer.valueOf(this.f6024a.m9194d()));
            ConnectionCallbacks com_google_android_gms_internal_zzlg_zzg = new zzg();
            this.f6035l = (zzqw) this.f6044u.m8342a(this.f6026c, this.f6024a.m9182a(), this.f6042s, this.f6042s.m8544h(), com_google_android_gms_internal_zzlg_zzg, com_google_android_gms_internal_zzlg_zzg);
        }
        this.f6032i = this.f6024a.f6063d.size();
        this.f6045v.add(zzlk.m9204a().submit(new zze(this, hashMap)));
    }

    public void m9143a(int i) {
        m9122d(new ConnectionResult(8, null));
    }

    public void m9144a(Bundle bundle) {
        if (m9114b(3)) {
            if (bundle != null) {
                this.f6033j.putAll(bundle);
            }
            if (m9123e()) {
                m9133j();
            }
        }
    }

    public void m9145a(ConnectionResult connectionResult, Api<?> api, int i) {
        if (m9114b(3)) {
            m9113b(connectionResult, api, i);
            if (m9123e()) {
                m9133j();
            }
        }
    }

    public void m9146b() {
        Iterator it = this.f6024a.f6061b.iterator();
        while (it.hasNext()) {
            zzf com_google_android_gms_internal_zzli_zzf = (zzf) it.next();
            if (com_google_android_gms_internal_zzli_zzf.m9044d() != 1) {
                com_google_android_gms_internal_zzli_zzf.m9046g();
                it.remove();
            }
        }
        this.f6024a.m9195e();
        if (this.f6028e != null || this.f6024a.f6061b.isEmpty()) {
            m9137l();
            m9109a(true);
            this.f6024a.f6064e.clear();
            this.f6024a.m9185a(null);
            this.f6024a.f6060a.m8578a();
            return;
        }
        this.f6031h = true;
    }

    public void m9147c() {
        this.f6031h = false;
    }

    public String m9148d() {
        return "CONNECTING";
    }
}
