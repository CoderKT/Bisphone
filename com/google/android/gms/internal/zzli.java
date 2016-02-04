package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzk;
import com.google.android.gms.common.internal.zzx;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import se.emilsjolander.stickylistheaders.C1128R;

public final class zzli extends GoogleApiClient {
    private final com.google.android.gms.common.internal.zzk.zza f6059A;
    final zzk f6060a;
    final Queue<zzf<?>> f6061b;
    zzd f6062c;
    final Map<com.google.android.gms.common.api.Api.zzc<?>, com.google.android.gms.common.api.Api.zzb> f6063d;
    final Map<com.google.android.gms.common.api.Api.zzc<?>, ConnectionResult> f6064e;
    Set<Scope> f6065f;
    final com.google.android.gms.common.internal.zzf f6066g;
    final Map<Api<?>, Integer> f6067h;
    final com.google.android.gms.common.api.Api.zza<? extends zzqw, zzqx> f6068i;
    final Set<zzf<?>> f6069j;
    private final Lock f6070k;
    private final Condition f6071l;
    private final int f6072m;
    private final Context f6073n;
    private final Looper f6074o;
    private volatile boolean f6075p;
    private long f6076q;
    private long f6077r;
    private final zza f6078s;
    private final GoogleApiAvailability f6079t;
    private volatile zzlj f6080u;
    private ConnectionResult f6081v;
    private final Set<zzlm<?>> f6082w;
    private com.google.android.gms.common.api.zza f6083x;
    private final zze f6084y;
    private final ConnectionCallbacks f6085z;

    interface zzf<A extends com.google.android.gms.common.api.Api.zzb> {
        Integer m9037a();

        void m9038a(A a);

        void m9039a(Status status);

        void m9040a(zze com_google_android_gms_internal_zzli_zze);

        com.google.android.gms.common.api.Api.zzc<A> m9041b();

        void m9042b(Status status);

        void m9043c();

        int m9044d();

        boolean m9045f();

        void m9046g();
    }

    abstract class zzb {
        private final zzlj f5998a;

        protected zzb(zzlj com_google_android_gms_internal_zzlj) {
            this.f5998a = com_google_android_gms_internal_zzlj;
        }

        protected abstract void m9059a();

        public final void m9060a(zzli com_google_android_gms_internal_zzli) {
            com_google_android_gms_internal_zzli.f6070k.lock();
            try {
                if (com_google_android_gms_internal_zzli.f6080u == this.f5998a) {
                    m9059a();
                    com_google_android_gms_internal_zzli.f6070k.unlock();
                }
            } finally {
                com_google_android_gms_internal_zzli.f6070k.unlock();
            }
        }
    }

    interface zze {
        void m9157a(zzf<?> com_google_android_gms_internal_zzli_zzf_);
    }

    /* renamed from: com.google.android.gms.internal.zzli.1 */
    class C08591 implements zze {
        final /* synthetic */ zzli f6047a;

        C08591(zzli com_google_android_gms_internal_zzli) {
            this.f6047a = com_google_android_gms_internal_zzli;
        }

        public void m9158a(zzf<?> com_google_android_gms_internal_zzli_zzf_) {
            this.f6047a.f6069j.remove(com_google_android_gms_internal_zzli_zzf_);
            if (com_google_android_gms_internal_zzli_zzf_.m9037a() != null && this.f6047a.f6083x != null) {
                this.f6047a.f6083x.m8404a(com_google_android_gms_internal_zzli_zzf_.m9037a().intValue());
            }
        }
    }

    /* renamed from: com.google.android.gms.internal.zzli.2 */
    class C08602 implements ConnectionCallbacks {
        final /* synthetic */ zzli f6048a;

        C08602(zzli com_google_android_gms_internal_zzli) {
            this.f6048a = com_google_android_gms_internal_zzli;
        }

        public void m9159a(Bundle bundle) {
            this.f6048a.f6070k.lock();
            try {
                this.f6048a.f6080u.m9065a(bundle);
            } finally {
                this.f6048a.f6070k.unlock();
            }
        }

        public void m9160b(int i) {
            this.f6048a.f6070k.lock();
            try {
                this.f6048a.f6080u.m9064a(i);
            } finally {
                this.f6048a.f6070k.unlock();
            }
        }
    }

    /* renamed from: com.google.android.gms.internal.zzli.3 */
    class C08613 implements com.google.android.gms.common.internal.zzk.zza {
        final /* synthetic */ zzli f6049a;

        C08613(zzli com_google_android_gms_internal_zzli) {
            this.f6049a = com_google_android_gms_internal_zzli;
        }

        public boolean m9161b() {
            return this.f6049a.m9199i();
        }

        public Bundle m9162n() {
            return null;
        }
    }

    /* renamed from: com.google.android.gms.internal.zzli.4 */
    class C08624 implements OnConnectionFailedListener {
        final /* synthetic */ Api f6050a;
        final /* synthetic */ int f6051b;
        final /* synthetic */ zzli f6052c;

        C08624(zzli com_google_android_gms_internal_zzli, Api api, int i) {
            this.f6052c = com_google_android_gms_internal_zzli;
            this.f6050a = api;
            this.f6051b = i;
        }

        public void m9163a(ConnectionResult connectionResult) {
            this.f6052c.f6070k.lock();
            try {
                this.f6052c.f6080u.m9066a(connectionResult, this.f6050a, this.f6051b);
            } finally {
                this.f6052c.f6070k.unlock();
            }
        }
    }

    final class zza extends Handler {
        final /* synthetic */ zzli f6053a;

        zza(zzli com_google_android_gms_internal_zzli, Looper looper) {
            this.f6053a = com_google_android_gms_internal_zzli;
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    this.f6053a.m9181o();
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    this.f6053a.m9180n();
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    ((zzb) message.obj).m9060a(this.f6053a);
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    throw ((RuntimeException) message.obj);
                default:
                    Log.w("GoogleApiClientImpl", "Unknown message id: " + message.what);
            }
        }
    }

    class zzc implements DeathRecipient, zze {
        private final WeakReference<zzf<?>> f6054a;
        private final WeakReference<com.google.android.gms.common.api.zza> f6055b;
        private final WeakReference<IBinder> f6056c;

        private zzc(zzf com_google_android_gms_internal_zzli_zzf, com.google.android.gms.common.api.zza com_google_android_gms_common_api_zza, IBinder iBinder) {
            this.f6055b = new WeakReference(com_google_android_gms_common_api_zza);
            this.f6054a = new WeakReference(com_google_android_gms_internal_zzli_zzf);
            this.f6056c = new WeakReference(iBinder);
        }

        private void m9164a() {
            zzf com_google_android_gms_internal_zzli_zzf = (zzf) this.f6054a.get();
            com.google.android.gms.common.api.zza com_google_android_gms_common_api_zza = (com.google.android.gms.common.api.zza) this.f6055b.get();
            if (!(com_google_android_gms_common_api_zza == null || com_google_android_gms_internal_zzli_zzf == null)) {
                com_google_android_gms_common_api_zza.m8404a(com_google_android_gms_internal_zzli_zzf.m9037a().intValue());
            }
            IBinder iBinder = (IBinder) this.f6056c.get();
            if (this.f6056c != null) {
                iBinder.unlinkToDeath(this, 0);
            }
        }

        public void m9165a(zzf<?> com_google_android_gms_internal_zzli_zzf_) {
            m9164a();
        }

        public void binderDied() {
            m9164a();
        }
    }

    class zzd extends zzll {
        private WeakReference<zzli> f6058b;

        zzd(zzli com_google_android_gms_internal_zzli) {
            this.f6058b = new WeakReference(com_google_android_gms_internal_zzli);
        }

        public void m9170a() {
            zzli com_google_android_gms_internal_zzli = (zzli) this.f6058b.get();
            if (com_google_android_gms_internal_zzli != null) {
                com_google_android_gms_internal_zzli.m9180n();
            }
        }
    }

    public zzli(Context context, Looper looper, com.google.android.gms.common.internal.zzf com_google_android_gms_common_internal_zzf, GoogleApiAvailability googleApiAvailability, com.google.android.gms.common.api.Api.zza<? extends zzqw, zzqx> com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzqw__com_google_android_gms_internal_zzqx, Map<Api<?>, ApiOptions> map, ArrayList<ConnectionCallbacks> arrayList, ArrayList<OnConnectionFailedListener> arrayList2, int i) {
        this.f6070k = new ReentrantLock();
        this.f6061b = new LinkedList();
        this.f6076q = 120000;
        this.f6077r = 5000;
        this.f6063d = new HashMap();
        this.f6064e = new HashMap();
        this.f6065f = new HashSet();
        this.f6081v = null;
        this.f6082w = Collections.newSetFromMap(new WeakHashMap());
        this.f6069j = Collections.newSetFromMap(new ConcurrentHashMap(16, 0.75f, 2));
        this.f6084y = new C08591(this);
        this.f6085z = new C08602(this);
        this.f6059A = new C08613(this);
        this.f6073n = context;
        this.f6060a = new zzk(looper, this.f6059A);
        this.f6074o = looper;
        this.f6078s = new zza(this, looper);
        this.f6079t = googleApiAvailability;
        this.f6072m = i;
        this.f6067h = new HashMap();
        this.f6071l = this.f6070k.newCondition();
        this.f6080u = new zzlh(this);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.f6060a.m8582a((ConnectionCallbacks) it.next());
        }
        it = arrayList2.iterator();
        while (it.hasNext()) {
            this.f6060a.m8583a((OnConnectionFailedListener) it.next());
        }
        Map e = com_google_android_gms_common_internal_zzf.m8541e();
        for (Api api : map.keySet()) {
            int i2;
            Object obj = map.get(api);
            if (e.get(api) != null) {
                i2 = ((com.google.android.gms.common.internal.zzf.zza) e.get(api)).f5745b ? 1 : 2;
            } else {
                i2 = 0;
            }
            this.f6067h.put(api, Integer.valueOf(i2));
            this.f6063d.put(api.m8360c(), api.m8361d() ? m9174a(api.m8359b(), obj, context, looper, com_google_android_gms_common_internal_zzf, this.f6085z, m9172a(api, i2)) : m9171a(api.m8358a(), obj, context, looper, com_google_android_gms_common_internal_zzf, this.f6085z, m9172a(api, i2)));
        }
        this.f6066g = com_google_android_gms_common_internal_zzf;
        this.f6068i = com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzqw__com_google_android_gms_internal_zzqx;
    }

    private static <C extends com.google.android.gms.common.api.Api.zzb, O> C m9171a(com.google.android.gms.common.api.Api.zza<C, O> com_google_android_gms_common_api_Api_zza_C__O, Object obj, Context context, Looper looper, com.google.android.gms.common.internal.zzf com_google_android_gms_common_internal_zzf, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        return com_google_android_gms_common_api_Api_zza_C__O.m8342a(context, looper, com_google_android_gms_common_internal_zzf, obj, connectionCallbacks, onConnectionFailedListener);
    }

    private OnConnectionFailedListener m9172a(Api<?> api, int i) {
        return new C08624(this, api, i);
    }

    private static <C extends com.google.android.gms.common.api.Api.zzd, O> zzac m9174a(com.google.android.gms.common.api.Api.zze<C, O> com_google_android_gms_common_api_Api_zze_C__O, Object obj, Context context, Looper looper, com.google.android.gms.common.internal.zzf com_google_android_gms_common_internal_zzf, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        return new zzac(context, looper, com_google_android_gms_common_api_Api_zze_C__O.m8356a(), connectionCallbacks, onConnectionFailedListener, com_google_android_gms_common_internal_zzf, com_google_android_gms_common_api_Api_zze_C__O.m8357a(obj));
    }

    private static void m9175a(zzf<?> com_google_android_gms_internal_zzli_zzf_, com.google.android.gms.common.api.zza com_google_android_gms_common_api_zza, IBinder iBinder) {
        if (com_google_android_gms_internal_zzli_zzf_.m9045f()) {
            com_google_android_gms_internal_zzli_zzf_.m9040a(new zzc(com_google_android_gms_common_api_zza, iBinder, null));
        } else if (iBinder == null || !iBinder.isBinderAlive()) {
            com_google_android_gms_internal_zzli_zzf_.m9040a(null);
            com_google_android_gms_internal_zzli_zzf_.m9046g();
            com_google_android_gms_common_api_zza.m8404a(com_google_android_gms_internal_zzli_zzf_.m9037a().intValue());
        } else {
            zze com_google_android_gms_internal_zzli_zzc = new zzc(com_google_android_gms_common_api_zza, iBinder, null);
            com_google_android_gms_internal_zzli_zzf_.m9040a(com_google_android_gms_internal_zzli_zzc);
            try {
                iBinder.linkToDeath(com_google_android_gms_internal_zzli_zzc, 0);
            } catch (RemoteException e) {
                com_google_android_gms_internal_zzli_zzf_.m9046g();
                com_google_android_gms_common_api_zza.m8404a(com_google_android_gms_internal_zzli_zzf_.m9037a().intValue());
            }
        }
    }

    private void m9180n() {
        this.f6070k.lock();
        try {
            if (m9200j()) {
                m9191b();
            }
            this.f6070k.unlock();
        } catch (Throwable th) {
            this.f6070k.unlock();
        }
    }

    private void m9181o() {
        this.f6070k.lock();
        try {
            if (m9202l()) {
                m9191b();
            }
            this.f6070k.unlock();
        } catch (Throwable th) {
            this.f6070k.unlock();
        }
    }

    public Looper m9182a() {
        return this.f6074o;
    }

    public <C extends com.google.android.gms.common.api.Api.zzb> C m9183a(com.google.android.gms.common.api.Api.zzc<C> com_google_android_gms_common_api_Api_zzc_C) {
        Object obj = (com.google.android.gms.common.api.Api.zzb) this.f6063d.get(com_google_android_gms_common_api_Api_zzc_C);
        zzx.m8719a(obj, (Object) "Appropriate Api was not requested.");
        return obj;
    }

    public <A extends com.google.android.gms.common.api.Api.zzb, T extends com.google.android.gms.internal.zzlb.zza<? extends Result, A>> T m9184a(T t) {
        zzx.m8727b(t.m9051b() != null, "This task can not be executed (it's probably a Batch or malformed)");
        this.f6070k.lock();
        try {
            if (m9200j()) {
                this.f6061b.add(t);
                while (!this.f6061b.isEmpty()) {
                    zzf com_google_android_gms_internal_zzli_zzf = (zzf) this.f6061b.remove();
                    m9188a(com_google_android_gms_internal_zzli_zzf);
                    com_google_android_gms_internal_zzli_zzf.m9039a(Status.f5684c);
                }
            } else {
                t = this.f6080u.m9062a((com.google.android.gms.internal.zzlb.zza) t);
                this.f6070k.unlock();
            }
            return t;
        } finally {
            this.f6070k.unlock();
        }
    }

    void m9185a(ConnectionResult connectionResult) {
        this.f6070k.lock();
        try {
            this.f6081v = connectionResult;
            this.f6080u = new zzlh(this);
            this.f6080u.m9063a();
            this.f6071l.signalAll();
        } finally {
            this.f6070k.unlock();
        }
    }

    public void m9186a(OnConnectionFailedListener onConnectionFailedListener) {
        this.f6060a.m8583a(onConnectionFailedListener);
    }

    void m9187a(zzb com_google_android_gms_internal_zzli_zzb) {
        this.f6078s.sendMessage(this.f6078s.obtainMessage(3, com_google_android_gms_internal_zzli_zzb));
    }

    <A extends com.google.android.gms.common.api.Api.zzb> void m9188a(zzf<A> com_google_android_gms_internal_zzli_zzf_A) {
        this.f6069j.add(com_google_android_gms_internal_zzli_zzf_A);
        com_google_android_gms_internal_zzli_zzf_A.m9040a(this.f6084y);
    }

    void m9189a(RuntimeException runtimeException) {
        this.f6078s.sendMessage(this.f6078s.obtainMessage(4, runtimeException));
    }

    public void m9190a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append(str).append("mState=").append(this.f6080u.m9069d());
        printWriter.append(" mResuming=").print(this.f6075p);
        printWriter.append(" mWorkQueue.size()=").print(this.f6061b.size());
        printWriter.append(" mUnconsumedRunners.size()=").println(this.f6069j.size());
        String str2 = str + "  ";
        for (Api api : this.f6067h.keySet()) {
            printWriter.append(str).append(api.m8362e()).println(":");
            ((com.google.android.gms.common.api.Api.zzb) this.f6063d.get(api.m8360c())).m8348a(str2, fileDescriptor, printWriter, strArr);
        }
    }

    public void m9191b() {
        this.f6070k.lock();
        try {
            this.f6080u.m9068c();
        } finally {
            this.f6070k.unlock();
        }
    }

    public void m9192b(OnConnectionFailedListener onConnectionFailedListener) {
        this.f6060a.m8585b(onConnectionFailedListener);
    }

    public void m9193c() {
        this.f6070k.lock();
        try {
            m9202l();
            this.f6080u.m9067b();
        } finally {
            this.f6070k.unlock();
        }
    }

    public int m9194d() {
        return System.identityHashCode(this);
    }

    void m9195e() {
        for (zzf com_google_android_gms_internal_zzli_zzf : this.f6069j) {
            com_google_android_gms_internal_zzli_zzf.m9040a(null);
            if (com_google_android_gms_internal_zzli_zzf.m9037a() == null) {
                com_google_android_gms_internal_zzli_zzf.m9046g();
            } else {
                com_google_android_gms_internal_zzli_zzf.m9043c();
                m9175a(com_google_android_gms_internal_zzli_zzf, this.f6083x, m9183a(com_google_android_gms_internal_zzli_zzf.m9041b()).m8351d());
            }
        }
        this.f6069j.clear();
        for (zzlm a : this.f6082w) {
            a.m9205a();
        }
        this.f6082w.clear();
    }

    void m9196f() {
        for (com.google.android.gms.common.api.Api.zzb a : this.f6063d.values()) {
            a.m8344a();
        }
    }

    void m9197g() {
        this.f6070k.lock();
        try {
            this.f6080u = new zzlg(this, this.f6066g, this.f6067h, this.f6079t, this.f6068i, this.f6070k, this.f6073n);
            this.f6080u.m9063a();
            this.f6071l.signalAll();
        } finally {
            this.f6070k.unlock();
        }
    }

    void m9198h() {
        this.f6070k.lock();
        try {
            m9202l();
            this.f6080u = new zzlf(this);
            this.f6080u.m9063a();
            this.f6071l.signalAll();
        } finally {
            this.f6070k.unlock();
        }
    }

    public boolean m9199i() {
        return this.f6080u instanceof zzlf;
    }

    boolean m9200j() {
        return this.f6075p;
    }

    void m9201k() {
        if (!m9200j()) {
            this.f6075p = true;
            if (this.f6062c == null) {
                this.f6062c = (zzd) zzll.m9167a(this.f6073n.getApplicationContext(), new zzd(this), this.f6079t);
            }
            this.f6078s.sendMessageDelayed(this.f6078s.obtainMessage(1), this.f6076q);
            this.f6078s.sendMessageDelayed(this.f6078s.obtainMessage(2), this.f6077r);
        }
    }

    boolean m9202l() {
        if (!m9200j()) {
            return false;
        }
        this.f6075p = false;
        this.f6078s.removeMessages(2);
        this.f6078s.removeMessages(1);
        if (this.f6062c != null) {
            this.f6062c.m9169b();
            this.f6062c = null;
        }
        return true;
    }

    String m9203m() {
        Writer stringWriter = new StringWriter();
        m9190a("", null, new PrintWriter(stringWriter), null);
        return stringWriter.toString();
    }
}
