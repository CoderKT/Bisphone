package cz.msebera.android.httpclient.pool;

import cz.msebera.android.httpclient.util.Args;
import java.util.concurrent.TimeUnit;

public abstract class PoolEntry<T, C> {
    private final String f7672a;
    private final T f7673b;
    private final C f7674c;
    private final long f7675d;
    private final long f7676e;
    private long f7677f;
    private long f7678g;
    private volatile Object f7679h;

    public PoolEntry(String str, T t, C c, long j, TimeUnit timeUnit) {
        Args.m12722a((Object) t, "Route");
        Args.m12722a((Object) c, "Connection");
        Args.m12722a((Object) timeUnit, "Time unit");
        this.f7672a = str;
        this.f7673b = t;
        this.f7674c = c;
        this.f7675d = System.currentTimeMillis();
        if (j > 0) {
            this.f7676e = this.f7675d + timeUnit.toMillis(j);
        } else {
            this.f7676e = Long.MAX_VALUE;
        }
        this.f7678g = this.f7676e;
    }

    public T m12254f() {
        return this.f7673b;
    }

    public C m12255g() {
        return this.f7674c;
    }

    public void m12252a(Object obj) {
        this.f7679h = obj;
    }

    public synchronized long m12256h() {
        return this.f7678g;
    }

    public synchronized void m12251a(long j, TimeUnit timeUnit) {
        long toMillis;
        Args.m12722a((Object) timeUnit, "Time unit");
        this.f7677f = System.currentTimeMillis();
        if (j > 0) {
            toMillis = this.f7677f + timeUnit.toMillis(j);
        } else {
            toMillis = Long.MAX_VALUE;
        }
        this.f7678g = Math.min(toMillis, this.f7676e);
    }

    public synchronized boolean m12253a(long j) {
        return j >= this.f7678g;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[id:");
        stringBuilder.append(this.f7672a);
        stringBuilder.append("][route:");
        stringBuilder.append(this.f7673b);
        stringBuilder.append("][state:");
        stringBuilder.append(this.f7679h);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
