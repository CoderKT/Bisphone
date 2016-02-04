package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.util.VersionInfo;

public class HttpClientBuilder {
    static final String f7619a;
    private int f7620b;
    private int f7621c;

    static {
        VersionInfo a = VersionInfo.m12773a("cz.msebera.android.httpclient.client", HttpClientBuilder.class.getClassLoader());
        f7619a = "Apache-HttpClient/" + (a != null ? a.m12775a() : "UNAVAILABLE") + " (java 1.5)";
    }

    protected HttpClientBuilder() {
        this.f7620b = 0;
        this.f7621c = 0;
    }
}
