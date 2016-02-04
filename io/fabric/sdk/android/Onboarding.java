package io.fabric.sdk.android;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import io.fabric.sdk.android.services.common.ApiKey;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.DeliveryMechanism;
import io.fabric.sdk.android.services.network.DefaultHttpRequestFactory;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import io.fabric.sdk.android.services.settings.AppRequestData;
import io.fabric.sdk.android.services.settings.AppSettingsData;
import io.fabric.sdk.android.services.settings.CreateAppSpiCall;
import io.fabric.sdk.android.services.settings.IconRequest;
import io.fabric.sdk.android.services.settings.Settings;
import io.fabric.sdk.android.services.settings.SettingsData;
import io.fabric.sdk.android.services.settings.UpdateAppSpiCall;
import java.util.Collection;

class Onboarding extends Kit<Boolean> {
    private final Collection<Kit> f8186a;
    private final HttpRequestFactory f8187g;
    private PackageManager f8188h;
    private String f8189i;
    private PackageInfo f8190j;
    private String f8191k;
    private String f8192l;
    private String f8193m;
    private String f8194n;
    private String f8195o;

    protected /* synthetic */ Object m12961z() {
        return m12957b();
    }

    public Onboarding(Collection<Kit> collection) {
        this.f8186a = collection;
        this.f8187g = new DefaultHttpRequestFactory();
    }

    public String m12959d() {
        return "1.3.0.41";
    }

    protected boolean m12956a() {
        try {
            this.f8193m = m7859B().m13068h();
            this.f8188h = m7860C().getPackageManager();
            this.f8189i = m7860C().getPackageName();
            this.f8190j = this.f8188h.getPackageInfo(this.f8189i, 0);
            this.f8191k = Integer.toString(this.f8190j.versionCode);
            this.f8192l = this.f8190j.versionName == null ? "0.0" : this.f8190j.versionName;
            this.f8194n = this.f8188h.getApplicationLabel(m7860C().getApplicationInfo()).toString();
            this.f8195o = Integer.toString(m7860C().getApplicationInfo().targetSdkVersion);
            return true;
        } catch (Throwable e) {
            Fabric.m12905g().m12874d("Fabric", "Failed init", e);
            return false;
        }
    }

    protected Boolean m12957b() {
        SettingsData b;
        boolean a;
        String k = CommonUtils.m13036k(m7860C());
        try {
            Settings.m13293a().m13295a(this, this.f, this.f8187g, this.f8191k, this.f8192l, m12960e()).m13298c();
            b = Settings.m13293a().m13297b();
        } catch (Throwable e) {
            Fabric.m12905g().m12874d("Fabric", "Error dealing with settings", e);
            b = null;
        }
        if (b != null) {
            try {
                a = m12953a(k, b.f8439a, this.f8186a);
            } catch (Throwable e2) {
                Fabric.m12905g().m12874d("Fabric", "Error performing auto configuration.", e2);
            }
            return Boolean.valueOf(a);
        }
        a = false;
        return Boolean.valueOf(a);
    }

    public String m12958c() {
        return "io.fabric.sdk.android:fabric";
    }

    private boolean m12953a(String str, AppSettingsData appSettingsData, Collection<Kit> collection) {
        if ("new".equals(appSettingsData.f8394b)) {
            if (m12954b(str, appSettingsData, collection)) {
                return Settings.m13293a().m13299d();
            }
            Fabric.m12905g().m12874d("Fabric", "Failed to create app with Crashlytics service.", null);
            return false;
        } else if ("configured".equals(appSettingsData.f8394b)) {
            return Settings.m13293a().m13299d();
        } else {
            if (!appSettingsData.f8397e) {
                return true;
            }
            Fabric.m12905g().m12867a("Fabric", "Server says an update is required - forcing a full App update.");
            m12955c(str, appSettingsData, collection);
            return true;
        }
    }

    private boolean m12954b(String str, AppSettingsData appSettingsData, Collection<Kit> collection) {
        return new CreateAppSpiCall(this, m12960e(), appSettingsData.f8395c, this.f8187g).m13259a(m12951a(IconRequest.m13291a(m7860C(), str), collection));
    }

    private boolean m12955c(String str, AppSettingsData appSettingsData, Collection<Kit> collection) {
        return m12952a(appSettingsData, IconRequest.m13291a(m7860C(), str), (Collection) collection);
    }

    private boolean m12952a(AppSettingsData appSettingsData, IconRequest iconRequest, Collection<Kit> collection) {
        return new UpdateAppSpiCall(this, m12960e(), appSettingsData.f8395c, this.f8187g).m13301a(m12951a(iconRequest, collection));
    }

    private AppRequestData m12951a(IconRequest iconRequest, Collection<Kit> collection) {
        return new AppRequestData(new ApiKey().m12991a(m7860C()), m7859B().m13063c(), this.f8192l, this.f8191k, CommonUtils.m13011a(CommonUtils.m13038m(r0)), this.f8194n, DeliveryMechanism.m13042a(this.f8193m).m13043a(), this.f8195o, "0", iconRequest, collection);
    }

    String m12960e() {
        return CommonUtils.m13023b(m7860C(), "com.crashlytics.ApiEndpoint");
    }
}
