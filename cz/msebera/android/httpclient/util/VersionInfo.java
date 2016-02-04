package cz.msebera.android.httpclient.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class VersionInfo {
    private final String f7924a;
    private final String f7925b;
    private final String f7926c;
    private final String f7927d;
    private final String f7928e;

    protected VersionInfo(String str, String str2, String str3, String str4, String str5) {
        Args.m12722a((Object) str, "Package identifier");
        this.f7924a = str;
        if (str2 == null) {
            str2 = "UNAVAILABLE";
        }
        this.f7925b = str2;
        if (str3 == null) {
            str3 = "UNAVAILABLE";
        }
        this.f7926c = str3;
        if (str4 == null) {
            str4 = "UNAVAILABLE";
        }
        this.f7927d = str4;
        if (str5 == null) {
            str5 = "UNAVAILABLE";
        }
        this.f7928e = str5;
    }

    public final String m12775a() {
        return this.f7926c;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(((((this.f7924a.length() + 20) + this.f7925b.length()) + this.f7926c.length()) + this.f7927d.length()) + this.f7928e.length());
        stringBuilder.append("VersionInfo(").append(this.f7924a).append(':').append(this.f7925b);
        if (!"UNAVAILABLE".equals(this.f7926c)) {
            stringBuilder.append(':').append(this.f7926c);
        }
        if (!"UNAVAILABLE".equals(this.f7927d)) {
            stringBuilder.append(':').append(this.f7927d);
        }
        stringBuilder.append(')');
        if (!"UNAVAILABLE".equals(this.f7928e)) {
            stringBuilder.append('@').append(this.f7928e);
        }
        return stringBuilder.toString();
    }

    public static VersionInfo m12773a(String str, ClassLoader classLoader) {
        Map properties;
        Args.m12722a((Object) str, "Package identifier");
        if (classLoader == null) {
            classLoader = Thread.currentThread().getContextClassLoader();
        }
        InputStream resourceAsStream;
        try {
            resourceAsStream = classLoader.getResourceAsStream(str.replace('.', '/') + "/" + "version.properties");
            if (resourceAsStream != null) {
                properties = new Properties();
                properties.load(resourceAsStream);
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                }
            } else {
                properties = null;
            }
        } catch (IOException e2) {
            properties = null;
        } catch (Throwable th) {
            resourceAsStream.close();
        }
        if (properties != null) {
            return m12774a(str, properties, classLoader);
        }
        return null;
    }

    protected static VersionInfo m12774a(String str, Map<?, ?> map, ClassLoader classLoader) {
        String str2;
        String str3;
        String str4;
        String str5 = null;
        Args.m12722a((Object) str, "Package identifier");
        if (map != null) {
            String str6;
            String str7 = (String) map.get("info.module");
            if (str7 == null || str7.length() >= 1) {
                str6 = str7;
            } else {
                str6 = null;
            }
            str7 = (String) map.get("info.release");
            if (str7 == null || (str7.length() >= 1 && !str7.equals("${pom.version}"))) {
                str2 = str7;
            } else {
                str2 = null;
            }
            str7 = (String) map.get("info.timestamp");
            if (str7 == null || (str7.length() >= 1 && !str7.equals("${mvn.timestamp}"))) {
                str3 = str7;
                str4 = str2;
                str2 = str6;
            } else {
                str3 = null;
                str4 = str2;
                str2 = str6;
            }
        } else {
            str3 = null;
            str4 = null;
            str2 = null;
        }
        if (classLoader != null) {
            str5 = classLoader.toString();
        }
        return new VersionInfo(str, str2, str4, str3, str5);
    }
}
