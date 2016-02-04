package de.greenrobot.event;

final class Subscription {
    final Object f7992a;
    final SubscriberMethod f7993b;
    final int f7994c;
    volatile boolean f7995d;

    Subscription(Object obj, SubscriberMethod subscriberMethod, int i) {
        this.f7992a = obj;
        this.f7993b = subscriberMethod;
        this.f7994c = i;
        this.f7995d = true;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Subscription)) {
            return false;
        }
        Subscription subscription = (Subscription) obj;
        if (this.f7992a == subscription.f7992a && this.f7993b.equals(subscription.f7993b)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.f7992a.hashCode() + this.f7993b.f7989d.hashCode();
    }
}
