package com.crashlytics.android.answers;

import android.content.Context;
import io.fabric.sdk.android.services.common.ApiKey;
import io.fabric.sdk.android.services.events.EnabledEventsStrategy;
import io.fabric.sdk.android.services.events.FilesSender;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import io.fabric.sdk.android.services.settings.AnalyticsSettingsData;
import java.util.concurrent.ScheduledExecutorService;

class EnabledSessionAnalyticsManagerStrategy extends EnabledEventsStrategy<SessionEvent> implements SessionAnalyticsManagerStrategy<SessionEvent> {
    FilesSender f5473a;
    private final HttpRequestFactory f5474g;

    public EnabledSessionAnalyticsManagerStrategy(Context context, ScheduledExecutorService scheduledExecutorService, SessionAnalyticsFilesManager sessionAnalyticsFilesManager, HttpRequestFactory httpRequestFactory) {
        super(context, scheduledExecutorService, sessionAnalyticsFilesManager);
        this.f5474g = httpRequestFactory;
    }

    public FilesSender m8155a() {
        return this.f5473a;
    }

    public void m8156a(AnalyticsSettingsData analyticsSettingsData, String str) {
        this.f5473a = new DefaultSessionAnalyticsFilesSender(Answers.m8108b(), str, analyticsSettingsData.f8374a, this.f5474g, new ApiKey().m12991a(this.b));
        ((SessionAnalyticsFilesManager) this.d).m8171a(analyticsSettingsData);
        m8146a(analyticsSettingsData.f8375b);
    }
}
