package app.events;

import app.common.entity.HistoryEntity;

public class NewMessageEvent {
    public String f2411a;
    public HistoryEntity f2412b;

    public NewMessageEvent(HistoryEntity historyEntity, String str) {
        this.f2411a = "";
        this.f2411a = str;
        this.f2412b = historyEntity;
    }
}
