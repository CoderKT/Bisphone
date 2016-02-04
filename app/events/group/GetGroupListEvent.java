package app.events.group;

import app.messaging.group.GroupListener;
import app.xmpp.packet.groupv2.ListGroupsXE.GetGroupType;

public class GetGroupListEvent {
    GroupListener f2435a;
    GetGroupType f2436b;

    public GetGroupType m4880a() {
        return this.f2436b;
    }

    public GroupListener m4883b() {
        return this.f2435a;
    }

    public void m4881a(GroupListener groupListener) {
        this.f2435a = groupListener;
    }

    public void m4882a(GetGroupType getGroupType) {
        this.f2436b = getGroupType;
    }
}
