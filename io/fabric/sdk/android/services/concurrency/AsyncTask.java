package io.fabric.sdk.android.services.concurrency;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import se.emilsjolander.stickylistheaders.C1128R;

public abstract class AsyncTask<Params, Progress, Result> {
    private static final int f8170a;
    public static final Executor f8171b;
    public static final Executor f8172c;
    private static final int f8173d;
    private static final int f8174e;
    private static final ThreadFactory f8175f;
    private static final BlockingQueue<Runnable> f8176g;
    private static final InternalHandler f8177h;
    private static volatile Executor f8178i;
    private final WorkerRunnable<Params, Result> f8179j;
    private final FutureTask<Result> f8180k;
    private volatile Status f8181l;
    private final AtomicBoolean f8182m;
    private final AtomicBoolean f8183n;

    /* renamed from: io.fabric.sdk.android.services.concurrency.AsyncTask.1 */
    final class C09611 implements ThreadFactory {
        private final AtomicInteger f8284a;

        C09611() {
            this.f8284a = new AtomicInteger(1);
        }

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "AsyncTask #" + this.f8284a.getAndIncrement());
        }
    }

    abstract class WorkerRunnable<Params, Result> implements Callable<Result> {
        Params[] f8285b;

        private WorkerRunnable() {
        }
    }

    /* renamed from: io.fabric.sdk.android.services.concurrency.AsyncTask.2 */
    class C09622 extends WorkerRunnable<Params, Result> {
        final /* synthetic */ AsyncTask f8286a;

        C09622(AsyncTask asyncTask) {
            this.f8286a = asyncTask;
            super();
        }

        public Result call() {
            this.f8286a.f8183n.set(true);
            Process.setThreadPriority(10);
            return this.f8286a.m12924e(this.f8286a.m12927a(this.b));
        }
    }

    /* renamed from: io.fabric.sdk.android.services.concurrency.AsyncTask.3 */
    class C09633 extends FutureTask<Result> {
        final /* synthetic */ AsyncTask f8287a;

        C09633(AsyncTask asyncTask, Callable callable) {
            this.f8287a = asyncTask;
            super(callable);
        }

        protected void done() {
            try {
                this.f8287a.m12923d(get());
            } catch (Throwable e) {
                Log.w("AsyncTask", e);
            } catch (ExecutionException e2) {
                throw new RuntimeException("An error occured while executing doInBackground()", e2.getCause());
            } catch (CancellationException e3) {
                this.f8287a.m12923d(null);
            }
        }
    }

    /* renamed from: io.fabric.sdk.android.services.concurrency.AsyncTask.4 */
    /* synthetic */ class C09644 {
        static final /* synthetic */ int[] f8288a;

        static {
            f8288a = new int[Status.values().length];
            try {
                f8288a[Status.RUNNING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f8288a[Status.FINISHED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    class AsyncTaskResult<Data> {
        final AsyncTask f8289a;
        final Data[] f8290b;

        AsyncTaskResult(AsyncTask asyncTask, Data... dataArr) {
            this.f8289a = asyncTask;
            this.f8290b = dataArr;
        }
    }

    class InternalHandler extends Handler {
        public InternalHandler() {
            super(Looper.getMainLooper());
        }

        public void handleMessage(Message message) {
            AsyncTaskResult asyncTaskResult = (AsyncTaskResult) message.obj;
            switch (message.what) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    asyncTaskResult.f8289a.m12925f(asyncTaskResult.f8290b[0]);
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    asyncTaskResult.f8289a.m12932b(asyncTaskResult.f8290b);
                default:
            }
        }
    }

    class SerialExecutor implements Executor {
        final LinkedList<Runnable> f8293a;
        Runnable f8294b;

        /* renamed from: io.fabric.sdk.android.services.concurrency.AsyncTask.SerialExecutor.1 */
        class C09651 implements Runnable {
            final /* synthetic */ Runnable f8291a;
            final /* synthetic */ SerialExecutor f8292b;

            C09651(SerialExecutor serialExecutor, Runnable runnable) {
                this.f8292b = serialExecutor;
                this.f8291a = runnable;
            }

            public void run() {
                try {
                    this.f8291a.run();
                } finally {
                    this.f8292b.m13109a();
                }
            }
        }

        private SerialExecutor() {
            this.f8293a = new LinkedList();
        }

        public synchronized void execute(Runnable runnable) {
            this.f8293a.offer(new C09651(this, runnable));
            if (this.f8294b == null) {
                m13109a();
            }
        }

        protected synchronized void m13109a() {
            Runnable runnable = (Runnable) this.f8293a.poll();
            this.f8294b = runnable;
            if (runnable != null) {
                AsyncTask.f8171b.execute(this.f8294b);
            }
        }
    }

    public enum Status {
        PENDING,
        RUNNING,
        FINISHED
    }

    protected abstract Result m12927a(Params... paramsArr);

    static {
        f8170a = Runtime.getRuntime().availableProcessors();
        f8173d = f8170a + 1;
        f8174e = (f8170a * 2) + 1;
        f8175f = new C09611();
        f8176g = new LinkedBlockingQueue(128);
        f8171b = new ThreadPoolExecutor(f8173d, f8174e, 1, TimeUnit.SECONDS, f8176g, f8175f);
        f8172c = new SerialExecutor();
        f8177h = new InternalHandler();
        f8178i = f8172c;
    }

    public AsyncTask() {
        this.f8181l = Status.PENDING;
        this.f8182m = new AtomicBoolean();
        this.f8183n = new AtomicBoolean();
        this.f8179j = new C09622(this);
        this.f8180k = new C09633(this, this.f8179j);
    }

    private void m12923d(Result result) {
        if (!this.f8183n.get()) {
            m12924e(result);
        }
    }

    private Result m12924e(Result result) {
        f8177h.obtainMessage(1, new AsyncTaskResult(this, result)).sendToTarget();
        return result;
    }

    public final Status e_() {
        return this.f8181l;
    }

    protected void m12928a() {
    }

    protected void m12929a(Result result) {
    }

    protected void m12932b(Progress... progressArr) {
    }

    protected void m12931b(Result result) {
        f_();
    }

    protected void f_() {
    }

    public final boolean m12933e() {
        return this.f8182m.get();
    }

    public final boolean m12930a(boolean z) {
        this.f8182m.set(true);
        return this.f8180k.cancel(z);
    }

    public final AsyncTask<Params, Progress, Result> m12926a(Executor executor, Params... paramsArr) {
        if (this.f8181l != Status.PENDING) {
            switch (C09644.f8288a[this.f8181l.ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    throw new IllegalStateException("Cannot execute task: the task is already running.");
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        this.f8181l = Status.RUNNING;
        m12928a();
        this.f8179j.f8285b = paramsArr;
        executor.execute(this.f8180k);
        return this;
    }

    private void m12925f(Result result) {
        if (m12933e()) {
            m12931b((Object) result);
        } else {
            m12929a((Object) result);
        }
        this.f8181l = Status.FINISHED;
    }
}
