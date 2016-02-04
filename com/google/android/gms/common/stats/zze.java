package com.google.android.gms.common.stats;

import android.support.v4.util.SimpleArrayMap;

public class zze {
    private final long f5850a;
    private final int f5851b;
    private final SimpleArrayMap<String, Long> f5852c;

    public zze() {
        this.f5850a = 60000;
        this.f5851b = 10;
        this.f5852c = new SimpleArrayMap(10);
    }

    public zze(int i, long j) {
        this.f5850a = j;
        this.f5851b = i;
        this.f5852c = new SimpleArrayMap();
    }

    private void m8766a(long j, long j2) {
        for (int size = this.f5852c.size() - 1; size >= 0; size--) {
            if (j2 - ((Long) this.f5852c.m753c(size)).longValue() > j) {
                this.f5852c.m754d(size);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Long m8767a(java.lang.String r8) {
        /*
        r7 = this;
        r2 = android.os.SystemClock.elapsedRealtime();
        r0 = r7.f5850a;
        monitor-enter(r7);
    L_0x0007:
        r4 = r7.f5852c;	 Catch:{ all -> 0x003c }
        r4 = r4.size();	 Catch:{ all -> 0x003c }
        r5 = r7.f5851b;	 Catch:{ all -> 0x003c }
        if (r4 < r5) goto L_0x003f;
    L_0x0011:
        r7.m8766a(r0, r2);	 Catch:{ all -> 0x003c }
        r4 = 2;
        r0 = r0 / r4;
        r4 = "ConnectionTracker";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x003c }
        r5.<init>();	 Catch:{ all -> 0x003c }
        r6 = "The max capacity ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x003c }
        r6 = r7.f5851b;	 Catch:{ all -> 0x003c }
        r5 = r5.append(r6);	 Catch:{ all -> 0x003c }
        r6 = " is not enough. Current durationThreshold is: ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x003c }
        r5 = r5.append(r0);	 Catch:{ all -> 0x003c }
        r5 = r5.toString();	 Catch:{ all -> 0x003c }
        android.util.Log.w(r4, r5);	 Catch:{ all -> 0x003c }
        goto L_0x0007;
    L_0x003c:
        r0 = move-exception;
        monitor-exit(r7);	 Catch:{ all -> 0x003c }
        throw r0;
    L_0x003f:
        r0 = r7.f5852c;	 Catch:{ all -> 0x003c }
        r1 = java.lang.Long.valueOf(r2);	 Catch:{ all -> 0x003c }
        r0 = r0.put(r8, r1);	 Catch:{ all -> 0x003c }
        r0 = (java.lang.Long) r0;	 Catch:{ all -> 0x003c }
        monitor-exit(r7);	 Catch:{ all -> 0x003c }
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.stats.zze.a(java.lang.String):java.lang.Long");
    }

    public boolean m8768b(String str) {
        boolean z;
        synchronized (this) {
            z = this.f5852c.remove(str) != null;
        }
        return z;
    }
}
