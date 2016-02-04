package io.fabric.sdk.android.services.events;

import android.content.Context;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public abstract class EnabledEventsStrategy<T> implements EventsStrategy<T> {
    protected final Context f5468b;
    protected final ScheduledExecutorService f5469c;
    protected final EventsFilesManager<T> f5470d;
    protected final AtomicReference<ScheduledFuture<?>> f5471e;
    protected volatile int f5472f;

    public EnabledEventsStrategy(Context context, ScheduledExecutorService scheduledExecutorService, EventsFilesManager<T> eventsFilesManager) {
        this.f5472f = -1;
        this.f5468b = context;
        this.f5469c = scheduledExecutorService;
        this.f5470d = eventsFilesManager;
        this.f5471e = new AtomicReference();
    }

    public void m8153f() {
        Object obj = 1;
        Object obj2 = this.f5472f != -1 ? 1 : null;
        if (this.f5471e.get() != null) {
            obj = null;
        }
        if (obj2 != null && r1 != null) {
            m8147a(this.f5472f, this.f5472f);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void m8154g() {
        /*
        r10 = this;
        r1 = 0;
        r3 = r10.m8137a();
        if (r3 != 0) goto L_0x000f;
    L_0x0007:
        r0 = r10.f5468b;
        r1 = "skipping files send because we don't yet know the target endpoint";
        io.fabric.sdk.android.services.common.CommonUtils.m13014a(r0, r1);
    L_0x000e:
        return;
    L_0x000f:
        r0 = r10.f5468b;
        r2 = "Sending all files";
        io.fabric.sdk.android.services.common.CommonUtils.m13014a(r0, r2);
        r0 = r10.f5470d;
        r0 = r0.m8167e();
        r2 = r10.f5468b;	 Catch:{ Exception -> 0x0062 }
        r4 = java.util.Locale.US;	 Catch:{ Exception -> 0x0062 }
        r5 = "attempt to send batch of %d files";
        r6 = 1;
        r6 = new java.lang.Object[r6];	 Catch:{ Exception -> 0x0062 }
        r7 = 0;
        r8 = r0.size();	 Catch:{ Exception -> 0x0062 }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ Exception -> 0x0062 }
        r6[r7] = r8;	 Catch:{ Exception -> 0x0062 }
        r4 = java.lang.String.format(r4, r5, r6);	 Catch:{ Exception -> 0x0062 }
        io.fabric.sdk.android.services.common.CommonUtils.m13014a(r2, r4);	 Catch:{ Exception -> 0x0062 }
        r2 = r0;
        r0 = r1;
    L_0x0039:
        r1 = r2.size();	 Catch:{ Exception -> 0x0081 }
        if (r1 <= 0) goto L_0x0052;
    L_0x003f:
        r4 = r3.m8128a(r2);	 Catch:{ Exception -> 0x0081 }
        if (r4 == 0) goto L_0x0050;
    L_0x0045:
        r1 = r2.size();	 Catch:{ Exception -> 0x0081 }
        r1 = r1 + r0;
        r0 = r10.f5470d;	 Catch:{ Exception -> 0x0062 }
        r0.m8163a(r2);	 Catch:{ Exception -> 0x0062 }
        r0 = r1;
    L_0x0050:
        if (r4 != 0) goto L_0x005a;
    L_0x0052:
        if (r0 != 0) goto L_0x000e;
    L_0x0054:
        r0 = r10.f5470d;
        r0.m8169g();
        goto L_0x000e;
    L_0x005a:
        r1 = r10.f5470d;	 Catch:{ Exception -> 0x0081 }
        r1 = r1.m8167e();	 Catch:{ Exception -> 0x0081 }
        r2 = r1;
        goto L_0x0039;
    L_0x0062:
        r0 = move-exception;
    L_0x0063:
        r2 = r10.f5468b;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "Failed to send batch of analytics files to server: ";
        r3 = r3.append(r4);
        r4 = r0.getMessage();
        r3 = r3.append(r4);
        r3 = r3.toString();
        io.fabric.sdk.android.services.common.CommonUtils.m13015a(r2, r3, r0);
        r0 = r1;
        goto L_0x0052;
    L_0x0081:
        r1 = move-exception;
        r9 = r1;
        r1 = r0;
        r0 = r9;
        goto L_0x0063;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.fabric.sdk.android.services.events.EnabledEventsStrategy.g():void");
    }

    public void m8149b() {
        m8154g();
    }

    protected void m8147a(int i, int i2) {
        try {
            Runnable timeBasedFileRollOverRunnable = new TimeBasedFileRollOverRunnable(this.f5468b, this);
            CommonUtils.m13014a(this.f5468b, "Scheduling time based file roll over every " + i2 + " seconds");
            this.f5471e.set(this.f5469c.scheduleAtFixedRate(timeBasedFileRollOverRunnable, (long) i, (long) i2, TimeUnit.SECONDS));
        } catch (Throwable e) {
            CommonUtils.m13015a(this.f5468b, "Failed to schedule time based file roll over", e);
        }
    }

    public void m8151d() {
        if (this.f5471e.get() != null) {
            CommonUtils.m13014a(this.f5468b, "Cancelling time-based rollover because no events are currently being generated.");
            ((ScheduledFuture) this.f5471e.get()).cancel(false);
            this.f5471e.set(null);
        }
    }

    protected void m8146a(int i) {
        this.f5472f = i;
        m8147a(0, this.f5472f);
    }

    public void m8150c() {
        this.f5470d.m8168f();
    }

    public void m8148a(T t) {
        CommonUtils.m13014a(this.f5468b, t.toString());
        try {
            this.f5470d.m8162a((Object) t);
        } catch (Throwable e) {
            CommonUtils.m13015a(this.f5468b, "Failed to write event.", e);
        }
        m8153f();
    }

    public boolean m8152e() {
        try {
            return this.f5470d.m8166d();
        } catch (Throwable e) {
            CommonUtils.m13015a(this.f5468b, "Failed to roll file over.", e);
            return false;
        }
    }
}
