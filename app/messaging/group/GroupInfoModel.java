package app.messaging.group;

import app.xmpp.packet.groupv2.GroupElement;

public class GroupInfoModel {
    GroupElement f3633a;
    String f3634b;
    GroupListener f3635c;

    public GroupInfoModel(GroupElement groupElement, String str) {
        this.f3633a = groupElement;
        this.f3634b = str;
    }

    public GroupElement m6247a() {
        return this.f3633a;
    }

    public String m6249b() {
        return this.f3634b;
    }

    public GroupListener m6250c() {
        return this.f3635c;
    }

    public void m6248a(GroupListener groupListener) {
        this.f3635c = groupListener;
    }
}
