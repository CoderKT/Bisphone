package io.fabric.sdk.android.services.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.persistence.PreferenceStore;
import io.fabric.sdk.android.services.persistence.PreferenceStoreImpl;

class AdvertisingInfoProvider {
    private final Context f8202a;
    private final PreferenceStore f8203b;

    /* renamed from: io.fabric.sdk.android.services.common.AdvertisingInfoProvider.1 */
    class C09531 extends BackgroundPriorityRunnable {
        final /* synthetic */ AdvertisingInfo f8200a;
        final /* synthetic */ AdvertisingInfoProvider f8201b;

        C09531(AdvertisingInfoProvider advertisingInfoProvider, AdvertisingInfo advertisingInfo) {
            this.f8201b = advertisingInfoProvider;
            this.f8200a = advertisingInfo;
        }

        public void m12969a() {
            AdvertisingInfo a = this.f8201b.m12975e();
            if (!this.f8200a.equals(a)) {
                Fabric.m12905g().m12867a("Fabric", "Asychronously getting Advertising Info and storing it to preferences");
                this.f8201b.m12973b(a);
            }
        }
    }

    public AdvertisingInfoProvider(Context context) {
        this.f8202a = context.getApplicationContext();
        this.f8203b = new PreferenceStoreImpl(context, "TwitterAdvertisingInfoPreferences");
    }

    public AdvertisingInfo m12976a() {
        AdvertisingInfo b = m12977b();
        if (m12974c(b)) {
            Fabric.m12905g().m12867a("Fabric", "Using AdvertisingInfo from Preference Store");
            m12971a(b);
            return b;
        }
        b = m12975e();
        m12973b(b);
        return b;
    }

    private void m12971a(AdvertisingInfo advertisingInfo) {
        new Thread(new C09531(this, advertisingInfo)).start();
    }

    @SuppressLint({"CommitPrefEdits"})
    private void m12973b(AdvertisingInfo advertisingInfo) {
        if (m12974c(advertisingInfo)) {
            this.f8203b.m13248a(this.f8203b.m13249b().putString("advertising_id", advertisingInfo.f8198a).putBoolean("limit_ad_tracking_enabled", advertisingInfo.f8199b));
        } else {
            this.f8203b.m13248a(this.f8203b.m13249b().remove("advertising_id").remove("limit_ad_tracking_enabled"));
        }
    }

    protected AdvertisingInfo m12977b() {
        return new AdvertisingInfo(this.f8203b.m13247a().getString("advertising_id", ""), this.f8203b.m13247a().getBoolean("limit_ad_tracking_enabled", false));
    }

    public AdvertisingInfoStrategy m12978c() {
        return new AdvertisingInfoReflectionStrategy(this.f8202a);
    }

    public AdvertisingInfoStrategy m12979d() {
        return new AdvertisingInfoServiceStrategy(this.f8202a);
    }

    private boolean m12974c(AdvertisingInfo advertisingInfo) {
        return (advertisingInfo == null || TextUtils.isEmpty(advertisingInfo.f8198a)) ? false : true;
    }

    private AdvertisingInfo m12975e() {
        AdvertisingInfo a = m12978c().m12980a();
        if (m12974c(a)) {
            Fabric.m12905g().m12867a("Fabric", "Using AdvertisingInfo from Reflection Provider");
        } else {
            a = m12979d().m12980a();
            if (m12974c(a)) {
                Fabric.m12905g().m12867a("Fabric", "Using AdvertisingInfo from Service Provider");
            } else {
                Fabric.m12905g().m12867a("Fabric", "AdvertisingInfo not present");
            }
        }
        return a;
    }
}
