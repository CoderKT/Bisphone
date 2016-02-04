package com.crashlytics.android.answers;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import io.fabric.sdk.android.services.events.EventTransform;
import java.io.IOException;
import org.jivesoftware.smack.util.StringUtils;
import org.json.JSONObject;

class SessionEventTransform implements EventTransform<SessionEvent> {
    SessionEventTransform() {
    }

    public byte[] m8181a(SessionEvent sessionEvent) {
        return m8183b(sessionEvent).toString().getBytes(StringUtils.UTF8);
    }

    @TargetApi(9)
    public JSONObject m8183b(SessionEvent sessionEvent) {
        try {
            JSONObject jSONObject = new JSONObject();
            SessionEventMetadata sessionEventMetadata = sessionEvent.f5495a;
            jSONObject.put("appBundleId", sessionEventMetadata.f5502a);
            jSONObject.put("executionId", sessionEventMetadata.f5503b);
            jSONObject.put("installationId", sessionEventMetadata.f5504c);
            jSONObject.put("androidId", sessionEventMetadata.f5505d);
            jSONObject.put("advertisingId", sessionEventMetadata.f5506e);
            jSONObject.put("betaDeviceToken", sessionEventMetadata.f5507f);
            jSONObject.put("buildId", sessionEventMetadata.f5508g);
            jSONObject.put("osVersion", sessionEventMetadata.f5509h);
            jSONObject.put("deviceModel", sessionEventMetadata.f5510i);
            jSONObject.put("appVersionCode", sessionEventMetadata.f5511j);
            jSONObject.put("appVersionName", sessionEventMetadata.f5512k);
            jSONObject.put("timestamp", sessionEvent.f5496b);
            jSONObject.put("type", sessionEvent.f5497c.toString());
            jSONObject.put("details", new JSONObject(sessionEvent.f5498d));
            jSONObject.put("customType", sessionEvent.f5499e);
            jSONObject.put("customAttributes", new JSONObject(sessionEvent.f5500f));
            return jSONObject;
        } catch (Throwable e) {
            if (VERSION.SDK_INT >= 9) {
                throw new IOException(e.getMessage(), e);
            }
            throw new IOException(e.getMessage());
        }
    }
}
