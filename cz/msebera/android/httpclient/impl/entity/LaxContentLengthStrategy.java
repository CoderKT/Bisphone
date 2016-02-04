package cz.msebera.android.httpclient.impl.entity;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.HttpMessage;
import cz.msebera.android.httpclient.ProtocolException;
import cz.msebera.android.httpclient.entity.ContentLengthStrategy;
import cz.msebera.android.httpclient.util.Args;

public class LaxContentLengthStrategy implements ContentLengthStrategy {
    public static final LaxContentLengthStrategy f7790a;
    private final int f7791b;

    static {
        f7790a = new LaxContentLengthStrategy();
    }

    public LaxContentLengthStrategy(int i) {
        this.f7791b = i;
    }

    public LaxContentLengthStrategy() {
        this(-1);
    }

    public long m12516a(HttpMessage httpMessage) {
        Args.m12722a((Object) httpMessage, "HTTP message");
        Header c = httpMessage.m10616c("Transfer-Encoding");
        if (c != null) {
            try {
                HeaderElement[] e = c.m11363e();
                int length = e.length;
                if (!"identity".equalsIgnoreCase(c.m11362d()) && length > 0 && "chunked".equalsIgnoreCase(e[length - 1].m11368a())) {
                    return -2;
                }
                return -1;
            } catch (Throwable e2) {
                throw new ProtocolException("Invalid Transfer-Encoding header value: " + c, e2);
            }
        } else if (httpMessage.m10616c("Content-Length") == null) {
            return (long) this.f7791b;
        } else {
            long parseLong;
            Header[] b = httpMessage.m10615b("Content-Length");
            int length2 = b.length - 1;
            while (length2 >= 0) {
                try {
                    parseLong = Long.parseLong(b[length2].m11362d());
                    break;
                } catch (NumberFormatException e3) {
                    length2--;
                }
            }
            parseLong = -1;
            if (parseLong >= 0) {
                return parseLong;
            }
            return -1;
        }
    }
}
