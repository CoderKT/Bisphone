package app.xmpp.packet.groupv2;

public class CreateAckXE extends AbstractXE {
    private String f5130a;

    public CreateAckXE(String str) {
        this.f5130a = str;
    }

    public String getElementName() {
        return "create-ack";
    }

    public CharSequence toXML() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(getElementName()).append(" ").append("xmlns").append("='").append("bpn:group:v2").append("' ").append("id").append("='").append(this.f5130a).append("'/>");
        return stringBuilder;
    }
}
