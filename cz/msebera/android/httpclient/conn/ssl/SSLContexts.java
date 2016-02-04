package cz.msebera.android.httpclient.conn.ssl;

import javax.net.ssl.SSLContext;
import org.jivesoftware.smack.util.TLSUtils;

public class SSLContexts {
    public static SSLContext m11760a() {
        try {
            SSLContext instance = SSLContext.getInstance(TLSUtils.TLS);
            instance.init(null, null, null);
            return instance;
        } catch (Throwable e) {
            throw new SSLInitializationException(e.getMessage(), e);
        } catch (Throwable e2) {
            throw new SSLInitializationException(e2.getMessage(), e2);
        }
    }

    public static SSLContextBuilder m11761b() {
        return new SSLContextBuilder();
    }
}
