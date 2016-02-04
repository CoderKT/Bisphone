package org.jivesoftware.smack.util;

public class SystemUtil {
    public static final String PROPERTY_JAVA_VENDOR = "java.vendor";

    public static boolean onAndroid() {
        return System.getProperty(PROPERTY_JAVA_VENDOR).contains("Android");
    }
}
