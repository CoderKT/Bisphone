package com.loopj.android.http;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.impl.client.AbstractHttpClient;
import cz.msebera.android.httpclient.protocol.HttpContext;
import java.net.MalformedURLException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.jivesoftware.smackx.receipts.DeliveryReceiptRequest;

public class AsyncHttpRequest implements Runnable {
    private final AbstractHttpClient f6528a;
    private final HttpContext f6529b;
    private final HttpUriRequest f6530c;
    private final ResponseHandlerInterface f6531d;
    private final AtomicBoolean f6532e;
    private int f6533f;
    private boolean f6534g;
    private volatile boolean f6535h;
    private boolean f6536i;

    public AsyncHttpRequest(AbstractHttpClient abstractHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, ResponseHandlerInterface responseHandlerInterface) {
        this.f6532e = new AtomicBoolean();
        this.f6528a = (AbstractHttpClient) Utils.m10777a((Object) abstractHttpClient, "client");
        this.f6529b = (HttpContext) Utils.m10777a((Object) httpContext, "context");
        this.f6530c = (HttpUriRequest) Utils.m10777a((Object) httpUriRequest, DeliveryReceiptRequest.ELEMENT);
        this.f6531d = (ResponseHandlerInterface) Utils.m10777a((Object) responseHandlerInterface, "responseHandler");
    }

    public void m10593a(AsyncHttpRequest asyncHttpRequest) {
    }

    public void m10596b(AsyncHttpRequest asyncHttpRequest) {
    }

    public void run() {
        if (!m10594a()) {
            if (!this.f6536i) {
                this.f6536i = true;
                m10593a(this);
            }
            if (!m10594a()) {
                this.f6531d.m5468i();
                if (!m10594a()) {
                    try {
                        m10590e();
                    } catch (Throwable e) {
                        if (m10594a()) {
                            AsyncHttpClient.f6518a.m10682b("AsyncHttpRequest", "makeRequestWithRetries returned error", e);
                        } else {
                            this.f6531d.m5462b(0, null, null, e);
                        }
                    }
                    if (!m10594a()) {
                        this.f6531d.m5469j();
                        if (!m10594a()) {
                            m10596b(this);
                            this.f6535h = true;
                        }
                    }
                }
            }
        }
    }

    private void m10589d() {
        if (!m10594a()) {
            if (this.f6530c.m10648k().getScheme() == null) {
                throw new MalformedURLException("No valid URI scheme was provided");
            }
            if (this.f6531d instanceof RangeFileAsyncHttpResponseHandler) {
                ((RangeFileAsyncHttpResponseHandler) this.f6531d).m10727a(this.f6530c);
            }
            HttpResponse a = this.f6528a.m12016a(this.f6530c, this.f6529b);
            if (!m10594a()) {
                this.f6531d.m5455a(this.f6531d, a);
                if (!m10594a()) {
                    this.f6531d.m5456a(a);
                    if (!m10594a()) {
                        this.f6531d.m5464b(this.f6531d, a);
                    }
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m10590e() {
        /*
        r7 = this;
        r1 = 1;
        r0 = 0;
        r2 = r7.f6528a;
        r3 = r2.m12058z();
        r2 = r1;
    L_0x0009:
        if (r2 == 0) goto L_0x0073;
    L_0x000b:
        r7.m10589d();	 Catch:{ UnknownHostException -> 0x000f, NullPointerException -> 0x0076, IOException -> 0x00a0 }
    L_0x000e:
        return;
    L_0x000f:
        r0 = move-exception;
        r2 = new java.io.IOException;	 Catch:{ Exception -> 0x004c }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x004c }
        r4.<init>();	 Catch:{ Exception -> 0x004c }
        r5 = "UnknownHostException exception: ";
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x004c }
        r5 = r0.getMessage();	 Catch:{ Exception -> 0x004c }
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x004c }
        r4 = r4.toString();	 Catch:{ Exception -> 0x004c }
        r2.<init>(r4);	 Catch:{ Exception -> 0x004c }
        r4 = r7.f6533f;	 Catch:{ Exception -> 0x004c }
        if (r4 <= 0) goto L_0x0074;
    L_0x0030:
        r4 = r7.f6533f;	 Catch:{ Exception -> 0x004c }
        r4 = r4 + 1;
        r7.f6533f = r4;	 Catch:{ Exception -> 0x004c }
        r5 = r7.f6529b;	 Catch:{ Exception -> 0x004c }
        r0 = r3.m10745a(r0, r4, r5);	 Catch:{ Exception -> 0x004c }
        if (r0 == 0) goto L_0x0074;
    L_0x003e:
        r0 = r1;
    L_0x003f:
        r6 = r2;
        r2 = r0;
        r0 = r6;
    L_0x0042:
        if (r2 == 0) goto L_0x0009;
    L_0x0044:
        r4 = r7.f6531d;	 Catch:{ Exception -> 0x004c }
        r5 = r7.f6533f;	 Catch:{ Exception -> 0x004c }
        r4.m5461b(r5);	 Catch:{ Exception -> 0x004c }
        goto L_0x0009;
    L_0x004c:
        r0 = move-exception;
        r1 = r0;
        r0 = com.loopj.android.http.AsyncHttpClient.f6518a;
        r2 = "AsyncHttpRequest";
        r3 = "Unhandled exception origin cause";
        r0.m10682b(r2, r3, r1);
        r0 = new java.io.IOException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Unhandled exception: ";
        r2 = r2.append(r3);
        r1 = r1.getMessage();
        r1 = r2.append(r1);
        r1 = r1.toString();
        r0.<init>(r1);
    L_0x0073:
        throw r0;
    L_0x0074:
        r0 = 0;
        goto L_0x003f;
    L_0x0076:
        r2 = move-exception;
        r0 = new java.io.IOException;	 Catch:{ Exception -> 0x004c }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x004c }
        r4.<init>();	 Catch:{ Exception -> 0x004c }
        r5 = "NPE in HttpClient: ";
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x004c }
        r2 = r2.getMessage();	 Catch:{ Exception -> 0x004c }
        r2 = r4.append(r2);	 Catch:{ Exception -> 0x004c }
        r2 = r2.toString();	 Catch:{ Exception -> 0x004c }
        r0.<init>(r2);	 Catch:{ Exception -> 0x004c }
        r2 = r7.f6533f;	 Catch:{ Exception -> 0x004c }
        r2 = r2 + 1;
        r7.f6533f = r2;	 Catch:{ Exception -> 0x004c }
        r4 = r7.f6529b;	 Catch:{ Exception -> 0x004c }
        r2 = r3.m10745a(r0, r2, r4);	 Catch:{ Exception -> 0x004c }
        goto L_0x0042;
    L_0x00a0:
        r0 = move-exception;
        r2 = r7.m10594a();	 Catch:{ Exception -> 0x004c }
        if (r2 != 0) goto L_0x000e;
    L_0x00a7:
        r2 = r7.f6533f;	 Catch:{ Exception -> 0x004c }
        r2 = r2 + 1;
        r7.f6533f = r2;	 Catch:{ Exception -> 0x004c }
        r4 = r7.f6529b;	 Catch:{ Exception -> 0x004c }
        r2 = r3.m10745a(r0, r2, r4);	 Catch:{ Exception -> 0x004c }
        goto L_0x0042;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loopj.android.http.AsyncHttpRequest.e():void");
    }

    public boolean m10594a() {
        boolean z = this.f6532e.get();
        if (z) {
            m10591f();
        }
        return z;
    }

    private synchronized void m10591f() {
        if (!(this.f6535h || !this.f6532e.get() || this.f6534g)) {
            this.f6534g = true;
            this.f6531d.m5470k();
        }
    }

    public boolean m10597b() {
        return m10594a() || this.f6535h;
    }

    public boolean m10595a(boolean z) {
        this.f6532e.set(true);
        this.f6530c.m10646i();
        return m10594a();
    }

    public AsyncHttpRequest m10592a(Object obj) {
        this.f6531d.m5457a(obj);
        return this;
    }

    public Object m10598c() {
        return this.f6531d.m5465d();
    }
}
