package com.nostra13.universalimageloader.core;

import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

class ImageLoaderEngine {
    final ImageLoaderConfiguration f6961a;
    private Executor f6962b;
    private Executor f6963c;
    private Executor f6964d;
    private final Map<Integer, String> f6965e;
    private final Map<String, ReentrantLock> f6966f;
    private final AtomicBoolean f6967g;
    private final AtomicBoolean f6968h;
    private final AtomicBoolean f6969i;
    private final Object f6970j;

    /* renamed from: com.nostra13.universalimageloader.core.ImageLoaderEngine.1 */
    class C09181 implements Runnable {
        final /* synthetic */ LoadAndDisplayImageTask f6959a;
        final /* synthetic */ ImageLoaderEngine f6960b;

        C09181(ImageLoaderEngine imageLoaderEngine, LoadAndDisplayImageTask loadAndDisplayImageTask) {
            this.f6960b = imageLoaderEngine;
            this.f6959a = loadAndDisplayImageTask;
        }

        public void run() {
            File a = this.f6960b.f6961a.f6953o.m10900a(this.f6959a.m11164a());
            Object obj = (a == null || !a.exists()) ? null : 1;
            this.f6960b.m11127g();
            if (obj != null) {
                this.f6960b.f6963c.execute(this.f6959a);
            } else {
                this.f6960b.f6962b.execute(this.f6959a);
            }
        }
    }

    ImageLoaderEngine(ImageLoaderConfiguration imageLoaderConfiguration) {
        this.f6965e = Collections.synchronizedMap(new HashMap());
        this.f6966f = new WeakHashMap();
        this.f6967g = new AtomicBoolean(false);
        this.f6968h = new AtomicBoolean(false);
        this.f6969i = new AtomicBoolean(false);
        this.f6970j = new Object();
        this.f6961a = imageLoaderConfiguration;
        this.f6962b = imageLoaderConfiguration.f6945g;
        this.f6963c = imageLoaderConfiguration.f6946h;
        this.f6964d = DefaultConfigurationFactory.m10991a();
    }

    void m11132a(LoadAndDisplayImageTask loadAndDisplayImageTask) {
        this.f6964d.execute(new C09181(this, loadAndDisplayImageTask));
    }

    void m11133a(ProcessAndDisplayImageTask processAndDisplayImageTask) {
        m11127g();
        this.f6963c.execute(processAndDisplayImageTask);
    }

    private void m11127g() {
        if (!this.f6961a.f6947i && ((ExecutorService) this.f6962b).isShutdown()) {
            this.f6962b = m11128h();
        }
        if (!this.f6961a.f6948j && ((ExecutorService) this.f6963c).isShutdown()) {
            this.f6963c = m11128h();
        }
    }

    private Executor m11128h() {
        return DefaultConfigurationFactory.m10992a(this.f6961a.f6949k, this.f6961a.f6950l, this.f6961a.f6951m);
    }

    String m11129a(ImageAware imageAware) {
        return (String) this.f6965e.get(Integer.valueOf(imageAware.m11233f()));
    }

    void m11134a(ImageAware imageAware, String str) {
        this.f6965e.put(Integer.valueOf(imageAware.m11233f()), str);
    }

    void m11137b(ImageAware imageAware) {
        this.f6965e.remove(Integer.valueOf(imageAware.m11233f()));
    }

    void m11131a() {
        this.f6967g.set(true);
    }

    void m11136b() {
        this.f6967g.set(false);
        synchronized (this.f6970j) {
            this.f6970j.notifyAll();
        }
    }

    void m11135a(Runnable runnable) {
        this.f6964d.execute(runnable);
    }

    ReentrantLock m11130a(String str) {
        ReentrantLock reentrantLock = (ReentrantLock) this.f6966f.get(str);
        if (reentrantLock != null) {
            return reentrantLock;
        }
        reentrantLock = new ReentrantLock();
        this.f6966f.put(str, reentrantLock);
        return reentrantLock;
    }

    AtomicBoolean m11138c() {
        return this.f6967g;
    }

    Object m11139d() {
        return this.f6970j;
    }

    boolean m11140e() {
        return this.f6968h.get();
    }

    boolean m11141f() {
        return this.f6969i.get();
    }
}
