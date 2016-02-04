package com.nostra13.universalimageloader.cache.memory.impl;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.cache.memory.MemoryCache;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class LruMemoryCache implements MemoryCache {
    private final LinkedHashMap<String, Bitmap> f6851a;
    private final int f6852b;
    private int f6853c;

    public LruMemoryCache(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.f6852b = i;
        this.f6851a = new LinkedHashMap(0, 0.75f, true);
    }

    public final Bitmap m10981a(String str) {
        if (str == null) {
            throw new NullPointerException("key == null");
        }
        Bitmap bitmap;
        synchronized (this) {
            bitmap = (Bitmap) this.f6851a.get(str);
        }
        return bitmap;
    }

    public final boolean m10983a(String str, Bitmap bitmap) {
        if (str == null || bitmap == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.f6853c += m10980b(str, bitmap);
            Bitmap bitmap2 = (Bitmap) this.f6851a.put(str, bitmap);
            if (bitmap2 != null) {
                this.f6853c -= m10980b(str, bitmap2);
            }
        }
        m10979a(this.f6852b);
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m10979a(int r4) {
        /*
        r3 = this;
    L_0x0000:
        monitor-enter(r3);
        r0 = r3.f6853c;	 Catch:{ all -> 0x0032 }
        if (r0 < 0) goto L_0x0011;
    L_0x0005:
        r0 = r3.f6851a;	 Catch:{ all -> 0x0032 }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x0032 }
        if (r0 == 0) goto L_0x0035;
    L_0x000d:
        r0 = r3.f6853c;	 Catch:{ all -> 0x0032 }
        if (r0 == 0) goto L_0x0035;
    L_0x0011:
        r0 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0032 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0032 }
        r1.<init>();	 Catch:{ all -> 0x0032 }
        r2 = r3.getClass();	 Catch:{ all -> 0x0032 }
        r2 = r2.getName();	 Catch:{ all -> 0x0032 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0032 }
        r2 = ".sizeOf() is reporting inconsistent results!";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0032 }
        r1 = r1.toString();	 Catch:{ all -> 0x0032 }
        r0.<init>(r1);	 Catch:{ all -> 0x0032 }
        throw r0;	 Catch:{ all -> 0x0032 }
    L_0x0032:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0032 }
        throw r0;
    L_0x0035:
        r0 = r3.f6853c;	 Catch:{ all -> 0x0032 }
        if (r0 <= r4) goto L_0x0041;
    L_0x0039:
        r0 = r3.f6851a;	 Catch:{ all -> 0x0032 }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x0032 }
        if (r0 == 0) goto L_0x0043;
    L_0x0041:
        monitor-exit(r3);	 Catch:{ all -> 0x0032 }
    L_0x0042:
        return;
    L_0x0043:
        r0 = r3.f6851a;	 Catch:{ all -> 0x0032 }
        r0 = r0.entrySet();	 Catch:{ all -> 0x0032 }
        r0 = r0.iterator();	 Catch:{ all -> 0x0032 }
        r0 = r0.next();	 Catch:{ all -> 0x0032 }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ all -> 0x0032 }
        if (r0 != 0) goto L_0x0057;
    L_0x0055:
        monitor-exit(r3);	 Catch:{ all -> 0x0032 }
        goto L_0x0042;
    L_0x0057:
        r1 = r0.getKey();	 Catch:{ all -> 0x0032 }
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x0032 }
        r0 = r0.getValue();	 Catch:{ all -> 0x0032 }
        r0 = (android.graphics.Bitmap) r0;	 Catch:{ all -> 0x0032 }
        r2 = r3.f6851a;	 Catch:{ all -> 0x0032 }
        r2.remove(r1);	 Catch:{ all -> 0x0032 }
        r2 = r3.f6853c;	 Catch:{ all -> 0x0032 }
        r0 = r3.m10980b(r1, r0);	 Catch:{ all -> 0x0032 }
        r0 = r2 - r0;
        r3.f6853c = r0;	 Catch:{ all -> 0x0032 }
        monitor-exit(r3);	 Catch:{ all -> 0x0032 }
        goto L_0x0000;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache.a(int):void");
    }

    public final Bitmap m10984b(String str) {
        if (str == null) {
            throw new NullPointerException("key == null");
        }
        Bitmap bitmap;
        synchronized (this) {
            bitmap = (Bitmap) this.f6851a.remove(str);
            if (bitmap != null) {
                this.f6853c -= m10980b(str, bitmap);
            }
        }
        return bitmap;
    }

    public Collection<String> m10982a() {
        Collection hashSet;
        synchronized (this) {
            hashSet = new HashSet(this.f6851a.keySet());
        }
        return hashSet;
    }

    public void m10985b() {
        m10979a(-1);
    }

    private int m10980b(String str, Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    public final synchronized String toString() {
        return String.format("LruCache[maxSize=%d]", new Object[]{Integer.valueOf(this.f6852b)});
    }
}
