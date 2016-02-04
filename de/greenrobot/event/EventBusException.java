package de.greenrobot.event;

public class EventBusException extends RuntimeException {
    public EventBusException(String str) {
        super(str);
    }

    public EventBusException(String str, Throwable th) {
        super(str, th);
    }
}
