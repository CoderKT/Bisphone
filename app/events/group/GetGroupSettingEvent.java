package app.events.group;

import app.messaging.group.GroupListener;

public class GetGroupSettingEvent {
    String f2440a;
    GroupListener f2441b;

    public String m4886a() {
        return this.f2440a;
    }

    public void m4888a(String str) {
        this.f2440a = str;
    }

    public GroupListener m4889b() {
        return this.f2441b;
    }

    public void m4887a(GroupListener groupListener) {
        this.f2441b = groupListener;
    }
}
