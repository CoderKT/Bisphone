package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.config.RequestConfig;
import cz.msebera.android.httpclient.protocol.HttpContext;
import java.util.Collection;
import java.util.Map;
import java.util.Queue;

public class TargetAuthenticationStrategy extends AuthenticationStrategyImpl {
    public static final TargetAuthenticationStrategy f7627b;

    public /* bridge */ /* synthetic */ Queue m12163a(Map map, HttpHost httpHost, HttpResponse httpResponse, HttpContext httpContext) {
        return super.m12067a(map, httpHost, httpResponse, httpContext);
    }

    static {
        f7627b = new TargetAuthenticationStrategy();
    }

    public TargetAuthenticationStrategy() {
        super(401, "WWW-Authenticate");
    }

    Collection<String> m12162a(RequestConfig requestConfig) {
        return requestConfig.m11496d();
    }
}
