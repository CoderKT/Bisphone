package io.fabric.sdk.android.services.common;

import android.os.Process;

public abstract class BackgroundPriorityRunnable implements Runnable {
    protected abstract void m8049a();

    public final void run() {
        Process.setThreadPriority(10);
        m8049a();
    }
}
