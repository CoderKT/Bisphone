package cz.msebera.android.httpclient.client;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.auth.AuthScheme;

public interface AuthCache {
    AuthScheme m11452a(HttpHost httpHost);

    void m11453a(HttpHost httpHost, AuthScheme authScheme);

    void m11454b(HttpHost httpHost);
}
