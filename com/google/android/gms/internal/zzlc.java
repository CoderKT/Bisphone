package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzx;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import se.emilsjolander.stickylistheaders.C1128R;

public abstract class zzlc<R extends Result> extends PendingResult<R> {
    protected final zza<R> f5984a;
    private final Object f5985b;
    private final CountDownLatch f5986c;
    private final ArrayList<com.google.android.gms.common.api.PendingResult.zza> f5987d;
    private ResultCallback<? super R> f5988e;
    private volatile R f5989f;
    private volatile boolean f5990g;
    private boolean f5991h;
    private boolean f5992i;
    private zzq f5993j;
    private Integer f5994k;
    private volatile zzlq<R> f5995l;

    public class zza<R extends Result> extends Handler {
        public zza() {
            this(Looper.getMainLooper());
        }

        public zza(Looper looper) {
            super(looper);
        }

        public void m9056a() {
            removeMessages(2);
        }

        public void m9057a(ResultCallback<? super R> resultCallback, R r) {
            sendMessage(obtainMessage(1, new Pair(resultCallback, r)));
        }

        protected void m9058b(ResultCallback<? super R> resultCallback, R r) {
            try {
                resultCallback.m8393a(r);
            } catch (RuntimeException e) {
                zzlc.m9025b((Result) r);
                throw e;
            }
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    Pair pair = (Pair) message.obj;
                    m9058b((ResultCallback) pair.first, (Result) pair.second);
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    ((zzlc) message.obj).m9031b(Status.f5685d);
                default:
                    Log.wtf("BasePendingResult", "Don't know how to handle message: " + message.what, new Exception());
            }
        }
    }

    @Deprecated
    protected zzlc(Looper looper) {
        this.f5985b = new Object();
        this.f5986c = new CountDownLatch(1);
        this.f5987d = new ArrayList();
        this.f5984a = new zza(looper);
    }

    public static void m9025b(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).m8391a();
            } catch (Throwable e) {
                Log.w("BasePendingResult", "Unable to release " + result, e);
            }
        }
    }

    private void m9026c(R r) {
        this.f5989f = r;
        this.f5993j = null;
        this.f5986c.countDown();
        Status a = this.f5989f.m8392a();
        if (this.f5988e != null) {
            this.f5984a.m9056a();
            if (!this.f5991h) {
                this.f5984a.m9057a(this.f5988e, m9027i());
            }
        }
        Iterator it = this.f5987d.iterator();
        while (it.hasNext()) {
            ((com.google.android.gms.common.api.PendingResult.zza) it.next()).m8388a(a);
        }
        this.f5987d.clear();
    }

    private R m9027i() {
        R r;
        boolean z = true;
        synchronized (this.f5985b) {
            if (this.f5990g) {
                z = false;
            }
            zzx.m8723a(z, (Object) "Result has already been consumed.");
            zzx.m8723a(m9034f(), (Object) "Result is not ready.");
            r = this.f5989f;
            this.f5989f = null;
            this.f5988e = null;
            this.f5990g = true;
        }
        m9033e();
        return r;
    }

    public Integer m9028a() {
        return this.f5994k;
    }

    public final void m9029a(R r) {
        boolean z = true;
        synchronized (this.f5985b) {
            if (this.f5992i || this.f5991h) {
                m9025b((Result) r);
                return;
            }
            zzx.m8723a(!m9034f(), (Object) "Results have already been set");
            if (this.f5990g) {
                z = false;
            }
            zzx.m8723a(z, (Object) "Result has already been consumed");
            m9026c((Result) r);
        }
    }

    public final void m9030a(ResultCallback<? super R> resultCallback) {
        boolean z = true;
        zzx.m8723a(!this.f5990g, (Object) "Result has already been consumed.");
        synchronized (this.f5985b) {
            if (this.f5995l != null) {
                z = false;
            }
            zzx.m8723a(z, (Object) "Cannot set callbacks if then() has been called.");
            if (m9036h()) {
                return;
            }
            if (m9034f()) {
                this.f5984a.m9057a(resultCallback, m9027i());
            } else {
                this.f5988e = resultCallback;
            }
        }
    }

    public final void m9031b(Status status) {
        synchronized (this.f5985b) {
            if (!m9034f()) {
                m9029a(m9032c(status));
                this.f5992i = true;
            }
        }
    }

    protected abstract R m9032c(Status status);

    protected void m9033e() {
    }

    public final boolean m9034f() {
        return this.f5986c.getCount() == 0;
    }

    public void m9035g() {
        synchronized (this.f5985b) {
            if (this.f5991h || this.f5990g) {
                return;
            }
            if (this.f5993j != null) {
                try {
                    this.f5993j.m8617a();
                } catch (RemoteException e) {
                }
            }
            m9025b(this.f5989f);
            this.f5988e = null;
            this.f5991h = true;
            m9026c(m9032c(Status.f5686e));
        }
    }

    public boolean m9036h() {
        boolean z;
        synchronized (this.f5985b) {
            z = this.f5991h;
        }
        return z;
    }
}
