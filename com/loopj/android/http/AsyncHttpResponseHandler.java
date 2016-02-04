package com.loopj.android.http;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.StatusLine;
import cz.msebera.android.httpclient.client.HttpResponseException;
import cz.msebera.android.httpclient.util.ByteArrayBuffer;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URI;
import org.jivesoftware.smack.util.StringUtils;
import se.emilsjolander.stickylistheaders.C1128R;

public abstract class AsyncHttpResponseHandler implements ResponseHandlerInterface {
    private String f3034a;
    private Handler f3035b;
    private boolean f3036c;
    private boolean f3037d;
    private URI f3038e;
    private Header[] f3039f;
    private Looper f3040g;
    private WeakReference<Object> f3041h;

    class ResponderHandler extends Handler {
        private final AsyncHttpResponseHandler f6537a;

        ResponderHandler(AsyncHttpResponseHandler asyncHttpResponseHandler, Looper looper) {
            super(looper);
            this.f6537a = asyncHttpResponseHandler;
        }

        public void handleMessage(Message message) {
            this.f6537a.m5477a(message);
        }
    }

    public abstract void m5474a(int i, Header[] headerArr, byte[] bArr);

    public abstract void m5475a(int i, Header[] headerArr, byte[] bArr, Throwable th);

    public AsyncHttpResponseHandler() {
        this(null);
    }

    public AsyncHttpResponseHandler(Looper looper) {
        this.f3034a = StringUtils.UTF8;
        this.f3038e = null;
        this.f3039f = null;
        this.f3040g = null;
        this.f3041h = new WeakReference(null);
        if (looper == null) {
            looper = Looper.myLooper();
        }
        this.f3040g = looper;
        m5485a(false);
        m5495b(false);
    }

    public AsyncHttpResponseHandler(boolean z) {
        this.f3034a = StringUtils.UTF8;
        this.f3038e = null;
        this.f3039f = null;
        this.f3040g = null;
        this.f3041h = new WeakReference(null);
        m5495b(z);
        if (!m5500g()) {
            this.f3040g = Looper.myLooper();
            m5485a(false);
        }
    }

    public Object m5497d() {
        return this.f3041h.get();
    }

    public void m5480a(Object obj) {
        this.f3041h = new WeakReference(obj);
    }

    public URI m5498e() {
        return this.f3038e;
    }

    public void m5484a(URI uri) {
        this.f3038e = uri;
    }

    public void m5486a(Header[] headerArr) {
        this.f3039f = headerArr;
    }

    public boolean m5499f() {
        return this.f3036c;
    }

    public void m5485a(boolean z) {
        if (!z && this.f3040g == null) {
            z = true;
            AsyncHttpClient.f6518a.m10684d("AsyncHttpRH", "Current thread has not called Looper.prepare(). Forcing synchronous mode.");
        }
        if (!z && this.f3035b == null) {
            this.f3035b = new ResponderHandler(this, this.f3040g);
        } else if (z && this.f3035b != null) {
            this.f3035b = null;
        }
        this.f3036c = z;
    }

    public boolean m5500g() {
        return this.f3037d;
    }

    public void m5495b(boolean z) {
        if (z) {
            this.f3040g = null;
            this.f3035b = null;
        }
        this.f3037d = z;
    }

    public String m5501h() {
        return this.f3034a == null ? StringUtils.UTF8 : this.f3034a;
    }

    public void m5482a(String str) {
        this.f3034a = str;
    }

    public void m5476a(long j, long j2) {
        LogInterface logInterface = AsyncHttpClient.f6518a;
        String str = "AsyncHttpRH";
        String str2 = "Progress %d from %d (%2.0f%%)";
        Object[] objArr = new Object[3];
        objArr[0] = Long.valueOf(j);
        objArr[1] = Long.valueOf(j2);
        objArr[2] = Double.valueOf(j2 > 0 ? ((((double) j) * 1.0d) / ((double) j2)) * 100.0d : -1.0d);
        logInterface.m10679a(str, String.format(str2, objArr));
    }

    public void m5472a() {
    }

    public void m5496c() {
    }

    public void m5478a(ResponseHandlerInterface responseHandlerInterface, HttpResponse httpResponse) {
    }

    public void m5494b(ResponseHandlerInterface responseHandlerInterface, HttpResponse httpResponse) {
    }

    public void m5473a(int i) {
        AsyncHttpClient.f6518a.m10681b("AsyncHttpRH", String.format("Request retry no. %d", new Object[]{Integer.valueOf(i)}));
    }

    public void m5488b() {
        AsyncHttpClient.f6518a.m10681b("AsyncHttpRH", "Request got cancelled");
    }

    public void m5483a(Throwable th) {
        AsyncHttpClient.f6518a.m10682b("AsyncHttpRH", "User-space exception detected!", th);
        throw new RuntimeException(th);
    }

    public final void m5492b(long j, long j2) {
        m5493b(m5471a(4, new Object[]{Long.valueOf(j), Long.valueOf(j2)}));
    }

    public final void m5490b(int i, Header[] headerArr, byte[] bArr) {
        m5493b(m5471a(0, new Object[]{Integer.valueOf(i), headerArr, bArr}));
    }

    public final void m5491b(int i, Header[] headerArr, byte[] bArr, Throwable th) {
        m5493b(m5471a(1, new Object[]{Integer.valueOf(i), headerArr, bArr, th}));
    }

    public final void m5502i() {
        m5493b(m5471a(2, null));
    }

    public final void m5503j() {
        m5493b(m5471a(3, null));
    }

    public final void m5489b(int i) {
        m5493b(m5471a(5, new Object[]{Integer.valueOf(i)}));
    }

    public final void m5504k() {
        m5493b(m5471a(6, null));
    }

    protected void m5477a(Message message) {
        try {
            Object[] objArr;
            switch (message.what) {
                case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                    objArr = (Object[]) message.obj;
                    if (objArr == null || objArr.length < 3) {
                        AsyncHttpClient.f6518a.m10685e("AsyncHttpRH", "SUCCESS_MESSAGE didn't got enough params");
                        return;
                    } else {
                        m5474a(((Integer) objArr[0]).intValue(), (Header[]) objArr[1], (byte[]) objArr[2]);
                        return;
                    }
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    objArr = (Object[]) message.obj;
                    if (objArr == null || objArr.length < 4) {
                        AsyncHttpClient.f6518a.m10685e("AsyncHttpRH", "FAILURE_MESSAGE didn't got enough params");
                        return;
                    } else {
                        m5475a(((Integer) objArr[0]).intValue(), (Header[]) objArr[1], (byte[]) objArr[2], (Throwable) objArr[3]);
                        return;
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    m5472a();
                    return;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    m5496c();
                    return;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    objArr = (Object[]) message.obj;
                    if (objArr == null || objArr.length < 2) {
                        AsyncHttpClient.f6518a.m10685e("AsyncHttpRH", "PROGRESS_MESSAGE didn't got enough params");
                        return;
                    } else {
                        m5476a(((Long) objArr[0]).longValue(), ((Long) objArr[1]).longValue());
                        return;
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    objArr = (Object[]) message.obj;
                    if (objArr == null || objArr.length != 1) {
                        AsyncHttpClient.f6518a.m10685e("AsyncHttpRH", "RETRY_MESSAGE didn't get enough params");
                        return;
                    } else {
                        m5473a(((Integer) objArr[0]).intValue());
                        return;
                    }
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    m5488b();
                    return;
                default:
                    return;
            }
        } catch (Throwable th) {
            m5483a(th);
        }
        m5483a(th);
    }

    protected void m5493b(Message message) {
        if (m5499f() || this.f3035b == null) {
            m5477a(message);
        } else if (!Thread.currentThread().isInterrupted()) {
            Utils.m10778a(this.f3035b != null, "handler should not be null!");
            this.f3035b.sendMessage(message);
        }
    }

    protected void m5481a(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (m5499f() || this.f3035b == null) {
            runnable.run();
        } else {
            this.f3035b.post(runnable);
        }
    }

    protected Message m5471a(int i, Object obj) {
        return Message.obtain(this.f3035b, i, obj);
    }

    public void m5479a(HttpResponse httpResponse) {
        if (!Thread.currentThread().isInterrupted()) {
            StatusLine a = httpResponse.m11391a();
            byte[] a2 = m5487a(httpResponse.m11393b());
            if (!Thread.currentThread().isInterrupted()) {
                if (a.m11410b() >= 300) {
                    m5491b(a.m11410b(), httpResponse.m10620e(), a2, new HttpResponseException(a.m11410b(), a.m11411c()));
                } else {
                    m5490b(a.m11410b(), httpResponse.m10620e(), a2);
                }
            }
        }
    }

    byte[] m5487a(HttpEntity httpEntity) {
        int i = 4096;
        if (httpEntity != null) {
            InputStream a = httpEntity.m10540a();
            if (a != null) {
                long b = httpEntity.m10542b();
                if (b > 2147483647L) {
                    throw new IllegalArgumentException("HTTP entity too large to be buffered in memory");
                }
                if (b > 0) {
                    i = (int) b;
                }
                try {
                    ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(i);
                    byte[] bArr = new byte[4096];
                    long j = 0;
                    while (true) {
                        int read = a.read(bArr);
                        if (read == -1 || Thread.currentThread().isInterrupted()) {
                            break;
                        }
                        long j2 = ((long) read) + j;
                        byteArrayBuffer.m12734a(bArr, 0, read);
                        if (b <= 0) {
                            j = 1;
                        } else {
                            j = b;
                        }
                        m5492b(j2, j);
                        j = j2;
                    }
                    AsyncHttpClient.m10567a(a);
                    AsyncHttpClient.m10566a(httpEntity);
                    return byteArrayBuffer.m12737b();
                } catch (OutOfMemoryError e) {
                    System.gc();
                    throw new IOException("File too large to fit into available memory");
                } catch (Throwable th) {
                    AsyncHttpClient.m10567a(a);
                    AsyncHttpClient.m10566a(httpEntity);
                }
            }
        }
        return null;
    }
}
