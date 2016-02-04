package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.conn.ClientConnectionManager;
import cz.msebera.android.httpclient.conn.OperatedClientConnection;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.HttpContext;

@Deprecated
public abstract class AbstractPooledConnAdapter extends AbstractClientConnAdapter {
    protected volatile AbstractPoolEntry f7639a;

    protected AbstractPooledConnAdapter(ClientConnectionManager clientConnectionManager, AbstractPoolEntry abstractPoolEntry) {
        super(clientConnectionManager, abstractPoolEntry.f7635b);
        this.f7639a = abstractPoolEntry;
    }

    @Deprecated
    protected AbstractPoolEntry m12210s() {
        return this.f7639a;
    }

    protected void m12203a(AbstractPoolEntry abstractPoolEntry) {
        if (m12192q() || abstractPoolEntry == null) {
            throw new ConnectionShutdownException();
        }
    }

    protected synchronized void m12209n() {
        this.f7639a = null;
        super.m12189n();
    }

    public HttpRoute m12208h() {
        AbstractPoolEntry s = m12210s();
        m12203a(s);
        return s.f7638e == null ? null : s.f7638e.m11717j();
    }

    public void m12202a(HttpRoute httpRoute, HttpContext httpContext, HttpParams httpParams) {
        AbstractPoolEntry s = m12210s();
        m12203a(s);
        s.m12196a(httpRoute, httpContext, httpParams);
    }

    public void m12206a(boolean z, HttpParams httpParams) {
        AbstractPoolEntry s = m12210s();
        m12203a(s);
        s.m12199a(z, httpParams);
    }

    public void m12201a(HttpHost httpHost, boolean z, HttpParams httpParams) {
        AbstractPoolEntry s = m12210s();
        m12203a(s);
        s.m12195a(httpHost, z, httpParams);
    }

    public void m12204a(HttpContext httpContext, HttpParams httpParams) {
        AbstractPoolEntry s = m12210s();
        m12203a(s);
        s.m12197a(httpContext, httpParams);
    }

    public void close() {
        AbstractPoolEntry s = m12210s();
        if (s != null) {
            s.m12200b();
        }
        OperatedClientConnection o = m12190o();
        if (o != null) {
            o.close();
        }
    }

    public void m12207e() {
        AbstractPoolEntry s = m12210s();
        if (s != null) {
            s.m12200b();
        }
        OperatedClientConnection o = m12190o();
        if (o != null) {
            o.m11377e();
        }
    }

    public void m12205a(Object obj) {
        AbstractPoolEntry s = m12210s();
        m12203a(s);
        s.m12198a(obj);
    }
}
