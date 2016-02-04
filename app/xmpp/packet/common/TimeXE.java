package app.xmpp.packet.common;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smackx.time.packet.Time;

public class TimeXE implements ExtensionElement {
    private String f5119a;

    public TimeXE(String str) {
        this.f5119a = str;
    }

    public String m7579a() {
        return this.f5119a;
    }

    public String getNamespace() {
        return "bpn:common:v1";
    }

    public String getElementName() {
        return Time.ELEMENT;
    }

    public CharSequence toXML() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(Time.ELEMENT).append(" ").append("xmlns").append("='").append("bpn:common:v1").append("' ").append("v").append("='").append(this.f5119a).append("'/>");
        return stringBuilder;
    }
}
