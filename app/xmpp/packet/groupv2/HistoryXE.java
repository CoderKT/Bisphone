package app.xmpp.packet.groupv2;

import org.jivesoftware.smackx.muc.packet.MUCInitialPresence.History;

public class HistoryXE extends AbstractXE {
    private long f5163a;
    private int f5164b;

    public HistoryXE(long j, int i) {
        this.f5163a = j;
        this.f5164b = i;
    }

    public long m7652a() {
        return this.f5163a;
    }

    public int m7653b() {
        return this.f5164b;
    }

    public String getElementName() {
        return History.ELEMENT;
    }

    public CharSequence toXML() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(History.ELEMENT).append(" ").append("xmlns").append("='").append("bpn:group:v2").append("' >").append("<query").append(" ").append("timestamp").append("='").append(this.f5163a).append("' ").append("window").append("='").append(this.f5164b).append("'/>");
        stringBuilder.append("</").append(History.ELEMENT).append(">");
        return stringBuilder;
    }
}
