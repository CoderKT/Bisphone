package cz.msebera.android.httpclient.impl.entity;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpMessage;
import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.ProtocolException;
import cz.msebera.android.httpclient.entity.ContentLengthStrategy;
import cz.msebera.android.httpclient.util.Args;

public class StrictContentLengthStrategy implements ContentLengthStrategy {
    public static final StrictContentLengthStrategy f7792a;
    private final int f7793b;

    static {
        f7792a = new StrictContentLengthStrategy();
    }

    public StrictContentLengthStrategy(int i) {
        this.f7793b = i;
    }

    public StrictContentLengthStrategy() {
        this(-1);
    }

    public long m12517a(HttpMessage httpMessage) {
        Args.m12722a((Object) httpMessage, "HTTP message");
        Header c = httpMessage.m10616c("Transfer-Encoding");
        if (c != null) {
            String d = c.m11362d();
            if ("chunked".equalsIgnoreCase(d)) {
                if (!httpMessage.m10617d().m11401c(HttpVersion.f7209b)) {
                    return -2;
                }
                throw new ProtocolException("Chunked transfer encoding not allowed for " + httpMessage.m10617d());
            } else if ("identity".equalsIgnoreCase(d)) {
                return -1;
            } else {
                throw new ProtocolException("Unsupported transfer encoding: " + d);
            }
        }
        c = httpMessage.m10616c("Content-Length");
        if (c == null) {
            return (long) this.f7793b;
        }
        String d2 = c.m11362d();
        try {
            long parseLong = Long.parseLong(d2);
            if (parseLong >= 0) {
                return parseLong;
            }
            throw new ProtocolException("Negative content length: " + d2);
        } catch (NumberFormatException e) {
            throw new ProtocolException("Invalid content length: " + d2);
        }
    }
}
