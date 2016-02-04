package io.fabric.sdk.android.services.common;

import io.fabric.sdk.android.Fabric;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public final class ExecutorUtils {

    /* renamed from: io.fabric.sdk.android.services.common.ExecutorUtils.1 */
    final class C09571 implements ThreadFactory {
        final /* synthetic */ String f8235a;
        final /* synthetic */ AtomicLong f8236b;

        /* renamed from: io.fabric.sdk.android.services.common.ExecutorUtils.1.1 */
        class C09561 extends BackgroundPriorityRunnable {
            final /* synthetic */ Runnable f8233a;
            final /* synthetic */ C09571 f8234b;

            C09561(C09571 c09571, Runnable runnable) {
                this.f8234b = c09571;
                this.f8233a = runnable;
            }

            public void m13044a() {
                this.f8233a.run();
            }
        }

        C09571(String str, AtomicLong atomicLong) {
            this.f8235a = str;
            this.f8236b = atomicLong;
        }

        public Thread newThread(Runnable runnable) {
            Thread newThread = Executors.defaultThreadFactory().newThread(new C09561(this, runnable));
            newThread.setName(this.f8235a + this.f8236b.getAndIncrement());
            return newThread;
        }
    }

    /* renamed from: io.fabric.sdk.android.services.common.ExecutorUtils.2 */
    final class C09582 extends BackgroundPriorityRunnable {
        final /* synthetic */ String f8237a;
        final /* synthetic */ ExecutorService f8238b;
        final /* synthetic */ long f8239c;
        final /* synthetic */ TimeUnit f8240d;

        C09582(String str, ExecutorService executorService, long j, TimeUnit timeUnit) {
            this.f8237a = str;
            this.f8238b = executorService;
            this.f8239c = j;
            this.f8240d = timeUnit;
        }

        public void m13045a() {
            try {
                Fabric.m12905g().m12867a("Fabric", "Executing shutdown hook for " + this.f8237a);
                this.f8238b.shutdown();
                if (!this.f8238b.awaitTermination(this.f8239c, this.f8240d)) {
                    Fabric.m12905g().m12867a("Fabric", this.f8237a + " did not shut down in the" + " allocated time. Requesting immediate shutdown.");
                    this.f8238b.shutdownNow();
                }
            } catch (InterruptedException e) {
                Fabric.m12905g().m12867a("Fabric", String.format(Locale.US, "Interrupted while waiting for %s to shut down. Requesting immediate shutdown.", new Object[]{this.f8237a}));
                this.f8238b.shutdownNow();
            }
        }
    }

    public static ExecutorService m13046a(String str) {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor(m13050c(str));
        m13047a(str, newSingleThreadExecutor);
        return newSingleThreadExecutor;
    }

    public static ScheduledExecutorService m13049b(String str) {
        Object newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor(m13050c(str));
        m13047a(str, newSingleThreadScheduledExecutor);
        return newSingleThreadScheduledExecutor;
    }

    public static final ThreadFactory m13050c(String str) {
        return new C09571(str, new AtomicLong(1));
    }

    private static final void m13047a(String str, ExecutorService executorService) {
        m13048a(str, executorService, 2, TimeUnit.SECONDS);
    }

    public static final void m13048a(String str, ExecutorService executorService, long j, TimeUnit timeUnit) {
        Runtime.getRuntime().addShutdownHook(new Thread(new C09582(str, executorService, j, timeUnit), "Crashlytics Shutdown Hook for " + str));
    }
}
