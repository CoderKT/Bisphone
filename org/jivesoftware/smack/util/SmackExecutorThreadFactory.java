package org.jivesoftware.smack.util;

import java.util.concurrent.ThreadFactory;

public final class SmackExecutorThreadFactory implements ThreadFactory {
    private final int connectionCounterValue;
    private int count;
    private final String name;

    public SmackExecutorThreadFactory(int i, String str) {
        this.count = 0;
        this.connectionCounterValue = i;
        this.name = str;
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        StringBuilder append = new StringBuilder().append("Smack-").append(this.name).append(' ');
        int i = this.count;
        this.count = i + 1;
        thread.setName(append.append(i).append(" (").append(this.connectionCounterValue).append(")").toString());
        thread.setDaemon(true);
        return thread;
    }
}
