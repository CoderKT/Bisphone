package cz.msebera.android.httpclient.client;

import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.protocol.HttpContext;

public interface RedirectStrategy {
    boolean m11472a(HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext);

    HttpUriRequest m11473b(HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext);
}
