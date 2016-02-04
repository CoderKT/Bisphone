package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.HttpConnection;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import java.util.HashMap;
import java.util.Map;

@Deprecated
public class IdleConnectionHandler {
    public HttpClientAndroidLog f7682a;
    private final Map<HttpConnection, Object> f7683b;

    public IdleConnectionHandler() {
        this.f7682a = new HttpClientAndroidLog(getClass());
        this.f7683b = new HashMap();
    }

    public void m12263a() {
        this.f7683b.clear();
    }
}
