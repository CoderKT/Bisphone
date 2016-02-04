package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.ConnectionReuseStrategy;
import cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.ProtocolException;
import cz.msebera.android.httpclient.auth.AuthProtocolState;
import cz.msebera.android.httpclient.auth.AuthScheme;
import cz.msebera.android.httpclient.auth.AuthState;
import cz.msebera.android.httpclient.auth.UsernamePasswordCredentials;
import cz.msebera.android.httpclient.client.AuthenticationHandler;
import cz.msebera.android.httpclient.client.AuthenticationStrategy;
import cz.msebera.android.httpclient.client.HttpRequestRetryHandler;
import cz.msebera.android.httpclient.client.RedirectException;
import cz.msebera.android.httpclient.client.RedirectHandler;
import cz.msebera.android.httpclient.client.RedirectStrategy;
import cz.msebera.android.httpclient.client.RequestDirector;
import cz.msebera.android.httpclient.client.UserTokenHandler;
import cz.msebera.android.httpclient.client.methods.AbortableHttpRequest;
import cz.msebera.android.httpclient.client.params.HttpClientParams;
import cz.msebera.android.httpclient.client.utils.URIUtils;
import cz.msebera.android.httpclient.conn.BasicManagedEntity;
import cz.msebera.android.httpclient.conn.ClientConnectionManager;
import cz.msebera.android.httpclient.conn.ClientConnectionRequest;
import cz.msebera.android.httpclient.conn.ConnectionKeepAliveStrategy;
import cz.msebera.android.httpclient.conn.ManagedClientConnection;
import cz.msebera.android.httpclient.conn.routing.BasicRouteDirector;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.conn.routing.HttpRouteDirector;
import cz.msebera.android.httpclient.conn.routing.HttpRoutePlanner;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.impl.auth.BasicScheme;
import cz.msebera.android.httpclient.message.BasicHttpRequest;
import cz.msebera.android.httpclient.params.HttpConnectionParams;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.params.HttpProtocolParams;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.protocol.HttpProcessor;
import cz.msebera.android.httpclient.protocol.HttpRequestExecutor;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.EntityUtils;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URI;
import java.util.concurrent.TimeUnit;
import se.emilsjolander.stickylistheaders.C1128R;

@Deprecated
public class DefaultRequestDirector implements RequestDirector {
    public HttpClientAndroidLog f7586a;
    protected final ClientConnectionManager f7587b;
    protected final HttpRoutePlanner f7588c;
    protected final ConnectionReuseStrategy f7589d;
    protected final ConnectionKeepAliveStrategy f7590e;
    protected final HttpRequestExecutor f7591f;
    protected final HttpProcessor f7592g;
    protected final HttpRequestRetryHandler f7593h;
    @Deprecated
    protected final RedirectHandler f7594i;
    protected final RedirectStrategy f7595j;
    @Deprecated
    protected final AuthenticationHandler f7596k;
    protected final AuthenticationStrategy f7597l;
    @Deprecated
    protected final AuthenticationHandler f7598m;
    protected final AuthenticationStrategy f7599n;
    protected final UserTokenHandler f7600o;
    protected final HttpParams f7601p;
    protected ManagedClientConnection f7602q;
    protected final AuthState f7603r;
    protected final AuthState f7604s;
    private final HttpAuthenticator f7605t;
    private int f7606u;
    private int f7607v;
    private final int f7608w;
    private HttpHost f7609x;

    public DefaultRequestDirector(HttpClientAndroidLog httpClientAndroidLog, HttpRequestExecutor httpRequestExecutor, ClientConnectionManager clientConnectionManager, ConnectionReuseStrategy connectionReuseStrategy, ConnectionKeepAliveStrategy connectionKeepAliveStrategy, HttpRoutePlanner httpRoutePlanner, HttpProcessor httpProcessor, HttpRequestRetryHandler httpRequestRetryHandler, RedirectStrategy redirectStrategy, AuthenticationStrategy authenticationStrategy, AuthenticationStrategy authenticationStrategy2, UserTokenHandler userTokenHandler, HttpParams httpParams) {
        Args.m12722a((Object) httpClientAndroidLog, "Log");
        Args.m12722a((Object) httpRequestExecutor, "Request executor");
        Args.m12722a((Object) clientConnectionManager, "Client connection manager");
        Args.m12722a((Object) connectionReuseStrategy, "Connection reuse strategy");
        Args.m12722a((Object) connectionKeepAliveStrategy, "Connection keep alive strategy");
        Args.m12722a((Object) httpRoutePlanner, "Route planner");
        Args.m12722a((Object) httpProcessor, "HTTP protocol processor");
        Args.m12722a((Object) httpRequestRetryHandler, "HTTP request retry handler");
        Args.m12722a((Object) redirectStrategy, "Redirect strategy");
        Args.m12722a((Object) authenticationStrategy, "Target authentication strategy");
        Args.m12722a((Object) authenticationStrategy2, "Proxy authentication strategy");
        Args.m12722a((Object) userTokenHandler, "User token handler");
        Args.m12722a((Object) httpParams, "HTTP parameters");
        this.f7586a = httpClientAndroidLog;
        this.f7605t = new HttpAuthenticator(httpClientAndroidLog);
        this.f7591f = httpRequestExecutor;
        this.f7587b = clientConnectionManager;
        this.f7589d = connectionReuseStrategy;
        this.f7590e = connectionKeepAliveStrategy;
        this.f7588c = httpRoutePlanner;
        this.f7592g = httpProcessor;
        this.f7593h = httpRequestRetryHandler;
        this.f7595j = redirectStrategy;
        this.f7597l = authenticationStrategy;
        this.f7599n = authenticationStrategy2;
        this.f7600o = userTokenHandler;
        this.f7601p = httpParams;
        if (redirectStrategy instanceof DefaultRedirectStrategyAdaptor) {
            this.f7594i = ((DefaultRedirectStrategyAdaptor) redirectStrategy).m12111a();
        } else {
            this.f7594i = null;
        }
        if (authenticationStrategy instanceof AuthenticationStrategyAdaptor) {
            this.f7596k = ((AuthenticationStrategyAdaptor) authenticationStrategy).m12060a();
        } else {
            this.f7596k = null;
        }
        if (authenticationStrategy2 instanceof AuthenticationStrategyAdaptor) {
            this.f7598m = ((AuthenticationStrategyAdaptor) authenticationStrategy2).m12060a();
        } else {
            this.f7598m = null;
        }
        this.f7602q = null;
        this.f7606u = 0;
        this.f7607v = 0;
        this.f7603r = new AuthState();
        this.f7604s = new AuthState();
        this.f7608w = this.f7601p.m12081a("http.protocol.max-redirects", 100);
    }

    private RequestWrapper m12114a(HttpRequest httpRequest) {
        if (httpRequest instanceof HttpEntityEnclosingRequest) {
            return new EntityEnclosingRequestWrapper((HttpEntityEnclosingRequest) httpRequest);
        }
        return new RequestWrapper(httpRequest);
    }

    protected void m12122a(RequestWrapper requestWrapper, HttpRoute httpRoute) {
        try {
            URI k = requestWrapper.m12138k();
            if (httpRoute.m11697d() == null || httpRoute.m11698e()) {
                if (k.isAbsolute()) {
                    k = URIUtils.m11590a(k, null, true);
                } else {
                    k = URIUtils.m11589a(k);
                }
            } else if (k.isAbsolute()) {
                k = URIUtils.m11589a(k);
            } else {
                k = URIUtils.m11590a(k, httpRoute.m11693a(), true);
            }
            requestWrapper.m12133a(k);
        } catch (Throwable e) {
            throw new ProtocolException("Invalid URI: " + requestWrapper.m12135h().m11408c(), e);
        }
    }

    public HttpResponse m12118a(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) {
        Object obj = null;
        httpContext.m11529a("http.auth.target-scope", this.f7603r);
        httpContext.m11529a("http.auth.proxy-scope", this.f7604s);
        RequestWrapper a = m12114a(httpRequest);
        a.m10624a(this.f7601p);
        HttpRoute b = m12124b(httpHost, a, httpContext);
        this.f7609x = (HttpHost) a.m10636g().m12084a("http.virtual-host");
        if (this.f7609x != null && this.f7609x.m11385b() == -1) {
            int b2 = (httpHost != null ? httpHost : b.m11693a()).m11385b();
            if (b2 != -1) {
                this.f7609x = new HttpHost(this.f7609x.m11384a(), b2, this.f7609x.m11386c());
            }
        }
        RoutedRequest routedRequest = new RoutedRequest(a, b);
        HttpResponse httpResponse = null;
        boolean z = false;
        while (obj == null) {
            try {
                HttpRequest a2 = routedRequest.m12160a();
                HttpRoute b3 = routedRequest.m12161b();
                Object a3 = httpContext.m11528a("http.user-token");
                if (this.f7602q == null) {
                    ClientConnectionRequest a4 = this.f7587b.m11632a(b3, a3);
                    if (httpRequest instanceof AbortableHttpRequest) {
                        ((AbortableHttpRequest) httpRequest).m10638a(a4);
                    }
                    this.f7602q = a4.m11640a(HttpClientParams.m11527c(this.f7601p), TimeUnit.MILLISECONDS);
                    if (HttpConnectionParams.m12677f(this.f7601p) && this.f7602q.m11375c()) {
                        this.f7586a.m11834a("Stale connection check");
                        if (this.f7602q.m11376d()) {
                            this.f7586a.m11834a("Stale connection detected");
                            this.f7602q.close();
                        }
                    }
                }
                if (httpRequest instanceof AbortableHttpRequest) {
                    ((AbortableHttpRequest) httpRequest).m10639a(this.f7602q);
                }
                try {
                    m12115a(routedRequest, httpContext);
                    String userInfo = a2.m12138k().getUserInfo();
                    if (userInfo != null) {
                        this.f7603r.m11433a(new BasicScheme(), new UsernamePasswordCredentials(userInfo));
                    }
                    if (this.f7609x != null) {
                        httpHost = this.f7609x;
                    } else {
                        URI k = a2.m12138k();
                        if (k.isAbsolute()) {
                            httpHost = URIUtils.m11592b(k);
                        }
                    }
                    if (httpHost == null) {
                        httpHost = b3.m11693a();
                    }
                    a2.m12140m();
                    m12122a((RequestWrapper) a2, b3);
                    httpContext.m11529a("http.target_host", httpHost);
                    httpContext.m11529a("http.route", b3);
                    httpContext.m11529a("http.connection", this.f7602q);
                    this.f7591f.m12706a(a2, this.f7592g, httpContext);
                    HttpResponse b4 = m12116b(routedRequest, httpContext);
                    if (b4 == null) {
                        httpResponse = b4;
                    } else {
                        b4.m10609a(this.f7601p);
                        this.f7591f.m12707a(b4, this.f7592g, httpContext);
                        z = this.f7589d.m11360a(b4, httpContext);
                        if (z) {
                            long a5 = this.f7590e.m11642a(b4, httpContext);
                            if (this.f7586a.m11836a()) {
                                if (a5 > 0) {
                                    userInfo = "for " + a5 + " " + TimeUnit.MILLISECONDS;
                                } else {
                                    userInfo = "indefinitely";
                                }
                                this.f7586a.m11834a("Connection can be kept alive " + userInfo);
                            }
                            this.f7602q.m11653a(a5, TimeUnit.MILLISECONDS);
                        }
                        RoutedRequest a6 = m12119a(routedRequest, b4, httpContext);
                        if (a6 == null) {
                            obj = 1;
                        } else {
                            if (z) {
                                EntityUtils.m12764a(b4.m11393b());
                                this.f7602q.m11660k();
                            } else {
                                this.f7602q.close();
                                if (this.f7604s.m11436b().compareTo(AuthProtocolState.CHALLENGED) > 0 && this.f7604s.m11437c() != null && this.f7604s.m11437c().m11419c()) {
                                    this.f7586a.m11834a("Resetting proxy auth state");
                                    this.f7604s.m11430a();
                                }
                                if (this.f7603r.m11436b().compareTo(AuthProtocolState.CHALLENGED) > 0 && this.f7603r.m11437c() != null && this.f7603r.m11437c().m11419c()) {
                                    this.f7586a.m11834a("Resetting target auth state");
                                    this.f7603r.m11430a();
                                }
                            }
                            if (!a6.m12161b().equals(routedRequest.m12161b())) {
                                m12120a();
                            }
                            routedRequest = a6;
                        }
                        if (this.f7602q != null) {
                            Object a7;
                            if (a3 == null) {
                                a7 = this.f7600o.m11475a(httpContext);
                                httpContext.m11529a("http.user-token", a7);
                            } else {
                                a7 = a3;
                            }
                            if (a7 != null) {
                                this.f7602q.m11657a(a7);
                            }
                        }
                        httpResponse = b4;
                    }
                } catch (TunnelRefusedException e) {
                    if (this.f7586a.m11836a()) {
                        this.f7586a.m11834a(e.getMessage());
                    }
                    httpResponse = e.m12168a();
                }
            } catch (InterruptedException e2) {
                Thread.currentThread().interrupt();
                throw new InterruptedIOException();
            } catch (Throwable e3) {
                InterruptedIOException interruptedIOException = new InterruptedIOException("Connection has been shut down");
                interruptedIOException.initCause(e3);
                throw interruptedIOException;
            } catch (HttpException e4) {
                m12117b();
                throw e4;
            } catch (IOException e5) {
                m12117b();
                throw e5;
            } catch (RuntimeException e6) {
                m12117b();
                throw e6;
            }
        }
        if (httpResponse == null || httpResponse.m11393b() == null || !httpResponse.m11393b().m10546f()) {
            if (z) {
                this.f7602q.m11660k();
            }
            m12120a();
        } else {
            httpResponse.m11392a(new BasicManagedEntity(httpResponse.m11393b(), this.f7602q, z));
        }
        return httpResponse;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m12115a(cz.msebera.android.httpclient.impl.client.RoutedRequest r8, cz.msebera.android.httpclient.protocol.HttpContext r9) {
        /*
        r7 = this;
        r2 = r8.m12161b();
        r3 = r8.m12160a();
        r0 = 0;
    L_0x0009:
        r1 = "http.request";
        r9.m11529a(r1, r3);
        r0 = r0 + 1;
        r1 = r7.f7602q;	 Catch:{ IOException -> 0x002f }
        r1 = r1.m11375c();	 Catch:{ IOException -> 0x002f }
        if (r1 != 0) goto L_0x0023;
    L_0x0018:
        r1 = r7.f7602q;	 Catch:{ IOException -> 0x002f }
        r4 = r7.f7601p;	 Catch:{ IOException -> 0x002f }
        r1.m11655a(r2, r9, r4);	 Catch:{ IOException -> 0x002f }
    L_0x001f:
        r7.m12121a(r2, r9);	 Catch:{ IOException -> 0x002f }
        return;
    L_0x0023:
        r1 = r7.f7602q;	 Catch:{ IOException -> 0x002f }
        r4 = r7.f7601p;	 Catch:{ IOException -> 0x002f }
        r4 = cz.msebera.android.httpclient.params.HttpConnectionParams.m12668a(r4);	 Catch:{ IOException -> 0x002f }
        r1.m11374b(r4);	 Catch:{ IOException -> 0x002f }
        goto L_0x001f;
    L_0x002f:
        r1 = move-exception;
        r4 = r7.f7602q;	 Catch:{ IOException -> 0x00a9 }
        r4.close();	 Catch:{ IOException -> 0x00a9 }
    L_0x0035:
        r4 = r7.f7593h;
        r4 = r4.m10745a(r1, r0, r9);
        if (r4 == 0) goto L_0x00a8;
    L_0x003d:
        r4 = r7.f7586a;
        r4 = r4.m11844d();
        if (r4 == 0) goto L_0x0009;
    L_0x0045:
        r4 = r7.f7586a;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "I/O exception (";
        r5 = r5.append(r6);
        r6 = r1.getClass();
        r6 = r6.getName();
        r5 = r5.append(r6);
        r6 = ") caught when connecting to ";
        r5 = r5.append(r6);
        r5 = r5.append(r2);
        r6 = ": ";
        r5 = r5.append(r6);
        r6 = r1.getMessage();
        r5 = r5.append(r6);
        r5 = r5.toString();
        r4.m11843d(r5);
        r4 = r7.f7586a;
        r4 = r4.m11836a();
        if (r4 == 0) goto L_0x008e;
    L_0x0085:
        r4 = r7.f7586a;
        r5 = r1.getMessage();
        r4.m11835a(r5, r1);
    L_0x008e:
        r1 = r7.f7586a;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "Retrying connect to ";
        r4 = r4.append(r5);
        r4 = r4.append(r2);
        r4 = r4.toString();
        r1.m11843d(r4);
        goto L_0x0009;
    L_0x00a8:
        throw r1;
    L_0x00a9:
        r4 = move-exception;
        goto L_0x0035;
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.httpclient.impl.client.DefaultRequestDirector.a(cz.msebera.android.httpclient.impl.client.RoutedRequest, cz.msebera.android.httpclient.protocol.HttpContext):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private cz.msebera.android.httpclient.HttpResponse m12116b(cz.msebera.android.httpclient.impl.client.RoutedRequest r8, cz.msebera.android.httpclient.protocol.HttpContext r9) {
        /*
        r7 = this;
        r1 = 0;
        r2 = r8.m12160a();
        r3 = r8.m12161b();
        r0 = r1;
    L_0x000a:
        r4 = r7.f7606u;
        r4 = r4 + 1;
        r7.f7606u = r4;
        r2.m12143p();
        r4 = r2.m12139l();
        if (r4 != 0) goto L_0x0032;
    L_0x0019:
        r1 = r7.f7586a;
        r2 = "Cannot retry non-repeatable request";
        r1.m11834a(r2);
        if (r0 == 0) goto L_0x002a;
    L_0x0022:
        r1 = new cz.msebera.android.httpclient.client.NonRepeatableRequestException;
        r2 = "Cannot retry request with a non-repeatable request entity.  The cause lists the reason the original request failed.";
        r1.<init>(r2, r0);
        throw r1;
    L_0x002a:
        r0 = new cz.msebera.android.httpclient.client.NonRepeatableRequestException;
        r1 = "Cannot retry request with a non-repeatable request entity.";
        r0.<init>(r1);
        throw r0;
    L_0x0032:
        r0 = r7.f7602q;	 Catch:{ IOException -> 0x0087 }
        r0 = r0.m11375c();	 Catch:{ IOException -> 0x0087 }
        if (r0 != 0) goto L_0x004e;
    L_0x003a:
        r0 = r3.m11698e();	 Catch:{ IOException -> 0x0087 }
        if (r0 != 0) goto L_0x007f;
    L_0x0040:
        r0 = r7.f7586a;	 Catch:{ IOException -> 0x0087 }
        r4 = "Reopening the direct connection.";
        r0.m11834a(r4);	 Catch:{ IOException -> 0x0087 }
        r0 = r7.f7602q;	 Catch:{ IOException -> 0x0087 }
        r4 = r7.f7601p;	 Catch:{ IOException -> 0x0087 }
        r0.m11655a(r3, r9, r4);	 Catch:{ IOException -> 0x0087 }
    L_0x004e:
        r0 = r7.f7586a;	 Catch:{ IOException -> 0x0087 }
        r0 = r0.m11836a();	 Catch:{ IOException -> 0x0087 }
        if (r0 == 0) goto L_0x0076;
    L_0x0056:
        r0 = r7.f7586a;	 Catch:{ IOException -> 0x0087 }
        r4 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0087 }
        r4.<init>();	 Catch:{ IOException -> 0x0087 }
        r5 = "Attempt ";
        r4 = r4.append(r5);	 Catch:{ IOException -> 0x0087 }
        r5 = r7.f7606u;	 Catch:{ IOException -> 0x0087 }
        r4 = r4.append(r5);	 Catch:{ IOException -> 0x0087 }
        r5 = " to execute request";
        r4 = r4.append(r5);	 Catch:{ IOException -> 0x0087 }
        r4 = r4.toString();	 Catch:{ IOException -> 0x0087 }
        r0.m11834a(r4);	 Catch:{ IOException -> 0x0087 }
    L_0x0076:
        r0 = r7.f7591f;	 Catch:{ IOException -> 0x0087 }
        r4 = r7.f7602q;	 Catch:{ IOException -> 0x0087 }
        r1 = r0.m12705a(r2, r4, r9);	 Catch:{ IOException -> 0x0087 }
    L_0x007e:
        return r1;
    L_0x007f:
        r0 = r7.f7586a;	 Catch:{ IOException -> 0x0087 }
        r4 = "Proxied connection. Need to start over.";
        r0.m11834a(r4);	 Catch:{ IOException -> 0x0087 }
        goto L_0x007e;
    L_0x0087:
        r0 = move-exception;
        r4 = r7.f7586a;
        r5 = "Closing the connection.";
        r4.m11834a(r5);
        r4 = r7.f7602q;	 Catch:{ IOException -> 0x0140 }
        r4.close();	 Catch:{ IOException -> 0x0140 }
    L_0x0094:
        r4 = r7.f7593h;
        r5 = r2.m12142o();
        r4 = r4.m10745a(r0, r5, r9);
        if (r4 == 0) goto L_0x0113;
    L_0x00a0:
        r4 = r7.f7586a;
        r4 = r4.m11844d();
        if (r4 == 0) goto L_0x00e0;
    L_0x00a8:
        r4 = r7.f7586a;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "I/O exception (";
        r5 = r5.append(r6);
        r6 = r0.getClass();
        r6 = r6.getName();
        r5 = r5.append(r6);
        r6 = ") caught when processing request to ";
        r5 = r5.append(r6);
        r5 = r5.append(r3);
        r6 = ": ";
        r5 = r5.append(r6);
        r6 = r0.getMessage();
        r5 = r5.append(r6);
        r5 = r5.toString();
        r4.m11843d(r5);
    L_0x00e0:
        r4 = r7.f7586a;
        r4 = r4.m11836a();
        if (r4 == 0) goto L_0x00f1;
    L_0x00e8:
        r4 = r7.f7586a;
        r5 = r0.getMessage();
        r4.m11835a(r5, r0);
    L_0x00f1:
        r4 = r7.f7586a;
        r4 = r4.m11844d();
        if (r4 == 0) goto L_0x000a;
    L_0x00f9:
        r4 = r7.f7586a;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "Retrying request to ";
        r5 = r5.append(r6);
        r5 = r5.append(r3);
        r5 = r5.toString();
        r4.m11843d(r5);
        goto L_0x000a;
    L_0x0113:
        r1 = r0 instanceof cz.msebera.android.httpclient.NoHttpResponseException;
        if (r1 == 0) goto L_0x013f;
    L_0x0117:
        r1 = new cz.msebera.android.httpclient.NoHttpResponseException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = r3.m11693a();
        r3 = r3.m11388e();
        r2 = r2.append(r3);
        r3 = " failed to respond";
        r2 = r2.append(r3);
        r2 = r2.toString();
        r1.<init>(r2);
        r0 = r0.getStackTrace();
        r1.setStackTrace(r0);
        throw r1;
    L_0x013f:
        throw r0;
    L_0x0140:
        r4 = move-exception;
        goto L_0x0094;
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.httpclient.impl.client.DefaultRequestDirector.b(cz.msebera.android.httpclient.impl.client.RoutedRequest, cz.msebera.android.httpclient.protocol.HttpContext):cz.msebera.android.httpclient.HttpResponse");
    }

    protected void m12120a() {
        try {
            this.f7602q.m11616i();
        } catch (Throwable e) {
            this.f7586a.m11835a("IOException releasing connection", e);
        }
        this.f7602q = null;
    }

    protected HttpRoute m12124b(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) {
        HttpRoutePlanner httpRoutePlanner = this.f7588c;
        if (httpHost == null) {
            httpHost = (HttpHost) httpRequest.m10622g().m12084a("http.default-host");
        }
        return httpRoutePlanner.m11701a(httpHost, httpRequest, httpContext);
    }

    protected void m12121a(HttpRoute httpRoute, HttpContext httpContext) {
        HttpRouteDirector basicRouteDirector = new BasicRouteDirector();
        int a;
        do {
            Object h = this.f7602q.m11659h();
            a = basicRouteDirector.m11680a(httpRoute, h);
            switch (a) {
                case -1:
                    throw new HttpException("Unable to establish route: planned = " + httpRoute + "; current = " + h);
                case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    this.f7602q.m11655a(httpRoute, httpContext, this.f7601p);
                    continue;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    boolean b = m12125b(httpRoute, httpContext);
                    this.f7586a.m11834a("Tunnel to target created.");
                    this.f7602q.m11658a(b, this.f7601p);
                    continue;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    int c = h.m11696c() - 1;
                    boolean a2 = m12123a(httpRoute, c, httpContext);
                    this.f7586a.m11834a("Tunnel to proxy created.");
                    this.f7602q.m11654a(httpRoute.m11694a(c), a2, this.f7601p);
                    continue;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    this.f7602q.m11656a(httpContext, this.f7601p);
                    continue;
                default:
                    throw new IllegalStateException("Unknown step indicator " + a + " from RouteDirector.");
            }
        } while (a > 0);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected boolean m12125b(cz.msebera.android.httpclient.conn.routing.HttpRoute r8, cz.msebera.android.httpclient.protocol.HttpContext r9) {
        /*
        r7 = this;
        r1 = r8.m11697d();
        r6 = r8.m11693a();
    L_0x0008:
        r0 = r7.f7602q;
        r0 = r0.m11375c();
        if (r0 != 0) goto L_0x0017;
    L_0x0010:
        r0 = r7.f7602q;
        r2 = r7.f7601p;
        r0.m11655a(r8, r9, r2);
    L_0x0017:
        r0 = r7.m12126c(r8, r9);
        r2 = r7.f7601p;
        r0.m10609a(r2);
        r2 = "http.target_host";
        r9.m11529a(r2, r6);
        r2 = "http.route";
        r9.m11529a(r2, r8);
        r2 = "http.proxy_host";
        r9.m11529a(r2, r1);
        r2 = "http.connection";
        r3 = r7.f7602q;
        r9.m11529a(r2, r3);
        r2 = "http.request";
        r9.m11529a(r2, r0);
        r2 = r7.f7591f;
        r3 = r7.f7592g;
        r2.m12706a(r0, r3, r9);
        r2 = r7.f7591f;
        r3 = r7.f7602q;
        r2 = r2.m12705a(r0, r3, r9);
        r0 = r7.f7601p;
        r2.m10609a(r0);
        r0 = r7.f7591f;
        r3 = r7.f7592g;
        r0.m12707a(r2, r3, r9);
        r0 = r2.m11391a();
        r0 = r0.m11410b();
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r0 >= r3) goto L_0x007f;
    L_0x0062:
        r0 = new cz.msebera.android.httpclient.HttpException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r3 = "Unexpected response to CONNECT request: ";
        r1 = r1.append(r3);
        r2 = r2.m11391a();
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x007f:
        r0 = r7.f7601p;
        r0 = cz.msebera.android.httpclient.client.params.HttpClientParams.m11526b(r0);
        if (r0 == 0) goto L_0x0008;
    L_0x0087:
        r0 = r7.f7605t;
        r3 = r7.f7599n;
        r4 = r7.f7604s;
        r5 = r9;
        r0 = r0.m11913a(r1, r2, r3, r4, r5);
        if (r0 == 0) goto L_0x00c0;
    L_0x0094:
        r0 = r7.f7605t;
        r3 = r7.f7599n;
        r4 = r7.f7604s;
        r5 = r9;
        r0 = r0.m12149c(r1, r2, r3, r4, r5);
        if (r0 == 0) goto L_0x00c0;
    L_0x00a1:
        r0 = r7.f7589d;
        r0 = r0.m11360a(r2, r9);
        if (r0 == 0) goto L_0x00b9;
    L_0x00a9:
        r0 = r7.f7586a;
        r3 = "Connection kept alive";
        r0.m11834a(r3);
        r0 = r2.m11393b();
        cz.msebera.android.httpclient.util.EntityUtils.m12764a(r0);
        goto L_0x0008;
    L_0x00b9:
        r0 = r7.f7602q;
        r0.close();
        goto L_0x0008;
    L_0x00c0:
        r0 = r2.m11391a();
        r0 = r0.m11410b();
        r1 = 299; // 0x12b float:4.19E-43 double:1.477E-321;
        if (r0 <= r1) goto L_0x00fc;
    L_0x00cc:
        r0 = r2.m11393b();
        if (r0 == 0) goto L_0x00da;
    L_0x00d2:
        r1 = new cz.msebera.android.httpclient.entity.BufferedHttpEntity;
        r1.<init>(r0);
        r2.m11392a(r1);
    L_0x00da:
        r0 = r7.f7602q;
        r0.close();
        r0 = new cz.msebera.android.httpclient.impl.client.TunnelRefusedException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r3 = "CONNECT refused by proxy: ";
        r1 = r1.append(r3);
        r3 = r2.m11391a();
        r1 = r1.append(r3);
        r1 = r1.toString();
        r0.<init>(r1, r2);
        throw r0;
    L_0x00fc:
        r0 = r7.f7602q;
        r0.m11660k();
        r0 = 0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.httpclient.impl.client.DefaultRequestDirector.b(cz.msebera.android.httpclient.conn.routing.HttpRoute, cz.msebera.android.httpclient.protocol.HttpContext):boolean");
    }

    protected boolean m12123a(HttpRoute httpRoute, int i, HttpContext httpContext) {
        throw new HttpException("Proxy chains are not supported.");
    }

    protected HttpRequest m12126c(HttpRoute httpRoute, HttpContext httpContext) {
        HttpHost a = httpRoute.m11693a();
        String a2 = a.m11384a();
        int b = a.m11385b();
        if (b < 0) {
            b = this.f7587b.m11633a().m11740a(a.m11386c()).m11725a();
        }
        StringBuilder stringBuilder = new StringBuilder(a2.length() + 6);
        stringBuilder.append(a2);
        stringBuilder.append(':');
        stringBuilder.append(Integer.toString(b));
        return new BasicHttpRequest("CONNECT", stringBuilder.toString(), HttpProtocolParams.m12682b(this.f7601p));
    }

    protected RoutedRequest m12119a(RoutedRequest routedRequest, HttpResponse httpResponse, HttpContext httpContext) {
        HttpRoute b = routedRequest.m12161b();
        Object a = routedRequest.m12160a();
        HttpParams g = a.m10636g();
        if (HttpClientParams.m11526b(g)) {
            HttpHost httpHost;
            HttpHost httpHost2 = (HttpHost) httpContext.m11528a("http.target_host");
            if (httpHost2 == null) {
                httpHost2 = b.m11693a();
            }
            if (httpHost2.m11385b() < 0) {
                httpHost = new HttpHost(httpHost2.m11384a(), this.f7587b.m11633a().m11738a(httpHost2).m11725a(), httpHost2.m11386c());
            } else {
                httpHost = httpHost2;
            }
            boolean a2 = this.f7605t.m11913a(httpHost, httpResponse, this.f7597l, this.f7603r, httpContext);
            HttpHost d = b.m11697d();
            if (d == null) {
                d = b.m11693a();
            }
            boolean a3 = this.f7605t.m11913a(d, httpResponse, this.f7599n, this.f7604s, httpContext);
            if (a2) {
                if (this.f7605t.m12149c(httpHost, httpResponse, this.f7597l, this.f7603r, httpContext)) {
                    return routedRequest;
                }
            }
            if (a3) {
                if (this.f7605t.m12149c(d, httpResponse, this.f7599n, this.f7604s, httpContext)) {
                    return routedRequest;
                }
            }
        }
        if (!HttpClientParams.m11525a(g) || !this.f7595j.m11472a(a, httpResponse, httpContext)) {
            return null;
        }
        if (this.f7607v >= this.f7608w) {
            throw new RedirectException("Maximum redirects (" + this.f7608w + ") exceeded");
        }
        this.f7607v++;
        this.f7609x = null;
        HttpRequest b2 = this.f7595j.m11473b(a, httpResponse, httpContext);
        b2.m10611a(a.m12141n().m10620e());
        URI k = b2.m10648k();
        HttpHost b3 = URIUtils.m11592b(k);
        if (b3 == null) {
            throw new ProtocolException("Redirect URI does not specify a valid host name: " + k);
        }
        if (!b.m11693a().equals(b3)) {
            this.f7586a.m11834a("Resetting target auth state");
            this.f7603r.m11430a();
            AuthScheme c = this.f7604s.m11437c();
            if (c != null && c.m11419c()) {
                this.f7586a.m11834a("Resetting proxy auth state");
                this.f7604s.m11430a();
            }
        }
        Object a4 = m12114a(b2);
        a4.m10624a(g);
        HttpRoute b4 = m12124b(b3, a4, httpContext);
        RoutedRequest routedRequest2 = new RoutedRequest(a4, b4);
        if (!this.f7586a.m11836a()) {
            return routedRequest2;
        }
        this.f7586a.m11834a("Redirecting to '" + k + "' via " + b4);
        return routedRequest2;
    }

    private void m12117b() {
        ManagedClientConnection managedClientConnection = this.f7602q;
        if (managedClientConnection != null) {
            this.f7602q = null;
            try {
                managedClientConnection.m11617j();
            } catch (Throwable e) {
                if (this.f7586a.m11836a()) {
                    this.f7586a.m11835a(e.getMessage(), e);
                }
            }
            try {
                managedClientConnection.m11616i();
            } catch (Throwable e2) {
                this.f7586a.m11835a("Error releasing connection", e2);
            }
        }
    }
}
