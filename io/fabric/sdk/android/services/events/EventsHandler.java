package io.fabric.sdk.android.services.events;

import android.content.Context;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.util.concurrent.ScheduledExecutorService;

public abstract class EventsHandler<T> implements EventsStorageListener {
    protected final Context f5517a;
    protected final ScheduledExecutorService f5518b;
    protected EventsStrategy<T> f5519c;

    /* renamed from: io.fabric.sdk.android.services.events.EventsHandler.1 */
    class C09681 implements Runnable {
        final /* synthetic */ Object f8317a;
        final /* synthetic */ boolean f8318b;
        final /* synthetic */ EventsHandler f8319c;

        C09681(EventsHandler eventsHandler, Object obj, boolean z) {
            this.f8319c = eventsHandler;
            this.f8317a = obj;
            this.f8318b = z;
        }

        public void run() {
            try {
                this.f8319c.f5519c.m8132a(this.f8317a);
                if (this.f8318b) {
                    this.f8319c.f5519c.m8136e();
                }
            } catch (Throwable e) {
                CommonUtils.m13015a(this.f8319c.f5517a, "Failed to record event.", e);
            }
        }
    }

    /* renamed from: io.fabric.sdk.android.services.events.EventsHandler.2 */
    class C09692 implements Runnable {
        final /* synthetic */ Object f8320a;
        final /* synthetic */ EventsHandler f8321b;

        C09692(EventsHandler eventsHandler, Object obj) {
            this.f8321b = eventsHandler;
            this.f8320a = obj;
        }

        public void run() {
            try {
                this.f8321b.f5519c.m8132a(this.f8320a);
            } catch (Throwable e) {
                CommonUtils.m13015a(this.f8321b.f5517a, "Crashlytics failed to record event", e);
            }
        }
    }

    /* renamed from: io.fabric.sdk.android.services.events.EventsHandler.3 */
    class C09703 implements Runnable {
        final /* synthetic */ EventsHandler f8322a;

        C09703(EventsHandler eventsHandler) {
            this.f8322a = eventsHandler;
        }

        public void run() {
            try {
                this.f8322a.f5519c.m8133b();
            } catch (Throwable e) {
                CommonUtils.m13015a(this.f8322a.f5517a, "Failed to send events files.", e);
            }
        }
    }

    /* renamed from: io.fabric.sdk.android.services.events.EventsHandler.4 */
    class C09714 implements Runnable {
        final /* synthetic */ EventsHandler f8323a;

        C09714(EventsHandler eventsHandler) {
            this.f8323a = eventsHandler;
        }

        public void run() {
            try {
                EventsStrategy eventsStrategy = this.f8323a.f5519c;
                this.f8323a.f5519c = this.f8323a.m8185a();
                eventsStrategy.m8134c();
            } catch (Throwable e) {
                CommonUtils.m13015a(this.f8323a.f5517a, "Failed to disable events.", e);
            }
        }
    }

    protected abstract EventsStrategy<T> m8185a();

    public EventsHandler(Context context, EventsStrategy<T> eventsStrategy, EventsFilesManager eventsFilesManager, ScheduledExecutorService scheduledExecutorService) {
        this.f5517a = context.getApplicationContext();
        this.f5518b = scheduledExecutorService;
        this.f5519c = eventsStrategy;
        eventsFilesManager.m8161a((EventsStorageListener) this);
    }

    public void m8187a(T t, boolean z) {
        m8191b(new C09681(this, t, z));
    }

    public void m8186a(T t) {
        m8188a(new C09692(this, t));
    }

    public void m8189a(String str) {
        m8191b(new C09703(this));
    }

    public void m8190b() {
        m8191b(new C09714(this));
    }

    protected void m8188a(Runnable runnable) {
        try {
            this.f5518b.submit(runnable).get();
        } catch (Throwable e) {
            CommonUtils.m13015a(this.f5517a, "Failed to run events task", e);
        }
    }

    protected void m8191b(Runnable runnable) {
        try {
            this.f5518b.submit(runnable);
        } catch (Throwable e) {
            CommonUtils.m13015a(this.f5517a, "Failed to submit events task", e);
        }
    }
}
