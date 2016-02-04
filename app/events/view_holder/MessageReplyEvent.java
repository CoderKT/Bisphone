package app.events.view_holder;

import app.common.entity.HistoryEntity;

public class MessageReplyEvent {
    private HistoryEntity f2507a;
    private String f2508b;
    private boolean f2509c;
    private int f2510d;

    public MessageReplyEvent(HistoryEntity historyEntity, String str, boolean z, int i) {
        this.f2509c = false;
        this.f2507a = historyEntity;
        this.f2508b = str;
        this.f2509c = z;
        this.f2510d = i;
    }

    public int m4968a() {
        return this.f2510d;
    }

    public HistoryEntity m4969b() {
        return this.f2507a;
    }

    public String m4970c() {
        return this.f2508b;
    }
}
