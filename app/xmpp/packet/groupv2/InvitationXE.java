package app.xmpp.packet.groupv2;

public class InvitationXE extends AbstractXEWithGroupElement {
    public InvitationXE(GroupElement groupElement) {
        super(groupElement);
    }

    public String getElementName() {
        return "invitation";
    }
}
