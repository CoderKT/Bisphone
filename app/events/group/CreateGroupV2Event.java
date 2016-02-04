package app.events.group;

import app.messaging.group.GroupListener;
import app.xmpp.packet.groupv2.GroupElement;

public class CreateGroupV2Event {
    GroupElement f2429a;
    GroupListener f2430b;

    public GroupListener m4869a() {
        return this.f2430b;
    }

    public GroupElement m4872b() {
        return this.f2429a;
    }

    public void m4870a(GroupListener groupListener) {
        this.f2430b = groupListener;
    }

    public void m4871a(GroupElement groupElement) {
        this.f2429a = groupElement;
    }
}
