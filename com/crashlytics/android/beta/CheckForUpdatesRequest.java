package com.crashlytics.android.beta;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

class CheckForUpdatesRequest extends AbstractSpiCall {
    private final CheckForUpdatesResponseTransform f5534b;

    public CheckForUpdatesRequest(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory, CheckForUpdatesResponseTransform checkForUpdatesResponseTransform) {
        super(kit, str, str2, httpRequestFactory, HttpMethod.GET);
        this.f5534b = checkForUpdatesResponseTransform;
    }

    public CheckForUpdatesResponse m8213a(String str, String str2, BuildProperties buildProperties) {
        Throwable e;
        Throwable th;
        CheckForUpdatesResponse checkForUpdatesResponse = null;
        HttpRequest a;
        try {
            Map a2 = m8212a(buildProperties);
            try {
                a = m8211a(m7990a(a2), str, str2);
                Fabric.m12905g().m12867a("Beta", "Checking for updates from " + m7991a());
                Fabric.m12905g().m12867a("Beta", "Checking for updates query params are: " + a2);
                if (a.m13216c()) {
                    Fabric.m12905g().m12867a("Beta", "Checking for updates was successful");
                    checkForUpdatesResponse = this.f5534b.m8214a(new JSONObject(a.m13221e()));
                    if (a != null) {
                        Fabric.m12905g().m12867a("Fabric", "Checking for updates request ID: " + a.m13212b("X-REQUEST-ID"));
                    }
                } else {
                    Fabric.m12905g().m12873d("Beta", "Checking for updates failed. Response code: " + a.m13210b());
                    if (a != null) {
                        Fabric.m12905g().m12867a("Fabric", "Checking for updates request ID: " + a.m13212b("X-REQUEST-ID"));
                    }
                }
            } catch (Exception e2) {
                e = e2;
                try {
                    Fabric.m12905g().m12874d("Beta", "Error while checking for updates from " + m7991a(), e);
                    if (a != null) {
                        Fabric.m12905g().m12867a("Fabric", "Checking for updates request ID: " + a.m13212b("X-REQUEST-ID"));
                    }
                    return checkForUpdatesResponse;
                } catch (Throwable th2) {
                    th = th2;
                    if (a != null) {
                        Fabric.m12905g().m12867a("Fabric", "Checking for updates request ID: " + a.m13212b("X-REQUEST-ID"));
                    }
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            a = null;
            Fabric.m12905g().m12874d("Beta", "Error while checking for updates from " + m7991a(), e);
            if (a != null) {
                Fabric.m12905g().m12867a("Fabric", "Checking for updates request ID: " + a.m13212b("X-REQUEST-ID"));
            }
            return checkForUpdatesResponse;
        } catch (Throwable e4) {
            a = null;
            th = e4;
            if (a != null) {
                Fabric.m12905g().m12867a("Fabric", "Checking for updates request ID: " + a.m13212b("X-REQUEST-ID"));
            }
            throw th;
        }
        return checkForUpdatesResponse;
    }

    private HttpRequest m8211a(HttpRequest httpRequest, String str, String str2) {
        return httpRequest.m13200a("Accept", "application/json").m13200a("User-Agent", "Crashlytics Android SDK/" + this.a.m7872d()).m13200a("X-CRASHLYTICS-DEVELOPER-TOKEN", "bca6990fc3c15a8105800c0673517a4b579634a1").m13200a("X-CRASHLYTICS-API-CLIENT-TYPE", "android").m13200a("X-CRASHLYTICS-API-CLIENT-VERSION", this.a.m7872d()).m13200a("X-CRASHLYTICS-API-KEY", str).m13200a("X-CRASHLYTICS-D", str2);
    }

    private Map<String, String> m8212a(BuildProperties buildProperties) {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("build_version", buildProperties.f5522a);
        hashMap.put("display_version", buildProperties.f5523b);
        hashMap.put("instance", buildProperties.f5524c);
        hashMap.put("source", "3");
        return hashMap;
    }
}
