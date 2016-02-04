package com.crashlytics.android;

import android.os.Looper;
import io.fabric.sdk.android.Fabric;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

class CrashlyticsExecutorServiceWrapper {
    private final ExecutorService f5351a;

    /* renamed from: com.crashlytics.android.CrashlyticsExecutorServiceWrapper.1 */
    class C06011 implements Runnable {
        final /* synthetic */ Runnable f5347a;
        final /* synthetic */ CrashlyticsExecutorServiceWrapper f5348b;

        C06011(CrashlyticsExecutorServiceWrapper crashlyticsExecutorServiceWrapper, Runnable runnable) {
            this.f5348b = crashlyticsExecutorServiceWrapper;
            this.f5347a = runnable;
        }

        public void run() {
            try {
                this.f5347a.run();
            } catch (Throwable e) {
                Fabric.m12905g().m12874d("Fabric", "Failed to execute task.", e);
            }
        }
    }

    /* renamed from: com.crashlytics.android.CrashlyticsExecutorServiceWrapper.2 */
    class C06022 implements Callable<T> {
        final /* synthetic */ Callable f5349a;
        final /* synthetic */ CrashlyticsExecutorServiceWrapper f5350b;

        C06022(CrashlyticsExecutorServiceWrapper crashlyticsExecutorServiceWrapper, Callable callable) {
            this.f5350b = crashlyticsExecutorServiceWrapper;
            this.f5349a = callable;
        }

        public T call() {
            try {
                return this.f5349a.call();
            } catch (Throwable e) {
                Fabric.m12905g().m12874d("Fabric", "Failed to execute task.", e);
                return null;
            }
        }
    }

    public CrashlyticsExecutorServiceWrapper(ExecutorService executorService) {
        this.f5351a = executorService;
    }

    <T> T m7921a(Callable<T> callable) {
        try {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                return this.f5351a.submit(callable).get(4, TimeUnit.SECONDS);
            }
            return this.f5351a.submit(callable).get();
        } catch (RejectedExecutionException e) {
            Fabric.m12905g().m12867a("Fabric", "Executor is shut down because we're handling a fatal crash.");
            return null;
        } catch (Throwable e2) {
            Fabric.m12905g().m12874d("Fabric", "Failed to execute task.", e2);
            return null;
        }
    }

    Future<?> m7922a(Runnable runnable) {
        try {
            return this.f5351a.submit(new C06011(this, runnable));
        } catch (RejectedExecutionException e) {
            Fabric.m12905g().m12867a("Fabric", "Executor is shut down because we're handling a fatal crash.");
            return null;
        }
    }

    <T> Future<T> m7923b(Callable<T> callable) {
        try {
            return this.f5351a.submit(new C06022(this, callable));
        } catch (RejectedExecutionException e) {
            Fabric.m12905g().m12867a("Fabric", "Executor is shut down because we're handling a fatal crash.");
            return null;
        }
    }
}
