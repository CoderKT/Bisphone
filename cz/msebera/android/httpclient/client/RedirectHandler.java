package cz.msebera.android.httpclient.client;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.protocol.HttpContext;
import java.net.URI;

@Deprecated
public interface RedirectHandler {
    boolean m11470a(HttpResponse httpResponse, HttpContext httpContext);

    URI m11471b(HttpResponse httpResponse, HttpContext httpContext);
}
