package app.events.group;

import app.messaging.group.GroupListener;

public class RejectGroupInvitationEvent {
    String f2479a;
    GroupListener f2480b;

    public GroupListener m4940a() {
        return this.f2480b;
    }

    public String m4943b() {
        return this.f2479a;
    }

    public void m4941a(GroupListener groupListener) {
        this.f2480b = groupListener;
    }

    public void m4942a(String str) {
        this.f2479a = str;
    }
}
