package app.xmpp.packet.groupv2;

public class MembersAckXE extends AbstractXEWithMemberList {
    public String getElementName() {
        return "members-ack";
    }
}
