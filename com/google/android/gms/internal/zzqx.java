package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.api.GoogleApiClient.ServerAuthCodeCallbacks;

public final class zzqx implements Optional {
    public static final zzqx f6163a;
    private final boolean f6164b;
    private final boolean f6165c;
    private final String f6166d;
    private final ServerAuthCodeCallbacks f6167e;
    private final boolean f6168f;
    private final boolean f6169g;

    public final class zza {
        private boolean f6157a;
        private boolean f6158b;
        private String f6159c;
        private ServerAuthCodeCallbacks f6160d;
        private boolean f6161e;
        private boolean f6162f;

        public zzqx m9324a() {
            return new zzqx(this.f6158b, this.f6159c, this.f6160d, this.f6161e, this.f6162f, null);
        }
    }

    static {
        f6163a = new zza().m9324a();
    }

    private zzqx(boolean z, boolean z2, String str, ServerAuthCodeCallbacks serverAuthCodeCallbacks, boolean z3, boolean z4) {
        this.f6164b = z;
        this.f6165c = z2;
        this.f6166d = str;
        this.f6167e = serverAuthCodeCallbacks;
        this.f6168f = z3;
        this.f6169g = z4;
    }

    public boolean m9325a() {
        return this.f6164b;
    }

    public boolean m9326b() {
        return this.f6165c;
    }

    public String m9327c() {
        return this.f6166d;
    }

    public ServerAuthCodeCallbacks m9328d() {
        return this.f6167e;
    }

    public boolean m9329e() {
        return this.f6168f;
    }

    public boolean m9330f() {
        return this.f6169g;
    }
}
