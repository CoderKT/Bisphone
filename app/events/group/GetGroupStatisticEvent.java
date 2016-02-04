package app.events.group;

import app.messaging.group.GroupListener;

public class GetGroupStatisticEvent {
    private String f2442a;
    private GroupListener f2443b;

    public GetGroupStatisticEvent(String str, GroupListener groupListener) {
        this.f2442a = str;
        this.f2443b = groupListener;
    }

    public String m4890a() {
        return this.f2442a;
    }

    public GroupListener m4891b() {
        return this.f2443b;
    }
}
