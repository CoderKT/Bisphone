package app.events.group;

import app.xmpp.packet.groupv2.MembersXE.MemberState;
import java.util.ArrayList;

public class GetGroupMemberChanged {
    private String f2437a;
    private ArrayList<String> f2438b;
    private MemberState f2439c;

    public GetGroupMemberChanged(String str, ArrayList<String> arrayList, MemberState memberState) {
        this.f2437a = str;
        this.f2438b = arrayList;
        this.f2439c = memberState;
    }

    public ArrayList<String> m4884a() {
        return this.f2438b;
    }

    public MemberState m4885b() {
        return this.f2439c;
    }
}
