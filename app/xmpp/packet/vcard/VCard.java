package app.xmpp.packet.vcard;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;

public class VCard extends IQ {
    private String f5232a;
    private String f5233b;
    private String f5234c;

    public VCard() {
        super(org.jivesoftware.smackx.vcardtemp.packet.VCard.ELEMENT, org.jivesoftware.smackx.vcardtemp.packet.VCard.NAMESPACE);
    }

    public void m7724a(String str) {
        this.f5232a = str;
    }

    public String m7723a() {
        return this.f5233b;
    }

    public void m7726b(String str) {
        this.f5233b = str;
    }

    public String m7725b() {
        return this.f5234c;
    }

    public void m7727c(String str) {
        this.f5234c = str;
    }

    protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
        iQChildElementXmlStringBuilder.rightAngleBracket();
        if (this.f5232a != null) {
            iQChildElementXmlStringBuilder.openElement("JABBERID").append(this.f5232a).closeElement("JABBERID");
        }
        if (this.f5233b != null) {
            iQChildElementXmlStringBuilder.openElement("PHOTO").append(this.f5233b).closeElement("PHOTO");
        }
        if (this.f5234c != null) {
            iQChildElementXmlStringBuilder.openElement("NICKNAME").append(this.f5234c).closeElement("NICKNAME");
        }
        return iQChildElementXmlStringBuilder;
    }
}
