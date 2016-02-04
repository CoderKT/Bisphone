package com.google.android.gms.internal;

import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzx;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class zzlp extends Fragment implements OnCancelListener {
    private static final GoogleApiAvailability f6099a;
    private boolean f6100b;
    private boolean f6101c;
    private int f6102d;
    private ConnectionResult f6103e;
    private final Handler f6104f;
    private zzll f6105g;
    private final SparseArray<zza> f6106h;

    class zza implements OnConnectionFailedListener {
        public final int f6090a;
        public final GoogleApiClient f6091b;
        public final OnConnectionFailedListener f6092c;
        final /* synthetic */ zzlp f6093d;

        public zza(zzlp com_google_android_gms_internal_zzlp, int i, GoogleApiClient googleApiClient, OnConnectionFailedListener onConnectionFailedListener) {
            this.f6093d = com_google_android_gms_internal_zzlp;
            this.f6090a = i;
            this.f6091b = googleApiClient;
            this.f6092c = onConnectionFailedListener;
            googleApiClient.m8382a((OnConnectionFailedListener) this);
        }

        public void m9206a() {
            this.f6091b.m8385b(this);
            this.f6091b.m8386c();
        }

        public void m9207a(ConnectionResult connectionResult) {
            this.f6093d.f6104f.post(new zzb(this.f6093d, this.f6090a, connectionResult));
        }

        public void m9208a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.append(str).append("GoogleApiClient #").print(this.f6090a);
            printWriter.println(":");
            this.f6091b.m8383a(str + "  ", fileDescriptor, printWriter, strArr);
        }
    }

    class zzb implements Runnable {
        final /* synthetic */ zzlp f6096a;
        private final int f6097b;
        private final ConnectionResult f6098c;

        /* renamed from: com.google.android.gms.internal.zzlp.zzb.1 */
        class C08641 extends zzll {
            final /* synthetic */ Dialog f6094b;
            final /* synthetic */ zzb f6095c;

            C08641(zzb com_google_android_gms_internal_zzlp_zzb, Dialog dialog) {
                this.f6095c = com_google_android_gms_internal_zzlp_zzb;
                this.f6094b = dialog;
            }

            protected void m9209a() {
                this.f6095c.f6096a.m9210M();
                this.f6094b.dismiss();
            }
        }

        public zzb(zzlp com_google_android_gms_internal_zzlp, int i, ConnectionResult connectionResult) {
            this.f6096a = com_google_android_gms_internal_zzlp;
            this.f6097b = i;
            this.f6098c = connectionResult;
        }

        public void run() {
            if (this.f6096a.f6100b && !this.f6096a.f6101c) {
                this.f6096a.f6101c = true;
                this.f6096a.f6102d = this.f6097b;
                this.f6096a.f6103e = this.f6098c;
                if (this.f6098c.m8302a()) {
                    try {
                        this.f6098c.m8301a(this.f6096a.m227i(), ((this.f6096a.m227i().getSupportFragmentManager().m359d().indexOf(this.f6096a) + 1) << 16) + 1);
                    } catch (SendIntentException e) {
                        this.f6096a.m9210M();
                    }
                } else if (zzlp.f6099a.m8313a(this.f6098c.m8304c())) {
                    GooglePlayServicesUtil.m8322a(this.f6098c.m8304c(), this.f6096a.m227i(), this.f6096a, 2, this.f6096a);
                } else if (this.f6098c.m8304c() == 18) {
                    this.f6096a.f6105g = zzll.m9166a(this.f6096a.m227i().getApplicationContext(), new C08641(this, zzlp.f6099a.m8311a(this.f6096a.m227i(), this.f6096a)));
                } else {
                    this.f6096a.m9216a(this.f6097b, this.f6098c);
                }
            }
        }
    }

    static {
        f6099a = GoogleApiAvailability.m8308a();
    }

    public zzlp() {
        this.f6102d = -1;
        this.f6104f = new Handler(Looper.getMainLooper());
        this.f6106h = new SparseArray();
    }

    private void m9210M() {
        this.f6101c = false;
        this.f6102d = -1;
        this.f6103e = null;
        if (this.f6105g != null) {
            this.f6105g.m9169b();
            this.f6105g = null;
        }
        for (int i = 0; i < this.f6106h.size(); i++) {
            ((zza) this.f6106h.valueAt(i)).f6091b.m8384b();
        }
    }

    public static zzlp m9215a(FragmentActivity fragmentActivity) {
        zzx.m8725b("Must be called from main thread of process");
        try {
            zzlp com_google_android_gms_internal_zzlp = (zzlp) fragmentActivity.getSupportFragmentManager().m352a("GmsSupportLifecycleFragment");
            return (com_google_android_gms_internal_zzlp == null || com_google_android_gms_internal_zzlp.m235n()) ? null : com_google_android_gms_internal_zzlp;
        } catch (Throwable e) {
            throw new IllegalStateException("Fragment with tag GmsSupportLifecycleFragment is not a SupportLifecycleFragment", e);
        }
    }

    private void m9216a(int i, ConnectionResult connectionResult) {
        Log.w("GmsSupportLifecycleFragment", "Unresolved error while connecting client. Stopping auto-manage.");
        zza com_google_android_gms_internal_zzlp_zza = (zza) this.f6106h.get(i);
        if (com_google_android_gms_internal_zzlp_zza != null) {
            m9228b(i);
            OnConnectionFailedListener onConnectionFailedListener = com_google_android_gms_internal_zzlp_zza.f6092c;
            if (onConnectionFailedListener != null) {
                onConnectionFailedListener.m5633a(connectionResult);
            }
        }
        m9210M();
    }

    public static zzlp m9220b(FragmentActivity fragmentActivity) {
        zzlp a = m9215a(fragmentActivity);
        FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        if (a != null) {
            return a;
        }
        Fragment com_google_android_gms_internal_zzlp = new zzlp();
        supportFragmentManager.m353a().m113a(com_google_android_gms_internal_zzlp, "GmsSupportLifecycleFragment").m114b();
        supportFragmentManager.m357b();
        return com_google_android_gms_internal_zzlp;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m9224a(int r5, int r6, android.content.Intent r7) {
        /*
        r4 = this;
        r0 = 1;
        r1 = 0;
        switch(r5) {
            case 1: goto L_0x0019;
            case 2: goto L_0x000c;
            default: goto L_0x0005;
        };
    L_0x0005:
        r0 = r1;
    L_0x0006:
        if (r0 == 0) goto L_0x0029;
    L_0x0008:
        r4.m9210M();
    L_0x000b:
        return;
    L_0x000c:
        r2 = f6099a;
        r3 = r4.m227i();
        r2 = r2.m8310a(r3);
        if (r2 != 0) goto L_0x0005;
    L_0x0018:
        goto L_0x0006;
    L_0x0019:
        r2 = -1;
        if (r6 == r2) goto L_0x0006;
    L_0x001c:
        if (r6 != 0) goto L_0x0005;
    L_0x001e:
        r0 = new com.google.android.gms.common.ConnectionResult;
        r2 = 13;
        r3 = 0;
        r0.<init>(r2, r3);
        r4.f6103e = r0;
        goto L_0x0005;
    L_0x0029:
        r0 = r4.f6102d;
        r1 = r4.f6103e;
        r4.m9216a(r0, r1);
        goto L_0x000b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzlp.a(int, int, android.content.Intent):void");
    }

    public void m9225a(int i, GoogleApiClient googleApiClient, OnConnectionFailedListener onConnectionFailedListener) {
        zzx.m8719a((Object) googleApiClient, (Object) "GoogleApiClient instance cannot be null");
        zzx.m8723a(this.f6106h.indexOfKey(i) < 0, "Already managing a GoogleApiClient with id " + i);
        this.f6106h.put(i, new zza(this, i, googleApiClient, onConnectionFailedListener));
        if (this.f6100b && !this.f6101c) {
            googleApiClient.m8384b();
        }
    }

    public void m9226a(Bundle bundle) {
        super.m195a(bundle);
        if (bundle != null) {
            this.f6101c = bundle.getBoolean("resolving_error", false);
            this.f6102d = bundle.getInt("failed_client_id", -1);
            if (this.f6102d >= 0) {
                this.f6103e = new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution"));
            }
        }
    }

    public void m9227a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.m200a(str, fileDescriptor, printWriter, strArr);
        for (int i = 0; i < this.f6106h.size(); i++) {
            ((zza) this.f6106h.valueAt(i)).m9208a(str, fileDescriptor, printWriter, strArr);
        }
    }

    public void m9228b(int i) {
        zza com_google_android_gms_internal_zzlp_zza = (zza) this.f6106h.get(i);
        this.f6106h.remove(i);
        if (com_google_android_gms_internal_zzlp_zza != null) {
            com_google_android_gms_internal_zzlp_zza.m9206a();
        }
    }

    public void m9229c() {
        super.m208c();
        this.f6100b = true;
        if (!this.f6101c) {
            for (int i = 0; i < this.f6106h.size(); i++) {
                ((zza) this.f6106h.valueAt(i)).f6091b.m8384b();
            }
        }
    }

    public void m9230d() {
        super.m212d();
        this.f6100b = false;
        for (int i = 0; i < this.f6106h.size(); i++) {
            ((zza) this.f6106h.valueAt(i)).f6091b.m8386c();
        }
    }

    public void m9231e(Bundle bundle) {
        super.m218e(bundle);
        bundle.putBoolean("resolving_error", this.f6101c);
        if (this.f6102d >= 0) {
            bundle.putInt("failed_client_id", this.f6102d);
            bundle.putInt("failed_status", this.f6103e.m8304c());
            bundle.putParcelable("failed_resolution", this.f6103e.m8305d());
        }
    }

    public void onCancel(DialogInterface dialogInterface) {
        m9216a(this.f6102d, new ConnectionResult(13, null));
    }
}
