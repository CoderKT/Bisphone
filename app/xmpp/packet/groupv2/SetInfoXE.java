package app.xmpp.packet.groupv2;

public class SetInfoXE extends AbstractXEWithGroupElement {
    public SetInfoXE(GroupElement groupElement) {
        super(groupElement);
    }

    public String getElementName() {
        return "set-info";
    }

    public CharSequence toXML() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(getElementName()).append(" ").append("xmlns").append("='").append("bpn:group:v2").append("' ").append(">");
        stringBuilder.append(this.a.m7618g()).append("</").append(getElementName()).append(">");
        return stringBuilder;
    }
}
