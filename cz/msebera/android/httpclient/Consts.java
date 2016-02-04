package cz.msebera.android.httpclient;

import java.nio.charset.Charset;
import org.jivesoftware.smack.util.StringUtils;

public final class Consts {
    public static final Charset f7197a;
    public static final Charset f7198b;
    public static final Charset f7199c;

    static {
        f7197a = Charset.forName(StringUtils.UTF8);
        f7198b = Charset.forName(StringUtils.USASCII);
        f7199c = Charset.forName("ISO-8859-1");
    }
}
