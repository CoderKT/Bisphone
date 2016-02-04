package io.fabric.sdk.android.services.settings;

import io.fabric.sdk.android.services.common.CurrentTimeProvider;
import org.json.JSONObject;

public interface SettingsJsonTransform {
    SettingsData m13272a(CurrentTimeProvider currentTimeProvider, JSONObject jSONObject);

    JSONObject m13273a(JSONObject jSONObject);
}
