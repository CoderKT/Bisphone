package app.events.group;

import app.messaging.group.GroupListener;
import app.xmpp.packet.groupv2.MemberElement;
import java.util.List;

public class InviteMemberToGroupEvent {
    GroupListener f2463a;
    String f2464b;
    List<MemberElement> f2465c;

    public List<MemberElement> m4918a() {
        return this.f2465c;
    }

    public String m4922b() {
        return this.f2464b;
    }

    public GroupListener m4923c() {
        return this.f2463a;
    }

    public void m4920a(String str) {
        this.f2464b = str;
    }

    public void m4919a(GroupListener groupListener) {
        this.f2463a = groupListener;
    }

    public void m4921a(List<MemberElement> list) {
        this.f2465c = list;
    }
}
