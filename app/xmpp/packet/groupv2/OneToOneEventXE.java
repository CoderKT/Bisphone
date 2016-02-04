package app.xmpp.packet.groupv2;

import org.jivesoftware.smackx.xhtmlim.XHTMLText;

public class OneToOneEventXE extends AbstractXEWithGroupElement {
    OneToOneEventAction f5189b;

    public enum OneToOneEventAction {
        i,
        k,
        d;

        public static OneToOneEventAction m7671a(String str) {
            try {
                return valueOf(str.toLowerCase());
            } catch (Exception e) {
                return null;
            }
        }
    }

    public OneToOneEventXE(GroupElement groupElement) {
        super(groupElement);
    }

    public String getElementName() {
        return "oto-event";
    }

    public OneToOneEventAction m7673b() {
        return this.f5189b;
    }

    public void m7672a(OneToOneEventAction oneToOneEventAction) {
        this.f5189b = oneToOneEventAction;
    }

    public CharSequence toXML() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append("oto-event").append(" ").append("xmlns").append("='").append("bpn:group:v2").append("' ").append(XHTMLText.f8584A).append("='").append(this.f5189b).append("'>").append(this.a.m7618g()).append("</").append("oto-event").append(">");
        return stringBuilder;
    }
}
