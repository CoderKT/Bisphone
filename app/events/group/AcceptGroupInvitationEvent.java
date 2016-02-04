package app.events.group;

import app.messaging.group.GroupListener;

public class AcceptGroupInvitationEvent {
    String f2421a;
    GroupListener f2422b;

    public GroupListener m4856a() {
        return this.f2422b;
    }

    public String m4859b() {
        return this.f2421a;
    }

    public void m4857a(GroupListener groupListener) {
        this.f2422b = groupListener;
    }

    public void m4858a(String str) {
        this.f2421a = str;
    }
}
