package com.crashlytics.android.beta;

import android.content.Context;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.cache.ValueLoader;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class DeviceTokenLoader implements ValueLoader<String> {
    public /* synthetic */ Object m8219c(Context context) {
        return m8216a(context);
    }

    public String m8216a(Context context) {
        long nanoTime = System.nanoTime();
        String str = "";
        ZipInputStream zipInputStream = null;
        try {
            zipInputStream = m8218b(context);
            str = m8217a(zipInputStream);
            if (zipInputStream != null) {
                try {
                    zipInputStream.close();
                } catch (Throwable e) {
                    Fabric.m12905g().m12874d("Beta", "Failed to close the APK file", e);
                }
            }
        } catch (Throwable e2) {
            Fabric.m12905g().m12874d("Beta", "Failed to find this app in the PackageManager", e2);
            if (zipInputStream != null) {
                try {
                    zipInputStream.close();
                } catch (Throwable e22) {
                    Fabric.m12905g().m12874d("Beta", "Failed to close the APK file", e22);
                }
            }
        } catch (Throwable e222) {
            Fabric.m12905g().m12874d("Beta", "Failed to find the APK file", e222);
            if (zipInputStream != null) {
                try {
                    zipInputStream.close();
                } catch (Throwable e2222) {
                    Fabric.m12905g().m12874d("Beta", "Failed to close the APK file", e2222);
                }
            }
        } catch (Throwable e22222) {
            Fabric.m12905g().m12874d("Beta", "Failed to read the APK file", e22222);
            if (zipInputStream != null) {
                try {
                    zipInputStream.close();
                } catch (Throwable e222222) {
                    Fabric.m12905g().m12874d("Beta", "Failed to close the APK file", e222222);
                }
            }
        } catch (Throwable th) {
            if (zipInputStream != null) {
                try {
                    zipInputStream.close();
                } catch (Throwable e2222222) {
                    Fabric.m12905g().m12874d("Beta", "Failed to close the APK file", e2222222);
                }
            }
        }
        Fabric.m12905g().m12867a("Beta", "Beta device token load took " + (((double) (System.nanoTime() - nanoTime)) / 1000000.0d) + "ms");
        return str;
    }

    ZipInputStream m8218b(Context context) {
        return new ZipInputStream(new FileInputStream(context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir));
    }

    String m8217a(ZipInputStream zipInputStream) {
        String name;
        do {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry == null) {
                return "";
            }
            name = nextEntry.getName();
        } while (!name.startsWith("assets/com.crashlytics.android.beta/dirfactor-device-token="));
        return name.substring("assets/com.crashlytics.android.beta/dirfactor-device-token=".length(), name.length() - 1);
    }
}
