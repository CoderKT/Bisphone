package io.fabric.sdk.android;

import android.content.Context;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.concurrency.DependsOn;
import io.fabric.sdk.android.services.concurrency.Task;
import java.io.File;
import java.util.Collection;

public abstract class Kit<Result> implements Comparable<Kit> {
    Fabric f5322b;
    InitializationTask<Result> f5323c;
    Context f5324d;
    InitializationCallback<Result> f5325e;
    IdManager f5326f;

    public abstract String m7871c();

    public abstract String m7872d();

    protected abstract Result m7873z();

    public /* synthetic */ int compareTo(Object obj) {
        return m7865a((Kit) obj);
    }

    public Kit() {
        this.f5323c = new InitializationTask(this);
    }

    void m7866a(Context context, Fabric fabric, InitializationCallback<Result> initializationCallback, IdManager idManager) {
        this.f5322b = fabric;
        this.f5324d = new FabricContext(context, m7871c(), m7862E());
        this.f5325e = initializationCallback;
        this.f5326f = idManager;
    }

    final void m7858A() {
        this.f5323c.m12936a(this.f5322b.m12915e(), (Void) null);
    }

    protected boolean m7868a() {
        return true;
    }

    protected void m7867a(Result result) {
    }

    protected void m7869b(Result result) {
    }

    protected IdManager m7859B() {
        return this.f5326f;
    }

    public Context m7860C() {
        return this.f5324d;
    }

    public Fabric m7861D() {
        return this.f5322b;
    }

    public String m7862E() {
        return ".Fabric" + File.separator + m7871c();
    }

    public int m7865a(Kit kit) {
        if (m7870b(kit)) {
            return 1;
        }
        if (kit.m7870b(this)) {
            return -1;
        }
        if (m7863F() && !kit.m7863F()) {
            return 1;
        }
        if (m7863F() || !kit.m7863F()) {
            return 0;
        }
        return -1;
    }

    boolean m7870b(Kit kit) {
        DependsOn dependsOn = (DependsOn) getClass().getAnnotation(DependsOn.class);
        if (dependsOn != null) {
            for (Object equals : dependsOn.m13120a()) {
                if (equals.equals(kit.getClass())) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean m7863F() {
        return ((DependsOn) getClass().getAnnotation(DependsOn.class)) != null;
    }

    protected Collection<Task> m7864G() {
        return this.f5323c.m12939c();
    }
}
