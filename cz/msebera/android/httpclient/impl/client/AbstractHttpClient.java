package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.ConnectionReuseStrategy;
import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpRequestInterceptor;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpResponseInterceptor;
import cz.msebera.android.httpclient.auth.AuthSchemeRegistry;
import cz.msebera.android.httpclient.client.AuthenticationStrategy;
import cz.msebera.android.httpclient.client.BackoffManager;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.ConnectionBackoffStrategy;
import cz.msebera.android.httpclient.client.CookieStore;
import cz.msebera.android.httpclient.client.CredentialsProvider;
import cz.msebera.android.httpclient.client.HttpRequestRetryHandler;
import cz.msebera.android.httpclient.client.RedirectStrategy;
import cz.msebera.android.httpclient.client.RequestDirector;
import cz.msebera.android.httpclient.client.UserTokenHandler;
import cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import cz.msebera.android.httpclient.client.params.HttpClientParamConfig;
import cz.msebera.android.httpclient.conn.ClientConnectionManager;
import cz.msebera.android.httpclient.conn.ClientConnectionManagerFactory;
import cz.msebera.android.httpclient.conn.ConnectionKeepAliveStrategy;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.conn.routing.HttpRoutePlanner;
import cz.msebera.android.httpclient.conn.scheme.SchemeRegistry;
import cz.msebera.android.httpclient.cookie.CookieSpecRegistry;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.impl.DefaultConnectionReuseStrategy;
import cz.msebera.android.httpclient.impl.auth.BasicSchemeFactory;
import cz.msebera.android.httpclient.impl.auth.DigestSchemeFactory;
import cz.msebera.android.httpclient.impl.auth.NTLMSchemeFactory;
import cz.msebera.android.httpclient.impl.conn.BasicClientConnectionManager;
import cz.msebera.android.httpclient.impl.conn.DefaultHttpRoutePlanner;
import cz.msebera.android.httpclient.impl.conn.SchemeRegistryFactory;
import cz.msebera.android.httpclient.impl.cookie.BestMatchSpecFactory;
import cz.msebera.android.httpclient.impl.cookie.BrowserCompatSpecFactory;
import cz.msebera.android.httpclient.impl.cookie.IgnoreSpecFactory;
import cz.msebera.android.httpclient.impl.cookie.NetscapeDraftSpecFactory;
import cz.msebera.android.httpclient.impl.cookie.RFC2109SpecFactory;
import cz.msebera.android.httpclient.impl.cookie.RFC2965SpecFactory;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.BasicHttpContext;
import cz.msebera.android.httpclient.protocol.BasicHttpProcessor;
import cz.msebera.android.httpclient.protocol.DefaultedHttpContext;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.protocol.HttpProcessor;
import cz.msebera.android.httpclient.protocol.HttpRequestExecutor;
import cz.msebera.android.httpclient.protocol.ImmutableHttpProcessor;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;

@Deprecated
public abstract class AbstractHttpClient extends CloseableHttpClient {
    public HttpClientAndroidLog f7541a;
    private HttpParams f7542c;
    private HttpRequestExecutor f7543d;
    private ClientConnectionManager f7544e;
    private ConnectionReuseStrategy f7545f;
    private ConnectionKeepAliveStrategy f7546g;
    private CookieSpecRegistry f7547h;
    private AuthSchemeRegistry f7548i;
    private BasicHttpProcessor f7549j;
    private ImmutableHttpProcessor f7550k;
    private HttpRequestRetryHandler f7551l;
    private RedirectStrategy f7552m;
    private AuthenticationStrategy f7553n;
    private AuthenticationStrategy f7554o;
    private CookieStore f7555p;
    private CredentialsProvider f7556q;
    private HttpRoutePlanner f7557r;
    private UserTokenHandler f7558s;
    private ConnectionBackoffStrategy f7559t;
    private BackoffManager f7560u;

    protected abstract HttpParams m12028a();

    protected abstract BasicHttpProcessor m12034b();

    protected AbstractHttpClient(ClientConnectionManager clientConnectionManager, HttpParams httpParams) {
        this.f7541a = new HttpClientAndroidLog(getClass());
        this.f7542c = httpParams;
        this.f7544e = clientConnectionManager;
    }

    protected HttpContext m12035c() {
        HttpContext basicHttpContext = new BasicHttpContext();
        basicHttpContext.m11529a("http.scheme-registry", m12050r().m11633a());
        basicHttpContext.m11529a("http.authscheme-registry", m12052t());
        basicHttpContext.m11529a("http.cookiespec-registry", m12054v());
        basicHttpContext.m11529a("http.cookie-store", m12021D());
        basicHttpContext.m11529a("http.auth.credentials-provider", m12022E());
        return basicHttpContext;
    }

    protected ClientConnectionManager m12036d() {
        SchemeRegistry a = SchemeRegistryFactory.m12319a();
        HttpParams q = m12049q();
        String str = (String) q.m12084a("http.connection-manager.factory-class-name");
        if (str != null) {
            try {
                ClientConnectionManagerFactory clientConnectionManagerFactory = (ClientConnectionManagerFactory) Class.forName(str).newInstance();
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Invalid class name: " + str);
            } catch (IllegalAccessException e2) {
                throw new IllegalAccessError(e2.getMessage());
            } catch (InstantiationException e3) {
                throw new InstantiationError(e3.getMessage());
            }
        }
        clientConnectionManagerFactory = null;
        if (clientConnectionManagerFactory != null) {
            return clientConnectionManagerFactory.m11636a(q, a);
        }
        return new BasicClientConnectionManager(a);
    }

    protected AuthSchemeRegistry m12037e() {
        AuthSchemeRegistry authSchemeRegistry = new AuthSchemeRegistry();
        authSchemeRegistry.m11427a("Basic", new BasicSchemeFactory());
        authSchemeRegistry.m11427a("Digest", new DigestSchemeFactory());
        authSchemeRegistry.m11427a("NTLM", new NTLMSchemeFactory());
        return authSchemeRegistry;
    }

    protected CookieSpecRegistry m12038f() {
        CookieSpecRegistry cookieSpecRegistry = new CookieSpecRegistry();
        cookieSpecRegistry.m11800a("best-match", new BestMatchSpecFactory());
        cookieSpecRegistry.m11800a("compatibility", new BrowserCompatSpecFactory());
        cookieSpecRegistry.m11800a("netscape", new NetscapeDraftSpecFactory());
        cookieSpecRegistry.m11800a("rfc2109", new RFC2109SpecFactory());
        cookieSpecRegistry.m11800a("rfc2965", new RFC2965SpecFactory());
        cookieSpecRegistry.m11800a("ignoreCookies", new IgnoreSpecFactory());
        return cookieSpecRegistry;
    }

    protected HttpRequestExecutor m12039g() {
        return new HttpRequestExecutor();
    }

    protected ConnectionReuseStrategy m12040h() {
        return new DefaultConnectionReuseStrategy();
    }

    protected ConnectionKeepAliveStrategy m12041i() {
        return new DefaultConnectionKeepAliveStrategy();
    }

    protected HttpRequestRetryHandler m12042j() {
        return new DefaultHttpRequestRetryHandler();
    }

    protected AuthenticationStrategy m12043k() {
        return new TargetAuthenticationStrategy();
    }

    protected AuthenticationStrategy m12044l() {
        return new ProxyAuthenticationStrategy();
    }

    protected CookieStore m12045m() {
        return new BasicCookieStore();
    }

    protected CredentialsProvider m12046n() {
        return new BasicCredentialsProvider();
    }

    protected HttpRoutePlanner m12047o() {
        return new DefaultHttpRoutePlanner(m12050r().m11633a());
    }

    protected UserTokenHandler m12048p() {
        return new DefaultUserTokenHandler();
    }

    public final synchronized HttpParams m12049q() {
        if (this.f7542c == null) {
            this.f7542c = m12028a();
        }
        return this.f7542c;
    }

    public final synchronized ClientConnectionManager m12050r() {
        if (this.f7544e == null) {
            this.f7544e = m12036d();
        }
        return this.f7544e;
    }

    public final synchronized HttpRequestExecutor m12051s() {
        if (this.f7543d == null) {
            this.f7543d = m12039g();
        }
        return this.f7543d;
    }

    public final synchronized AuthSchemeRegistry m12052t() {
        if (this.f7548i == null) {
            this.f7548i = m12037e();
        }
        return this.f7548i;
    }

    public final synchronized ConnectionBackoffStrategy m12053u() {
        return this.f7559t;
    }

    public final synchronized CookieSpecRegistry m12054v() {
        if (this.f7547h == null) {
            this.f7547h = m12038f();
        }
        return this.f7547h;
    }

    public final synchronized BackoffManager m12055w() {
        return this.f7560u;
    }

    public final synchronized ConnectionReuseStrategy m12056x() {
        if (this.f7545f == null) {
            this.f7545f = m12040h();
        }
        return this.f7545f;
    }

    public final synchronized ConnectionKeepAliveStrategy m12057y() {
        if (this.f7546g == null) {
            this.f7546g = m12041i();
        }
        return this.f7546g;
    }

    public final synchronized HttpRequestRetryHandler m12058z() {
        if (this.f7551l == null) {
            this.f7551l = m12042j();
        }
        return this.f7551l;
    }

    public synchronized void m12033a(HttpRequestRetryHandler httpRequestRetryHandler) {
        this.f7551l = httpRequestRetryHandler;
    }

    public final synchronized RedirectStrategy m12018A() {
        if (this.f7552m == null) {
            this.f7552m = new DefaultRedirectStrategy();
        }
        return this.f7552m;
    }

    public final synchronized AuthenticationStrategy m12019B() {
        if (this.f7553n == null) {
            this.f7553n = m12043k();
        }
        return this.f7553n;
    }

    public final synchronized AuthenticationStrategy m12020C() {
        if (this.f7554o == null) {
            this.f7554o = m12044l();
        }
        return this.f7554o;
    }

    public final synchronized CookieStore m12021D() {
        if (this.f7555p == null) {
            this.f7555p = m12045m();
        }
        return this.f7555p;
    }

    public final synchronized CredentialsProvider m12022E() {
        if (this.f7556q == null) {
            this.f7556q = m12046n();
        }
        return this.f7556q;
    }

    public final synchronized HttpRoutePlanner m12023F() {
        if (this.f7557r == null) {
            this.f7557r = m12047o();
        }
        return this.f7557r;
    }

    public final synchronized UserTokenHandler m12024G() {
        if (this.f7558s == null) {
            this.f7558s = m12048p();
        }
        return this.f7558s;
    }

    protected final synchronized BasicHttpProcessor m12025H() {
        if (this.f7549j == null) {
            this.f7549j = m12034b();
        }
        return this.f7549j;
    }

    private synchronized HttpProcessor m12017I() {
        HttpProcessor httpProcessor;
        int i = 0;
        synchronized (this) {
            if (this.f7550k == null) {
                int i2;
                BasicHttpProcessor H = m12025H();
                int a = H.m12688a();
                HttpRequestInterceptor[] httpRequestInterceptorArr = new HttpRequestInterceptor[a];
                for (i2 = 0; i2 < a; i2++) {
                    httpRequestInterceptorArr[i2] = H.m12689a(i2);
                }
                i2 = H.m12696b();
                HttpResponseInterceptor[] httpResponseInterceptorArr = new HttpResponseInterceptor[i2];
                while (i < i2) {
                    httpResponseInterceptorArr[i] = H.m12697b(i);
                    i++;
                }
                this.f7550k = new ImmutableHttpProcessor(httpRequestInterceptorArr, httpResponseInterceptorArr);
            }
            httpProcessor = this.f7550k;
        }
        return httpProcessor;
    }

    public synchronized void m12032a(HttpResponseInterceptor httpResponseInterceptor) {
        m12025H().m12700b(httpResponseInterceptor);
        this.f7550k = null;
    }

    public synchronized void m12030a(HttpRequestInterceptor httpRequestInterceptor) {
        m12025H().m12698b(httpRequestInterceptor);
        this.f7550k = null;
    }

    public synchronized void m12031a(HttpRequestInterceptor httpRequestInterceptor, int i) {
        m12025H().m12699b(httpRequestInterceptor, i);
        this.f7550k = null;
    }

    protected final CloseableHttpResponse m12027a(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) {
        HttpContext httpContext2;
        CloseableHttpResponse a;
        HttpRoute a2;
        Args.m12722a((Object) httpRequest, "HTTP request");
        synchronized (this) {
            HttpContext c = m12035c();
            if (httpContext == null) {
                httpContext2 = c;
            } else {
                Object defaultedHttpContext = new DefaultedHttpContext(httpContext, c);
            }
            HttpParams a3 = m12029a(httpRequest);
            httpContext2.m11529a("http.request-config", HttpClientParamConfig.m11524a(a3));
            RequestDirector a4 = m12026a(m12051s(), m12050r(), m12056x(), m12057y(), m12023F(), m12017I(), m12058z(), m12018A(), m12019B(), m12020C(), m12024G(), a3);
            HttpRoutePlanner F = m12023F();
            ConnectionBackoffStrategy u = m12053u();
            BackoffManager w = m12055w();
        }
        if (u == null || w == null) {
            a = CloseableHttpResponseProxy.m12097a(a4.m11474a(httpHost, httpRequest, httpContext2));
        } else {
            HttpHost httpHost2;
            if (httpHost != null) {
                httpHost2 = httpHost;
            } else {
                httpHost2 = (HttpHost) m12029a(httpRequest).m12084a("http.default-host");
            }
            try {
                a2 = F.m11701a(httpHost2, httpRequest, httpContext2);
                a = CloseableHttpResponseProxy.m12097a(a4.m11474a(httpHost, httpRequest, httpContext2));
                if (u.m11465a((HttpResponse) a)) {
                    w.m11463a(a2);
                } else {
                    w.m11464b(a2);
                }
            } catch (Throwable e) {
                if (u.m11466a(e)) {
                    w.m11463a(a2);
                }
                throw e;
            } catch (Throwable e2) {
                if (u.m11466a(e2)) {
                    w.m11463a(a2);
                }
                if (e2 instanceof HttpException) {
                    throw ((HttpException) e2);
                } else if (e2 instanceof IOException) {
                    throw ((IOException) e2);
                } else {
                    throw new UndeclaredThrowableException(e2);
                }
            } catch (Throwable e22) {
                throw new ClientProtocolException(e22);
            }
        }
        return a;
    }

    protected RequestDirector m12026a(HttpRequestExecutor httpRequestExecutor, ClientConnectionManager clientConnectionManager, ConnectionReuseStrategy connectionReuseStrategy, ConnectionKeepAliveStrategy connectionKeepAliveStrategy, HttpRoutePlanner httpRoutePlanner, HttpProcessor httpProcessor, HttpRequestRetryHandler httpRequestRetryHandler, RedirectStrategy redirectStrategy, AuthenticationStrategy authenticationStrategy, AuthenticationStrategy authenticationStrategy2, UserTokenHandler userTokenHandler, HttpParams httpParams) {
        return new DefaultRequestDirector(this.f7541a, httpRequestExecutor, clientConnectionManager, connectionReuseStrategy, connectionKeepAliveStrategy, httpRoutePlanner, httpProcessor, httpRequestRetryHandler, redirectStrategy, authenticationStrategy, authenticationStrategy2, userTokenHandler, httpParams);
    }

    protected HttpParams m12029a(HttpRequest httpRequest) {
        return new ClientParamsStack(null, m12049q(), httpRequest.m10622g(), null);
    }

    public void close() {
        m12050r().m11635b();
    }
}
