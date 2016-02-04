package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.conn.routing.HttpRoute;

@Deprecated
public class RoutedRequest {
    protected final RequestWrapper f7625a;
    protected final HttpRoute f7626b;

    public RoutedRequest(RequestWrapper requestWrapper, HttpRoute httpRoute) {
        this.f7625a = requestWrapper;
        this.f7626b = httpRoute;
    }

    public final RequestWrapper m12160a() {
        return this.f7625a;
    }

    public final HttpRoute m12161b() {
        return this.f7626b;
    }
}
