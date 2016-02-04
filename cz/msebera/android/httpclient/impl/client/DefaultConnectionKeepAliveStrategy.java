package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.conn.ConnectionKeepAliveStrategy;
import cz.msebera.android.httpclient.message.BasicHeaderElementIterator;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;

public class DefaultConnectionKeepAliveStrategy implements ConnectionKeepAliveStrategy {
    public static final DefaultConnectionKeepAliveStrategy f7577a;

    static {
        f7577a = new DefaultConnectionKeepAliveStrategy();
    }

    public long m12099a(HttpResponse httpResponse, HttpContext httpContext) {
        Args.m12722a((Object) httpResponse, "HTTP response");
        BasicHeaderElementIterator basicHeaderElementIterator = new BasicHeaderElementIterator(httpResponse.m10619e("Keep-Alive"));
        while (basicHeaderElementIterator.hasNext()) {
            HeaderElement a = basicHeaderElementIterator.m11372a();
            String a2 = a.m11368a();
            String b = a.m11369b();
            if (b != null && a2.equalsIgnoreCase("timeout")) {
                try {
                    return Long.parseLong(b) * 1000;
                } catch (NumberFormatException e) {
                }
            }
        }
        return -1;
    }
}
