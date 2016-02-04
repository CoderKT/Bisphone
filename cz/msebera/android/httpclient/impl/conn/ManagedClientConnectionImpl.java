package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.conn.ClientConnectionManager;
import cz.msebera.android.httpclient.conn.ClientConnectionOperator;
import cz.msebera.android.httpclient.conn.ManagedClientConnection;
import cz.msebera.android.httpclient.conn.OperatedClientConnection;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.conn.routing.RouteTracker;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

@Deprecated
class ManagedClientConnectionImpl implements ManagedClientConnection {
    private final ClientConnectionManager f7691a;
    private final ClientConnectionOperator f7692b;
    private volatile HttpPoolEntry f7693c;
    private volatile boolean f7694d;
    private volatile long f7695e;

    ManagedClientConnectionImpl(ClientConnectionManager clientConnectionManager, ClientConnectionOperator clientConnectionOperator, HttpPoolEntry httpPoolEntry) {
        Args.m12722a((Object) clientConnectionManager, "Connection manager");
        Args.m12722a((Object) clientConnectionOperator, "Connection operator");
        Args.m12722a((Object) httpPoolEntry, "HTTP pool entry");
        this.f7691a = clientConnectionManager;
        this.f7692b = clientConnectionOperator;
        this.f7693c = httpPoolEntry;
        this.f7694d = false;
        this.f7695e = Long.MAX_VALUE;
    }

    HttpPoolEntry m12315n() {
        return this.f7693c;
    }

    HttpPoolEntry m12316o() {
        HttpPoolEntry httpPoolEntry = this.f7693c;
        this.f7693c = null;
        return httpPoolEntry;
    }

    public ClientConnectionManager m12317p() {
        return this.f7691a;
    }

    private OperatedClientConnection m12288r() {
        HttpPoolEntry httpPoolEntry = this.f7693c;
        if (httpPoolEntry == null) {
            return null;
        }
        return (OperatedClientConnection) httpPoolEntry.m12255g();
    }

    private OperatedClientConnection m12289s() {
        HttpPoolEntry httpPoolEntry = this.f7693c;
        if (httpPoolEntry != null) {
            return (OperatedClientConnection) httpPoolEntry.m12255g();
        }
        throw new ConnectionShutdownException();
    }

    private HttpPoolEntry m12290t() {
        HttpPoolEntry httpPoolEntry = this.f7693c;
        if (httpPoolEntry != null) {
            return httpPoolEntry;
        }
        throw new ConnectionShutdownException();
    }

    public void close() {
        HttpPoolEntry httpPoolEntry = this.f7693c;
        if (httpPoolEntry != null) {
            OperatedClientConnection operatedClientConnection = (OperatedClientConnection) httpPoolEntry.m12255g();
            httpPoolEntry.m12257a().m11715h();
            operatedClientConnection.close();
        }
    }

    public void m12306e() {
        HttpPoolEntry httpPoolEntry = this.f7693c;
        if (httpPoolEntry != null) {
            OperatedClientConnection operatedClientConnection = (OperatedClientConnection) httpPoolEntry.m12255g();
            httpPoolEntry.m12257a().m11715h();
            operatedClientConnection.m11377e();
        }
    }

    public boolean m12304c() {
        OperatedClientConnection r = m12288r();
        if (r != null) {
            return r.m11375c();
        }
        return false;
    }

    public boolean m12305d() {
        OperatedClientConnection r = m12288r();
        if (r != null) {
            return r.m11376d();
        }
        return true;
    }

    public void m12303b(int i) {
        m12289s().m11374b(i);
    }

    public void m12302b() {
        m12289s().m11383b();
    }

    public boolean m12301a(int i) {
        return m12289s().m11382a(i);
    }

    public void m12296a(HttpResponse httpResponse) {
        m12289s().m11381a(httpResponse);
    }

    public HttpResponse m12291a() {
        return m12289s().m11378a();
    }

    public void m12293a(HttpEntityEnclosingRequest httpEntityEnclosingRequest) {
        m12289s().m11379a(httpEntityEnclosingRequest);
    }

    public void m12295a(HttpRequest httpRequest) {
        m12289s().m11380a(httpRequest);
    }

    public InetAddress m12307f() {
        return m12289s().m11389f();
    }

    public int m12308g() {
        return m12289s().m11390g();
    }

    public SSLSession m12314m() {
        Socket i = m12289s().m11666i();
        if (i instanceof SSLSocket) {
            return ((SSLSocket) i).getSession();
        }
        return null;
    }

    public HttpRoute m12309h() {
        return m12290t().m12260c();
    }

    public void m12297a(HttpRoute httpRoute, HttpContext httpContext, HttpParams httpParams) {
        OperatedClientConnection operatedClientConnection;
        HttpHost httpHost;
        Args.m12722a((Object) httpRoute, "Route");
        Args.m12722a((Object) httpParams, "HTTP parameters");
        synchronized (this) {
            if (this.f7693c == null) {
                throw new ConnectionShutdownException();
            }
            Object a = this.f7693c.m12257a();
            Asserts.m12728a(a, "Route tracker");
            Asserts.m12729a(!a.m11716i(), "Connection already open");
            operatedClientConnection = (OperatedClientConnection) this.f7693c.m12255g();
        }
        HttpHost d = httpRoute.m11697d();
        ClientConnectionOperator clientConnectionOperator = this.f7692b;
        if (d != null) {
            httpHost = d;
        } else {
            httpHost = httpRoute.m11693a();
        }
        clientConnectionOperator.m11639a(operatedClientConnection, httpHost, httpRoute.m11695b(), httpContext, httpParams);
        synchronized (this) {
            if (this.f7693c == null) {
                throw new InterruptedIOException();
            }
            RouteTracker a2 = this.f7693c.m12257a();
            if (d == null) {
                a2.m11705a(operatedClientConnection.m11665h());
            } else {
                a2.m11704a(d, operatedClientConnection.m11665h());
            }
        }
    }

    public void m12300a(boolean z, HttpParams httpParams) {
        HttpHost a;
        OperatedClientConnection operatedClientConnection;
        Args.m12722a((Object) httpParams, "HTTP parameters");
        synchronized (this) {
            if (this.f7693c == null) {
                throw new ConnectionShutdownException();
            }
            Object a2 = this.f7693c.m12257a();
            Asserts.m12728a(a2, "Route tracker");
            Asserts.m12729a(a2.m11716i(), "Connection not open");
            Asserts.m12729a(!a2.m11712e(), "Connection is already tunnelled");
            a = a2.m11702a();
            operatedClientConnection = (OperatedClientConnection) this.f7693c.m12255g();
        }
        operatedClientConnection.m11663a(null, a, z, httpParams);
        synchronized (this) {
            if (this.f7693c == null) {
                throw new InterruptedIOException();
            }
            this.f7693c.m12257a().m11708b(z);
        }
    }

    public void m12294a(HttpHost httpHost, boolean z, HttpParams httpParams) {
        OperatedClientConnection operatedClientConnection;
        Args.m12722a((Object) httpHost, "Next proxy");
        Args.m12722a((Object) httpParams, "HTTP parameters");
        synchronized (this) {
            if (this.f7693c == null) {
                throw new ConnectionShutdownException();
            }
            Object a = this.f7693c.m12257a();
            Asserts.m12728a(a, "Route tracker");
            Asserts.m12729a(a.m11716i(), "Connection not open");
            operatedClientConnection = (OperatedClientConnection) this.f7693c.m12255g();
        }
        operatedClientConnection.m11663a(null, httpHost, z, httpParams);
        synchronized (this) {
            if (this.f7693c == null) {
                throw new InterruptedIOException();
            }
            this.f7693c.m12257a().m11707b(httpHost, z);
        }
    }

    public void m12298a(HttpContext httpContext, HttpParams httpParams) {
        HttpHost a;
        OperatedClientConnection operatedClientConnection;
        Args.m12722a((Object) httpParams, "HTTP parameters");
        synchronized (this) {
            if (this.f7693c == null) {
                throw new ConnectionShutdownException();
            }
            Object a2 = this.f7693c.m12257a();
            Asserts.m12728a(a2, "Route tracker");
            Asserts.m12729a(a2.m11716i(), "Connection not open");
            Asserts.m12729a(a2.m11712e(), "Protocol layering without a tunnel not supported");
            Asserts.m12729a(!a2.m11713f(), "Multiple protocol layering not supported");
            a = a2.m11702a();
            operatedClientConnection = (OperatedClientConnection) this.f7693c.m12255g();
        }
        this.f7692b.m11638a(operatedClientConnection, a, httpContext, httpParams);
        synchronized (this) {
            if (this.f7693c == null) {
                throw new InterruptedIOException();
            }
            this.f7693c.m12257a().m11710c(operatedClientConnection.m11665h());
        }
    }

    public void m12299a(Object obj) {
        m12290t().m12252a(obj);
    }

    public void m12312k() {
        this.f7694d = true;
    }

    public void m12313l() {
        this.f7694d = false;
    }

    public boolean m12318q() {
        return this.f7694d;
    }

    public void m12292a(long j, TimeUnit timeUnit) {
        if (j > 0) {
            this.f7695e = timeUnit.toMillis(j);
        } else {
            this.f7695e = -1;
        }
    }

    public void m12310i() {
        synchronized (this) {
            if (this.f7693c == null) {
                return;
            }
            this.f7691a.m11634a(this, this.f7695e, TimeUnit.MILLISECONDS);
            this.f7693c = null;
        }
    }

    public void m12311j() {
        synchronized (this) {
            if (this.f7693c == null) {
                return;
            }
            this.f7694d = false;
            try {
                ((OperatedClientConnection) this.f7693c.m12255g()).m11377e();
            } catch (IOException e) {
            }
            this.f7691a.m11634a(this, this.f7695e, TimeUnit.MILLISECONDS);
            this.f7693c = null;
        }
    }
}
