package org.jxmpp.util;

import org.jxmpp.util.cache.LruCache;

public class XmppStringUtils {
    private static final LruCache<String, String> f8625a;
    private static final LruCache<String, String> f8626b;

    public static String m13441a(String str) {
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf(64);
        if (indexOf <= 0) {
            return "";
        }
        int indexOf2 = str.indexOf(47);
        if (indexOf2 < 0 || indexOf2 >= indexOf) {
            return str.substring(0, indexOf);
        }
        return "";
    }

    public static String m13444b(String str) {
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf(64);
        if (indexOf + 1 > str.length()) {
            return "";
        }
        int indexOf2 = str.indexOf(47);
        if (indexOf2 <= 0) {
            return str.substring(indexOf + 1);
        }
        if (indexOf2 > indexOf) {
            return str.substring(indexOf + 1, indexOf2);
        }
        return str.substring(0, indexOf2);
    }

    public static String m13446c(String str) {
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf(47);
        if (indexOf + 1 > str.length() || indexOf < 0) {
            return "";
        }
        return str.substring(indexOf + 1);
    }

    public static String m13447d(String str) {
        int indexOf = str.indexOf(47);
        if (indexOf < 0) {
            return str;
        }
        if (indexOf == 0) {
            return "";
        }
        return str.substring(0, indexOf);
    }

    public static boolean m13448e(String str) {
        if (m13441a(str).length() <= 0 || m13444b(str).length() <= 0 || m13446c(str).length() <= 0) {
            return false;
        }
        return true;
    }

    static {
        f8625a = new LruCache(100);
        f8626b = new LruCache(100);
    }

    public static String m13440a(CharSequence charSequence, CharSequence charSequence2) {
        return m13442a(charSequence != null ? charSequence.toString() : null, charSequence2.toString());
    }

    public static String m13442a(String str, String str2) {
        return m13443a(str, str2, null);
    }

    public static String m13443a(String str, String str2, String str3) {
        int i = 0;
        if (str2 == null) {
            throw new IllegalArgumentException("domainpart must not be null");
        }
        int length = str != null ? str.length() : 0;
        int length2 = str2.length();
        if (str3 != null) {
            i = str3.length();
        }
        StringBuilder stringBuilder = new StringBuilder(((length2 + length) + i) + 2);
        if (length > 0) {
            stringBuilder.append(str).append('@');
        }
        stringBuilder.append(str2);
        if (i > 0) {
            stringBuilder.append('/').append(str3);
        }
        return stringBuilder.toString();
    }

    public static String m13445b(String str, String str2) {
        return str + '\t' + str2;
    }
}
