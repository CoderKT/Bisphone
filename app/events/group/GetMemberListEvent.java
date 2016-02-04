package app.events.group;

import app.messaging.group.GroupListener;
import app.xmpp.packet.groupv2.MembersXE.MemberState;

public class GetMemberListEvent {
    GroupListener f2444a;
    String f2445b;
    MemberState f2446c;

    public MemberState m4892a() {
        return this.f2446c;
    }

    public String m4896b() {
        return this.f2445b;
    }

    public GroupListener m4897c() {
        return this.f2444a;
    }

    public void m4894a(MemberState memberState) {
        this.f2446c = memberState;
    }

    public void m4895a(String str) {
        this.f2445b = str;
    }

    public void m4893a(GroupListener groupListener) {
        this.f2444a = groupListener;
    }
}
