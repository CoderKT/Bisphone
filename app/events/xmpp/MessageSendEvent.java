package app.events.xmpp;

import app.common.entity.HistoryEntity;

public class MessageSendEvent {
    private HistoryEntity f2519a;
    private int f2520b;

    public MessageSendEvent(HistoryEntity historyEntity, int i) {
        this.f2519a = historyEntity;
        this.f2520b = i;
    }

    public HistoryEntity m4985a() {
        return this.f2519a;
    }

    public int m4986b() {
        return this.f2520b;
    }
}
