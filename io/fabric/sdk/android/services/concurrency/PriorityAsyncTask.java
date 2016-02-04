package io.fabric.sdk.android.services.concurrency;

import io.fabric.sdk.android.services.concurrency.AsyncTask.Status;
import java.util.Collection;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public abstract class PriorityAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> implements Dependency<Task>, PriorityProvider, Task {
    private final PriorityTask f8184a;

    class ProxyExecutor<Result> implements Executor {
        private final Executor f8308a;
        private final PriorityAsyncTask f8309b;

        /* renamed from: io.fabric.sdk.android.services.concurrency.PriorityAsyncTask.ProxyExecutor.1 */
        class C09661 extends PriorityFutureTask<Result> {
            final /* synthetic */ ProxyExecutor f8307a;

            C09661(ProxyExecutor proxyExecutor, Runnable runnable, Object obj) {
                this.f8307a = proxyExecutor;
                super(runnable, obj);
            }

            public <T extends Dependency<Task> & PriorityProvider & Task> T m13132a() {
                return this.f8307a.f8309b;
            }
        }

        public ProxyExecutor(Executor executor, PriorityAsyncTask priorityAsyncTask) {
            this.f8308a = executor;
            this.f8309b = priorityAsyncTask;
        }

        public void execute(Runnable runnable) {
            this.f8308a.execute(new C09661(this, runnable, null));
        }
    }

    public /* synthetic */ void m12940c(Object obj) {
        m12934a((Task) obj);
    }

    public PriorityAsyncTask() {
        this.f8184a = new PriorityTask();
    }

    public final void m12936a(ExecutorService executorService, Params... paramsArr) {
        super.m12926a(new ProxyExecutor(executorService, this), (Object[]) paramsArr);
    }

    public int compareTo(Object obj) {
        return Priority.m13121a(this, obj);
    }

    public void m12934a(Task task) {
        if (e_() != Status.PENDING) {
            throw new IllegalStateException("Must not add Dependency after task is running");
        }
        ((Dependency) ((PriorityProvider) m12943g())).m7830c(task);
    }

    public Collection<Task> m12939c() {
        return ((Dependency) ((PriorityProvider) m12943g())).m7829c();
    }

    public boolean m12941d() {
        return ((Dependency) ((PriorityProvider) m12943g())).m7831d();
    }

    public Priority m12937b() {
        return ((PriorityProvider) m12943g()).m7832b();
    }

    public void m12938b(boolean z) {
        ((Task) ((PriorityProvider) m12943g())).m7834b(z);
    }

    public boolean m12942f() {
        return ((Task) ((PriorityProvider) m12943g())).m7835f();
    }

    public void m12935a(Throwable th) {
        ((Task) ((PriorityProvider) m12943g())).m7833a(th);
    }

    public <T extends Dependency<Task> & PriorityProvider & Task> T m12943g() {
        return this.f8184a;
    }
}
