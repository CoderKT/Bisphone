package com.crashlytics.android.answers;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build.VERSION;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.Crash.FatalException;
import io.fabric.sdk.android.services.common.Crash.LoggedException;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.common.IdManager.DeviceIdentifierType;
import io.fabric.sdk.android.services.common.SystemCurrentTimeProvider;
import io.fabric.sdk.android.services.events.GZIPQueueFileEventStorage;
import io.fabric.sdk.android.services.network.DefaultHttpRequestFactory;
import io.fabric.sdk.android.services.persistence.FileStoreImpl;
import io.fabric.sdk.android.services.persistence.PreferenceStore;
import io.fabric.sdk.android.services.persistence.PreferenceStoreImpl;
import io.fabric.sdk.android.services.settings.Settings;
import io.fabric.sdk.android.services.settings.SettingsData;
import java.io.File;
import java.util.Map;
import java.util.UUID;

abstract class AnswersKit extends Kit<Boolean> {
    SessionAnalyticsManager f5457a;
    private String f5458g;
    private String f5459h;
    private PreferenceStore f5460i;
    private long f5461j;

    AnswersKit() {
    }

    protected /* synthetic */ Object m8107z() {
        return m8103e();
    }

    @SuppressLint({"NewApi"})
    protected boolean m8098a() {
        try {
            this.f5460i = new PreferenceStoreImpl(this);
            Context C = m7860C();
            PackageInfo packageInfo = C.getPackageManager().getPackageInfo(C.getPackageName(), 0);
            this.f5458g = Integer.toString(packageInfo.versionCode);
            this.f5459h = packageInfo.versionName == null ? "0.0" : packageInfo.versionName;
            if (VERSION.SDK_INT >= 9) {
                this.f5461j = packageInfo.firstInstallTime;
            } else {
                this.f5461j = new File(C.getPackageManager().getApplicationInfo(C.getPackageName(), 0).sourceDir).lastModified();
            }
            return true;
        } catch (Throwable e) {
            Fabric.m12905g().m12874d("Answers", "Error setting up app properties", e);
            return false;
        }
    }

    protected Boolean m8103e() {
        Context C = m7860C();
        m8095a(C);
        try {
            SettingsData b = Settings.m13293a().m13297b();
            if (b == null) {
                return Boolean.valueOf(false);
            }
            if (b.f8442d.f8412d) {
                this.f5457a.m8116a(b.f8443e, m8104f());
                return Boolean.valueOf(true);
            }
            CommonUtils.m13014a(C, "Disabling analytics collection based on settings flag value.");
            this.f5457a.m8114a();
            return Boolean.valueOf(false);
        } catch (Throwable e) {
            Fabric.m12905g().m12874d("Answers", "Error dealing with settings", e);
            return Boolean.valueOf(false);
        }
    }

    public String m8101c() {
        return "com.crashlytics.sdk.android:answers";
    }

    public String m8102d() {
        return "1.1.2.37";
    }

    @SuppressLint({"CommitPrefEdits"})
    @TargetApi(14)
    private void m8095a(Context context) {
        try {
            SessionAnalyticsFilesManager sessionAnalyticsFilesManager = new SessionAnalyticsFilesManager(context, new SessionEventTransform(), new SystemCurrentTimeProvider(), new GZIPQueueFileEventStorage(m7860C(), m8106h(), "session_analytics.tap", "session_analytics_to_send"));
            IdManager B = m7859B();
            Map g = B.m13067g();
            SessionEventMetadata sessionEventMetadata = new SessionEventMetadata(context.getPackageName(), UUID.randomUUID().toString(), B.m13062b(), (String) g.get(DeviceIdentifierType.ANDROID_ID), (String) g.get(DeviceIdentifierType.ANDROID_ADVERTISING_ID), (String) g.get(DeviceIdentifierType.FONT_TOKEN), CommonUtils.m13038m(context), B.m13064d(), B.m13065e(), this.f5458g, this.f5459h);
            Application application = (Application) m7860C().getApplicationContext();
            if (application == null || VERSION.SDK_INT < 14) {
                this.f5457a = SessionAnalyticsManager.m8113a(context, sessionEventMetadata, sessionAnalyticsFilesManager, new DefaultHttpRequestFactory(Fabric.m12905g()));
            } else {
                this.f5457a = AutoSessionAnalyticsManager.m8126a(application, sessionEventMetadata, sessionAnalyticsFilesManager, new DefaultHttpRequestFactory(Fabric.m12905g()));
            }
            if (m8099a(this.f5461j)) {
                Fabric.m12905g().m12867a("Answers", "First launch");
                this.f5457a.m8118b();
                this.f5460i.m13248a(this.f5460i.m13249b().putBoolean("analytics_launched", true));
            }
        } catch (Throwable e) {
            CommonUtils.m13015a(context, "Crashlytics failed to initialize session analytics.", e);
        }
    }

    String m8104f() {
        return CommonUtils.m13023b(m7860C(), "com.crashlytics.ApiEndpoint");
    }

    public void m8097a(LoggedException loggedException) {
        if (this.f5457a != null) {
            this.f5457a.m8120b(loggedException.m13040a());
        }
    }

    public void m8096a(FatalException fatalException) {
        if (this.f5457a != null) {
            this.f5457a.m8117a(fatalException.m13040a());
        }
    }

    boolean m8105g() {
        return this.f5460i.m13247a().getBoolean("analytics_launched", false);
    }

    boolean m8099a(long j) {
        return !m8105g() && m8100b(j);
    }

    boolean m8100b(long j) {
        return System.currentTimeMillis() - j < 3600000;
    }

    File m8106h() {
        return new FileStoreImpl(this).m13245a();
    }
}
