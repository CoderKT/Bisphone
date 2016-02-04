package com.crashlytics.android.answers;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.ExecutorUtils;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.util.concurrent.ScheduledExecutorService;

@TargetApi(14)
class AutoSessionAnalyticsManager extends SessionAnalyticsManager {
    private final Application f5465c;
    private final ActivityLifecycleCallbacks f5466d;

    /* renamed from: com.crashlytics.android.answers.AutoSessionAnalyticsManager.1 */
    class C06131 implements ActivityLifecycleCallbacks {
        final /* synthetic */ AutoSessionAnalyticsManager f5462a;

        C06131(AutoSessionAnalyticsManager autoSessionAnalyticsManager) {
            this.f5462a = autoSessionAnalyticsManager;
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
            this.f5462a.m8115a(activity);
        }

        public void onActivityDestroyed(Activity activity) {
            this.f5462a.m8119b(activity);
        }

        public void onActivityPaused(Activity activity) {
            this.f5462a.m8121c(activity);
        }

        public void onActivityResumed(Activity activity) {
            this.f5462a.m8122d(activity);
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            this.f5462a.m8123e(activity);
        }

        public void onActivityStarted(Activity activity) {
            this.f5462a.m8124f(activity);
        }

        public void onActivityStopped(Activity activity) {
            this.f5462a.m8125g(activity);
        }
    }

    public static AutoSessionAnalyticsManager m8126a(Application application, SessionEventMetadata sessionEventMetadata, SessionAnalyticsFilesManager sessionAnalyticsFilesManager, HttpRequestFactory httpRequestFactory) {
        ScheduledExecutorService b = ExecutorUtils.m13049b("Crashlytics Trace Manager");
        return new AutoSessionAnalyticsManager(sessionEventMetadata, new SessionEventsHandler(application, new EnabledSessionAnalyticsManagerStrategy(application, b, sessionAnalyticsFilesManager, httpRequestFactory), sessionAnalyticsFilesManager, b), application);
    }

    AutoSessionAnalyticsManager(SessionEventMetadata sessionEventMetadata, SessionEventsHandler sessionEventsHandler, Application application) {
        super(sessionEventMetadata, sessionEventsHandler);
        this.f5466d = new C06131(this);
        this.f5465c = application;
        CommonUtils.m13014a(Answers.m8108b().m7860C(), "Registering activity lifecycle callbacks for session analytics.");
        application.registerActivityLifecycleCallbacks(this.f5466d);
    }

    public void m8127a() {
        CommonUtils.m13014a(Answers.m8108b().m7860C(), "Unregistering activity lifecycle callbacks for session analytics");
        this.f5465c.unregisterActivityLifecycleCallbacks(this.f5466d);
        super.m8114a();
    }
}
