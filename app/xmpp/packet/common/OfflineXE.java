package app.xmpp.packet.common;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smackx.offline.packet.OfflineMessageRequest;

public class OfflineXE implements ExtensionElement {
    public String getNamespace() {
        return "bpn:common:v1";
    }

    public String getElementName() {
        return OfflineMessageRequest.ELEMENT;
    }

    public CharSequence toXML() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(OfflineMessageRequest.ELEMENT).append(" ").append("xmlns").append("='").append("bpn:common:v1").append("'/>");
        return stringBuilder;
    }
}
