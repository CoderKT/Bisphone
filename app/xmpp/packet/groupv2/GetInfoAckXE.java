package app.xmpp.packet.groupv2;

public class GetInfoAckXE extends AbstractXEWithGroupElement {
    public GetInfoAckXE(GroupElement groupElement) {
        super(groupElement);
    }

    public String getElementName() {
        return "get-info-ack";
    }
}
