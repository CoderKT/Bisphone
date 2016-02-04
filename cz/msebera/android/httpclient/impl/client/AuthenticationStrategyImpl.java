package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.FormattedHeader;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.auth.AuthOption;
import cz.msebera.android.httpclient.auth.AuthScheme;
import cz.msebera.android.httpclient.auth.AuthSchemeProvider;
import cz.msebera.android.httpclient.auth.AuthScope;
import cz.msebera.android.httpclient.auth.Credentials;
import cz.msebera.android.httpclient.auth.MalformedChallengeException;
import cz.msebera.android.httpclient.client.AuthCache;
import cz.msebera.android.httpclient.client.AuthenticationStrategy;
import cz.msebera.android.httpclient.client.CredentialsProvider;
import cz.msebera.android.httpclient.client.config.RequestConfig;
import cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import cz.msebera.android.httpclient.config.Lookup;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.protocol.HTTP;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;

abstract class AuthenticationStrategyImpl implements AuthenticationStrategy {
    private static final List<String> f7563b;
    public HttpClientAndroidLog f7564a;
    private final int f7565c;
    private final String f7566d;

    abstract Collection<String> m12066a(RequestConfig requestConfig);

    static {
        f7563b = Collections.unmodifiableList(Arrays.asList(new String[]{"negotiate", "Kerberos", "NTLM", "Digest", "Basic"}));
    }

    AuthenticationStrategyImpl(int i, String str) {
        this.f7564a = new HttpClientAndroidLog(getClass());
        this.f7565c = i;
        this.f7566d = str;
    }

    public boolean m12069a(HttpHost httpHost, HttpResponse httpResponse, HttpContext httpContext) {
        Args.m12722a((Object) httpResponse, "HTTP response");
        return httpResponse.m11391a().m11410b() == this.f7565c;
    }

    public Map<String, Header> m12071b(HttpHost httpHost, HttpResponse httpResponse, HttpContext httpContext) {
        Args.m12722a((Object) httpResponse, "HTTP response");
        Header[] b = httpResponse.m10615b(this.f7566d);
        Map<String, Header> hashMap = new HashMap(b.length);
        for (Header header : b) {
            CharArrayBuffer a;
            int b2;
            if (header instanceof FormattedHeader) {
                a = ((FormattedHeader) header).m11364a();
                b2 = ((FormattedHeader) header).m11365b();
            } else {
                String d = header.m11362d();
                if (d == null) {
                    throw new MalformedChallengeException("Header value is null");
                }
                CharArrayBuffer charArrayBuffer = new CharArrayBuffer(d.length());
                charArrayBuffer.m12751a(d);
                a = charArrayBuffer;
                b2 = 0;
            }
            while (b2 < a.m12757c() && HTTP.m12703a(a.m12744a(b2))) {
                b2++;
            }
            int i = b2;
            while (i < a.m12757c() && !HTTP.m12703a(a.m12744a(i))) {
                i++;
            }
            hashMap.put(a.m12746a(b2, i).toLowerCase(Locale.ENGLISH), header);
        }
        return hashMap;
    }

    public Queue<AuthOption> m12067a(Map<String, Header> map, HttpHost httpHost, HttpResponse httpResponse, HttpContext httpContext) {
        Args.m12722a((Object) map, "Map of auth challenges");
        Args.m12722a((Object) httpHost, "Host");
        Args.m12722a((Object) httpResponse, "HTTP response");
        Args.m12722a((Object) httpContext, "HTTP context");
        HttpClientContext a = HttpClientContext.m11538a(httpContext);
        Queue<AuthOption> linkedList = new LinkedList();
        Lookup f = a.m11546f();
        if (f == null) {
            this.f7564a.m11834a("Auth scheme registry not set in the context");
            return linkedList;
        }
        CredentialsProvider g = a.m11547g();
        if (g == null) {
            this.f7564a.m11834a("Credentials provider not set in the context");
            return linkedList;
        }
        Collection a2 = m12066a(a.m11551k());
        if (a2 == null) {
            a2 = f7563b;
        }
        if (this.f7564a.m11836a()) {
            this.f7564a.m11834a("Authentication schemes in the order of preference: " + r0);
        }
        for (String str : r0) {
            Header header = (Header) map.get(str.toLowerCase(Locale.ENGLISH));
            if (header != null) {
                AuthSchemeProvider authSchemeProvider = (AuthSchemeProvider) f.m11424b(str);
                if (authSchemeProvider != null) {
                    AuthScheme a3 = authSchemeProvider.m11422a(httpContext);
                    a3.m11417a(header);
                    Credentials a4 = g.m11469a(new AuthScope(httpHost.m11384a(), httpHost.m11385b(), a3.m11418b(), a3.m11416a()));
                    if (a4 != null) {
                        linkedList.add(new AuthOption(a3, a4));
                    }
                } else if (this.f7564a.m11842c()) {
                    this.f7564a.m11840c("Authentication scheme " + str + " not supported");
                }
            } else if (this.f7564a.m11836a()) {
                this.f7564a.m11834a("Challenge for " + str + " authentication scheme not available");
            }
        }
        return linkedList;
    }

    public void m12068a(HttpHost httpHost, AuthScheme authScheme, HttpContext httpContext) {
        Args.m12722a((Object) httpHost, "Host");
        Args.m12722a((Object) authScheme, "Auth scheme");
        Args.m12722a((Object) httpContext, "HTTP context");
        HttpClientContext a = HttpClientContext.m11538a(httpContext);
        if (m12070a(authScheme)) {
            AuthCache h = a.m11548h();
            if (h == null) {
                h = new BasicAuthCache();
                a.m11541a(h);
            }
            if (this.f7564a.m11836a()) {
                this.f7564a.m11834a("Caching '" + authScheme.m11416a() + "' auth scheme for " + httpHost);
            }
            h.m11453a(httpHost, authScheme);
        }
    }

    protected boolean m12070a(AuthScheme authScheme) {
        if (authScheme == null || !authScheme.m11420d()) {
            return false;
        }
        String a = authScheme.m11416a();
        if (a.equalsIgnoreCase("Basic") || a.equalsIgnoreCase("Digest")) {
            return true;
        }
        return false;
    }

    public void m12072b(HttpHost httpHost, AuthScheme authScheme, HttpContext httpContext) {
        Args.m12722a((Object) httpHost, "Host");
        Args.m12722a((Object) httpContext, "HTTP context");
        AuthCache h = HttpClientContext.m11538a(httpContext).m11548h();
        if (h != null) {
            if (this.f7564a.m11836a()) {
                this.f7564a.m11834a("Clearing cached auth scheme for " + httpHost);
            }
            h.m11454b(httpHost);
        }
    }
}
