package io.fabric.sdk.android.services.settings;

import android.content.Context;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.ApiKey;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.DeliveryMechanism;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.common.SystemCurrentTimeProvider;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

public class Settings {
    private final AtomicReference<SettingsData> f8431a;
    private final CountDownLatch f8432b;
    private SettingsController f8433c;
    private boolean f8434d;

    public interface SettingsAccess<T> {
        T m7850b(SettingsData settingsData);
    }

    class LazyHolder {
        private static final Settings f8430a;

        static {
            f8430a = new Settings();
        }
    }

    public static Settings m13293a() {
        return LazyHolder.f8430a;
    }

    private Settings() {
        this.f8431a = new AtomicReference();
        this.f8432b = new CountDownLatch(1);
        this.f8434d = false;
    }

    public synchronized Settings m13295a(Kit kit, IdManager idManager, HttpRequestFactory httpRequestFactory, String str, String str2, String str3) {
        Settings settings;
        if (this.f8434d) {
            settings = this;
        } else {
            if (this.f8433c == null) {
                Context C = kit.m7860C();
                String c = idManager.m13063c();
                String a = new ApiKey().m12991a(C);
                String h = idManager.m13068h();
                SystemCurrentTimeProvider systemCurrentTimeProvider = new SystemCurrentTimeProvider();
                DefaultSettingsJsonTransform defaultSettingsJsonTransform = new DefaultSettingsJsonTransform();
                DefaultCachedSettingsIo defaultCachedSettingsIo = new DefaultCachedSettingsIo(kit);
                String k = CommonUtils.m13036k(C);
                String str4 = str3;
                DefaultSettingsSpiCall defaultSettingsSpiCall = new DefaultSettingsSpiCall(kit, str4, String.format(Locale.US, "https://settings.crashlytics.com/spi/v2/platforms/android/apps/%s/settings", new Object[]{c}), httpRequestFactory);
                h = str2;
                String str5 = str;
                this.f8433c = new DefaultSettingsController(kit, new SettingsRequest(a, idManager.m13060a(a, c), CommonUtils.m13011a(CommonUtils.m13038m(C)), h, str5, DeliveryMechanism.m13042a(h).m13043a(), k), systemCurrentTimeProvider, defaultSettingsJsonTransform, defaultCachedSettingsIo, defaultSettingsSpiCall);
            }
            this.f8434d = true;
            settings = this;
        }
        return settings;
    }

    public <T> T m13296a(SettingsAccess<T> settingsAccess, T t) {
        SettingsData settingsData = (SettingsData) this.f8431a.get();
        return settingsData == null ? t : settingsAccess.m7850b(settingsData);
    }

    public SettingsData m13297b() {
        try {
            this.f8432b.await();
            return (SettingsData) this.f8431a.get();
        } catch (InterruptedException e) {
            Fabric.m12905g().m12873d("Fabric", "Interrupted while waiting for settings data.");
            return null;
        }
    }

    public synchronized boolean m13298c() {
        SettingsData a;
        a = this.f8433c.m13262a();
        m13294a(a);
        return a != null;
    }

    public synchronized boolean m13299d() {
        SettingsData a;
        a = this.f8433c.m13263a(SettingsCacheBehavior.SKIP_CACHE_LOOKUP);
        m13294a(a);
        if (a == null) {
            Fabric.m12905g().m12874d("Fabric", "Failed to force reload of settings from Crashlytics.", null);
        }
        return a != null;
    }

    private void m13294a(SettingsData settingsData) {
        this.f8431a.set(settingsData);
        this.f8432b.countDown();
    }
}
