package org.jivesoftware.smack.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventManger<K, R, E extends Exception> {
    private final Map<K, Reference<R>> events;

    public interface Callback<E extends Exception> {
        void action();
    }

    class Reference<V> {
        volatile V eventResult;

        private Reference() {
        }
    }

    public EventManger() {
        this.events = new ConcurrentHashMap();
    }

    public R performActionAndWaitForEvent(K k, long j, Callback<E> callback) {
        Reference reference = new Reference();
        this.events.put(k, reference);
        try {
            synchronized (reference) {
                callback.action();
                reference.wait(j);
            }
            R r = reference.eventResult;
            return r;
        } finally {
            this.events.remove(k);
        }
    }

    public boolean signalEvent(K k, R r) {
        Reference reference = (Reference) this.events.get(k);
        if (reference == null) {
            return false;
        }
        reference.eventResult = r;
        synchronized (reference) {
            reference.notifyAll();
        }
        return true;
    }
}
