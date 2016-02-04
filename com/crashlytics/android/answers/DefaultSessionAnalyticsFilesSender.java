package com.crashlytics.android.answers;

import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.ResponseParser;
import io.fabric.sdk.android.services.events.FilesSender;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.io.File;
import java.util.List;

class DefaultSessionAnalyticsFilesSender extends AbstractSpiCall implements FilesSender {
    private final String f5467b;

    public DefaultSessionAnalyticsFilesSender(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory, String str3) {
        this(kit, str, str2, httpRequestFactory, str3, HttpMethod.POST);
    }

    DefaultSessionAnalyticsFilesSender(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory, String str3, HttpMethod httpMethod) {
        super(kit, str, str2, httpRequestFactory, httpMethod);
        this.f5467b = str3;
    }

    public boolean m8131a(List<File> list) {
        HttpRequest a = m8130a(m8129a(m7992b(), this.f5467b), (List) list);
        CommonUtils.m13014a(Answers.m8108b().m7860C(), "Sending " + list.size() + " analytics files to " + m7991a());
        int b = a.m13210b();
        CommonUtils.m13014a(Answers.m8108b().m7860C(), "Response code for analytics file send is " + b);
        return ResponseParser.m13104a(b) == 0;
    }

    private HttpRequest m8129a(HttpRequest httpRequest, String str) {
        return httpRequest.m13200a("X-CRASHLYTICS-API-CLIENT-TYPE", "android").m13200a("X-CRASHLYTICS-API-CLIENT-VERSION", Answers.m8108b().m8112d()).m13200a("X-CRASHLYTICS-API-KEY", str);
    }

    private HttpRequest m8130a(HttpRequest httpRequest, List<File> list) {
        int i = 0;
        for (File file : list) {
            CommonUtils.m13014a(Answers.m8108b().m7860C(), "Adding analytics session file " + file.getName() + " to multipart POST");
            httpRequest.m13203a("session_analytics_file_" + i, file.getName(), "application/vnd.crashlytics.android.events", file);
            i++;
        }
        return httpRequest;
    }
}
