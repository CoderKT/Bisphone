package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.client.protocol.RequestAddCookies;
import cz.msebera.android.httpclient.client.protocol.RequestAuthCache;
import cz.msebera.android.httpclient.client.protocol.RequestClientConnControl;
import cz.msebera.android.httpclient.client.protocol.RequestDefaultHeaders;
import cz.msebera.android.httpclient.client.protocol.RequestProxyAuthentication;
import cz.msebera.android.httpclient.client.protocol.RequestTargetAuthentication;
import cz.msebera.android.httpclient.client.protocol.ResponseProcessCookies;
import cz.msebera.android.httpclient.conn.ClientConnectionManager;
import cz.msebera.android.httpclient.params.HttpConnectionParams;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.params.HttpProtocolParams;
import cz.msebera.android.httpclient.params.SyncBasicHttpParams;
import cz.msebera.android.httpclient.protocol.BasicHttpProcessor;
import cz.msebera.android.httpclient.protocol.HTTP;
import cz.msebera.android.httpclient.protocol.RequestContent;
import cz.msebera.android.httpclient.protocol.RequestExpectContinue;
import cz.msebera.android.httpclient.protocol.RequestTargetHost;
import cz.msebera.android.httpclient.protocol.RequestUserAgent;

@Deprecated
public class DefaultHttpClient extends AbstractHttpClient {
    public DefaultHttpClient(ClientConnectionManager clientConnectionManager, HttpParams httpParams) {
        super(clientConnectionManager, httpParams);
    }

    public DefaultHttpClient() {
        super(null, null);
    }

    protected HttpParams m12101a() {
        HttpParams syncBasicHttpParams = new SyncBasicHttpParams();
        m12100a(syncBasicHttpParams);
        return syncBasicHttpParams;
    }

    public static void m12100a(HttpParams httpParams) {
        HttpProtocolParams.m12680a(httpParams, HttpVersion.f7210c);
        HttpProtocolParams.m12681a(httpParams, HTTP.f7912a.name());
        HttpConnectionParams.m12670a(httpParams, true);
        HttpConnectionParams.m12671b(httpParams, 8192);
        HttpProtocolParams.m12683b(httpParams, HttpClientBuilder.f7619a);
    }

    protected BasicHttpProcessor m12102b() {
        BasicHttpProcessor basicHttpProcessor = new BasicHttpProcessor();
        basicHttpProcessor.m12698b(new RequestDefaultHeaders());
        basicHttpProcessor.m12698b(new RequestContent());
        basicHttpProcessor.m12698b(new RequestTargetHost());
        basicHttpProcessor.m12698b(new RequestClientConnControl());
        basicHttpProcessor.m12698b(new RequestUserAgent());
        basicHttpProcessor.m12698b(new RequestExpectContinue());
        basicHttpProcessor.m12698b(new RequestAddCookies());
        basicHttpProcessor.m12700b(new ResponseProcessCookies());
        basicHttpProcessor.m12698b(new RequestAuthCache());
        basicHttpProcessor.m12698b(new RequestTargetAuthentication());
        basicHttpProcessor.m12698b(new RequestProxyAuthentication());
        return basicHttpProcessor;
    }
}
