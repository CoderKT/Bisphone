package app.xmpp.packet.groupv2;

public class KickAckXE extends AbstractXEWithMemberList {
    public String getElementName() {
        return "kick-ack";
    }
}
