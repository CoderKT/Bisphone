package app.xmpp.packet.groupv2;

public abstract class AbstractXESimple extends AbstractXE {
    public CharSequence toXML() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(getElementName()).append(" ").append("xmlns").append("='").append("bpn:group:v2").append("'/>");
        return stringBuilder;
    }
}
