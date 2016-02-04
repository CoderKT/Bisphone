package io.fabric.sdk.android.services.settings;

import io.fabric.sdk.android.services.common.CurrentTimeProvider;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Session;
import org.jivesoftware.smackx.muc.packet.MUCUser.Status;
import org.json.JSONObject;

class DefaultSettingsJsonTransform implements SettingsJsonTransform {
    DefaultSettingsJsonTransform() {
    }

    public SettingsData m13282a(CurrentTimeProvider currentTimeProvider, JSONObject jSONObject) {
        int optInt = jSONObject.optInt("settings_version", 0);
        int optInt2 = jSONObject.optInt("cache_duration", 3600);
        return new SettingsData(m13274a(currentTimeProvider, (long) optInt2, jSONObject), m13275b(jSONObject.getJSONObject("app")), m13279f(jSONObject.getJSONObject(Session.ELEMENT)), m13280g(jSONObject.getJSONObject("prompt")), m13277d(jSONObject.getJSONObject("features")), m13278e(jSONObject.getJSONObject("analytics")), m13281h(jSONObject.getJSONObject("beta")), optInt, optInt2);
    }

    public JSONObject m13283a(JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject(jSONObject.toString());
        jSONObject2.getJSONObject("features").remove("collect_analytics");
        jSONObject2.remove("analytics");
        return jSONObject2;
    }

    private AppSettingsData m13275b(JSONObject jSONObject) {
        String string = jSONObject.getString("identifier");
        String string2 = jSONObject.getString(Status.ELEMENT);
        String string3 = jSONObject.getString("url");
        String string4 = jSONObject.getString("reports_url");
        boolean optBoolean = jSONObject.optBoolean("update_required", false);
        AppIconSettingsData appIconSettingsData = null;
        if (jSONObject.has("icon") && jSONObject.getJSONObject("icon").has("hash")) {
            appIconSettingsData = m13276c(jSONObject.getJSONObject("icon"));
        }
        return new AppSettingsData(string, string2, string3, string4, optBoolean, appIconSettingsData);
    }

    private AppIconSettingsData m13276c(JSONObject jSONObject) {
        return new AppIconSettingsData(jSONObject.getString("hash"), jSONObject.getInt("width"), jSONObject.getInt("height"));
    }

    private FeaturesSettingsData m13277d(JSONObject jSONObject) {
        return new FeaturesSettingsData(jSONObject.optBoolean("prompt_enabled", false), jSONObject.optBoolean("collect_logged_exceptions", true), jSONObject.optBoolean("collect_reports", true), jSONObject.optBoolean("collect_analytics", false));
    }

    private AnalyticsSettingsData m13278e(JSONObject jSONObject) {
        return new AnalyticsSettingsData(jSONObject.optString("url", "https://e.crashlytics.com/spi/v2/events"), jSONObject.optInt("flush_interval_secs", 600), jSONObject.optInt("max_byte_size_per_file", 8000), jSONObject.optInt("max_file_count_per_send", 1), jSONObject.optInt("max_pending_send_file_count", 100));
    }

    private SessionSettingsData m13279f(JSONObject jSONObject) {
        return new SessionSettingsData(jSONObject.optInt("log_buffer_size", 64000), jSONObject.optInt("max_chained_exception_depth", 8), jSONObject.optInt("max_custom_exception_events", 64), jSONObject.optInt("max_custom_key_value_pairs", 64), jSONObject.optInt("identifier_mask", 255), jSONObject.optBoolean("send_session_without_crash", false));
    }

    private PromptSettingsData m13280g(JSONObject jSONObject) {
        return new PromptSettingsData(jSONObject.optString("title", "Send Crash Report?"), jSONObject.optString(Message.ELEMENT, "Looks like we crashed! Please help us fix the problem by sending a crash report."), jSONObject.optString("send_button_title", "Send"), jSONObject.optBoolean("show_cancel_button", true), jSONObject.optString("cancel_button_title", "Don't Send"), jSONObject.optBoolean("show_always_send_button", true), jSONObject.optString("always_send_button_title", "Always Send"));
    }

    private BetaSettingsData m13281h(JSONObject jSONObject) {
        return new BetaSettingsData(jSONObject.optString("update_endpoint", SettingsJsonConstants.f8448a), jSONObject.optInt("update_suspend_duration", 3600));
    }

    private long m13274a(CurrentTimeProvider currentTimeProvider, long j, JSONObject jSONObject) {
        if (jSONObject.has("expires_at")) {
            return jSONObject.getLong("expires_at");
        }
        return currentTimeProvider.m13041a() + (1000 * j);
    }
}
