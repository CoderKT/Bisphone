package app.xmpp.packet.common;

import org.jivesoftware.smack.packet.ExtensionElement;

public class ExpirationXE implements ExtensionElement {
    private long f5118a;

    public ExpirationXE(long j) {
        this.f5118a = j;
    }

    public String getNamespace() {
        return "bpn:common:v1";
    }

    public String getElementName() {
        return "expiration";
    }

    public CharSequence toXML() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append("expiration").append(" ").append("xmlns").append("='").append("bpn:common:v1").append("' ").append("v").append("='").append(this.f5118a).append("'/>");
        return stringBuilder;
    }
}
