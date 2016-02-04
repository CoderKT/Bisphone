package de.greenrobot.event;

import java.util.ArrayList;
import java.util.List;

final class PendingPost {
    private static final List<PendingPost> f7976d;
    Object f7977a;
    Subscription f7978b;
    PendingPost f7979c;

    static {
        f7976d = new ArrayList();
    }

    private PendingPost(Object obj, Subscription subscription) {
        this.f7977a = obj;
        this.f7978b = subscription;
    }

    static PendingPost m12799a(Subscription subscription, Object obj) {
        synchronized (f7976d) {
            int size = f7976d.size();
            if (size > 0) {
                PendingPost pendingPost = (PendingPost) f7976d.remove(size - 1);
                pendingPost.f7977a = obj;
                pendingPost.f7978b = subscription;
                pendingPost.f7979c = null;
                return pendingPost;
            }
            return new PendingPost(obj, subscription);
        }
    }

    static void m12800a(PendingPost pendingPost) {
        pendingPost.f7977a = null;
        pendingPost.f7978b = null;
        pendingPost.f7979c = null;
        synchronized (f7976d) {
            if (f7976d.size() < 10000) {
                f7976d.add(pendingPost);
            }
        }
    }
}
