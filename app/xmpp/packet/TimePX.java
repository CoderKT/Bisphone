package app.xmpp.packet;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smackx.time.packet.Time;

public class TimePX implements ExtensionElement {
    private long f5049a;

    public TimePX(long j) {
        this.f5049a = j;
    }

    public long m7528a() {
        return this.f5049a;
    }

    public String getElementName() {
        return Time.ELEMENT;
    }

    public String getNamespace() {
        return "http://bisphone.com/protocol/time";
    }

    public CharSequence toXML() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(Time.ELEMENT).append(" ").append("xmlns").append("='").append("http://bisphone.com/protocol/time").append("'>").append(this.f5049a).append("</").append(Time.ELEMENT).append(">");
        return stringBuilder;
    }
}
