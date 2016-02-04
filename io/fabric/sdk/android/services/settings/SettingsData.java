package io.fabric.sdk.android.services.settings;

public class SettingsData {
    public final AppSettingsData f8439a;
    public final SessionSettingsData f8440b;
    public final PromptSettingsData f8441c;
    public final FeaturesSettingsData f8442d;
    public final AnalyticsSettingsData f8443e;
    public final BetaSettingsData f8444f;
    public final long f8445g;
    public final int f8446h;
    public final int f8447i;

    public SettingsData(long j, AppSettingsData appSettingsData, SessionSettingsData sessionSettingsData, PromptSettingsData promptSettingsData, FeaturesSettingsData featuresSettingsData, AnalyticsSettingsData analyticsSettingsData, BetaSettingsData betaSettingsData, int i, int i2) {
        this.f8445g = j;
        this.f8439a = appSettingsData;
        this.f8440b = sessionSettingsData;
        this.f8441c = promptSettingsData;
        this.f8442d = featuresSettingsData;
        this.f8446h = i;
        this.f8447i = i2;
        this.f8443e = analyticsSettingsData;
        this.f8444f = betaSettingsData;
    }

    public boolean m13300a(long j) {
        return this.f8445g < j;
    }
}
