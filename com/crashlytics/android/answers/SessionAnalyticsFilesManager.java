package com.crashlytics.android.answers;

import android.content.Context;
import io.fabric.sdk.android.services.common.CurrentTimeProvider;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import io.fabric.sdk.android.services.events.EventsStorage;
import io.fabric.sdk.android.services.settings.AnalyticsSettingsData;
import java.util.UUID;

class SessionAnalyticsFilesManager extends EventsFilesManager<SessionEvent> {
    private AnalyticsSettingsData f5482g;

    SessionAnalyticsFilesManager(Context context, SessionEventTransform sessionEventTransform, CurrentTimeProvider currentTimeProvider, EventsStorage eventsStorage) {
        super(context, sessionEventTransform, currentTimeProvider, eventsStorage, 100);
    }

    protected String m8170a() {
        return "sa" + "_" + UUID.randomUUID().toString() + "_" + this.c.m13041a() + ".tap";
    }

    protected int m8172b() {
        return this.f5482g == null ? super.m8164b() : this.f5482g.f8378e;
    }

    protected int m8173c() {
        return this.f5482g == null ? super.m8165c() : this.f5482g.f8376c;
    }

    void m8171a(AnalyticsSettingsData analyticsSettingsData) {
        this.f5482g = analyticsSettingsData;
    }
}
