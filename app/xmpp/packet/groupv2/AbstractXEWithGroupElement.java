package app.xmpp.packet.groupv2;

public abstract class AbstractXEWithGroupElement extends AbstractXE {
    protected GroupElement f5127a;

    public GroupElement m7592a() {
        return this.f5127a;
    }

    public AbstractXEWithGroupElement(GroupElement groupElement) {
        this.f5127a = groupElement;
    }

    public CharSequence toXML() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(getElementName()).append(" ").append("xmlns").append("='").append("bpn:group:v2").append("'>").append(this.f5127a.m7618g()).append("</").append(getElementName()).append(">");
        return stringBuilder;
    }
}
