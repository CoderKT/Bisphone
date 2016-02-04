package cz.msebera.android.httpclient;

import java.io.Serializable;

public final class HttpVersion extends ProtocolVersion implements Serializable {
    public static final HttpVersion f7208a;
    public static final HttpVersion f7209b;
    public static final HttpVersion f7210c;

    static {
        f7208a = new HttpVersion(0, 9);
        f7209b = new HttpVersion(1, 0);
        f7210c = new HttpVersion(1, 1);
    }

    public HttpVersion(int i, int i2) {
        super("HTTP", i, i2);
    }

    public ProtocolVersion m11402a(int i, int i2) {
        if (i == this.e && i2 == this.f) {
            return this;
        }
        if (i == 1) {
            if (i2 == 0) {
                return f7209b;
            }
            if (i2 == 1) {
                return f7210c;
            }
        }
        if (i == 0 && i2 == 9) {
            return f7208a;
        }
        this(i, i2);
        return this;
    }
}
