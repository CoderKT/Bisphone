package de.greenrobot.event;

final class PendingPostQueue {
    private PendingPost f7980a;
    private PendingPost f7981b;

    PendingPostQueue() {
    }

    synchronized void m12803a(PendingPost pendingPost) {
        if (pendingPost == null) {
            throw new NullPointerException("null cannot be enqueued");
        }
        if (this.f7981b != null) {
            this.f7981b.f7979c = pendingPost;
            this.f7981b = pendingPost;
        } else if (this.f7980a == null) {
            this.f7981b = pendingPost;
            this.f7980a = pendingPost;
        } else {
            throw new IllegalStateException("Head present, but no tail");
        }
        notifyAll();
    }

    synchronized PendingPost m12801a() {
        PendingPost pendingPost;
        pendingPost = this.f7980a;
        if (this.f7980a != null) {
            this.f7980a = this.f7980a.f7979c;
            if (this.f7980a == null) {
                this.f7981b = null;
            }
        }
        return pendingPost;
    }

    synchronized PendingPost m12802a(int i) {
        if (this.f7980a == null) {
            wait((long) i);
        }
        return m12801a();
    }
}
