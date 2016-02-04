package app.events.group;

import app.messaging.group.GroupListener;

public class GetGroupInfoEvent {
    GroupListener f2433a;
    String f2434b;

    public GroupListener m4876a() {
        return this.f2433a;
    }

    public String m4879b() {
        return this.f2434b;
    }

    public void m4877a(GroupListener groupListener) {
        this.f2433a = groupListener;
    }

    public void m4878a(String str) {
        this.f2434b = str;
    }
}
