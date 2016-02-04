package com.google.android.gms.internal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class zzlk {
    private static final ExecutorService f6088a;

    final class zza implements ThreadFactory {
        private final ThreadFactory f6086a;
        private AtomicInteger f6087b;

        private zza() {
            this.f6086a = Executors.defaultThreadFactory();
            this.f6087b = new AtomicInteger(0);
        }

        public Thread newThread(Runnable runnable) {
            Thread newThread = this.f6086a.newThread(runnable);
            newThread.setName("GAC_Executor[" + this.f6087b.getAndIncrement() + "]");
            return newThread;
        }
    }

    static {
        f6088a = Executors.newFixedThreadPool(2, new zza());
    }

    public static ExecutorService m9204a() {
        return f6088a;
    }
}
