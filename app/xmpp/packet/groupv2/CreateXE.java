package app.xmpp.packet.groupv2;

public class CreateXE extends AbstractXEWithGroupElement {
    public CreateXE(GroupElement groupElement) {
        super(groupElement);
    }

    public String getElementName() {
        return "create";
    }
}
