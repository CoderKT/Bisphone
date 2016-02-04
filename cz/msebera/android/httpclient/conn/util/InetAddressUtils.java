package cz.msebera.android.httpclient.conn.util;

import java.util.regex.Pattern;

public class InetAddressUtils {
    private static final Pattern f7385a;
    private static final Pattern f7386b;
    private static final Pattern f7387c;
    private static final Pattern f7388d;

    static {
        f7385a = Pattern.compile("^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$");
        f7386b = Pattern.compile("^::[fF]{4}:(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$");
        f7387c = Pattern.compile("^[0-9a-fA-F]{1,4}(:[0-9a-fA-F]{1,4}){7}$");
        f7388d = Pattern.compile("^(([0-9A-Fa-f]{1,4}(:[0-9A-Fa-f]{1,4}){0,5})?)::(([0-9A-Fa-f]{1,4}(:[0-9A-Fa-f]{1,4}){0,5})?)$");
    }

    public static boolean m11764a(String str) {
        return f7385a.matcher(str).matches();
    }

    public static boolean m11765b(String str) {
        return f7387c.matcher(str).matches();
    }

    public static boolean m11766c(String str) {
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (str.charAt(i2) == ':') {
                i++;
            }
        }
        if (i > 7 || !f7388d.matcher(str).matches()) {
            return false;
        }
        return true;
    }

    public static boolean m11767d(String str) {
        return m11765b(str) || m11766c(str);
    }
}
