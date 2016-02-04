package com.loopj.android.http;

import android.os.SystemClock;
import cz.msebera.android.httpclient.NoHttpResponseException;
import cz.msebera.android.httpclient.client.HttpRequestRetryHandler;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.protocol.HttpContext;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Iterator;
import javax.net.ssl.SSLException;

class RetryHandler implements HttpRequestRetryHandler {
    private static final HashSet<Class<?>> f6635a;
    private static final HashSet<Class<?>> f6636b;
    private final int f6637c;
    private final int f6638d;

    static {
        f6635a = new HashSet();
        f6636b = new HashSet();
        f6635a.add(NoHttpResponseException.class);
        f6635a.add(UnknownHostException.class);
        f6635a.add(SocketException.class);
        f6636b.add(InterruptedIOException.class);
        f6636b.add(SSLException.class);
    }

    public RetryHandler(int i, int i2) {
        this.f6637c = i;
        this.f6638d = i2;
    }

    public boolean m10746a(IOException iOException, int i, HttpContext httpContext) {
        boolean z = true;
        Boolean bool = (Boolean) httpContext.m11528a("http.request_sent");
        boolean z2 = bool != null && bool.booleanValue();
        if (i > this.f6637c) {
            z = false;
        } else if (!m10747a(f6635a, iOException)) {
            if (m10747a(f6636b, iOException)) {
                z = false;
            } else if (!z2) {
            }
        }
        if (z && ((HttpUriRequest) httpContext.m11528a("http.request")) == null) {
            return false;
        }
        if (z) {
            SystemClock.sleep((long) this.f6638d);
        } else {
            iOException.printStackTrace();
        }
        return z;
    }

    protected boolean m10747a(HashSet<Class<?>> hashSet, Throwable th) {
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            if (((Class) it.next()).isInstance(th)) {
                return true;
            }
        }
        return false;
    }
}
