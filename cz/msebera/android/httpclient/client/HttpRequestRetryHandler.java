package cz.msebera.android.httpclient.client;

import cz.msebera.android.httpclient.protocol.HttpContext;
import java.io.IOException;

public interface HttpRequestRetryHandler {
    boolean m10745a(IOException iOException, int i, HttpContext httpContext);
}
