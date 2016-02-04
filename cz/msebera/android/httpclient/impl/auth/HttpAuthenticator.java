package cz.msebera.android.httpclient.impl.auth;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.auth.AuthProtocolState;
import cz.msebera.android.httpclient.auth.AuthScheme;
import cz.msebera.android.httpclient.auth.AuthState;
import cz.msebera.android.httpclient.auth.MalformedChallengeException;
import cz.msebera.android.httpclient.client.AuthenticationStrategy;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.protocol.HttpContext;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import se.emilsjolander.stickylistheaders.C1128R;

public class HttpAuthenticator {
    public HttpClientAndroidLog f7473a;

    /* renamed from: cz.msebera.android.httpclient.impl.auth.HttpAuthenticator.1 */
    /* synthetic */ class C09371 {
        static final /* synthetic */ int[] f7472a;

        static {
            f7472a = new int[AuthProtocolState.values().length];
            try {
                f7472a[AuthProtocolState.CHALLENGED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f7472a[AuthProtocolState.HANDSHAKE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f7472a[AuthProtocolState.SUCCESS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f7472a[AuthProtocolState.FAILURE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f7472a[AuthProtocolState.UNCHALLENGED.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public HttpAuthenticator(HttpClientAndroidLog httpClientAndroidLog) {
        if (httpClientAndroidLog == null) {
            httpClientAndroidLog = new HttpClientAndroidLog(getClass());
        }
        this.f7473a = httpClientAndroidLog;
    }

    public HttpAuthenticator() {
        this(null);
    }

    public boolean m11913a(HttpHost httpHost, HttpResponse httpResponse, AuthenticationStrategy authenticationStrategy, AuthState authState, HttpContext httpContext) {
        if (authenticationStrategy.m11460a(httpHost, httpResponse, httpContext)) {
            this.f7473a.m11834a("Authentication required");
            if (authState.m11436b() == AuthProtocolState.SUCCESS) {
                authenticationStrategy.m11462b(httpHost, authState.m11437c(), httpContext);
            }
            return true;
        }
        switch (C09371.f7472a[authState.m11436b().ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                this.f7473a.m11834a("Authentication succeeded");
                authState.m11431a(AuthProtocolState.SUCCESS);
                authenticationStrategy.m11459a(httpHost, authState.m11437c(), httpContext);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                break;
            default:
                authState.m11431a(AuthProtocolState.UNCHALLENGED);
                break;
        }
        return false;
    }

    public boolean m11914b(HttpHost httpHost, HttpResponse httpResponse, AuthenticationStrategy authenticationStrategy, AuthState authState, HttpContext httpContext) {
        try {
            if (this.f7473a.m11836a()) {
                this.f7473a.m11834a(httpHost.m11388e() + " requested authentication");
            }
            Map b = authenticationStrategy.m11461b(httpHost, httpResponse, httpContext);
            if (b.isEmpty()) {
                this.f7473a.m11834a("Response contains no authentication challenges");
                return false;
            }
            AuthScheme c = authState.m11437c();
            switch (C09371.f7472a[authState.m11436b().ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    if (c == null) {
                        this.f7473a.m11834a("Auth scheme is null");
                        authenticationStrategy.m11462b(httpHost, null, httpContext);
                        authState.m11430a();
                        authState.m11431a(AuthProtocolState.FAILURE);
                        return false;
                    }
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    authState.m11430a();
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    return false;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    break;
            }
            if (c != null) {
                Header header = (Header) b.get(c.m11416a().toLowerCase(Locale.ENGLISH));
                if (header != null) {
                    this.f7473a.m11834a("Authorization challenge processed");
                    c.m11417a(header);
                    if (c.m11420d()) {
                        this.f7473a.m11834a("Authentication failed");
                        authenticationStrategy.m11462b(httpHost, authState.m11437c(), httpContext);
                        authState.m11430a();
                        authState.m11431a(AuthProtocolState.FAILURE);
                        return false;
                    }
                    authState.m11431a(AuthProtocolState.HANDSHAKE);
                    return true;
                }
                authState.m11430a();
            }
            Queue a = authenticationStrategy.m11458a(b, httpHost, httpResponse, httpContext);
            if (a == null || a.isEmpty()) {
                return false;
            }
            if (this.f7473a.m11836a()) {
                this.f7473a.m11834a("Selected authentication options: " + a);
            }
            authState.m11431a(AuthProtocolState.CHALLENGED);
            authState.m11435a(a);
            return true;
        } catch (MalformedChallengeException e) {
            if (this.f7473a.m11842c()) {
                this.f7473a.m11840c("Malformed challenge: " + e.getMessage());
            }
            authState.m11430a();
            return false;
        }
    }
}
