package io.fabric.sdk.android.services.settings;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

class DefaultSettingsSpiCall extends AbstractSpiCall implements SettingsSpiCall {
    public DefaultSettingsSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory) {
        this(kit, str, str2, httpRequestFactory, HttpMethod.GET);
    }

    DefaultSettingsSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory, HttpMethod httpMethod) {
        super(kit, str, str2, httpRequestFactory, httpMethod);
    }

    public JSONObject m13289a(SettingsRequest settingsRequest) {
        HttpRequest httpRequest = null;
        try {
            Map b = m13287b(settingsRequest);
            httpRequest = m13285a(m7990a(b), settingsRequest);
            Fabric.m12905g().m12867a("Fabric", "Requesting settings from " + m7991a());
            Fabric.m12905g().m12867a("Fabric", "Settings query params were: " + b);
            JSONObject a = m13288a(httpRequest);
            return a;
        } finally {
            if (httpRequest != null) {
                Fabric.m12905g().m12867a("Fabric", "Settings request ID: " + httpRequest.m13212b("X-REQUEST-ID"));
            }
        }
    }

    JSONObject m13288a(HttpRequest httpRequest) {
        int b = httpRequest.m13210b();
        Fabric.m12905g().m12867a("Fabric", "Settings result was: " + b);
        if (m13290a(b)) {
            return m13286a(httpRequest.m13221e());
        }
        Fabric.m12905g().m12873d("Fabric", "Failed to retrieve settings from " + m7991a());
        return null;
    }

    boolean m13290a(int i) {
        return i == 200 || i == 201 || i == 202 || i == 203;
    }

    private JSONObject m13286a(String str) {
        try {
            return new JSONObject(str);
        } catch (Throwable e) {
            Fabric.m12905g().m12868a("Fabric", "Failed to parse settings JSON from " + m7991a(), e);
            Fabric.m12905g().m12867a("Fabric", "Settings response " + str);
            return null;
        }
    }

    private Map<String, String> m13287b(SettingsRequest settingsRequest) {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("build_version", settingsRequest.f8453e);
        hashMap.put("display_version", settingsRequest.f8452d);
        hashMap.put("source", Integer.toString(settingsRequest.f8454f));
        if (settingsRequest.f8455g != null) {
            hashMap.put("icon_hash", settingsRequest.f8455g);
        }
        String str = settingsRequest.f8451c;
        if (!CommonUtils.m13028c(str)) {
            hashMap.put("instance", str);
        }
        return hashMap;
    }

    private HttpRequest m13285a(HttpRequest httpRequest, SettingsRequest settingsRequest) {
        return httpRequest.m13200a("X-CRASHLYTICS-API-KEY", settingsRequest.f8449a).m13200a("X-CRASHLYTICS-API-CLIENT-TYPE", "android").m13200a("X-CRASHLYTICS-D", settingsRequest.f8450b).m13200a("X-CRASHLYTICS-API-CLIENT-VERSION", this.a.m7872d()).m13200a("Accept", "application/json");
    }
}
