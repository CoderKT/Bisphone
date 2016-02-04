package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.auth.AuthScope;
import cz.msebera.android.httpclient.auth.Credentials;
import cz.msebera.android.httpclient.client.CredentialsProvider;
import cz.msebera.android.httpclient.util.Args;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BasicCredentialsProvider implements CredentialsProvider {
    private final ConcurrentHashMap<AuthScope, Credentials> f7570a;

    public BasicCredentialsProvider() {
        this.f7570a = new ConcurrentHashMap();
    }

    private static Credentials m12079a(Map<AuthScope, Credentials> map, AuthScope authScope) {
        Credentials credentials = (Credentials) map.get(authScope);
        if (credentials != null) {
            return credentials;
        }
        int i = -1;
        AuthScope authScope2 = null;
        for (AuthScope authScope3 : map.keySet()) {
            AuthScope authScope32;
            int i2;
            int a = authScope.m11429a(authScope32);
            if (a > i) {
                i2 = a;
            } else {
                authScope32 = authScope2;
                i2 = i;
            }
            i = i2;
            authScope2 = authScope32;
        }
        if (authScope2 != null) {
            return (Credentials) map.get(authScope2);
        }
        return credentials;
    }

    public Credentials m12080a(AuthScope authScope) {
        Args.m12722a((Object) authScope, "Authentication scope");
        return m12079a(this.f7570a, authScope);
    }

    public String toString() {
        return this.f7570a.toString();
    }
}
