package com.crashlytics.android.beta;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.cache.MemoryValueCache;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.DeviceIdentifierProvider;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.common.IdManager.DeviceIdentifierType;
import io.fabric.sdk.android.services.common.SystemCurrentTimeProvider;
import io.fabric.sdk.android.services.network.DefaultHttpRequestFactory;
import io.fabric.sdk.android.services.persistence.PreferenceStoreImpl;
import io.fabric.sdk.android.services.settings.BetaSettingsData;
import io.fabric.sdk.android.services.settings.Settings;
import io.fabric.sdk.android.services.settings.SettingsData;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Beta extends Kit<Boolean> implements DeviceIdentifierProvider {
    private final MemoryValueCache<String> f5520a;
    private final DeviceTokenLoader f5521g;

    public Beta() {
        this.f5520a = new MemoryValueCache();
        this.f5521g = new DeviceTokenLoader();
    }

    protected /* synthetic */ Object m8207z() {
        return m8202b();
    }

    protected Boolean m8202b() {
        Fabric.m12905g().m12867a("Beta", "Beta kit initializing...");
        Context C = m7860C();
        IdManager B = m7859B();
        if (TextUtils.isEmpty(m8197a(C, B.m13068h()))) {
            Fabric.m12905g().m12867a("Beta", "A Beta device token was not found for this app");
            return Boolean.valueOf(false);
        }
        Fabric.m12905g().m12867a("Beta", "Beta device token is present, checking for app updates.");
        BetaSettingsData g = m8199g();
        BuildProperties a = m8196a(C);
        if (m8200a(g, a)) {
            m8198a(C, B, g, a);
        }
        return Boolean.valueOf(true);
    }

    public Map<DeviceIdentifierType, String> m8205e() {
        CharSequence a = m8197a(m7860C(), m7859B().m13068h());
        Map<DeviceIdentifierType, String> hashMap = new HashMap();
        if (!TextUtils.isEmpty(a)) {
            hashMap.put(DeviceIdentifierType.FONT_TOKEN, a);
        }
        return hashMap;
    }

    public String m8203c() {
        return "com.crashlytics.sdk.android:beta";
    }

    public String m8204d() {
        return "1.1.2.37";
    }

    private void m8198a(Context context, IdManager idManager, BetaSettingsData betaSettingsData, BuildProperties buildProperties) {
        new CheckForUpdatesController(context, this, idManager, betaSettingsData, buildProperties, new PreferenceStoreImpl(Fabric.m12897a(Beta.class)), new SystemCurrentTimeProvider(), new DefaultHttpRequestFactory(Fabric.m12905g())).m8210a();
    }

    @TargetApi(11)
    boolean m8201a(String str, int i) {
        if (i < 11) {
            return str == null;
        } else {
            return "io.crash.air".equals(str);
        }
    }

    boolean m8200a(BetaSettingsData betaSettingsData, BuildProperties buildProperties) {
        return (betaSettingsData == null || TextUtils.isEmpty(betaSettingsData.f8399a) || buildProperties == null) ? false : true;
    }

    private String m8197a(Context context, String str) {
        if (m8201a(str, VERSION.SDK_INT)) {
            Fabric.m12905g().m12867a("Beta", "App was installed by Beta. Getting device token");
            try {
                String str2 = (String) this.f5520a.m12965a(context, this.f5521g);
                if ("".equals(str2)) {
                    str2 = null;
                }
                return str2;
            } catch (Throwable e) {
                Fabric.m12905g().m12874d("Beta", "Failed to load the Beta device token", e);
                return null;
            }
        }
        Fabric.m12905g().m12867a("Beta", "App was not installed by Beta. Skipping device token");
        return null;
    }

    private BetaSettingsData m8199g() {
        SettingsData b = Settings.m13293a().m13297b();
        if (b != null) {
            return b.f8444f;
        }
        return null;
    }

    private BuildProperties m8196a(Context context) {
        Throwable th;
        Throwable e;
        Object obj;
        Throwable th2;
        BuildProperties buildProperties;
        InputStream inputStream = null;
        InputStream open;
        try {
            open = context.getAssets().open("crashlytics-build.properties");
            if (open != null) {
                BuildProperties a;
                try {
                    a = BuildProperties.m8208a(open);
                } catch (Throwable e2) {
                    th = e2;
                    obj = inputStream;
                    th2 = th;
                    try {
                        Fabric.m12905g().m12874d("Beta", "Error reading Beta build properties", th2);
                        if (open != null) {
                            try {
                                open.close();
                            } catch (Throwable th22) {
                                Fabric.m12905g().m12874d("Beta", "Error closing Beta build properties asset", th22);
                            }
                        }
                        return buildProperties;
                    } catch (Throwable th3) {
                        e2 = th3;
                        if (open != null) {
                            try {
                                open.close();
                            } catch (Throwable th222) {
                                Fabric.m12905g().m12874d("Beta", "Error closing Beta build properties asset", th222);
                            }
                        }
                        throw e2;
                    }
                }
                try {
                    Fabric.m12905g().m12867a("Beta", a.f5525d + " build properties: " + a.f5523b + " (" + a.f5522a + ")" + " - " + a.f5524c);
                    buildProperties = a;
                } catch (Throwable e22) {
                    th = e22;
                    buildProperties = a;
                    th222 = th;
                    Fabric.m12905g().m12874d("Beta", "Error reading Beta build properties", th222);
                    if (open != null) {
                        open.close();
                    }
                    return buildProperties;
                }
            }
            obj = inputStream;
            if (open != null) {
                try {
                    open.close();
                } catch (Throwable th2222) {
                    Fabric.m12905g().m12874d("Beta", "Error closing Beta build properties asset", th2222);
                }
            }
        } catch (Throwable e222) {
            open = inputStream;
            InputStream inputStream2 = inputStream;
            th2222 = e222;
            buildProperties = inputStream2;
            Fabric.m12905g().m12874d("Beta", "Error reading Beta build properties", th2222);
            if (open != null) {
                open.close();
            }
            return buildProperties;
        } catch (Throwable th4) {
            e222 = th4;
            open = inputStream;
            if (open != null) {
                open.close();
            }
            throw e222;
        }
        return buildProperties;
    }

    String m8206f() {
        return CommonUtils.m13023b(m7860C(), "com.crashlytics.ApiEndpoint");
    }
}
