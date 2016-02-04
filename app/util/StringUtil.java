package app.util;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;

public class StringUtil {
    public static String m7064a(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase();
    }

    public static String m7062a() {
        return new BigInteger(130, new SecureRandom()).toString(32);
    }

    public static String m7063a(int i) {
        String a = m7062a();
        if (a.length() > i) {
            return a.substring(0, i);
        }
        return a;
    }

    public static boolean m7066b(String str) {
        return str == null || str.isEmpty() || str.trim().isEmpty();
    }

    public static String m7065b() {
        return UUID.randomUUID().toString();
    }
}
