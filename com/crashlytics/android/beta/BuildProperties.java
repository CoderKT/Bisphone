package com.crashlytics.android.beta;

import java.io.InputStream;
import java.util.Properties;

class BuildProperties {
    public final String f5522a;
    public final String f5523b;
    public final String f5524c;
    public final String f5525d;

    BuildProperties(String str, String str2, String str3, String str4) {
        this.f5522a = str;
        this.f5523b = str2;
        this.f5524c = str3;
        this.f5525d = str4;
    }

    public static BuildProperties m8209a(Properties properties) {
        return new BuildProperties(properties.getProperty("version_code"), properties.getProperty("version_name"), properties.getProperty("build_id"), properties.getProperty("package_name"));
    }

    public static BuildProperties m8208a(InputStream inputStream) {
        Properties properties = new Properties();
        properties.load(inputStream);
        return m8209a(properties);
    }
}
