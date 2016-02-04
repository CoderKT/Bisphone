package cz.msebera.android.httpclient.client.protocol;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpRequestInterceptor;
import cz.msebera.android.httpclient.auth.AuthOption;
import cz.msebera.android.httpclient.auth.AuthProtocolState;
import cz.msebera.android.httpclient.auth.AuthScheme;
import cz.msebera.android.httpclient.auth.AuthState;
import cz.msebera.android.httpclient.auth.AuthenticationException;
import cz.msebera.android.httpclient.auth.ContextAwareAuthScheme;
import cz.msebera.android.httpclient.auth.Credentials;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Asserts;
import java.util.Queue;
import se.emilsjolander.stickylistheaders.C1128R;

@Deprecated
abstract class RequestAuthenticationBase implements HttpRequestInterceptor {
    final HttpClientAndroidLog f7300a;

    /* renamed from: cz.msebera.android.httpclient.client.protocol.RequestAuthenticationBase.1 */
    /* synthetic */ class C09331 {
        static final /* synthetic */ int[] f7299a;

        static {
            f7299a = new int[AuthProtocolState.values().length];
            try {
                f7299a[AuthProtocolState.FAILURE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f7299a[AuthProtocolState.SUCCESS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f7299a[AuthProtocolState.CHALLENGED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public RequestAuthenticationBase() {
        this.f7300a = new HttpClientAndroidLog(getClass());
    }

    void m11557a(AuthState authState, HttpRequest httpRequest, HttpContext httpContext) {
        AuthScheme c = authState.m11437c();
        Credentials d = authState.m11438d();
        switch (C09331.f7299a[authState.m11436b().ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                m11556a(c);
                if (c.m11419c()) {
                    return;
                }
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                Queue e = authState.m11439e();
                if (e == null) {
                    m11556a(c);
                    break;
                }
                while (!e.isEmpty()) {
                    AuthOption authOption = (AuthOption) e.remove();
                    c = authOption.m11413a();
                    d = authOption.m11414b();
                    authState.m11433a(c, d);
                    if (this.f7300a.m11836a()) {
                        this.f7300a.m11834a("Generating response to an authentication challenge using " + c.m11416a() + " scheme");
                    }
                    try {
                        httpRequest.m10608a(m11555a(c, d, httpRequest, httpContext));
                        return;
                    } catch (AuthenticationException e2) {
                        if (this.f7300a.m11842c()) {
                            this.f7300a.m11840c(c + " authentication error: " + e2.getMessage());
                        }
                    }
                }
                return;
        }
        if (c != null) {
            try {
                httpRequest.m10608a(m11555a(c, d, httpRequest, httpContext));
            } catch (AuthenticationException e22) {
                if (this.f7300a.m11839b()) {
                    this.f7300a.m11837b(c + " authentication error: " + e22.getMessage());
                }
            }
        }
    }

    private void m11556a(AuthScheme authScheme) {
        Asserts.m12728a((Object) authScheme, "Auth scheme");
    }

    private Header m11555a(AuthScheme authScheme, Credentials credentials, HttpRequest httpRequest, HttpContext httpContext) {
        Asserts.m12728a((Object) authScheme, "Auth scheme");
        if (authScheme instanceof ContextAwareAuthScheme) {
            return ((ContextAwareAuthScheme) authScheme).m11440a(credentials, httpRequest, httpContext);
        }
        return authScheme.m11415a(credentials, httpRequest);
    }
}
