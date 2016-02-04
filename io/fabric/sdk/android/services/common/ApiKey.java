package io.fabric.sdk.android.services.common;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import io.fabric.sdk.android.Fabric;

public class ApiKey {
    public String m12991a(Context context) {
        Object b = m12992b(context);
        if (TextUtils.isEmpty(b)) {
            b = m12993c(context);
        }
        if (TextUtils.isEmpty(b)) {
            m12994d(context);
        }
        return b;
    }

    protected String m12992b(Context context) {
        String str = null;
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle != null) {
                str = bundle.getString("io.fabric.ApiKey");
                if (str == null) {
                    Fabric.m12905g().m12867a("Fabric", "Falling back to Crashlytics key lookup from Manifest");
                    str = bundle.getString("com.crashlytics.ApiKey");
                }
            }
        } catch (Exception e) {
            Fabric.m12905g().m12867a("Fabric", "Caught non-fatal exception while retrieving apiKey: " + e);
        }
        return str;
    }

    protected String m12993c(Context context) {
        int a = CommonUtils.m12998a(context, "io.fabric.ApiKey", "string");
        if (a == 0) {
            Fabric.m12905g().m12867a("Fabric", "Falling back to Crashlytics key lookup from Strings");
            a = CommonUtils.m12998a(context, "com.crashlytics.ApiKey", "string");
        }
        if (a != 0) {
            return context.getResources().getString(a);
        }
        return null;
    }

    protected void m12994d(Context context) {
        if (Fabric.m12906h() || CommonUtils.m13034i(context)) {
            throw new IllegalArgumentException(m12990a());
        }
        Fabric.m12905g().m12873d("Fabric", m12990a());
    }

    protected String m12990a() {
        return "Fabric could not be initialized, API key missing from AndroidManifest.xml. Add the following tag to your Application element \n\t<meta-data android:name=\"io.fabric.ApiKey\" android:value=\"YOUR_API_KEY\"/>";
    }
}
