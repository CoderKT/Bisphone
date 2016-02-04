package org.jivesoftware.smack.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BoundedThreadPoolExecutor extends ThreadPoolExecutor {
    private final Semaphore semaphore;

    /* renamed from: org.jivesoftware.smack.util.BoundedThreadPoolExecutor.1 */
    class C10241 implements Runnable {
        final /* synthetic */ Runnable val$command;

        C10241(Runnable runnable) {
            this.val$command = runnable;
        }

        public void run() {
            try {
                this.val$command.run();
            } finally {
                BoundedThreadPoolExecutor.this.semaphore.release();
            }
        }
    }

    public BoundedThreadPoolExecutor(int i, int i2, long j, TimeUnit timeUnit, int i3, ThreadFactory threadFactory) {
        super(i, i2, j, timeUnit, new ArrayBlockingQueue(i3), threadFactory);
        this.semaphore = new Semaphore(i3);
    }

    public void executeBlocking(Runnable runnable) {
        this.semaphore.acquire();
        try {
            execute(new C10241(runnable));
        } catch (Throwable e) {
            this.semaphore.release();
            if (e instanceof RejectedExecutionException) {
                throw ((RejectedExecutionException) e);
            }
            throw new RuntimeException(e);
        }
    }
}
