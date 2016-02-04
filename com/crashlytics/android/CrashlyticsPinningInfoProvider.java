package com.crashlytics.android;

import io.fabric.sdk.android.services.network.PinningInfoProvider;
import java.io.InputStream;

class CrashlyticsPinningInfoProvider implements PinningInfoProvider {
    private final PinningInfoProvider f5352a;

    public CrashlyticsPinningInfoProvider(PinningInfoProvider pinningInfoProvider) {
        this.f5352a = pinningInfoProvider;
    }

    public InputStream m7930a() {
        return this.f5352a.m8041a();
    }

    public String m7931b() {
        return this.f5352a.m8042b();
    }

    public String[] m7932c() {
        return this.f5352a.m8043c();
    }

    public long m7933d() {
        return -1;
    }
}
