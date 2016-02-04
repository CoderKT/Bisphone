package app.xmpp.packet.groupv2;

public class InfoXE extends AbstractXEWithGroupElement {
    private String f5165b;

    public InfoXE(GroupElement groupElement, String str) {
        super(groupElement);
        this.f5165b = str;
    }

    public String getElementName() {
        return "info";
    }

    public String m7654b() {
        return this.f5165b;
    }

    public CharSequence toXML() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append("info").append(" ").append("xmlns").append("='").append("bpn:group:v2").append("' ").append("i").append("='").append(this.f5165b).append("'/>");
        return stringBuilder;
    }
}
