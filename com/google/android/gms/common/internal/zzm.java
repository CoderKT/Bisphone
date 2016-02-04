package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.IBinder;
import android.os.Message;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import se.emilsjolander.stickylistheaders.C1128R;

final class zzm extends zzl implements Callback {
    private final HashMap<zza, zzb> f5798a;
    private final Context f5799b;
    private final Handler f5800c;
    private final com.google.android.gms.common.stats.zzb f5801d;
    private final long f5802e;

    final class zza {
        private final String f5787a;
        private final ComponentName f5788b;

        public zza(String str) {
            this.f5787a = zzx.m8720a(str);
            this.f5788b = null;
        }

        public Intent m8589a() {
            return this.f5787a != null ? new Intent(this.f5787a).setPackage("com.google.android.gms") : new Intent().setComponent(this.f5788b);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza com_google_android_gms_common_internal_zzm_zza = (zza) obj;
            return zzw.m8717a(this.f5787a, com_google_android_gms_common_internal_zzm_zza.f5787a) && zzw.m8717a(this.f5788b, com_google_android_gms_common_internal_zzm_zza.f5788b);
        }

        public int hashCode() {
            return zzw.m8715a(this.f5787a, this.f5788b);
        }

        public String toString() {
            return this.f5787a == null ? this.f5788b.flattenToString() : this.f5787a;
        }
    }

    final class zzb {
        final /* synthetic */ zzm f5790a;
        private final zza f5791b;
        private final Set<ServiceConnection> f5792c;
        private int f5793d;
        private boolean f5794e;
        private IBinder f5795f;
        private final zza f5796g;
        private ComponentName f5797h;

        public class zza implements ServiceConnection {
            final /* synthetic */ zzb f5789a;

            public zza(zzb com_google_android_gms_common_internal_zzm_zzb) {
                this.f5789a = com_google_android_gms_common_internal_zzm_zzb;
            }

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                synchronized (this.f5789a.f5790a.f5798a) {
                    this.f5789a.f5795f = iBinder;
                    this.f5789a.f5797h = componentName;
                    for (ServiceConnection onServiceConnected : this.f5789a.f5792c) {
                        onServiceConnected.onServiceConnected(componentName, iBinder);
                    }
                    this.f5789a.f5793d = 1;
                }
            }

            public void onServiceDisconnected(ComponentName componentName) {
                synchronized (this.f5789a.f5790a.f5798a) {
                    this.f5789a.f5795f = null;
                    this.f5789a.f5797h = componentName;
                    for (ServiceConnection onServiceDisconnected : this.f5789a.f5792c) {
                        onServiceDisconnected.onServiceDisconnected(componentName);
                    }
                    this.f5789a.f5793d = 2;
                }
            }
        }

        public zzb(zzm com_google_android_gms_common_internal_zzm, zza com_google_android_gms_common_internal_zzm_zza) {
            this.f5790a = com_google_android_gms_common_internal_zzm;
            this.f5796g = com_google_android_gms_common_internal_zzm_zza;
            this.f5791b = new zza(this);
            this.f5792c = new HashSet();
            this.f5793d = 2;
        }

        public void m8595a(ServiceConnection serviceConnection, String str) {
            this.f5790a.f5801d.m8763a(this.f5790a.f5799b, serviceConnection, str, this.f5796g.m8589a());
            this.f5792c.add(serviceConnection);
        }

        public void m8596a(String str) {
            this.f5793d = 3;
            this.f5794e = this.f5790a.f5801d.m8764a(this.f5790a.f5799b, str, this.f5796g.m8589a(), this.f5791b, 129);
            if (!this.f5794e) {
                this.f5793d = 2;
                try {
                    this.f5790a.f5801d.m8762a(this.f5790a.f5799b, this.f5791b);
                } catch (IllegalArgumentException e) {
                }
            }
        }

        public boolean m8597a() {
            return this.f5794e;
        }

        public boolean m8598a(ServiceConnection serviceConnection) {
            return this.f5792c.contains(serviceConnection);
        }

        public int m8599b() {
            return this.f5793d;
        }

        public void m8600b(ServiceConnection serviceConnection, String str) {
            this.f5790a.f5801d.m8765b(this.f5790a.f5799b, serviceConnection);
            this.f5792c.remove(serviceConnection);
        }

        public void m8601b(String str) {
            this.f5790a.f5801d.m8762a(this.f5790a.f5799b, this.f5791b);
            this.f5794e = false;
            this.f5793d = 2;
        }

        public boolean m8602c() {
            return this.f5792c.isEmpty();
        }

        public IBinder m8603d() {
            return this.f5795f;
        }

        public ComponentName m8604e() {
            return this.f5797h;
        }
    }

    zzm(Context context) {
        this.f5798a = new HashMap();
        this.f5799b = context.getApplicationContext();
        this.f5800c = new Handler(context.getMainLooper(), this);
        this.f5801d = com.google.android.gms.common.stats.zzb.m8753a();
        this.f5802e = 5000;
    }

    private boolean m8606a(zza com_google_android_gms_common_internal_zzm_zza, ServiceConnection serviceConnection, String str) {
        boolean a;
        zzx.m8719a((Object) serviceConnection, (Object) "ServiceConnection must not be null");
        synchronized (this.f5798a) {
            zzb com_google_android_gms_common_internal_zzm_zzb = (zzb) this.f5798a.get(com_google_android_gms_common_internal_zzm_zza);
            if (com_google_android_gms_common_internal_zzm_zzb != null) {
                this.f5800c.removeMessages(0, com_google_android_gms_common_internal_zzm_zzb);
                if (!com_google_android_gms_common_internal_zzm_zzb.m8598a(serviceConnection)) {
                    com_google_android_gms_common_internal_zzm_zzb.m8595a(serviceConnection, str);
                    switch (com_google_android_gms_common_internal_zzm_zzb.m8599b()) {
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                            serviceConnection.onServiceConnected(com_google_android_gms_common_internal_zzm_zzb.m8604e(), com_google_android_gms_common_internal_zzm_zzb.m8603d());
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                            com_google_android_gms_common_internal_zzm_zzb.m8596a(str);
                            break;
                        default:
                            break;
                    }
                }
                throw new IllegalStateException("Trying to bind a GmsServiceConnection that was already connected before.  config=" + com_google_android_gms_common_internal_zzm_zza);
            }
            com_google_android_gms_common_internal_zzm_zzb = new zzb(this, com_google_android_gms_common_internal_zzm_zza);
            com_google_android_gms_common_internal_zzm_zzb.m8595a(serviceConnection, str);
            com_google_android_gms_common_internal_zzm_zzb.m8596a(str);
            this.f5798a.put(com_google_android_gms_common_internal_zzm_zza, com_google_android_gms_common_internal_zzm_zzb);
            a = com_google_android_gms_common_internal_zzm_zzb.m8597a();
        }
        return a;
    }

    private void m8608b(zza com_google_android_gms_common_internal_zzm_zza, ServiceConnection serviceConnection, String str) {
        zzx.m8719a((Object) serviceConnection, (Object) "ServiceConnection must not be null");
        synchronized (this.f5798a) {
            zzb com_google_android_gms_common_internal_zzm_zzb = (zzb) this.f5798a.get(com_google_android_gms_common_internal_zzm_zza);
            if (com_google_android_gms_common_internal_zzm_zzb == null) {
                throw new IllegalStateException("Nonexistent connection status for service config: " + com_google_android_gms_common_internal_zzm_zza);
            } else if (com_google_android_gms_common_internal_zzm_zzb.m8598a(serviceConnection)) {
                com_google_android_gms_common_internal_zzm_zzb.m8600b(serviceConnection, str);
                if (com_google_android_gms_common_internal_zzm_zzb.m8602c()) {
                    this.f5800c.sendMessageDelayed(this.f5800c.obtainMessage(0, com_google_android_gms_common_internal_zzm_zzb), this.f5802e);
                }
            } else {
                throw new IllegalStateException("Trying to unbind a GmsServiceConnection  that was not bound before.  config=" + com_google_android_gms_common_internal_zzm_zza);
            }
        }
    }

    public boolean m8610a(String str, ServiceConnection serviceConnection, String str2) {
        return m8606a(new zza(str), serviceConnection, str2);
    }

    public void m8611b(String str, ServiceConnection serviceConnection, String str2) {
        m8608b(new zza(str), serviceConnection, str2);
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                zzb com_google_android_gms_common_internal_zzm_zzb = (zzb) message.obj;
                synchronized (this.f5798a) {
                    if (com_google_android_gms_common_internal_zzm_zzb.m8602c()) {
                        if (com_google_android_gms_common_internal_zzm_zzb.m8597a()) {
                            com_google_android_gms_common_internal_zzm_zzb.m8601b("GmsClientSupervisor");
                        }
                        this.f5798a.remove(com_google_android_gms_common_internal_zzm_zzb.f5796g);
                    }
                    break;
                }
                return true;
            default:
                return false;
        }
    }
}
