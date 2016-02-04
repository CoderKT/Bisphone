package com.crashlytics.android.beta;

import android.content.Context;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.common.ApiKey;
import io.fabric.sdk.android.services.common.CurrentTimeProvider;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import io.fabric.sdk.android.services.persistence.PreferenceStore;
import io.fabric.sdk.android.services.settings.BetaSettingsData;

class CheckForUpdatesController {
    private final Context f5526a;
    private final Beta f5527b;
    private final IdManager f5528c;
    private final BetaSettingsData f5529d;
    private final BuildProperties f5530e;
    private final PreferenceStore f5531f;
    private final CurrentTimeProvider f5532g;
    private final HttpRequestFactory f5533h;

    public CheckForUpdatesController(Context context, Beta beta, IdManager idManager, BetaSettingsData betaSettingsData, BuildProperties buildProperties, PreferenceStore preferenceStore, CurrentTimeProvider currentTimeProvider, HttpRequestFactory httpRequestFactory) {
        this.f5526a = context;
        this.f5527b = beta;
        this.f5528c = idManager;
        this.f5529d = betaSettingsData;
        this.f5530e = buildProperties;
        this.f5531f = preferenceStore;
        this.f5532g = currentTimeProvider;
        this.f5533h = httpRequestFactory;
    }

    public void m8210a() {
        long a = this.f5532g.m13041a();
        long j = (long) (this.f5529d.f8400b * 1000);
        Fabric.m12905g().m12867a("Beta", "Check for updates delay: " + j);
        long j2 = this.f5531f.m13247a().getLong("last_update_check", 0);
        Fabric.m12905g().m12867a("Beta", "Check for updates last check time: " + j2);
        j += j2;
        Fabric.m12905g().m12867a("Beta", "Check for updates current time: " + a + ", next check time: " + j);
        if (a >= j) {
            try {
                Fabric.m12905g().m12867a("Beta", "Performing update check");
                String a2 = new ApiKey().m12991a(this.f5526a);
                new CheckForUpdatesRequest(this.f5527b, this.f5527b.m8206f(), this.f5529d.f8399a, this.f5533h, new CheckForUpdatesResponseTransform()).m8213a(a2, this.f5528c.m13060a(a2, this.f5530e.f5525d), this.f5530e);
            } finally {
                this.f5531f.m13249b().putLong("last_update_check", a).commit();
            }
        } else {
            Fabric.m12905g().m12867a("Beta", "Check for updates next check time was not passed");
        }
    }
}
