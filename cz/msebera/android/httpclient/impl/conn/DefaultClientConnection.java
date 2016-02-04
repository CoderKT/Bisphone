package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpResponseFactory;
import cz.msebera.android.httpclient.conn.ManagedHttpClientConnection;
import cz.msebera.android.httpclient.conn.OperatedClientConnection;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.impl.SocketHttpClientConnection;
import cz.msebera.android.httpclient.io.HttpMessageParser;
import cz.msebera.android.httpclient.io.SessionInputBuffer;
import cz.msebera.android.httpclient.io.SessionOutputBuffer;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.params.HttpProtocolParams;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

@Deprecated
public class DefaultClientConnection extends SocketHttpClientConnection implements ManagedHttpClientConnection, OperatedClientConnection, HttpContext {
    public HttpClientAndroidLog f7650a;
    public HttpClientAndroidLog f7651b;
    public HttpClientAndroidLog f7652c;
    private volatile Socket f7653d;
    private HttpHost f7654e;
    private boolean f7655f;
    private volatile boolean f7656g;
    private final Map<String, Object> f7657h;

    public DefaultClientConnection() {
        this.f7650a = new HttpClientAndroidLog(getClass());
        this.f7651b = new HttpClientAndroidLog("cz.msebera.android.httpclient.headers");
        this.f7652c = new HttpClientAndroidLog("cz.msebera.android.httpclient.wire");
        this.f7657h = new HashMap();
    }

    public final boolean m12232h() {
        return this.f7655f;
    }

    public final Socket m12233i() {
        return this.f7653d;
    }

    public SSLSession m12234m() {
        if (this.f7653d instanceof SSLSocket) {
            return ((SSLSocket) this.f7653d).getSession();
        }
        return null;
    }

    public void m12227a(Socket socket, HttpHost httpHost) {
        m11882q();
        this.f7653d = socket;
        this.f7654e = httpHost;
        if (this.f7656g) {
            socket.close();
            throw new InterruptedIOException("Connection already shutdown");
        }
    }

    public void m12229a(boolean z, HttpParams httpParams) {
        Args.m12722a((Object) httpParams, "Parameters");
        m11882q();
        this.f7655f = z;
        m11873a(this.f7653d, httpParams);
    }

    public void m12231e() {
        this.f7656g = true;
        try {
            super.m11877e();
            if (this.f7650a.m11836a()) {
                this.f7650a.m11834a("Connection " + this + " shut down");
            }
            Socket socket = this.f7653d;
            if (socket != null) {
                socket.close();
            }
        } catch (Throwable e) {
            this.f7650a.m11835a("I/O error shutting down connection", e);
        }
    }

    public void close() {
        try {
            super.close();
            if (this.f7650a.m11836a()) {
                this.f7650a.m11834a("Connection " + this + " closed");
            }
        } catch (Throwable e) {
            this.f7650a.m11835a("I/O error closing connection", e);
        }
    }

    protected SessionInputBuffer m12223a(Socket socket, int i, HttpParams httpParams) {
        if (i <= 0) {
            i = 8192;
        }
        SessionInputBuffer a = super.m11872a(socket, i, httpParams);
        if (this.f7652c.m11836a()) {
            return new LoggingSessionInputBuffer(a, new Wire(this.f7652c), HttpProtocolParams.m12679a(httpParams));
        }
        return a;
    }

    protected SessionOutputBuffer m12230b(Socket socket, int i, HttpParams httpParams) {
        if (i <= 0) {
            i = 8192;
        }
        SessionOutputBuffer b = super.m11874b(socket, i, httpParams);
        if (this.f7652c.m11836a()) {
            return new LoggingSessionOutputBuffer(b, new Wire(this.f7652c), HttpProtocolParams.m12679a(httpParams));
        }
        return b;
    }

    protected HttpMessageParser<HttpResponse> m12222a(SessionInputBuffer sessionInputBuffer, HttpResponseFactory httpResponseFactory, HttpParams httpParams) {
        return new DefaultHttpResponseParser(sessionInputBuffer, null, httpResponseFactory, httpParams);
    }

    public void m12228a(Socket socket, HttpHost httpHost, boolean z, HttpParams httpParams) {
        m11881j();
        Args.m12722a((Object) httpHost, "Target host");
        Args.m12722a((Object) httpParams, "Parameters");
        if (socket != null) {
            this.f7653d = socket;
            m11873a(socket, httpParams);
        }
        this.f7654e = httpHost;
        this.f7655f = z;
    }

    public HttpResponse m12221a() {
        HttpResponse a = super.m11845a();
        if (this.f7650a.m11836a()) {
            this.f7650a.m11834a("Receiving response: " + a.m11391a());
        }
        if (this.f7651b.m11836a()) {
            this.f7651b.m11834a("<< " + a.m11391a().toString());
            for (Object obj : a.m10620e()) {
                this.f7651b.m11834a("<< " + obj.toString());
            }
        }
        return a;
    }

    public void m12225a(HttpRequest httpRequest) {
        if (this.f7650a.m11836a()) {
            this.f7650a.m11834a("Sending request: " + httpRequest.m10637h());
        }
        super.m11850a(httpRequest);
        if (this.f7651b.m11836a()) {
            this.f7651b.m11834a(">> " + httpRequest.m10637h().toString());
            for (Object obj : httpRequest.m10620e()) {
                this.f7651b.m11834a(">> " + obj.toString());
            }
        }
    }

    public Object m12224a(String str) {
        return this.f7657h.get(str);
    }

    public void m12226a(String str, Object obj) {
        this.f7657h.put(str, obj);
    }
}
