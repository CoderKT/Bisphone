package app.events.group;

import app.messaging.group.GroupListener;
import app.xmpp.packet.groupv2.MemberElement;
import java.util.List;

public class KickMemberEvent {
    GroupListener f2466a;
    String f2467b;
    List<MemberElement> f2468c;

    public List<MemberElement> m4924a() {
        return this.f2468c;
    }

    public String m4928b() {
        return this.f2467b;
    }

    public GroupListener m4929c() {
        return this.f2466a;
    }

    public void m4926a(String str) {
        this.f2467b = str;
    }

    public void m4925a(GroupListener groupListener) {
        this.f2466a = groupListener;
    }

    public void m4927a(List<MemberElement> list) {
        this.f2468c = list;
    }
}
