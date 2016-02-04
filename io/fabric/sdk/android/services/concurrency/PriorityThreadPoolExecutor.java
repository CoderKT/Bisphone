package io.fabric.sdk.android.services.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PriorityThreadPoolExecutor extends ThreadPoolExecutor {
    private static final int f8311a;
    private static final int f8312b;
    private static final int f8313c;

    public final class PriorityThreadFactory implements ThreadFactory {
        private final int f8310a;

        public PriorityThreadFactory(int i) {
            this.f8310a = i;
        }

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setPriority(this.f8310a);
            thread.setName("Queue");
            return thread;
        }
    }

    public /* synthetic */ BlockingQueue getQueue() {
        return m13136b();
    }

    static {
        f8311a = Runtime.getRuntime().availableProcessors();
        f8312b = f8311a + 1;
        f8313c = (f8311a * 2) + 1;
    }

    <T extends Runnable & Dependency & Task & PriorityProvider> PriorityThreadPoolExecutor(int i, int i2, long j, TimeUnit timeUnit, DependencyPriorityBlockingQueue<T> dependencyPriorityBlockingQueue, ThreadFactory threadFactory) {
        super(i, i2, j, timeUnit, dependencyPriorityBlockingQueue, threadFactory);
        prestartAllCoreThreads();
    }

    public static <T extends Runnable & Dependency & Task & PriorityProvider> PriorityThreadPoolExecutor m13135a(int i, int i2) {
        return new PriorityThreadPoolExecutor(i, i2, 1, TimeUnit.SECONDS, new DependencyPriorityBlockingQueue(), new PriorityThreadFactory(10));
    }

    public static PriorityThreadPoolExecutor m13134a() {
        return m13135a(f8312b, f8313c);
    }

    protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return new PriorityFutureTask(runnable, t);
    }

    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new PriorityFutureTask(callable);
    }

    public void execute(Runnable runnable) {
        if (PriorityTask.m7836a((Object) runnable)) {
            super.execute(runnable);
        } else {
            super.execute(newTaskFor(runnable, null));
        }
    }

    protected void afterExecute(Runnable runnable, Throwable th) {
        Task task = (Task) runnable;
        task.m7834b(true);
        task.m7833a(th);
        m13136b().m13119d();
        super.afterExecute(runnable, th);
    }

    public DependencyPriorityBlockingQueue m13136b() {
        return (DependencyPriorityBlockingQueue) super.getQueue();
    }
}
