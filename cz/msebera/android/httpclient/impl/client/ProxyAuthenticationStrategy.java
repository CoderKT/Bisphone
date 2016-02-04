package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.config.RequestConfig;
import cz.msebera.android.httpclient.protocol.HttpContext;
import java.util.Collection;
import java.util.Map;
import java.util.Queue;

public class ProxyAuthenticationStrategy extends AuthenticationStrategyImpl {
    public static final ProxyAuthenticationStrategy f7622b;

    public /* bridge */ /* synthetic */ Queue m12151a(Map map, HttpHost httpHost, HttpResponse httpResponse, HttpContext httpContext) {
        return super.m12067a(map, httpHost, httpResponse, httpContext);
    }

    static {
        f7622b = new ProxyAuthenticationStrategy();
    }

    public ProxyAuthenticationStrategy() {
        super(407, "Proxy-Authenticate");
    }

    Collection<String> m12150a(RequestConfig requestConfig) {
        return requestConfig.m11497e();
    }
}
