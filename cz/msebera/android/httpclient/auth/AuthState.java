package cz.msebera.android.httpclient.auth;

import cz.msebera.android.httpclient.util.Args;
import java.util.Collection;
import java.util.Queue;

public class AuthState {
    private AuthProtocolState f7230a;
    private AuthScheme f7231b;
    private AuthScope f7232c;
    private Credentials f7233d;
    private Queue<AuthOption> f7234e;

    public AuthState() {
        this.f7230a = AuthProtocolState.UNCHALLENGED;
    }

    public void m11430a() {
        this.f7230a = AuthProtocolState.UNCHALLENGED;
        this.f7234e = null;
        this.f7231b = null;
        this.f7232c = null;
        this.f7233d = null;
    }

    public AuthProtocolState m11436b() {
        return this.f7230a;
    }

    public void m11431a(AuthProtocolState authProtocolState) {
        if (authProtocolState == null) {
            authProtocolState = AuthProtocolState.UNCHALLENGED;
        }
        this.f7230a = authProtocolState;
    }

    public AuthScheme m11437c() {
        return this.f7231b;
    }

    public Credentials m11438d() {
        return this.f7233d;
    }

    public void m11433a(AuthScheme authScheme, Credentials credentials) {
        Args.m12722a((Object) authScheme, "Auth scheme");
        Args.m12722a((Object) credentials, "Credentials");
        this.f7231b = authScheme;
        this.f7233d = credentials;
        this.f7234e = null;
    }

    public Queue<AuthOption> m11439e() {
        return this.f7234e;
    }

    public void m11435a(Queue<AuthOption> queue) {
        Args.m12723a((Collection) queue, "Queue of auth options");
        this.f7234e = queue;
        this.f7231b = null;
        this.f7233d = null;
    }

    @Deprecated
    public void m11432a(AuthScheme authScheme) {
        if (authScheme == null) {
            m11430a();
        } else {
            this.f7231b = authScheme;
        }
    }

    @Deprecated
    public void m11434a(Credentials credentials) {
        this.f7233d = credentials;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("state:").append(this.f7230a).append(";");
        if (this.f7231b != null) {
            stringBuilder.append("auth scheme:").append(this.f7231b.m11416a()).append(";");
        }
        if (this.f7233d != null) {
            stringBuilder.append("credentials present");
        }
        return stringBuilder.toString();
    }
}
