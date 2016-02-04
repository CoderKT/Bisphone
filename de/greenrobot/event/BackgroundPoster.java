package de.greenrobot.event;

final class BackgroundPoster implements Runnable {
    private final PendingPostQueue f7931a;
    private final EventBus f7932b;
    private volatile boolean f7933c;

    BackgroundPoster(EventBus eventBus) {
        this.f7932b = eventBus;
        this.f7931a = new PendingPostQueue();
    }

    public void m12777a(Subscription subscription, Object obj) {
        PendingPost a = PendingPost.m12799a(subscription, obj);
        synchronized (this) {
            this.f7931a.m12803a(a);
            if (!this.f7933c) {
                this.f7933c = true;
                this.f7932b.m12792b().execute(this);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r5 = this;
        r4 = 0;
    L_0x0001:
        r0 = r5.f7931a;	 Catch:{ InterruptedException -> 0x0022 }
        r1 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r0 = r0.m12802a(r1);	 Catch:{ InterruptedException -> 0x0022 }
        if (r0 != 0) goto L_0x001c;
    L_0x000b:
        monitor-enter(r5);	 Catch:{ InterruptedException -> 0x0022 }
        r0 = r5.f7931a;	 Catch:{ all -> 0x0046 }
        r0 = r0.m12801a();	 Catch:{ all -> 0x0046 }
        if (r0 != 0) goto L_0x001b;
    L_0x0014:
        r0 = 0;
        r5.f7933c = r0;	 Catch:{ all -> 0x0046 }
        monitor-exit(r5);	 Catch:{ all -> 0x0046 }
        r5.f7933c = r4;
    L_0x001a:
        return;
    L_0x001b:
        monitor-exit(r5);	 Catch:{ all -> 0x0046 }
    L_0x001c:
        r1 = r5.f7932b;	 Catch:{ InterruptedException -> 0x0022 }
        r1.m12789a(r0);	 Catch:{ InterruptedException -> 0x0022 }
        goto L_0x0001;
    L_0x0022:
        r0 = move-exception;
        r1 = "Event";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0049 }
        r2.<init>();	 Catch:{ all -> 0x0049 }
        r3 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x0049 }
        r3 = r3.getName();	 Catch:{ all -> 0x0049 }
        r2 = r2.append(r3);	 Catch:{ all -> 0x0049 }
        r3 = " was interruppted";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0049 }
        r2 = r2.toString();	 Catch:{ all -> 0x0049 }
        android.util.Log.w(r1, r2, r0);	 Catch:{ all -> 0x0049 }
        r5.f7933c = r4;
        goto L_0x001a;
    L_0x0046:
        r0 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0046 }
        throw r0;	 Catch:{ InterruptedException -> 0x0022 }
    L_0x0049:
        r0 = move-exception;
        r5.f7933c = r4;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: de.greenrobot.event.BackgroundPoster.run():void");
    }
}
