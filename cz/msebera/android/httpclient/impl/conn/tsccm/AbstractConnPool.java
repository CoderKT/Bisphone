package cz.msebera.android.httpclient.impl.conn.tsccm;

import cz.msebera.android.httpclient.conn.OperatedClientConnection;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.impl.conn.IdleConnectionHandler;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Deprecated
public abstract class AbstractConnPool {
    public HttpClientAndroidLog f7699a;
    protected final Lock f7700b;
    protected Set<BasicPoolEntry> f7701c;
    protected volatile boolean f7702d;
    protected IdleConnectionHandler f7703e;

    protected AbstractConnPool() {
        this.f7699a = new HttpClientAndroidLog(getClass());
        this.f7701c = new HashSet();
        this.f7703e = new IdleConnectionHandler();
        this.f7700b = new ReentrantLock();
    }

    public void m12329a() {
        this.f7700b.lock();
        try {
            if (!this.f7702d) {
                Iterator it = this.f7701c.iterator();
                while (it.hasNext()) {
                    BasicPoolEntry basicPoolEntry = (BasicPoolEntry) it.next();
                    it.remove();
                    m12330a(basicPoolEntry.m12334c());
                }
                this.f7703e.m12263a();
                this.f7702d = true;
                this.f7700b.unlock();
            }
        } finally {
            this.f7700b.unlock();
        }
    }

    protected void m12330a(OperatedClientConnection operatedClientConnection) {
        if (operatedClientConnection != null) {
            try {
                operatedClientConnection.close();
            } catch (Throwable e) {
                this.f7699a.m11835a("I/O error closing connection", e);
            }
        }
    }
}
