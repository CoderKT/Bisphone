package cz.msebera.android.httpclient.conn;

import cz.msebera.android.httpclient.HttpHost;
import java.io.InterruptedIOException;

public class ConnectTimeoutException extends InterruptedIOException {
    private final HttpHost f7338a;

    public ConnectTimeoutException() {
        this.f7338a = null;
    }

    public ConnectTimeoutException(String str) {
        super(str);
        this.f7338a = null;
    }
}
