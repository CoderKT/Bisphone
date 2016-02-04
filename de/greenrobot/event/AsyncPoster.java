package de.greenrobot.event;

class AsyncPoster implements Runnable {
    private final PendingPostQueue f7929a;
    private final EventBus f7930b;

    AsyncPoster(EventBus eventBus) {
        this.f7930b = eventBus;
        this.f7929a = new PendingPostQueue();
    }

    public void m12776a(Subscription subscription, Object obj) {
        this.f7929a.m12803a(PendingPost.m12799a(subscription, obj));
        this.f7930b.m12792b().execute(this);
    }

    public void run() {
        PendingPost a = this.f7929a.m12801a();
        if (a == null) {
            throw new IllegalStateException("No pending post available");
        }
        this.f7930b.m12789a(a);
    }
}
