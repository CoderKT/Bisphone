package com.crashlytics.android.beta;

import org.json.JSONObject;

class CheckForUpdatesResponseTransform {
    CheckForUpdatesResponseTransform() {
    }

    public CheckForUpdatesResponse m8214a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        return new CheckForUpdatesResponse(jSONObject.optString("url", null), jSONObject.optString("version_string", null), jSONObject.optString("display_version", null), jSONObject.optString("build_version", null), jSONObject.optString("identifier", null), jSONObject.optString("instance_identifier", null));
    }
}
