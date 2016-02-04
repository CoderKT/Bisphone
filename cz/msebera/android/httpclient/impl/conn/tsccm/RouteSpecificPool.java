package cz.msebera.android.httpclient.impl.conn.tsccm;

import cz.msebera.android.httpclient.conn.params.ConnPerRoute;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import cz.msebera.android.httpclient.util.LangUtils;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

@Deprecated
public class RouteSpecificPool {
    public HttpClientAndroidLog f7725a;
    protected final HttpRoute f7726b;
    protected final int f7727c;
    protected final ConnPerRoute f7728d;
    protected final LinkedList<BasicPoolEntry> f7729e;
    protected final Queue<WaitingThread> f7730f;
    protected int f7731g;

    public RouteSpecificPool(HttpRoute httpRoute, ConnPerRoute connPerRoute) {
        this.f7725a = new HttpClientAndroidLog(getClass());
        this.f7726b = httpRoute;
        this.f7728d = connPerRoute;
        this.f7727c = connPerRoute.m11668a(httpRoute);
        this.f7729e = new LinkedList();
        this.f7730f = new LinkedList();
        this.f7731g = 0;
    }

    public final HttpRoute m12360a() {
        return this.f7726b;
    }

    public final int m12364b() {
        return this.f7727c;
    }

    public boolean m12367c() {
        return this.f7731g < 1 && this.f7730f.isEmpty();
    }

    public int m12369d() {
        return this.f7728d.m11668a(this.f7726b) - this.f7731g;
    }

    public BasicPoolEntry m12361a(Object obj) {
        BasicPoolEntry basicPoolEntry;
        if (!this.f7729e.isEmpty()) {
            ListIterator listIterator = this.f7729e.listIterator(this.f7729e.size());
            while (listIterator.hasPrevious()) {
                basicPoolEntry = (BasicPoolEntry) listIterator.previous();
                if (basicPoolEntry.m12194a() != null) {
                    if (LangUtils.m12769a(obj, basicPoolEntry.m12194a())) {
                    }
                }
                listIterator.remove();
                return basicPoolEntry;
            }
        }
        if (m12369d() != 0 || this.f7729e.isEmpty()) {
            return null;
        }
        basicPoolEntry = (BasicPoolEntry) this.f7729e.remove();
        basicPoolEntry.m12333b();
        try {
            basicPoolEntry.m12334c().close();
            return basicPoolEntry;
        } catch (Throwable e) {
            this.f7725a.m11835a("I/O error closing connection", e);
            return basicPoolEntry;
        }
    }

    public void m12362a(BasicPoolEntry basicPoolEntry) {
        if (this.f7731g < 1) {
            throw new IllegalStateException("No entry created for this pool. " + this.f7726b);
        } else if (this.f7731g <= this.f7729e.size()) {
            throw new IllegalStateException("No entry allocated from this pool. " + this.f7726b);
        } else {
            this.f7729e.add(basicPoolEntry);
        }
    }

    public void m12365b(BasicPoolEntry basicPoolEntry) {
        Args.m12724a(this.f7726b.equals(basicPoolEntry.m12335d()), "Entry not planned for this pool");
        this.f7731g++;
    }

    public boolean m12368c(BasicPoolEntry basicPoolEntry) {
        boolean remove = this.f7729e.remove(basicPoolEntry);
        if (remove) {
            this.f7731g--;
        }
        return remove;
    }

    public void m12370e() {
        Asserts.m12729a(this.f7731g > 0, "There is no entry that could be dropped");
        this.f7731g--;
    }

    public void m12363a(WaitingThread waitingThread) {
        Args.m12722a((Object) waitingThread, "Waiting thread");
        this.f7730f.add(waitingThread);
    }

    public boolean m12371f() {
        return !this.f7730f.isEmpty();
    }

    public WaitingThread m12372g() {
        return (WaitingThread) this.f7730f.peek();
    }

    public void m12366b(WaitingThread waitingThread) {
        if (waitingThread != null) {
            this.f7730f.remove(waitingThread);
        }
    }
}
