package de.greenrobot.event;

public final class SubscriberExceptionEvent {
    public final EventBus f7982a;
    public final Throwable f7983b;
    public final Object f7984c;
    public final Object f7985d;

    public SubscriberExceptionEvent(EventBus eventBus, Throwable th, Object obj, Object obj2) {
        this.f7982a = eventBus;
        this.f7983b = th;
        this.f7984c = obj;
        this.f7985d = obj2;
    }
}
