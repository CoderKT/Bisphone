package io.fabric.sdk.android.services.settings;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.ResponseParser;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.io.Closeable;
import java.io.InputStream;
import org.jivesoftware.smackx.xdata.packet.DataForm;

abstract class AbstractAppSpiCall extends AbstractSpiCall {
    public AbstractAppSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory, HttpMethod httpMethod) {
        super(kit, str, str2, httpRequestFactory, httpMethod);
    }

    public boolean m13256a(AppRequestData appRequestData) {
        HttpRequest b = m13254b(m13253a(m7992b(), appRequestData), appRequestData);
        Fabric.m12905g().m12867a("Fabric", "Sending app info to " + m7991a());
        if (appRequestData.f8391j != null) {
            Fabric.m12905g().m12867a("Fabric", "App icon hash is " + appRequestData.f8391j.f8413a);
            Fabric.m12905g().m12867a("Fabric", "App icon size is " + appRequestData.f8391j.f8415c + DataForm.ELEMENT + appRequestData.f8391j.f8416d);
        }
        int b2 = b.m13210b();
        Fabric.m12905g().m12867a("Fabric", ("POST".equals(b.m13234p()) ? "Create" : "Update") + " app request ID: " + b.m13212b("X-REQUEST-ID"));
        Fabric.m12905g().m12867a("Fabric", "Result was " + b2);
        if (ResponseParser.m13104a(b2) == 0) {
            return true;
        }
        return false;
    }

    private HttpRequest m13253a(HttpRequest httpRequest, AppRequestData appRequestData) {
        return httpRequest.m13200a("X-CRASHLYTICS-API-KEY", appRequestData.f8382a).m13200a("X-CRASHLYTICS-API-CLIENT-TYPE", "android").m13200a("X-CRASHLYTICS-API-CLIENT-VERSION", this.a.m7872d());
    }

    private HttpRequest m13254b(HttpRequest httpRequest, AppRequestData appRequestData) {
        HttpRequest e = httpRequest.m13220e("app[identifier]", appRequestData.f8383b).m13220e("app[name]", appRequestData.f8387f).m13220e("app[display_version]", appRequestData.f8384c).m13220e("app[build_version]", appRequestData.f8385d).m13199a("app[source]", Integer.valueOf(appRequestData.f8388g)).m13220e("app[minimum_sdk_version]", appRequestData.f8389h).m13220e("app[built_sdk_version]", appRequestData.f8390i);
        if (!CommonUtils.m13028c(appRequestData.f8386e)) {
            e.m13220e("app[instance_identifier]", appRequestData.f8386e);
        }
        if (appRequestData.f8391j != null) {
            Closeable closeable = null;
            try {
                closeable = this.a.m7860C().getResources().openRawResource(appRequestData.f8391j.f8414b);
                e.m13220e("app[icon][hash]", appRequestData.f8391j.f8413a).m13204a("app[icon][data]", "icon.png", "application/octet-stream", (InputStream) closeable).m13199a("app[icon][width]", Integer.valueOf(appRequestData.f8391j.f8415c)).m13199a("app[icon][height]", Integer.valueOf(appRequestData.f8391j.f8416d));
            } catch (Throwable e2) {
                Fabric.m12905g().m12874d("Fabric", "Failed to find app icon with resource ID: " + appRequestData.f8391j.f8414b, e2);
            } finally {
                String str = "Failed to close app icon InputStream.";
                CommonUtils.m13016a(closeable, str);
            }
        }
        if (appRequestData.f8392k != null) {
            for (Kit kit : appRequestData.f8392k) {
                e.m13220e(m13255a(kit), kit.m7872d() == null ? "" : kit.m7872d());
            }
        }
        return e;
    }

    String m13255a(Kit kit) {
        return "app[build][libraries][" + (kit.m7871c() == null ? "" : kit.m7871c()) + "]";
    }
}
