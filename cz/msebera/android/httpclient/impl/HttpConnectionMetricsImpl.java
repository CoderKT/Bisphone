package cz.msebera.android.httpclient.impl;

import cz.msebera.android.httpclient.io.HttpTransportMetrics;

public class HttpConnectionMetricsImpl {
    private final HttpTransportMetrics f7453a;
    private final HttpTransportMetrics f7454b;
    private long f7455c;
    private long f7456d;

    public HttpConnectionMetricsImpl(HttpTransportMetrics httpTransportMetrics, HttpTransportMetrics httpTransportMetrics2) {
        this.f7455c = 0;
        this.f7456d = 0;
        this.f7453a = httpTransportMetrics;
        this.f7454b = httpTransportMetrics2;
    }

    public void m11869a() {
        this.f7455c++;
    }

    public void m11870b() {
        this.f7456d++;
    }
}
