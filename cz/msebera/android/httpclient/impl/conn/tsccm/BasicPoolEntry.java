package cz.msebera.android.httpclient.impl.conn.tsccm;

import cz.msebera.android.httpclient.conn.ClientConnectionOperator;
import cz.msebera.android.httpclient.conn.OperatedClientConnection;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.impl.conn.AbstractPoolEntry;
import cz.msebera.android.httpclient.util.Args;
import java.util.concurrent.TimeUnit;

@Deprecated
public class BasicPoolEntry extends AbstractPoolEntry {
    private final long f7704f;
    private long f7705g;
    private final long f7706h;
    private long f7707i;

    public BasicPoolEntry(ClientConnectionOperator clientConnectionOperator, HttpRoute httpRoute, long j, TimeUnit timeUnit) {
        super(clientConnectionOperator, httpRoute);
        Args.m12722a((Object) httpRoute, "HTTP route");
        this.f7704f = System.currentTimeMillis();
        if (j > 0) {
            this.f7706h = this.f7704f + timeUnit.toMillis(j);
        } else {
            this.f7706h = Long.MAX_VALUE;
        }
        this.f7707i = this.f7706h;
    }

    protected final OperatedClientConnection m12334c() {
        return this.f7635b;
    }

    protected final HttpRoute m12335d() {
        return this.f7636c;
    }

    protected void m12333b() {
        super.m12200b();
    }

    public void m12331a(long j, TimeUnit timeUnit) {
        long toMillis;
        this.f7705g = System.currentTimeMillis();
        if (j > 0) {
            toMillis = this.f7705g + timeUnit.toMillis(j);
        } else {
            toMillis = Long.MAX_VALUE;
        }
        this.f7707i = Math.min(this.f7706h, toMillis);
    }

    public boolean m12332a(long j) {
        return j >= this.f7707i;
    }
}
