package de.greenrobot.event;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;

final class HandlerPoster extends Handler {
    private final PendingPostQueue f7970a;
    private final int f7971b;
    private final EventBus f7972c;
    private boolean f7973d;

    HandlerPoster(EventBus eventBus, Looper looper, int i) {
        super(looper);
        this.f7972c = eventBus;
        this.f7971b = i;
        this.f7970a = new PendingPostQueue();
    }

    void m12798a(Subscription subscription, Object obj) {
        PendingPost a = PendingPost.m12799a(subscription, obj);
        synchronized (this) {
            this.f7970a.m12803a(a);
            if (!this.f7973d) {
                this.f7973d = true;
                if (!sendMessage(obtainMessage())) {
                    throw new EventBusException("Could not send handler message");
                }
            }
        }
    }

    public void handleMessage(Message message) {
        try {
            long uptimeMillis = SystemClock.uptimeMillis();
            do {
                PendingPost a = this.f7970a.m12801a();
                if (a == null) {
                    synchronized (this) {
                        a = this.f7970a.m12801a();
                        if (a == null) {
                            this.f7973d = false;
                            this.f7973d = false;
                            return;
                        }
                    }
                }
                this.f7972c.m12789a(a);
            } while (SystemClock.uptimeMillis() - uptimeMillis < ((long) this.f7971b));
            if (sendMessage(obtainMessage())) {
                this.f7973d = true;
                return;
            }
            throw new EventBusException("Could not send handler message");
        } catch (Throwable th) {
            this.f7973d = false;
        }
    }
}
