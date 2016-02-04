package com.loopj.android.http;

import android.os.Looper;
import java.lang.ref.WeakReference;

public class RequestHandle {
    private final WeakReference<AsyncHttpRequest> f6615a;

    /* renamed from: com.loopj.android.http.RequestHandle.1 */
    class C08951 implements Runnable {
        final /* synthetic */ AsyncHttpRequest f6612a;
        final /* synthetic */ boolean f6613b;
        final /* synthetic */ RequestHandle f6614c;

        C08951(RequestHandle requestHandle, AsyncHttpRequest asyncHttpRequest, boolean z) {
            this.f6614c = requestHandle;
            this.f6612a = asyncHttpRequest;
            this.f6613b = z;
        }

        public void run() {
            this.f6612a.m10595a(this.f6613b);
        }
    }

    public RequestHandle(AsyncHttpRequest asyncHttpRequest) {
        this.f6615a = new WeakReference(asyncHttpRequest);
    }

    public boolean m10730a(boolean z) {
        AsyncHttpRequest asyncHttpRequest = (AsyncHttpRequest) this.f6615a.get();
        if (asyncHttpRequest == null) {
            return false;
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            return asyncHttpRequest.m10595a(z);
        }
        new Thread(new C08951(this, asyncHttpRequest, z)).start();
        return true;
    }

    public boolean m10729a() {
        AsyncHttpRequest asyncHttpRequest = (AsyncHttpRequest) this.f6615a.get();
        return asyncHttpRequest == null || asyncHttpRequest.m10597b();
    }

    public boolean m10731b() {
        AsyncHttpRequest asyncHttpRequest = (AsyncHttpRequest) this.f6615a.get();
        return asyncHttpRequest == null || asyncHttpRequest.m10594a();
    }

    public boolean m10732c() {
        boolean z = m10731b() || m10729a();
        if (z) {
            this.f6615a.clear();
        }
        return z;
    }

    public Object m10733d() {
        AsyncHttpRequest asyncHttpRequest = (AsyncHttpRequest) this.f6615a.get();
        return asyncHttpRequest == null ? null : asyncHttpRequest.m10598c();
    }

    public RequestHandle m10728a(Object obj) {
        AsyncHttpRequest asyncHttpRequest = (AsyncHttpRequest) this.f6615a.get();
        if (asyncHttpRequest != null) {
            asyncHttpRequest.m10592a(obj);
        }
        return this;
    }
}
