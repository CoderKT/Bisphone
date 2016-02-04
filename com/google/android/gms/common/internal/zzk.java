package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzk implements Callback {
    final ArrayList<ConnectionCallbacks> f5776a;
    private final zza f5777b;
    private final ArrayList<ConnectionCallbacks> f5778c;
    private final ArrayList<OnConnectionFailedListener> f5779d;
    private volatile boolean f5780e;
    private final AtomicInteger f5781f;
    private boolean f5782g;
    private final Handler f5783h;
    private final Object f5784i;

    public interface zza {
        boolean m8482b();

        Bundle m8483n();
    }

    public zzk(Looper looper, zza com_google_android_gms_common_internal_zzk_zza) {
        this.f5778c = new ArrayList();
        this.f5776a = new ArrayList();
        this.f5779d = new ArrayList();
        this.f5780e = false;
        this.f5781f = new AtomicInteger(0);
        this.f5782g = false;
        this.f5784i = new Object();
        this.f5777b = com_google_android_gms_common_internal_zzk_zza;
        this.f5783h = new Handler(looper, this);
    }

    public void m8578a() {
        this.f5780e = false;
        this.f5781f.incrementAndGet();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m8579a(int r6) {
        /*
        r5 = this;
        r0 = 0;
        r1 = 1;
        r2 = android.os.Looper.myLooper();
        r3 = r5.f5783h;
        r3 = r3.getLooper();
        if (r2 != r3) goto L_0x000f;
    L_0x000e:
        r0 = r1;
    L_0x000f:
        r2 = "onUnintentionalDisconnection must only be called on the Handler thread";
        com.google.android.gms.common.internal.zzx.m8723a(r0, r2);
        r0 = r5.f5783h;
        r0.removeMessages(r1);
        r1 = r5.f5784i;
        monitor-enter(r1);
        r0 = 1;
        r5.f5782g = r0;	 Catch:{ all -> 0x005e }
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x005e }
        r2 = r5.f5778c;	 Catch:{ all -> 0x005e }
        r0.<init>(r2);	 Catch:{ all -> 0x005e }
        r2 = r5.f5781f;	 Catch:{ all -> 0x005e }
        r2 = r2.get();	 Catch:{ all -> 0x005e }
        r3 = r0.iterator();	 Catch:{ all -> 0x005e }
    L_0x0030:
        r0 = r3.hasNext();	 Catch:{ all -> 0x005e }
        if (r0 == 0) goto L_0x0048;
    L_0x0036:
        r0 = r3.next();	 Catch:{ all -> 0x005e }
        r0 = (com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks) r0;	 Catch:{ all -> 0x005e }
        r4 = r5.f5780e;	 Catch:{ all -> 0x005e }
        if (r4 == 0) goto L_0x0048;
    L_0x0040:
        r4 = r5.f5781f;	 Catch:{ all -> 0x005e }
        r4 = r4.get();	 Catch:{ all -> 0x005e }
        if (r4 == r2) goto L_0x0052;
    L_0x0048:
        r0 = r5.f5776a;	 Catch:{ all -> 0x005e }
        r0.clear();	 Catch:{ all -> 0x005e }
        r0 = 0;
        r5.f5782g = r0;	 Catch:{ all -> 0x005e }
        monitor-exit(r1);	 Catch:{ all -> 0x005e }
        return;
    L_0x0052:
        r4 = r5.f5778c;	 Catch:{ all -> 0x005e }
        r4 = r4.contains(r0);	 Catch:{ all -> 0x005e }
        if (r4 == 0) goto L_0x0030;
    L_0x005a:
        r0.m5632b(r6);	 Catch:{ all -> 0x005e }
        goto L_0x0030;
    L_0x005e:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x005e }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.zzk.a(int):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m8580a(android.os.Bundle r6) {
        /*
        r5 = this;
        r2 = 0;
        r1 = 1;
        r0 = android.os.Looper.myLooper();
        r3 = r5.f5783h;
        r3 = r3.getLooper();
        if (r0 != r3) goto L_0x006e;
    L_0x000e:
        r0 = r1;
    L_0x000f:
        r3 = "onConnectionSuccess must only be called on the Handler thread";
        com.google.android.gms.common.internal.zzx.m8723a(r0, r3);
        r3 = r5.f5784i;
        monitor-enter(r3);
        r0 = r5.f5782g;	 Catch:{ all -> 0x0080 }
        if (r0 != 0) goto L_0x0070;
    L_0x001b:
        r0 = r1;
    L_0x001c:
        com.google.android.gms.common.internal.zzx.m8722a(r0);	 Catch:{ all -> 0x0080 }
        r0 = r5.f5783h;	 Catch:{ all -> 0x0080 }
        r4 = 1;
        r0.removeMessages(r4);	 Catch:{ all -> 0x0080 }
        r0 = 1;
        r5.f5782g = r0;	 Catch:{ all -> 0x0080 }
        r0 = r5.f5776a;	 Catch:{ all -> 0x0080 }
        r0 = r0.size();	 Catch:{ all -> 0x0080 }
        if (r0 != 0) goto L_0x0072;
    L_0x0030:
        com.google.android.gms.common.internal.zzx.m8722a(r1);	 Catch:{ all -> 0x0080 }
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x0080 }
        r1 = r5.f5778c;	 Catch:{ all -> 0x0080 }
        r0.<init>(r1);	 Catch:{ all -> 0x0080 }
        r1 = r5.f5781f;	 Catch:{ all -> 0x0080 }
        r1 = r1.get();	 Catch:{ all -> 0x0080 }
        r2 = r0.iterator();	 Catch:{ all -> 0x0080 }
    L_0x0044:
        r0 = r2.hasNext();	 Catch:{ all -> 0x0080 }
        if (r0 == 0) goto L_0x0064;
    L_0x004a:
        r0 = r2.next();	 Catch:{ all -> 0x0080 }
        r0 = (com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks) r0;	 Catch:{ all -> 0x0080 }
        r4 = r5.f5780e;	 Catch:{ all -> 0x0080 }
        if (r4 == 0) goto L_0x0064;
    L_0x0054:
        r4 = r5.f5777b;	 Catch:{ all -> 0x0080 }
        r4 = r4.m8482b();	 Catch:{ all -> 0x0080 }
        if (r4 == 0) goto L_0x0064;
    L_0x005c:
        r4 = r5.f5781f;	 Catch:{ all -> 0x0080 }
        r4 = r4.get();	 Catch:{ all -> 0x0080 }
        if (r4 == r1) goto L_0x0074;
    L_0x0064:
        r0 = r5.f5776a;	 Catch:{ all -> 0x0080 }
        r0.clear();	 Catch:{ all -> 0x0080 }
        r0 = 0;
        r5.f5782g = r0;	 Catch:{ all -> 0x0080 }
        monitor-exit(r3);	 Catch:{ all -> 0x0080 }
        return;
    L_0x006e:
        r0 = r2;
        goto L_0x000f;
    L_0x0070:
        r0 = r2;
        goto L_0x001c;
    L_0x0072:
        r1 = r2;
        goto L_0x0030;
    L_0x0074:
        r4 = r5.f5776a;	 Catch:{ all -> 0x0080 }
        r4 = r4.contains(r0);	 Catch:{ all -> 0x0080 }
        if (r4 != 0) goto L_0x0044;
    L_0x007c:
        r0.m5631a(r6);	 Catch:{ all -> 0x0080 }
        goto L_0x0044;
    L_0x0080:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0080 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.zzk.a(android.os.Bundle):void");
    }

    public void m8581a(ConnectionResult connectionResult) {
        zzx.m8723a(Looper.myLooper() == this.f5783h.getLooper(), (Object) "onConnectionFailure must only be called on the Handler thread");
        this.f5783h.removeMessages(1);
        synchronized (this.f5784i) {
            ArrayList arrayList = new ArrayList(this.f5779d);
            int i = this.f5781f.get();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                OnConnectionFailedListener onConnectionFailedListener = (OnConnectionFailedListener) it.next();
                if (!this.f5780e || this.f5781f.get() != i) {
                    return;
                } else if (this.f5779d.contains(onConnectionFailedListener)) {
                    onConnectionFailedListener.m5633a(connectionResult);
                }
            }
        }
    }

    public void m8582a(ConnectionCallbacks connectionCallbacks) {
        zzx.m8718a((Object) connectionCallbacks);
        synchronized (this.f5784i) {
            if (this.f5778c.contains(connectionCallbacks)) {
                Log.w("GmsClientEvents", "registerConnectionCallbacks(): listener " + connectionCallbacks + " is already registered");
            } else {
                this.f5778c.add(connectionCallbacks);
            }
        }
        if (this.f5777b.m8482b()) {
            this.f5783h.sendMessage(this.f5783h.obtainMessage(1, connectionCallbacks));
        }
    }

    public void m8583a(OnConnectionFailedListener onConnectionFailedListener) {
        zzx.m8718a((Object) onConnectionFailedListener);
        synchronized (this.f5784i) {
            if (this.f5779d.contains(onConnectionFailedListener)) {
                Log.w("GmsClientEvents", "registerConnectionFailedListener(): listener " + onConnectionFailedListener + " is already registered");
            } else {
                this.f5779d.add(onConnectionFailedListener);
            }
        }
    }

    public void m8584b() {
        this.f5780e = true;
    }

    public void m8585b(OnConnectionFailedListener onConnectionFailedListener) {
        zzx.m8718a((Object) onConnectionFailedListener);
        synchronized (this.f5784i) {
            if (!this.f5779d.remove(onConnectionFailedListener)) {
                Log.w("GmsClientEvents", "unregisterConnectionFailedListener(): listener " + onConnectionFailedListener + " not found");
            }
        }
    }

    public boolean handleMessage(Message message) {
        if (message.what == 1) {
            ConnectionCallbacks connectionCallbacks = (ConnectionCallbacks) message.obj;
            synchronized (this.f5784i) {
                if (this.f5780e && this.f5777b.m8482b() && this.f5778c.contains(connectionCallbacks)) {
                    connectionCallbacks.m5631a(this.f5777b.m8483n());
                }
            }
            return true;
        }
        Log.wtf("GmsClientEvents", "Don't know how to handle message: " + message.what, new Exception());
        return false;
    }
}
