package com.google.android.gms.signin.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient.ServerAuthCodeCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.ServerAuthCodeCallbacks.CheckResult;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.AuthAccountRequest;
import com.google.android.gms.common.internal.BinderWrapper;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.internal.zzt;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzqw;
import com.google.android.gms.internal.zzqx;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;

public class zzi extends zzj<zzf> implements zzqw {
    private final boolean f6506d;
    private final zzf f6507e;
    private final zzqx f6508f;
    private Integer f6509g;
    private final ExecutorService f6510h;

    class zza extends com.google.android.gms.signin.internal.zzd.zza {
        private final zzqx f6504a;
        private final ExecutorService f6505b;

        /* renamed from: com.google.android.gms.signin.internal.zzi.zza.1 */
        class C08831 implements Runnable {
            final /* synthetic */ List f6496a;
            final /* synthetic */ String f6497b;
            final /* synthetic */ zzf f6498c;
            final /* synthetic */ zza f6499d;

            C08831(zza com_google_android_gms_signin_internal_zzi_zza, List list, String str, zzf com_google_android_gms_signin_internal_zzf) {
                this.f6499d = com_google_android_gms_signin_internal_zzi_zza;
                this.f6496a = list;
                this.f6497b = str;
                this.f6498c = com_google_android_gms_signin_internal_zzf;
            }

            public void run() {
                try {
                    CheckResult a = this.f6499d.m10519a().m8375a(this.f6497b, Collections.unmodifiableSet(new HashSet(this.f6496a)));
                    this.f6498c.m10502a(new CheckServerAuthResult(a.m8373a(), a.m8374b()));
                } catch (Throwable e) {
                    Log.e("SignInClientImpl", "RemoteException thrown when processing checkServerAuthorization callback", e);
                }
            }
        }

        /* renamed from: com.google.android.gms.signin.internal.zzi.zza.2 */
        class C08842 implements Runnable {
            final /* synthetic */ String f6500a;
            final /* synthetic */ String f6501b;
            final /* synthetic */ zzf f6502c;
            final /* synthetic */ zza f6503d;

            C08842(zza com_google_android_gms_signin_internal_zzi_zza, String str, String str2, zzf com_google_android_gms_signin_internal_zzf) {
                this.f6503d = com_google_android_gms_signin_internal_zzi_zza;
                this.f6500a = str;
                this.f6501b = str2;
                this.f6502c = com_google_android_gms_signin_internal_zzf;
            }

            public void run() {
                try {
                    this.f6502c.m10505a(this.f6503d.m10519a().m8376a(this.f6500a, this.f6501b));
                } catch (Throwable e) {
                    Log.e("SignInClientImpl", "RemoteException thrown when processing uploadServerAuthCode callback", e);
                }
            }
        }

        public zza(zzqx com_google_android_gms_internal_zzqx, ExecutorService executorService) {
            this.f6504a = com_google_android_gms_internal_zzqx;
            this.f6505b = executorService;
        }

        private ServerAuthCodeCallbacks m10519a() {
            return this.f6504a.m9328d();
        }

        public void m10521a(String str, String str2, zzf com_google_android_gms_signin_internal_zzf) {
            this.f6505b.submit(new C08842(this, str, str2, com_google_android_gms_signin_internal_zzf));
        }

        public void m10522a(String str, List<Scope> list, zzf com_google_android_gms_signin_internal_zzf) {
            this.f6505b.submit(new C08831(this, list, str, com_google_android_gms_signin_internal_zzf));
        }
    }

    public zzi(Context context, Looper looper, boolean z, zzf com_google_android_gms_common_internal_zzf, zzqx com_google_android_gms_internal_zzqx, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, ExecutorService executorService) {
        super(context, looper, 44, com_google_android_gms_common_internal_zzf, connectionCallbacks, onConnectionFailedListener);
        this.f6506d = z;
        this.f6507e = com_google_android_gms_common_internal_zzf;
        this.f6508f = com_google_android_gms_internal_zzqx;
        this.f6509g = com_google_android_gms_common_internal_zzf.m8545i();
        this.f6510h = executorService;
    }

    public static Bundle m10523a(zzqx com_google_android_gms_internal_zzqx, Integer num, ExecutorService executorService) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", com_google_android_gms_internal_zzqx.m9325a());
        bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", com_google_android_gms_internal_zzqx.m9326b());
        bundle.putString("com.google.android.gms.signin.internal.serverClientId", com_google_android_gms_internal_zzqx.m9327c());
        if (com_google_android_gms_internal_zzqx.m9328d() != null) {
            bundle.putParcelable("com.google.android.gms.signin.internal.signInCallbacks", new BinderWrapper(new zza(com_google_android_gms_internal_zzqx, executorService).asBinder()));
        }
        if (num != null) {
            bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", num.intValue());
        }
        bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", com_google_android_gms_internal_zzqx.m9329e());
        bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", com_google_android_gms_internal_zzqx.m9330f());
        return bundle;
    }

    protected /* synthetic */ IInterface m10524a(IBinder iBinder) {
        return m10528b(iBinder);
    }

    public void m10525a(zzp com_google_android_gms_common_internal_zzp, Set<Scope> set, zze com_google_android_gms_signin_internal_zze) {
        zzx.m8719a((Object) com_google_android_gms_signin_internal_zze, (Object) "Expecting a valid ISignInCallbacks");
        try {
            ((zzf) m8522o()).m10499a(new AuthAccountRequest(com_google_android_gms_common_internal_zzp, set), com_google_android_gms_signin_internal_zze);
        } catch (Throwable e) {
            Log.w("SignInClientImpl", "Remote service probably died when authAccount is called");
            try {
                com_google_android_gms_signin_internal_zze.m9080a(new ConnectionResult(8, null), new AuthAccountResult());
            } catch (RemoteException e2) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onAuthAccount should be executed from the same process, unexpected RemoteException.", e);
            }
        }
    }

    public void m10526a(zzp com_google_android_gms_common_internal_zzp, boolean z) {
        try {
            ((zzf) m8522o()).m10501a(com_google_android_gms_common_internal_zzp, this.f6509g.intValue(), z);
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
        }
    }

    public void m10527a(zzt com_google_android_gms_common_internal_zzt) {
        zzx.m8719a((Object) com_google_android_gms_common_internal_zzt, (Object) "Expecting a valid IResolveAccountCallbacks");
        try {
            ((zzf) m8522o()).m10500a(new ResolveAccountRequest(this.f6507e.m8538b(), this.f6509g.intValue()), com_google_android_gms_common_internal_zzt);
        } catch (Throwable e) {
            Log.w("SignInClientImpl", "Remote service probably died when resolveAccount is called");
            try {
                com_google_android_gms_common_internal_zzt.m8711a(new ResolveAccountResponse(8));
            } catch (RemoteException e2) {
                Log.wtf("SignInClientImpl", "IResolveAccountCallbacks#onAccountResolutionComplete should be executed from the same process, unexpected RemoteException.", e);
            }
        }
    }

    protected zzf m10528b(IBinder iBinder) {
        return com.google.android.gms.signin.internal.zzf.zza.m10515a(iBinder);
    }

    public boolean m10529c() {
        return this.f6506d;
    }

    protected String m10530e() {
        return "com.google.android.gms.signin.service.START";
    }

    protected String m10531f() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }

    public void m10532g() {
        try {
            ((zzf) m8522o()).m10497a(this.f6509g.intValue());
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
        }
    }

    public void m10533h() {
        m8505a(new zzj.zzf(this));
    }

    protected Bundle m10534l() {
        Bundle a = m10523a(this.f6508f, this.f6507e.m8545i(), this.f6510h);
        if (!m8517j().getPackageName().equals(this.f6507e.m8542f())) {
            a.putString("com.google.android.gms.signin.internal.realClientPackageName", this.f6507e.m8542f());
        }
        return a;
    }
}
