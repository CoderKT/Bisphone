package io.fabric.sdk.android.services.concurrency;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class PriorityTask implements Dependency<Task>, PriorityProvider, Task {
    private final List<Task> f5302a;
    private final AtomicBoolean f5303b;
    private final AtomicReference<Throwable> f5304c;

    public PriorityTask() {
        this.f5302a = new ArrayList();
        this.f5303b = new AtomicBoolean(false);
        this.f5304c = new AtomicReference(null);
    }

    public /* synthetic */ void m7842c(Object obj) {
        m7837a((Task) obj);
    }

    public synchronized Collection<Task> m7841c() {
        return Collections.unmodifiableCollection(this.f5302a);
    }

    public synchronized void m7837a(Task task) {
        this.f5302a.add(task);
    }

    public boolean m7843d() {
        for (Task f : m7841c()) {
            if (!f.m7835f()) {
                return false;
            }
        }
        return true;
    }

    public synchronized void m7840b(boolean z) {
        this.f5303b.set(z);
    }

    public boolean m7844f() {
        return this.f5303b.get();
    }

    public Priority m7839b() {
        return Priority.NORMAL;
    }

    public void m7838a(Throwable th) {
        this.f5304c.set(th);
    }

    public int compareTo(Object obj) {
        return Priority.m13121a(this, obj);
    }

    public static boolean m7836a(Object obj) {
        try {
            Dependency dependency = (Dependency) obj;
            Task task = (Task) obj;
            PriorityProvider priorityProvider = (PriorityProvider) obj;
            if (dependency == null || task == null || priorityProvider == null) {
                return false;
            }
            return true;
        } catch (ClassCastException e) {
            return false;
        }
    }
}
