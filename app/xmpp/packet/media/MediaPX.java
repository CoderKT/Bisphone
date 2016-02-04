package app.xmpp.packet.media;

import org.jivesoftware.smack.packet.ExtensionElement;

public class MediaPX implements ExtensionElement {
    public String getElementName() {
        return "media";
    }

    public String getNamespace() {
        return "http://bisphone.com/protocol/media";
    }

    public CharSequence toXML() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append("media").append(" ").append("xmlns").append("='").append("http://bisphone.com/protocol/media").append("' ").append("type").append("='").append("unknown").append("'/>");
        return stringBuilder;
    }
}
