package app.xmpp.packet.groupv2;

import org.jivesoftware.smackx.xhtmlim.XHTMLText;

public class EventXE extends AbstractXE {
    private Action f5137a;
    private String f5138b;
    private String f5139c;

    public enum Action {
        j,
        k,
        l,
        d,
        c;

        public static Action m7597a(String str) {
            try {
                return valueOf(str.toLowerCase());
            } catch (Exception e) {
                return null;
            }
        }
    }

    public EventXE(Action action, String str, String str2) {
        this.f5137a = action;
        this.f5138b = str;
        this.f5139c = str2;
    }

    public Action m7598a() {
        return this.f5137a;
    }

    public String m7599b() {
        return this.f5138b;
    }

    public String m7600c() {
        return this.f5139c;
    }

    public String getElementName() {
        return "event";
    }

    public CharSequence toXML() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(getElementName()).append(" ").append("xmlns").append("='").append("bpn:group:v2").append("' ").append(XHTMLText.f8584A).append("='").append(this.f5137a).append("' ").append("m").append("='").append(this.f5138b);
        if (this.f5139c != null) {
            stringBuilder.append("' ").append("b").append("='").append(this.f5139c);
        }
        stringBuilder.append("'/>");
        return stringBuilder;
    }
}
