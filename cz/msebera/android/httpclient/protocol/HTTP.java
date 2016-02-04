package cz.msebera.android.httpclient.protocol;

import cz.msebera.android.httpclient.Consts;
import java.nio.charset.Charset;

public final class HTTP {
    public static final Charset f7912a;
    public static final Charset f7913b;

    static {
        f7912a = Consts.f7199c;
        f7913b = Consts.f7198b;
    }

    public static boolean m12703a(char c) {
        return c == ' ' || c == '\t' || c == '\r' || c == '\n';
    }
}
