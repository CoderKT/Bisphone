package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import se.emilsjolander.stickylistheaders.C1128R;

public abstract class zzj<T extends IInterface> implements com.google.android.gms.common.api.Api.zzb, com.google.android.gms.common.internal.zzk.zza {
    public static final String[] f5722c;
    final Handler f5723a;
    protected AtomicInteger f5724b;
    private final Context f5725d;
    private final zzf f5726e;
    private final Looper f5727f;
    private final zzl f5728g;
    private final GoogleApiAvailability f5729h;
    private final Object f5730i;
    private zzs f5731j;
    private com.google.android.gms.common.api.GoogleApiClient.zza f5732k;
    private T f5733l;
    private final ArrayList<zzc<?>> f5734m;
    private zze f5735n;
    private int f5736o;
    private final Set<Scope> f5737p;
    private final Account f5738q;
    private final ConnectionCallbacks f5739r;
    private final OnConnectionFailedListener f5740s;
    private final int f5741t;

    public abstract class zzc<TListener> {
        private TListener f5760a;
        private boolean f5761b;
        final /* synthetic */ zzj f5762d;

        public zzc(zzj com_google_android_gms_common_internal_zzj, TListener tListener) {
            this.f5762d = com_google_android_gms_common_internal_zzj;
            this.f5760a = tListener;
            this.f5761b = false;
        }

        protected abstract void m8552a(TListener tListener);

        protected abstract void m8553b();

        public void m8554c() {
            synchronized (this) {
                Object obj = this.f5760a;
                if (this.f5761b) {
                    Log.w("GmsClient", "Callback proxy " + this + " being reused. This is not safe.");
                }
            }
            if (obj != null) {
                try {
                    m8552a(obj);
                } catch (RuntimeException e) {
                    m8553b();
                    throw e;
                }
            }
            m8553b();
            synchronized (this) {
                this.f5761b = true;
            }
            m8555d();
        }

        public void m8555d() {
            m8556e();
            synchronized (this.f5762d.f5734m) {
                this.f5762d.f5734m.remove(this);
            }
        }

        public void m8556e() {
            synchronized (this) {
                this.f5760a = null;
            }
        }
    }

    abstract class zza extends zzc<Boolean> {
        public final int f5763a;
        public final Bundle f5764b;
        final /* synthetic */ zzj f5765c;

        protected zza(zzj com_google_android_gms_common_internal_zzj, int i, Bundle bundle) {
            this.f5765c = com_google_android_gms_common_internal_zzj;
            super(com_google_android_gms_common_internal_zzj, Boolean.valueOf(true));
            this.f5763a = i;
            this.f5764b = bundle;
        }

        protected abstract void m8557a(ConnectionResult connectionResult);

        protected void m8558a(Boolean bool) {
            PendingIntent pendingIntent = null;
            if (bool == null) {
                this.f5765c.m8491b(1, null);
                return;
            }
            switch (this.f5763a) {
                case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                    if (!m8560a()) {
                        this.f5765c.m8491b(1, null);
                        m8557a(new ConnectionResult(8, null));
                    }
                case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                    this.f5765c.m8491b(1, null);
                    throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
                default:
                    this.f5765c.m8491b(1, null);
                    if (this.f5764b != null) {
                        pendingIntent = (PendingIntent) this.f5764b.getParcelable("pendingIntent");
                    }
                    m8557a(new ConnectionResult(this.f5763a, pendingIntent));
            }
        }

        protected /* synthetic */ void m8559a(Object obj) {
            m8558a((Boolean) obj);
        }

        protected abstract boolean m8560a();

        protected void m8561b() {
        }
    }

    final class zzb extends Handler {
        final /* synthetic */ zzj f5766a;

        public zzb(zzj com_google_android_gms_common_internal_zzj, Looper looper) {
            this.f5766a = com_google_android_gms_common_internal_zzj;
            super(looper);
        }

        private void m8562a(Message message) {
            zzc com_google_android_gms_common_internal_zzj_zzc = (zzc) message.obj;
            com_google_android_gms_common_internal_zzj_zzc.m8553b();
            com_google_android_gms_common_internal_zzj_zzc.m8555d();
        }

        private boolean m8563b(Message message) {
            return message.what == 2 || message.what == 1 || message.what == 5 || message.what == 6;
        }

        public void handleMessage(Message message) {
            if (this.f5766a.f5724b.get() != message.arg1) {
                if (m8563b(message)) {
                    m8562a(message);
                }
            } else if ((message.what == 1 || message.what == 5 || message.what == 6) && !this.f5766a.m8516i()) {
                m8562a(message);
            } else if (message.what == 3) {
                ConnectionResult connectionResult = new ConnectionResult(message.arg2, null);
                this.f5766a.f5732k.m8377a(connectionResult);
                this.f5766a.m8504a(connectionResult);
            } else if (message.what == 4) {
                this.f5766a.m8491b(4, null);
                if (this.f5766a.f5739r != null) {
                    this.f5766a.f5739r.m5632b(message.arg2);
                }
                this.f5766a.m8500a(message.arg2);
                this.f5766a.m8487a(4, 1, null);
            } else if (message.what == 2 && !this.f5766a.m8510b()) {
                m8562a(message);
            } else if (m8563b(message)) {
                ((zzc) message.obj).m8554c();
            } else {
                Log.wtf("GmsClient", "Don't know how to handle message: " + message.what, new Exception());
            }
        }
    }

    public final class zzd extends com.google.android.gms.common.internal.zzr.zza {
        private zzj f5767a;
        private final int f5768b;

        public zzd(zzj com_google_android_gms_common_internal_zzj, int i) {
            this.f5767a = com_google_android_gms_common_internal_zzj;
            this.f5768b = i;
        }

        private void m8567a() {
            this.f5767a = null;
        }

        public void m8568a(int i, Bundle bundle) {
            zzx.m8719a(this.f5767a, (Object) "onAccountValidationComplete can be called only once per call to validateAccount");
            this.f5767a.m8501a(i, bundle, this.f5768b);
            m8567a();
        }

        public void m8569a(int i, IBinder iBinder, Bundle bundle) {
            zzx.m8719a(this.f5767a, (Object) "onPostInitComplete can be called only once per call to getRemoteService");
            this.f5767a.m8502a(i, iBinder, bundle, this.f5768b);
            m8567a();
        }
    }

    public final class zze implements ServiceConnection {
        final /* synthetic */ zzj f5769a;
        private final int f5770b;

        public zze(zzj com_google_android_gms_common_internal_zzj, int i) {
            this.f5769a = com_google_android_gms_common_internal_zzj;
            this.f5770b = i;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            zzx.m8719a((Object) iBinder, (Object) "Expecting a valid IBinder");
            this.f5769a.f5731j = com.google.android.gms.common.internal.zzs.zza.m8710a(iBinder);
            this.f5769a.m8511c(this.f5770b);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            this.f5769a.f5723a.sendMessage(this.f5769a.f5723a.obtainMessage(4, this.f5770b, 1));
        }
    }

    public class zzf implements com.google.android.gms.common.api.GoogleApiClient.zza {
        final /* synthetic */ zzj f5771a;

        public zzf(zzj com_google_android_gms_common_internal_zzj) {
            this.f5771a = com_google_android_gms_common_internal_zzj;
        }

        public void m8570a(ConnectionResult connectionResult) {
            if (connectionResult.m8303b()) {
                this.f5771a.m8507a(null, this.f5771a.f5737p);
            } else if (this.f5771a.f5740s != null) {
                this.f5771a.f5740s.m5633a(connectionResult);
            }
        }

        public void m8571b(ConnectionResult connectionResult) {
            throw new IllegalStateException("Legacy GmsClient received onReportAccountValidation callback.");
        }
    }

    public final class zzg extends zza {
        public final IBinder f5772e;
        final /* synthetic */ zzj f5773f;

        public zzg(zzj com_google_android_gms_common_internal_zzj, int i, IBinder iBinder, Bundle bundle) {
            this.f5773f = com_google_android_gms_common_internal_zzj;
            super(com_google_android_gms_common_internal_zzj, i, bundle);
            this.f5772e = iBinder;
        }

        protected void m8572a(ConnectionResult connectionResult) {
            if (this.f5773f.f5740s != null) {
                this.f5773f.f5740s.m5633a(connectionResult);
            }
            this.f5773f.m8504a(connectionResult);
        }

        protected boolean m8573a() {
            try {
                String interfaceDescriptor = this.f5772e.getInterfaceDescriptor();
                if (this.f5773f.m8515f().equals(interfaceDescriptor)) {
                    IInterface a = this.f5773f.m8497a(this.f5772e);
                    if (a == null || !this.f5773f.m8487a(2, 3, a)) {
                        return false;
                    }
                    Bundle n = this.f5773f.m8521n();
                    if (this.f5773f.f5739r != null) {
                        this.f5773f.f5739r.m5631a(n);
                    }
                    return true;
                }
                Log.e("GmsClient", "service descriptor mismatch: " + this.f5773f.m8515f() + " vs. " + interfaceDescriptor);
                return false;
            } catch (RemoteException e) {
                Log.w("GmsClient", "service probably died");
                return false;
            }
        }
    }

    public final class zzh extends zza {
        final /* synthetic */ zzj f5774e;

        public zzh(zzj com_google_android_gms_common_internal_zzj) {
            this.f5774e = com_google_android_gms_common_internal_zzj;
            super(com_google_android_gms_common_internal_zzj, 0, null);
        }

        protected void m8574a(ConnectionResult connectionResult) {
            this.f5774e.f5732k.m8377a(connectionResult);
            this.f5774e.m8504a(connectionResult);
        }

        protected boolean m8575a() {
            this.f5774e.f5732k.m8377a(ConnectionResult.f5634a);
            return true;
        }
    }

    public final class zzi extends zza {
        final /* synthetic */ zzj f5775e;

        public zzi(zzj com_google_android_gms_common_internal_zzj, int i, Bundle bundle) {
            this.f5775e = com_google_android_gms_common_internal_zzj;
            super(com_google_android_gms_common_internal_zzj, i, bundle);
        }

        protected void m8576a(ConnectionResult connectionResult) {
            this.f5775e.f5732k.m8378b(connectionResult);
            this.f5775e.m8504a(connectionResult);
        }

        protected boolean m8577a() {
            this.f5775e.f5732k.m8378b(ConnectionResult.f5634a);
            return true;
        }
    }

    static {
        f5722c = new String[]{"service_esmobile", "service_googleme"};
    }

    protected zzj(Context context, Looper looper, int i, zzf com_google_android_gms_common_internal_zzf, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, zzl.m8586a(context), GoogleApiAvailability.m8308a(), i, com_google_android_gms_common_internal_zzf, (ConnectionCallbacks) zzx.m8718a((Object) connectionCallbacks), (OnConnectionFailedListener) zzx.m8718a((Object) onConnectionFailedListener));
    }

    protected zzj(Context context, Looper looper, zzl com_google_android_gms_common_internal_zzl, GoogleApiAvailability googleApiAvailability, int i, zzf com_google_android_gms_common_internal_zzf, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        this.f5730i = new Object();
        this.f5734m = new ArrayList();
        this.f5736o = 1;
        this.f5724b = new AtomicInteger(0);
        this.f5725d = (Context) zzx.m8719a((Object) context, (Object) "Context must not be null");
        this.f5727f = (Looper) zzx.m8719a((Object) looper, (Object) "Looper must not be null");
        this.f5728g = (zzl) zzx.m8719a((Object) com_google_android_gms_common_internal_zzl, (Object) "Supervisor must not be null");
        this.f5729h = (GoogleApiAvailability) zzx.m8719a((Object) googleApiAvailability, (Object) "API availability must not be null");
        this.f5723a = new zzb(this, looper);
        this.f5741t = i;
        this.f5726e = (zzf) zzx.m8718a((Object) com_google_android_gms_common_internal_zzf);
        this.f5738q = com_google_android_gms_common_internal_zzf.m8536a();
        this.f5737p = m8490b(com_google_android_gms_common_internal_zzf.m8540d());
        this.f5739r = connectionCallbacks;
        this.f5740s = onConnectionFailedListener;
    }

    private boolean m8487a(int i, int i2, T t) {
        boolean z;
        synchronized (this.f5730i) {
            if (this.f5736o != i) {
                z = false;
            } else {
                m8491b(i2, t);
                z = true;
            }
        }
        return z;
    }

    private Set<Scope> m8490b(Set<Scope> set) {
        Set<Scope> a = m8498a((Set) set);
        if (a == null) {
            return a;
        }
        for (Scope contains : a) {
            if (!set.contains(contains)) {
                throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
            }
        }
        return a;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m8491b(int r5, T r6) {
        /*
        r4 = this;
        r0 = 1;
        r1 = 0;
        r2 = 3;
        if (r5 != r2) goto L_0x001d;
    L_0x0005:
        r3 = r0;
    L_0x0006:
        if (r6 == 0) goto L_0x001f;
    L_0x0008:
        r2 = r0;
    L_0x0009:
        if (r3 != r2) goto L_0x0021;
    L_0x000b:
        com.google.android.gms.common.internal.zzx.m8726b(r0);
        r1 = r4.f5730i;
        monitor-enter(r1);
        r4.f5736o = r5;	 Catch:{ all -> 0x0027 }
        r4.f5733l = r6;	 Catch:{ all -> 0x0027 }
        r4.m8503a(r5, r6);	 Catch:{ all -> 0x0027 }
        switch(r5) {
            case 1: goto L_0x002e;
            case 2: goto L_0x0023;
            case 3: goto L_0x002a;
            default: goto L_0x001b;
        };	 Catch:{ all -> 0x0027 }
    L_0x001b:
        monitor-exit(r1);	 Catch:{ all -> 0x0027 }
        return;
    L_0x001d:
        r3 = r1;
        goto L_0x0006;
    L_0x001f:
        r2 = r1;
        goto L_0x0009;
    L_0x0021:
        r0 = r1;
        goto L_0x000b;
    L_0x0023:
        r4.m8495r();	 Catch:{ all -> 0x0027 }
        goto L_0x001b;
    L_0x0027:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0027 }
        throw r0;
    L_0x002a:
        r4.c_();	 Catch:{ all -> 0x0027 }
        goto L_0x001b;
    L_0x002e:
        r4.m8496s();	 Catch:{ all -> 0x0027 }
        goto L_0x001b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.zzj.b(int, android.os.IInterface):void");
    }

    private void m8495r() {
        if (this.f5735n != null) {
            Log.e("GmsClient", "Calling connect() while still connected, missing disconnect() for " + m8514e());
            this.f5728g.m8588b(m8514e(), this.f5735n, b_());
            this.f5724b.incrementAndGet();
        }
        this.f5735n = new zze(this, this.f5724b.get());
        if (!this.f5728g.m8587a(m8514e(), this.f5735n, b_())) {
            Log.e("GmsClient", "unable to connect to service: " + m8514e());
            this.f5723a.sendMessage(this.f5723a.obtainMessage(3, this.f5724b.get(), 9));
        }
    }

    private void m8496s() {
        if (this.f5735n != null) {
            this.f5728g.m8588b(m8514e(), this.f5735n, b_());
            this.f5735n = null;
        }
    }

    protected abstract T m8497a(IBinder iBinder);

    protected Set<Scope> m8498a(Set<Scope> set) {
        return set;
    }

    public void m8499a() {
        this.f5724b.incrementAndGet();
        synchronized (this.f5734m) {
            int size = this.f5734m.size();
            for (int i = 0; i < size; i++) {
                ((zzc) this.f5734m.get(i)).m8556e();
            }
            this.f5734m.clear();
        }
        m8491b(1, null);
    }

    protected void m8500a(int i) {
    }

    protected void m8501a(int i, Bundle bundle, int i2) {
        this.f5723a.sendMessage(this.f5723a.obtainMessage(5, i2, -1, new zzi(this, i, bundle)));
    }

    protected void m8502a(int i, IBinder iBinder, Bundle bundle, int i2) {
        this.f5723a.sendMessage(this.f5723a.obtainMessage(1, i2, -1, new zzg(this, i, iBinder, bundle)));
    }

    protected void m8503a(int i, T t) {
    }

    protected void m8504a(ConnectionResult connectionResult) {
    }

    public void m8505a(com.google.android.gms.common.api.GoogleApiClient.zza com_google_android_gms_common_api_GoogleApiClient_zza) {
        this.f5732k = (com.google.android.gms.common.api.GoogleApiClient.zza) zzx.m8719a((Object) com_google_android_gms_common_api_GoogleApiClient_zza, (Object) "Connection progress callbacks cannot be null.");
        m8491b(2, null);
    }

    public void m8506a(zzp com_google_android_gms_common_internal_zzp) {
        try {
            this.f5731j.m8633a(new zzd(this, this.f5724b.get()), new ValidateAccountRequest(com_google_android_gms_common_internal_zzp, (Scope[]) this.f5737p.toArray(new Scope[this.f5737p.size()]), this.f5725d.getPackageName(), m8523p()));
        } catch (DeadObjectException e) {
            Log.w("GmsClient", "service died");
            m8509b(1);
        } catch (Throwable e2) {
            Log.w("GmsClient", "Remote exception occurred", e2);
        }
    }

    public void m8507a(zzp com_google_android_gms_common_internal_zzp, Set<Scope> set) {
        try {
            GetServiceRequest a = new GetServiceRequest(this.f5741t).m8419a(this.f5725d.getPackageName()).m8417a(m8519l());
            if (set != null) {
                a.m8420a((Collection) set);
            }
            if (m8512c()) {
                a.m8416a(m8518k()).m8418a(com_google_android_gms_common_internal_zzp);
            } else if (m8524q()) {
                a.m8416a(this.f5738q);
            }
            this.f5731j.m8632a(new zzd(this, this.f5724b.get()), a);
        } catch (DeadObjectException e) {
            Log.w("GmsClient", "service died");
            m8509b(1);
        } catch (Throwable e2) {
            Log.w("GmsClient", "Remote exception occurred", e2);
        }
    }

    public void m8508a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        synchronized (this.f5730i) {
            int i = this.f5736o;
            IInterface iInterface = this.f5733l;
        }
        printWriter.append(str).append("mConnectState=");
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                printWriter.print("DISCONNECTED");
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                printWriter.print("CONNECTING");
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                printWriter.print("CONNECTED");
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                printWriter.print("DISCONNECTING");
                break;
            default:
                printWriter.print("UNKNOWN");
                break;
        }
        printWriter.append(" mService=");
        if (iInterface == null) {
            printWriter.println("null");
        } else {
            printWriter.append(m8515f()).append("@").println(Integer.toHexString(System.identityHashCode(iInterface.asBinder())));
        }
    }

    public void m8509b(int i) {
        this.f5723a.sendMessage(this.f5723a.obtainMessage(4, this.f5724b.get(), i));
    }

    public boolean m8510b() {
        boolean z;
        synchronized (this.f5730i) {
            z = this.f5736o == 3;
        }
        return z;
    }

    protected final String b_() {
        return this.f5726e.m8543g();
    }

    protected void m8511c(int i) {
        this.f5723a.sendMessage(this.f5723a.obtainMessage(6, i, -1, new zzh(this)));
    }

    public boolean m8512c() {
        return false;
    }

    protected void c_() {
    }

    public IBinder m8513d() {
        return this.f5731j == null ? null : this.f5731j.asBinder();
    }

    protected abstract String m8514e();

    protected abstract String m8515f();

    public boolean m8516i() {
        boolean z;
        synchronized (this.f5730i) {
            z = this.f5736o == 2;
        }
        return z;
    }

    public final Context m8517j() {
        return this.f5725d;
    }

    public final Account m8518k() {
        return this.f5738q != null ? this.f5738q : new Account("<<default account>>", "com.google");
    }

    protected Bundle m8519l() {
        return new Bundle();
    }

    protected final void m8520m() {
        if (!m8510b()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    public Bundle m8521n() {
        return null;
    }

    public final T m8522o() {
        T t;
        synchronized (this.f5730i) {
            if (this.f5736o == 4) {
                throw new DeadObjectException();
            }
            m8520m();
            zzx.m8723a(this.f5733l != null, (Object) "Client is connected but service is null");
            t = this.f5733l;
        }
        return t;
    }

    protected Bundle m8523p() {
        return null;
    }

    public boolean m8524q() {
        return false;
    }
}
