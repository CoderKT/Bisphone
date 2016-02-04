package cz.msebera.android.httpclient.auth;

import cz.msebera.android.httpclient.util.Args;

public final class AuthOption {
    private final AuthScheme f7211a;
    private final Credentials f7212b;

    public AuthOption(AuthScheme authScheme, Credentials credentials) {
        Args.m12722a((Object) authScheme, "Auth scheme");
        Args.m12722a((Object) credentials, "User credentials");
        this.f7211a = authScheme;
        this.f7212b = credentials;
    }

    public AuthScheme m11413a() {
        return this.f7211a;
    }

    public Credentials m11414b() {
        return this.f7212b;
    }

    public String toString() {
        return this.f7211a.toString();
    }
}
