package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzx;
import java.util.concurrent.atomic.AtomicReference;

public class zzlb {

    public abstract class zza<R extends Result, A extends zzb> extends zzlc<R> implements zzf<A> {
        private final zzc<A> f5996b;
        private AtomicReference<zze> f5997c;

        protected zza(zzc<A> com_google_android_gms_common_api_Api_zzc_A, GoogleApiClient googleApiClient) {
            super(((GoogleApiClient) zzx.m8719a((Object) googleApiClient, (Object) "GoogleApiClient must not be null")).m8379a());
            this.f5997c = new AtomicReference();
            this.f5996b = (zzc) zzx.m8718a((Object) com_google_android_gms_common_api_Api_zzc_A);
        }

        private void m9047a(RemoteException remoteException) {
            m9049a(new Status(8, remoteException.getLocalizedMessage(), null));
        }

        public final void m9048a(A a) {
            try {
                m9052b(a);
            } catch (RemoteException e) {
                m9047a(e);
                throw e;
            } catch (RemoteException e2) {
                m9047a(e2);
            }
        }

        public final void m9049a(Status status) {
            zzx.m8727b(!status.m8402e(), "Failed result must not be success");
            m9029a(m9032c(status));
        }

        public void m9050a(zze com_google_android_gms_internal_zzli_zze) {
            this.f5997c.set(com_google_android_gms_internal_zzli_zze);
        }

        public final zzc<A> m9051b() {
            return this.f5996b;
        }

        protected abstract void m9052b(A a);

        public void m9053c() {
            m9030a(null);
        }

        public int m9054d() {
            return 0;
        }

        protected void m9055e() {
            zze com_google_android_gms_internal_zzli_zze = (zze) this.f5997c.getAndSet(null);
            if (com_google_android_gms_internal_zzli_zze != null) {
                com_google_android_gms_internal_zzli_zze.m9157a(this);
            }
        }
    }
}
