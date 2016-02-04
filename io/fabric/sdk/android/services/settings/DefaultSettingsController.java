package io.fabric.sdk.android.services.settings;

import android.annotation.SuppressLint;
import android.content.SharedPreferences.Editor;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.CurrentTimeProvider;
import io.fabric.sdk.android.services.persistence.PreferenceStore;
import io.fabric.sdk.android.services.persistence.PreferenceStoreImpl;
import org.json.JSONObject;

class DefaultSettingsController implements SettingsController {
    private final SettingsRequest f8402a;
    private final SettingsJsonTransform f8403b;
    private final CurrentTimeProvider f8404c;
    private final CachedSettingsIo f8405d;
    private final SettingsSpiCall f8406e;
    private final Kit f8407f;
    private final PreferenceStore f8408g;

    public DefaultSettingsController(Kit kit, SettingsRequest settingsRequest, CurrentTimeProvider currentTimeProvider, SettingsJsonTransform settingsJsonTransform, CachedSettingsIo cachedSettingsIo, SettingsSpiCall settingsSpiCall) {
        this.f8407f = kit;
        this.f8402a = settingsRequest;
        this.f8404c = currentTimeProvider;
        this.f8403b = settingsJsonTransform;
        this.f8405d = cachedSettingsIo;
        this.f8406e = settingsSpiCall;
        this.f8408g = new PreferenceStoreImpl(this.f8407f);
    }

    public SettingsData m13266a() {
        return m13267a(SettingsCacheBehavior.USE_CACHE);
    }

    public SettingsData m13267a(SettingsCacheBehavior settingsCacheBehavior) {
        Throwable th;
        SettingsData settingsData;
        Throwable th2;
        SettingsData settingsData2 = null;
        try {
            if (!(Fabric.m12906h() || m13271d())) {
                settingsData2 = m13265b(settingsCacheBehavior);
            }
            if (settingsData2 == null) {
                try {
                    JSONObject a = this.f8406e.m13284a(this.f8402a);
                    if (a != null) {
                        settingsData2 = this.f8403b.m13272a(this.f8404c, a);
                        this.f8405d.m13258a(settingsData2.f8445g, a);
                        m13264a(a, "Loaded settings: ");
                        m13268a(m13269b());
                    }
                } catch (Throwable e) {
                    th = e;
                    settingsData = settingsData2;
                    th2 = th;
                    Fabric.m12905g().m12874d("Fabric", "Unknown error while loading Crashlytics settings. Crashes will be cached until settings can be retrieved.", th2);
                    return settingsData;
                }
            }
            settingsData = settingsData2;
            if (settingsData == null) {
                try {
                    settingsData = m13265b(SettingsCacheBehavior.IGNORE_CACHE_EXPIRATION);
                } catch (Exception e2) {
                    th2 = e2;
                    Fabric.m12905g().m12874d("Fabric", "Unknown error while loading Crashlytics settings. Crashes will be cached until settings can be retrieved.", th2);
                    return settingsData;
                }
            }
        } catch (Throwable e3) {
            th = e3;
            settingsData = null;
            th2 = th;
            Fabric.m12905g().m12874d("Fabric", "Unknown error while loading Crashlytics settings. Crashes will be cached until settings can be retrieved.", th2);
            return settingsData;
        }
        return settingsData;
    }

    private SettingsData m13265b(SettingsCacheBehavior settingsCacheBehavior) {
        Throwable th;
        SettingsData settingsData = null;
        try {
            if (SettingsCacheBehavior.SKIP_CACHE_LOOKUP.equals(settingsCacheBehavior)) {
                return null;
            }
            JSONObject a = this.f8405d.m13257a();
            if (a != null) {
                SettingsData a2 = this.f8403b.m13272a(this.f8404c, a);
                if (a2 != null) {
                    m13264a(a, "Loaded cached settings: ");
                    long a3 = this.f8404c.m13041a();
                    if (SettingsCacheBehavior.IGNORE_CACHE_EXPIRATION.equals(settingsCacheBehavior) || !a2.m13300a(a3)) {
                        try {
                            Fabric.m12905g().m12867a("Fabric", "Returning cached settings.");
                            return a2;
                        } catch (Throwable e) {
                            Throwable th2 = e;
                            settingsData = a2;
                            th = th2;
                            Fabric.m12905g().m12874d("Fabric", "Failed to get cached settings", th);
                            return settingsData;
                        }
                    }
                    Fabric.m12905g().m12867a("Fabric", "Cached settings have expired.");
                    return null;
                }
                Fabric.m12905g().m12874d("Fabric", "Failed to transform cached settings data.", null);
                return null;
            }
            Fabric.m12905g().m12867a("Fabric", "No cached settings data found.");
            return null;
        } catch (Exception e2) {
            th = e2;
            Fabric.m12905g().m12874d("Fabric", "Failed to get cached settings", th);
            return settingsData;
        }
    }

    private void m13264a(JSONObject jSONObject, String str) {
        if (!CommonUtils.m13030e(this.f8407f.m7860C())) {
            jSONObject = this.f8403b.m13273a(jSONObject);
        }
        Fabric.m12905g().m12867a("Fabric", str + jSONObject.toString());
    }

    String m13269b() {
        return CommonUtils.m13011a(CommonUtils.m13038m(this.f8407f.m7860C()));
    }

    String m13270c() {
        return this.f8408g.m13247a().getString("existing_instance_identifier", "");
    }

    @SuppressLint({"CommitPrefEdits"})
    boolean m13268a(String str) {
        Editor b = this.f8408g.m13249b();
        b.putString("existing_instance_identifier", str);
        return this.f8408g.m13248a(b);
    }

    boolean m13271d() {
        return !m13270c().equals(m13269b());
    }
}
