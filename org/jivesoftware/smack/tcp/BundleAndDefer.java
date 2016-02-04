package org.jivesoftware.smack.tcp;

import java.util.concurrent.atomic.AtomicBoolean;

public class BundleAndDefer {
    private final AtomicBoolean isStopped;

    BundleAndDefer(AtomicBoolean atomicBoolean) {
        this.isStopped = atomicBoolean;
    }

    public void stopCurrentBundleAndDefer() {
        synchronized (this.isStopped) {
            if (this.isStopped.get()) {
                return;
            }
            this.isStopped.set(true);
            this.isStopped.notify();
        }
    }
}
