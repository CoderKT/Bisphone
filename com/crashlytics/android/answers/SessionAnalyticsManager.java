package com.crashlytics.android.answers;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import io.fabric.sdk.android.services.common.ExecutorUtils;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import io.fabric.sdk.android.services.settings.AnalyticsSettingsData;
import java.util.concurrent.ScheduledExecutorService;

class SessionAnalyticsManager {
    final SessionEventMetadata f5463a;
    final SessionEventsHandler f5464b;

    public static SessionAnalyticsManager m8113a(Context context, SessionEventMetadata sessionEventMetadata, SessionAnalyticsFilesManager sessionAnalyticsFilesManager, HttpRequestFactory httpRequestFactory) {
        ScheduledExecutorService b = ExecutorUtils.m13049b("Crashlytics SAM");
        return new SessionAnalyticsManager(sessionEventMetadata, new SessionEventsHandler(context, new EnabledSessionAnalyticsManagerStrategy(context, b, sessionAnalyticsFilesManager, httpRequestFactory), sessionAnalyticsFilesManager, b));
    }

    SessionAnalyticsManager(SessionEventMetadata sessionEventMetadata, SessionEventsHandler sessionEventsHandler) {
        this.f5463a = sessionEventMetadata;
        this.f5464b = sessionEventsHandler;
    }

    public void m8117a(String str) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("onCrash called from main thread!!!");
        }
        this.f5464b.m8186a((Object) SessionEvent.m8179b(this.f5463a, str));
    }

    public void m8120b(String str) {
        this.f5464b.m8187a(SessionEvent.m8178a(this.f5463a, str), false);
    }

    public void m8118b() {
        this.f5464b.m8187a(SessionEvent.m8174a(this.f5463a), true);
    }

    public void m8115a(Activity activity) {
        this.f5464b.m8187a(SessionEvent.m8175a(this.f5463a, Type.CREATE, activity), false);
    }

    public void m8119b(Activity activity) {
        this.f5464b.m8187a(SessionEvent.m8175a(this.f5463a, Type.DESTROY, activity), false);
    }

    public void m8121c(Activity activity) {
        this.f5464b.m8187a(SessionEvent.m8175a(this.f5463a, Type.PAUSE, activity), false);
    }

    public void m8122d(Activity activity) {
        this.f5464b.m8187a(SessionEvent.m8175a(this.f5463a, Type.RESUME, activity), false);
    }

    public void m8123e(Activity activity) {
        this.f5464b.m8187a(SessionEvent.m8175a(this.f5463a, Type.SAVE_INSTANCE_STATE, activity), false);
    }

    public void m8124f(Activity activity) {
        this.f5464b.m8187a(SessionEvent.m8175a(this.f5463a, Type.START, activity), false);
    }

    public void m8125g(Activity activity) {
        this.f5464b.m8187a(SessionEvent.m8175a(this.f5463a, Type.STOP, activity), false);
    }

    public void m8116a(AnalyticsSettingsData analyticsSettingsData, String str) {
        this.f5464b.m8194a(analyticsSettingsData, str);
    }

    public void m8114a() {
        this.f5464b.m8190b();
    }
}
