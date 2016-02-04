package app.events.group;

import app.messaging.group.GroupListener;

public class DestroyEvent {
    String f2431a;
    GroupListener f2432b;

    public GroupListener m4873a() {
        return this.f2432b;
    }

    public void m4874a(GroupListener groupListener) {
        this.f2432b = groupListener;
    }

    public DestroyEvent(String str) {
        this.f2431a = str;
    }

    public String m4875b() {
        return this.f2431a;
    }
}
