package com.crashlytics.android;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.common.ResponseParser;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.util.Map.Entry;

class DefaultCreateReportSpiCall extends AbstractSpiCall implements CreateReportSpiCall {
    public DefaultCreateReportSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory) {
        super(kit, str, str2, httpRequestFactory, HttpMethod.POST);
    }

    public boolean m7995a(CreateReportRequest createReportRequest) {
        HttpRequest b = m7994b(m7993a(m7992b(), createReportRequest), createReportRequest);
        Fabric.m12905g().m12867a("Fabric", "Sending report to: " + m7991a());
        int b2 = b.m13210b();
        Fabric.m12905g().m12867a("Fabric", "Create report request ID: " + b.m13212b("X-REQUEST-ID"));
        Fabric.m12905g().m12867a("Fabric", "Result was: " + b2);
        return ResponseParser.m13104a(b2) == 0;
    }

    private HttpRequest m7993a(HttpRequest httpRequest, CreateReportRequest createReportRequest) {
        HttpRequest a = httpRequest.m13200a("X-CRASHLYTICS-API-KEY", createReportRequest.f5391a).m13200a("X-CRASHLYTICS-API-CLIENT-TYPE", "android").m13200a("X-CRASHLYTICS-API-CLIENT-VERSION", Crashlytics.m7891f().m7899d());
        HttpRequest httpRequest2 = a;
        for (Entry a2 : createReportRequest.f5392b.m8048e().entrySet()) {
            httpRequest2 = httpRequest2.m13206a(a2);
        }
        return httpRequest2;
    }

    private HttpRequest m7994b(HttpRequest httpRequest, CreateReportRequest createReportRequest) {
        Report report = createReportRequest.f5392b;
        return httpRequest.m13203a("report[file]", report.m8045b(), "application/octet-stream", report.m8047d()).m13220e("report[identifier]", report.m8046c());
    }
}
