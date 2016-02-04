package org.jivesoftware.smack.util;

public class Async {
    public static Thread go(Runnable runnable) {
        Thread daemonThreadFrom = daemonThreadFrom(runnable);
        daemonThreadFrom.start();
        return daemonThreadFrom;
    }

    public static Thread go(Runnable runnable, String str) {
        Thread daemonThreadFrom = daemonThreadFrom(runnable);
        daemonThreadFrom.setName(str);
        daemonThreadFrom.start();
        return daemonThreadFrom;
    }

    public static Thread daemonThreadFrom(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        return thread;
    }
}
