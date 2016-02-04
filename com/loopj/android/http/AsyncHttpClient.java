package com.loopj.android.http;

import android.content.Context;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpRequestInterceptor;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpResponseInterceptor;
import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.auth.AuthScope;
import cz.msebera.android.httpclient.auth.AuthState;
import cz.msebera.android.httpclient.auth.Credentials;
import cz.msebera.android.httpclient.client.CredentialsProvider;
import cz.msebera.android.httpclient.client.methods.HttpEntityEnclosingRequestBase;
import cz.msebera.android.httpclient.client.methods.HttpHead;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.conn.ClientConnectionManager;
import cz.msebera.android.httpclient.conn.params.ConnManagerParams;
import cz.msebera.android.httpclient.conn.params.ConnPerRouteBean;
import cz.msebera.android.httpclient.conn.scheme.PlainSocketFactory;
import cz.msebera.android.httpclient.conn.scheme.Scheme;
import cz.msebera.android.httpclient.conn.scheme.SchemeRegistry;
import cz.msebera.android.httpclient.conn.scheme.SocketFactory;
import cz.msebera.android.httpclient.conn.ssl.SSLSocketFactory;
import cz.msebera.android.httpclient.entity.HttpEntityWrapper;
import cz.msebera.android.httpclient.impl.auth.BasicScheme;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.impl.conn.tsccm.ThreadSafeClientConnManager;
import cz.msebera.android.httpclient.params.BasicHttpParams;
import cz.msebera.android.httpclient.params.HttpConnectionParams;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.params.HttpProtocolParams;
import cz.msebera.android.httpclient.protocol.BasicHttpContext;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.protocol.SyncBasicHttpContext;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPInputStream;
import org.jivesoftware.smack.util.StringUtils;

public class AsyncHttpClient {
    public static LogInterface f6518a;
    private final DefaultHttpClient f6519b;
    private final HttpContext f6520c;
    private final Map<Context, List<RequestHandle>> f6521d;
    private final Map<String, String> f6522e;
    private int f6523f;
    private int f6524g;
    private int f6525h;
    private ExecutorService f6526i;
    private boolean f6527j;

    /* renamed from: com.loopj.android.http.AsyncHttpClient.1 */
    class C08851 implements HttpRequestInterceptor {
        final /* synthetic */ AsyncHttpClient f6511a;

        C08851(AsyncHttpClient asyncHttpClient) {
            this.f6511a = asyncHttpClient;
        }

        public void m10536a(HttpRequest httpRequest, HttpContext httpContext) {
            if (!httpRequest.m10612a("Accept-Encoding")) {
                httpRequest.m10610a("Accept-Encoding", "gzip");
            }
            for (String str : this.f6511a.f6522e.keySet()) {
                if (httpRequest.m10612a(str)) {
                    AsyncHttpClient.f6518a.m10681b("AsyncHttpClient", String.format("Headers were overwritten! (%s | %s) overwrites (%s | %s)", new Object[]{str, this.f6511a.f6522e.get(str), r1.m11361c(), httpRequest.m10616c(str).m11362d()}));
                    httpRequest.m10613b(r1);
                }
                httpRequest.m10610a(str, (String) this.f6511a.f6522e.get(str));
            }
        }
    }

    /* renamed from: com.loopj.android.http.AsyncHttpClient.2 */
    class C08862 implements HttpResponseInterceptor {
        final /* synthetic */ AsyncHttpClient f6512a;

        C08862(AsyncHttpClient asyncHttpClient) {
            this.f6512a = asyncHttpClient;
        }

        public void m10538a(HttpResponse httpResponse, HttpContext httpContext) {
            HttpEntity b = httpResponse.m11393b();
            if (b != null) {
                Header g = b.m10547g();
                if (g != null) {
                    for (HeaderElement a : g.m11363e()) {
                        if (a.m11368a().equalsIgnoreCase("gzip")) {
                            httpResponse.m11392a(new InflatingEntity(b));
                            return;
                        }
                    }
                }
            }
        }
    }

    /* renamed from: com.loopj.android.http.AsyncHttpClient.3 */
    class C08873 implements HttpRequestInterceptor {
        final /* synthetic */ AsyncHttpClient f6513a;

        C08873(AsyncHttpClient asyncHttpClient) {
            this.f6513a = asyncHttpClient;
        }

        public void m10539a(HttpRequest httpRequest, HttpContext httpContext) {
            AuthState authState = (AuthState) httpContext.m11528a("http.auth.target-scope");
            CredentialsProvider credentialsProvider = (CredentialsProvider) httpContext.m11528a("http.auth.credentials-provider");
            HttpHost httpHost = (HttpHost) httpContext.m11528a("http.target_host");
            if (authState.m11437c() == null) {
                Credentials a = credentialsProvider.m11469a(new AuthScope(httpHost.m11384a(), httpHost.m11385b()));
                if (a != null) {
                    authState.m11432a(new BasicScheme());
                    authState.m11434a(a);
                }
            }
        }
    }

    class InflatingEntity extends HttpEntityWrapper {
        InputStream f6515a;
        PushbackInputStream f6516b;
        GZIPInputStream f6517c;

        public InflatingEntity(HttpEntity httpEntity) {
            super(httpEntity);
        }

        public InputStream m10558a() {
            this.f6515a = this.d.m10540a();
            this.f6516b = new PushbackInputStream(this.f6515a, 2);
            if (!AsyncHttpClient.m10569a(this.f6516b)) {
                return this.f6516b;
            }
            this.f6517c = new GZIPInputStream(this.f6516b);
            return this.f6517c;
        }

        public long m10559b() {
            return this.d == null ? 0 : this.d.m10542b();
        }

        public void m10560c() {
            AsyncHttpClient.m10567a(this.f6515a);
            AsyncHttpClient.m10567a(this.f6516b);
            AsyncHttpClient.m10567a(this.f6517c);
            super.m10552c();
        }
    }

    static {
        f6518a = new LogHandler();
    }

    public AsyncHttpClient() {
        this(false, 80, 443);
    }

    public AsyncHttpClient(boolean z, int i, int i2) {
        this(m10563a(z, i, i2));
    }

    public AsyncHttpClient(SchemeRegistry schemeRegistry) {
        boolean z = true;
        this.f6523f = 10;
        this.f6524g = 10000;
        this.f6525h = 10000;
        this.f6527j = true;
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        ConnManagerParams.m11672a((HttpParams) basicHttpParams, (long) this.f6524g);
        ConnManagerParams.m11673a((HttpParams) basicHttpParams, new ConnPerRouteBean(this.f6523f));
        ConnManagerParams.m11671a((HttpParams) basicHttpParams, 10);
        HttpConnectionParams.m12669a((HttpParams) basicHttpParams, this.f6525h);
        HttpConnectionParams.m12673c(basicHttpParams, this.f6524g);
        HttpConnectionParams.m12670a((HttpParams) basicHttpParams, true);
        HttpConnectionParams.m12671b(basicHttpParams, 8192);
        HttpProtocolParams.m12680a((HttpParams) basicHttpParams, HttpVersion.f7210c);
        ClientConnectionManager a = m10577a(schemeRegistry, basicHttpParams);
        if (a == null) {
            z = false;
        }
        Utils.m10778a(z, "Custom implementation of #createConnectionManager(SchemeRegistry, BasicHttpParams) returned null");
        this.f6526i = m10579a();
        this.f6521d = Collections.synchronizedMap(new WeakHashMap());
        this.f6522e = new HashMap();
        this.f6520c = new SyncBasicHttpContext(new BasicHttpContext());
        this.f6519b = new DefaultHttpClient(a, basicHttpParams);
        this.f6519b.m12030a(new C08851(this));
        this.f6519b.m12032a(new C08862(this));
        this.f6519b.m12031a(new C08873(this), 0);
        this.f6519b.m12033a(new RetryHandler(5, 1500));
    }

    private static SchemeRegistry m10563a(boolean z, int i, int i2) {
        SocketFactory b;
        if (z) {
            f6518a.m10681b("AsyncHttpClient", "Beware! Using the fix is insecure, as it doesn't verify SSL certificates.");
        }
        if (i < 1) {
            i = 80;
            f6518a.m10681b("AsyncHttpClient", "Invalid HTTP port number specified, defaulting to 80");
        }
        if (i2 < 1) {
            i2 = 443;
            f6518a.m10681b("AsyncHttpClient", "Invalid HTTPS port number specified, defaulting to 443");
        }
        if (z) {
            b = MySSLSocketFactory.m10724b();
        } else {
            b = SSLSocketFactory.m10709d();
        }
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.m11739a(new Scheme("http", PlainSocketFactory.m11719a(), i));
        schemeRegistry.m11739a(new Scheme("https", b, i2));
        return schemeRegistry;
    }

    public static String m10564a(boolean z, String str, RequestParams requestParams) {
        if (str == null) {
            return null;
        }
        String toASCIIString;
        String trim;
        if (z) {
            try {
                URL url = new URL(URLDecoder.decode(str, StringUtils.UTF8));
                toASCIIString = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef()).toASCIIString();
            } catch (Throwable e) {
                f6518a.m10682b("AsyncHttpClient", "getUrlWithQueryString encoding URL", e);
            }
            if (requestParams != null) {
                return toASCIIString;
            }
            trim = requestParams.m10744b().trim();
            if (trim.equals("") && !trim.equals("?")) {
                return (toASCIIString + (toASCIIString.contains("?") ? "&" : "?")) + trim;
            }
        }
        toASCIIString = str;
        if (requestParams != null) {
            return toASCIIString;
        }
        trim = requestParams.m10744b().trim();
        return trim.equals("") ? toASCIIString : toASCIIString;
    }

    public static boolean m10569a(PushbackInputStream pushbackInputStream) {
        boolean z = true;
        if (pushbackInputStream == null) {
            return false;
        }
        byte[] bArr = new byte[2];
        int i = 0;
        while (i < 2) {
            try {
                int read = pushbackInputStream.read(bArr, i, 2 - i);
                if (read < 0) {
                    return false;
                }
                i += read;
            } finally {
                pushbackInputStream.unread(bArr, 0, i);
            }
        }
        pushbackInputStream.unread(bArr, 0, i);
        if (35615 != ((bArr[0] & 255) | ((bArr[1] << 8) & 65280))) {
            z = false;
        }
        return z;
    }

    public static void m10567a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Throwable e) {
                f6518a.m10680a("AsyncHttpClient", "Cannot close input stream", e);
            }
        }
    }

    public static void m10568a(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (Throwable e) {
                f6518a.m10680a("AsyncHttpClient", "Cannot close output stream", e);
            }
        }
    }

    public static void m10566a(HttpEntity httpEntity) {
        if (httpEntity instanceof HttpEntityWrapper) {
            try {
                for (Field field : HttpEntityWrapper.class.getDeclaredFields()) {
                    if (field.getName().equals("wrappedEntity")) {
                        break;
                    }
                }
                Field field2 = null;
                if (field2 != null) {
                    field2.setAccessible(true);
                    HttpEntity httpEntity2 = (HttpEntity) field2.get(httpEntity);
                    if (httpEntity2 != null) {
                        httpEntity2.m10543c();
                    }
                }
            } catch (Throwable th) {
                f6518a.m10682b("AsyncHttpClient", "wrappedEntity consume", th);
            }
        }
    }

    public void m10582a(ExecutorService executorService) {
        this.f6526i = executorService;
    }

    protected ExecutorService m10579a() {
        return Executors.newCachedThreadPool();
    }

    protected ClientConnectionManager m10577a(SchemeRegistry schemeRegistry, BasicHttpParams basicHttpParams) {
        return new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry);
    }

    public void m10580a(int i) {
        if (i < 1000) {
            i = 10000;
        }
        m10586b(i);
        m10588c(i);
    }

    public void m10586b(int i) {
        if (i < 1000) {
            i = 10000;
        }
        this.f6524g = i;
        HttpParams q = this.f6519b.m12049q();
        ConnManagerParams.m11672a(q, (long) this.f6524g);
        HttpConnectionParams.m12673c(q, this.f6524g);
    }

    public void m10588c(int i) {
        if (i < 1000) {
            i = 10000;
        }
        this.f6525h = i;
        HttpConnectionParams.m12669a(this.f6519b.m12049q(), this.f6525h);
    }

    public void m10581a(Object obj, boolean z) {
        if (obj == null) {
            f6518a.m10681b("AsyncHttpClient", "cancelRequestsByTAG, passed TAG is null, cannot proceed");
            return;
        }
        for (List<RequestHandle> list : this.f6521d.values()) {
            if (list != null) {
                for (RequestHandle requestHandle : list) {
                    if (obj.equals(requestHandle.m10733d())) {
                        requestHandle.m10730a(z);
                    }
                }
            }
        }
    }

    public RequestHandle m10573a(Context context, String str, Header[] headerArr, RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        HttpUriRequest httpHead = new HttpHead(m10564a(this.f6527j, str, requestParams));
        if (headerArr != null) {
            httpHead.m10611a(headerArr);
        }
        return m10585b(this.f6519b, this.f6520c, httpHead, null, responseHandlerInterface, context);
    }

    public RequestHandle m10576a(String str, ResponseHandlerInterface responseHandlerInterface) {
        return m10571a(null, str, null, responseHandlerInterface);
    }

    public RequestHandle m10571a(Context context, String str, RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        return m10585b(this.f6519b, this.f6520c, new HttpGet(m10564a(this.f6527j, str, requestParams)), null, responseHandlerInterface, context);
    }

    public RequestHandle m10584b(Context context, String str, Header[] headerArr, RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        HttpUriRequest httpGet = new HttpGet(m10564a(this.f6527j, str, requestParams));
        if (headerArr != null) {
            httpGet.m10611a(headerArr);
        }
        return m10585b(this.f6519b, this.f6520c, httpGet, null, responseHandlerInterface, context);
    }

    public RequestHandle m10575a(String str, RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        return m10583b(null, str, requestParams, responseHandlerInterface);
    }

    public RequestHandle m10583b(Context context, String str, RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        return m10572a(context, str, m10561a(requestParams, responseHandlerInterface), null, responseHandlerInterface);
    }

    public RequestHandle m10572a(Context context, String str, HttpEntity httpEntity, String str2, ResponseHandlerInterface responseHandlerInterface) {
        return m10585b(this.f6519b, this.f6520c, m10562a(new HttpPost(m10578a(str)), httpEntity), str2, responseHandlerInterface, context);
    }

    public RequestHandle m10574a(Context context, String str, Header[] headerArr, RequestParams requestParams, String str2, ResponseHandlerInterface responseHandlerInterface) {
        HttpUriRequest httpPost = new HttpPost(m10578a(str));
        if (requestParams != null) {
            httpPost.m10658a(m10561a(requestParams, responseHandlerInterface));
        }
        if (headerArr != null) {
            httpPost.m10626a(headerArr);
        }
        return m10585b(this.f6519b, this.f6520c, httpPost, str2, responseHandlerInterface, context);
    }

    public RequestHandle m10587c(Context context, String str, Header[] headerArr, RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        HttpUriRequest httpDelete = new HttpDelete(m10564a(this.f6527j, str, requestParams));
        if (headerArr != null) {
            httpDelete.m10626a(headerArr);
        }
        return m10585b(this.f6519b, this.f6520c, httpDelete, null, responseHandlerInterface, context);
    }

    protected AsyncHttpRequest m10570a(DefaultHttpClient defaultHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, String str, ResponseHandlerInterface responseHandlerInterface, Context context) {
        return new AsyncHttpRequest(defaultHttpClient, httpContext, httpUriRequest, responseHandlerInterface);
    }

    protected RequestHandle m10585b(DefaultHttpClient defaultHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, String str, ResponseHandlerInterface responseHandlerInterface, Context context) {
        if (httpUriRequest == null) {
            throw new IllegalArgumentException("HttpUriRequest must not be null");
        } else if (responseHandlerInterface == null) {
            throw new IllegalArgumentException("ResponseHandler must not be null");
        } else if (!responseHandlerInterface.m5466f() || responseHandlerInterface.m5467g()) {
            if (str != null) {
                if ((httpUriRequest instanceof HttpEntityEnclosingRequestBase) && ((HttpEntityEnclosingRequestBase) httpUriRequest).m10660c() != null && httpUriRequest.m10612a("Content-Type")) {
                    f6518a.m10684d("AsyncHttpClient", "Passed contentType will be ignored because HttpEntity sets content type");
                } else {
                    httpUriRequest.m10614b("Content-Type", str);
                }
            }
            responseHandlerInterface.m5460a(httpUriRequest.m10620e());
            responseHandlerInterface.m5458a(httpUriRequest.m10648k());
            Object a = m10570a(defaultHttpClient, httpContext, httpUriRequest, str, responseHandlerInterface, context);
            this.f6526i.submit(a);
            RequestHandle requestHandle = new RequestHandle(a);
            if (context != null) {
                List list;
                synchronized (this.f6521d) {
                    list = (List) this.f6521d.get(context);
                    if (list == null) {
                        list = Collections.synchronizedList(new LinkedList());
                        this.f6521d.put(context, list);
                    }
                }
                list.add(requestHandle);
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    if (((RequestHandle) it.next()).m10732c()) {
                        it.remove();
                    }
                }
            }
            return requestHandle;
        } else {
            throw new IllegalArgumentException("Synchronous ResponseHandler used in AsyncHttpClient. You should create your response handler in a looper thread or use SyncHttpClient instead.");
        }
    }

    protected URI m10578a(String str) {
        return URI.create(str).normalize();
    }

    private HttpEntity m10561a(RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        HttpEntity httpEntity = null;
        if (requestParams != null) {
            try {
                httpEntity = requestParams.m10739a(responseHandlerInterface);
            } catch (Throwable e) {
                if (responseHandlerInterface != null) {
                    responseHandlerInterface.m5462b(0, httpEntity, httpEntity, e);
                } else {
                    e.printStackTrace();
                }
            }
        }
        return httpEntity;
    }

    private HttpEntityEnclosingRequestBase m10562a(HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase, HttpEntity httpEntity) {
        if (httpEntity != null) {
            httpEntityEnclosingRequestBase.m10658a(httpEntity);
        }
        return httpEntityEnclosingRequestBase;
    }
}
