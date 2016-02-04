package io.fabric.sdk.android.services.concurrency;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class PriorityFutureTask<V> extends FutureTask<V> implements Dependency<Task>, PriorityProvider, Task {
    final Object f8306b;

    public /* synthetic */ void m13129c(Object obj) {
        m13124a((Task) obj);
    }

    public PriorityFutureTask(Callable<V> callable) {
        super(callable);
        this.f8306b = m13123a((Object) callable);
    }

    public PriorityFutureTask(Runnable runnable, V v) {
        super(runnable, v);
        this.f8306b = m13123a((Object) runnable);
    }

    public int compareTo(Object obj) {
        return ((PriorityProvider) m13122a()).compareTo(obj);
    }

    public void m13124a(Task task) {
        ((Dependency) ((PriorityProvider) m13122a())).m7830c(task);
    }

    public Collection<Task> m13128c() {
        return ((Dependency) ((PriorityProvider) m13122a())).m7829c();
    }

    public boolean m13130d() {
        return ((Dependency) ((PriorityProvider) m13122a())).m7831d();
    }

    public Priority m13126b() {
        return ((PriorityProvider) m13122a()).m7832b();
    }

    public void m13127b(boolean z) {
        ((Task) ((PriorityProvider) m13122a())).m7834b(z);
    }

    public boolean m13131f() {
        return ((Task) ((PriorityProvider) m13122a())).m7835f();
    }

    public void m13125a(Throwable th) {
        ((Task) ((PriorityProvider) m13122a())).m7833a(th);
    }

    public <T extends Dependency<Task> & PriorityProvider & Task> T m13122a() {
        return (Dependency) this.f8306b;
    }

    protected <T extends Dependency<Task> & PriorityProvider & Task> T m13123a(Object obj) {
        if (PriorityTask.m7836a(obj)) {
            return (Dependency) obj;
        }
        return new PriorityTask();
    }
}
