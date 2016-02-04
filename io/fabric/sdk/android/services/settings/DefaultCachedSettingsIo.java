package io.fabric.sdk.android.services.settings;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.persistence.FileStoreImpl;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import org.json.JSONObject;

class DefaultCachedSettingsIo implements CachedSettingsIo {
    private final Kit f8401a;

    public DefaultCachedSettingsIo(Kit kit) {
        this.f8401a = kit;
    }

    public JSONObject m13260a() {
        Closeable fileInputStream;
        Throwable e;
        Closeable closeable = null;
        Fabric.m12905g().m12867a("Fabric", "Reading cached settings...");
        try {
            JSONObject jSONObject;
            File file = new File(new FileStoreImpl(this.f8401a).m13245a(), "com.crashlytics.settings.json");
            if (file.exists()) {
                fileInputStream = new FileInputStream(file);
                try {
                    jSONObject = new JSONObject(CommonUtils.m13005a((InputStream) fileInputStream));
                    closeable = fileInputStream;
                } catch (Exception e2) {
                    e = e2;
                    try {
                        Fabric.m12905g().m12874d("Fabric", "Failed to fetch cached settings", e);
                        CommonUtils.m13016a(fileInputStream, "Error while closing settings cache file.");
                        return null;
                    } catch (Throwable th) {
                        e = th;
                        closeable = fileInputStream;
                        CommonUtils.m13016a(closeable, "Error while closing settings cache file.");
                        throw e;
                    }
                }
            }
            Fabric.m12905g().m12867a("Fabric", "No cached settings found.");
            jSONObject = null;
            CommonUtils.m13016a(closeable, "Error while closing settings cache file.");
            return jSONObject;
        } catch (Exception e3) {
            e = e3;
            fileInputStream = null;
            Fabric.m12905g().m12874d("Fabric", "Failed to fetch cached settings", e);
            CommonUtils.m13016a(fileInputStream, "Error while closing settings cache file.");
            return null;
        } catch (Throwable th2) {
            e = th2;
            CommonUtils.m13016a(closeable, "Error while closing settings cache file.");
            throw e;
        }
    }

    public void m13261a(long j, JSONObject jSONObject) {
        Closeable fileWriter;
        Throwable e;
        Fabric.m12905g().m12867a("Fabric", "Writing settings to cache file...");
        if (jSONObject != null) {
            Closeable closeable = null;
            try {
                jSONObject.put("expires_at", j);
                fileWriter = new FileWriter(new File(new FileStoreImpl(this.f8401a).m13245a(), "com.crashlytics.settings.json"));
                try {
                    fileWriter.write(jSONObject.toString());
                    fileWriter.flush();
                    CommonUtils.m13016a(fileWriter, "Failed to close settings writer.");
                } catch (Exception e2) {
                    e = e2;
                    try {
                        Fabric.m12905g().m12874d("Fabric", "Failed to cache settings", e);
                        CommonUtils.m13016a(fileWriter, "Failed to close settings writer.");
                    } catch (Throwable th) {
                        e = th;
                        closeable = fileWriter;
                        CommonUtils.m13016a(closeable, "Failed to close settings writer.");
                        throw e;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                fileWriter = null;
                Fabric.m12905g().m12874d("Fabric", "Failed to cache settings", e);
                CommonUtils.m13016a(fileWriter, "Failed to close settings writer.");
            } catch (Throwable th2) {
                e = th2;
                CommonUtils.m13016a(closeable, "Failed to close settings writer.");
                throw e;
            }
        }
    }
}
