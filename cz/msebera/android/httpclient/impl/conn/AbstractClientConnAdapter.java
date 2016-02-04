package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.conn.ClientConnectionManager;
import cz.msebera.android.httpclient.conn.ManagedClientConnection;
import cz.msebera.android.httpclient.conn.OperatedClientConnection;
import cz.msebera.android.httpclient.protocol.HttpContext;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

@Deprecated
public abstract class AbstractClientConnAdapter implements ManagedClientConnection, HttpContext {
    private final ClientConnectionManager f7629a;
    private volatile OperatedClientConnection f7630b;
    private volatile boolean f7631c;
    private volatile boolean f7632d;
    private volatile long f7633e;

    protected AbstractClientConnAdapter(ClientConnectionManager clientConnectionManager, OperatedClientConnection operatedClientConnection) {
        this.f7629a = clientConnectionManager;
        this.f7630b = operatedClientConnection;
        this.f7631c = false;
        this.f7632d = false;
        this.f7633e = Long.MAX_VALUE;
    }

    protected synchronized void m12189n() {
        this.f7630b = null;
        this.f7633e = Long.MAX_VALUE;
    }

    protected OperatedClientConnection m12190o() {
        return this.f7630b;
    }

    protected ClientConnectionManager m12191p() {
        return this.f7629a;
    }

    protected boolean m12192q() {
        return this.f7632d;
    }

    protected final void m12175a(OperatedClientConnection operatedClientConnection) {
        if (m12192q() || operatedClientConnection == null) {
            throw new ConnectionShutdownException();
        }
    }

    public boolean m12180c() {
        OperatedClientConnection o = m12190o();
        if (o == null) {
            return false;
        }
        return o.m11375c();
    }

    public boolean m12181d() {
        if (m12192q()) {
            return true;
        }
        OperatedClientConnection o = m12190o();
        if (o != null) {
            return o.m11376d();
        }
        return true;
    }

    public void m12179b(int i) {
        OperatedClientConnection o = m12190o();
        m12175a(o);
        o.m11374b(i);
    }

    public void m12178b() {
        OperatedClientConnection o = m12190o();
        m12175a(o);
        o.m11383b();
    }

    public boolean m12177a(int i) {
        OperatedClientConnection o = m12190o();
        m12175a(o);
        return o.m11382a(i);
    }

    public void m12174a(HttpResponse httpResponse) {
        OperatedClientConnection o = m12190o();
        m12175a(o);
        m12187l();
        o.m11381a(httpResponse);
    }

    public HttpResponse m12169a() {
        OperatedClientConnection o = m12190o();
        m12175a(o);
        m12187l();
        return o.m11378a();
    }

    public void m12172a(HttpEntityEnclosingRequest httpEntityEnclosingRequest) {
        OperatedClientConnection o = m12190o();
        m12175a(o);
        m12187l();
        o.m11379a(httpEntityEnclosingRequest);
    }

    public void m12173a(HttpRequest httpRequest) {
        OperatedClientConnection o = m12190o();
        m12175a(o);
        m12187l();
        o.m11380a(httpRequest);
    }

    public InetAddress m12182f() {
        OperatedClientConnection o = m12190o();
        m12175a(o);
        return o.m11389f();
    }

    public int m12183g() {
        OperatedClientConnection o = m12190o();
        m12175a(o);
        return o.m11390g();
    }

    public SSLSession m12188m() {
        OperatedClientConnection o = m12190o();
        m12175a(o);
        if (!m12180c()) {
            return null;
        }
        SSLSession session;
        Socket i = o.m11666i();
        if (i instanceof SSLSocket) {
            session = ((SSLSocket) i).getSession();
        } else {
            session = null;
        }
        return session;
    }

    public void m12186k() {
        this.f7631c = true;
    }

    public void m12187l() {
        this.f7631c = false;
    }

    public boolean m12193r() {
        return this.f7631c;
    }

    public void m12171a(long j, TimeUnit timeUnit) {
        if (j > 0) {
            this.f7633e = timeUnit.toMillis(j);
        } else {
            this.f7633e = -1;
        }
    }

    public synchronized void m12184i() {
        if (!this.f7632d) {
            this.f7632d = true;
            this.f7629a.m11634a(this, this.f7633e, TimeUnit.MILLISECONDS);
        }
    }

    public synchronized void m12185j() {
        if (!this.f7632d) {
            this.f7632d = true;
            m12187l();
            try {
                m11377e();
            } catch (IOException e) {
            }
            this.f7629a.m11634a(this, this.f7633e, TimeUnit.MILLISECONDS);
        }
    }

    public Object m12170a(String str) {
        OperatedClientConnection o = m12190o();
        m12175a(o);
        if (o instanceof HttpContext) {
            return ((HttpContext) o).m11528a(str);
        }
        return null;
    }

    public void m12176a(String str, Object obj) {
        OperatedClientConnection o = m12190o();
        m12175a(o);
        if (o instanceof HttpContext) {
            ((HttpContext) o).m11529a(str, obj);
        }
    }
}
