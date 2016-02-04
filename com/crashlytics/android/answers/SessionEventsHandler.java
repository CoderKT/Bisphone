package com.crashlytics.android.answers;

import android.content.Context;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.events.EventsHandler;
import io.fabric.sdk.android.services.events.EventsStrategy;
import io.fabric.sdk.android.services.settings.AnalyticsSettingsData;
import java.util.concurrent.ScheduledExecutorService;

class SessionEventsHandler extends EventsHandler<SessionEvent> {

    /* renamed from: com.crashlytics.android.answers.SessionEventsHandler.1 */
    class C06141 implements Runnable {
        final /* synthetic */ AnalyticsSettingsData f5514a;
        final /* synthetic */ String f5515b;
        final /* synthetic */ SessionEventsHandler f5516c;

        C06141(SessionEventsHandler sessionEventsHandler, AnalyticsSettingsData analyticsSettingsData, String str) {
            this.f5516c = sessionEventsHandler;
            this.f5514a = analyticsSettingsData;
            this.f5515b = str;
        }

        public void run() {
            try {
                ((SessionAnalyticsManagerStrategy) this.f5516c.c).m8144a(this.f5514a, this.f5515b);
            } catch (Throwable e) {
                CommonUtils.m13015a(Answers.m8108b().m7860C(), "Crashlytics failed to set analytics settings data.", e);
            }
        }
    }

    SessionEventsHandler(Context context, EventsStrategy<SessionEvent> eventsStrategy, SessionAnalyticsFilesManager sessionAnalyticsFilesManager, ScheduledExecutorService scheduledExecutorService) {
        super(context, eventsStrategy, sessionAnalyticsFilesManager, scheduledExecutorService);
    }

    protected EventsStrategy<SessionEvent> m8193a() {
        return new DisabledSessionAnalyticsManagerStrategy();
    }

    protected void m8194a(AnalyticsSettingsData analyticsSettingsData, String str) {
        super.m8191b(new C06141(this, analyticsSettingsData, str));
    }
}
