package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.conn.ClientConnectionOperator;
import cz.msebera.android.httpclient.conn.DnsResolver;
import cz.msebera.android.httpclient.conn.OperatedClientConnection;
import cz.msebera.android.httpclient.conn.scheme.Scheme;
import cz.msebera.android.httpclient.conn.scheme.SchemeLayeredSocketFactory;
import cz.msebera.android.httpclient.conn.scheme.SchemeRegistry;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.params.HttpConnectionParams;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import java.net.InetAddress;
import java.net.Socket;

@Deprecated
public class DefaultClientConnectionOperator implements ClientConnectionOperator {
    public HttpClientAndroidLog f7658a;
    protected final SchemeRegistry f7659b;
    protected final DnsResolver f7660c;

    public DefaultClientConnectionOperator(SchemeRegistry schemeRegistry) {
        this.f7658a = new HttpClientAndroidLog(getClass());
        Args.m12722a((Object) schemeRegistry, "Scheme registry");
        this.f7659b = schemeRegistry;
        this.f7660c = new SystemDefaultDnsResolver();
    }

    public OperatedClientConnection m12236a() {
        return new DefaultClientConnection();
    }

    private SchemeRegistry m12235a(HttpContext httpContext) {
        SchemeRegistry schemeRegistry = (SchemeRegistry) httpContext.m11528a("http.scheme-registry");
        if (schemeRegistry == null) {
            return this.f7659b;
        }
        return schemeRegistry;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m12238a(cz.msebera.android.httpclient.conn.OperatedClientConnection r14, cz.msebera.android.httpclient.HttpHost r15, java.net.InetAddress r16, cz.msebera.android.httpclient.protocol.HttpContext r17, cz.msebera.android.httpclient.params.HttpParams r18) {
        /*
        r13 = this;
        r2 = "Connection";
        cz.msebera.android.httpclient.util.Args.m12722a(r14, r2);
        r2 = "Target host";
        cz.msebera.android.httpclient.util.Args.m12722a(r15, r2);
        r2 = "HTTP parameters";
        r0 = r18;
        cz.msebera.android.httpclient.util.Args.m12722a(r0, r2);
        r2 = r14.m11375c();
        if (r2 != 0) goto L_0x00a0;
    L_0x0017:
        r2 = 1;
    L_0x0018:
        r3 = "Connection must not be open";
        cz.msebera.android.httpclient.util.Asserts.m12729a(r2, r3);
        r0 = r17;
        r2 = r13.m12235a(r0);
        r3 = r15.m11386c();
        r2 = r2.m11740a(r3);
        r6 = r2.m11727b();
        r3 = r15.m11384a();
        r7 = r13.m12240a(r3);
        r3 = r15.m11385b();
        r8 = r2.m11726a(r3);
        r2 = 0;
    L_0x0040:
        r3 = r7.length;
        if (r2 >= r3) goto L_0x009f;
    L_0x0043:
        r4 = r7[r2];
        r3 = r7.length;
        r3 = r3 + -1;
        if (r2 != r3) goto L_0x00a3;
    L_0x004a:
        r3 = 1;
    L_0x004b:
        r0 = r18;
        r5 = r6.m10698a(r0);
        r14.m11662a(r5, r15);
        r9 = new cz.msebera.android.httpclient.conn.HttpInetSocketAddress;
        r9.<init>(r15, r4, r8);
        r4 = 0;
        if (r16 == 0) goto L_0x0064;
    L_0x005c:
        r4 = new java.net.InetSocketAddress;
        r10 = 0;
        r0 = r16;
        r4.<init>(r0, r10);
    L_0x0064:
        r10 = r13.f7658a;
        r10 = r10.m11836a();
        if (r10 == 0) goto L_0x0084;
    L_0x006c:
        r10 = r13.f7658a;
        r11 = new java.lang.StringBuilder;
        r11.<init>();
        r12 = "Connecting to ";
        r11 = r11.append(r12);
        r11 = r11.append(r9);
        r11 = r11.toString();
        r10.m11834a(r11);
    L_0x0084:
        r0 = r18;
        r4 = r6.m10699a(r5, r9, r4, r0);	 Catch:{ ConnectException -> 0x00a5, ConnectTimeoutException -> 0x00a9 }
        if (r5 == r4) goto L_0x00dd;
    L_0x008c:
        r14.m11662a(r4, r15);	 Catch:{ ConnectException -> 0x00a5, ConnectTimeoutException -> 0x00a9 }
    L_0x008f:
        r0 = r17;
        r1 = r18;
        r13.m12239a(r4, r0, r1);	 Catch:{ ConnectException -> 0x00a5, ConnectTimeoutException -> 0x00a9 }
        r4 = r6.m10700a(r4);	 Catch:{ ConnectException -> 0x00a5, ConnectTimeoutException -> 0x00a9 }
        r0 = r18;
        r14.m11664a(r4, r0);	 Catch:{ ConnectException -> 0x00a5, ConnectTimeoutException -> 0x00a9 }
    L_0x009f:
        return;
    L_0x00a0:
        r2 = 0;
        goto L_0x0018;
    L_0x00a3:
        r3 = 0;
        goto L_0x004b;
    L_0x00a5:
        r4 = move-exception;
        if (r3 == 0) goto L_0x00ad;
    L_0x00a8:
        throw r4;
    L_0x00a9:
        r4 = move-exception;
        if (r3 == 0) goto L_0x00ad;
    L_0x00ac:
        throw r4;
    L_0x00ad:
        r3 = r13.f7658a;
        r3 = r3.m11836a();
        if (r3 == 0) goto L_0x00d9;
    L_0x00b5:
        r3 = r13.f7658a;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "Connect to ";
        r4 = r4.append(r5);
        r4 = r4.append(r9);
        r5 = " timed out. ";
        r4 = r4.append(r5);
        r5 = "Connection will be retried using another IP address";
        r4 = r4.append(r5);
        r4 = r4.toString();
        r3.m11834a(r4);
    L_0x00d9:
        r2 = r2 + 1;
        goto L_0x0040;
    L_0x00dd:
        r4 = r5;
        goto L_0x008f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.httpclient.impl.conn.DefaultClientConnectionOperator.a(cz.msebera.android.httpclient.conn.OperatedClientConnection, cz.msebera.android.httpclient.HttpHost, java.net.InetAddress, cz.msebera.android.httpclient.protocol.HttpContext, cz.msebera.android.httpclient.params.HttpParams):void");
    }

    public void m12237a(OperatedClientConnection operatedClientConnection, HttpHost httpHost, HttpContext httpContext, HttpParams httpParams) {
        Args.m12722a((Object) operatedClientConnection, "Connection");
        Args.m12722a((Object) httpHost, "Target host");
        Args.m12722a((Object) httpParams, "Parameters");
        Asserts.m12729a(operatedClientConnection.m11375c(), "Connection must be open");
        Scheme a = m12235a(httpContext).m11740a(httpHost.m11386c());
        Asserts.m12729a(a.m11727b() instanceof SchemeLayeredSocketFactory, "Socket factory must implement SchemeLayeredSocketFactory");
        SchemeLayeredSocketFactory schemeLayeredSocketFactory = (SchemeLayeredSocketFactory) a.m11727b();
        Socket a2 = schemeLayeredSocketFactory.m10706a(operatedClientConnection.m11666i(), httpHost.m11384a(), a.m11726a(httpHost.m11385b()), httpParams);
        m12239a(a2, httpContext, httpParams);
        operatedClientConnection.m11663a(a2, httpHost, schemeLayeredSocketFactory.m10700a(a2), httpParams);
    }

    protected void m12239a(Socket socket, HttpContext httpContext, HttpParams httpParams) {
        socket.setTcpNoDelay(HttpConnectionParams.m12674c(httpParams));
        socket.setSoTimeout(HttpConnectionParams.m12668a(httpParams));
        int d = HttpConnectionParams.m12675d(httpParams);
        if (d >= 0) {
            socket.setSoLinger(d > 0, d);
        }
    }

    protected InetAddress[] m12240a(String str) {
        return this.f7660c.m11643a(str);
    }
}
