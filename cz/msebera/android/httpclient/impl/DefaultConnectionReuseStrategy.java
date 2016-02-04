package cz.msebera.android.httpclient.impl;

import cz.msebera.android.httpclient.ConnectionReuseStrategy;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderIterator;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.ParseException;
import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.TokenIterator;
import cz.msebera.android.httpclient.message.BasicTokenIterator;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;

public class DefaultConnectionReuseStrategy implements ConnectionReuseStrategy {
    public static final DefaultConnectionReuseStrategy f7448a;

    static {
        f7448a = new DefaultConnectionReuseStrategy();
    }

    public boolean m11864a(HttpResponse httpResponse, HttpContext httpContext) {
        boolean z = true;
        Args.m12722a((Object) httpResponse, "HTTP response");
        Args.m12722a((Object) httpContext, "HTTP context");
        ProtocolVersion a = httpResponse.m11391a().m11409a();
        Header c = httpResponse.m10616c("Transfer-Encoding");
        if (c != null) {
            if (!"chunked".equalsIgnoreCase(c.m11362d())) {
                return false;
            }
        } else if (m11862a(httpResponse)) {
            Header[] b = httpResponse.m10615b("Content-Length");
            if (b.length != 1) {
                return false;
            }
            try {
                if (Integer.parseInt(b[0].m11362d()) < 0) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        HeaderIterator e2 = httpResponse.m10619e("Connection");
        if (!e2.hasNext()) {
            e2 = httpResponse.m10619e("Proxy-Connection");
        }
        if (e2.hasNext()) {
            try {
                TokenIterator a2 = m11863a(e2);
                boolean z2 = false;
                while (a2.hasNext()) {
                    String a3 = a2.m11412a();
                    if ("Close".equalsIgnoreCase(a3)) {
                        return false;
                    }
                    if ("Keep-Alive".equalsIgnoreCase(a3)) {
                        z2 = true;
                    }
                }
                if (z2) {
                    return true;
                }
            } catch (ParseException e3) {
                return false;
            }
        }
        if (a.m11401c(HttpVersion.f7209b)) {
            z = false;
        }
        return z;
    }

    protected TokenIterator m11863a(HeaderIterator headerIterator) {
        return new BasicTokenIterator(headerIterator);
    }

    private boolean m11862a(HttpResponse httpResponse) {
        int b = httpResponse.m11391a().m11410b();
        return (b < 200 || b == 204 || b == 304 || b == 205) ? false : true;
    }
}
