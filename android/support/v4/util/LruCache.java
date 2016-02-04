package android.support.v4.util;

import java.util.LinkedHashMap;

public class LruCache<K, V> {
    private final LinkedHashMap<K, V> f391a;
    private int f392b;
    private int f393c;
    private int f394d;
    private int f395e;
    private int f396f;
    private int f397g;
    private int f398h;

    public LruCache(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.f393c = i;
        this.f391a = new LinkedHashMap(0, 0.75f, true);
    }

    public final V m776a(K k) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            V v = this.f391a.get(k);
            if (v != null) {
                this.f397g++;
                return v;
            }
            this.f398h++;
            V b = m781b(k);
            if (b == null) {
                return null;
            }
            synchronized (this) {
                this.f395e++;
                v = this.f391a.put(k, b);
                if (v != null) {
                    this.f391a.put(k, v);
                } else {
                    this.f392b += m775c(k, b);
                }
            }
            if (v != null) {
                m779a(false, k, b, v);
                return v;
            }
            m778a(this.f393c);
            return b;
        }
    }

    public final V m777a(K k, V v) {
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        V put;
        synchronized (this) {
            this.f394d++;
            this.f392b += m775c(k, v);
            put = this.f391a.put(k, v);
            if (put != null) {
                this.f392b -= m775c(k, put);
            }
        }
        if (put != null) {
            m779a(false, k, put, v);
        }
        m778a(this.f393c);
        return put;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m778a(int r5) {
        /*
        r4 = this;
    L_0x0000:
        monitor-enter(r4);
        r0 = r4.f392b;	 Catch:{ all -> 0x0032 }
        if (r0 < 0) goto L_0x0011;
    L_0x0005:
        r0 = r4.f391a;	 Catch:{ all -> 0x0032 }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x0032 }
        if (r0 == 0) goto L_0x0035;
    L_0x000d:
        r0 = r4.f392b;	 Catch:{ all -> 0x0032 }
        if (r0 == 0) goto L_0x0035;
    L_0x0011:
        r0 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0032 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0032 }
        r1.<init>();	 Catch:{ all -> 0x0032 }
        r2 = r4.getClass();	 Catch:{ all -> 0x0032 }
        r2 = r2.getName();	 Catch:{ all -> 0x0032 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0032 }
        r2 = ".sizeOf() is reporting inconsistent results!";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0032 }
        r1 = r1.toString();	 Catch:{ all -> 0x0032 }
        r0.<init>(r1);	 Catch:{ all -> 0x0032 }
        throw r0;	 Catch:{ all -> 0x0032 }
    L_0x0032:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0032 }
        throw r0;
    L_0x0035:
        r0 = r4.f392b;	 Catch:{ all -> 0x0032 }
        if (r0 <= r5) goto L_0x0041;
    L_0x0039:
        r0 = r4.f391a;	 Catch:{ all -> 0x0032 }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x0032 }
        if (r0 == 0) goto L_0x0043;
    L_0x0041:
        monitor-exit(r4);	 Catch:{ all -> 0x0032 }
        return;
    L_0x0043:
        r0 = r4.f391a;	 Catch:{ all -> 0x0032 }
        r0 = r0.entrySet();	 Catch:{ all -> 0x0032 }
        r0 = r0.iterator();	 Catch:{ all -> 0x0032 }
        r0 = r0.next();	 Catch:{ all -> 0x0032 }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ all -> 0x0032 }
        r1 = r0.getKey();	 Catch:{ all -> 0x0032 }
        r0 = r0.getValue();	 Catch:{ all -> 0x0032 }
        r2 = r4.f391a;	 Catch:{ all -> 0x0032 }
        r2.remove(r1);	 Catch:{ all -> 0x0032 }
        r2 = r4.f392b;	 Catch:{ all -> 0x0032 }
        r3 = r4.m775c(r1, r0);	 Catch:{ all -> 0x0032 }
        r2 = r2 - r3;
        r4.f392b = r2;	 Catch:{ all -> 0x0032 }
        r2 = r4.f396f;	 Catch:{ all -> 0x0032 }
        r2 = r2 + 1;
        r4.f396f = r2;	 Catch:{ all -> 0x0032 }
        monitor-exit(r4);	 Catch:{ all -> 0x0032 }
        r2 = 1;
        r3 = 0;
        r4.m779a(r2, r1, r0, r3);
        goto L_0x0000;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.util.LruCache.a(int):void");
    }

    protected void m779a(boolean z, K k, V v, V v2) {
    }

    protected V m781b(K k) {
        return null;
    }

    private int m775c(K k, V v) {
        int b = m780b(k, v);
        if (b >= 0) {
            return b;
        }
        throw new IllegalStateException("Negative size: " + k + "=" + v);
    }

    protected int m780b(K k, V v) {
        return 1;
    }

    public final synchronized String toString() {
        String format;
        int i = 0;
        synchronized (this) {
            int i2 = this.f397g + this.f398h;
            if (i2 != 0) {
                i = (this.f397g * 100) / i2;
            }
            format = String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", new Object[]{Integer.valueOf(this.f393c), Integer.valueOf(this.f397g), Integer.valueOf(this.f398h), Integer.valueOf(i)});
        }
        return format;
    }
}
