package de.greenrobot.event;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventBusBuilder {
    private static final ExecutorService f7961i;
    boolean f7962a;
    boolean f7963b;
    boolean f7964c;
    boolean f7965d;
    boolean f7966e;
    boolean f7967f;
    ExecutorService f7968g;
    List<Class<?>> f7969h;

    static {
        f7961i = Executors.newCachedThreadPool();
    }

    EventBusBuilder() {
        this.f7962a = true;
        this.f7963b = true;
        this.f7964c = true;
        this.f7965d = true;
        this.f7967f = true;
        this.f7968g = f7961i;
    }
}
