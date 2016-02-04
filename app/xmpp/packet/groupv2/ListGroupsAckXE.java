package app.xmpp.packet.groupv2;

public class ListGroupsAckXE extends AbstractXEWithGroupList {
    public String getElementName() {
        return "get-groups-ack";
    }
}
